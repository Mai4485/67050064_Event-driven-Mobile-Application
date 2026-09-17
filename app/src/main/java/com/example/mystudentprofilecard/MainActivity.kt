package com.example.mystudentprofilecard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mystudentprofilecard.ui.theme.MyStudentProfileCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state = rememberRegistrationState()
            MyStudentProfileCardTheme(darkTheme = state.darkMode) {
                AppRoot(state = state)
            }
        }
    }
}