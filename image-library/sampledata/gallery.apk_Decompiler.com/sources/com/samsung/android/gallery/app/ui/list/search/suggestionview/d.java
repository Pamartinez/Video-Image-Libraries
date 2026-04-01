package com.samsung.android.gallery.app.ui.list.search.suggestionview;

import com.samsung.android.gallery.app.ui.list.search.suggestionview.HierarchicalViewAdapter;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ HierarchicalViewAdapter d;

    public /* synthetic */ d(HierarchicalViewAdapter hierarchicalViewAdapter) {
        this.d = hierarchicalViewAdapter;
    }

    public final void accept(Object obj) {
        this.d.lambda$onItemClicked$3((HierarchicalViewAdapter.HierarchicalItem) obj);
    }
}
