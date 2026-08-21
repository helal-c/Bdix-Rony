package com.bdixrony.bdcinema

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class Bdcinema : MainAPI() {
    override var mainUrl = "http://10.253.253.250/"
    override var name = "BdCinema"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
