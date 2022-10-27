package com.example.offlinecaching.data.repo

import androidx.room.withTransaction
import com.example.offlinecaching.data.api.CoffeeServices
import com.example.offlinecaching.data.database.AppDatabase
import com.example.offlinecaching.util.networkBoundResource
import javax.inject.Inject

class CoffeeRepository @Inject constructor(
    private val api: CoffeeServices,
    private val db: AppDatabase
) {
    private val coffeeDao = db.coffeeDao()

    fun getCoffee() = networkBoundResource(
        query = {
            coffeeDao.getAllData()
        },
        fetch = {
            api.getCoffeeAsync(30).await()
        },
        saveFetchResult = { desserts ->
            db.withTransaction {
                coffeeDao.deleteAll()
                coffeeDao.insertData(desserts)
            }
        }
    )
}