package com.genesys.feature.pokedex.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.genesys.core.navigation.AppNavigator
import com.genesys.core.navigation.Route
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.genesys.feature.pokedex.main.PokedexAction
import com.genesys.feature.pokedex.main.PokedexSideEffect
import com.genesys.feature.pokedex.main.PokedexViewModel
import com.genesys.feature.pokedex.main.PokedexDetailScreen
import com.genesys.feature.pokedex.main.PokedexScreen
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun PokedexGraph(
    backStack: NavBackStack<NavKey>,
    navigator: AppNavigator,
    modifier: Modifier = Modifier,
    viewModel: PokedexViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is PokedexSideEffect.OpenPokedexDetail -> {
                navigator.navigate(Route.PokedexDetail(sideEffect.pokedexId))
            }
        }
    }
    val entries = entryProvider<NavKey> {
        entry<Route.Pokedex> {
            PokedexScreen(
                state = state,
                onRetry = { viewModel.onAction(PokedexAction.LoadPokedex) },
                onLoadNextPage = { viewModel.onAction(PokedexAction.LoadNextPage) },
                onSearchQueryChanged = { query -> viewModel.onAction(PokedexAction.OnSearchQueryChanged(query)) },
                onPokemonClick = { pokemon ->
                    viewModel.onAction(PokedexAction.OnPokemonClicked(pokemon))
                },
                modifier = modifier
            )
        }

        entry<Route.PokedexDetail> { destination ->
            PokedexDetailScreen(
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
