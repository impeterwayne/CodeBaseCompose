package com.genesys.core.common.base

sealed class ResultFlow<T> {
    data class Success<T>(val data: T) : ResultFlow<T>()
    data class Error<T>(val msg: String? = null) : ResultFlow<T>()
    data class Loading<T>(val info: String = "") : ResultFlow<T>()
    class Initial<T>() : ResultFlow<T>()
}

inline fun <T> ResultFlow<T>.doOnSuccess(
    crossinline action: (data: T) -> Unit
) {
    if (this is ResultFlow.Success<T>) {
        action.invoke(data)
    }
}

inline fun <T> ResultFlow<T>.doOnLoading(
    crossinline action: (info: String) -> Unit
) {
    if (this is ResultFlow.Loading<T>) {
        action.invoke(info)
    }
}

inline fun <T> ResultFlow<T>.doOnError(
    crossinline action: (msg: String?) -> Unit
) {
    if (this is ResultFlow.Error<T>) {
        action.invoke(msg)
    }
}
