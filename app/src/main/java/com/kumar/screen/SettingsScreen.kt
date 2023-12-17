package com.kumar.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kumar.mdp.R

@Composable
fun SettingsScreen(paddingValues: PaddingValues) {
    Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)) {
            Text(text = "Settings", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(20.dp))
            SettingRow(res = R.drawable.round_person_24, text = "Account")
            SettingRow(res = R.drawable.round_notifications_24, text = "Notification")
            SettingRow(res = R.drawable.outline_remove_red_eye_24, text = "Appearance")
            SettingRow(res = R.drawable.round_security_24, text = "Privacy & Security")
        }
    }
}

@Composable
fun SettingRow(@DrawableRes res: Int, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(id = res), contentDescription = "")
            Text(text = text, modifier = Modifier.padding(start = 8.dp))
        }
        Icon(
            painter = painterResource(id = R.drawable.round_chevron_right_24),
            contentDescription = "",

            )

    }
}

@Preview
@Composable
fun PreviewSettingRow() {
    SettingRow(res = R.drawable.round_person_24, text = "Account")
}

@Preview
@Composable
fun PreviewSettingsScreen() {
    SettingsScreen(PaddingValues(16.dp))
}