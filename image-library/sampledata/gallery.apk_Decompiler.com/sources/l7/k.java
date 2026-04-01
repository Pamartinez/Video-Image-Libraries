package L7;

import com.samsung.android.gallery.app.ui.viewer2.details.DetailsSlideHandler;
import com.samsung.android.gallery.widget.photoview.PhotoView;
import com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.AnimationManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ float e;
    public final /* synthetic */ Object f;

    public /* synthetic */ k(Object obj, float f5, int i2) {
        this.d = i2;
        this.f = obj;
        this.e = f5;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((DetailsSlideHandler) this.f).lambda$doTransform$20(this.e);
                return;
            case 1:
                ((PhotoView) this.f).lambda$setAlpha$1(this.e);
                return;
            default:
                AnimationManager.createSizeAnimation$lambda$9((AnimationManager) this.f, this.e);
                return;
        }
    }
}
