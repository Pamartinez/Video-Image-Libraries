package com.samsung.android.sum.core.filter.factory;

import com.samsung.android.sum.core.plugin.ImgpPlugin;
import com.samsung.android.sum.core.plugin.PluginFixture;
import java.util.function.BinaryOperator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class o implements BinaryOperator {
    public final Object apply(Object obj, Object obj2) {
        return ImgpPlugin.join((ImgpPlugin) ((PluginFixture) obj), (ImgpPlugin) ((PluginFixture) obj2));
    }
}
