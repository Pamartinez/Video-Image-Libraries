package com.samsung.android.gallery.app.service;

import B8.g;
import C3.C0391a;
import U5.b;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import android.util.SparseArray;
import androidx.window.embedding.c;
import c4.C0432b;
import c4.C0435e;
import c4.C0438h;
import c4.C0441k;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dynamicview.DynamicViewInfo;
import com.samsung.android.gallery.module.dynamicview.DynamicViewPlayInfo;
import com.samsung.android.gallery.module.dynamicview.PlaybackOption;
import com.samsung.android.gallery.module.service.abstraction.BaseService;
import com.samsung.android.gallery.module.service.dialog.DialogHelper;
import com.samsung.android.gallery.module.service.dialog.NormalDialogHelper;
import com.samsung.android.gallery.module.service.notification.StoryNotificationHelper;
import com.samsung.android.gallery.module.service.support.StoryServiceHelper;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class StoryBaseService extends BaseService {
    protected Blackboard mBlackboard = null;
    private final SubscriberListener mCancelListener = new C0391a(22, this);
    private Bitmap mContentBitmap = null;
    private final Object mConversionLock = new Object();
    protected DialogHelper mDialogHelper = null;
    protected boolean mFromStoryPictures = false;
    protected boolean mIsImageSave = false;
    private boolean mIsUpdatable = false;
    protected StoryNotificationHelper mNotificationHelper = null;
    private String mNotificationMessage = null;
    private String mNotificationTitle = null;
    private StoryServiceHelper.OperationType mOperationType;
    private final ArrayList<PlaybackOption> mPlaybackOptions = new ArrayList<>();
    private volatile Handler mProgressHandler;
    protected final StoryServiceHelper mStoryServiceHelper = new StoryServiceHelper();
    protected final SparseArray<Task> mTask = new SparseArray<>();
    private HandlerThread mThread = null;
    private volatile Looper mThreadLooper;
    private Rect mVideoLayoutInfo;
    private String mVideoPath;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Task {
        int notificationId;
        String resultFilePath;

        public Task(int i2, String str) {
            this.notificationId = i2;
            this.resultFilePath = str;
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(super.toString());
            sb2.append(" {");
            sb2.append(this.notificationId);
            sb2.append(ArcCommonLog.TAG_COMMA);
            return C0212a.p(sb2, this.resultFilePath, "}");
        }
    }

    private void executeHandlePost(Runnable runnable) {
        Optional.ofNullable(this.mProgressHandler).ifPresent(new C0435e(runnable, 1));
    }

    private void finishOperation(String str) {
        onFinishedInternal(str);
        if (TextUtils.isEmpty(str)) {
            removeCurrentTask();
        } else {
            stopForeground(false);
        }
        this.mDialogHelper.dismissDialog();
        this.mStoryServiceHelper.stopEncode();
        unsubscribeEvents();
        Optional.ofNullable(this.mBlackboard).ifPresent(new C0438h(2));
    }

    private String getNotificationChannelName() {
        return this.mNotificationMessage;
    }

    private int getNotificationId(String str) {
        if (TextUtils.isEmpty(str)) {
            return this.TAG.hashCode();
        }
        return str.hashCode();
    }

    private void initialize() {
        HandlerThread handlerThread = new HandlerThread(this.TAG);
        this.mThread = handlerThread;
        handlerThread.start();
        this.mThreadLooper = this.mThread.getLooper();
        this.mProgressHandler = new Handler(this.mThreadLooper);
    }

    private void initializeDialogHelper() {
        NormalDialogHelper normalDialogHelper = new NormalDialogHelper();
        this.mDialogHelper = normalDialogHelper;
        normalDialogHelper.init(this.mBlackboard);
    }

    private void initializeNotificationHelper(Task task) {
        this.mNotificationTitle = "";
        this.mNotificationMessage = this.mStoryServiceHelper.getNotificationMessage(getBaseContext(), this.mOperationType, false);
        StoryNotificationHelper storyNotificationHelper = new StoryNotificationHelper(this, task.notificationId, this.TAG, getServiceClassName());
        this.mNotificationHelper = storyNotificationHelper;
        storyNotificationHelper.create(getNotificationChannelName());
        this.mNotificationHelper.show(this);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPrepareDataService$2(DynamicViewPlayInfo dynamicViewPlayInfo, DynamicViewPlayInfo dynamicViewPlayInfo2) {
        this.mPlaybackOptions.addAll(dynamicViewPlayInfo.getPlayList());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onProgressCompleted$3(boolean z, String str) {
        if (z) {
            onSuccess(str);
        } else {
            onFail();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onStartService$1(Task task) {
        String str;
        if (prepareSaveOperation(task.resultFilePath)) {
            this.mStoryServiceHelper.setProgressListener(new C0441k(this, 0));
            this.mStoryServiceHelper.setCompletedListener(new C0441k(this, 1));
            startSaveOperation();
            return;
        }
        finishOperation((String) null);
        onTerminateService((Intent) null);
        String str2 = this.TAG;
        StringBuilder sb2 = new StringBuilder("prepareSaveOperation failed finish service. ( ");
        if (this.mIsImageSave) {
            str = "Cover";
        } else {
            str = "Video";
        }
        sb2.append(str);
        sb2.append(" )");
        Log.w(str2, sb2.toString());
    }

    /* access modifiers changed from: private */
    public void onDialogCancelled(Object obj, Bundle bundle) {
        Log.d(this.TAG, "dialog is cancelled.");
        onInterruptService((Intent) null);
    }

    private void onFail() {
        String str = this.TAG;
        Log.d(str, "fail : " + this.mOperationType);
        Utils.showToast((Context) this, this.mStoryServiceHelper.getResultMessage(getBaseContext(), StoryServiceHelper.ResultState.FAIL, this.mOperationType));
        lambda$onInterruptService$0((Intent) null);
    }

    /* access modifiers changed from: private */
    /* renamed from: onInterruptInternal */
    public void lambda$onInterruptService$0(Intent intent) {
        synchronized (this.mConversionLock) {
            Log.d(this.TAG, "interrupting task.");
            finishOperation((String) null);
            this.mStoryServiceHelper.cancel();
            onTerminateService(intent);
        }
    }

    private void onInterruptService(Intent intent) {
        this.mIsUpdatable = false;
        executeHandlePost(new c(10, this, intent));
        Utils.showToast((Context) this, this.mStoryServiceHelper.getResultMessage(getBaseContext(), StoryServiceHelper.ResultState.CANCEL, this.mOperationType));
    }

    private Task onPrepareDataService(Intent intent) {
        boolean z;
        boolean z3;
        Blackboard instance = Blackboard.getInstance(intent.getStringExtra("blackboard_name"));
        this.mBlackboard = instance;
        DynamicViewInfo dynamicViewInfo = null;
        if (instance == null || intent.getExtras() == null) {
            Log.e(this.TAG, "blackboard is null");
            return null;
        }
        Bundle extras = intent.getExtras();
        this.mVideoPath = extras.getString("video_path", (String) null);
        this.mIsImageSave = extras.getBoolean("is_image_save", false);
        this.mFromStoryPictures = extras.getBoolean("from_story_pictures", false);
        this.mVideoLayoutInfo = (Rect) extras.getParcelable("video_layout_info");
        this.mOperationType = getOperationType();
        Bitmap contentBitmap = this.mStoryServiceHelper.getContentBitmap(this.mBlackboard, this.mVideoLayoutInfo, this.mFromStoryPictures, this.mIsImageSave);
        this.mContentBitmap = contentBitmap;
        boolean z7 = this.mIsImageSave;
        if (!z7 && (contentBitmap == null || this.mVideoPath == null || this.mVideoLayoutInfo == null)) {
            Log.d(this.TAG, "init fail {" + this.mContentBitmap + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mVideoPath + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mVideoLayoutInfo + "}");
            return null;
        } else if (!z7 || contentBitmap != null) {
            String string = extras.getString("output_file_path", (String) null);
            if (!FileUtils.makeDirectoryIfAbsent((File) new SecureFile(FileUtils.getDirectoryFromPath(string)))) {
                Log.d(this.TAG, "failed create directory : " + Logger.getEncodedString(FileUtils.getDirectoryFromPath(string)));
                return null;
            }
            String str = this.TAG;
            StringBuilder sb2 = new StringBuilder("preset : videoPath=");
            sb2.append(Logger.getEncodedString(this.mVideoPath));
            sb2.append(", resultPath=");
            sb2.append(Logger.getEncodedString(string));
            sb2.append(", isImageSave=");
            sb2.append(this.mIsImageSave);
            sb2.append(", mFromStoryPictures=");
            sb2.append(this.mFromStoryPictures);
            sb2.append(", videoLayoutInfo=");
            if (this.mVideoLayoutInfo == null) {
                z = true;
            } else {
                z = false;
            }
            sb2.append(z);
            sb2.append(", contentBitmap=");
            if (this.mContentBitmap == null) {
                z3 = true;
            } else {
                z3 = false;
            }
            sb2.append(z3);
            Log.d(str, sb2.toString());
            int notificationId = getNotificationId(string);
            this.mTask.put(notificationId, new Task(getNotificationId(string), string));
            MediaItem[] mediaItemArr = (MediaItem[]) this.mBlackboard.pop("data://user/selected");
            if (mediaItemArr != null && mediaItemArr.length >= 1) {
                dynamicViewInfo = MediaItemUtil.getDynamicViewInfo(mediaItemArr[0]);
            }
            if (dynamicViewInfo != null) {
                DynamicViewPlayInfo build = new DynamicViewInfo.PlayInfoBuilder(dynamicViewInfo).setFileDuration(mediaItemArr[0].getFileDuration()).setPlayType(1).setForList(true).build();
                Optional.ofNullable(build).ifPresent(new b(21, this, build));
            }
            registerServiceRunning();
            initializeDialogHelper();
            Task task = this.mTask.get(notificationId);
            if (task != null) {
                initializeNotificationHelper(task);
            }
            startForeground(this.mNotificationHelper.getSummaryId(), this.mNotificationHelper.getSummaryNotification());
            subscribeEvents();
            onServicePrepared(string);
            this.mStoryServiceHelper.setNotificationId(notificationId);
            return task;
        } else {
            Log.d(this.TAG, "init fail Content bitmap null");
            return null;
        }
    }

    /* access modifiers changed from: private */
    public void onProgressCompleted(String str) {
        String str2 = this.TAG;
        StringBuilder sb2 = new StringBuilder("operation completed [");
        sb2.append(!TextUtils.isEmpty(str));
        sb2.append("]");
        Log.d(str2, sb2.toString());
        this.mIsUpdatable = false;
        executeHandlePost(new g((Object) this, !TextUtils.isEmpty(str), (Object) str, 10));
    }

    /* access modifiers changed from: private */
    public void onProgressUpdated(int i2) {
        if (this.mIsUpdatable) {
            this.mDialogHelper.updateDialog(-1, -1, i2);
            this.mNotificationHelper.update(this, i2, this.mNotificationTitle, this.mNotificationMessage);
        }
    }

    private void onStartService(Intent intent) {
        long j2;
        Task onPrepareDataService = onPrepareDataService(intent);
        if (onPrepareDataService != null) {
            Log.d(this.TAG, "service is started.");
            this.mDialogHelper.showDialog(this.mNotificationMessage, -1, -1, 0);
            this.mNotificationHelper.update(this, 0, this.mNotificationTitle, this.mNotificationMessage);
            c cVar = new c(9, this, onPrepareDataService);
            if (this.mIsImageSave) {
                j2 = 100;
            } else {
                j2 = 0;
            }
            executeHandlePostDelay(cVar, j2);
            return;
        }
        finishOperation((String) null);
        onTerminateService((Intent) null);
        Log.w(this.TAG, "onPrepareDataService failed, finish service.");
    }

    private void onSuccess(String str) {
        synchronized (this.mConversionLock) {
            try {
                String str2 = this.TAG;
                Log.d(str2, "success : " + this.mOperationType);
                if (!StoryServiceHelper.OperationType.SAVE_CARD.equals(this.mOperationType)) {
                    if (StoryServiceHelper.OperationType.SAVE_COVER.equals(this.mOperationType)) {
                    }
                    this.mNotificationHelper.showStopNotification(this, FileUtils.getNameFromPath(str), this.mStoryServiceHelper.getNotificationMessage(getBaseContext(), this.mOperationType, true), str);
                    finishOperation(str);
                }
                Utils.showToast((Context) this, this.mStoryServiceHelper.getResultMessage(getBaseContext(), StoryServiceHelper.ResultState.SUCCESS, this.mOperationType));
                this.mNotificationHelper.showStopNotification(this, FileUtils.getNameFromPath(str), this.mStoryServiceHelper.getNotificationMessage(getBaseContext(), this.mOperationType, true), str);
                finishOperation(str);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private boolean prepareSaveOperation(String str) {
        if (this.mIsImageSave) {
            return this.mStoryServiceHelper.prepareSaveBitmap(str, this.mContentBitmap);
        }
        return this.mStoryServiceHelper.prepareEncode(getApplicationContext(), str, this.mVideoPath, this.mContentBitmap, this.mVideoLayoutInfo, this.mPlaybackOptions);
    }

    private void release() {
        this.mThread.quitSafely();
        this.mThreadLooper.quit();
        this.mProgressHandler = null;
        this.mThread = null;
        this.mBlackboard = null;
        unregisterServiceRunning();
    }

    private void startSaveOperation() {
        this.mIsUpdatable = true;
        if (this.mIsImageSave) {
            this.mStoryServiceHelper.startSaveBitmap(this.mContentBitmap);
        } else {
            this.mStoryServiceHelper.startEncode();
        }
    }

    private void subscribeEvents() {
        Blackboard blackboard = this.mBlackboard;
        if (blackboard != null) {
            blackboard.subscribeOnUi("command://CancelDialog", this.mCancelListener);
        }
    }

    private void unsubscribeEvents() {
        Blackboard blackboard = this.mBlackboard;
        if (blackboard != null) {
            blackboard.unsubscribe("command://CancelDialog", this.mCancelListener);
        }
    }

    public final void executeHandlePostDelay(Runnable runnable, long j2) {
        Optional.ofNullable(this.mProgressHandler).ifPresent(new C0432b(runnable, j2, 1));
    }

    public abstract StoryServiceHelper.OperationType getOperationType();

    public abstract String getServiceClassName();

    public final boolean isTaskEmpty() {
        if (this.mTask.size() == 0) {
            return true;
        }
        return false;
    }

    public abstract void onCallActivity(Intent intent);

    public void onCreate() {
        super.onCreate();
        initialize();
    }

    public void onDestroy() {
        release();
        super.onDestroy();
    }

    public abstract void onFinishedInternal(String str);

    public abstract void onServicePrepared(String str);

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
                    case 1768765646:
                        if (action.equals("com.samsung.android.gallery.app.service.CALL_ACTIVITY")) {
                            c5 = 2;
                            break;
                        }
                        break;
                }
                switch (c5) {
                    case 0:
                        onInterruptService(intent);
                        break;
                    case 1:
                        onStartService(intent);
                        break;
                    case 2:
                        onCallActivity(intent);
                        break;
                    default:
                        onTerminateService(intent);
                        break;
                }
            }
        } else {
            Log.w(this.TAG, "unable to operate startCommand");
        }
        return 2;
    }

    public void onTerminateService(Intent intent) {
        int intExtra;
        StoryNotificationHelper storyNotificationHelper;
        if (intent != null && (intExtra = intent.getIntExtra("notification_id", -1)) != -1 && (storyNotificationHelper = this.mNotificationHelper) != null) {
            storyNotificationHelper.dismiss(intExtra);
            this.mTask.remove(intExtra);
        }
    }

    public final void removeCurrentTask() {
        int notificationId = this.mStoryServiceHelper.getNotificationId();
        if (notificationId != -1) {
            this.mNotificationHelper.dismiss(notificationId);
            this.mTask.remove(notificationId);
        }
    }

    public void removeTask(int i2) {
        this.mTask.remove(i2);
    }

    public void registerServiceRunning() {
    }

    public void unregisterServiceRunning() {
    }
}
