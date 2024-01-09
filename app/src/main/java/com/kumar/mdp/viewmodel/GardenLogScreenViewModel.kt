package com.kumar.mdp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kumar.mdp.data.PlantDatabase
import com.kumar.mdp.data.PlantRepository
import com.kumar.mdp.model.Plant
import com.kumar.mdp.screen.GardenLogScreenUiState
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class GardenLogScreenViewModel(database: PlantDatabase) : ViewModel() {
    private var repository = PlantRepository(database.plantDao())

    private val _uiState: MutableLiveData<GardenLogScreenUiState> = MutableLiveData(
        GardenLogScreenUiState()
    )
    val uiState: LiveData<GardenLogScreenUiState> = _uiState

    init {
        viewModelScope.launch {
            repository.allPlants.collect {
                _uiState.value = _uiState.value?.copy(plants = it)
            }
        }
    }

    fun setCurrentSelectedPlant(id: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value?.copy(plant = repository.getPlantById(id).first())
        }
    }

    fun addPlant(plant: Plant) {
        viewModelScope.launch {
            repository.insert(plant)
        }
    }

    fun deletePlant(plant: Plant) {
        viewModelScope.launch {
            repository.delete(plant)
        }
    }
}

class GardenLogScreenViewModelFactory(private val database: PlantDatabase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GardenLogScreenViewModel::class.java)) {
            return GardenLogScreenViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}