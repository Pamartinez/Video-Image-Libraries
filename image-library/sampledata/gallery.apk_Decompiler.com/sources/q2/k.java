package q2;

import Ae.a;
import Te.G;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.appcompat.oneui.common.BlurSupportable;
import androidx.appcompat.oneui.common.internal.policy.BlurInfoState;
import androidx.appcompat.oneui.common.internal.semblurinfo.SemBlurInfoStateBuilder;
import androidx.core.oneui.common.internal.semblurinfo.SemBlurInfoState;
import androidx.dynamicanimation.animation.SpringAnimation;
import com.sec.android.gallery3d.R;
import ig.g;
import java.util.List;
import kotlin.jvm.internal.j;
import n2.C0244g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class k extends View implements BlurSupportable {
    public int d = 2;
    public SemBlurInfoState e;
    public Drawable f;
    public a g = j.d;

    /* renamed from: h  reason: collision with root package name */
    public Rect f1880h = new Rect();

    /* renamed from: i  reason: collision with root package name */
    public final ObjectAnimator f1881i;

    /* renamed from: j  reason: collision with root package name */
    public final C0244g f1882j;

    public k(Context context) {
        super(context, (AttributeSet) null, 0);
        C0244g gVar = new C0244g();
        gVar.f1849c.e();
        gVar.f1848a = new g(4, this);
        this.f1882j = gVar;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, new i("AlphaAnim", 0), new float[]{getAlpha()});
        j.d(ofFloat, "ofFloat(this, mBgViewAlphaAnimProperty, alpha)");
        this.f1881i = ofFloat;
        ofFloat.setDuration(150);
    }

    public final void a(Rect rect) {
        this.f1880h = rect;
        RectF rectF = new RectF(rect);
        C0244g gVar = this.f1882j;
        gVar.getClass();
        G g3 = gVar.f1849c;
        g3.getClass();
        Log.d("RectFAnimation", "animateToFinalPosition " + rectF);
        List<a> list = (List) g3.f3755j;
        j.d(list, "startListeners");
        for (a invoke : list) {
            invoke.invoke();
        }
        ((SpringAnimation) g3.f3752c).animateToFinalPosition(rectF.left * 100.0f);
        ((SpringAnimation) g3.d).animateToFinalPosition(rectF.top * 100.0f);
        ((SpringAnimation) g3.e).animateToFinalPosition(rectF.right * 100.0f);
        ((SpringAnimation) g3.f).animateToFinalPosition(rectF.bottom * 100.0f);
    }

    public final boolean b(Context context) {
        if (Build.VERSION.SDK_INT < 35) {
            return false;
        }
        clearBlurInfo(context);
        float dimension = context.getResources().getDimension(R.dimen.sesl_projection_bg_radius);
        SemBlurInfoStateBuilder generateFloatingComponentBlurInfoStateBuilder = BlurInfoState.INSTANCE.generateFloatingComponentBlurInfoStateBuilder(context, this.d);
        Drawable drawable = this.f;
        if (drawable != null) {
            generateFloatingComponentBlurInfoStateBuilder.nonBlurBackground(drawable);
        }
        generateFloatingComponentBlurInfoStateBuilder.cornerRadius(dimension);
        SemBlurInfoState build = generateFloatingComponentBlurInfoStateBuilder.build();
        this.e = build;
        return build.applyBlurInfo(this);
    }

    public final void c(boolean z, boolean z3) {
        float f5;
        long j2;
        float alpha = getAlpha();
        if (z) {
            f5 = 1.0f;
        } else {
            f5 = 0.0f;
        }
        ObjectAnimator objectAnimator = this.f1881i;
        objectAnimator.setFloatValues(new float[]{alpha, f5});
        if (z3) {
            j2 = 150;
        } else {
            j2 = 0;
        }
        objectAnimator.setDuration(j2);
        objectAnimator.start();
    }

    public final void clearBlurInfo(Context context) {
        j.e(context, "context");
        SemBlurInfoState semBlurInfoState = this.e;
        if (semBlurInfoState != null) {
            semBlurInfoState.clearBlurInfo(this);
        }
        this.e = null;
    }

    public final C0244g getAnim() {
        return this.f1882j;
    }

    public final Rect getLastFinalRect() {
        return this.f1880h;
    }

    public void setBackground(Drawable drawable) {
        super.setBackground(drawable);
        this.f = drawable;
    }

    public void setBlurMode(int i2) {
        this.d = i2;
        Context context = getContext();
        j.d(context, "context");
        b(context);
    }

    public final void setFinalPosition(Rect rect) {
        j.e(rect, "rect");
        this.f1880h = rect;
        RectF rectF = new RectF(rect);
        C0244g gVar = this.f1882j;
        gVar.getClass();
        gVar.b.set(rectF);
    }

    public final void setLastFinalRect(Rect rect) {
        j.e(rect, "<set-?>");
        this.f1880h = rect;
    }

    public final void setOnResizeUpdate(a aVar) {
        j.e(aVar, "onResizeUpdate");
        this.g = aVar;
    }
}
