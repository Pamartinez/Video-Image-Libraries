package com.samsung.android.gallery.module.cloud.sdk;

import A.a;
import Qb.c;
import S8.C0577a;
import Sd.A;
import Sd.B;
import Sd.C;
import Sd.C0836a;
import Sd.C0837b;
import Sd.C0838c;
import Sd.C0839d;
import Sd.D;
import Sd.e;
import Sd.i;
import Sd.w;
import Sd.x;
import Sd.y;
import Sd.z;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import com.samsung.android.gallery.module.cloud.abstraction.CloudDownloadListener;
import com.samsung.android.gallery.module.cloud.abstraction.CloudDownloadMonitor;
import com.samsung.android.gallery.module.cloud.abstraction.DownloadParams;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.scsp.common.Holder;
import java.io.FileDescriptor;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SamsungCloud2Sdk {
    public static final int SERVICE_ACCOUNT_REQUIRED = x.AccountRequired.ordinal();
    public static final int SERVICE_ACCOUNT_REQUIRED_FOR_NEW_GALLERY = x.AccountRequiredForNewGallery.ordinal();
    public static final int SERVICE_LEGACY_STATUS_REQUIRED = x.LegacyServiceStatusRequired.ordinal();
    public static final int SERVICE_MIGRATION_AVAILABLE = x.MigrationAvailable.ordinal();
    public static final int SERVICE_NEW_GALLERY_AVAILABLE = x.NewGalleryAvailable.ordinal();
    public static final int SERVICE_NOT_AVAILABLE = x.ServiceNotAvailable.ordinal();
    public static final int SERVICE_ONE_DRIVE_AVAILABLE = x.OneDriveAvailable.ordinal();
    public static final int SERVICE_ONE_DRIVE_LINK_REQUIRED = x.OneDriveLinkRequired.ordinal();
    public static final int SERVICE_SUBSCRIPTION_REQUIRED = x.SubscriptionRequired.ordinal();
    public static final int SERVICE_SUBSCRIPTION_REQUIRED_LINKED_BEFORE = x.SubscriptionRequiredLinkedBefore.ordinal();
    public static final int SYNC_SETTING_DISABLED = z.Disabled.ordinal();
    public static final int SYNC_SETTING_OFF = z.Off.ordinal();
    public static final int SYNC_SETTING_ON = z.On.ordinal();
    public static final int SYNC_SETTING_UNKNOWN = z.Unknown.ordinal();
    public static final int SYNC_STATUS_CANCELED = B.Canceled.ordinal();
    public static final int SYNC_STATUS_COMPLETED = B.Completed.ordinal();
    public static final int SYNC_STATUS_FAILED = B.Failed.ordinal();
    public static final int SYNC_STATUS_NONE = B.None.ordinal();
    public static final int SYNC_STATUS_PROGRESS = B.InProgress.ordinal();
    public static final int SYNC_STATUS_UNKNOWN = B.Unknown.ordinal();

    public static C0836a bindDownloadMonitor(C0836a aVar, DownloadParams downloadParams) {
        CloudDownloadMonitor downloadMonitor = downloadParams.getDownloadMonitor();
        if (downloadMonitor != null) {
            CloudDownloadListener progressListener = downloadMonitor.getProgressListener(downloadParams.getCloudServerId());
            if (progressListener != null) {
                ((C0837b) aVar).f = new c(9, progressListener);
            }
            downloadMonitor.addDownloadRequest(downloadParams.getCloudServerId(), aVar);
        }
        return aVar;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:1|2|3|4|5|6|10) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x002a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void cancelDownload(Sd.C0836a r7) {
        /*
            java.lang.String r0 = "SamsungCloud2Sdk"
            java.lang.String r1 = "cancel download"
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x003e }
            Sd.b r7 = (Sd.C0837b) r7     // Catch:{ Exception -> 0x003e }
            r7.getClass()     // Catch:{ Exception -> 0x003e }
            java.lang.String r4 = "[SCG-SDK][0.0.0019]"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x003e }
            java.lang.String r6 = "download: cancel. "
            r5.<init>(r6)     // Catch:{ Exception -> 0x003e }
            java.lang.String r6 = r7.b     // Catch:{ Exception -> 0x003e }
            r5.append(r6)     // Catch:{ Exception -> 0x003e }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x003e }
            android.util.Log.e(r4, r5)     // Catch:{ Exception -> 0x003e }
            r4 = 1
            r7.g = r4     // Catch:{ Exception -> 0x003e }
            java.lang.Runnable r7 = r7.f3698h     // Catch:{ all -> 0x002a }
            r7.run()     // Catch:{ all -> 0x002a }
        L_0x002a:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x003e }
            r7.<init>(r1)     // Catch:{ Exception -> 0x003e }
            java.lang.String r1 = com.samsung.android.gallery.support.utils.Logger.vt((long) r2)     // Catch:{ Exception -> 0x003e }
            r7.append(r1)     // Catch:{ Exception -> 0x003e }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x003e }
            com.samsung.android.gallery.support.utils.Log.d(r0, r7)     // Catch:{ Exception -> 0x003e }
            goto L_0x0049
        L_0x003e:
            r7 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "cancel download failed. e="
            r1.<init>(r2)
            A.a.s(r7, r1, r0)
        L_0x0049:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.cloud.sdk.SamsungCloud2Sdk.cancelDownload(Sd.a):void");
    }

    public static ArrayList<Uri> download(Context context, DownloadParams downloadParams) {
        ParcelFileDescriptor openFileDescriptor;
        boolean z;
        long currentTimeMillis = System.currentTimeMillis();
        boolean z3 = true;
        if (!FileUtils.makeParentIfAbsent(downloadParams.getTargetPath()) || TextUtils.isEmpty(downloadParams.getCloudServerId())) {
            downloadParams.clear();
            Log.e((CharSequence) "SamsungCloud2Sdk", "download failed. wrong arguments", Boolean.valueOf(!TextUtils.isEmpty(downloadParams.getCloudServerId())));
            return null;
        }
        Uri insertRecordForMerge = SamsungCloudSdk.insertRecordForMerge(context, downloadParams);
        try {
            openFileDescriptor = SamsungCloudSdk.openFileDescriptor(context, insertRecordForMerge);
            if (!((C0837b) bindDownloadMonitor(new C0837b(context, downloadParams.getCloudServerId(), (String) null, openFileDescriptor.getFileDescriptor()), downloadParams)).d(false).a() || openFileDescriptor.getStatSize() <= 0) {
                z = false;
            } else {
                z = true;
            }
            Log.d("SamsungCloud2Sdk", "download" + Logger.vt(Boolean.valueOf(z), Long.valueOf(openFileDescriptor.getStatSize()), Long.valueOf(currentTimeMillis)));
            if (z) {
                ArrayList<Uri> contentUriAfterDownload = SamsungCloudSdk.getContentUriAfterDownload(context, insertRecordForMerge, downloadParams);
                openFileDescriptor.close();
                downloadParams.clear();
                return contentUriAfterDownload;
            }
            SamsungCloudSdk.removeInsertedRecord(context, insertRecordForMerge);
            openFileDescriptor.close();
            downloadParams.clear();
            return null;
        } catch (Exception e) {
            try {
                SamsungCloudSdk.removeInsertedRecord(context, insertRecordForMerge);
                StringBuilder sb2 = new StringBuilder("download failed");
                Long valueOf = Long.valueOf(downloadParams.getFileId());
                if (insertRecordForMerge == null) {
                    z3 = false;
                }
                sb2.append(Logger.v(valueOf, Boolean.valueOf(z3)));
                Log.e((CharSequence) "SamsungCloud2Sdk", sb2.toString(), (Throwable) e);
                return null;
            } finally {
                downloadParams.clear();
            }
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static boolean downloadFileDirectly(Context context, DownloadParams downloadParams) {
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = true;
        if (!FileUtils.makeParentIfAbsent(downloadParams.getTargetPath()) || TextUtils.isEmpty(downloadParams.getCloudServerId())) {
            downloadParams.clear();
            Log.e((CharSequence) "SamsungCloud2Sdk", "download directly failed. wrong arguments", Boolean.valueOf(!TextUtils.isEmpty(downloadParams.getCloudServerId())));
            return false;
        }
        try {
            if (!((C0837b) bindDownloadMonitor(new C0837b(context, downloadParams.getCloudServerId(), downloadParams.getTargetPath(), (FileDescriptor) null), downloadParams)).d(false).a() || FileUtils.length(downloadParams.getTargetPath()) <= 0) {
                z = false;
            }
            Log.d("SamsungCloud2Sdk", "download directly" + Logger.vt(Boolean.valueOf(z), Long.valueOf(currentTimeMillis)));
            downloadParams.clear();
            return z;
        } catch (Exception e) {
            Log.e((CharSequence) "SamsungCloud2Sdk", "download directly failed" + Logger.v(Long.valueOf(downloadParams.getFileId())), (Throwable) e);
            downloadParams.clear();
            return false;
        } catch (Throwable th) {
            downloadParams.clear();
            throw th;
        }
    }

    public static boolean downloadItemOriginalFile(Context context, String str, String str2) {
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = true;
        if (!FileUtils.makeParentIfAbsent(str2) || TextUtils.isEmpty(str)) {
            Log.e((CharSequence) "SamsungCloud2Sdk", "downloadItemOriginalFile failed. wrong arguments", Boolean.valueOf(!TextUtils.isEmpty(str)));
            return false;
        }
        try {
            if (!new C0837b(context, str, str2, (FileDescriptor) null).d(true).a() || FileUtils.length(str2) <= 0) {
                z = false;
            }
            Log.d("SamsungCloud2Sdk", "downloadItemOriginalFile" + Logger.vt(Boolean.valueOf(z), Long.valueOf(currentTimeMillis)));
            return z;
        } catch (Exception e) {
            Log.e((CharSequence) "SamsungCloud2Sdk", "downloadItemOriginalFile failed", (Throwable) e);
            return false;
        }
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [Sd.d, java.lang.Object] */
    public static C0839d getApi(Context context) {
        new i(context, "NON-BLOCKING");
        ? obj = new Object();
        obj.f3699a = new i(context, "NON-BLOCKING");
        obj.f3699a = new D(context);
        return obj;
    }

    public static String getStreamingUrl(Context context, String str) {
        return null;
    }

    public static boolean isContentSyncOn(Context context) {
        if (requestGetSyncSetting(context) == SYNC_SETTING_ON) {
            return true;
        }
        return false;
    }

    public static y requestCheckIfServiceIsAvailable(Context context) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Holder holder = new Holder();
            getApi(context).f3699a.a(new Bundle(), "check_if_new_gallery_is_available", new c(13, new C0577a(holder, 2)));
            y yVar = (y) holder.get();
            Log.d("SamsungCloud2Sdk", "requestCheckIfServiceIsAvailable" + Logger.vt(Boolean.valueOf(yVar.a()), yVar.e, Boolean.valueOf(yVar.f), Long.valueOf(yVar.f3723h), Boolean.valueOf(yVar.f3724i.contains("PERMISSION_REQUIRED")), Long.valueOf(currentTimeMillis)));
            return yVar;
        } catch (Exception e) {
            a.s(e, new StringBuilder("requestCheckIfServiceIsAvailable failed. e="), "SamsungCloud2Sdk");
            return null;
        }
    }

    public static boolean requestEmptyTrash(Context context) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Holder holder = new Holder();
            getApi(context).f3699a.a(new Bundle(), "empty_trash", new C0838c(new C0577a(holder, 1), 0));
            boolean a7 = ((w) holder.get()).a();
            Log.d("SamsungCloud2Sdk", "requestEmptyTrash" + Logger.vt(Boolean.valueOf(a7), Long.valueOf(currentTimeMillis)));
            return a7;
        } catch (Exception e) {
            a.s(e, new StringBuilder("requestEmptyTrash failed. e="), "SamsungCloud2Sdk");
            return false;
        }
    }

    public static Bundle requestGetAlbumStatus(Context context) {
        Bundle bundle;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Holder holder = new Holder();
            getApi(context).f3699a.a(new Bundle(), "get_album_status", new c(14, new C0577a(holder, 3)));
            e eVar = (e) holder.get();
            boolean a7 = eVar.a();
            Bundle bundle2 = eVar.e;
            if (a7) {
                bundle = bundle2;
            } else {
                bundle = null;
            }
            Log.d("SamsungCloud2Sdk", "requestGetAlbumStatus" + Logger.vt(Boolean.valueOf(eVar.a()), bundle2, Long.valueOf(currentTimeMillis)));
            return bundle;
        } catch (Exception e) {
            a.s(e, new StringBuilder("requestGetAlbumStatus failed. e="), "SamsungCloud2Sdk");
            return null;
        }
    }

    public static int requestGetSyncSetting(Context context) {
        int i2;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Holder holder = new Holder();
            getApi(context).f3699a.a(new Bundle(), "get_sync_setting", new c(11, new C0577a(holder, 0)));
            A a7 = (A) holder.get();
            boolean a10 = a7.a();
            z zVar = a7.e;
            if (a10) {
                i2 = zVar.ordinal();
            } else {
                i2 = -1;
            }
            Log.d("SamsungCloud2Sdk", "requestGetSyncSetting" + Logger.vt(Boolean.valueOf(a7.a()), zVar, Long.valueOf(currentTimeMillis)));
            return i2;
        } catch (Exception e) {
            a.s(e, new StringBuilder("requestGetSyncSetting failed. e="), "SamsungCloud2Sdk");
            return -1;
        }
    }

    public static int requestGetSyncStatus(Context context) {
        int i2;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Holder holder = new Holder();
            getApi(context).f3699a.a(new Bundle(), "get_sync_status", new c(12, new C0577a(holder, 4)));
            C c5 = (C) holder.get();
            boolean a7 = c5.a();
            B b = c5.e;
            if (a7) {
                i2 = b.ordinal();
            } else {
                i2 = -1;
            }
            Log.d("SamsungCloud2Sdk", "requestGetSyncStatus" + Logger.vt(Boolean.valueOf(c5.a()), b, Long.valueOf(currentTimeMillis)));
            return i2;
        } catch (Exception e) {
            a.s(e, new StringBuilder("requestGetSyncStatus failed. e="), "SamsungCloud2Sdk");
            return -1;
        }
    }

    public static boolean requestSetSyncState(Context context, boolean z) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Holder holder = new Holder();
            if (z) {
                getApi(context).f3699a.a(new Bundle(), "set_sync_on", new C0838c(new C0577a(holder, 1), 1));
            } else {
                getApi(context).f3699a.a(new Bundle(), "set_sync_off", new C0838c(new C0577a(holder, 1), 2));
            }
            boolean a7 = ((w) holder.get()).a();
            Log.d("SamsungCloud2Sdk", "requestSetSyncState" + Logger.vt(Boolean.valueOf(a7), Long.valueOf(currentTimeMillis)));
            return a7;
        } catch (Exception e) {
            a.s(e, new StringBuilder("requestSetSyncState failed. e="), "SamsungCloud2Sdk");
            return false;
        }
    }
}
