package com.kumar.mdp.screen

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.kumar.mdp.bottom_navigation.BottomNavigationBar
import com.kumar.mdp.bottom_navigation.NavigationHost

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {paddingValues ->
        NavigationHost(navController = navController, paddingValues = paddingValues)
    }
}