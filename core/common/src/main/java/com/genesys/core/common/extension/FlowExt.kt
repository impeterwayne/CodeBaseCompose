package com.genesys.core.common.extension

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

fun <T> LifecycleOwner.collectLatestRepeatOnLifecycle(
    flow: Flow<T>,
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    action: suspend CoroutineScope.(T) -> Unit
): Job {
    return lifecycleScope.launch(Dispatchers.Main) {
        repeatOnLifecycle(lifecycleState) {
            flow.collectLatest {
                action.invoke(this@launch, it)
            }
        }
    }
}

fun <T> LifecycleOwner.collectLatestRepeatOnLifecycle(
    flow: Flow<T>,
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    delay: Long = 200,
    action: suspend CoroutineScope.(T) -> Unit
): Job {
    return lifecycleScope.launch(Dispatchers.Main) {
        repeatOnLifecycle(lifecycleState) {
            flow.onEach { delay(delay) }.collectLatest {
                action.invoke(this@launch, it)
            }
        }
    }
}

fun <T> LifecycleOwner.collectRepeatOnLifecycle(
    flow: Flow<T>,
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    action: suspend CoroutineScope.(T) -> Unit
): Job {
    return lifecycleScope.launch(Dispatchers.Main) {
        repeatOnLifecycle(lifecycleState) {
            flow.collect {
                action.invoke(this@launch, it)
            }
        }
    }
}

fun <T> LifecycleOwner.collectRepeatOnLifecycle(
    flow: Flow<T>,
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    delay: Long = 200,
    action: suspend CoroutineScope.(T) -> Unit
): Job {
    return lifecycleScope.launch(Dispatchers.Main) {
        repeatOnLifecycle(lifecycleState) {
            flow.onEach { delay(200) }.collect {
                action.invoke(this@launch, it)
            }
        }
    }
}
