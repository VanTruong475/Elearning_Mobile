package com.example.btlop.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import com.example.btlop.data.ThemePreferences

// Light Theme Color Scheme (Trắng)
private val LightColorScheme = lightColorScheme(
    primary = LightPrimary,
    background = LightBackground,
    surface = LightSurface,
    onBackground = LightOnBackground,
    onSurface = LightOnSurface
)

// Dark Theme Color Scheme (Đen)
private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    background = DarkBackground,
    surface = DarkSurface,
    onBackground = DarkOnBackground,
    onSurface = DarkOnSurface
)

// Pink Theme Color Scheme (Hồng)
private val PinkColorScheme = lightColorScheme(
    primary = PinkPrimary,
    background = PinkBackground,
    surface = PinkSurface,
    onBackground = PinkOnBackground,
    onSurface = PinkOnSurface
)

// Blue Theme Color Scheme (Xanh dương)
private val BlueColorScheme = lightColorScheme(
    primary = BluePrimary,
    background = BlueBackground,
    surface = BlueSurface,
    onBackground = BlueOnBackground,
    onSurface = BlueOnSurface
)

@Composable
fun BtlopTheme(
    theme: String = ThemePreferences.THEME_LIGHT,
    content: @Composable () -> Unit
) {
    val colorScheme = when (theme) {
        ThemePreferences.THEME_LIGHT -> LightColorScheme
        ThemePreferences.THEME_DARK -> DarkColorScheme
        ThemePreferences.THEME_PINK -> PinkColorScheme
        ThemePreferences.THEME_BLUE -> BlueColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}