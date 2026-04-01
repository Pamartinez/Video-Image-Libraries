package com.samsung.android.sum.core.plugin;

import com.samsung.android.sum.core.plugin.PluginFixture;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface Plugin<T extends PluginFixture<?>> {
    void bindToFixture(T t);

    Stream<Plugin<? extends PluginFixture<?>>> stream() {
        return Stream.of(this);
    }
}
