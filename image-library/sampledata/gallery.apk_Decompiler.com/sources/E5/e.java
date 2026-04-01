package e5;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPinchAnimationManager;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements PropertyAnimator.PropertyAnimationListener {
    public final /* synthetic */ boolean d;

    public /* synthetic */ e(boolean z) {
        this.d = z;
    }

    public final void onAnimationEnd(View view) {
        PicturesPinchAnimationManager.lambda$createImageViewAnim$10(this.d, view);
    }
}
