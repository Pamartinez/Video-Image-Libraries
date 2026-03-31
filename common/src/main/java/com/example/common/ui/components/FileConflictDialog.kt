package com.example.common.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Shared conflict dialog with optional bulk actions.
 */
@Composable
fun FileConflictDialog(
    fileName: String,
    onReplace: () -> Unit,
    onRename: () -> Unit,
    onSkip: () -> Unit,
    onSkipAll: (() -> Unit)? = null,
    onReplaceAll: (() -> Unit)? = null,
    renameActionLabel: String = "Rename"
) {
    val hasBulkActions = onSkipAll != null || onReplaceAll != null

    AlertDialog(
        onDismissRequest = { },
        shape = RoundedCornerShape(28.dp),
        containerColor = Color(0xFF2C2C2C),
        title = {
            Text(
                text = "File already exists",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        },
        text = {
            Text(
                text = "\"$fileName\" already exists in this folder. What would you like to do?",
                fontSize = 15.sp,
                color = Color(0xFFBBBBBB),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        },
        confirmButton = {
            if (hasBulkActions) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(0.dp)
                ) {
                    Row {
                        if (onReplaceAll != null) {
                            TextButton(onClick = onReplaceAll) {
                                Text("Replace All", color = Color(0xFF2979FF))
                            }
                        }
                        TextButton(onClick = onReplace) {
                            Text("Replace", color = Color(0xFF2979FF), fontWeight = FontWeight.Bold)
                        }
                    }
                    Row {
                        if (onSkipAll != null) {
                            TextButton(onClick = onSkipAll) {
                                Text("Skip All", color = Color(0xFFAAAAAA))
                            }
                        }
                        TextButton(onClick = onSkip) {
                            Text("Skip", color = Color(0xFFAAAAAA))
                        }
                        TextButton(onClick = onRename) {
                            Text(renameActionLabel, color = Color.White, fontWeight = FontWeight.Medium)
                        }
                    }
                }
            } else {
                TextButton(onClick = onReplace) {
                    Text("Replace", color = Color(0xFF2979FF), fontWeight = FontWeight.Bold)
                }
            }
        },
        dismissButton = {
            if (hasBulkActions) return@AlertDialog
            Row {
                TextButton(onClick = onSkip) {
                    Text("Skip", color = Color(0xFFAAAAAA))
                }
                TextButton(onClick = onRename) {
                    Text(renameActionLabel, color = Color.White, fontWeight = FontWeight.Medium)
                }
            }
        }
    )
}

