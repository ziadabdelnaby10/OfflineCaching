package com.example.offlinecaching.data.repo

import androidx.room.withTransaction
import com.example.offlinecaching.data.api.DessertServices
import com.example.offlinecaching.data.database.DessertDatabase
import com.example.offlinecaching.util.networkBoundResource
import javax.inject.Inject

class DessertRepository @Inject constructor(
    private val api: DessertServices,
    private val db: DessertDatabase
) {
    private val dessertDao = db.dessertDao()

    fun getDessert() = networkBoundResource(
        query = {
            dessertDao.getAllData()
        },
        fetch = {
            api.getDesserts(30).await()
        },
        saveFetchResult = { desserts ->
            db.withTransaction {
                dessertDao.deleteAll()
                dessertDao.insertData(desserts)
            }
        }
    )
}