package com.genesys.core.common.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

abstract class BaseDialogFragment<VB>(
    private val inflate: Inflater<VB>
) : DialogFragment() where VB : ViewDataBinding {

    private var _viewBinding: VB? = null
    protected val viewBinding get() = _viewBinding!!

    abstract val TAG: String

    var isCreated: Boolean = false
        private set

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

        dialog?.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
            )
            val windowParams: WindowManager.LayoutParams = attributes
            windowParams.dimAmount = dimAmount()
            windowParams.flags = windowParams.flags or WindowManager.LayoutParams.FLAG_DIM_BEHIND
            attributes = windowParams
        }

        initViews()
        initObservers()
        initListeners()
    }

    abstract fun initViews()
    protected open fun initObservers() {}
    protected open fun initListeners() {}

    protected open fun dimAmount(): Float = 0.6f
    protected open fun width(): Float = 0.95f

    fun show(fragmentManager: FragmentManager) {
        if (isAdded || isVisible || isRemoving || isStateSaved) {
            return
        }
        this.show(fragmentManager, TAG)
    }

    fun setDialogCancelable(isCancelable: Boolean) {
        this.isCancelable = isCancelable
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isCreated = false
        _viewBinding = null
    }

    override fun onStart() {
        super.onStart()

        dialog?.window?.let { window ->
            val params: WindowManager.LayoutParams = window.attributes
            val displayMetrics = context?.resources?.displayMetrics
            params.width = (displayMetrics?.widthPixels?.times(width()))?.toInt() ?: params.width
            window.attributes = params
        }
    }
}
