package com.genesys.core.network.dto.pokedex

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ResponsePokemonList(
    @SerializedName("count") val count: Int = 0,
    @SerializedName("next") val next: String? = null,
    @SerializedName("previous") val previous: String? = null,
    @SerializedName("results") val results: List<ResponsePokemonItem> = emptyList()
)

@Keep
data class ResponsePokemonItem(
    @SerializedName("name") val name: String = "",
    @SerializedName("url") val url: String = ""
)
