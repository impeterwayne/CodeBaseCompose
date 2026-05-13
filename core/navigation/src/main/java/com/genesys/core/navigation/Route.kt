package com.genesys.core.navigation

import android.os.Parcelable
import androidx.navigation3.runtime.NavKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

sealed interface Route : NavKey, Parcelable {
    @Serializable
    @Parcelize
    data object Templates : Route

    @Serializable
    @Parcelize
    data class TemplateDetail(
        val templateId: String
    ) : Route

    @Serializable
    @Parcelize
    data object Projects : Route

    @Serializable
    @Parcelize
    data object Inbox : Route

    @Serializable
    @Parcelize
    data object Settings : Route
}
