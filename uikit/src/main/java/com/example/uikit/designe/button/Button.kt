package com.example.uikit.designe.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uikit.theme.AppTheme

@Composable
fun PrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leftContent: @Composable () -> Unit = {},
    rightContent: @Composable () -> Unit = {},
    backgroundColor: ButtonColor = ButtonColor.BLUE,
    size: Size = Size.XXL,
    round: Round = Round.SQUARE,
    onClick: () -> Unit
) {
    val style = when (size) {
        Size.L, Size.XL -> AppTheme.typography.regular
        Size.XXL -> AppTheme.typography.semiBold
    }
    Button(
        enabled = enabled,
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = when (backgroundColor) {
                ButtonColor.BLUE -> AppTheme.colors.blue
                ButtonColor.GREEN -> AppTheme.colors.green
                ButtonColor.TRANSPARENT -> Color.Transparent
            },
            contentColor = Color.White,
            disabledBackgroundColor = when (backgroundColor) {
                ButtonColor.BLUE -> AppTheme.colors.darkBlue
                ButtonColor.GREEN -> AppTheme.colors.darkGreen
                ButtonColor.TRANSPARENT -> Color.Transparent
            }
        ),
        contentPadding = when (size) {
            Size.L -> PaddingValues(horizontal = 8.dp, vertical = 7.dp)
            Size.XL -> PaddingValues(horizontal = 8.dp, vertical = 11.dp)
            Size.XXL -> PaddingValues(horizontal = 8.dp, vertical = 14.dp)

        },
        shape = when (round) {
            Round.SQUARE -> RoundedCornerShape(8.dp)
            Round.CIRCLE -> RoundedCornerShape(50.dp)
        },
        elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp, 0.dp, 0.dp),
    ) {
        leftContent()
        Text(
            maxLines = 1,
            modifier = Modifier,
            text = text,
            style = style.copy(
                fontSize = when (size) {
                    Size.L, Size.XL -> 14.sp
                    Size.XXL -> 16.sp
                },
                lineHeight = when (size) {
                    Size.L, Size.XL -> 18.2.sp
                    Size.XXL -> 20.8.sp
                },
                color = if (enabled) when (backgroundColor) {
                    ButtonColor.BLUE, ButtonColor.GREEN -> AppTheme.colors.white
                    ButtonColor.TRANSPARENT -> AppTheme.colors.blue
                } else when (backgroundColor) {
                    ButtonColor.BLUE, ButtonColor.GREEN -> AppTheme.colors.gray4
                    ButtonColor.TRANSPARENT -> AppTheme.colors.darkBlue
                },
                textAlign = TextAlign.Center,
            )
        )
        rightContent()
    }
}


enum class ButtonColor {
    BLUE, GREEN, TRANSPARENT
}

enum class Size {
    L, XL, XXL
}

enum class Round {
    SQUARE, CIRCLE
}

