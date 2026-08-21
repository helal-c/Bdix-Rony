package com.bdixrony.ihub

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class Ihub : MainAPI() {
    override var mainUrl = "http://ihub.live/"
    override var name = "Ihub"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
