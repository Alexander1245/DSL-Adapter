package com.dart69.dsl_adapter.builder

import androidx.viewbinding.ViewBinding
import com.dart69.dsl_adapter.adapter.BindingAdapter
import com.dart69.dsl_adapter.multi_binding.ViewHolderScope

interface AdapterBuilderScope<T: Any, VB: ViewBinding> {
    fun diffCallback(callback: BindingAdapter.DiffCallback<T>)

    fun items(scope: ViewHolderScope.() -> Unit)
}

fun <T: Any, VB: ViewBinding> buildAdapter(
    block: AdapterBuilderScope<T, VB>.() -> Unit,
): BindingAdapter<T, VB> = BindingAdapterBuilderImpl<T, VB>().apply(block).build()