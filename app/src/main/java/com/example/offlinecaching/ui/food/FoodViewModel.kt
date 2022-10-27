package com.example.offlinecaching.ui.food

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.offlinecaching.data.repo.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(repository: FoodRepository) : ViewModel() {

    val foodLiveData = repository.getFood().asLiveData()
}