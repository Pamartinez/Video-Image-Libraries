package com.example.common.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * Minimum color contract shared by both ImageLibraryColors and VideoLibraryColors.
 * Compose components in the common module read from [LocalLibraryColors].
 */
interface LibraryColors {
    val primary: Color
    val screenBackground: Color
    val actionBarBg: Color
    val listFirstText: Color
    val listSecondText: Color
    val dividerColor: Color
    val cardBackground: Color
    val menuBg: Color
    val iconColor: Color
    val circleButtonBg: Color
    val popupBg: Color
    val detailLabelColor: Color
}

/**
 * CompositionLocal providing the active [LibraryColors].
 * Each library theme must provide its own color object via [LocalLibraryColors].
 */
val LocalLibraryColors = compositionLocalOf<LibraryColors> {
    error("No LibraryColors provided. Wrap your content in ImageLibraryTheme / VideoLibraryTheme.")
}

