package com.samsung.android.gallery.app.activity.external;

import com.samsung.android.gallery.app.activity.ViewNavigatorController;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Runnable {
    public final /* synthetic */ int d = 0;
    public final /* synthetic */ int e;
    public final /* synthetic */ MediaItem[] f;
    public final /* synthetic */ ViewNavigatorController g;

    public /* synthetic */ h(QuickViewNavigatorController quickViewNavigatorController, MediaItem[] mediaItemArr, int i2) {
        this.g = quickViewNavigatorController;
        this.f = mediaItemArr;
        this.e = i2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((QuickViewNavigatorController) this.g).lambda$loadQuickViewInternal$3(this.f, this.e);
                return;
            default:
                ((SharingsViewNavigatorController) this.g).lambda$launchSharingChoice$3(this.e, this.f);
                return;
        }
    }

    public /* synthetic */ h(SharingsViewNavigatorController sharingsViewNavigatorController, int i2, MediaItem[] mediaItemArr) {
        this.g = sharingsViewNavigatorController;
        this.e = i2;
        this.f = mediaItemArr;
    }
}
