package z2;

import D0.f;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import x2.C0344k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class p extends FrameLayout {

    /* renamed from: o  reason: collision with root package name */
    public static final o f2212o = new Object();
    public q d;
    public final C0344k e;
    public int f;
    public final float g;

    /* renamed from: h  reason: collision with root package name */
    public final float f2213h;

    /* renamed from: i  reason: collision with root package name */
    public final int f2214i;

    /* renamed from: j  reason: collision with root package name */
    public final int f2215j;
    public ColorStateList k;
    public PorterDuff.Mode l;
    public Rect m;
    public boolean n;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: android.graphics.drawable.GradientDrawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: android.graphics.drawable.GradientDrawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: x2.g} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: android.graphics.drawable.GradientDrawable} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public p(android.content.Context r6, android.util.AttributeSet r7) {
        /*
            r5 = this;
            r0 = 0
            android.content.Context r6 = D2.a.a(r6, r7, r0, r0)
            r5.<init>(r6, r7)
            android.content.Context r6 = r5.getContext()
            int[] r1 = Q1.a.L
            android.content.res.TypedArray r1 = r6.obtainStyledAttributes(r7, r1)
            r2 = 6
            boolean r3 = r1.hasValue(r2)
            if (r3 == 0) goto L_0x0021
            int r2 = r1.getDimensionPixelSize(r2, r0)
            float r2 = (float) r2
            androidx.core.view.ViewCompat.setElevation(r5, r2)
        L_0x0021:
            r2 = 2
            r3 = 1
            int r2 = r1.getInt(r2, r3)
            r5.f = r2
            r2 = 8
            boolean r2 = r1.hasValue(r2)
            if (r2 != 0) goto L_0x0039
            r2 = 9
            boolean r2 = r1.hasValue(r2)
            if (r2 == 0) goto L_0x0043
        L_0x0039:
            x2.j r7 = x2.C0344k.b(r6, r7, r0, r0)
            x2.k r7 = r7.a()
            r5.e = r7
        L_0x0043:
            r7 = 3
            r2 = 1065353216(0x3f800000, float:1.0)
            float r7 = r1.getFloat(r7, r2)
            r5.g = r7
            r7 = 4
            android.content.res.ColorStateList r6 = B1.a.u(r6, r1, r7)
            r5.setBackgroundTintList(r6)
            r6 = 5
            r7 = -1
            int r6 = r1.getInt(r6, r7)
            android.graphics.PorterDuff$Mode r4 = android.graphics.PorterDuff.Mode.SRC_IN
            android.graphics.PorterDuff$Mode r6 = h2.u.d(r6, r4)
            r5.setBackgroundTintMode(r6)
            float r6 = r1.getFloat(r3, r2)
            r5.f2213h = r6
            int r6 = r1.getDimensionPixelSize(r0, r7)
            r5.f2214i = r6
            r6 = 7
            int r6 = r1.getDimensionPixelSize(r6, r7)
            r5.f2215j = r6
            android.content.res.Resources r6 = r1.getResources()
            r7 = 2131168057(0x7f070b39, float:1.7950405E38)
            int r6 = r6.getDimensionPixelSize(r7)
            z2.q.y = r6
            r1.recycle()
            z2.o r6 = f2212o
            r5.setOnTouchListener(r6)
            r5.setFocusable(r3)
            android.graphics.drawable.Drawable r6 = r5.getBackground()
            if (r6 != 0) goto L_0x00ea
            float r6 = r5.getBackgroundOverlayColorAlpha()
            r7 = 2130968882(0x7f040132, float:1.754643E38)
            int r7 = o1.C0246a.W(r7, r5)
            r1 = 2130968859(0x7f04011b, float:1.7546384E38)
            int r1 = o1.C0246a.W(r1, r5)
            int r6 = o1.C0246a.c0(r6, r7, r1)
            x2.k r7 = r5.e
            if (r7 == 0) goto L_0x00bb
            x2.g r0 = new x2.g
            r0.<init>((x2.C0344k) r7)
            android.content.res.ColorStateList r6 = android.content.res.ColorStateList.valueOf(r6)
            r0.k(r6)
            goto L_0x00d5
        L_0x00bb:
            android.content.res.Resources r7 = r5.getResources()
            r1 = 2131166951(0x7f0706e7, float:1.7948162E38)
            float r7 = r7.getDimension(r1)
            android.graphics.drawable.GradientDrawable r1 = new android.graphics.drawable.GradientDrawable
            r1.<init>()
            r1.setShape(r0)
            r1.setCornerRadius(r7)
            r1.setColor(r6)
            r0 = r1
        L_0x00d5:
            android.content.res.ColorStateList r6 = r5.k
            if (r6 == 0) goto L_0x00e3
            android.graphics.drawable.Drawable r6 = androidx.core.graphics.drawable.DrawableCompat.wrap(r0)
            android.content.res.ColorStateList r7 = r5.k
            androidx.core.graphics.drawable.DrawableCompat.setTintList(r6, r7)
            goto L_0x00e7
        L_0x00e3:
            android.graphics.drawable.Drawable r6 = androidx.core.graphics.drawable.DrawableCompat.wrap(r0)
        L_0x00e7:
            androidx.core.view.ViewCompat.setBackground(r5, r6)
        L_0x00ea:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: z2.p.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    /* access modifiers changed from: private */
    public void setBaseTransientBottomBar(q qVar) {
        this.d = qVar;
    }

    public float getActionTextColorAlpha() {
        return this.f2213h;
    }

    public int getAnimationMode() {
        return this.f;
    }

    public float getBackgroundOverlayColorAlpha() {
        return this.g;
    }

    public int getMaxInlineActionWidth() {
        return this.f2215j;
    }

    public int getMaxWidth() {
        return this.f2214i;
    }

    public final void onAttachedToWindow() {
        WindowInsets rootWindowInsets;
        super.onAttachedToWindow();
        q qVar = this.d;
        if (!(qVar == null || (rootWindowInsets = qVar.f2221i.getRootWindowInsets()) == null)) {
            qVar.r = rootWindowInsets.getMandatorySystemGestureInsets().bottom;
            qVar.i();
        }
        ViewCompat.requestApplyInsets(this);
    }

    public final void onDetachedFromWindow() {
        boolean z;
        boolean z3;
        super.onDetachedFromWindow();
        q qVar = this.d;
        if (qVar != null) {
            f F4 = f.F();
            k kVar = qVar.f2225x;
            synchronized (F4.e) {
                z = true;
                if (!F4.H(kVar)) {
                    v vVar = (v) F4.f106h;
                    if (vVar == null || kVar == null || vVar.f2233a.get() != kVar) {
                        z3 = false;
                    } else {
                        z3 = true;
                    }
                    if (!z3) {
                        z = false;
                    }
                }
            }
            if (z) {
                q.f2217C.post(new i(qVar, 1));
            }
        }
    }

    public final void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        super.onLayout(z, i2, i7, i8, i10);
        q qVar = this.d;
        if (qVar != null && qVar.t) {
            qVar.g();
            qVar.t = false;
        }
    }

    public void onMeasure(int i2, int i7) {
        super.onMeasure(i2, i7);
        super.onMeasure(i2, i7);
    }

    public void setAnimationMode(int i2) {
        this.f = i2;
    }

    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    public void setBackgroundDrawable(Drawable drawable) {
        if (!(drawable == null || this.k == null)) {
            drawable = DrawableCompat.wrap(drawable.mutate());
            DrawableCompat.setTintList(drawable, this.k);
            DrawableCompat.setTintMode(drawable, this.l);
        }
        super.setBackgroundDrawable(drawable);
    }

    public void setBackgroundTintList(ColorStateList colorStateList) {
        this.k = colorStateList;
        if (getBackground() != null) {
            Drawable wrap = DrawableCompat.wrap(getBackground().mutate());
            DrawableCompat.setTintList(wrap, colorStateList);
            DrawableCompat.setTintMode(wrap, this.l);
            if (wrap != getBackground()) {
                super.setBackgroundDrawable(wrap);
            }
        }
    }

    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        this.l = mode;
        if (getBackground() != null) {
            Drawable wrap = DrawableCompat.wrap(getBackground().mutate());
            DrawableCompat.setTintMode(wrap, mode);
            if (wrap != getBackground()) {
                super.setBackgroundDrawable(wrap);
            }
        }
    }

    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        super.setLayoutParams(layoutParams);
        if (!this.n && (layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            this.m = new Rect(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
            q qVar = this.d;
            if (qVar != null) {
                int i2 = q.y;
                qVar.i();
            }
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        o oVar;
        if (onClickListener != null) {
            oVar = null;
        } else {
            oVar = f2212o;
        }
        setOnTouchListener(oVar);
        super.setOnClickListener(onClickListener);
    }
}
