package Kb;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewPropertyAnimatorUpdateListener;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.snackbar.SnackbarContentLayout;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.ProcessingViewManager;
import com.samsung.android.gallery.widget.listview.scroller.FastScroller;
import x2.C0340g;
import z2.q;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ b(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.d) {
            case 0:
                ((FastScroller) this.e).lambda$createScrollPopupViewAlphaScaleAnimator$1((ViewGroup.LayoutParams) this.f, valueAnimator);
                return;
            case 1:
                AppBarLayout.a((AppBarLayout) this.e, (C0340g) this.f, valueAnimator);
                return;
            case 2:
                ProcessingViewManager.lambda$getGlowViewAlphaInAnimator$3((View) this.e, (View) this.f, valueAnimator);
                return;
            case 3:
                ((ViewPropertyAnimatorUpdateListener) this.e).onAnimationUpdate((View) this.f);
                return;
            default:
                SnackbarContentLayout snackbarContentLayout = (SnackbarContentLayout) this.f;
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                ((q) this.e).f2221i.setAlpha(floatValue);
                if (snackbarContentLayout != null) {
                    q.f(snackbarContentLayout, (int) (floatValue * 255.0f));
                    snackbarContentLayout.invalidate();
                    return;
                }
                return;
        }
    }
}
