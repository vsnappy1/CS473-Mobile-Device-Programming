package com.kumar.mdp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kumar.mdp.data.PlantDatabase
import com.kumar.mdp.data.PlantRepository
import com.kumar.mdp.screen.PlantDetailScreenUiState
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class PlantDetailScreenViewModel(database: PlantDatabase, plantId: Int) : ViewModel() {
    private var repository = PlantRepository(database.plantDao())

    private val _uiState: MutableLiveData<PlantDetailScreenUiState> = MutableLiveData(
        PlantDetailScreenUiState()
    )
    val uiState: LiveData<PlantDetailScreenUiState> = _uiState

    init {
        viewModelScope.launch {
            _uiState.value = _uiState.value?.copy(plant = repository.getPlantById(plantId).first())
        }
    }
}

class PlantDetailScreenViewModelFactory(
    private val database: PlantDatabase,
    private val plantId: Int
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlantDetailScreenViewModel::class.java)) {
            return PlantDetailScreenViewModel(database, plantId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}