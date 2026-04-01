package androidx.work.impl.utils;

import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ WorkDatabase d;
    public final /* synthetic */ String e;
    public final /* synthetic */ WorkManagerImpl f;

    public /* synthetic */ b(WorkDatabase workDatabase, String str, WorkManagerImpl workManagerImpl) {
        this.d = workDatabase;
        this.e = str;
        this.f = workManagerImpl;
    }

    public final void run() {
        CancelWorkRunnable$forTag$1.invoke$lambda$0(this.d, this.e, this.f);
    }
}
