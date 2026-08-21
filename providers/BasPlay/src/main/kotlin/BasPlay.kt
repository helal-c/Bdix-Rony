package com.bdixrony.basplay

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class BasPlay : MainAPI() {
    override var mainUrl = "http://10.20.30.40/"
    override var name = "BAS PLAY"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
