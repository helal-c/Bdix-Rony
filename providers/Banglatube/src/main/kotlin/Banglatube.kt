package com.bdixrony.banglatube

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class Banglatube : MainAPI() {
    override var mainUrl = "http://www.banglatube.net/"
    override var name = "BanglaTube"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
