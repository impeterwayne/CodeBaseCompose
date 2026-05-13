package com.genesys.feature.settings.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.genesys.feature.settings.R
import com.genesys.core.designsystem.component.AppChip
import com.genesys.core.designsystem.component.AppPageFrame
import com.genesys.core.designsystem.component.AppPanel
import com.genesys.core.designsystem.component.AppPanelTone
import com.genesys.core.designsystem.component.AppPrimaryButton
import com.genesys.core.designsystem.component.AppSectionHeader
import com.genesys.core.designsystem.component.AppSecondaryButton
import com.genesys.core.designsystem.component.AppText
import com.genesys.core.designsystem.theme.AppTheme
import com.genesys.core.model.settings.SettingGroup
import com.genesys.core.model.settings.SettingItem

private val settingGroups = listOf(
    SettingGroup(
        title = "Workspace",
        subtitle = "Operations",
        items = listOf(
            SettingItem(
                title = "Default project view",
                description = "Choose the landing view shown when opening a project workspace.",
                value = "Board",
                highlighted = true
            ),
            SettingItem(
                title = "Shared review mode",
                description = "Keep external review links enabled for current collaborators.",
                value = "Enabled",
                highlighted = true
            )
        )
    ),
    SettingGroup(
        title = "Notifications",
        subtitle = "Signal control",
        items = listOf(
            SettingItem(
                title = "Approval reminders",
                description = "Receive reminders when pending approvals are close to their due time.",
                value = "Every 2 hours",
                highlighted = false
            ),
            SettingItem(
                title = "Digest delivery",
                description = "Bundle low-priority updates into a single summary instead of individual pings.",
                value = "08:30 daily",
                highlighted = false
            )
        )
    ),
    SettingGroup(
        title = "Security",
        subtitle = "Access",
        items = listOf(
            SettingItem(
                title = "Session verification",
                description = "Require a fresh verification step before downloading client delivery assets.",
                value = "Required",
                highlighted = true
            ),
            SettingItem(
                title = "Device trust window",
                description = "How long a signed-in device stays trusted before a new verification challenge.",
                value = "14 days",
                highlighted = false
            )
        )
    )
)

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier
) {
    AppPageFrame(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(0.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(AppTheme.spacing.lg),
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.lg)
        ) {
            item {
                SettingsHero()
            }

            settingGroups.forEach { group ->
                item {
                    AppSectionHeader(
                        title = group.title,
                        subtitle = group.subtitle
                    )
                }

                items(
                    items = group.items,
                    key = { "${group.title}-${it.title}" }
                ) { setting ->
                    SettingCard(setting = setting)
                }
            }
        }
    }
}

@Composable
private fun SettingsHero() {
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

@Composable
private fun SettingCard(
    setting: SettingItem
) {
    AppPanel(
        tone = if (setting.highlighted) AppPanelTone.Raised else AppPanelTone.Frame
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.sm)
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
                        text = setting.title,
                        style = AppTheme.typography.titleLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    AppText(
                        text = setting.description,
                        style = AppTheme.typography.bodyLarge
                    )
                }

                AppChip(
                    text = setting.value,
                    selected = setting.highlighted
                )
            }

            AppText(
                text = stringResource(R.string.settings_edit_unavailable),
                style = AppTheme.typography.labelMedium,
                color = AppTheme.colorScheme.colorBorder
            )
        }
    }
}
