package com.example.mystudentprofilecard

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val FieldGray = Color(0xFFF0F0F0)

@Composable
fun RegistrationScreen(state: RegistrationState, onRegistered: () -> Unit) {
    var showPhotoPicker by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Box(contentAlignment = Alignment.BottomEnd) {
            ProfileAvatar(photoRes = state.profilePhotoRes, size = 100.dp)
            IconButton(
                onClick = { showPhotoPicker = true },
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(BrandBlue)
            ) {
                Icon(
                    imageVector = Icons.Default.PhotoCamera,
                    contentDescription = "เปลี่ยนรูป",
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "ยินดีต้อนรับ!",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
        )
        Text(
            "กรุณากรอกข้อมูลของคุณ",
            color = Color.Gray,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = state.studentName,
            onValueChange = { state.studentName = it },
            placeholder = { Text("Enter your name") },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = FieldGray,
                focusedContainerColor = FieldGray,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        )

        if (state.studentName.isNotBlank()) {
            Spacer(modifier = Modifier.height(4.dp))
            Text("Hello, ${state.studentName}!", color = BrandBlue, fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            value = state.studentId,
            onValueChange = { state.studentId = it },
            placeholder = { Text("e.g. 6612345678") },
            leadingIcon = {
                Icon(Icons.Default.Badge, contentDescription = null, tint = Color.Gray)
            },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = FieldGray,
                focusedContainerColor = FieldGray,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text("Degree (ระดับการศึกษา)", fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth())
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = state.selectedDegree == "Bachelor",
                onClick = { state.selectedDegree = "Bachelor" }
            )
            Text("Bachelor")
            Spacer(modifier = Modifier.width(16.dp))
            RadioButton(
                selected = state.selectedDegree == "Master",
                onClick = { state.selectedDegree = "Master" }
            )
            Text("Master")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Skills (ทักษะที่สนใจ)", fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth())
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = state.usePython, onCheckedChange = { state.usePython = it })
            Text("Python")
            Spacer(modifier = Modifier.width(8.dp))
            Checkbox(checked = state.useKotlin, onCheckedChange = { state.useKotlin = it })
            Text("Kotlin")
            Spacer(modifier = Modifier.width(8.dp))
            Checkbox(checked = state.useJava, onCheckedChange = { state.useJava = it })
            Text("Java")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Notifications, contentDescription = null, tint = BrandBlue)
                Spacer(modifier = Modifier.width(8.dp))
                Text("รับการแจ้งเตือน", fontWeight = FontWeight.Bold)
            }
            Switch(checked = state.notificationEnabled, onCheckedChange = { state.notificationEnabled = it })
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Interest Level (ระดับความสนใจ)", fontWeight = FontWeight.Bold)
            Text("${state.interestLevel.toInt()}%", fontWeight = FontWeight.Bold, color = BrandBlue)
        }
        Slider(
            value = state.interestLevel,
            onValueChange = { state.interestLevel = it },
            valueRange = 0f..100f
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text("Profile Completion", fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = { state.profileProgress },
            modifier = Modifier.fillMaxWidth(),
            color = Color(0xFF4CAF50)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            "${(state.profileProgress * 100).toInt()}%",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(28.dp))

        // 12) Button + Event : REGISTER -> เปลี่ยน State -> Navigate
        Button(
            onClick = {
                state.registered = true
                onRegistered()
            },
            colors = ButtonDefaults.buttonColors(containerColor = BrandBlue),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {
            Icon(Icons.Default.Save, contentDescription = null, tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text("REGISTER", fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(20.dp))
    }

    if (showPhotoPicker) {
        PhotoPickerDialog(
            photos = sampleProfilePhotos,
            onSelect = {
                state.profilePhotoRes = it
                showPhotoPicker = false
            },
            onDismiss = { showPhotoPicker = false }
        )
    }
}