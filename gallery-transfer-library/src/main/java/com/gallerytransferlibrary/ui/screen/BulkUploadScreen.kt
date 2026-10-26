package com.gallerytransferlibrary.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.common.ui.components.AppMenuItem
import com.common.ui.components.CircularBackButton
import com.common.ui.components.ScreenTopBar
import com.common.ui.components.SettingsSection
import com.common.ui.theme.LocalLibraryColors
import com.gallerytransferlibrary.data.model.AgeUnit
import com.gallerytransferlibrary.data.model.BulkUploadCriteria
import com.common.data.model.ViewType
import com.gallerytransferlibrary.data.model.FilterType
import com.gallerytransferlibrary.data.model.MediaItem
import com.gallerytransferlibrary.data.model.SizeUnit
import com.gallerytransferlibrary.ui.viewmodel.MatchState

/**
 * Picks a set of conditions and uploads everything that matches in one go. The upload itself is
 * handed to `BulkUploadWorker` as criteria, so it survives the app being closed.
 */
@Composable
fun BulkUploadScreen(
    criteria: BulkUploadCriteria,
    matchState: MatchState,
    excludedKeys: Set<String>,
    canUpload: Boolean,
    destinationPath: String,
    deleteAfterUpload: Boolean,
    onToggleItem: (String) -> Unit,
    onSelectAllItems: () -> Unit,
    onMediaTypeChange: (FilterType) -> Unit,
    onSizeAmountChange: (String) -> Unit,
    onSizeUnitChange: (SizeUnit) -> Unit,
    onAgeAmountChange: (String) -> Unit,
    onAgeUnitChange: (AgeUnit) -> Unit,
    onUpload: () -> Unit,
    onBack: () -> Unit
) {
    val colors = LocalLibraryColors.current

    // Raw text, so the field can be cleared and retyped. Blank is a valid shorthand for the
    // default; "0" is an error. Seeded once — the VM owns the parsed value from here on.
    var sizeText by remember { mutableStateOf(criteria.sizeAmount.toString()) }
    var ageText by remember { mutableStateOf(criteria.ageAmount.toString()) }
    var showConfirm by remember { mutableStateOf(false) }
    var showMatches by remember { mutableStateOf(false) }

    val ready = matchState as? MatchState.Ready

    // What is actually going to be uploaded, after the user's ticks. Remembered so a long match
    // list isn't re-filtered and re-summed on every recomposition.
    val selection = remember(ready, excludedKeys) {
        val items = ready?.items?.filter { it.uniqueKey !in excludedKeys } ?: emptyList()
        items to items.sumOf { it.size }
    }
    val (selectedItems, selectedBytes) = selection
    val uploadEnabled = canUpload && criteria.isValid && selectedItems.isNotEmpty()

    BackHandler { if (showMatches) showMatches = false else onBack() }

    // The preview replaces this screen rather than stacking on it, so the criteria and the ticks
    // stay put and returning is instant.
    if (showMatches && ready != null) {
        MatchingItemsScreen(
            items = ready.items,
            excludedKeys = excludedKeys,
            onToggleItem = onToggleItem,
            onSelectAllItems = onSelectAllItems,
            onBack = { showMatches = false }
        )
        return
    }

    Column(modifier = Modifier.fillMaxSize().background(colors.screenBackground)) {
        ScreenTopBar {
            CircularBackButton(onClick = onBack)
            Spacer(Modifier.width(12.dp))
            Text(
                text = "Bulk upload",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = colors.listFirstText
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
                .padding(top = 8.dp)
                .navigationBarsPadding()
                .padding(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SettingsSection(title = "What to upload") {
                Column(Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                    Text(
                        text = "Media type",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = colors.listFirstText
                    )
                    Spacer(Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        MEDIA_TYPE_OPTIONS.forEach { (type, label) ->
                            RadioOption(
                                label = label,
                                selected = criteria.mediaType == type,
                                onClick = { onMediaTypeChange(type) }
                            )
                        }
                    }

                    Spacer(Modifier.height(12.dp))
                    AmountRow(
                        label = "Size larger than",
                        text = sizeText,
                        onTextChange = { sizeText = it; onSizeAmountChange(it) },
                        unitLabel = criteria.sizeUnit.label,
                        unitOptions = SizeUnit.entries.map { it.label },
                        onUnitSelected = { onSizeUnitChange(SizeUnit.entries[it]) }
                    )

                    Spacer(Modifier.height(12.dp))
                    AmountRow(
                        label = "Older than",
                        text = ageText,
                        onTextChange = { ageText = it; onAgeAmountChange(it) },
                        unitLabel = criteria.ageUnit.label,
                        unitOptions = AgeUnit.entries.map { it.label },
                        onUnitSelected = { onAgeUnitChange(AgeUnit.entries[it]) }
                    )

                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = "Defaults: images and videos, larger than 1 MB, older than 1 day. " +
                            "Leave a box empty to use its default.",
                        fontSize = 12.sp,
                        color = colors.listSecondText
                    )
                    if (!criteria.isValid) {
                        Spacer(Modifier.height(6.dp))
                        Text(
                            text = "Enter 1 or more — 0 isn't allowed.",
                            fontSize = 12.sp,
                            color = ERROR_RED
                        )
                    }
                }
            }

            SettingsSection(title = "Matches") {
                val canPreview = (ready?.count ?: 0) > 0
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .then(
                            if (canPreview) Modifier.clickable { showMatches = true } else Modifier
                        )
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (matchState is MatchState.Loading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(18.dp),
                            strokeWidth = 2.dp,
                            color = colors.primary
                        )
                        Spacer(Modifier.width(12.dp))
                        Text("Checking your library…", fontSize = 15.sp, color = colors.listSecondText)
                    } else {
                        Column(Modifier.weight(1f)) {
                            val count = selectedItems.size
                            Text(
                                text = when {
                                    matchState is MatchState.Invalid -> "—"
                                    ready == null || ready.count == 0 -> "No items match"
                                    count == 0 -> "Nothing selected"
                                    else -> "$count item${if (count == 1) "" else "s"} " +
                                        "• ${formatBytes(selectedBytes)}"
                                },
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = colors.listFirstText
                            )
                            if (canPreview) {
                                val excluded = ready!!.count - selectedItems.size
                                Text(
                                    text = if (excluded > 0) {
                                        "$excluded of ${ready.count} unticked — tap to change"
                                    } else {
                                        "Tap to see what will be uploaded"
                                    },
                                    fontSize = 12.sp,
                                    color = colors.listSecondText
                                )
                            }
                        }
                        if (canPreview) {
                            Icon(
                                Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = "Show matching items",
                                tint = colors.listSecondText
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(4.dp))
            Button(
                onClick = { showConfirm = true },
                enabled = uploadEnabled,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.primary,
                    contentColor = Color.White
                ),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(vertical = 14.dp)
            ) {
                Icon(Icons.Default.CloudUpload, contentDescription = null, modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(8.dp))
                Text("Upload", fontSize = 15.sp, fontWeight = FontWeight.Medium)
            }
            if (!canUpload) {
                Text(
                    text = "Connect Dropbox in Settings first.",
                    fontSize = 12.sp,
                    color = colors.listSecondText,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
        }
    }

    if (showConfirm && selectedItems.isNotEmpty()) {
        BulkUploadConfirmDialog(
            count = selectedItems.size,
            totalBytes = selectedBytes,
            destinationPath = destinationPath.ifBlank { "your Dropbox root folder" },
            deleteAfterUpload = deleteAfterUpload,
            onConfirm = { showConfirm = false; onUpload() },
            onDismiss = { showConfirm = false }
        )
    }
}

/**
 * The items the current criteria match, each ticked by default. Unticking one leaves it out of the
 * upload — worth having, since the upload can delete the originals.
 *
 * Reuses `ItemGrid` from [MediaListScreen] rather than duplicating a grid: in selection mode it
 * already draws the check indicator and tint, so a tap toggles and the visuals match the rest of
 * the app for free. Long-press opens the item, since tap is taken by the tick.
 */
@Composable
private fun MatchingItemsScreen(
    items: List<MediaItem>,
    excludedKeys: Set<String>,
    onToggleItem: (String) -> Unit,
    onSelectAllItems: () -> Unit,
    onBack: () -> Unit
) {
    val colors = LocalLibraryColors.current
    val context = LocalContext.current
    var viewerIndex by remember { mutableStateOf<Int?>(null) }

    val selectedKeys = remember(items, excludedKeys) {
        items.mapNotNullTo(HashSet()) { it.uniqueKey.takeIf { key -> key !in excludedKeys } }
    }

    Column(modifier = Modifier.fillMaxSize().background(colors.screenBackground)) {
        ScreenTopBar {
            CircularBackButton(onClick = onBack)
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    text = "Will be uploaded",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colors.listFirstText
                )
                Text(
                    text = "${selectedKeys.size} of ${items.size} selected",
                    fontSize = 12.sp,
                    color = colors.listSecondText
                )
            }
            if (selectedKeys.size < items.size) {
                TextButton(onClick = onSelectAllItems) {
                    Text("Select all", color = colors.primary, fontSize = 14.sp)
                }
            }
        }
        ItemGrid(
            items = items,
            selectedKeys = selectedKeys,
            selectionMode = true,
            viewType = ViewType.GRID_SMALL,
            canDrag = false,
            onItemClick = { _, item -> onToggleItem(item.uniqueKey) },
            onItemLongClick = { item ->
                if (item.isVideo) playVideo(context, item)
                else viewerIndex = items.indexOfFirst { it.uniqueKey == item.uniqueKey }
            },
            onReorder = { _, _ -> },
            onReorderDone = {}
        )
    }

    viewerIndex?.let { start ->
        val images = items.filter { !it.isVideo }
        val startInImages = items.getOrNull(start)?.let { selected ->
            images.indexOfFirst { it.uniqueKey == selected.uniqueKey }.coerceAtLeast(0)
        } ?: 0
        if (images.isNotEmpty()) {
            ImageViewer(
                images = images,
                startIndex = startInImages,
                onClose = { viewerIndex = null }
            )
        }
    }
}

private val MEDIA_TYPE_OPTIONS = listOf(
    FilterType.IMAGES to "Image",
    FilterType.VIDEOS to "Video",
    FilterType.ALL to "Both"
)

private val ERROR_RED = Color(0xFFEF5350)

@Composable
private fun RadioOption(label: String, selected: Boolean, onClick: () -> Unit) {
    val colors = LocalLibraryColors.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(end = 8.dp)
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = androidx.compose.material3.RadioButtonDefaults.colors(
                selectedColor = colors.primary,
                unselectedColor = Color(0xFF777D8A)
            )
        )
        Text(label, fontSize = 15.sp, color = colors.listFirstText)
    }
}

/**
 * A number box plus a unit dropdown. Both conditions (size and age) render exactly this, differing
 * only in their values, so it lives here once rather than being copy-pasted per condition.
 */
@Composable
private fun AmountRow(
    label: String,
    text: String,
    onTextChange: (String) -> Unit,
    unitLabel: String,
    unitOptions: List<String>,
    onUnitSelected: (Int) -> Unit
) {
    val colors = LocalLibraryColors.current
    var expanded by remember { mutableStateOf(false) }

    Text(label, fontSize = 15.sp, fontWeight = FontWeight.Medium, color = colors.listFirstText)
    Spacer(Modifier.height(6.dp))
    Row(verticalAlignment = Alignment.CenterVertically) {
        OutlinedTextField(
            value = text,
            // Digits only: keeps the field parseable while still letting "0" be typed, so it can be
            // reported as the error it is rather than silently swallowed.
            onValueChange = { new -> if (new.length <= 6 && new.all(Char::isDigit)) onTextChange(new) },
            singleLine = true,
            keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.width(110.dp)
        )
        Spacer(Modifier.width(12.dp))
        Box {
            OutlinedButton(onClick = { expanded = true }) {
                Text(unitLabel, fontSize = 15.sp)
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Change unit")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                containerColor = colors.menuBg
            ) {
                unitOptions.forEachIndexed { index, option ->
                    AppMenuItem(
                        text = option,
                        onDismiss = { expanded = false },
                        onClick = { onUnitSelected(index) },
                        textColor = colors.listFirstText
                    )
                }
            }
        }
    }
}

/**
 * Confirms before the upload starts. Worth a step because delete-after-upload defaults to ON, so
 * the action can remove the originals from the device — the dialog says so explicitly.
 */
@Composable
private fun BulkUploadConfirmDialog(
    count: Int,
    totalBytes: Long,
    destinationPath: String,
    deleteAfterUpload: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    val colors = LocalLibraryColors.current
    val itemLabel = "$count item${if (count == 1) "" else "s"}"
    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(28.dp),
        containerColor = colors.popupBg,
        titleContentColor = colors.listFirstText,
        textContentColor = colors.listSecondText,
        title = {
            Text("Upload $itemLabel?", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        },
        text = {
            Text(
                buildString {
                    append("$itemLabel (${formatBytes(totalBytes)}) will be uploaded to $destinationPath.")
                    if (deleteAfterUpload) {
                        append("\n\nDelete after upload is on, so the originals will be moved to ")
                        append("your device's trash once they finish uploading.")
                    }
                    append("\n\nThis keeps running if you close the app.")
                },
                fontSize = 15.sp
            )
        },
        dismissButton = {
            androidx.compose.material3.TextButton(onClick = onDismiss) {
                Text("Cancel", color = colors.primary, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
        },
        confirmButton = {
            androidx.compose.material3.TextButton(onClick = onConfirm) {
                Text("Upload", color = colors.primary, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
        }
    )
}

private fun formatBytes(bytes: Long): String {
    val kb = 1024.0
    val mb = kb * 1024
    val gb = mb * 1024
    return when {
        bytes >= gb -> String.format("%.1f GB", bytes / gb)
        bytes >= mb -> String.format("%.1f MB", bytes / mb)
        bytes >= kb -> String.format("%.0f KB", bytes / kb)
        else -> "$bytes B"
    }
}
