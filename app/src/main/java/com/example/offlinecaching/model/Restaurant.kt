package com.example.offlinecaching.model


data class Restaurant(
    val address: String,
    val description: String,
    val hours: Hours,
    val id: Int,
    val logo: String,
    val name: String,
    val phone_number: String,
    val review: String,
    val type: String,
    val uid: String
)