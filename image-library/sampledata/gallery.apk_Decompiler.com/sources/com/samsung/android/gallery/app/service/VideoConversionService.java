package com.samsung.android.gallery.app.service;

import A.a;
import A4.B;
import C3.C0391a;
import E7.c;
import U5.b;
import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import c4.C0438h;
import c4.C0443m;
import c4.C0444n;
import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.abstraction.ConvertingUsageType;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.UriItemLoader;
import com.samsung.android.gallery.module.media.VideoConversionHelper;
import com.samsung.android.gallery.module.media.VideoTransCoder;
import com.samsung.android.gallery.module.service.abstraction.BaseService;
import com.samsung.android.gallery.module.service.dialog.DialogHelper;
import com.samsung.android.gallery.module.service.dialog.NormalDialogHelper;
import com.samsung.android.gallery.module.service.notification.VideoConversionNotificationHelper;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;
import java.io.File;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoConversionService extends BaseService {
    private Blackboard mBlackboard = null;
    private final SubscriberListener mCancelListener = new C0391a(23, this);
    private final Consumer<Boolean> mCompleteListener = new C0443m(this, 2);
    private final Object mConversionLock = new Object();
    private int mConvertingUsageType;
    private DialogHelper mDialogHelper = null;
    private VideoTransCoder mEncoder = null;
    private VideoConversionHelper mHelper = null;
    private boolean mIsUpdatable = false;
    private VideoConversionNotificationHelper mNotificationHelper = null;
    private String mNotificationMessage = null;
    private String mNotificationTitle = null;
    private Uri mOriginalUri;
    private String mOutFilePath = null;
    private volatile Handler mProgressHandler;
    private final Consumer<Integer> mProgressListener = new C0443m(this, 3);
    private HandlerThread mThread = null;
    private volatile Looper mThreadLooper;

    private boolean checkOutFilePath(MediaItem[] mediaItemArr, Intent intent) {
        if (isShareConvert()) {
            File externalFilesDir = getExternalFilesDir(".share");
            if (externalFilesDir != null) {
                this.mOutFilePath = this.mHelper.getFilePath(mediaItemArr[0], externalFilesDir.getAbsolutePath());
            } else {
                Log.e(this.TAG, "target directory is not created. prepare failed.");
            }
        } else {
            this.mOutFilePath = intent.getStringExtra("output_file_path");
        }
        if (this.mOutFilePath != null) {
            return true;
        }
        Log.e(this.TAG, "OutFilePath is null");
        return false;
    }

    /* access modifiers changed from: private */
    public void encodingStart() {
        int estimatedSize = getEstimatedSize();
        String str = this.TAG;
        Log.d(str, "estimated size [" + estimatedSize + "]");
        if (estimatedSize > 0) {
            this.mDialogHelper.showDialog(this.mNotificationMessage, -1, -1, 0);
            this.mNotificationHelper.update(this, 0, this.mNotificationTitle, this.mNotificationMessage);
            this.mEncoder.start();
            this.mIsUpdatable = true;
            registerVideoConversionServiceRunning();
            return;
        }
        Log.w(this.TAG, "unable to start encoding, finish service.");
        onTerminateService();
    }

    private void encodingStop(boolean z) {
        a.v("stop encoding [", "]", this.TAG, z);
        VideoTransCoder videoTransCoder = this.mEncoder;
        if (videoTransCoder != null) {
            videoTransCoder.finish();
            this.mEncoder = null;
        }
        if (z && this.mOutFilePath != null) {
            SecureFile secureFile = new SecureFile(this.mOutFilePath);
            if (secureFile.exists()) {
                a.v("delete encoded file [", "]", this.TAG, secureFile.delete());
            }
        }
    }

    private int getEstimatedSize() {
        MediaItem mediaItem;
        Blackboard blackboard = this.mBlackboard;
        if (blackboard == null || this.mHelper == null) {
            Log.e(this.TAG, "mBlackboard or mHelper is invalid.");
            return -1;
        }
        MediaItem[] mediaItemArr = (MediaItem[]) blackboard.pop("data://user/selected");
        if (mediaItemArr == null || mediaItemArr.length == 0 || (mediaItem = mediaItemArr[0]) == null) {
            Log.e(this.TAG, "items are empty. prepare failed.");
            return -1;
        }
        this.mOriginalUri = ContentUri.getUri(mediaItem);
        VideoTransCoder videoTransCoder = new VideoTransCoder(this.mOutFilePath, this.mProgressListener, this.mCompleteListener);
        this.mEncoder = videoTransCoder;
        return videoTransCoder.prepare(mediaItemArr[0]);
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

    private boolean init(Intent intent) {
        this.mBlackboard = Blackboard.getInstance(intent.getStringExtra("blackboard_name"));
        this.mConvertingUsageType = intent.getIntExtra("usage_type", 0);
        this.mHelper = new VideoConversionHelper(this);
        Blackboard blackboard = this.mBlackboard;
        if (blackboard == null) {
            Log.e(this.TAG, "Blackboard is null. Init failed.");
            return false;
        }
        MediaItem[] mediaItemArr = (MediaItem[]) blackboard.read("data://user/selected");
        boolean z = true;
        if (mediaItemArr == null || mediaItemArr.length == 0 || mediaItemArr[0] == null) {
            String str = this.TAG;
            if (mediaItemArr == null) {
                z = false;
            }
            Log.e((CharSequence) str, "init failed. null items", Boolean.valueOf(z));
            return false;
        } else if (!checkOutFilePath(mediaItemArr, intent)) {
            return false;
        } else {
            this.mNotificationTitle = FileUtils.getNameFromPath(this.mOutFilePath);
            this.mNotificationMessage = getString(R.string.video_conversion_dialog_title);
            initDialogHelper();
            initNotificationHelper();
            startForeground(this.mNotificationHelper.getSummaryId(), this.mNotificationHelper.getSummaryNotification());
            subscribeEvents();
            return true;
        }
    }

    private void initDialogHelper() {
        NormalDialogHelper normalDialogHelper = new NormalDialogHelper();
        this.mDialogHelper = normalDialogHelper;
        normalDialogHelper.init(this.mBlackboard);
    }

    private void initNotificationHelper() {
        VideoConversionNotificationHelper videoConversionNotificationHelper = new VideoConversionNotificationHelper(this, getNotificationId(), this.TAG, "com.samsung.android.gallery.app.service.VideoConversionService");
        this.mNotificationHelper = videoConversionNotificationHelper;
        videoConversionNotificationHelper.create(getNotificationChannelName());
        this.mNotificationHelper.show(this);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$notifyResult$8(FileItemInterface fileItemInterface, Blackboard blackboard) {
        blackboard.postEvent(EventMessage.obtain(3018, this.mConvertingUsageType, 0, fileItemInterface));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCompleted$0(boolean z, Handler handler) {
        C0444n nVar;
        if (z) {
            nVar = new C0444n(this, 0);
        } else {
            nVar = new C0444n(this, 1);
        }
        handler.post(nVar);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onInterruptService$2(Handler handler) {
        handler.post(new C0444n(this, 4));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onStartService$3(Handler handler) {
        handler.post(new C0444n(this, 2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onTerminateService$4(Handler handler) {
        handler.post(new C0444n(this, 3));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$unsubscribeEvents$5(Blackboard blackboard) {
        blackboard.unsubscribe("command://CancelDialog", this.mCancelListener);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateAndNotify$6(VideoConversionHelper videoConversionHelper) {
        videoConversionHelper.updateDatabase(this.mOutFilePath);
    }

    private void notifyResult(String str, FileItemInterface fileItemInterface) {
        Optional.ofNullable(this.mHelper).ifPresent(new B(str, 24));
        Optional.ofNullable(this.mBlackboard).ifPresent(new b(22, this, fileItemInterface));
    }

    private void onCallActivity() {
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

    /* access modifiers changed from: private */
    public void onCompleted(boolean z) {
        a.v("encoding completed [", "]", this.TAG, z);
        if (z) {
            onUpdated(100);
        }
        this.mIsUpdatable = false;
        unregisterVideoConversionServiceRunning();
        Optional.ofNullable(this.mProgressHandler).ifPresent(new c(this, z, 9));
    }

    /* access modifiers changed from: private */
    public void onDialogCancelled(Object obj, Bundle bundle) {
        Log.d(this.TAG, "dialog is cancelled.");
        onInterruptService();
        Optional.ofNullable(this.mBlackboard).ifPresent(new C0438h(3));
    }

    /* access modifiers changed from: private */
    public void onFail() {
        Log.d(this.TAG, "fail to convert video");
        VideoTransCoder videoTransCoder = this.mEncoder;
        int i2 = R.string.unknown_error_description;
        if (videoTransCoder == null || videoTransCoder.getOriginalItem() == null) {
            if (isShareConvert()) {
                i2 = R.string.couldnt_share_video_try_again;
            }
            Utils.showToast((Context) this, i2);
            return;
        }
        if (isShareConvert()) {
            i2 = R.string.can_not_convert_video_sending_original;
        }
        Utils.showToast((Context) this, i2);
        notifyResult(this.mEncoder.getOriginalItem().getPath(), this.mEncoder.getOriginalItem());
        onInterruptInternal();
    }

    /* access modifiers changed from: private */
    public void onInterruptInternal() {
        synchronized (this.mConversionLock) {
            try {
                if (this.mEncoder != null) {
                    Log.d(this.TAG, "interrupting task.");
                    encodingStop(true);
                    this.mDialogHelper.dismissDialog();
                    this.mNotificationHelper.dismiss();
                    stopForeground(true);
                    onTerminateService();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void onInterruptService() {
        this.mIsUpdatable = false;
        unregisterVideoConversionServiceRunning();
        Utils.showToast((Context) this, (int) R.string.conversion_canceled);
        Optional.ofNullable(this.mProgressHandler).ifPresent(new C0443m(this, 6));
    }

    private void onStartService(Intent intent) {
        if (init(intent)) {
            Log.d(this.TAG, "service is started.");
            Optional.ofNullable(this.mProgressHandler).ifPresent(new C0443m(this, 5));
            return;
        }
        Log.w(this.TAG, "unable to init, finish service.");
        onTerminateService();
    }

    /* access modifiers changed from: private */
    public void onSuccess() {
        synchronized (this.mConversionLock) {
            try {
                if (this.mEncoder != null) {
                    Log.d(this.TAG, "succeed to convert video");
                    Utils.showToast((Context) this, (int) R.string.video_converted);
                    updateAndNotify();
                    if (!isShareConvert()) {
                        MediaScannerConnection.scanFile(getBaseContext(), new String[]{this.mOutFilePath}, (String[]) null, (MediaScannerConnection.OnScanCompletedListener) null);
                    }
                    encodingStop(false);
                    this.mDialogHelper.dismissDialog();
                    this.mNotificationHelper.showStopNotification(this, this.mNotificationTitle, getString(R.string.video_auto_conversion_finished));
                    stopForeground(false);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void onTerminateService() {
        unsubscribeEvents();
        Optional.ofNullable(this.mProgressHandler).ifPresent(new C0443m(this, 4));
    }

    /* access modifiers changed from: private */
    public void onUpdated(int i2) {
        if (this.mIsUpdatable) {
            this.mDialogHelper.updateDialog(-1, -1, i2);
            this.mNotificationHelper.update(this, i2, this.mNotificationTitle, this.mNotificationMessage);
        }
    }

    private void registerVideoConversionServiceRunning() {
        Blackboard.getApplicationInstance().publish("data://running_video_conversion", this.mOutFilePath);
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
        this.mHelper = null;
        unregisterVideoConversionServiceRunning();
    }

    private void unregisterVideoConversionServiceRunning() {
        Blackboard.getApplicationInstance().erase("data://running_video_conversion");
    }

    private void unsubscribeEvents() {
        Optional.ofNullable(this.mBlackboard).ifPresent(new C0443m(this, 0));
    }

    private void updateAndNotify() {
        SecureFile secureFile = new SecureFile(this.mOutFilePath);
        if (secureFile.exists()) {
            Optional.ofNullable(this.mHelper).ifPresent(new C0443m(this, 1));
            MediaItem createUriItem = UriItemLoader.createUriItem(getBaseContext(), secureFile);
            createUriItem.setExtra(ExtrasID.ORIGINAL_URI, this.mOriginalUri);
            notifyResult(this.mOutFilePath, createUriItem);
            return;
        }
        Log.e(this.TAG, "failed to encoding.");
    }

    public boolean isShareConvert() {
        if (this.mConvertingUsageType == ConvertingUsageType.COMMON_SHARE.value) {
            return true;
        }
        return false;
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
                    case 1768765646:
                        if (action.equals("com.samsung.android.gallery.app.service.CALL_ACTIVITY")) {
                            c5 = 2;
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
}
