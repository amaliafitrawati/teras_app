package com.bangkit.teras_app.ui.screen.login

import android.content.pm.ActivityInfo
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.bangkit.teras_app.ui.common.UiState
import com.bangkit.teras_app.ui.components.EmailTextField
import com.bangkit.teras_app.ui.components.LoadingComponent
import com.bangkit.teras_app.ui.components.PasswordTextField
import com.bangkit.teras_app.ui.components.PopupDialog
import com.bangkit.teras_app.ui.navigation.Screen

@Composable
fun LoginScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel : LoginViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository(LocalContext.current)))){
    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var isShow by remember { mutableStateOf(false) }
    var alertMessage by remember { mutableStateOf("") }

    val uiState by viewModel.uiState.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(35.dp)
            .verticalScroll(rememberScrollState())){
        LoadingComponent(isLoading)
        PopupDialog(isShow = isShow, onDismiss = { isShow = false }, message = alertMessage)
        Text(
            text = stringResource(R.string.login_header),
            fontWeight = FontWeight.SemiBold,
            fontSize = 19.sp,
            modifier = Modifier.padding(bottom = 10.dp))

        Text(
            text = stringResource(R.string.desk_apk),
            fontWeight = FontWeight.Light,
            color = colorResource(R.color.lightGray),
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 108.dp)
        )

        LaunchedEffect(uiState){
            isLoading = false
            when(uiState){
                is UiState.Loading -> {
                    isLoading = true
                }is UiState.Success -> {
                    navController.navigate(Screen.App.route)
                }is UiState.Error ->{
                    isShow = true
                    alertMessage = (uiState as UiState.Error).errorMessage
                }
                else -> ""
            }
        }


        Image(
            painter = painterResource(R.drawable.img_login),
            contentDescription = "Image Register",
            modifier = Modifier
                .size(320.dp)
                .fillMaxWidth()
        )

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

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.primaryblue)),
                onClick = {
                          if(!email.isNullOrBlank() and !password.isNullOrBlank()){
                              viewModel.loginUser(email,password)
                          }
                },
                modifier = Modifier
                    .height(41.dp)
                    .width(160.dp)
                    .align(Alignment.Center)
            ) {
                Text(
                    text = stringResource(R.string.login_button),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally ){
            Text(
                text = stringResource(R.string.have_account),
                textAlign = TextAlign.Center,
            )
            ClickableText(
                text = AnnotatedString(stringResource(R.string.register_here)),
                style = TextStyle(
                    color = colorResource(R.color.primaryblue),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier.padding(start = 4.dp),
                onClick = {
                    navController.navigate(Screen.Register.route)
                }
            )
        }
    }
}
