package com.samsung.android.gallery.module.aiedit;

import com.samsung.android.gallery.support.library.abstraction.VslMesDetectorCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ RemasterDetector2 d;
    public final /* synthetic */ VslMesDetectorCompat e;

    public /* synthetic */ b(RemasterDetector2 remasterDetector2, VslMesDetectorCompat vslMesDetectorCompat) {
        this.d = remasterDetector2;
        this.e = vslMesDetectorCompat;
    }

    public final void run() {
        this.d.lambda$releaseAsync$1(this.e);
    }
}
