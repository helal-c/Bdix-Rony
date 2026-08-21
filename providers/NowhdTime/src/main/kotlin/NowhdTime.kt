package com.bdixrony.nowhdtime

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class NowhdTime : MainAPI() {
    override var mainUrl = "https://nowhdtime.com.bd/"
    override var name = "NowHD TIME"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
