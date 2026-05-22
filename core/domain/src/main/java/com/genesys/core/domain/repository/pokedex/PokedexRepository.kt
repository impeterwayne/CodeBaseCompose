package com.genesys.core.domain.repository.pokedex

import com.genesys.core.common.base.Result
import com.genesys.core.model.pokedex.PokedexCollections
import com.genesys.core.model.pokedex.PokemonInfo
import kotlinx.coroutines.flow.Flow

interface PokedexRepository {
    fun getAllPokedex(page: Int = 0): Flow<Result<List<PokedexCollections>>>
    fun getPokemonInfo(name: String): Flow<Result<PokemonInfo>>
}
