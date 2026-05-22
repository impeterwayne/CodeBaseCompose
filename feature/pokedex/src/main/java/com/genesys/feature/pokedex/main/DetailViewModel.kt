package com.genesys.feature.pokedex.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genesys.core.common.base.Result
import com.genesys.core.domain.usecase.pokedex.GetPokemonInfoUseCase
import com.genesys.core.model.pokedex.PokemonInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getPokemonInfoUseCase: GetPokemonInfoUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var pokemonName: String = savedStateHandle.get<String>("pokedexId").orEmpty()
 
     private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
     val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()
 
     init {
         if (pokemonName.isNotBlank()) {
             loadPokemonDetail()
         }
     }
 
     fun loadPokemonDetail() {
         if (pokemonName.isBlank()) {
             _uiState.value = DetailUiState.Error("Invalid Pokemon Name")
             return
         }
        viewModelScope.launch {
            getPokemonInfoUseCase(pokemonName).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        _uiState.value = DetailUiState.Loading
                    }
                    is Result.Success -> {
                        _uiState.value = DetailUiState.Success(result.data)
                    }
                    is Result.Error -> {
                        _uiState.value = DetailUiState.Error(result.msg ?: "Failed to load details")
                    }
                    is Result.Initial -> Unit
                }
            }
        }
    }
}

sealed interface DetailUiState {
    data object Loading : DetailUiState
    data class Success(val pokemonInfo: PokemonInfo) : DetailUiState
    data class Error(val message: String) : DetailUiState
}
