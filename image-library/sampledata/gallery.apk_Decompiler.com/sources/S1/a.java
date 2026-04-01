package S1;

import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import com.google.android.material.appbar.AppBarLayout;
import x2.C0340g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ AppBarLayout d;
    public final /* synthetic */ ColorStateList e;
    public final /* synthetic */ ColorStateList f;
    public final /* synthetic */ C0340g g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Integer f776h;

    public /* synthetic */ a(AppBarLayout appBarLayout, ColorStateList colorStateList, ColorStateList colorStateList2, C0340g gVar, Integer num) {
        this.d = appBarLayout;
        this.e = colorStateList;
        this.f = colorStateList2;
        this.g = gVar;
        this.f776h = num;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        AppBarLayout.b(this.d, this.e, this.f, this.g, this.f776h, valueAnimator);
    }
}
