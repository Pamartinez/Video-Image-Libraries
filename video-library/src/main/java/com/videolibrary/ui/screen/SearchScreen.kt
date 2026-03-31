package com.videolibrary.ui.screen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.videolibrary.data.model.VideoItem
import com.videolibrary.ui.components.VideoGridItem
import com.videolibrary.ui.theme.LocalVideoColors

/**
 * Search screen matching Blazor .search-container + .search-input CSS:
 * Rounded search input below header, themed colors.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(
    query: String,
    results: List<VideoItem>,
    onQueryChange: (String) -> Unit,
    onBack: () -> Unit,
    onVideoClick: (VideoItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember { FocusRequester() }
    val colors = LocalVideoColors.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Column(modifier = modifier.fillMaxSize().background(colors.screenBackground)) {
        // Header
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = colors.actionBarBg
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .heightIn(min = 56.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(colors.circleButtonBg)
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = colors.iconColor,
                        modifier = Modifier.size(22.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Search",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colors.listFirstText
                )
            }
        }

        // Search input (matching .search-container + .search-input)
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = colors.actionBarBg
        ) {
            TextField(
                value = query,
                onValueChange = onQueryChange,
                placeholder = {
                    Text(
                        "Search videos...",
                        color = colors.listSecondText,
                        fontSize = 15.sp
                    )
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .focusRequester(focusRequester),
                shape = RoundedCornerShape(24.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colors.dividerColor,
                    unfocusedContainerColor = colors.dividerColor,
                    focusedTextColor = colors.listFirstText,
                    unfocusedTextColor = colors.listFirstText,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = colors.primary
                ),
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        IconButton(onClick = { onQueryChange("") }) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = "Clear",
                                tint = colors.iconColor
                            )
                        }
                    }
                }
            )
        }

        if (query.isBlank()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Search my videos",
                    fontSize = 16.sp,
                    color = colors.listSecondText
                )
            }
        } else if (results.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No results found",
                    fontSize = 16.sp,
                    color = colors.listSecondText,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            LazyVerticalGrid(
                columns               = GridCells.Fixed(3),
                modifier              = Modifier.fillMaxSize(),
                contentPadding        = PaddingValues(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement   = Arrangement.spacedBy(4.dp)
            ) {
                items(results, key = { it.id }) { video ->
                    VideoGridItem(
                        video           = video,
                        isSelected      = false,
                        isSelectionMode = false,
                        isLargeGrid     = false,
                        onClick         = { onVideoClick(video) },
                        onLongClick     = {},
                        modifier        = Modifier.animateItem(
                            placementSpec = spring(
                                dampingRatio = Spring.DampingRatioNoBouncy,
                                stiffness    = 4000f
                            )
                        )
                    )
                }
            }
        }
    }
}
