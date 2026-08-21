package com.bdixrony.ctgftp

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class CtgFtp : MainAPI() {
    override var mainUrl = "https://ftp.ctgfun.com/"
    override var name = "CTG FTP"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
