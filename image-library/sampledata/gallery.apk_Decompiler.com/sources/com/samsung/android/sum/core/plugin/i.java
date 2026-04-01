package com.samsung.android.sum.core.plugin;

import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ i(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((CloneablePlugin) ((Plugin) obj2)).clone().bindToFixture((PluginFixture) obj);
                return;
            default:
                ((PluginStore) obj2).lambda$add$3((Plugin) obj);
                return;
        }
    }
}
