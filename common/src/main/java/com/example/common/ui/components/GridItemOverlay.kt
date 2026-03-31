package com.example.common.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.ui.theme.LocalLibraryColors

/**
 * Shared overlay for folder and group grid items:
 * - Bottom gradient scrim
 * - Name + subtitle centred at the bottom
 * - Circular selection indicator (position via [checkIndicatorAlignment])
 * - Primary-colour selection tint when [isSelected]
 *
 * Uses [LocalLibraryColors] so it adapts to both ImageLibraryTheme and VideoLibraryTheme.
 */
@Composable
fun GridItemOverlay(
    name: String,
    subtitle: String,
    isSelected: Boolean,
    isSelectionMode: Boolean,
    nameFontSize: TextUnit = 13.sp,
    countFontSize: TextUnit = 12.sp,
    checkIndicatorAlignment: Alignment = Alignment.TopEnd
) {
    val colors = LocalLibraryColors.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.75f)
    ) {
        // Bottom gradient scrim
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .align(Alignment.BottomCenter)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f))
                    )
                )
        )

        // Name + count at bottom-centre
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, bottom = 4.dp)
        ) {
            Text(
                text       = name,
                fontSize   = nameFontSize,
                lineHeight = nameFontSize,
                fontWeight = FontWeight.SemiBold,
                color      = Color.White,
                maxLines   = 1,
                overflow   = TextOverflow.Ellipsis,
                textAlign  = TextAlign.Center
            )
            Text(
                text       = subtitle,
                fontSize   = countFontSize,
                lineHeight = countFontSize,
                color      = Color.White.copy(alpha = 0.7f),
                maxLines   = 1,
                overflow   = TextOverflow.Ellipsis,
                textAlign  = TextAlign.Center
            )
        }

        // Selection tint
        if (isSelected) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.75f)
                    .background(colors.primary.copy(alpha = 0.15f))
            )
        }

        // Circular check indicator
        if (isSelectionMode) {
            Box(
                modifier = Modifier
                    .align(checkIndicatorAlignment)
                    .padding(6.dp)
            ) {
                CircularCheckIndicator(
                    isSelected    = isSelected,
                    selectedColor = colors.primary,
                    size          = 24.dp
                )
            }
        }
    }
}

