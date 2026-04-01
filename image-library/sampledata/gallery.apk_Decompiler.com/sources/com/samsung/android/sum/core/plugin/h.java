package com.samsung.android.sum.core.plugin;

import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Supplier {
    public final /* synthetic */ Plugin d;

    public /* synthetic */ h(Plugin plugin) {
        this.d = plugin;
    }

    public final Object get() {
        return Boolean.valueOf(this.d instanceof CloneablePlugin);
    }
}
