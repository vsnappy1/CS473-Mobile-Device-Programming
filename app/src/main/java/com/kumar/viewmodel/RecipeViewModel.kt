package com.kumar.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kumar.data.recipes
import com.kumar.model.Recipe
import com.kumar.screen.RecipesScreenUiState

class RecipeViewModel: ViewModel() {

    private val _uiState : MutableLiveData<RecipesScreenUiState> = MutableLiveData(RecipesScreenUiState())
    val uiState: LiveData<RecipesScreenUiState> = _uiState

    init {
        // load recipes
        _uiState.value = _uiState.value?.copy(recipes = recipes)
    }

    fun addRecipe(recipe: Recipe){
        recipes.add(recipe)
        _uiState.value = _uiState.value?.copy(recipes = listOf()) // list was not refreshing properly, so I added this
        _uiState.value = _uiState.value?.copy(recipes = recipes)
    }
}