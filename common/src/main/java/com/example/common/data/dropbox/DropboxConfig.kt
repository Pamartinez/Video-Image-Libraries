package com.example.common.data.dropbox

/**
 * Immutable configuration for a Dropbox client. App-agnostic: each consuming app injects its own
 * [appKey], [redirectUri] and [tokenStoreName] via [DropboxClientFactory], so this stack can be
 * reused by image-library, video-library and gallery-transfer-library without duplication.
 */
data class DropboxConfig(
    val appKey: String,
    val redirectUri: String,
    val tokenStoreName: String,
    val scopes: List<String> = listOf("files.content.write", "files.metadata.read")
) {
    val scopeParam: String get() = scopes.joinToString(" ")

    /** URL loaded inside the in-app WebView login modal. */
    fun buildAuthorizeUrl(codeChallenge: String): String {
        val scope = java.net.URLEncoder.encode(scopeParam, "UTF-8")
        val redirect = java.net.URLEncoder.encode(redirectUri, "UTF-8")
        return "https://www.dropbox.com/oauth2/authorize" +
            "?client_id=$appKey" +
            "&response_type=code" +
            "&code_challenge=$codeChallenge" +
            "&code_challenge_method=S256" +
            "&token_access_type=offline" +
            "&redirect_uri=$redirect" +
            "&scope=$scope" +
            "&force_reapprove=false"
    }

    companion object {
        const val API_BASE = "https://api.dropboxapi.com/"
        const val CONTENT_BASE = "https://content.dropboxapi.com/"
        const val UPLOAD_SINGLE_LIMIT = 150L * 1024 * 1024 // 150 MB
        const val UPLOAD_CHUNK_SIZE = 8L * 1024 * 1024      // 8 MB session chunks
    }
}
