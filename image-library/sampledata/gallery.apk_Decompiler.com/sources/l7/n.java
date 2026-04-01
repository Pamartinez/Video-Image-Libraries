package L7;

import com.samsung.android.gallery.app.ui.viewer2.details.DetailsSlideHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements Runnable {
    public final /* synthetic */ DetailsSlideHandler d;
    public final /* synthetic */ float e;
    public final /* synthetic */ boolean f;

    public /* synthetic */ n(DetailsSlideHandler detailsSlideHandler, float f5, boolean z) {
        this.d = detailsSlideHandler;
        this.e = f5;
        this.f = z;
    }

    public final void run() {
        this.d.lambda$doTransformIfExpand$19(this.e, this.f);
    }
}
