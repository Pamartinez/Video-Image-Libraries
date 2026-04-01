package com.samsung.android.gallery.module.cloud;

import A.a;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.cloud.abstraction.CloudDownloadMonitor;
import com.samsung.android.gallery.module.cloud.abstraction.DownloadCanceller;
import com.samsung.android.gallery.module.cloud.abstraction.DownloadParams;
import com.samsung.android.gallery.module.cloud.abstraction.SamsungCloud;
import com.samsung.android.gallery.module.cloud.sdk.DownloadMonitor;
import com.samsung.android.gallery.module.cloud.sdk.DownloadStatus;
import com.samsung.android.gallery.module.cloud.sdk.SamsungCloudSdk;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.common.CommonUtils;
import com.samsung.scsp.media.Media;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SamsungCloudPImpl implements SamsungCloud {
    private final SyncStateHelper helper = createSyncStateHelper();

    private boolean changeSyncStateDisableAutomatically(Context context) {
        Account samsungAccount = getSamsungAccount(context);
        if (samsungAccount != null) {
            ContentResolver.setSyncAutomatically(samsungAccount, "media", false);
            requestToSyncDisable(context);
            return true;
        }
        Log.d("SamsungCloudPImpl", "changeSCloudContentSyncState Account : None ");
        return false;
    }

    private boolean changeSyncStateEnableMediaProxy(Context context) {
        try {
            Uri parse = Uri.parse("content://com.samsung.android.scloud.media.app");
            Bundle bundle = new Bundle();
            bundle.putInt("sync_value", 1);
            Bundle call = context.getContentResolver().call(parse, "setSyncStatus", (String) null, bundle);
            if (call == null || !call.getBoolean("is_success")) {
                return false;
            }
            return true;
        } catch (Exception e) {
            a.s(e, new StringBuilder("changeContentSyncState failed e="), "SamsungCloudPImpl");
            return false;
        }
    }

    private Account getSamsungAccount(Context context) {
        try {
            AccountManager accountManager = AccountManager.get(context);
            if (accountManager == null) {
                return null;
            }
            Account[] accountsByType = accountManager.getAccountsByType(CommonUtils.SAMSUNG_ACCOUNT_PACKAGE_NAME);
            if (accountsByType.length > 0) {
                return accountsByType[0];
            }
            return null;
        } catch (Exception e) {
            Log.e((CharSequence) "SamsungCloudPImpl", "getSamsungAccount failed", (Throwable) e);
            return null;
        }
    }

    private boolean isSyncOnAutomatically(Context context) {
        Account samsungAccount = getSamsungAccount(context);
        if (samsungAccount != null) {
            return ContentResolver.getSyncAutomatically(samsungAccount, "media");
        }
        Log.d("SamsungCloudPImpl", "isSyncOnAutomatically no account");
        return false;
    }

    private void requestToSyncDisable(Context context) {
        Intent intent = new Intent("com.samsung.android.scloud.media.sync.ACTION_CLOUD_MEDIA_REQUEST");
        intent.setPackage("com.samsung.android.scloud");
        intent.putExtra("cloud_request_mode", 3);
        intent.putExtra("auto_sync", false);
        context.sendBroadcast(intent);
    }

    public boolean changeContentSyncState(Context context, boolean z) {
        boolean z3;
        long currentTimeMillis = System.currentTimeMillis();
        if (z) {
            z3 = changeSyncStateEnableMediaProxy(context);
        } else {
            z3 = changeSyncStateDisableAutomatically(context);
        }
        StringBuilder sb2 = new StringBuilder("changeContentSyncState ");
        sb2.append(z);
        sb2.append(ArcCommonLog.TAG_COMMA);
        a.x(sb2, currentTimeMillis, "SamsungCloudPImpl");
        return z3;
    }

    public SyncStateHelper createSyncStateHelper() {
        return new SyncStateHelper();
    }

    public ArrayList<Uri> download(Context context, DownloadParams downloadParams) {
        return SamsungCloudSdk.download(context, downloadParams);
    }

    public boolean downloadFileDirectly(Context context, DownloadParams downloadParams) {
        return SamsungCloudSdk.downloadFileDirectly(context, downloadParams);
    }

    public Media empty(Context context, Media media) {
        Object obj;
        Media clearCloud = SamsungCloudSdk.clearCloud(context, media);
        StringBuilder sb2 = new StringBuilder("empty cloud file Media{");
        if (clearCloud == null) {
            obj = "fail";
        } else {
            obj = clearCloud.rcode;
        }
        sb2.append(obj);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(media.photoId);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(media.clientTimestamp);
        sb2.append("}");
        Log.d("SamsungCloudPImpl", sb2.toString());
        return clearCloud;
    }

    public int getAlbumSyncStatus(Context context, int i2, String str) {
        return this.helper.getAlbumSyncStatus(context, i2, str);
    }

    public DownloadCanceller getDownloadCanceller() {
        return new DownloadStatus();
    }

    public CloudDownloadMonitor getDownloadMonitor() {
        return new DownloadMonitor();
    }

    public final String getDownloadUrl(Context context, long j2, String str) {
        return SamsungCloudSdk.getDownloadUrl(context, str);
    }

    public String getStreamingUrl(Context context, long j2, String str) {
        return SamsungCloudSdk.getStreamUrl(context, str);
    }

    public boolean isCloudSyncOn(Context context) {
        return this.helper.isCloudSyncOn(context);
    }

    public boolean isCloudSyncOnCached(Context context) {
        return this.helper.isCloudSyncOnCached(context);
    }

    public boolean isContentSyncOn(Context context) {
        return isSyncOnAutomatically(context);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005e, code lost:
        if (java.lang.Integer.parseInt(r11) == 1) goto L_0x0071;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isSyncOffFolder(android.content.Context r10, java.lang.String r11) {
        /*
            r9 = this;
            r9 = 0
            if (r10 == 0) goto L_0x008d
            boolean r0 = android.text.TextUtils.isEmpty(r11)
            if (r0 == 0) goto L_0x000b
            goto L_0x008d
        L_0x000b:
            java.lang.String r0 = "/"
            boolean r0 = r11.endsWith(r0)
            r1 = 1
            if (r0 == 0) goto L_0x0018
            java.lang.String r11 = t1.C0280e.d(r1, r9, r11)
        L_0x0018:
            boolean r0 = com.samsung.android.gallery.support.utils.FileUtils.isInRemovableStorage(r11)
            java.lang.String r2 = "SamsungCloudPImpl"
            if (r0 == 0) goto L_0x0027
            java.lang.String r9 = "this folder is sdcard folder so sync off."
            com.samsung.android.gallery.support.utils.Log.d(r2, r9)
            return r1
        L_0x0027:
            int r11 = com.samsung.android.gallery.support.utils.FileUtils.getBucketId(r11)
            java.lang.String r6 = "bucket_id = ?"
            java.lang.String r11 = java.lang.Integer.toString(r11)
            java.lang.String[] r7 = new java.lang.String[]{r11}
            java.lang.String r11 = "album_sync"
            java.lang.String[] r5 = new java.lang.String[]{r11}
            com.samsung.android.gallery.support.providers.UriInterface r11 = com.samsung.android.gallery.support.providers.MediaUri.getInstance()
            android.net.Uri r4 = r11.getDirectories()
            android.content.ContentResolver r3 = r10.getContentResolver()     // Catch:{ Exception -> 0x006d }
            r8 = 0
            android.database.Cursor r10 = r3.query(r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x006d }
            if (r10 == 0) goto L_0x0070
            boolean r11 = r10.moveToFirst()     // Catch:{ all -> 0x0061 }
            if (r11 == 0) goto L_0x0070
            java.lang.String r11 = r10.getString(r9)     // Catch:{ all -> 0x0061 }
            if (r11 == 0) goto L_0x0070
            int r11 = java.lang.Integer.parseInt(r11)     // Catch:{ all -> 0x0061 }
            if (r11 != r1) goto L_0x0070
            goto L_0x0071
        L_0x0061:
            r0 = move-exception
            r9 = r0
            r10.close()     // Catch:{ all -> 0x0067 }
            goto L_0x006c
        L_0x0067:
            r0 = move-exception
            r10 = r0
            r9.addSuppressed(r10)     // Catch:{ Exception -> 0x006d }
        L_0x006c:
            throw r9     // Catch:{ Exception -> 0x006d }
        L_0x006d:
            r0 = move-exception
            r9 = r0
            goto L_0x007b
        L_0x0070:
            r9 = r1
        L_0x0071:
            if (r10 == 0) goto L_0x0086
            r10.close()     // Catch:{ Exception -> 0x0077 }
            goto L_0x0086
        L_0x0077:
            r0 = move-exception
            r10 = r0
            r1 = r9
            r9 = r10
        L_0x007b:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = "isSyncOffFolder failed e="
            r10.<init>(r11)
            A.a.s(r9, r10, r2)
            r9 = r1
        L_0x0086:
            java.lang.String r10 = "is sync off folder ["
            java.lang.String r11 = "]"
            A.a.v(r10, r11, r2, r9)
        L_0x008d:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.cloud.SamsungCloudPImpl.isSyncOffFolder(android.content.Context, java.lang.String):boolean");
    }

    public boolean isSyncSuccess(Context context) {
        return this.helper.isSyncSuccess(context);
    }

    public boolean isSyncing(Context context) {
        return this.helper.isSyncing(context);
    }

    public void releaseSyncState() {
        this.helper.releaseSyncState();
    }

    public Media restore(Context context, Media media) {
        Object obj;
        Media restoreCloud = SamsungCloudSdk.restoreCloud(context, media);
        StringBuilder sb2 = new StringBuilder("restore cloud file Media{");
        if (restoreCloud == null) {
            obj = "fail";
        } else {
            obj = restoreCloud.rcode;
        }
        sb2.append(obj);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(media.photoId);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(media.clientTimestamp);
        sb2.append("}");
        Log.d("SamsungCloudPImpl", sb2.toString());
        return restoreCloud;
    }

    public final boolean setFavorite(Context context, String str, String str2, int i2) {
        try {
            Uri secMediaUri = MediaUri.getInstance().getSecMediaUri();
            ContentValues contentValues = new ContentValues();
            contentValues.put("is_favorite", Integer.valueOf(i2));
            contentValues.put("dirty", 1);
            String str3 = "cloud_server_id = ?";
            if (TextUtils.isEmpty(str)) {
                str3 = "cloud_server_path = ?";
                str = str2;
            }
            if (context.getContentResolver().update(secMediaUri, contentValues, str3, new String[]{str}) > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            a.s(e, new StringBuilder("setFavorite failed e="), "SamsungCloudPImpl");
            return false;
        }
    }

    public void updateAlbumSyncData(Bundle bundle) {
        this.helper.updateAlbumSyncData(bundle);
    }

    public void updateCloudSyncOnCache(boolean z) {
        this.helper.updateCloudSyncOnCache(z);
    }

    public void updateSyncStatus(Bundle bundle) {
        this.helper.updateSyncStatus(bundle);
    }

    public boolean isSyncSuccess() {
        return this.helper.isSyncSuccess();
    }

    public boolean isSyncing() {
        return this.helper.isSyncing();
    }

    public void updateSyncStatus(int i2) {
        this.helper.updateSyncStatus(i2);
    }
}
