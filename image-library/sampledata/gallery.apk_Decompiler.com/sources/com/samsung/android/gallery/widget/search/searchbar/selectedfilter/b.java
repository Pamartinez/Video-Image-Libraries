package com.samsung.android.gallery.widget.search.searchbar.selectedfilter;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.search.searchbar.selectedfilter.SearchSelectedFiltersEditableAdapter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ b(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((SearchSelectedFiltersEditableAdapter.AnonymousClass1) this.e).lambda$onTextChanged$0((String) this.f);
                return;
            default:
                ((SearchSelectedFiltersViewHolder) this.e).lambda$setRoundThumb$0((MediaItem) this.f);
                return;
        }
    }
}
