package com.redowan

import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.utils.*

class XtremexFtpProvider : MainAPI() {

    override var mainUrl = "https://xtremexbd.com"

    override var name = "Xtreme'x BDIX"

    override val supportedTypes = setOf(
        TvType.Movie,
        TvType.TvSeries,
        TvType.Anime
    )

    override var lang = "bn"

    override val hasMainPage = true

    override val hasDownloadSupport = false

    override val hasQuickSearch = false

    data class Server(
        val name: String,
        val url: String,
        val category: String
    )

    private val servers = listOf(

        Server(
            "Circle FTP",
            "http://main.circleftp.net/",
            "BDIX FTP"
        ),

        Server(
            "New Circle FTP",
            "http://new.circleftp.net/",
            "BDIX FTP"
        ),

        Server(
            "Discovery FTP",
            "https://discoveryftp.net/",
            "BDIX FTP"
        ),

        Server(
            "Discovery Movies",
            "https://movies.discoveryftp.net/",
            "Movies"
        ),

        Server(
            "Movie Haat",
            "https://moviehaat.net/",
            "Movies"
        ),

        Server(
            "Ihub",
            "http://ihub.live/",
            "Movies"
        ),

        Server(
            "IBCCL",
            "http://103.203.93.2/",
            "BDIX FTP"
        ),

        Server(
            "Ghuri",
            "http://103.96.104.6/",
            "BDIX FTP"
        ),

        Server(
            "FUN TIME",
            "http://172.20.21.22/",
            "BDIX FTP"
        ),

        Server(
            "Bokasoka",
            "http://bokasoka.net/",
            "Movies"
        ),

        Server(
            "Movies World",
            "http://10.10.10.3/movies",
            "Movies"
        ),

        Server(
            "CLOUD FTP",
            "http://172.19.178.179/",
            "BDIX FTP"
        ),

        Server(
            "FTP Media",
            "http://10.1.1.1/",
            "BDIX FTP"
        ),

        Server(
            "BAS PLAY",
            "http://10.20.30.40/",
            "BDIX FTP"
        ),

        Server(
            "ZFLIXBD",
            "http://zflixbd.com/",
            "Movies"
        ),

        Server(
            "DhakaMovie",
            "http://dhakamovie.com/",
            "Movies"
        ),

        Server(
            "CTGFUN",
            "https://movie.ctgfun.com/",
            "Movies"
        ),

        Server(
            "CTG FTP",
            "https://ftp.ctgfun.com/",
            "BDIX FTP"
        ),

        Server(
            "CINEPLEX BD",
            "http://cineplexbd.net/index.php",
            "Movies"
        ),

        Server(
            "POLYFLIX",
            "http://pollyflix.cineplexbd.net/",
            "Movies"
        ),

        Server(
            "BdCinema",
            "http://10.253.253.250/",
            "BDIX FTP"
        ),

        Server(
            "SebaIT",
            "http://103.195.1.50/",
            "BDIX FTP"
        ),

        Server(
            "BanglaTube",
            "http://www.banglatube.net/",
            "Movies"
        ),

        Server(
            "Jaltrapala",
            "http://jatrapala.com/",
            "Movies"
        ),

        Server(
            "ROYALFLIX",
            "http://103.112.150.230/",
            "Movies"
        ),

        Server(
            "TIMEPASS",
            "https://30.30.30.130/",
            "Movies"
        ),

        Server(
            "Flixhub.Live",
            "https://flixhub.net/",
            "Movies"
        ),

        Server(
            "dflix.Live",
            "http://dflix.live/",
            "Movies"
        ),

        Server(
            "FmFTP",
            "https://fmftp.net/",
            "BDIX FTP"
        ),

        Server(
            "HALUM",
            "http://halum.net/",
            "Movies"
        ),

        Server(
            "SARAIL",
            "http://movies.sarail.net/",
            "Movies"
        ),

        Server(
            "FTP ZONE",
            "http://103.225.94.27/mediaserver/",
            "BDIX FTP"
        ),

        Server(
            "Murgi Live",
            "http://murgi.live/",
            "Movies"
        ),

        Server(
            "NowHD TIME",
            "https://nowhdtime.com.bd/",
            "Movies"
        ),

        Server(
            "Relax Time",
            "http://10.100.100.10/",
            "BDIX FTP"
        ),

        Server(
            "ICC FTP",
            "http://10.16.100.244/dashboard.php?session=1&category=0",
            "BDIX FTP"
        ),

        Server(
            "Sam FTP",
            "http://172.16.50.4/",
            "BDIX FTP"
        )
    )

    override suspend fun getMainPage(
        page: Int,
        request: MainPageRequest
    ): HomePageResponse {

        val groups = servers.groupBy {
            it.category
        }

        val lists = groups.map { (category, items) ->

            HomePageList(
                category,
                items.map { server ->

                    newMovieSearchResponse(
                        name = server.name,
                        url = server.url,
                        type = TvType.Movie
                    )
                }
            )
        }

        return newHomePageResponse(
            lists,
            hasNext = false
        )
    }

    override suspend fun search(
        query: String
    ): List<SearchResponse> {

        return servers
            .filter {
                it.name.contains(
                    query,
                    ignoreCase = true
                )
            }
            .map {

                newMovieSearchResponse(
                    name = it.name,
                    url = it.url,
                    type = TvType.Movie
                )
            }
    }

    override suspend fun load(
        url: String
    ): LoadResponse {

        val server = servers.find {
            it.url == url
        }

        return newMovieLoadResponse(
            name = server?.name ?: "BDIX Server",
            url = url,
            type = TvType.Movie,
            dataUrl = url
        ) {

            plot = "Xtreme'x BDIX Local Media Server"
        }
    }

    override suspend fun loadLinks(
        data: String,
        isCasting: Boolean,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ): Boolean {

        return try {

            val doc = app.get(
                data,
                timeout = 15
            ).document

            val links = doc
                .select("a[href], video source, video")
                .mapNotNull { element ->

                    val url =
                        element.attr("href")
                            .ifBlank {
                                element.attr("src")
                            }

                    if (url.isBlank()) {
                        null
                    } else {
                        url
                    }
                }
                .filter { url ->

                    val lower =
                        url.lowercase()

                    lower.contains(".mp4") ||
                    lower.contains(".mkv") ||
                    lower.contains(".webm") ||
                    lower.contains(".m3u8") ||
                    lower.contains(".ts")
                }
                .distinct()

            if (links.isEmpty()) {
                return false
            }

            links.forEachIndexed { index, link ->

                val finalUrl =
                    resolveUrl(
                        data,
                        link
                    )

                val lower =
                    finalUrl.lowercase()

                val type =
                    if (
                        lower.contains(".m3u8") ||
                        lower.contains("m3u8")
                    ) {
                        ExtractorLinkType.M3U8
                    } else {
                        ExtractorLinkType.VIDEO
                    }

                callback(
                    ExtractorLink(
                        source = name,
                        name = "$name #${index + 1}",
                        url = finalUrl,
                        referer = data,
                        quality = Qualities.Unknown.value,
                        type = type
                    )
                )
            }

            true

        } catch (e: Exception) {

            false
        }
    }

    private fun resolveUrl(
        base: String,
        url: String
    ): String {

        return when {

            url.startsWith("http://") ||
            url.startsWith("https://") -> {
                url
            }

            url.startsWith("//") -> {
                "https:$url"
            }

            else -> {
                try {
                    java.net.URI(base)
                        .resolve(url)
                        .toString()
                } catch (e: Exception) {
                    url
                }
            }
        }
    }
}