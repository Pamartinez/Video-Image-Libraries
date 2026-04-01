package androidx.work.impl.utils;

import android.content.Context;
import androidx.work.ForegroundInfo;
import androidx.work.ForegroundUpdater;
import androidx.work.ListenableFutureKt;
import androidx.work.Logger;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.foreground.ForegroundProcessor;
import androidx.work.impl.foreground.SystemForegroundDispatcher;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkSpecKt;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.UUID;
import r3.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class WorkForegroundUpdater implements ForegroundUpdater {
    private static final String TAG = Logger.tagWithPrefix("WMFgUpdater");
    final ForegroundProcessor mForegroundProcessor;
    private final TaskExecutor mTaskExecutor;
    final WorkSpecDao mWorkSpecDao;

    public WorkForegroundUpdater(WorkDatabase workDatabase, ForegroundProcessor foregroundProcessor, TaskExecutor taskExecutor) {
        this.mForegroundProcessor = foregroundProcessor;
        this.mTaskExecutor = taskExecutor;
        this.mWorkSpecDao = workDatabase.workSpecDao();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Void lambda$setForegroundAsync$0(UUID uuid, ForegroundInfo foregroundInfo, Context context) {
        String uuid2 = uuid.toString();
        WorkSpec workSpec = this.mWorkSpecDao.getWorkSpec(uuid2);
        if (workSpec == null || workSpec.state.isFinished()) {
            throw new IllegalStateException("Calls to setForegroundAsync() must complete before a ListenableWorker signals completion of work by returning an instance of Result.");
        }
        this.mForegroundProcessor.startForeground(uuid2, foregroundInfo);
        context.startService(SystemForegroundDispatcher.createNotifyIntent(context, WorkSpecKt.generationalId(workSpec), foregroundInfo));
        return null;
    }

    public ListenableFuture setForegroundAsync(Context context, UUID uuid, ForegroundInfo foregroundInfo) {
        return ListenableFutureKt.executeAsync(this.mTaskExecutor.getSerialTaskExecutor(), "setForegroundAsync", new b(1, uuid, this, foregroundInfo, context));
    }
}
