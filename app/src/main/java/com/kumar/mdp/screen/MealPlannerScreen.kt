package com.kumar.mdp.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kumar.mdp.data.recipes
import com.kumar.mdp.enums.Day
import com.kumar.mdp.R
import com.kumar.mdp.model.MealPlan
import com.kumar.mdp.model.MealPlanOnDay
import com.kumar.mdp.model.Recipe
import com.kumar.mdp.viewmodel.MealPlannerViewModel

data class MealPlannerScreenUiState(
    val mealPlans: List<MealPlanOnDay> = listOf()
)

@Composable
fun MealPlannerScreen() {
    val viewModel: MealPlannerViewModel = viewModel()
    val uiState by viewModel.uiState.observeAsState(MealPlannerScreenUiState())

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            item {
                Spacer(modifier = Modifier.height(10.dp))
            }
            items(uiState.mealPlans) {
                DayPlan(mealPlanOnDay = it)
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun DayPlan(mealPlanOnDay: MealPlanOnDay) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ){
        Column(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = mealPlanOnDay.day.name, style = MaterialTheme.typography.titleMedium)
            PlanRow(mealPlanOnDay.mealPlan.breakfastRecipe, "Breakfast")
            PlanRow(mealPlanOnDay.mealPlan.lunchRecipe, "Lunch")
            PlanRow(mealPlanOnDay.mealPlan.dinnerRecipe, "Dinner")
        }
    }

}

@Composable
private fun PlanRow(recipe: Recipe?, timeOfDay: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = timeOfDay)
        if (recipe == null) {
            Icon(
                painter = painterResource(id = R.drawable.round_add_24),
                contentDescription = "Add"
            )
        } else {
            Plan(recipe)
        }
    }
}

@Composable
private fun Plan(recipe: Recipe) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(recipe.image)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.round_wifi_protected_setup_24),
            error = painterResource(id = R.drawable.round_fastfood_24),
            contentDescription = stringResource(R.string.description_recipe_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
        )
        Text(
            modifier = Modifier
                .widthIn(min = 10.dp, max = 150.dp)
                .padding(start = 4.dp),
            text = recipe.name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDayPlan() {
    DayPlan(mealPlanOnDay = MealPlanOnDay(
        Day.MONDAY,
        MealPlan(breakfastRecipe = recipes[0])
    )
    )
}

