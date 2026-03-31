package com.videolibrary

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.videolibrary.ui.screen.VideoListScreen
import com.videolibrary.ui.theme.VideoLibraryTheme
import com.videolibrary.ui.viewmodel.VideoListViewModel

class MainActivity : ComponentActivity() {

    private val vm: VideoListViewModel by viewModels()
    private var permissionGranted = mutableStateOf(false)

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        permissionGranted.value = isGranted
        if (isGranted) {
            requestManageStorageIfNeeded()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionGranted.value = hasVideoPermission()
        enableEdgeToEdge()
        setContent {
            VideoLibraryTheme(
                darkTheme = true,
                lockFontScale = true
            ) {
                val hasPermission by permissionGranted
                if (hasPermission) {
                    val vm: VideoListViewModel = viewModel()
                    VideoListScreen(viewModel = vm)
                } else {
                    PermissionScreen(
                        onRequestPermission = { requestVideoPermission() }
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        permissionGranted.value = hasVideoPermission()
    }

    override fun onStop() {
        super.onStop()
        vm.onAppBackground()
    }

    private fun hasVideoPermission(): Boolean {
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_VIDEO
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestVideoPermission() {
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_VIDEO
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }
        requestPermissionLauncher.launch(permission)
    }

    private fun requestManageStorageIfNeeded() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && !Environment.isExternalStorageManager()) {
            try {
                val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION).apply {
                    data = Uri.parse("package:$packageName")
                }
                startActivity(intent)
            } catch (_: Exception) {
                val intent = Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
                startActivity(intent)
            }
        }
    }
}

/**
 * Permission screen matching Blazor .permission-screen CSS:
 * Centered icon, title, description, and rounded "Allow" button.
 */
@Composable
fun PermissionScreen(onRequestPermission: () -> Unit) {
    val colors = com.videolibrary.ui.theme.LocalVideoColors.current
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colors.screenBackground
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
                .statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Colorful app icon (video player shape matching launcher icon)
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF7B1FA2), // Deep purple
                                Color(0xFF9C27B0), // Purple
                                Color(0xFF3F51B5)  // Indigo
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                // White rounded rectangle (video screen)
                Box(
                    modifier = Modifier
                        .size(width = 56.dp, height = 38.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    // Purple play button
                    Icon(
                        imageVector = Icons.Filled.PlayArrow,
                        contentDescription = null,
                        modifier = Modifier.size(28.dp),
                        tint = Color(0xFF67239B)
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Allow permissions",
                fontSize = 18.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold,
                color = colors.listFirstText
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "To access and manage the videos on your device, allow the required permissions.",
                fontSize = 14.sp,
                color = colors.listSecondText,
                textAlign = TextAlign.Center,
                modifier = Modifier.widthIn(max = 280.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            // Rounded primary button (matching .permission-btn)
            Button(
                onClick = onRequestPermission,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.primary,
                    contentColor = androidx.compose.ui.graphics.Color.White
                ),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 12.dp)
            ) {
                Text("Allow", fontSize = 15.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Medium)
            }
        }
    }
}