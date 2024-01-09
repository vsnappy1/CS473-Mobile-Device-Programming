package com.kumar.mdp.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Forest
import androidx.compose.material.icons.rounded.WaterDrop
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.kumar.mdp.MyApplication
import com.kumar.mdp.R
import com.kumar.mdp.data.plant
import com.kumar.mdp.model.Plant
import com.kumar.mdp.viewmodel.GardenLogScreenViewModel
import com.kumar.mdp.viewmodel.GardenLogScreenViewModelFactory

data class GardenLogScreenUiState(
    val plants: List<Plant> = listOf(),
    val plant: Plant? = null
)

@Composable
fun GardenLogScreen(onItemClick: (Int) -> Unit) {
    val applicationContext = LocalContext.current.applicationContext as MyApplication
    val viewModel: GardenLogScreenViewModel =
        viewModel(factory = GardenLogScreenViewModelFactory(applicationContext.getPlantDatabaseInstance()))
    val uiState by viewModel.uiState.observeAsState(GardenLogScreenUiState())

    LazyColumn(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(uiState.plants) {
            GardenLogItem(plant = it){onItemClick(it.id)}
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GardenLogItem(plant: Plant, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        onClick = onItemClick
    ) {
        Row(
            modifier = Modifier
                .height(150.dp)
                .padding(8.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(plant.imageUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.round_wifi_protected_setup_24),
                error = painterResource(id = R.drawable.round_local_florist_24),
                contentDescription = stringResource(R.string.plant_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(4.dp)
                    .size(150.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 8.dp)
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
                    Text(text = plant.plantingDate, style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewGardenLogItem() {
    GardenLogItem(plant){}
}