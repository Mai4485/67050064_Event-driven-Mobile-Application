package com.example.mystudentprofilecard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

// State กลางของทั้งแอป — เก็บค่าจากทุก UI Component ไว้ที่เดียว (State Hoisting)
// ทุกหน้าจอ (Registration/Profile/Settings/Home) รับ "state" ตัวเดียวกันนี้ผ่าน parameter
// แก้ค่าจากหน้าไหนก็ตาม หน้าอื่นที่อ่านค่าเดียวกันจะอัปเดตตามทันทีเพราะ Compose recompose ให้เอง
class RegistrationState {
    // Screen 1: Registration
    var studentName by mutableStateOf("")
    var studentId by mutableStateOf("")
    var selectedDegree by mutableStateOf("Bachelor")   // RadioButton + RadioGroup
    var usePython by mutableStateOf(true)              // CheckBox
    var useKotlin by mutableStateOf(true)
    var useJava by mutableStateOf(false)
    var interestLevel by mutableStateOf(50f)           // SeekBar
    var registered by mutableStateOf(false)            // Event หลังกด REGISTER

    var notificationEnabled by mutableStateOf(true)    // Switch
    var isFavorite by mutableStateOf(false)            // ToggleButton
    var darkMode by mutableStateOf(false)               // Switch

    var profilePhotoRes by mutableStateOf<Int?>(null)

    val profileProgress: Float
        get() {
            var filled = 0
            val total = 3
            if (studentName.isNotBlank()) filled++
            if (studentId.isNotBlank()) filled++
            if (usePython || useKotlin || useJava) filled++
            return filled.toFloat() / total
        }
}

@Composable
fun rememberRegistrationState(): RegistrationState = remember { RegistrationState() }