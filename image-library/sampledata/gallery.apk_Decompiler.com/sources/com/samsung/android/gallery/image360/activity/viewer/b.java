package com.samsung.android.gallery.image360.activity.viewer;

import com.samsung.android.gallery.image360.activity.viewer.Image360Presenter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ b(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void run() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((Image360Presenter.AnonymousClass1) obj).lambda$call$0();
                return;
            default:
                ((Image360Presenter.ImageDecodeListener) obj).lambda$onFutureDone$0();
                return;
        }
    }
}
