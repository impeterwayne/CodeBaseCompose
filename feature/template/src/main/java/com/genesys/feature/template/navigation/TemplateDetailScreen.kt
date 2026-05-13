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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import com.genesys.feature.template.R
import com.genesys.core.designsystem.component.AppPageFrame
import com.genesys.core.designsystem.component.AppPanel
import com.genesys.core.designsystem.component.AppPanelTone
import com.genesys.core.designsystem.component.AppText
import com.genesys.core.designsystem.theme.AppTheme

@Composable
fun TemplateDetailScreen(
    templateId: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    AppPageFrame(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.colorBgElevated),
        contentPadding = PaddingValues(AppTheme.spacing.md)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.md)
        ) {
            AppPanel(
                modifier = Modifier.fillMaxWidth(),
                tone = AppPanelTone.Raised,
                onClick = onBack,
                onClickLabel = stringResource(R.string.template_back_label),
                role = Role.Button
            ) {
                AppText(
                    text = stringResource(R.string.template_detail_back),
                    style = AppTheme.typography.labelLarge,
                    color = AppTheme.colorScheme.colorText,
                    modifier = Modifier.padding(AppTheme.spacing.md)
                )
            }

            AppPanel(
                modifier = Modifier.fillMaxWidth(),
                tone = AppPanelTone.Raised,
                contentPadding = PaddingValues(AppTheme.spacing.lg)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.sm)
                ) {
                    AppText(
                        text = stringResource(R.string.template_detail_title),
                        style = AppTheme.typography.headlineMedium,
                        color = AppTheme.colorScheme.colorText
                    )
                    AppText(
                        text = templateId,
                        style = AppTheme.typography.bodyLarge,
                        color = AppTheme.colorScheme.colorBorder
                    )
                }
            }
        }
    }
}
