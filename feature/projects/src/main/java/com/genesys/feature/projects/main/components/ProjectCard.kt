package com.genesys.feature.projects.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.genesys.core.designsystem.component.AppChip
import com.genesys.core.designsystem.component.AppPanel
import com.genesys.core.designsystem.component.AppPanelTone
import com.genesys.core.designsystem.component.AppText
import com.genesys.core.designsystem.theme.AppTheme
import com.genesys.core.model.projects.ProjectOverview
import com.genesys.feature.projects.R

@Composable
fun ProjectCard(
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
