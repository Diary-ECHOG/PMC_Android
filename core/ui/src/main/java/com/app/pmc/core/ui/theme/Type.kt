package com.app.pmc.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
    //todo : 사이즈 수정
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    )
)

val LargeDescription = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.W600,
    fontSize = 24.sp,
    lineHeight = 34.sp
)

val ButtonLabel600 = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.W600,
    fontSize = 14.sp,
    lineHeight = 24.sp
)

val ButtonLabel500 = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.W500,
    fontSize = 14.sp,
    lineHeight = 24.sp
)

val TextSmall = TextStyle(
    fontSize = 14.sp,
    lineHeight = 16.sp
)
