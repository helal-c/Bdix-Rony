package com.bdixrony.ctgfun

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class Ctgfun : MainAPI() {
    override var mainUrl = "https://movie.ctgfun.com/"
    override var name = "CTGFUN"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
