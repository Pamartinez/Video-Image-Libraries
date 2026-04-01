package com.samsung.android.gallery.app.ui.list.trash;

import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Runnable {
    public final /* synthetic */ TrashPresenter d;
    public final /* synthetic */ List e;
    public final /* synthetic */ long f;

    public /* synthetic */ f(TrashPresenter trashPresenter, List list, long j2) {
        this.d = trashPresenter;
        this.e = list;
        this.f = j2;
    }

    public final void run() {
        this.d.lambda$cleanUpIfEmpty$1(this.e, this.f);
    }
}
