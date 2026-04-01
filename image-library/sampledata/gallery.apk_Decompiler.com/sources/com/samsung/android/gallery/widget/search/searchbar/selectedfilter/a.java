package com.samsung.android.gallery.widget.search.searchbar.selectedfilter;

import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SearchSelectedFiltersAdapter e;
    public final /* synthetic */ SearchSelectedFiltersViewHolder f;

    public /* synthetic */ a(SearchSelectedFiltersAdapter searchSelectedFiltersAdapter, SearchSelectedFiltersViewHolder searchSelectedFiltersViewHolder, int i2) {
        this.d = i2;
        this.e = searchSelectedFiltersAdapter;
        this.f = searchSelectedFiltersViewHolder;
    }

    public final void onClick(View view) {
        switch (this.d) {
            case 0:
                this.e.lambda$updateDeleteButton$0(this.f, view);
                return;
            default:
                this.e.lambda$updateDeleteButton$1(this.f, view);
                return;
        }
    }
}
