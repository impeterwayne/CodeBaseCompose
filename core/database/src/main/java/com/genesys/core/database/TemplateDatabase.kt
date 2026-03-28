package com.genesys.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.genesys.core.database.converters.TemplateListConverter
import com.genesys.core.database.dao.TemplateCollectionsDao
import com.genesys.core.database.entity.TemplateCollectionsEntity

@Database(
    entities = [TemplateCollectionsEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(value = [TemplateListConverter::class])
abstract class TemplateDatabase : RoomDatabase() {
    abstract fun templateCollectionsDao(): TemplateCollectionsDao
}
