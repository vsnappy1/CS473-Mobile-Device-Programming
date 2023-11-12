package com.kumar.mdp.data

import androidx.annotation.DrawableRes

data class Product (
    val name: String,
    val description: String,
    val price: Double,
    @DrawableRes val logo: Int,
    @DrawableRes val image: Int
)