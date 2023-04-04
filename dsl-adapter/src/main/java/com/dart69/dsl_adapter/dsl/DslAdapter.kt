package com.dart69.dsl_adapter.dsl

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.dart69.dsl_adapter.base.BaseViewHolder
import com.dart69.dsl_adapter.base.CallbackWrapper
import com.dart69.dsl_adapter.base.CommonAdapter

typealias ViewHolderBinder<T, VB> = BaseViewHolder<T, VB>.(T, VB) -> Unit

abstract class DslAdapter<T : Any, VB : ViewBinding>(
    callback: CallbackWrapper.ItemCallback<T>,
    private val binder: ViewHolderBinder<T, VB>,
) : CommonAdapter<T, VB>(callback) {
    protected abstract fun provideBinding(parent: ViewGroup, viewType: Int): VB

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DslViewHolder<T, VB> =
        DslViewHolder(provideBinding(parent, viewType), binder)
}

open class DslViewHolder<T : Any, VB : ViewBinding>(
    binding: VB,
    private val binder: ViewHolderBinder<T, VB>,
) : BaseViewHolder<T, VB>(binding) {
    override fun bind(item: T) = binder(this, item, binding)
}