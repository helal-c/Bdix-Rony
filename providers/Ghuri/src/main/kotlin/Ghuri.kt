package com.bdixrony.ghuri

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class Ghuri : MainAPI() {
    override var mainUrl = "http://103.96.104.6/"
    override var name = "Ghuri"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
