package com.example.offlinecaching.util

import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.offlinecaching.adapter.DessertRecyclerAdapter
import com.example.offlinecaching.model.Dessert

@BindingAdapter("listDessertItem")
fun RecyclerView.listDessertItem(items: List<Dessert>?) {
    val adapter = this.adapter as DessertRecyclerAdapter
    adapter.submitList(items)
}

@BindingAdapter("progressStates")
fun ContentLoadingProgressBar.progressStates(result: Resource<List<Dessert>>?) {
    if (result is Resource.Loading && !result.data.isNullOrEmpty())
        this.show()
    else if (result is Resource.Failed || (result is Resource.Success && !result.data.isNullOrEmpty()))
        this.hide()
}

@BindingAdapter("textError")
fun TextView.textError(result: Resource<List<Dessert>>?) {
    if (result is Resource.Failed) {
        val text = result.error?.localizedMessage.toString()
        Toast.makeText(this.context , text , Toast.LENGTH_LONG).show()
    }
    this.visibility = View.GONE
}