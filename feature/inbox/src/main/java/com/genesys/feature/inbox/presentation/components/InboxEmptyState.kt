package com.genesys.feature.inbox.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.genesys.core.designsystem.component.AppPanel
import com.genesys.core.designsystem.component.AppPanelTone
import com.genesys.core.designsystem.component.AppText
import com.genesys.core.designsystem.theme.AppTheme
import com.genesys.core.model.inbox.InboxFilter
import com.genesys.feature.inbox.R

@Composable
fun InboxEmptyState(
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
