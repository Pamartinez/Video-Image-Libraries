package androidx.media3.effect;

import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.Assertions;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class VideoFrameProcessingTaskExecutor {
    private final ErrorListener errorListener;
    private final Queue<Task> highPriorityTasks = new ArrayDeque();
    private final Object lock = new Object();
    private boolean shouldCancelTasks;
    private final boolean shouldShutdownExecutorService;
    private final ExecutorService singleThreadExecutorService;
    private final Future<Thread> threadFuture;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ErrorListener {
        void onError(VideoFrameProcessingException videoFrameProcessingException);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Task {
        void run();
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Object, java.util.concurrent.Callable] */
    public VideoFrameProcessingTaskExecutor(ExecutorService executorService, boolean z, ErrorListener errorListener2) {
        this.singleThreadExecutorService = executorService;
        this.threadFuture = executorService.submit(new Object());
        this.shouldShutdownExecutorService = z;
        this.errorListener = errorListener2;
    }

    private void handleException(Exception exc) {
        synchronized (this.lock) {
            try {
                if (!this.shouldCancelTasks) {
                    this.shouldCancelTasks = true;
                    this.errorListener.onError(VideoFrameProcessingException.from(exc));
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    private boolean isRunningOnVideoFrameProcessingThread() {
        try {
            if (Thread.currentThread() == this.threadFuture.get(500, TimeUnit.MILLISECONDS)) {
                return true;
            }
            return false;
        } catch (InterruptedException e) {
            throw e;
        } catch (Exception e7) {
            handleException(e7);
            return false;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$invoke$0(Task task) {
        try {
            task.run();
        } catch (Exception e) {
            handleException(e);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r3 = r2.lock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0010, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r0 = r2.highPriorityTasks.poll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0019, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x001a, code lost:
        if (r0 != null) goto L_0x0022;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r4.run();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x001f, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0022, code lost:
        r0.run();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ void lambda$wrapTaskAndSubmitToExecutorService$3(boolean r3, androidx.media3.effect.VideoFrameProcessingTaskExecutor.Task r4) {
        /*
            r2 = this;
            java.lang.Object r0 = r2.lock     // Catch:{ Exception -> 0x0020 }
            monitor-enter(r0)     // Catch:{ Exception -> 0x0020 }
            boolean r1 = r2.shouldCancelTasks     // Catch:{ all -> 0x000b }
            if (r1 == 0) goto L_0x000d
            if (r3 == 0) goto L_0x000d
            monitor-exit(r0)     // Catch:{ all -> 0x000b }
            return
        L_0x000b:
            r3 = move-exception
            goto L_0x0029
        L_0x000d:
            monitor-exit(r0)     // Catch:{ all -> 0x000b }
        L_0x000e:
            java.lang.Object r3 = r2.lock     // Catch:{ Exception -> 0x0020 }
            monitor-enter(r3)     // Catch:{ Exception -> 0x0020 }
            java.util.Queue<androidx.media3.effect.VideoFrameProcessingTaskExecutor$Task> r0 = r2.highPriorityTasks     // Catch:{ all -> 0x0026 }
            java.lang.Object r0 = r0.poll()     // Catch:{ all -> 0x0026 }
            androidx.media3.effect.VideoFrameProcessingTaskExecutor$Task r0 = (androidx.media3.effect.VideoFrameProcessingTaskExecutor.Task) r0     // Catch:{ all -> 0x0026 }
            monitor-exit(r3)     // Catch:{ all -> 0x0026 }
            if (r0 != 0) goto L_0x0022
            r4.run()     // Catch:{ Exception -> 0x0020 }
            return
        L_0x0020:
            r3 = move-exception
            goto L_0x002b
        L_0x0022:
            r0.run()     // Catch:{ Exception -> 0x0020 }
            goto L_0x000e
        L_0x0026:
            r4 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0026 }
            throw r4     // Catch:{ Exception -> 0x0020 }
        L_0x0029:
            monitor-exit(r0)     // Catch:{ all -> 0x000b }
            throw r3     // Catch:{ Exception -> 0x0020 }
        L_0x002b:
            r2.handleException(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.effect.VideoFrameProcessingTaskExecutor.lambda$wrapTaskAndSubmitToExecutorService$3(boolean, androidx.media3.effect.VideoFrameProcessingTaskExecutor$Task):void");
    }

    private Future<?> wrapTaskAndSubmitToExecutorService(Task task, boolean z) {
        return this.singleThreadExecutorService.submit(new x(this, z, task));
    }

    public void invoke(Task task) {
        if (isRunningOnVideoFrameProcessingThread()) {
            try {
                task.run();
            } catch (Exception e) {
                handleException(e);
            }
        } else {
            try {
                this.singleThreadExecutorService.submit(new p(4, this, task)).get(500, TimeUnit.MILLISECONDS);
            } catch (RuntimeException | ExecutionException | TimeoutException e7) {
                handleException(e7);
            }
        }
    }

    public void release(Task task) {
        Assertions.checkState(!isRunningOnVideoFrameProcessingThread());
        synchronized (this.lock) {
            this.shouldCancelTasks = true;
            this.highPriorityTasks.clear();
        }
        wrapTaskAndSubmitToExecutorService(task, false);
        if (this.shouldShutdownExecutorService) {
            this.singleThreadExecutorService.shutdown();
            if (!this.singleThreadExecutorService.awaitTermination(500, TimeUnit.MILLISECONDS)) {
                this.errorListener.onError(new VideoFrameProcessingException("Release timed out. OpenGL resources may not be cleaned up properly."));
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0014, code lost:
        if (r3 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0016, code lost:
        handleException(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void submit(androidx.media3.effect.VideoFrameProcessingTaskExecutor.Task r3, boolean r4) {
        /*
            r2 = this;
            java.lang.Object r0 = r2.lock
            monitor-enter(r0)
            boolean r1 = r2.shouldCancelTasks     // Catch:{ all -> 0x000b }
            if (r1 == 0) goto L_0x000d
            if (r4 == 0) goto L_0x000d
            monitor-exit(r0)     // Catch:{ all -> 0x000b }
            return
        L_0x000b:
            r2 = move-exception
            goto L_0x001a
        L_0x000d:
            r2.wrapTaskAndSubmitToExecutorService(r3, r4)     // Catch:{ RejectedExecutionException -> 0x0012 }
            r3 = 0
            goto L_0x0013
        L_0x0012:
            r3 = move-exception
        L_0x0013:
            monitor-exit(r0)     // Catch:{ all -> 0x000b }
            if (r3 == 0) goto L_0x0019
            r2.handleException(r3)
        L_0x0019:
            return
        L_0x001a:
            monitor-exit(r0)     // Catch:{ all -> 0x000b }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.effect.VideoFrameProcessingTaskExecutor.submit(androidx.media3.effect.VideoFrameProcessingTaskExecutor$Task, boolean):void");
    }

    /* JADX WARNING: type inference failed for: r3v1, types: [androidx.media3.effect.VideoFrameProcessingTaskExecutor$Task, java.lang.Object] */
    public void submitWithHighPriority(Task task) {
        synchronized (this.lock) {
            try {
                if (!this.shouldCancelTasks) {
                    this.highPriorityTasks.add(task);
                    submit(new Object());
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    public void verifyVideoFrameProcessingThread() {
        try {
            Assertions.checkState(isRunningOnVideoFrameProcessingThread());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            handleException(e);
        }
    }

    public void submit(Task task) {
        submit(task, true);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$submitWithHighPriority$1() {
    }
}
