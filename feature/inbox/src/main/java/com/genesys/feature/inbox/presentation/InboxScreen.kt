package com.genesys.feature.inbox.presentation

import androidx.compose.ui.tooling.preview.Preview
import com.genesys.feature.inbox.presentation.InboxUiState
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

import com.genesys.feature.inbox.presentation.components.InboxHero
import com.genesys.feature.inbox.presentation.components.InboxEmptyState
import com.genesys.feature.inbox.presentation.components.InboxThreadCard

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

@Preview
@Composable
private fun InboxScreenPreview() {
    AppTheme {
        InboxScreen(state = InboxUiState(), onAction = {})
    }
}
