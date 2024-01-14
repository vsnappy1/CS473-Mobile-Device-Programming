package com.kumar.mdp.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Forest
import androidx.compose.material.icons.rounded.WaterDrop
import androidx.compose.material.icons.rounded.WifiProtectedSetup
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kumar.mdp.MyApplication
import com.kumar.mdp.R
import com.kumar.mdp.data.plant
import com.kumar.mdp.model.Plant
import com.kumar.mdp.viewmodel.GardenLogScreenViewModel
import com.kumar.mdp.viewmodel.GardenLogScreenViewModelFactory
import com.vsnappy1.datepicker.DatePicker
import java.time.LocalDate

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
    var isAddPlantDialogVisible by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.plants) {
                GardenLogItem(plant = it) { onItemClick(it.id) }
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp),
            onClick = { isAddPlantDialogVisible = true }) {
            Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add Button")
        }

        AnimatedVisibility(visible = isAddPlantDialogVisible) {
            Dialog(onDismissRequest = { isAddPlantDialogVisible = false }) {
                AddPlantDialog(
                    onCancel = { isAddPlantDialogVisible = false },
                    onAdd = {
                        viewModel.addPlant(it)
                        isAddPlantDialogVisible = false
                    })
            }
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
                placeholder = rememberVectorPainter(image = Icons.Rounded.WifiProtectedSetup),
                error = rememberVectorPainter(image = Icons.Rounded.Forest),
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddPlantDialog(
    onCancel: () -> Unit,
    onAdd: (Plant) -> Unit
) {
    val localDate = LocalDate.now()
    var name by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var image by remember { mutableStateOf("") }
    var wateringFrequency by remember { mutableStateOf("0") }
    var plantingDate by remember { mutableStateOf("${localDate.monthValue}/${localDate.dayOfMonth}/${localDate.year}") }
    Card(
        modifier = Modifier,
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
            modifier = Modifier
                .padding(8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(text = "Add Plant", style = MaterialTheme.typography.headlineLarge)
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(text = "Name") })
            OutlinedTextField(
                value = type,
                onValueChange = { type = it },
                label = { Text(text = "Type") })
            OutlinedTextField(
                value = image,
                onValueChange = { image = it },
                label = { Text(text = "Image Url") })
            OutlinedTextField(
                value = wateringFrequency,
                onValueChange = {
                    wateringFrequency = it.ifBlank {
                        "0"
                    }
                },
                label = { Text(text = "Watering Frequency") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            var isDatePickerVisible by remember { mutableStateOf(false) }

            OutlinedTextField(
                modifier = Modifier
                    .onFocusChanged {
                        isDatePickerVisible = it.hasFocus
                    },
                value = plantingDate,
                onValueChange = {},
                readOnly = true,
                label = { Text(text = "Planing date") }
            )
            if (isDatePickerVisible) {
                DatePicker(onDateSelected = { year, month, day ->
                    plantingDate = "${month + 1}/$day/$year"
                })
            }
            val isAllFieldsFilled =
                name.isNotBlank() &&
                        type.isNotBlank() &&
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
                            Plant(
                                name = name,
                                type = type,
                                imageUrl = image,
                                wateringFrequency = wateringFrequency.toInt(),
                                plantingDate = plantingDate
                            )
                        )
                    }) {
                    Text(text = "Add")
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewGardenLogItem() {
    GardenLogItem(plant) {}
}