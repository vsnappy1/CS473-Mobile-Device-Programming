package com.kumar.mdp.model

import com.kumar.mdp.enums.Day

data class MealPlanOnDay(
    val day: Day,
    val mealPlan: MealPlan
)