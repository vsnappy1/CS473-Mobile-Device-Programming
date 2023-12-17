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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kumar.mdp.data.blogs
import com.kumar.mdp.R
import com.kumar.mdp.model.Blog
import com.kumar.mdp.viewmodel.BlogViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class BlogScreenUiState(
    val blogs: List<Blog> = listOf()
)

@Composable
fun BlogScreen() {

    val viewModel: BlogViewModel = viewModel()
    val uiState by viewModel.uiState.observeAsState(BlogScreenUiState())
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
            items(uiState.blogs) {
                BlogItem(blog = it)
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
                AddBlogDialog(onCancel = { isAddRecipeDialogVisible = false }, onAdd = {
                    viewModel.addBlog(it)
                    isAddRecipeDialogVisible = false
                    coroutineScope.launch {
                        scrollState.animateScrollToItem(uiState.blogs.size - 1) // Scroll to latest added item
                    }
                })
            }
    }

}


@Composable
fun BlogItem(blog: Blog) {
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
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = blog.title, style = MaterialTheme.typography.titleLarge)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = blog.username)
                Text(text = blog.createOn.format(DateTimeFormatter.ofPattern("dd MMM yyyy")))
            }
            Text(
                text = blog.description,
                style = MaterialTheme.typography.labelMedium.copy(color = Color.Gray)
            )
        }
    }

}

@Composable
fun AddBlogDialog(
    onCancel: () -> Unit,
    onAdd: (Blog) -> Unit
) {
    var username by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
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
            Text(text = "Add Blog", style = MaterialTheme.typography.headlineLarge)
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text(text = "Username") })
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(text = "Title") })
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(text = "Description") })

            val isAllFieldsFilled = username.isNotBlank() &&
                    title.isNotBlank() &&
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
                            Blog(
                                username = username,
                                title = title,
                                description = description,
                                createOn = LocalDate.now()
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
fun PreviewBlogItem() {
    BlogItem(blogs[0])
}