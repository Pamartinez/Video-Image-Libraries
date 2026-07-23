package com.example.common.data.dropbox.api

import com.example.common.data.dropbox.model.CreateFolderRequest
import com.example.common.data.dropbox.model.CreateFolderResult
import com.example.common.data.dropbox.model.FileMetadata
import com.example.common.data.dropbox.model.ListFolderContinueRequest
import com.example.common.data.dropbox.model.ListFolderRequest
import com.example.common.data.dropbox.model.ListFolderResponse
import com.example.common.data.dropbox.model.TokenResponse
import com.example.common.data.dropbox.model.UploadSessionStartResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

/** Token endpoint — no auth header (uses PKCE / refresh token in the form body). */
interface DropboxAuthApi {
    @FormUrlEncoded
    @POST("oauth2/token")
    suspend fun exchangeCode(
        @Field("code") code: String,
        @Field("grant_type") grantType: String = "authorization_code",
        @Field("client_id") clientId: String,
        @Field("code_verifier") codeVerifier: String,
        @Field("redirect_uri") redirectUri: String
    ): Response<TokenResponse>

    @FormUrlEncoded
    @POST("oauth2/token")
    suspend fun refreshToken(
        @Field("refresh_token") refreshToken: String,
        @Field("grant_type") grantType: String = "refresh_token",
        @Field("client_id") clientId: String
    ): Response<TokenResponse>
}

/** JSON RPC endpoints on api.dropboxapi.com (require Bearer auth). */
interface DropboxApi {
    @POST("2/files/list_folder")
    suspend fun listFolder(@Body request: ListFolderRequest): Response<ListFolderResponse>

    @POST("2/files/list_folder/continue")
    suspend fun listFolderContinue(@Body request: ListFolderContinueRequest): Response<ListFolderResponse>

    @POST("2/files/create_folder_v2")
    suspend fun createFolder(@Body request: CreateFolderRequest): Response<CreateFolderResult>
}

/** Content endpoints on content.dropboxapi.com (require Bearer auth + octet-stream body). */
interface DropboxContentApi {
    @POST("2/files/upload")
    suspend fun upload(
        @Header("Dropbox-API-Arg") apiArg: String,
        @Body body: RequestBody
    ): Response<FileMetadata>

    @POST("2/files/upload_session/start")
    suspend fun uploadSessionStart(
        @Header("Dropbox-API-Arg") apiArg: String,
        @Body body: RequestBody
    ): Response<UploadSessionStartResponse>

    @POST("2/files/upload_session/append_v2")
    suspend fun uploadSessionAppend(
        @Header("Dropbox-API-Arg") apiArg: String,
        @Body body: RequestBody
    ): Response<Unit>

    @POST("2/files/upload_session/finish")
    suspend fun uploadSessionFinish(
        @Header("Dropbox-API-Arg") apiArg: String,
        @Body body: RequestBody
    ): Response<FileMetadata>
}
