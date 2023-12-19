package com.bangkit.teras_app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.bangkit.teras_app.R

val customFont = FontFamily(
    Font(R.font.custom_regular),
    Font(R.font.custom_bold),
    Font(R.font.custom_semi_bold),
    Font(R.font.custom_extra_bold),
    Font(R.font.custom_light),
    Font(R.font.custom_extra_light),
    Font(R.font.custom_italic)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = customFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val plusjakartasansFontFamily = FontFamily(
    Font(R.font.plusjakartasans_regular, FontWeight.Normal),
    Font(R.font.plusjakartasans_medium, FontWeight.Medium),
    Font(R.font.plusjakartasans_semibold, FontWeight.SemiBold),
    Font(R.font.plusjakartasans_bold, FontWeight.Bold)
)
