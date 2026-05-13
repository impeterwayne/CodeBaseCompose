package com.genesys.feature.inbox.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.genesys.core.navigation.AppNavigator
import com.genesys.core.navigation.Route
import com.genesys.feature.inbox.presentation.InboxScreen
import com.genesys.feature.inbox.presentation.InboxViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun InboxGraph(
    backStack: NavBackStack<NavKey>,
    navigator: AppNavigator,
    modifier: Modifier = Modifier,
    viewModel: InboxViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()
    val entries = entryProvider<NavKey> {
        entry<Route.Inbox> {
            InboxScreen(
                state = state,
                onAction = viewModel::onAction,
                modifier = modifier
            )
        }
    }

    NavDisplay(
        backStack = backStack,
        onBack = navigator::popIfPossible,
        entryProvider = entries,
        modifier = modifier
    )
}
