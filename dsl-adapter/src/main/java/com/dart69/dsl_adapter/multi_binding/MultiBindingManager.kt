package com.dart69.dsl_adapter.multi_binding

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.dart69.dsl_adapter.base.ViewHolderBinder
import kotlin.reflect.KClass

interface ViewHolderScope {
    fun <T : Any, VB : ViewBinding> viewHolder(
        itemClass: KClass<T>,
        bindingClass: KClass<VB>,
        binder: ViewHolderBinder<T, VB>,
    )
}

interface MultiBindingManager : ViewHolderScope {
    fun <T : Any> provideItemViewType(item: T): Int

    fun <VB : ViewBinding> inflateBinding(viewType: Int, parent: ViewGroup): VB

    fun <T : Any, VB : ViewBinding> bindViewHolder(item: T, binding: VB)
}

inline fun <reified T : Any, reified VB : ViewBinding> ViewHolderScope.viewHolder(
    onBind: ViewHolderBinder<T, VB>,
) {
    viewHolder(T::class, VB::class, onBind)
}