package com.example.mystudentprofilecard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StatItem(value: String, label: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(value, fontSize = 18.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        Text(label, fontSize = 12.sp, color = Color.Gray, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun InfoRow(icon: ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = Color(0xFF1565C0), modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(14.dp))
        Column {
            Text(label, fontSize = 17.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Text(value, fontSize = 15.sp, fontWeight = FontWeight.Normal, color = Color(0xFF616161))
        }
    }
}

val sampleProfilePhotos = listOf(
    R.drawable.me ,
    R.drawable.dog ,
    R.drawable.cat
)

@Composable
fun ProfileAvatar(photoRes: Int?, size: Dp, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.size(size).clip(CircleShape).background(Color(0xFFBBDEFB)),
        contentAlignment = Alignment.Center
    ) {
        if (photoRes != null) {
            Image(
                painter = painterResource(id = photoRes),
                contentDescription = "รูปโปรไฟล์",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(size).clip(CircleShape)
            )
        } else {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "รูปโปรไฟล์",
                modifier = Modifier.size(size * 0.56f),
                tint = Color(0xFF1565C0)
            )
        }
    }
}

@Composable
fun PhotoPickerDialog(photos: List<Int>, onSelect: (Int) -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {},
        title = { Text("เลือกรูปโปรไฟล์") },
        text = {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(photos) { res ->
                    Image(
                        painter = painterResource(id = res),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .clickable { onSelect(res) }
                    )
                }
            }
        }
    )
}