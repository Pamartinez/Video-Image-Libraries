package com.example.common.ui.screen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.ui.components.FastScrollerForGrid
import com.example.common.ui.theme.LocalLibraryColors

/**
 * Shared search screen used by both image-library and video-library.
 */
@Composable
fun <T> SharedSearchScreen(
    query: String,
    results: List<T>,
    itemKey: (T) -> Any,
    onQueryChange: (String) -> Unit,
    onBack: () -> Unit,
    onItemClick: (T) -> Unit,
    renderItem: @Composable (item: T, modifier: Modifier) -> Unit,
    placeholder: String,
    emptyPrompt: String,
    modifier: Modifier = Modifier,
    gridPadding: Dp = 16.dp
) {
    val focusRequester = remember { FocusRequester() }
    val colors = LocalLibraryColors.current
    val lazyGridState = rememberLazyGridState()

    LaunchedEffect(Unit) { focusRequester.requestFocus() }

    Column(modifier = modifier.fillMaxSize().background(colors.screenBackground)) {
        Surface(modifier = Modifier.fillMaxWidth(), color = colors.actionBarBg) {
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
                    modifier = Modifier.size(44.dp).clip(CircleShape).background(colors.circleButtonBg)
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

        Surface(modifier = Modifier.fillMaxWidth(), color = colors.actionBarBg) {
            TextField(
                value = query,
                onValueChange = onQueryChange,
                placeholder = { Text(placeholder, color = colors.listSecondText, fontSize = 15.sp) },
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
                            Icon(Icons.Default.Close, contentDescription = "Clear", tint = colors.iconColor)
                        }
                    }
                }
            )
        }

        when {
            query.isBlank() -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = emptyPrompt, fontSize = 16.sp, color = colors.listSecondText)
                }
            }
            results.isEmpty() -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "No results found",
                        fontSize = 16.sp,
                        color = colors.listSecondText,
                        textAlign = TextAlign.Center
                    )
                }
            }
            else -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        state = lazyGridState,
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(gridPadding),
                        horizontalArrangement = Arrangement.spacedBy(gridPadding),
                        verticalArrangement = Arrangement.spacedBy(gridPadding)
                    ) {
                        items(results, key = itemKey) { item ->
                            renderItem(
                                item,
                                Modifier.animateItem(
                                    placementSpec = spring(
                                        dampingRatio = Spring.DampingRatioNoBouncy,
                                        stiffness = 4000f
                                    )
                                )
                            )
                        }
                    }
                    FastScrollerForGrid(
                        state = lazyGridState,
                        itemCount = results.size,
                        sectionLabel = { index ->
                            val clamped = index.coerceIn(0, (results.size - 1).coerceAtLeast(0))
                            itemKey(results[clamped]).toString().take(12)
                        }
                    )
                }
            }
        }
    }
}
