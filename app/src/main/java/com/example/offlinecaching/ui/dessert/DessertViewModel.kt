package com.example.offlinecaching.ui.dessert

import androidx.lifecycle.*
import com.example.offlinecaching.data.api.DessertServices
import com.example.offlinecaching.data.repo.DessertRepository
import com.example.offlinecaching.model.Dessert
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DessertViewModel @Inject constructor(repository: DessertRepository) : ViewModel() {

    val dessertLiveData = repository.getDessert().asLiveData()
    /*val dessertLiveData: LiveData<List<Dessert>>
        get() = _dessertLiveData*/


}