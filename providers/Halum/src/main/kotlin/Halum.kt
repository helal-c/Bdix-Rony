package com.bdixrony.halum

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class Halum : MainAPI() {
    override var mainUrl = "http://halum.net/"
    override var name = "HALUM"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
