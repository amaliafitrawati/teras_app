package com.bangkit.teras_app.data

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import com.bangkit.teras_app.R

@Composable
fun LockScreenOrientation(orientation: Int) {
    val context = LocalContext.current
    DisposableEffect(orientation) {
        val activity = context.findActivity() ?: return@DisposableEffect onDispose {}
        val originalOrientation = activity.requestedOrientation
        activity.requestedOrientation = orientation
        onDispose {
            // restore original orientation when view disappears
            activity.requestedOrientation = originalOrientation
        }
    }
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

@Composable
fun topAppBarLabel(screenName: String): Int {
    return when (screenName) {
        "board" -> R.string.menu_board
        "browseStory" -> R.string.menu_browse_story
        "addStory" -> R.string.menu_add_story
        else -> R.string.app_name
    }
}

@Composable
fun checkMinus(value: Double?): Boolean {
    return if (value != null) {
        value < 0
    }else{
        false
    }
}