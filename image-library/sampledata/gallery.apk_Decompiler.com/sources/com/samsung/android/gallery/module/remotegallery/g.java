package com.samsung.android.gallery.module.remotegallery;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ RemoteClient e;
    public final /* synthetic */ long f;
    public final /* synthetic */ AtomicReference g;

    public /* synthetic */ g(RemoteClient remoteClient, long j2, AtomicReference atomicReference, int i2) {
        this.d = i2;
        this.e = remoteClient;
        this.f = j2;
        this.g = atomicReference;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$getPreview$2(this.f, this.g);
                return;
            default:
                this.e.lambda$getThumb$3(this.f, this.g);
                return;
        }
    }
}
