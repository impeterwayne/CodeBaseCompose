package com.genesys.core.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


abstract class BaseViewModel<Event : BaseViewModel.IEvent>() : ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { context, throwable ->
        println("IViewModel: with $context: $throwable")
    }

    private val _purchaseState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    internal var purchaseState: StateFlow<Boolean> = _purchaseState.asStateFlow()
    internal val purchaseStateValue: Boolean
        get() = purchaseState.value

    private val _networkState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    internal val networkState: StateFlow<Boolean> = _networkState.asStateFlow()
    internal val networkStateValue: Boolean
        get() = networkState.value

    fun setNetworkState(isConnected: Boolean) {
        _networkState.value = isConnected
    }

    protected fun launchBlock(
        dispatcher: CoroutineDispatcher = Dispatchers.Default,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return viewModelScope.launch(
            context = dispatcher + coroutineExceptionHandler,
            start = start,
            block = block
        )
    }

    abstract fun onEvent(state: Event)

    interface IEvent

    override fun onCleared() {
        super.onCleared()
    }
}


