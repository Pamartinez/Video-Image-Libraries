package com.samsung.android.gallery.module.service.download;

import android.content.Context;
import android.os.AsyncTask;
import android.os.ResultReceiver;
import androidx.core.app.NotificationCompat;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.service.message.DownloadMsgMgr;
import com.samsung.android.gallery.module.service.notification.DownloadNotificationHelper;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import e5.C0451a;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DownloadTask extends AsyncTask<MediaItem[], Void, Void> {
    protected final String TAG = getClass().getSimpleName();
    protected NotificationCompat.Builder mBuilder;
    final WeakReference<Context> mContextRef;
    long mCurrentSize = 0;
    Callback mDownloadCallback;
    int mDownloadCount = 0;
    int mImageCount = 0;
    int mImageFailCount = 0;
    int mImageSuccessCount = 0;
    boolean mIsInterrupted;
    private boolean mIsProgressing = false;
    String mLocationKey;
    DownloadNotificationHelper mNotificationHelper;
    final int mNotificationId;
    String mNotificationMessage;
    String mNotificationTitle;
    private long mNotifyTime = -1;
    ResultReceiver mResultReceiver;
    int mTotalCount = 0;
    long mTotalSize = 0;
    int mVideoCount = 0;
    int mVideoFailCount = 0;
    int mVideoSuccessCount = 0;

    public DownloadTask(Context context) {
        this.mContextRef = new WeakReference<>(context);
        this.mNotificationId = System.identityHashCode(this);
    }

    private void showLastNotification(Context context, String str, String str2) {
        NotificationCompat.Builder stopBuilder = getStopBuilder(context, str, str2);
        this.mBuilder = stopBuilder;
        this.mNotificationHelper.notify(this.mNotificationId, stopBuilder.build());
    }

    private void showNotification() {
        Context context = getContext();
        if (context != null) {
            NotificationCompat.Builder startBuilder = this.mNotificationHelper.getStartBuilder(context, this.mNotificationId);
            this.mBuilder = startBuilder;
            this.mNotificationHelper.notify(this.mNotificationId, startBuilder.build());
        }
    }

    /* access modifiers changed from: private */
    public void showStartMessage() {
        Context context = getContext();
        if (context != null) {
            Utils.showToast(context, DownloadMsgMgr.getDownloadStartToastMessage(context, this.mImageCount, this.mVideoCount));
        }
    }

    private void showStopMessage(boolean z) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        if (z) {
            String downloadSuccessToastMessage = getDownloadSuccessToastMessage(context);
            Utils.showToast(context, downloadSuccessToastMessage);
            showLastNotification(context, downloadSuccessToastMessage, (String) null);
            return;
        }
        Utils.showToast(context, getDownloadFailToastMessage(context));
        String[] downloadFailNotificationMessage = DownloadMsgMgr.getDownloadFailNotificationMessage(context, this.mImageFailCount, this.mVideoFailCount);
        showLastNotification(context, downloadFailNotificationMessage[0], downloadFailNotificationMessage[1]);
    }

    public abstract void download(List<MediaItem> list);

    public Context getContext() {
        return this.mContextRef.get();
    }

    public abstract String getDownloadFailToastMessage(Context context);

    public abstract String getDownloadSuccessToastMessage(Context context);

    public long getFileSize(MediaItem mediaItem) {
        if (mediaItem.getFileSize() > 0) {
            return mediaItem.getFileSize();
        }
        return mediaItem.getCloudOriginalSize();
    }

    public int getNotificationId() {
        return this.mNotificationId;
    }

    public String getNotificationMessage() {
        int i2;
        int i7 = this.mTotalCount;
        if (i7 == this.mImageCount) {
            i2 = 0;
        } else if (i7 == this.mVideoCount) {
            i2 = 1;
        } else {
            i2 = 2;
        }
        return DownloadMsgMgr.getDownloadProgressNotificationMessage(getContext(), i2, this.mDownloadCount, this.mTotalCount);
    }

    public int getPercentage() {
        long j2 = this.mTotalSize;
        if (j2 > 0) {
            return (int) ((this.mCurrentSize * 100) / j2);
        }
        return (this.mDownloadCount * 100) / this.mTotalCount;
    }

    public Collection<MediaItem> getRemains() {
        return new ArrayList();
    }

    public abstract NotificationCompat.Builder getStopBuilder(Context context, String str, String str2);

    public void interrupt() {
        Log.d(this.TAG, "interrupt", Integer.valueOf(this.mNotificationId));
        this.mIsInterrupted = true;
        this.mIsProgressing = false;
        this.mNotificationHelper.dismiss(this.mNotificationId);
        this.mDownloadCallback.onDownloadInterrupted(this.mNotificationId);
    }

    public boolean isProgressing() {
        return this.mIsProgressing;
    }

    public void onPreExecute() {
        Log.d(this.TAG, "started", Integer.valueOf(this.mNotificationId));
        this.mIsProgressing = true;
    }

    public abstract List<MediaItem> prepareDownload(MediaItem[] mediaItemArr);

    public void progress(long j2, long j3) {
        long j8 = this.mTotalSize;
        if (j8 > 0) {
            updateNotification((int) (((this.mCurrentSize + j2) * 100) / j8));
        }
    }

    public DownloadTask setCallback(Callback callback) {
        this.mDownloadCallback = callback;
        return this;
    }

    public DownloadTask setLocationKey(String str) {
        this.mLocationKey = str;
        return this;
    }

    public DownloadTask setNotificationHelper(DownloadNotificationHelper downloadNotificationHelper) {
        this.mNotificationHelper = downloadNotificationHelper;
        return this;
    }

    public DownloadTask setResultReceiver(ResultReceiver resultReceiver) {
        this.mResultReceiver = resultReceiver;
        return this;
    }

    public void updateDownloadedCount(boolean z, boolean z3) {
        if (z3) {
            if (z) {
                this.mVideoSuccessCount++;
            } else {
                this.mImageSuccessCount++;
            }
        } else if (z) {
            this.mVideoFailCount++;
        } else {
            this.mImageFailCount++;
        }
    }

    public void updateNotification(int i2) {
        if (this.mNotificationHelper.isUpdatable(this.mNotifyTime, i2)) {
            this.mNotifyTime = System.currentTimeMillis();
            this.mBuilder.setContentTitle(this.mNotificationTitle).setStyle(this.mNotificationHelper.getBigText(this.mNotificationMessage)).setProgress(100, i2, false);
            this.mNotificationHelper.notify(this.mNotificationId, this.mBuilder.build());
        }
    }

    public Void doInBackground(MediaItem[]... mediaItemArr) {
        List<MediaItem> prepareDownload = prepareDownload(mediaItemArr[0]);
        if (prepareDownload.isEmpty()) {
            return null;
        }
        this.mDownloadCallback.onDownloadStart(this.mNotificationId);
        ThreadUtil.postOnUiThread(new C0451a(6, this));
        showNotification();
        download(prepareDownload);
        return null;
    }

    public void onPostExecute(Void voidR) {
        boolean z = this.mImageSuccessCount + this.mVideoSuccessCount > 0;
        Log.d(this.TAG, "finished", Integer.valueOf(this.mNotificationId), Boolean.valueOf(z));
        this.mIsProgressing = false;
        showStopMessage(z);
        this.mDownloadCallback.onDownloadEnd(this.mNotificationId, z);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Callback {
        void onDownloadEnd(int i2, boolean z);

        void onDownloadProgress(int i2);

        void onDownloadStart(int i2);

        void onDownloadInterrupted(int i2) {
        }
    }
}
