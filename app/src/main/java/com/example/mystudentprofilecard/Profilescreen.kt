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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen(state: RegistrationState, onEditClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                onClick = onEditClick,
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(Icons.Default.Edit, contentDescription = "แก้ไขโปรไฟล์", tint = BrandBlue)
            }
        }

        ProfileAvatar(photoRes = state.profilePhotoRes, size = 100.dp)

        Spacer(modifier = Modifier.height(12.dp))

        val displayName = state.studentName.ifBlank { "ยังไม่ได้กรอกชื่อ" }
        Text(displayName, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Computer Science", fontSize = 14.sp, color = Color.Gray)
        if (state.studentId.isNotBlank()) {
            Text("ID: ${state.studentId}", fontSize = 14.sp, color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                InfoRow(icon = Icons.Default.School, label = "Degree", value = state.selectedDegree)

                Spacer(modifier = Modifier.height(8.dp))

                val skills = buildList {
                    if (state.usePython) add("Python")
                    if (state.useKotlin) add("Kotlin")
                    if (state.useJava) add("Java")
                }
                InfoRow(
                    icon = Icons.Default.Code,
                    label = "Skills",
                    value = if (skills.isEmpty()) "-" else skills.joinToString(" • ")
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.TrendingUp,
                        contentDescription = null,
                        tint = BrandBlue,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(14.dp))
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "Interest Level",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Text(
                                "${state.interestLevel.toInt()}%",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xFF616161)
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        LinearProgressIndicator(
                            progress = { state.interestLevel / 100f },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Surface(
            modifier = Modifier.toggleable(
                value = state.isFavorite,
                onValueChange = { state.isFavorite = it }
            ),
            shape = RoundedCornerShape(50),
            color = if (state.isFavorite) Color(0xFFFCE4EC) else Color(0xFFF0F0F0)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 28.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = if (state.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (state.isFavorite) Color(0xFFE91E63) else Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "Favorite",
                    fontSize = 16.sp,
                    color = if (state.isFavorite) Color(0xFFE91E63) else Color.Gray,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onEditClick,
            colors = ButtonDefaults.buttonColors(containerColor = BrandBlue),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Icon(Icons.Default.Edit, contentDescription = null, tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Edit Profile", fontWeight = FontWeight.Bold)
        }
    }
}