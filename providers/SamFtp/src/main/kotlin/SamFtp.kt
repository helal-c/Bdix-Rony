package com.bdixrony.samftp

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class SamFtp : MainAPI() {
    override var mainUrl = "http://172.16.50.4/"
    override var name = "Sam FTP"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
