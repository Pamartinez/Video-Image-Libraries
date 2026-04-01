package S7;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.viewer2.remaster.RemasterLayoutHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ RemasterLayoutHandler d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ ViewGroup.MarginLayoutParams f;
    public final /* synthetic */ View g;

    public /* synthetic */ f(RemasterLayoutHandler remasterLayoutHandler, boolean z, ViewGroup.MarginLayoutParams marginLayoutParams, View view) {
        this.d = remasterLayoutHandler;
        this.e = z;
        this.f = marginLayoutParams;
        this.g = view;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.d.lambda$startFocusRoiAnim$8(this.e, this.f, this.g, valueAnimator);
    }
}
