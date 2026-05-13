package com.genesys.core.network.dto.template

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ResponseTemplateCollections(
    @SerializedName("code")
    val code: String = "",
    @SerializedName("id")
    val id: String = "",
    @SerializedName("items")
    val templates: List<ResponseTemplate> = listOf(),
    @SerializedName("name")
    val name: String = "",
    @SerializedName("sort")
    val sort: Int = 0
)
