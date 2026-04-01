package com.samsung.android.gallery.app.ui.list.abstraction;

import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements View.OnLayoutChangeListener {
    public final /* synthetic */ AbsListViewAdapter d;

    public /* synthetic */ k(AbsListViewAdapter absListViewAdapter) {
        this.d = absListViewAdapter;
    }

    public final void onLayoutChange(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
        this.d.onLayoutChanged(view, i2, i7, i8, i10, i11, i12, i13, i14);
    }
}
