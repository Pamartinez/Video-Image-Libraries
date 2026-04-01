package com.samsung.android.gallery.plugins.panorama;

import com.samsung.android.gallery.plugins.panorama.PanoramaActivity;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ PanoramaActivity.AnonymousClass1 d;
    public final /* synthetic */ boolean e;

    public /* synthetic */ a(PanoramaActivity.AnonymousClass1 r1, boolean z) {
        this.d = r1;
        this.e = z;
    }

    public final void run() {
        this.d.lambda$onTableModeChanged$0(this.e);
    }
}
