package com.genesys.feature.template.main

import com.genesys.core.common.base.BaseViewModel
import com.genesys.core.common.base.doOnError
import com.genesys.core.common.base.doOnLoading
import com.genesys.core.common.base.doOnSuccess
import com.genesys.core.domain.usecase.template.GetAllTemplatesUseCase
import com.genesys.core.model.template.Template
import com.genesys.core.model.template.TemplateCollections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllTemplatesUseCase: GetAllTemplatesUseCase
) : BaseViewModel<MainViewModel.MainEvent>() {

    private val _templateCollections = MutableStateFlow<List<TemplateCollections>>(emptyList())
    val templateCollections: StateFlow<List<TemplateCollections>> = _templateCollections.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    override fun onEvent(state: MainEvent) {
        when (state) {
            is MainEvent.LoadTemplates -> loadTemplates()
            is MainEvent.OnTemplateClicked -> onTemplateClicked(state.template)
        }
    }

    private fun loadTemplates() {
        launchBlock {
            getAllTemplatesUseCase().collect { result ->
                result.doOnLoading {
                    _isLoading.value = true
                    _errorMessage.value = null
                }
                result.doOnSuccess { data ->
                    _isLoading.value = false
                    _templateCollections.value = data
                }
                result.doOnError { msg ->
                    _isLoading.value = false
                    _errorMessage.value = msg ?: "Failed to load templates"
                }
            }
        }
    }

    private fun onTemplateClicked(template: Template) {
        // Handle template click - can be expanded for navigation, detail view, etc.
    }

    sealed class MainEvent : IEvent {
        data object LoadTemplates : MainEvent()
        data class OnTemplateClicked(val template: Template) : MainEvent()
    }
}
