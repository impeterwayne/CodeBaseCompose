package com.genesys.feature.projects.main

import com.genesys.core.common.base.BaseViewModel
import com.genesys.core.domain.usecase.projects.GetProjectMetricsUseCase
import com.genesys.core.domain.usecase.projects.GetProjectOverviewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ProjectsViewModel @Inject constructor(
    private val getProjectMetricsUseCase: GetProjectMetricsUseCase,
    private val getProjectOverviewsUseCase: GetProjectOverviewsUseCase
) : BaseViewModel<ProjectsUiState, ProjectsSideEffect, ProjectsAction>() {

    override val container = container<ProjectsUiState, ProjectsSideEffect>(
        ProjectsUiState(
            metrics = getProjectMetricsUseCase(),
            overviews = getProjectOverviewsUseCase()
        )
    )

    override fun onAction(action: ProjectsAction) {
        // No actions defined yet
    }
}
