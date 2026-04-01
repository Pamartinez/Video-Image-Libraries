package p4;

import android.view.View;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.StoriesDrawerSlideAnimationManager;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements PropertyAnimator.PropertyAnimationListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ StoriesDrawerSlideAnimationManager e;

    public /* synthetic */ j(StoriesDrawerSlideAnimationManager storiesDrawerSlideAnimationManager, int i2) {
        this.d = i2;
        this.e = storiesDrawerSlideAnimationManager;
    }

    public final void onAnimationEnd(View view) {
        int i2 = this.d;
        StoriesDrawerSlideAnimationManager storiesDrawerSlideAnimationManager = this.e;
        switch (i2) {
            case 0:
                storiesDrawerSlideAnimationManager.resetTranslate(view);
                return;
            case 1:
                storiesDrawerSlideAnimationManager.resetAlpha(view);
                return;
            case 2:
                storiesDrawerSlideAnimationManager.resetAlpha(view);
                return;
            default:
                storiesDrawerSlideAnimationManager.resetTranslate(view);
                return;
        }
    }
}
