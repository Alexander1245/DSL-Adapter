package com.dart69.dsl_adapter.dsl

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.dart69.dsl_adapter.base.CallbackWrapper
import com.dart69.dsl_adapter.base.CommonAdapter
import com.dart69.dsl_adapter.binding.createBinding

typealias AdapterBuilderScope<T, VB> = AdapterBuilder<T, VB>.() -> Unit

interface AdapterBuilder<T : Any, VB : ViewBinding> {
    fun itemCallback(callback: CallbackWrapper.ItemCallback<T>): AdapterBuilder<T, VB>

    fun viewHolder(block: ViewHolderBinder<T, VB>): AdapterBuilder<T, VB>
}

inline fun <T : Any, reified VB : ViewBinding> adapter(
    block: AdapterBuilderScope<T, VB>,
): CommonAdapter<T, VB> {
    val builder = object : AdapterBuilder<T, VB> {
        var itemCallback = CallbackWrapper.ItemCallback<T> { old, new -> old == new }
        var binder: ViewHolderBinder<T, VB> = { _, _ -> }

        override fun itemCallback(callback: CallbackWrapper.ItemCallback<T>): AdapterBuilder<T, VB> =
            apply {
                itemCallback = callback
            }

        override fun viewHolder(block: ViewHolderBinder<T, VB>): AdapterBuilder<T, VB> =
            apply {
                binder = block
            }
    }
    block(builder)
    return object : DslAdapter<T, VB>(builder.itemCallback, builder.binder) {
        override fun provideBinding(parent: ViewGroup, viewType: Int): VB = createBinding(parent)
    }
}