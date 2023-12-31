package com.bangkit.teras_app

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.bangkit.teras_app.data.topAppBarLabel
import com.bangkit.teras_app.ui.components.BottomBar
import com.bangkit.teras_app.ui.navigation.Screen
import com.bangkit.teras_app.ui.screen.account.AccountScreen
import com.bangkit.teras_app.ui.screen.story.AddStoryScreen
import com.bangkit.teras_app.ui.screen.board.BoardScreen
import com.bangkit.teras_app.ui.screen.home.HomeScreen
import com.bangkit.teras_app.ui.screen.login.LoginScreen
import com.bangkit.teras_app.ui.screen.register.RegisterScreen
import com.bangkit.teras_app.ui.screen.story.BrowseStoryScreen

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TerasApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),){
    val currentRoute = currentRoute(navController).toString()
    Scaffold(
        topBar = {
            if (currentRoute !in listOf("home", "account","register","login")) {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = stringResource(topAppBarLabel(screenName = currentRoute)),
                            style = MaterialTheme.typography.headlineSmall,)
                    },
                    )
            }
        },
        bottomBar = {
            if (currentRoute !in listOf("register","login")) {
                BottomBar(navController,
                    modifier = Modifier
                        .fillMaxWidth()
                        .graphicsLayer {
                            shadowElevation = 50f
                        }
                )
            }
        },
        modifier = modifier
    ){ innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Login.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            navigation(
                startDestination = Screen.App.Home.route,
                route = Screen.App.route
            ) {
                composable(Screen.App.Home.route) {
                    HomeScreen()
                }
                composable(Screen.App.Board.route) {
                    BoardScreen()
                }
                composable(Screen.App.AddStory.route) {
                    AddStoryScreen()
                }
                composable(Screen.App.BrowseStory.route) {
                    BrowseStoryScreen()
                }
                composable(Screen.App.Account.route) {
                    AccountScreen(navController = navController)
                }

            }
            composable(Screen.Login.route) {
                LoginScreen(navController = navController)
            }
            composable(Screen.Register.route) {
                RegisterScreen(navController = navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TerasAppPreview(){
    TerasApp()
}