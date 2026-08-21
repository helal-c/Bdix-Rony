package com.bdixrony.fmftp

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class Fmftp : MainAPI() {
    override var mainUrl = "https://fmftp.net/"
    override var name = "FmFtp"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
