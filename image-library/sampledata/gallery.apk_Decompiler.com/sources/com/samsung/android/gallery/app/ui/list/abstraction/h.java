package com.samsung.android.gallery.app.ui.list.abstraction;

import android.view.View;
import android.widget.TextView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Runnable {
    public final /* synthetic */ AbsListFragment d;
    public final /* synthetic */ TextView e;
    public final /* synthetic */ View f;
    public final /* synthetic */ View g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ boolean f2528h;

    public /* synthetic */ h(AbsListFragment absListFragment, TextView textView, View view, View view2, boolean z) {
        this.d = absListFragment;
        this.e = textView;
        this.f = view;
        this.g = view2;
        this.f2528h = z;
    }

    public final void run() {
        this.d.lambda$adjustEmptyViewButtonLayout$4(this.e, this.f, this.g, this.f2528h);
    }
}
