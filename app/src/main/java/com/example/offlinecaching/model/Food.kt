package com.example.offlinecaching.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.offlinecaching.util.Constants

@Entity(tableName = Constants.FOOD_TABLE)
data class Food(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val description: String,
    val dish: String,
    val ingredient: String,
    val measurement: String,
    val uid: String
)