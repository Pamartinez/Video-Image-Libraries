package com.google.android.material.textfield;

import A2.e;
import A4.C0372g;
import Ad.j;
import B2.A;
import B2.B;
import B2.C;
import B2.E;
import B2.f;
import B2.g;
import B2.l;
import B2.n;
import B2.p;
import B2.s;
import B2.t;
import B2.w;
import B2.y;
import B2.z;
import R1.a;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.ViewTreeObserver;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$style;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.DrawableUtils;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.text.BidiFormatter;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TextViewCompat;
import androidx.customview.view.AbsSavedState;
import androidx.transition.Fade;
import androidx.transition.TransitionManager;
import c0.C0086a;
import com.google.android.material.internal.CheckableImageButton;
import com.samsung.android.imagetranslation.common.LttEngineErrors;
import com.sec.android.gallery3d.R;
import h2.C0208c;
import h2.C0209d;
import h2.u;
import java.util.Iterator;
import java.util.LinkedHashSet;
import o1.C0246a;
import og.k;
import x2.C0334a;
import x2.C0336c;
import x2.C0339f;
import x2.C0340g;
import x2.C0343j;
import x2.C0344k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TextInputLayout extends LinearLayout implements ViewTreeObserver.OnGlobalLayoutListener {

    /* renamed from: F0  reason: collision with root package name */
    public static final int[][] f1508F0 = {new int[]{16842919}, new int[0]};

    /* renamed from: A  reason: collision with root package name */
    public Fade f1509A;

    /* renamed from: A0  reason: collision with root package name */
    public boolean f1510A0;
    public ColorStateList B;

    /* renamed from: B0  reason: collision with root package name */
    public ValueAnimator f1511B0;

    /* renamed from: C  reason: collision with root package name */
    public ColorStateList f1512C;

    /* renamed from: C0  reason: collision with root package name */
    public boolean f1513C0;
    public ColorStateList D;

    /* renamed from: D0  reason: collision with root package name */
    public boolean f1514D0;
    public ColorStateList E;

    /* renamed from: E0  reason: collision with root package name */
    public boolean f1515E0;

    /* renamed from: F  reason: collision with root package name */
    public boolean f1516F;

    /* renamed from: G  reason: collision with root package name */
    public CharSequence f1517G;

    /* renamed from: H  reason: collision with root package name */
    public boolean f1518H;

    /* renamed from: I  reason: collision with root package name */
    public C0340g f1519I;

    /* renamed from: J  reason: collision with root package name */
    public C0340g f1520J;

    /* renamed from: K  reason: collision with root package name */
    public StateListDrawable f1521K;
    public boolean L;

    /* renamed from: M  reason: collision with root package name */
    public C0340g f1522M;

    /* renamed from: N  reason: collision with root package name */
    public C0340g f1523N;

    /* renamed from: O  reason: collision with root package name */
    public C0344k f1524O;

    /* renamed from: P  reason: collision with root package name */
    public boolean f1525P;
    public final int Q;
    public int R;
    public int S;
    public int T;
    public int U;
    public int V;

    /* renamed from: W  reason: collision with root package name */
    public int f1526W;
    public int a0;
    public final Rect b0 = new Rect();

    /* renamed from: c0  reason: collision with root package name */
    public final Rect f1527c0 = new Rect();
    public final FrameLayout d;
    public final RectF d0 = new RectF();
    public final y e;

    /* renamed from: e0  reason: collision with root package name */
    public Typeface f1528e0;
    public final p f;

    /* renamed from: f0  reason: collision with root package name */
    public ColorDrawable f1529f0;
    public EditText g;

    /* renamed from: g0  reason: collision with root package name */
    public int f1530g0;

    /* renamed from: h  reason: collision with root package name */
    public CharSequence f1531h;
    public final LinkedHashSet h0 = new LinkedHashSet();

    /* renamed from: i  reason: collision with root package name */
    public int f1532i = -1;

    /* renamed from: i0  reason: collision with root package name */
    public ColorDrawable f1533i0;

    /* renamed from: j  reason: collision with root package name */
    public int f1534j = -1;

    /* renamed from: j0  reason: collision with root package name */
    public int f1535j0;
    public int k = -1;
    public Drawable k0;
    public int l = -1;
    public ColorStateList l0;
    public final t m = new t(this);

    /* renamed from: m0  reason: collision with root package name */
    public ColorStateList f1536m0;
    public boolean n;

    /* renamed from: n0  reason: collision with root package name */
    public int f1537n0;

    /* renamed from: o  reason: collision with root package name */
    public int f1538o;
    public int o0;

    /* renamed from: p  reason: collision with root package name */
    public boolean f1539p;

    /* renamed from: p0  reason: collision with root package name */
    public int f1540p0;
    public C q = new j(1);
    public ColorStateList q0;
    public AppCompatTextView r;
    public int r0;
    public int s;
    public int s0;
    public int t;

    /* renamed from: t0  reason: collision with root package name */
    public int f1541t0;
    public CharSequence u;

    /* renamed from: u0  reason: collision with root package name */
    public int f1542u0;
    public boolean v;

    /* renamed from: v0  reason: collision with root package name */
    public int f1543v0;
    public AppCompatTextView w;

    /* renamed from: w0  reason: collision with root package name */
    public int f1544w0;

    /* renamed from: x  reason: collision with root package name */
    public ColorStateList f1545x;

    /* renamed from: x0  reason: collision with root package name */
    public boolean f1546x0;
    public int y;
    public final C0208c y0;
    public Fade z;

    /* renamed from: z0  reason: collision with root package name */
    public boolean f1547z0;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TextInputLayout(android.content.Context r20, android.util.AttributeSet r21) {
        /*
            r19 = this;
            r0 = r19
            r2 = r21
            r4 = 2130969966(0x7f04056e, float:1.7548629E38)
            r7 = 2131952963(0x7f130543, float:1.9542384E38)
            r1 = r20
            android.content.Context r1 = D2.a.a(r1, r2, r4, r7)
            r0.<init>(r1, r2, r4)
            r8 = -1
            r0.f1532i = r8
            r0.f1534j = r8
            r0.k = r8
            r0.l = r8
            B2.t r1 = new B2.t
            r1.<init>(r0)
            r0.m = r1
            Ad.j r1 = new Ad.j
            r3 = 1
            r1.<init>(r3)
            r0.q = r1
            android.graphics.Rect r1 = new android.graphics.Rect
            r1.<init>()
            r0.b0 = r1
            android.graphics.Rect r1 = new android.graphics.Rect
            r1.<init>()
            r0.f1527c0 = r1
            android.graphics.RectF r1 = new android.graphics.RectF
            r1.<init>()
            r0.d0 = r1
            java.util.LinkedHashSet r1 = new java.util.LinkedHashSet
            r1.<init>()
            r0.h0 = r1
            h2.c r1 = new h2.c
            r1.<init>(r0)
            r0.y0 = r1
            r9 = 0
            r0.f1515E0 = r9
            android.content.Context r3 = r0.getContext()
            r10 = 1
            r0.setOrientation(r10)
            r0.setWillNotDraw(r9)
            r0.setAddStatesFromChildren(r10)
            android.widget.FrameLayout r11 = new android.widget.FrameLayout
            r11.<init>(r3)
            r0.d = r11
            r11.setAddStatesFromChildren(r10)
            android.view.animation.LinearInterpolator r5 = R1.a.f640a
            r1.U = r5
            r1.i(r9)
            r1.T = r5
            r1.i(r9)
            r5 = 8388659(0x800033, float:1.1755015E-38)
            r1.l(r5)
            r12 = 22
            r13 = 20
            r14 = 40
            r15 = 45
            r1 = 49
            int[] r6 = new int[]{r12, r13, r14, r15, r1}
            r5 = 2131952963(0x7f130543, float:1.9542384E38)
            h2.p.a(r3, r2, r4, r5)
            r16 = r1
            r1 = r3
            int[] r3 = Q1.a.f624O
            r13 = r16
            h2.p.b(r1, r2, r3, r4, r5, r6)
            androidx.appcompat.widget.TintTypedArray r3 = androidx.appcompat.widget.TintTypedArray.obtainStyledAttributes(r1, r2, r3, r4, r5)
            B2.y r5 = new B2.y
            r5.<init>(r0, r3)
            r0.e = r5
            r6 = 48
            boolean r6 = r3.getBoolean(r6, r10)
            r0.f1516F = r6
            r6 = 4
            java.lang.CharSequence r6 = r3.getText(r6)
            r0.setHint((java.lang.CharSequence) r6)
            r6 = 47
            boolean r6 = r3.getBoolean(r6, r10)
            r0.f1510A0 = r6
            r6 = 42
            boolean r6 = r3.getBoolean(r6, r10)
            r0.f1547z0 = r6
            r6 = 6
            boolean r16 = r3.hasValue(r6)
            if (r16 == 0) goto L_0x00d3
            int r6 = r3.getInt(r6, r8)
            r0.setMinEms(r6)
            goto L_0x00e1
        L_0x00d3:
            r6 = 3
            boolean r16 = r3.hasValue(r6)
            if (r16 == 0) goto L_0x00e1
            int r6 = r3.getDimensionPixelSize(r6, r8)
            r0.setMinWidth(r6)
        L_0x00e1:
            r6 = 5
            boolean r16 = r3.hasValue(r6)
            r12 = 2
            if (r16 == 0) goto L_0x00f1
            int r6 = r3.getInt(r6, r8)
            r0.setMaxEms(r6)
            goto L_0x00fe
        L_0x00f1:
            boolean r6 = r3.hasValue(r12)
            if (r6 == 0) goto L_0x00fe
            int r6 = r3.getDimensionPixelSize(r12, r8)
            r0.setMaxWidth(r6)
        L_0x00fe:
            x2.j r2 = x2.C0344k.b(r1, r2, r4, r7)
            x2.k r2 = r2.a()
            r0.f1524O = r2
            android.content.res.Resources r2 = r1.getResources()
            r4 = 2131166964(0x7f0706f4, float:1.7948188E38)
            int r2 = r2.getDimensionPixelOffset(r4)
            r0.Q = r2
            r2 = 9
            int r2 = r3.getDimensionPixelOffset(r2, r9)
            r0.S = r2
            android.content.res.Resources r2 = r1.getResources()
            r4 = 2131166965(0x7f0706f5, float:1.794819E38)
            int r2 = r2.getDimensionPixelSize(r4)
            r4 = 16
            int r2 = r3.getDimensionPixelSize(r4, r2)
            r0.U = r2
            android.content.res.Resources r2 = r1.getResources()
            r4 = 2131166966(0x7f0706f6, float:1.7948192E38)
            int r2 = r2.getDimensionPixelSize(r4)
            r4 = 17
            int r2 = r3.getDimensionPixelSize(r4, r2)
            r0.V = r2
            int r2 = r0.U
            r0.T = r2
            r2 = 13
            r4 = -1082130432(0xffffffffbf800000, float:-1.0)
            float r2 = r3.getDimension(r2, r4)
            r6 = 12
            float r6 = r3.getDimension(r6, r4)
            r7 = 10
            float r7 = r3.getDimension(r7, r4)
            r12 = 11
            float r4 = r3.getDimension(r12, r4)
            x2.k r12 = r0.f1524O
            x2.j r12 = r12.e()
            r17 = 0
            int r18 = (r2 > r17 ? 1 : (r2 == r17 ? 0 : -1))
            if (r18 < 0) goto L_0x0174
            x2.a r15 = new x2.a
            r15.<init>(r2)
            r12.e = r15
        L_0x0174:
            int r2 = (r6 > r17 ? 1 : (r6 == r17 ? 0 : -1))
            if (r2 < 0) goto L_0x017f
            x2.a r2 = new x2.a
            r2.<init>(r6)
            r12.f = r2
        L_0x017f:
            int r2 = (r7 > r17 ? 1 : (r7 == r17 ? 0 : -1))
            if (r2 < 0) goto L_0x018a
            x2.a r2 = new x2.a
            r2.<init>(r7)
            r12.g = r2
        L_0x018a:
            int r2 = (r4 > r17 ? 1 : (r4 == r17 ? 0 : -1))
            if (r2 < 0) goto L_0x0195
            x2.a r2 = new x2.a
            r2.<init>(r4)
            r12.f2119h = r2
        L_0x0195:
            x2.k r2 = r12.a()
            r0.f1524O = r2
            r2 = 7
            android.content.res.ColorStateList r2 = B1.a.v(r1, r3, r2)
            if (r2 == 0) goto L_0x01fb
            int r4 = r2.getDefaultColor()
            r0.r0 = r4
            r0.a0 = r4
            boolean r4 = r2.isStateful()
            r6 = 16843623(0x1010367, float:2.3696E-38)
            r7 = -16842910(0xfffffffffefeff62, float:-1.6947497E38)
            if (r4 == 0) goto L_0x01db
            int[] r4 = new int[]{r7}
            int r4 = r2.getColorForState(r4, r8)
            r0.s0 = r4
            r4 = 16842908(0x101009c, float:2.3693995E-38)
            r7 = 16842910(0x101009e, float:2.3694E-38)
            int[] r4 = new int[]{r4, r7}
            int r4 = r2.getColorForState(r4, r8)
            r0.f1541t0 = r4
            int[] r4 = new int[]{r6, r7}
            int r2 = r2.getColorForState(r4, r8)
            r0.f1542u0 = r2
            goto L_0x0205
        L_0x01db:
            int r2 = r0.r0
            r0.f1541t0 = r2
            r2 = 2131101071(0x7f06058f, float:1.7814541E38)
            android.content.res.ColorStateList r2 = androidx.appcompat.content.res.AppCompatResources.getColorStateList(r1, r2)
            int[] r4 = new int[]{r7}
            int r4 = r2.getColorForState(r4, r8)
            r0.s0 = r4
            int[] r4 = new int[]{r6}
            int r2 = r2.getColorForState(r4, r8)
            r0.f1542u0 = r2
            goto L_0x0205
        L_0x01fb:
            r0.a0 = r9
            r0.r0 = r9
            r0.s0 = r9
            r0.f1541t0 = r9
            r0.f1542u0 = r9
        L_0x0205:
            boolean r2 = r3.hasValue(r10)
            if (r2 == 0) goto L_0x0213
            android.content.res.ColorStateList r2 = r3.getColorStateList(r10)
            r0.f1536m0 = r2
            r0.l0 = r2
        L_0x0213:
            r2 = 14
            android.content.res.ColorStateList r4 = B1.a.v(r1, r3, r2)
            int r2 = r3.getColor(r2, r9)
            r0.f1540p0 = r2
            r2 = 2131101093(0x7f0605a5, float:1.7814586E38)
            int r2 = androidx.core.content.ContextCompat.getColor(r1, r2)
            r0.f1537n0 = r2
            r2 = 2131101094(0x7f0605a6, float:1.7814588E38)
            int r2 = androidx.core.content.ContextCompat.getColor(r1, r2)
            r0.f1543v0 = r2
            r2 = 2131101096(0x7f0605a8, float:1.7814592E38)
            int r2 = androidx.core.content.ContextCompat.getColor(r1, r2)
            r0.o0 = r2
            if (r4 == 0) goto L_0x023f
            r0.setBoxStrokeColorStateList(r4)
        L_0x023f:
            r2 = 15
            boolean r4 = r3.hasValue(r2)
            if (r4 == 0) goto L_0x024e
            android.content.res.ColorStateList r1 = B1.a.v(r1, r3, r2)
            r0.setBoxStrokeErrorColor(r1)
        L_0x024e:
            int r1 = r3.getResourceId(r13, r8)
            if (r1 == r8) goto L_0x025b
            int r1 = r3.getResourceId(r13, r9)
            r0.setHintTextAppearance(r1)
        L_0x025b:
            r1 = 24
            android.content.res.ColorStateList r1 = r3.getColorStateList(r1)
            r0.D = r1
            r1 = 25
            android.content.res.ColorStateList r1 = r3.getColorStateList(r1)
            r0.E = r1
            int r1 = r3.getResourceId(r14, r9)
            r2 = 35
            java.lang.CharSequence r2 = r3.getText(r2)
            r4 = 34
            int r4 = r3.getInt(r4, r10)
            r6 = 36
            boolean r6 = r3.getBoolean(r6, r9)
            r7 = 45
            int r7 = r3.getResourceId(r7, r9)
            r12 = 44
            boolean r12 = r3.getBoolean(r12, r9)
            r13 = 43
            java.lang.CharSequence r13 = r3.getText(r13)
            r14 = 57
            int r14 = r3.getResourceId(r14, r9)
            r15 = 56
            java.lang.CharSequence r15 = r3.getText(r15)
            r10 = 18
            boolean r10 = r3.getBoolean(r10, r9)
            r9 = 19
            int r8 = r3.getInt(r9, r8)
            r0.setCounterMaxLength(r8)
            r8 = 0
            r9 = 22
            int r9 = r3.getResourceId(r9, r8)
            r0.t = r9
            r9 = 20
            int r9 = r3.getResourceId(r9, r8)
            r0.s = r9
            r9 = 8
            int r9 = r3.getInt(r9, r8)
            r0.setBoxBackgroundMode(r9)
            r0.setErrorContentDescription(r2)
            r0.setErrorAccessibilityLiveRegion(r4)
            int r2 = r0.s
            r0.setCounterOverflowTextAppearance(r2)
            r0.setHelperTextTextAppearance(r7)
            r0.setErrorTextAppearance(r1)
            int r1 = r0.t
            r0.setCounterTextAppearance(r1)
            r0.setPlaceholderText(r15)
            r0.setPlaceholderTextAppearance(r14)
            r1 = 41
            boolean r2 = r3.hasValue(r1)
            if (r2 == 0) goto L_0x02f3
            android.content.res.ColorStateList r1 = r3.getColorStateList(r1)
            r0.setErrorTextColor(r1)
        L_0x02f3:
            r1 = 46
            boolean r2 = r3.hasValue(r1)
            if (r2 == 0) goto L_0x0302
            android.content.res.ColorStateList r1 = r3.getColorStateList(r1)
            r0.setHelperTextColor(r1)
        L_0x0302:
            r1 = 50
            boolean r2 = r3.hasValue(r1)
            if (r2 == 0) goto L_0x0311
            android.content.res.ColorStateList r1 = r3.getColorStateList(r1)
            r0.setHintTextColor(r1)
        L_0x0311:
            r1 = 23
            boolean r2 = r3.hasValue(r1)
            if (r2 == 0) goto L_0x0320
            android.content.res.ColorStateList r1 = r3.getColorStateList(r1)
            r0.setCounterTextColor(r1)
        L_0x0320:
            r1 = 21
            boolean r2 = r3.hasValue(r1)
            if (r2 == 0) goto L_0x032f
            android.content.res.ColorStateList r1 = r3.getColorStateList(r1)
            r0.setCounterOverflowTextColor(r1)
        L_0x032f:
            r1 = 58
            boolean r2 = r3.hasValue(r1)
            if (r2 == 0) goto L_0x033e
            android.content.res.ColorStateList r1 = r3.getColorStateList(r1)
            r0.setPlaceholderTextColor(r1)
        L_0x033e:
            B2.p r1 = new B2.p
            r1.<init>(r0, r3)
            r0.f = r1
            r2 = 1
            r8 = 0
            boolean r4 = r3.getBoolean(r8, r2)
            r3.recycle()
            r3 = 2
            androidx.core.view.ViewCompat.setImportantForAccessibility(r0, r3)
            androidx.core.view.ViewCompat.setImportantForAutofill(r0, r2)
            r11.addView(r5)
            r11.addView(r1)
            r0.addView(r11)
            r0.setEnabled(r4)
            r0.setHelperTextEnabled(r12)
            r0.setErrorEnabled(r6)
            r0.setCounterEnabled(r10)
            r0.setHelperText(r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    private Drawable getEditTextBoxBackground() {
        int i2;
        EditText editText = this.g;
        if (!(editText instanceof AutoCompleteTextView) || editText.getInputType() != 0) {
            return this.f1519I;
        }
        int W6 = C0246a.W(R$attr.colorControlHighlight, this.g);
        int i7 = this.R;
        int[][] iArr = f1508F0;
        if (i7 == 2) {
            Context context = getContext();
            C0340g gVar = this.f1519I;
            TypedValue N6 = k.N(context, R.attr.colorSurface, "TextInputLayout");
            int i8 = N6.resourceId;
            if (i8 != 0) {
                i2 = ContextCompat.getColor(context, i8);
            } else {
                i2 = N6.data;
            }
            C0340g gVar2 = new C0340g(gVar.d.f2103a);
            int c02 = C0246a.c0(0.1f, W6, i2);
            gVar2.k(new ColorStateList(iArr, new int[]{c02, 0}));
            gVar2.setTint(i2);
            ColorStateList colorStateList = new ColorStateList(iArr, new int[]{c02, i2});
            C0340g gVar3 = new C0340g(gVar.d.f2103a);
            gVar3.setTint(-1);
            return new LayerDrawable(new Drawable[]{new RippleDrawable(colorStateList, gVar2, gVar3), gVar});
        } else if (i7 != 1) {
            return null;
        } else {
            C0340g gVar4 = this.f1519I;
            int i10 = this.a0;
            return new RippleDrawable(new ColorStateList(iArr, new int[]{C0246a.c0(0.1f, W6, i10), i10}), gVar4, gVar4);
        }
    }

    private Drawable getOrCreateFilledDropDownMenuBackground() {
        if (this.f1521K == null) {
            StateListDrawable stateListDrawable = new StateListDrawable();
            this.f1521K = stateListDrawable;
            stateListDrawable.addState(new int[]{16842922}, getOrCreateOutlinedDropDownMenuBackground());
            this.f1521K.addState(new int[0], f(false));
        }
        return this.f1521K;
    }

    private Drawable getOrCreateOutlinedDropDownMenuBackground() {
        if (this.f1520J == null) {
            this.f1520J = f(true);
        }
        return this.f1520J;
    }

    public static void k(ViewGroup viewGroup, boolean z3) {
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            childAt.setEnabled(z3);
            if (childAt instanceof ViewGroup) {
                k((ViewGroup) childAt, z3);
            }
        }
    }

    private void setEditText(EditText editText) {
        if (this.g == null) {
            if (getEndIconMode() != 3 && !(editText instanceof TextInputEditText)) {
                Log.i("TextInputLayout", "EditText added is not a TextInputEditText. Please switch to using that class instead.");
            }
            this.g = editText;
            int i2 = this.f1532i;
            if (i2 != -1) {
                setMinEms(i2);
            } else {
                setMinWidth(this.k);
            }
            int i7 = this.f1534j;
            if (i7 != -1) {
                setMaxEms(i7);
            } else {
                setMaxWidth(this.l);
            }
            this.L = false;
            i();
            setTextInputAccessibilityDelegate(new B(this));
            Typeface typeface = this.g.getTypeface();
            C0208c cVar = this.y0;
            boolean m4 = cVar.m(typeface);
            boolean o2 = cVar.o(typeface);
            if (m4 || o2) {
                cVar.i(false);
            }
            float textSize = this.g.getTextSize();
            if (cVar.f1759j != textSize) {
                cVar.f1759j = textSize;
                cVar.i(false);
            }
            float letterSpacing = this.g.getLetterSpacing();
            if (cVar.f1753e0 != letterSpacing) {
                cVar.f1753e0 = letterSpacing;
                cVar.i(false);
            }
            int gravity = this.g.getGravity();
            cVar.l((gravity & LttEngineErrors.ERROR_INPAINTING_OCR_BLOCK_TABULAR) | 48);
            if (cVar.f1756h != gravity) {
                cVar.f1756h = gravity;
                cVar.i(false);
            }
            this.f1544w0 = ViewCompat.getMinimumHeight(editText);
            this.g.addTextChangedListener(new z(this, editText));
            if (this.l0 == null) {
                this.l0 = this.g.getHintTextColors();
            }
            if (this.f1516F) {
                if (TextUtils.isEmpty(this.f1517G)) {
                    CharSequence hint = this.g.getHint();
                    this.f1531h = hint;
                    setHint(hint);
                    this.g.setHint((CharSequence) null);
                }
                this.f1518H = true;
            }
            p();
            if (this.r != null) {
                n(this.g.getText());
            }
            r();
            this.m.b();
            this.e.bringToFront();
            p pVar = this.f;
            pVar.bringToFront();
            Iterator it = this.h0.iterator();
            while (it.hasNext()) {
                ((n) it.next()).a(this);
            }
            pVar.m();
            if (!isEnabled()) {
                editText.setEnabled(false);
            }
            u(false, true);
            return;
        }
        throw new IllegalArgumentException("We already have an EditText, can only have one");
    }

    private void setHintInternal(CharSequence charSequence) {
        if (!TextUtils.equals(charSequence, this.f1517G)) {
            this.f1517G = charSequence;
            C0208c cVar = this.y0;
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
            if (!this.f1546x0) {
                j();
            }
        }
    }

    private void setPlaceholderTextEnabled(boolean z3) {
        if (this.v != z3) {
            if (z3) {
                AppCompatTextView appCompatTextView = this.w;
                if (appCompatTextView != null) {
                    this.d.addView(appCompatTextView);
                    this.w.setVisibility(0);
                }
            } else {
                AppCompatTextView appCompatTextView2 = this.w;
                if (appCompatTextView2 != null) {
                    appCompatTextView2.setVisibility(8);
                }
                this.w = null;
            }
            this.v = z3;
        }
    }

    public final void a(float f5) {
        C0208c cVar = this.y0;
        if (cVar.b != f5) {
            if (this.f1511B0 == null) {
                ValueAnimator valueAnimator = new ValueAnimator();
                this.f1511B0 = valueAnimator;
                valueAnimator.setInterpolator(k.M(getContext(), R.attr.motionEasingEmphasizedInterpolator, a.b));
                this.f1511B0.setDuration((long) k.L(getContext(), R.attr.motionDurationMedium4, 167));
                this.f1511B0.addUpdateListener(new e(1, this));
            }
            this.f1511B0.setFloatValues(new float[]{cVar.b, f5});
            this.f1511B0.start();
        }
    }

    public final void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (view instanceof EditText) {
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
            layoutParams2.gravity = (layoutParams2.gravity & LttEngineErrors.ERROR_INPAINTING_OCR_BLOCK_TABULAR) | 16;
            FrameLayout frameLayout = this.d;
            frameLayout.addView(view, layoutParams2);
            frameLayout.setLayoutParams(layoutParams);
            t();
            setEditText((EditText) view);
            return;
        }
        super.addView(view, i2, layoutParams);
    }

    public final void b() {
        ColorStateList colorStateList;
        Integer num;
        int i2;
        int i7;
        int i8;
        int i10;
        C0340g gVar = this.f1519I;
        if (gVar != null) {
            C0344k kVar = gVar.d.f2103a;
            C0344k kVar2 = this.f1524O;
            if (kVar != kVar2) {
                gVar.setShapeAppearanceModel(kVar2);
            }
            if (this.R == 2 && (i8 = this.T) > -1 && (i10 = this.f1526W) != 0) {
                C0340g gVar2 = this.f1519I;
                gVar2.d.f2107j = (float) i8;
                gVar2.invalidateSelf();
                ColorStateList valueOf = ColorStateList.valueOf(i10);
                C0339f fVar = gVar2.d;
                if (fVar.d != valueOf) {
                    fVar.d = valueOf;
                    gVar2.onStateChange(gVar2.getState());
                }
            }
            int i11 = this.a0;
            if (this.R == 1) {
                Context context = getContext();
                TypedValue J4 = k.J(context, R.attr.colorSurface);
                if (J4 != null) {
                    int i12 = J4.resourceId;
                    if (i12 != 0) {
                        i7 = ContextCompat.getColor(context, i12);
                    } else {
                        i7 = J4.data;
                    }
                    num = Integer.valueOf(i7);
                } else {
                    num = null;
                }
                if (num != null) {
                    i2 = num.intValue();
                } else {
                    i2 = 0;
                }
                i11 = ColorUtils.compositeColors(this.a0, i2);
            }
            this.a0 = i11;
            this.f1519I.k(ColorStateList.valueOf(i11));
            C0340g gVar3 = this.f1522M;
            if (!(gVar3 == null || this.f1523N == null)) {
                if (this.T > -1 && this.f1526W != 0) {
                    if (this.g.isFocused()) {
                        colorStateList = ColorStateList.valueOf(this.f1537n0);
                    } else {
                        colorStateList = ColorStateList.valueOf(this.f1526W);
                    }
                    gVar3.k(colorStateList);
                    this.f1523N.k(ColorStateList.valueOf(this.f1526W));
                }
                invalidate();
            }
            s();
        }
    }

    public final int c() {
        float e7;
        if (!this.f1516F) {
            return 0;
        }
        int i2 = this.R;
        C0208c cVar = this.y0;
        if (i2 == 0) {
            e7 = cVar.e();
        } else if (i2 != 2) {
            return 0;
        } else {
            e7 = cVar.e() / 2.0f;
        }
        return (int) e7;
    }

    public final Fade d() {
        Fade fade = new Fade();
        fade.setDuration((long) k.L(getContext(), R.attr.motionDurationShort2, 87));
        fade.setInterpolator(k.M(getContext(), R.attr.motionEasingLinearInterpolator, a.f640a));
        return fade;
    }

    public final void dispatchProvideAutofillStructure(ViewStructure viewStructure, int i2) {
        EditText editText = this.g;
        if (editText == null) {
            super.dispatchProvideAutofillStructure(viewStructure, i2);
            return;
        }
        if (this.f1531h != null) {
            boolean z3 = this.f1518H;
            this.f1518H = false;
            CharSequence hint = editText.getHint();
            this.g.setHint(this.f1531h);
            try {
                super.dispatchProvideAutofillStructure(viewStructure, i2);
            } finally {
                this.g.setHint(hint);
                this.f1518H = z3;
            }
        } else {
            viewStructure.setAutofillId(getAutofillId());
            onProvideAutofillStructure(viewStructure, i2);
            onProvideAutofillVirtualStructure(viewStructure, i2);
            FrameLayout frameLayout = this.d;
            viewStructure.setChildCount(frameLayout.getChildCount());
            for (int i7 = 0; i7 < frameLayout.getChildCount(); i7++) {
                View childAt = frameLayout.getChildAt(i7);
                ViewStructure newChild = viewStructure.newChild(i7);
                childAt.dispatchProvideAutofillStructure(newChild, i2);
                if (childAt == this.g) {
                    newChild.setHint(getHint());
                }
            }
        }
    }

    public final void dispatchRestoreInstanceState(SparseArray sparseArray) {
        this.f1514D0 = true;
        super.dispatchRestoreInstanceState(sparseArray);
        this.f1514D0 = false;
    }

    public final void draw(Canvas canvas) {
        C0340g gVar;
        super.draw(canvas);
        boolean z3 = this.f1516F;
        C0208c cVar = this.y0;
        if (z3) {
            cVar.d(canvas);
        }
        if (this.f1523N != null && (gVar = this.f1522M) != null) {
            gVar.draw(canvas);
            if (this.g.isFocused()) {
                Rect bounds = this.f1523N.getBounds();
                Rect bounds2 = this.f1522M.getBounds();
                float f5 = cVar.b;
                int centerX = bounds2.centerX();
                bounds.left = a.c(f5, centerX, bounds2.left);
                bounds.right = a.c(f5, centerX, bounds2.right);
                this.f1523N.draw(canvas);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void drawableStateChanged() {
        /*
            r4 = this;
            boolean r0 = r4.f1513C0
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            r0 = 1
            r4.f1513C0 = r0
            super.drawableStateChanged()
            int[] r1 = r4.getDrawableState()
            r2 = 0
            h2.c r3 = r4.y0
            if (r3 == 0) goto L_0x002f
            r3.f1747P = r1
            android.content.res.ColorStateList r1 = r3.m
            if (r1 == 0) goto L_0x0020
            boolean r1 = r1.isStateful()
            if (r1 != 0) goto L_0x002a
        L_0x0020:
            android.content.res.ColorStateList r1 = r3.l
            if (r1 == 0) goto L_0x002f
            boolean r1 = r1.isStateful()
            if (r1 == 0) goto L_0x002f
        L_0x002a:
            r3.i(r2)
            r1 = r0
            goto L_0x0030
        L_0x002f:
            r1 = r2
        L_0x0030:
            android.widget.EditText r3 = r4.g
            if (r3 == 0) goto L_0x0045
            boolean r3 = androidx.core.view.ViewCompat.isLaidOut(r4)
            if (r3 == 0) goto L_0x0041
            boolean r3 = r4.isEnabled()
            if (r3 == 0) goto L_0x0041
            goto L_0x0042
        L_0x0041:
            r0 = r2
        L_0x0042:
            r4.u(r0, r2)
        L_0x0045:
            r4.r()
            r4.x()
            if (r1 == 0) goto L_0x0050
            r4.invalidate()
        L_0x0050:
            r4.f1513C0 = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.drawableStateChanged():void");
    }

    public final boolean e() {
        if (!this.f1516F || TextUtils.isEmpty(this.f1517G) || !(this.f1519I instanceof g)) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: type inference failed for: r3v1, types: [o1.a, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r4v0, types: [o1.a, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r5v0, types: [o1.a, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r6v0, types: [o1.a, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r7v0, types: [x2.e, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r8v0, types: [x2.e, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r9v0, types: [x2.e, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r10v0, types: [x2.e, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r0v3, types: [x2.k, java.lang.Object] */
    public final C0340g f(boolean z3) {
        float f5;
        float f8;
        ColorStateList colorStateList;
        int i2;
        float dimensionPixelOffset = (float) getResources().getDimensionPixelOffset(R.dimen.mtrl_shape_corner_size_small_component);
        if (z3) {
            f5 = dimensionPixelOffset;
        } else {
            f5 = 0.0f;
        }
        EditText editText = this.g;
        if (editText instanceof w) {
            f8 = ((w) editText).getPopupElevation();
        } else {
            f8 = (float) getResources().getDimensionPixelOffset(R.dimen.m3_comp_outlined_autocomplete_menu_container_elevation);
        }
        int dimensionPixelOffset2 = getResources().getDimensionPixelOffset(R.dimen.mtrl_exposed_dropdown_menu_popup_vertical_padding);
        ? obj = new Object();
        ? obj2 = new Object();
        ? obj3 = new Object();
        ? obj4 = new Object();
        ? obj5 = new Object();
        ? obj6 = new Object();
        ? obj7 = new Object();
        ? obj8 = new Object();
        C0334a aVar = new C0334a(f5);
        C0334a aVar2 = new C0334a(f5);
        C0334a aVar3 = new C0334a(dimensionPixelOffset);
        C0334a aVar4 = new C0334a(dimensionPixelOffset);
        ? obj9 = new Object();
        obj9.f2122a = obj;
        obj9.b = obj2;
        obj9.f2123c = obj3;
        obj9.d = obj4;
        obj9.e = aVar;
        obj9.f = aVar2;
        obj9.g = aVar4;
        obj9.f2124h = aVar3;
        obj9.f2125i = obj5;
        obj9.f2126j = obj6;
        obj9.k = obj7;
        obj9.l = obj8;
        EditText editText2 = this.g;
        if (editText2 instanceof w) {
            colorStateList = ((w) editText2).getDropDownBackgroundTintList();
        } else {
            colorStateList = null;
        }
        Context context = getContext();
        if (colorStateList == null) {
            int i7 = C0340g.y;
            TypedValue N6 = k.N(context, R.attr.colorSurface, C0340g.class.getSimpleName());
            int i8 = N6.resourceId;
            if (i8 != 0) {
                i2 = ContextCompat.getColor(context, i8);
            } else {
                i2 = N6.data;
            }
            colorStateList = ColorStateList.valueOf(i2);
        }
        C0340g gVar = new C0340g();
        gVar.i(context);
        gVar.k(colorStateList);
        gVar.j(f8);
        gVar.setShapeAppearanceModel(obj9);
        C0339f fVar = gVar.d;
        if (fVar.g == null) {
            fVar.g = new Rect();
        }
        gVar.d.g.set(0, dimensionPixelOffset2, 0, dimensionPixelOffset2);
        gVar.invalidateSelf();
        return gVar;
    }

    public final int g(int i2, boolean z3) {
        int compoundPaddingLeft;
        if (!z3 && getPrefixText() != null) {
            compoundPaddingLeft = this.e.a();
        } else if (!z3 || getSuffixText() == null) {
            compoundPaddingLeft = this.g.getCompoundPaddingLeft();
        } else {
            compoundPaddingLeft = this.f.c();
        }
        return compoundPaddingLeft + i2;
    }

    public int getBaseline() {
        EditText editText = this.g;
        if (editText == null) {
            return super.getBaseline();
        }
        int baseline = editText.getBaseline();
        return c() + getPaddingTop() + baseline;
    }

    public C0340g getBoxBackground() {
        int i2 = this.R;
        if (i2 == 1 || i2 == 2) {
            return this.f1519I;
        }
        throw new IllegalStateException();
    }

    public int getBoxBackgroundColor() {
        return this.a0;
    }

    public int getBoxBackgroundMode() {
        return this.R;
    }

    public int getBoxCollapsedPaddingTop() {
        return this.S;
    }

    public float getBoxCornerRadiusBottomEnd() {
        boolean c5 = u.c(this);
        RectF rectF = this.d0;
        if (c5) {
            return this.f1524O.f2124h.a(rectF);
        }
        return this.f1524O.g.a(rectF);
    }

    public float getBoxCornerRadiusBottomStart() {
        boolean c5 = u.c(this);
        RectF rectF = this.d0;
        if (c5) {
            return this.f1524O.g.a(rectF);
        }
        return this.f1524O.f2124h.a(rectF);
    }

    public float getBoxCornerRadiusTopEnd() {
        boolean c5 = u.c(this);
        RectF rectF = this.d0;
        if (c5) {
            return this.f1524O.e.a(rectF);
        }
        return this.f1524O.f.a(rectF);
    }

    public float getBoxCornerRadiusTopStart() {
        boolean c5 = u.c(this);
        RectF rectF = this.d0;
        if (c5) {
            return this.f1524O.f.a(rectF);
        }
        return this.f1524O.e.a(rectF);
    }

    public int getBoxStrokeColor() {
        return this.f1540p0;
    }

    public ColorStateList getBoxStrokeErrorColor() {
        return this.q0;
    }

    public int getBoxStrokeWidth() {
        return this.U;
    }

    public int getBoxStrokeWidthFocused() {
        return this.V;
    }

    public int getCounterMaxLength() {
        return this.f1538o;
    }

    public CharSequence getCounterOverflowDescription() {
        AppCompatTextView appCompatTextView;
        if (!this.n || !this.f1539p || (appCompatTextView = this.r) == null) {
            return null;
        }
        return appCompatTextView.getContentDescription();
    }

    public ColorStateList getCounterOverflowTextColor() {
        return this.f1512C;
    }

    public ColorStateList getCounterTextColor() {
        return this.B;
    }

    public ColorStateList getCursorColor() {
        return this.D;
    }

    public ColorStateList getCursorErrorColor() {
        return this.E;
    }

    public ColorStateList getDefaultHintTextColor() {
        return this.l0;
    }

    public EditText getEditText() {
        return this.g;
    }

    public CharSequence getEndIconContentDescription() {
        return this.f.f58j.getContentDescription();
    }

    public Drawable getEndIconDrawable() {
        return this.f.f58j.getDrawable();
    }

    public int getEndIconMinSize() {
        return this.f.f60p;
    }

    public int getEndIconMode() {
        return this.f.l;
    }

    public ImageView.ScaleType getEndIconScaleType() {
        return this.f.q;
    }

    public CheckableImageButton getEndIconView() {
        return this.f.f58j;
    }

    public CharSequence getError() {
        t tVar = this.m;
        if (tVar.q) {
            return tVar.f73p;
        }
        return null;
    }

    public int getErrorAccessibilityLiveRegion() {
        return this.m.t;
    }

    public CharSequence getErrorContentDescription() {
        return this.m.s;
    }

    public int getErrorCurrentTextColors() {
        AppCompatTextView appCompatTextView = this.m.r;
        if (appCompatTextView != null) {
            return appCompatTextView.getCurrentTextColor();
        }
        return -1;
    }

    public Drawable getErrorIconDrawable() {
        return this.f.f.getDrawable();
    }

    public CharSequence getHelperText() {
        t tVar = this.m;
        if (tVar.f74x) {
            return tVar.w;
        }
        return null;
    }

    public int getHelperTextCurrentTextColor() {
        AppCompatTextView appCompatTextView = this.m.y;
        if (appCompatTextView != null) {
            return appCompatTextView.getCurrentTextColor();
        }
        return -1;
    }

    public CharSequence getHint() {
        if (this.f1516F) {
            return this.f1517G;
        }
        return null;
    }

    public final float getHintCollapsedTextHeight() {
        return this.y0.e();
    }

    public final int getHintCurrentCollapsedTextColor() {
        C0208c cVar = this.y0;
        return cVar.f(cVar.m);
    }

    public ColorStateList getHintTextColor() {
        return this.f1536m0;
    }

    public C getLengthCounter() {
        return this.q;
    }

    public int getMaxEms() {
        return this.f1534j;
    }

    public int getMaxWidth() {
        return this.l;
    }

    public int getMinEms() {
        return this.f1532i;
    }

    public int getMinWidth() {
        return this.k;
    }

    @Deprecated
    public CharSequence getPasswordVisibilityToggleContentDescription() {
        return this.f.f58j.getContentDescription();
    }

    @Deprecated
    public Drawable getPasswordVisibilityToggleDrawable() {
        return this.f.f58j.getDrawable();
    }

    public CharSequence getPlaceholderText() {
        if (this.v) {
            return this.u;
        }
        return null;
    }

    public int getPlaceholderTextAppearance() {
        return this.y;
    }

    public ColorStateList getPlaceholderTextColor() {
        return this.f1545x;
    }

    public CharSequence getPrefixText() {
        return this.e.f;
    }

    public ColorStateList getPrefixTextColor() {
        return this.e.e.getTextColors();
    }

    public TextView getPrefixTextView() {
        return this.e.e;
    }

    public C0344k getShapeAppearanceModel() {
        return this.f1524O;
    }

    public CharSequence getStartIconContentDescription() {
        return this.e.g.getContentDescription();
    }

    public Drawable getStartIconDrawable() {
        return this.e.g.getDrawable();
    }

    public int getStartIconMinSize() {
        return this.e.f80j;
    }

    public ImageView.ScaleType getStartIconScaleType() {
        return this.e.k;
    }

    public CharSequence getSuffixText() {
        return this.f.s;
    }

    public ColorStateList getSuffixTextColor() {
        return this.f.t.getTextColors();
    }

    public TextView getSuffixTextView() {
        return this.f.t;
    }

    public Typeface getTypeface() {
        return this.f1528e0;
    }

    public final int h(int i2, boolean z3) {
        int compoundPaddingRight;
        if (!z3 && getSuffixText() != null) {
            compoundPaddingRight = this.f.c();
        } else if (!z3 || getPrefixText() == null) {
            compoundPaddingRight = this.g.getCompoundPaddingRight();
        } else {
            compoundPaddingRight = this.e.a();
        }
        return i2 - compoundPaddingRight;
    }

    /* JADX WARNING: type inference failed for: r0v37, types: [x2.g, B2.g] */
    public final void i() {
        int i2 = this.R;
        if (i2 == 0) {
            this.f1519I = null;
            this.f1522M = null;
            this.f1523N = null;
        } else if (i2 == 1) {
            this.f1519I = new C0340g(this.f1524O);
            this.f1522M = new C0340g();
            this.f1523N = new C0340g();
        } else if (i2 == 2) {
            if (!this.f1516F || (this.f1519I instanceof g)) {
                this.f1519I = new C0340g(this.f1524O);
            } else {
                C0344k kVar = this.f1524O;
                int i7 = g.f46A;
                if (kVar == null) {
                    kVar = new C0344k();
                }
                f fVar = new f(kVar, new RectF());
                ? gVar = new C0340g((C0339f) fVar);
                gVar.z = fVar;
                this.f1519I = gVar;
            }
            this.f1522M = null;
            this.f1523N = null;
        } else {
            throw new IllegalArgumentException(C0086a.l(new StringBuilder(), this.R, " is illegal; only @BoxBackgroundMode constants are supported."));
        }
        s();
        x();
        if (this.R == 1) {
            if (getContext().getResources().getConfiguration().fontScale >= 2.0f) {
                this.S = getResources().getDimensionPixelSize(R.dimen.material_font_2_0_box_collapsed_padding_top);
            } else if (B1.a.E(getContext())) {
                this.S = getResources().getDimensionPixelSize(R.dimen.material_font_1_3_box_collapsed_padding_top);
            }
        }
        if (this.g != null && this.R == 1) {
            if (getContext().getResources().getConfiguration().fontScale >= 2.0f) {
                EditText editText = this.g;
                ViewCompat.setPaddingRelative(editText, ViewCompat.getPaddingStart(editText), getResources().getDimensionPixelSize(R.dimen.material_filled_edittext_font_2_0_padding_top), ViewCompat.getPaddingEnd(this.g), getResources().getDimensionPixelSize(R.dimen.material_filled_edittext_font_2_0_padding_bottom));
            } else if (B1.a.E(getContext())) {
                EditText editText2 = this.g;
                ViewCompat.setPaddingRelative(editText2, ViewCompat.getPaddingStart(editText2), getResources().getDimensionPixelSize(R.dimen.material_filled_edittext_font_1_3_padding_top), ViewCompat.getPaddingEnd(this.g), getResources().getDimensionPixelSize(R.dimen.material_filled_edittext_font_1_3_padding_bottom));
            }
        }
        if (this.R != 0) {
            t();
        }
        EditText editText3 = this.g;
        if (editText3 instanceof AutoCompleteTextView) {
            AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) editText3;
            if (autoCompleteTextView.getDropDownBackground() == null) {
                int i8 = this.R;
                if (i8 == 2) {
                    autoCompleteTextView.setDropDownBackgroundDrawable(getOrCreateOutlinedDropDownMenuBackground());
                } else if (i8 == 1) {
                    autoCompleteTextView.setDropDownBackgroundDrawable(getOrCreateFilledDropDownMenuBackground());
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void j() {
        /*
            r12 = this;
            boolean r0 = r12.e()
            if (r0 != 0) goto L_0x0008
            goto L_0x00f0
        L_0x0008:
            android.widget.EditText r0 = r12.g
            int r0 = r0.getWidth()
            android.widget.EditText r1 = r12.g
            int r1 = r1.getGravity()
            h2.c r2 = r12.y0
            java.lang.CharSequence r3 = r2.E
            boolean r3 = r2.b(r3)
            r2.f1739G = r3
            android.graphics.Rect r4 = r2.f
            r5 = 1073741824(0x40000000, float:2.0)
            r6 = 1
            r7 = 5
            r8 = 8388613(0x800005, float:1.175495E-38)
            r9 = 17
            if (r1 == r9) goto L_0x0051
            r10 = r1 & 7
            if (r10 != r6) goto L_0x0030
            goto L_0x0051
        L_0x0030:
            r10 = r1 & r8
            if (r10 == r8) goto L_0x0046
            r10 = r1 & 5
            if (r10 != r7) goto L_0x0039
            goto L_0x0046
        L_0x0039:
            if (r3 == 0) goto L_0x0042
            int r3 = r4.right
            float r3 = (float) r3
            float r10 = r2.h0
        L_0x0040:
            float r3 = r3 - r10
            goto L_0x0057
        L_0x0042:
            int r3 = r4.left
        L_0x0044:
            float r3 = (float) r3
            goto L_0x0057
        L_0x0046:
            if (r3 == 0) goto L_0x004b
            int r3 = r4.left
            goto L_0x0044
        L_0x004b:
            int r3 = r4.right
            float r3 = (float) r3
            float r10 = r2.h0
            goto L_0x0040
        L_0x0051:
            float r3 = (float) r0
            float r3 = r3 / r5
            float r10 = r2.h0
            float r10 = r10 / r5
            goto L_0x0040
        L_0x0057:
            int r10 = r4.left
            float r10 = (float) r10
            float r3 = java.lang.Math.max(r3, r10)
            android.graphics.RectF r10 = r12.d0
            r10.left = r3
            int r11 = r4.top
            float r11 = (float) r11
            r10.top = r11
            if (r1 == r9) goto L_0x008d
            r9 = r1 & 7
            if (r9 != r6) goto L_0x006e
            goto L_0x008d
        L_0x006e:
            r0 = r1 & r8
            if (r0 == r8) goto L_0x0083
            r0 = r1 & 5
            if (r0 != r7) goto L_0x0077
            goto L_0x0083
        L_0x0077:
            boolean r0 = r2.f1739G
            if (r0 == 0) goto L_0x007f
            int r0 = r4.right
        L_0x007d:
            float r0 = (float) r0
            goto L_0x0093
        L_0x007f:
            float r0 = r2.h0
        L_0x0081:
            float r0 = r0 + r3
            goto L_0x0093
        L_0x0083:
            boolean r0 = r2.f1739G
            if (r0 == 0) goto L_0x008a
            float r0 = r2.h0
            goto L_0x0081
        L_0x008a:
            int r0 = r4.right
            goto L_0x007d
        L_0x008d:
            float r0 = (float) r0
            float r0 = r0 / r5
            float r1 = r2.h0
            float r1 = r1 / r5
            float r0 = r0 + r1
        L_0x0093:
            int r1 = r4.right
            float r1 = (float) r1
            float r0 = java.lang.Math.min(r0, r1)
            r10.right = r0
            int r0 = r4.top
            float r0 = (float) r0
            float r1 = r2.e()
            float r1 = r1 + r0
            r10.bottom = r1
            float r0 = r10.width()
            r1 = 0
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 <= 0) goto L_0x00f0
            float r0 = r10.height()
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 > 0) goto L_0x00b8
            goto L_0x00f0
        L_0x00b8:
            float r0 = r10.left
            int r1 = r12.Q
            float r1 = (float) r1
            float r0 = r0 - r1
            r10.left = r0
            float r0 = r10.right
            float r0 = r0 + r1
            r10.right = r0
            int r0 = r12.getPaddingLeft()
            int r0 = -r0
            float r0 = (float) r0
            int r1 = r12.getPaddingTop()
            int r1 = -r1
            float r1 = (float) r1
            float r2 = r10.height()
            float r2 = r2 / r5
            float r1 = r1 - r2
            int r2 = r12.T
            float r2 = (float) r2
            float r1 = r1 + r2
            r10.offset(r0, r1)
            x2.g r12 = r12.f1519I
            B2.g r12 = (B2.g) r12
            r12.getClass()
            float r0 = r10.left
            float r1 = r10.top
            float r2 = r10.right
            float r3 = r10.bottom
            r12.p(r0, r1, r2, r3)
        L_0x00f0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.j():void");
    }

    public final void l(AppCompatTextView appCompatTextView, int i2) {
        try {
            TextViewCompat.setTextAppearance(appCompatTextView, i2);
            if (appCompatTextView.getTextColors().getDefaultColor() != -65281) {
                return;
            }
        } catch (Exception unused) {
        }
        TextViewCompat.setTextAppearance(appCompatTextView, R$style.TextAppearance_AppCompat_Caption);
        appCompatTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.design_error));
    }

    public final boolean m() {
        t tVar = this.m;
        if (tVar.f72o != 1 || tVar.r == null || TextUtils.isEmpty(tVar.f73p)) {
            return false;
        }
        return true;
    }

    public final void n(Editable editable) {
        int i2;
        boolean z3;
        int i7;
        ((j) this.q).getClass();
        if (editable != null) {
            i2 = editable.length();
        } else {
            i2 = 0;
        }
        boolean z7 = this.f1539p;
        int i8 = this.f1538o;
        if (i8 == -1) {
            this.r.setText(String.valueOf(i2));
            this.r.setContentDescription((CharSequence) null);
            this.f1539p = false;
        } else {
            if (i2 > i8) {
                z3 = true;
            } else {
                z3 = false;
            }
            this.f1539p = z3;
            Context context = getContext();
            AppCompatTextView appCompatTextView = this.r;
            int i10 = this.f1538o;
            if (this.f1539p) {
                i7 = R.string.character_counter_overflowed_content_description;
            } else {
                i7 = R.string.character_counter_content_description;
            }
            appCompatTextView.setContentDescription(context.getString(i7, new Object[]{Integer.valueOf(i2), Integer.valueOf(i10)}));
            if (z7 != this.f1539p) {
                o();
            }
            this.r.setText(BidiFormatter.getInstance().unicodeWrap(getContext().getString(R.string.character_counter_pattern, new Object[]{Integer.valueOf(i2), Integer.valueOf(this.f1538o)})));
        }
        if (this.g != null && z7 != this.f1539p) {
            u(false, false);
            x();
            r();
        }
    }

    public final void o() {
        int i2;
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        AppCompatTextView appCompatTextView = this.r;
        if (appCompatTextView != null) {
            if (this.f1539p) {
                i2 = this.s;
            } else {
                i2 = this.t;
            }
            l(appCompatTextView, i2);
            if (!this.f1539p && (colorStateList2 = this.B) != null) {
                this.r.setTextColor(colorStateList2);
            }
            if (this.f1539p && (colorStateList = this.f1512C) != null) {
                this.r.setTextColor(colorStateList);
            }
        }
    }

    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.y0.h(configuration);
    }

    public final void onGlobalLayout() {
        int max;
        p pVar = this.f;
        pVar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        boolean z3 = false;
        this.f1515E0 = false;
        if (this.g != null && this.g.getMeasuredHeight() < (max = Math.max(pVar.getMeasuredHeight(), this.e.getMeasuredHeight()))) {
            this.g.setMinimumHeight(max);
            z3 = true;
        }
        boolean q10 = q();
        if (z3 || q10) {
            this.g.post(new C0372g(5, this));
        }
    }

    public final void onLayout(boolean z3, int i2, int i7, int i8, int i10) {
        int i11;
        int i12;
        super.onLayout(z3, i2, i7, i8, i10);
        EditText editText = this.g;
        if (editText != null) {
            Rect rect = this.b0;
            C0209d.a(this, editText, rect);
            C0340g gVar = this.f1522M;
            if (gVar != null) {
                int i13 = rect.bottom;
                gVar.setBounds(rect.left, i13 - this.U, rect.right, i13);
            }
            C0340g gVar2 = this.f1523N;
            if (gVar2 != null) {
                int i14 = rect.bottom;
                gVar2.setBounds(rect.left, i14 - this.V, rect.right, i14);
            }
            if (this.f1516F) {
                float textSize = this.g.getTextSize();
                C0208c cVar = this.y0;
                if (cVar.f1759j != textSize) {
                    cVar.f1759j = textSize;
                    cVar.i(false);
                }
                int gravity = this.g.getGravity();
                cVar.l((gravity & LttEngineErrors.ERROR_INPAINTING_OCR_BLOCK_TABULAR) | 48);
                if (cVar.f1756h != gravity) {
                    cVar.f1756h = gravity;
                    cVar.i(false);
                }
                if (this.g != null) {
                    boolean c5 = u.c(this);
                    int i15 = rect.bottom;
                    Rect rect2 = this.f1527c0;
                    rect2.bottom = i15;
                    int i16 = this.R;
                    if (i16 == 1) {
                        rect2.left = g(rect.left, c5);
                        rect2.top = rect.top + this.S;
                        rect2.right = h(rect.right, c5);
                    } else if (i16 != 2) {
                        rect2.left = g(rect.left, c5);
                        rect2.top = getPaddingTop();
                        rect2.right = h(rect.right, c5);
                    } else {
                        rect2.left = this.g.getPaddingLeft() + rect.left;
                        rect2.top = rect.top - c();
                        rect2.right = rect.right - this.g.getPaddingRight();
                    }
                    int i17 = rect2.left;
                    int i18 = rect2.top;
                    int i19 = rect2.right;
                    int i20 = rect2.bottom;
                    Rect rect3 = cVar.f;
                    if (!(rect3.left == i17 && rect3.top == i18 && rect3.right == i19 && rect3.bottom == i20)) {
                        rect3.set(i17, i18, i19, i20);
                        cVar.Q = true;
                    }
                    if (this.g != null) {
                        TextPaint textPaint = cVar.S;
                        textPaint.setTextSize(cVar.f1759j);
                        textPaint.setTypeface(cVar.f1765x);
                        textPaint.setLetterSpacing(cVar.f1753e0);
                        float f5 = -textPaint.ascent();
                        rect2.left = this.g.getCompoundPaddingLeft() + rect.left;
                        if (this.R != 1 || this.g.getMinLines() > 1) {
                            i11 = rect.top + this.g.getCompoundPaddingTop();
                        } else {
                            i11 = (int) (((float) rect.centerY()) - (f5 / 2.0f));
                        }
                        rect2.top = i11;
                        rect2.right = rect.right - this.g.getCompoundPaddingRight();
                        if (this.R != 1 || this.g.getMinLines() > 1) {
                            i12 = rect.bottom - this.g.getCompoundPaddingBottom();
                        } else {
                            i12 = (int) (((float) rect2.top) + f5);
                        }
                        rect2.bottom = i12;
                        int i21 = rect2.left;
                        int i22 = rect2.top;
                        int i23 = rect2.right;
                        Rect rect4 = cVar.e;
                        if (!(rect4.left == i21 && rect4.top == i22 && rect4.right == i23 && rect4.bottom == i12)) {
                            rect4.set(i21, i22, i23, i12);
                            cVar.Q = true;
                        }
                        cVar.i(false);
                        if (e() && !this.f1546x0) {
                            j();
                            return;
                        }
                        return;
                    }
                    throw new IllegalStateException();
                }
                throw new IllegalStateException();
            }
        }
    }

    public final void onMeasure(int i2, int i7) {
        EditText editText;
        super.onMeasure(i2, i7);
        boolean z3 = this.f1515E0;
        p pVar = this.f;
        if (!z3) {
            pVar.getViewTreeObserver().addOnGlobalLayoutListener(this);
            this.f1515E0 = true;
        }
        if (!(this.w == null || (editText = this.g) == null)) {
            this.w.setGravity(editText.getGravity());
            this.w.setPadding(this.g.getCompoundPaddingLeft(), this.g.getCompoundPaddingTop(), this.g.getCompoundPaddingRight(), this.g.getCompoundPaddingBottom());
        }
        pVar.m();
    }

    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof E)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        E e7 = (E) parcelable;
        super.onRestoreInstanceState(e7.getSuperState());
        setError(e7.d);
        if (e7.e) {
            post(new A(0, (Object) this));
        }
        requestLayout();
    }

    /* JADX WARNING: type inference failed for: r8v0, types: [x2.e, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r9v0, types: [x2.e, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r10v0, types: [x2.e, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r11v0, types: [x2.e, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r3v3, types: [x2.k, java.lang.Object] */
    public final void onRtlPropertiesChanged(int i2) {
        super.onRtlPropertiesChanged(i2);
        boolean z3 = true;
        if (i2 != 1) {
            z3 = false;
        }
        if (z3 != this.f1525P) {
            C0336c cVar = this.f1524O.e;
            RectF rectF = this.d0;
            float a7 = cVar.a(rectF);
            float a10 = this.f1524O.f.a(rectF);
            float a11 = this.f1524O.f2124h.a(rectF);
            float a12 = this.f1524O.g.a(rectF);
            C0344k kVar = this.f1524O;
            C0246a aVar = kVar.f2122a;
            C0246a aVar2 = kVar.b;
            C0246a aVar3 = kVar.d;
            C0246a aVar4 = kVar.f2123c;
            ? obj = new Object();
            ? obj2 = new Object();
            ? obj3 = new Object();
            ? obj4 = new Object();
            C0334a aVar5 = new C0334a(a10);
            C0334a aVar6 = new C0334a(a7);
            C0334a aVar7 = new C0334a(a12);
            C0334a aVar8 = new C0334a(a11);
            ? obj5 = new Object();
            obj5.f2122a = aVar2;
            obj5.b = aVar;
            obj5.f2123c = aVar3;
            obj5.d = aVar4;
            obj5.e = aVar5;
            obj5.f = aVar6;
            obj5.g = aVar8;
            obj5.f2124h = aVar7;
            obj5.f2125i = obj;
            obj5.f2126j = obj2;
            obj5.k = obj3;
            obj5.l = obj4;
            this.f1525P = z3;
            setShapeAppearanceModel(obj5);
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.os.Parcelable, androidx.customview.view.AbsSavedState, B2.E] */
    public final Parcelable onSaveInstanceState() {
        boolean z3;
        ? absSavedState = new AbsSavedState(super.onSaveInstanceState());
        if (m()) {
            absSavedState.d = getError();
        }
        p pVar = this.f;
        if (pVar.l == 0 || !pVar.f58j.d) {
            z3 = false;
        } else {
            z3 = true;
        }
        absSavedState.e = z3;
        return absSavedState;
    }

    public final void p() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2 = this.D;
        if (colorStateList2 == null) {
            Context context = getContext();
            TypedValue J4 = k.J(context, R$attr.colorControlActivated);
            if (J4 != null) {
                int i2 = J4.resourceId;
                if (i2 != 0) {
                    colorStateList2 = ContextCompat.getColorStateList(context, i2);
                } else {
                    int i7 = J4.data;
                    if (i7 != 0) {
                        colorStateList2 = ColorStateList.valueOf(i7);
                    }
                }
            }
            colorStateList2 = null;
        }
        EditText editText = this.g;
        if (editText != null && editText.getTextCursorDrawable() != null) {
            Drawable mutate = DrawableCompat.wrap(this.g.getTextCursorDrawable()).mutate();
            if ((m() || (this.r != null && this.f1539p)) && (colorStateList = this.E) != null) {
                colorStateList2 = colorStateList;
            }
            DrawableCompat.setTintList(mutate, colorStateList2);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x010d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean q() {
        /*
            r10 = this;
            android.widget.EditText r0 = r10.g
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            android.graphics.drawable.Drawable r0 = r10.getStartIconDrawable()
            r2 = 0
            r3 = 2
            r4 = 3
            r5 = 1
            if (r0 != 0) goto L_0x0020
            java.lang.CharSequence r0 = r10.getPrefixText()
            if (r0 == 0) goto L_0x005f
            android.widget.TextView r0 = r10.getPrefixTextView()
            int r0 = r0.getVisibility()
            if (r0 != 0) goto L_0x005f
        L_0x0020:
            B2.y r0 = r10.e
            int r6 = r0.getMeasuredWidth()
            if (r6 <= 0) goto L_0x005f
            int r0 = r0.getMeasuredWidth()
            android.widget.EditText r6 = r10.g
            int r6 = r6.getPaddingLeft()
            int r0 = r0 - r6
            android.graphics.drawable.ColorDrawable r6 = r10.f1529f0
            if (r6 == 0) goto L_0x003b
            int r6 = r10.f1530g0
            if (r6 == r0) goto L_0x0047
        L_0x003b:
            android.graphics.drawable.ColorDrawable r6 = new android.graphics.drawable.ColorDrawable
            r6.<init>()
            r10.f1529f0 = r6
            r10.f1530g0 = r0
            r6.setBounds(r1, r1, r0, r5)
        L_0x0047:
            android.widget.EditText r0 = r10.g
            android.graphics.drawable.Drawable[] r0 = androidx.core.widget.TextViewCompat.getCompoundDrawablesRelative(r0)
            r6 = r0[r1]
            android.graphics.drawable.ColorDrawable r7 = r10.f1529f0
            if (r6 == r7) goto L_0x0078
            android.widget.EditText r6 = r10.g
            r8 = r0[r5]
            r9 = r0[r3]
            r0 = r0[r4]
            androidx.core.widget.TextViewCompat.setCompoundDrawablesRelative(r6, r7, r8, r9, r0)
            goto L_0x0076
        L_0x005f:
            android.graphics.drawable.ColorDrawable r0 = r10.f1529f0
            if (r0 == 0) goto L_0x0078
            android.widget.EditText r0 = r10.g
            android.graphics.drawable.Drawable[] r0 = androidx.core.widget.TextViewCompat.getCompoundDrawablesRelative(r0)
            android.widget.EditText r6 = r10.g
            r7 = r0[r5]
            r8 = r0[r3]
            r0 = r0[r4]
            androidx.core.widget.TextViewCompat.setCompoundDrawablesRelative(r6, r2, r7, r8, r0)
            r10.f1529f0 = r2
        L_0x0076:
            r0 = r5
            goto L_0x0079
        L_0x0078:
            r0 = r1
        L_0x0079:
            B2.p r6 = r10.f
            boolean r7 = r6.e()
            if (r7 != 0) goto L_0x008f
            int r7 = r6.l
            if (r7 == 0) goto L_0x008b
            boolean r7 = r6.d()
            if (r7 != 0) goto L_0x008f
        L_0x008b:
            java.lang.CharSequence r7 = r6.s
            if (r7 == 0) goto L_0x010d
        L_0x008f:
            int r7 = r6.getMeasuredWidth()
            if (r7 <= 0) goto L_0x010d
            androidx.appcompat.widget.AppCompatTextView r7 = r6.t
            int r7 = r7.getMeasuredWidth()
            android.widget.EditText r8 = r10.g
            int r8 = r8.getPaddingRight()
            int r7 = r7 - r8
            boolean r8 = r6.e()
            if (r8 == 0) goto L_0x00ab
            com.google.android.material.internal.CheckableImageButton r2 = r6.f
            goto L_0x00b7
        L_0x00ab:
            int r8 = r6.l
            if (r8 == 0) goto L_0x00b7
            boolean r8 = r6.d()
            if (r8 == 0) goto L_0x00b7
            com.google.android.material.internal.CheckableImageButton r2 = r6.f58j
        L_0x00b7:
            if (r2 == 0) goto L_0x00ca
            int r6 = r2.getMeasuredWidth()
            int r6 = r6 + r7
            android.view.ViewGroup$LayoutParams r2 = r2.getLayoutParams()
            android.view.ViewGroup$MarginLayoutParams r2 = (android.view.ViewGroup.MarginLayoutParams) r2
            int r2 = androidx.core.view.MarginLayoutParamsCompat.getMarginStart(r2)
            int r7 = r2 + r6
        L_0x00ca:
            android.widget.EditText r2 = r10.g
            android.graphics.drawable.Drawable[] r2 = androidx.core.widget.TextViewCompat.getCompoundDrawablesRelative(r2)
            android.graphics.drawable.ColorDrawable r6 = r10.f1533i0
            if (r6 == 0) goto L_0x00eb
            int r8 = r10.f1535j0
            if (r8 == r7) goto L_0x00eb
            r10.f1535j0 = r7
            r6.setBounds(r1, r1, r7, r5)
            android.widget.EditText r0 = r10.g
            r1 = r2[r1]
            r3 = r2[r5]
            android.graphics.drawable.ColorDrawable r10 = r10.f1533i0
            r2 = r2[r4]
            androidx.core.widget.TextViewCompat.setCompoundDrawablesRelative(r0, r1, r3, r10, r2)
            return r5
        L_0x00eb:
            if (r6 != 0) goto L_0x00f9
            android.graphics.drawable.ColorDrawable r6 = new android.graphics.drawable.ColorDrawable
            r6.<init>()
            r10.f1533i0 = r6
            r10.f1535j0 = r7
            r6.setBounds(r1, r1, r7, r5)
        L_0x00f9:
            r3 = r2[r3]
            android.graphics.drawable.ColorDrawable r6 = r10.f1533i0
            if (r3 == r6) goto L_0x012f
            r10.k0 = r3
            android.widget.EditText r10 = r10.g
            r0 = r2[r1]
            r1 = r2[r5]
            r2 = r2[r4]
            androidx.core.widget.TextViewCompat.setCompoundDrawablesRelative(r10, r0, r1, r6, r2)
            return r5
        L_0x010d:
            android.graphics.drawable.ColorDrawable r6 = r10.f1533i0
            if (r6 == 0) goto L_0x012f
            android.widget.EditText r6 = r10.g
            android.graphics.drawable.Drawable[] r6 = androidx.core.widget.TextViewCompat.getCompoundDrawablesRelative(r6)
            r3 = r6[r3]
            android.graphics.drawable.ColorDrawable r7 = r10.f1533i0
            if (r3 != r7) goto L_0x012b
            android.widget.EditText r0 = r10.g
            r1 = r6[r1]
            r3 = r6[r5]
            android.graphics.drawable.Drawable r7 = r10.k0
            r4 = r6[r4]
            androidx.core.widget.TextViewCompat.setCompoundDrawablesRelative(r0, r1, r3, r7, r4)
            goto L_0x012c
        L_0x012b:
            r5 = r0
        L_0x012c:
            r10.f1533i0 = r2
            return r5
        L_0x012f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.q():boolean");
    }

    public final void r() {
        Drawable background;
        AppCompatTextView appCompatTextView;
        EditText editText = this.g;
        if (editText != null && this.R == 0 && (background = editText.getBackground()) != null) {
            if (DrawableUtils.canSafelyMutateDrawable(background)) {
                background = background.mutate();
            }
            if (m()) {
                background.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(getErrorCurrentTextColors(), PorterDuff.Mode.SRC_IN));
            } else if (!this.f1539p || (appCompatTextView = this.r) == null) {
                DrawableCompat.clearColorFilter(background);
                this.g.refreshDrawableState();
            } else {
                background.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(appCompatTextView.getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
            }
        }
    }

    public final void s() {
        EditText editText = this.g;
        if (editText != null && this.f1519I != null) {
            if ((this.L || editText.getBackground() == null) && this.R != 0) {
                ViewCompat.setBackground(this.g, getEditTextBoxBackground());
                this.L = true;
            }
        }
    }

    public void setBoxBackgroundColor(int i2) {
        if (this.a0 != i2) {
            this.a0 = i2;
            this.r0 = i2;
            this.f1541t0 = i2;
            this.f1542u0 = i2;
            b();
        }
    }

    public void setBoxBackgroundColorResource(int i2) {
        setBoxBackgroundColor(ContextCompat.getColor(getContext(), i2));
    }

    public void setBoxBackgroundColorStateList(ColorStateList colorStateList) {
        int defaultColor = colorStateList.getDefaultColor();
        this.r0 = defaultColor;
        this.a0 = defaultColor;
        this.s0 = colorStateList.getColorForState(new int[]{-16842910}, -1);
        this.f1541t0 = colorStateList.getColorForState(new int[]{16842908, 16842910}, -1);
        this.f1542u0 = colorStateList.getColorForState(new int[]{16843623, 16842910}, -1);
        b();
    }

    public void setBoxBackgroundMode(int i2) {
        if (i2 != this.R) {
            this.R = i2;
            if (this.g != null) {
                i();
            }
        }
    }

    public void setBoxCollapsedPaddingTop(int i2) {
        this.S = i2;
    }

    public void setBoxCornerFamily(int i2) {
        C0343j e7 = this.f1524O.e();
        C0336c cVar = this.f1524O.e;
        e7.f2117a = k.l(i2);
        e7.e = cVar;
        C0336c cVar2 = this.f1524O.f;
        e7.b = k.l(i2);
        e7.f = cVar2;
        C0336c cVar3 = this.f1524O.f2124h;
        e7.d = k.l(i2);
        e7.f2119h = cVar3;
        C0336c cVar4 = this.f1524O.g;
        e7.f2118c = k.l(i2);
        e7.g = cVar4;
        this.f1524O = e7.a();
        b();
    }

    public void setBoxStrokeColor(int i2) {
        if (this.f1540p0 != i2) {
            this.f1540p0 = i2;
            x();
        }
    }

    public void setBoxStrokeColorStateList(ColorStateList colorStateList) {
        if (colorStateList.isStateful()) {
            this.f1537n0 = colorStateList.getDefaultColor();
            this.f1543v0 = colorStateList.getColorForState(new int[]{-16842910}, -1);
            this.o0 = colorStateList.getColorForState(new int[]{16843623, 16842910}, -1);
            this.f1540p0 = colorStateList.getColorForState(new int[]{16842908, 16842910}, -1);
        } else if (this.f1540p0 != colorStateList.getDefaultColor()) {
            this.f1540p0 = colorStateList.getDefaultColor();
        }
        x();
    }

    public void setBoxStrokeErrorColor(ColorStateList colorStateList) {
        if (this.q0 != colorStateList) {
            this.q0 = colorStateList;
            x();
        }
    }

    public void setBoxStrokeWidth(int i2) {
        this.U = i2;
        x();
    }

    public void setBoxStrokeWidthFocused(int i2) {
        this.V = i2;
        x();
    }

    public void setBoxStrokeWidthFocusedResource(int i2) {
        setBoxStrokeWidthFocused(getResources().getDimensionPixelSize(i2));
    }

    public void setBoxStrokeWidthResource(int i2) {
        setBoxStrokeWidth(getResources().getDimensionPixelSize(i2));
    }

    public void setCounterEnabled(boolean z3) {
        if (this.n != z3) {
            Editable editable = null;
            t tVar = this.m;
            if (z3) {
                AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
                this.r = appCompatTextView;
                appCompatTextView.setId(R.id.textinput_counter);
                Typeface typeface = this.f1528e0;
                if (typeface != null) {
                    this.r.setTypeface(typeface);
                }
                this.r.setMaxLines(1);
                tVar.a(this.r, 2);
                MarginLayoutParamsCompat.setMarginStart((ViewGroup.MarginLayoutParams) this.r.getLayoutParams(), getResources().getDimensionPixelOffset(R.dimen.mtrl_textinput_counter_margin_start));
                o();
                if (this.r != null) {
                    EditText editText = this.g;
                    if (editText != null) {
                        editable = editText.getText();
                    }
                    n(editable);
                }
            } else {
                tVar.g(this.r, 2);
                this.r = null;
            }
            this.n = z3;
        }
    }

    public void setCounterMaxLength(int i2) {
        Editable editable;
        if (this.f1538o != i2) {
            if (i2 > 0) {
                this.f1538o = i2;
            } else {
                this.f1538o = -1;
            }
            if (this.n && this.r != null) {
                EditText editText = this.g;
                if (editText == null) {
                    editable = null;
                } else {
                    editable = editText.getText();
                }
                n(editable);
            }
        }
    }

    public void setCounterOverflowTextAppearance(int i2) {
        if (this.s != i2) {
            this.s = i2;
            o();
        }
    }

    public void setCounterOverflowTextColor(ColorStateList colorStateList) {
        if (this.f1512C != colorStateList) {
            this.f1512C = colorStateList;
            o();
        }
    }

    public void setCounterTextAppearance(int i2) {
        if (this.t != i2) {
            this.t = i2;
            o();
        }
    }

    public void setCounterTextColor(ColorStateList colorStateList) {
        if (this.B != colorStateList) {
            this.B = colorStateList;
            o();
        }
    }

    public void setCursorColor(ColorStateList colorStateList) {
        if (this.D != colorStateList) {
            this.D = colorStateList;
            p();
        }
    }

    public void setCursorErrorColor(ColorStateList colorStateList) {
        if (this.E != colorStateList) {
            this.E = colorStateList;
            if (m() || (this.r != null && this.f1539p)) {
                p();
            }
        }
    }

    public void setDefaultHintTextColor(ColorStateList colorStateList) {
        this.l0 = colorStateList;
        this.f1536m0 = colorStateList;
        if (this.g != null) {
            u(false, false);
        }
    }

    public void setEnabled(boolean z3) {
        k(this, z3);
        super.setEnabled(z3);
    }

    public void setEndIconActivated(boolean z3) {
        this.f.f58j.setActivated(z3);
    }

    public void setEndIconCheckable(boolean z3) {
        this.f.f58j.setCheckable(z3);
    }

    public void setEndIconContentDescription(int i2) {
        p pVar = this.f;
        CharSequence text = i2 != 0 ? pVar.getResources().getText(i2) : null;
        CheckableImageButton checkableImageButton = pVar.f58j;
        if (checkableImageButton.getContentDescription() != text) {
            checkableImageButton.setContentDescription(text);
        }
    }

    public void setEndIconDrawable(int i2) {
        p pVar = this.f;
        Drawable drawable = i2 != 0 ? AppCompatResources.getDrawable(pVar.getContext(), i2) : null;
        TextInputLayout textInputLayout = pVar.d;
        CheckableImageButton checkableImageButton = pVar.f58j;
        checkableImageButton.setImageDrawable(drawable);
        if (drawable != null) {
            L2.a.c(textInputLayout, checkableImageButton, pVar.n, pVar.f59o);
            L2.a.u(textInputLayout, checkableImageButton, pVar.n);
        }
    }

    public void setEndIconMinSize(int i2) {
        p pVar = this.f;
        if (i2 < 0) {
            pVar.getClass();
            throw new IllegalArgumentException("endIconSize cannot be less than 0");
        } else if (i2 != pVar.f60p) {
            pVar.f60p = i2;
            CheckableImageButton checkableImageButton = pVar.f58j;
            checkableImageButton.setMinimumWidth(i2);
            checkableImageButton.setMinimumHeight(i2);
            CheckableImageButton checkableImageButton2 = pVar.f;
            checkableImageButton2.setMinimumWidth(i2);
            checkableImageButton2.setMinimumHeight(i2);
        }
    }

    public void setEndIconMode(int i2) {
        this.f.g(i2);
    }

    public void setEndIconOnClickListener(View.OnClickListener onClickListener) {
        p pVar = this.f;
        CheckableImageButton checkableImageButton = pVar.f58j;
        View.OnLongClickListener onLongClickListener = pVar.r;
        checkableImageButton.setOnClickListener(onClickListener);
        L2.a.x(checkableImageButton, onLongClickListener);
    }

    public void setEndIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        p pVar = this.f;
        pVar.r = onLongClickListener;
        CheckableImageButton checkableImageButton = pVar.f58j;
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        L2.a.x(checkableImageButton, onLongClickListener);
    }

    public void setEndIconScaleType(ImageView.ScaleType scaleType) {
        p pVar = this.f;
        pVar.q = scaleType;
        pVar.f58j.setScaleType(scaleType);
        pVar.f.setScaleType(scaleType);
    }

    public void setEndIconTintList(ColorStateList colorStateList) {
        p pVar = this.f;
        if (pVar.n != colorStateList) {
            pVar.n = colorStateList;
            L2.a.c(pVar.d, pVar.f58j, colorStateList, pVar.f59o);
        }
    }

    public void setEndIconTintMode(PorterDuff.Mode mode) {
        p pVar = this.f;
        if (pVar.f59o != mode) {
            pVar.f59o = mode;
            L2.a.c(pVar.d, pVar.f58j, pVar.n, mode);
        }
    }

    public void setEndIconVisible(boolean z3) {
        this.f.h(z3);
    }

    public void setError(CharSequence charSequence) {
        t tVar = this.m;
        if (!tVar.q) {
            if (!TextUtils.isEmpty(charSequence)) {
                setErrorEnabled(true);
            } else {
                return;
            }
        }
        if (!TextUtils.isEmpty(charSequence)) {
            tVar.c();
            tVar.f73p = charSequence;
            tVar.r.setText(charSequence);
            int i2 = tVar.n;
            if (i2 != 1) {
                tVar.f72o = 1;
            }
            tVar.i(i2, tVar.f72o, tVar.h(tVar.r, charSequence));
            return;
        }
        tVar.f();
    }

    public void setErrorAccessibilityLiveRegion(int i2) {
        t tVar = this.m;
        tVar.t = i2;
        AppCompatTextView appCompatTextView = tVar.r;
        if (appCompatTextView != null) {
            ViewCompat.setAccessibilityLiveRegion(appCompatTextView, i2);
        }
    }

    public void setErrorContentDescription(CharSequence charSequence) {
        t tVar = this.m;
        tVar.s = charSequence;
        AppCompatTextView appCompatTextView = tVar.r;
        if (appCompatTextView != null) {
            appCompatTextView.setContentDescription(charSequence);
        }
    }

    public void setErrorEnabled(boolean z3) {
        t tVar = this.m;
        TextInputLayout textInputLayout = tVar.f69h;
        if (tVar.q != z3) {
            tVar.c();
            if (z3) {
                AppCompatTextView appCompatTextView = new AppCompatTextView(tVar.g);
                tVar.r = appCompatTextView;
                appCompatTextView.setId(R.id.textinput_error);
                tVar.r.setTextAlignment(5);
                Typeface typeface = tVar.B;
                if (typeface != null) {
                    tVar.r.setTypeface(typeface);
                }
                int i2 = tVar.u;
                tVar.u = i2;
                AppCompatTextView appCompatTextView2 = tVar.r;
                if (appCompatTextView2 != null) {
                    tVar.f69h.l(appCompatTextView2, i2);
                }
                ColorStateList colorStateList = tVar.v;
                tVar.v = colorStateList;
                AppCompatTextView appCompatTextView3 = tVar.r;
                if (!(appCompatTextView3 == null || colorStateList == null)) {
                    appCompatTextView3.setTextColor(colorStateList);
                }
                CharSequence charSequence = tVar.s;
                tVar.s = charSequence;
                AppCompatTextView appCompatTextView4 = tVar.r;
                if (appCompatTextView4 != null) {
                    appCompatTextView4.setContentDescription(charSequence);
                }
                int i7 = tVar.t;
                tVar.t = i7;
                AppCompatTextView appCompatTextView5 = tVar.r;
                if (appCompatTextView5 != null) {
                    ViewCompat.setAccessibilityLiveRegion(appCompatTextView5, i7);
                }
                tVar.r.setVisibility(4);
                tVar.a(tVar.r, 0);
            } else {
                tVar.f();
                tVar.g(tVar.r, 0);
                tVar.r = null;
                textInputLayout.r();
                textInputLayout.x();
            }
            tVar.q = z3;
        }
    }

    public void setErrorIconDrawable(int i2) {
        p pVar = this.f;
        pVar.i(i2 != 0 ? AppCompatResources.getDrawable(pVar.getContext(), i2) : null);
        L2.a.u(pVar.d, pVar.f, pVar.g);
    }

    public void setErrorIconOnClickListener(View.OnClickListener onClickListener) {
        p pVar = this.f;
        CheckableImageButton checkableImageButton = pVar.f;
        View.OnLongClickListener onLongClickListener = pVar.f57i;
        checkableImageButton.setOnClickListener(onClickListener);
        L2.a.x(checkableImageButton, onLongClickListener);
    }

    public void setErrorIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        p pVar = this.f;
        pVar.f57i = onLongClickListener;
        CheckableImageButton checkableImageButton = pVar.f;
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        L2.a.x(checkableImageButton, onLongClickListener);
    }

    public void setErrorIconTintList(ColorStateList colorStateList) {
        p pVar = this.f;
        if (pVar.g != colorStateList) {
            pVar.g = colorStateList;
            L2.a.c(pVar.d, pVar.f, colorStateList, pVar.f56h);
        }
    }

    public void setErrorIconTintMode(PorterDuff.Mode mode) {
        p pVar = this.f;
        if (pVar.f56h != mode) {
            pVar.f56h = mode;
            L2.a.c(pVar.d, pVar.f, pVar.g, mode);
        }
    }

    public void setErrorTextAppearance(int i2) {
        t tVar = this.m;
        tVar.u = i2;
        AppCompatTextView appCompatTextView = tVar.r;
        if (appCompatTextView != null) {
            tVar.f69h.l(appCompatTextView, i2);
        }
    }

    public void setErrorTextColor(ColorStateList colorStateList) {
        t tVar = this.m;
        tVar.v = colorStateList;
        AppCompatTextView appCompatTextView = tVar.r;
        if (appCompatTextView != null && colorStateList != null) {
            appCompatTextView.setTextColor(colorStateList);
        }
    }

    public void setExpandedHintEnabled(boolean z3) {
        if (this.f1547z0 != z3) {
            this.f1547z0 = z3;
            u(false, false);
        }
    }

    public void setHelperText(CharSequence charSequence) {
        boolean isEmpty = TextUtils.isEmpty(charSequence);
        t tVar = this.m;
        if (!isEmpty) {
            if (!tVar.f74x) {
                setHelperTextEnabled(true);
            }
            tVar.c();
            tVar.w = charSequence;
            tVar.y.setText(charSequence);
            int i2 = tVar.n;
            if (i2 != 2) {
                tVar.f72o = 2;
            }
            tVar.i(i2, tVar.f72o, tVar.h(tVar.y, charSequence));
        } else if (tVar.f74x) {
            setHelperTextEnabled(false);
        }
    }

    public void setHelperTextColor(ColorStateList colorStateList) {
        t tVar = this.m;
        tVar.f66A = colorStateList;
        AppCompatTextView appCompatTextView = tVar.y;
        if (appCompatTextView != null && colorStateList != null) {
            appCompatTextView.setTextColor(colorStateList);
        }
    }

    public void setHelperTextEnabled(boolean z3) {
        t tVar = this.m;
        TextInputLayout textInputLayout = tVar.f69h;
        if (tVar.f74x != z3) {
            tVar.c();
            if (z3) {
                AppCompatTextView appCompatTextView = new AppCompatTextView(tVar.g);
                tVar.y = appCompatTextView;
                appCompatTextView.setId(R.id.textinput_helper_text);
                tVar.y.setTextAlignment(5);
                Typeface typeface = tVar.B;
                if (typeface != null) {
                    tVar.y.setTypeface(typeface);
                }
                tVar.y.setVisibility(4);
                ViewCompat.setAccessibilityLiveRegion(tVar.y, 1);
                int i2 = tVar.z;
                tVar.z = i2;
                AppCompatTextView appCompatTextView2 = tVar.y;
                if (appCompatTextView2 != null) {
                    TextViewCompat.setTextAppearance(appCompatTextView2, i2);
                }
                ColorStateList colorStateList = tVar.f66A;
                tVar.f66A = colorStateList;
                AppCompatTextView appCompatTextView3 = tVar.y;
                if (!(appCompatTextView3 == null || colorStateList == null)) {
                    appCompatTextView3.setTextColor(colorStateList);
                }
                tVar.a(tVar.y, 1);
                tVar.y.setAccessibilityDelegate(new s(tVar));
            } else {
                tVar.c();
                int i7 = tVar.n;
                if (i7 == 2) {
                    tVar.f72o = 0;
                }
                tVar.i(i7, tVar.f72o, tVar.h(tVar.y, ""));
                tVar.g(tVar.y, 1);
                tVar.y = null;
                textInputLayout.r();
                textInputLayout.x();
            }
            tVar.f74x = z3;
        }
    }

    public void setHelperTextTextAppearance(int i2) {
        t tVar = this.m;
        tVar.z = i2;
        AppCompatTextView appCompatTextView = tVar.y;
        if (appCompatTextView != null) {
            TextViewCompat.setTextAppearance(appCompatTextView, i2);
        }
    }

    public void setHint(CharSequence charSequence) {
        if (this.f1516F) {
            setHintInternal(charSequence);
            sendAccessibilityEvent(2048);
        }
    }

    public void setHintAnimationEnabled(boolean z3) {
        this.f1510A0 = z3;
    }

    public void setHintEnabled(boolean z3) {
        if (z3 != this.f1516F) {
            this.f1516F = z3;
            if (!z3) {
                this.f1518H = false;
                if (!TextUtils.isEmpty(this.f1517G) && TextUtils.isEmpty(this.g.getHint())) {
                    this.g.setHint(this.f1517G);
                }
                setHintInternal((CharSequence) null);
            } else {
                CharSequence hint = this.g.getHint();
                if (!TextUtils.isEmpty(hint)) {
                    if (TextUtils.isEmpty(this.f1517G)) {
                        setHint(hint);
                    }
                    this.g.setHint((CharSequence) null);
                }
                this.f1518H = true;
            }
            if (this.g != null) {
                t();
            }
        }
    }

    public void setHintTextAppearance(int i2) {
        C0208c cVar = this.y0;
        cVar.k(i2);
        this.f1536m0 = cVar.m;
        if (this.g != null) {
            u(false, false);
            t();
        }
    }

    public void setHintTextColor(ColorStateList colorStateList) {
        if (this.f1536m0 != colorStateList) {
            if (this.l0 == null) {
                C0208c cVar = this.y0;
                if (cVar.m != colorStateList) {
                    cVar.m = colorStateList;
                    cVar.i(false);
                }
            }
            this.f1536m0 = colorStateList;
            if (this.g != null) {
                u(false, false);
            }
        }
    }

    public void setLengthCounter(C c5) {
        this.q = c5;
    }

    public void setMaxEms(int i2) {
        this.f1534j = i2;
        EditText editText = this.g;
        if (editText != null && i2 != -1) {
            editText.setMaxEms(i2);
        }
    }

    public void setMaxWidth(int i2) {
        this.l = i2;
        EditText editText = this.g;
        if (editText != null && i2 != -1) {
            editText.setMaxWidth(i2);
        }
    }

    public void setMaxWidthResource(int i2) {
        setMaxWidth(getContext().getResources().getDimensionPixelSize(i2));
    }

    public void setMinEms(int i2) {
        this.f1532i = i2;
        EditText editText = this.g;
        if (editText != null && i2 != -1) {
            editText.setMinEms(i2);
        }
    }

    public void setMinWidth(int i2) {
        this.k = i2;
        EditText editText = this.g;
        if (editText != null && i2 != -1) {
            editText.setMinWidth(i2);
        }
    }

    public void setMinWidthResource(int i2) {
        setMinWidth(getContext().getResources().getDimensionPixelSize(i2));
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(int i2) {
        p pVar = this.f;
        pVar.f58j.setContentDescription(i2 != 0 ? pVar.getResources().getText(i2) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(int i2) {
        p pVar = this.f;
        pVar.f58j.setImageDrawable(i2 != 0 ? AppCompatResources.getDrawable(pVar.getContext(), i2) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleEnabled(boolean z3) {
        p pVar = this.f;
        if (z3 && pVar.l != 1) {
            pVar.g(1);
        } else if (!z3) {
            pVar.g(0);
        } else {
            pVar.getClass();
        }
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintList(ColorStateList colorStateList) {
        p pVar = this.f;
        pVar.n = colorStateList;
        L2.a.c(pVar.d, pVar.f58j, colorStateList, pVar.f59o);
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintMode(PorterDuff.Mode mode) {
        p pVar = this.f;
        pVar.f59o = mode;
        L2.a.c(pVar.d, pVar.f58j, pVar.n, mode);
    }

    public void setPlaceholderText(CharSequence charSequence) {
        Editable editable;
        if (this.w == null) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
            this.w = appCompatTextView;
            appCompatTextView.setId(R.id.textinput_placeholder);
            ViewCompat.setImportantForAccessibility(this.w, 2);
            Fade d2 = d();
            this.z = d2;
            d2.setStartDelay(67);
            this.f1509A = d();
            setPlaceholderTextAppearance(this.y);
            setPlaceholderTextColor(this.f1545x);
        }
        if (TextUtils.isEmpty(charSequence)) {
            setPlaceholderTextEnabled(false);
        } else {
            if (!this.v) {
                setPlaceholderTextEnabled(true);
            }
            this.u = charSequence;
        }
        EditText editText = this.g;
        if (editText == null) {
            editable = null;
        } else {
            editable = editText.getText();
        }
        v(editable);
    }

    public void setPlaceholderTextAppearance(int i2) {
        this.y = i2;
        AppCompatTextView appCompatTextView = this.w;
        if (appCompatTextView != null) {
            TextViewCompat.setTextAppearance(appCompatTextView, i2);
        }
    }

    public void setPlaceholderTextColor(ColorStateList colorStateList) {
        if (this.f1545x != colorStateList) {
            this.f1545x = colorStateList;
            AppCompatTextView appCompatTextView = this.w;
            if (appCompatTextView != null && colorStateList != null) {
                appCompatTextView.setTextColor(colorStateList);
            }
        }
    }

    public void setPrefixText(CharSequence charSequence) {
        CharSequence charSequence2;
        y yVar = this.e;
        yVar.getClass();
        if (TextUtils.isEmpty(charSequence)) {
            charSequence2 = null;
        } else {
            charSequence2 = charSequence;
        }
        yVar.f = charSequence2;
        yVar.e.setText(charSequence);
        yVar.e();
    }

    public void setPrefixTextAppearance(int i2) {
        TextViewCompat.setTextAppearance(this.e.e, i2);
    }

    public void setPrefixTextColor(ColorStateList colorStateList) {
        this.e.e.setTextColor(colorStateList);
    }

    public void setShapeAppearanceModel(C0344k kVar) {
        C0340g gVar = this.f1519I;
        if (gVar != null && gVar.d.f2103a != kVar) {
            this.f1524O = kVar;
            b();
        }
    }

    public void setStartIconCheckable(boolean z3) {
        this.e.g.setCheckable(z3);
    }

    public void setStartIconContentDescription(int i2) {
        setStartIconContentDescription(i2 != 0 ? getResources().getText(i2) : null);
    }

    public void setStartIconDrawable(int i2) {
        setStartIconDrawable(i2 != 0 ? AppCompatResources.getDrawable(getContext(), i2) : null);
    }

    public void setStartIconMinSize(int i2) {
        y yVar = this.e;
        if (i2 < 0) {
            yVar.getClass();
            throw new IllegalArgumentException("startIconSize cannot be less than 0");
        } else if (i2 != yVar.f80j) {
            yVar.f80j = i2;
            CheckableImageButton checkableImageButton = yVar.g;
            checkableImageButton.setMinimumWidth(i2);
            checkableImageButton.setMinimumHeight(i2);
        }
    }

    public void setStartIconOnClickListener(View.OnClickListener onClickListener) {
        y yVar = this.e;
        CheckableImageButton checkableImageButton = yVar.g;
        View.OnLongClickListener onLongClickListener = yVar.l;
        checkableImageButton.setOnClickListener(onClickListener);
        L2.a.x(checkableImageButton, onLongClickListener);
    }

    public void setStartIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        y yVar = this.e;
        yVar.l = onLongClickListener;
        CheckableImageButton checkableImageButton = yVar.g;
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        L2.a.x(checkableImageButton, onLongClickListener);
    }

    public void setStartIconScaleType(ImageView.ScaleType scaleType) {
        y yVar = this.e;
        yVar.k = scaleType;
        yVar.g.setScaleType(scaleType);
    }

    public void setStartIconTintList(ColorStateList colorStateList) {
        y yVar = this.e;
        if (yVar.f78h != colorStateList) {
            yVar.f78h = colorStateList;
            L2.a.c(yVar.d, yVar.g, colorStateList, yVar.f79i);
        }
    }

    public void setStartIconTintMode(PorterDuff.Mode mode) {
        y yVar = this.e;
        if (yVar.f79i != mode) {
            yVar.f79i = mode;
            L2.a.c(yVar.d, yVar.g, yVar.f78h, mode);
        }
    }

    public void setStartIconVisible(boolean z3) {
        this.e.c(z3);
    }

    public void setSuffixText(CharSequence charSequence) {
        CharSequence charSequence2;
        p pVar = this.f;
        pVar.getClass();
        if (TextUtils.isEmpty(charSequence)) {
            charSequence2 = null;
        } else {
            charSequence2 = charSequence;
        }
        pVar.s = charSequence2;
        pVar.t.setText(charSequence);
        pVar.n();
    }

    public void setSuffixTextAppearance(int i2) {
        TextViewCompat.setTextAppearance(this.f.t, i2);
    }

    public void setSuffixTextColor(ColorStateList colorStateList) {
        this.f.t.setTextColor(colorStateList);
    }

    public void setTextInputAccessibilityDelegate(B b) {
        EditText editText = this.g;
        if (editText != null) {
            ViewCompat.setAccessibilityDelegate(editText, b);
        }
    }

    public void setTypeface(Typeface typeface) {
        if (typeface != this.f1528e0) {
            this.f1528e0 = typeface;
            C0208c cVar = this.y0;
            boolean m4 = cVar.m(typeface);
            boolean o2 = cVar.o(typeface);
            if (m4 || o2) {
                cVar.i(false);
            }
            t tVar = this.m;
            if (typeface != tVar.B) {
                tVar.B = typeface;
                AppCompatTextView appCompatTextView = tVar.r;
                if (appCompatTextView != null) {
                    appCompatTextView.setTypeface(typeface);
                }
                AppCompatTextView appCompatTextView2 = tVar.y;
                if (appCompatTextView2 != null) {
                    appCompatTextView2.setTypeface(typeface);
                }
            }
            AppCompatTextView appCompatTextView3 = this.r;
            if (appCompatTextView3 != null) {
                appCompatTextView3.setTypeface(typeface);
            }
        }
    }

    public final void t() {
        if (this.R != 1) {
            FrameLayout frameLayout = this.d;
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) frameLayout.getLayoutParams();
            int c5 = c();
            if (c5 != layoutParams.topMargin) {
                layoutParams.topMargin = c5;
                frameLayout.requestLayout();
            }
        }
    }

    public final void u(boolean z3, boolean z7) {
        boolean z9;
        boolean z10;
        ColorStateList colorStateList;
        AppCompatTextView appCompatTextView;
        ColorStateList colorStateList2;
        int i2;
        boolean isEnabled = isEnabled();
        EditText editText = this.g;
        if (editText == null || TextUtils.isEmpty(editText.getText())) {
            z9 = false;
        } else {
            z9 = true;
        }
        EditText editText2 = this.g;
        if (editText2 == null || !editText2.hasFocus()) {
            z10 = false;
        } else {
            z10 = true;
        }
        ColorStateList colorStateList3 = this.l0;
        C0208c cVar = this.y0;
        if (colorStateList3 != null) {
            cVar.j(colorStateList3);
        }
        Editable editable = null;
        if (!isEnabled) {
            ColorStateList colorStateList4 = this.l0;
            if (colorStateList4 != null) {
                i2 = colorStateList4.getColorForState(new int[]{-16842910}, this.f1543v0);
            } else {
                i2 = this.f1543v0;
            }
            cVar.j(ColorStateList.valueOf(i2));
        } else if (m()) {
            AppCompatTextView appCompatTextView2 = this.m.r;
            if (appCompatTextView2 != null) {
                colorStateList2 = appCompatTextView2.getTextColors();
            } else {
                colorStateList2 = null;
            }
            cVar.j(colorStateList2);
        } else if (this.f1539p && (appCompatTextView = this.r) != null) {
            cVar.j(appCompatTextView.getTextColors());
        } else if (!(!z10 || (colorStateList = this.f1536m0) == null || cVar.m == colorStateList)) {
            cVar.m = colorStateList;
            cVar.i(false);
        }
        p pVar = this.f;
        y yVar = this.e;
        if (z9 || !this.f1547z0 || (isEnabled() && z10)) {
            if (z7 || this.f1546x0) {
                ValueAnimator valueAnimator = this.f1511B0;
                if (valueAnimator != null && valueAnimator.isRunning()) {
                    this.f1511B0.cancel();
                }
                if (!z3 || !this.f1510A0) {
                    cVar.p(1.0f);
                } else {
                    a(1.0f);
                }
                this.f1546x0 = false;
                if (e()) {
                    j();
                }
                EditText editText3 = this.g;
                if (editText3 != null) {
                    editable = editText3.getText();
                }
                v(editable);
                yVar.m = false;
                yVar.e();
                pVar.u = false;
                pVar.n();
            }
        } else if (z7 || !this.f1546x0) {
            ValueAnimator valueAnimator2 = this.f1511B0;
            if (valueAnimator2 != null && valueAnimator2.isRunning()) {
                this.f1511B0.cancel();
            }
            if (!z3 || !this.f1510A0) {
                cVar.p(0.0f);
            } else {
                a(0.0f);
            }
            if (e() && !((g) this.f1519I).z.q.isEmpty() && e()) {
                ((g) this.f1519I).p(0.0f, 0.0f, 0.0f, 0.0f);
            }
            this.f1546x0 = true;
            AppCompatTextView appCompatTextView3 = this.w;
            if (appCompatTextView3 != null && this.v) {
                appCompatTextView3.setText((CharSequence) null);
                TransitionManager.beginDelayedTransition(this.d, this.f1509A);
                this.w.setVisibility(4);
            }
            yVar.m = true;
            yVar.e();
            pVar.u = true;
            pVar.n();
        }
    }

    public final void v(Editable editable) {
        int i2;
        ((j) this.q).getClass();
        if (editable != null) {
            i2 = editable.length();
        } else {
            i2 = 0;
        }
        FrameLayout frameLayout = this.d;
        if (i2 != 0 || this.f1546x0) {
            AppCompatTextView appCompatTextView = this.w;
            if (appCompatTextView != null && this.v) {
                appCompatTextView.setText((CharSequence) null);
                TransitionManager.beginDelayedTransition(frameLayout, this.f1509A);
                this.w.setVisibility(4);
            }
        } else if (this.w != null && this.v && !TextUtils.isEmpty(this.u)) {
            this.w.setText(this.u);
            TransitionManager.beginDelayedTransition(frameLayout, this.z);
            this.w.setVisibility(0);
            this.w.bringToFront();
            announceForAccessibility(this.u);
        }
    }

    public final void w(boolean z3, boolean z7) {
        int defaultColor = this.q0.getDefaultColor();
        int colorForState = this.q0.getColorForState(new int[]{16843623, 16842910}, defaultColor);
        int colorForState2 = this.q0.getColorForState(new int[]{16843518, 16842910}, defaultColor);
        if (z3) {
            this.f1526W = colorForState2;
        } else if (z7) {
            this.f1526W = colorForState;
        } else {
            this.f1526W = defaultColor;
        }
    }

    public final void x() {
        boolean z3;
        AppCompatTextView appCompatTextView;
        EditText editText;
        EditText editText2;
        if (this.f1519I != null && this.R != 0) {
            boolean z7 = false;
            if (isFocused() || ((editText2 = this.g) != null && editText2.hasFocus())) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (isHovered() || ((editText = this.g) != null && editText.isHovered())) {
                z7 = true;
            }
            if (!isEnabled()) {
                this.f1526W = this.f1543v0;
            } else if (m()) {
                if (this.q0 != null) {
                    w(z3, z7);
                } else {
                    this.f1526W = getErrorCurrentTextColors();
                }
            } else if (!this.f1539p || (appCompatTextView = this.r) == null) {
                if (z3) {
                    this.f1526W = this.f1540p0;
                } else if (z7) {
                    this.f1526W = this.o0;
                } else {
                    this.f1526W = this.f1537n0;
                }
            } else if (this.q0 != null) {
                w(z3, z7);
            } else {
                this.f1526W = appCompatTextView.getCurrentTextColor();
            }
            p();
            p pVar = this.f;
            TextInputLayout textInputLayout = pVar.d;
            CheckableImageButton checkableImageButton = pVar.f58j;
            TextInputLayout textInputLayout2 = pVar.d;
            pVar.l();
            L2.a.u(textInputLayout2, pVar.f, pVar.g);
            L2.a.u(textInputLayout2, checkableImageButton, pVar.n);
            if (pVar.b() instanceof l) {
                if (!textInputLayout.m() || checkableImageButton.getDrawable() == null) {
                    L2.a.c(textInputLayout, checkableImageButton, pVar.n, pVar.f59o);
                } else {
                    Drawable mutate = DrawableCompat.wrap(checkableImageButton.getDrawable()).mutate();
                    DrawableCompat.setTint(mutate, textInputLayout.getErrorCurrentTextColors());
                    checkableImageButton.setImageDrawable(mutate);
                }
            }
            y yVar = this.e;
            L2.a.u(yVar.d, yVar.g, yVar.f78h);
            if (this.R == 2) {
                int i2 = this.T;
                if (!z3 || !isEnabled()) {
                    this.T = this.U;
                } else {
                    this.T = this.V;
                }
                if (this.T != i2 && e() && !this.f1546x0) {
                    if (e()) {
                        ((g) this.f1519I).p(0.0f, 0.0f, 0.0f, 0.0f);
                    }
                    j();
                }
            }
            if (this.R == 1) {
                if (!isEnabled()) {
                    this.a0 = this.s0;
                } else if (z7 && !z3) {
                    this.a0 = this.f1542u0;
                } else if (z3) {
                    this.a0 = this.f1541t0;
                } else {
                    this.a0 = this.r0;
                }
            }
            b();
        }
    }

    public void setStartIconContentDescription(CharSequence charSequence) {
        CheckableImageButton checkableImageButton = this.e.g;
        if (checkableImageButton.getContentDescription() != charSequence) {
            checkableImageButton.setContentDescription(charSequence);
        }
    }

    public void setStartIconDrawable(Drawable drawable) {
        this.e.b(drawable);
    }

    public void setHint(int i2) {
        setHint(i2 != 0 ? getResources().getText(i2) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(CharSequence charSequence) {
        this.f.f58j.setContentDescription(charSequence);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(Drawable drawable) {
        this.f.f58j.setImageDrawable(drawable);
    }

    public void setErrorIconDrawable(Drawable drawable) {
        this.f.i(drawable);
    }

    public void setEndIconContentDescription(CharSequence charSequence) {
        CheckableImageButton checkableImageButton = this.f.f58j;
        if (checkableImageButton.getContentDescription() != charSequence) {
            checkableImageButton.setContentDescription(charSequence);
        }
    }

    public void setEndIconDrawable(Drawable drawable) {
        p pVar = this.f;
        TextInputLayout textInputLayout = pVar.d;
        CheckableImageButton checkableImageButton = pVar.f58j;
        checkableImageButton.setImageDrawable(drawable);
        if (drawable != null) {
            L2.a.c(textInputLayout, checkableImageButton, pVar.n, pVar.f59o);
            L2.a.u(textInputLayout, checkableImageButton, pVar.n);
        }
    }
}
