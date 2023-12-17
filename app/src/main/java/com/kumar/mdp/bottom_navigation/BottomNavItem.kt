package com.kumar.mdp.bottom_navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : BottomNavItem("home", Icons.Rounded.Home, "Home")
    object Settings : BottomNavItem("settings", Icons.Rounded.Settings, "Settings")
    object Notification : BottomNavItem("notification", Icons.Rounded.Notifications, "Notification")
}