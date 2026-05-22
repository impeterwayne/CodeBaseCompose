package com.genesys.core.model.pokedex

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
    var page: Int = 0,
    val name: String = "",
    val url: String = ""
) : Parcelable {

    val index: String
        inline get() {
            val parts = url.split("/".toRegex()).dropLast(1)
            return parts.last()
        }

    val imageUrl: String
        inline get() {
            return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
        }

    fun getFormattedNumber(): String {
        return "#${index.padStart(3, '0')}"
    }

    fun getDisplayName(): String {
        return name.replaceFirstChar { it.uppercase() }
    }
}
