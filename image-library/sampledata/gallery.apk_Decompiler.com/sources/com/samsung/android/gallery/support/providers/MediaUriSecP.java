package com.samsung.android.gallery.support.providers;

import android.net.Uri;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MediaUriSecP extends MediaUriGed implements UriInterface {
    private static final Uri AUTHORITY_URI;
    private static final Uri FILES_TABLE_URI_SEC_RO;
    private static final Uri RAW_QUERY_URI;
    private static final Uri SEC_DIRECTORIES_URI = Uri.parse("content://media/external/cmh/directories");
    private static final Uri SEC_MEDIA_TABLE_URI;
    private static final Uri SEC_PICKER_URI = Uri.parse("content://media/external/gallery/picker");
    private static final Uri SEC_SCLOUD_DELETED = Uri.parse("content://media/external/scloud/deleted");
    private static final Uri SEC_SCLOUD_POLICY = Uri.parse("content://media/external/scloud/policy");

    static {
        Uri parse = Uri.parse("content://media/external/sec");
        AUTHORITY_URI = parse;
        FILES_TABLE_URI_SEC_RO = Uri.withAppendedPath(parse, "file");
        SEC_MEDIA_TABLE_URI = Uri.withAppendedPath(parse, "media");
        RAW_QUERY_URI = Uri.withAppendedPath(parse, "media/raw_sql");
    }

    public Uri getCloudWatchUri() {
        return getSecMediaUri();
    }

    public Uri getDirectories() {
        return SEC_DIRECTORIES_URI;
    }

    public Uri getRawQueryUri(String str) {
        return RAW_QUERY_URI.buildUpon().appendEncodedPath(escapeQuery(str)).build();
    }

    public Uri getScloudDeleted() {
        return SEC_SCLOUD_DELETED;
    }

    public Uri getScloudPolicy() {
        return SEC_SCLOUD_POLICY;
    }

    public Uri getSecCloudUri() {
        return SEC_MEDIA_TABLE_URI;
    }

    public Uri getSecMediaUri() {
        return SEC_MEDIA_TABLE_URI;
    }

    public Uri getSecPickerUri() {
        return SEC_PICKER_URI;
    }

    public String getSecPickerUriString() {
        return "content://media/external/gallery/picker/";
    }

    public boolean matches(String str) {
        if (Pattern.matches("^content://(|\\d+@)media/external/(images|video)/.*", str) || Pattern.matches("^content://(|\\d+@)media/external/gallery/picker/(image|video)/.*", str)) {
            return true;
        }
        return false;
    }
}
