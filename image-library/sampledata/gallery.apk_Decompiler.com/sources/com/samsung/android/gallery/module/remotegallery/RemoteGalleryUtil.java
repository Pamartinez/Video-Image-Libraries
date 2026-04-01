package com.samsung.android.gallery.module.remotegallery;

import N2.j;
import android.graphics.Bitmap;
import android.os.PowerManager;
import com.samsung.android.gallery.compat.qrencoder.QRCodeWriter;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.FileNameBuilder;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.sdk.scs.base.StatusCodes;
import com.samsung.android.sum.core.types.NumericEnum;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class RemoteGalleryUtil {
    private static long lockedTime;
    private static PowerManager.WakeLock wakeLock;

    public static String createDeeplink(String str, String str2) {
        return j.d("app://com.sec.android.gallery3d/location/albums/fileList?id=0&shortcut_album=true&remoteIp=", str, "&pass=", str2, "&min_version=2024011800");
    }

    public static Bitmap createQrCode(String str, String str2) {
        try {
            return new QRCodeWriter().encode(createDeeplink(str, str2), StatusCodes.INPUT_MISSING, StatusCodes.INPUT_MISSING);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String createWebLink(String str, String str2) {
        return j.d("http://", str, NumericEnum.SEP, str2, "/");
    }

    public static Bitmap createWebQrCode(String str, String str2) {
        try {
            return new QRCodeWriter().encode(createWebLink(str, str2), StatusCodes.INPUT_MISSING, StatusCodes.INPUT_MISSING);
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean download(FileItemInterface fileItemInterface, Runnable runnable) {
        if (PocFeatures.isEnabled(PocFeatures.WifiGalleryClient) && fileItemInterface.getStorageType() == StorageType.RemoteItem) {
            String buildUnique = new FileNameBuilder(getPath(fileItemInterface)).buildUnique();
            FileUtils.makeParentIfAbsent(buildUnique);
            new RemoteClient(getIp(fileItemInterface)).download(fileItemInterface.getFileId(), new SecureFile(buildUnique));
            if (FileUtils.length(buildUnique) > 0) {
                Log.d("RemoteGallery", "try write file success : " + buildUnique);
                MediaScanner.scanFile(buildUnique, new i(buildUnique, runnable));
                return true;
            }
            Log.d("RemoteGallery", "try write file failed : " + buildUnique);
        }
        return false;
    }

    public static String downloadToTemp(FileItemInterface fileItemInterface) {
        String remoteTempFilePath = getRemoteTempFilePath(fileItemInterface);
        if (FileUtils.length(remoteTempFilePath) != fileItemInterface.getFileSize()) {
            new RemoteClient(getIp(fileItemInterface)).download(fileItemInterface.getFileId(), new SecureFile(remoteTempFilePath));
            if (FileUtils.length(remoteTempFilePath) > 0) {
                return remoteTempFilePath;
            }
            return null;
        }
        return remoteTempFilePath;
    }

    public static String getIp(FileItemInterface fileItemInterface) {
        String path = fileItemInterface.getPath();
        if (path == null) {
            return fileItemInterface.getDisplayName().substring(9);
        }
        String substring = path.substring(9);
        return substring.substring(0, substring.indexOf("/"));
    }

    public static String getPath(FileItemInterface fileItemInterface) {
        String substring = fileItemInterface.getPath().substring(9);
        return substring.substring(substring.indexOf("/") + 1);
    }

    private static String getRemoteTempFilePath(FileItemInterface fileItemInterface) {
        return FileUtils.getExternalFilesDir("remoteTemp") + "/" + fileItemInterface.getComplexHashCode() + "_" + fileItemInterface.getDisplayName();
    }

    private static PowerManager.WakeLock getWakeUpLock() {
        PowerManager powerManager = (PowerManager) AppResources.getAppContext().getSystemService("power");
        if (powerManager != null) {
            return powerManager.newWakeLock(268435457, "wakeUpForRemote_Gallery");
        }
        return null;
    }

    public static boolean isRemoteAlbum(FileItemInterface fileItemInterface) {
        if (fileItemInterface.getDisplayName().startsWith("remote://") || fileItemInterface.getDisplayName().startsWith("remote.")) {
            return true;
        }
        return false;
    }

    public static boolean isRemoteFile(FileItemInterface fileItemInterface) {
        if (fileItemInterface.getStorageType() == StorageType.RemoteItem) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$download$0(String str, Runnable runnable) {
        Log.d("RemoteGallery", "try write file scanned : " + str);
        if (runnable != null) {
            runnable.run();
        }
    }

    public static void removeWakeup() {
        try {
            PowerManager.WakeLock wakeLock2 = wakeLock;
            if (wakeLock2 != null && wakeLock2.isHeld()) {
                wakeLock.release();
            }
        } catch (RuntimeException unused) {
        }
    }

    public static void wakeUp() {
        wakeUp(StatusCodes.INPUT_MISSING);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002c, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void wakeUp(int r8) {
        /*
            java.lang.Class<com.samsung.android.gallery.module.remotegallery.RemoteGalleryUtil> r0 = com.samsung.android.gallery.module.remotegallery.RemoteGalleryUtil.class
            monitor-enter(r0)
            android.os.PowerManager$WakeLock r1 = wakeLock     // Catch:{ all -> 0x0010 }
            r2 = 1000(0x3e8, double:4.94E-321)
            if (r1 != 0) goto L_0x0012
            android.os.PowerManager$WakeLock r1 = getWakeUpLock()     // Catch:{ all -> 0x0010 }
            wakeLock = r1     // Catch:{ all -> 0x0010 }
            goto L_0x0022
        L_0x0010:
            r8 = move-exception
            goto L_0x002d
        L_0x0012:
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0010 }
            long r6 = lockedTime     // Catch:{ all -> 0x0010 }
            long r6 = r4 - r6
            int r1 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r1 >= 0) goto L_0x0020
            monitor-exit(r0)
            return
        L_0x0020:
            lockedTime = r4     // Catch:{ all -> 0x0010 }
        L_0x0022:
            android.os.PowerManager$WakeLock r1 = wakeLock     // Catch:{ all -> 0x0010 }
            if (r1 == 0) goto L_0x002b
            long r4 = (long) r8     // Catch:{ all -> 0x0010 }
            long r4 = r4 * r2
            r1.acquire(r4)     // Catch:{ all -> 0x0010 }
        L_0x002b:
            monitor-exit(r0)
            return
        L_0x002d:
            monitor-exit(r0)     // Catch:{ all -> 0x0010 }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.remotegallery.RemoteGalleryUtil.wakeUp(int):void");
    }
}
