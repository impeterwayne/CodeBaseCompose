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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.genesys.core.designsystem.component.GenesysChip
import com.genesys.core.designsystem.component.GenesysPageFrame
import com.genesys.core.designsystem.component.GenesysPanel
import com.genesys.core.designsystem.component.GenesysPanelTone
import com.genesys.core.designsystem.component.GenesysPrimaryButton
import com.genesys.core.designsystem.component.GenesysSectionHeader
import com.genesys.core.designsystem.component.GenesysSecondaryButton
import com.genesys.core.designsystem.component.GenesysText
import com.genesys.core.designsystem.theme.GenesysTheme

private data class SettingItem(
    val title: String,
    val description: String,
    val value: String,
    val highlighted: Boolean
)

private data class SettingGroup(
    val title: String,
    val subtitle: String,
    val items: List<SettingItem>
)

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
    GenesysPageFrame(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(0.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(GenesysTheme.spacing.lg),
            verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.lg)
        ) {
            item {
                SettingsHero()
            }

            settingGroups.forEach { group ->
                item {
                    GenesysSectionHeader(
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
    GenesysPanel(
        tone = GenesysPanelTone.Heavy
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.md)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.xs)
            ) {
                GenesysText(
                    text = "Settings",
                    style = GenesysTheme.typography.headlineSmall,
                    color = GenesysTheme.colors.onPrimaryContainer
                )
                GenesysText(
                    text = "Workspace controls are stable. One security change is queued for rollout this week.",
                    style = GenesysTheme.typography.bodyLarge,
                    color = GenesysTheme.colors.onPrimaryContainer
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm)
            ) {
                GenesysChip(text = "Workspace live", selected = true)
                GenesysChip(text = "2FA enforced", selected = false)
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm)
            ) {
                GenesysPrimaryButton(
                    text = "Manage team",
                    onClick = {},
                    modifier = Modifier.weight(1f)
                )
                GenesysSecondaryButton(
                    text = "Export policy",
                    onClick = {},
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun SettingCard(
    setting: SettingItem
) {
    GenesysPanel(
        tone = if (setting.highlighted) GenesysPanelTone.Raised else GenesysPanelTone.Frame
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.xs)
                ) {
                    GenesysText(
                        text = setting.title,
                        style = GenesysTheme.typography.titleLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    GenesysText(
                        text = setting.description,
                        style = GenesysTheme.typography.bodyLarge
                    )
                }

                GenesysChip(
                    text = setting.value,
                    selected = setting.highlighted
                )
            }

            GenesysText(
                text = "Edit preference",
                style = GenesysTheme.typography.labelMedium,
                color = GenesysTheme.colors.outline
            )
        }
    }
}
