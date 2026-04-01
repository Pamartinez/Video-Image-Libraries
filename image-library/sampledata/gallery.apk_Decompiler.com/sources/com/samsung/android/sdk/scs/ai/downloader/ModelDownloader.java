package com.samsung.android.sdk.scs.ai.downloader;

import android.content.Context;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sdk.scs.base.utils.Log;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ModelDownloader {
    public static final int CHECK_RESULT_AVAILABLE = 0;
    public static final int CHECK_RESULT_NEED_DOWNLOAD = 1;
    public static final int CHECK_RESULT_NETWORK_UNAVAILABLE = -3;
    public static final int CHECK_RESULT_NOT_SUPPORTED = -1;
    public static final int CHECK_RESULT_SERVER_ERROR = -5;
    public static final int CHECK_RESULT_STORE_NOT_FOUND = -4;
    public static final int CHECK_RESULT_TIMEOUT = -6;
    public static final int CHECK_RESULT_UNKNOWN_FEATURE = -2;
    public static final String MODEL_FEATURE_LITERT = "litert";
    public static final String MODEL_FEATURE_UNKNOWN = "unknown";
    public static final String MODEL_FEATURE_VISIONLVM = "visionlvm";
    private static final String TAG = "ScsApi@ModelDownloader";
    private final ModelDownloaderCoreExecutor mCoreServicedExecutor;
    private final ModelDownloaderSequentialExecutor mExecutor;
    private final ModelDownloaderExecutor mServiceExecutor;

    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public @interface CheckResult {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public @interface ModelFeature {
    }

    public ModelDownloader(Context context) {
        Log.d(TAG, "ModelDownloader");
        this.mServiceExecutor = new ModelDownloaderExecutor(context);
        this.mCoreServicedExecutor = new ModelDownloaderCoreExecutor(context);
        this.mExecutor = new ModelDownloaderSequentialExecutor(context);
    }

    public Task<Boolean> cancel(String str) {
        Log.d(TAG, "cancel(" + str + ")");
        ModelDownloaderCancelRunnable modelDownloaderCancelRunnable = new ModelDownloaderCancelRunnable(this.mCoreServicedExecutor);
        modelDownloaderCancelRunnable.setParameters(str);
        this.mCoreServicedExecutor.execute(modelDownloaderCancelRunnable);
        return modelDownloaderCancelRunnable.getTask();
    }

    public Task<Integer> check(String str) {
        Log.d(TAG, "check(" + str + ")");
        ModelDownloaderCheckAndDownloadRunnable modelDownloaderCheckAndDownloadRunnable = new ModelDownloaderCheckAndDownloadRunnable(this.mCoreServicedExecutor);
        modelDownloaderCheckAndDownloadRunnable.setParameter(str, true, false);
        Log.d(TAG, "core task: " + modelDownloaderCheckAndDownloadRunnable.getTask().getTaskId());
        ModelDownloaderCheckRunnable modelDownloaderCheckRunnable = new ModelDownloaderCheckRunnable(this.mServiceExecutor);
        modelDownloaderCheckRunnable.setParameters(str);
        Log.d(TAG, "cloud task: " + modelDownloaderCheckRunnable.getTask().getTaskId());
        ModelDownloaderSequentialRunnable modelDownloaderSequentialRunnable = new ModelDownloaderSequentialRunnable();
        modelDownloaderSequentialRunnable.setDefaultResult(-6);
        modelDownloaderSequentialRunnable.addTask(modelDownloaderCheckAndDownloadRunnable, this.mCoreServicedExecutor);
        modelDownloaderSequentialRunnable.addTask(modelDownloaderCheckRunnable, this.mServiceExecutor);
        this.mExecutor.execute(modelDownloaderSequentialRunnable);
        return modelDownloaderSequentialRunnable.getTask();
    }

    public Task<Integer> checkPackage(String str) {
        Log.d(TAG, "checkPackage(" + str + ")");
        ModelDownloaderCheckAndDownloadPackageRunnable modelDownloaderCheckAndDownloadPackageRunnable = new ModelDownloaderCheckAndDownloadPackageRunnable(this.mServiceExecutor);
        modelDownloaderCheckAndDownloadPackageRunnable.setParameter(str, true, false);
        Log.d(TAG, "cloud task: " + modelDownloaderCheckAndDownloadPackageRunnable.getTask().getTaskId());
        this.mServiceExecutor.execute(modelDownloaderCheckAndDownloadPackageRunnable);
        return modelDownloaderCheckAndDownloadPackageRunnable.getTask();
    }

    public Task<Integer> checkStore(String str) {
        Log.d(TAG, "check(" + str + ")");
        ModelDownloaderCheckAndDownloadRunnable modelDownloaderCheckAndDownloadRunnable = new ModelDownloaderCheckAndDownloadRunnable(this.mCoreServicedExecutor);
        modelDownloaderCheckAndDownloadRunnable.setParameter(str, false, false);
        Log.d(TAG, "core task: " + modelDownloaderCheckAndDownloadRunnable.getTask().getTaskId());
        ModelDownloaderCheckStoreRunnable modelDownloaderCheckStoreRunnable = new ModelDownloaderCheckStoreRunnable(this.mServiceExecutor);
        modelDownloaderCheckStoreRunnable.setParameters(str);
        Log.d(TAG, "cloud task: " + modelDownloaderCheckStoreRunnable.getTask().getTaskId());
        ModelDownloaderSequentialRunnable modelDownloaderSequentialRunnable = new ModelDownloaderSequentialRunnable();
        modelDownloaderSequentialRunnable.setDefaultResult(-6);
        modelDownloaderSequentialRunnable.addTask(modelDownloaderCheckAndDownloadRunnable, this.mCoreServicedExecutor);
        modelDownloaderSequentialRunnable.addTask(modelDownloaderCheckStoreRunnable, this.mServiceExecutor);
        this.mExecutor.execute(modelDownloaderSequentialRunnable);
        return modelDownloaderSequentialRunnable.getTask();
    }

    public Task<Integer> checkStorePackage(String str) {
        Log.d(TAG, "checkStorePackage(" + str + ")");
        ModelDownloaderCheckAndDownloadPackageRunnable modelDownloaderCheckAndDownloadPackageRunnable = new ModelDownloaderCheckAndDownloadPackageRunnable(this.mServiceExecutor);
        modelDownloaderCheckAndDownloadPackageRunnable.setParameter(str, false, false);
        Log.d(TAG, "cloud task: " + modelDownloaderCheckAndDownloadPackageRunnable.getTask().getTaskId());
        this.mServiceExecutor.execute(modelDownloaderCheckAndDownloadPackageRunnable);
        return modelDownloaderCheckAndDownloadPackageRunnable.getTask();
    }

    public Task<Integer> download(String str) {
        Log.d(TAG, "download(" + str + ")");
        ModelDownloaderCheckAndDownloadRunnable modelDownloaderCheckAndDownloadRunnable = new ModelDownloaderCheckAndDownloadRunnable(this.mCoreServicedExecutor);
        modelDownloaderCheckAndDownloadRunnable.setParameter(str, false, true);
        Log.d(TAG, "core task: " + modelDownloaderCheckAndDownloadRunnable.getTask().getTaskId());
        ModelDownloaderDownloadRunnable modelDownloaderDownloadRunnable = new ModelDownloaderDownloadRunnable(this.mServiceExecutor);
        modelDownloaderDownloadRunnable.setParameters(str);
        ModelDownloaderSequentialRunnable modelDownloaderSequentialRunnable = new ModelDownloaderSequentialRunnable();
        modelDownloaderSequentialRunnable.setDefaultResult(-6);
        modelDownloaderSequentialRunnable.addTask(modelDownloaderCheckAndDownloadRunnable, this.mCoreServicedExecutor);
        modelDownloaderSequentialRunnable.addTask(modelDownloaderDownloadRunnable, this.mServiceExecutor);
        this.mExecutor.execute(modelDownloaderSequentialRunnable);
        return modelDownloaderSequentialRunnable.getTask();
    }

    public Task<Boolean> downloadCore(String str) {
        Log.d(TAG, "download(" + str + ")");
        ModelDownloaderCoreDownloadRunnable modelDownloaderCoreDownloadRunnable = new ModelDownloaderCoreDownloadRunnable(this.mCoreServicedExecutor);
        modelDownloaderCoreDownloadRunnable.setParameters(str);
        this.mCoreServicedExecutor.execute(modelDownloaderCoreDownloadRunnable);
        return modelDownloaderCoreDownloadRunnable.getTask();
    }

    public Task<Integer> downloadPackage(String str) {
        Log.d(TAG, "downloadPackage(" + str + ")");
        ModelDownloaderCheckAndDownloadPackageRunnable modelDownloaderCheckAndDownloadPackageRunnable = new ModelDownloaderCheckAndDownloadPackageRunnable(this.mServiceExecutor);
        modelDownloaderCheckAndDownloadPackageRunnable.setParameter(str, false, true);
        Log.d(TAG, "cloud task: " + modelDownloaderCheckAndDownloadPackageRunnable.getTask().getTaskId());
        this.mServiceExecutor.execute(modelDownloaderCheckAndDownloadPackageRunnable);
        return modelDownloaderCheckAndDownloadPackageRunnable.getTask();
    }

    public void release() {
        Log.d(TAG, "release()");
        this.mCoreServicedExecutor.deInit();
        this.mServiceExecutor.deInit();
    }
}
