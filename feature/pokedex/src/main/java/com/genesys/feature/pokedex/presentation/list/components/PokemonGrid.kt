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
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.genesys.feature.pokedex.presentation.common.components.CustomCircularProgressIndicator
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
    val gridState = rememberLazyGridState()

    val shouldLoadMore = remember {
        derivedStateOf {
            val layoutInfo = gridState.layoutInfo
            val totalItemsCount = layoutInfo.totalItemsCount
            val lastVisibleItemIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            
            showLoadMore && !isLoadMoreLoading && totalItemsCount > 0 && lastVisibleItemIndex >= (totalItemsCount - 4)
        }
    }

    LaunchedEffect(shouldLoadMore.value) {
        if (shouldLoadMore.value) {
            onLoadNextPage()
        }
    }

    LazyVerticalGrid(
        state = gridState,
        columns = GridCells.Fixed(2),
        modifier = modifier,
        contentPadding = PaddingValues(
            top = AppTheme.spacing.md,
            bottom = AppTheme.spacing.lg
        ),
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
                    isLoading = isLoadMoreLoading
                )
            }
        }
    }
}

@Composable
private fun LoadMoreSection(
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    if (isLoading) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = AppTheme.spacing.lg),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomCircularProgressIndicator(
                modifier = Modifier.size(40.dp),
                strokeWidth = 4.dp
            )
        }
    }
}
