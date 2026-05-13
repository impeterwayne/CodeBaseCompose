package com.genesys.core.model.pagination

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Meta(
    @SerializedName("pagination")
    val pagination: Pagination = Pagination()
)
