package com.dart69.dsl_adapter.base

import androidx.recyclerview.widget.DiffUtil

class CallbackWrapper<T: Any>(
    private val callback: ItemCallback<T>
) : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        callback.areItemsTheSame(oldItem, newItem)

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        callback.areContentsTheSame(oldItem, newItem)

    fun interface ItemCallback<T: Any> {
        fun areItemsTheSame(oldItem: T, newItem: T): Boolean

        fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem
    }
}