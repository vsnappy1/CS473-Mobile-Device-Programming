package com.kumar.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kumar.data.recipes
import com.kumar.mdp.R
import com.kumar.model.Recipe
import com.kumar.viewmodel.RecipeViewModel

data class RecipesScreenUiState(
    val recipes: List<Recipe> = listOf()
)

@Composable
fun RecipesScreen() {

    val viewModel: RecipeViewModel = viewModel()
    val uiState by viewModel.uiState.observeAsState(RecipesScreenUiState())

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.recipes) {
                RecipeCard(recipe = it)
            }
        }
    }
}


@Composable
fun RecipeCard(recipe: Recipe) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally) {
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
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                Text(text = recipe.name, style = MaterialTheme.typography.titleLarge)
                Text(
                    text = recipe.description,
                    style = MaterialTheme.typography.labelMedium.copy(color = Color.Gray)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconWithText(R.drawable.round_access_time_24, getTime(recipe.cookingTime))
                    IconWithText(R.drawable.round_star_24, recipe.userRatings.toString())
                }
            }
        }
    }

}

@Composable
private fun IconWithText(@DrawableRes res: Int, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = res),
            contentDescription = "Cook Time"
        )
        Text(text = text, modifier = Modifier.padding(start = 4.dp))
    }
}

fun getTime(cookingTime: Int): String {
    val hours = cookingTime / 60
    val minutes = cookingTime % 60
    var time = ""
    if (hours > 0) {
        time += "$hours hrs "
    }
    return "$time$minutes mins"
}

@Preview(showBackground = true)
@Composable
fun PreviewRecipeCard() {
    RecipeCard(recipe = recipes[2])
}

