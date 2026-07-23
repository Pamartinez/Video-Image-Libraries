package com.gallerytransferlibrary.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.VideoFrameDecoder
import coil.request.ImageRequest
import com.gallerytransferlibrary.data.model.MediaItem

/**
 * Thumbnail for a local media item. Uses Coil's [VideoFrameDecoder] so video URIs render a frame,
 * and overlays a play badge for videos.
 */
@Composable
fun MediaThumbnail(
    item: MediaItem,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val loader = remember(context) {
        ImageLoader.Builder(context)
            .components { add(VideoFrameDecoder.Factory()) }
            .crossfade(true)
            .build()
    }
    Box(modifier = modifier.background(Color(0xFF1A1A1A))) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(item.uri)
                .crossfade(true)
                .build(),
            imageLoader = loader,
            contentDescription = item.displayName,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        if (item.isVideo) {
            Icon(
                imageVector = Icons.Filled.PlayCircle,
                contentDescription = "Video",
                tint = Color.White.copy(alpha = 0.9f),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(4.dp)
                    .size(20.dp)
            )
        }
    }
}
