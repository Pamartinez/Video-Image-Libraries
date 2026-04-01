package com.samsung.android.gallery.app.activity.preload;

import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ AtomicBoolean d;

    public /* synthetic */ b(AtomicBoolean atomicBoolean) {
        this.d = atomicBoolean;
    }

    public final void accept(Object obj) {
        this.d.set(((LaunchIntent) obj).isFromBixby());
    }
}
