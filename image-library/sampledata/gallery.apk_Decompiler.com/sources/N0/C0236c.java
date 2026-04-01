package n0;

import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkerUpdater;
import androidx.work.impl.model.WorkSpec;
import java.util.List;
import java.util.Set;

/* renamed from: n0.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0236c implements Runnable {
    public final /* synthetic */ WorkDatabase d;
    public final /* synthetic */ WorkSpec e;
    public final /* synthetic */ WorkSpec f;
    public final /* synthetic */ List g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ String f1839h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ Set f1840i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ boolean f1841j;

    public /* synthetic */ C0236c(WorkDatabase workDatabase, WorkSpec workSpec, WorkSpec workSpec2, List list, String str, Set set, boolean z) {
        this.d = workDatabase;
        this.e = workSpec;
        this.f = workSpec2;
        this.g = list;
        this.f1839h = str;
        this.f1840i = set;
        this.f1841j = z;
    }

    public final void run() {
        WorkerUpdater.updateWorkImpl$lambda$2(this.d, this.e, this.f, this.g, this.f1839h, this.f1840i, this.f1841j);
    }
}
