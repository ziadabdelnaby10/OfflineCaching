package com.example.offlinecaching.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.offlinecaching.databinding.ItemRestaurantBinding
import com.example.offlinecaching.model.Restaurant

class RestaurantRecyclerAdapter(private val onClickListener: RestaurantOnClickListener) :
    ListAdapter<Restaurant, RestaurantViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Restaurant>() {
        override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder =
        RestaurantViewHolder.create(parent)

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val item = getItem(position)
        if (item is Restaurant) {
            holder.bind(item, onClickListener)
        }
    }
}

class RestaurantViewHolder(private val binding: ItemRestaurantBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Restaurant, onClickListener: RestaurantOnClickListener) {
        binding.restaurant = item
        binding.executePendingBindings()
        binding.root.setOnClickListener {
            onClickListener.onClick(adapterPosition, item)
        }
    }

    companion object {
        fun create(parent: ViewGroup): RestaurantViewHolder {
            val binding = ItemRestaurantBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return RestaurantViewHolder(binding)
        }
    }
}


interface RestaurantOnClickListener {
    fun onClick(position: Int, item: Restaurant)
}