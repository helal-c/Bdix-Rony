package com.bdixrony.circlenew

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class CircleNew : MainAPI() {
    override var mainUrl = "http://new.circleftp.net/"
    override var name = "Circle New"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
