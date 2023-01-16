package com.kkk.justodo.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kkk.justodo.R
import com.kkk.justodo.databinding.TodoItemBinding
import com.kkk.justodo.model.Item


class MainAdapter(private val clickListener: AdapterItemClickListener) :
    ListAdapter<Item, MainAdapter.ItemHolder>(Comparator()) {

    inner class ItemHolder(binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemText = binding.itemText
        val importantButton = binding.ivIsImportant
        val deleteButton = binding.ivDelete
        val doneButton = binding.ivIsDone

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ItemHolder(binding)



        binding.root.setOnClickListener(object : DoubleClickListener() {
            override fun onDoubleClick(v: View) {
                clickListener.onItemDoubleClick(currentList[holder.adapterPosition])
            }

            override fun onSingleClick(v: View) {
                clickListener.onItemClick(currentList[holder.adapterPosition])
                holder.importantButton.setImageResource(
                    if (currentList[holder.adapterPosition].isImportant) R.drawable.ic_important
                    else R.drawable.ic_not_important
                )
            }
        })

        holder.deleteButton.setOnClickListener {
            clickListener.onClickDelete(currentList[holder.adapterPosition])
        }

        holder.doneButton.setOnClickListener {
            clickListener.onClickDone(currentList[holder.adapterPosition])
            holder.doneButton.setImageResource(
                if (currentList[holder.adapterPosition].isDone) R.drawable.ic_checked
                else R.drawable.ic_not_checked
            )
        }
        return holder
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.itemText.text = currentList[position].body
        holder.importantButton.setImageResource(
            if (currentList[position].isImportant) R.drawable.ic_important
            else R.drawable.ic_not_important
        )
        holder.doneButton.setImageResource(
            if (currentList[position].isDone) R.drawable.ic_checked
            else R.drawable.ic_not_checked
        )

    }
}

interface AdapterItemClickListener {
    fun onItemClick(item: Item)
    fun onItemDoubleClick(item: Item)

    fun onClickDelete(item: Item)
    fun onClickDone(item: Item)
    fun onClickImportant(item: Item)
}