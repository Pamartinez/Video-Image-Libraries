package com.gallerytransferlibrary.ui.screen

import android.graphics.Bitmap
import android.net.Uri
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.common.data.dropbox.DropboxConfig
import com.example.common.data.dropbox.PkceSession

/**
 * Full-screen in-app WebView login modal for Dropbox OAuth (PKCE).
 *
 * Loads the Dropbox authorize page; when Dropbox redirects to the app's [DropboxConfig.redirectUri]
 * it intercepts the URL, extracts the `code` (or `error`) query param and calls [onResult].
 * A null code means the user cancelled or Dropbox returned an error.
 */
@Composable
fun DropboxSignInModal(
    config: DropboxConfig,
    onResult: (code: String?) -> Unit
) {
    // Start a fresh PKCE session and build the authorize URL once per modal.
    val authorizeUrl = remember { config.buildAuthorizeUrl(PkceSession.begin()) }
    var loading by remember { mutableStateOf(true) }

    Dialog(
        onDismissRequest = { onResult(null) },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnBackPress = true,
            dismissOnClickOutside = false
        )
    ) {
        Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF121212)) {
            Column(Modifier.fillMaxSize().statusBarsPadding()) {
                Row(
                    modifier = Modifier.fillMaxWidth().height(52.dp).padding(horizontal = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { onResult(null) }) {
                        Icon(Icons.Filled.Close, contentDescription = "Cancel", tint = Color.White)
                    }
                    Text(
                        "Sign in to Dropbox",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                if (loading) {
                    LinearProgressIndicator(
                        modifier = Modifier.fillMaxWidth(),
                        color = Color(0xFF0381FE)
                    )
                }
                Box(Modifier.fillMaxSize()) {
                    AndroidView(
                        modifier = Modifier.fillMaxSize(),
                        factory = { ctx ->
                            WebView(ctx).apply {
                                settings.javaScriptEnabled = true
                                settings.domStorageEnabled = true
                                webViewClient = object : WebViewClient() {
                                    override fun shouldOverrideUrlLoading(
                                        view: WebView?,
                                        request: WebResourceRequest?
                                    ): Boolean {
                                        val url = request?.url ?: return false
                                        return handleRedirect(url, config.redirectUri, onResult)
                                    }

                                    @Deprecated("Deprecated in Java")
                                    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                                        val uri = url?.let { Uri.parse(it) } ?: return false
                                        return handleRedirect(uri, config.redirectUri, onResult)
                                    }

                                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                                        loading = true
                                    }

                                    override fun onPageFinished(view: WebView?, url: String?) {
                                        loading = false
                                    }
                                }
                                loadUrl(authorizeUrl)
                            }
                        }
                    )
                    if (loading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = Color(0xFF0381FE)
                        )
                    }
                }
            }
        }
    }
}

/**
 * If [url] is the OAuth redirect, extract the code/error and invoke [onResult]; returns true to tell
 * the WebView we handled the navigation.
 */
private fun handleRedirect(
    url: Uri,
    redirectUri: String,
    onResult: (String?) -> Unit
): Boolean {
    val redirect = Uri.parse(redirectUri)
    val matches = url.scheme == redirect.scheme &&
        (redirect.host.isNullOrEmpty() || url.host == redirect.host)
    if (!matches) return false
    val code = url.getQueryParameter("code")
    onResult(code) // null when the user denied or an error param is present
    return true
}
