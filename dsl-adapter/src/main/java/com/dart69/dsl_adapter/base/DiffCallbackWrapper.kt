package com.dart69.dsl_adapter.base

import androidx.recyclerview.widget.DiffUtil
import com.dart69.dsl_adapter.adapter.BindingAdapter

internal class DiffCallbackWrapper<T: Any>(
    private val callback: BindingAdapter.DiffCallback<T>
) : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        callback.areItemsTheSame(oldItem, newItem)

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        callback.areContentsTheSame(oldItem, newItem)
}