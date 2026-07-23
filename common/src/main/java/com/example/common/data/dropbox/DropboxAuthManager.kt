package com.example.common.data.dropbox

import com.example.common.data.dropbox.api.DropboxAuthApi
import com.example.common.data.dropbox.model.TokenResponse
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * Owns OAuth token lifecycle: exchanging the authorization code (from the WebView modal) and
 * refreshing the access token. Thread-safe via a mutex so concurrent 401s trigger only one refresh.
 */
class DropboxAuthManager(
    private val config: DropboxConfig,
    private val authApi: DropboxAuthApi,
    val tokenStore: TokenStore
) {
    private val refreshMutex = Mutex()

    val isConnected: Boolean get() = tokenStore.isConnected

    /** Exchanges the authorization code captured by the WebView for access + refresh tokens. */
    suspend fun exchangeCode(code: String): Boolean {
        val response = authApi.exchangeCode(
            code = code,
            clientId = config.appKey,
            codeVerifier = PkceSession.verifier ?: return false,
            redirectUri = config.redirectUri
        )
        val body = response.body()
        if (response.isSuccessful && body?.accessToken != null) {
            persist(body)
            PkceSession.clear()
            return true
        }
        return false
    }

    /**
     * Returns a valid access token, refreshing if expired/near-expiry. Returns null when there is no
     * valid session at all (never connected or refresh failed) — the caller should show login.
     */
    suspend fun getValidAccessToken(): String? {
        val access = tokenStore.accessToken
        val notExpired = System.currentTimeMillis() < tokenStore.expiresAt - 60_000
        if (!access.isNullOrBlank() && notExpired) return access
        return refreshAccessToken()
    }

    /** Forces a token refresh using the stored refresh token. */
    suspend fun refreshAccessToken(): String? = refreshMutex.withLock {
        // Another coroutine may have refreshed while we waited on the lock.
        val current = tokenStore.accessToken
        if (!current.isNullOrBlank() && System.currentTimeMillis() < tokenStore.expiresAt - 60_000) {
            return current
        }
        val refresh = tokenStore.refreshToken ?: return null
        return try {
            val response = authApi.refreshToken(refreshToken = refresh, clientId = config.appKey)
            val body = response.body()
            if (response.isSuccessful && body?.accessToken != null) {
                persist(body)
                body.accessToken
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    fun disconnect() = tokenStore.clear()

    private fun persist(body: TokenResponse) {
        tokenStore.saveTokens(body.accessToken, body.refreshToken, body.expiresIn)
    }
}

/**
 * Holds the PKCE verifier/challenge between launching the WebView login modal and exchanging the
 * returned authorization code. Process-scoped singleton so the exchange is self-contained.
 */
object PkceSession {
    @Volatile var verifier: String? = null
        private set
    @Volatile var challenge: String? = null
        private set

    /** Starts a new PKCE session and returns the code challenge for the authorize URL. */
    fun begin(): String {
        val v = PkceUtil.generateCodeVerifier()
        verifier = v
        val c = PkceUtil.generateCodeChallenge(v)
        challenge = c
        return c
    }

    fun clear() {
        verifier = null
        challenge = null
    }
}
