package com.kumar.mdp.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NotificationScreen(paddingValues: PaddingValues) {
    Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Notifications", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(20.dp))
            NotificationItem(
                title = "New Recipe Added!!!",
                description = "Karachi chicken karahi recipe added to the list."
            )
            NotificationItem(
                title = "Try this ;)",
                description = "Karachi chicken karahi with onion recipe added to the list."
            )
            NotificationItem(
                title = "Tired of same recipes, this will help :)",
                description = "Karachi chicken karahi only tomatoes recipe added to the list."
            )
            NotificationItem(
                title = "Biryani version 2",
                description = "Famous Chicken biryani recipe added to the list."
            )
        }
    }
}

@Preview
@Composable
fun PreviewNotificationScreen() {
    NotificationScreen(PaddingValues(16.dp))
}


@Composable
fun NotificationItem(title: String, description: String) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .fillMaxWidth()
        ) {
            Text(text = title, style = MaterialTheme.typography.titleSmall)
            Text(
                text = description,
                style = MaterialTheme.typography.labelMedium.copy(color = Color.Gray)
            )
        }
    }
}

@Preview
@Composable
fun PreviewNotificationItem() {
    NotificationItem(
        title = "New Recipe Added!!!",
        description = "Karachi chicken karahi recipe added to the list."
    )
}