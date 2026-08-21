package com.bdixrony.cineplexbd

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class CineplexBd : MainAPI() {
    override var mainUrl = "http://cineplexbd.net/index.php"
    override var name = "CINEPLEX BD"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
