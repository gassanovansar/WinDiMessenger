package com.example.uikit.designe.toolBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.core.ext.clickableRound
import com.example.uikit.R
import com.example.uikit.theme.AppTheme


@Composable
 fun Toolbar(
    modifier: Modifier = Modifier,
    startTitle: String? = null,
    title: String? = null,
    centerContent: @Composable () -> Unit = {},
    leftIcon: (@Composable () -> Unit)? = null,
    rightIcon: (@Composable () -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(horizontal = 16.dp),
    ) {
        Row(modifier = Modifier.align(Alignment.CenterStart)) {
            Box(modifier = Modifier.align(Alignment.CenterVertically)) {
                leftIcon?.invoke()
            }
            if (startTitle != null) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = if (leftIcon == null) 0.dp else 16.dp),
                    text = startTitle,
                    style = AppTheme.typography.semiBold.copy(
                        fontSize = 20.sp,
                        lineHeight = 28.sp,
                        color = AppTheme.colors.white,
                    )
                )
            }


        }

        Box(modifier = Modifier.align(Alignment.Center)) {
            if (title != null) {
                Text(
                    text = title,
                    style = AppTheme.typography.semiBold.copy(
                        fontSize = 20.sp,
                        lineHeight = 28.sp,
                        color = AppTheme.colors.white,
                    )
                )
            } else {
                Box(modifier = Modifier.padding(start = 8.dp)) {
                    centerContent()
                }
            }
        }

        Box(modifier = Modifier.align(Alignment.CenterEnd)) {
            rightIcon?.invoke()
        }


    }
}

@Composable
 fun BackIcon(modifier: Modifier = Modifier, onClick: (() -> Unit)? = null) {
    val navigator = LocalNavigator.currentOrThrow
    Image(
        painter = painterResource(id = R.drawable.ic_back),
        contentDescription = null,
        modifier = modifier.clickableRound(32.dp) {
            if (onClick == null) navigator.pop()
            else onClick()
        }
    )
}

