package com.bangkit.teras_app.ui.screen.story

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BrowseStoryScreen(modifier: Modifier = Modifier) {
    Text(text = "Browse Story Screen")
}

@Preview(showBackground = true)
@Composable
fun BrowseStoryPreview(){
    BrowseStoryScreen()
}