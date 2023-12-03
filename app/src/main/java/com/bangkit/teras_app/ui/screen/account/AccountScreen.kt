package com.bangkit.teras_app.ui.screen.account

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AccountScreen(modifier: Modifier = Modifier){
    Text(text = "Account Screen")
}

@Preview(showBackground = true)
@Composable
fun AccountScreenPreview(){
    AccountScreen()
}