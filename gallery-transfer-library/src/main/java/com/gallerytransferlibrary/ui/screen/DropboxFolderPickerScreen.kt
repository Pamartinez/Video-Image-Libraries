package com.gallerytransferlibrary.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CreateNewFolder
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.data.dropbox.DropboxRepository
import com.example.common.data.dropbox.model.Entry
import com.gallerytransferlibrary.ui.theme.LocalGalleryColors
import kotlinx.coroutines.launch

/**
 * Lets the user browse their Dropbox folders and pick an upload destination.
 * Navigates into subfolders; "Use this folder" returns the current path (root = "").
 */
@Composable
fun DropboxFolderPickerScreen(
    repository: DropboxRepository,
    onSelect: (path: String) -> Unit,
    onBack: () -> Unit
) {
    val colors = LocalGalleryColors.current
    val scope = rememberCoroutineScope()
    var currentPath by remember { mutableStateOf("") }
    var folders by remember { mutableStateOf<List<Entry>>(emptyList()) }
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }
    var reloadKey by remember { mutableStateOf(0) }
    var showCreateDialog by remember { mutableStateOf(false) }
    var creating by remember { mutableStateOf(false) }
    var createError by remember { mutableStateOf<String?>(null) }
    var newFolderName by remember { mutableStateOf("") }

    LaunchedEffect(currentPath, reloadKey) {
        loading = true
        error = null
        try {
            folders = repository.listFolders(currentPath)
        } catch (e: Exception) {
            error = e.message ?: "Couldn't load folders."
            folders = emptyList()
        }
        loading = false
    }

    Surface(modifier = Modifier.fillMaxSize(), color = colors.screenBackground) {
        Column(Modifier.fillMaxSize().statusBarsPadding()) {
            Row(
                modifier = Modifier.fillMaxWidth().height(56.dp).padding(horizontal = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    if (currentPath.isEmpty()) onBack()
                    else currentPath = currentPath.substringBeforeLast('/', "")
                }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = colors.iconColor)
                }
                Column {
                    Text(
                        "Choose destination",
                        color = colors.listFirstText,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        if (currentPath.isEmpty()) "Dropbox (/)" else currentPath,
                        color = colors.listSecondText,
                        fontSize = 12.sp
                    )
                }
                Spacer(Modifier.weight(1f))
                IconButton(onClick = {
                    newFolderName = ""
                    createError = null
                    showCreateDialog = true
                }) {
                    Icon(Icons.Filled.CreateNewFolder, "New folder", tint = colors.iconColor)
                }
            }

            Box(Modifier.weight(1f).fillMaxWidth()) {
                when {
                    loading -> CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = colors.primary
                    )
                    error != null -> Text(
                        error!!,
                        color = colors.listSecondText,
                        modifier = Modifier.align(Alignment.Center).padding(24.dp)
                    )
                    folders.isEmpty() -> Text(
                        "No subfolders here.",
                        color = colors.listSecondText,
                        modifier = Modifier.align(Alignment.Center)
                    )
                    else -> LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 8.dp)
                    ) {
                        items(folders, key = { it.pathLower ?: it.name ?: "" }) { entry ->
                            FolderRow(
                                name = entry.name ?: "",
                                onClick = { currentPath = entry.pathLower ?: currentPath }
                            )
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(16.dp)
            ) {
                Button(
                    onClick = { onSelect(currentPath) },
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.primary, contentColor = Color.White
                    )
                ) {
                    Text("Use this folder", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }

    if (showCreateDialog) {
        AlertDialog(
            onDismissRequest = { if (!creating) showCreateDialog = false },
            title = { Text("New folder") },
            text = {
                Column {
                    OutlinedTextField(
                        value = newFolderName,
                        onValueChange = {
                            newFolderName = it
                            createError = null
                        },
                        singleLine = true,
                        enabled = !creating,
                        label = { Text("Folder name") }
                    )
                    if (createError != null) {
                        Spacer(Modifier.height(8.dp))
                        Text(createError!!, color = Color(0xFFE53935), fontSize = 12.sp)
                    }
                }
            },
            confirmButton = {
                TextButton(
                    enabled = !creating && newFolderName.isNotBlank(),
                    onClick = {
                        val name = newFolderName.trim()
                        scope.launch {
                            creating = true
                            createError = null
                            try {
                                repository.createFolder(currentPath, name)
                                creating = false
                                showCreateDialog = false
                                reloadKey++
                            } catch (e: Exception) {
                                creating = false
                                createError = if (e.message?.contains("conflict") == true)
                                    "A folder with that name already exists."
                                else e.message ?: "Couldn't create folder."
                            }
                        }
                    }
                ) { Text(if (creating) "Creating…" else "Create") }
            },
            dismissButton = {
                TextButton(enabled = !creating, onClick = { showCreateDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
private fun FolderRow(name: String, onClick: () -> Unit) {
    val colors = LocalGalleryColors.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Filled.Folder,
            contentDescription = null,
            tint = colors.iconColor,
            modifier = Modifier.size(24.dp)
        )
        Spacer(Modifier.width(16.dp))
        Text(name, color = colors.listFirstText, fontSize = 16.sp)
    }
}
