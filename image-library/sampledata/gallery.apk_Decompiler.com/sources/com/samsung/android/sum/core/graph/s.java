package com.samsung.android.sum.core.graph;

import com.samsung.android.sum.core.buffer.MediaBuffer;
import java.util.List;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class s implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4120a;
    public final /* synthetic */ List b;

    public /* synthetic */ s(int i2, List list) {
        this.f4120a = i2;
        this.b = list;
    }

    public final Object apply(Object obj) {
        switch (this.f4120a) {
            case 0:
                return MFGraph.lambda$run$5(this.b, (Integer) obj);
            case 1:
                return (MediaBuffer) this.b.get(((Integer) obj).intValue());
            default:
                return MFGraph.lambda$run$6(this.b, (Integer) obj);
        }
    }
}
