package com.samsung.android.gallery.app.ui.list.sharings.pictures;

import android.widget.TextView;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ SharingHeaderViewDelegate e;

    public /* synthetic */ d(SharingHeaderViewDelegate sharingHeaderViewDelegate, int i2) {
        this.d = i2;
        this.e = sharingHeaderViewDelegate;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        SharingHeaderViewDelegate sharingHeaderViewDelegate = this.e;
        TextView textView = (TextView) obj;
        switch (i2) {
            case 0:
                sharingHeaderViewDelegate.lambda$composeTipCard$2(textView);
                return;
            default:
                sharingHeaderViewDelegate.lambda$composeTipCard$4(textView);
                return;
        }
    }
}
