package com.samsung.android.gallery.module.cloud.abstraction;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.scsp.media.Media;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface SamsungCloud {
    boolean changeContentSyncState(Context context, boolean z);

    int copy(Context context, String str, String str2) {
        return -1;
    }

    boolean delete(Context context, String str) {
        return false;
    }

    ArrayList<Uri> download(Context context, DownloadParams downloadParams);

    boolean downloadFileDirectly(Context context, DownloadParams downloadParams) {
        return false;
    }

    boolean downloadItemOriginalFile(Context context, String str, String str2, boolean z, String str3, boolean z3) {
        return false;
    }

    Media empty(Context context, Media media);

    int getAlbumSyncStatus(Context context, int i2, String str) {
        return 0;
    }

    DownloadCanceller getDownloadCanceller();

    CloudDownloadMonitor getDownloadMonitor();

    String getDownloadUrl(Context context, long j2, String str);

    String getStreamingUrl(Context context, long j2, String str);

    boolean isCloudSyncOn(Context context) {
        return false;
    }

    boolean isCloudSyncOnCached(Context context) {
        return false;
    }

    boolean isContentSyncOn(Context context);

    boolean isSyncOffFolder(Context context, String str);

    boolean isSyncSuccess() {
        return false;
    }

    boolean isSyncing() {
        return false;
    }

    int move(Context context, String str, String str2, boolean z) {
        return -1;
    }

    Media restore(Context context, Media media);

    boolean setFavorite(Context context, String str, String str2, int i2);

    void updateSyncStatus(int i2) {
    }

    int copy(Context context, ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        return -1;
    }

    boolean isSyncSuccess(Context context) {
        return false;
    }

    boolean isSyncing(Context context) {
        return false;
    }

    int move(Context context, ArrayList<String> arrayList, ArrayList<String> arrayList2, boolean z) {
        return -1;
    }

    void updateSyncStatus(Bundle bundle) {
    }

    void releaseSyncState() {
    }

    String getOriginalFilePath(String str) {
        return str;
    }

    void updateAlbumSyncData(Bundle bundle) {
    }

    void updateCloudSyncOnCache(boolean z) {
    }
}
