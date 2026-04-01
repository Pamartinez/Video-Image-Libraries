package com.google.android.material.appbar;

import B0.a;
import G0.c;
import S1.e;
import S1.g;
import S1.h;
import S1.i;
import S1.m;
import S1.t;
import S1.x;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$style;
import androidx.appcompat.R$styleable;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.TextViewCompat;
import androidx.reflect.os.SeslBuildReflector$SeslVersionReflector;
import com.google.android.material.appbar.model.AppBarModel;
import com.sec.android.gallery3d.R;
import g2.C0197a;
import h2.C0208c;
import h2.C0209d;
import h2.p;
import java.util.HashMap;
import og.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CollapsingToolbarLayout extends FrameLayout {

    /* renamed from: W  reason: collision with root package name */
    public static final /* synthetic */ int f1351W = 0;

    /* renamed from: A  reason: collision with root package name */
    public h f1352A;
    public int B;

    /* renamed from: C  reason: collision with root package name */
    public int f1353C;
    public WindowInsetsCompat D;
    public int E = 0;

    /* renamed from: F  reason: collision with root package name */
    public boolean f1354F;

    /* renamed from: G  reason: collision with root package name */
    public int f1355G = 0;

    /* renamed from: H  reason: collision with root package name */
    public boolean f1356H;

    /* renamed from: I  reason: collision with root package name */
    public final a f1357I;

    /* renamed from: J  reason: collision with root package name */
    public final HashMap f1358J = new HashMap();

    /* renamed from: K  reason: collision with root package name */
    public final LinearLayout f1359K;
    public final LinearLayout L;

    /* renamed from: M  reason: collision with root package name */
    public final ViewStubCompat f1360M;

    /* renamed from: N  reason: collision with root package name */
    public final TextView f1361N;

    /* renamed from: O  reason: collision with root package name */
    public TextView f1362O;

    /* renamed from: P  reason: collision with root package name */
    public boolean f1363P;
    public boolean Q;
    public final int R;
    public final int S;
    public float T;
    public float U;
    public final boolean V = true;
    public boolean d = true;
    public final int e;
    public ViewGroup f;
    public View g;

    /* renamed from: h  reason: collision with root package name */
    public View f1364h;

    /* renamed from: i  reason: collision with root package name */
    public int f1365i;

    /* renamed from: j  reason: collision with root package name */
    public int f1366j;
    public int k;
    public int l;
    public final Rect m = new Rect();
    public final C0208c n;

    /* renamed from: o  reason: collision with root package name */
    public final C0197a f1367o;

    /* renamed from: p  reason: collision with root package name */
    public boolean f1368p;
    public boolean q;
    public Drawable r;
    public Drawable s;
    public int t;
    public boolean u;
    public ValueAnimator v;
    public long w;

    /* renamed from: x  reason: collision with root package name */
    public final TimeInterpolator f1369x;
    public final TimeInterpolator y;
    public int z = -1;

    public CollapsingToolbarLayout(Context context, AttributeSet attributeSet) {
        super(D2.a.a(context, attributeSet, R.attr.collapsingToolbarLayoutStyle, com.samsung.android.photoremaster.engine.R.style.Widget_Design_CollapsingToolbar), attributeSet, R.attr.collapsingToolbarLayoutStyle);
        boolean z3;
        int i2;
        ColorStateList u3;
        ColorStateList u6;
        TextUtils.TruncateAt truncateAt;
        int statusbarHeight;
        Context context2 = getContext();
        TypedArray d2 = p.d(context2, attributeSet, Q1.a.k, R.attr.collapsingToolbarLayoutStyle, com.samsung.android.photoremaster.engine.R.style.Widget_Design_CollapsingToolbar, new int[0]);
        this.f1368p = d2.getBoolean(25, false);
        boolean z7 = d2.getBoolean(13, true);
        this.f1363P = z7;
        boolean z9 = this.f1368p;
        if (z9 == z7 && z9) {
            this.f1368p = false;
        }
        boolean z10 = d2.getBoolean(22, true);
        C0208c cVar = new C0208c(this);
        this.n = cVar;
        if (this.f1368p) {
            cVar.U = R1.a.e;
            cVar.i(false);
            cVar.f1740H = false;
            int i7 = d2.getInt(4, 8388691);
            if (cVar.f1756h != i7) {
                cVar.f1756h = i7;
                cVar.i(false);
            }
            cVar.l(d2.getInt(0, 8388627));
            int dimensionPixelSize = d2.getDimensionPixelSize(5, 0);
            this.l = dimensionPixelSize;
            this.k = dimensionPixelSize;
            this.f1366j = dimensionPixelSize;
            this.f1365i = dimensionPixelSize;
        }
        this.f1367o = new C0197a(context2);
        this.S = d2.getResourceId(14, 0);
        this.R = d2.getResourceId(12, 0);
        if (d2.hasValue(10)) {
            this.S = d2.getResourceId(10, 0);
        }
        CharSequence text = d2.getText(21);
        if (!this.f1363P || TextUtils.isEmpty(text)) {
            z3 = false;
        } else {
            z3 = true;
        }
        this.Q = z3;
        FrameLayout frameLayout = new FrameLayout(context2);
        this.f1357I = new a(frameLayout);
        addView(frameLayout);
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context2).inflate(R.layout.sesl_app_bar, frameLayout, false);
        a aVar = this.f1357I;
        if (viewGroup != null) {
            ((t) aVar.e).push(viewGroup);
            ((FrameLayout) aVar.d).addView(viewGroup);
        } else {
            aVar.getClass();
        }
        LinearLayout linearLayout = (LinearLayout) viewGroup.findViewById(R.id.collapsing_appbar_title_layout_parent);
        this.L = linearLayout;
        if (linearLayout != null && (statusbarHeight = getStatusbarHeight()) > 0) {
            LinearLayout linearLayout2 = this.L;
            linearLayout2.setPadding(linearLayout2.getPaddingLeft(), linearLayout2.getPaddingTop(), linearLayout2.getPaddingRight(), statusbarHeight);
        }
        this.f1359K = (LinearLayout) findViewById(R.id.collapsing_appbar_title_layout);
        if (this.f1363P) {
            TextView textView = (TextView) findViewById(R.id.collapsing_appbar_extended_title);
            textView.setHyphenationFrequency(1);
            textView.setTextAppearance(context2, this.S);
            textView.setVisibility(0);
            this.f1361N = textView;
        }
        if (this.Q) {
            c(text);
        }
        e();
        j();
        if (d2.hasValue(8)) {
            this.f1365i = d2.getDimensionPixelSize(8, 0);
        }
        if (d2.hasValue(7)) {
            this.k = d2.getDimensionPixelSize(7, 0);
        }
        if (d2.hasValue(9)) {
            this.f1366j = d2.getDimensionPixelSize(9, 0);
        }
        if (d2.hasValue(6)) {
            this.l = d2.getDimensionPixelSize(6, 0);
        }
        setTitle(d2.getText(23));
        if (this.f1368p) {
            cVar.n(com.samsung.android.photoremaster.engine.R.style.TextAppearance_Design_CollapsingToolbar_Expanded);
            cVar.k(R$style.TextAppearance_AppCompat_Widget_ActionBar_Title);
            if (d2.hasValue(10)) {
                cVar.n(d2.getResourceId(10, 0));
            }
            if (d2.hasValue(1)) {
                cVar.k(d2.getResourceId(1, 0));
            }
            if (d2.hasValue(27)) {
                int i8 = d2.getInt(27, -1);
                if (i8 == 0) {
                    truncateAt = TextUtils.TruncateAt.START;
                } else if (i8 == 1) {
                    truncateAt = TextUtils.TruncateAt.MIDDLE;
                } else if (i8 != 3) {
                    truncateAt = TextUtils.TruncateAt.END;
                } else {
                    truncateAt = TextUtils.TruncateAt.MARQUEE;
                }
                setTitleEllipsize(truncateAt);
            }
            if (d2.hasValue(11) && cVar.l != (u6 = B1.a.u(context2, d2, 11))) {
                cVar.l = u6;
                cVar.i(false);
            }
            if (d2.hasValue(2) && cVar.m != (u3 = B1.a.u(context2, d2, 2))) {
                cVar.m = u3;
                cVar.i(false);
            }
        }
        this.z = d2.getDimensionPixelSize(19, -1);
        if (d2.hasValue(17) && (i2 = d2.getInt(17, 1)) != cVar.l0) {
            cVar.l0 = i2;
            Bitmap bitmap = cVar.f1741I;
            if (bitmap != null) {
                bitmap.recycle();
                cVar.f1741I = null;
            }
            cVar.i(false);
        }
        if (d2.hasValue(26)) {
            cVar.T = AnimationUtils.loadInterpolator(context2, d2.getResourceId(26, 0));
            cVar.i(false);
        }
        this.w = (long) d2.getInt(18, 600);
        this.f1369x = k.M(context2, R.attr.motionEasingStandardInterpolator, R1.a.f641c);
        this.y = k.M(context2, R.attr.motionEasingStandardInterpolator, R1.a.d);
        setContentScrim(d2.getDrawable(3));
        setStatusBarScrim(d2.getDrawable(20));
        this.e = d2.getResourceId(28, -1);
        this.f1354F = d2.getBoolean(16, false);
        this.f1356H = d2.getBoolean(15, false);
        d2.recycle();
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(R$styleable.AppCompatTheme);
        boolean z11 = obtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowActionModeOverlay, false);
        if (z10 && !z11) {
            LayoutInflater.from(context2).inflate(R.layout.sesl_material_action_mode_view_stub, this, true);
            this.f1360M = (ViewStubCompat) findViewById(R.id.action_mode_bar_stub);
        }
        obtainStyledAttributes.recycle();
        setWillNotDraw(false);
        ViewCompat.setOnApplyWindowInsetsListener(this, new c(8, (Object) this));
    }

    public static x b(View view) {
        x xVar = (x) view.getTag(R.id.view_offset_helper);
        if (xVar != null) {
            return xVar;
        }
        x xVar2 = new x(view);
        view.setTag(R.id.view_offset_helper, xVar2);
        return xVar2;
    }

    private int getDefaultContentScrimColorForTitleCollapseFadeMode() {
        Context context = getContext();
        TypedValue J4 = k.J(context, R.attr.colorSurfaceContainer);
        ColorStateList colorStateList = null;
        if (J4 != null) {
            int i2 = J4.resourceId;
            if (i2 != 0) {
                colorStateList = ContextCompat.getColorStateList(context, i2);
            } else {
                int i7 = J4.data;
                if (i7 != 0) {
                    colorStateList = ColorStateList.valueOf(i7);
                }
            }
        }
        if (colorStateList != null) {
            return colorStateList.getDefaultColor();
        }
        float dimension = getResources().getDimension(R.dimen.design_appbar_elevation);
        C0197a aVar = this.f1367o;
        return aVar.a(dimension, aVar.d);
    }

    private int getStatusbarHeight() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return getResources().getDimensionPixelOffset(identifier);
        }
        return 0;
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.view.View] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a() {
        /*
            r6 = this;
            boolean r0 = r6.d
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            r0 = 0
            r6.f = r0
            r6.g = r0
            r1 = -1
            int r2 = r6.e
            if (r2 == r1) goto L_0x002f
            android.view.View r1 = r6.findViewById(r2)
            android.view.ViewGroup r1 = (android.view.ViewGroup) r1
            r6.f = r1
            if (r1 == 0) goto L_0x002f
            android.view.ViewParent r2 = r1.getParent()
        L_0x001d:
            if (r2 == r6) goto L_0x002d
            if (r2 == 0) goto L_0x002d
            boolean r3 = r2 instanceof android.view.View
            if (r3 == 0) goto L_0x0028
            r1 = r2
            android.view.View r1 = (android.view.View) r1
        L_0x0028:
            android.view.ViewParent r2 = r2.getParent()
            goto L_0x001d
        L_0x002d:
            r6.g = r1
        L_0x002f:
            android.view.ViewGroup r1 = r6.f
            r2 = 0
            if (r1 != 0) goto L_0x005a
            int r1 = r6.getChildCount()
            r3 = r2
        L_0x0039:
            if (r3 >= r1) goto L_0x004e
            android.view.View r4 = r6.getChildAt(r3)
            boolean r5 = r4 instanceof androidx.appcompat.widget.Toolbar
            if (r5 != 0) goto L_0x004b
            boolean r5 = r4 instanceof android.widget.Toolbar
            if (r5 == 0) goto L_0x0048
            goto L_0x004b
        L_0x0048:
            int r3 = r3 + 1
            goto L_0x0039
        L_0x004b:
            r0 = r4
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
        L_0x004e:
            r6.f = r0
            androidx.appcompat.widget.ViewStubCompat r0 = r6.f1360M
            if (r0 == 0) goto L_0x005a
            r0.bringToFront()
            r0.invalidate()
        L_0x005a:
            r6.f()
            r6.d = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.CollapsingToolbarLayout.a():void");
    }

    public final void addView(View view, ViewGroup.LayoutParams layoutParams) {
        g gVar;
        super.addView(view, layoutParams);
        if (this.f1363P && (gVar = (g) view.getLayoutParams()) != null && gVar.f781c) {
            TextView textView = this.f1361N;
            if (textView != null && textView.getParent() == this.f1359K) {
                this.f1361N.setVisibility(8);
            }
            TextView textView2 = this.f1362O;
            if (textView2 != null && textView2.getParent() == this.f1359K) {
                this.f1362O.setVisibility(8);
            }
            if (view.getParent() != null) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
            this.f1359K.addView(view, layoutParams);
        }
    }

    public final void c(CharSequence charSequence) {
        if (!this.f1363P || TextUtils.isEmpty(charSequence)) {
            this.Q = false;
            TextView textView = this.f1362O;
            if (textView != null) {
                textView.setVisibility(8);
            }
        } else {
            this.Q = true;
            if (this.f1362O == null) {
                TextView textView2 = (TextView) findViewById(R.id.collapsing_appbar_extended_subtitle);
                textView2.setTextAppearance(getContext(), this.R);
                this.f1362O = textView2;
            }
            this.f1362O.setText(charSequence);
            this.f1362O.setVisibility(0);
            TextView textView3 = this.f1361N;
            if (textView3 != null) {
                textView3.setTextSize(0, (float) getContext().getResources().getDimensionPixelSize(R.dimen.sesl_appbar_extended_title_text_size_with_subtitle));
            }
        }
        j();
        requestLayout();
    }

    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof g;
    }

    public final void d(AppBarModel appBarModel) {
        ViewGroup viewGroup;
        HashMap hashMap = this.f1358J;
        for (AppBarModel appBarModel2 : hashMap.keySet()) {
            ViewGroup viewGroup2 = (ViewGroup) hashMap.get(appBarModel2);
            a aVar = this.f1357I;
            AnimatorSet animatorSet = (AnimatorSet) aVar.f34h;
            t tVar = (t) aVar.e;
            tVar.remove(viewGroup2);
            if (viewGroup2 != null) {
                if (animatorSet.isRunning()) {
                    animatorSet.cancel();
                }
                ((ObjectAnimator) aVar.g).setTarget(viewGroup2);
                ObjectAnimator objectAnimator = (ObjectAnimator) aVar.f;
                if (!tVar.isEmpty()) {
                    viewGroup = (ViewGroup) tVar.peek();
                } else {
                    viewGroup = null;
                }
                objectAnimator.setTarget(viewGroup);
                animatorSet.start();
            }
        }
        hashMap.clear();
        if (appBarModel != null) {
            T1.a create = appBarModel.create();
            hashMap.put(appBarModel, create);
            a aVar2 = this.f1357I;
            if (create != null) {
                ((t) aVar2.e).push(create);
                ((FrameLayout) aVar2.d).addView(create);
            } else {
                aVar2.getClass();
            }
        }
        if (getParent() instanceof AppBarLayout) {
            ((AppBarLayout) getParent()).seslSetSuggestion(!hashMap.isEmpty());
        }
    }

    public final void draw(Canvas canvas) {
        int i2;
        Drawable drawable;
        super.draw(canvas);
        a();
        if (this.f == null && (drawable = this.r) != null && this.t > 0) {
            drawable.mutate().setAlpha(this.t);
            this.r.draw(canvas);
        }
        if (this.f1368p && this.q) {
            ViewGroup viewGroup = this.f;
            C0208c cVar = this.n;
            if (viewGroup == null || this.r == null || this.t <= 0 || this.f1353C != 1 || cVar.b >= cVar.d) {
                cVar.d(canvas);
            } else {
                int save = canvas.save();
                canvas.clipRect(this.r.getBounds(), Region.Op.DIFFERENCE);
                cVar.d(canvas);
                canvas.restoreToCount(save);
            }
        }
        if (this.s != null && this.t > 0) {
            WindowInsetsCompat windowInsetsCompat = this.D;
            if (windowInsetsCompat != null) {
                i2 = windowInsetsCompat.getSystemWindowInsetTop();
            } else {
                i2 = 0;
            }
            if (i2 > 0) {
                this.s.setBounds(0, -this.B, getWidth(), i2 - this.B);
                this.s.mutate().setAlpha(this.t);
                this.s.draw(canvas);
            }
        }
    }

    public final boolean drawChild(Canvas canvas, View view, long j2) {
        boolean z3;
        View view2;
        Drawable drawable = this.r;
        if (drawable == null || this.t <= 0 || ((view2 = this.g) == null || view2 == this ? view != this.f : view != view2)) {
            z3 = false;
        } else {
            int width = getWidth();
            int height = getHeight();
            if (this.f1353C == 1 && view != null && this.f1368p) {
                height = view.getBottom();
            }
            drawable.setBounds(0, 0, width, height);
            this.r.mutate().setAlpha(this.t);
            this.r.draw(canvas);
            z3 = true;
        }
        if (super.drawChild(canvas, view, j2) || z3) {
            return true;
        }
        return false;
    }

    public final void drawableStateChanged() {
        boolean z3;
        ColorStateList colorStateList;
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.s;
        boolean z7 = false;
        if (drawable == null || !drawable.isStateful()) {
            z3 = false;
        } else {
            z3 = drawable.setState(drawableState);
        }
        Drawable drawable2 = this.r;
        if (drawable2 != null && drawable2.isStateful()) {
            z3 |= drawable2.setState(drawableState);
        }
        C0208c cVar = this.n;
        if (cVar != null) {
            cVar.f1747P = drawableState;
            ColorStateList colorStateList2 = cVar.m;
            if ((colorStateList2 != null && colorStateList2.isStateful()) || ((colorStateList = cVar.l) != null && colorStateList.isStateful())) {
                cVar.i(false);
                z7 = true;
            }
            z3 |= z7;
        }
        if (z3) {
            invalidate();
        }
    }

    public final void e() {
        if (getParent() instanceof AppBarLayout) {
            AppBarLayout appBarLayout = (AppBarLayout) getParent();
            if (appBarLayout.useCollapsedHeight()) {
                this.T = appBarLayout.seslGetCollapsedHeight();
            } else {
                this.T = (float) getResources().getDimensionPixelSize(R$dimen.sesl_action_bar_height_with_padding);
            }
        } else {
            this.T = (float) getResources().getDimensionPixelSize(R$dimen.sesl_action_bar_height_with_padding);
        }
    }

    public final void f() {
        View view;
        if (!this.f1368p && (view = this.f1364h) != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.f1364h);
            }
        }
        if (this.f1368p && this.f != null) {
            if (this.f1364h == null) {
                this.f1364h = new View(getContext());
            }
            if (this.f1364h.getParent() == null) {
                this.f.addView(this.f1364h, -1, -1);
            }
        }
    }

    public final void g() {
        boolean z3;
        if (this.r != null || this.s != null) {
            if (getHeight() + this.B < getScrimVisibleHeightTrigger()) {
                z3 = true;
            } else {
                z3 = false;
            }
            setScrimsShown(z3);
        }
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [S1.g, android.view.ViewGroup$LayoutParams, android.widget.FrameLayout$LayoutParams] */
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        ? layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.f780a = 0;
        layoutParams.b = 0.5f;
        return layoutParams;
    }

    public int getCollapsedTitleGravity() {
        if (this.f1368p) {
            return this.n.f1757i;
        }
        return 0;
    }

    public float getCollapsedTitleTextSize() {
        return this.n.k;
    }

    public Typeface getCollapsedTitleTypeface() {
        if (!this.f1368p) {
            return Typeface.DEFAULT;
        }
        Typeface typeface = this.n.u;
        if (typeface != null) {
            return typeface;
        }
        return Typeface.DEFAULT;
    }

    public Drawable getContentScrim() {
        return this.r;
    }

    public int getExpandedTitleGravity() {
        if (this.f1363P) {
            return this.f1361N.getGravity();
        }
        if (this.f1368p) {
            return this.n.f1756h;
        }
        return 0;
    }

    public int getExpandedTitleMarginBottom() {
        return this.l;
    }

    public int getExpandedTitleMarginEnd() {
        return this.k;
    }

    public int getExpandedTitleMarginStart() {
        return this.f1365i;
    }

    public int getExpandedTitleMarginTop() {
        return this.f1366j;
    }

    public float getExpandedTitleTextSize() {
        return this.n.f1759j;
    }

    public Typeface getExpandedTitleTypeface() {
        if (this.f1363P) {
            return this.f1361N.getTypeface();
        }
        if (!this.f1368p) {
            return Typeface.DEFAULT;
        }
        Typeface typeface = this.n.f1765x;
        if (typeface != null) {
            return typeface;
        }
        return Typeface.DEFAULT;
    }

    public int getHyphenationFrequency() {
        return this.n.o0;
    }

    public int getLineCount() {
        StaticLayout staticLayout = this.n.f1755g0;
        if (staticLayout != null) {
            return staticLayout.getLineCount();
        }
        return 0;
    }

    public float getLineSpacingAdd() {
        return this.n.f1755g0.getSpacingAdd();
    }

    public float getLineSpacingMultiplier() {
        return this.n.f1755g0.getSpacingMultiplier();
    }

    public int getMaxLines() {
        return this.n.l0;
    }

    public int getScrimAlpha() {
        return this.t;
    }

    public long getScrimAnimationDuration() {
        return this.w;
    }

    public int getScrimVisibleHeightTrigger() {
        int i2;
        int i7 = this.z;
        if (i7 >= 0) {
            return i7 + this.E + this.f1355G;
        }
        WindowInsetsCompat windowInsetsCompat = this.D;
        if (windowInsetsCompat != null) {
            i2 = windowInsetsCompat.getSystemWindowInsetTop();
        } else {
            i2 = 0;
        }
        int minimumHeight = ViewCompat.getMinimumHeight(this);
        if (minimumHeight > 0) {
            return Math.min((minimumHeight * 2) + i2, getHeight());
        }
        return getHeight() / 3;
    }

    public Drawable getStatusBarScrim() {
        return this.s;
    }

    public CharSequence getSubTitle() {
        TextView textView;
        TextView textView2 = this.f1362O;
        if (textView2 == null || textView2.getVisibility() != 0 || (textView = this.f1362O) == null) {
            return null;
        }
        return textView.getText();
    }

    public CharSequence getTitle() {
        if (this.f1368p) {
            return this.n.E;
        }
        return this.f1361N.getText();
    }

    public int getTitleCollapseMode() {
        return this.f1353C;
    }

    public TimeInterpolator getTitlePositionInterpolator() {
        return this.n.T;
    }

    public TextUtils.TruncateAt getTitleTextEllipsize() {
        return this.n.D;
    }

    public final void h(int i2, int i7, int i8, int i10, boolean z3) {
        View view;
        boolean z7;
        boolean z9;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        if (this.f1368p && (view = this.f1364h) != null) {
            int i17 = 0;
            if (!ViewCompat.isAttachedToWindow(view) || this.f1364h.getVisibility() != 0) {
                z7 = false;
            } else {
                z7 = true;
            }
            this.q = z7;
            if (z7 || z3) {
                if (ViewCompat.getLayoutDirection(this) == 1) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                View view2 = this.g;
                if (view2 == null) {
                    view2 = this.f;
                }
                int height = ((getHeight() - b(view2).b) - view2.getHeight()) - ((g) view2.getLayoutParams()).bottomMargin;
                View view3 = this.f1364h;
                Rect rect = this.m;
                C0209d.a(this, view3, rect);
                ViewGroup viewGroup = this.f;
                if (viewGroup instanceof Toolbar) {
                    Toolbar toolbar = (Toolbar) viewGroup;
                    i17 = toolbar.getTitleMarginStart();
                    i12 = toolbar.getTitleMarginEnd();
                    i11 = toolbar.getTitleMarginTop();
                    i13 = toolbar.getTitleMarginBottom();
                } else if (viewGroup instanceof android.widget.Toolbar) {
                    android.widget.Toolbar toolbar2 = (android.widget.Toolbar) viewGroup;
                    i17 = toolbar2.getTitleMarginStart();
                    i12 = toolbar2.getTitleMarginEnd();
                    i11 = toolbar2.getTitleMarginTop();
                    i13 = toolbar2.getTitleMarginBottom();
                } else {
                    i13 = 0;
                    i12 = 0;
                    i11 = 0;
                }
                int i18 = rect.left;
                if (z9) {
                    i14 = i12;
                } else {
                    i14 = i17;
                }
                int i19 = i18 + i14;
                int i20 = rect.top + height + i11;
                int i21 = rect.right;
                if (!z9) {
                    i17 = i12;
                }
                int i22 = i21 - i17;
                int i23 = (rect.bottom + height) - i13;
                C0208c cVar = this.n;
                Rect rect2 = cVar.f;
                if (!(rect2.left == i19 && rect2.top == i20 && rect2.right == i22 && rect2.bottom == i23)) {
                    rect2.set(i19, i20, i22, i23);
                    cVar.Q = true;
                }
                if (z9) {
                    i15 = this.k;
                } else {
                    i15 = this.f1365i;
                }
                int i24 = rect.top + this.f1366j;
                int i25 = i8 - i2;
                if (z9) {
                    i16 = this.f1365i;
                } else {
                    i16 = this.k;
                }
                int i26 = i25 - i16;
                int i27 = (i10 - i7) - this.l;
                Rect rect3 = cVar.e;
                if (!(rect3.left == i15 && rect3.top == i24 && rect3.right == i26 && rect3.bottom == i27)) {
                    rect3.set(i15, i24, i26, i27);
                    cVar.Q = true;
                }
                cVar.i(z3);
            }
        }
    }

    public final void i() {
        CharSequence charSequence;
        if (this.f != null && this.f1368p && TextUtils.isEmpty(this.n.E)) {
            ViewGroup viewGroup = this.f;
            if (viewGroup instanceof Toolbar) {
                charSequence = ((Toolbar) viewGroup).getTitle();
            } else if (viewGroup instanceof android.widget.Toolbar) {
                charSequence = ((android.widget.Toolbar) viewGroup).getTitle();
            } else {
                charSequence = null;
            }
            setTitle(charSequence);
        }
    }

    public final void j() {
        Resources resources = getResources();
        this.U = m.b(getContext());
        if (this.f1363P) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(this.S, R$styleable.TextAppearance);
            TypedValue peekValue = obtainStyledAttributes.peekValue(R$styleable.TextAppearance_android_textSize);
            if (peekValue == null) {
                Log.i("Sesl_CTL", "ExtendTitleAppearance value is null");
                obtainStyledAttributes.recycle();
                return;
            }
            float complexToFloat = TypedValue.complexToFloat(peekValue.data);
            float min = Math.min(resources.getConfiguration().fontScale, 1.0f);
            obtainStyledAttributes.recycle();
            Log.i("Sesl_CTL", "updateTitleLayout : context : " + getContext() + ", textSize : " + complexToFloat + ", fontScale : " + min + ", mSubTitleEnabled : " + this.Q);
            if (!this.Q) {
                this.f1361N.setTextSize(1, complexToFloat * min);
            } else {
                this.f1361N.setTextSize(0, (float) resources.getDimensionPixelSize(R.dimen.sesl_appbar_extended_title_text_size_with_subtitle));
                TextView textView = this.f1362O;
                if (textView != null) {
                    textView.setTextSize(0, (float) resources.getDimensionPixelSize(R.dimen.sesl_appbar_extended_subtitle_text_size));
                }
            }
            if (Math.abs(this.U - 0.3f) >= 1.0E-5f) {
                this.f1361N.setSingleLine(false);
                this.f1361N.setMaxLines(2);
            } else if (this.Q) {
                this.f1361N.setSingleLine(true);
                this.f1361N.setMaxLines(1);
            } else {
                this.f1361N.setSingleLine(false);
                this.f1361N.setMaxLines(2);
            }
            int maxLines = this.f1361N.getMaxLines();
            if (SeslBuildReflector$SeslVersionReflector.getField_SEM_PLATFORM_INT() >= 120000) {
                if (maxLines > 1) {
                    try {
                        int statusbarHeight = getStatusbarHeight();
                        if (this.Q && statusbarHeight > 0) {
                            statusbarHeight += getResources().getDimensionPixelSize(R$dimen.sesl_action_bar_top_padding);
                        }
                        LinearLayout linearLayout = this.L;
                        linearLayout.setPadding(linearLayout.getPaddingLeft(), linearLayout.getPaddingTop(), linearLayout.getPaddingRight(), statusbarHeight);
                    } catch (Exception e7) {
                        Log.e("Sesl_CTL", Log.getStackTraceString(e7));
                    }
                } else {
                    this.f1361N.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                    TextViewCompat.setAutoSizeTextTypeWithDefaults(this.f1361N, 0);
                    this.f1361N.setTextSize(0, (float) resources.getDimensionPixelSize(R.dimen.sesl_appbar_extended_title_text_size_with_subtitle));
                }
            }
        }
        for (T1.a updateResource : this.f1358J.values()) {
            updateResource.updateResource(getContext());
        }
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (parent instanceof AppBarLayout) {
            AppBarLayout appBarLayout = (AppBarLayout) parent;
            if (this.f1353C == 1) {
                appBarLayout.setLiftOnScroll(false);
            }
            ViewCompat.setFitsSystemWindows(this, ViewCompat.getFitsSystemWindows(appBarLayout));
            if (this.f1352A == null) {
                this.f1352A = new h(this);
            }
            appBarLayout.addOnOffsetChangedListener((e) this.f1352A);
            ViewCompat.requestApplyInsets(this);
        }
    }

    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.f1368p) {
            this.n.h(configuration);
        }
        this.U = m.b(getContext());
        e();
        j();
    }

    public final void onDetachedFromWindow() {
        ViewParent parent = getParent();
        h hVar = this.f1352A;
        if (hVar != null && (parent instanceof AppBarLayout)) {
            ((AppBarLayout) parent).removeOnOffsetChangedListener((e) hVar);
        }
        super.onDetachedFromWindow();
    }

    public final void onLayout(boolean z3, int i2, int i7, int i8, int i10) {
        super.onLayout(z3, i2, i7, i8, i10);
        int i11 = i2;
        int i12 = i7;
        int i13 = i8;
        int i14 = i10;
        WindowInsetsCompat windowInsetsCompat = this.D;
        if (windowInsetsCompat != null) {
            int systemWindowInsetTop = windowInsetsCompat.getSystemWindowInsetTop();
            int childCount = getChildCount();
            for (int i15 = 0; i15 < childCount; i15++) {
                View childAt = getChildAt(i15);
                if (!ViewCompat.getFitsSystemWindows(childAt) && childAt.getTop() < systemWindowInsetTop) {
                    ViewCompat.offsetTopAndBottom(childAt, systemWindowInsetTop);
                }
            }
        }
        int childCount2 = getChildCount();
        for (int i16 = 0; i16 < childCount2; i16++) {
            x b = b(getChildAt(i16));
            View view = b.f792a;
            b.b = view.getTop();
            b.f793c = view.getLeft();
        }
        h(i11, i12, i13, i14, false);
        i();
        g();
        int childCount3 = getChildCount();
        for (int i17 = 0; i17 < childCount3; i17++) {
            b(getChildAt(i17)).a();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:37:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onMeasure(int r10, int r11) {
        /*
            r9 = this;
            r9.a()
            super.onMeasure(r10, r11)
            int r11 = android.view.View.MeasureSpec.getMode(r11)
            androidx.core.view.WindowInsetsCompat r0 = r9.D
            if (r0 == 0) goto L_0x0013
            int r0 = r0.getSystemWindowInsetTop()
            goto L_0x0014
        L_0x0013:
            r0 = 0
        L_0x0014:
            r1 = 1073741824(0x40000000, float:2.0)
            if (r11 == 0) goto L_0x001c
            boolean r11 = r9.f1354F
            if (r11 == 0) goto L_0x002c
        L_0x001c:
            if (r0 <= 0) goto L_0x002c
            r9.E = r0
            int r11 = r9.getMeasuredHeight()
            int r11 = r11 + r0
            int r11 = android.view.View.MeasureSpec.makeMeasureSpec(r11, r1)
            super.onMeasure(r10, r11)
        L_0x002c:
            boolean r11 = r9.f1356H
            if (r11 == 0) goto L_0x0083
            boolean r11 = r9.f1368p
            if (r11 == 0) goto L_0x0083
            h2.c r11 = r9.n
            int r0 = r11.l0
            r2 = 1
            if (r0 <= r2) goto L_0x0083
            r9.i()
            int r6 = r9.getMeasuredWidth()
            int r7 = r9.getMeasuredHeight()
            r8 = 1
            r4 = 0
            r5 = 0
            r3 = r9
            r3.h(r4, r5, r6, r7, r8)
            int r9 = r11.n
            if (r9 <= r2) goto L_0x0084
            android.text.TextPaint r0 = r11.S
            float r4 = r11.f1759j
            r0.setTextSize(r4)
            android.graphics.Typeface r4 = r11.f1765x
            r0.setTypeface(r4)
            float r11 = r11.f1753e0
            r0.setLetterSpacing(r11)
            float r11 = r0.ascent()
            float r11 = -r11
            float r0 = r0.descent()
            float r0 = r0 + r11
            int r11 = java.lang.Math.round(r0)
            int r9 = r9 - r2
            int r9 = r9 * r11
            r3.f1355G = r9
            int r9 = r3.getMeasuredHeight()
            int r11 = r3.f1355G
            int r9 = r9 + r11
            int r9 = android.view.View.MeasureSpec.makeMeasureSpec(r9, r1)
            super.onMeasure(r10, r9)
            goto L_0x0084
        L_0x0083:
            r3 = r9
        L_0x0084:
            android.view.ViewGroup r9 = r3.f
            if (r9 == 0) goto L_0x00c8
            android.view.View r10 = r3.g
            if (r10 == 0) goto L_0x00ac
            if (r10 != r3) goto L_0x008f
            goto L_0x00ac
        L_0x008f:
            android.view.ViewGroup$LayoutParams r9 = r10.getLayoutParams()
            boolean r11 = r9 instanceof android.view.ViewGroup.MarginLayoutParams
            if (r11 == 0) goto L_0x00a4
            android.view.ViewGroup$MarginLayoutParams r9 = (android.view.ViewGroup.MarginLayoutParams) r9
            int r10 = r10.getMeasuredHeight()
            int r11 = r9.topMargin
            int r10 = r10 + r11
            int r9 = r9.bottomMargin
            int r10 = r10 + r9
            goto L_0x00a8
        L_0x00a4:
            int r10 = r10.getMeasuredHeight()
        L_0x00a8:
            r3.setMinimumHeight(r10)
            return
        L_0x00ac:
            android.view.ViewGroup$LayoutParams r10 = r9.getLayoutParams()
            boolean r11 = r10 instanceof android.view.ViewGroup.MarginLayoutParams
            if (r11 == 0) goto L_0x00c1
            android.view.ViewGroup$MarginLayoutParams r10 = (android.view.ViewGroup.MarginLayoutParams) r10
            int r9 = r9.getMeasuredHeight()
            int r11 = r10.topMargin
            int r9 = r9 + r11
            int r10 = r10.bottomMargin
            int r9 = r9 + r10
            goto L_0x00c5
        L_0x00c1:
            int r9 = r9.getMeasuredHeight()
        L_0x00c5:
            r3.setMinimumHeight(r9)
        L_0x00c8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.CollapsingToolbarLayout.onMeasure(int, int):void");
    }

    public final void onSizeChanged(int i2, int i7, int i8, int i10) {
        super.onSizeChanged(i2, i7, i8, i10);
        Drawable drawable = this.r;
        if (drawable != null) {
            ViewGroup viewGroup = this.f;
            if (this.f1353C == 1 && viewGroup != null && this.f1368p) {
                i7 = viewGroup.getBottom();
            }
            drawable.setBounds(0, 0, i2, i7);
        }
    }

    public void setCollapsedTitleGravity(int i2) {
        if (this.f1368p) {
            this.n.l(i2);
        }
    }

    public void setCollapsedTitleTextAppearance(int i2) {
        if (this.f1368p) {
            this.n.k(i2);
        }
    }

    public void setCollapsedTitleTextColor(int i2) {
        setCollapsedTitleTextColor(ColorStateList.valueOf(i2));
    }

    public void setCollapsedTitleTextSize(float f5) {
        C0208c cVar = this.n;
        if (cVar.k != f5) {
            cVar.k = f5;
            cVar.i(false);
        }
    }

    public void setCollapsedTitleTypeface(Typeface typeface) {
        if (this.f1368p) {
            C0208c cVar = this.n;
            if (cVar.m(typeface)) {
                cVar.i(false);
            }
        }
    }

    public void setContentScrim(Drawable drawable) {
        Drawable drawable2 = this.r;
        if (drawable2 != drawable) {
            Drawable drawable3 = null;
            if (drawable2 != null) {
                drawable2.setCallback((Drawable.Callback) null);
            }
            if (drawable != null) {
                drawable3 = drawable.mutate();
            }
            this.r = drawable3;
            if (drawable3 != null) {
                drawable3.setBounds(0, 0, getWidth(), getHeight());
                this.r.setCallback(this);
                this.r.setAlpha(this.t);
            }
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void setContentScrimColor(int i2) {
        setContentScrim(new ColorDrawable(i2));
    }

    public void setContentScrimResource(int i2) {
        setContentScrim(ContextCompat.getDrawable(getContext(), i2));
    }

    public void setExpandedTitleColor(int i2) {
        setExpandedTitleTextColor(ColorStateList.valueOf(i2));
    }

    public void setExpandedTitleGravity(int i2) {
        if (this.f1363P) {
            this.f1361N.setGravity(i2);
        } else if (this.f1368p) {
            C0208c cVar = this.n;
            if (cVar.f1756h != i2) {
                cVar.f1756h = i2;
                cVar.i(false);
            }
        }
    }

    public void setExpandedTitleMarginBottom(int i2) {
        this.l = i2;
        requestLayout();
    }

    public void setExpandedTitleMarginEnd(int i2) {
        this.k = i2;
        requestLayout();
    }

    public void setExpandedTitleMarginStart(int i2) {
        this.f1365i = i2;
        requestLayout();
    }

    public void setExpandedTitleMarginTop(int i2) {
        this.f1366j = i2;
        requestLayout();
    }

    public void setExpandedTitleTextAppearance(int i2) {
        if (this.f1363P) {
            this.f1361N.setTextAppearance(getContext(), i2);
        } else if (this.f1368p) {
            this.n.n(i2);
        }
    }

    public void setExpandedTitleTextColor(ColorStateList colorStateList) {
        if (this.f1363P) {
            this.f1361N.setTextColor(colorStateList);
        } else if (this.f1368p) {
            C0208c cVar = this.n;
            if (cVar.l != colorStateList) {
                cVar.l = colorStateList;
                cVar.i(false);
            }
        }
    }

    public void setExpandedTitleTextSize(float f5) {
        C0208c cVar = this.n;
        if (cVar.f1759j != f5) {
            cVar.f1759j = f5;
            cVar.i(false);
        }
    }

    public void setExpandedTitleTypeface(Typeface typeface) {
        if (this.f1363P) {
            this.f1361N.setTypeface(typeface);
        } else if (this.f1368p) {
            C0208c cVar = this.n;
            if (cVar.o(typeface)) {
                cVar.i(false);
            }
        }
    }

    public void setExtraMultilineHeightEnabled(boolean z3) {
        this.f1356H = z3;
    }

    public void setForceApplySystemWindowInsetTop(boolean z3) {
        this.f1354F = z3;
    }

    public void setHyphenationFrequency(int i2) {
        this.n.o0 = i2;
    }

    public void setLineSpacingAdd(float f5) {
        this.n.f1761m0 = f5;
    }

    public void setLineSpacingMultiplier(float f5) {
        this.n.f1762n0 = f5;
    }

    public void setMaxLines(int i2) {
        C0208c cVar = this.n;
        if (i2 != cVar.l0) {
            cVar.l0 = i2;
            Bitmap bitmap = cVar.f1741I;
            if (bitmap != null) {
                bitmap.recycle();
                cVar.f1741I = null;
            }
            cVar.i(false);
        }
    }

    public void setRtlTextDirectionHeuristicsEnabled(boolean z3) {
        this.n.f1740H = z3;
    }

    public void setScrimAlpha(int i2) {
        ViewGroup viewGroup;
        if (i2 != this.t) {
            if (!(this.r == null || (viewGroup = this.f) == null)) {
                ViewCompat.postInvalidateOnAnimation(viewGroup);
            }
            this.t = i2;
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void setScrimAnimationDuration(long j2) {
        this.w = j2;
    }

    public void setScrimVisibleHeightTrigger(int i2) {
        if (this.z != i2) {
            this.z = i2;
            g();
        }
    }

    public void setScrimsShown(boolean z3) {
        boolean z7;
        TimeInterpolator timeInterpolator;
        int i2 = 0;
        if (!ViewCompat.isLaidOut(this) || isInEditMode()) {
            z7 = false;
        } else {
            z7 = true;
        }
        if (this.u != z3) {
            if (z7) {
                if (z3) {
                    i2 = 255;
                }
                a();
                ValueAnimator valueAnimator = this.v;
                if (valueAnimator == null) {
                    ValueAnimator valueAnimator2 = new ValueAnimator();
                    this.v = valueAnimator2;
                    if (i2 > this.t) {
                        timeInterpolator = this.f1369x;
                    } else {
                        timeInterpolator = this.y;
                    }
                    valueAnimator2.setInterpolator(timeInterpolator);
                    this.v.addUpdateListener(new A2.e(2, this));
                } else if (valueAnimator.isRunning()) {
                    this.v.cancel();
                }
                this.v.setDuration(this.w);
                this.v.setIntValues(new int[]{this.t, i2});
                this.v.start();
            } else {
                if (z3) {
                    i2 = 255;
                }
                setScrimAlpha(i2);
            }
            this.u = z3;
        }
    }

    public void setStaticLayoutBuilderConfigurer(i iVar) {
        C0208c cVar = this.n;
        if (iVar != null) {
            cVar.i(true);
        } else {
            cVar.getClass();
        }
    }

    public void setStatusBarScrim(Drawable drawable) {
        boolean z3;
        Drawable drawable2 = this.s;
        if (drawable2 != drawable) {
            Drawable drawable3 = null;
            if (drawable2 != null) {
                drawable2.setCallback((Drawable.Callback) null);
            }
            if (drawable != null) {
                drawable3 = drawable.mutate();
            }
            this.s = drawable3;
            if (drawable3 != null) {
                if (drawable3.isStateful()) {
                    this.s.setState(getDrawableState());
                }
                DrawableCompat.setLayoutDirection(this.s, ViewCompat.getLayoutDirection(this));
                Drawable drawable4 = this.s;
                if (getVisibility() == 0) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                drawable4.setVisible(z3, false);
                this.s.setCallback(this);
                this.s.setAlpha(this.t);
            }
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void setStatusBarScrimColor(int i2) {
        setStatusBarScrim(new ColorDrawable(i2));
    }

    public void setStatusBarScrimResource(int i2) {
        setStatusBarScrim(ContextCompat.getDrawable(getContext(), i2));
    }

    public void setTitle(CharSequence charSequence) {
        if (this.f1368p) {
            C0208c cVar = this.n;
            if (charSequence == null || !TextUtils.equals(cVar.E, charSequence)) {
                cVar.E = charSequence;
                cVar.f1738F = null;
                Bitmap bitmap = cVar.f1741I;
                if (bitmap != null) {
                    bitmap.recycle();
                    cVar.f1741I = null;
                }
                cVar.i(false);
            }
            setContentDescription(getTitle());
        } else {
            TextView textView = this.f1361N;
            if (textView != null) {
                textView.setText(charSequence);
            }
        }
        j();
    }

    public void setTitleCollapseMode(int i2) {
        boolean z3;
        this.f1353C = i2;
        if (i2 == 1) {
            z3 = true;
        } else {
            z3 = false;
        }
        this.n.f1751c = z3;
        ViewParent parent = getParent();
        if (parent instanceof AppBarLayout) {
            AppBarLayout appBarLayout = (AppBarLayout) parent;
            if (this.f1353C == 1) {
                appBarLayout.setLiftOnScroll(false);
            }
        }
        if (z3 && this.r == null) {
            setContentScrimColor(getDefaultContentScrimColorForTitleCollapseFadeMode());
        }
    }

    public void setTitleEllipsize(TextUtils.TruncateAt truncateAt) {
        C0208c cVar = this.n;
        cVar.D = truncateAt;
        cVar.i(false);
    }

    public void setTitleEnabled(boolean z3) {
        TextView textView;
        if (!z3) {
            this.f1363P = false;
            this.f1368p = false;
        } else if (this.f1361N != null) {
            this.f1363P = true;
        } else {
            this.f1363P = false;
        }
        if (!z3 && !this.f1363P && (textView = this.f1361N) != null) {
            textView.setVisibility(4);
        }
        if (z3 && this.f1368p) {
            f();
            requestLayout();
        }
    }

    public void setTitlePositionInterpolator(TimeInterpolator timeInterpolator) {
        C0208c cVar = this.n;
        cVar.T = timeInterpolator;
        cVar.i(false);
    }

    public void setVisibility(int i2) {
        boolean z3;
        super.setVisibility(i2);
        if (i2 == 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        Drawable drawable = this.s;
        if (!(drawable == null || drawable.isVisible() == z3)) {
            this.s.setVisible(z3, false);
        }
        Drawable drawable2 = this.r;
        if (drawable2 != null && drawable2.isVisible() != z3) {
            this.r.setVisible(z3, false);
        }
    }

    public final boolean verifyDrawable(Drawable drawable) {
        if (super.verifyDrawable(drawable) || drawable == this.r || drawable == this.s) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [S1.g, android.widget.FrameLayout$LayoutParams] */
    public final FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        Context context = getContext();
        ? layoutParams = new FrameLayout.LayoutParams(context, attributeSet);
        layoutParams.f780a = 0;
        layoutParams.b = 0.5f;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, Q1.a.l);
        layoutParams.f780a = obtainStyledAttributes.getInt(1, 0);
        layoutParams.b = obtainStyledAttributes.getFloat(2, 0.5f);
        layoutParams.f781c = obtainStyledAttributes.getBoolean(0, false);
        obtainStyledAttributes.recycle();
        return layoutParams;
    }

    public void setCollapsedTitleTextColor(ColorStateList colorStateList) {
        if (this.f1368p) {
            C0208c cVar = this.n;
            if (cVar.m != colorStateList) {
                cVar.m = colorStateList;
                cVar.i(false);
            }
        }
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [S1.g, android.widget.FrameLayout$LayoutParams] */
    /* renamed from: generateDefaultLayoutParams  reason: collision with other method in class */
    public final FrameLayout.LayoutParams m34generateDefaultLayoutParams() {
        ? layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.f780a = 0;
        layoutParams.b = 0.5f;
        return layoutParams;
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [S1.g, android.view.ViewGroup$LayoutParams, android.widget.FrameLayout$LayoutParams] */
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        ? layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
        layoutParams2.f780a = 0;
        layoutParams2.b = 0.5f;
        return layoutParams2;
    }
}
