package com.example.offlinecaching.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.offlinecaching.databinding.ItemFoodBinding
import com.example.offlinecaching.model.Food

class FoodRecyclerAdapter(private val onClickListener: FoodOnClickListener) :
    ListAdapter<Food, FoodViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Food>() {
        override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder =
        FoodViewHolder.create(parent)

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val item = getItem(position)
        if (item is Food) {
            holder.bind(item, onClickListener)
        }
    }
}

class FoodViewHolder(private val binding: ItemFoodBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Food, onClickListener: FoodOnClickListener) {
        binding.food = item
        binding.executePendingBindings()
        binding.root.setOnClickListener {
            onClickListener.onClick(adapterPosition, item)
        }
    }

    companion object {
        fun create(parent: ViewGroup): FoodViewHolder {
            val binding = ItemFoodBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return FoodViewHolder(binding)
        }
    }
}


interface FoodOnClickListener {
    fun onClick(position: Int, item: Food)
}