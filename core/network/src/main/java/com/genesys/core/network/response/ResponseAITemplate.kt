package com.genesys.core.network.response

import com.squareup.moshi.Json
import androidx.annotation.Keep
import com.genesys.core.model.pagination.Meta
import com.genesys.core.model.template.TemplateCollections

@Keep
data class ResponseAITemplate(
    @Json(name = "data")
    val `data`: List<TemplateCollections> = listOf(),
    @Json(name = "meta")
    val meta: Meta = Meta()
)
