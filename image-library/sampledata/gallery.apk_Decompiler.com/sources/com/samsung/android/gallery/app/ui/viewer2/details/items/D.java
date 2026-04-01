package com.samsung.android.gallery.app.ui.viewer2.details.items;

import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class D implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ ViewHolderTag e;

    public /* synthetic */ D(ViewHolderTag viewHolderTag, int i2) {
        this.d = i2;
        this.e = viewHolderTag;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        ViewHolderTag viewHolderTag = this.e;
        switch (i2) {
            case 0:
                viewHolderTag.lambda$bindView$0(view);
                return;
            default:
                viewHolderTag.lambda$bindView$1(view);
                return;
        }
    }
}
