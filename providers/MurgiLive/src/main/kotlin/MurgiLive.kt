package com.bdixrony.murgilive

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class MurgiLive : MainAPI() {
    override var mainUrl = "http://murgi.live"
    override var name = "Murgi live"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
