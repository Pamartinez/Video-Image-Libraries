package a6;

import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.OnDemandRecommendView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ OnDemandRecommendView e;

    public /* synthetic */ h(OnDemandRecommendView onDemandRecommendView, int i2) {
        this.d = i2;
        this.e = onDemandRecommendView;
    }

    public final void run() {
        int i2 = this.d;
        OnDemandRecommendView onDemandRecommendView = this.e;
        switch (i2) {
            case 0:
                onDemandRecommendView.lambda$startRecommendSpringAnimation$3();
                return;
            default:
                onDemandRecommendView.lambda$hide$0();
                return;
        }
    }
}
