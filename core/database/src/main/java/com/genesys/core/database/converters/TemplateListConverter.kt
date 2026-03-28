package com.genesys.core.database.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.genesys.core.model.template.Template
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject

@ProvidedTypeConverter
class TemplateListConverter @Inject constructor(moshi: Moshi) {
    private val type = Types.newParameterizedType(List::class.java, Template::class.java)
    private val adapter: JsonAdapter<List<Template>> = moshi.adapter(type)

    @TypeConverter
    fun fromString(value: String): List<Template>? {
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromList(list: List<Template>?): String {
        return adapter.toJson(list)
    }
}
