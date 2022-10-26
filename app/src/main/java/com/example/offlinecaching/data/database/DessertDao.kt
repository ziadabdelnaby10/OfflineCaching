package com.example.offlinecaching.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.offlinecaching.model.Dessert
import com.example.offlinecaching.util.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface DessertDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(list: List<Dessert>)

    @Query("DELETE FROM ${Constants.DESSERT_TABLE}")
    suspend fun deleteAll()

    @Query("SELECT * FROM ${Constants.DESSERT_TABLE}")
    fun getAllData() : Flow<List<Dessert>>
}