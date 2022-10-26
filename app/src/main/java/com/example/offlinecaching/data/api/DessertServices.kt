package com.example.offlinecaching.data.api

import com.example.offlinecaching.model.Dessert
import com.example.offlinecaching.util.Constants
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface DessertServices {
    @GET(Constants.DESSERT_PATH)
    fun getDesserts(@Query("size") size: Int): Deferred<List<Dessert>>
}