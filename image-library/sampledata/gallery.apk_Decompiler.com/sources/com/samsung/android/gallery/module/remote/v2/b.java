package com.samsung.android.gallery.module.remote.v2;

import com.samsung.android.gallery.module.remote.v2.RemoteDisplayState;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ RemoteDisplayState.AnonymousClass1 e;
    public final /* synthetic */ int f;

    public /* synthetic */ b(RemoteDisplayState.AnonymousClass1 r1, int i2, int i7) {
        this.d = i7;
        this.e = r1;
        this.f = i2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$onDisplayRemoved$1(this.f);
                return;
            default:
                this.e.lambda$onDisplayAdded$0(this.f);
                return;
        }
    }
}
