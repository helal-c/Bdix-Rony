package com.bdixrony.jaltrapala

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class Jaltrapala : MainAPI() {
    override var mainUrl = "http://jatrapala.com/"
    override var name = "Jaltrapala"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
