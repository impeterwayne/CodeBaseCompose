package com.genesys.feature.inbox.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.genesys.feature.inbox.R
import com.genesys.core.designsystem.component.AppChip
import com.genesys.core.designsystem.component.AppPageFrame
import com.genesys.core.designsystem.component.AppPanel
import com.genesys.core.designsystem.component.AppPanelTone
import com.genesys.core.designsystem.component.AppPrimaryButton
import com.genesys.core.designsystem.component.AppSectionHeader
import com.genesys.core.designsystem.component.AppSecondaryButton
import com.genesys.core.designsystem.component.AppText
import com.genesys.core.designsystem.theme.AppTheme
import com.genesys.core.model.inbox.InboxFilter
import com.genesys.core.model.inbox.InboxThreadUiModel

@Composable
fun InboxScreen(
    state: InboxUiState,
    onAction: (InboxAction) -> Unit,
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
                InboxHero(
                    state = state,
                    onAction = onAction
                )
            }

            if (state.visibleGroups.isEmpty()) {
                item {
                    InboxEmptyState(
                        selectedFilter = state.selectedFilter
                    )
                }
            } else {
                state.visibleGroups.forEach { group ->
                    item(key = "${group.id}-header") {
                        AppSectionHeader(
                            title = group.title,
                            subtitle = group.subtitle
                        )
                    }

                    items(
                        items = group.items,
                        key = { it.id }
                    ) { thread ->
                        InboxThreadCard(thread = thread)
                    }
                }
            }
        }
    }
}

@Composable
private fun InboxHero(
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

@Composable
private fun InboxEmptyState(
    selectedFilter: InboxFilter
) {
    AppPanel(
        tone = AppPanelTone.Frame
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.xs)
        ) {
            AppText(
                text = stringResource(R.string.inbox_no_threads, selectedFilter.label.lowercase()),
                style = AppTheme.typography.titleLarge
            )
            AppText(
                text = stringResource(R.string.inbox_empty_message),
                style = AppTheme.typography.bodyLarge,
                color = AppTheme.colorScheme.colorBorder
            )
        }
    }
}

@Composable
private fun InboxThreadCard(
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
