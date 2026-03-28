package com.genesys.core.data.mmkv

import android.content.Context
import com.tencent.mmkv.MMKV

object MMKVData {

    private const val KEY_LAST_FETCH_TEMPLATE_TIME = "key_last_fetch_template_time"

    var lastFetchTemplateTime: Long
        get() = MyMMKV.valueOf(KEY_LAST_FETCH_TEMPLATE_TIME, 0L)
        set(value) {
            MyMMKV.setValue(KEY_LAST_FETCH_TEMPLATE_TIME, value)
        }

    object MyMMKV {
        private const val STORAGE_ID = "genesys_mmkv"
        private val storage by lazy { MMKV.mmkvWithID(STORAGE_ID) }

        fun init(context: Context) {
            MMKV.initialize(context)
        }

        fun get(): MMKV {
            return storage
        }

        fun contains(key: String): Boolean {
            return storage.containsKey(key)
        }

        fun remove(key: String) {
            storage.removeValueForKey(key)
        }

        @Suppress("UNCHECKED_CAST")
        fun <T> valueOf(key: String, defaultValue: T): T {
            return when (defaultValue) {
                is Boolean -> storage.decodeBool(key, defaultValue)
                is Int -> storage.decodeInt(key, defaultValue)
                is Float -> storage.decodeFloat(key, defaultValue)
                is Long -> storage.decodeLong(key, defaultValue)
                is Double -> storage.decodeDouble(key, defaultValue)
                is String -> storage.decodeString(key, defaultValue) ?: defaultValue
                is ByteArray -> storage.decodeBytes(key, defaultValue) ?: defaultValue
                else -> throw IllegalArgumentException("Generic type is not supported")
            } as T
        }

        fun <T> setValue(key: String, value: T) {
            when (value) {
                is Boolean -> storage.encode(key, value)
                is Int -> storage.encode(key, value)
                is Float -> storage.encode(key, value)
                is Long -> storage.encode(key, value)
                is Double -> storage.encode(key, value)
                is String -> storage.encode(key, value)
                is ByteArray -> storage.encode(key, value)
                else -> throw IllegalArgumentException("$value type is not supported")
            }
        }
    }
}
