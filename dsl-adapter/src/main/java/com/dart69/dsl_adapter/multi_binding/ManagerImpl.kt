package com.dart69.dsl_adapter.multi_binding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.dart69.dsl_adapter.base.BindingProvider
import com.dart69.dsl_adapter.base.ViewHolderBinder
import kotlin.reflect.KClass

class MultiBindingManagerImpl(
    private val bindingFactory: BindingFactory = ReflectionBindingFactory(),
) : MultiBindingManager {
    private val itemViewTypes = mutableMapOf<KClass<out Any>, Int>()
    private val providers = mutableMapOf<Int, BindingProvider>()
    private val binders = mutableMapOf<Int, ViewHolderBinder<out Any, out ViewBinding>>()

    override fun <T : Any, VB : ViewBinding> viewHolder(
        itemClass: KClass<T>,
        bindingClass: KClass<VB>,
        binder: ViewHolderBinder<T, VB>,
    ) {
        require(itemClass !in itemViewTypes) { "An attempt to bind several viewHolders to one itemClass." }
        val newIndex = itemViewTypes.size
        itemViewTypes[itemClass] = newIndex
        providers[newIndex] = BindingProvider { parent ->
            bindingFactory.create(bindingClass, LayoutInflater.from(parent.context), parent)
        }
        binders[newIndex] = binder
    }

    override fun <T : Any> provideItemViewType(item: T): Int =
        itemViewTypes[item::class] ?: error("Can't find a view type for the $item.")

    @Suppress("UNCHECKED_CAST")
    override fun <VB : ViewBinding> inflateBinding(viewType: Int, parent: ViewGroup): VB {
        val bindingProvider = providers[viewType]
        return bindingProvider?.provide(parent) as? VB
            ?: error("There aren't any binding associated with given itemClass.")
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any, VB : ViewBinding> bindViewHolder(item: T, binding: VB) {
        val binder = binders[provideItemViewType(item)] as? ViewHolderBinder<T, VB>
        binder?.bind(item, binding) ?: error("Can't bind actual binding")
    }
}