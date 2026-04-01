package androidx.room;

import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import com.samsung.o3dp.lib3dphotography.i;
import java.util.ArrayDeque;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\b\u0010\tJ\r\u0010\n\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000bR\u0014\u0010\u0002\u001a\u00020\u00018\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010\fR\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\r8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Landroidx/room/TransactionExecutor;", "Ljava/util/concurrent/Executor;", "executor", "<init>", "(Ljava/util/concurrent/Executor;)V", "Ljava/lang/Runnable;", "command", "Lme/x;", "execute", "(Ljava/lang/Runnable;)V", "scheduleNext", "()V", "Ljava/util/concurrent/Executor;", "Ljava/util/ArrayDeque;", "tasks", "Ljava/util/ArrayDeque;", "active", "Ljava/lang/Runnable;", "", "syncLock", "Ljava/lang/Object;", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TransactionExecutor implements Executor {
    private Runnable active;
    private final Executor executor;
    private final Object syncLock = new Object();
    private final ArrayDeque<Runnable> tasks = new ArrayDeque<>();

    public TransactionExecutor(Executor executor2) {
        j.e(executor2, "executor");
        this.executor = executor2;
    }

    /* access modifiers changed from: private */
    public static final void execute$lambda$1$lambda$0(Runnable runnable, TransactionExecutor transactionExecutor) {
        j.e(runnable, "$command");
        j.e(transactionExecutor, "this$0");
        try {
            runnable.run();
        } finally {
            transactionExecutor.scheduleNext();
        }
    }

    public void execute(Runnable runnable) {
        j.e(runnable, Contract.COMMAND);
        synchronized (this.syncLock) {
            this.tasks.offer(new i(19, runnable, this));
            if (this.active == null) {
                scheduleNext();
            }
        }
    }

    public final void scheduleNext() {
        synchronized (this.syncLock) {
            Runnable poll = this.tasks.poll();
            Runnable runnable = poll;
            this.active = runnable;
            if (poll != null) {
                this.executor.execute(runnable);
            }
        }
    }
}
