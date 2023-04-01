package com.historical.streetlight.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val GameColor = Color(0xFFD6CBAE)
val DarkGreen = Color(0xFF437369)
val ButtonGradientTop = Color(0xFF584C64)
val ButtonGradientBottom = Color(0xFF616267)
val DarkGreenTop = Color(0xFF3D5A4F)
val DarkGreenBottom = Color(0xFF437369)
val Gradient =
    Brush.verticalGradient(
        listOf(
            ButtonGradientTop,
            ButtonGradientBottom.copy(alpha = 0f)
        )
    )

val GradientWithGreen =
    Brush.verticalGradient(
        listOf(
            DarkGreenTop.copy(
                alpha = 0.75f
            ),
            DarkGreenBottom.copy(
                alpha = 0.75f
            )
        )
    )