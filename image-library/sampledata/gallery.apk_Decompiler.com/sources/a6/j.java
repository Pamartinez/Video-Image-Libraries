package a6;

import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.PdcRecommendDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ PdcRecommendDelegate e;

    public /* synthetic */ j(PdcRecommendDelegate pdcRecommendDelegate, int i2) {
        this.d = i2;
        this.e = pdcRecommendDelegate;
    }

    public final void run() {
        int i2 = this.d;
        PdcRecommendDelegate pdcRecommendDelegate = this.e;
        switch (i2) {
            case 0:
                pdcRecommendDelegate.lambda$invalidateView$1();
                return;
            default:
                pdcRecommendDelegate.updateLayout();
                return;
        }
    }
}
