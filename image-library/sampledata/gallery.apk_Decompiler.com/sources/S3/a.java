package s3;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.graphics.drawable.RotateDrawable;
import android.view.View;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.Handle;
import kotlin.jvm.internal.u;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ Rect d;
    public final /* synthetic */ Rect e;
    public final /* synthetic */ int f;
    public final /* synthetic */ int g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ RotateDrawable f1915h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ u f1916i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ Handle f1917j;
    public final /* synthetic */ View k;

    public /* synthetic */ a(Rect rect, Rect rect2, int i2, int i7, RotateDrawable rotateDrawable, u uVar, Handle handle, View view) {
        this.d = rect;
        this.e = rect2;
        this.f = i2;
        this.g = i7;
        this.f1915h = rotateDrawable;
        this.f1916i = uVar;
        this.f1917j = handle;
        this.k = view;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        Handle.showHandleAnimation$lambda$6$lambda$5(this.d, this.e, this.f, this.g, this.f1915h, this.f1916i, this.f1917j, this.k, valueAnimator);
    }
}
