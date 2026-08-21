package com.bdixrony.moviesworld

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class MoviesWorld : MainAPI() {
    override var mainUrl = "http://10.10.10.3/movies"
    override var name = "Movies World"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
