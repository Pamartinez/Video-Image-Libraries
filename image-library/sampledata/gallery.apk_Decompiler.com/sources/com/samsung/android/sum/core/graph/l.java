package com.samsung.android.sum.core.graph;

import com.samsung.android.sum.core.channel.BufferChannel;
import java.util.HashMap;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4115a;
    public final /* synthetic */ HashMap b;

    public /* synthetic */ l(int i2, HashMap hashMap) {
        this.f4115a = i2;
        this.b = hashMap;
    }

    public final Object apply(Object obj) {
        int i2 = this.f4115a;
        HashMap hashMap = this.b;
        Enum enumR = (Enum) obj;
        switch (i2) {
            case 0:
                return (BufferChannel) hashMap.get(enumR);
            default:
                return GraphNodeBase.lambda$configOutputChannels$6(hashMap, enumR);
        }
    }
}
