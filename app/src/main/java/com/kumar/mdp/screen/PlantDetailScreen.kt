package com.kumar.mdp.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideIn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Forest
import androidx.compose.material.icons.rounded.WaterDrop
import androidx.compose.material.icons.rounded.WifiProtectedSetup
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kumar.mdp.MyApplication
import com.kumar.mdp.R
import com.kumar.mdp.model.Plant
import com.kumar.mdp.viewmodel.PlantDetailScreenViewModel
import com.kumar.mdp.viewmodel.PlantDetailScreenViewModelFactory
import kotlinx.coroutines.delay

data class PlantDetailScreenUiState(
    val plant: Plant? = Plant(
        1, "Evergreen oak", "Fagaceae", 2, "09-13-2022",
        "https://d2seqvvyy3b8p2.cloudfront.net/40ab8e7cdddbe3e78a581b84efa4e893.jpg"
    )
)

@Composable
fun PlantDetailScreen(
    plantId: Int,
    onDelete: () -> Unit
) {

    val applicationContext = LocalContext.current.applicationContext as MyApplication
    val viewModel: PlantDetailScreenViewModel =
        viewModel(
            factory = PlantDetailScreenViewModelFactory(
                applicationContext.getPlantDatabaseInstance(),
                plantId
            )
        )
    val uiState by viewModel.uiState.observeAsState(PlantDetailScreenUiState())
    var isVisible by remember { mutableStateOf(false) }
    uiState.plant?.let { plant ->
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
        ) {
            AnimatedVisibility(
                visible = isVisible,
                enter = slideIn(tween(durationMillis = 1000)) { IntOffset(0, -500) }.plus(
                    fadeIn(
                        tween(durationMillis = 1000)
                    )
                )
            ) {
                Box() {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(plant.imageUrl)
                            .crossfade(true)
                            .build(),
                        placeholder = rememberVectorPainter(image = Icons.Rounded.WifiProtectedSetup),
                        error = rememberVectorPainter(image = Icons.Rounded.Forest),
                        contentDescription = stringResource(R.string.plant_image),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth()
                            .height(300.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                    Icon(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(16.dp)
                            .clip(CircleShape)
                            .clickable {
                                viewModel.delete()
                                onDelete()
                            },
                        imageVector = Icons.Rounded.Delete,
                        contentDescription = "Delete",
                        tint = Color.Red
                    )
                }
            }
            AnimatedVisibility(
                visible = isVisible,
                enter = slideIn(tween(durationMillis = 1000)) { IntOffset(1000, 0) }.plus(
                    fadeIn(
                        tween(durationMillis = 1000)
                    )
                )
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(8.dp)
                ) {
                    Text(
                        text = plant.name,
                        style = MaterialTheme.typography.headlineMedium,
                        maxLines = 1
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Rounded.Forest,
                            contentDescription = "Forest",
                            tint = Color.Green
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = plant.type,
                            style = MaterialTheme.typography.titleMedium,
                            maxLines = 1
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Rounded.WaterDrop,
                            contentDescription = "Water Drop",
                            tint = Color.Blue
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${plant.wateringFrequency}",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Rounded.CalendarMonth,
                            contentDescription = "Calendar Month",
                            tint = Color.DarkGray
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = plant.plantingDate,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        delay(500)
        isVisible = true
    }
}


@Preview
@Composable
fun PreviewPlantDetailScreen() {
    PlantDetailScreen(plantId = 1, {})
}