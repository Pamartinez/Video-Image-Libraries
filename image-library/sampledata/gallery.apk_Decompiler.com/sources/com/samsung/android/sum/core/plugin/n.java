package com.samsung.android.sum.core.plugin;

import com.samsung.android.sum.core.functional.DescriptorStreamLoader;
import java.util.Map;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4141a;
    public final /* synthetic */ StaplePluginStore b;

    public /* synthetic */ n(StaplePluginStore staplePluginStore, int i2) {
        this.f4141a = i2;
        this.b = staplePluginStore;
    }

    public final Object apply(Object obj) {
        int i2 = this.f4141a;
        StaplePluginStore staplePluginStore = this.b;
        switch (i2) {
            case 0:
                return staplePluginStore.lambda$remove$3((Map.Entry) obj);
            default:
                return staplePluginStore.lambda$add$1((DescriptorStreamLoader) obj);
        }
    }
}
