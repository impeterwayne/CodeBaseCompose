package com.genesys.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.genesys.core.database.entity.TemplateCollectionsEntity

@Dao
interface TemplateCollectionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTemplateCollections(collections: List<TemplateCollectionsEntity>)

    @Query("SELECT * FROM TemplateCollectionsEntity ORDER BY sort ASC")
    suspend fun getAllTemplateCollections(): List<TemplateCollectionsEntity>

    @Query("DELETE FROM TemplateCollectionsEntity")
    suspend fun clearAllTemplateCollections()
}
