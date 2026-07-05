@file:OptIn(ExperimentalFoundationStyleApi::class)

package com.genesys.core.designsystem.component

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.genesys.core.designsystem.theme.AppTheme

@Composable
fun ErrorState(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
    retryText: String = "Retry"
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        AppPanel(
            modifier = Modifier.fillMaxWidth(0.78f),
            tone = AppPanelTone.Error,
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.md)
        ) {
            AppText(
                text = "Archive Unavailable",
                style = AppTheme.typography.titleLarge,
                color = AppTheme.colorScheme.colorError
            )
            AppDivider()
            AppText(
                text = message,
                style = AppTheme.typography.bodyLarge
            )
            AppSecondaryButton(
                text = retryText,
                onClick = onRetry,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview
@Composable
private fun ErrorStatePreview() {
    AppTheme {
        ErrorState(message = "An error occurred", onRetry = {})
    }
}
