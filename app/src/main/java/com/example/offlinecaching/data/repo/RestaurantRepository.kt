package com.example.offlinecaching.data.repo

import androidx.room.withTransaction
import com.example.offlinecaching.data.api.RestaurantServices
import com.example.offlinecaching.data.database.AppDatabase
import com.example.offlinecaching.util.networkBoundResource
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val api: RestaurantServices,
    private val db: AppDatabase
) {
    private val restaurantDao = db.restaurantDao()

    fun getRestaurant() = networkBoundResource(
        query = {
            restaurantDao.getAllData()
        },
        fetch = {
            api.getRestaurantAsync(30).await()
        },
        saveFetchResult = { desserts ->
            db.withTransaction {
                restaurantDao.deleteAll()
                restaurantDao.insertData(desserts)
            }
        }
    )
}