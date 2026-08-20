package com.redowan

import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.utils.*

class XtremexTvProvider : MainAPI() {

    override var mainUrl = "https://xtreamcommunication.vercel.app"

    override var name = "Xtreme'x Live TV"

    override val supportedTypes = setOf(
        TvType.Live
    )

    override var lang = "bn"

    override val hasMainPage = true

    override val hasDownloadSupport = false

    override val hasQuickSearch = false

    data class TvServer(
        val name: String,
        val url: String,
        val category: String
    )

    private val servers = listOf(

        TvServer(
            "XTREMEX TV",
            "https://xtremextv.vercel.app/",
            "Featured"
        ),

        TvServer(
            "BDIPTV",
            "http://tv.bdiptv.net/",
            "BDIX TV"
        ),

        TvServer(
            "QUICK TV",
            "http://172.19.17.28/",
            "BDIX TV"
        ),

        TvServer(
            "CLOUD TV",
            "http://172.19.178.180/",
            "BDIX TV"
        ),

        TvServer(
            "BdCinema TV",
            "http://10.253.253.244/",
            "BDIX TV"
        ),

        TvServer(
            "LIVE SPORTS 1",
            "http://10.47.57.10/",
            "Sports"
        ),

        TvServer(
            "LIVE SPORTS 2",
            "http://172.16.200.211/",
            "Sports"
        ),

        TvServer(
            "Redforce Live",
            "http://redforce.live/",
            "General TV"
        ),

        TvServer(
            "Jatrapala Live TV",
            "http://jatrapala.com/live-tv.html",
            "General TV"
        ),

        TvServer(
            "BanglaTube TV",
            "http://172.50.50.8/",
            "General TV"
        ),

        TvServer(
            "Ideal TV",
            "http://172.16.60.2/",
            "General TV"
        ),

        TvServer(
            "Plusbox TV",
            "https://plusbox.tv/",
            "General TV"
        ),

        TvServer(
            "Nethome TV",
            "http://172.16.200.205/",
            "General TV"
        ),

        TvServer(
            "TV Portal",
            "http://198.195.239.50/",
            "General TV"
        ),

        TvServer(
            "FUN TIME TV",
            "http://172.20.21.22/live_tv.php?key=1",
            "General TV"
        ),

        TvServer(
            "Deltainfo IPTV",
            "http://iptv.deltainfonet.com/",
            "General TV"
        ),

        TvServer(
            "KSB NET TV",
            "http://tv.ksbnet.net/",
            "General TV"
        ),

        TvServer(
            "BAS NET TV",
            "http://10.99.99.99/",
            "BDIX TV"
        ),

        TvServer(
            "ANTBD TV",
            "http://172.17.50.112/",
            "BDIX TV"
        ),

        TvServer(
            "Cityplex TV",
            "http://live.cityplex.live/",
            "BDIX TV"
        ),

        TvServer(
            "Smart Box",
            "http://smartbox.digital/jwtv1/index.php",
            "BDIX TV"
        ),

        TvServer(
            "AYNA TV",
            "https://shopnojaal.ct.ws/ayna/?i=2",
            "BDIX TV"
        ),

        TvServer(
            "FTPBD Live",
            "http://ftpbdlive.com/",
            "BDIX TV"
        ),

        TvServer(
            "Cloud TV Box",
            "http://172.19.178.180/",
            "BDIX TV"
        ),

        TvServer(
            "Torongo Plus",
            "https://torongoplus.vercel.app/",
            "General TV"
        ),

        TvServer(
            "IMOTV",
            "https://imotv.net/",
            "General TV"
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

                    newLiveSearchResponse(
                        name = server.name,
                        url = server.url,
                        type = TvType.Live
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

                newLiveSearchResponse(
                    name = it.name,
                    url = it.url,
                    type = TvType.Live
                )
            }
    }

    override suspend fun load(
        url: String
    ): LoadResponse {

        val server = servers.find {
            it.url == url
        }

        return newLiveStreamLoadResponse(
            name = server?.name ?: "BDIX Live TV",
            url = url,
            dataUrl = url
        ) {

            plot = "Xtreme'x BDIX Live TV"
        }
    }

    override suspend fun loadLinks(
        data: String,
        isCasting: Boolean,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ): Boolean {

        return try {

            val response = app.get(
                data,
                timeout = 15
            )

            val doc = response.document

            val streams = mutableListOf<String>()

            doc.select(
                "video source, video, source"
            ).forEach { element ->

                val src = element.attr("src")

                if (src.isNotBlank()) {
                    streams.add(src)
                }
            }

            doc.select(
                "a[href]"
            ).forEach { element ->

                val href = element.attr("href")

                val lower = href.lowercase()

                if (
                    lower.contains(".m3u8") ||
                    lower.contains(".mp4") ||
                    lower.contains(".webm") ||
                    lower.contains(".ts")
                ) {
                    streams.add(href)
                }
            }

            val uniqueStreams = streams.distinct()

            if (uniqueStreams.isEmpty()) {

                val lowerData = data.lowercase()

                if (
                    lowerData.contains(".m3u8") ||
                    lowerData.contains(".mp4") ||
                    lowerData.contains(".webm") ||
                    lowerData.contains(".ts")
                ) {

                    callback(
                        ExtractorLink(
                            source = name,
                            name = "Direct Stream",
                            url = data,
                            referer = data,
                            quality = Qualities.Unknown.value,
                            type =
                                if (
                                    lowerData.contains(".m3u8")
                                ) {
                                    ExtractorLinkType.M3U8
                                } else {
                                    ExtractorLinkType.VIDEO
                                }
                        )
                    )

                    return true
                }

                return false
            }

            uniqueStreams.forEachIndexed { index, stream ->

                val finalUrl = resolveUrl(
                    data,
                    stream
                )

                val lower = finalUrl.lowercase()

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
                        name = "Live Stream #${index + 1}",
                        url = finalUrl,
                        referer = data,
                        quality = Qualities.P1080.value,
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