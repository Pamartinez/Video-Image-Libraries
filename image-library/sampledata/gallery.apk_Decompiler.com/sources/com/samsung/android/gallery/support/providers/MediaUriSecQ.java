package com.samsung.android.gallery.support.providers;

import android.net.Uri;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MediaUriSecQ extends MediaUriGed implements UriInterface {
    private static final Uri AUTHORITY_URI;
    private static final Uri FILES_TABLE_URI_SEC_RO;
    private static final Uri GROUP_TABLE_URI = Uri.parse("content://secmedia/cmh/group");
    private static final Uri IMAGES_TABLE_URI_SEC_RO;
    private static final Uri LOCATION_EDIT_URI = Uri.parse("content://secmedia/gallery/location");
    static final Pattern PATTERN_SEC_MEDIA_AUTHORITY = Pattern.compile("^content://(|\\d+@)secmedia/(((images|video)/)?media/|gallery/picker/(image|video)/).*");
    public static final Uri RAW_QUERY_URI;
    private static final Uri SEC_CLOUD_TABLE_URI = Uri.parse("content://secmedia/cloud");
    static final Uri SEC_MEDIA_TABLE_URI = Uri.parse("content://secmedia/media");
    private static final Uri SEC_PICKER_URI = Uri.parse("content://secmedia/gallery/picker");
    private static final Uri SEC_SCLOUD_DELETED = Uri.parse("content://secmedia/scloud/deleted");
    private static final Uri SEC_SCLOUD_POLICY = Uri.parse("content://secmedia/scloud/policy");
    private static final Uri VIDEOS_TABLE_URI_SEC_RO;

    static {
        Uri parse = Uri.parse("content://secmedia");
        AUTHORITY_URI = parse;
        FILES_TABLE_URI_SEC_RO = Uri.withAppendedPath(parse, "files");
        IMAGES_TABLE_URI_SEC_RO = Uri.withAppendedPath(parse, "images/media");
        VIDEOS_TABLE_URI_SEC_RO = Uri.withAppendedPath(parse, "video/media");
        RAW_QUERY_URI = Uri.withAppendedPath(parse, "raw_sql");
    }

    public Uri getCloudWatchUri() {
        return SEC_MEDIA_TABLE_URI;
    }

    public Uri getGroupTableUri() {
        return GROUP_TABLE_URI;
    }

    public Uri getImageWatchUri() {
        return SEC_MEDIA_TABLE_URI;
    }

    public Uri getLocationEditUri() {
        return LOCATION_EDIT_URI;
    }

    public Uri getMediaSyncUri() {
        return getSecMediaUri();
    }

    public Uri getRawQueryUri(String str) {
        return RAW_QUERY_URI;
    }

    public Uri getScloudDeleted() {
        return SEC_SCLOUD_DELETED;
    }

    public Uri getScloudPolicy() {
        return SEC_SCLOUD_POLICY;
    }

    public Uri getSecCloudUri() {
        return SEC_CLOUD_TABLE_URI;
    }

    public Uri getSecMediaUri() {
        return SEC_MEDIA_TABLE_URI;
    }

    public Uri getSecPickerUri() {
        return SEC_PICKER_URI;
    }

    public String getSecPickerUriString() {
        return "content://secmedia/gallery/picker/";
    }

    public Uri getVideoWatchUri() {
        return SEC_MEDIA_TABLE_URI;
    }

    public boolean isSecMediaUri(String str) {
        if (str == null || !PATTERN_SEC_MEDIA_AUTHORITY.matcher(str).matches()) {
            return false;
        }
        return true;
    }

    public boolean matches(String str) {
        if (PATTERN_SEC_MEDIA_AUTHORITY.matcher(str).matches() || UriInterface.PATTERN_GED_MEDIA_AUTHORITY.matcher(str).matches()) {
            return true;
        }
        return false;
    }
}
