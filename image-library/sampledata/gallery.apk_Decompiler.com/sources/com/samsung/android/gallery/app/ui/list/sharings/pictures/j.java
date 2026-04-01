package com.samsung.android.gallery.app.ui.list.sharings.pictures;

import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ j(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        SharingCoverView sharingCoverView = (SharingCoverView) obj;
        switch (this.d) {
            case 0:
                sharingCoverView.setSelectionMode(true);
                return;
            default:
                sharingCoverView.setSelectionMode(false);
                return;
        }
    }
}
