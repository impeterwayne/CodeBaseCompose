package com.genesys.core.navigation

import android.os.Parcelable
import androidx.navigation3.runtime.NavKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

sealed interface Route : NavKey, Parcelable {
    @Serializable
    @Parcelize
    data object Pokedex : Route

    @Serializable
    @Parcelize
    data class PokedexDetail(
        val pokedexId: String
    ) : Route

    @Serializable
    @Parcelize
    data object Feature1 : Route

    @Serializable
    @Parcelize
    data object Feature2 : Route

    @Serializable
    @Parcelize
    data object Feature3 : Route
}
