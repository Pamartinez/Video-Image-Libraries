package com.samsung.android.gallery.app.ui.list.abstraction;

import com.samsung.android.gallery.app.ui.list.abstraction.AbsListFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Runnable {
    public final /* synthetic */ AbsListFragment.AnonymousClass2 d;
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;

    public /* synthetic */ i(AbsListFragment.AnonymousClass2 r1, int i2, int i7) {
        this.d = r1;
        this.e = i2;
        this.f = i7;
    }

    public final void run() {
        this.d.lambda$onDataRangeChanged$0(this.e, this.f);
    }
}
