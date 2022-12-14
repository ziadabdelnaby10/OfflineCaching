package com.example.offlinecaching.data.repo

import androidx.room.withTransaction
import com.example.offlinecaching.data.api.DessertServices
import com.example.offlinecaching.data.database.AppDatabase
import com.example.offlinecaching.util.networkBoundResource
import javax.inject.Inject

class DessertRepository @Inject constructor(
    private val api: DessertServices,
    private val db: AppDatabase
) {
    private val dessertDao = db.dessertDao()

    fun getDessert() = networkBoundResource(
        query = {
            dessertDao.getAllData()
        },
        fetch = {
            api.getDessertsAsync(30).await()
        },
        saveFetchResult = { desserts ->
            db.withTransaction {
                dessertDao.deleteAll()
                dessertDao.insertData(desserts)
            }
        }
    )
}