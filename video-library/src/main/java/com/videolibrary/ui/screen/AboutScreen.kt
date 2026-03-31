package com.videolibrary.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.videolibrary.data.util.FileLogger
import com.example.common.ui.screen.AboutScreen as CommonAboutScreen

@Composable
fun AboutScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    CommonAboutScreen(
        appName      = "Video Library",
        screenTitle  = "About Video",
        logDirectory = FileLogger.logDirectory,
        onLogEvent   = { tag, msg -> FileLogger.i(tag, msg) },
        onBack       = onBack,
        modifier     = modifier
    )
}
