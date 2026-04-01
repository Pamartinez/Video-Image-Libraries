package com.samsung.android.gallery.support.providers;

import android.net.Uri;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MediaUriCmh implements UriInterface {
    private static final Uri AUTHORITY_URI;
    private static final Uri FILES_TABLE_URI_RW;
    private static final Uri IMAGES_TABLE_URI_RW;
    private static final Uri VIDEOS_TABLE_URI_RW;

    static {
        Uri parse = Uri.parse("content://com.samsung.cmh");
        AUTHORITY_URI = parse;
        FILES_TABLE_URI_RW = Uri.withAppendedPath(parse, "files");
        IMAGES_TABLE_URI_RW = Uri.withAppendedPath(parse, "images");
        VIDEOS_TABLE_URI_RW = Uri.withAppendedPath(parse, "video");
    }

    public Uri getCloudWatchUri() {
        return null;
    }

    public Uri getDirectories() {
        return unsupportedUri();
    }

    public Uri getFilesUri() {
        return FILES_TABLE_URI_RW;
    }

    public Uri getImageUri() {
        return IMAGES_TABLE_URI_RW;
    }

    public Uri getImageWatchUri() {
        return Uri.withAppendedPath(AUTHORITY_URI, "notify_images");
    }

    public Uri getRawQueryUri(String str) {
        return AUTHORITY_URI.buildUpon().appendPath("rawquery").appendQueryParameter("rawquery", str).build();
    }

    public Uri getScloudDeleted() {
        return unsupportedUri();
    }

    public Uri getScloudPolicy() {
        return unsupportedUri();
    }

    public Uri getSecCloudUri() {
        return unsupportedUri();
    }

    public Uri getSecMediaUri() {
        return FILES_TABLE_URI_RW;
    }

    public Uri getSecPickerUri() {
        return unsupportedUri();
    }

    public String getSecPickerUriString() {
        return null;
    }

    public Uri getVideoUri() {
        return VIDEOS_TABLE_URI_RW;
    }

    public Uri getVideoWatchUri() {
        return Uri.withAppendedPath(AUTHORITY_URI, "notify_videos");
    }

    public boolean isSecMediaUri(String str) {
        return false;
    }

    public boolean matches(String str) {
        return Pattern.matches("^content://(|\\d+@)media/external/(images|video)/.*", str);
    }
}
