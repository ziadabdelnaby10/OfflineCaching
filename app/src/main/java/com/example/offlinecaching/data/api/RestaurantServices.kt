package com.example.offlinecaching.data.api

import com.example.offlinecaching.model.Restaurant
import com.example.offlinecaching.util.Constants
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantServices {
    @GET(Constants.RESTAURANT_PATH)
    fun getRestaurantAsync(@Query("size") size: Int): Deferred<List<Restaurant>>
}