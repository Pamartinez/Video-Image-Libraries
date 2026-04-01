package com.samsung.android.gallery.app.ui.viewer2.container.delegate.pager;

import com.samsung.android.gallery.app.ui.viewer2.container.delegate.pager.ViewPagerDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ViewPagerDelegate.AnonymousClass1 e;
    public final /* synthetic */ int f;

    public /* synthetic */ a(ViewPagerDelegate.AnonymousClass1 r1, int i2, int i7) {
        this.d = i7;
        this.e = r1;
        this.f = i2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$onPageSelected$0(this.f);
                return;
            default:
                this.e.lambda$onPageSelectedInternal$1(this.f);
                return;
        }
    }
}
