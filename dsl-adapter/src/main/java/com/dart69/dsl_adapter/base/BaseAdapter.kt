package com.dart69.dsl_adapter.base

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

typealias CommonAdapter<T, VB> = BaseAdapter<T, BaseViewHolder<T, VB>>

abstract class BaseAdapter<T : Any, VH : BaseViewHolder<T, *>>(
    callback: CallbackWrapper.ItemCallback<T>,
) : ListAdapter<T, VH>(CallbackWrapper(callback)) {

    override fun onBindViewHolder(holder: VH, position: Int) =
        holder.bind(currentList[position])
}

abstract class BaseViewHolder<T : Any, VB : ViewBinding>(
    protected val binding: VB,
) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(item: T)
}