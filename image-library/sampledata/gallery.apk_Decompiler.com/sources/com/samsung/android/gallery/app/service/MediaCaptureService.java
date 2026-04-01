package com.samsung.android.gallery.app.service;

import A.a;
import C3.C0391a;
import H3.n;
import N2.j;
import U5.b;
import W8.C0579a;
import X3.C0413a;
import a6.g;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import androidx.window.embedding.c;
import c0.C0086a;
import c4.C0431a;
import c4.C0436f;
import c4.C0437g;
import c4.C0438h;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.local.LocalProviderHelper;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.abstraction.ConvertingUsageType;
import com.samsung.android.gallery.module.abstraction.MediaCaptureMode;
import com.samsung.android.gallery.module.bgm.BgmUriProviderManager;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.DynamicViewData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.data.UriItemLoader;
import com.samsung.android.gallery.module.dynamicview.DynamicViewPlayInfo;
import com.samsung.android.gallery.module.dynamicview.PlaybackOption;
import com.samsung.android.gallery.module.media.MediaCaptureWorker;
import com.samsung.android.gallery.module.media.MediaDynamicCaptureWorker;
import com.samsung.android.gallery.module.media.MediaHighlightClipCaptureWorker;
import com.samsung.android.gallery.module.media.MediaInstantSlowMoCaptureWorker;
import com.samsung.android.gallery.module.media.MediaLogVideoCaptureWorker;
import com.samsung.android.gallery.module.media.MediaMotionPhotoCaptureWorker;
import com.samsung.android.gallery.module.media.MediaSuperSlowCaptureWorker;
import com.samsung.android.gallery.module.service.abstraction.BaseService;
import com.samsung.android.gallery.module.service.dialog.DialogHelper;
import com.samsung.android.gallery.module.service.dialog.NormalDialogHelper;
import com.samsung.android.gallery.module.service.notification.MediaCaptureNotificationHelper;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.abstraction.BgmOptions;
import com.samsung.android.gallery.support.library.abstraction.MediaCaptureCompat;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayback;
import com.samsung.android.gallery.support.type.PendingShare;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileNameBuilder;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IFormat;
import com.samsung.android.sdk.sgpl.media.MediaMetadataInterface;
import com.samsung.o3dp.lib3dphotography.common.O3DPConstant;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MediaCaptureService extends BaseService {
    private Blackboard mBlackboard = null;
    private final SubscriberListener mCancelListener = new C0391a(21, this);
    private final Object mCaptureLock = new Object();
    private final Consumer<Boolean> mCompleteListener = new C0437g(this, 3);
    private int mConvertingUsageType;
    private DialogHelper mDialogHelper = null;
    private boolean mIsUpdatable = false;
    private String mLocationKey;
    private int mMediaCaptureMode;
    private MediaCaptureWorker mMediaCaptureWorker;
    private MediaCaptureNotificationHelper mNotificationHelper = null;
    private String mNotificationMessage = null;
    private String mNotificationTitle = null;
    private long mOriginDateTime = -1;
    private String mOutFilePath = null;
    private volatile Handler mProgressHandler;
    private final Consumer<Integer> mProgressListener = new C0437g(this, 4);
    private HandlerThread mThread = null;
    private volatile Looper mThreadLooper;
    private boolean mUserCancelled = false;

    private void captureStop(boolean z) {
        a.v("stop capture start [", "]", this.TAG, z);
        MediaCaptureWorker mediaCaptureWorker = this.mMediaCaptureWorker;
        if (mediaCaptureWorker != null) {
            mediaCaptureWorker.finish();
            this.mMediaCaptureWorker = null;
        }
        if (z && this.mOutFilePath != null) {
            SecureFile secureFile = new SecureFile(this.mOutFilePath);
            if (secureFile.exists()) {
                a.v("delete capture file [", "]", this.TAG, secureFile.delete());
            }
        }
        Log.d(this.TAG, "stop capture end");
    }

    private MediaCaptureWorker createMediaCaptureWorker(MediaItem mediaItem) {
        boolean z = PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_GROUP_PANEL;
        if (z && MediaItemUtil.isHighLightClip(mediaItem)) {
            return new MediaHighlightClipCaptureWorker(this.mOutFilePath, this.mProgressListener, this.mCompleteListener, new g(14, this));
        }
        if (z && MediaItemUtil.isSuperSlowClip(mediaItem)) {
            return new MediaSuperSlowCaptureWorker(this.mOutFilePath, this.mProgressListener, this.mCompleteListener, new g(14, this));
        }
        if (PreferenceFeatures.OneUi6x.SUPPORT_INSTANT_SLOW_MO_SAVE_CLIP && isInstantSlowMoCaptureMode() && MediaItemUtil.hasInstantSloMoLatestExecuteSection(mediaItem)) {
            return new MediaInstantSlowMoCaptureWorker(this.mOutFilePath, this.mProgressListener, this.mCompleteListener, new g(14, this));
        }
        if (MediaItemUtil.isMotionPhotoAutoPlayViewMode(mediaItem)) {
            return new MediaMotionPhotoCaptureWorker(this.mOutFilePath, this.mProgressListener, this.mCompleteListener, new g(14, this));
        }
        if (mediaItem.isLogVideo()) {
            return new MediaLogVideoCaptureWorker(this.mOutFilePath, this.mProgressListener, this.mCompleteListener, new g(14, this));
        }
        return new MediaDynamicCaptureWorker(this.mOutFilePath, this.mProgressListener, this.mCompleteListener, new g(14, this));
    }

    private ArrayList<MediaPlayback> getDynamicPlaybacks(MediaItem mediaItem) {
        DynamicViewPlayInfo dynamicViewPlayInfo;
        String str;
        if (mediaItem == null) {
            dynamicViewPlayInfo = null;
        } else {
            dynamicViewPlayInfo = DynamicViewData.of(mediaItem).dynamicViewPlayInfo;
        }
        String str2 = this.TAG;
        StringBuilder sb2 = new StringBuilder("MediaCapturePlaybacks ");
        if (dynamicViewPlayInfo == null) {
            str = "null";
        } else {
            str = dynamicViewPlayInfo.toDebugString();
        }
        j.y(sb2, str, str2);
        if (dynamicViewPlayInfo == null) {
            return new ArrayList<>();
        }
        ArrayList<MediaPlayback> arrayList = new ArrayList<>();
        Iterator<PlaybackOption> it = dynamicViewPlayInfo.getPlayList().iterator();
        while (it.hasNext()) {
            PlaybackOption next = it.next();
            arrayList.add(new MediaPlayback(next.mStartMs, next.mEndMs, next.mSpeed));
        }
        return arrayList;
    }

    private File getMediaCaptureSavePath(MediaItem mediaItem) {
        String str;
        File file;
        if (mediaItem.isCloudOnly()) {
            str = mediaItem.getCloudServerPath();
        } else {
            str = mediaItem.getPath();
        }
        if (str != null) {
            file = new File(FileUtils.getParent(str));
        } else {
            file = null;
        }
        if (file != null && FileUtils.makeDirectoryIfAbsent(file)) {
            return new File(new FileNameBuilder(str).setExtension(IFormat.FORMAT_MP4).buildUnique());
        }
        throw new IOException();
    }

    private File getMediaCaptureSharePath(MediaItem mediaItem) {
        File externalFilesDir = getExternalFilesDir(".share");
        if (externalFilesDir != null && FileUtils.makeDirectoryIfAbsent(externalFilesDir)) {
            return new File(getShareFilePath(mediaItem, externalFilesDir.getAbsolutePath()));
        }
        throw new IOException();
    }

    private ArrayList<MediaPlayback> getMotionPhotoPlaybacks(MediaItem mediaItem) {
        ArrayList<MediaPlayback> arrayList = DetailsData.of(mediaItem).motionPhotoPlaybacks;
        if (arrayList != null) {
            return arrayList;
        }
        return new ArrayList<>();
    }

    private String getNotificationChannelName() {
        return this.mNotificationMessage;
    }

    private int getNotificationId() {
        String str;
        if (TextUtils.isEmpty(this.mOutFilePath)) {
            str = this.TAG;
        } else {
            str = this.mOutFilePath;
        }
        return str.hashCode();
    }

    private String getOutFilePathAndMakeFile(MediaItem mediaItem) {
        File file;
        try {
            if (isShareType()) {
                file = getMediaCaptureSharePath(mediaItem);
            } else {
                file = getMediaCaptureSavePath(mediaItem);
            }
            file.createNewFile();
            return file.getAbsolutePath();
        } catch (Exception unused) {
            Log.e(this.TAG, "fail to create target file");
            return null;
        }
    }

    private List<MediaPlayback> getPlaybacks(MediaItem mediaItem) {
        MediaCaptureWorker mediaCaptureWorker = this.mMediaCaptureWorker;
        if (mediaCaptureWorker instanceof MediaMotionPhotoCaptureWorker) {
            return getMotionPhotoPlaybacks(mediaItem);
        }
        if (mediaCaptureWorker instanceof MediaDynamicCaptureWorker) {
            return getDynamicPlaybacks(mediaItem);
        }
        return new ArrayList();
    }

    private String getShareFilePath(MediaItem mediaItem, String str) {
        String path = mediaItem.getPath();
        if (path == null) {
            throw new IllegalStateException("path is null");
        } else if (DynamicViewData.of(mediaItem).dynamicViewPlayInfo == null) {
            return new FileNameBuilder(path).setDirectoryPath(str).setExtension(IFormat.FORMAT_MP4).buildUnique();
        } else {
            StringBuilder s = C0212a.s(str);
            s.append(File.separator);
            s.append(FileUtils.getBaseName(path));
            s.append("_");
            s.append(mediaItem.getTitle());
            s.append(O3DPConstant.MP4_EXTENSION);
            return s.toString();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: handlePreparedListenerCallback */
    public void lambda$prepareMediaCaptureWorker$0(Boolean bool, MediaItem mediaItem, boolean z) {
        if (bool.booleanValue()) {
            Log.d(this.TAG, "mediaCapture prepared");
            if (z) {
                prepareBgm(mediaItem);
            }
            Optional.ofNullable(this.mProgressHandler).ifPresent(new C0437g(this, 0));
            return;
        }
        Log.d(this.TAG, "mediaCapture prepare fail");
        ThreadUtil.runOnUiThread(new C0436f(this, 1));
    }

    private boolean init(Intent intent) {
        this.mUserCancelled = false;
        Blackboard instance = Blackboard.getInstance(intent.getStringExtra("blackboard_name"));
        this.mBlackboard = instance;
        if (instance == null) {
            Log.e(this.TAG, "blackboard is null. prepare failed.");
            return false;
        }
        MediaItem[] mediaItemArr = (MediaItem[]) instance.pop("data://user/selected");
        if (mediaItemArr == null || mediaItemArr.length == 0 || mediaItemArr[0] == null) {
            return false;
        }
        this.mLocationKey = intent.getStringExtra("location_key");
        this.mConvertingUsageType = intent.getIntExtra("usage_type", 0);
        this.mMediaCaptureMode = intent.getIntExtra("media_capture_mode", 0);
        String outFilePathAndMakeFile = getOutFilePathAndMakeFile(mediaItemArr[0]);
        this.mOutFilePath = outFilePathAndMakeFile;
        if (TextUtils.isEmpty(outFilePathAndMakeFile)) {
            Log.e(this.TAG, "create target file Path");
            return false;
        }
        this.mOriginDateTime = mediaItemArr[0].getDateTaken();
        registerServiceRunning();
        this.mNotificationTitle = FileUtils.getNameFromPath(this.mOutFilePath);
        if (isShareType()) {
            this.mNotificationMessage = getString(R.string.video_conversion_dialog_title);
        } else if (isInstantSlowMoCaptureMode()) {
            this.mNotificationMessage = getString(R.string.saving_instant_slow_mo_clip);
        } else {
            this.mNotificationMessage = getString(R.string.saving_video);
        }
        initDialogHelper();
        initNotificationHelper();
        startForeground(this.mNotificationHelper.getSummaryId(), this.mNotificationHelper.getSummaryNotification());
        subscribeEvents();
        prepareMediaCaptureWorker(mediaItemArr[0]);
        return true;
    }

    private void initDialogHelper() {
        NormalDialogHelper normalDialogHelper = new NormalDialogHelper();
        this.mDialogHelper = normalDialogHelper;
        normalDialogHelper.init(this.mBlackboard);
    }

    private void initNotificationHelper() {
        MediaCaptureNotificationHelper mediaCaptureNotificationHelper = new MediaCaptureNotificationHelper(this, getNotificationId(), this.TAG, "com.samsung.android.gallery.app.service.MediaCaptureService");
        this.mNotificationHelper = mediaCaptureNotificationHelper;
        mediaCaptureNotificationHelper.create(getNotificationChannelName());
        this.mNotificationHelper.show(this);
    }

    private boolean isAudioPlayable(MediaItem mediaItem) {
        if (!Features.isEnabled(Features.SUPPORT_BGM_SERVICE) || mediaItem == null) {
            return false;
        }
        return ((Boolean) Optional.ofNullable(DynamicViewData.of(mediaItem).dynamicViewPlayInfo).map(new C0431a(1)).orElse(Boolean.FALSE)).booleanValue();
    }

    private boolean isInstantSlowMoCaptureMode() {
        if (MediaCaptureMode.getMode(this.mMediaCaptureMode) == MediaCaptureMode.INSTANT_SLOW_MO_CLIP) {
            return true;
        }
        return false;
    }

    private boolean isShareType() {
        if (ConvertingUsageType.COMMON_SHARE == ConvertingUsageType.of(this.mConvertingUsageType)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handlePreparedListenerCallback$1(Handler handler) {
        handler.post(new C0436f(this, 4));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$notifyResultForShare$13(FileItemInterface fileItemInterface, Blackboard blackboard) {
        blackboard.postEvent(EventMessage.obtain(3018, this.mConvertingUsageType, 0, fileItemInterface));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCompleted$5(boolean z, Handler handler) {
        C0436f fVar;
        if (z) {
            fVar = new C0436f(this, 6);
        } else {
            fVar = new C0436f(this, 7);
        }
        handler.post(fVar);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onInterruptService$9(Handler handler) {
        handler.post(new C0436f(this, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onMediaCaptureError$2(Handler handler) {
        handler.post(new C0436f(this, 7));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onSuccess$7(String str) {
        this.mNotificationHelper.showStopNotification(this, this.mNotificationTitle, str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onTerminateService$11(Handler handler) {
        handler.post(new C0436f(this, 3));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$scanAndNotifyResult$15(Blackboard blackboard, String str, Uri uri) {
        if ((LocationKey.isDynamicViewList(this.mLocationKey) || isInstantSlowMoCaptureMode()) && blackboard != null) {
            ThreadUtil.postOnBgThreadDelayed(new c(8, blackboard, uri), 500);
        } else {
            Utils.showToast((Context) this, getString(R.string.video_saved_in, new Object[]{BucketUtils.getTranslatedDirectory(this.mOutFilePath)}));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startViewer$3(QueryParams queryParams) {
        queryParams.setFilePath(this.mOutFilePath);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startViewer$4() {
        Cursor query;
        MediaItem mediaItem = null;
        try {
            query = DbCompat.query(DbKey.ALL_PICTURES, new C0437g(this, 1));
            if (query != null) {
                if (query.moveToFirst()) {
                    mediaItem = MediaItemBuilder.create(query);
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        if (mediaItem != null) {
            ArrayList arrayList = new ArrayList();
            Intent intent = new Intent("android.intent.action.VIEW");
            arrayList.add(ContentUri.getUri(mediaItem));
            intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.activity.external.GalleryExternalActivity");
            intent.setData((Uri) arrayList.get(0));
            intent.putExtra("uriListData", arrayList);
            intent.putExtra("useUriList", true);
            intent.addFlags(335544320);
            startActivity(intent);
        } else {
            Log.e(this.TAG, "save file is not exist");
        }
        ThreadUtil.postOnUiThread(new C0436f(this, 2));
        return;
        throw th;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$unsubscribeEvents$12(Blackboard blackboard) {
        blackboard.unsubscribe("command://CancelDialog", this.mCancelListener);
    }

    private void notifyFinish() {
        Optional.ofNullable(this.mBlackboard).ifPresent(new C0579a(29));
    }

    private void notifyResultForShare(FileItemInterface fileItemInterface) {
        savePath(this.mOutFilePath);
        updateDatabase(this.mOutFilePath);
        Optional.ofNullable(this.mBlackboard).ifPresent(new b(20, this, fileItemInterface));
    }

    private void onCallActivity() {
        if (isShareType()) {
            startShareForActivity();
        } else {
            startViewer();
        }
    }

    /* access modifiers changed from: private */
    public void onCompleted(boolean z) {
        a.v("encoding completed [", "]", this.TAG, z);
        if (z) {
            onUpdated(100, false);
        }
        this.mIsUpdatable = false;
        unregisterServiceRunning();
        Optional.ofNullable(this.mProgressHandler).ifPresent(new E7.c(this, z, 8));
        notifyFinish();
    }

    /* access modifiers changed from: private */
    public void onDialogCancelled(Object obj, Bundle bundle) {
        Log.d(this.TAG, "dialog is cancelled.");
        onInterruptService();
        this.mUserCancelled = true;
    }

    /* access modifiers changed from: private */
    public void onFail() {
        Log.d(this.TAG, "fail to convert video", Boolean.valueOf(this.mUserCancelled));
        if (this.mMediaCaptureWorker != null) {
            onInterruptInternal();
        }
        if (!this.mUserCancelled) {
            if (isShareType()) {
                Utils.showToast((Context) this, (int) R.string.conversion_canceled);
            } else {
                Utils.showToast((Context) this, (int) R.string.canceled_saving_video);
            }
            Optional.ofNullable(this.mBlackboard).ifPresent(new C0438h(0));
        }
        this.mUserCancelled = false;
    }

    /* access modifiers changed from: private */
    public void onInterruptInternal() {
        synchronized (this.mCaptureLock) {
            try {
                if (this.mMediaCaptureWorker != null) {
                    Log.d(this.TAG, "interrupting task.");
                    this.mDialogHelper.dismissDialog();
                    this.mNotificationHelper.dismiss();
                    stopForeground(true);
                    captureStop(true);
                    unregisterServiceRunning();
                    onTerminateService();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    public void onInterruptService() {
        Log.d(this.TAG, "onInterruptService");
        this.mIsUpdatable = false;
        if (isShareType()) {
            Utils.showToast((Context) this, (int) R.string.conversion_canceled);
        } else {
            Utils.showToast((Context) this, (int) R.string.story_saving_cancel_toast);
        }
        Optional.ofNullable(this.mProgressHandler).ifPresent(new C0437g(this, 5));
        Optional.ofNullable(this.mBlackboard).ifPresent(new C0438h(1));
    }

    /* access modifiers changed from: private */
    public boolean onMediaCaptureError(MediaCaptureCompat mediaCaptureCompat, int i2, int i7) {
        Log.e(this.TAG, a.d(i2, i7, "onMediaCaptureError {", GlobalPostProcInternalPPInterface.SPLIT_REGEX, "}"));
        this.mIsUpdatable = false;
        unregisterServiceRunning();
        Optional.ofNullable(this.mProgressHandler).ifPresent(new C0437g(this, 2));
        return true;
    }

    /* access modifiers changed from: private */
    public void onMediaCapturePrepared() {
        if (this.mMediaCaptureWorker != null) {
            this.mDialogHelper.showDialog(this.mNotificationMessage, -1, -1, 0);
            this.mNotificationHelper.update(this, 0, this.mNotificationTitle, this.mNotificationMessage);
            this.mMediaCaptureWorker.start();
            this.mIsUpdatable = true;
            return;
        }
        Log.w(this.TAG, "mediaCapture was already destroyed");
    }

    private void onStartService(Intent intent) {
        if (!init(intent)) {
            onTerminateService();
            Log.w(this.TAG, "unable to init, finish service.");
            return;
        }
        Log.d(this.TAG, "service is started.");
    }

    /* access modifiers changed from: private */
    public void onSuccess() {
        String str;
        synchronized (this.mCaptureLock) {
            try {
                if (this.mMediaCaptureWorker != null) {
                    Log.d(this.TAG, "succeed to convert video");
                    captureStop(false);
                    if (isShareType()) {
                        Utils.showToast((Context) this, (int) R.string.video_converted);
                        updateAndNotifyForShare();
                    } else {
                        scanAndNotifyResult();
                    }
                    this.mDialogHelper.dismissDialog();
                    if (isShareType()) {
                        str = getString(R.string.video_auto_conversion_finished);
                    } else {
                        str = getString(R.string.video_saved_in, new Object[]{BucketUtils.getTranslatedDirectory(this.mOutFilePath)});
                    }
                    ThreadUtil.postOnUiThreadDelayed(new c(7, this, str), 200);
                    stopForeground(false);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    public void onTerminateService() {
        unsubscribeEvents();
        Optional.ofNullable(this.mProgressHandler).ifPresent(new C0437g(this, 6));
        notifyFinish();
    }

    /* access modifiers changed from: private */
    public void onUpdated(int i2) {
        onUpdated(i2, true);
    }

    private void prepareBgm(MediaItem mediaItem) {
        boolean z;
        if (isAudioPlayable(mediaItem)) {
            int fileDuration = mediaItem.getFileDuration();
            DynamicViewPlayInfo dynamicViewPlayInfo = DynamicViewData.of(mediaItem).dynamicViewPlayInfo;
            if (dynamicViewPlayInfo == null) {
                Log.e(this.TAG, "fail to prepare bgm due to no DynamicViewPlayInfo");
                return;
            }
            BgmOptions createBgmOptions = BgmUriProviderManager.getInstance().createBgmOptions(dynamicViewPlayInfo.getDuration(), fileDuration, dynamicViewPlayInfo.getType());
            if (createBgmOptions != null) {
                this.mMediaCaptureWorker.initBackgroundMusic(createBgmOptions);
            }
            String str = this.TAG;
            StringBuilder sb2 = new StringBuilder("prepare bgm?");
            if (createBgmOptions != null) {
                z = true;
            } else {
                z = false;
            }
            sb2.append(z);
            Log.d(str, sb2.toString());
        }
    }

    private void prepareMediaCaptureWorker(MediaItem mediaItem) {
        MediaCaptureWorker createMediaCaptureWorker = createMediaCaptureWorker(mediaItem);
        this.mMediaCaptureWorker = createMediaCaptureWorker;
        createMediaCaptureWorker.prepare(mediaItem, getPlaybacks(mediaItem), new C0413a(this, mediaItem, createMediaCaptureWorker instanceof MediaDynamicCaptureWorker, 1));
    }

    private void registerServiceRunning() {
        Blackboard.getApplicationInstance().publish("data://running_media_capture", this.mOutFilePath);
    }

    private void scanAndNotifyResult() {
        Blackboard blackboard = this.mBlackboard;
        if (SdkConfig.lessThan(SdkConfig.SEM.V)) {
            updateIso(this.mOriginDateTime + 1000, this.mOutFilePath);
        }
        MediaScannerConnection.scanFile(this, new String[]{this.mOutFilePath}, (String[]) null, new n(3, this, blackboard));
    }

    private void startShareForActivity() {
        Log.d(this.TAG, "call gallery activity, finish service");
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.activity.GalleryActivity");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.addFlags(805306368);
        intent.putExtra("key-pending-sharing-event", true);
        intent.putExtra("usage_type", this.mConvertingUsageType);
        Blackboard blackboard = this.mBlackboard;
        if (blackboard != null) {
            intent.putExtra("key_pending_blackboard_name", blackboard.getName());
        }
        startActivity(intent);
        onTerminateService();
    }

    private void startViewer() {
        ThreadUtil.postOnBgThread(new C0436f(this, 5));
    }

    private void subscribeEvents() {
        this.mBlackboard.subscribeOnUi("command://CancelDialog", this.mCancelListener);
    }

    private void threadInit() {
        HandlerThread handlerThread = new HandlerThread(this.TAG);
        this.mThread = handlerThread;
        handlerThread.start();
        this.mThreadLooper = this.mThread.getLooper();
        this.mProgressHandler = new Handler(this.mThreadLooper);
    }

    private void threadRelease() {
        this.mThread.quitSafely();
        this.mThreadLooper.quit();
        this.mProgressHandler = null;
        this.mThread = null;
        this.mBlackboard = null;
        unregisterServiceRunning();
    }

    private void unregisterServiceRunning() {
        Blackboard.getApplicationInstance().erase("data://running_media_capture");
    }

    private void unsubscribeEvents() {
        Optional.ofNullable(this.mBlackboard).ifPresent(new C0437g(this, 7));
    }

    private void updateAndNotifyForShare() {
        SecureFile secureFile = new SecureFile(this.mOutFilePath);
        if (secureFile.exists()) {
            notifyResultForShare(UriItemLoader.createUriItem(getBaseContext(), secureFile));
        } else {
            Log.e(this.TAG, "failed to encoding.");
        }
    }

    private void updateIso(long j2, String str) {
        if (!FileUtils.exists(str) || j2 <= 0) {
            Log.w((CharSequence) this.TAG, "ignore time update", Long.valueOf(j2));
            return;
        }
        try {
            String exifDateTime = TimeUtil.getExifDateTime(j2);
            MediaMetadataInterface mediaMetadataInterface = new MediaMetadataInterface(str);
            mediaMetadataInterface.setAttribute(MediaMetadataInterface.Attribute.ATTR_CREATION_TIME, exifDateTime);
            mediaMetadataInterface.saveAttributes();
            Log.d(this.TAG, "time update", Long.valueOf(j2));
        } catch (Exception e) {
            Log.e(this.TAG, e.toString());
        }
    }

    public void onCreate() {
        super.onCreate();
        threadInit();
    }

    public void onDestroy() {
        threadRelease();
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int i2, int i7) {
        if (intent != null) {
            String action = intent.getAction();
            Log.d(this.TAG, "service receives [" + action + "]");
            if (action != null) {
                char c5 = 65535;
                switch (action.hashCode()) {
                    case -670797158:
                        if (action.equals("com.samsung.android.gallery.app.service.STOP_SERVICE")) {
                            c5 = 0;
                            break;
                        }
                        break;
                    case 980299926:
                        if (action.equals("com.samsung.android.gallery.app.service.START_SERVICE")) {
                            c5 = 1;
                            break;
                        }
                        break;
                    case 1082223299:
                        if (action.equals("com.samsung.android.gallery.app.service.DELETE_SERVICE")) {
                            c5 = 2;
                            break;
                        }
                        break;
                    case 1768765646:
                        if (action.equals("com.samsung.android.gallery.app.service.CALL_ACTIVITY")) {
                            c5 = 3;
                            break;
                        }
                        break;
                }
                switch (c5) {
                    case 0:
                        onInterruptService();
                        break;
                    case 1:
                        onStartService(intent);
                        break;
                    case 2:
                        Log.i(this.TAG, "ACTION_DELETE_SERVICE");
                        stopForeground(true);
                        MediaCaptureNotificationHelper mediaCaptureNotificationHelper = this.mNotificationHelper;
                        if (mediaCaptureNotificationHelper != null) {
                            mediaCaptureNotificationHelper.destroy();
                            break;
                        }
                        break;
                    case 3:
                        onCallActivity();
                        break;
                    default:
                        onTerminateService();
                        break;
                }
            }
        } else {
            Log.w(this.TAG, "unable to operate startCommand");
        }
        return 2;
    }

    public void savePath(String str) {
        PendingShare.set(str);
    }

    public void updateDatabase(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        ContentValues c5 = C0086a.c("__absPath", str);
        c5.put("date_added", Long.valueOf(currentTimeMillis));
        new LocalProviderHelper(getContentResolver()).insertConvertedFile(c5);
        a.x(new StringBuilder("insert to local db +"), currentTimeMillis, this.TAG);
    }

    private void onUpdated(int i2, boolean z) {
        if (this.mIsUpdatable) {
            this.mDialogHelper.updateDialog(-1, -1, i2);
            if (z) {
                this.mNotificationHelper.update(this, i2, this.mNotificationTitle, this.mNotificationMessage);
            }
        }
    }
}
