package com.genesys.core.network.dto.pokedex

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ResponsePokemonDetail(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("name") val name: String = "",
    @SerializedName("height") val height: Int = 0,
    @SerializedName("weight") val weight: Int = 0,
    @SerializedName("base_experience") val baseExperience: Int = 0,
    @SerializedName("stats") val stats: List<ResponseStatSlot> = emptyList(),
    @SerializedName("types") val types: List<ResponseTypeSlot> = emptyList()
)

@Keep
data class ResponseStatSlot(
    @SerializedName("base_stat") val baseStat: Int = 0,
    @SerializedName("stat") val stat: ResponseStatItem = ResponseStatItem()
)

@Keep
data class ResponseStatItem(
    @SerializedName("name") val name: String = ""
)

@Keep
data class ResponseTypeSlot(
    @SerializedName("type") val type: ResponseTypeItem = ResponseTypeItem()
)

@Keep
data class ResponseTypeItem(
    @SerializedName("name") val name: String = ""
)
