package com.genesys.feature.pokedex.presentation.list

import com.genesys.core.common.base.mvi.Action
import com.genesys.core.common.base.mvi.SideEffect
import com.genesys.core.common.base.mvi.UiState
import com.genesys.core.model.pokedex.Pokemon

data class PokedexUiState(
    val pokemonList: List<Pokemon> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadMoreLoading: Boolean = false,
    val searchQuery: String = "",
    val currentPage: Int = 0,
    val errorMessage: String? = null
) : UiState {
    val filteredPokemon: List<Pokemon>
        get() = if (searchQuery.isBlank()) {
            pokemonList
        } else {
            pokemonList.filter { it.name.contains(searchQuery, ignoreCase = true) }
        }
}

sealed interface PokedexAction : Action {
    data object LoadPokedex : PokedexAction
    data object LoadNextPage : PokedexAction
    data class OnSearchQueryChanged(val query: String) : PokedexAction
    data class OnPokemonClicked(val pokemon: Pokemon) : PokedexAction
}

sealed interface PokedexSideEffect : SideEffect {
    data class OpenPokedexDetail(val pokedexId: String) : PokedexSideEffect
}
