package com.genesys.core.database.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.genesys.core.model.pokedex.Pokemon
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

@ProvidedTypeConverter
class PokedexListConverter @Inject constructor(private val gson: Gson) {
    @TypeConverter
    fun fromString(value: String): List<Pokemon>? {
        val listType = object : TypeToken<List<Pokemon>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Pokemon>?): String {
        return gson.toJson(list)
    }
}
