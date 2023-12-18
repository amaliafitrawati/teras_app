package com.bangkit.teras_app.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsernameTextField(){
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier.fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameTextField(
    onChangedText: (String) -> Unit
){
    var text = remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = text.value,
        onValueChange = {
            text.value = it
            onChangedText(it.text)
        },
        leadingIcon = {
            Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = null)
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTextField(
    onChangedText: (String) -> Unit){
    var text = remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = text.value,
        onValueChange = {
            text.value = it
            onChangedText(it.text)
        },
        leadingIcon = {
            Icon(imageVector = Icons.Filled.AlternateEmail, contentDescription = null)
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    onChangedText: (String) -> Unit){
    var text = remember { mutableStateOf(TextFieldValue("")) }
    var showPassword: Boolean by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = text.value,
        onValueChange = {
            text.value = it
            onChangedText(it.text)
        },

        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
        leadingIcon = {
            IconButton(onClick = { showPassword = !showPassword}) {
                if(showPassword){
                    Icon(imageVector = Icons.Filled.Lock, contentDescription = "")
                }else {
                    Icon(imageVector = Icons.Filled.Lock, contentDescription = "")
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewComposable(){
    UsernameTextField()
}