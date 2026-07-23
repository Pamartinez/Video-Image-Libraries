# Gallery Transfer Library — Design Document

**Status:** Planned (not yet implemented)
**Module:** `gallery-transfer-library`
**Package / Application ID:** `com.gallerytransferlibrary`
**Last updated:** 2026-07-21

---

## 1. Overview

`gallery-transfer-library` is a new Android app module (sibling to `image-library` and
`video-library`, sharing the `common` module) whose purpose is to **browse local media and upload
it to Dropbox**.

The user can:
1. Browse local folders containing **images and videos**.
2. **Multi-select** individual items (images/videos) **or** select whole folders.
3. **Upload** the selection to Dropbox.

The core feature is the **Dropbox integration**, implemented directly against the **Dropbox HTTP API
using Retrofit/OkHttp — no Dropbox SDK**. Authentication uses **OAuth 2.0 with PKCE**.

### Explicitly out of scope
Dropbox download/sync, groups/albums, local copy/move, rename/delete, hide/unhide, backup/restore,
drag-to-reorder, and search. This app is intentionally a **reduced feature set** compared to the
other two libraries.

---

## 2. Local Browsing UI

### Root screen (folders)
- Grid/list of **folders**, each aggregating both images and videos.
- Toolbar shows only: a **view-toggle icon** and a **3-dot overflow menu**.
- **3-dot menu contains ONLY:** `Sort`, `View as`, `Settings`, `About app`.

### Folder detail screen (items)
- Mixed grid of images + videos within the selected folder.
- **Tap an item (not in selection mode):**
  - Image → in-app carousel viewer (adapted from `image-library`).
  - Video → external player via `Intent.ACTION_VIEW` (as `video-library` does).

### Selection mode
Entered by long-press (items in a folder, or folders at root). The bottom action bar shows:

| Action | When enabled |
| --- | --- |
| **Upload to Dropbox** | ≥ 1 item or folder selected |
| **Open location** | Exactly 1 item selected |

Selecting a folder means "upload every image/video inside that folder".

---

## 3. Dropbox Integration (core feature)

### 3.1 Summary
| Aspect | Decision |
| --- | --- |
| Transport | Dropbox HTTP API via **Retrofit + OkHttp** (no SDK) |
| Direction | **Upload only** (local → Dropbox) |
| Auth | **OAuth 2.0 PKCE** via a **full-screen in-app WebView login modal**; public client, **no app secret** |
| Refresh | `token_access_type=offline`; auto-refresh on HTTP 401 |
| Token storage | **EncryptedSharedPreferences** (access + refresh) |
| Access level | **Full Dropbox** |
| Destination | **Configurable folder in Settings** (Dropbox folder picker); default `/` |
| Execution | **Foreground progress UI** + **WorkManager** background continuation |
| Conflicts | Per-file **Overwrite / Keep both / Skip** (+ "apply to all"), reusing `FileConflictDialog` |

### 3.2 OAuth 2.0 PKCE flow (in-app WebView login modal)
1. User taps **Connect Dropbox** in Settings.
2. A **full-screen modal sheet with an embedded `WebView`** opens. The app generates a PKCE
   `code_verifier` + `code_challenge` (S256) and the WebView loads the authorize URL:
   ```
   https://www.dropbox.com/oauth2/authorize
     ?client_id=APP_KEY
     &response_type=code
     &code_challenge=<S256>
     &code_challenge_method=S256
     &token_access_type=offline
     &redirect_uri=com.gallerytransferlibrary://oauth
     &scope=files.content.write files.metadata.read
   ```
3. The user logs in and approves **inside the WebView**. Dropbox redirects to
   `com.gallerytransferlibrary://oauth?code=...`; the WebView's
   `WebViewClient.shouldOverrideUrlLoading` **intercepts** this URL, extracts the `code`, and closes
   the modal. **No Custom Tab, no browser, no manifest intent-filter — the user never leaves the app.**
4. App exchanges the code (+ `code_verifier`) at `POST https://api.dropboxapi.com/oauth2/token` for
   **access + refresh** tokens, stored encrypted. The token is never handled inside the WebView.
5. On any `401`, a `TokenAuthenticator` refreshes using the refresh token and retries once.

> **No sign-out / disconnect** is implemented for now (may be added later).

### 3.2a Auth-on-demand during upload (resume after login)
If the user starts an upload while **not connected** (no tokens) or with an **expired/unrefreshable
session**, the upload does not fail:
1. The pending upload request (selected items + destination) is **held**.
2. The **WebView login modal** is shown automatically.
3. On successful login (tokens stored), the **held upload automatically resumes/continues** from where it
   was, with no need for the user to re-tap Upload.
4. If the user cancels the login modal, the upload is cancelled cleanly (no partial state).

Note: a merely-expired **access token with a valid refresh token** never reaches this flow — the
`TokenAuthenticator` refreshes silently on 401. The modal only appears when there is **no valid session at
all** (never connected, or refresh token invalid/revoked).

### 3.3 API endpoints used
| Purpose | Method / Endpoint | Host |
| --- | --- | --- |
| Authorize (in WebView modal) | `GET /oauth2/authorize` | `www.dropbox.com` |
| Token exchange / refresh | `POST /oauth2/token` (form) | `api.dropboxapi.com` |
| List folders | `POST /2/files/list_folder` (+ `/continue`) | `api.dropboxapi.com` |
| Upload ≤ 150 MB | `POST /2/files/upload` (octet-stream, `Dropbox-API-Arg` header) | `content.dropboxapi.com` |
| Upload > 150 MB | `upload_session/start` → `append_v2` (4 MB chunks) → `finish` | `content.dropboxapi.com` |

Two OkHttp clients are used: an **API client** (`api.dropboxapi.com`, JSON RPC) and a **content
client** (`content.dropboxapi.com`, streamed octet-stream with long timeouts and a progress-reporting
`RequestBody`).

### 3.4 Conflict resolution mapping
| User choice | Dropbox behavior |
| --- | --- |
| **Overwrite** | `WriteMode.overwrite` |
| **Keep both** | `WriteMode.add` + `autorename=true` |
| **Skip** | File is not uploaded |

"Apply to all" short-circuits subsequent prompts (mirrors `MediaTransferHelper.onConflict`).

---

## 4. Architecture & Code Reuse

### 4.1 Reusability requirement (important)
> The **entire Dropbox stack (auth + API + upload orchestration) lives in the `common` module** so
> that `image-library` and `video-library` can adopt "Upload to Dropbox" later with **zero
> duplication**. `gallery-transfer-library` is simply the first consumer.

The stack is **app-agnostic**: the **app key, redirect URI, and token-store name are injected per
app** (via a factory such as `DropboxClientFactory.create(appKey, redirectUri, tokenStoreName)`),
so it is never hard-coupled to one app's `BuildConfig`.

### 4.2 Reused from `common` (unchanged)
`FolderItem`, `MixedItem`, `ViewType`, sort options + utilities, `BottomActionBar` (with additive
`showUpload` / `onUpload` params), `FileConflictDialog`, `CopyMoveProgressDialog` (reused for upload
progress), `FileManagerHelper.openFolder` (Open location), permission/media utils, theme,
`ZoomableImage`, `AboutScreen`, shared settings scaffolding, Sort/View-as dialogs, `FastScroller`.

### 4.3 Borrowed/adapted into the module
- **Image viewer:** `ImageCarouselScreen`, `CarouselOverlayBars`, `ImageThumbnail` (from image-library).
- **Video thumbnails:** `VideoThumbnail`, `VideoThumbnailCache`, `VideoThumbnailDiskCache`,
  `VideoThumbnailExtractor`, `ThumbnailGenerationService` (from video-library).
- **Video playback:** `ACTION_VIEW` intent pattern (from video-library `playVideo`).
- **Scaffolding:** MainActivity / manifest / theme / launcher / gradle modeled on video-library.

### 4.4 New code — local media (in `gallery-transfer-library`)
`data/model/MediaItem.kt`, `data/repository/MediaRepository.kt` (MediaStore images + videos →
`FolderItem`s + per-bucket media), `data/preferences/AppPreferences.kt` (viewType, sort,
`dropboxDestPath`), `ui/viewmodel/MediaListViewModel.kt`,
`ui/screen/{MediaListScreen, FolderDetailScreen, MediaCarouselScreen, SettingsScreen}.kt`,
`ui/components/MediaGridItem.kt`, MainActivity + resources.

### 4.5 New code — Dropbox layer (in `common`, `data/dropbox/`)
`DropboxConfig`, `PkceUtil`, `DropboxAuthManager`, `TokenStore` (EncryptedSharedPreferences,
namespaced per app), `api/DropboxApi` (Retrofit), `api/DropboxAuthInterceptor`,
`api/DropboxTokenAuthenticator`, `DropboxRepository` (listFolders, uploadFile with progress, session
upload), `model/*` DTOs, and a `DropboxClientFactory`.

### 4.6 New code — upload orchestration (in `common`, `upload/`)
`UploadItem`, `UploadManager` (queue + per-file progress + suspend conflict callback mirroring
`MediaTransferHelper.onConflict`), `UploadWorker` (WorkManager `CoroutineWorker` + foreground
notification). **App-specific bits stay in the module:** WebView sign-in modal, folder-picker screen,
progress/conflict overlay host composition. (No manifest redirect intent-filter is needed — the WebView
intercepts the redirect internally.)

---

## 5. User Flows

1. **Connect:** Settings → *Connect Dropbox* → full-screen WebView login modal → user logs in + approves →
   WebView intercepts the redirect `code` → modal closes → token exchange → stored.
2. **Choose destination:** Settings → *Dropbox folder* → picker → save path.
3. **Upload:** browse → select items/folders → *Upload to Dropbox* → **if not connected / no valid session,
   the login modal appears automatically and the upload resumes after login** → resolve conflicts →
   progress overlay → WorkManager continues if backgrounded → done.

---

## 6. Key Decisions

| ID | Decision |
| --- | --- |
| **D1** | PKCE public client; offline refresh token; **no secret** stored. Login uses a **full-screen in-app WebView modal** that intercepts the redirect to capture the `code`; app then exchanges it for tokens via Retrofit. No Custom Tab, no manifest intent-filter. **No sign-out for now.** |
| **D2** | Single `UploadManager` as source of truth; foreground observes directly; on background, remaining queue is handed to `UploadWorker` (unique work, foreground notification) to avoid double-upload. (Alt: WorkManager-only with `WorkInfo` observation — finalize at implementation start.) |
| **D3** | Conflict mapping: Overwrite→`overwrite`; Keep both→`add`+`autorename`; Skip→omit; "apply to all" short-circuits. |
| **D4** | Add additive `showUpload` / `onUpload` params to `common` `BottomActionBar` (other two apps unaffected). |
| **D5** | Ship `/upload` (≤150 MB) first; add chunked `upload_session` for >150 MB as a sequenced follow-up. |
| **D6** | App key via `local.properties` → `BuildConfig.DROPBOX_APP_KEY`; redirect scheme in manifest; tokens encrypted. |
| **D7** | Reduced feature set ⇒ full image/video parity rule does **not** apply; only shared ops (Open location, Sort, View as, viewers, conflict dialog) match common components. |
| **D9** | **Auth-on-demand upload:** starting an upload while not connected / no valid session holds the request, auto-shows the login modal, and **auto-resumes the upload after login**. Silent refresh (via TokenAuthenticator) handles merely-expired access tokens without a modal. Cancelling login cancels the upload cleanly. |

---

## 7. Dependencies to Add

Retrofit + a converter (Moshi or kotlinx-serialization) + OkHttp + logging-interceptor;
`androidx.browser` (Custom Tabs); `androidx.security-crypto` (EncryptedSharedPreferences);
`androidx.work-runtime-ktx` (WorkManager); Coil + coil-video.

Because the Dropbox stack lives in `common`, the networking/security/work dependencies are added to
**`common/build.gradle.kts`** (inherited by all apps). Versions go in `libs.versions.toml`.

---

## 8. Dropbox App Console Setup (required before end-to-end auth)

> **Why an App key is required:** the App key is the app's **`client_id`** — the public identifier every
> OAuth provider requires to know which app is making requests. It appears in the authorize URL and is
> **not** a secret, **not** a password, and **not** the user's login. It's a **one-time developer setup**;
> end-users just log in with their own Dropbox account in the modal and never see the key. There is no way
> to call the Dropbox API without one (even the official SDK requires it).

1. Go to https://www.dropbox.com/developers/apps → **Create app**.
2. Choose **Scoped access** → **Full Dropbox** → name the app.
3. Under **Permissions**, enable `files.content.write` + `files.metadata.read` → **Submit**.
4. Under **Settings → Redirect URIs**, add `com.gallerytransferlibrary://oauth` (PKCE — no secret needed).
   The in-app WebView intercepts this redirect directly; it is never actually navigated to.
5. Copy the **App key** into git-ignored `local.properties`:
   ```
   DROPBOX_APP_KEY=your_app_key_here
   ```
   exposed as `BuildConfig.DROPBOX_APP_KEY`.
6. Do **not** store an app secret (PKCE public client).

---

## 9. Risks & Open Items

- Dropbox **app key + approved scopes** are required before end-to-end auth can be tested (user needs
  help creating the app — see §8).
- **WebView login modal** correctness: enable cookies/JavaScript, ensure `shouldOverrideUrlLoading` catches
  the `com.gallerytransferlibrary://oauth` custom scheme, and handle user-cancel (modal closed with no code).
- `upload_session` correctness (chunk sizing, cursor offsets) for large videos.
- MediaStore URI → stream via `contentResolver.openInputStream`; carry the item size for progress.
- **D2** (foreground vs WorkManager-only execution) to be finalized at implementation start.

---

## 10. Implementation Todos

Tracked in the session's SQL `todos` table.

**Phase A — local browse foundation**
`scaffold-module` → `data-layer` → `thumbnails` → `viewmodel` → `root-screen` → `folder-screen` → `viewers`

**Phase B — Dropbox integration (core, in `common`)**
`dropbox-deps` → `dropbox-auth` → `dropbox-api` → `dropbox-folder-picker` → `upload-manager` →
`upload-worker` → `bottombar-upload` → `upload-progress-ui`

**Phase C — hardening**
`large-file-session` → `build-verify`
