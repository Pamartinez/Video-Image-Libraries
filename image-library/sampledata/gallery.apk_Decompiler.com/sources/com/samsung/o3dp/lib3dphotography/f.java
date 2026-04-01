package com.samsung.o3dp.lib3dphotography;

import android.content.Context;
import android.graphics.Bitmap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Runnable {
    public final /* synthetic */ GLContextManager d;
    public final /* synthetic */ Bitmap e;
    public final /* synthetic */ Bitmap f;
    public final /* synthetic */ DepthMap g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ DepthMap f4207h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ O3DPListener f4208i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ Context f4209j;

    public /* synthetic */ f(GLContextManager gLContextManager, Bitmap bitmap, Bitmap bitmap2, DepthMap depthMap, DepthMap depthMap2, O3DPListener o3DPListener, Context context) {
        this.d = gLContextManager;
        this.e = bitmap;
        this.f = bitmap2;
        this.g = depthMap;
        this.f4207h = depthMap2;
        this.f4208i = o3DPListener;
        this.f4209j = context;
    }

    public final void run() {
        this.d.lambda$init3DShader$2(this.e, this.f, this.g, this.f4207h, this.f4208i, this.f4209j);
    }
}
