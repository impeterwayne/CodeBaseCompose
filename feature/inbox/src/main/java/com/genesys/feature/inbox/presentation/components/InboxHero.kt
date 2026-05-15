package com.genesys.feature.inbox.presentation.components

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import com.genesys.core.designsystem.component.AppChip
import com.genesys.core.designsystem.component.AppPanel
import com.genesys.core.designsystem.component.AppPanelTone
import com.genesys.core.designsystem.component.AppPrimaryButton
import com.genesys.core.designsystem.component.AppSecondaryButton
import com.genesys.core.designsystem.component.AppText
import com.genesys.core.designsystem.theme.AppTheme
import com.genesys.core.model.inbox.InboxFilter
import com.genesys.feature.inbox.R
import com.genesys.feature.inbox.presentation.InboxAction
import com.genesys.feature.inbox.presentation.InboxUiState

@Composable
fun InboxHero(
    state: InboxUiState,
    onAction: (InboxAction) -> Unit
) {
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
                    text = stringResource(R.string.inbox_title),
                    style = AppTheme.typography.headlineSmall,
                    color = AppTheme.colorScheme.colorTextOnPrimary
                )
                AppText(
                    text = state.heroMessage,
                    style = AppTheme.typography.bodyLarge,
                    color = AppTheme.colorScheme.colorTextOnPrimary
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.sm)
            ) {
                InboxFilter.entries.forEach { filter ->
                    AppChip(
                        text = filter.label,
                        selected = filter == state.selectedFilter,
                        onClick = { onAction(InboxAction.SelectFilter(filter)) },
                        onClickLabel = stringResource(R.string.inbox_select_filter_label),
                        role = Role.Tab
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.sm)
            ) {
                AppPrimaryButton(
                    text = stringResource(R.string.inbox_open_queue),
                    onClick = { onAction(InboxAction.FocusPriorityQueue) },
                    modifier = Modifier.weight(1f)
                )
                AppSecondaryButton(
                    text = if (state.totalUnreadCount > 0) {
                        stringResource(R.string.inbox_mark_all_read)
                    } else {
                        stringResource(R.string.inbox_all_read)
                    },
                    onClick = { onAction(InboxAction.MarkAllRead) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Preview
@Composable
private fun InboxHeroPreview() {
    AppTheme {
        InboxHero(state = com.genesys.feature.inbox.presentation.InboxUiState(), onAction = {})
    }
}
