package com.genesys.core.model.pokedex

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokedexCollections(
    val code: String = "",
    val id: String = "",
    val pokemon: List<Pokemon> = listOf(),
    val name: String = "",
    val sort: Int = 0
) : Parcelable
