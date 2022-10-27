package com.example.offlinecaching.di

import android.app.Application
import androidx.room.Room
import com.example.offlinecaching.data.api.CoffeeServices
import com.example.offlinecaching.data.api.DessertServices
import com.example.offlinecaching.data.api.FoodServices
import com.example.offlinecaching.data.api.RestaurantServices
import com.example.offlinecaching.data.database.AppDatabase
import com.example.offlinecaching.util.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            )
        )
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideDessertService(retrofit: Retrofit): DessertServices =
        retrofit.create(DessertServices::class.java)

    @Provides
    @Singleton
    fun provideFoodService(retrofit: Retrofit): FoodServices =
        retrofit.create(FoodServices::class.java)

    @Provides
    @Singleton
    fun provideCoffeeService(retrofit: Retrofit): CoffeeServices =
        retrofit.create(CoffeeServices::class.java)

    @Provides
    @Singleton
    fun provideRestaurantService(retrofit: Retrofit): RestaurantServices =
        retrofit.create(RestaurantServices::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase =
        Room.databaseBuilder(app, AppDatabase::class.java, Constants.APP_DATABASE).build()
}