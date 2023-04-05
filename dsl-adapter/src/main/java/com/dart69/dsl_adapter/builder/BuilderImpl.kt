package com.dart69.dsl_adapter.builder

import androidx.viewbinding.ViewBinding
import com.dart69.dsl_adapter.adapter.BindingAdapter
import com.dart69.dsl_adapter.multi_binding.MultiBindingManagerImpl
import com.dart69.dsl_adapter.multi_binding.ViewHolderScope

internal class BindingAdapterBuilderImpl<T : Any, VB : ViewBinding> : AdapterBuilderScope<T, VB> {
    private var callback: BindingAdapter.DiffCallback<T>? = null
    private var scope: ViewHolderScope.() -> Unit = {}

    override fun diffCallback(callback: BindingAdapter.DiffCallback<T>) {
        this.callback = callback
    }


    override fun items(scope: ViewHolderScope.() -> Unit) {
        this.scope = scope
    }

    fun build(): BindingAdapter<T, VB> =
        BindingAdapter(
            requireNotNull(callback) { "DiffCallback not found. Did you add it to builder?" },
            MultiBindingManagerImpl().apply(scope)
        )
}