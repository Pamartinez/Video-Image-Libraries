package com.samsung.android.sum.core.filter.factory;

import com.samsung.android.sum.core.descriptor.NNFWProfile;
import com.samsung.android.sum.core.plugin.ImgpPlugin;
import com.samsung.android.sum.core.plugin.PluginFixture;
import com.samsung.android.sum.core.plugin.PluginStore;
import java.util.Optional;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4083a;

    public /* synthetic */ c(int i2) {
        this.f4083a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f4083a) {
            case 0:
                return MediaFilterCreatorChain.lambda$prepare$3((MediaFilterCreator) obj);
            case 1:
                return ((PluginStore.Entry) obj).getDescriptor();
            case 2:
                return ((NNFWProfile) obj).flatten();
            case 3:
                return ((ImgpPlugin.Type) obj).name();
            case 4:
                return (PluginFixture) ((Optional) obj).get();
            default:
                return ((PluginStore.Entry) obj).getPluginFixture();
        }
    }
}
