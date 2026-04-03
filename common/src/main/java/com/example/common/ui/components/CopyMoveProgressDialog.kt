package com.example.common.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Samsung Gallery-style bottom-sheet progress popup for move/copy operations.
 * Progress bar uses the Samsung One UI signature gradient:
 *   blue (#4169FF) → teal (#2196C4) → green (#29C76F)
 */
@Composable
fun CopyMoveProgressDialog(
    title: String,
    current: Int,
    total: Int,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Samsung One UI signature gradient: blue → teal → green
    val gradientColors = listOf(
        Color(0xFF2196C4),
        Color(0xFF29C76F),
        Color(0xFF4169FF),  // vivid blue
        Color(0xFF2196C4),  // teal
        Color(0xFF29C76F)   // vivid green
    )
    val trackColor  = Color(0xFF3A3A3A)
    val trackShape  = RoundedCornerShape(50)   // fully pill-shaped

    val fraction = if (total > 0) current.toFloat() / total else 0f
    val animatedFraction by animateFloatAsState(
        targetValue    = fraction,
        animationSpec  = tween(durationMillis = 300),
        label          = "progress"    )
    val percent = (animatedFraction * 100).toInt()

    Box(
        modifier = modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication        = null,
                onClick           = {}
            ),
        contentAlignment = Alignment.BottomCenter
    ) {
        AnimatedVisibility(
            visible = true,
            enter   = slideInVertically(initialOffsetY = { it }),
            exit    = slideOutVertically(targetOffsetY = { it })
        ) {
            Surface(
                shape          = RoundedCornerShape(28.dp),
                color          = Color(0xFF2C2C2C),
                tonalElevation = 0.dp,
                shadowElevation = 0.dp,
                modifier       = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(
                        start = 24.dp, end = 24.dp, top = 28.dp, bottom = 16.dp
                    )
                ) {
                    Text(
                        text       = title,
                        fontSize   = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color      = Color.White
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // ── Progress bar: BoxWithConstraints anchors the gradient to the
                    //    full track width so the fill progressively reveals blue→teal→green
                    BoxWithConstraints(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(trackShape)
                            .border(
                                width = 1.dp,
                                brush = Brush.linearGradient(
                                    colors = gradientColors,
                                    start  = Offset.Zero,
                                    end    = Offset(Float.POSITIVE_INFINITY, 0f)
                                ),
                                shape = trackShape
                            )
                            .background(trackColor)
                    ) {
                        val fullWidthPx = constraints.maxWidth.toFloat()
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(animatedFraction)
                                .clip(trackShape)
                                .background(
                                    Brush.linearGradient(
                                        colors = gradientColors,
                                        start  = Offset.Zero,
                                        end    = Offset(fullWidthPx, 0f)
                                    )
                                )
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier              = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text     = "$current/$total",
                            fontSize = 15.sp,
                            color    = Color(0xFFAAAAAA)
                        )
                        Text(
                            text     = "$percent%",
                            fontSize = 15.sp,
                            color    = Color(0xFFAAAAAA)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Box(
                        modifier         = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        TextButton(onClick = onCancel) {
                            Text(
                                text       = "Cancel",
                                fontSize   = 17.sp,
                                fontWeight = FontWeight.Medium,
                                color      = Color(0xFF4169FF)
                            )
                        }
                    }
                }
            }
        }
    }
}

