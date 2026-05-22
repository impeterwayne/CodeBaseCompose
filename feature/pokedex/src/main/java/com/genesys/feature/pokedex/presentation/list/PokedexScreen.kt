package com.genesys.feature.pokedex.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.genesys.core.designsystem.component.AppPageFrame
import com.genesys.core.designsystem.component.ErrorState
import com.genesys.core.designsystem.component.LoadingIndicator
import com.genesys.core.designsystem.theme.AppTheme
import com.genesys.core.model.pokedex.Pokemon
import com.genesys.core.navigation.AppNavigator
import com.genesys.core.navigation.Route
import com.genesys.feature.pokedex.R
import com.genesys.feature.pokedex.presentation.list.components.PokemonGrid
import com.genesys.feature.pokedex.presentation.list.components.PokemonSearchBar
import com.genesys.feature.pokedex.presentation.list.components.PokedexHeader
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun PokedexRoute(
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

@Composable
fun PokedexScreen(
    state: PokedexUiState,
    onRetry: () -> Unit,
    onLoadNextPage: () -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onPokemonClick: (Pokemon) -> Unit,
    modifier: Modifier = Modifier
) {
    AppPageFrame(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.colorBgLayout),
        contentPadding = PaddingValues(0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = AppTheme.spacing.md)
        ) {
            PokedexHeader()

            PokemonSearchBar(
                query = state.searchQuery,
                onQueryChanged = onSearchQueryChanged
            )

            // Content States (Loading, Error, or Pokemon Grid)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                when {
                    state.isLoading && state.pokemonList.isEmpty() -> {
                        LoadingIndicator()
                    }

                    state.errorMessage != null && state.pokemonList.isEmpty() -> {
                        ErrorState(
                            message = state.errorMessage ?: stringResource(R.string.pokedex_error_generic),
                            onRetry = onRetry
                        )
                    }

                    else -> {
                        PokemonGrid(
                            pokemonList = state.filteredPokemon,
                            isLoadMoreLoading = state.isLoadMoreLoading,
                            showLoadMore = state.searchQuery.isBlank() && state.pokemonList.isNotEmpty(),
                            onLoadNextPage = onLoadNextPage,
                            onPokemonClick = onPokemonClick,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokedexScreenLightPreview() {
    AppTheme(darkTheme = false) {
        PokedexScreen(
            state = PokedexUiState(
                pokemonList = listOf(
                    Pokemon(page = 1, name = "bulbasaur", url = "https://pokeapi.co/api/v2/pokemon/1/"),
                    Pokemon(page = 1, name = "ivysaur", url = "https://pokeapi.co/api/v2/pokemon/2/"),
                    Pokemon(page = 1, name = "venusaur", url = "https://pokeapi.co/api/v2/pokemon/3/")
                ),
                isLoading = false,
                searchQuery = ""
            ),
            onRetry = {},
            onLoadNextPage = {},
            onSearchQueryChanged = {},
            onPokemonClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PokedexScreenDarkPreview() {
    AppTheme(darkTheme = true) {
        PokedexScreen(
            state = PokedexUiState(
                pokemonList = listOf(
                    Pokemon(page = 1, name = "bulbasaur", url = "https://pokeapi.co/api/v2/pokemon/1/"),
                    Pokemon(page = 1, name = "ivysaur", url = "https://pokeapi.co/api/v2/pokemon/2/"),
                    Pokemon(page = 1, name = "venusaur", url = "https://pokeapi.co/api/v2/pokemon/3/")
                ),
                isLoading = false,
                searchQuery = ""
            ),
            onRetry = {},
            onLoadNextPage = {},
            onSearchQueryChanged = {},
            onPokemonClick = {}
        )
    }
}
