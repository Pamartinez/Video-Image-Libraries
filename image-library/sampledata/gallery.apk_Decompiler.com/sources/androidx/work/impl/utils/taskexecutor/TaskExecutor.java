package androidx.work.impl.utils.taskexecutor;

import Vf.C0886x;
import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface TaskExecutor {
    void executeOnTaskThread(Runnable runnable) {
        getSerialTaskExecutor().execute(runnable);
    }

    Executor getMainThreadExecutor();

    SerialExecutor getSerialTaskExecutor();

    C0886x getTaskCoroutineDispatcher();
}
