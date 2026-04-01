package androidx.work.impl.utils;

import androidx.work.impl.WorkManagerImpl;
import java.util.UUID;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ WorkManagerImpl d;
    public final /* synthetic */ UUID e;

    public /* synthetic */ a(WorkManagerImpl workManagerImpl, UUID uuid) {
        this.d = workManagerImpl;
        this.e = uuid;
    }

    public final void run() {
        CancelWorkRunnable$forId$1.invoke$lambda$0(this.d, this.e);
    }
}
