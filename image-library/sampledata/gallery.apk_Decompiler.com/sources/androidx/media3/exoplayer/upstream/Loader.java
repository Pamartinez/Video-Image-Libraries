package androidx.media3.exoplayer.upstream;

import K.a;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.util.ReleasableExecutor;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Loader {
    public static final LoadErrorAction DONT_RETRY = new LoadErrorAction(2, -9223372036854775807L);
    public static final LoadErrorAction DONT_RETRY_FATAL = new LoadErrorAction(3, -9223372036854775807L);
    public static final LoadErrorAction RETRY = createRetryAction(false, -9223372036854775807L);
    public static final LoadErrorAction RETRY_RESET_ERROR_COUNT = createRetryAction(true, -9223372036854775807L);
    /* access modifiers changed from: private */
    public LoadTask<? extends Loadable> currentTask;
    /* access modifiers changed from: private */
    public final ReleasableExecutor downloadExecutor;
    /* access modifiers changed from: private */
    public IOException fatalError;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Callback<T extends Loadable> {
        void onLoadCanceled(T t, long j2, long j3, boolean z);

        void onLoadCompleted(T t, long j2, long j3);

        LoadErrorAction onLoadError(T t, long j2, long j3, IOException iOException, int i2);

        void onLoadStarted(T t, long j2, long j3, int i2);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class LoadErrorAction {
        /* access modifiers changed from: private */
        public final long retryDelayMillis;
        /* access modifiers changed from: private */
        public final int type;

        public boolean isRetry() {
            int i2 = this.type;
            if (i2 == 0 || i2 == 1) {
                return true;
            }
            return false;
        }

        private LoadErrorAction(int i2, long j2) {
            this.type = i2;
            this.retryDelayMillis = j2;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class LoadTask<T extends Loadable> extends Handler implements Runnable {
        private Callback<T> callback;
        private boolean canceled;
        private IOException currentError;
        public final int defaultMinRetryCount;
        private int errorCount;
        private Thread executorThread;
        private final T loadable;
        private volatile boolean released;
        private final long startTimeMs;

        public LoadTask(Looper looper, T t, Callback<T> callback2, int i2, long j2) {
            super(looper);
            this.loadable = t;
            this.callback = callback2;
            this.defaultMinRetryCount = i2;
            this.startTimeMs = j2;
        }

        private void execute() {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            ((Callback) Assertions.checkNotNull(this.callback)).onLoadStarted(this.loadable, elapsedRealtime, elapsedRealtime - this.startTimeMs, this.errorCount);
            this.currentError = null;
            Loader.this.downloadExecutor.execute((Runnable) Assertions.checkNotNull(Loader.this.currentTask));
        }

        private void finish() {
            LoadTask unused = Loader.this.currentTask = null;
        }

        private long getRetryDelayMillis() {
            return (long) Math.min((this.errorCount - 1) * 1000, 5000);
        }

        public void cancel(boolean z) {
            this.released = z;
            this.currentError = null;
            if (hasMessages(1)) {
                this.canceled = true;
                removeMessages(1);
                if (!z) {
                    sendEmptyMessage(2);
                }
            } else {
                synchronized (this) {
                    try {
                        this.canceled = true;
                        this.loadable.cancelLoad();
                        Thread thread = this.executorThread;
                        if (thread != null) {
                            thread.interrupt();
                        }
                    } catch (Throwable th) {
                        while (true) {
                            throw th;
                        }
                    }
                }
            }
            if (z) {
                finish();
                long elapsedRealtime = SystemClock.elapsedRealtime();
                ((Callback) Assertions.checkNotNull(this.callback)).onLoadCanceled(this.loadable, elapsedRealtime, elapsedRealtime - this.startTimeMs, true);
                this.callback = null;
            }
        }

        public void handleMessage(Message message) {
            long j2;
            if (!this.released) {
                int i2 = message.what;
                if (i2 == 1) {
                    execute();
                } else if (i2 != 4) {
                    finish();
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    long j3 = elapsedRealtime - this.startTimeMs;
                    Callback callback2 = (Callback) Assertions.checkNotNull(this.callback);
                    if (this.canceled) {
                        callback2.onLoadCanceled(this.loadable, elapsedRealtime, j3, false);
                        return;
                    }
                    int i7 = message.what;
                    if (i7 == 2) {
                        try {
                            callback2.onLoadCompleted(this.loadable, elapsedRealtime, j3);
                        } catch (RuntimeException e) {
                            RuntimeException runtimeException = e;
                            Log.e("LoadTask", "Unexpected exception handling load completed", runtimeException);
                            IOException unused = Loader.this.fatalError = new UnexpectedLoaderException(runtimeException);
                        }
                    } else if (i7 == 3) {
                        IOException iOException = (IOException) message.obj;
                        this.currentError = iOException;
                        int i8 = this.errorCount + 1;
                        this.errorCount = i8;
                        LoadErrorAction onLoadError = callback2.onLoadError(this.loadable, elapsedRealtime, j3, iOException, i8);
                        if (onLoadError.type == 3) {
                            IOException unused2 = Loader.this.fatalError = this.currentError;
                        } else if (onLoadError.type != 2) {
                            if (onLoadError.type == 1) {
                                this.errorCount = 1;
                            }
                            if (onLoadError.retryDelayMillis != -9223372036854775807L) {
                                j2 = onLoadError.retryDelayMillis;
                            } else {
                                j2 = getRetryDelayMillis();
                            }
                            start(j2);
                        }
                    }
                } else {
                    throw ((Error) message.obj);
                }
            }
        }

        public void maybeThrowError(int i2) {
            IOException iOException = this.currentError;
            if (iOException != null && this.errorCount > i2) {
                throw iOException;
            }
        }

        public void run() {
            boolean z;
            try {
                synchronized (this) {
                    z = this.canceled;
                    this.executorThread = Thread.currentThread();
                }
                if (!z) {
                    TraceUtil.beginSection("load:".concat(this.loadable.getClass().getSimpleName()));
                    this.loadable.load();
                    TraceUtil.endSection();
                }
                synchronized (this) {
                    this.executorThread = null;
                    Thread.interrupted();
                }
                if (!this.released) {
                    sendEmptyMessage(2);
                }
            } catch (IOException e) {
                if (!this.released) {
                    obtainMessage(3, e).sendToTarget();
                }
            } catch (Exception e7) {
                if (!this.released) {
                    Log.e("LoadTask", "Unexpected exception loading stream", e7);
                    obtainMessage(3, new UnexpectedLoaderException(e7)).sendToTarget();
                }
            } catch (OutOfMemoryError e8) {
                if (!this.released) {
                    Log.e("LoadTask", "OutOfMemory error loading stream", e8);
                    obtainMessage(3, new UnexpectedLoaderException(e8)).sendToTarget();
                }
            } catch (Error e9) {
                if (!this.released) {
                    Log.e("LoadTask", "Unexpected error loading stream", e9);
                    obtainMessage(4, e9).sendToTarget();
                }
                throw e9;
            } catch (Throwable th) {
                TraceUtil.endSection();
                throw th;
            }
        }

        public void start(long j2) {
            boolean z;
            if (Loader.this.currentTask == null) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkState(z);
            LoadTask unused = Loader.this.currentTask = this;
            if (j2 > 0) {
                sendEmptyMessageDelayed(1, j2);
            } else {
                execute();
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Loadable {
        void cancelLoad();

        void load();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ReleaseCallback {
        void onLoaderReleased();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ReleaseTask implements Runnable {
        private final ReleaseCallback callback;

        public ReleaseTask(ReleaseCallback releaseCallback) {
            this.callback = releaseCallback;
        }

        public void run() {
            this.callback.onLoaderReleased();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class UnexpectedLoaderException extends IOException {
        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public UnexpectedLoaderException(java.lang.Throwable r4) {
            /*
                r3 = this;
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                java.lang.String r1 = "Unexpected "
                r0.<init>(r1)
                java.lang.Class r1 = r4.getClass()
                java.lang.String r1 = r1.getSimpleName()
                r0.append(r1)
                java.lang.String r1 = r4.getMessage()
                if (r1 == 0) goto L_0x0024
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r2 = ": "
                r1.<init>(r2)
                java.lang.String r1 = N2.j.i(r4, r1)
                goto L_0x0026
            L_0x0024:
                java.lang.String r1 = ""
            L_0x0026:
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                r3.<init>(r0, r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.upstream.Loader.UnexpectedLoaderException.<init>(java.lang.Throwable):void");
        }
    }

    public Loader(String str) {
        this(ReleasableExecutor.from(Util.newSingleThreadExecutor("ExoPlayer:Loader:" + str), new a(11)));
    }

    public static LoadErrorAction createRetryAction(boolean z, long j2) {
        return new LoadErrorAction(z ? 1 : 0, j2);
    }

    public void cancelLoading() {
        ((LoadTask) Assertions.checkStateNotNull(this.currentTask)).cancel(false);
    }

    public void clearFatalError() {
        this.fatalError = null;
    }

    public boolean hasFatalError() {
        if (this.fatalError != null) {
            return true;
        }
        return false;
    }

    public boolean isLoading() {
        if (this.currentTask != null) {
            return true;
        }
        return false;
    }

    public void maybeThrowError() {
        maybeThrowError(Integer.MIN_VALUE);
    }

    public void release() {
        release((ReleaseCallback) null);
    }

    public <T extends Loadable> long startLoading(T t, Callback<T> callback, int i2) {
        this.fatalError = null;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        new LoadTask((Looper) Assertions.checkStateNotNull(Looper.myLooper()), t, callback, i2, elapsedRealtime).start(0);
        return elapsedRealtime;
    }

    public void maybeThrowError(int i2) {
        IOException iOException = this.fatalError;
        if (iOException == null) {
            LoadTask<? extends Loadable> loadTask = this.currentTask;
            if (loadTask != null) {
                if (i2 == Integer.MIN_VALUE) {
                    i2 = loadTask.defaultMinRetryCount;
                }
                loadTask.maybeThrowError(i2);
                return;
            }
            return;
        }
        throw iOException;
    }

    public void release(ReleaseCallback releaseCallback) {
        LoadTask<? extends Loadable> loadTask = this.currentTask;
        if (loadTask != null) {
            loadTask.cancel(true);
        }
        if (releaseCallback != null) {
            this.downloadExecutor.execute(new ReleaseTask(releaseCallback));
        }
        this.downloadExecutor.release();
    }

    public Loader(ReleasableExecutor releasableExecutor) {
        this.downloadExecutor = releasableExecutor;
    }
}
