package com.genesys.feature.pokedex.presentation.detail

import com.genesys.core.common.base.BaseViewModel
import com.genesys.core.common.base.Result
import com.genesys.core.domain.usecase.pokedex.GetPokemonInfoUseCase
import com.genesys.feature.pokedex.R
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getPokemonInfoUseCase: GetPokemonInfoUseCase
) : BaseViewModel<DetailUiState, DetailSideEffect, DetailAction>() {

    private var pokedexId: String = ""

    override val container = container<DetailUiState, DetailSideEffect>(DetailUiState.Loading)

    fun loadPokemon(name: String) {
        if (pokedexId == name) return
        pokedexId = name
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
                reduce { DetailUiState.Error(messageResId = R.string.pokedex_invalid_name) }
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
                        DetailUiState.Error(
                            message = result.msg,
                            messageResId = if (result.msg == null) R.string.pokedex_failed_to_load_details else null
                        )
                    }
                    is Result.Initial -> Unit
                }
            }
        }
    }
}
