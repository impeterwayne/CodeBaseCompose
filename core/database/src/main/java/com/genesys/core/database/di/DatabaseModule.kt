package com.genesys.core.database.di

import android.content.Context
import androidx.room.Room
import com.genesys.core.database.PokedexDatabase
import com.genesys.core.database.converters.PokedexListConverter
import com.genesys.core.database.dao.PokedexCollectionsDao
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext appContext: Context,
        pokedexListConverter: PokedexListConverter
    ): PokedexDatabase {
        return Room.databaseBuilder(
            appContext,
            PokedexDatabase::class.java,
            "pokedex_database.db"
        )
            .addTypeConverter(pokedexListConverter)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePokedexCollectionDao(pokedexDatabase: PokedexDatabase): PokedexCollectionsDao {
        return pokedexDatabase.pokedexCollectionsDao()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()
}
