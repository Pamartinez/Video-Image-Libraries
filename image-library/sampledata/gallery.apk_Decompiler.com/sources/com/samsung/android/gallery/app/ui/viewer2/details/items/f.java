package com.samsung.android.gallery.app.ui.viewer2.details.items;

import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ DetailsItemC2pa e;

    public /* synthetic */ f(DetailsItemC2pa detailsItemC2pa, int i2) {
        this.d = i2;
        this.e = detailsItemC2pa;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        DetailsItemC2pa detailsItemC2pa = this.e;
        switch (i2) {
            case 0:
                detailsItemC2pa.lambda$setToolbar$2(view);
                return;
            default:
                detailsItemC2pa.lambda$onViewInflate$0(view);
                return;
        }
    }
}
