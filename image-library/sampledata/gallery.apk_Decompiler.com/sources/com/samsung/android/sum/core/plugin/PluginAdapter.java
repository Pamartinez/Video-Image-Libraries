package com.samsung.android.sum.core.plugin;

import com.samsung.android.sum.core.plugin.PluginFixture;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PluginAdapter<T extends PluginFixture<?>> implements Plugin<T> {
    private final Consumer<T> function;
    private final Class<T> pluginType;

    public PluginAdapter(Class<T> cls, Consumer<T> consumer) {
        this.pluginType = cls;
        this.function = consumer;
    }

    public void bindToFixture(T t) {
        this.function.accept(t);
    }

    public Class<T> getPluginType() {
        return this.pluginType;
    }
}
