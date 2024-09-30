package com.example.uikit.designe.appCard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.uikit.theme.AppTheme

@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(8.dp),
    backgroundColor: Color = AppTheme.colors.gray1,
    border: BorderStroke? = null,
    elevation: Dp = 0.dp,
    content: @Composable BoxScope.() -> Unit
) {
    Card(
        modifier = modifier,
        backgroundColor = backgroundColor,
        shape = shape,
        border = border,
        elevation = elevation
    ) {
        Box {
            content()
        }

    }
}