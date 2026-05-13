package com.genesys.core.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genesys.core.common.base.mvi.Action
import com.genesys.core.common.base.mvi.SideEffect
import com.genesys.core.common.base.mvi.UiState
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
import org.orbitmvi.orbit.ContainerHost

abstract class BaseViewModel<STATE : UiState, SIDE_EFFECT : SideEffect, ACTION : Action>() : ViewModel(), ContainerHost<STATE, SIDE_EFFECT> {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { context, throwable ->
        println("IViewModel: with $context: $throwable")
    }

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

    abstract fun onAction(action: ACTION)

    override fun onCleared() {
        super.onCleared()
    }
}


