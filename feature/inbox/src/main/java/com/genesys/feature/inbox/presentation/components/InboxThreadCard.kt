package com.genesys.feature.inbox.presentation.components

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
import com.genesys.core.model.inbox.InboxThreadUiModel
import com.genesys.feature.inbox.R

@Composable
fun InboxThreadCard(
    thread: InboxThreadUiModel
) {
    AppPanel(
        tone = AppPanelTone.Raised
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.sm)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AppText(
                    text = thread.sender,
                    style = AppTheme.typography.labelLarge
                )
                AppText(
                    text = thread.time,
                    style = AppTheme.typography.labelMedium,
                    color = AppTheme.colorScheme.colorBorder
                )
            }

            AppText(
                text = thread.subject,
                style = AppTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            AppText(
                text = thread.preview,
                style = AppTheme.typography.bodyLarge
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.sm)
            ) {
                AppChip(
                    text = thread.category,
                    selected = thread.unread
                )
                AppChip(
                    text = if (thread.unread) {
                        stringResource(R.string.inbox_unread)
                    } else {
                        stringResource(R.string.inbox_read)
                    },
                    selected = thread.unread
                )
            }
        }
    }
}
