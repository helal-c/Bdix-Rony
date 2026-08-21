package com.bdixrony.cloudftp

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class CloudFtp : MainAPI() {
    override var mainUrl = "http://172.19.178.179/"
    override var name = "Cloud FTP"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
