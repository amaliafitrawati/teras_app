package com.bangkit.teras_app.ui.navigation

sealed class Screen(val route : String) {

    object App : Screen(route = "app") {
        object Home : Screen("home")
        object Board : Screen("board")
        object AddStory : Screen("addStory")
        object BrowseStory : Screen("browseStory")
        object Account : Screen("account")
        object DetailStory : Screen("browseStory/{id}") {
            fun createRoute(id: String) = "browseStory/$id"
        }
    }


    object Login : Screen("login")
    object Register : Screen("register")
}