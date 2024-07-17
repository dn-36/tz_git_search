package com.project.tzgamblingcompany.presentation.ui.splash

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.tzgamblingcompany.R

@Composable
fun SplashComponent() {
    var startAnimation by remember { mutableStateOf(false) }


    val alpha by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = TweenSpec(durationMillis = 2000, easing = LinearEasing)
    )


    LaunchedEffect(Unit) {
        startAnimation = true
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "App Logo",
                modifier = Modifier.size(100.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary.copy(alpha = alpha))
            )
            Spacer(modifier = Modifier.height(16.dp))
            androidx.compose.material3.Text(
                text = "MyApp",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary.copy(alpha = alpha)
            )
        }
    }
}