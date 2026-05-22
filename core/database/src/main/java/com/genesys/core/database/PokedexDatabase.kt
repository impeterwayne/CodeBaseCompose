package com.genesys.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.genesys.core.database.converters.PokedexListConverter
import com.genesys.core.database.dao.PokedexCollectionsDao
import com.genesys.core.database.entity.PokedexCollectionsEntity

@Database(
    entities = [PokedexCollectionsEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(value = [PokedexListConverter::class])
abstract class PokedexDatabase : RoomDatabase() {
    abstract fun pokedexCollectionsDao(): PokedexCollectionsDao
}
