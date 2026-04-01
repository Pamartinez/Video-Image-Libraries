package l7;

import com.samsung.android.gallery.app.ui.viewer2.container.delegate.film.FilmStripSeekerDelegate;

/* renamed from: l7.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0485c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ FilmStripSeekerDelegate e;

    public /* synthetic */ C0485c(FilmStripSeekerDelegate filmStripSeekerDelegate, int i2) {
        this.d = i2;
        this.e = filmStripSeekerDelegate;
    }

    public final void run() {
        int i2 = this.d;
        FilmStripSeekerDelegate filmStripSeekerDelegate = this.e;
        switch (i2) {
            case 0:
                filmStripSeekerDelegate.lambda$setActionInvokeListener$4();
                return;
            default:
                filmStripSeekerDelegate.lambda$onItemRestored$8();
                return;
        }
    }
}
