package com.videolibrary.ui.theme

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
data class VideoLibraryColors(
    override val primary: Color,
    val tabSelected: Color,
    override val screenBackground: Color,
    override val actionBarBg: Color,
    override val listFirstText: Color,
    override val listSecondText: Color,
    val tabTextDefault: Color,
    val tabTextSelected: Color,
    override val dividerColor: Color,
    override val cardBackground: Color,
    override val menuBg: Color,
    override val iconColor: Color,
    val durationBg: Color,
    val durationText: Color,
    val overlayBg: Color,
    override val circleButtonBg: Color,
    override val popupBg: Color,
    val bottomBarBg: Color,
    override val detailLabelColor: Color,
) : LibraryColors

val LightVideoColors = VideoLibraryColors(
    primary = PrimaryLight,
    tabSelected = TabSelectedLight,
    screenBackground = ScreenBackgroundLight,
    actionBarBg = ActionBarBgLight,
    listFirstText = ListFirstTextLight,
    listSecondText = ListSecondTextLight,
    tabTextDefault = TabTextDefaultLight,
    tabTextSelected = TabTextSelectedLight,
    dividerColor = DividerColorLight,
    cardBackground = CardBackgroundLight,
    menuBg = MenuBgLight,
    iconColor = IconColorLight,
    durationBg = DurationBgColor,
    durationText = DurationTextColor,
    overlayBg = OverlayBgLight,
    circleButtonBg = CircleButtonBgLight,
    popupBg = PopupBgLight,
    bottomBarBg = BottomBarBgLight,
    detailLabelColor = DetailLabelLight,
)

val DarkVideoColors = VideoLibraryColors(
    primary = PrimaryDark,
    tabSelected = TabSelectedDark,
    screenBackground = ScreenBackgroundDark,
    actionBarBg = ActionBarBgDark,
    listFirstText = ListFirstTextDark,
    listSecondText = ListSecondTextDark,
    tabTextDefault = TabTextDefaultDark,
    tabTextSelected = TabTextSelectedDark,
    dividerColor = DividerColorDark,
    cardBackground = CardBackgroundDark,
    menuBg = MenuBgDark,
    iconColor = IconColorDark,
    durationBg = DurationBgColorDark,
    durationText = DurationTextColor,
    overlayBg = OverlayBgDark,
    circleButtonBg = CircleButtonBgDark,
    popupBg = PopupBgDark,
    bottomBarBg = BottomBarBgDark,
    detailLabelColor = DetailLabelDark,
)

val LocalVideoColors = compositionLocalOf { DarkVideoColors }

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
fun VideoLibraryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    lockFontScale: Boolean = true,
    content: @Composable () -> Unit
) {
    // Never use dynamic colors — always use our fixed Samsung-style palette
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val videoColors = if (darkTheme) DarkVideoColors else LightVideoColors

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
        LocalVideoColors provides videoColors,
        LocalLibraryColors provides videoColors
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}