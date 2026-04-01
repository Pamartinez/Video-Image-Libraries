package com.samsung.android.gallery.support.config;

import android.util.ArrayMap;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ ArrayMap d;

    public /* synthetic */ b(ArrayMap arrayMap) {
        this.d = arrayMap;
    }

    public final void accept(Object obj) {
        this.d.put(((String[]) obj)[0], ((String[]) obj)[1]);
    }
}
