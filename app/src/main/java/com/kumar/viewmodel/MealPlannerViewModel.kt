package com.kumar.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kumar.data.mealPlans
import com.kumar.screen.MealPlannerScreenUiState

class MealPlannerViewModel: ViewModel() {
    private val _uiState : MutableLiveData<MealPlannerScreenUiState> = MutableLiveData(
        MealPlannerScreenUiState()
    )
    val uiState: LiveData<MealPlannerScreenUiState> = _uiState

    init {
        _uiState.value = _uiState.value?.copy(mealPlans = mealPlans)
    }
}