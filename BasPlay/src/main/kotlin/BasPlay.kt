package com.bdixrony.basplay

import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.utils.*
import org.jsoup.nodes.Document

class BasPlay : MainAPI() {
    override var mainUrl = "http://10.20.30.40/"
    override var name = "BAS PLAY"
    override var version = 1
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)
    override val hasMainPage = true

    override suspend fun getMainPage(page: Int, request: MainPageRequest): HomePageResponse {
        val document = app.get(mainUrl).document
        val items = mutableListOf<HomePageList>()
        
        // Parse main page for featured/recent content
        // Adjust selectors based on actual site structure
        val movies = document.select("a[href*=/movie/], a[href*=/video/], a[href*=/watch/], .movie-item, .video-item, .item").mapNotNull { element ->
            val href = element.attr("href")
            val title = element.text().trim().ifEmpty { element.attr("title") }
            if (href.isNotEmpty() && title.isNotEmpty() && title.length > 2) {
                val url = if (href.startsWith("http")) href else "$mainUrl$href"
                MovieSearchResponse(
                    name = title,
                    url = url,
                    apiName = name,
                    type = TvType.Movie
                )
            }
        }.take(20)
        
        if (movies.isNotEmpty()) {
            items.add(HomePageList("Movies", movies))
        }
        
        return HomePageResponse(items, hasNext = false)
    }

    override suspend fun search(query: String): List<SearchResponse> {
        // Try common search patterns
        val searchUrls = listOf(
            "$mainUrl/search?q=$query",
            "$mainUrl/search/$query",
            "$mainUrl/?s=$query",
            "$mainUrl/index.php?search=$query"
        )
        
        for (searchUrl in searchUrls) {
            try {
                val document = app.get(searchUrl).document
                val results = parseSearchResults(document)
                if (results.isNotEmpty()) {
                    return results
                }
            } catch (e: Exception) {
                continue
            }
        }
        
        return emptyList()
    }

    private fun parseSearchResults(document: Document): List<SearchResponse> {
        return document.select("a[href*=/movie/], a[href*=/video/], a[href*=/watch/], .movie-item, .video-item, .item, .result-item").mapNotNull { element ->
            val href = element.attr("href")
            val title = element.text().trim().ifEmpty { element.attr("title") }
            if (href.isNotEmpty() && title.isNotEmpty() && title.length > 2) {
                val url = if (href.startsWith("http")) href else "$mainUrl$href"
                MovieSearchResponse(
                    name = title,
                    url = url,
                    apiName = name,
                    type = TvType.Movie
                )
            }
        }
    }

    override suspend fun load(url: String): LoadResponse {
        val document = app.get(url).document
        
        val title = document.select("h1, h2, .title, .movie-title, .video-title").firstOrNull()?.text()?.trim() ?: "Unknown"
        val poster = document.select("img[src*=/poster/], img[src*=/thumb/], img[src*=/cover/], .poster img, .thumb img").firstOrNull()?.let {
            val src = it.attr("src")
            if (src.startsWith("http")) src else "$mainUrl$src"
        }
        val description = document.select(".description, .plot, .summary, .synopsis, p").firstOrNull()?.text()
        
        // Try to find video source
        val videoUrl = document.select("video source, source[src], iframe[src], a[href*=.mp4], a[href*=.mkv], a[href*=.avi]").firstOrNull()?.let { element ->
            when {
                element.tagName() == "source" -> element.attr("src")
                element.tagName() == "iframe" -> element.attr("src")
                element.attr("href").isNotEmpty() -> element.attr("href")
                else -> element.attr("src")
            }
        } ?: url
        
        return newMovieLoadResponse(title, url, TvType.Movie, videoUrl) {
            this.posterUrl = poster
            this.plot = description
        }
    }

    override suspend fun loadLinks(
        data: String,
        isCasting: Boolean,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ): Boolean {
        // If data is a direct video URL
        if (data.matches(Regex(".*\.(mp4|mkv|avi|m4v|mov)(\?.*)?$", RegexOption.IGNORE_CASE))) {
            callback.invoke(
                ExtractorLink(
                    source = name,
                    name = name,
                    url = data,
                    referer = mainUrl,
                    quality = Qualities.Unknown.value
                )
            )
            return true
        }
        
        // If data is an iframe/embed URL, try to extract video from it
        if (data.contains("iframe") || data.contains("embed") || data.contains("player")) {
            try {
                val document = app.get(data).document
                val videoUrls = extractVideoUrls(document)
                if (videoUrls.isNotEmpty()) {
                    videoUrls.forEach { videoUrl ->
                        callback.invoke(
                            ExtractorLink(
                                source = name,
                                name = name,
                                url = videoUrl,
                                referer = data,
                                quality = Qualities.Unknown.value
                            )
                        )
                    }
                    return true
                }
            } catch (e: Exception) {
                // Ignore and try next method
            }
        }
        
        // Otherwise try to extract from page
        try {
            val document = app.get(data).document
            val videoUrls = extractVideoUrls(document)
            
            videoUrls.forEach { videoUrl ->
                callback.invoke(
                    ExtractorLink(
                        source = name,
                        name = name,
                        url = videoUrl,
                        referer = mainUrl,
                        quality = Qualities.Unknown.value
                    )
                )
            }
            
            return videoUrls.isNotEmpty()
        } catch (e: Exception) {
            return false
        }
    }
    
    private fun extractVideoUrls(document: Document): List<String> {
        val urls = mutableListOf<String>()
        
        // Look for direct video sources
        document.select("video source, source[src*=/video/], source[src*=/media/]").forEach { source ->
            val src = source.attr("src")
            if (src.isNotEmpty() && !urls.contains(src)) {
                urls.add(if (src.startsWith("http")) src else "$mainUrl$src")
            }
        }
        
        // Look for video file links
        document.select("a[href*=.mp4], a[href*=.mkv], a[href*=.avi], a[href*=.m4v]").forEach { link ->
            val href = link.attr("href")
            if (href.isNotEmpty() && !urls.contains(href)) {
                urls.add(if (href.startsWith("http")) href else "$mainUrl$href")
            }
        }
        
        // Look for embedded players
        document.select("iframe[src], embed[src], object[data]").forEach { embed ->
            val src = embed.attr("src").ifEmpty { embed.attr("data") }
            if (src.isNotEmpty() && !urls.contains(src)) {
                urls.add(if (src.startsWith("http")) src else "$mainUrl$src")
            }
        }
        
        return urls
    }
}
