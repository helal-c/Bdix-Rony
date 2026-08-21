package com.bdixrony.flixhublive

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class FlixhubLive : MainAPI() {
    override var mainUrl = "https://flixhub.net/"
    override var name = "Flixhub.Live"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
