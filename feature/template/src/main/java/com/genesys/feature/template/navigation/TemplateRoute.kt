package com.genesys.feature.template.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.genesys.feature.template.main.MainEvent
import com.genesys.feature.template.main.MainSideEffect
import com.genesys.feature.template.main.MainViewModel
import com.genesys.feature.template.main.TemplateScreen
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun TemplateRoute(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is MainSideEffect.OpenTemplate -> Unit
        }
    }

    LaunchedEffect(Unit) {
        viewModel.onEvent(MainEvent.LoadTemplates)
    }

    TemplateScreen(
        state = state,
        onRetry = { viewModel.onEvent(MainEvent.LoadTemplates) },
        onTemplateClick = { template ->
            viewModel.onEvent(MainEvent.OnTemplateClicked(template))
        },
        modifier = modifier
    )
}
