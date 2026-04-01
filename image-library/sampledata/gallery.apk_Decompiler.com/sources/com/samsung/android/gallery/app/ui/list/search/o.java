package com.samsung.android.gallery.app.ui.list.search;

import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class o implements View.OnClickListener {
    public final /* synthetic */ ISearchView d;
    public final /* synthetic */ String e;

    public /* synthetic */ o(ISearchView iSearchView, String str) {
        this.d = iSearchView;
        this.e = str;
    }

    public final void onClick(View view) {
        this.d.onCategoryExpansionClick(this.e);
    }
}
