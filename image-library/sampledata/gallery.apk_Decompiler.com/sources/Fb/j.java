package Fb;

import com.samsung.android.gallery.support.library.abstraction.BoosterCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ BoosterCompat e;

    public /* synthetic */ j(BoosterCompat boosterCompat, int i2) {
        this.d = i2;
        this.e = boosterCompat;
    }

    public final void run() {
        int i2 = this.d;
        BoosterCompat boosterCompat = this.e;
        switch (i2) {
            case 0:
                boosterCompat.releaseScroll();
                return;
            default:
                boosterCompat.acquireScroll();
                return;
        }
    }
}
