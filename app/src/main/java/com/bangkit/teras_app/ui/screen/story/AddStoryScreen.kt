package com.bangkit.teras_app.ui.screen.story

import android.content.pm.ActivityInfo
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bangkit.teras_app.data.LockScreenOrientation

@Composable
fun AddStoryScreen(modifier: Modifier = Modifier) {
    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
}


@Preview(showBackground = true)
@Composable
fun AddStoryPreview(){
}