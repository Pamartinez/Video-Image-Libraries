package e5;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPinchAnimationManager;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements PropertyAnimator.PropertyAnimationListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ PicturesPinchAnimationManager e;

    public /* synthetic */ c(PicturesPinchAnimationManager picturesPinchAnimationManager, int i2) {
        this.d = i2;
        this.e = picturesPinchAnimationManager;
    }

    public final void onAnimationEnd(View view) {
        int i2 = this.d;
        PicturesPinchAnimationManager picturesPinchAnimationManager = this.e;
        switch (i2) {
            case 0:
                picturesPinchAnimationManager.resetTranslate(view);
                return;
            case 1:
                picturesPinchAnimationManager.resetTranslate(view);
                return;
            case 2:
                picturesPinchAnimationManager.resetTranslate(view);
                return;
            default:
                picturesPinchAnimationManager.resetTranslate(view);
                return;
        }
    }
}
