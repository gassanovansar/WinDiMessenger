package com.example.uikit.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.Notification
import com.example.uikit.designe.appCard.AppCard
import com.example.uikit.theme.AppTheme
import kotlinx.coroutines.delay


@Composable
fun NotificationCenter(notification: Notification?) {

    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(notification) {
        visible = notification != null
        delay(1500L)
        visible = false

    }


    val density = LocalDensity.current
    AnimatedVisibility(
        modifier = Modifier
            .padding(top = 16.dp)
            .padding(16.dp)
            .fillMaxWidth(),
        visible = visible,
        enter = slideInVertically {
            // Slide in from 40 dp from the top.
            with(density) { -40.dp.roundToPx() }
        } + expandVertically(
            // Expand from the top.
            expandFrom = Alignment.Top
        ) + fadeIn(
            // Fade in with the initial alpha of 0.3f.
            initialAlpha = 0.3f
        ),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()

    ) {
        AppCard(
            shape = RoundedCornerShape(16.dp),
            elevation = 4.dp,
            backgroundColor = AppTheme.colors.white
        ) {

            Text(
                modifier = Modifier.align(Alignment.Center),
                text = notification?.message ?: "",
                style = AppTheme.typography.medium.copy(
                    fontSize = 18.sp,
                    lineHeight = 25.sp,
                    color = AppTheme.colors.black,
                )
            )
        }

    }

}
