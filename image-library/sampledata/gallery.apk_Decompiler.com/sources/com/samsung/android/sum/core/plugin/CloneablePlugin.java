package com.samsung.android.sum.core.plugin;

import com.samsung.android.sum.core.plugin.PluginFixture;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CloneablePlugin<T extends PluginFixture<T>> implements Plugin<T>, Cloneable {
    public Plugin<? extends PluginFixture<T>> clone() {
        try {
            return (CloneablePlugin) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("fail to clone plugin: ".concat(getClass().getSimpleName()), e);
        }
    }
}
