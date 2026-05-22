package com.genesys.core.network.dto.pokedex

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ResponsePokedexCollections(
    @SerializedName("code")
    val code: String = "",
    @SerializedName("id")
    val id: String = "",
    @SerializedName("items")
    val pokemon: List<ResponsePokemon> = listOf(),
    @SerializedName("name")
    val name: String = "",
    @SerializedName("sort")
    val sort: Int = 0
)
