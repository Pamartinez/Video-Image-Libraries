package com.samsung.android.gallery.app.ui.list.abstraction;

import com.samsung.android.gallery.app.ui.list.abstraction.AbsListFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ b(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void run() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((AbsListFragment) obj).initializeScroll();
                return;
            case 1:
                ((AbsListFragment) obj).startShrinkAnimation();
                return;
            case 2:
                ((AbsListFragment) obj).lambda$createSimpleAutoScroller$5();
                return;
            case 3:
                ((AbsListFragment) obj).lambda$initializeOptionalUi$6();
                return;
            default:
                ((AbsListFragment.AnonymousClass2) obj).onDataChangedInternal();
                return;
        }
    }
}
