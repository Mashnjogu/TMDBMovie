package com.example.tmdbmovie.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color.Black,
    primaryVariant = Color.Black,
    secondary = SoftYellow,
    error = RedErrorLight,
    onPrimary = Color.White,
    background = BlackBlue,
    onBackground = Color.White,
    surface = Color.DarkGray
)

private val LightColorPalette = lightColors(
    primary = Color.White,
    primaryVariant = Color.LightGray,
    secondary = BlackBlue,
    onPrimary = BlackBlue,
    error = RedErrorDark,
    onError = RedErrorLight,
    background = Color.White,
    onSecondary = SoftYellow,
    onBackground = BlackBlue,
    surface = LightGray

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun TMDBMovieTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = MontseratTypography,
        shapes = Shapes,
        content = content
    )
}