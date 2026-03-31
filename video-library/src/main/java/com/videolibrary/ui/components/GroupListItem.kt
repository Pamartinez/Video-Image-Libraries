package com.videolibrary.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FolderSpecial
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.ui.components.CircularCheckIndicator
import com.example.common.data.model.GroupItem
import com.videolibrary.ui.theme.LocalVideoColors

/** List-row representation of a [GroupItem] (used when viewType == LIST). */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GroupListItem(
    group: GroupItem,
    isSelected: Boolean,
    isSelectionMode: Boolean,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val colors = LocalVideoColors.current
    val thumbSize = 72.dp

    Row(
        modifier = modifier
            .fillMaxWidth()
            .combinedClickable(onClick = onClick, onLongClick = onLongClick)
            .background(if (isSelected) colors.primary.copy(alpha = 0.12f) else Color.Transparent)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Thumbnail / mosaic (72×72)
        Box(
            modifier = Modifier
                .size(thumbSize)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF3A3A3A))
        ) {
            val uri = group.previewUris.firstOrNull()
            if (uri != null) {
                VideoThumbnail(
                    contentUri         = uri,
                    contentDescription = group.name,
                    contentScale       = ContentScale.Crop,
                    modifier           = Modifier.fillMaxSize()
                )
            } else {
                Icon(
                    imageVector        = Icons.Default.FolderSpecial,
                    contentDescription = null,
                    tint               = Color.White.copy(alpha = 0.4f),
                    modifier           = Modifier
                        .size(36.dp)
                        .align(Alignment.Center)
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text       = group.name,
                fontSize   = 16.sp,
                fontWeight = FontWeight.Medium,
                color      = colors.listFirstText,
                maxLines   = 1,
                overflow   = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(2.dp))
            val subtitle = buildString {
                if (group.subGroupCount > 0) append("${group.subGroupCount} groups  ")
                append("${group.folderCount} folders  ${group.totalItemCount} videos")
            }
            Text(
                text     = subtitle,
                fontSize = 14.sp,
                color    = colors.listSecondText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        if (isSelectionMode) {
            CircularCheckIndicator(
                isSelected = isSelected,
                selectedColor = LocalVideoColors.current.primary,
                modifier   = Modifier.padding(start = 8.dp)
            )
        }
    }
}



