package com.samsung.android.sdk.scs.base.tasks;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class TaskExecutors {
    static final Executor EXECUTOR = new BasicExecutor();
    public static final Executor MAIN_THREAD = new MainExecutor();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class MainExecutor implements Executor {
        private final Handler mHandler = new Handler(Looper.getMainLooper());

        public final void execute(Runnable runnable) {
            this.mHandler.post(runnable);
        }
    }

    private TaskExecutors() {
    }
}
