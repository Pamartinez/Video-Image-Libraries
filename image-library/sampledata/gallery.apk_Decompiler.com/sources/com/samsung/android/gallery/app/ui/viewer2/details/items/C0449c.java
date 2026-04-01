package com.samsung.android.gallery.app.ui.viewer2.details.items;

import android.view.View;

/* renamed from: com.samsung.android.gallery.app.ui.viewer2.details.items.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0449c implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ DetailsItem3rdParty e;

    public /* synthetic */ C0449c(DetailsItem3rdParty detailsItem3rdParty, int i2) {
        this.d = i2;
        this.e = detailsItem3rdParty;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        DetailsItem3rdParty detailsItem3rdParty = this.e;
        switch (i2) {
            case 0:
                detailsItem3rdParty.lambda$onViewInflate$1(view);
                return;
            default:
                detailsItem3rdParty.lambda$onViewInflate$2(view);
                return;
        }
    }
}
