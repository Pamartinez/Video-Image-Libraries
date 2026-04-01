package V7;

import android.animation.ValueAnimator;
import com.samsung.android.gallery.app.ui.viewer2.selection.SelectionViewZoomDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ SelectionViewZoomDelegate d;
    public final /* synthetic */ float e;
    public final /* synthetic */ float f;
    public final /* synthetic */ float g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ float f2460h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ float f2461i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ float f2462j;

    public /* synthetic */ c(SelectionViewZoomDelegate selectionViewZoomDelegate, float f5, float f8, float f10, float f11, float f12, float f13) {
        this.d = selectionViewZoomDelegate;
        this.e = f5;
        this.f = f8;
        this.g = f10;
        this.f2460h = f11;
        this.f2461i = f12;
        this.f2462j = f13;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.d.lambda$resetScale$0(this.e, this.f, this.g, this.f2460h, this.f2461i, this.f2462j, valueAnimator);
    }
}
