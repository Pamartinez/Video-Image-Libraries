package com.samsung.android.gallery.widget.search.filter;

import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ FilterSubListView d;

    public /* synthetic */ d(FilterSubListView filterSubListView) {
        this.d = filterSubListView;
    }

    public final void accept(Object obj) {
        this.d.addEntity((FilterResultsEntity) obj);
    }
}
