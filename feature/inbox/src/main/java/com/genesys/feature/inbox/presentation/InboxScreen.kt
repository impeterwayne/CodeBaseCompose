package com.genesys.feature.inbox.presentation

import androidx.compose.foundation.clickable
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
import com.genesys.core.model.inbox.InboxFilter
import com.genesys.core.model.inbox.InboxThreadUiModel

@Composable
fun InboxScreen(
    state: InboxUiState,
    onAction: (InboxAction) -> Unit,
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
                        GenesysSectionHeader(
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
                    text = "Inbox",
                    style = GenesysTheme.typography.headlineSmall,
                    color = GenesysTheme.colors.onPrimaryContainer
                )
                GenesysText(
                    text = state.heroMessage,
                    style = GenesysTheme.typography.bodyLarge,
                    color = GenesysTheme.colors.onPrimaryContainer
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm)
            ) {
                InboxFilter.entries.forEach { filter ->
                    GenesysChip(
                        text = filter.label,
                        selected = filter == state.selectedFilter,
                        modifier = Modifier.clickable {
                            onAction(InboxAction.SelectFilter(filter))
                        }
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm)
            ) {
                GenesysPrimaryButton(
                    text = "Open queue",
                    onClick = { onAction(InboxAction.FocusPriorityQueue) },
                    modifier = Modifier.weight(1f)
                )
                GenesysSecondaryButton(
                    text = if (state.totalUnreadCount > 0) "Mark all read" else "All read",
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
    GenesysPanel(
        tone = GenesysPanelTone.Frame
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.xs)
        ) {
            GenesysText(
                text = "No threads in ${selectedFilter.label.lowercase()}",
                style = GenesysTheme.typography.titleLarge
            )
            GenesysText(
                text = "Switch filters or wait for the next message batch to land.",
                style = GenesysTheme.typography.bodyLarge,
                color = GenesysTheme.colors.outline
            )
        }
    }
}

@Composable
private fun InboxThreadCard(
    thread: InboxThreadUiModel
) {
    GenesysPanel(
        tone = GenesysPanelTone.Raised
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                GenesysText(
                    text = thread.sender,
                    style = GenesysTheme.typography.labelLarge
                )
                GenesysText(
                    text = thread.time,
                    style = GenesysTheme.typography.labelMedium,
                    color = GenesysTheme.colors.outline
                )
            }

            GenesysText(
                text = thread.subject,
                style = GenesysTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            GenesysText(
                text = thread.preview,
                style = GenesysTheme.typography.bodyLarge
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm)
            ) {
                GenesysChip(
                    text = thread.category,
                    selected = thread.unread
                )
                GenesysChip(
                    text = if (thread.unread) "Unread" else "Read",
                    selected = thread.unread
                )
            }
        }
    }
}
