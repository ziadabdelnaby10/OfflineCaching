package com.example.offlinecaching.ui.dessert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.offlinecaching.data.repo.DessertRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DessertViewModel @Inject constructor(repository: DessertRepository) : ViewModel() {

    val dessertLiveData = repository.getDessert().asLiveData()


}