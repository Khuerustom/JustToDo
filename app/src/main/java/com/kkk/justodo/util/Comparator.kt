package com.kkk.justodo.util

import androidx.recyclerview.widget.DiffUtil
import com.kkk.justodo.model.Item

class Comparator: DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }
}