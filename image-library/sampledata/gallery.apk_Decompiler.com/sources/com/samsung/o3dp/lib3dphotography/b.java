package com.samsung.o3dp.lib3dphotography;

import java.util.function.Consumer;

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
                ((Consumer) obj).accept((Object) null);
                return;
            default:
                ((O3DPSurfaceView) obj).lambda$onDrawFrame$2();
                return;
        }
    }
}
