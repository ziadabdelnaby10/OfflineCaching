package com.example.offlinecaching.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.offlinecaching.model.Restaurant
import com.example.offlinecaching.util.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(list: List<Restaurant>)

    @Query("DELETE FROM ${Constants.RESTAURANT_TABLE}")
    suspend fun deleteAll()

    @Query("SELECT * FROM ${Constants.RESTAURANT_TABLE}")
    fun getAllData(): Flow<List<Restaurant>>
}