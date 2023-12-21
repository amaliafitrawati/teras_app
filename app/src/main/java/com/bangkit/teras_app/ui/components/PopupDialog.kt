package com.bangkit.teras_app.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun PopupDialog(status : String, isShow: Boolean, onDismiss: () -> Unit, message: String) {
    if (isShow) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(status)
            },
            text = {
                Text(message)
            },
            confirmButton = {
                if(status.equals("Gagal", true)){
                    Button(
                        onClick = onDismiss){
                        Text("Ok")
                    }
                }
            }
        )
    }
}