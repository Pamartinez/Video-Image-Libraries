package com.samsung.android.gallery.app.service;

import B8.d;
import I3.f;
import V8.a;
import W8.C0579a;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.ResultReceiver;
import androidx.core.app.NotificationCompat;
import ba.C0582a;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.service.abstraction.BaseService;
import com.samsung.android.gallery.module.service.download.CloudDownloadTask;
import com.samsung.android.gallery.module.service.download.DownloadTask;
import com.samsung.android.gallery.module.service.download.WifiClientDownloadTask;
import com.samsung.android.gallery.module.service.notification.DownloadNotificationHelper;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DownloadService extends BaseService implements DownloadTask.Callback {
    private static final int SERVICE_RESUME_NOTIFICATION_ID = System.identityHashCode("DownloadService:continue-timeout");
    private Blackboard mBlackboard;
    private final AtomicBoolean mIsServiceTimeout = new AtomicBoolean(false);
    protected final HashSet<Integer> mMarks = new HashSet<>();
    protected DownloadNotificationHelper mNotificationHelper;
    protected final ConcurrentHashMap<Integer, DownloadTask> mTasks = new ConcurrentHashMap<>();

    private int executeTask(MediaItem[] mediaItemArr, Intent intent) {
        DownloadTask createDownloadTask = createDownloadTask(intent);
        createDownloadTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new MediaItem[][]{mediaItemArr});
        int notificationId = createDownloadTask.getNotificationId();
        synchronized (this.mMarks) {
            this.mTasks.put(Integer.valueOf(notificationId), createDownloadTask);
            this.mMarks.add(Integer.valueOf(notificationId));
        }
        return notificationId;
    }

    private void initNotificationHelper() {
        if (this.mNotificationHelper == null) {
            DownloadNotificationHelper downloadNotificationHelper = new DownloadNotificationHelper(this, this.TAG, getServiceClassName());
            this.mNotificationHelper = downloadNotificationHelper;
            downloadNotificationHelper.create(getString(R.string.download));
            this.mNotificationHelper.dismiss(SERVICE_RESUME_NOTIFICATION_ID);
        }
    }

    private boolean isTaskEmpty() {
        return this.mTasks.isEmpty();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$onDownloadFinish$0(Map.Entry entry) {
        return entry.getKey() + "=" + ((DownloadTask) entry.getValue()).getRemains().size();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onDownloadFinish$2(ArrayList arrayList, String str, ArrayList arrayList2, DownloadNotificationHelper downloadNotificationHelper, Blackboard blackboard) {
        String str2 = this.TAG;
        StringBuilder sb2 = new StringBuilder("onDownloadFinish > resume notification due to service timeout");
        Integer valueOf = Integer.valueOf(arrayList.size());
        int i2 = SERVICE_RESUME_NOTIFICATION_ID;
        sb2.append(Logger.v(valueOf, str, Integer.valueOf(i2), TimeUtil.getIsoLocalDateTime(this.mStartTime)));
        Log.majorEvent(str2, sb2.toString());
        Objects.requireNonNull(downloadNotificationHelper);
        arrayList2.forEach(new C0582a(3, downloadNotificationHelper));
        blackboard.publish("data://user/selected", arrayList.toArray(new MediaItem[0]));
        NotificationCompat.Builder contentTitle = downloadNotificationHelper.getServiceResumeBuilder(this, i2, blackboard.getName()).setContentTitle(AppResources.getString(R.string.failed_download_items));
        downloadNotificationHelper.notify(i2, contentTitle.setContentText(arrayList.size() + " items are not completed due to service timeout").build());
    }

    private void onCallActivity(Intent intent) {
        Log.d(this.TAG, "downloaded notification is selected");
        startGalleryActivity(intent);
        onTerminateService(intent, true);
    }

    private void onTerminateService(Intent intent, boolean z) {
        int intExtra = intent.getIntExtra("notification_id", -1);
        Log.d(this.TAG, "onTerminateService", Integer.valueOf(intExtra));
        if (intExtra != -1) {
            synchronized (this.mMarks) {
                this.mTasks.remove(Integer.valueOf(intExtra));
                this.mMarks.remove(Integer.valueOf(intExtra));
            }
            if (z) {
                initNotificationHelper();
                this.mNotificationHelper.dismiss(intExtra);
            }
            stopService();
        }
    }

    private void startViewer(ArrayList<Uri> arrayList) {
        Log.d(this.TAG, "start viewer", Integer.valueOf(arrayList.size()));
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.activity.external.GalleryExternalActivity");
        intent.setData(arrayList.get(0));
        intent.putExtra("uriListData", arrayList);
        intent.putExtra("useUriList", true);
        intent.addFlags(335544320);
        startActivity(intent);
    }

    public DownloadTask createDownloadTask(Intent intent) {
        DownloadTask downloadTask;
        String stringExtra = intent.getStringExtra("location_key");
        ResultReceiver resultReceiver = (ResultReceiver) intent.getParcelableExtra("download_result_receiver");
        if (PocFeatures.isEnabled(PocFeatures.WifiGalleryClient)) {
            downloadTask = new WifiClientDownloadTask(this);
        } else {
            downloadTask = new CloudDownloadTask(this);
        }
        return downloadTask.setCallback(this).setNotificationHelper(this.mNotificationHelper).setResultReceiver(resultReceiver).setLocationKey(stringExtra);
    }

    public String getServiceClassName() {
        return "com.samsung.android.gallery.app.service.DownloadService";
    }

    public void onDestroy() {
        super.onDestroy();
        synchronized (this.mMarks) {
            this.mMarks.clear();
            this.mTasks.clear();
        }
    }

    public void onDownloadEnd(int i2, boolean z) {
        Log.d(this.TAG, "onDownloadEnd", Integer.valueOf(i2), Boolean.valueOf(z));
        if (z && !SdkConfig.atLeast(SdkConfig.GED.R)) {
            BlackboardUtils.forceRefreshPicturesData(this.mBlackboard, !SdkConfig.atLeast(SdkConfig.GED.Q));
        }
        synchronized (this.mMarks) {
            try {
                this.mMarks.remove(Integer.valueOf(i2));
                if (this.mMarks.isEmpty()) {
                    onDownloadFinish();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void onDownloadFinish() {
        DownloadService downloadService;
        DownloadNotificationHelper downloadNotificationHelper;
        stopForeground(false);
        String str = (String) this.mTasks.entrySet().stream().map(new a(27)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX, "[", "]"));
        Log.i(this.TAG, "onDownloadFinish", Boolean.valueOf(this.mIsServiceTimeout.get()), str);
        if (this.mIsServiceTimeout.get()) {
            ArrayList arrayList = new ArrayList();
            this.mTasks.values().forEach(new d(arrayList, 27));
            if (arrayList.isEmpty() || (downloadNotificationHelper = this.mNotificationHelper) == null) {
                downloadService = this;
            } else {
                ArrayList arrayList2 = new ArrayList(this.mTasks.keySet());
                arrayList2.add(Integer.valueOf(downloadNotificationHelper.getSummaryId()));
                downloadService = this;
                ThreadUtil.postOnUiThreadDelayed(new f(downloadService, arrayList, str, arrayList2, downloadNotificationHelper, Blackboard.getApplicationInstance()), 10);
            }
            downloadService.stopSelf();
        }
    }

    public void onDownloadStart(int i2) {
        Log.d(this.TAG, "onDownloadStart", Integer.valueOf(i2));
    }

    public void onInterruptService(Intent intent) {
        int intExtra = intent.getIntExtra("notification_id", -1);
        Log.d(this.TAG, "onInterruptService", Integer.valueOf(intExtra));
        if (intExtra != -1) {
            DownloadTask downloadTask = this.mTasks.get(Integer.valueOf(intExtra));
            if (downloadTask != null) {
                downloadTask.interrupt();
            } else {
                DownloadNotificationHelper downloadNotificationHelper = this.mNotificationHelper;
                if (downloadNotificationHelper != null) {
                    downloadNotificationHelper.dismiss(intExtra);
                }
            }
            synchronized (this.mMarks) {
                this.mTasks.remove(Integer.valueOf(intExtra));
                this.mMarks.remove(Integer.valueOf(intExtra));
            }
        }
    }

    public int onStartCommand(Intent intent, int i2, int i7) {
        if (intent != null) {
            String action = intent.getAction();
            Log.d(this.TAG, "service receives", Integer.valueOf(i7), Logger.getSimpleName(action));
            if (action == null) {
                return 2;
            }
            switch (action.hashCode()) {
                case -670797158:
                    if (action.equals("com.samsung.android.gallery.app.service.STOP_SERVICE")) {
                        onInterruptService(intent);
                        return 2;
                    }
                    break;
                case 980299926:
                    if (action.equals("com.samsung.android.gallery.app.service.START_SERVICE")) {
                        onStartService(intent);
                        return 2;
                    }
                    break;
                case 1082223299:
                    boolean equals = action.equals("com.samsung.android.gallery.app.service.DELETE_SERVICE");
                    break;
                case 1768765646:
                    if (action.equals("com.samsung.android.gallery.app.service.CALL_ACTIVITY")) {
                        onCallActivity(intent);
                        return 2;
                    }
                    break;
            }
            onTerminateService(intent, false);
            return 2;
        }
        Log.e(this.TAG, "service receives skip. null intent");
        return 2;
    }

    public void onStartService(Intent intent) {
        Blackboard blackboard;
        int size = this.mTasks.size();
        String stringExtra = intent.getStringExtra("blackboard_name");
        if (stringExtra == null) {
            blackboard = null;
        } else {
            blackboard = Blackboard.getInstance(stringExtra);
        }
        if (blackboard == null) {
            Log.w((CharSequence) this.TAG, "onStartService skip. null blackboard", Integer.valueOf(size));
            if (size == 0) {
                stopSelf();
                return;
            }
            return;
        }
        this.mBlackboard = blackboard;
        MediaItem[] mediaItemArr = (MediaItem[]) blackboard.pop("data://user/selected");
        if (mediaItemArr == null || mediaItemArr.length == 0) {
            Log.w((CharSequence) this.TAG, "onStartService skip. empty items", Integer.valueOf(size));
            if (size == 0) {
                stopSelf();
                return;
            }
            return;
        }
        initNotificationHelper();
        boolean booleanExtra = intent.getBooleanExtra("is_download_service_resume", false);
        int executeTask = executeTask(mediaItemArr, intent);
        String str = this.TAG;
        Log.i(str, "onStartService" + Logger.v(Integer.valueOf(executeTask), Integer.valueOf(size), Boolean.valueOf(booleanExtra)));
        startForeground(this.mNotificationHelper.getSummaryId(), this.mNotificationHelper.getSummaryNotification());
    }

    public void onTimeout(int i2, int i7) {
        super.onTimeout(i2, i7);
        this.mIsServiceTimeout.set(true);
        this.mTasks.values().forEach(new C0579a(26));
    }

    public void startGalleryActivity(Intent intent) {
        ArrayList arrayList = UnsafeCast.toArrayList(intent.getSerializableExtra("notification_data"));
        if (arrayList == null || arrayList.isEmpty()) {
            startMain();
        } else {
            startViewer(arrayList);
        }
    }

    public void startMain() {
        Log.d(this.TAG, "start gallery for empty uri");
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.activity.GalleryActivity");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.addFlags(805306368);
        startActivity(intent);
    }

    public final void stopService() {
        if (isTaskEmpty()) {
            Log.d(this.TAG, "stopService");
            stopSelf();
            return;
        }
        Log.w(this.TAG, "stopService skip. task remained");
    }

    public void onDownloadProgress(int i2) {
    }
}
