package com.samsung.android.gallery.module.idleworker;

import com.samsung.android.gallery.module.idleworker.IdleWorker;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Supplier {
    public final /* synthetic */ int d;

    public /* synthetic */ a(int i2) {
        this.d = i2;
    }

    public final Object get() {
        switch (this.d) {
            case 0:
                return IdleWorker.IdleChargedJobs.AnonymousClass1.lambda$new$0();
            case 1:
                return IdleWorker.IdleChargedJobs.AnonymousClass1.lambda$new$1();
            case 2:
                return IdleWorker.IdleChargedJobs.AnonymousClass1.lambda$new$2();
            case 3:
                return IdleWorker.IdleChargedJobs.AnonymousClass1.lambda$new$3();
            case 4:
                return IdleWorker.IdleChargedJobs.AnonymousClass1.lambda$new$4();
            case 5:
                return IdleWorker.IdleChargedJobs.AnonymousClass1.lambda$new$5();
            case 6:
                return IdleWorker.IdleJobs.AnonymousClass1.lambda$new$0();
            case 7:
                return IdleWorker.IdleJobs.AnonymousClass1.lambda$new$1();
            case 8:
                return IdleWorker.IdleJobs.AnonymousClass1.lambda$new$2();
            default:
                return IdleWorker.IdleJobs.AnonymousClass1.lambda$new$3();
        }
    }
}
