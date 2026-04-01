package com.samsung.android.gallery.module.dataset;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class D0 implements Runnable {
    public final /* synthetic */ MediaDataTimeline d;
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;

    public /* synthetic */ D0(MediaDataTimeline mediaDataTimeline, int i2, int i7) {
        this.d = mediaDataTimeline;
        this.e = i2;
        this.f = i7;
    }

    public final void run() {
        this.d.lambda$updateDataRange$13(this.e, this.f);
    }
}
