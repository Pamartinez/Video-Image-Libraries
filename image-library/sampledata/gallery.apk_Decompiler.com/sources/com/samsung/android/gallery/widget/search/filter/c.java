package com.samsung.android.gallery.widget.search.filter;

import com.samsung.android.gallery.widget.search.filter.OnlyThemView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ OnlyThemView.OnlyThemViewHolder d;
    public final /* synthetic */ boolean e;

    public /* synthetic */ c(OnlyThemView.OnlyThemViewHolder onlyThemViewHolder, boolean z) {
        this.d = onlyThemViewHolder;
        this.e = z;
    }

    public final void run() {
        this.d.lambda$restoreTitle$0(this.e);
    }
}
