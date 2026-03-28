package com.genesys.core.common.extension

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Gson.fromJsonType(json: String): T =
    fromJson(json, object : TypeToken<T>() {}.type)

inline fun <reified T> Gson.toJsonType(obj: T): String = toJson(obj)

inline fun <reified T> Gson.fromJsonList(json: String): List<T> {
    val typeToken = object : TypeToken<List<T>>() {}.type
    return fromJson(json, typeToken)
}

inline fun <reified T> T.clone(gson: Gson = Gson()): T {
    return gson.fromJsonType(json = gson.toJsonType(this))
}
