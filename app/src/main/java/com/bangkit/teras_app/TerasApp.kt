package com.bangkit.teras_app

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bangkit.teras_app.ui.components.BottomBar
import com.bangkit.teras_app.ui.navigation.Screen
import com.bangkit.teras_app.ui.screen.account.AccountScreen
import com.bangkit.teras_app.ui.screen.story.AddStoryScreen
import com.bangkit.teras_app.ui.screen.board.BoardScreen
import com.bangkit.teras_app.ui.screen.home.HomeScreen
import com.bangkit.teras_app.ui.screen.story.BrowseStoryScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TerasApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),){
    Scaffold(
        bottomBar = {
            BottomBar(navController,
                modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer{
                        shadowElevation = 50f
                    }
            )
        },
        modifier = modifier
    ){ innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.Board.route) {
                BoardScreen()
            }
            composable(Screen.AddStory.route) {
                AddStoryScreen()
            }
            composable(Screen.BrowseStory.route) {
                BrowseStoryScreen()
            }
            composable(Screen.Account.route) {
                AccountScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TerasAppPreview(){
    TerasApp()
}