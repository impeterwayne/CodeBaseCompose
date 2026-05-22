package com.genesys.core.network.dto.pokedex

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.genesys.core.network.dto.pagination.Meta
import com.genesys.core.network.dto.pokedex.ResponsePokedexCollections

@Keep
data class ResponseAIPokedex(
    @SerializedName("data")
    val `data`: List<ResponsePokedexCollections> = listOf(),
    @SerializedName("meta")
    val meta: Meta = Meta()
)
