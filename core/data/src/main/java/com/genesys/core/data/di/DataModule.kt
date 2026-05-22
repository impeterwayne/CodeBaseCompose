package com.genesys.core.data.di

import com.genesys.core.data.repository.pokedex.PokedexRepositoryImpl
import com.genesys.core.domain.repository.pokedex.PokedexRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {
    @Binds
    fun bindPokedexRepository(
        pokedexRepositoryImpl: PokedexRepositoryImpl
    ): PokedexRepository
}
