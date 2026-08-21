package com.bdixrony.ibccl

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class Ibccl : MainAPI() {
    override var mainUrl = "http://103.203.93.2/"
    override var name = "IBCCL"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
