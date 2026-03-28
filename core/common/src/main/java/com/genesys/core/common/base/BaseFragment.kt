package com.genesys.core.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

typealias Inflater<VB> = (LayoutInflater, ViewGroup?, Boolean) -> VB

abstract class BaseFragment<VB>(private val inflate: Inflater<VB>) : Fragment()
        where VB : ViewBinding {

    private var _viewBinding: VB? = null
    protected val viewBinding: VB
        get() = _viewBinding!!

    var isCreated: Boolean = false
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupInit()
    }

    protected open fun setupInit() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflate(inflater, container, false).also { _viewBinding = it }.root.let {
        if (it.parent != null) {
            ((it.parent) as ViewGroup).removeView(it)
        }
        return@let it
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCreated = true

        initViews()
        initObservers()
        initListeners()
    }

    abstract fun initViews()
    protected open fun initObservers() = Unit
    protected open fun initListeners() = Unit

    protected fun withViewBindings(block: VB.() -> Unit) {
        with(viewBinding, block)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isCreated = false
        _viewBinding = null
    }
}
