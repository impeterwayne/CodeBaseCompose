package com.genesys.feature.projects.main

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.genesys.feature.projects.R
import com.genesys.core.designsystem.component.AppChip
import com.genesys.core.designsystem.component.AppPageFrame
import com.genesys.core.designsystem.component.AppPanel
import com.genesys.core.designsystem.component.AppPanelTone
import com.genesys.core.designsystem.component.AppPrimaryButton
import com.genesys.core.designsystem.component.AppSectionHeader
import com.genesys.core.designsystem.component.AppSecondaryButton
import com.genesys.core.designsystem.component.AppText
import com.genesys.core.designsystem.theme.AppTheme
import com.genesys.core.model.projects.ProjectMetric
import com.genesys.core.model.projects.ProjectOverview

import com.genesys.feature.projects.main.components.ProjectsHero
import com.genesys.feature.projects.main.components.MetricCard
import com.genesys.feature.projects.main.components.ProjectCard

@Composable
fun ProjectsScreen(
    state: ProjectsUiState,
    onAction: (ProjectsAction) -> Unit,
    modifier: Modifier = Modifier
) {

    AppPageFrame(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(0.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(AppTheme.spacing.lg),
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.lg)
        ) {
            item {
                ProjectsHero(metrics = state.metrics)
            }

            item {
                AppSectionHeader(
                    title = stringResource(R.string.projects_current_work_title),
                    subtitle = stringResource(R.string.projects_current_work_subtitle)
                )
            }

            items(
                items = state.overviews,
                key = { it.name }
            ) { project ->
                ProjectCard(project = project)
            }
        }
    }
}

@Preview
@Composable
private fun ProjectsScreenPreview() {
    AppTheme {
        ProjectsScreen(state = ProjectsUiState(), onAction = {})
    }
}
