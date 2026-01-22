package com.example.ui_interface.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

@Immutable
data class CustomTypography(
    val titleBold: TextStyle = TextStyle(
        fontSize = 32.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 1.em,
        letterSpacing = 0.em,
        color = black
    ),
    val titleBold2: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 0.2.em,
        letterSpacing = 0.em,
        color = black
    ),
    val headLineMedium: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 0.24.em,
        letterSpacing = 0.em,
        color = gray
    ),
    val titleMedium: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.W500,
        lineHeight = 0.20.em,
        letterSpacing = 0.em,
        color = black
    ),
    val titleMediumError: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.W500,
        lineHeight = 0.20.em,
        letterSpacing = 0.em,
        color = red
    ),
    val titleMedium2: TextStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.W500,
        lineHeight = 0.16.em,
        letterSpacing = 0.em,
        color = darkGray
    ),
    val titleMedium3: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.W500,
        lineHeight = 1.em,
        letterSpacing = 0.em,
        color = gray
    ),
    val titleMedium4: TextStyle = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.W500,
        lineHeight = 0.2.em,
        letterSpacing = 0.em,
        color = darkGray
    ),
    val headLineRegular: TextStyle = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 0.16.em,
        letterSpacing = 0.em,
        color = gray
    ),
    val headLineRegular2: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 0.2.em,
        letterSpacing = 0.em,
        color = gray
    ),
    val headLineRegular3: TextStyle = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 1.em,
        letterSpacing = 0.em,
        color = white
    ),
    val captionSemibold: TextStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.W600,
        lineHeight = 0.22.em,
        letterSpacing = 0.em,
        color = white
    ),
    val captionSemibold2: TextStyle = TextStyle(
        fontSize = 21.sp,
        fontWeight = FontWeight.W600,
        lineHeight = 0.1.em,
        letterSpacing = 0.em,
        color = black
    ),
    val captionSemibold3: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.W600,
        lineHeight = 0.2.em,
        letterSpacing = 0.em,
        color = black
    ),
)
val LocalTypography = staticCompositionLocalOf { CustomTypography() }

@Composable
fun CustomTheme(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalTypography provides CustomTypography()
    ) {
        content
    }
}

//object CustomTheme{
//    val customColors: CustomColors
//        @Composable
//        @ReadOnlyComposable
//        get() = LocalCustomColor.current
//
//    val customTypography:CustomTypography
//        @Composable
//        @ReadOnlyComposable
//        get() = LocalTypography.current
//}