package com.samsung.android.sdk.scs.base.tasks;

import com.samsung.android.sdk.scs.base.ResultException;
import com.samsung.android.sdk.scs.base.feature.FeatureStatusCache;
import com.samsung.android.sdk.scs.base.utils.Log;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TaskRunnable<TResult> implements Runnable {
    private static final String TAG = "ScsApi@TaskRunnable<>";
    private static final String TASK_DELIMITER = "#";
    private static final String THREAD_NAME_PREFIX = "scs";
    /* access modifiers changed from: protected */
    public final TaskCompletionSource<TResult> mSource;

    public TaskRunnable() {
        this(new TaskCompletionSource());
    }

    private String createThreadName(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        String str2 = str.split(TASK_DELIMITER)[0];
        if (!str2.startsWith(THREAD_NAME_PREFIX)) {
            str2 = "scs-".concat(str2);
        }
        StringBuilder t = C0212a.t(str2, TASK_DELIMITER);
        t.append(getClass().getSimpleName());
        t.append(Log.TAG_SEPARATOR);
        t.append(Integer.toHexString(hashCode()));
        return t.toString();
    }

    public abstract void execute();

    public abstract String getFeatureName();

    public TaskCompletionSource<TResult> getSource() {
        return this.mSource;
    }

    public Task<TResult> getTask() {
        return this.mSource.getTask();
    }

    public void run() {
        try {
            boolean interrupted = Thread.interrupted();
            Thread.currentThread().setName(createThreadName(Thread.currentThread().getName()));
            Log.i(TAG, "run() - " + Thread.currentThread() + ", interrupt : " + interrupted);
            int status = FeatureStatusCache.getStatus(getFeatureName());
            if (status == 0) {
                if (!interrupted) {
                    execute();
                    return;
                }
            }
            ResultException resultException = new ResultException(status, getFeatureName() + " is not available. statusCode: " + status + ", isInterrupted: " + interrupted);
            Log.i(TAG, resultException.getMessage());
            this.mSource.setException(resultException);
        } catch (Exception e) {
            Log.e(TAG, "Uncaught Exception!!!", e);
            this.mSource.trySetException(e);
        }
    }

    public TaskRunnable(TaskCompletionSource<TResult> taskCompletionSource) {
        this.mSource = taskCompletionSource;
    }
}
