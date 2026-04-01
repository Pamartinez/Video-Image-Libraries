package com.samsung.android.gallery.widget.search.filter;

import O3.l;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ Runnable d;

    public /* synthetic */ b(Runnable runnable) {
        this.d = runnable;
    }

    public final void run() {
        Optional.ofNullable(this.d).ifPresent(new l(0));
    }
}
