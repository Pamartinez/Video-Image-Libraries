package com.example.common.data.dropbox.api

import com.example.common.data.dropbox.DropboxAuthManager
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.Route

/**
 * Adds the current Bearer access token to every Dropbox API/content request, refreshing proactively
 * when it is expired.
 */
class DropboxAuthInterceptor(
    private val authManager: DropboxAuthManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking { authManager.getValidAccessToken() }
        val request = if (token != null) {
            chain.request().newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
        } else {
            chain.request()
        }
        return chain.proceed(request)
    }
}

/**
 * On a 401, refreshes the access token once and retries the request. Gives up (returns null) if the
 * refresh fails or the request already carried a freshly-refreshed token.
 */
class DropboxTokenAuthenticator(
    private val authManager: DropboxAuthManager
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): okhttp3.Request? {
        if (responseCount(response) >= 2) return null // already retried once
        val newToken = runBlocking { authManager.refreshAccessToken() } ?: return null
        return response.request.newBuilder()
            .header("Authorization", "Bearer $newToken")
            .build()
    }

    private fun responseCount(response: Response): Int {
        var count = 1
        var prior = response.priorResponse
        while (prior != null) {
            count++
            prior = prior.priorResponse
        }
        return count
    }
}
