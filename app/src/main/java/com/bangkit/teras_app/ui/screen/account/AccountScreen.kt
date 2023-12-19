package com.bangkit.teras_app.ui.screen.account

import android.content.pm.ActivityInfo
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.bangkit.teras_app.data.LockScreenOrientation

@Composable
fun AccountScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier){
    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    Button(onClick = {
        navController.navigate("register")
    }) {

    }
}
