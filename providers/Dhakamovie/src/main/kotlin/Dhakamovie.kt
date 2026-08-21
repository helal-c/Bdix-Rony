package com.bdixrony.dhakamovie

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.TvType

class Dhakamovie : MainAPI() {
    override var mainUrl = "http://dhakamovie.com/"
    override var name = "DhakaMovie"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Provider scaffold only.
    // Implement load/search/loadLinks using the site's documented/public API
    // or a parser you are authorized to use.
}
