package com.bangkit.teras_app.ui.screen.register

import android.content.pm.ActivityInfo
import android.util.Log
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.bangkit.teras_app.data.RiceProductionRepository
import com.bangkit.teras_app.model.listProvince
import com.bangkit.teras_app.ui.components.EmailTextField
import com.bangkit.teras_app.ui.components.NameTextField
import com.bangkit.teras_app.ui.components.PasswordTextField
import com.bangkit.teras_app.ui.components.SampleSpinner

@Composable
fun RegisterScreen(
    navController: NavHostController,
    viewModel : RegisterViewModel = viewModel(factory = ViewModelFactory(RiceProductionRepository())),
    modifier: Modifier = Modifier) {
    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var province by remember { mutableStateOf<Pair<String, String>>(Pair("", "")) }

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
            text = stringResource(R.string.username),
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


        SampleSpinner(list = listProvince,
            preselected = listProvince.first(),
            onSelectionChanged = { selection ->
                Log.e("SELECTION DATA", selection.first )
                Log.e("SELECTION SECOND", selection.second )
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

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center, // Align vertically at center
            horizontalAlignment = Alignment.CenterHorizontally ){
            Text(
                text = "Already Have account?",
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
