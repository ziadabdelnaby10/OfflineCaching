package com.example.offlinecaching.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.offlinecaching.model.Coffee
import com.example.offlinecaching.util.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface CoffeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(list: List<Coffee>)

    @Query("DELETE FROM ${Constants.COFFEE_TABLE}")
    suspend fun deleteAll()

    @Query("SELECT * FROM ${Constants.COFFEE_TABLE}")
    fun getAllData(): Flow<List<Coffee>>
}