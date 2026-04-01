package com.samsung.android.gallery.app.service;

import A.a;
import A4.C0385u;
import F.b;
import N2.j;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import bc.C0584a;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.fileio.compat.FileOpApi;
import com.samsung.android.gallery.module.fileio.compat.FileOpError;
import com.samsung.android.gallery.module.fileio.compat.FileOpJob;
import com.samsung.android.gallery.module.secured.KeepStorageManager;
import com.samsung.android.gallery.module.service.abstraction.BaseService;
import com.samsung.android.gallery.module.service.dialog.DialogHelper;
import com.samsung.android.gallery.module.service.dialog.NormalDialogHelper;
import com.samsung.android.gallery.module.service.message.FileOperationMsgMgr;
import com.samsung.android.gallery.module.service.message.FileOperationMsgParams;
import com.samsung.android.gallery.module.service.notification.DefaultNotificationHelper;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FileOpService extends BaseService {
    final AtomicBoolean mCancelSignal;
    final DialogDelegate mDialogDelegate;
    final FileOpApi mFileOpApi;
    final FileOpData mFileOpData;
    int mGlobalPrepMode;
    Handler mHandler;
    KeepStorageDelegate mKeepStorageDelegate;
    final NotificationDelegate mNotificationDelegate;
    final AtomicBoolean mPrepared;
    final AtomicBoolean mScanState;
    State mState;
    final AtomicBoolean mTerminatePending = new AtomicBoolean(false);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class KeepStorageDelegate {
        final String fileOpType;
        final boolean inPrivate;
        KeepStorageManager keepStorageManager;

        public KeepStorageDelegate(Object obj) {
            String str;
            boolean z;
            if (obj instanceof Intent) {
                str = ((Intent) obj).getStringExtra("operation_type");
            } else {
                str = "";
            }
            this.fileOpType = str;
            if ("move_to_secured".equals(str) || "move_from_secured".equals(str)) {
                z = true;
            } else {
                z = false;
            }
            this.inPrivate = z;
        }

        public void close() {
            if (this.inPrivate) {
                this.keepStorageManager.close("FileOpService");
                this.keepStorageManager.closeSession("FileOpService");
            }
        }

        public void open() {
            if (this.inPrivate) {
                KeepStorageManager instance = KeepStorageManager.getInstance();
                this.keepStorageManager = instance;
                instance.openSession("FileOpService");
                this.keepStorageManager.open("FileOpService");
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class NotificationDelegate {
        String mChannelName;
        DefaultNotificationHelper mHelper;
        FileOpJob mLastJob;
        final FileOpService mService;

        public NotificationDelegate(FileOpService fileOpService) {
            this.mService = fileOpService;
        }

        public void dismiss() {
            Log.d("FileOpService#Noti", "dismiss", this.mChannelName);
            DefaultNotificationHelper defaultNotificationHelper = this.mHelper;
            if (defaultNotificationHelper != null) {
                defaultNotificationHelper.dismiss();
                this.mHelper = null;
            }
            this.mService.stopForeground(true);
            Blackboard.getApplicationInstance().erase("data://running_service");
        }

        public Context getContext() {
            return this.mService;
        }

        public void init() {
            String serviceName = FileOperationMsgMgr.getServiceName(getContext(), this.mService.mFileOpData.isCopy(), this.mService.mFileOpData.isAlbumRename());
            this.mChannelName = serviceName;
            Log.d("FileOpService#Noti", "init", serviceName);
            if (this.mHelper == null) {
                DefaultNotificationHelper defaultNotificationHelper = new DefaultNotificationHelper(getContext(), -1639717032, "FileOpService", FileOpService.class.getName());
                this.mHelper = defaultNotificationHelper;
                defaultNotificationHelper.create(this.mChannelName);
                this.mHelper.show(this.mService);
                this.mService.startForeground(this.mHelper.getSummaryId(), this.mHelper.getSummaryNotification());
                Blackboard.getApplicationInstance().publish("data://running_service", "FileOpService");
            }
        }

        public void update(int i2, int i7) {
            int i8;
            if (i7 == 0) {
                i8 = 0;
            } else {
                i8 = (i2 * 100) / i7;
            }
            FileOpJob peek = this.mService.mFileOpData.queue.peek();
            if (peek == null) {
                peek = this.mLastJob;
            }
            if (peek != null) {
                this.mHelper.update(this.mService, i8, FileUtils.getNameFromPath(peek.source.getPath()), FileOperationMsgMgr.getNotificationMessage(getContext(), FileOperationMsgParams.builder().setIsCopy(peek.isCopy()).setIsMove(peek.isMove()).setIsRename(this.mService.mFileOpData.isAlbumRename()).setOperateCount(i2).setTotalCount(i7).build()));
                this.mLastJob = peek;
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum State {
        None,
        Preparing,
        Ready,
        Terminated
    }

    public FileOpService() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.mCancelSignal = atomicBoolean;
        this.mPrepared = new AtomicBoolean(false);
        this.mScanState = new AtomicBoolean(false);
        this.mNotificationDelegate = new NotificationDelegate(this);
        DialogDelegate dialogDelegate = new DialogDelegate(this);
        this.mDialogDelegate = dialogDelegate;
        this.mFileOpData = new FileOpData();
        this.mFileOpApi = new FileOpApi().setProgress(new g(1, dialogDelegate)).setCancelSignal(new C0385u(14, atomicBoolean));
        this.mState = State.None;
        this.mGlobalPrepMode = 0;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startService$0() {
        this.mHandler.sendEmptyMessage(2);
    }

    public void closeKeepStorage(Object obj) {
        KeepStorageDelegate keepStorageDelegate = this.mKeepStorageDelegate;
        if (keepStorageDelegate != null) {
            keepStorageDelegate.close();
            this.mKeepStorageDelegate = null;
        }
    }

    public void finishJob(FileOpError fileOpError) {
        if (fileOpError != null) {
            this.mFileOpData.errorCode = fileOpError;
        }
        Log.d("FileOpService", "finishJob", this.mFileOpData.errorCode);
        this.mTerminatePending.set(true);
        this.mFileOpData.scanFolder(this);
        this.mHandler.sendEmptyMessageDelayed(99, 1000);
    }

    public boolean handleMessage(Message message) {
        int i2 = message.what;
        if (i2 == 1) {
            if (PocFeatures.SUPPORT_PRIVATE_ALBUM) {
                openKeepStorage(message.obj);
            }
            if (prepareService(message.obj)) {
                startService(message.obj);
            } else {
                this.mHandler.sendEmptyMessage(99);
            }
        } else if (i2 == 2) {
            processJob();
        } else if (i2 == 3) {
            finishJob((FileOpError) message.obj);
        } else if (i2 == 4 || i2 == 99) {
            if (PocFeatures.SUPPORT_PRIVATE_ALBUM) {
                closeKeepStorage(message.obj);
            }
            this.mState = State.Terminated;
            stopService(message.obj);
        }
        return true;
    }

    public boolean initJob(MediaItem[] mediaItemArr) {
        long currentTimeMillis = System.currentTimeMillis();
        this.mFileOpData.clear();
        FileOpData fileOpData = this.mFileOpData;
        String str = fileOpData.targetDir;
        String str2 = fileOpData.storageName;
        int i2 = fileOpData.operation;
        boolean isRemovableStorage = fileOpData.isRemovableStorage();
        ArrayList arrayList = new ArrayList();
        for (MediaItem mediaItem : mediaItemArr) {
            if (mediaItem.isPostProcessing() || mediaItem.getReferencePath() == null) {
                this.mFileOpData.skipPppList.add(mediaItem);
            } else if (!isRemovableStorage || !mediaItem.isCloudOnly()) {
                arrayList.add(new FileOpJob(mediaItem, str, i2).inSameStorage(str2));
            } else {
                this.mFileOpData.skipCloudList.add(mediaItem);
            }
        }
        this.mFileOpData.initJob(arrayList);
        Log.d("FileOpService", "initJob " + this.mFileOpData + " +" + (System.currentTimeMillis() - currentTimeMillis));
        if (arrayList.isEmpty() || !this.mFileOpData.computeStorage((List<FileOpJob>) arrayList)) {
            return false;
        }
        return true;
    }

    public final void onCreate() {
        super.onCreate();
        HandlerThread handlerThread = new HandlerThread("BG_FileOpService");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper(), new b(2, this));
    }

    public final void onDestroy() {
        this.mDialogDelegate.dismiss();
        this.mNotificationDelegate.dismiss();
        Handler handler = this.mHandler;
        if (handler != null) {
            try {
                handler.removeCallbacksAndMessages((Object) null);
                this.mHandler.getLooper().quitSafely();
            } catch (Exception e) {
                a.s(e, new StringBuilder("onDestroy recycle handler failed. e="), "FileOpService");
            }
            this.mHandler = null;
        }
        super.onDestroy();
    }

    public final int onStartCommand(Intent intent, int i2, int i7) {
        String str;
        String str2;
        if (intent != null) {
            str = intent.getAction();
        } else {
            str = "null";
        }
        Log.d("FileOpService", "onStartCommand", str, Integer.valueOf(i7));
        this.mHandler.removeCallbacksAndMessages((Object) null);
        switch (str.hashCode()) {
            case -670797158:
                str2 = "com.samsung.android.gallery.app.service.STOP_SERVICE";
                break;
            case -540770623:
                if (str.equals("com.samsung.android.gallery.app.service.CONTINUE_SERVICE ")) {
                    State state = this.mState;
                    if (state == State.Ready) {
                        startService(intent);
                        return 2;
                    } else if (state == State.Terminated) {
                        return 2;
                    } else {
                        Handler handler = this.mHandler;
                        handler.sendMessage(handler.obtainMessage(1, intent));
                        return 2;
                    }
                }
                break;
            case 980299926:
                if (str.equals("com.samsung.android.gallery.app.service.START_SERVICE")) {
                    Handler handler2 = this.mHandler;
                    handler2.sendMessage(handler2.obtainMessage(1, intent));
                    return 2;
                }
                break;
            case 1082223299:
                str2 = "com.samsung.android.gallery.app.service.DELETE_SERVICE";
                break;
        }
        boolean equals = str.equals(str2);
        this.mFileOpData.errorCode = FileOpError.OperationCanceled;
        Handler handler3 = this.mHandler;
        handler3.sendMessage(handler3.obtainMessage(4, intent));
        return 2;
    }

    public void openKeepStorage(Object obj) {
        if (this.mKeepStorageDelegate == null) {
            KeepStorageDelegate keepStorageDelegate = new KeepStorageDelegate(obj);
            this.mKeepStorageDelegate = keepStorageDelegate;
            keepStorageDelegate.open();
        }
    }

    public void postFinishJob(FileOpError fileOpError) {
        this.mHandler.removeCallbacksAndMessages((Object) null);
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(3, fileOpError));
    }

    public boolean prepareService(Object obj) {
        if ((obj instanceof Intent) && !this.mPrepared.getAndSet(true)) {
            this.mState = State.Preparing;
            this.mScanState.set(false);
            this.mGlobalPrepMode = 0;
            Log.d("FileOpService", "prepareService#" + this.mState);
            if (!this.mFileOpData.init((Intent) obj)) {
                if (!TextUtils.isEmpty(this.mFileOpData.targetDir)) {
                    FileOpData fileOpData = this.mFileOpData;
                    if (fileOpData.operation != 0 && (!fileOpData.isAlbumRename() || this.mFileOpData.albumId != 0)) {
                        if (!FileUtils.isValidPath(this.mFileOpData.targetDir)) {
                            this.mFileOpData.errorCode = FileOpError.PathInvalid;
                        } else {
                            FileOpData fileOpData2 = this.mFileOpData;
                            if (fileOpData2.items.length == 0) {
                                fileOpData2.errorCode = FileOpError.NoItemAvailable;
                            }
                        }
                        Log.e((CharSequence) "FileOpService", "prepareService fail to init", this.mFileOpData.errorCode);
                        return false;
                    }
                }
                this.mFileOpData.errorCode = FileOpError.WrongIntent;
                Log.e((CharSequence) "FileOpService", "prepareService fail to init", this.mFileOpData.errorCode);
                return false;
            } else if (!FileUtils.makeDirectoryIfAbsent(this.mFileOpData.targetDir)) {
                FileOpData fileOpData3 = this.mFileOpData;
                FileOpError fileOpError = FileOpError.MakeDirFailed;
                fileOpData3.errorCode = fileOpError;
                Log.e((CharSequence) "FileOpService", "prepareService fail to mkdirs", fileOpError);
                return false;
            } else if (!this.mFileOpData.initStorageState()) {
                this.mFileOpData.errorCode = FileOpError.StorageNotEnough;
                Log.e((CharSequence) "FileOpService", "prepareService failed. low storage " + this.mFileOpData.storageState, this.mFileOpData.errorCode);
                return false;
            } else if (!initJob(this.mFileOpData.items)) {
                if (this.mFileOpData.isLowStorage()) {
                    this.mFileOpData.errorCode = FileOpError.StorageNotEnough;
                } else if (!this.mFileOpData.skipCloudList.isEmpty()) {
                    this.mFileOpData.errorCode = FileOpError.CloudToSdcardUnsupported;
                } else {
                    int size = this.mFileOpData.skipPppList.size();
                    FileOpData fileOpData4 = this.mFileOpData;
                    if (size == fileOpData4.items.length) {
                        fileOpData4.errorCode = FileOpError.ItemsAllPpp;
                    } else {
                        fileOpData4.errorCode = FileOpError.ItemsUnknown;
                    }
                }
                Log.e((CharSequence) "FileOpService", "prepareService failed", Integer.valueOf(this.mFileOpData.totalList.size()), this.mFileOpData.errorCode);
                return false;
            } else {
                if (this.mFileOpData.totalList.size() > 30) {
                    this.mFileOpApi.setProgress((Consumer<Float>) null);
                }
                try {
                    this.mDialogDelegate.init(this.mFileOpData.blackboard);
                    this.mNotificationDelegate.init();
                    this.mState = State.Ready;
                    Log.d("FileOpService", "prepareService#" + this.mState);
                } catch (Exception e) {
                    Log.e("FileOpService", "prepareService#" + this.mState + " failed. e=" + e.getMessage());
                    return false;
                }
            }
        }
        return true;
    }

    public void processJob() {
        int size = this.mFileOpData.totalList.size();
        int size2 = size - this.mFileOpData.queue.size();
        this.mDialogDelegate.update(size2, size);
        this.mNotificationDelegate.update(size2, size);
        if (this.mFileOpData.queue.isEmpty()) {
            postFinishJob((FileOpError) null);
            return;
        }
        FileOpJob peek = this.mFileOpData.queue.peek();
        if (peek == null) {
            return;
        }
        if (!this.mFileOpData.computeStorage(peek)) {
            Log.d("FileOpService", "processJob failed. low storage" + Logger.v(Integer.valueOf(size2), Integer.valueOf(size)) + " " + this.mFileOpData.storageState);
            postFinishJob(FileOpError.StorageNotEnough);
        } else if (!peek.prepareOrWait(this.mGlobalPrepMode)) {
            Log.d("FileOpService", "processJob#wait" + Logger.v(Integer.valueOf(size2), Integer.valueOf(size), Integer.valueOf(this.mGlobalPrepMode), Integer.valueOf(peek.prepMode)) + " " + peek.toSimpleString());
            this.mDialogDelegate.waitForOptions(peek);
        } else {
            Log.d("FileOpService", "processJob" + Logger.v(Integer.valueOf(size2), Integer.valueOf(size)) + " " + peek.toSimpleString());
            this.mFileOpData.queue.poll();
            if (this.mFileOpApi.execute(peek)) {
                this.mFileOpData.passList.add(peek);
                if (peek.isRename() || peek.isMove()) {
                    ThumbnailLoader.getInstance().removeDiskCache(peek.source);
                }
            } else if (peek.source.isCloudOnly()) {
                this.mFileOpData.failCloudList.add(peek);
            } else {
                this.mFileOpData.failList.add(peek);
            }
            if (!this.mCancelSignal.get()) {
                this.mHandler.sendEmptyMessageDelayed(2, 0);
            }
        }
    }

    public void startService(Object obj) {
        Log.d("FileOpService", "startService " + obj);
        if (this.mFileOpData.replaceIfChanged(obj)) {
            this.mDialogDelegate.init(this.mFileOpData.blackboard);
        }
        this.mDialogDelegate.show();
        this.mFileOpData.start(this);
        ThreadUtil.postOnUiThreadDelayed(new C0584a(7, this), 300);
    }

    public void stopService(Object obj) {
        String str;
        StringBuilder sb2 = new StringBuilder("stopService ");
        if (obj != null) {
            str = obj + GlobalPostProcInternalPPInterface.SPLIT_REGEX;
        } else {
            str = "";
        }
        j.y(sb2, str, "FileOpService");
        this.mHandler.removeCallbacksAndMessages((Object) null);
        this.mFileOpData.stop(this);
        this.mPrepared.set(false);
        stopSelf();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DialogDelegate {
        Blackboard mBlackboard;
        final SubscriberListener mCancelListener = new h(this, 0);
        DialogHelper mHelper;
        FileOpJob mLastJob;
        int mLastProgress;
        final SubscriberListener mOptionListener = new h(this, 1);
        int mPercentage;
        int mPos;
        final SubscriberListener mRenameListener = new h(this, 2);
        String mScreenId;
        final FileOpService mService;
        int mSingleResolution = 100;
        int mTotalCount;

        public DialogDelegate(FileOpService fileOpService) {
            this.mService = fileOpService;
        }

        /* access modifiers changed from: private */
        public void onCancel(Object obj, Bundle bundle) {
            if (!this.mService.mTerminatePending.get()) {
                Log.d("FileOpService#Dlg", "onCancel", Integer.valueOf(this.mService.mFileOpData.queue.size()), Boolean.valueOf(this.mService.mTerminatePending.get()));
                this.mService.mCancelSignal.set(true);
                this.mService.postFinishJob(FileOpError.OperationCanceled);
                return;
            }
            Log.d("FileOpService#Dlg", "skip onCancel", Integer.valueOf(this.mService.mFileOpData.queue.size()), Boolean.valueOf(this.mService.mTerminatePending.get()));
        }

        /* access modifiers changed from: private */
        public void onOptionSelected(Object obj, Bundle bundle) {
            if (this.mLastJob == null) {
                Log.e("FileOpService#Dlg", "onOptionSelected wrong arguments " + obj);
                return;
            }
            int i2 = BundleWrapper.getInt(bundle, "target", -1);
            int i7 = 0;
            boolean z = BundleWrapper.getBoolean(bundle, "checked", false);
            Log.d("FileOpService#Dlg", "onOptionSelected", obj, Integer.valueOf(i2), Boolean.valueOf(z));
            if (i2 == -2) {
                this.mLastJob.prepMode = 1;
            } else if (i2 == -3) {
                this.mLastJob.prepMode = 4;
            } else if (i2 == -1) {
                if (!z) {
                    this.mHelper.showRenameDialog(this.mLastJob.buildNewName());
                    return;
                }
                this.mLastJob.prepMode = 2;
            }
            if (z) {
                FileOpService fileOpService = this.mService;
                if (i2 == -2) {
                    i7 = 1;
                } else if (i2 == -3) {
                    i7 = 4;
                } else if (i2 == -1) {
                    i7 = 2;
                }
                fileOpService.mGlobalPrepMode = i7;
            }
            this.mService.mHandler.sendEmptyMessageDelayed(2, 0);
        }

        /* access modifiers changed from: private */
        public void onRename(Object obj, Bundle bundle) {
            FileOpJob fileOpJob = this.mLastJob;
            if (fileOpJob == null) {
                Log.e("FileOpService#Dlg", "onRename wrong arguments " + obj);
                return;
            }
            if (obj != null) {
                fileOpJob.prepMode = 3;
                fileOpJob.targetCustom = (String) obj;
            }
            Log.d("FileOpService#Dlg", "onRename", fileOpJob.source.getPath(), obj);
            this.mService.mHandler.sendEmptyMessageDelayed(2, 0);
        }

        public void dismiss() {
            DialogHelper dialogHelper = this.mHelper;
            if (dialogHelper != null) {
                dialogHelper.dismissDialog();
            }
            unsubscribe(this.mBlackboard);
        }

        public void init(Blackboard blackboard) {
            AnalyticsScreenId analyticsScreenId;
            if (this.mHelper == null) {
                this.mHelper = new NormalDialogHelper();
            }
            this.mSingleResolution = 100 / this.mService.mFileOpData.totalList.size();
            this.mHelper.init(blackboard);
            if (this.mService.mFileOpData.operation == 1) {
                analyticsScreenId = AnalyticsScreenId.SCREEN_COPY_TO_ALBUM;
            } else {
                analyticsScreenId = AnalyticsScreenId.SCREEN_MOVE_TO_ALBUM;
            }
            this.mScreenId = analyticsScreenId.toString();
            Blackboard blackboard2 = this.mBlackboard;
            if (blackboard2 != blackboard) {
                unsubscribe(blackboard2);
                this.mBlackboard = blackboard;
                subscribe(blackboard);
            }
        }

        public void show() {
            int i2;
            if (this.mHelper != null) {
                FileOpData fileOpData = this.mService.mFileOpData;
                int count = (int) fileOpData.totalList.stream().filter(new f(3)).count();
                int size = fileOpData.totalList.size() - count;
                String dialogTitle = FileOperationMsgMgr.getDialogTitle(this.mService, fileOpData.targetDir, fileOpData.isCopy(), fileOpData.isAlbumRename(), FileOperationMsgParams.builder().setImageCount(size).setVideoCount(count).setTotalCount(size + count).build());
                int size2 = this.mService.mFileOpData.totalList.size();
                int size3 = size2 - this.mService.mFileOpData.queue.size();
                if (size2 == 0) {
                    i2 = 0;
                } else {
                    i2 = (size3 * 100) / size2;
                }
                Log.d("FileOpService#Dlg", "show", dialogTitle, Integer.valueOf(size3), Integer.valueOf(size2), Integer.valueOf(i2));
                this.mHelper.showDialog(dialogTitle, size3, size2, i2);
            }
        }

        public void subscribe(Blackboard blackboard) {
            blackboard.subscribe("command://CancelDialog", this.mCancelListener);
            blackboard.subscribe("command://OperationSelected", this.mOptionListener);
            blackboard.subscribe("command://RenameSelected", this.mRenameListener);
        }

        public void unsubscribe(Blackboard blackboard) {
            if (blackboard != null) {
                blackboard.unsubscribe("command://CancelDialog", this.mCancelListener);
                blackboard.unsubscribe("command://OperationSelected", this.mOptionListener);
                blackboard.unsubscribe("command://RenameSelected", this.mRenameListener);
            }
        }

        public void update(int i2, int i7) {
            int i8;
            DialogHelper dialogHelper = this.mHelper;
            if (dialogHelper != null) {
                if (i7 == 0) {
                    i8 = 0;
                } else {
                    i8 = (i2 * 100) / i7;
                }
                this.mPercentage = i8;
                this.mPos = i2;
                this.mTotalCount = i7;
                this.mLastProgress = 0;
                dialogHelper.updateDialog(i2, i7, i8);
            }
        }

        public void waitForOptions(FileOpJob fileOpJob) {
            this.mLastJob = fileOpJob;
            this.mHelper.showFileOperationDialog(fileOpJob.source.getTitle(), fileOpJob.target, this.mScreenId);
        }

        public void update(float f) {
            int i2 = this.mSingleResolution;
            int min = Math.min((int) (((float) i2) * f), i2);
            if (min > this.mLastProgress) {
                this.mLastProgress = min;
                this.mHelper.updateDialog(this.mPos, this.mTotalCount, this.mPercentage + min);
            }
        }
    }
}
