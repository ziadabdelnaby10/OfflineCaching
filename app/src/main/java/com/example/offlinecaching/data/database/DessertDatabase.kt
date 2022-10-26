package com.example.offlinecaching.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.offlinecaching.model.Dessert

@Database(entities = [Dessert::class] , version = 1 , exportSchema = false)
abstract class DessertDatabase : RoomDatabase(){
    abstract fun dessertDao() : DessertDao
}