package com.bdixrony.timepass

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class Timepass : MainAPI() {
    override var mainUrl = "https://30.30.30.130/"
    override var name = "TIMEPASS"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
