package com.kkk.justodo.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kkk.justodo.databinding.ItemHolderBinding
import com.kkk.justodo.model.Item


class MainAdapter(private val clickListener: IClickListener): ListAdapter<Item, MainAdapter.ItemHolder>(Comparator()) {

    inner class ItemHolder(val binding: ItemHolderBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding = ItemHolderBinding.inflate(LayoutInflater.from(parent.context))
        val holder = ItemHolder(binding)
        binding.root.setOnClickListener {
            clickListener.onClickRoot(currentList[holder.adapterPosition].body)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val text = currentList[position].body
        holder.binding.tvHolder.text = text
    }
}

interface IClickListener {
    fun onClickRoot(i: String)
}