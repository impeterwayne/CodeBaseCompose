package com.genesys.feature.pokedex.presentation.list

import com.genesys.core.common.base.BaseViewModel
import com.genesys.core.common.base.Result
import com.genesys.core.domain.usecase.pokedex.GetAllPokedexUseCase
import com.genesys.core.model.pokedex.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class PokedexViewModel @Inject constructor(
    private val getAllPokedexUseCase: GetAllPokedexUseCase
) : BaseViewModel<PokedexUiState, PokedexSideEffect, PokedexAction>() {

    override val container = container<PokedexUiState, PokedexSideEffect>(PokedexUiState())

    init {
        loadPokedex()
    }

    override fun onAction(action: PokedexAction) {
        when (action) {
            PokedexAction.LoadPokedex -> loadPokedex()
            PokedexAction.LoadNextPage -> loadNextPage()
            is PokedexAction.OnSearchQueryChanged -> updateSearchQuery(action.query)
            is PokedexAction.OnPokemonClicked -> onPokemonClicked(action.pokemon)
        }
    }

    private fun loadPokedex() {
        intent {
            if (state.isLoading) return@intent

            getAllPokedexUseCase(page = 0).collect { result ->
                when (result) {
                    is Result.Loading -> reduce {
                        state.copy(
                            isLoading = true,
                            errorMessage = null
                        )
                    }

                    is Result.Success -> reduce {
                        val newPokemon = result.data.flatMap { it.pokemon }
                        state.copy(
                            pokemonList = newPokemon,
                            currentPage = 0,
                            isLoading = false,
                            errorMessage = null
                        )
                    }

                    is Result.Error -> reduce {
                        state.copy(
                            isLoading = false,
                            errorMessage = result.msg ?: "Failed to load Pokedex"
                        )
                    }

                    is Result.Initial -> Unit
                }
            }
        }
    }

    private fun loadNextPage() {
        intent {
            if (state.isLoadMoreLoading || state.isLoading) return@intent
            val nextPage = state.currentPage + 1

            getAllPokedexUseCase(page = nextPage).collect { result ->
                when (result) {
                    is Result.Loading -> reduce {
                        state.copy(isLoadMoreLoading = true)
                    }

                    is Result.Success -> reduce {
                        val newPokemon = result.data.flatMap { it.pokemon }
                        val combined = (state.pokemonList + newPokemon).distinctBy { it.name }
                        state.copy(
                            pokemonList = combined,
                            currentPage = nextPage,
                            isLoadMoreLoading = false
                        )
                    }

                    is Result.Error -> reduce {
                        state.copy(isLoadMoreLoading = false)
                    }

                    is Result.Initial -> Unit
                }
            }
        }
    }

    private fun updateSearchQuery(query: String) {
        intent {
            reduce {
                state.copy(searchQuery = query)
            }
        }
    }

    private fun onPokemonClicked(pokemon: Pokemon) {
        intent {
            postSideEffect(PokedexSideEffect.OpenPokedexDetail(pokemon.name))
        }
    }
}
