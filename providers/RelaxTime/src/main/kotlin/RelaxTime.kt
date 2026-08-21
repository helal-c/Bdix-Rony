package com.bdixrony.relaxtime

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class RelaxTime : MainAPI() {
    override var mainUrl = "http://10.100.100.10/"
    override var name = "Relax Time"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
