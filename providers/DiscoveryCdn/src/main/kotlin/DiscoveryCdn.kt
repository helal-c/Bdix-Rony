package com.bdixrony.discoverycdn

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class DiscoveryCdn : MainAPI() {
    override var mainUrl = "https://discoveryftp.net/"
    override var name = "Discovery CDN"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
