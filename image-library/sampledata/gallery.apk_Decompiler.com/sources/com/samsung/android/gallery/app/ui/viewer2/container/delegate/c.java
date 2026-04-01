package com.samsung.android.gallery.app.ui.viewer2.container.delegate;

import com.samsung.android.gallery.app.ui.viewer2.container.delegate.ViewPagerFilmScrollSyncDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ ViewPagerFilmScrollSyncDelegate.AnonymousClass1 d;
    public final /* synthetic */ int e;

    public /* synthetic */ c(ViewPagerFilmScrollSyncDelegate.AnonymousClass1 r1, int i2) {
        this.d = r1;
        this.e = i2;
    }

    public final void run() {
        this.d.lambda$onScrollStateChanged$0(this.e);
    }
}
