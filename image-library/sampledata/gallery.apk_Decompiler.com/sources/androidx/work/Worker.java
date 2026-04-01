package androidx.work;

import android.content.Context;
import androidx.work.ListenableWorker;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH'J\b\u0010\t\u001a\u00020\nH\u0017J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\fH\u0016J\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\f¨\u0006\u000e"}, d2 = {"Landroidx/work/Worker;", "Landroidx/work/ListenableWorker;", "context", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "getForegroundInfo", "Landroidx/work/ForegroundInfo;", "getForegroundInfoAsync", "Lcom/google/common/util/concurrent/ListenableFuture;", "startWork", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Worker extends ListenableWorker {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Worker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        j.e(context, "context");
        j.e(workerParameters, "workerParams");
    }

    public abstract ListenableWorker.Result doWork();

    public ForegroundInfo getForegroundInfo() {
        throw new IllegalStateException("Expedited WorkRequests require a Worker to provide an implementation for `getForegroundInfo()`");
    }

    public ListenableFuture getForegroundInfoAsync() {
        Executor backgroundExecutor = getBackgroundExecutor();
        j.d(backgroundExecutor, "backgroundExecutor");
        return WorkerKt.future(backgroundExecutor, new Worker$getForegroundInfoAsync$1(this));
    }

    public final ListenableFuture startWork() {
        Executor backgroundExecutor = getBackgroundExecutor();
        j.d(backgroundExecutor, "backgroundExecutor");
        return WorkerKt.future(backgroundExecutor, new Worker$startWork$1(this));
    }
}
