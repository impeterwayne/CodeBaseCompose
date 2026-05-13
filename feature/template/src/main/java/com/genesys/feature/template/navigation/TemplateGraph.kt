package com.genesys.feature.template.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.genesys.core.navigation.AppNavigator
import com.genesys.core.navigation.Route
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.genesys.feature.template.main.MainEvent
import com.genesys.feature.template.main.MainSideEffect
import com.genesys.feature.template.main.MainViewModel
import com.genesys.feature.template.main.TemplateScreen
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun TemplateGraph(
    backStack: NavBackStack<NavKey>,
    navigator: AppNavigator,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is MainSideEffect.OpenTemplate -> {
                navigator.navigate(Route.TemplateDetail(sideEffect.templateId))
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.onEvent(MainEvent.LoadTemplates)
    }

    val entries = entryProvider<NavKey> {
        entry<Route.Templates> {
            TemplateScreen(
                state = state,
                onRetry = { viewModel.onEvent(MainEvent.LoadTemplates) },
                onTemplateClick = { template ->
                    viewModel.onEvent(MainEvent.OnTemplateClicked(template))
                },
                modifier = modifier
            )
        }

        entry<Route.TemplateDetail> { destination ->
            TemplateDetailScreen(
                templateId = destination.templateId,
                onBack = navigator::popIfPossible,
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
