package c2;

import R1.b;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.core.graphics.drawable.DrawableCompat;
import com.google.android.material.chip.Chip;
import com.samsung.android.sdk.cover.ScoverState;
import com.sec.android.gallery3d.R;
import h2.l;
import h2.m;
import h2.u;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import u2.C0290e;
import v2.C0299a;
import x2.C0334a;
import x2.C0339f;
import x2.C0340g;
import x2.C0343j;
import x2.C0344k;
import x2.C0346m;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e extends C0340g implements Drawable.Callback, l {

    /* renamed from: H0  reason: collision with root package name */
    public static final int[] f1055H0 = {16842910};

    /* renamed from: I0  reason: collision with root package name */
    public static final ShapeDrawable f1056I0 = new ShapeDrawable(new OvalShape());

    /* renamed from: A  reason: collision with root package name */
    public ColorStateList f1057A;

    /* renamed from: A0  reason: collision with root package name */
    public int[] f1058A0;
    public float B;

    /* renamed from: B0  reason: collision with root package name */
    public ColorStateList f1059B0;

    /* renamed from: C  reason: collision with root package name */
    public float f1060C = -1.0f;

    /* renamed from: C0  reason: collision with root package name */
    public WeakReference f1061C0 = new WeakReference((Object) null);
    public ColorStateList D;

    /* renamed from: D0  reason: collision with root package name */
    public TextUtils.TruncateAt f1062D0;
    public float E;

    /* renamed from: E0  reason: collision with root package name */
    public boolean f1063E0;

    /* renamed from: F  reason: collision with root package name */
    public ColorStateList f1064F;

    /* renamed from: F0  reason: collision with root package name */
    public int f1065F0;

    /* renamed from: G  reason: collision with root package name */
    public CharSequence f1066G;

    /* renamed from: G0  reason: collision with root package name */
    public boolean f1067G0;

    /* renamed from: H  reason: collision with root package name */
    public boolean f1068H;

    /* renamed from: I  reason: collision with root package name */
    public Drawable f1069I;

    /* renamed from: J  reason: collision with root package name */
    public ColorStateList f1070J;

    /* renamed from: K  reason: collision with root package name */
    public float f1071K;
    public boolean L;

    /* renamed from: M  reason: collision with root package name */
    public boolean f1072M;

    /* renamed from: N  reason: collision with root package name */
    public Drawable f1073N;

    /* renamed from: O  reason: collision with root package name */
    public RippleDrawable f1074O;

    /* renamed from: P  reason: collision with root package name */
    public ColorStateList f1075P;
    public float Q;
    public CharSequence R;
    public boolean S;
    public boolean T;
    public Drawable U;
    public ColorStateList V;

    /* renamed from: W  reason: collision with root package name */
    public b f1076W;

    /* renamed from: X  reason: collision with root package name */
    public b f1077X;
    public float Y;
    public float Z;
    public float a0;
    public float b0;

    /* renamed from: c0  reason: collision with root package name */
    public float f1078c0;
    public float d0;

    /* renamed from: e0  reason: collision with root package name */
    public float f1079e0;

    /* renamed from: f0  reason: collision with root package name */
    public float f1080f0;

    /* renamed from: g0  reason: collision with root package name */
    public final Context f1081g0;
    public final Paint h0 = new Paint(1);

    /* renamed from: i0  reason: collision with root package name */
    public final Paint.FontMetrics f1082i0 = new Paint.FontMetrics();

    /* renamed from: j0  reason: collision with root package name */
    public final RectF f1083j0 = new RectF();
    public final PointF k0 = new PointF();
    public final Path l0 = new Path();

    /* renamed from: m0  reason: collision with root package name */
    public final m f1084m0;

    /* renamed from: n0  reason: collision with root package name */
    public int f1085n0;
    public int o0;

    /* renamed from: p0  reason: collision with root package name */
    public int f1086p0;
    public int q0;
    public int r0;
    public int s0;

    /* renamed from: t0  reason: collision with root package name */
    public boolean f1087t0;

    /* renamed from: u0  reason: collision with root package name */
    public int f1088u0;

    /* renamed from: v0  reason: collision with root package name */
    public int f1089v0 = ScoverState.TYPE_NFC_SMART_COVER;

    /* renamed from: w0  reason: collision with root package name */
    public ColorFilter f1090w0;

    /* renamed from: x0  reason: collision with root package name */
    public PorterDuffColorFilter f1091x0;
    public ColorStateList y0;
    public ColorStateList z;

    /* renamed from: z0  reason: collision with root package name */
    public PorterDuff.Mode f1092z0 = PorterDuff.Mode.SRC_IN;

    public e(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.chipStyle, com.samsung.android.photoremaster.engine.R.style.Widget_MaterialComponents_Chip_Action);
        i(context);
        this.f1081g0 = context;
        m mVar = new m(this);
        this.f1084m0 = mVar;
        this.f1066G = "";
        mVar.f1774a.density = context.getResources().getDisplayMetrics().density;
        int[] iArr = f1055H0;
        setState(iArr);
        if (!Arrays.equals(this.f1058A0, iArr)) {
            this.f1058A0 = iArr;
            if (U()) {
                x(getState(), iArr);
            }
        }
        this.f1063E0 = true;
        f1056I0.setTint(-1);
    }

    public static void V(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback((Drawable.Callback) null);
        }
    }

    public static boolean u(ColorStateList colorStateList) {
        if (colorStateList == null || !colorStateList.isStateful()) {
            return false;
        }
        return true;
    }

    public static boolean v(Drawable drawable) {
        if (drawable == null || !drawable.isStateful()) {
            return false;
        }
        return true;
    }

    public final void A(ColorStateList colorStateList) {
        Drawable drawable;
        if (this.V != colorStateList) {
            this.V = colorStateList;
            if (this.T && (drawable = this.U) != null && this.S) {
                DrawableCompat.setTintList(drawable, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public final void B(boolean z3) {
        if (this.T != z3) {
            boolean S10 = S();
            this.T = z3;
            boolean S11 = S();
            if (S10 != S11) {
                if (S11) {
                    p(this.U);
                } else {
                    V(this.U);
                }
                invalidateSelf();
                w();
            }
        }
    }

    public final void C(float f) {
        if (this.f1060C != f) {
            this.f1060C = f;
            C0343j e = this.d.f2103a.e();
            e.e = new C0334a(f);
            e.f = new C0334a(f);
            e.g = new C0334a(f);
            e.f2119h = new C0334a(f);
            setShapeAppearanceModel(e.a());
        }
    }

    public final void D(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.f1069I;
        Drawable drawable4 = null;
        if (drawable3 != null) {
            drawable2 = DrawableCompat.unwrap(drawable3);
        } else {
            drawable2 = null;
        }
        if (drawable2 != drawable) {
            float r = r();
            if (drawable != null) {
                drawable4 = DrawableCompat.wrap(drawable).mutate();
            }
            this.f1069I = drawable4;
            float r5 = r();
            V(drawable2);
            if (T()) {
                p(this.f1069I);
            }
            invalidateSelf();
            if (r != r5) {
                w();
            }
        }
    }

    public final void E(float f) {
        if (this.f1071K != f) {
            float r = r();
            this.f1071K = f;
            float r5 = r();
            invalidateSelf();
            if (r != r5) {
                w();
            }
        }
    }

    public final void F(ColorStateList colorStateList) {
        this.L = true;
        if (this.f1070J != colorStateList) {
            this.f1070J = colorStateList;
            if (T()) {
                DrawableCompat.setTintList(this.f1069I, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public final void G(boolean z3) {
        if (this.f1068H != z3) {
            boolean T9 = T();
            this.f1068H = z3;
            boolean T10 = T();
            if (T9 != T10) {
                if (T10) {
                    p(this.f1069I);
                } else {
                    V(this.f1069I);
                }
                invalidateSelf();
                w();
            }
        }
    }

    public final void H(ColorStateList colorStateList) {
        if (this.D != colorStateList) {
            this.D = colorStateList;
            if (this.f1067G0) {
                C0339f fVar = this.d;
                if (fVar.d != colorStateList) {
                    fVar.d = colorStateList;
                    onStateChange(getState());
                }
            }
            onStateChange(getState());
        }
    }

    public final void I(float f) {
        if (this.E != f) {
            this.E = f;
            this.h0.setStrokeWidth(f);
            if (this.f1067G0) {
                this.d.f2107j = f;
                invalidateSelf();
            }
            invalidateSelf();
        }
    }

    public final void J(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.f1073N;
        Drawable drawable4 = null;
        if (drawable3 != null) {
            drawable2 = DrawableCompat.unwrap(drawable3);
        } else {
            drawable2 = null;
        }
        if (drawable2 != drawable) {
            float s = s();
            if (drawable != null) {
                drawable4 = DrawableCompat.wrap(drawable).mutate();
            }
            this.f1073N = drawable4;
            this.f1074O = new RippleDrawable(C0299a.b(this.f1064F), this.f1073N, f1056I0);
            float s5 = s();
            V(drawable2);
            if (U()) {
                p(this.f1073N);
            }
            invalidateSelf();
            if (s != s5) {
                w();
            }
        }
    }

    public final void K(float f) {
        if (this.f1079e0 != f) {
            this.f1079e0 = f;
            invalidateSelf();
            if (U()) {
                w();
            }
        }
    }

    public final void L(float f) {
        if (this.Q != f) {
            this.Q = f;
            invalidateSelf();
            if (U()) {
                w();
            }
        }
    }

    public final void M(float f) {
        if (this.d0 != f) {
            this.d0 = f;
            invalidateSelf();
            if (U()) {
                w();
            }
        }
    }

    public final void N(ColorStateList colorStateList) {
        if (this.f1075P != colorStateList) {
            this.f1075P = colorStateList;
            if (U()) {
                DrawableCompat.setTintList(this.f1073N, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public final void O(boolean z3) {
        if (this.f1072M != z3) {
            boolean U8 = U();
            this.f1072M = z3;
            boolean U10 = U();
            if (U8 != U10) {
                if (U10) {
                    p(this.f1073N);
                } else {
                    V(this.f1073N);
                }
                invalidateSelf();
                w();
            }
        }
    }

    public final void P(float f) {
        if (this.a0 != f) {
            float r = r();
            this.a0 = f;
            float r5 = r();
            invalidateSelf();
            if (r != r5) {
                w();
            }
        }
    }

    public final void Q(float f) {
        if (this.Z != f) {
            float r = r();
            this.Z = f;
            float r5 = r();
            invalidateSelf();
            if (r != r5) {
                w();
            }
        }
    }

    public final void R(ColorStateList colorStateList) {
        if (this.f1064F != colorStateList) {
            this.f1064F = colorStateList;
            this.f1059B0 = null;
            onStateChange(getState());
        }
    }

    public final boolean S() {
        if (!this.T || this.U == null || !this.f1087t0) {
            return false;
        }
        return true;
    }

    public final boolean T() {
        if (!this.f1068H || this.f1069I == null) {
            return false;
        }
        return true;
    }

    public final boolean U() {
        if (!this.f1072M || this.f1073N == null) {
            return false;
        }
        return true;
    }

    public final void a() {
        w();
        invalidateSelf();
    }

    public final void draw(Canvas canvas) {
        int i2;
        int i7;
        Canvas canvas2;
        float f;
        boolean z3;
        int i8;
        Rect bounds = getBounds();
        if (!bounds.isEmpty() && (i2 = this.f1089v0) != 0) {
            if (i2 < 255) {
                canvas2 = canvas;
                i7 = canvas2.saveLayerAlpha((float) bounds.left, (float) bounds.top, (float) bounds.right, (float) bounds.bottom, i2);
            } else {
                canvas2 = canvas;
                i7 = 0;
            }
            boolean z7 = this.f1067G0;
            Paint paint = this.h0;
            RectF rectF = this.f1083j0;
            if (!z7) {
                paint.setColor(this.f1085n0);
                paint.setStyle(Paint.Style.FILL);
                rectF.set(bounds);
                canvas2.drawRoundRect(rectF, t(), t(), paint);
            }
            if (!this.f1067G0) {
                paint.setColor(this.o0);
                paint.setStyle(Paint.Style.FILL);
                ColorFilter colorFilter = this.f1090w0;
                if (colorFilter == null) {
                    colorFilter = this.f1091x0;
                }
                paint.setColorFilter(colorFilter);
                rectF.set(bounds);
                canvas2.drawRoundRect(rectF, t(), t(), paint);
            }
            if (this.f1067G0) {
                super.draw(canvas);
            }
            if (this.E > 0.0f && !this.f1067G0) {
                paint.setColor(this.q0);
                paint.setStyle(Paint.Style.STROKE);
                if (!this.f1067G0) {
                    ColorFilter colorFilter2 = this.f1090w0;
                    if (colorFilter2 == null) {
                        colorFilter2 = this.f1091x0;
                    }
                    paint.setColorFilter(colorFilter2);
                }
                float f5 = this.E / 2.0f;
                rectF.set(((float) bounds.left) + f5, ((float) bounds.top) + f5, ((float) bounds.right) - f5, ((float) bounds.bottom) - f5);
                float f8 = this.f1060C - (this.E / 2.0f);
                canvas2.drawRoundRect(rectF, f8, f8, paint);
            }
            paint.setColor(this.r0);
            paint.setStyle(Paint.Style.FILL);
            rectF.set(bounds);
            if (!this.f1067G0) {
                canvas2.drawRoundRect(rectF, t(), t(), paint);
            } else {
                RectF rectF2 = new RectF(bounds);
                C0339f fVar = this.d;
                C0344k kVar = fVar.f2103a;
                float f10 = fVar.f2106i;
                G0.e eVar = this.s;
                C0346m mVar = this.t;
                Path path = this.l0;
                mVar.a(kVar, f10, rectF2, eVar, path);
                d(canvas2, paint, path, this.d.f2103a, f());
            }
            if (T()) {
                q(bounds, rectF);
                float f11 = rectF.left;
                float f12 = rectF.top;
                canvas2.translate(f11, f12);
                this.f1069I.setBounds(0, 0, (int) rectF.width(), (int) rectF.height());
                this.f1069I.draw(canvas2);
                canvas2.translate(-f11, -f12);
            }
            if (S()) {
                q(bounds, rectF);
                float f13 = rectF.left;
                float f14 = rectF.top;
                canvas2.translate(f13, f14);
                this.U.setBounds(0, 0, (int) rectF.width(), (int) rectF.height());
                this.U.draw(canvas2);
                canvas2.translate(-f13, -f14);
            }
            if (this.f1063E0 && this.f1066G != null) {
                PointF pointF = this.k0;
                pointF.set(0.0f, 0.0f);
                Paint.Align align = Paint.Align.LEFT;
                CharSequence charSequence = this.f1066G;
                m mVar2 = this.f1084m0;
                if (charSequence != null) {
                    float r = this.Y + r() + this.b0;
                    if (DrawableCompat.getLayoutDirection(this) == 0) {
                        pointF.x = ((float) bounds.left) + r;
                    } else {
                        pointF.x = ((float) bounds.right) - r;
                        align = Paint.Align.RIGHT;
                    }
                    TextPaint textPaint = mVar2.f1774a;
                    Paint.FontMetrics fontMetrics = this.f1082i0;
                    textPaint.getFontMetrics(fontMetrics);
                    pointF.y = ((float) bounds.centerY()) - ((fontMetrics.descent + fontMetrics.ascent) / 2.0f);
                }
                rectF.setEmpty();
                if (this.f1066G != null) {
                    float r5 = r();
                    float s = s();
                    float f15 = this.Y + r5 + this.b0;
                    float f16 = this.f1080f0 + s + this.f1078c0;
                    if (DrawableCompat.getLayoutDirection(this) == 0) {
                        rectF.left = ((float) bounds.left) + f15;
                        rectF.right = ((float) bounds.right) - f16;
                    } else {
                        rectF.left = ((float) bounds.left) + f16;
                        rectF.right = ((float) bounds.right) - f15;
                    }
                    rectF.top = (float) bounds.top;
                    rectF.bottom = (float) bounds.bottom;
                }
                C0290e eVar2 = mVar2.g;
                TextPaint textPaint2 = mVar2.f1774a;
                if (eVar2 != null) {
                    textPaint2.drawableState = getState();
                    mVar2.g.d(this.f1081g0, textPaint2, mVar2.b);
                }
                textPaint2.setTextAlign(align);
                String charSequence2 = this.f1066G.toString();
                if (!mVar2.e) {
                    f = mVar2.f1775c;
                } else {
                    mVar2.a(charSequence2);
                    f = mVar2.f1775c;
                }
                if (Math.round(f) > Math.round(rectF.width())) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z3) {
                    int save = canvas2.save();
                    canvas2.clipRect(rectF);
                    i8 = save;
                } else {
                    i8 = 0;
                }
                CharSequence charSequence3 = this.f1066G;
                if (z3 && this.f1062D0 != null) {
                    charSequence3 = TextUtils.ellipsize(charSequence3, textPaint2, rectF.width(), this.f1062D0);
                }
                int length = charSequence3.length();
                float f17 = pointF.x;
                float f18 = pointF.y;
                Canvas canvas3 = canvas;
                canvas3.drawText(charSequence3, 0, length, f17, f18, textPaint2);
                canvas2 = canvas3;
                if (z3) {
                    canvas2.restoreToCount(i8);
                }
            }
            if (U()) {
                rectF.setEmpty();
                if (U()) {
                    float f19 = this.f1080f0 + this.f1079e0;
                    if (DrawableCompat.getLayoutDirection(this) == 0) {
                        float f20 = ((float) bounds.right) - f19;
                        rectF.right = f20;
                        rectF.left = f20 - this.Q;
                    } else {
                        float f21 = ((float) bounds.left) + f19;
                        rectF.left = f21;
                        rectF.right = f21 + this.Q;
                    }
                    float exactCenterY = bounds.exactCenterY();
                    float f22 = this.Q;
                    float f23 = exactCenterY - (f22 / 2.0f);
                    rectF.top = f23;
                    rectF.bottom = f23 + f22;
                }
                float f24 = rectF.left;
                float f25 = rectF.top;
                canvas2.translate(f24, f25);
                this.f1073N.setBounds(0, 0, (int) rectF.width(), (int) rectF.height());
                this.f1074O.setBounds(this.f1073N.getBounds());
                this.f1074O.jumpToCurrentState();
                this.f1074O.draw(canvas2);
                canvas2.translate(-f24, -f25);
            }
            if (this.f1089v0 < 255) {
                canvas2.restoreToCount(i7);
            }
        }
    }

    public final int getAlpha() {
        return this.f1089v0;
    }

    public final ColorFilter getColorFilter() {
        return this.f1090w0;
    }

    public final int getIntrinsicHeight() {
        return (int) this.B;
    }

    public final int getIntrinsicWidth() {
        float f;
        float r = r() + this.Y + this.b0;
        String charSequence = this.f1066G.toString();
        m mVar = this.f1084m0;
        if (!mVar.e) {
            f = mVar.f1775c;
        } else {
            mVar.a(charSequence);
            f = mVar.f1775c;
        }
        return Math.min(Math.round(s() + f + r + this.f1078c0 + this.f1080f0), this.f1065F0);
    }

    public final int getOpacity() {
        return -3;
    }

    public final void getOutline(Outline outline) {
        Outline outline2;
        if (this.f1067G0) {
            super.getOutline(outline);
            return;
        }
        Rect bounds = getBounds();
        if (!bounds.isEmpty()) {
            outline.setRoundRect(bounds, this.f1060C);
            outline2 = outline;
        } else {
            outline2 = outline;
            outline2.setRoundRect(0, 0, getIntrinsicWidth(), (int) this.B, this.f1060C);
        }
        outline2.setAlpha(((float) this.f1089v0) / 255.0f);
    }

    public final void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    public final boolean isStateful() {
        ColorStateList colorStateList;
        if (u(this.z) || u(this.f1057A) || u(this.D)) {
            return true;
        }
        C0290e eVar = this.f1084m0.g;
        if (eVar != null && (colorStateList = eVar.f1948j) != null && colorStateList.isStateful()) {
            return true;
        }
        if ((!this.T || this.U == null || !this.S) && !v(this.f1069I) && !v(this.U) && !u(this.y0)) {
            return false;
        }
        return true;
    }

    public final boolean onLayoutDirectionChanged(int i2) {
        boolean onLayoutDirectionChanged = super.onLayoutDirectionChanged(i2);
        if (T()) {
            onLayoutDirectionChanged |= DrawableCompat.setLayoutDirection(this.f1069I, i2);
        }
        if (S()) {
            onLayoutDirectionChanged |= DrawableCompat.setLayoutDirection(this.U, i2);
        }
        if (U()) {
            onLayoutDirectionChanged |= DrawableCompat.setLayoutDirection(this.f1073N, i2);
        }
        if (!onLayoutDirectionChanged) {
            return true;
        }
        invalidateSelf();
        return true;
    }

    public final boolean onLevelChange(int i2) {
        boolean onLevelChange = super.onLevelChange(i2);
        if (T()) {
            onLevelChange |= this.f1069I.setLevel(i2);
        }
        if (S()) {
            onLevelChange |= this.U.setLevel(i2);
        }
        if (U()) {
            onLevelChange |= this.f1073N.setLevel(i2);
        }
        if (onLevelChange) {
            invalidateSelf();
        }
        return onLevelChange;
    }

    public final boolean onStateChange(int[] iArr) {
        if (this.f1067G0) {
            super.onStateChange(iArr);
        }
        return x(iArr, this.f1058A0);
    }

    public final void p(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(this);
            DrawableCompat.setLayoutDirection(drawable, DrawableCompat.getLayoutDirection(this));
            drawable.setLevel(getLevel());
            drawable.setVisible(isVisible(), false);
            if (drawable == this.f1073N) {
                if (drawable.isStateful()) {
                    drawable.setState(this.f1058A0);
                }
                DrawableCompat.setTintList(drawable, this.f1075P);
                return;
            }
            Drawable drawable2 = this.f1069I;
            if (drawable == drawable2 && this.L) {
                DrawableCompat.setTintList(drawable2, this.f1070J);
            }
            if (drawable.isStateful()) {
                drawable.setState(getState());
            }
        }
    }

    public final void q(Rect rect, RectF rectF) {
        Drawable drawable;
        Drawable drawable2;
        rectF.setEmpty();
        if (T() || S()) {
            float f = this.Y + this.Z;
            if (this.f1087t0) {
                drawable = this.U;
            } else {
                drawable = this.f1069I;
            }
            float f5 = this.f1071K;
            if (f5 <= 0.0f && drawable != null) {
                f5 = (float) drawable.getIntrinsicWidth();
            }
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                float f8 = ((float) rect.left) + f;
                rectF.left = f8;
                rectF.right = f8 + f5;
            } else {
                float f10 = ((float) rect.right) - f;
                rectF.right = f10;
                rectF.left = f10 - f5;
            }
            if (this.f1087t0) {
                drawable2 = this.U;
            } else {
                drawable2 = this.f1069I;
            }
            float f11 = this.f1071K;
            if (f11 <= 0.0f && drawable2 != null) {
                f11 = (float) Math.ceil((double) u.b(this.f1081g0, 24));
                if (((float) drawable2.getIntrinsicHeight()) <= f11) {
                    f11 = (float) drawable2.getIntrinsicHeight();
                }
            }
            float exactCenterY = rect.exactCenterY() - (f11 / 2.0f);
            rectF.top = exactCenterY;
            rectF.bottom = exactCenterY + f11;
        }
    }

    public final float r() {
        Drawable drawable;
        if (!T() && !S()) {
            return 0.0f;
        }
        float f = this.Z;
        if (this.f1087t0) {
            drawable = this.U;
        } else {
            drawable = this.f1069I;
        }
        float f5 = this.f1071K;
        if (f5 <= 0.0f && drawable != null) {
            f5 = (float) drawable.getIntrinsicWidth();
        }
        return f5 + f + this.a0;
    }

    public final float s() {
        if (U()) {
            return this.d0 + this.Q + this.f1079e0;
        }
        return 0.0f;
    }

    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j2) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j2);
        }
    }

    public final void setAlpha(int i2) {
        if (this.f1089v0 != i2) {
            this.f1089v0 = i2;
            invalidateSelf();
        }
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        if (this.f1090w0 != colorFilter) {
            this.f1090w0 = colorFilter;
            invalidateSelf();
        }
    }

    public final void setTintList(ColorStateList colorStateList) {
        if (this.y0 != colorStateList) {
            this.y0 = colorStateList;
            onStateChange(getState());
        }
    }

    public final void setTintMode(PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilter;
        if (this.f1092z0 != mode) {
            this.f1092z0 = mode;
            ColorStateList colorStateList = this.y0;
            if (colorStateList == null || mode == null) {
                porterDuffColorFilter = null;
            } else {
                porterDuffColorFilter = new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
            }
            this.f1091x0 = porterDuffColorFilter;
            invalidateSelf();
        }
    }

    public final boolean setVisible(boolean z3, boolean z7) {
        boolean visible = super.setVisible(z3, z7);
        if (T()) {
            visible |= this.f1069I.setVisible(z3, z7);
        }
        if (S()) {
            visible |= this.U.setVisible(z3, z7);
        }
        if (U()) {
            visible |= this.f1073N.setVisible(z3, z7);
        }
        if (visible) {
            invalidateSelf();
        }
        return visible;
    }

    public final float t() {
        if (this.f1067G0) {
            return g();
        }
        return this.f1060C;
    }

    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    public final void w() {
        d dVar = (d) this.f1061C0.get();
        if (dVar != null) {
            Chip chip = (Chip) dVar;
            chip.c(chip.f1442p);
            chip.requestLayout();
            chip.invalidateOutline();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:101:0x0132  */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x0141  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0150  */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x016d  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x0176  */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x017b  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00a8  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00bb  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00c0  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x00fb  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0100  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x010c  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0129  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean x(int[] r12, int[] r13) {
        /*
            r11 = this;
            boolean r0 = super.onStateChange(r12)
            android.content.res.ColorStateList r1 = r11.z
            r2 = 0
            if (r1 == 0) goto L_0x0010
            int r3 = r11.f1085n0
            int r1 = r1.getColorForState(r12, r3)
            goto L_0x0011
        L_0x0010:
            r1 = r2
        L_0x0011:
            int r1 = r11.c(r1)
            int r3 = r11.f1085n0
            r4 = 1
            if (r3 == r1) goto L_0x001d
            r11.f1085n0 = r1
            r0 = r4
        L_0x001d:
            android.content.res.ColorStateList r3 = r11.f1057A
            if (r3 == 0) goto L_0x0028
            int r5 = r11.o0
            int r3 = r3.getColorForState(r12, r5)
            goto L_0x0029
        L_0x0028:
            r3 = r2
        L_0x0029:
            int r3 = r11.c(r3)
            int r5 = r11.o0
            if (r5 == r3) goto L_0x0034
            r11.o0 = r3
            r0 = r4
        L_0x0034:
            int r1 = androidx.core.graphics.ColorUtils.compositeColors(r3, r1)
            int r3 = r11.f1086p0
            if (r3 == r1) goto L_0x003e
            r3 = r4
            goto L_0x003f
        L_0x003e:
            r3 = r2
        L_0x003f:
            x2.f r5 = r11.d
            android.content.res.ColorStateList r5 = r5.f2104c
            if (r5 != 0) goto L_0x0047
            r5 = r4
            goto L_0x0048
        L_0x0047:
            r5 = r2
        L_0x0048:
            r3 = r3 | r5
            if (r3 == 0) goto L_0x0055
            r11.f1086p0 = r1
            android.content.res.ColorStateList r0 = android.content.res.ColorStateList.valueOf(r1)
            r11.k(r0)
            r0 = r4
        L_0x0055:
            android.content.res.ColorStateList r1 = r11.D
            if (r1 == 0) goto L_0x0060
            int r3 = r11.q0
            int r1 = r1.getColorForState(r12, r3)
            goto L_0x0061
        L_0x0060:
            r1 = r2
        L_0x0061:
            int r3 = r11.q0
            if (r3 == r1) goto L_0x0068
            r11.q0 = r1
            r0 = r4
        L_0x0068:
            android.content.res.ColorStateList r1 = r11.f1059B0
            if (r1 == 0) goto L_0x00a3
            int r1 = r12.length
            r3 = 0
            r5 = r3
            r6 = r5
            r7 = r6
        L_0x0071:
            r8 = 1
            if (r5 >= r1) goto L_0x0093
            r9 = r12[r5]
            r10 = 16842910(0x101009e, float:2.3694E-38)
            if (r9 != r10) goto L_0x007d
            r6 = r8
            goto L_0x0090
        L_0x007d:
            r10 = 16842908(0x101009c, float:2.3693995E-38)
            if (r9 != r10) goto L_0x0084
        L_0x0082:
            r7 = r8
            goto L_0x0090
        L_0x0084:
            r10 = 16842919(0x10100a7, float:2.3694026E-38)
            if (r9 != r10) goto L_0x008a
            goto L_0x0082
        L_0x008a:
            r10 = 16843623(0x1010367, float:2.3696E-38)
            if (r9 != r10) goto L_0x0090
            goto L_0x0082
        L_0x0090:
            int r5 = r5 + 1
            goto L_0x0071
        L_0x0093:
            if (r6 == 0) goto L_0x0098
            if (r7 == 0) goto L_0x0098
            r3 = r8
        L_0x0098:
            if (r3 == 0) goto L_0x00a3
            android.content.res.ColorStateList r1 = r11.f1059B0
            int r3 = r11.r0
            int r1 = r1.getColorForState(r12, r3)
            goto L_0x00a4
        L_0x00a3:
            r1 = r2
        L_0x00a4:
            int r3 = r11.r0
            if (r3 == r1) goto L_0x00aa
            r11.r0 = r1
        L_0x00aa:
            h2.m r1 = r11.f1084m0
            u2.e r1 = r1.g
            if (r1 == 0) goto L_0x00bb
            android.content.res.ColorStateList r1 = r1.f1948j
            if (r1 == 0) goto L_0x00bb
            int r3 = r11.s0
            int r1 = r1.getColorForState(r12, r3)
            goto L_0x00bc
        L_0x00bb:
            r1 = r2
        L_0x00bc:
            int r3 = r11.s0
            if (r3 == r1) goto L_0x00c3
            r11.s0 = r1
            r0 = r4
        L_0x00c3:
            int[] r1 = r11.getState()
            if (r1 != 0) goto L_0x00ca
            goto L_0x00de
        L_0x00ca:
            int r3 = r1.length
            r5 = r2
        L_0x00cc:
            if (r5 >= r3) goto L_0x00de
            r6 = r1[r5]
            r7 = 16842912(0x10100a0, float:2.3694006E-38)
            if (r6 != r7) goto L_0x00db
            boolean r1 = r11.S
            if (r1 == 0) goto L_0x00de
            r1 = r4
            goto L_0x00df
        L_0x00db:
            int r5 = r5 + 1
            goto L_0x00cc
        L_0x00de:
            r1 = r2
        L_0x00df:
            boolean r3 = r11.f1087t0
            if (r3 == r1) goto L_0x00fb
            android.graphics.drawable.Drawable r3 = r11.U
            if (r3 == 0) goto L_0x00fb
            float r0 = r11.r()
            r11.f1087t0 = r1
            float r1 = r11.r()
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 == 0) goto L_0x00f8
            r0 = r4
            r1 = r0
            goto L_0x00fc
        L_0x00f8:
            r1 = r2
            r0 = r4
            goto L_0x00fc
        L_0x00fb:
            r1 = r2
        L_0x00fc:
            android.content.res.ColorStateList r3 = r11.y0
            if (r3 == 0) goto L_0x0107
            int r5 = r11.f1088u0
            int r3 = r3.getColorForState(r12, r5)
            goto L_0x0108
        L_0x0107:
            r3 = r2
        L_0x0108:
            int r5 = r11.f1088u0
            if (r5 == r3) goto L_0x0129
            r11.f1088u0 = r3
            android.content.res.ColorStateList r0 = r11.y0
            android.graphics.PorterDuff$Mode r3 = r11.f1092z0
            if (r0 == 0) goto L_0x0125
            if (r3 != 0) goto L_0x0117
            goto L_0x0125
        L_0x0117:
            int[] r5 = r11.getState()
            int r0 = r0.getColorForState(r5, r2)
            android.graphics.PorterDuffColorFilter r5 = new android.graphics.PorterDuffColorFilter
            r5.<init>(r0, r3)
            goto L_0x0126
        L_0x0125:
            r5 = 0
        L_0x0126:
            r11.f1091x0 = r5
            goto L_0x012a
        L_0x0129:
            r4 = r0
        L_0x012a:
            android.graphics.drawable.Drawable r0 = r11.f1069I
            boolean r0 = v(r0)
            if (r0 == 0) goto L_0x0139
            android.graphics.drawable.Drawable r0 = r11.f1069I
            boolean r0 = r0.setState(r12)
            r4 = r4 | r0
        L_0x0139:
            android.graphics.drawable.Drawable r0 = r11.U
            boolean r0 = v(r0)
            if (r0 == 0) goto L_0x0148
            android.graphics.drawable.Drawable r0 = r11.U
            boolean r0 = r0.setState(r12)
            r4 = r4 | r0
        L_0x0148:
            android.graphics.drawable.Drawable r0 = r11.f1073N
            boolean r0 = v(r0)
            if (r0 == 0) goto L_0x0165
            int r0 = r12.length
            int r3 = r13.length
            int r0 = r0 + r3
            int[] r0 = new int[r0]
            int r3 = r12.length
            java.lang.System.arraycopy(r12, r2, r0, r2, r3)
            int r12 = r12.length
            int r3 = r13.length
            java.lang.System.arraycopy(r13, r2, r0, r12, r3)
            android.graphics.drawable.Drawable r12 = r11.f1073N
            boolean r12 = r12.setState(r0)
            r4 = r4 | r12
        L_0x0165:
            android.graphics.drawable.RippleDrawable r12 = r11.f1074O
            boolean r12 = v(r12)
            if (r12 == 0) goto L_0x0174
            android.graphics.drawable.RippleDrawable r12 = r11.f1074O
            boolean r12 = r12.setState(r13)
            r4 = r4 | r12
        L_0x0174:
            if (r4 == 0) goto L_0x0179
            r11.invalidateSelf()
        L_0x0179:
            if (r1 == 0) goto L_0x017e
            r11.w()
        L_0x017e:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: c2.e.x(int[], int[]):boolean");
    }

    public final void y(boolean z3) {
        if (this.S != z3) {
            this.S = z3;
            float r = r();
            if (!z3 && this.f1087t0) {
                this.f1087t0 = false;
            }
            float r5 = r();
            invalidateSelf();
            if (r != r5) {
                w();
            }
        }
    }

    public final void z(Drawable drawable) {
        if (this.U != drawable) {
            float r = r();
            this.U = drawable;
            float r5 = r();
            V(this.U);
            p(this.U);
            invalidateSelf();
            if (r != r5) {
                w();
            }
        }
    }
}
