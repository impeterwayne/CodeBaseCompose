package com.genesys.core.common.base

import androidx.viewbinding.ViewBinding

interface Binding<B : ViewBinding> {

    val binding: B
}

inline fun <B : ViewBinding, R> Binding<B>.withBinding(block: B.() -> R): R = with(binding, block)
