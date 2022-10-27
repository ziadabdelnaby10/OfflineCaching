package com.example.offlinecaching.data.repo

import androidx.room.withTransaction
import com.example.offlinecaching.data.api.FoodServices
import com.example.offlinecaching.data.database.AppDatabase
import com.example.offlinecaching.util.networkBoundResource
import javax.inject.Inject

class FoodRepository @Inject constructor(
    private val api: FoodServices,
    private val db: AppDatabase
) {
    private val foodDao = db.foodDao()

    fun getFood() = networkBoundResource(
        query = {
            foodDao.getAllData()
        },
        fetch = {
            api.getFoodAsync(30).await()
        },
        saveFetchResult = { desserts ->
            db.withTransaction {
                foodDao.deleteAll()
                foodDao.insertData(desserts)
            }
        }
    )
}