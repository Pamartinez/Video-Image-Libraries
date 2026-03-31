package com.imagelibrary.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Density
import androidx.core.view.WindowCompat
import com.example.common.ui.theme.LibraryColors
import com.example.common.ui.theme.LocalLibraryColors

/**
 * Custom color holder matching the Blazor app.css CSS variables exactly.
 */
data class ImageLibraryColors(
    override val primary: Color,
    override val screenBackground: Color,
    override val actionBarBg: Color,
    override val listFirstText: Color,
    override val listSecondText: Color,
    override val dividerColor: Color,
    override val cardBackground: Color,
    override val menuBg: Color,
    override val iconColor: Color,
    override val circleButtonBg: Color,
    override val popupBg: Color,
    override val detailLabelColor: Color,
) : LibraryColors

val LightImageColors = ImageLibraryColors(
    primary = PrimaryLight,
    screenBackground = ScreenBackgroundLight,
    actionBarBg = ActionBarBgLight,
    listFirstText = ListFirstTextLight,
    listSecondText = ListSecondTextLight,
    dividerColor = DividerColorLight,
    cardBackground = CardBackgroundLight,
    menuBg = MenuBgLight,
    iconColor = IconColorLight,
    circleButtonBg = CircleButtonBgLight,
    popupBg = PopupBgLight,
    detailLabelColor = DetailLabelLight,
)

val DarkImageColors = ImageLibraryColors(
    primary = PrimaryDark,
    screenBackground = ScreenBackgroundDark,
    actionBarBg = ActionBarBgDark,
    listFirstText = ListFirstTextDark,
    listSecondText = ListSecondTextDark,
    dividerColor = DividerColorDark,
    cardBackground = CardBackgroundDark,
    menuBg = MenuBgDark,
    iconColor = IconColorDark,
    circleButtonBg = CircleButtonBgDark,
    popupBg = PopupBgDark,
    detailLabelColor = DetailLabelDark,
)

val LocalImageColors = compositionLocalOf { DarkImageColors }

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    onPrimary = Color.Black,
    primaryContainer = Color(0xFF1A3A5C),
    onPrimaryContainer = PrimaryDark,
    secondary = PrimaryDark,
    background = ScreenBackgroundDark,
    surface = ScreenBackgroundDark,
    onBackground = ListFirstTextDark,
    onSurface = ListFirstTextDark,
    onSurfaceVariant = ListSecondTextDark,
    surfaceVariant = CardBackgroundDark,
    outline = DividerColorDark,
    outlineVariant = DividerColorDark,
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryLight,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFD4E4F7),
    onPrimaryContainer = PrimaryLight,
    secondary = PrimaryLight,
    background = ScreenBackgroundLight,
    surface = ScreenBackgroundLight,
    onBackground = ListFirstTextLight,
    onSurface = ListFirstTextLight,
    onSurfaceVariant = ListSecondTextLight,
    surfaceVariant = ScreenBackgroundLight,
    outline = DividerColorLight,
    outlineVariant = DividerColorLight,
)

@Composable
fun ImageLibraryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    lockFontScale: Boolean = true,
    content: @Composable () -> Unit
) {
    // Never use dynamic colors — always use our fixed Samsung-style palette
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val imageColors = if (darkTheme) DarkImageColors else LightImageColors

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    val currentDensity = LocalDensity.current
    val fixedDensity = remember(currentDensity) {
        Density(density = currentDensity.density, fontScale = 1f)
    }

    CompositionLocalProvider(
        LocalDensity provides if (lockFontScale) fixedDensity else currentDensity,
        LocalImageColors provides imageColors,
        LocalLibraryColors provides imageColors
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}