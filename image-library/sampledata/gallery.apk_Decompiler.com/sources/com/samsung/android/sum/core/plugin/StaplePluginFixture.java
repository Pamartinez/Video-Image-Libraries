package com.samsung.android.sum.core.plugin;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StaplePluginFixture extends PluginFixture<StaplePluginFixture> {
    public StaplePluginFixture(Plugin<? extends StaplePluginFixture> plugin) {
        super(plugin);
    }

    public boolean isValid() {
        return !this.operatorMap.keySet().isEmpty();
    }
}
