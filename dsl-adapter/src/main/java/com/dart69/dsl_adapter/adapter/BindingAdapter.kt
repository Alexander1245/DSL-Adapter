package com.dart69.dsl_adapter.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.dart69.dsl_adapter.base.DiffCallbackWrapper
import com.dart69.dsl_adapter.multi_binding.MultiBindingManager

open class BindingAdapter<T : Any, VB : ViewBinding>(
    diffCallback: DiffCallback<T>,
    private val manager: MultiBindingManager,
) : ListAdapter<T, BindingAdapter.BindingViewHolder<T, VB>>(
    DiffCallbackWrapper(
        diffCallback
    )
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<T, VB> =
        BindingViewHolder(manager.inflateBinding(viewType, parent), manager)

    override fun onBindViewHolder(holder: BindingViewHolder<T, VB>, position: Int) =
        holder.bind(currentList[position])

    override fun getItemViewType(position: Int): Int =
        manager.provideItemViewType(currentList[position])

    fun interface DiffCallback<T : Any> {
        fun areItemsTheSame(oldItem: T, newItem: T): Boolean

        fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem
    }

    open class BindingViewHolder<T : Any, VB : ViewBinding>(
        private val binding: VB,
        private val manager: MultiBindingManager,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: T) {
            manager.bindViewHolder(item, binding)
        }
    }
}