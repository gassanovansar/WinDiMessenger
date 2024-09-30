package com.example.uikit.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

interface AppColors {
    val white: Color
    val black: Color
    val gray1: Color
    val gray2: Color
    val gray3: Color
    val gray4: Color
    val gray5: Color
    val blue: Color
    val darkBlue: Color
    val green: Color
    val darkGreen: Color
    val red: Color
    val shadows: Color


}


class AppColorsLight(
    override val white: Color,
    override val black: Color,
    override val gray1: Color,
    override val gray2: Color,
    override val gray3: Color,
    override val gray4: Color,
    override val gray5: Color,
    override val blue: Color,
    override val darkBlue: Color,
    override val green: Color,
    override val darkGreen: Color,
    override val red: Color,
    override val shadows: Color


) : AppColors

 fun appLightColors() = AppColorsLight(
    white = Color(0xFFFFFFFF),
    black = Color(0xFF0C0C0C),
    gray1 = Color(0xFF222325),
    gray2 = Color(0xFF313234),
    gray3 = Color(0xFF858688),
    gray4 = Color(0xFF9F9F9F),
    gray5 = Color(0xFFDBDBDB),
    blue = Color(0xFF2B7EFE),
    darkBlue = Color(0xFF00427D),
    green = Color(0xFF4CB24E),
    darkGreen = Color(0xFF015905),
    red = Color(0xFFFF0000),
    shadows = Color(0xE50C0C0C),
)


 fun appDarkColors() = AppColorsLight(
    white = Color(0xFFFFFFFF),
    black = Color(0xFF0C0C0C),
    gray1 = Color(0xFF222325),
    gray2 = Color(0xFF313234),
    gray3 = Color(0xFF858688),
    gray4 = Color(0xFF9F9F9F),
    gray5 = Color(0xFFDBDBDB),
    blue = Color(0xFF2B7EFE),
    darkBlue = Color(0xFF00427D),
    green = Color(0xFF4CB24E),
    darkGreen = Color(0xFF015905),
    red = Color(0xFFFF0000),
    shadows = Color(0xE50C0C0C),
)


 val LocalColors = compositionLocalOf<AppColors> {
    error(
        "No colors provided! Make sure to wrap all usages of components in a " +
                "AppTheme."
    )
}
