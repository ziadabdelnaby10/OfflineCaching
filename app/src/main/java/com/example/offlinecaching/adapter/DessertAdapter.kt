package com.example.offlinecaching.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.offlinecaching.databinding.ItemDessertBinding
import com.example.offlinecaching.model.Dessert

class DessertRecyclerAdapter(private val onClickListener: DessertOnClickListener) :
    ListAdapter<Dessert, DessertViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Dessert>() {
        override fun areItemsTheSame(oldItem: Dessert, newItem: Dessert): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Dessert, newItem: Dessert): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DessertViewHolder =
        DessertViewHolder.create(parent)

    override fun onBindViewHolder(holder: DessertViewHolder, position: Int) {
        val item = getItem(position)
        if (item is Dessert) {
            holder.bind(item, onClickListener)
        }
    }
}

class DessertViewHolder(private val binding: ItemDessertBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Dessert, onClickListener: DessertOnClickListener) {
        binding.dessert = item
        binding.executePendingBindings()
        binding.root.setOnClickListener {
            onClickListener.onClick(adapterPosition, item)
        }
    }

    companion object {
        fun create(parent: ViewGroup): DessertViewHolder {
            val binding = ItemDessertBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return DessertViewHolder(binding)
        }
    }
}


interface DessertOnClickListener {
    fun onClick(position: Int, item: Dessert)
}