package com.samsung.android.gallery.module.cloud;

import N2.j;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.cloud.abstraction.CloudDownloadMonitor;
import com.samsung.android.gallery.module.cloud.abstraction.DownloadCanceller;
import com.samsung.android.gallery.module.cloud.abstraction.DownloadParams;
import com.samsung.android.gallery.module.cloud.sdk.SamsungCloudSdk;
import com.samsung.android.gallery.module.nondestruction.NondestructiveEditor;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.scsp.media.Media;
import i.C0212a;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SamsungCloudTImpl extends SamsungCloudQImpl {
    public /* bridge */ /* synthetic */ boolean changeContentSyncState(Context context, boolean z) {
        return super.changeContentSyncState(context, z);
    }

    public /* bridge */ /* synthetic */ ArrayList download(Context context, DownloadParams downloadParams) {
        return super.download(context, downloadParams);
    }

    public /* bridge */ /* synthetic */ boolean downloadFileDirectly(Context context, DownloadParams downloadParams) {
        return super.downloadFileDirectly(context, downloadParams);
    }

    public boolean downloadItemOriginalFile(Context context, String str, String str2, boolean z, String str3, boolean z3) {
        String hiddenOriginalPath = NondestructiveEditor.getHiddenOriginalPath(z, str2);
        if (FileUtils.exists(hiddenOriginalPath)) {
            Log.d("SamsungCloudTImpl", "failed to download: original file is already existed");
            return false;
        }
        String originalPathHashKey = getOriginalPathHashKey(str2);
        if (TextUtils.isEmpty(originalPathHashKey)) {
            Log.d("SamsungCloudTImpl", "failed to download: original path hash is empty");
            return false;
        }
        String str4 = hiddenOriginalPath;
        boolean downloadOriginal = downloadOriginal(context, str, str4, str3, z3);
        if (downloadOriginal) {
            insertToNDE(context, str4, originalPathHashKey);
        }
        C0212a.x("downloaded item original file: ", "SamsungCloudTImpl", downloadOriginal);
        return downloadOriginal;
    }

    public boolean downloadOriginal(Context context, String str, String str2, String str3, boolean z) {
        return SamsungCloudSdk.downloadItemOriginalFile(context, str, str2, str3, z);
    }

    public /* bridge */ /* synthetic */ Media empty(Context context, Media media) {
        return super.empty(context, media);
    }

    public /* bridge */ /* synthetic */ int getAlbumSyncStatus(Context context, int i2, String str) {
        return super.getAlbumSyncStatus(context, i2, str);
    }

    public /* bridge */ /* synthetic */ DownloadCanceller getDownloadCanceller() {
        return super.getDownloadCanceller();
    }

    public /* bridge */ /* synthetic */ CloudDownloadMonitor getDownloadMonitor() {
        return super.getDownloadMonitor();
    }

    public String getOriginalPathHashKey(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new String(SeApiCompat.getSefFileCompat().getData(new File(str), SefInfo.ORIGINAL_PATH_HASH_KEY.keyName));
        } catch (IOException e) {
            j.r(e, new StringBuilder("getOriginalPathHashKey failed e="), "SamsungCloudTImpl");
            return null;
        }
    }

    public /* bridge */ /* synthetic */ String getStreamingUrl(Context context, long j2, String str) {
        return super.getStreamingUrl(context, j2, str);
    }

    public void insertToNDE(Context context, String str, String str2) {
        NondestructiveEditor nondestructiveEditor = new NondestructiveEditor();
        File file = new File(str);
        String localOriginalFilePath = nondestructiveEditor.getLocalOriginalFilePath(context, str2);
        if (!file.isFile() || !TextUtils.isEmpty(localOriginalFilePath)) {
            Log.e("SamsungCloudTImpl", "failed to insert ( " + file.isFile() + ArcCommonLog.TAG_COMMA + TextUtils.isEmpty(localOriginalFilePath) + ")");
            return;
        }
        nondestructiveEditor.insertOriginalData(context, file, str2);
    }

    public /* bridge */ /* synthetic */ boolean isCloudSyncOn(Context context) {
        return super.isCloudSyncOn(context);
    }

    public /* bridge */ /* synthetic */ boolean isCloudSyncOnCached(Context context) {
        return super.isCloudSyncOnCached(context);
    }

    public /* bridge */ /* synthetic */ boolean isContentSyncOn(Context context) {
        return super.isContentSyncOn(context);
    }

    public /* bridge */ /* synthetic */ boolean isSyncOffFolder(Context context, String str) {
        return super.isSyncOffFolder(context, str);
    }

    public /* bridge */ /* synthetic */ boolean isSyncSuccess() {
        return super.isSyncSuccess();
    }

    public /* bridge */ /* synthetic */ boolean isSyncing() {
        return super.isSyncing();
    }

    public /* bridge */ /* synthetic */ void releaseSyncState() {
        super.releaseSyncState();
    }

    public /* bridge */ /* synthetic */ Media restore(Context context, Media media) {
        return super.restore(context, media);
    }

    public /* bridge */ /* synthetic */ void updateAlbumSyncData(Bundle bundle) {
        super.updateAlbumSyncData(bundle);
    }

    public /* bridge */ /* synthetic */ void updateCloudSyncOnCache(boolean z) {
        super.updateCloudSyncOnCache(z);
    }

    public /* bridge */ /* synthetic */ void updateSyncStatus(int i2) {
        super.updateSyncStatus(i2);
    }

    public /* bridge */ /* synthetic */ boolean isSyncSuccess(Context context) {
        return super.isSyncSuccess(context);
    }

    public /* bridge */ /* synthetic */ boolean isSyncing(Context context) {
        return super.isSyncing(context);
    }

    public /* bridge */ /* synthetic */ void updateSyncStatus(Bundle bundle) {
        super.updateSyncStatus(bundle);
    }
}
