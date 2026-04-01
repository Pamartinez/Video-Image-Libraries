package b2;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback;

/* renamed from: b2.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0080a extends Animatable2Compat$AnimationCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C0082c f1041a;

    public C0080a(C0082c cVar) {
        this.f1041a = cVar;
    }

    public final void onAnimationEnd(Drawable drawable) {
        super.onAnimationEnd(drawable);
        ColorStateList colorStateList = this.f1041a.n;
        if (colorStateList != null) {
            DrawableCompat.setTintList(drawable, colorStateList);
        }
    }

    public final void onAnimationStart(Drawable drawable) {
        super.onAnimationStart(drawable);
        C0082c cVar = this.f1041a;
        ColorStateList colorStateList = cVar.n;
        if (colorStateList != null) {
            DrawableCompat.setTint(drawable, colorStateList.getColorForState(cVar.r, colorStateList.getDefaultColor()));
        }
    }
}
