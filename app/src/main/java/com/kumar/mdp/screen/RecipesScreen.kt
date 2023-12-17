package com.kumar.mdp.screen

import android.content.Context
import android.content.Intent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kumar.mdp.R
import com.kumar.mdp.data.recipes
import com.kumar.mdp.model.Recipe
import com.kumar.mdp.viewmodel.RecipeViewModel
import kotlinx.coroutines.launch


data class RecipesScreenUiState(
    val recipes: List<Recipe> = listOf()
)

@Composable
fun RecipesScreen() {

    val viewModel: RecipeViewModel = viewModel()
    val uiState by viewModel.uiState.observeAsState(RecipesScreenUiState())
    var isAddRecipeDialogVisible by remember { mutableStateOf(false) }
    val scrollState = rememberLazyListState()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = scrollState,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(10.dp))
            }
            items(uiState.recipes) {
                RecipeCard(recipe = it)
            }
            item {
                Spacer(modifier = Modifier.height(70.dp))
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .padding(24.dp)
                .align(Alignment.BottomEnd),
            onClick = {
                isAddRecipeDialogVisible = true
            }) {
            Icon(
                painter = painterResource(id = R.drawable.round_add_24),
                contentDescription = "Add recipe"
            )
        }

        val coroutineScope = rememberCoroutineScope()
        if (isAddRecipeDialogVisible)
            Dialog(onDismissRequest = { isAddRecipeDialogVisible = false }) {
                AddRecipeDialog(onCancel = { isAddRecipeDialogVisible = false }, onAdd = {
                    viewModel.addRecipe(it)
                    isAddRecipeDialogVisible = false
                    coroutineScope.launch {
                        scrollState.animateScrollToItem(uiState.recipes.size - 1) // Scroll to latest added item
                    }
                })
            }
    }
}


@Composable
fun RecipeCard(recipe: Recipe) {
    val context = LocalContext.current
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box {
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
                Icon(
                    imageVector = Icons.Rounded.Share, contentDescription = "Share",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp)
                        .clip(CircleShape)
                        .clickable {
                            shareRecipe(recipe, context)
                        },
                    tint = Color.White
                )
            }
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

private fun shareRecipe(recipe: Recipe, context: Context) {
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "text/plain"
    shareIntent.putExtra(
        Intent.EXTRA_TEXT,
        recipe.toString()
    )
    context.startActivity(shareIntent)
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

@Preview
@Composable
fun PreviewRecipeCard() {
    RecipeCard(recipe = recipes[2])
}

@Composable
fun AddRecipeDialog(
    onCancel: () -> Unit,
    onAdd: (Recipe) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var image by remember { mutableStateOf("") }
    var cookingTime by remember { mutableStateOf("0") }
    var userRatings by remember { mutableStateOf("0.0") }
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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Add Recipe", style = MaterialTheme.typography.headlineLarge)
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(text = "Name") })
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(text = "Description") })
            OutlinedTextField(
                value = image,
                onValueChange = { image = it },
                label = { Text(text = "Image Url") })
            OutlinedTextField(
                value = cookingTime,
                onValueChange = {
                    cookingTime = it.ifBlank {
                        "0"
                    }
                },
                label = { Text(text = "Cooking Time") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            OutlinedTextField(
                value = userRatings,
                onValueChange = {
                    userRatings = it.ifBlank {
                        "0.0"
                    }
                },
                label = { Text(text = "User Rating") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )
            val isAllFieldsFilled = name.isNotBlank() &&
                    description.isNotBlank() &&
                    image.isNotBlank()

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = onCancel) {
                    Text(text = "Cancel")
                }
                Button(
                    enabled = isAllFieldsFilled,
                    onClick = {
                        onAdd(
                            Recipe(
                                name,
                                description,
                                image,
                                cookingTime.toInt(),
                                userRatings.toDouble()
                            )
                        )
                    }) {
                    Text(text = "Add")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddRecipeDialog() {
    AddRecipeDialog(onCancel = {}, onAdd = {})
}

