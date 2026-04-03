package com.imagelibrary.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.common.ui.screen.SharedSearchScreen
import com.imagelibrary.data.model.ImageItem
import com.imagelibrary.ui.components.ImageGridItem

@Composable
fun SearchScreen(
    query: String,
    results: List<ImageItem>,
    onQueryChange: (String) -> Unit,
    onBack: () -> Unit,
    onImageClick: (ImageItem) -> Unit,
    modifier: Modifier = Modifier
) {
    SharedSearchScreen(
        query         = query,
        results       = results,
        itemKey       = { it.id },
        onQueryChange = onQueryChange,
        onBack        = onBack,
        onItemClick   = onImageClick,
        renderItem    = { image, mod ->
            ImageGridItem(
                image           = image,
                isSelected      = false,
                isSelectionMode = false,
                isLargeGrid     = false,
                onClick         = { onImageClick(image) },
                onLongClick     = {},
                modifier        = mod
            )
        },
        placeholder = "Search images...",
        emptyPrompt = "Search my images",
        modifier    = modifier,
        gridPadding = 16.dp
    )
}

