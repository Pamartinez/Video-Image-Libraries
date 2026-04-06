package com.example.common.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

/**
 * Samsung Gallery style file conflict dialog.
 *
 * **MANDATORY RULE**: This is the standard conflict dialog for both image-library and video-library.
 * All file conflict scenarios must use this exact dialog style.
 *
 * Layout matches Samsung Gallery:
 * - Takes up ~95% of screen width (just small padding on sides)
 * - Three horizontal buttons at bottom: Skip | Replace | Rename/Keep Both
 * - Buttons separated by vertical dividers
 * - Buttons are evenly distributed across the width
 */
@Composable
fun FileConflictDialog(
    fileName: String,
    applyToAll: Boolean,
    onApplyToAllToggle: () -> Unit,
    onReplace: () -> Unit,
    onRename: () -> Unit,
    onSkip: () -> Unit,
    onCancel: () -> Unit,
    renameActionLabel: String = "Rename"
) {
    Dialog(
        onDismissRequest = onCancel,  // Back button cancels entire operation
        properties = DialogProperties(
            dismissOnBackPress = true,           // Allow back button to cancel
            dismissOnClickOutside = false,       // Prevent accidental dismissal
            usePlatformDefaultWidth = false      // Allow custom width (full-width with custom padding)
        )
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),  // Reduced to match Samsung Gallery's nearly full-width dialog
            shape = RoundedCornerShape(28.dp),
            color = Color(0xFF2C2C2C)
        ) {
            Column {
                // Content section with padding
                Column(modifier = Modifier.padding(24.dp)) {
                    // Title
                    Text(
                        text = "Rename item or replace existing one?",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Message
                    Text(
                        text = "There's already an item named $fileName in the selected album.",
                        fontSize = 15.sp,
                        color = Color(0xFFBBBBBB),
                        lineHeight = 20.sp
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // "Apply to all items" checkbox
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(onClick = onApplyToAllToggle)
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = if (applyToAll) Icons.Filled.CheckCircle else Icons.Outlined.Circle,
                            contentDescription = "Apply to all",
                            tint = if (applyToAll) Color(0xFF2979FF) else Color(0xFF5A5A5A),
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Apply to all items",
                            fontSize = 15.sp,
                            color = Color.White
                        )
                    }
                }


                // Three horizontal buttons with vertical dividers (Samsung Gallery style)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Skip button
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clickable(onClick = onSkip),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Skip",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }

                    // Vertical divider
                    VerticalDivider(
                        thickness = 1.dp,
                        color = Color(0xFF3E3E3E)
                    )

                    // Replace button
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clickable(onClick = onReplace),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Replace",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }

                    // Vertical divider
                    VerticalDivider(
                        thickness = 0.5.dp,
                        color = Color(0xFF3E3E3E)
                    )

                    // Rename/Keep Both button
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clickable(onClick = onRename),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = renameActionLabel,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
