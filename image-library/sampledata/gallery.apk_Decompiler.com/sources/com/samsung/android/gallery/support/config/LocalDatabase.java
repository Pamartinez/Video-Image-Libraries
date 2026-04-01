package com.samsung.android.gallery.support.config;

import android.net.Uri;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class LocalDatabase {
    public static final Uri ALBUM_COVER_HISTORY_URI;
    public static final Uri ALBUM_ORDER_URI;
    public static final Uri ALBUM_URI;
    public static final Uri ASHMEM_URI;
    public static final Uri FILESYSTEM_MONITOR_URI;
    public static final Uri GALLERY_CACHE_URI;
    public static final Uri GALLERY_FILE_URI;
    public static final Uri LOG_URI;
    public static final Uri MX_ALBUM_URI;
    public static final Uri MY_QUERY_URI;
    public static final Uri OPERATION_HISTORY_URI;
    public static final Uri RECOVERY_URI;
    public static final Uri SEARCH_HISTORY_URI;
    public static final Uri SHARE_ALBUM_URI;
    public static final Uri SHARE_URI;
    public static final Uri SUGGEST_URI;
    public static final Uri TRASH_URI;
    public static final Uri URI;
    @Deprecated
    public static final Uri URI_LEGACY = Uri.parse("content://com.sec.android.gallery3d.provider");

    static {
        Uri parse = Uri.parse("content://com.sec.android.gallery3d.provider2");
        URI = parse;
        ALBUM_ORDER_URI = Uri.withAppendedPath(parse, "album_display_info_table");
        ALBUM_URI = Uri.withAppendedPath(parse, "album");
        MX_ALBUM_URI = Uri.withAppendedPath(parse, "mxalbum");
        ALBUM_COVER_HISTORY_URI = Uri.withAppendedPath(parse, "album_cover_history");
        SEARCH_HISTORY_URI = Uri.withAppendedPath(parse, "search_history");
        MY_QUERY_URI = Uri.withAppendedPath(parse, "my_query");
        LOG_URI = Uri.withAppendedPath(parse, "log");
        TRASH_URI = Uri.withAppendedPath(parse, "trash");
        SHARE_URI = Uri.withAppendedPath(parse, "share");
        SUGGEST_URI = Uri.withAppendedPath(parse, "suggested");
        ASHMEM_URI = Uri.withAppendedPath(parse, "ashmem");
        OPERATION_HISTORY_URI = Uri.withAppendedPath(parse, "operation_history");
        FILESYSTEM_MONITOR_URI = Uri.withAppendedPath(parse, "filesystem_monitor");
        GALLERY_FILE_URI = Uri.withAppendedPath(parse, "local");
        GALLERY_CACHE_URI = Uri.withAppendedPath(parse, "cache");
        SHARE_ALBUM_URI = parse.buildUpon().appendPath("share").appendPath("album").build();
        RECOVERY_URI = Uri.withAppendedPath(parse, "recover");
    }

    public static Uri getLocalUriForFile(String str) {
        return GALLERY_FILE_URI.buildUpon().appendPath(str).build();
    }
}
