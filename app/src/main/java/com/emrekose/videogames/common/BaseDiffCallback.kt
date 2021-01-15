package com.emrekose.videogames.common

import androidx.recyclerview.widget.DiffUtil

abstract class BaseDiffCallback<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem == newItem
}