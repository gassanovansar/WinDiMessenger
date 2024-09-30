package com.example.core.ext

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import kotlinx.coroutines.delay


fun Modifier.clickableRound(
    radius: Dp,
    onClick: () -> Unit
): Modifier {
    return clip(RoundedCornerShape(radius))
        .clickable {
            onClick()
        }
}

fun Modifier.clickableDebounce(interval: Long = 400L,enabled: Boolean = true, onClick: () -> Unit): Modifier = composed(
    inspectorInfo = {
        name = "clickableOnce"
        value = onClick
    }
) {
    var enableAgain by remember { mutableStateOf(true) }
    LaunchedEffect(
        key1 = enableAgain,
        block = {
            if (enableAgain) return@LaunchedEffect
            delay(timeMillis = interval)
            enableAgain = true
        })
    clickable(enabled) {
        if (enableAgain) {
            enableAgain = false
            onClick()
        }
    }
}