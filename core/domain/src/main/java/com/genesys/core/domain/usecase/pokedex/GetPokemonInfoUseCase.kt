package com.genesys.core.domain.usecase.pokedex

import com.genesys.core.common.base.Result
import com.genesys.core.domain.repository.pokedex.PokedexRepository
import com.genesys.core.model.pokedex.PokemonInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonInfoUseCase @Inject constructor(
    private val pokedexRepository: PokedexRepository
) {
    operator fun invoke(name: String): Flow<Result<PokemonInfo>> {
        return pokedexRepository.getPokemonInfo(name)
    }
}
