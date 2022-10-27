package com.example.offlinecaching.util

import android.widget.ImageView
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.offlinecaching.adapter.CoffeeRecyclerAdapter
import com.example.offlinecaching.adapter.DessertRecyclerAdapter
import com.example.offlinecaching.adapter.FoodRecyclerAdapter
import com.example.offlinecaching.adapter.RestaurantRecyclerAdapter
import com.example.offlinecaching.model.Coffee
import com.example.offlinecaching.model.Dessert
import com.example.offlinecaching.model.Food
import com.example.offlinecaching.model.Restaurant
import com.squareup.picasso.Picasso


////////////////////// Dessert ///////////////////////////////////
@BindingAdapter("listDessertItem")
fun RecyclerView.listDessertItem(items: List<Dessert>?) {
    val adapter = this.adapter as DessertRecyclerAdapter
    adapter.submitList(items)
}

@BindingAdapter("textDessertError")
fun RecyclerView.textDessertError(result: Resource<List<Dessert>>?) {
    if (result is Resource.Failed) {
        val text = result.error?.localizedMessage.toString()
        Toast.makeText(this.context, text, Toast.LENGTH_LONG).show()
    }
}

@BindingAdapter("progressDessertStates")
fun ContentLoadingProgressBar.progressDessertStates(result: Resource<List<Dessert>>?) {
    if (result is Resource.Loading && !result.data.isNullOrEmpty())
        this.show()
    else if (result is Resource.Failed || (result is Resource.Success && !result.data.isNullOrEmpty()))
        this.hide()
}

////////////////////// Food ///////////////////////////////////

@BindingAdapter("listFoodItem")
fun RecyclerView.listFoodItem(items: List<Food>?) {
    val adapter = this.adapter as FoodRecyclerAdapter
    adapter.submitList(items)
}

@BindingAdapter("textFoodError")
fun RecyclerView.textFoodError(result: Resource<List<Food>>?) {
    if (result is Resource.Failed) {
        val text = result.error?.localizedMessage.toString()
        Toast.makeText(this.context, text, Toast.LENGTH_LONG).show()
    }
}

@BindingAdapter("progressFoodStates")
fun ContentLoadingProgressBar.progressFoodStates(result: Resource<List<Food>>?) {
    if (result is Resource.Loading && !result.data.isNullOrEmpty())
        this.show()
    else if (result is Resource.Failed || (result is Resource.Success && !result.data.isNullOrEmpty()))
        this.hide()
}

////////////////////// Coffee ///////////////////////////////////

@BindingAdapter("listCoffeeItem")
fun RecyclerView.listCoffeeItem(items: List<Coffee>?) {
    val adapter = this.adapter as CoffeeRecyclerAdapter
    adapter.submitList(items)
}

@BindingAdapter("textCoffeeError")
fun RecyclerView.textCoffeeError(result: Resource<List<Coffee>>?) {
    if (result is Resource.Failed) {
        val text = result.error?.localizedMessage.toString()
        Toast.makeText(this.context, text, Toast.LENGTH_LONG).show()
    }
}

@BindingAdapter("progressCoffeeStates")
fun ContentLoadingProgressBar.progressCoffeeStates(result: Resource<List<Coffee>>?) {
    if (result is Resource.Loading && !result.data.isNullOrEmpty())
        this.show()
    else if (result is Resource.Failed || (result is Resource.Success && !result.data.isNullOrEmpty()))
        this.hide()
}

////////////////////// Restaurant ///////////////////////////////////

@BindingAdapter("listRestaurantItem")
fun RecyclerView.listRestaurantItem(items: List<Restaurant>?) {
    val adapter = this.adapter as RestaurantRecyclerAdapter
    adapter.submitList(items)
}

@BindingAdapter("textRestaurantError")
fun RecyclerView.textRestaurantError(result: Resource<List<Restaurant>>?) {
    if (result is Resource.Failed) {
        val text = result.error?.localizedMessage.toString()
        Toast.makeText(this.context, text, Toast.LENGTH_LONG).show()
    }
}

@BindingAdapter("progressRestaurantStates")
fun ContentLoadingProgressBar.progressRestaurantStates(result: Resource<List<Restaurant>>?) {
    if (result is Resource.Loading && !result.data.isNullOrEmpty())
        this.show()
    else if (result is Resource.Failed || (result is Resource.Success && !result.data.isNullOrEmpty()))
        this.hide()
}

@BindingAdapter("imgUrl")
fun ImageView.imgUrl(url: String?) {
    Picasso.with(this.context).load(url).error(android.R.drawable.stat_notify_error)
        .placeholder(android.R.drawable.stat_sys_download).into(this)
}