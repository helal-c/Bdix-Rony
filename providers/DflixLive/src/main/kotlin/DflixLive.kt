package com.bdixrony.dflixlive

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class DflixLive : MainAPI() {
    override var mainUrl = "http://dflix.live/"
    override var name = "dflix.Live"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
