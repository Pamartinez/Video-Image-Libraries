package com.samsung.android.gallery.widget.search.searchbar.selectedfilter;

import java.util.ArrayList;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SearchSelectedFiltersViewEditable f3221a;

    public /* synthetic */ c(SearchSelectedFiltersViewEditable searchSelectedFiltersViewEditable) {
        this.f3221a = searchSelectedFiltersViewEditable;
    }

    public final void accept(Object obj, Object obj2) {
        this.f3221a.onAutoCompleteItemsLoaded((ArrayList) obj, (String) obj2);
    }
}
