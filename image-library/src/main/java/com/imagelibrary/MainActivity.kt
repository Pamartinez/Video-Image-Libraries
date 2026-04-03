package com.imagelibrary

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.common.util.PermissionUtils
import com.imagelibrary.ui.screen.ImageListScreen
import com.imagelibrary.ui.theme.ImageLibraryTheme
import com.imagelibrary.ui.viewmodel.ImageListViewModel

class MainActivity : ComponentActivity() {

    private var permissionGranted = mutableStateOf(false)
    private lateinit var vm: ImageListViewModel

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        permissionGranted.value = isGranted
        if (isGranted) {
            vm.loadData()
            PermissionUtils.requestManageStorageIfNeeded(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(this)[ImageListViewModel::class.java]
        permissionGranted.value = hasImagePermission()
        enableEdgeToEdge()
        setContent {
            ImageLibraryTheme(
                darkTheme = true,
                lockFontScale = true
            ) {
                val hasPermission by permissionGranted
                if (hasPermission) {
                    ImageListScreen(viewModel = vm)
                } else {
                    PermissionScreen(
                        onRequestPermission = { requestImagePermission() }
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val hadPermission = permissionGranted.value
        permissionGranted.value = hasImagePermission()
        if (!hadPermission && permissionGranted.value) {
            vm.loadData()
        }
    }

    override fun onStop() {
        super.onStop()
        if (::vm.isInitialized) vm.onAppBackground()
    }

    private fun hasImagePermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_MEDIA_IMAGES
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun requestImagePermission() {
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }
        requestPermissionLauncher.launch(permission)
    }
}

@Composable
fun PermissionScreen(onRequestPermission: () -> Unit) {
    val colors = com.imagelibrary.ui.theme.LocalImageColors.current
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
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(140.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .background(
                            brush = androidx.compose.ui.graphics.Brush.radialGradient(
                                colors = listOf(
                                    colors.primary.copy(alpha = 0.10f),
                                    colors.primary.copy(alpha = 0.03f),
                                    androidx.compose.ui.graphics.Color.Transparent
                                )
                            ),
                            shape = androidx.compose.foundation.shape.CircleShape
                        )
                )
                Image(
                    painter = painterResource(id = R.mipmap.ic_launcher),
                    contentDescription = "App icon",
                    modifier = Modifier.size(100.dp)
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Allow permissions",
                fontSize = 20.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                color = colors.listFirstText
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "To access and manage the images on your device, allow the required permissions.",
                fontSize = 14.sp,
                color = colors.listSecondText,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp,
                modifier = Modifier.widthIn(max = 280.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = onRequestPermission,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.primary,
                    contentColor = androidx.compose.ui.graphics.Color.White
                ),
                contentPadding = PaddingValues(horizontal = 40.dp, vertical = 14.dp)
            ) {
                Text(
                    "Allow",
                    fontSize = 16.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold
                )
            }
        }
    }
}