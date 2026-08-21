package com.bdixrony.discoverymovies

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class DiscoveryMovies : MainAPI() {
    override var mainUrl = "https://movies.discoveryftp.net/"
    override var name = "Discovery Movies"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
