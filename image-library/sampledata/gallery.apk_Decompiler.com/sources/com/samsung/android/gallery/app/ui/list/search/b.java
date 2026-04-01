package com.samsung.android.gallery.app.ui.list.search;

import android.view.View;
import com.samsung.android.gallery.module.dataset.MediaData;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements View.OnClickListener {
    public final /* synthetic */ CategoryCardHolderV2 d;
    public final /* synthetic */ ISearchView e;
    public final /* synthetic */ MediaData f;
    public final /* synthetic */ int g;

    public /* synthetic */ b(CategoryCardHolderV2 categoryCardHolderV2, ISearchView iSearchView, MediaData mediaData, int i2) {
        this.d = categoryCardHolderV2;
        this.e = iSearchView;
        this.f = mediaData;
        this.g = i2;
    }

    public final void onClick(View view) {
        this.d.lambda$setArrow$0(this.e, this.f, this.g, view);
    }
}
