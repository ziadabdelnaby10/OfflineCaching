package com.example.offlinecaching.ui.restaurant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.offlinecaching.data.repo.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(repository: RestaurantRepository) : ViewModel() {
    val restaurantLiveData = repository.getRestaurant().asLiveData()
}