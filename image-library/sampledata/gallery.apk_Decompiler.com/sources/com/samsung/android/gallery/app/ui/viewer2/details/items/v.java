package com.samsung.android.gallery.app.ui.viewer2.details.items;

import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class v implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ DetailsItemLocation e;

    public /* synthetic */ v(DetailsItemLocation detailsItemLocation, int i2) {
        this.d = i2;
        this.e = detailsItemLocation;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        DetailsItemLocation detailsItemLocation = this.e;
        switch (i2) {
            case 0:
                detailsItemLocation.lambda$onViewInflate$0(view);
                return;
            case 1:
                detailsItemLocation.moveToSettings(view);
                return;
            default:
                detailsItemLocation.openNavigation(view);
                return;
        }
    }
}
