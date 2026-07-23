package com.example.common.data.dropbox

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

/**
 * Securely persists Dropbox OAuth tokens using EncryptedSharedPreferences.
 * The store is namespaced per app via [DropboxConfig.tokenStoreName] so multiple apps that reuse
 * this stack never collide.
 */
class TokenStore(context: Context, storeName: String) {

    private val prefs: SharedPreferences = run {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        EncryptedSharedPreferences.create(
            context,
            "dropbox_tokens_$storeName",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    var accessToken: String?
        get() = prefs.getString(KEY_ACCESS, null)
        set(value) = prefs.edit().putString(KEY_ACCESS, value).apply()

    var refreshToken: String?
        get() = prefs.getString(KEY_REFRESH, null)
        set(value) = prefs.edit().putString(KEY_REFRESH, value).apply()

    /** Epoch millis when the current access token expires. */
    var expiresAt: Long
        get() = prefs.getLong(KEY_EXPIRES_AT, 0L)
        set(value) = prefs.edit().putLong(KEY_EXPIRES_AT, value).apply()

    var accountName: String?
        get() = prefs.getString(KEY_ACCOUNT, null)
        set(value) = prefs.edit().putString(KEY_ACCOUNT, value).apply()

    val isConnected: Boolean get() = !refreshToken.isNullOrBlank()

    fun saveTokens(access: String?, refresh: String?, expiresInSeconds: Long?) {
        prefs.edit().apply {
            if (!access.isNullOrBlank()) putString(KEY_ACCESS, access)
            // Dropbox only returns a refresh token on the initial exchange, not on refresh calls.
            if (!refresh.isNullOrBlank()) putString(KEY_REFRESH, refresh)
            if (expiresInSeconds != null) {
                putLong(KEY_EXPIRES_AT, System.currentTimeMillis() + expiresInSeconds * 1000L)
            }
            apply()
        }
    }

    fun clear() = prefs.edit().clear().apply()

    companion object {
        private const val KEY_ACCESS = "access_token"
        private const val KEY_REFRESH = "refresh_token"
        private const val KEY_EXPIRES_AT = "expires_at"
        private const val KEY_ACCOUNT = "account_name"
    }
}
