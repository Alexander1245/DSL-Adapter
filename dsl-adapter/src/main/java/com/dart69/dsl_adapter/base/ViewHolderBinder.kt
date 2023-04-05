package com.dart69.dsl_adapter.base

import androidx.viewbinding.ViewBinding

fun interface ViewHolderBinder<T : Any, VB : ViewBinding> {
    fun bind(item: T, binding: VB)
}