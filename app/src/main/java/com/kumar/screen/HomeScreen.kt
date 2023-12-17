package com.kumar.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {
    val titles = listOf("Recipes", " Meal Planner", "Blog", "Contact", "About Me")
    var tabIndex by remember { mutableIntStateOf(3) }
    Scaffold(
        topBar = {
            ScrollableTabRow(
                edgePadding = 0.dp,
                selectedTabIndex = tabIndex) {
                titles.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(text = title) },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index }
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)){
            when(tabIndex){
                0 -> RecipesScreen()
                1 -> MealPlannerScreen()
                2 -> BlogScreen()
                3 -> ContactScreen()
                4 -> AboutMeScreen()
            }
        }
    }
}