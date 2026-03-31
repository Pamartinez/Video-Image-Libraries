package com.example.common.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.common.ui.theme.LocalLibraryColors

// ─────────────────────────────────────────────────────────────────────────────
// Shared sort / view row helpers
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun SortOptionRow(
    label: String,
    isSelected: Boolean,
    textColor: Color,
    accentColor: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 2.dp, horizontal = 0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick  = onClick,
            modifier = Modifier.size(36.dp),
            colors   = RadioButtonDefaults.colors(
                selectedColor   = accentColor,
                unselectedColor = Color(0xFF777D8A)
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text       = label,
            fontSize   = 16.sp,
            fontWeight = FontWeight.Normal,
            color      = textColor
        )
    }
}

@Composable
fun ViewOptionRow(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = isSelected, onClick = onClick)
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = label, style = MaterialTheme.typography.bodyLarge)
        if (isSelected) {
            Spacer(modifier = Modifier.weight(1f))
            Icon(Icons.Default.Check, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// SortDialog
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Generic sort dialog that works with any enum or data type.
 * Pass [options] as the full list and [labelFor] to convert each option to a
 * display string. Uses [LocalLibraryColors] so it adapts to both themes.
 */
@Composable
fun <T> SortDialog(
    options: List<T>,
    labelFor: (T) -> String,
    currentOption: T,
    onOptionSelected: (T) -> Unit,
    onDismiss: () -> Unit
) {
    val colors = LocalLibraryColors.current
    var selected by remember { mutableStateOf(currentOption) }

    AlertDialog(
        onDismissRequest  = onDismiss,
        shape             = RoundedCornerShape(28.dp),
        containerColor    = colors.popupBg,
        titleContentColor = colors.listFirstText,
        textContentColor  = colors.listFirstText,
        title = { Text("Sort by", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
        text  = {
            Column {
                options.forEach { option ->
                    SortOptionRow(
                        label      = labelFor(option),
                        isSelected = selected == option,
                        textColor  = colors.listFirstText,
                        accentColor = colors.primary
                    ) { selected = option }
                }
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = colors.primary, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
        },
        confirmButton = {
            TextButton(onClick = { onOptionSelected(selected); onDismiss() }) {
                Text("Done", color = colors.primary, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
        }
    )
}

// ─────────────────────────────────────────────────────────────────────────────
// DestroyGroupDialog
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Confirmation dialog shown before destroying a group.
 * Warns that member folders will become ungrouped and sub-groups will move up.
 * Uses [LocalLibraryColors] so it adapts to both ImageLibraryTheme and VideoLibraryTheme.
 */
@Composable
fun DestroyGroupDialog(
    groupName: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    val colors = LocalLibraryColors.current
    AlertDialog(
        onDismissRequest  = onDismiss,
        shape             = RoundedCornerShape(28.dp),
        containerColor    = colors.popupBg,
        titleContentColor = colors.listFirstText,
        textContentColor  = colors.listSecondText,
        title = { Text("Destroy \"$groupName\"?", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
        text  = {
            Text(
                "Folders inside will become ungrouped. Sub-groups will move up one level.",
                fontSize = 15.sp
            )
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = colors.primary, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm(); onDismiss() }) {
                Text("Destroy", color = Color(0xFFEF5350), fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
        }
    )
}

// ─────────────────────────────────────────────────────────────────────────────
// RenameDialog
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Generic rename dialog for any media item.
 * Strips the file extension for editing and re-attaches it on confirm.
 *
 * @param title  dialog title, e.g. "Rename image" or "Rename video"
 */
@Composable
fun RenameDialog(
    currentName: String,
    title: String,
    onRename: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var name by remember { mutableStateOf(currentName.substringBeforeLast(".")) }
    val extension = currentName.substringAfterLast(".", "")
    val maxChars = 255

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
                    text       = title,
                    fontSize   = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color      = Color.White,
                    modifier   = Modifier.padding(start = 24.dp, end = 24.dp, top = 28.dp, bottom = 16.dp)
                )

                TextField(
                    value         = name,
                    onValueChange = { if (it.length <= maxChars) name = it },
                    singleLine    = true,
                    placeholder   = { Text("Name", color = Color.White.copy(alpha = 0.45f)) },
                    textStyle     = TextStyle(fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Normal),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor   = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedTextColor        = Color.White,
                        unfocusedTextColor      = Color.White,
                        cursorColor             = Color.White,
                        focusedIndicatorColor   = Color.White,
                        unfocusedIndicatorColor = Color.White.copy(alpha = 0.5f),
                        selectionColors         = TextSelectionColors(
                            handleColor     = Color(0xFF1565C0),
                            backgroundColor = Color(0xFF1976D2).copy(alpha = 0.55f)
                        )
                    ),
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)
                )

                Text(
                    text     = "${name.length}/$maxChars",
                    color    = Color.White.copy(alpha = 0.5f),
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 24.dp, top = 4.dp)
                )

                Spacer(Modifier.height(20.dp))
                HorizontalDivider(color = Color.White.copy(alpha = 0.15f))

                val canConfirm = name.isNotBlank()
                Row(modifier = Modifier.fillMaxWidth().height(56.dp)) {
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
                                val newName = if (extension.isNotEmpty()) "$name.$extension" else name
                                onRename(newName)
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

// ─────────────────────────────────────────────────────────────────────────────
// CreateFolderDialog
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun CreateFolderDialog(
    existingNames: List<String>,
    onCreate: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var name by remember { mutableStateOf("") }
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
                    text       = "Create folder",
                    fontSize   = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color      = Color.White,
                    modifier   = Modifier.padding(start = 24.dp, end = 24.dp, top = 28.dp, bottom = 16.dp)
                )

                TextField(
                    value         = name,
                    onValueChange = {
                        name = it
                        val dcimDir = android.os.Environment.getExternalStoragePublicDirectory(
                            android.os.Environment.DIRECTORY_DCIM
                        )
                        val folderExists = java.io.File(dcimDir, it).exists()
                        val nameInUse    = existingNames.any { n -> n.equals(it, ignoreCase = true) }
                        error = when {
                            nameInUse    -> "Folder name already in use."
                            folderExists -> "A folder with this name already exists."
                            else         -> null
                        }
                    },
                    singleLine  = true,
                    placeholder = { Text("Folder name", color = Color.White.copy(alpha = 0.45f)) },
                    textStyle   = TextStyle(fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Normal),
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

                val canConfirm = name.isNotBlank() && error == null
                Row(modifier = Modifier.fillMaxWidth().height(56.dp)) {
                    TextButton(
                        onClick  = onDismiss,
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text("Cancel", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                    }
                    VerticalDivider(color = Color.White.copy(alpha = 0.15f))
                    TextButton(
                        onClick  = { if (canConfirm) { onCreate(name.trim()); onDismiss() } },
                        enabled  = canConfirm,
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text(
                            "Create",
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

// ─────────────────────────────────────────────────────────────────────────────
// DetailsDialog
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Generic full-screen details dialog.
 *
 * @param rows  ordered list of (label, value) pairs rendered as [DetailsRow]s
 * @param path  file path shown in the dedicated path box at the bottom
 */
@Composable
fun DetailsDialog(
    rows: List<Pair<String, String>>,
    path: String,
    onDismiss: () -> Unit
) {
    val colors = LocalLibraryColors.current

    Dialog(
        onDismissRequest = onDismiss,
        properties       = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color    = colors.popupBg
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp, vertical = 18.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    text       = "Details",
                    style      = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.SemiBold,
                    color      = colors.listFirstText
                )

                rows.forEach { (label, value) ->
                    DetailsRow(label = label, value = value, colors = colors)
                }

                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text       = "Path",
                        style      = MaterialTheme.typography.titleLarge,
                        color      = colors.detailLabelColor,
                        fontWeight = FontWeight.Medium
                    )
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = colors.dividerColor
                    ) {
                        Text(
                            text     = path,
                            style    = MaterialTheme.typography.titleMedium,
                            color    = colors.listFirstText,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DetailsRow(label: String, value: String, colors: com.example.common.ui.theme.LibraryColors) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(
            text       = label,
            style      = MaterialTheme.typography.titleLarge,
            color      = colors.detailLabelColor,
            fontWeight = FontWeight.Medium
        )
        Text(
            text  = value,
            style = MaterialTheme.typography.headlineSmall,
            color = colors.listFirstText
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// DeleteConfirmDialog
// ─────────────────────────────────────────────────────────────────────────────

/**
 * iOS-style confirmation sheet for delete / trash operations.
 *
 * @param itemName   singular noun for a media file  ("image", "video", …)
 * @param folderName singular noun for a folder/album ("album", "folder", …)
 * @param totalItemCount total media items inside selected folders / groups
 */
@Composable
fun DeleteConfirmDialog(
    count: Int,
    isFolder: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    albumCount: Int = 0,
    groupCount: Int = 0,
    totalItemCount: Int = 0,
    itemName: String = "item",
    folderName: String = "folder"
) {
    val message: String = when {
        groupCount > 0 || albumCount > 0 -> {
            val parts = mutableListOf<String>()
            if (groupCount > 0) parts += if (groupCount == 1) "1 group" else "$groupCount groups"
            if (albumCount > 0) parts += if (albumCount == 1) "1 $folderName" else "$albumCount ${folderName}s"
            val selection  = if (parts.size == 1) parts[0] else "${parts[0]}, ${parts[1]}"
            val itemsPart  = if (totalItemCount > 0) ", and the $totalItemCount items in them" else ""
            "Move $selection$itemsPart to the Trash?"
        }
        !isFolder -> if (count == 1) "Move 1 $itemName to the Trash?"
                     else            "Move $count ${itemName}s to the Trash?"
        else      -> if (count == 1) "Move 1 $folderName to the Trash?"
                     else            "Move $count ${folderName}s to the Trash?"
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier       = Modifier.fillMaxWidth(0.88f),
            shape          = RoundedCornerShape(18.dp),
            color          = Color(0xFF3A3A3C),
            tonalElevation = 0.dp
        ) {
            Column {
                Text(
                    text       = message,
                    color      = Color.White,
                    fontSize   = 15.sp,
                    lineHeight = 22.sp,
                    modifier   = Modifier.padding(
                        start  = 20.dp, end    = 20.dp,
                        top    = 20.dp, bottom = 20.dp
                    )
                )
                HorizontalDivider(color = Color.White.copy(alpha = 0.15f), thickness = 0.5.dp)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                ) {
                    TextButton(
                        onClick  = onDismiss,
                        modifier = Modifier.weight(1f).fillMaxHeight(),
                        shape    = RoundedCornerShape(bottomStart = 18.dp)
                    ) {
                        Text("Cancel", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Normal)
                    }
                    VerticalDivider(
                        modifier  = Modifier.fillMaxHeight(),
                        color     = Color.White.copy(alpha = 0.15f),
                        thickness = 0.5.dp
                    )
                    TextButton(
                        onClick  = { onConfirm(); onDismiss() },
                        modifier = Modifier.weight(1f).fillMaxHeight(),
                        shape    = RoundedCornerShape(bottomEnd = 18.dp)
                    ) {
                        Text("Move to Trash", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// GroupNameDialog
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Single-input dialog for both "Create group" and "Rename group" flows.
 * Uses an underline-style [TextField] with auto-focus and real-time validation.
 *
 * @param existingNames names already in use (accepts both [List] and [Set])
 * @param confirmLabel  label of the confirm button ("Create", "Rename", …)
 */
@Composable
fun GroupNameDialog(
    initialName: String = "",
    title: String = "Create group",
    confirmLabel: String = "Create",
    existingNames: Collection<String> = emptyList(),
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var fieldValue by remember {
        mutableStateOf(
            TextFieldValue(text = initialName, selection = TextRange(0, initialName.length))
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
                    text       = title,
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
                        error = if (existingNames.any { n -> n.equals(it.text.trim(), ignoreCase = true) })
                            "A group with this name already exists." else null
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

                val canConfirm = fieldValue.text.isNotBlank() && error == null
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
                        onClick  = { if (canConfirm) { onConfirm(fieldValue.text.trim()); onDismiss() } },
                        enabled  = canConfirm,
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text(
                            confirmLabel,
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

// ─────────────────────────────────────────────────────────────────────────────
// CreateAlbumDialog
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Step 1 of Create Album: user types the album name.
 * Pre-fills with the next unique "Album N" name and validates in real-time
 * against existing DCIM folder names.
 */
@Composable
fun CreateAlbumDialog(
    existingDcimNames: Set<String> = emptySet(),
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val initialName = remember(existingDcimNames) { generateUniqueAlbumName(existingDcimNames) }
    var fieldValue by remember {
        mutableStateOf(TextFieldValue(text = initialName, selection = TextRange(0, initialName.length)))
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
                    text       = "Create album",
                    fontSize   = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color      = Color.White,
                    modifier   = Modifier.padding(start = 24.dp, end = 24.dp, top = 28.dp, bottom = 16.dp)
                )

                TextField(
                    value         = fieldValue,
                    onValueChange = {
                        fieldValue = it
                        error = if (existingDcimNames.any { n -> n.equals(it.text.trim(), ignoreCase = true) })
                            "A folder with this name already exists." else null
                    },
                    singleLine = true,
                    textStyle  = TextStyle(fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Normal),
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

                val canConfirm = fieldValue.text.isNotBlank() && error == null
                Row(modifier = Modifier.fillMaxWidth().height(56.dp)) {
                    TextButton(
                        onClick  = onDismiss,
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text("Cancel", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                    }
                    VerticalDivider(color = Color.White.copy(alpha = 0.15f))
                    TextButton(
                        onClick  = { if (canConfirm) { onConfirm(fieldValue.text.trim()); onDismiss() } },
                        enabled  = canConfirm,
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text(
                            "Create",
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

private fun generateUniqueAlbumName(existingNames: Set<String>): String {
    val lower = existingNames.map { it.lowercase() }
    var counter = 1
    while (lower.contains("album $counter")) counter++
    return "Album $counter"
}

// ─────────────────────────────────────────────────────────────────────────────
// CopyMoveAlbumDialog
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Step 3 of Create Album: the user chooses whether to Copy or Move the
 * selected media items into the newly-named album folder.
 *
 * @param itemCount  number of selected items
 * @param itemLabel  singular noun for the media type, e.g. "image" or "video"
 */
@Composable
fun CopyMoveAlbumDialog(
    itemCount: Int,
    itemLabel: String,
    onCopy: () -> Unit,
    onMove: () -> Unit,
    onCancel: () -> Unit
) {
    val plural = if (itemCount == 1) itemLabel else "${itemLabel}s"
    Dialog(
        onDismissRequest = onCancel,
        properties       = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            shape    = RoundedCornerShape(20.dp),
            color    = Color(0xFF3D3D3D),
            modifier = Modifier.fillMaxWidth(0.88f).wrapContentHeight()
        ) {
            Column {
                Text(
                    text       = "Add $itemCount $plural to album",
                    fontSize   = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color      = Color.White,
                    modifier   = Modifier.padding(start = 24.dp, end = 24.dp, top = 28.dp, bottom = 10.dp)
                )
                Text(
                    text     = "Would you like to copy or move the selected $plural?",
                    fontSize = 14.sp,
                    color    = Color.White.copy(alpha = 0.65f),
                    modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
                )
                HorizontalDivider(color = Color.White.copy(alpha = 0.15f))
                Row(modifier = Modifier.fillMaxWidth().height(56.dp)) {
                    TextButton(
                        onClick  = onCancel,
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text("Cancel", color = Color.White.copy(alpha = 0.65f), fontSize = 15.sp)
                    }
                    VerticalDivider(color = Color.White.copy(alpha = 0.15f))
                    TextButton(
                        onClick  = onCopy,
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text("Copy", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Medium)
                    }
                    VerticalDivider(color = Color.White.copy(alpha = 0.15f))
                    TextButton(
                        onClick  = onMove,
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    ) {
                        Text("Move", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Medium)
                    }
                }
            }
        }
    }
}
