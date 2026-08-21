package com.bdixrony.bokasoka

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class Bokasoka : MainAPI() {
    override var mainUrl = "http://bokasoka.net/"
    override var name = "Bokasoka"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
