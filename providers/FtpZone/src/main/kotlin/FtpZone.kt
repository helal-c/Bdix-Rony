package com.bdixrony.ftpzone

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class FtpZone : MainAPI() {
    override var mainUrl = "http://103.225.94.27/mediaserver/"
    override var name = "FTP ZONE"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
