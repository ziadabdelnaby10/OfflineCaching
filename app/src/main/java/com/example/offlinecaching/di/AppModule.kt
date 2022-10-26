package com.example.offlinecaching.di

import android.app.Application
import androidx.room.Room
import com.example.offlinecaching.data.api.DessertServices
import com.example.offlinecaching.data.database.DessertDatabase
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
    fun provideDatabase(app: Application): DessertDatabase =
        Room.databaseBuilder(app, DessertDatabase::class.java, Constants.APP_DATABASE).build()
}