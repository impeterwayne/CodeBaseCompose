package com.genesys.feature.template.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.genesys.core.navigation.GenesysNavigator
import com.genesys.core.navigation.GenesysScreen
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
fun TemplateRoute(
    backStack: NavBackStack<NavKey>,
    navigator: GenesysNavigator,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is MainSideEffect.OpenTemplate -> {
                navigator.navigate(GenesysScreen.TemplateDetail(sideEffect.templateId))
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.onEvent(MainEvent.LoadTemplates)
    }

    val entries = entryProvider<NavKey> {
        entry<GenesysScreen.Templates> {
            TemplateScreen(
                state = state,
                onRetry = { viewModel.onEvent(MainEvent.LoadTemplates) },
                onTemplateClick = { template ->
                    viewModel.onEvent(MainEvent.OnTemplateClicked(template))
                },
                modifier = modifier
            )
        }

        entry<GenesysScreen.TemplateDetail> { destination ->
            TemplateDetailRoute(
                templateId = destination.templateId,
                onBack = {
                    if (backStack.last() != GenesysScreen.Templates) {
                        navigator.popBackStack()
                    }
                },
                modifier = modifier
            )
        }
    }

    NavDisplay(
        backStack = backStack,
        onBack = {
            if (backStack.last() != GenesysScreen.Templates) {
                navigator.popBackStack()
            }
        },
        entryProvider = entries,
        modifier = modifier
    )
}
