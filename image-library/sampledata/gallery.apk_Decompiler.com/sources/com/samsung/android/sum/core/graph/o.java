package com.samsung.android.sum.core.graph;

import com.samsung.android.sum.core.channel.BufferChannel;
import com.samsung.android.sum.core.graph.MFDescriptorGraph;
import java.util.Map;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class o implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4117a;
    public final /* synthetic */ Object b;

    public /* synthetic */ o(int i2, Object obj) {
        this.f4117a = i2;
        this.b = obj;
    }

    public final Object apply(Object obj) {
        int i2 = this.f4117a;
        Object obj2 = this.b;
        switch (i2) {
            case 0:
                return MFDescriptorGraph.lambda$toMediaFilterGraph$2((MFGraphUnitFactory) obj2, (MFDescriptorGraph.DescriptorNode) obj);
            case 1:
                return ((MFGraphUnitFactory) obj2).newBufferChannel(((Integer) obj).intValue());
            default:
                return (BufferChannel) ((Map) obj2).get((Enum) obj);
        }
    }
}
