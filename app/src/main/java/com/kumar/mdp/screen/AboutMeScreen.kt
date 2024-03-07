package com.kumar.mdp.screen

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kumar.mdp.data.aboutMeList
import com.kumar.mdp.R
import com.kumar.mdp.model.AboutMe
import com.kumar.mdp.viewmodel.AboutMeViewModel
import kotlinx.coroutines.launch

data class AboutMeScreenUiState(
    val aboutMeList: List<AboutMe> = listOf()
)

@Composable
fun AboutMeScreen() {

    val viewModel: AboutMeViewModel = viewModel()
    val uiState by viewModel.uiState.observeAsState(AboutMeScreenUiState())
    var isAddRecipeDialogVisible by remember { mutableStateOf(false) }
    val scrollState = rememberLazyListState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.user_image),
                contentDescription = "User",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
            )
            Text(text = "Vishal Kumar", style = MaterialTheme.typography.titleMedium)
            LazyColumn(
                state = scrollState,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                }
                items(uiState.aboutMeList) {
                    AboutMeItem(aboutMe = it)
                }
                item {
                    Spacer(modifier = Modifier.height(70.dp))
                }
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
                AddAboutMeDialog(onCancel = { isAddRecipeDialogVisible = false }, onAdd = {
                    viewModel.addAboutMe(it)
                    isAddRecipeDialogVisible = false
                    coroutineScope.launch {
                        scrollState.animateScrollToItem(uiState.aboutMeList.size - 1) // Scroll to latest added item
                    }
                })
            }
    }
}


@Composable
fun AboutMeItem(aboutMe: AboutMe) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
            Text(text = aboutMe.title, style = MaterialTheme.typography.titleLarge)
            Text(
                text = aboutMe.description,
                style = MaterialTheme.typography.labelMedium.copy(color = Color.Gray)
            )
        }
    }
}

@Preview
@Composable
fun PreviewAboutMeItem() {
    AboutMeItem(aboutMe = aboutMeList[0])
}

@Composable
fun AddAboutMeDialog(
    onCancel: () -> Unit,
    onAdd: (AboutMe) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
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
                value = title,
                onValueChange = { title = it },
                label = { Text(text = "Title") })
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(text = "Description") })
            val isAllFieldsFilled = title.isNotBlank() &&
                    description.isNotBlank()

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
                            AboutMe(title, description)
                        )
                    }) {
                    Text(text = "Add")
                }
            }
        }
    }
}