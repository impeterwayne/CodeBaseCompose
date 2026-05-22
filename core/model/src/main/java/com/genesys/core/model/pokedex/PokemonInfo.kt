package com.genesys.core.model.pokedex

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonInfo(
    val id: Int = 0,
    val name: String = "",
    val height: Int = 0,
    val weight: Int = 0,
    val baseExperience: Int = 0,
    val hp: Int = 0,
    val attack: Int = 0,
    val defense: Int = 0,
    val speed: Int = 0,
    val types: List<String> = emptyList()
) : Parcelable {

    fun getFormattedHeight(): String {
        val heightInMeters = height.toDouble() / 10.0
        return "$heightInMeters m"
    }

    fun getFormattedWeight(): String {
        val weightInKg = weight.toDouble() / 10.0
        return "$weightInKg kg"
    }

    fun getDisplayName(): String {
        return name.replaceFirstChar { it.uppercase() }
    }
}
