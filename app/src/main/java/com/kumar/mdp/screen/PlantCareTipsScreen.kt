package com.kumar.mdp.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kumar.mdp.data.plantCareTips
import com.kumar.mdp.model.PlantCareTip

@Composable
fun PlantCareTipsScreen() {

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Plant Care Tips",
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(23.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(plantCareTips) {
                TipItem(plantCareTip = it)
            }
        }
    }
}

@Composable
fun TipItem(plantCareTip: PlantCareTip) {
    Card {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = plantCareTip.plantName, style = MaterialTheme.typography.titleMedium)
            Text(text = plantCareTip.tip, style = MaterialTheme.typography.labelMedium)
        }
    }
}

@Preview
@Composable
fun PreviewTipItem() {
    TipItem(plantCareTip = plantCareTips[0])
}