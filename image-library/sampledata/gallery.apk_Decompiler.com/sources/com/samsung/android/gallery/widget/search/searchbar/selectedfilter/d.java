package com.samsung.android.gallery.widget.search.searchbar.selectedfilter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ SearchSelectedFiltersViewEditable d;

    public /* synthetic */ d(SearchSelectedFiltersViewEditable searchSelectedFiltersViewEditable) {
        this.d = searchSelectedFiltersViewEditable;
    }

    public final void run() {
        this.d.dismissAutoCompleteView();
    }
}
