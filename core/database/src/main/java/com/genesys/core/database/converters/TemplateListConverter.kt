package com.genesys.core.database.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.genesys.core.model.template.Template
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

@ProvidedTypeConverter
class TemplateListConverter @Inject constructor(private val gson: Gson) {
    @TypeConverter
    fun fromString(value: String): List<Template>? {
        val listType = object : TypeToken<List<Template>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Template>?): String {
        return gson.toJson(list)
    }
}
