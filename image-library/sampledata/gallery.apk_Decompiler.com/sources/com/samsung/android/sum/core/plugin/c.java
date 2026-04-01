package com.samsung.android.sum.core.plugin;

import com.samsung.android.sum.core.functional.Operator;
import com.samsung.android.sum.core.plugin.ImgpPlugin;
import java.util.Map;
import java.util.function.BinaryOperator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements BinaryOperator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4137a;

    public /* synthetic */ c(int i2) {
        this.f4137a = i2;
    }

    public final Object apply(Object obj, Object obj2) {
        switch (this.f4137a) {
            case 0:
                return ImgpPlugin.ImgpPluginGroup.lambda$new$1((Operator) obj, (Operator) obj2);
            default:
                return PluginStore.lambda$of$8((Map) obj, (Map) obj2);
        }
    }
}
