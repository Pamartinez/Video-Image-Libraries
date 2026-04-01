package com.samsung.android.gallery.module.publisher;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class G implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SearchDataBasePublisher e;
    public final /* synthetic */ AtomicReference f;

    public /* synthetic */ G(SearchDataBasePublisher searchDataBasePublisher, AtomicReference atomicReference, int i2) {
        this.d = i2;
        this.e = searchDataBasePublisher;
        this.f = atomicReference;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$publishRecommendationData$22(this.f);
                return;
            default:
                this.e.lambda$publishRecommendationData$23(this.f);
                return;
        }
    }
}
