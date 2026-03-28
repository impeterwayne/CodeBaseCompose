package com.genesys.core.model.pagination

import com.squareup.moshi.Json
import androidx.annotation.Keep

@Keep
data class Meta(
    @Json(name = "pagination")
    val pagination: Pagination = Pagination()
)
