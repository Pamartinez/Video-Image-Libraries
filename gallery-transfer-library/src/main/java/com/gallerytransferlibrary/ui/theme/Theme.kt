package com.gallerytransferlibrary.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
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
import com.example.common.ui.theme.CardBackgroundDark
import com.example.common.ui.theme.CircleButtonBgDark
import com.example.common.ui.theme.DetailLabelDark
import com.example.common.ui.theme.DividerColorDark
import com.example.common.ui.theme.IconColorDark
import com.example.common.ui.theme.ListFirstTextDark
import com.example.common.ui.theme.ListSecondTextDark
import com.example.common.ui.theme.MenuBgDark
import com.example.common.ui.theme.PopupBgDark
import com.example.common.ui.theme.PrimaryDark
import com.example.common.ui.theme.ScreenBackgroundDark

/**
 * Concrete [LibraryColors] for Gallery Transfer. This app ships dark-theme only to match its
 * media-viewer focus (mirrors how the sibling libraries pin a fixed Samsung-style palette).
 */
data class GalleryTransferColors(
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
    val bottomBarBg: Color,
    val overlayBg: Color,
    val durationBg: Color,
    val durationText: Color,
) : LibraryColors

val DarkGalleryColors = GalleryTransferColors(
    primary = PrimaryDark,
    screenBackground = ScreenBackgroundDark,
    actionBarBg = ScreenBackgroundDark,
    listFirstText = ListFirstTextDark,
    listSecondText = ListSecondTextDark,
    dividerColor = DividerColorDark,
    cardBackground = CardBackgroundDark,
    menuBg = MenuBgDark,
    iconColor = IconColorDark,
    circleButtonBg = CircleButtonBgDark,
    popupBg = PopupBgDark,
    detailLabelColor = DetailLabelDark,
    bottomBarBg = BottomBarBgDark,
    overlayBg = OverlayBgDark,
    durationBg = DurationBgColorDark,
    durationText = DurationTextColor,
)

val LocalGalleryColors = compositionLocalOf { DarkGalleryColors }

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

@Composable
fun GalleryTransferTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    lockFontScale: Boolean = true,
    content: @Composable () -> Unit
) {
    val galleryColors = DarkGalleryColors

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    val currentDensity = LocalDensity.current
    val fixedDensity = remember(currentDensity) {
        Density(density = currentDensity.density, fontScale = 1f)
    }

    CompositionLocalProvider(
        LocalDensity provides if (lockFontScale) fixedDensity else currentDensity,
        LocalGalleryColors provides galleryColors,
        LocalLibraryColors provides galleryColors
    ) {
        MaterialTheme(
            colorScheme = DarkColorScheme,
            typography = Typography,
            content = content
        )
    }
}
