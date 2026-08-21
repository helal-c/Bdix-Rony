package com.bdixrony.zflixbd

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class Zflixbd : MainAPI() {
    override var mainUrl = "http://zflixbd.com/"
    override var name = "ZFLIXBD"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
