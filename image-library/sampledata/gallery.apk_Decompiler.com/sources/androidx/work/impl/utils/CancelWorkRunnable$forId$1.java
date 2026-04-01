package androidx.work.impl.utils;

import Ae.a;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.k;

@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lme/x;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CancelWorkRunnable$forId$1 extends k implements a {
    final /* synthetic */ UUID $id;
    final /* synthetic */ WorkManagerImpl $workManagerImpl;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CancelWorkRunnable$forId$1(WorkManagerImpl workManagerImpl, UUID uuid) {
        super(0);
        this.$workManagerImpl = workManagerImpl;
        this.$id = uuid;
    }

    /* access modifiers changed from: private */
    public static final void invoke$lambda$0(WorkManagerImpl workManagerImpl, UUID uuid) {
        String uuid2 = uuid.toString();
        j.d(uuid2, "id.toString()");
        CancelWorkRunnable.cancel(workManagerImpl, uuid2);
    }

    public final void invoke() {
        WorkDatabase workDatabase = this.$workManagerImpl.getWorkDatabase();
        j.d(workDatabase, "workManagerImpl.workDatabase");
        workDatabase.runInTransaction((Runnable) new a(this.$workManagerImpl, this.$id));
        CancelWorkRunnable.reschedulePendingWorkers(this.$workManagerImpl);
    }
}
