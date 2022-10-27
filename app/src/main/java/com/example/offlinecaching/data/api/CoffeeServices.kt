package com.example.offlinecaching.data.api

import com.example.offlinecaching.model.Coffee
import com.example.offlinecaching.util.Constants
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface CoffeeServices {
    @GET(Constants.COFFEE_PATH)
    fun getCoffeeAsync(@Query("size") size: Int): Deferred<List<Coffee>>
}