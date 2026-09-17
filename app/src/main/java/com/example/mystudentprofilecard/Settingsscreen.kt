package com.example.mystudentprofilecard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsScreen(state: RegistrationState, onAboutClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        SectionHeader("Appearance")
        Spacer(modifier = Modifier.height(4.dp))

        //Switch Dark Mode
        SettingsRow(icon = Icons.Default.DarkMode, label = "Dark Mode") {
            Switch(checked = state.darkMode, onCheckedChange = { state.darkMode = it })
        }

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(24.dp))

        SectionHeader("Preference")
        Spacer(modifier = Modifier.height(4.dp))

        //Switch Notifications
        SettingsRow(icon = Icons.Default.Notifications, label = "Notifications") {
            Switch(checked = state.notificationEnabled, onCheckedChange = { state.notificationEnabled = it })
        }

        SettingsRow(icon = Icons.Default.Favorite, label = "Favorite") {
            Switch(checked = state.isFavorite, onCheckedChange = { state.isFavorite = it })
        }

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(24.dp))

        SectionHeader("About")
        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onAboutClick() }
                .padding(vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Info, contentDescription = null, tint = BrandBlue, modifier = Modifier.size(26.dp))
                Spacer(modifier = Modifier.width(16.dp))
                Text("About App", fontSize = 17.sp)
            }
            Icon(Icons.Default.ChevronRight, contentDescription = null)
        }
    }
}

@Composable
private fun SectionHeader(title: String) {
    Text(
        title,
        fontWeight = FontWeight.Bold,
        color = Color.DarkGray,
        fontSize = 15.sp,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
private fun SettingsRow(
    icon: ImageVector,
    label: String,
    trailing: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = BrandBlue, modifier = Modifier.size(26.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Text(label, fontSize = 17.sp)
        }
        trailing()
    }
}