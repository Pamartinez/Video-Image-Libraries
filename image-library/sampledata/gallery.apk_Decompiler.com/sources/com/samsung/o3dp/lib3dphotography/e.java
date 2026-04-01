package com.samsung.o3dp.lib3dphotography;

import android.content.Context;
import com.samsung.o3dp.lib3dphotography.MeshUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ GLContextManager e;
    public final /* synthetic */ Context f;
    public final /* synthetic */ MeshUtils.MeshInfo g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ O3DPListener f4206h;

    public /* synthetic */ e(GLContextManager gLContextManager, Context context, MeshUtils.MeshInfo meshInfo, O3DPListener o3DPListener, int i2) {
        this.d = i2;
        this.e = gLContextManager;
        this.f = context;
        this.g = meshInfo;
        this.f4206h = o3DPListener;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$init3DShader$4(this.f, this.g, this.f4206h);
                return;
            default:
                this.e.lambda$init3DShader$3(this.f, this.g, this.f4206h);
                return;
        }
    }
}
