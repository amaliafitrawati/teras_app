package com.bangkit.teras_app.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import kotlinx.coroutines.delay

@Composable
fun LoadingComponent(isLoading: Boolean) {
    if (isLoading) {
        Box(modifier = Modifier.run { fillMaxSize() }) {
            CircularProgressIndicator(
                color = Color.Blue,
                modifier = Modifier
                    .size(75.dp)
                    .padding(16.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
fun loadingAnimation(
    isLoading: Boolean,
    circleColor: Color = Color.Blue,
    animationDelay: Int = 1500,
) {
    if(isLoading){
        Box(){
            val circles = listOf(
                remember {
                    Animatable(initialValue = 0f)
                },
                remember {
                    Animatable(initialValue = 0f)
                },
                remember {
                    Animatable(initialValue = 0f)
                }
            )

            circles.forEachIndexed { index, animatable ->
                LaunchedEffect(Unit) {
                    delay(timeMillis = (animationDelay / 3L) * (index + 1))
                    animatable.animateTo(
                        targetValue = 1f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(
                                durationMillis = animationDelay,
                                easing = LinearEasing
                            ),
                            repeatMode = RepeatMode.Restart
                        )
                    )
                }
            }


            Box(
                modifier = Modifier
                    .size(size = 200.dp)
                    .background(color = Color.Transparent)
            ) {
                circles.forEachIndexed { index, animatable ->
                    Box(
                        modifier = Modifier
                            .scale(scale = animatable.value)
                            .size(size = 200.dp)
                            .clip(shape = CircleShape)
                            .background(
                                color = circleColor
                                    .copy(alpha = (1 - animatable.value))
                            )
                    ) {
                    }
                }
            }
        }
    }
}