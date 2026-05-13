package com.genesys.feature.projects.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.genesys.core.designsystem.component.AppPanel
import com.genesys.core.designsystem.component.AppPanelTone
import com.genesys.core.designsystem.component.AppText
import com.genesys.core.designsystem.theme.AppTheme

@Composable
fun MetricCard(
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
