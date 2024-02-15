package com.example.little_lemon.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.little_lemon.R

// Set of Material typography styles to start with

val Markazi = FontFamily(
    Font(R.font.markazi_text_regular, FontWeight.Normal),
    Font(R.font.markazi_text_bold, FontWeight.Bold),
    Font(R.font.markazi_text_semi_bold, FontWeight.SemiBold),
    Font(R.font.markazi_text_edium, FontWeight.Medium)
)

val Karla = FontFamily(
    Font(R.font.karla_regular, FontWeight.Normal),
    Font(R.font.karla_medium, FontWeight.Medium),
    Font(R.font.karla_extra_bold, FontWeight.ExtraBold),
    Font(R.font.karla_bold, FontWeight.Bold)
)
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Karla,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium
    ),
    titleLarge = TextStyle(
        fontFamily = Markazi,
        fontSize = 64.sp,
        fontWeight = FontWeight.Medium
    ),
    titleMedium = TextStyle(
        fontFamily = Markazi,
        fontSize = 40.sp,
        fontWeight = FontWeight.Normal
    ),
    headlineLarge = TextStyle(
        fontFamily = Karla,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium
    ),
    displayMedium = TextStyle(
        fontFamily = Karla,
        fontSize = 16.sp,
        fontWeight = FontWeight.ExtraBold
    ),
    headlineMedium = TextStyle(
        fontFamily = Karla,
        fontSize = 20.sp,
        fontWeight = FontWeight.ExtraBold
    ),
    labelMedium = TextStyle(
        fontFamily = Karla,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    ),
    bodyMedium = TextStyle(
        fontFamily = Karla,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 1.5.sp,

    ),
    displaySmall = TextStyle(
        fontFamily = Karla,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium
    )







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
)