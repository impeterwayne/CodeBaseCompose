package com.genesys.feature.template.main

import androidx.lifecycle.ViewModel
import com.genesys.core.common.base.Result
import com.genesys.core.domain.usecase.template.GetAllTemplatesUseCase
import com.genesys.core.model.template.Template
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllTemplatesUseCase: GetAllTemplatesUseCase
) : ViewModel(), ContainerHost<MainUiState, MainSideEffect> {

    override val container = container<MainUiState, MainSideEffect>(MainUiState())

    fun onEvent(event: MainEvent) {
        when (event) {
            MainEvent.LoadTemplates -> loadTemplates()
            is MainEvent.OnTemplateClicked -> onTemplateClicked(event.template)
        }
    }

    private fun loadTemplates() {
        intent {
            if (state.isLoading) return@intent

            getAllTemplatesUseCase().collect { result ->
                when (result) {
                    is Result.Loading -> reduce {
                        state.copy(
                            isLoading = true,
                            errorMessage = null
                        )
                    }

                    is Result.Success -> reduce {
                        state.copy(
                            templateCollections = result.data,
                            isLoading = false,
                            errorMessage = null
                        )
                    }

                    is Result.Error -> reduce {
                        state.copy(
                            isLoading = false,
                            errorMessage = result.msg ?: "Failed to load templates"
                        )
                    }

                    is Result.Initial -> Unit
                }
            }
        }
    }

    private fun onTemplateClicked(template: Template) {
        intent {
            postSideEffect(MainSideEffect.OpenTemplate(template.id))
        }
    }
}
