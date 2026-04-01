package com.samsung.android.sum.core.graph;

import com.samsung.android.sum.core.plugin.NativeUniImgpPlugin;
import java.util.HashMap;
import java.util.List;
import java.util.function.IntFunction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class r implements IntFunction {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4118a;
    public final /* synthetic */ Object b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f4119c;

    public /* synthetic */ r(int i2, Object obj, Object obj2) {
        this.f4118a = i2;
        this.b = obj;
        this.f4119c = obj2;
    }

    public final Object apply(int i2) {
        switch (this.f4118a) {
            case 0:
                return MFGraph.lambda$run$4((List) this.b, (List) this.f4119c, i2);
            default:
                return ((NativeUniImgpPlugin) this.b).lambda$makeBufferFromMap$4((HashMap) this.f4119c, i2);
        }
    }
}
