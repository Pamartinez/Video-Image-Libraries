package com.samsung.android.sum.core.channel;

import java.util.concurrent.Future;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Consumer {
    public final void accept(Object obj) {
        ((Future) obj).cancel(true);
    }
}
