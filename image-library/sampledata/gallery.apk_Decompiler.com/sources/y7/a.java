package Y7;

import com.samsung.android.gallery.app.ui.viewer2.slideshow.delegate.SlideDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SlideDelegate e;

    public /* synthetic */ a(SlideDelegate slideDelegate, int i2) {
        this.d = i2;
        this.e = slideDelegate;
    }

    public final void run() {
        int i2 = this.d;
        SlideDelegate slideDelegate = this.e;
        switch (i2) {
            case 0:
                slideDelegate.lambda$stopMove$1();
                return;
            default:
                slideDelegate.lambda$stopMove$2();
                return;
        }
    }
}
