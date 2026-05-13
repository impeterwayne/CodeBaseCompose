package com.genesys.feature.projects.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.genesys.core.designsystem.component.AppPanel
import com.genesys.core.designsystem.component.AppPanelTone
import com.genesys.core.designsystem.component.AppPrimaryButton
import com.genesys.core.designsystem.component.AppSecondaryButton
import com.genesys.core.designsystem.component.AppText
import com.genesys.core.designsystem.theme.AppTheme
import com.genesys.core.model.projects.ProjectMetric
import com.genesys.feature.projects.R

@Composable
fun ProjectsHero(
    metrics: List<ProjectMetric>
) {
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
                metrics.forEach { metric ->
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
