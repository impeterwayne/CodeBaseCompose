package com.genesys.core.network.response

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.genesys.core.model.pagination.Meta
import com.genesys.core.model.template.TemplateCollections

@Keep
data class ResponseAITemplate(
    @SerializedName("data")
    val `data`: List<TemplateCollections> = listOf(),
    @SerializedName("meta")
    val meta: Meta = Meta()
)
