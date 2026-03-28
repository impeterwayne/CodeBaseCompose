package com.genesys.core.ui.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

object RecyclerViewBinding {
    @JvmStatic
    @BindingAdapter("adapter")
    fun bindAdapter(view : RecyclerView, adapter : RecyclerView.Adapter<*>)
    {
        view.adapter = adapter.apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }
    @JvmStatic
    @BindingAdapter("adapter")
    fun bindAdapter(view : ViewPager2, adapter : FragmentStateAdapter) {
        view.adapter = adapter
    }
}
