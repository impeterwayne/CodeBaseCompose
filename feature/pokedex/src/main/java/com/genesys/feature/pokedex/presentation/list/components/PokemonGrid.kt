package com.genesys.feature.pokedex.presentation.list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.genesys.core.designsystem.component.AppSecondaryButton
import com.genesys.core.designsystem.component.LoadingIndicator
import com.genesys.core.designsystem.theme.AppTheme
import com.genesys.core.model.pokedex.Pokemon

@Composable
fun PokemonGrid(
    pokemonList: List<Pokemon>,
    isLoadMoreLoading: Boolean,
    showLoadMore: Boolean,
    onLoadNextPage: () -> Unit,
    onPokemonClick: (Pokemon) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        contentPadding = PaddingValues(bottom = AppTheme.spacing.lg),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.md),
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.md)
    ) {
        items(pokemonList, key = { it.name }) { pokemon ->
            PokemonCard(
                pokemon = pokemon,
                onClick = { onPokemonClick(pokemon) }
            )
        }

        if (showLoadMore) {
            item(span = { GridItemSpan(2) }) {
                LoadMoreSection(
                    isLoading = isLoadMoreLoading,
                    onLoadNextPage = onLoadNextPage
                )
            }
        }
    }
}

@Composable
private fun LoadMoreSection(
    isLoading: Boolean,
    onLoadNextPage: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = AppTheme.spacing.lg),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isLoading) {
            LoadingIndicator(modifier = Modifier.size(40.dp))
        } else {
            AppSecondaryButton(
                text = "Load More Pokémon",
                onClick = onLoadNextPage,
                modifier = Modifier.fillMaxWidth(0.6f)
            )
        }
    }
}
