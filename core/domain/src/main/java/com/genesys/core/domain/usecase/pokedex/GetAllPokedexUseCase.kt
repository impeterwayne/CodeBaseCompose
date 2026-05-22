package com.genesys.core.domain.usecase.pokedex

import com.genesys.core.common.base.Result
import com.genesys.core.domain.repository.pokedex.PokedexRepository
import com.genesys.core.model.pokedex.PokedexCollections
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllPokedexUseCase @Inject constructor(
    private val pokedexRepository: PokedexRepository
) {
    operator fun invoke(page: Int = 0): Flow<Result<List<PokedexCollections>>> {
        return pokedexRepository.getAllPokedex(page)
    }
}
