package com.samsung.android.gallery.app.ui.list.abstraction;

import com.samsung.android.gallery.app.ui.list.abstraction.AbsListFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Runnable {
    public final /* synthetic */ AbsListFragment.AnonymousClass2 d;
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;
    public final /* synthetic */ Object g;

    public /* synthetic */ j(AbsListFragment.AnonymousClass2 r1, int i2, int i7, Object obj) {
        this.d = r1;
        this.e = i2;
        this.f = i7;
        this.g = obj;
    }

    public final void run() {
        this.d.lambda$onDataRangeChanged$1(this.e, this.f, this.g);
    }
}
