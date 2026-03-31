package com.example.common.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.FolderOpen
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.data.util.FileManagerHelper
import com.example.common.ui.theme.LocalLibraryColors
import java.io.File

/**
 * Shared About screen used by both image-library and video-library.
 *
 * @param appName        Display name shown in the body ("Image Library" / "Video Library").
 * @param screenTitle    Top-bar title ("About" / "About Video").
 * @param logDirectory   Directory to open when the user taps "Open Logs Folder".
 * @param onLogEvent     Callback to log a message (passed to the library-specific FileLogger).
 */
@Composable
fun AboutScreen(
    appName: String,
    screenTitle: String = "About",
    logDirectory: File,
    onLogEvent: (tag: String, message: String) -> Unit = { _, _ -> },
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val ctx    = LocalContext.current
    val colors = LocalLibraryColors.current
    val versionName = try {
        ctx.packageManager.getPackageInfo(ctx.packageName, 0).versionName ?: "1.0"
    } catch (_: Exception) { "1.0" }

    Column(modifier = modifier.fillMaxSize().background(colors.screenBackground)) {
        // ── Header ──────────────────────────────────────────────────────
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
                    onClick  = onBack,
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(colors.circleButtonBg)
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint               = colors.iconColor,
                        modifier           = Modifier.size(22.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text       = screenTitle,
                    fontSize   = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color      = colors.listFirstText
                )
            }
        }

        // ── Content ──────────────────────────────────────────────────────
        Column(
            modifier              = Modifier.fillMaxSize().padding(24.dp),
            horizontalAlignment   = Alignment.CenterHorizontally,
            verticalArrangement   = Arrangement.Center
        ) {
            Text(text = appName,         fontSize = 16.sp, color = colors.listFirstText)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Version $versionName", fontSize = 14.sp, color = colors.listSecondText)

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedButton(
                onClick = {
                    if (!logDirectory.exists()) logDirectory.mkdirs()
                    onLogEvent("AboutScreen", "Opened log folder from About screen")
                    FileManagerHelper.openFolder(ctx, logDirectory.absolutePath)
                },
                shape  = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = colors.primary)
            ) {
                Icon(Icons.Outlined.FolderOpen, contentDescription = null, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Open Logs Folder", fontSize = 14.sp)
            }
        }
    }
}

