package com.genesys.core.navigation

import android.os.Parcelable
import androidx.navigation3.runtime.NavKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

sealed interface GenesysScreen : NavKey, Parcelable {
    @Serializable
    @Parcelize
    data object Templates : GenesysScreen

    @Serializable
    @Parcelize
    data class TemplateDetail(
        val templateId: String
    ) : GenesysScreen

    @Serializable
    @Parcelize
    data object Projects : GenesysScreen

    @Serializable
    @Parcelize
    data object Inbox : GenesysScreen

    @Serializable
    @Parcelize
    data object Settings : GenesysScreen
}
