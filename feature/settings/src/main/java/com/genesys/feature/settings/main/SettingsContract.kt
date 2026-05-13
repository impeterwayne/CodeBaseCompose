package com.genesys.feature.settings.main

import com.genesys.core.common.base.mvi.UiState
import com.genesys.core.common.base.mvi.Action
import com.genesys.core.common.base.mvi.SideEffect
import com.genesys.core.model.settings.SettingGroup

data class SettingsUiState(
    val groups: List<SettingGroup> = emptyList()
) : UiState

sealed interface SettingsAction : Action

sealed interface SettingsSideEffect : SideEffect
