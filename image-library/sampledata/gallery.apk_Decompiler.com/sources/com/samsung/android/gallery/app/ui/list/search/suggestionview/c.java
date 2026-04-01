package com.samsung.android.gallery.app.ui.list.search.suggestionview;

import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements View.OnClickListener {
    public final /* synthetic */ HierarchicalViewAdapter d;
    public final /* synthetic */ HierarchicalViewHolder e;

    public /* synthetic */ c(HierarchicalViewAdapter hierarchicalViewAdapter, HierarchicalViewHolder hierarchicalViewHolder) {
        this.d = hierarchicalViewAdapter;
        this.e = hierarchicalViewHolder;
    }

    public final void onClick(View view) {
        this.d.lambda$createViewHolderInternal$0(this.e, view);
    }
}
