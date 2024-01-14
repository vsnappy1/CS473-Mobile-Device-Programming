package com.kumar.mdp.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kumar.mdp.R

@Composable
fun HomeScreen(
    onGardenLogClick: () -> Unit,
    onPlantCareTipsClick: () -> Unit,
    onScanPlantClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Welcome",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "Gardener!!",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = 45.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(30.dp))
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FeatureCard(
                    resourceId = R.drawable.garden,
                    text = "My Garden Log",
                    onClick = onGardenLogClick
                )
                FeatureCard(
                    resourceId = R.drawable.plant_care,
                    text = "Plant Care Tips",
                    onClick = onPlantCareTipsClick
                )
                FeatureCard(
                    resourceId = R.drawable.scan_plant,
                    text = "Scan Plant",
                    onClick = onScanPlantClick
                )
            }

        }
    }

}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun FeatureCard(
    @DrawableRes resourceId: Int,
    text: String,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) {
            Image(
                painter = painterResource(id = resourceId),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .background(Color.Gray.copy(alpha = 0.5f))
                    .fillMaxWidth()
            ) {
                Text(
                    text = text,
                    modifier = Modifier.padding(bottom = 4.dp, start = 4.dp),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen({}, {}, {})
}