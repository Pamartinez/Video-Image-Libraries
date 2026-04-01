package b4;

import com.samsung.android.gallery.app.remote.v2.PresentationViewPagerHolder;

/* renamed from: b4.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0425e implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ PresentationViewPagerHolder e;

    public /* synthetic */ C0425e(PresentationViewPagerHolder presentationViewPagerHolder, int i2) {
        this.d = i2;
        this.e = presentationViewPagerHolder;
    }

    public final void run() {
        int i2 = this.d;
        PresentationViewPagerHolder presentationViewPagerHolder = this.e;
        switch (i2) {
            case 0:
                presentationViewPagerHolder.lambda$hideMediaView$2();
                return;
            default:
                presentationViewPagerHolder.lambda$showMediaView$1();
                return;
        }
    }
}
