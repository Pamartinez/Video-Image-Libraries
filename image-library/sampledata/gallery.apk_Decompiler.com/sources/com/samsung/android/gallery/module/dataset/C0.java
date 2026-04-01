package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.module.dataset.tables.DefaultTable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0 implements DefaultTable.OnLoadDoneListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MediaDataTimeline f2942a;
    public final /* synthetic */ int b;

    public /* synthetic */ C0(MediaDataTimeline mediaDataTimeline, int i2) {
        this.f2942a = mediaDataTimeline;
        this.b = i2;
    }

    public final void onLoadDone() {
        this.f2942a.lambda$getOnLoadDoneListener$5(this.b);
    }
}
