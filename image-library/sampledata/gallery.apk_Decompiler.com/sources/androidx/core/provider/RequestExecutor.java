package androidx.core.provider;

import android.os.Handler;
import android.os.Process;
import androidx.core.util.Consumer;
import androidx.core.util.Preconditions;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class RequestExecutor {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DefaultThreadFactory implements ThreadFactory {
        private int mPriority;
        private String mThreadName;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class ProcessPriorityThread extends Thread {
            private final int mPriority;

            public ProcessPriorityThread(Runnable runnable, String str, int i2) {
                super(runnable, str);
                this.mPriority = i2;
            }

            public void run() {
                Process.setThreadPriority(this.mPriority);
                super.run();
            }
        }

        public DefaultThreadFactory(String str, int i2) {
            this.mThreadName = str;
            this.mPriority = i2;
        }

        public Thread newThread(Runnable runnable) {
            return new ProcessPriorityThread(runnable, this.mThreadName, this.mPriority);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class HandlerExecutor implements Executor {
        private final Handler mHandler;

        public HandlerExecutor(Handler handler) {
            this.mHandler = (Handler) Preconditions.checkNotNull(handler);
        }

        public void execute(Runnable runnable) {
            if (!this.mHandler.post((Runnable) Preconditions.checkNotNull(runnable))) {
                throw new RejectedExecutionException(this.mHandler + " is shutting down");
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ReplyRunnable<T> implements Runnable {
        private Callable<T> mCallable;
        private Consumer<T> mConsumer;
        private Handler mHandler;

        public ReplyRunnable(Handler handler, Callable<T> callable, Consumer<T> consumer) {
            this.mCallable = callable;
            this.mConsumer = consumer;
            this.mHandler = handler;
        }

        public void run() {
            final T t;
            try {
                t = this.mCallable.call();
            } catch (Exception unused) {
                t = null;
            }
            final Consumer<T> consumer = this.mConsumer;
            this.mHandler.post(new Runnable() {
                public void run() {
                    consumer.accept(t);
                }
            });
        }
    }

    public static ThreadPoolExecutor createDefaultExecutor(String str, int i2, int i7) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, (long) i7, TimeUnit.MILLISECONDS, new LinkedBlockingDeque(), new DefaultThreadFactory(str, i2));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return threadPoolExecutor;
    }

    public static Executor createHandlerExecutor(Handler handler) {
        return new HandlerExecutor(handler);
    }

    public static <T> void execute(Executor executor, Callable<T> callable, Consumer<T> consumer) {
        executor.execute(new ReplyRunnable(CalleeHandler.create(), callable, consumer));
    }

    public static <T> T submit(ExecutorService executorService, Callable<T> callable, int i2) {
        try {
            return executorService.submit(callable).get((long) i2, TimeUnit.MILLISECONDS);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e7) {
            throw e7;
        } catch (TimeoutException unused) {
            throw new InterruptedException("timeout");
        }
    }
}
