package com.example.common.data.dropbox

import android.content.Context
import com.example.common.data.dropbox.api.DropboxApi
import com.example.common.data.dropbox.api.DropboxAuthApi
import com.example.common.data.dropbox.api.DropboxAuthInterceptor
import com.example.common.data.dropbox.api.DropboxContentApi
import com.example.common.data.dropbox.api.DropboxTokenAuthenticator
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Bundle of ready-to-use Dropbox components for one app. Built by [DropboxClientFactory].
 */
class DropboxClient(
    val config: DropboxConfig,
    val authManager: DropboxAuthManager,
    val repository: DropboxRepository
)

/**
 * Builds the full Dropbox stack (two Retrofit clients, auth manager, repository) from a
 * [DropboxConfig]. App-agnostic: each app injects its own app key / redirect / token store name, so
 * the same code serves image-library, video-library and gallery-transfer-library.
 */
object DropboxClientFactory {

    fun create(context: Context, config: DropboxConfig): DropboxClient {
        val appContext = context.applicationContext
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        val tokenStore = TokenStore(appContext, config.tokenStoreName)

        // Auth client has NO interceptor/authenticator (the token endpoint is unauthenticated).
        val authClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        val authApi = Retrofit.Builder()
            .baseUrl(DropboxConfig.API_BASE)
            .client(authClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(DropboxAuthApi::class.java)

        val authManager = DropboxAuthManager(config, authApi, tokenStore)

        val authInterceptor = DropboxAuthInterceptor(authManager)
        val authenticator = DropboxTokenAuthenticator(authManager)

        // JSON RPC client (short timeouts).
        val apiClient = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .authenticator(authenticator)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        val api = Retrofit.Builder()
            .baseUrl(DropboxConfig.API_BASE)
            .client(apiClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(DropboxApi::class.java)

        // Content client (long timeouts for large uploads).
        val contentClient = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .authenticator(authenticator)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(0, TimeUnit.MILLISECONDS)
            .readTimeout(0, TimeUnit.MILLISECONDS)
            .build()

        val contentApi = Retrofit.Builder()
            .baseUrl(DropboxConfig.CONTENT_BASE)
            .client(contentClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(DropboxContentApi::class.java)

        val repository = DropboxRepository(appContext.contentResolver, api, contentApi, moshi)

        return DropboxClient(config, authManager, repository)
    }
}
