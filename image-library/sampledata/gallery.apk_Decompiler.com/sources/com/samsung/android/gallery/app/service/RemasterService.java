package com.samsung.android.gallery.app.service;

import A.a;
import B8.g;
import N2.j;
import X3.C0413a;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import ba.C0582a;
import c4.C0439i;
import c4.C0440j;
import com.samsung.android.gallery.database.dbtype.MimeType;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.cloud.abstraction.CloudDownloadMonitor;
import com.samsung.android.gallery.module.cloud.abstraction.DownloadCanceller;
import com.samsung.android.gallery.module.cloud.abstraction.DownloadParams;
import com.samsung.android.gallery.module.cloud.sdk.CloudErrorType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.data.UriItemLoader;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.module.remaster.ErrorReason;
import com.samsung.android.gallery.module.remaster.RemasterHelper;
import com.samsung.android.gallery.module.service.abstraction.BaseService;
import com.samsung.android.gallery.module.service.notification.RemasterNotificationHelper;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MemLog;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import java.io.File;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class RemasterService extends BaseService {
    private Blackboard mBlackboard = null;
    private final Consumer<RemasterHelper.Result> mCompleteListener = new C0439i(this, 1);
    private boolean mIsInterrupted = false;
    private MediaItem mMediaItem;
    private RemasterNotificationHelper mNotificationHelper = null;
    private volatile Handler mProgressHandler;
    private final Consumer<Double> mProgressListener = new C0439i(this, 2);
    private final Object mRemasterLock = new Object();
    private RemasterWorker mRemasterWorker;
    private HandlerThread mThread = null;
    private volatile Looper mThreadLooper;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RemasterWorker extends Thread {
        private static final String DOWNLOAD_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
        private final Blackboard mBlackboard;
        private final DownloadCanceller mCanceller = SamsungCloudCompat.getDownloadCanceller();
        private final Consumer<RemasterHelper.Result> mCompleteConsumer;
        private final MediaItem mMediaItem;
        private final CloudDownloadMonitor mMonitor = SamsungCloudCompat.getDownloadMonitor();
        private final RemasterHelper mRemasterHelper;

        public RemasterWorker(MediaItem mediaItem, Blackboard blackboard, Consumer<RemasterHelper.Result> consumer) {
            File file;
            String str;
            this.mMediaItem = mediaItem;
            this.mCompleteConsumer = consumer;
            this.mBlackboard = blackboard;
            Activity readActivity = BlackboardUtils.readActivity(blackboard);
            if (readActivity != null) {
                file = readActivity.getCacheDir();
            } else {
                file = null;
            }
            if (file != null) {
                str = file.getAbsolutePath();
            } else {
                str = "";
            }
            MemLog memLog = new MemLog("RMC");
            this.mRemasterHelper = new RemasterHelper(SeApiCompat.createVslMesDetectorCompat(str));
            memLog.check();
        }

        private RemasterHelper.Result checkInterrupt(RemasterHelper.Result result) {
            if (this.mRemasterHelper.isInterrupted()) {
                return RemasterHelper.INTERRUPTED_RESULT;
            }
            return result;
        }

        private MediaItem download(MediaItem mediaItem) {
            if (this.mRemasterHelper.isInterrupted()) {
                Log.d("RemasterService", "RemasterWorker interrupted download");
                return mediaItem;
            }
            Log.d("RemasterService", "RemasterWorker before download=" + getItemInfo(mediaItem));
            String originalFilePath = SamsungCloudCompat.getOriginalFilePath(mediaItem.getCloudServerPath());
            if (originalFilePath == null) {
                originalFilePath = DOWNLOAD_PATH + File.separator + mediaItem.getTitle();
            }
            MediaItem resultItem = getResultItem(SamsungCloudCompat.download(AppResources.getAppContext(), DownloadParams.builder().setFileItemInterface(mediaItem).setTargetPath(originalFilePath).setDownloadCanceller(this.mCanceller).setDownloadMonitor(this.mMonitor).build()));
            if (resultItem != null) {
                resultItem.setTag("ViewerBitmapKey", MediaItemUtil.getViewerBitmapKey(mediaItem));
                BlackboardUtils.forceRefreshPicturesData(this.mBlackboard, false);
                this.mBlackboard.postEvent(EventMessage.obtain(3037, resultItem));
                resultItem = resultItem.clone();
                MediaItemUtil.setRemasterAutosave(resultItem, MediaItemUtil.isRemasterAutosave(mediaItem));
            }
            j.y(new StringBuilder("RemasterWorker after download="), getItemInfo(resultItem), "RemasterService");
            return resultItem;
        }

        private RemasterHelper.Result fakeRemastering() {
            Log.d("RemasterService", "RemasterWorker start{fake}");
            return this.mRemasterHelper.fakeRemaster(this.mMediaItem);
        }

        private String getItemInfo(MediaItem mediaItem) {
            if (mediaItem == null) {
                return "item is null";
            }
            return mediaItem.getFileId() + "";
        }

        private long getRemasterType(MediaItem mediaItem) {
            long revitalizedType = MediaItemSuggest.getRevitalizedType(mediaItem);
            int widthInDB = mediaItem.getWidthInDB();
            int heightInDB = mediaItem.getHeightInDB();
            if (mediaItem.isGif()) {
                return 11;
            }
            if (revitalizedType > 0) {
                return revitalizedType;
            }
            if (!Features.isEnabled(Features.SUPPORT_ON_DEMAND_REMASTER_GIF) && this.mRemasterHelper.supportUpscaleType(widthInDB, heightInDB)) {
                return 1;
            }
            if (revitalizedType == -1) {
                return 7;
            }
            return 0;
        }

        private MediaItem getResultItem(ArrayList<Uri> arrayList) {
            ArrayList arrayList2 = new ArrayList();
            if (arrayList == null || arrayList.isEmpty() || hasError(arrayList.get(0))) {
                Log.w("RemasterService", "Couldn't download image: ");
                return null;
            }
            for (int i2 = 0; i2 < 5; i2++) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (UriItemLoader.loadMediaItemFromUris(arrayList, arrayList2)) {
                    return (MediaItem) arrayList2.get(0);
                }
                Log.w("RemasterService", "load mediaItem from uri : " + i2);
            }
            Log.w("RemasterService", "Couldn't load a media item from content uri");
            return null;
        }

        private boolean hasError(Uri uri) {
            if (uri == null || CloudErrorType.None != CloudErrorType.parseOf(uri)) {
                return true;
            }
            return false;
        }

        private RemasterHelper.Result remastering() {
            RemasterHelper.Result result;
            Log.d("RemasterService", "RemasterWorker start{remastering}");
            MediaItem mediaItem = this.mMediaItem;
            long remasterType = getRemasterType(mediaItem);
            if (mediaItem.isCloudOnly()) {
                mediaItem = download(mediaItem);
            }
            MemLog memLog = new MemLog("RM");
            if (mediaItem != null) {
                result = this.mRemasterHelper.remaster(mediaItem, remasterType);
            } else {
                result = RemasterHelper.INTERRUPTED_RESULT;
            }
            memLog.check();
            if (result.success) {
                updateRemasteredItem(mediaItem, result);
            }
            return result;
        }

        private void updateRemasteredItem(MediaItem mediaItem, RemasterHelper.Result result) {
            if (mediaItem != null) {
                String str = result.path;
                BitmapOptions parse = BitmapOptionsFactory.parse(str);
                mediaItem.setSize(parse.outWidth, parse.outHeight);
                MediaItemSuggest.setOriginalPath(mediaItem, mediaItem.getPath());
                MediaItemSuggest.setRevitalizedResultType(mediaItem, result.type);
                mediaItem.setPath(str);
                mediaItem.setMimeType(MimeType.getMimeType(str).mimeType);
                mediaItem.setRevitalization();
            }
        }

        private void waitThread(long j2) {
            try {
                synchronized (this) {
                    wait(j2);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void interruptRemaster() {
            this.mRemasterHelper.interrupt();
            synchronized (this) {
                notify();
            }
        }

        public void run() {
            RemasterHelper.Result result;
            long j2;
            String str;
            String str2;
            long currentTimeMillis = System.currentTimeMillis();
            if (FileUtils.exists(MediaItemSuggest.getRemasterPath(this.mMediaItem))) {
                result = fakeRemastering();
            } else {
                result = remastering();
            }
            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            if (result.success) {
                j2 = 3500 - currentTimeMillis2;
            } else {
                if (!this.mRemasterHelper.isInterrupted()) {
                    this.mBlackboard.postEvent(EventMessage.obtain(3036, result.errorReason));
                }
                j2 = 0;
            }
            StringBuilder sb2 = new StringBuilder("RemasterWorker done {");
            if (this.mRemasterHelper.isInterrupted()) {
                str = "interrupted";
            } else {
                str = "-";
            }
            sb2.append(str);
            sb2.append(',');
            if (result.success) {
                str2 = "success";
            } else {
                str2 = "failed";
            }
            sb2.append(str2);
            sb2.append(',');
            sb2.append(j2);
            sb2.append("} +");
            sb2.append(currentTimeMillis2);
            Log.d("RemasterService", sb2.toString());
            if (j2 > 0) {
                waitThread(j2);
            }
            this.mCompleteConsumer.accept(checkInterrupt(result));
        }

        public void setProgressListener(Consumer<Double> consumer) {
            RemasterHelper remasterHelper = this.mRemasterHelper;
            if (remasterHelper != null) {
                remasterHelper.setProgressListener(consumer);
            }
        }
    }

    private void deleteInterimFile(String str) {
        if (!TextUtils.isEmpty(str)) {
            SecureFile secureFile = new SecureFile(str);
            if (secureFile.exists()) {
                a.v("delete remaster interim file [", "]", "RemasterService", secureFile.delete());
            }
        }
    }

    private int getNotificationId() {
        return -1951943040;
    }

    private int getNotificationText(MediaItem mediaItem) {
        return RemasterHelper.getProcessingMessage(mediaItem);
    }

    private boolean init(Intent intent) {
        MediaItem mediaItem;
        Blackboard instance = Blackboard.getInstance(intent.getStringExtra("blackboard_name"));
        this.mBlackboard = instance;
        if (instance == null) {
            Log.e("RemasterService", "blackboard is null. prepare failed.");
            return false;
        }
        MediaItem[] mediaItemArr = (MediaItem[]) instance.pop("data://user/selected");
        if (mediaItemArr == null || mediaItemArr.length < 1 || (mediaItem = mediaItemArr[0]) == null) {
            return false;
        }
        this.mMediaItem = mediaItem;
        Log.d("RemasterService", "init", Long.valueOf(mediaItem.getFileId()), Long.valueOf(MediaItemSuggest.getRevitalizedType(this.mMediaItem)));
        registerServiceRunning();
        initNotificationHelper();
        startForeground(this.mNotificationHelper.getSummaryId(), this.mNotificationHelper.getSummaryNotification());
        startRemasterWorker();
        return true;
    }

    private void initNotificationHelper() {
        this.mNotificationHelper = new RemasterNotificationHelper(this, getNotificationId(), "RemasterService", "com.samsung.android.gallery.app.service.RemasterService");
        String string = getString(getNotificationText(this.mMediaItem));
        this.mNotificationHelper.create(string);
        this.mNotificationHelper.show(this, string);
    }

    private void interruptRemaster() {
        if (!this.mIsInterrupted) {
            Log.d("RemasterService", "interruptRemaster");
            Blackboard blackboard = this.mBlackboard;
            if (blackboard != null) {
                blackboard.postEvent(EventMessage.obtain(3036, ErrorReason.CANCEL));
            }
            RemasterWorker remasterWorker = this.mRemasterWorker;
            if (remasterWorker != null) {
                SimpleThreadPool.getInstance().execute(new d(1, remasterWorker));
            }
            this.mIsInterrupted = true;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCompleted$0(boolean z, RemasterHelper.Result result) {
        if (z) {
            onSuccess();
        } else {
            onFail(result);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCompleted$1(boolean z, RemasterHelper.Result result, Handler handler) {
        handler.post(new g((Object) this, z, (Object) result, 9));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onInterruptService$2(Handler handler) {
        handler.post(new C0440j(this, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onTerminateService$3(Handler handler) {
        handler.post(new C0440j(this, 1));
    }

    private void notifyResultForRemaster(RemasterHelper.Result result) {
        Optional.ofNullable(this.mBlackboard).ifPresent(new C0582a(4, result));
    }

    private void onCallActivity() {
        Log.d("RemasterService", "ignore onCallActivity");
    }

    /* access modifiers changed from: private */
    public void onCompleted(RemasterHelper.Result result) {
        boolean z;
        if (!result.success || !FileUtils.exists(result.path)) {
            z = false;
        } else {
            z = true;
        }
        a.v("remaster completed [", "]", "RemasterService", z);
        unregisterServiceRunning();
        Optional.ofNullable(this.mProgressHandler).ifPresent(new C0413a(this, z, result));
        this.mIsInterrupted = false;
        notifyResultForRemaster(result);
        onTerminateService();
    }

    private void onFail(RemasterHelper.Result result) {
        synchronized (this.mRemasterLock) {
            deleteInterimFile(result.path);
            stopForeground(true);
        }
    }

    /* access modifiers changed from: private */
    public void onInterruptInternal() {
        synchronized (this.mRemasterLock) {
            interruptRemaster();
            Log.d("RemasterService", "interrupting task.");
        }
    }

    private void onInterruptService() {
        Log.d("RemasterService", "onInterruptService");
        RemasterNotificationHelper remasterNotificationHelper = this.mNotificationHelper;
        if (remasterNotificationHelper != null) {
            remasterNotificationHelper.showStopNotification(this, getString(RemasterHelper.getErrorReasonText(this.mMediaItem)));
        }
        Optional.ofNullable(this.mProgressHandler).ifPresent(new C0439i(this, 0));
    }

    /* access modifiers changed from: private */
    public void onProgress(Double d) {
        Blackboard blackboard = this.mBlackboard;
        if (blackboard != null) {
            blackboard.postEvent(EventMessage.obtain(3045, d));
        }
    }

    private void onStartService(Intent intent) {
        if (init(intent)) {
            Log.d("RemasterService", "service#start");
            return;
        }
        onTerminateService();
        Log.w("RemasterService", "service#start failed. terminated");
    }

    private void onSuccess() {
        synchronized (this.mRemasterLock) {
            Log.d("RemasterService", "succeed remaster");
            stopForeground(true);
        }
    }

    private void onTerminateService() {
        RemasterNotificationHelper remasterNotificationHelper = this.mNotificationHelper;
        if (remasterNotificationHelper != null) {
            remasterNotificationHelper.dismiss();
        }
        Optional.ofNullable(this.mProgressHandler).ifPresent(new C0439i(this, 3));
    }

    private void registerServiceRunning() {
        Blackboard.getApplicationInstance().publish("data://running_media_remaster", Boolean.TRUE);
    }

    private void startRemasterWorker() {
        this.mRemasterWorker = new RemasterWorker(this.mMediaItem, this.mBlackboard, this.mCompleteListener);
        if (this.mMediaItem.isGif()) {
            this.mRemasterWorker.setProgressListener(this.mProgressListener);
        }
        this.mRemasterWorker.start();
        Log.d("RemasterService", "start remaster");
    }

    private void threadInit() {
        HandlerThread handlerThread = new HandlerThread("RemasterService");
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
        this.mRemasterWorker = null;
        unregisterServiceRunning();
    }

    private void unregisterServiceRunning() {
        Blackboard.getApplicationInstance().erase("data://running_media_remaster");
    }

    public void onCreate() {
        super.onCreate();
        threadInit();
    }

    public void onDestroy() {
        boolean z;
        RemasterWorker remasterWorker = this.mRemasterWorker;
        if (remasterWorker == null || !remasterWorker.isAlive()) {
            z = false;
        } else {
            z = true;
        }
        a.v("service#destroy [", "]", "RemasterService", z);
        if (z) {
            onInterruptInternal();
        }
        threadRelease();
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int i2, int i7) {
        if (intent != null) {
            String action = intent.getAction();
            Log.d("RemasterService", "service receives [" + action + "]");
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
                    case 2:
                        onInterruptService();
                        break;
                    case 1:
                        onStartService(intent);
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
            Log.w("RemasterService", "unable to operate startCommand");
        }
        return 2;
    }
}
