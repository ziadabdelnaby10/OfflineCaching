package com.example.offlinecaching.data.api

import com.example.offlinecaching.model.Food
import com.example.offlinecaching.util.Constants
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodServices {
    @GET(Constants.FOOD_PATH)
    fun getFoodAsync(@Query("size") size: Int): Deferred<List<Food>>
}