package com.samsung.android.gallery.app.service;

import A.a;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.app.service.abstraction.AbsProgressService;
import com.samsung.android.gallery.module.clipboard.ClipboardPasteHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.service.abstraction.ProgressJob;
import com.samsung.android.gallery.module.service.message.FileOperationMsgMgr;
import com.samsung.android.gallery.module.service.message.FileOperationMsgParams;
import com.samsung.android.gallery.support.utils.FileNameBuilder;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.MediaScannerListener;
import com.samsung.android.gallery.support.utils.MemoryUtils;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import t1.C0280e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PasteMultiControlService extends AbsProgressService {
    private byte mClipId;
    private int mDeviceId;
    private String mDialogTitle = null;
    private boolean mIsLowStorage = false;
    private long mOperatedSize = 0;
    private int mProgressCount = 0;
    private String mTargetAlbumPath;
    private ClipboardPasteHelper.PasteResult mTempResult = null;
    private long mTempSize = 0;
    private int mTotalCount = 0;
    private long mTotalSize = 0;

    public PasteMultiControlService() {
        super("PasteMultiControlService", "com.samsung.android.gallery.app.service.PasteMultiControlService");
    }

    private void checkPasteState() {
        try {
            ClipboardPasteHelper.PasteResult pasteResult = ClipboardPasteHelper.getPasteResult(this, this.mClipId, this.mDeviceId);
            this.mTempResult = pasteResult;
            if (pasteResult != null && !pasteResult.isSuccess()) {
                clearQueue();
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("unable to get result, e="), "PasteMultiControlService");
        }
    }

    private boolean checkValidFileSize(long j2, long j3, String str) {
        if (j2 == j3 && j3 != 0) {
            return true;
        }
        Log.e((CharSequence) "PasteMultiControlService", "invalid file size", Long.valueOf(j2), Long.valueOf(j3));
        deleteTempFile(str);
        checkPasteState();
        return false;
    }

    private void copyMCFItem(MediaItem mediaItem, String str) {
        String str2;
        InputStream openInputStream;
        Throwable th;
        FileOutputStream fileOutputStream;
        Throwable th2;
        if (FileUtils.exists(str)) {
            str2 = new FileNameBuilder(str).buildUnique();
        } else {
            str2 = str;
        }
        SecureFile secureFile = new SecureFile(str2);
        String path = mediaItem.getPath();
        long fileSize = mediaItem.getFileSize();
        this.mTempSize = 0;
        Log.p("PasteMultiControlService", "copyMCFItem::openInputStream() start");
        long currentTimeMillis = System.currentTimeMillis();
        try {
            openInputStream = getContentResolver().openInputStream(Uri.parse(path));
            fileOutputStream = new FileOutputStream(secureFile);
            Log.p("PasteMultiControlService", "copyMCFItem::openInputStream() elapsed +" + (System.currentTimeMillis() - currentTimeMillis));
            if (openInputStream == null) {
                Log.e("PasteMultiControlService", "copyMCFItem::failed. null input-stream");
                fileOutputStream.close();
                if (openInputStream == null) {
                    return;
                }
            } else {
                byte[] bArr = new byte[2048];
                while (true) {
                    int read = openInputStream.read(bArr);
                    if (read <= -1) {
                        this.mTempSize = 0;
                        this.mOperatedSize += fileSize;
                        SecureFile secureFile2 = secureFile;
                        if (checkValidFileSize(fileSize, secureFile2.length(), secureFile2.getPath())) {
                            MediaScanner.scanFile(str2, (MediaScannerListener) null);
                        }
                    } else if (isInterrupted()) {
                        Log.e("PasteMultiControlService", "copyMCFItem::failed. interrupted");
                        break;
                    } else {
                        fileOutputStream.write(bArr, 0, read);
                        this.mTempSize = Math.min(fileSize, this.mTempSize + ((long) read));
                        updateDialogAndNotification();
                    }
                }
                fileOutputStream.close();
            }
            openInputStream.close();
            return;
            throw th;
            throw th2;
        } catch (Exception e) {
            Log.e((CharSequence) "PasteMultiControlService", "unable to copyMCFItem", (Throwable) e);
        } catch (Throwable th3) {
            th.addSuppressed(th3);
        }
    }

    private void deleteTempFile(String str) {
        boolean z;
        SecureFile secureFile = new SecureFile(str);
        boolean exists = secureFile.exists();
        if (exists) {
            z = secureFile.delete();
        } else {
            z = false;
        }
        Log.w("PasteMultiControlService", "delete temp file [" + exists + "][" + z + "]");
    }

    private long getAvailableStorage() {
        String storageName = FileUtils.getStorageName(this.mTargetAlbumPath);
        return Math.max(MemoryUtils.getAvailableStorageSize(MemoryUtils.getStorageState(storageName), storageName) - 9437184, 0);
    }

    private String getNotificationMessage() {
        return FileOperationMsgMgr.getNotificationMessage(this, FileOperationMsgParams.builder().setIsCopy(true).setOperateCount(this.mProgressCount).setTotalCount(this.mTotalCount).build());
    }

    private void updateDialogAndNotification() {
        this.mDialogHelper.updateDialog(this.mProgressCount, this.mTotalCount, getPercentage());
        updateNotification();
    }

    public boolean addJobs(Intent intent) {
        MediaItem[] mediaItemArr = (MediaItem[]) this.mBlackboard.pop("data://user/selected");
        if (mediaItemArr == null || mediaItemArr.length == 0) {
            Log.w("PasteMultiControlService", "items are empty. adding failed.");
            return false;
        }
        for (MediaItem mediaItem : mediaItemArr) {
            if (mediaItem != null) {
                addToQueue(new ProgressJob(mediaItem));
                this.mTotalSize = mediaItem.getFileSize() + this.mTotalSize;
            }
        }
        if (getAvailableStorage() < this.mTotalSize) {
            Log.w("PasteMultiControlService", "low storage. adding failed.");
            this.mIsLowStorage = true;
            return false;
        } else if (isQueueEmpty()) {
            Log.w("PasteMultiControlService", "queue is empty. adding failed.");
            return false;
        } else {
            this.mTotalCount = getQueueSize();
            this.mDialogTitle = FileOperationMsgMgr.getDialogTitle(this, this.mTargetAlbumPath, true, false, (FileOperationMsgParams) null);
            return true;
        }
    }

    public void doJob(ProgressJob progressJob) {
        try {
            this.mProgressCount++;
            MediaItem mediaItem = (MediaItem) progressJob.getParam(0);
            String displayName = mediaItem.getDisplayName();
            this.mNotificationTitle = displayName;
            this.mNotificationMessage = getNotificationMessage();
            updateDialogAndNotification();
            Log.d("PasteMultiControlService", "do job [" + this.mProgressCount + "][" + this.mTotalCount + "]");
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.mTargetAlbumPath);
            sb2.append(File.separator);
            sb2.append(displayName);
            copyMCFItem(mediaItem, sb2.toString());
        } catch (Exception e) {
            Log.e("PasteMultiControlService", "do job failed, e=" + e.getMessage());
        } catch (Throwable th) {
            ((com.samsung.android.sdk.scs.ai.language.a) this.mJobCallback).a();
            throw th;
        }
        ((com.samsung.android.sdk.scs.ai.language.a) this.mJobCallback).a();
    }

    public String getChannelName() {
        return FileOperationMsgMgr.getServiceName(this, true, false);
    }

    public int getPercentage() {
        long j2 = this.mTotalSize;
        if (j2 == 0) {
            return 0;
        }
        if (this.mOperatedSize > j2) {
            this.mOperatedSize = j2;
        }
        return (int) (((this.mOperatedSize + this.mTempSize) * 100) / j2);
    }

    public void onCleanInternal() {
        ClipboardPasteHelper.PasteResult pasteResult;
        String str;
        this.mDialogHelper.dismissDialog();
        if (this.mIsLowStorage) {
            Utils.showToast((Context) this, getString(R.string.not_enough_space));
        } else if (!isInterrupted() && (pasteResult = this.mTempResult) != null && !pasteResult.isSuccess()) {
            if (TextUtils.isEmpty(this.mTempResult.getResult())) {
                str = getString(R.string.there_is_no_media_file_to_paste);
            } else {
                str = this.mTempResult.getResult();
            }
            Utils.showToast((Context) this, str);
        }
        super.onCleanInternal();
    }

    public void onContinueInternal() {
        this.mDialogHelper.showDialog(this.mDialogTitle, this.mProgressCount, this.mTotalCount, getPercentage());
    }

    public void onEndInternal() {
        super.onEndInternal();
        checkPasteState();
    }

    public boolean onPrepareInternal(Intent intent) {
        String stringExtra = intent.getStringExtra("target_album_path");
        this.mTargetAlbumPath = stringExtra;
        if (stringExtra != null && stringExtra.charAt(stringExtra.length() - 1) == File.separatorChar) {
            this.mTargetAlbumPath = C0280e.d(1, 0, this.mTargetAlbumPath);
        }
        this.mClipId = intent.getByteExtra("clip_id", (byte) 0);
        this.mDeviceId = intent.getIntExtra("device_id", -1);
        return super.onPrepareInternal(intent);
    }

    public void onStartInternal() {
        super.onStartInternal();
        this.mDialogHelper.showDialog(this.mDialogTitle, 1, this.mTotalCount, getPercentage());
    }
}
