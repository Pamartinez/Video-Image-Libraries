package com.samsung.android.gallery.support.providers;

import android.net.Uri;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaUriGmp extends MediaUriGed {
    private static final Uri AUTHORITY_URI;
    private static final Uri GROUP_TABLE_URI;
    public static final Uri RAW_QUERY_URI;
    private static final Uri SEC_CLOUD_TABLE_URI = Uri.parse("content://secmedia/cloud");
    private static final Uri SEC_MEDIA_TABLE_URI;
    private static final Uri SEC_PICKER_URI = Uri.parse("content://secmedia/gallery/picker");
    private static final Uri SEC_SCLOUD_DELETED = Uri.parse("content://secmedia/scloud/deleted");
    private static final Uri SEC_SCLOUD_POLICY = Uri.parse("content://secmedia/scloud/policy");

    static {
        Uri parse = Uri.parse("content://com.sec.android.gallery3d.provider.GalleryMediaProvider");
        AUTHORITY_URI = parse;
        SEC_MEDIA_TABLE_URI = Uri.withAppendedPath(parse, "files");
        GROUP_TABLE_URI = Uri.withAppendedPath(parse, "group_contents");
        RAW_QUERY_URI = Uri.withAppendedPath(parse, "raw_sql");
    }

    public Uri getCloudWatchUri() {
        return null;
    }

    public /* bridge */ /* synthetic */ Uri getDirectories() {
        return super.getDirectories();
    }

    public /* bridge */ /* synthetic */ Uri getFilesUri(String str) {
        return super.getFilesUri(str);
    }

    public Uri getGroupTableUri() {
        return GROUP_TABLE_URI;
    }

    public Uri getImageWatchUri() {
        return SEC_MEDIA_TABLE_URI;
    }

    public /* bridge */ /* synthetic */ Uri getMediaSyncUri() {
        return super.getMediaSyncUri();
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

    public Uri getSecFilesUri() {
        return SEC_MEDIA_TABLE_URI;
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
        return null;
    }

    public /* bridge */ /* synthetic */ boolean isSecMediaUri(String str) {
        return super.isSecMediaUri(str);
    }

    public /* bridge */ /* synthetic */ boolean matches(String str) {
        return super.matches(str);
    }
}
