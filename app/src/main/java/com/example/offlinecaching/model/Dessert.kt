package com.example.offlinecaching.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.offlinecaching.util.Constants

@Entity(tableName = Constants.DESSERT_TABLE)
data class Dessert(
    val flavor: String,
    @PrimaryKey(autoGenerate = false) val id: Int,
    val topping: String,
    val uid: String,
    val variety: String
)