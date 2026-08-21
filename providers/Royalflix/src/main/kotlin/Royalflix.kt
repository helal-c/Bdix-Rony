package com.bdixrony.royalflix

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class Royalflix : MainAPI() {
    override var mainUrl = "http://103.112.150.230/"
    override var name = "ROYALFLIX"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
