package com.samsung.android.gallery.module.publisher;

import com.samsung.android.gallery.support.blackboard.Subscriber;

/* renamed from: com.samsung.android.gallery.module.publisher.t  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0639t implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Subscriber e;

    public /* synthetic */ C0639t(Subscriber subscriber, int i2) {
        this.d = i2;
        this.e = subscriber;
    }

    public final void run() {
        int i2 = this.d;
        Subscriber subscriber = this.e;
        switch (i2) {
            case 0:
                ((DataChangeEventPublisher) subscriber).processDeferredChangeEvent();
                return;
            case 1:
                ((ListDataPublisher) subscriber).loadTimelineFullData();
                return;
            default:
                ((SearchDataPublisher) subscriber).lambda$publishSuggestionKeywords$12();
                return;
        }
    }
}
