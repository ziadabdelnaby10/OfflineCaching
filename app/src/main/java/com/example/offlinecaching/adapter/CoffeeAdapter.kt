package com.example.offlinecaching.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.offlinecaching.databinding.ItemCoffeeBinding
import com.example.offlinecaching.model.Coffee

class CoffeeRecyclerAdapter(private val onClickListener: CoffeeOnClickListener) :
    ListAdapter<Coffee, CoffeeViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Coffee>() {
        override fun areItemsTheSame(oldItem: Coffee, newItem: Coffee): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Coffee, newItem: Coffee): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeViewHolder =
        CoffeeViewHolder.create(parent)

    override fun onBindViewHolder(holder: CoffeeViewHolder, position: Int) {
        val item = getItem(position)
        if (item is Coffee) {
            holder.bind(item, onClickListener)
        }
    }
}

class CoffeeViewHolder(private val binding: ItemCoffeeBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Coffee, onClickListener: CoffeeOnClickListener) {
        binding.coffee = item
        binding.executePendingBindings()
        binding.root.setOnClickListener {
            onClickListener.onClick(adapterPosition, item)
        }
    }

    companion object {
        fun create(parent: ViewGroup): CoffeeViewHolder {
            val binding = ItemCoffeeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return CoffeeViewHolder(binding)
        }
    }
}


interface CoffeeOnClickListener {
    fun onClick(position: Int, item: Coffee)
}