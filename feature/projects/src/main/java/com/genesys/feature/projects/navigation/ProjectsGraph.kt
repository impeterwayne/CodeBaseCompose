package com.genesys.feature.projects.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.genesys.core.navigation.AppNavigator
import com.genesys.core.navigation.Route
import com.genesys.feature.projects.main.ProjectsScreen

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.genesys.feature.projects.main.ProjectsViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun ProjectsGraph(
    backStack: NavBackStack<NavKey>,
    navigator: AppNavigator,
    modifier: Modifier = Modifier,
    viewModel: ProjectsViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()

    val entries = entryProvider<NavKey> {
        entry<Route.Projects> {
            ProjectsScreen(
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
