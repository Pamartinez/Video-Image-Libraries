package com.example.common.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

/**
 * Dialog for renaming an album/folder.
 * Validates in real-time against existing album names to prevent duplicates.
 *
 * @param currentName     current name of the album
 * @param existingNames   list of all existing album names for validation
 * @param onRename        callback with the new name when user confirms
 * @param onDismiss       callback when user cancels or dismisses
 */
@Composable
fun AlbumRenameDialog(
    currentName: String,
    existingNames: Set<String> = emptySet(),
    onRename: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var fieldValue by remember {
        mutableStateOf(
            TextFieldValue(text = currentName, selection = TextRange(0, currentName.length))
        )
    }
    var error by remember { mutableStateOf<String?>(null) }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(80)
        focusRequester.requestFocus()
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties       = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            shape    = RoundedCornerShape(20.dp),
            color    = Color(0xFF3D3D3D),
            modifier = Modifier.fillMaxWidth(0.88f).wrapContentHeight()
        ) {
            Column {
                Text(
                    text       = "Rename album",
                    fontSize   = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color      = Color.White,
                    modifier   = Modifier.padding(
                        start = 24.dp, end = 24.dp, top = 28.dp, bottom = 16.dp
                    )
                )

                TextField(
                    value         = fieldValue,
                    onValueChange = {
                        fieldValue = it
                        val trimmedName = it.text.trim()
                        // Check if name is different from current (case-insensitive) and already exists
                        error = if (trimmedName.isNotBlank() &&
                                    !trimmedName.equals(currentName, ignoreCase = true) &&
                                    existingNames.any { n -> n.equals(trimmedName, ignoreCase = true) }
                        ) {
                            "A folder with this name already exists."
                        } else null
                    },
                    singleLine = true,
                    textStyle  = TextStyle(
                        fontSize   = 18.sp,
                        color      = Color.White,
                        fontWeight = FontWeight.Normal
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor   = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedTextColor        = Color.White,
                        unfocusedTextColor      = Color.White,
                        cursorColor             = Color.White,
                        focusedIndicatorColor   = if (error != null) Color(0xFFEF5350) else Color.White,
                        unfocusedIndicatorColor = if (error != null) Color(0xFFEF5350) else Color.White.copy(alpha = 0.5f),
                        selectionColors         = TextSelectionColors(
                            handleColor     = Color(0xFF1565C0),
                            backgroundColor = Color(0xFF1976D2).copy(alpha = 0.55f)
                        )
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .focusRequester(focusRequester)
                )

                if (error != null) {
                    Text(
                        text     = error!!,
                        color    = Color(0xFFEF5350),
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 24.dp, top = 4.dp, end = 24.dp)
                    )
                    Spacer(Modifier.height(16.dp))
                } else {
                    Spacer(Modifier.height(32.dp))
                }

                HorizontalDivider(color = Color.White.copy(alpha = 0.15f))

                val trimmedName = fieldValue.text.trim()
                val canConfirm = trimmedName.isNotBlank() &&
                                 error == null &&
                                 !trimmedName.equals(currentName, ignoreCase = true)
                Row(
                    modifier = Modifier.fillMaxWidth().height(56.dp)
                ) {
                    TextButton(
                        onClick  = onDismiss,
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text("Cancel", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                    }
                    VerticalDivider(color = Color.White.copy(alpha = 0.15f))
                    TextButton(
                        onClick  = {
                            if (canConfirm) {
                                onRename(trimmedName)
                                onDismiss()
                            }
                        },
                        enabled  = canConfirm,
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text(
                            "Rename",
                            color      = if (canConfirm) Color.White else Color.White.copy(alpha = 0.3f),
                            fontSize   = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

