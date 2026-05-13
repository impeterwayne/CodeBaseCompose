package com.genesys.feature.template.main

import com.genesys.core.common.base.mvi.Action
import com.genesys.core.common.base.mvi.SideEffect
import com.genesys.core.common.base.mvi.UiState
import com.genesys.core.model.template.Template
import com.genesys.core.model.template.TemplateCollections

data class MainUiState(
    val templateCollections: List<TemplateCollections> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) : UiState

sealed interface MainAction : Action {
    data object LoadTemplates : MainAction
    data class OnTemplateClicked(val template: Template) : MainAction
}

sealed interface MainSideEffect : SideEffect {
    data class OpenTemplate(val templateId: String) : MainSideEffect
}
