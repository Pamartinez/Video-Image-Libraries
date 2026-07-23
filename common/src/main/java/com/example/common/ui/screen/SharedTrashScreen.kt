package com.example.common.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.RestoreFromTrash
import androidx.compose.material.icons.filled.SelectAll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.VideoFrameDecoder
import coil.request.ImageRequest
import com.example.common.data.db.TrashStore
import com.example.common.data.model.TrashEntry
import com.example.common.ui.components.CircularCheckIndicator
import com.example.common.ui.theme.LocalLibraryColors
import java.io.File
import kotlin.math.ceil

/**
 * Shared internal-Trash browser used identically by gallery-transfer, image-library and
 * video-library. Shows trashed items (kept for [retentionDays] days) and lets the user Restore,
 * Delete permanently, or Empty the whole trash. Thumbnails are rendered from the trashed files.
 */
@Composable
fun SharedTrashScreen(
    entries: List<TrashEntry>,
    selectedIds: Set<String>,
    selectionMode: Boolean,
    isLoading: Boolean,
    retentionDays: Int,
    onBack: () -> Unit,
    onToggleSelect: (String) -> Unit,
    onLongPress: (String) -> Unit,
    onSelectAll: () -> Unit,
    onExitSelection: () -> Unit,
    onRestore: () -> Unit,
    onDeleteForever: () -> Unit,
    onEmptyAll: () -> Unit
) {
    val colors = LocalLibraryColors.current
    val context = LocalContext.current
    val trashDir = remember { TrashStore.trashDir() }
    val loader = remember(context) {
        ImageLoader.Builder(context)
            .components { add(VideoFrameDecoder.Factory()) }
            .crossfade(true)
            .build()
    }

    Surface(modifier = Modifier.fillMaxSize(), color = colors.screenBackground) {
        Box(Modifier.fillMaxSize()) {
            Column(Modifier.fillMaxSize().statusBarsPadding()) {
                Row(
                    modifier = Modifier.fillMaxWidth().height(56.dp).padding(horizontal = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (selectionMode) {
                        IconButton(onClick = onExitSelection) {
                            Icon(Icons.Default.Close, "Close", tint = colors.iconColor)
                        }
                        Text(
                            text = "${selectedIds.size} selected",
                            color = colors.listFirstText,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.weight(1f)
                        )
                        IconButton(onClick = onSelectAll) {
                            Icon(Icons.Default.SelectAll, "Select all", tint = colors.iconColor)
                        }
                    } else {
                        IconButton(onClick = onBack) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = colors.iconColor)
                        }
                        Text(
                            text = "Trash",
                            color = colors.listFirstText,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.weight(1f)
                        )
                        if (entries.isNotEmpty()) {
                            TextButton(onClick = onEmptyAll) {
                                Text("Empty", color = colors.primary, fontSize = 15.sp)
                            }
                        }
                    }
                }

                Text(
                    text = "Items are kept for $retentionDays days, then deleted automatically.",
                    color = colors.listSecondText,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )

                when {
                    isLoading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = colors.primary)
                    }
                    entries.isEmpty() -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Trash is empty", color = colors.listSecondText, fontSize = 15.sp)
                    }
                    else -> LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(2.dp, 2.dp, 2.dp, 96.dp)
                    ) {
                        items(entries, key = { it.id }) { entry ->
                            TrashCell(
                                entry = entry,
                                trashDir = trashDir,
                                loader = loader,
                                retentionDays = retentionDays,
                                selectionMode = selectionMode,
                                selected = entry.id in selectedIds,
                                selectedColor = colors.primary,
                                onClick = { onToggleSelect(entry.id) },
                                onLongClick = { onLongPress(entry.id) }
                            )
                        }
                    }
                }
            }

            if (selectionMode) {
                Surface(
                    modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth(),
                    color = colors.actionBarBg
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().navigationBarsPadding().padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        val enabled = selectedIds.isNotEmpty()
                        TrashBarItem(Icons.Default.RestoreFromTrash, "Restore", colors.listFirstText, enabled, onRestore)
                        TrashBarItem(Icons.Default.DeleteForever, "Delete", colors.listFirstText, enabled, onDeleteForever)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TrashCell(
    entry: TrashEntry,
    trashDir: File,
    loader: ImageLoader,
    retentionDays: Int,
    selectionMode: Boolean,
    selected: Boolean,
    selectedColor: Color,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {
    val context = LocalContext.current
    val daysLeftLabel = remember(entry.deleteTimeMillis, retentionDays) {
        val dayMs = 24L * 60L * 60L * 1000L
        val expiry = entry.deleteTimeMillis + retentionDays * dayMs
        val daysLeft = ceil((expiry - System.currentTimeMillis()).toDouble() / dayMs)
            .toInt().coerceAtLeast(0)
        when {
            daysLeft <= 0 -> "Last day"
            daysLeft == 1 -> "1 day"
            else -> "$daysLeft days"
        }
    }
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(1.dp)
            .clip(RoundedCornerShape(2.dp))
            .background(Color(0xFF1A1A1A))
            .combinedClickable(onClick = onClick, onLongClick = onLongClick)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(File(trashDir, entry.trashFileName))
                .crossfade(true)
                .build(),
            imageLoader = loader,
            contentDescription = entry.displayName,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(4.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color.Black.copy(alpha = 0.55f))
                .padding(horizontal = 5.dp, vertical = 2.dp)
        ) {
            Text(
                text = daysLeftLabel,
                color = Color.White,
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium
            )
        }
        if (entry.isVideo) {
            Icon(
                imageVector = Icons.Filled.PlayCircle,
                contentDescription = "Video",
                tint = Color.White.copy(alpha = 0.9f),
                modifier = Modifier.align(Alignment.BottomEnd).padding(4.dp).size(20.dp)
            )
        }
        if (selectionMode) {
            Box(Modifier.align(Alignment.TopEnd).padding(4.dp)) {
                CircularCheckIndicator(isSelected = selected, selectedColor = selectedColor)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TrashBarItem(
    icon: ImageVector,
    label: String,
    tint: Color,
    enabled: Boolean,
    onClick: () -> Unit
) {
    val alpha = if (enabled) 1f else 0.4f
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .combinedClickable(enabled = enabled, onClick = onClick)
            .padding(horizontal = 24.dp, vertical = 6.dp)
    ) {
        Icon(icon, label, tint = tint.copy(alpha = alpha), modifier = Modifier.size(24.dp))
        Spacer(Modifier.height(4.dp))
        Text(label, color = tint.copy(alpha = alpha), fontSize = 12.sp)
    }
}
