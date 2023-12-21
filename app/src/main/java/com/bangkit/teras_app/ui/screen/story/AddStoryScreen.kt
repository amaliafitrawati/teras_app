package com.bangkit.teras_app.ui.screen.story

import android.content.pm.ActivityInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bangkit.teras_app.R
import com.bangkit.teras_app.ViewModelFactory
import com.bangkit.teras_app.data.LockScreenOrientation
import com.bangkit.teras_app.di.Injection
import com.bangkit.teras_app.ui.screen.account.AccountViewModel
import com.bangkit.teras_app.ui.theme.plusjakartasansFontFamily

@Composable
fun AddStoryScreen(modifier: Modifier = Modifier,
                   viewModel : AccountViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository(LocalContext.current)))){
    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .size(500.dp)
                    .shadow(
                        elevation = 1.dp,
                        shape = RoundedCornerShape(bottomEnd = 22.dp, bottomStart = 22.dp)
                    )
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.photoprofile),
                        contentDescription = "Profile Photo",
                        modifier = Modifier
                            .size(135.dp)
                            .padding(8.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                    )
                    Text(
                        text = "Nama",
                        style = TextStyle(
                            fontFamily = plusjakartasansFontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 30.sp,
                            color = Color.Black
                        ),
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = "Alamat",
                        style = TextStyle(
                            fontFamily = plusjakartasansFontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp,
                            color = Color.Black
                        ),
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(35.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {

                    IconButton(
                        onClick = { /*TODO*/ },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Logout,
                            contentDescription = "Logout",
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Text(
                        text = "Logout",
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                    )
                }
            }
        }
}


@Preview(showBackground = true)
@Composable
fun AddStoryPreview(){
    AddStoryScreen()
}