package com.genesys.core.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.genesys.core.designsystem.theme.GenesysTheme
import java.util.Locale

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
        GenesysPanel(
            modifier = Modifier.fillMaxWidth(0.72f),
            tone = GenesysPanelTone.Frame,
            verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm)
        ) {
            GenesysText(
                text = title.uppercase(Locale.ROOT),
                style = GenesysTheme.typography.labelLarge,
                color = GenesysTheme.colorScheme.colorPrimary
            )
            GenesysDivider()
            GenesysText(
                text = message,
                style = GenesysTheme.typography.bodyLarge
            )
        }
    }
}
