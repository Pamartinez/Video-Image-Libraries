package e5;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPinchAnimationManagerV2;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements PropertyAnimator.PropertyAnimationListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ PicturesPinchAnimationManagerV2 e;

    public /* synthetic */ f(PicturesPinchAnimationManagerV2 picturesPinchAnimationManagerV2, int i2) {
        this.d = i2;
        this.e = picturesPinchAnimationManagerV2;
    }

    public final void onAnimationEnd(View view) {
        int i2 = this.d;
        PicturesPinchAnimationManagerV2 picturesPinchAnimationManagerV2 = this.e;
        switch (i2) {
            case 0:
                picturesPinchAnimationManagerV2.resetTranslate(view);
                return;
            case 1:
                picturesPinchAnimationManagerV2.resetScale(view);
                return;
            default:
                picturesPinchAnimationManagerV2.resetAlpha(view);
                return;
        }
    }
}
