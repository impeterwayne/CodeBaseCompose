package com.genesys.core.network.dto.pagination

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Pagination(
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("pageCount")
    val pageCount: Int = 0,
    @SerializedName("pageSize")
    val pageSize: Int = 0,
    @SerializedName("total")
    val total: Int = 0
)
