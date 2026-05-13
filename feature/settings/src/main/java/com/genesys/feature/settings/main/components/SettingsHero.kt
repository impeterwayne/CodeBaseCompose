package com.genesys.feature.settings.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.genesys.core.designsystem.component.AppChip
import com.genesys.core.designsystem.component.AppPanel
import com.genesys.core.designsystem.component.AppPanelTone
import com.genesys.core.designsystem.component.AppPrimaryButton
import com.genesys.core.designsystem.component.AppSecondaryButton
import com.genesys.core.designsystem.component.AppText
import com.genesys.core.designsystem.theme.AppTheme
import com.genesys.feature.settings.R

@Composable
fun SettingsHero() {
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
                    text = stringResource(R.string.settings_title),
                    style = AppTheme.typography.headlineSmall,
                    color = AppTheme.colorScheme.colorTextOnPrimary
                )
                AppText(
                    text = stringResource(R.string.settings_hero_message),
                    style = AppTheme.typography.bodyLarge,
                    color = AppTheme.colorScheme.colorTextOnPrimary
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.sm)
            ) {
                AppChip(text = stringResource(R.string.settings_workspace_live), selected = true)
                AppChip(text = stringResource(R.string.settings_2fa_enforced), selected = false)
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.sm)
            ) {
                AppPrimaryButton(
                    text = stringResource(R.string.settings_manage_team_unavailable),
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    enabled = false
                )
                AppSecondaryButton(
                    text = stringResource(R.string.settings_export_policy_unavailable),
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    enabled = false
                )
            }
        }
    }
}
