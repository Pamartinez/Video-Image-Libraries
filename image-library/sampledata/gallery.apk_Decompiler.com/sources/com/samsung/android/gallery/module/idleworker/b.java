package com.samsung.android.gallery.module.idleworker;

import com.samsung.android.gallery.module.abstraction.IdleWorkerJob;
import com.samsung.android.gallery.module.idleworker.IdleWorker;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3026a;

    public /* synthetic */ b(int i2) {
        this.f3026a = i2;
    }

    public final boolean test(Object obj) {
        return IdleWorker.UniqueJobs.lambda$valueOf$1(this.f3026a, (IdleWorkerJob) obj);
    }
}
