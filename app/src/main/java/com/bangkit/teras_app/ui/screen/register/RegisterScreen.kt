package com.bangkit.teras_app.ui.screen.register

import android.content.pm.ActivityInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bangkit.teras_app.R
import com.bangkit.teras_app.ViewModelFactory
import com.bangkit.teras_app.data.LockScreenOrientation
import com.bangkit.teras_app.di.Injection
import com.bangkit.teras_app.model.listProvince
import com.bangkit.teras_app.ui.common.UiState
import com.bangkit.teras_app.ui.components.EmailTextField
import com.bangkit.teras_app.ui.components.NameTextField
import com.bangkit.teras_app.ui.components.PasswordTextField
import com.bangkit.teras_app.ui.components.PopupDialog
import com.bangkit.teras_app.ui.components.ProvinceSpinner
import com.bangkit.teras_app.ui.navigation.Screen

@Composable
fun RegisterScreen(
    navController: NavHostController,
    viewModel : RegisterViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository(LocalContext.current)))){
    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var province by remember { mutableStateOf<Pair<String, String>>(Pair("", "")) }
    var isShow by remember { mutableStateOf(false) }
    var alertMessage by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }

    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(35.dp)
            .verticalScroll(rememberScrollState())){
        Text(
            text = stringResource(R.string.register_header),
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 5.dp))

        Text(
            text = stringResource(R.string.register_text),
            fontWeight = FontWeight.Light,
            color = colorResource(R.color.lightGray),
            fontSize = 14.sp)

        Image(
            painter = painterResource(R.drawable.img_register),
            contentDescription = "Image Register",
            modifier = Modifier
                .size(300.dp)
                .fillMaxWidth())

        Text(
            text = stringResource(R.string.name),
            fontWeight = FontWeight.Normal,
            color = colorResource(R.color.hitGray),
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 4.dp, top = 12.dp))

        NameTextField(onChangedText = {name = it})

        Text(
            text = stringResource(R.string.email),
            fontWeight = FontWeight.Normal,
            color = colorResource(R.color.hitGray),
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 4.dp, top = 12.dp))

        EmailTextField(onChangedText = {email = it})

        Text(
            text = stringResource(R.string.password),
            fontWeight = FontWeight.Normal,
            color = colorResource(R.color.hitGray),
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 4.dp, top = 12.dp))

        PasswordTextField(onChangedText = {password = it})


        ProvinceSpinner(list = listProvince,
            preselected = listProvince.first(),
            onSelectionChanged = { selection ->
                province = selection
            } )

        Button(
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.primaryblue)),
                onClick = {
                    viewModel.register(name, email, password, province.first)
                },
                modifier = Modifier.padding(bottom = 4.dp, top = 12.dp)) {
            Text(
                text = stringResource(R.string.register_btn),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        LaunchedEffect(uiState){
            when(uiState){
            is UiState.Loading -> {
            }is UiState.Success -> {
                status = "Berhasil"
                isShow = true
                alertMessage = "Registrasi Berhasil"
                navController.navigate(Screen.Login.route)
            }is UiState.Error ->{
                isShow = true
                status = "Gagal"
                alertMessage = "Email Sudah Terdaftar!"
            }
                else -> {}
            }
        }

        PopupDialog(status = status, isShow = isShow, onDismiss = { isShow = false }, message = alertMessage)

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally ){
            Text(
                text = stringResource(R.string.have_account),
                textAlign = TextAlign.Center,
            )
            ClickableText(
                text = AnnotatedString(stringResource(R.string.login_here)),
                style = TextStyle(
                    color = colorResource(R.color.primaryblue),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier.padding(start = 4.dp),
                onClick = {
                    navController.navigate("login")
                }
            )
        }
    }
}
