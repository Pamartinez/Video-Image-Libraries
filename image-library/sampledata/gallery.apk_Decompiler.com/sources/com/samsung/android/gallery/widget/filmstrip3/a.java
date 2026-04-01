package com.samsung.android.gallery.widget.filmstrip3;

import com.samsung.android.gallery.widget.filmstrip3.FilmStripView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ FilmStripView.AnonymousClass2 e;

    public /* synthetic */ a(FilmStripView.AnonymousClass2 r1, int i2) {
        this.d = i2;
        this.e = r1;
    }

    public final void run() {
        int i2 = this.d;
        FilmStripView.AnonymousClass2 r1 = this.e;
        switch (i2) {
            case 0:
                r1.lambda$onScrollStateChanged$0();
                return;
            default:
                r1.lambda$finishSmoothScroll$1();
                return;
        }
    }
}
