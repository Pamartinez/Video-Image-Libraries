package com.samsung.android.gallery.widget.search.searchbar.selectedfilter;

import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ SearchSelectedFiltersViewEditable d;
    public final /* synthetic */ ArrayList e;
    public final /* synthetic */ String f;

    public /* synthetic */ e(SearchSelectedFiltersViewEditable searchSelectedFiltersViewEditable, ArrayList arrayList, String str) {
        this.d = searchSelectedFiltersViewEditable;
        this.e = arrayList;
        this.f = str;
    }

    public final void run() {
        this.d.lambda$onAutoCompleteItemsLoaded$0(this.e, this.f);
    }
}
