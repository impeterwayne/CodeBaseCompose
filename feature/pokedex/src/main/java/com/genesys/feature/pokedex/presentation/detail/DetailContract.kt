package com.genesys.feature.pokedex.presentation.detail

import com.genesys.core.common.base.mvi.Action
import com.genesys.core.common.base.mvi.SideEffect
import com.genesys.core.common.base.mvi.UiState
import com.genesys.core.model.pokedex.PokemonInfo

sealed interface DetailUiState : UiState {
    data object Loading : DetailUiState
    data class Success(val pokemonInfo: PokemonInfo) : DetailUiState
    data class Error(val message: String) : DetailUiState
}

sealed interface DetailAction : Action {
    data object LoadPokemonDetail : DetailAction
}

sealed interface DetailSideEffect : SideEffect
