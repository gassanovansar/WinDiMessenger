package com.example.uikit.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.uikit.theme.AppTheme


@Composable
fun PageContainer(
    modifier: Modifier = Modifier,
    background: Color = AppTheme.colors.shadows,
    brush: Brush? = null,
    fill: Boolean = true,
    isLoading: State<Boolean> = mutableStateOf(false),
    header: @Composable (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit,
    footer: @Composable (BoxScope.() -> Unit)? = null,
) {
    var modifier = if (brush != null) {
        modifier.background(brush)
    } else {
        modifier.background(background)
    }
    modifier = if (fill) {
        modifier
            .fillMaxHeight()
    } else {
        modifier.padding(bottom = 12.dp)
    }
    val localFocusManager = LocalFocusManager.current

    DisposableEffect(key1 = localFocusManager) {
        onDispose {
            localFocusManager.clearFocus(true)
        }
    }
    Box {


        Column(
            modifier = modifier
                .fillMaxWidth()
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        localFocusManager.clearFocus()
                    })
                }
        ) {
            if (!fill) {
                Box(
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(AppTheme.colors.gray2)
                        .width(38.dp)
                        .height(5.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
            Box(modifier = Modifier.fillMaxWidth()) {
                header?.invoke()
            }

            Box(
                modifier = Modifier
                    .weight(1f, fill)
            ) {
                content()
//            ErrorMessageView(error = error)
            }
            Box(modifier = Modifier.fillMaxWidth()) {
                footer?.invoke(this)
            }


        }
        if (isLoading.value) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(AppTheme.colors.shadows.copy(alpha = 0.5F))


            )
        }
    }


}

