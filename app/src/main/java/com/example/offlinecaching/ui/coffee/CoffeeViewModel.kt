package com.example.offlinecaching.ui.coffee

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.offlinecaching.data.repo.CoffeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoffeeViewModel @Inject constructor(repository: CoffeeRepository) : ViewModel() {

    val coffeeLiveData = repository.getCoffee().asLiveData()
}