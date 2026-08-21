package com.bdixrony.sarail

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class Sarail : MainAPI() {
    override var mainUrl = "http://movies.sarail.net/"
    override var name = "SARAIL"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
