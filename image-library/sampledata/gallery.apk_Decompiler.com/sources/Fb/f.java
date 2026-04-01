package Fb;

import android.animation.ValueAnimator;
import android.view.View;
import c2.p;
import com.google.android.material.chip.SeslChipGroup;
import com.samsung.android.gallery.widget.listview.GalleryRecyclerView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int d;

    public /* synthetic */ f(int i2) {
        this.d = i2;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.d) {
            case 0:
                GalleryRecyclerView.lambda$initializeFastScroll$3(valueAnimator);
                return;
            case 1:
                int i2 = SeslChipGroup.r;
                View view = (View) ((p) valueAnimator).d.get();
                if (view != null) {
                    view.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                    return;
                }
                return;
            default:
                int i7 = SeslChipGroup.r;
                View view2 = (View) ((p) valueAnimator).d.get();
                if (view2 != null) {
                    view2.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                    return;
                }
                return;
        }
    }
}
