package com.samsung.android.sum.core.plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PluginGroup implements Plugin<PluginFixture<?>> {
    protected List<Plugin<?>> plugins = new ArrayList();

    public void addImgpPlugin(Consumer<ImgpPlugin> consumer) {
        this.plugins.add(new PluginAdapter(ImgpPlugin.class, consumer));
    }

    public void addNNPlugin(Consumer<NNPlugin> consumer) {
        this.plugins.add(new PluginAdapter(NNPlugin.class, consumer));
    }

    public void bindToFixture(PluginFixture<?> pluginFixture) {
        throw new UnsupportedOperationException("use stream for PluginGroup");
    }

    public Stream<Plugin<? extends PluginFixture<?>>> stream() {
        return this.plugins.stream();
    }
}
