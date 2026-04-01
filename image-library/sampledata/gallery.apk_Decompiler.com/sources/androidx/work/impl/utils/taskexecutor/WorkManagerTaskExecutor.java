package androidx.work.impl.utils.taskexecutor;

import Vf.C0886x;
import Vf.D;
import android.os.Handler;
import android.os.Looper;
import androidx.work.impl.utils.SerialExecutorImpl;
import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class WorkManagerTaskExecutor implements TaskExecutor {
    private final SerialExecutorImpl mBackgroundExecutor;
    private final Executor mMainThreadExecutor = new Executor() {
        public void execute(Runnable runnable) {
            WorkManagerTaskExecutor.this.mMainThreadHandler.post(runnable);
        }
    };
    final Handler mMainThreadHandler = new Handler(Looper.getMainLooper());
    private final C0886x mTaskDispatcher;

    public WorkManagerTaskExecutor(Executor executor) {
        SerialExecutorImpl serialExecutorImpl = new SerialExecutorImpl(executor);
        this.mBackgroundExecutor = serialExecutorImpl;
        this.mTaskDispatcher = D.h(serialExecutorImpl);
    }

    public Executor getMainThreadExecutor() {
        return this.mMainThreadExecutor;
    }

    public C0886x getTaskCoroutineDispatcher() {
        return this.mTaskDispatcher;
    }

    public SerialExecutorImpl getSerialTaskExecutor() {
        return this.mBackgroundExecutor;
    }
}
