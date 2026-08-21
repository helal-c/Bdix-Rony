package com.bdixrony.circleold

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class CircleOld : MainAPI() {
    override var mainUrl = "http://main.circleftp.net/"
    override var name = "Circle Old"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
