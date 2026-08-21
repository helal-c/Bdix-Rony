package com.bdixrony.ftpmedia

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class FtpMedia : MainAPI() {
    override var mainUrl = "http://10.1.1.1/"
    override var name = "FTP Media"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
