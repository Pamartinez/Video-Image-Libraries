package com.samsung.android.sum.core.graph;

import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4114a;
    public final /* synthetic */ GraphNodeBase b;

    public /* synthetic */ i(GraphNodeBase graphNodeBase, int i2) {
        this.f4114a = i2;
        this.b = graphNodeBase;
    }

    public final Object apply(Object obj) {
        int i2 = this.f4114a;
        GraphNodeBase graphNodeBase = this.b;
        switch (i2) {
            case 0:
                return Boolean.valueOf(graphNodeBase.parseException((Exception) obj));
            case 1:
                return graphNodeBase.lambda$configInputChannels$3((Enum) obj);
            case 2:
                return graphNodeBase.lambda$configInputChannels$4((Enum) obj);
            default:
                return graphNodeBase.lambda$configOutputChannels$7((Enum) obj);
        }
    }
}
