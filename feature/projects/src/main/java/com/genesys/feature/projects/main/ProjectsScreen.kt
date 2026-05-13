package com.genesys.feature.projects.main

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

private val projectMetrics = listOf(
    ProjectMetric(label = "Active", value = "06"),
    ProjectMetric(label = "In review", value = "03"),
    ProjectMetric(label = "Due this week", value = "09")
)

private val projectOverviews = listOf(
    ProjectOverview(
        name = "Spring Campaign Launch",
        stage = "Production",
        dueDate = "Due Apr 02",
        lead = "Lead: Amelia",
        summary = "Final asset approvals and export presets are being locked for the launch batch.",
        riskLabel = "On track",
        highlighted = true
    ),
    ProjectOverview(
        name = "Creator Partnership Deck",
        stage = "Feedback",
        dueDate = "Due Apr 04",
        lead = "Lead: Jordan",
        summary = "Sales notes are merged. Legal review and pricing slides still need sign-off before handoff.",
        riskLabel = "Needs review",
        highlighted = false
    ),
    ProjectOverview(
        name = "Template Refresh Q2",
        stage = "Planning",
        dueDate = "Due Apr 08",
        lead = "Lead: Priya",
        summary = "The design system audit is complete and the first set of replacement layouts is queued.",
        riskLabel = "Research",
        highlighted = false
    )
)

@Composable
fun ProjectsScreen(
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
                ProjectsHero()
            }

            item {
                AppSectionHeader(
                    title = stringResource(R.string.projects_current_work_title),
                    subtitle = stringResource(R.string.projects_current_work_subtitle)
                )
            }

            items(
                items = projectOverviews,
                key = { it.name }
            ) { project ->
                ProjectCard(project = project)
            }
        }
    }
}

@Composable
private fun ProjectsHero() {
    AppPanel(
        tone = AppPanelTone.Heavy
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.md)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.xs)
            ) {
                AppText(
                    text = stringResource(R.string.projects_title),
                    style = AppTheme.typography.headlineSmall,
                    color = AppTheme.colorScheme.colorTextOnPrimary
                )
                AppText(
                    text = stringResource(R.string.projects_hero_message),
                    style = AppTheme.typography.bodyLarge,
                    color = AppTheme.colorScheme.colorTextOnPrimary
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.sm)
            ) {
                projectMetrics.forEach { metric ->
                    MetricCard(
                        label = metric.label,
                        value = metric.value,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.sm)
            ) {
                AppPrimaryButton(
                    text = stringResource(R.string.projects_create_brief_unavailable),
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    enabled = false
                )
                AppSecondaryButton(
                    text = stringResource(R.string.projects_calendar_unavailable),
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    enabled = false
                )
            }
        }
    }
}

@Composable
private fun MetricCard(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    AppPanel(
        modifier = modifier,
        tone = AppPanelTone.Frame
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.xs)
        ) {
            AppText(
                text = value,
                style = AppTheme.typography.headlineSmall
            )
            AppText(
                text = label,
                style = AppTheme.typography.labelMedium,
                color = AppTheme.colorScheme.colorBorder
            )
        }
    }
}

@Composable
private fun ProjectCard(
    project: ProjectOverview
) {
    AppPanel(
        tone = AppPanelTone.Raised
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.md)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.sm)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.xs)
                ) {
                    AppText(
                        text = project.name,
                        style = AppTheme.typography.titleLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    AppText(
                        text = "${project.stage} • ${project.dueDate}",
                        style = AppTheme.typography.labelMedium,
                        color = AppTheme.colorScheme.colorBorder
                    )
                }

                AppChip(
                    text = project.riskLabel,
                    selected = project.highlighted
                )
            }

            AppText(
                text = project.summary,
                style = AppTheme.typography.bodyLarge
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AppText(
                    text = project.lead,
                    style = AppTheme.typography.labelMedium,
                    color = AppTheme.colorScheme.colorBorder
                )
                AppText(
                    text = stringResource(R.string.projects_current_work_subtitle),
                    style = AppTheme.typography.labelMedium,
                    color = AppTheme.colorScheme.colorBorder
                )
            }
        }
    }
}
