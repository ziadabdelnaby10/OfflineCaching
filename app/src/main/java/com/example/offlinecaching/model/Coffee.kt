package com.example.offlinecaching.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.offlinecaching.util.Constants

@Entity(tableName = Constants.COFFEE_TABLE)
data class Coffee(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val blend_name: String,
    val intensifier: String,
    val notes: String,
    val origin: String,
    val uid: String,
    val variety: String
)