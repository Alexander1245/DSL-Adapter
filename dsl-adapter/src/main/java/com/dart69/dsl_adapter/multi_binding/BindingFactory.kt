package com.dart69.dsl_adapter.multi_binding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import kotlin.reflect.KClass

interface BindingFactory {
    fun <VB : ViewBinding> create(
        bindingClass: KClass<VB>,
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        attachToParent: Boolean = false,
    ): VB
}

@Suppress("UNCHECKED_CAST")
internal class ReflectionBindingFactory : BindingFactory {
    override fun <VB : ViewBinding> create(
        bindingClass: KClass<VB>,
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        attachToParent: Boolean,
    ): VB {
        val inflate = bindingClass.members.first {
            it.name == INFLATE && it.parameters.size == PARAMETERS_SIZE
        }
        return inflate.call(layoutInflater, parent, attachToParent) as VB
    }

    private companion object {
        const val INFLATE = "inflate"
        const val PARAMETERS_SIZE = 3
    }
}