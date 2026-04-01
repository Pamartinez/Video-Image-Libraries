package com.samsung.scsp.common;

import java.util.Map;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Consumer {
    public final /* synthetic */ PreferenceItem d;
    public final /* synthetic */ Object e;

    public /* synthetic */ g(PreferenceItem preferenceItem, Object obj) {
        this.d = preferenceItem;
        this.e = obj;
    }

    public final void accept(Object obj) {
        this.d.lambda$accept$8(this.e, (Map.Entry) obj);
    }
}
