package com.auro.application.core.extension

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


fun <T, V : RecyclerView.ViewHolder> ListAdapter<T, V>.isEmpty(): Boolean {
    return itemCount <= 0
}

fun <T, V : RecyclerView.ViewHolder> ListAdapter<T, V>.isLastItemPosition(position: Int): Boolean =
    position == itemCount - 1