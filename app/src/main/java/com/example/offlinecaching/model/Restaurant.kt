package com.example.offlinecaching.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.offlinecaching.util.Constants

@Entity(tableName = Constants.RESTAURANT_TABLE)
data class Restaurant(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val address: String,
    val description: String,
    //val hours: Hours,
    val logo: String,
    val name: String,
    val phone_number: String,
    val review: String,
    val type: String,
    val uid: String
)