package com.samsung.o3dp.lib3dphotography;

import android.content.Context;
import com.samsung.o3dp.lib3dphotography.MeshUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ GLContextManager d;
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;
    public final /* synthetic */ O3DPListener g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Context f4204h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ MeshUtils.MeshInfo f4205i;

    public /* synthetic */ d(GLContextManager gLContextManager, int i2, int i7, O3DPListener o3DPListener, Context context, MeshUtils.MeshInfo meshInfo) {
        this.d = gLContextManager;
        this.e = i2;
        this.f = i7;
        this.g = o3DPListener;
        this.f4204h = context;
        this.f4205i = meshInfo;
    }

    public final void run() {
        this.d.lambda$init3DShader$5(this.e, this.f, this.g, this.f4204h, this.f4205i);
    }
}
