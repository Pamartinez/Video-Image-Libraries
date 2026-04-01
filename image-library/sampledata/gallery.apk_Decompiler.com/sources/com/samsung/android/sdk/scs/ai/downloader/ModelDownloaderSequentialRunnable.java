package com.samsung.android.sdk.scs.ai.downloader;

import a6.g;
import android.util.Log;
import android.util.Pair;
import com.samsung.android.sdk.scs.base.connection.ServiceExecutor;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ModelDownloaderSequentialRunnable<TResult> extends TaskRunnable<TResult> {
    private static final int RUNNABLE_TIMEOUT_SECONDS = 60;
    private static final String TAG = "ModelDownloaderSequentialRunnable";
    private TResult mDefault;
    private final ArrayList<Pair<TaskRunnable<TResult>, ServiceExecutor>> mTasks = new ArrayList<>();

    public void addTask(TaskRunnable<TResult> taskRunnable, ServiceExecutor serviceExecutor) {
        String str = TAG;
        Log.d(str, "addTask: " + taskRunnable.getTask().getTaskId());
        this.mTasks.add(new Pair(taskRunnable, serviceExecutor));
    }

    public void execute() {
        boolean z;
        String str = TAG;
        Log.d(str, "execute");
        if (this.mTasks.isEmpty()) {
            this.mSource.setException(new Exception("Empty task"));
            Log.e(str, "execute: Empty task");
            return;
        }
        Iterator<Pair<TaskRunnable<TResult>, ServiceExecutor>> it = this.mTasks.iterator();
        Exception exc = null;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Pair next = it.next();
            CountDownLatch countDownLatch = new CountDownLatch(1);
            TaskRunnable taskRunnable = (TaskRunnable) next.first;
            Task task = taskRunnable.getTask();
            task.addOnCompleteListener(new g(24, countDownLatch));
            String str2 = TAG;
            Log.d(str2, "execute: task=" + task.getTaskId());
            ((ServiceExecutor) next.second).execute(taskRunnable);
            try {
                z = countDownLatch.await(60, TimeUnit.SECONDS);
            } catch (InterruptedException unused) {
                z = false;
            }
            if (!z) {
                String str3 = TAG;
                Log.e(str3, "execute: timeout or interrupted: " + task.getTaskId());
                break;
            } else if (task.isSuccessful()) {
                this.mSource.setResult(task.getResult());
                String str4 = TAG;
                Log.d(str4, "execute: result: " + task.getTaskId());
                return;
            } else {
                Exception exception = task.getException();
                if (exception != null) {
                    exc = exception;
                }
            }
        }
        if (exc != null) {
            this.mSource.setException(exc);
            String str5 = TAG;
            Log.e(str5, "execute: set exception: " + exc);
            return;
        }
        this.mSource.setResult(this.mDefault);
        String str6 = TAG;
        Log.e(str6, "execute: set default: " + this.mDefault);
    }

    public String getFeatureName() {
        return Feature.FEATURE_VISUAL_DOWNLOAD;
    }

    public void setDefaultResult(TResult tresult) {
        this.mDefault = tresult;
    }
}
