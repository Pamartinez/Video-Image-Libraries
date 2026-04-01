package com.samsung.android.gallery.module.service.download;

import A.a;
import A4.I;
import Fa.C0563q;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import androidx.core.app.NotificationCompat;
import c0.C0086a;
import com.samsung.android.gallery.module.R$dimen;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.cloud.abstraction.CloudDownloadMonitor;
import com.samsung.android.gallery.module.cloud.abstraction.DownloadCanceller;
import com.samsung.android.gallery.module.cloud.abstraction.DownloadParams;
import com.samsung.android.gallery.module.cloud.sdk.CloudErrorType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.graphics.ImageDecoder;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.service.message.DownloadMsgMgr;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.settings.activity.e;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CloudDownloadTask extends DownloadTask {
    private static final String DOWNLOAD_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
    private final DownloadCanceller mCanceller = SamsungCloudCompat.getDownloadCanceller();
    private final ArrayList<Uri> mDownloadedUris = new ArrayList<>();
    private CloudErrorType mErrorType = CloudErrorType.None;
    protected Bitmap mIcon = null;
    ConcurrentLinkedQueue<MediaItem> mItems = new ConcurrentLinkedQueue<>();
    private final DownloadSyncMgr mManager = new DownloadSyncMgr();
    private final CloudDownloadMonitor mMonitor = SamsungCloudCompat.getDownloadMonitor();

    public CloudDownloadTask(Context context) {
        super(context);
    }

    private Bitmap getImageThumbnail(String str, int i2, boolean z) {
        try {
            float size = ((float) ThumbKind.SMALL_CROP_KIND.size()) / ((float) getContext().getResources().getDimensionPixelSize(R$dimen.sharing_noti_layout_item_image_size));
            BitmapOptions withQuramCodec = new BitmapOptions().withQuramCodec(z);
            withQuramCodec.inSampleSize = BitmapUtils.computeSampleSizeLarger(size);
            Bitmap decodeFile = ImageDecoder.decodeFile(str, withQuramCodec);
            if (decodeFile == null) {
                return null;
            }
            if (i2 != 0) {
                return BitmapUtils.rotateBitmap(decodeFile, i2);
            }
            return decodeFile;
        } catch (Exception e) {
            a.s(e, new StringBuilder("unable to get thumbnail, e="), this.TAG);
            return null;
        }
    }

    private String getNotificationTitle(MediaItem mediaItem) {
        return mediaItem.getTitle();
    }

    private boolean isIconPrepared() {
        Bitmap bitmap = this.mIcon;
        if (bitmap == null || bitmap.isRecycled() || this.mIcon.getWidth() <= 0 || this.mIcon.getHeight() <= 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$downloadInternal$1(MediaItem mediaItem, CloudDownloadMonitor cloudDownloadMonitor) {
        cloudDownloadMonitor.addProgressListener(mediaItem.getCloudServerId(), new ea.a(this));
    }

    private void makeIcon(String str, MediaItem mediaItem) {
        Bitmap bitmap;
        if (mediaItem.isVideo()) {
            bitmap = BitmapUtils.getVideoThumbnailFromMeta(str);
        } else {
            bitmap = getImageThumbnail(str, mediaItem.getOrientation(), mediaItem.isQuramDecodable());
        }
        this.mIcon = bitmap;
    }

    private void sendTaskState(int i2, int i7, boolean z) {
        Bundle bundle;
        if (this.mResultReceiver != null) {
            if (i2 != 0) {
                bundle = new Bundle();
                bundle.putInt("download_count", i7);
                bundle.putBoolean("result", z);
            } else {
                bundle = null;
            }
            this.mResultReceiver.send(i2, bundle);
        }
    }

    public void download(List<MediaItem> list) {
        MediaItem peek;
        long currentTimeMillis = System.currentTimeMillis();
        this.mItems.addAll(list);
        while (true) {
            if (this.mIsInterrupted || (peek = this.mItems.peek()) == null) {
                break;
            }
            this.mDownloadCount++;
            this.mNotificationTitle = getNotificationTitle(peek);
            this.mNotificationMessage = getNotificationMessage();
            updateNotification(getPercentage());
            if (isValid(peek)) {
                long currentTimeMillis2 = System.currentTimeMillis();
                long fileId = peek.getFileId();
                this.mManager.add(fileId);
                boolean downloadInternal = downloadInternal(peek);
                this.mManager.remove(fileId);
                if (!downloadInternal && this.mIsInterrupted) {
                    Log.w(this.TAG, "download cancelled by interrupt");
                    break;
                }
                updateDownloadedCount(peek.isVideo(), downloadInternal);
                Log.d(this.TAG, "download item" + Logger.vt(Boolean.valueOf(downloadInternal), Long.valueOf(fileId), peek.getCloudServerId(), Long.valueOf(currentTimeMillis2)) + " " + this);
            } else {
                updateDownloadedCount(peek.isVideo(), true);
                Log.w(this.TAG, "download skip {T," + peek.getFileId() + "} " + this);
            }
            this.mItems.poll();
            this.mCurrentSize = getFileSize(peek) + this.mCurrentSize;
            this.mDownloadCallback.onDownloadProgress(this.mNotificationId);
        }
        if (this.mIsInterrupted) {
            Log.e(this.TAG, "download interrupted" + Logger.vt(currentTimeMillis) + " " + this);
            return;
        }
        Log.d(this.TAG, "download completed" + Logger.vt(currentTimeMillis) + " " + this);
    }

    public boolean downloadInternal(MediaItem mediaItem) {
        String originalFilePath = SamsungCloudCompat.getOriginalFilePath(mediaItem.getCloudServerPath());
        if (originalFilePath == null) {
            Log.w(this.TAG, "cloud server path is null. set default download folder [" + this.mNotificationId + "]");
            originalFilePath = DOWNLOAD_PATH + File.separator + mediaItem.getTitle();
        }
        Optional.ofNullable(this.mMonitor).ifPresent(new e(14, this, mediaItem));
        boolean isDownloaded = isDownloaded(SamsungCloudCompat.download(getContext(), DownloadParams.builder().setFileItemInterface(mediaItem).setTargetPath(originalFilePath).setDownloadCanceller(this.mCanceller).setDownloadMonitor(this.mMonitor).build()));
        updateState(isDownloaded, originalFilePath, mediaItem);
        return isDownloaded;
    }

    public boolean filter(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.isCloudOnly()) {
            return false;
        }
        if (mediaItem.getCloudServerId() != null) {
            return true;
        }
        if (mediaItem.isVideo()) {
            this.mVideoFailCount++;
        } else {
            this.mImageFailCount++;
        }
        return false;
    }

    public String getDownloadFailToastMessage(Context context) {
        return DownloadMsgMgr.getDownloadFailToastMessage(context, this.mErrorType, this.mImageFailCount, this.mVideoFailCount);
    }

    public String getDownloadSuccessToastMessage(Context context) {
        return DownloadMsgMgr.getDownloadSuccessToastMessage(context, this.mImageSuccessCount, this.mVideoSuccessCount);
    }

    public long getFileSize(MediaItem mediaItem) {
        if (Features.isEnabled(Features.SUPPORT_NEW_SAMSUNG_CLOUD)) {
            return 100;
        }
        return super.getFileSize(mediaItem);
    }

    public Collection<MediaItem> getRemains() {
        return this.mItems;
    }

    public final String getScreenId() {
        String str = this.mLocationKey;
        if (str == null) {
            return null;
        }
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -1327340640:
                if (str.equals("location://albums/fileList")) {
                    c5 = 0;
                    break;
                }
                break;
            case 263612166:
                if (str.equals("location://timeline")) {
                    c5 = 1;
                    break;
                }
                break;
            case 2140179036:
                if (str.equals("location://search/fileList")) {
                    c5 = 2;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return AnalyticsScreenId.SCREEN_SPLIT_VIEW_EDIT.toString();
            case 1:
                return AnalyticsScreenId.SCREEN_TIME_VIEW_EDIT.toString();
            case 2:
                return AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_RESULT_EDIT.toString();
            default:
                if (!this.mLocationKey.contains("viewer")) {
                    return null;
                }
                if (this.mVideoCount == 0) {
                    return AnalyticsScreenId.SCREEN_DETAIL_VIEW_PICTURE.toString();
                }
                return AnalyticsScreenId.SCREEN_DETAIL_VIEW_VIDEO.toString();
        }
    }

    public NotificationCompat.Builder getStopBuilder(Context context, String str, String str2) {
        return this.mNotificationHelper.getStopBuilder(context, this.mNotificationId, str, str2, this.mDownloadedUris, this.mIcon);
    }

    public void interrupt() {
        super.interrupt();
        this.mCanceller.onCancel(getContext());
        sendTaskState(2, this.mImageSuccessCount + this.mVideoSuccessCount, false);
        postAnalyticsLog();
    }

    public boolean isDownloaded(ArrayList<Uri> arrayList) {
        if (arrayList != null && !arrayList.isEmpty()) {
            Uri uri = arrayList.get(0);
            CloudErrorType parseOf = CloudErrorType.parseOf(uri);
            CloudErrorType cloudErrorType = this.mErrorType;
            CloudErrorType cloudErrorType2 = CloudErrorType.None;
            if (cloudErrorType == cloudErrorType2) {
                this.mErrorType = parseOf;
            }
            if (parseOf == cloudErrorType2) {
                if (this.mDownloadedUris.size() > 500) {
                    return true;
                }
                this.mDownloadedUris.add(uri);
                return true;
            }
        }
        return false;
    }

    public boolean isValid(MediaItem mediaItem) {
        return this.mManager.valid(mediaItem.getFileId());
    }

    public void onPreExecute() {
        super.onPreExecute();
        sendTaskState(0, 0, false);
    }

    public final void postAnalyticsLog() {
        String screenId = getScreenId();
        if (screenId != null) {
            AnalyticsLogger.getInstance().postLog(screenId, AnalyticsEventId.EVENT_NOTIFICATION_CANCEL.toString());
        }
    }

    public List<MediaItem> prepareDownload(MediaItem[] mediaItemArr) {
        List<MediaItem> flatten = MediaItemLoader.flatten(mediaItemArr, (Predicate<MediaItem>) new I(27, this));
        this.mTotalCount = flatten.size();
        int count = (int) flatten.stream().filter(new com.samsung.android.gallery.module.dynamicview.a(22)).count();
        this.mVideoCount = count;
        this.mImageCount = this.mTotalCount - count;
        this.mTotalSize = flatten.stream().mapToLong(new C0563q(2, this)).sum();
        if (flatten.isEmpty()) {
            Log.e(this.TAG, "prepare skip. empty " + this);
            if (this.mImageFailCount == 0 && this.mVideoFailCount == 0) {
                this.mErrorType = CloudErrorType.NotStart;
            }
            return flatten;
        }
        String str = this.TAG;
        Integer valueOf = Integer.valueOf(this.mNotificationId);
        StringBuilder sb2 = new StringBuilder("[IN=");
        sb2.append(mediaItemArr.length);
        sb2.append(",T=");
        sb2.append(this.mTotalCount);
        sb2.append(",i=");
        sb2.append(this.mImageCount);
        sb2.append(",v=");
        StringBuilder sb3 = new StringBuilder("SKIP[i=");
        sb3.append(this.mImageFailCount);
        sb3.append(",v=");
        Log.d(str, "prepare", valueOf, C0086a.l(sb2, this.mVideoCount, "]"), "size=" + this.mTotalSize, C0086a.l(sb3, this.mVideoFailCount, "]"));
        return flatten;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("Task@");
        sb2.append(this.mNotificationId);
        sb2.append("{");
        sb2.append(this.mTotalCount);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mDownloadCount);
        sb2.append(",STAT[i=");
        sb2.append(this.mImageSuccessCount);
        sb2.append(",v=");
        sb2.append(this.mVideoSuccessCount);
        sb2.append(",fi=");
        sb2.append(this.mImageFailCount);
        sb2.append(",fv=");
        return C0086a.l(sb2, this.mVideoFailCount, "]}");
    }

    public final void updateState(boolean z, String str, MediaItem mediaItem) {
        boolean z3;
        if (!z) {
            SecureFile secureFile = new SecureFile(str);
            boolean exists = secureFile.exists();
            if (exists) {
                z3 = secureFile.delete();
            } else {
                z3 = false;
            }
            Log.w((CharSequence) this.TAG, "delete temp file", Boolean.valueOf(exists), Boolean.valueOf(z3));
        } else if (!isIconPrepared()) {
            makeIcon(str, mediaItem);
        }
    }

    public void onPostExecute(Void voidR) {
        super.onPostExecute(voidR);
        int i2 = this.mImageSuccessCount;
        int i7 = this.mVideoSuccessCount;
        sendTaskState(1, i2 + i7, i2 + i7 > 0);
    }
}
