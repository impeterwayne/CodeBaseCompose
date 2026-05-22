package com.genesys.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.genesys.core.database.entity.PokedexCollectionsEntity

@Dao
interface PokedexCollectionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokedexCollections(collections: List<PokedexCollectionsEntity>)

    @Query("SELECT * FROM PokedexCollectionsEntity ORDER BY sort ASC")
    suspend fun getAllPokedexCollections(): List<PokedexCollectionsEntity>

    @Query("DELETE FROM PokedexCollectionsEntity")
    suspend fun clearAllPokedexCollections()
}
