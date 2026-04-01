package com.samsung.android.gallery.module.remotegallery;

import com.samsung.android.gallery.module.data.MediaItem;
import java.util.concurrent.CountDownLatch;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ c(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((ConnectionWrap) this.e).lambda$run$1((TransferHeader) this.f);
                return;
            case 1:
                ((RemoteClient) this.e).lambda$sendFiles$0((MediaItem[]) this.f);
                return;
            case 2:
                ((RemoteServer) this.e).lambda$start$1((CountDownLatch) this.f);
                return;
            default:
                ((RemoteWebServer) this.e).lambda$start$0((CountDownLatch) this.f);
                return;
        }
    }
}
