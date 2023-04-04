package com.dart69.dsl_adapter.binding

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
class ReflectionBindingFactory : BindingFactory {
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

inline fun <reified VB : ViewBinding> createBinding(
    layoutInflater: LayoutInflater,
    parent: ViewGroup,
    attachToParent: Boolean = false,
): VB = ReflectionBindingFactory().create(VB::class, layoutInflater, parent, attachToParent)

inline fun <reified VB : ViewBinding> createBinding(
    parent: ViewGroup,
    attachToParent: Boolean = false,
): VB = createBinding(LayoutInflater.from(parent.context), parent, attachToParent)