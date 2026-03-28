package com.genesys.core.common.extension

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

inline fun <T : Any> diffItemCallback(
    crossinline items: (old: T, new: T) -> Boolean = { old, new -> old === new },
    crossinline contents: (old: T, new: T) -> Boolean = { old, new -> old == new },
    crossinline getChangePayload: (old: T, new: T) -> Any? = { old, new -> null }
): DiffUtil.ItemCallback<T> {
    return object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T) = items(oldItem, newItem)
        override fun areContentsTheSame(oldItem: T, newItem: T) = contents(oldItem, newItem)
        override fun getChangePayload(oldItem: T, newItem: T) = getChangePayload(oldItem, newItem)
    }
}

abstract class DataBindingViewHolder<T : Any, VB : ViewDataBinding>(val dataBinding: VB) :
    RecyclerView.ViewHolder(dataBinding.root) {

    abstract fun onBind(item: T)
}

abstract class ViewBindingViewHolder<T : Any, VB : ViewBinding>(val viewBinding: VB) :
    RecyclerView.ViewHolder(viewBinding.root) {

    abstract fun onBind(item: T)
}
