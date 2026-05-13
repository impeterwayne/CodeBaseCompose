package com.genesys.feature.settings.main

import com.genesys.core.common.base.BaseViewModel
import com.genesys.core.domain.usecase.settings.GetSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getSettingsUseCase: GetSettingsUseCase
) : BaseViewModel<SettingsUiState, SettingsSideEffect, SettingsAction>() {

    override val container = container<SettingsUiState, SettingsSideEffect>(
        SettingsUiState(
            groups = getSettingsUseCase()
        )
    )

    override fun onAction(action: SettingsAction) {
        // No actions defined yet
    }
}
