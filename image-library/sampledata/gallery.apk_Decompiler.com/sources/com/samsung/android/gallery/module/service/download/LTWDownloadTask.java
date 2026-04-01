package com.samsung.android.gallery.module.service.download;

import android.content.Context;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.samsung.android.gallery.database.dal.DebugLogger;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.microsoft.YourPhone;
import com.samsung.android.gallery.module.service.message.LTWDownloadMsgMgr;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.StorageUtil;
import i.C0212a;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LTWDownloadTask extends DownloadTask {
    private YourPhone.ErrorType mErrorType = YourPhone.ErrorType.NONE;
    private final int mTargetAlbumId;
    private final String mTargetAlbumPath;

    public LTWDownloadTask(Context context, String str, int i2) {
        super(context);
        this.mTargetAlbumPath = str;
        this.mTargetAlbumId = i2;
    }

    private void addDownloadable(ArrayList<MediaItem> arrayList, MediaItem mediaItem) {
        if (mediaItem != null) {
            arrayList.add(mediaItem);
            if (mediaItem.isVideo()) {
                this.mVideoCount++;
            } else {
                this.mImageCount++;
            }
            this.mTotalSize = getFileSize(mediaItem) + this.mTotalSize;
        }
    }

    private void deleteFailedDownloadFile(String str) {
        boolean z;
        SecureFile secureFile = new SecureFile(str);
        boolean exists = secureFile.exists();
        if (exists) {
            z = secureFile.delete();
        } else {
            z = false;
        }
        String str2 = this.TAG;
        Log.w(str2, "delete temp downloaded file [" + exists + "][" + z + "]");
    }

    private boolean downloadInternal(MediaItem mediaItem) {
        if (TextUtils.isEmpty(mediaItem.getPath()) || TextUtils.isEmpty(this.mTargetAlbumPath)) {
            return false;
        }
        String str = this.mTargetAlbumPath + File.separator + mediaItem.getDisplayName();
        boolean downloadItem = downloadItem(mediaItem, str);
        if (!downloadItem) {
            if (StorageUtil.checkLowStorage(false)) {
                this.mErrorType = YourPhone.ErrorType.STORAGE;
            } else {
                this.mErrorType = YourPhone.ErrorType.ETC;
            }
            deleteFailedDownloadFile(str);
            return downloadItem;
        }
        this.mErrorType = YourPhone.ErrorType.NONE;
        return downloadItem;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        r11.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x009e, code lost:
        r2 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        r12 = r2.checkValidFileSize(r3, r13.getFileSize(), r1.getPath(), r1.length());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00a3, code lost:
        if (r12 == false) goto L_0x00ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00a5, code lost:
        com.samsung.android.gallery.support.utils.MediaScanner.scanFile(r14, (com.samsung.android.gallery.support.utils.MediaScannerListener) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00aa, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        r11.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:?, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00b3, code lost:
        return r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00b4, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00b7, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00c1, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:?, code lost:
        r13.addSuppressed(r0);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:49:0x00ad, B:60:0x00bd] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00cc A[SYNTHETIC, Splitter:B:70:0x00cc] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean downloadItem(com.samsung.android.gallery.module.data.MediaItem r13, java.lang.String r14) {
        /*
            r12 = this;
            java.lang.String r0 = "downloadItem::openInputStream() elapsed +"
            boolean r1 = com.samsung.android.gallery.support.utils.FileUtils.exists(r14)
            if (r1 == 0) goto L_0x000c
            java.lang.String r14 = com.samsung.android.gallery.support.utils.FileUtils.getNewFilePath(r14)
        L_0x000c:
            r12.setTimer(r14)
            com.samsung.android.gallery.support.utils.SecureFile r1 = new com.samsung.android.gallery.support.utils.SecureFile
            r1.<init>(r14)
            java.lang.String r3 = r13.getPath()
            java.lang.String r2 = r12.TAG
            java.lang.String r4 = "downloadItem::openInputStream() start"
            com.samsung.android.gallery.support.utils.Log.p(r2, r4)
            long r4 = java.lang.System.currentTimeMillis()
            r9 = 0
            android.content.Context r2 = r12.getContext()     // Catch:{ Exception -> 0x00d6 }
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch:{ Exception -> 0x00d6 }
            android.net.Uri r6 = android.net.Uri.parse(r3)     // Catch:{ Exception -> 0x00d6 }
            java.io.InputStream r10 = r2.openInputStream(r6)     // Catch:{ Exception -> 0x00d6 }
            java.io.FileOutputStream r11 = new java.io.FileOutputStream     // Catch:{ all -> 0x00c7 }
            r11.<init>(r1)     // Catch:{ all -> 0x00c7 }
            java.lang.String r2 = r12.TAG     // Catch:{ all -> 0x00ba }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ba }
            r6.<init>(r0)     // Catch:{ all -> 0x00ba }
            long r7 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00ba }
            long r7 = r7 - r4
            r6.append(r7)     // Catch:{ all -> 0x00ba }
            java.lang.String r0 = r6.toString()     // Catch:{ all -> 0x00ba }
            com.samsung.android.gallery.support.utils.Log.p(r2, r0)     // Catch:{ all -> 0x00ba }
            r12.resetTimer()     // Catch:{ all -> 0x00ba }
            if (r10 != 0) goto L_0x0073
            java.lang.String r13 = r12.TAG     // Catch:{ all -> 0x006f }
            java.lang.String r14 = "downloadItem::failed. null input-stream"
            com.samsung.android.gallery.support.utils.Log.e(r13, r14)     // Catch:{ all -> 0x006f }
            r11.close()     // Catch:{ all -> 0x006a }
            if (r10 == 0) goto L_0x0069
        L_0x0060:
            r10.close()     // Catch:{ Exception -> 0x0064 }
            return r9
        L_0x0064:
            r0 = move-exception
            r13 = r0
            r2 = r12
            goto L_0x00d9
        L_0x0069:
            return r9
        L_0x006a:
            r0 = move-exception
            r13 = r0
            r2 = r12
            goto L_0x00ca
        L_0x006f:
            r0 = move-exception
            r13 = r0
            r2 = r12
            goto L_0x00bd
        L_0x0073:
            r0 = 2048(0x800, float:2.87E-42)
            byte[] r0 = new byte[r0]     // Catch:{ all -> 0x00ba }
        L_0x0077:
            int r2 = r10.read(r0)     // Catch:{ all -> 0x00ba }
            r4 = -1
            if (r2 <= r4) goto L_0x0092
            r11.write(r0, r9, r2)     // Catch:{ all -> 0x006f }
            boolean r4 = r12.isCancelled()     // Catch:{ all -> 0x006f }
            if (r4 == 0) goto L_0x008b
            r11.close()     // Catch:{ all -> 0x006a }
            goto L_0x0060
        L_0x008b:
            long r4 = (long) r2
            long r6 = r12.mTotalSize     // Catch:{ all -> 0x006f }
            r12.progress(r4, r6)     // Catch:{ all -> 0x006f }
            goto L_0x0077
        L_0x0092:
            long r4 = r13.getFileSize()     // Catch:{ all -> 0x00ba }
            java.lang.String r6 = r1.getPath()     // Catch:{ all -> 0x00ba }
            long r7 = r1.length()     // Catch:{ all -> 0x00ba }
            r2 = r12
            boolean r12 = r2.checkValidFileSize(r3, r4, r6, r7)     // Catch:{ all -> 0x00aa }
            if (r12 == 0) goto L_0x00ad
            r13 = 0
            com.samsung.android.gallery.support.utils.MediaScanner.scanFile(r14, r13)     // Catch:{ all -> 0x00aa }
            goto L_0x00ad
        L_0x00aa:
            r0 = move-exception
        L_0x00ab:
            r13 = r0
            goto L_0x00bd
        L_0x00ad:
            r11.close()     // Catch:{ all -> 0x00b7 }
            r10.close()     // Catch:{ Exception -> 0x00b4 }
            return r12
        L_0x00b4:
            r0 = move-exception
        L_0x00b5:
            r13 = r0
            goto L_0x00d9
        L_0x00b7:
            r0 = move-exception
        L_0x00b8:
            r13 = r0
            goto L_0x00ca
        L_0x00ba:
            r0 = move-exception
            r2 = r12
            goto L_0x00ab
        L_0x00bd:
            r11.close()     // Catch:{ all -> 0x00c1 }
            goto L_0x00c6
        L_0x00c1:
            r0 = move-exception
            r12 = r0
            r13.addSuppressed(r12)     // Catch:{ all -> 0x00b7 }
        L_0x00c6:
            throw r13     // Catch:{ all -> 0x00b7 }
        L_0x00c7:
            r0 = move-exception
            r2 = r12
            goto L_0x00b8
        L_0x00ca:
            if (r10 == 0) goto L_0x00d5
            r10.close()     // Catch:{ all -> 0x00d0 }
            goto L_0x00d5
        L_0x00d0:
            r0 = move-exception
            r12 = r0
            r13.addSuppressed(r12)     // Catch:{ Exception -> 0x00b4 }
        L_0x00d5:
            throw r13     // Catch:{ Exception -> 0x00b4 }
        L_0x00d6:
            r0 = move-exception
            r2 = r12
            goto L_0x00b5
        L_0x00d9:
            java.lang.String r12 = r2.TAG
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            java.lang.String r0 = "unable to download item, e="
            r14.<init>(r0)
            A.a.s(r13, r14, r12)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.service.download.LTWDownloadTask.downloadItem(com.samsung.android.gallery.module.data.MediaItem, java.lang.String):boolean");
    }

    private String getNotificationTitle(MediaItem mediaItem) {
        return mediaItem.getDisplayName();
    }

    public boolean checkValidFileSize(String str, long j2, String str2, long j3) {
        if (j2 == j3 && j3 != 0) {
            return true;
        }
        Log.e(this.TAG, "file size check failed");
        StringBuilder sb2 = new StringBuilder("[file size check failed][s : ");
        sb2.append(Logger.getEncodedString(str));
        sb2.append(" : ");
        sb2.append(j2);
        sb2.append("][d : ");
        sb2.append(Logger.getEncodedString(str2));
        sb2.append(" : ");
        DebugLogger.getDeleteInstance().insertLog(C0212a.o(sb2, j3, "]"));
        return false;
    }

    public void download(List<MediaItem> list) {
        FileUtils.makeDirectoryIfAbsent(FileUtils.getDirectoryFromPath(this.mTargetAlbumPath, false));
        for (MediaItem next : list) {
            if (!this.mIsInterrupted) {
                this.mDownloadCount++;
                this.mNotificationTitle = getNotificationTitle(next);
                this.mNotificationMessage = getNotificationMessage();
                updateNotification(getPercentage());
                updateDownloadedCount(next.isVideo(), downloadInternal(next));
                this.mCurrentSize = getFileSize(next) + this.mCurrentSize;
            } else {
                return;
            }
        }
    }

    public String getDownloadFailToastMessage(Context context) {
        return LTWDownloadMsgMgr.getDownloadFailToastMessage(context, this.mErrorType, this.mImageFailCount, this.mVideoFailCount);
    }

    public String getDownloadSuccessToastMessage(Context context) {
        return LTWDownloadMsgMgr.getDownloadSuccessToastMessage(context, this.mImageSuccessCount, this.mVideoSuccessCount);
    }

    public NotificationCompat.Builder getStopBuilder(Context context, String str, String str2) {
        return this.mNotificationHelper.getStopBuilder(context, this.mNotificationId, str, str2, this.mTargetAlbumId);
    }

    public void interrupt() {
        super.interrupt();
        resetTimer();
    }

    public List<MediaItem> prepareDownload(MediaItem[] mediaItemArr) {
        ArrayList arrayList = new ArrayList();
        for (MediaItem mediaItem : mediaItemArr) {
            if (mediaItem != null) {
                addDownloadable(arrayList, mediaItem);
            }
        }
        this.mTotalCount = arrayList.size();
        if (arrayList.isEmpty()) {
            Log.e(this.TAG, "nothing added [" + this.mNotificationId + "][" + this.mImageFailCount + "][" + this.mVideoFailCount + "]");
        }
        return arrayList;
    }

    private void resetTimer() {
    }

    private void setTimer(String str) {
    }
}
