package com.bdixrony.iccftp

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class IccFtp : MainAPI() {
    override var mainUrl = "http://10.16.100.244/dashboard.php?session=1&category=0"
    override var name = "ICC FTP"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
