package ic;

import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.view.View;
import com.samsung.android.gallery.widget.videoview.mediaplayer.ScaleDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class q implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ ScaleDelegate d;
    public final /* synthetic */ float e;
    public final /* synthetic */ float f;
    public final /* synthetic */ View g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ float f3275h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ PointF f3276i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ float f3277j;

    public /* synthetic */ q(ScaleDelegate scaleDelegate, float f5, float f8, View view, float f10, PointF pointF, float f11) {
        this.d = scaleDelegate;
        this.e = f5;
        this.f = f8;
        this.g = view;
        this.f3275h = f10;
        this.f3276i = pointF;
        this.f3277j = f11;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.d.lambda$zoomTo$0(this.e, this.f, this.g, this.f3275h, this.f3276i, this.f3277j, valueAnimator);
    }
}
