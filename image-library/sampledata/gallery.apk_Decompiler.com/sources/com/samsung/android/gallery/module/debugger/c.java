package com.samsung.android.gallery.module.debugger;

import com.samsung.android.gallery.module.debugger.LeakTracker;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ AtomicBoolean d;

    public /* synthetic */ c(AtomicBoolean atomicBoolean) {
        this.d = atomicBoolean;
    }

    public final void accept(Object obj) {
        LeakTracker.LeakTracHandler.lambda$checkViewerBitmap$1(this.d, (String) obj);
    }
}
