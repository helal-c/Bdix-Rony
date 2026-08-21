package com.bdixrony.funtime

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class FunTime : MainAPI() {
    override var mainUrl = "http://172.20.21.22/"
    override var name = "FUN TIME"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
