package com.samsung.android.gallery.app.ui.list.timeline.quicksearch;

import androidx.recyclerview.widget.RecyclerView;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ AbsQuickSearchFilterListViewAdapter e;

    public /* synthetic */ b(AbsQuickSearchFilterListViewAdapter absQuickSearchFilterListViewAdapter, int i2) {
        this.d = i2;
        this.e = absQuickSearchFilterListViewAdapter;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        AbsQuickSearchFilterListViewAdapter absQuickSearchFilterListViewAdapter = this.e;
        switch (i2) {
            case 0:
                ((RecyclerView) obj).setAdapter((QuickSearchPeopleFilterListViewAdapter) absQuickSearchFilterListViewAdapter);
                return;
            default:
                ((RecyclerView) obj).setAdapter((QuickSearchLocationFilterListViewAdapter) absQuickSearchFilterListViewAdapter);
                return;
        }
    }
}
