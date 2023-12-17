package com.kumar.mdp.model

import java.time.LocalDate

data class Blog(
    val username: String,
    val title: String,
    val description: String,
    val createOn: LocalDate
)
