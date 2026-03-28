package com.genesys.core.common.extension

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.core.content.IntentCompat
import androidx.core.os.BundleCompat
import java.io.Serializable

inline fun <reified T : Parcelable> Bundle.parcelableArrayListExtra(key: String): ArrayList<T>? {
    return BundleCompat.getParcelableArrayList(this, key, T::class.java)
}

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? {
    return BundleCompat.getParcelable(this, key, T::class.java)
}

inline fun <reified T : Serializable> Bundle.serializable(key: String): T? {
    return BundleCompat.getSerializable(this, key, T::class.java)
}

inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? {
    return IntentCompat.getParcelableExtra(this, key, T::class.java)
}

inline fun <reified T : Serializable> Intent.serializable(key: String): T? {
    return IntentCompat.getSerializableExtra(this, key, T::class.java)
}
