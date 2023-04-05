package com.dart69.dsl_adapter.base

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

fun interface BindingProvider {
    fun provide(parent: ViewGroup): ViewBinding
}