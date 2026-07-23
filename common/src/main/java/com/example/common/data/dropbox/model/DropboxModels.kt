package com.example.common.data.dropbox.model

import com.squareup.moshi.Json

/** Response from POST /oauth2/token (both code exchange and refresh). */
data class TokenResponse(
    @Json(name = "access_token") val accessToken: String?,
    @Json(name = "refresh_token") val refreshToken: String? = null,
    @Json(name = "expires_in") val expiresIn: Long? = null,
    @Json(name = "token_type") val tokenType: String? = null,
    @Json(name = "account_id") val accountId: String? = null,
    @Json(name = "scope") val scope: String? = null
)

/** Request body for POST /2/files/list_folder. */
data class ListFolderRequest(
    val path: String,
    val recursive: Boolean = false,
    @Json(name = "include_deleted") val includeDeleted: Boolean = false,
    @Json(name = "include_media_info") val includeMediaInfo: Boolean = false
)

/** Request body for POST /2/files/list_folder/continue. */
data class ListFolderContinueRequest(val cursor: String)

data class ListFolderResponse(
    val entries: List<Entry> = emptyList(),
    val cursor: String? = null,
    @Json(name = "has_more") val hasMore: Boolean = false
)

data class Entry(
    @Json(name = ".tag") val tag: String?,
    val name: String?,
    @Json(name = "path_lower") val pathLower: String?,
    @Json(name = "path_display") val pathDisplay: String?
) {
    val isFolder: Boolean get() = tag == "folder"
}

/** Request body for POST /2/files/create_folder_v2. */
data class CreateFolderRequest(
    val path: String,
    val autorename: Boolean = false
)

/** Response from POST /2/files/create_folder_v2. */
data class CreateFolderResult(val metadata: Entry?)

/** Metadata returned by a successful upload. */
data class FileMetadata(
    val name: String?,
    @Json(name = "path_lower") val pathLower: String?,
    @Json(name = "path_display") val pathDisplay: String?,
    val id: String?,
    val size: Long? = null
)

/** Argument object serialized into the Dropbox-API-Arg header for uploads. */
data class UploadArg(
    val path: String,
    val mode: String = "add",
    val autorename: Boolean = false,
    val mute: Boolean = true,
    // true so a same-name file is always reported as a conflict, even if its contents are identical
    // (Dropbox otherwise silently dedups). Matches the image/video libraries' name-based conflict UX.
    @Json(name = "strict_conflict") val strictConflict: Boolean = true
)

// ── Upload session (large files) ──

data class UploadSessionStartResponse(@Json(name = "session_id") val sessionId: String)

data class UploadCursor(
    @Json(name = "session_id") val sessionId: String,
    val offset: Long
)

data class UploadSessionAppendArg(
    val cursor: UploadCursor,
    val close: Boolean = false
)

data class CommitInfo(
    val path: String,
    val mode: String = "add",
    val autorename: Boolean = false,
    val mute: Boolean = true,
    @Json(name = "strict_conflict") val strictConflict: Boolean = true
)

data class UploadSessionFinishArg(
    val cursor: UploadCursor,
    val commit: CommitInfo
)
