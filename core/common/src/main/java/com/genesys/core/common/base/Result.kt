package com.genesys.core.common.base

sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error<T>(val msg: String? = null) : Result<T>()
    data class Loading<T>(val info: String = "") : Result<T>()
    class Initial<T>() : Result<T>()
}

inline fun <T> Result<T>.doOnSuccess(
    crossinline action: (data: T) -> Unit
) {
    if (this is Result.Success<T>) {
        action.invoke(data)
    }
}

inline fun <T> Result<T>.doOnLoading(
    crossinline action: (info: String) -> Unit
) {
    if (this is Result.Loading<T>) {
        action.invoke(info)
    }
}

inline fun <T> Result<T>.doOnError(
    crossinline action: (msg: String?) -> Unit
) {
    if (this is Result.Error<T>) {
        action.invoke(msg)
    }
}
