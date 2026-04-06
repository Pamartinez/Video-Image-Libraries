package com.videolibrary.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * Video-library color definitions.
 * Shared colors are imported from common.ui.theme.
 * This file contains only video-library-specific colors.
 */

// ── Import all shared colors from common ──
// These are used by both image-library and video-library
import com.example.common.ui.theme.PrimaryLight
import com.example.common.ui.theme.PrimaryDark
import com.example.common.ui.theme.ScreenBackgroundLight
import com.example.common.ui.theme.ScreenBackgroundDark
import com.example.common.ui.theme.ActionBarBgLight
import com.example.common.ui.theme.ActionBarBgDark
import com.example.common.ui.theme.ListFirstTextLight
import com.example.common.ui.theme.ListFirstTextDark
import com.example.common.ui.theme.ListSecondTextLight
import com.example.common.ui.theme.ListSecondTextDark
import com.example.common.ui.theme.DividerColorLight
import com.example.common.ui.theme.DividerColorDark
import com.example.common.ui.theme.CardBackgroundLight
import com.example.common.ui.theme.CardBackgroundDark
import com.example.common.ui.theme.MenuBgLight
import com.example.common.ui.theme.MenuBgDark
import com.example.common.ui.theme.IconColorLight
import com.example.common.ui.theme.IconColorDark
import com.example.common.ui.theme.CircleButtonBgLight
import com.example.common.ui.theme.CircleButtonBgDark
import com.example.common.ui.theme.PopupBgLight
import com.example.common.ui.theme.PopupBgDark
import com.example.common.ui.theme.DetailLabelLight
import com.example.common.ui.theme.DetailLabelDark

// ── Video-library-specific colors ──

// Tab colors (used in tab navigation)
val TabSelectedLight = Color(0xFF0072DE)       // --tab-selected
val TabSelectedDark = Color(0xFF3E91FF)        // --tab-selected
val TabTextDefaultLight = Color(0xFF8C8C8C)    // --tab-text-default
val TabTextSelectedLight = Color(0xFF252525)   // --tab-text-selected
val TabTextDefaultDark = Color(0xFFA8A9A9)     // --tab-text-default
val TabTextSelectedDark = Color(0xFFFAFAFA)    // --tab-text-selected

// Video duration overlay colors
val DurationBgColor = Color(0x99252525)        // --duration-bg light
val DurationBgColorDark = Color(0x99000000)    // --duration-bg dark
val DurationTextColor = Color(0xFFFAFAFA)      // --duration-text

// Video player overlay
val OverlayBgLight = Color(0x80000000)         // --overlay-bg light
val OverlayBgDark = Color(0xB3000000)          // --overlay-bg dark

// Bottom action bar (video-specific)
val BottomBarBgLight = Color(0xCCF0F0F0)       // light bottom bar
val BottomBarBgDark = Color(0xCC2A2A2A)        // dark bottom bar

// Other shared colors (used across multiple features)
val ErrorRed = Color(0xFFFF4444)               // dialog-btn-danger
val FavoriteColor = Color(0xFFFF4081)          // folder-favorite
val NewIndicatorColor = Color(0xFFFF6B35)      // folder-new-indicator
val PopupDividerLight = Color(0x1A000000)      // light popup divider
val PopupDividerDark = Color(0x26FFFFFF)       // dark popup divider

