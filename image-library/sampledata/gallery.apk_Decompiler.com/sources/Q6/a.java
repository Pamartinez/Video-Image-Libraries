package Q6;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.suggestions.revitalized.RevitalizedPicturesPinchAnimationManager;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements PropertyAnimator.PropertyAnimationListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ RevitalizedPicturesPinchAnimationManager e;

    public /* synthetic */ a(RevitalizedPicturesPinchAnimationManager revitalizedPicturesPinchAnimationManager, int i2) {
        this.d = i2;
        this.e = revitalizedPicturesPinchAnimationManager;
    }

    public final void onAnimationEnd(View view) {
        int i2 = this.d;
        RevitalizedPicturesPinchAnimationManager revitalizedPicturesPinchAnimationManager = this.e;
        switch (i2) {
            case 0:
                revitalizedPicturesPinchAnimationManager.resetTranslate(view);
                return;
            default:
                revitalizedPicturesPinchAnimationManager.lambda$prepareThumbnailAnimation$2(view);
                return;
        }
    }
}
