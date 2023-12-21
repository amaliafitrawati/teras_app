package com.bangkit.teras_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.bangkit.teras_app.ui.screen.login.LoginScreen
import com.bangkit.teras_app.ui.theme.Teras_appTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Teras_appTheme {
                Surface(
                    modifier = Modifier.fillMaxSize().background(Color.White),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TerasApp()
                }
            }
        }
    }
}