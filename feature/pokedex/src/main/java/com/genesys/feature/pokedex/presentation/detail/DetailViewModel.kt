package com.genesys.feature.pokedex.presentation.detail

import androidx.lifecycle.SavedStateHandle
import com.genesys.core.common.base.BaseViewModel
import com.genesys.core.common.base.Result
import com.genesys.core.domain.usecase.pokedex.GetPokemonInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getPokemonInfoUseCase: GetPokemonInfoUseCase
) : BaseViewModel<DetailUiState, DetailSideEffect, DetailAction>() {

    private val pokedexId: String = savedStateHandle["pokedexId"] ?: ""

    override val container = container<DetailUiState, DetailSideEffect>(DetailUiState.Loading)

    init {
        loadPokemonDetail()
    }

    override fun onAction(action: DetailAction) {
        when (action) {
            is DetailAction.LoadPokemonDetail -> loadPokemonDetail()
        }
    }

    private fun loadPokemonDetail() {
        intent {
            if (pokedexId.isBlank()) {
                reduce { DetailUiState.Error("Invalid Pokemon Name") }
                return@intent
            }
            getPokemonInfoUseCase(pokedexId).collect { result ->
                when (result) {
                    is Result.Loading -> reduce {
                        DetailUiState.Loading
                    }
                    is Result.Success -> reduce {
                        DetailUiState.Success(result.data)
                    }
                    is Result.Error -> reduce {
                        DetailUiState.Error(result.msg ?: "Failed to load details")
                    }
                    is Result.Initial -> Unit
                }
            }
        }
    }
}
