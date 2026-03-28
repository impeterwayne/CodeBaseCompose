package com.genesys.core.database.di

import android.content.Context
import androidx.room.Room
import com.genesys.core.database.TemplateDatabase
import com.genesys.core.database.converters.TemplateListConverter
import com.genesys.core.database.dao.TemplateCollectionsDao
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
        templateListConverter: TemplateListConverter
    ): TemplateDatabase {
        return Room.databaseBuilder(
            appContext,
            TemplateDatabase::class.java,
            "remover_database.db"
        )
            .addTypeConverter(templateListConverter)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideTemplateCollectionDao(removerDatabase: TemplateDatabase): TemplateCollectionsDao {
        return removerDatabase.templateCollectionsDao()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}
