package com.genesys.core.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.genesys.core.designsystem.theme.GenesysTheme

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
        GenesysPanel(
            modifier = Modifier.fillMaxWidth(0.78f),
            tone = GenesysPanelTone.Error,
            verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.md)
        ) {
            GenesysText(
                text = "Archive Unavailable",
                style = GenesysTheme.typography.titleLarge,
                color = GenesysTheme.colorScheme.colorError
            )
            GenesysDivider()
            GenesysText(
                text = message,
                style = GenesysTheme.typography.bodyLarge
            )
            GenesysSecondaryButton(
                text = retryText,
                onClick = onRetry,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
