package com.genesys.feature.inbox.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.genesys.feature.inbox.presentation.InboxAction
import com.genesys.feature.inbox.presentation.InboxScreen
import com.genesys.feature.inbox.presentation.InboxViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun InboxRoute(
    modifier: Modifier = Modifier,
    viewModel: InboxViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()

    InboxScreen(
        state = state,
        onAction = viewModel::onAction,
        modifier = modifier
    )
}
