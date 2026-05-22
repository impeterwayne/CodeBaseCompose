package com.genesys.core.data.repository.pokedex

import com.genesys.core.common.base.Result
import com.genesys.core.domain.repository.pokedex.PokedexRepository
import com.genesys.core.model.pokedex.Pokemon
import com.genesys.core.model.pokedex.PokemonInfo
import com.genesys.core.model.pokedex.PokedexCollections
import com.genesys.core.network.service.ApiService
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PokedexRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : PokedexRepository {

    override fun getAllPokedex(page: Int): Flow<Result<List<PokedexCollections>>> = flow {
        emit(Result.Loading())

        val limit = 40
        val offset = page * limit
        val response = apiService.fetchPokemonList(limit = limit, offset = offset)

        response.suspendOnSuccess {
            val pokemonList = data.results.map { dto ->
                Pokemon(
                    page = page,
                    name = dto.name,
                    url = dto.url
                )
            }
            val collections = listOf(
                PokedexCollections(
                    id = "pokedex_page_$page",
                    name = "Pokedex Page ${page + 1}",
                    pokemon = pokemonList,
                    sort = page
                )
            )
            emit(Result.Success(collections))
        }.suspendOnFailure {
            emit(Result.Error(message()))
        }
    }.flowOn(Dispatchers.IO)

    override fun getPokemonInfo(name: String): Flow<Result<PokemonInfo>> = flow {
        emit(Result.Loading())

        val response = apiService.fetchPokemonInfo(name = name.lowercase())

        response.suspendOnSuccess {
            val dto = data
            val statsMap = dto.stats.associate { it.stat.name to it.baseStat }
            val pokemonInfo = PokemonInfo(
                id = dto.id,
                name = dto.name,
                height = dto.height,
                weight = dto.weight,
                baseExperience = dto.baseExperience,
                hp = statsMap["hp"] ?: 0,
                attack = statsMap["attack"] ?: 0,
                defense = statsMap["defense"] ?: 0,
                speed = statsMap["speed"] ?: 0,
                types = dto.types.map { it.type.name }
            )
            emit(Result.Success(pokemonInfo))
        }.suspendOnFailure {
            emit(Result.Error(message()))
        }
    }.flowOn(Dispatchers.IO)
}
