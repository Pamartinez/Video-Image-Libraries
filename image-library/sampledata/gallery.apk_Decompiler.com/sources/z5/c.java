package z5;

import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.RecommendContactDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ RecommendContactDelegate e;

    public /* synthetic */ c(RecommendContactDelegate recommendContactDelegate, int i2) {
        this.d = i2;
        this.e = recommendContactDelegate;
    }

    public final void run() {
        int i2 = this.d;
        RecommendContactDelegate recommendContactDelegate = this.e;
        switch (i2) {
            case 0:
                recommendContactDelegate.lambda$onRequestPermissionResult$2();
                return;
            case 1:
                recommendContactDelegate.lambda$loadRecommendContact$1();
                return;
            default:
                recommendContactDelegate.lambda$loadRecommendContact$0();
                return;
        }
    }
}
