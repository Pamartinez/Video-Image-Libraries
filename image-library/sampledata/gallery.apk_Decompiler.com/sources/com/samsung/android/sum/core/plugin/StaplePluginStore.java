package com.samsung.android.sum.core.plugin;

import android.content.Context;
import com.samsung.android.sum.core.buffer.u;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.functional.DescriptorStreamLoader;
import com.samsung.android.sum.core.plugin.PluginStore;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class StaplePluginStore extends PluginStore {
    public StaplePluginStore(Context context) {
        super(context);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Optional lambda$add$1(DescriptorStreamLoader descriptorStreamLoader) {
        return Optional.ofNullable(this.context).map(new m(0, descriptorStreamLoader));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$remove$2(PluginFixture pluginFixture, Map.Entry entry) {
        if (((PluginStore.Entry) entry.getValue()).getPluginFixture() == pluginFixture) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ PluginFixture lambda$remove$3(Map.Entry entry) {
        return this.registry.remove(entry.getKey()).getPluginFixture();
    }

    public PluginStore add(PluginFixture<?> pluginFixture) {
        MFDescriptor mFDescriptor = (MFDescriptor) Stream.of(new Optional[]{Optional.ofNullable(pluginFixture.getDescriptorLoader()).map(new b(6)), Optional.ofNullable(pluginFixture.getDescriptorStreamLoader()).flatMap(new n(this, 1))}).filter(new k(3)).findFirst().flatMap(Function.identity()).orElseThrow(new u(4));
        this.registry.put(mFDescriptor.getFilterId(), new PluginStore.Entry(mFDescriptor, pluginFixture));
        return this;
    }

    public PluginStore.Entry get(MFDescriptor mFDescriptor) {
        return this.registry.get(mFDescriptor.getFilterId());
    }

    public PluginFixture<?> remove(PluginFixture<?> pluginFixture) {
        return (PluginFixture) this.registry.entrySet().stream().filter(new a(2, pluginFixture)).findFirst().map(new n(this, 0)).orElse((Object) null);
    }
}
