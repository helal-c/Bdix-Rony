package com.bdixrony.sebait

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class Sebait : MainAPI() {
    override var mainUrl = "http://103.195.1.50/"
    override var name = "SebaIT"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
