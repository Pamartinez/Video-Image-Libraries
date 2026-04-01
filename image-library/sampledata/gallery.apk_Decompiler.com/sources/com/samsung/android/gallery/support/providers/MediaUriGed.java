package com.samsung.android.gallery.support.providers;

import android.net.Uri;
import android.provider.MediaStore;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MediaUriGed implements UriInterface {
    private static final Uri AUTHORITY_URI = Uri.parse("content://media");
    private static final Uri FILES_TABLE_URI_RW = MediaStore.Files.getContentUri("external");
    private static final Uri IMAGES_TABLE_URI_RW = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    private static final Uri VIDEOS_TABLE_URI_RW = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

    public final String escapeQuery(String str) {
        return str.replace("%", "%25").replace("/", "%2F");
    }

    public Uri getCloudWatchUri() {
        return null;
    }

    public Uri getDirectories() {
        return unsupportedUri();
    }

    public final Uri getFilesUri() {
        return FILES_TABLE_URI_RW;
    }

    public final Uri getImageUri() {
        return IMAGES_TABLE_URI_RW;
    }

    public Uri getImageWatchUri() {
        return IMAGES_TABLE_URI_RW;
    }

    public Uri getMediaSyncUri() {
        return getFilesUri();
    }

    public Uri getRawQueryUri(String str) {
        return unsupportedUri();
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

    public final Uri getVideoUri() {
        return VIDEOS_TABLE_URI_RW;
    }

    public Uri getVideoWatchUri() {
        return VIDEOS_TABLE_URI_RW;
    }

    public boolean isSecMediaUri(String str) {
        return false;
    }

    public boolean matches(String str) {
        return isGedMediaUri(str);
    }

    public Uri getFilesUri(String str) {
        return (str == null || str.startsWith("external")) ? getFilesUri() : MediaStore.Files.getContentUri(str);
    }
}
