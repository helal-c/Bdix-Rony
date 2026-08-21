package com.bdixrony.polyflix

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class Polyflix : MainAPI() {
    override var mainUrl = "http://pollyflix.cineplexbd.net/"
    override var name = "POLYFLIX"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
