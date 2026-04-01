package Ba;

import android.animation.ValueAnimator;
import android.graphics.PointF;
import com.samsung.android.gallery.plugins.motionphoto.MotionPhotoViewCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ MotionPhotoViewCompat d;
    public final /* synthetic */ float e;
    public final /* synthetic */ float f;
    public final /* synthetic */ float g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ PointF f2779h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ float f2780i;

    public /* synthetic */ k(MotionPhotoViewCompat motionPhotoViewCompat, float f5, float f8, float f10, PointF pointF, float f11) {
        this.d = motionPhotoViewCompat;
        this.e = f5;
        this.f = f8;
        this.g = f10;
        this.f2779h = pointF;
        this.f2780i = f11;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.d.lambda$zoomTo$1(this.e, this.f, this.g, this.f2779h, this.f2780i, valueAnimator);
    }
}
