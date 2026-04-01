package com.samsung.android.gallery.app.ui.list.abstraction;

import android.view.MotionEvent;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements ListViewHolder.OnHoverListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AbsListViewAdapter f2529a;

    public /* synthetic */ l(AbsListViewAdapter absListViewAdapter) {
        this.f2529a = absListViewAdapter;
    }

    public final boolean a(ListViewHolder listViewHolder, MotionEvent motionEvent) {
        return this.f2529a.lambda$new$2(listViewHolder, motionEvent);
    }
}
