package com.samsung.android.sum.core.descriptor;

import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ PluginDescriptorPair e;

    public /* synthetic */ c(PluginDescriptorPair pluginDescriptorPair, int i2) {
        this.d = i2;
        this.e = pluginDescriptorPair;
    }

    public final Object get() {
        int i2 = this.d;
        PluginDescriptorPair pluginDescriptorPair = this.e;
        switch (i2) {
            case 0:
                return pluginDescriptorPair.lambda$getInputFormat$0();
            case 1:
                return pluginDescriptorPair.lambda$getTargetFormat$2();
            default:
                return pluginDescriptorPair.lambda$getOutputFormat$1();
        }
    }
}
