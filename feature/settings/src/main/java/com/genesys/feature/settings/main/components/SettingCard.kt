package com.genesys.feature.settings.main.components

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.genesys.core.designsystem.component.AppChip
import com.genesys.core.designsystem.component.AppPanel
import com.genesys.core.designsystem.component.AppPanelTone
import com.genesys.core.designsystem.component.AppText
import com.genesys.core.designsystem.theme.AppTheme
import com.genesys.core.model.settings.SettingItem
import com.genesys.feature.settings.R

@Composable
fun SettingCard(
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

@Preview
@Composable
private fun SettingCardPreview() {
    AppTheme {
        SettingCard(
            setting = com.genesys.core.model.settings.SettingItem(
                title = "Dark Mode",
                description = "Enable dark theme",
                value = "On",
                highlighted = false
            )
        )
    }
}
