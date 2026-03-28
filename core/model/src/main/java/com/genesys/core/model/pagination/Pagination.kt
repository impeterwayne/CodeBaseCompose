package com.genesys.core.model.pagination

import com.squareup.moshi.Json
import androidx.annotation.Keep

@Keep
data class Pagination(
    @Json(name = "page")
    val page: Int = 0,
    @Json(name = "pageCount")
    val pageCount: Int = 0,
    @Json(name = "pageSize")
    val pageSize: Int = 0,
    @Json(name = "total")
    val total: Int = 0
)
