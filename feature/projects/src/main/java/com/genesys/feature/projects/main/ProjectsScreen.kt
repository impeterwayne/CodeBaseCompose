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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.genesys.core.designsystem.component.GenesysChip
import com.genesys.core.designsystem.component.GenesysPageFrame
import com.genesys.core.designsystem.component.GenesysPanel
import com.genesys.core.designsystem.component.GenesysPanelTone
import com.genesys.core.designsystem.component.GenesysPrimaryButton
import com.genesys.core.designsystem.component.GenesysSectionHeader
import com.genesys.core.designsystem.component.GenesysSecondaryButton
import com.genesys.core.designsystem.component.GenesysText
import com.genesys.core.designsystem.theme.GenesysTheme
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
    GenesysPageFrame(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(0.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(GenesysTheme.spacing.lg),
            verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.lg)
        ) {
            item {
                ProjectsHero()
            }

            item {
                GenesysSectionHeader(
                    title = "Current work",
                    subtitle = "Projects"
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
    GenesysPanel(
        tone = GenesysPanelTone.Heavy
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.md)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.xs)
            ) {
                GenesysText(
                    text = "Projects",
                    style = GenesysTheme.typography.headlineSmall,
                    color = GenesysTheme.colorScheme.colorTextOnPrimary
                )
                GenesysText(
                    text = "Three delivery lanes have pending milestones this week.",
                    style = GenesysTheme.typography.bodyLarge,
                    color = GenesysTheme.colorScheme.colorTextOnPrimary
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm)
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
                horizontalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm)
            ) {
                GenesysPrimaryButton(
                    text = "Create brief",
                    onClick = {},
                    modifier = Modifier.weight(1f)
                )
                GenesysSecondaryButton(
                    text = "View calendar",
                    onClick = {},
                    modifier = Modifier.weight(1f)
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
    GenesysPanel(
        modifier = modifier,
        tone = GenesysPanelTone.Frame
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.xs)
        ) {
            GenesysText(
                text = value,
                style = GenesysTheme.typography.headlineSmall
            )
            GenesysText(
                text = label,
                style = GenesysTheme.typography.labelMedium,
                color = GenesysTheme.colorScheme.colorBorder
            )
        }
    }
}

@Composable
private fun ProjectCard(
    project: ProjectOverview
) {
    GenesysPanel(
        tone = GenesysPanelTone.Raised
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.md)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.xs)
                ) {
                    GenesysText(
                        text = project.name,
                        style = GenesysTheme.typography.titleLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    GenesysText(
                        text = "${project.stage} • ${project.dueDate}",
                        style = GenesysTheme.typography.labelMedium,
                        color = GenesysTheme.colorScheme.colorBorder
                    )
                }

                GenesysChip(
                    text = project.riskLabel,
                    selected = project.highlighted
                )
            }

            GenesysText(
                text = project.summary,
                style = GenesysTheme.typography.bodyLarge
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                GenesysText(
                    text = project.lead,
                    style = GenesysTheme.typography.labelMedium,
                    color = GenesysTheme.colorScheme.colorBorder
                )
                GenesysText(
                    text = "Open board",
                    style = GenesysTheme.typography.labelMedium
                )
            }
        }
    }
}
