package com.example.offlinecaching.data.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.offlinecaching.model.Coffee
import com.example.offlinecaching.model.Dessert
import com.example.offlinecaching.model.Food
import com.example.offlinecaching.model.Restaurant

@Database(
    entities = [Dessert::class, Food::class, Coffee::class, Restaurant::class],
    version = 2,
    exportSchema = true,
    autoMigrations = [AutoMigration(from = 1, to = 2)]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dessertDao(): DessertDao
    abstract fun foodDao(): FoodDao
    abstract fun coffeeDao(): CoffeeDao
    abstract fun restaurantDao(): RestaurantDao
}

/*
@Database(
    entities = [Dessert::class],
    version = 1,
    exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dessertDao(): DessertDao
}*/
