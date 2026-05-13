package com.genesys.core.network.dto.template

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.genesys.core.network.dto.pagination.Meta
import com.genesys.core.network.dto.template.ResponseTemplateCollections

@Keep
data class ResponseAITemplate(
    @SerializedName("data")
    val `data`: List<ResponseTemplateCollections> = listOf(),
    @SerializedName("meta")
    val meta: Meta = Meta()
)
