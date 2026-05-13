package com.genesys.feature.settings.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.genesys.core.navigation.AppNavigator
import com.genesys.core.navigation.Route
import com.genesys.feature.settings.main.SettingsScreen

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.genesys.feature.settings.main.SettingsViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun SettingsGraph(
    backStack: NavBackStack<NavKey>,
    navigator: AppNavigator,
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()

    val entries = entryProvider<NavKey> {
        entry<Route.Settings> {
            SettingsScreen(
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
