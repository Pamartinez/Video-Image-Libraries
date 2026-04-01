package com.samsung.o3dp.lib3dphotography;

import android.content.Context;
import com.samsung.o3dp.lib3dphotography.O3DPContext;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ GLContextManager e;
    public final /* synthetic */ O3DPContext.LayerBitmapInfo[] f;
    public final /* synthetic */ Context g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ O3DPListener f4203h;

    public /* synthetic */ c(GLContextManager gLContextManager, O3DPContext.LayerBitmapInfo[] layerBitmapInfoArr, Context context, O3DPListener o3DPListener, int i2) {
        this.d = i2;
        this.e = gLContextManager;
        this.f = layerBitmapInfoArr;
        this.g = context;
        this.f4203h = o3DPListener;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$init3DShader$0(this.f, this.g, this.f4203h);
                return;
            default:
                this.e.lambda$init3DShader$1(this.f, this.g, this.f4203h);
                return;
        }
    }
}
