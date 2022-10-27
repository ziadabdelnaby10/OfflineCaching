package com.example.offlinecaching.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.offlinecaching.model.Food
import com.example.offlinecaching.util.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(list: List<Food>)

    @Query("DELETE FROM ${Constants.FOOD_TABLE}")
    suspend fun deleteAll()

    @Query("SELECT * FROM ${Constants.FOOD_TABLE}")
    fun getAllData(): Flow<List<Food>>
}