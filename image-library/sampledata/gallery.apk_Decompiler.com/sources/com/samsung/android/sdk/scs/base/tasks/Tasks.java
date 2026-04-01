package com.samsung.android.sdk.scs.base.tasks;

import com.samsung.android.sdk.scs.base.utils.Preconditions;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Tasks {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TaskCountDown implements OnCompleteListener<Object> {
        private final CountDownLatch mLatch;

        public /* synthetic */ TaskCountDown(int i2) {
            this();
        }

        public final void await() {
            this.mLatch.await();
        }

        public void onComplete(Task<Object> task) {
            this.mLatch.countDown();
        }

        private TaskCountDown() {
            this.mLatch = new CountDownLatch(1);
        }

        public final boolean await(long j2, TimeUnit timeUnit) {
            return this.mLatch.await(j2, timeUnit);
        }
    }

    public static <TResult> TResult await(Task<TResult> task) {
        Preconditions.checkNotMainThread();
        Preconditions.checkNotNull(task, "Task must not be null");
        if (task.isComplete()) {
            return completeTask(task);
        }
        TaskCountDown taskCountDown = new TaskCountDown(0);
        task.addOnCompleteListener(TaskExecutors.EXECUTOR, taskCountDown);
        taskCountDown.await();
        return completeTask(task);
    }

    private static <TResult> TResult completeTask(Task<TResult> task) {
        if (task.isSuccessful()) {
            return task.getResult();
        }
        if (task.isCanceled()) {
            throw new CancellationException("Task is already canceled");
        }
        throw new ExecutionException(task.getException());
    }

    public static <TResult> TResult await(Task<TResult> task, long j2, TimeUnit timeUnit) {
        Preconditions.checkNotMainThread();
        Preconditions.checkNotNull(task, "Task must not be null");
        Preconditions.checkNotNull(timeUnit, "TimeUnit must not be null");
        if (task.isComplete()) {
            return completeTask(task);
        }
        TaskCountDown taskCountDown = new TaskCountDown(0);
        task.addOnCompleteListener(TaskExecutors.EXECUTOR, taskCountDown);
        if (taskCountDown.await(j2, timeUnit)) {
            return completeTask(task);
        }
        throw new TimeoutException("Timed out waiting for Task");
    }
}
