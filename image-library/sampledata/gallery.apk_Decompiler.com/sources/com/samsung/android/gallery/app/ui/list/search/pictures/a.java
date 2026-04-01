package com.samsung.android.gallery.app.ui.list.search.pictures;

import com.samsung.android.gallery.app.ui.list.search.pictures.ExpansionHelper;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ AtomicInteger d;

    public /* synthetic */ a(AtomicInteger atomicInteger) {
        this.d = atomicInteger;
    }

    public final void accept(Object obj) {
        this.d.addAndGet(((ExpansionHelper.ExpandedInfo) obj).getExpandedCount() - ((ExpansionHelper.ExpandedInfo) obj).getOriginCount());
    }
}
