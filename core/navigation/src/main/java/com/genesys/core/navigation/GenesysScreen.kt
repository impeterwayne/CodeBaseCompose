package com.genesys.core.navigation

import android.os.Parcelable
import androidx.navigation3.runtime.NavKey
import kotlinx.parcelize.Parcelize

sealed interface GenesysScreen : NavKey, Parcelable {
    @Parcelize
    data object Templates : GenesysScreen

    @Parcelize
    data class TemplateDetail(
        val templateId: String
    ) : GenesysScreen

    @Parcelize
    data object Projects : GenesysScreen

    @Parcelize
    data object Inbox : GenesysScreen

    @Parcelize
    data object Settings : GenesysScreen
}
