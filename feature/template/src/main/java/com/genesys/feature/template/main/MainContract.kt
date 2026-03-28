package com.genesys.feature.template.main

import com.genesys.core.model.template.Template
import com.genesys.core.model.template.TemplateCollections

data class MainUiState(
    val templateCollections: List<TemplateCollections> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

sealed interface MainEvent {
    data object LoadTemplates : MainEvent
    data class OnTemplateClicked(val template: Template) : MainEvent
}

sealed interface MainSideEffect {
    data class OpenTemplate(val templateId: String) : MainSideEffect
}
