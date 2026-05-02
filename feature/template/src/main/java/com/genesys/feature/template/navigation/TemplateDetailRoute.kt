package com.genesys.feature.template.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.genesys.core.designsystem.component.GenesysPageFrame
import com.genesys.core.designsystem.component.GenesysPanel
import com.genesys.core.designsystem.component.GenesysPanelTone
import com.genesys.core.designsystem.component.GenesysText
import com.genesys.core.designsystem.theme.GenesysTheme

@Composable
fun TemplateDetailRoute(
    templateId: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    GenesysPageFrame(
        modifier = modifier
            .fillMaxSize()
            .background(GenesysTheme.colorScheme.colorBgElevated),
        contentPadding = PaddingValues(GenesysTheme.spacing.md)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.md)
        ) {
            GenesysPanel(
                modifier = Modifier.fillMaxWidth(),
                tone = GenesysPanelTone.Raised,
                onClick = onBack
            ) {
                GenesysText(
                    text = "Back to templates",
                    style = GenesysTheme.typography.labelLarge,
                    color = GenesysTheme.colorScheme.colorText,
                    modifier = Modifier.padding(GenesysTheme.spacing.md)
                )
            }

            GenesysPanel(
                modifier = Modifier.fillMaxWidth(),
                tone = GenesysPanelTone.Raised,
                contentPadding = PaddingValues(GenesysTheme.spacing.lg)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm)
                ) {
                    GenesysText(
                        text = "Template detail",
                        style = GenesysTheme.typography.headlineMedium,
                        color = GenesysTheme.colorScheme.colorText
                    )
                    GenesysText(
                        text = templateId,
                        style = GenesysTheme.typography.bodyLarge,
                        color = GenesysTheme.colorScheme.colorBorder
                    )
                }
            }
        }
    }
}
