package n0;

import a8.d;
import androidx.work.Configuration;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.model.WorkGenerationalId;
import java.util.List;
import java.util.concurrent.Executor;

/* renamed from: n0.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0234a implements ExecutionListener {
    public final /* synthetic */ Executor d;
    public final /* synthetic */ List e;
    public final /* synthetic */ Configuration f;
    public final /* synthetic */ WorkDatabase g;

    public /* synthetic */ C0234a(Executor executor, List list, Configuration configuration, WorkDatabase workDatabase) {
        this.d = executor;
        this.e = list;
        this.f = configuration;
        this.g = workDatabase;
    }

    public final void onExecuted(WorkGenerationalId workGenerationalId, boolean z) {
        this.d.execute(new d((Object) this.e, (Object) workGenerationalId, (Object) this.f, (Object) this.g, 17));
    }
}
