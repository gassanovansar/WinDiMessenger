package com.example.uikit.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.PlatformParagraphStyle
import androidx.compose.ui.text.PlatformSpanStyle
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.LineHeightStyle
import com.example.uikit.R
import com.example.uikit.theme.typography.BaseTypography
import com.example.uikit.theme.typography.TextStyleMedium
import com.example.uikit.theme.typography.TextStyleRegular
import com.example.uikit.theme.typography.TextStyleSemiBold

object AppFonts {
    val regular: BaseTypography = TextStyleRegular(FontFamily(Font(R.font.regular)))
    val medium: BaseTypography = TextStyleMedium(FontFamily(Font(R.font.medium)))
    val semiBold: BaseTypography = TextStyleSemiBold(FontFamily(Font(R.font.semibold)))
}


class AppTypography(
    val regular: TextStyle,
    val medium: TextStyle,
    val semiBold: TextStyle,
)


@Composable
fun textStyles(): AppTypography {
    return AppTypography(
        regular = AppFonts.regular.getComposeTextStyle(),
        medium = AppFonts.medium.getComposeTextStyle(),
        semiBold = AppFonts.semiBold.getComposeTextStyle(),
    )
}

private fun toTextStyle(typographyStyle: BaseTypography): TextStyle {
    return TextStyle(
        fontSize = typographyStyle.fontSize,
        fontFamily = typographyStyle.fontFamily,
        lineHeight = typographyStyle.lineHeight,
        fontWeight = typographyStyle.fontWeight,
        platformStyle = PlatformTextStyle(
            PlatformSpanStyle.Default,
            PlatformParagraphStyle.Default
        ),
        baselineShift = BaselineShift(typographyStyle.baselineShift),
        lineHeightStyle = LineHeightStyle(
            LineHeightStyle.Alignment.Center,
            LineHeightStyle.Trim.None
        )
    )
}

fun BaseTypography.getComposeTextStyle(): TextStyle {
    return toTextStyle(this)
}

 val LocalTypography = compositionLocalOf<AppTypography> {
    error(
        "No typography provided! Make sure to wrap all usages of components in a " +
                "AppTheme."
    )
}
