package com.gallerytransferlibrary

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gallerytransferlibrary.ui.screen.MediaListScreen
import com.gallerytransferlibrary.ui.theme.GalleryTransferTheme
import com.gallerytransferlibrary.ui.theme.LocalGalleryColors
import com.gallerytransferlibrary.ui.viewmodel.MediaListViewModel

class MainActivity : ComponentActivity() {

    private var permissionGranted = mutableStateOf(false)

    private val requestPermissionsLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { results ->
        permissionGranted.value = results.values.any { it }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionGranted.value = hasMediaPermission()
        enableEdgeToEdge()
        setContent {
            GalleryTransferTheme(darkTheme = true, lockFontScale = true) {
                val hasPermission by permissionGranted
                if (hasPermission) {
                    val vm: MediaListViewModel = viewModel()
                    MediaListScreen(viewModel = vm)
                } else {
                    PermissionScreen(onRequestPermission = { requestMediaPermissions() })
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        permissionGranted.value = hasMediaPermission()
    }

    private fun hasMediaPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) ==
                PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_VIDEO) ==
                PackageManager.PERMISSION_GRANTED
        } else {
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED
        }
    }

    private fun requestMediaPermissions() {
        val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arrayOf(Manifest.permission.READ_MEDIA_IMAGES, Manifest.permission.READ_MEDIA_VIDEO)
        } else {
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        requestPermissionsLauncher.launch(permissions)
    }
}

@Composable
fun PermissionScreen(onRequestPermission: () -> Unit) {
    val colors = LocalGalleryColors.current
    Surface(modifier = Modifier.fillMaxSize(), color = colors.screenBackground) {
        Column(
            modifier = Modifier.fillMaxSize().padding(32.dp).statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(
                        Brush.linearGradient(
                            colors = listOf(Color(0xFF0061A8), Color(0xFF0381FE), Color(0xFF3E91FF))
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.CloudUpload,
                    contentDescription = null,
                    modifier = Modifier.size(52.dp),
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Allow permissions",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = colors.listFirstText
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "To browse and upload your photos and videos, allow access to your media.",
                fontSize = 14.sp,
                color = colors.listSecondText,
                textAlign = TextAlign.Center,
                modifier = Modifier.widthIn(max = 280.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = onRequestPermission,
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.primary, contentColor = Color.White
                ),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 12.dp)
            ) {
                Text("Allow", fontSize = 15.sp, fontWeight = FontWeight.Medium)
            }
        }
    }
}
