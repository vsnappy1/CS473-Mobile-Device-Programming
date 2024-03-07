package com.kumar.mdp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kumar.mdp.data.mealPlans
import com.kumar.mdp.enums.Day
import com.kumar.mdp.model.Recipe
import com.kumar.mdp.screen.MealPlannerScreenUiState

class MealPlannerViewModel : ViewModel() {
    private val _uiState: MutableLiveData<MealPlannerScreenUiState> = MutableLiveData(
        MealPlannerScreenUiState()
    )
    val uiState: LiveData<MealPlannerScreenUiState> = _uiState

    init {
        _uiState.value = _uiState.value?.copy(mealPlans = mealPlans)
    }


    fun updateBreakFast(day: Day, recipe: Recipe) {
        mealPlans[day.index].mealPlan.breakfastRecipe = recipe
        updateUi()
    }

    fun updateLunch(day: Day, recipe: Recipe) {
        mealPlans[day.index].mealPlan.lunchRecipe = recipe
        updateUi()
    }

    fun updateDinner(day: Day, recipe: Recipe) {
        mealPlans[day.index].mealPlan.dinnerRecipe = recipe
        updateUi()
    }

    private fun updateUi() {
        _uiState.value = _uiState.value?.copy(mealPlans = listOf())
        _uiState.value = _uiState.value?.copy(mealPlans = mealPlans)
    }

}