package com.bangkit.teras_app.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.bangkit.teras_app.R
import com.bangkit.teras_app.ui.navigation.NavigationItem
import com.bangkit.teras_app.ui.navigation.Screen

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
        containerColor = Color.White
    ) {
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = ImageVector.vectorResource(R.drawable.ic_home),
                screen = Screen.App.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_board),
                icon = ImageVector.vectorResource(R.drawable.ic_board),
                screen = Screen.App.Board
            ),
//            NavigationItem(
//                title = stringResource(R.string.menu_add_story),
//                icon = ImageVector.vectorResource(R.drawable.ic_circle_add),
//                screen = Screen.App.AddStory
//            ),
//            NavigationItem(
//                title = stringResource(R.string.menu_browse_story),
//                icon = ImageVector.vectorResource(R.drawable.ic_browse),
//                screen = Screen.App.BrowseStory
//            ),
            NavigationItem(
                title = stringResource(R.string.menu_account),
                icon = ImageVector.vectorResource(R.drawable.ic_account),
                screen = Screen.App.Account
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                selected = false,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
            )
        }
    }
}

