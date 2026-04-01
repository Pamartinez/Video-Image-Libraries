package com.samsung.android.gallery.app.controller.abstraction;

import A.a;
import Ba.f;
import D7.g;
import Fa.C0571z;
import G3.b;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.app.controller.DialogTask;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.cloud.abstraction.CloudDownloadMonitor;
import com.samsung.android.gallery.module.cloud.abstraction.DownloadParams;
import com.samsung.android.gallery.module.cloud.sdk.CloudErrorType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.data.UriItemLoader;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.module.service.download.DownloadSyncMgr;
import com.samsung.android.gallery.module.service.message.DownloadMsgMgr;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MediaHelper;
import com.samsung.android.gallery.support.utils.StringResources;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.dialog.ProgressCircleBuilder;
import com.sec.android.gallery3d.R;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ViewerDownloadTask extends DialogTask {
    private static final String DOWNLOAD_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
    protected final Blackboard mBlackboard;
    protected final DownloadSyncMgr mDownloadSyncManager;
    protected CloudErrorType mErrorType = CloudErrorType.None;
    protected final Consumer<Object> mListener;
    private long mPreparedElapsed;
    private Dialog mProgressDialog;
    protected final DownloadType mType;

    public ViewerDownloadTask(Blackboard blackboard, Consumer<Object> consumer, DownloadType downloadType, DownloadSyncMgr downloadSyncMgr) {
        this.mListener = consumer;
        this.mType = downloadType;
        this.mDownloadSyncManager = downloadSyncMgr;
        this.mBlackboard = blackboard;
    }

    private void handleFailed(Context context) {
        if (context != null) {
            boolean isImage = getBaseMediaItem().isImage();
            if (this.mErrorType == CloudErrorType.GDPR) {
                SamsungCloudCompat.changeSyncState(context, false);
                if (this.mType == DownloadType.EDIT) {
                    showToast(context, context.getString(R.string.can_not_edit_image_gdpr, new Object[]{StringResources.getCloudBrand()}));
                    return;
                }
            }
            showToast(context, DownloadMsgMgr.getDownloadFailToastMessage(context, this.mErrorType, isImage ? 1 : 0, isImage ^ true ? 1 : 0));
            return;
        }
        Log.e(this.TAG, "handle failed. null context");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$loadAndCheckResult$2(MediaItem mediaItem) {
        if (mediaItem.isUriItem() || mediaItem.isCloudOnly()) {
            return true;
        }
        if (mediaItem.getPath() == null || !mediaItem.getPath().startsWith("/data/sec/cloud")) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPostExecute$0() {
        try {
            this.mProgressDialog.dismiss();
            notifyResult();
        } catch (IllegalArgumentException e) {
            String str = this.TAG;
            Log.e(str, "onPostExecute failed. e=" + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareDownloadedInternal$3(long j2, MediaItem mediaItem, Bitmap bitmap) {
        this.mPreparedElapsed = System.currentTimeMillis() - j2;
        String str = this.TAG;
        Log.d(str, "prepareDownloaded " + MediaItemUtil.getDebugLog(mediaItem) + " " + Logger.toString(bitmap) + " +" + this.mPreparedElapsed);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$processDownloadItem$1(AtomicInteger atomicInteger, long j2, long j3, long j8) {
        int i2 = (int) ((((float) j3) * 100.0f) / ((float) j8));
        if (i2 - atomicInteger.get() >= 25 || i2 > 99) {
            atomicInteger.set(i2);
            String str = this.TAG;
            StringBuilder sb2 = new StringBuilder("downloading");
            a.A(new Object[]{i2 + "%", Long.valueOf(j8), Long.valueOf(j2)}, sb2, str);
        }
    }

    public abstract boolean checkDownloaded(ArrayList<Uri> arrayList);

    public final ArrayList<Uri> download(Context context, MediaItem mediaItem, String str, CloudDownloadMonitor cloudDownloadMonitor) {
        return SamsungCloudCompat.download(context, DownloadParams.builder().setFileItemInterface(mediaItem).setTargetPath(str).setDownloadCanceller(SamsungCloudCompat.getDownloadCanceller()).setDownloadMonitor(cloudDownloadMonitor).build());
    }

    public abstract MediaItem getBaseMediaItem();

    public final String getTargetPath(MediaItem mediaItem) {
        String originalFilePath = SamsungCloudCompat.getOriginalFilePath(mediaItem.getCloudServerPath());
        if (originalFilePath != null) {
            return originalFilePath;
        }
        Log.d(this.TAG, "cloud server path is null. set default download folder");
        return DOWNLOAD_PATH + File.separator + mediaItem.getTitle();
    }

    public abstract boolean isDownloaded();

    public final void loadAndCheckResult(Context context, ArrayList<Uri> arrayList) {
        if (arrayList != null && !arrayList.isEmpty() && checkDownloaded(arrayList)) {
            ArrayList arrayList2 = new ArrayList();
            for (int i2 = 0; i2 < 3; i2++) {
                if (UriItemLoader.loadMediaItemFromUris(arrayList, arrayList2)) {
                    if (arrayList2.stream().noneMatch(new C0571z(3))) {
                        Log.d(this.TAG, "success downloaded", Integer.valueOf(i2));
                        onDownLoadedItems(arrayList2);
                        return;
                    }
                    Log.d(this.TAG, "download fail", Integer.valueOf(i2));
                    arrayList2.clear();
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException unused) {
                }
            }
        }
    }

    public abstract void notifyResult();

    public abstract void onDownLoadedItems(ArrayList<MediaItem> arrayList);

    public void onPreExecute() {
        int i2;
        Activity readActivity = BlackboardUtils.readActivity(this.mBlackboard);
        if (readActivity != null) {
            ProgressCircleBuilder progressCircleBuilder = new ProgressCircleBuilder(readActivity);
            if (getBaseMediaItem().isVideo()) {
                i2 = R.string.downloading_video;
            } else {
                i2 = R.string.downloading_image;
            }
            AlertDialog create = progressCircleBuilder.setProgressMessage(readActivity.getString(i2)).setTextColor(R.color.progress_avd_message_text_color).setLightTheme(true).create();
            this.mProgressDialog = create;
            create.show();
        }
    }

    public abstract boolean prepareDownloaded();

    public final void prepareDownloadedInternal(MediaItem mediaItem) {
        long currentTimeMillis = System.currentTimeMillis();
        if ((mediaItem.getWidth() <= 0 || mediaItem.getHeight() <= 0) && mediaItem.getPath() != null) {
            if (mediaItem.isImage()) {
                BitmapOptions parse = BitmapOptionsFactory.parse(mediaItem.getPath());
                mediaItem.setSize(parse.outWidth, parse.outHeight);
            } else {
                MediaHelper.VideoInfo videoInfo = MediaHelper.getVideoInfo(mediaItem.getPath());
                mediaItem.setSize(videoInfo.width, videoInfo.height);
                mediaItem.setOrientation(videoInfo.orientation);
            }
        }
        MediaItem mediaItem2 = mediaItem;
        ThumbnailLoader.getInstance().loadThumbnailSync(mediaItem2, ThumbKind.MEDIUM_KIND, new f((Object) this, currentTimeMillis, mediaItem2, 1), false);
    }

    public abstract ArrayList<Uri> processDownload(Context context, long j2);

    public final ArrayList<Uri> processDownloadItem(Context context, MediaItem mediaItem, long j2) {
        String targetPath = getTargetPath(mediaItem);
        this.mDownloadSyncManager.add(mediaItem.getFileId());
        CloudDownloadMonitor downloadMonitor = SamsungCloudCompat.getDownloadMonitor();
        if (downloadMonitor != null) {
            downloadMonitor.addProgressListener(mediaItem.getCloudServerId(), new b(this, new AtomicInteger(0), j2));
        }
        ArrayList<Uri> download = download(context, mediaItem, targetPath, downloadMonitor);
        this.mDownloadSyncManager.remove(mediaItem.getFileId());
        return download;
    }

    public Void doInBackground(Void... voidArr) {
        ArrayList<Uri> arrayList;
        Context appContext = AppResources.getAppContext();
        if (appContext == null) {
            Log.e(this.TAG, "download failed. null context");
            return null;
        }
        BlackboardUtils.collectExternalDataChangedEvent(this.mBlackboard, true);
        try {
            arrayList = processDownload(appContext, System.currentTimeMillis());
            try {
                loadAndCheckResult(appContext, arrayList);
                prepareDownloaded();
            } catch (Exception e) {
                e = e;
                try {
                    Log.e(this.TAG, "download failed e=" + e.getMessage());
                    BlackboardUtils.collectExternalDataChangedEvent(this.mBlackboard, false);
                    this.mBlackboard.post("command://event/DataDirty", (Object) null);
                    BlackboardUtils.forceRefreshPicturesData(this.mBlackboard, false);
                    return null;
                } catch (Throwable th) {
                    BlackboardUtils.collectExternalDataChangedEvent(this.mBlackboard, false);
                    this.mBlackboard.post("command://event/DataDirty", (Object) null);
                    throw th;
                }
            }
        } catch (Exception e7) {
            e = e7;
            arrayList = null;
            Log.e(this.TAG, "download failed e=" + e.getMessage());
            BlackboardUtils.collectExternalDataChangedEvent(this.mBlackboard, false);
            this.mBlackboard.post("command://event/DataDirty", (Object) null);
            BlackboardUtils.forceRefreshPicturesData(this.mBlackboard, false);
            return null;
        }
        BlackboardUtils.collectExternalDataChangedEvent(this.mBlackboard, false);
        this.mBlackboard.post("command://event/DataDirty", (Object) null);
        if (arrayList != null && !arrayList.isEmpty()) {
            BlackboardUtils.forceRefreshPicturesData(this.mBlackboard, false);
        }
        return null;
    }

    public void onPostExecute(Void voidR) {
        if (isDownloaded()) {
            ThreadUtil.postOnUiThreadDelayed(new g(20, this), SdkConfig.atLeast(SdkConfig.GED.R) ? Math.max(2000 - this.mPreparedElapsed, 1000) : 0);
            return;
        }
        try {
            this.mProgressDialog.dismiss();
            handleFailed(BlackboardUtils.readActivity(this.mBlackboard));
        } catch (IllegalArgumentException e) {
            String str = this.TAG;
            Log.e(str, "onPostExecute failed. e=" + e.getMessage());
        }
    }
}
