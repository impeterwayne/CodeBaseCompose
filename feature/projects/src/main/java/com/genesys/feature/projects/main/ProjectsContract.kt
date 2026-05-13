package com.genesys.feature.projects.main

import com.genesys.core.common.base.mvi.UiState
import com.genesys.core.common.base.mvi.Action
import com.genesys.core.common.base.mvi.SideEffect
import com.genesys.core.model.projects.ProjectMetric
import com.genesys.core.model.projects.ProjectOverview

data class ProjectsUiState(
    val metrics: List<ProjectMetric> = emptyList(),
    val overviews: List<ProjectOverview> = emptyList()
) : UiState

sealed interface ProjectsAction : Action

sealed interface ProjectsSideEffect : SideEffect
