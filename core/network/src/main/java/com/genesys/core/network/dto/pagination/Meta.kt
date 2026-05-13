package com.genesys.core.network.dto.pagination

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Meta(
    @SerializedName("pagination")
    val pagination: Pagination = Pagination()
)
