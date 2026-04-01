package com.samsung.android.gallery.support.providers;

import android.net.Uri;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaUriSecU extends MediaUriSecR {
    private static final Uri TRASH_URI = Uri.parse("content://sectrash/files");

    public /* bridge */ /* synthetic */ Uri getCloudWatchUri() {
        return super.getCloudWatchUri();
    }

    public /* bridge */ /* synthetic */ Uri getDirectories() {
        return super.getDirectories();
    }

    public /* bridge */ /* synthetic */ Uri getFilesUri(String str) {
        return super.getFilesUri(str);
    }

    public /* bridge */ /* synthetic */ Uri getGroupTableUri() {
        return super.getGroupTableUri();
    }

    public /* bridge */ /* synthetic */ Uri getImageWatchUri() {
        return super.getImageWatchUri();
    }

    public /* bridge */ /* synthetic */ Uri getLocationEditUri() {
        return super.getLocationEditUri();
    }

    public /* bridge */ /* synthetic */ Uri getMediaSyncUri() {
        return super.getMediaSyncUri();
    }

    public /* bridge */ /* synthetic */ Uri getRawQueryUri(String str) {
        return super.getRawQueryUri(str);
    }

    public /* bridge */ /* synthetic */ Uri getScloudDeleted() {
        return super.getScloudDeleted();
    }

    public /* bridge */ /* synthetic */ Uri getScloudPolicy() {
        return super.getScloudPolicy();
    }

    public /* bridge */ /* synthetic */ Uri getSecCloudUri() {
        return super.getSecCloudUri();
    }

    public /* bridge */ /* synthetic */ Uri getSecMediaUri() {
        return super.getSecMediaUri();
    }

    public /* bridge */ /* synthetic */ Uri getSecPickerUri() {
        return super.getSecPickerUri();
    }

    public /* bridge */ /* synthetic */ String getSecPickerUriString() {
        return super.getSecPickerUriString();
    }

    public Uri getSecTrashUri() {
        return TRASH_URI;
    }

    public /* bridge */ /* synthetic */ Uri getVideoWatchUri() {
        return super.getVideoWatchUri();
    }

    public /* bridge */ /* synthetic */ boolean isSecMediaUri(String str) {
        return super.isSecMediaUri(str);
    }

    public /* bridge */ /* synthetic */ boolean matches(String str) {
        return super.matches(str);
    }

    public /* bridge */ /* synthetic */ Uri getSecMediaUri(boolean z) {
        return super.getSecMediaUri(z);
    }
}
