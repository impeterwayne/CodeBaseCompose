package com.genesys.core.network.service

import com.genesys.core.network.dto.pokedex.ResponsePokemonList
import com.genesys.core.network.dto.pokedex.ResponsePokemonDetail
import com.skydoves.sandwich.ApiResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Streaming
import retrofit2.http.Url

interface ApiService {

    @GET("https://pokeapi.co/api/v2/pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): ApiResponse<ResponsePokemonList>

    @GET("https://pokeapi.co/api/v2/pokemon/{name}")
    suspend fun fetchPokemonInfo(
        @Path("name") name: String
    ): ApiResponse<ResponsePokemonDetail>

}
