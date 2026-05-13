package com.genesys.core.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.genesys.core.designsystem.theme.AppTheme

@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier,
    title: String = "Loading",
    message: String = "Preparing the archive."
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        AppPanel(
            modifier = Modifier.fillMaxWidth(0.72f),
            tone = AppPanelTone.Frame,
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.sm)
        ) {
            AppText(
                text = title,
                style = AppTheme.typography.labelLarge,
                color = AppTheme.colorScheme.colorPrimary
            )
            AppDivider()
            AppText(
                text = message,
                style = AppTheme.typography.bodyLarge
            )
        }
    }
}
