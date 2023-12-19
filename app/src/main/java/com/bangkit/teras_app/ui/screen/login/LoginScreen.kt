package com.bangkit.teras_app.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit.teras_app.R
import com.bangkit.teras_app.ui.components.EmailTextField
import com.bangkit.teras_app.ui.components.PasswordTextField

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(35.dp)){

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
                onClick = { },
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
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(){
    LoginScreen()
}