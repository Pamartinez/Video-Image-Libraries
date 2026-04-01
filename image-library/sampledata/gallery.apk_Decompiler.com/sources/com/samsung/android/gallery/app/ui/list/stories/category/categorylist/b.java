package com.samsung.android.gallery.app.ui.list.stories.category.categorylist;

import androidx.recyclerview.widget.RecyclerView;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ b(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((YearListView) obj).handleDensityChange();
                return;
            case 1:
                ((YearListView) obj).handleResolutionChange();
                return;
            case 2:
                ((YearListView) obj).resetFilter();
                return;
            default:
                ((RecyclerView.LayoutManager) obj).setItemPrefetchEnabled(false);
                return;
        }
    }
}
