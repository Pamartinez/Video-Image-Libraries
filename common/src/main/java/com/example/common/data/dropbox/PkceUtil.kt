package com.example.common.data.dropbox

import android.util.Base64
import java.security.MessageDigest
import java.security.SecureRandom

/**
 * PKCE (RFC 7636) helper for the OAuth 2.0 authorization-code flow with a public client.
 */
object PkceUtil {

    /** Generates a high-entropy code verifier (43–128 chars, URL-safe). */
    fun generateCodeVerifier(): String {
        val bytes = ByteArray(64)
        SecureRandom().nextBytes(bytes)
        return Base64.encodeToString(bytes, Base64.URL_SAFE or Base64.NO_PADDING or Base64.NO_WRAP)
    }

    /** Derives the S256 code challenge from a verifier. */
    fun generateCodeChallenge(verifier: String): String {
        val digest = MessageDigest.getInstance("SHA-256").digest(verifier.toByteArray(Charsets.US_ASCII))
        return Base64.encodeToString(digest, Base64.URL_SAFE or Base64.NO_PADDING or Base64.NO_WRAP)
    }
}
