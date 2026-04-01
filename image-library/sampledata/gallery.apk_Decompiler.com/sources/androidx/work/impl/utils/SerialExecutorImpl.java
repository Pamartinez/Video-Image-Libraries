package androidx.work.impl.utils;

import androidx.work.impl.utils.taskexecutor.SerialExecutor;
import java.util.ArrayDeque;
import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SerialExecutorImpl implements SerialExecutor {
    private Runnable mActive;
    private final Executor mExecutor;
    final Object mLock = new Object();
    private final ArrayDeque<Task> mTasks = new ArrayDeque<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Task implements Runnable {
        final Runnable mRunnable;
        final SerialExecutorImpl mSerialExecutor;

        public Task(SerialExecutorImpl serialExecutorImpl, Runnable runnable) {
            this.mSerialExecutor = serialExecutorImpl;
            this.mRunnable = runnable;
        }

        public void run() {
            try {
                this.mRunnable.run();
                synchronized (this.mSerialExecutor.mLock) {
                    this.mSerialExecutor.scheduleNext();
                }
            } catch (Throwable th) {
                synchronized (this.mSerialExecutor.mLock) {
                    this.mSerialExecutor.scheduleNext();
                    throw th;
                }
            }
        }
    }

    public SerialExecutorImpl(Executor executor) {
        this.mExecutor = executor;
    }

    public void execute(Runnable runnable) {
        synchronized (this.mLock) {
            try {
                this.mTasks.add(new Task(this, runnable));
                if (this.mActive == null) {
                    scheduleNext();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean hasPendingTasks() {
        boolean z;
        synchronized (this.mLock) {
            z = !this.mTasks.isEmpty();
        }
        return z;
    }

    public void scheduleNext() {
        Runnable poll = this.mTasks.poll();
        this.mActive = poll;
        if (poll != null) {
            this.mExecutor.execute(poll);
        }
    }
}
