package com.kumar.mdp.model

data class Recipe(
    val name: String,
    val description: String,
    val image: String,
    val cookingTime: Int,
    val userRatings: Double
)