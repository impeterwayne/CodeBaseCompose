package com.genesys.core.common.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentManager
import com.genesys.core.common.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialogFragment<T>(private val inflate: Inflater<T>) :
    BottomSheetDialogFragment() where T : ViewDataBinding {

    private var _viewbinding: T? = null
    protected val viewBinding get() = _viewbinding!!

    abstract val TAG: String

    var isCreated: Boolean = false
        private set

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            setCanceledOnTouchOutside(dismissWhenTouchOutside())
        }
    }

    protected open fun dismissWhenTouchOutside(): Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setStyle(STYLE_NORMAL, R.style.Base_Theme_Codebase)
        return inflate(inflater, container, false).also { _viewbinding = it }.root.let { rootView ->
            rootView.parent?.runCatching {
                (this as ViewGroup).removeView(rootView)
            }
            rootView
        }
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
    abstract fun initObservers()
    abstract fun initListeners()

    protected open fun dimAmount(): Float = 0.6f

    fun show(fragmentManager: FragmentManager) {
        if (this.isAdded || this.isRemoving || fragmentManager.isStateSaved) {
            return
        }
        this.show(fragmentManager, TAG)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewbinding = null
    }
}
