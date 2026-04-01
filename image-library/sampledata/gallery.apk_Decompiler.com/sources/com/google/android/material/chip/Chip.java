package com.google.android.material.chip;

import R1.b;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.text.BidiFormatter;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import c2.C0090a;
import c2.c;
import c2.d;
import c2.e;
import c2.j;
import com.sec.android.gallery3d.R;
import h2.g;
import h2.h;
import h2.m;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import og.k;
import u2.C0290e;
import v2.C0299a;
import x2.C0344k;
import x2.C0353t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Chip extends AppCompatCheckBox implements d, C0353t, h {
    public static final Rect w = new Rect();

    /* renamed from: x  reason: collision with root package name */
    public static final int[] f1437x = {16842913};
    public static final int[] y = {16842911};
    public e d;
    public InsetDrawable e;
    public RippleDrawable f;
    public View.OnClickListener g;

    /* renamed from: h  reason: collision with root package name */
    public CompoundButton.OnCheckedChangeListener f1438h;

    /* renamed from: i  reason: collision with root package name */
    public g f1439i;

    /* renamed from: j  reason: collision with root package name */
    public boolean f1440j;
    public boolean k;
    public boolean l;
    public boolean m;
    public boolean n;

    /* renamed from: o  reason: collision with root package name */
    public int f1441o;

    /* renamed from: p  reason: collision with root package name */
    public int f1442p;
    public CharSequence q;
    public final c r;
    public boolean s;
    public final Rect t = new Rect();
    public final RectF u = new RectF();
    public final C0090a v = new C0090a(0, this);

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Chip(android.content.Context r17, android.util.AttributeSet r18) {
        /*
            r16 = this;
            r0 = r16
            r2 = r18
            r1 = 2131953170(0x7f130612, float:1.9542803E38)
            r4 = 2130968805(0x7f0400e5, float:1.7546274E38)
            r3 = r17
            android.content.Context r1 = D2.a.a(r3, r2, r4, r1)
            r0.<init>(r1, r2, r4)
            android.graphics.Rect r1 = new android.graphics.Rect
            r1.<init>()
            r0.t = r1
            android.graphics.RectF r1 = new android.graphics.RectF
            r1.<init>()
            r0.u = r1
            c2.a r1 = new c2.a
            r3 = 0
            r1.<init>(r3, r0)
            r0.v = r1
            android.content.Context r7 = r0.getContext()
            r8 = 8388627(0x800013, float:1.175497E-38)
            r9 = 1
            if (r2 != 0) goto L_0x0034
            goto L_0x0094
        L_0x0034:
            java.lang.String r1 = "background"
            java.lang.String r3 = "http://schemas.android.com/apk/res/android"
            java.lang.String r1 = r2.getAttributeValue(r3, r1)
            java.lang.String r5 = "Chip"
            if (r1 == 0) goto L_0x0045
            java.lang.String r1 = "Do not set the background; Chip manages its own background drawable."
            android.util.Log.w(r5, r1)
        L_0x0045:
            java.lang.String r1 = "drawableLeft"
            java.lang.String r1 = r2.getAttributeValue(r3, r1)
            if (r1 != 0) goto L_0x03aa
            java.lang.String r1 = "drawableStart"
            java.lang.String r1 = r2.getAttributeValue(r3, r1)
            if (r1 != 0) goto L_0x03a2
            java.lang.String r1 = "drawableEnd"
            java.lang.String r1 = r2.getAttributeValue(r3, r1)
            java.lang.String r6 = "Please set end drawable using R.attr#closeIcon."
            if (r1 != 0) goto L_0x039c
            java.lang.String r1 = "drawableRight"
            java.lang.String r1 = r2.getAttributeValue(r3, r1)
            if (r1 != 0) goto L_0x0396
            java.lang.String r1 = "singleLine"
            boolean r1 = r2.getAttributeBooleanValue(r3, r1, r9)
            if (r1 == 0) goto L_0x038e
            java.lang.String r1 = "lines"
            int r1 = r2.getAttributeIntValue(r3, r1, r9)
            if (r1 != r9) goto L_0x038e
            java.lang.String r1 = "minLines"
            int r1 = r2.getAttributeIntValue(r3, r1, r9)
            if (r1 != r9) goto L_0x038e
            java.lang.String r1 = "maxLines"
            int r1 = r2.getAttributeIntValue(r3, r1, r9)
            if (r1 != r9) goto L_0x038e
            java.lang.String r1 = "gravity"
            int r1 = r2.getAttributeIntValue(r3, r1, r8)
            if (r1 == r8) goto L_0x0094
            java.lang.String r1 = "Chip text must be vertically center and start aligned"
            android.util.Log.w(r5, r1)
        L_0x0094:
            c2.e r10 = new c2.e
            r10.<init>(r7, r2)
            r11 = 0
            int[] r6 = new int[r11]
            android.content.Context r1 = r10.f1081g0
            int[] r3 = Q1.a.g
            r5 = 2131953170(0x7f130612, float:1.9542803E38)
            android.content.res.TypedArray r1 = h2.p.d(r1, r2, r3, r4, r5, r6)
            r12 = 37
            boolean r5 = r1.hasValue(r12)
            r10.f1067G0 = r5
            r5 = 24
            android.content.Context r6 = r10.f1081g0
            android.content.res.ColorStateList r5 = B1.a.u(r6, r1, r5)
            android.content.res.ColorStateList r13 = r10.z
            if (r13 == r5) goto L_0x00c4
            r10.z = r5
            int[] r5 = r10.getState()
            r10.onStateChange(r5)
        L_0x00c4:
            r5 = 11
            android.content.res.ColorStateList r5 = B1.a.u(r6, r1, r5)
            android.content.res.ColorStateList r13 = r10.f1057A
            if (r13 == r5) goto L_0x00d7
            r10.f1057A = r5
            int[] r5 = r10.getState()
            r10.onStateChange(r5)
        L_0x00d7:
            r5 = 19
            r13 = 0
            float r5 = r1.getDimension(r5, r13)
            float r14 = r10.B
            int r14 = (r14 > r5 ? 1 : (r14 == r5 ? 0 : -1))
            if (r14 == 0) goto L_0x00ec
            r10.B = r5
            r10.invalidateSelf()
            r10.w()
        L_0x00ec:
            r5 = 12
            boolean r14 = r1.hasValue(r5)
            if (r14 == 0) goto L_0x00fb
            float r5 = r1.getDimension(r5, r13)
            r10.C(r5)
        L_0x00fb:
            r5 = 22
            android.content.res.ColorStateList r5 = B1.a.u(r6, r1, r5)
            r10.H(r5)
            r5 = 23
            float r5 = r1.getDimension(r5, r13)
            r10.I(r5)
            r5 = 36
            android.content.res.ColorStateList r5 = B1.a.u(r6, r1, r5)
            r10.R(r5)
            r5 = 5
            java.lang.CharSequence r5 = r1.getText(r5)
            if (r5 != 0) goto L_0x011f
            java.lang.String r5 = ""
        L_0x011f:
            java.lang.CharSequence r14 = r10.f1066G
            boolean r14 = android.text.TextUtils.equals(r14, r5)
            h2.m r15 = r10.f1084m0
            if (r14 != 0) goto L_0x0133
            r10.f1066G = r5
            r15.e = r9
            r10.invalidateSelf()
            r10.w()
        L_0x0133:
            boolean r5 = r1.hasValue(r11)
            if (r5 == 0) goto L_0x0145
            int r5 = r1.getResourceId(r11, r11)
            if (r5 == 0) goto L_0x0145
            u2.e r14 = new u2.e
            r14.<init>(r6, r5)
            goto L_0x0146
        L_0x0145:
            r14 = 0
        L_0x0146:
            float r5 = r14.k
            float r5 = r1.getDimension(r9, r5)
            r14.k = r5
            r15.b(r14, r6)
            r5 = 3
            int r14 = r1.getInt(r5, r11)
            if (r14 == r9) goto L_0x0168
            r15 = 2
            if (r14 == r15) goto L_0x0163
            if (r14 == r5) goto L_0x015e
            goto L_0x016c
        L_0x015e:
            android.text.TextUtils$TruncateAt r5 = android.text.TextUtils.TruncateAt.END
            r10.f1062D0 = r5
            goto L_0x016c
        L_0x0163:
            android.text.TextUtils$TruncateAt r5 = android.text.TextUtils.TruncateAt.MIDDLE
            r10.f1062D0 = r5
            goto L_0x016c
        L_0x0168:
            android.text.TextUtils$TruncateAt r5 = android.text.TextUtils.TruncateAt.START
            r10.f1062D0 = r5
        L_0x016c:
            r5 = 18
            boolean r5 = r1.getBoolean(r5, r11)
            r10.G(r5)
            java.lang.String r5 = "http://schemas.android.com/apk/res-auto"
            if (r2 == 0) goto L_0x0192
            java.lang.String r14 = "chipIconEnabled"
            java.lang.String r14 = r2.getAttributeValue(r5, r14)
            if (r14 == 0) goto L_0x0192
            java.lang.String r14 = "chipIconVisible"
            java.lang.String r14 = r2.getAttributeValue(r5, r14)
            if (r14 != 0) goto L_0x0192
            r14 = 15
            boolean r14 = r1.getBoolean(r14, r11)
            r10.G(r14)
        L_0x0192:
            r14 = 14
            android.graphics.drawable.Drawable r14 = B1.a.w(r6, r1, r14)
            r10.D(r14)
            r14 = 17
            boolean r15 = r1.hasValue(r14)
            if (r15 == 0) goto L_0x01aa
            android.content.res.ColorStateList r14 = B1.a.u(r6, r1, r14)
            r10.F(r14)
        L_0x01aa:
            r14 = 16
            r15 = -1082130432(0xffffffffbf800000, float:-1.0)
            float r14 = r1.getDimension(r14, r15)
            r10.E(r14)
            r14 = 31
            boolean r14 = r1.getBoolean(r14, r11)
            r10.O(r14)
            if (r2 == 0) goto L_0x01d9
            java.lang.String r14 = "closeIconEnabled"
            java.lang.String r14 = r2.getAttributeValue(r5, r14)
            if (r14 == 0) goto L_0x01d9
            java.lang.String r14 = "closeIconVisible"
            java.lang.String r14 = r2.getAttributeValue(r5, r14)
            if (r14 != 0) goto L_0x01d9
            r14 = 26
            boolean r14 = r1.getBoolean(r14, r11)
            r10.O(r14)
        L_0x01d9:
            r14 = 25
            android.graphics.drawable.Drawable r14 = B1.a.w(r6, r1, r14)
            r10.J(r14)
            r14 = 30
            android.content.res.ColorStateList r14 = B1.a.u(r6, r1, r14)
            r10.N(r14)
            r14 = 28
            float r14 = r1.getDimension(r14, r13)
            r10.L(r14)
            r14 = 6
            boolean r14 = r1.getBoolean(r14, r11)
            r10.y(r14)
            r14 = 10
            boolean r14 = r1.getBoolean(r14, r11)
            r10.B(r14)
            if (r2 == 0) goto L_0x0220
            java.lang.String r14 = "checkedIconEnabled"
            java.lang.String r14 = r2.getAttributeValue(r5, r14)
            if (r14 == 0) goto L_0x0220
            java.lang.String r14 = "checkedIconVisible"
            java.lang.String r5 = r2.getAttributeValue(r5, r14)
            if (r5 != 0) goto L_0x0220
            r5 = 8
            boolean r5 = r1.getBoolean(r5, r11)
            r10.B(r5)
        L_0x0220:
            r5 = 7
            android.graphics.drawable.Drawable r5 = B1.a.w(r6, r1, r5)
            r10.z(r5)
            r5 = 9
            boolean r14 = r1.hasValue(r5)
            if (r14 == 0) goto L_0x0237
            android.content.res.ColorStateList r5 = B1.a.u(r6, r1, r5)
            r10.A(r5)
        L_0x0237:
            r5 = 39
            boolean r14 = r1.hasValue(r5)
            if (r14 == 0) goto L_0x024a
            int r5 = r1.getResourceId(r5, r11)
            if (r5 == 0) goto L_0x024a
            R1.b r5 = R1.b.a(r6, r5)
            goto L_0x024b
        L_0x024a:
            r5 = 0
        L_0x024b:
            r10.f1076W = r5
            r5 = 33
            boolean r14 = r1.hasValue(r5)
            if (r14 == 0) goto L_0x0260
            int r5 = r1.getResourceId(r5, r11)
            if (r5 == 0) goto L_0x0260
            R1.b r14 = R1.b.a(r6, r5)
            goto L_0x0261
        L_0x0260:
            r14 = 0
        L_0x0261:
            r10.f1077X = r14
            r5 = 21
            float r5 = r1.getDimension(r5, r13)
            float r6 = r10.Y
            int r6 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1))
            if (r6 == 0) goto L_0x0277
            r10.Y = r5
            r10.invalidateSelf()
            r10.w()
        L_0x0277:
            r5 = 35
            float r5 = r1.getDimension(r5, r13)
            r10.Q(r5)
            r5 = 34
            float r5 = r1.getDimension(r5, r13)
            r10.P(r5)
            r5 = 41
            float r5 = r1.getDimension(r5, r13)
            float r6 = r10.b0
            int r6 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1))
            if (r6 == 0) goto L_0x029d
            r10.b0 = r5
            r10.invalidateSelf()
            r10.w()
        L_0x029d:
            r5 = 40
            float r5 = r1.getDimension(r5, r13)
            float r6 = r10.f1078c0
            int r6 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1))
            if (r6 == 0) goto L_0x02b1
            r10.f1078c0 = r5
            r10.invalidateSelf()
            r10.w()
        L_0x02b1:
            r5 = 29
            float r5 = r1.getDimension(r5, r13)
            r10.M(r5)
            r5 = 27
            float r5 = r1.getDimension(r5, r13)
            r10.K(r5)
            r5 = 13
            float r5 = r1.getDimension(r5, r13)
            float r6 = r10.f1080f0
            int r6 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1))
            if (r6 == 0) goto L_0x02d7
            r10.f1080f0 = r5
            r10.invalidateSelf()
            r10.w()
        L_0x02d7:
            r5 = 4
            r6 = 2147483647(0x7fffffff, float:NaN)
            int r5 = r1.getDimensionPixelSize(r5, r6)
            r10.f1065F0 = r5
            r1.recycle()
            int[] r6 = new int[r11]
            r5 = 2131953170(0x7f130612, float:1.9542803E38)
            h2.p.a(r7, r2, r4, r5)
            r1 = r7
            h2.p.b(r1, r2, r3, r4, r5, r6)
            android.content.res.TypedArray r5 = r1.obtainStyledAttributes(r2, r3, r4, r5)
            r6 = 32
            boolean r6 = r5.getBoolean(r6, r11)
            r0.n = r6
            android.content.Context r6 = r0.getContext()
            r7 = 48
            float r6 = h2.u.b(r6, r7)
            double r6 = (double) r6
            double r6 = java.lang.Math.ceil(r6)
            float r6 = (float) r6
            r7 = 20
            float r6 = r5.getDimension(r7, r6)
            double r6 = (double) r6
            double r6 = java.lang.Math.ceil(r6)
            int r6 = (int) r6
            r0.f1442p = r6
            r5.recycle()
            r0.setChipDrawable(r10)
            float r5 = androidx.core.view.ViewCompat.getElevation(r0)
            r10.j(r5)
            int[] r6 = new int[r11]
            r5 = 2131953170(0x7f130612, float:1.9542803E38)
            h2.p.a(r1, r2, r4, r5)
            h2.p.b(r1, r2, r3, r4, r5, r6)
            android.content.res.TypedArray r1 = r1.obtainStyledAttributes(r2, r3, r4, r5)
            boolean r2 = r1.hasValue(r12)
            r1.recycle()
            c2.c r1 = new c2.c
            r1.<init>(r0, r0)
            r0.r = r1
            r0.e()
            if (r2 != 0) goto L_0x0351
            c2.b r1 = new c2.b
            r1.<init>(r0)
            r0.setOutlineProvider(r1)
        L_0x0351:
            boolean r1 = r0.f1440j
            r0.setChecked(r1)
            java.lang.CharSequence r1 = r10.f1066G
            r0.setText(r1)
            android.text.TextUtils$TruncateAt r1 = r10.f1062D0
            r0.setEllipsize(r1)
            r0.h()
            c2.e r1 = r0.d
            boolean r1 = r1.f1063E0
            if (r1 != 0) goto L_0x036f
            r0.setLines(r9)
            r0.setHorizontallyScrolling(r9)
        L_0x036f:
            r0.setGravity(r8)
            r0.g()
            boolean r1 = r0.n
            if (r1 == 0) goto L_0x037e
            int r1 = r0.f1442p
            r0.setMinHeight(r1)
        L_0x037e:
            int r1 = androidx.core.view.ViewCompat.getLayoutDirection(r0)
            r0.f1441o = r1
            Fa.K r1 = new Fa.K
            r2 = 2
            r1.<init>(r2, r0)
            super.setOnCheckedChangeListener(r1)
            return
        L_0x038e:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            java.lang.String r1 = "Chip does not support multi-line text"
            r0.<init>(r1)
            throw r0
        L_0x0396:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>(r6)
            throw r0
        L_0x039c:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>(r6)
            throw r0
        L_0x03a2:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            java.lang.String r1 = "Please set start drawable using R.attr#chipIcon."
            r0.<init>(r1)
            throw r0
        L_0x03aa:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            java.lang.String r1 = "Please set left drawable using R.attr#chipIcon."
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.Chip.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    /* access modifiers changed from: private */
    public RectF getCloseIconTouchBounds() {
        RectF rectF = this.u;
        rectF.setEmpty();
        if (d() && this.g != null) {
            e eVar = this.d;
            Rect bounds = eVar.getBounds();
            rectF.setEmpty();
            if (eVar.U()) {
                float f5 = eVar.f1080f0 + eVar.f1079e0 + eVar.Q + eVar.d0 + eVar.f1078c0;
                if (DrawableCompat.getLayoutDirection(eVar) == 0) {
                    float f8 = (float) bounds.right;
                    rectF.right = f8;
                    rectF.left = f8 - f5;
                } else {
                    float f10 = (float) bounds.left;
                    rectF.left = f10;
                    rectF.right = f10 + f5;
                }
                rectF.top = (float) bounds.top;
                rectF.bottom = (float) bounds.bottom;
            }
        }
        return rectF;
    }

    /* access modifiers changed from: private */
    public Rect getCloseIconTouchBoundsInt() {
        RectF closeIconTouchBounds = getCloseIconTouchBounds();
        Rect rect = this.t;
        rect.set((int) closeIconTouchBounds.left, (int) closeIconTouchBounds.top, (int) closeIconTouchBounds.right, (int) closeIconTouchBounds.bottom);
        return rect;
    }

    private C0290e getTextAppearance() {
        e eVar = this.d;
        if (eVar != null) {
            return eVar.f1084m0.g;
        }
        return null;
    }

    private void setCloseIconHovered(boolean z) {
        if (this.l != z) {
            this.l = z;
            refreshDrawableState();
        }
    }

    private void setCloseIconPressed(boolean z) {
        if (this.k != z) {
            this.k = z;
            refreshDrawableState();
        }
    }

    public final void c(int i2) {
        int i7;
        this.f1442p = i2;
        int i8 = 0;
        if (!this.n) {
            InsetDrawable insetDrawable = this.e;
            if (insetDrawable == null) {
                f();
            } else if (insetDrawable != null) {
                this.e = null;
                setMinWidth(0);
                setMinHeight((int) getChipMinHeight());
                f();
            }
        } else {
            int max = Math.max(0, i2 - ((int) this.d.B));
            int max2 = Math.max(0, i2 - this.d.getIntrinsicWidth());
            if (max2 > 0 || max > 0) {
                if (max2 > 0) {
                    i7 = max2 / 2;
                } else {
                    i7 = 0;
                }
                if (max > 0) {
                    i8 = max / 2;
                }
                int i10 = i8;
                if (this.e != null) {
                    Rect rect = new Rect();
                    this.e.getPadding(rect);
                    if (rect.top == i10 && rect.bottom == i10 && rect.left == i7 && rect.right == i7) {
                        f();
                        return;
                    }
                }
                if (getMinHeight() != i2) {
                    setMinHeight(i2);
                }
                if (getMinWidth() != i2) {
                    setMinWidth(i2);
                }
                this.e = new InsetDrawable(this.d, i7, i10, i7, i10);
                f();
                return;
            }
            InsetDrawable insetDrawable2 = this.e;
            if (insetDrawable2 == null) {
                f();
            } else if (insetDrawable2 != null) {
                this.e = null;
                setMinWidth(0);
                setMinHeight((int) getChipMinHeight());
                f();
            }
        }
    }

    public final boolean d() {
        Drawable drawable;
        e eVar = this.d;
        if (eVar == null) {
            return false;
        }
        Drawable drawable2 = eVar.f1073N;
        if (drawable2 != null) {
            drawable = DrawableCompat.unwrap(drawable2);
        } else {
            drawable = null;
        }
        if (drawable != null) {
            return true;
        }
        return false;
    }

    public final boolean dispatchHoverEvent(MotionEvent motionEvent) {
        if (!this.s) {
            return super.dispatchHoverEvent(motionEvent);
        }
        if (this.r.dispatchHoverEvent(motionEvent) || super.dispatchHoverEvent(motionEvent)) {
            return true;
        }
        return false;
    }

    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (!this.s) {
            return super.dispatchKeyEvent(keyEvent);
        }
        c cVar = this.r;
        if (!cVar.dispatchKeyEvent(keyEvent) || cVar.getKeyboardFocusedVirtualViewId() == Integer.MIN_VALUE) {
            return super.dispatchKeyEvent(keyEvent);
        }
        return true;
    }

    public final void drawableStateChanged() {
        int i2;
        super.drawableStateChanged();
        e eVar = this.d;
        boolean z = false;
        if (eVar != null && e.v(eVar.f1073N)) {
            e eVar2 = this.d;
            int isEnabled = isEnabled();
            if (this.m) {
                isEnabled++;
            }
            if (this.l) {
                isEnabled++;
            }
            if (this.k) {
                isEnabled++;
            }
            if (isChecked()) {
                isEnabled++;
            }
            int[] iArr = new int[isEnabled];
            if (isEnabled()) {
                iArr[0] = 16842910;
                i2 = 1;
            } else {
                i2 = 0;
            }
            if (this.m) {
                iArr[i2] = 16842908;
                i2++;
            }
            if (this.l) {
                iArr[i2] = 16843623;
                i2++;
            }
            if (this.k) {
                iArr[i2] = 16842919;
                i2++;
            }
            if (isChecked()) {
                iArr[i2] = 16842913;
            }
            if (!Arrays.equals(eVar2.f1058A0, iArr)) {
                eVar2.f1058A0 = iArr;
                if (eVar2.U()) {
                    z = eVar2.x(eVar2.getState(), iArr);
                }
            }
        }
        if (z) {
            invalidate();
        }
    }

    public final void e() {
        e eVar;
        if (!d() || (eVar = this.d) == null || !eVar.f1072M || this.g == null) {
            ViewCompat.setAccessibilityDelegate(this, (AccessibilityDelegateCompat) null);
            this.s = false;
            return;
        }
        ViewCompat.setAccessibilityDelegate(this, this.r);
        this.s = true;
    }

    public final void f() {
        this.f = new RippleDrawable(C0299a.b(this.d.f1064F), getBackgroundDrawable(), (Drawable) null);
        this.d.getClass();
        ViewCompat.setBackground(this, this.f);
        g();
    }

    public final void g() {
        e eVar;
        if (!TextUtils.isEmpty(getText()) && (eVar = this.d) != null) {
            int s5 = (int) (eVar.s() + eVar.f1080f0 + eVar.f1078c0);
            e eVar2 = this.d;
            int r5 = (int) (eVar2.r() + eVar2.Y + eVar2.b0);
            if (this.e != null) {
                Rect rect = new Rect();
                this.e.getPadding(rect);
                r5 += rect.left;
                s5 += rect.right;
            }
            ViewCompat.setPaddingRelative(this, r5, getPaddingTop(), s5, getPaddingBottom());
        }
    }

    public CharSequence getAccessibilityClassName() {
        if (!TextUtils.isEmpty(this.q)) {
            return this.q;
        }
        e eVar = this.d;
        if (eVar != null && eVar.S) {
            ViewParent parent = getParent();
            if (!(parent instanceof j) || !((j) parent).f1095j.f3473a) {
                return "android.widget.Button";
            }
            return "android.widget.RadioButton";
        } else if (isClickable()) {
            return "android.widget.Button";
        } else {
            return "android.view.View";
        }
    }

    public Drawable getBackgroundDrawable() {
        InsetDrawable insetDrawable = this.e;
        if (insetDrawable == null) {
            return this.d;
        }
        return insetDrawable;
    }

    public Drawable getCheckedIcon() {
        e eVar = this.d;
        if (eVar != null) {
            return eVar.U;
        }
        return null;
    }

    public ColorStateList getCheckedIconTint() {
        e eVar = this.d;
        if (eVar != null) {
            return eVar.V;
        }
        return null;
    }

    public ColorStateList getChipBackgroundColor() {
        e eVar = this.d;
        if (eVar != null) {
            return eVar.f1057A;
        }
        return null;
    }

    public float getChipCornerRadius() {
        e eVar = this.d;
        if (eVar != null) {
            return Math.max(0.0f, eVar.t());
        }
        return 0.0f;
    }

    public Drawable getChipDrawable() {
        return this.d;
    }

    public float getChipEndPadding() {
        e eVar = this.d;
        if (eVar != null) {
            return eVar.f1080f0;
        }
        return 0.0f;
    }

    public Drawable getChipIcon() {
        Drawable drawable;
        e eVar = this.d;
        if (eVar == null || (drawable = eVar.f1069I) == null) {
            return null;
        }
        return DrawableCompat.unwrap(drawable);
    }

    public float getChipIconSize() {
        e eVar = this.d;
        if (eVar != null) {
            return eVar.f1071K;
        }
        return 0.0f;
    }

    public ColorStateList getChipIconTint() {
        e eVar = this.d;
        if (eVar != null) {
            return eVar.f1070J;
        }
        return null;
    }

    public float getChipMinHeight() {
        e eVar = this.d;
        if (eVar != null) {
            return eVar.B;
        }
        return 0.0f;
    }

    public float getChipStartPadding() {
        e eVar = this.d;
        if (eVar != null) {
            return eVar.Y;
        }
        return 0.0f;
    }

    public ColorStateList getChipStrokeColor() {
        e eVar = this.d;
        if (eVar != null) {
            return eVar.D;
        }
        return null;
    }

    public float getChipStrokeWidth() {
        e eVar = this.d;
        if (eVar != null) {
            return eVar.E;
        }
        return 0.0f;
    }

    @Deprecated
    public CharSequence getChipText() {
        return getText();
    }

    public Drawable getCloseIcon() {
        Drawable drawable;
        e eVar = this.d;
        if (eVar == null || (drawable = eVar.f1073N) == null) {
            return null;
        }
        return DrawableCompat.unwrap(drawable);
    }

    public CharSequence getCloseIconContentDescription() {
        e eVar = this.d;
        if (eVar != null) {
            return eVar.R;
        }
        return null;
    }

    public float getCloseIconEndPadding() {
        e eVar = this.d;
        if (eVar != null) {
            return eVar.f1079e0;
        }
        return 0.0f;
    }

    public float getCloseIconSize() {
        e eVar = this.d;
        if (eVar != null) {
            return eVar.Q;
        }
        return 0.0f;
    }

    public float getCloseIconStartPadding() {
        e eVar = this.d;
        if (eVar != null) {
            return eVar.d0;
        }
        return 0.0f;
    }

    public ColorStateList getCloseIconTint() {
        e eVar = this.d;
        if (eVar != null) {
            return eVar.f1075P;
        }
        return null;
    }

    public TextUtils.TruncateAt getEllipsize() {
        e eVar = this.d;
        if (eVar != null) {
            return eVar.f1062D0;
        }
        return null;
    }

    public final void getFocusedRect(Rect rect) {
        if (this.s) {
            c cVar = this.r;
            if (cVar.getKeyboardFocusedVirtualViewId() == 1 || cVar.getAccessibilityFocusedVirtualViewId() == 1) {
                rect.set(getCloseIconTouchBoundsInt());
                return;
            }
        }
        super.getFocusedRect(rect);
    }

    public b getHideMotionSpec() {
        e eVar = this.d;
        if (eVar != null) {
            return eVar.f1077X;
        }
        return null;
    }

    public float getIconEndPadding() {
        e eVar = this.d;
        if (eVar != null) {
            return eVar.a0;
        }
        return 0.0f;
    }

    public float getIconStartPadding() {
        e eVar = this.d;
        if (eVar != null) {
            return eVar.Z;
        }
        return 0.0f;
    }

    public ColorStateList getRippleColor() {
        e eVar = this.d;
        if (eVar != null) {
            return eVar.f1064F;
        }
        return null;
    }

    public C0344k getShapeAppearanceModel() {
        return this.d.d.f2103a;
    }

    public b getShowMotionSpec() {
        e eVar = this.d;
        if (eVar != null) {
            return eVar.f1076W;
        }
        return null;
    }

    public float getTextEndPadding() {
        e eVar = this.d;
        if (eVar != null) {
            return eVar.f1078c0;
        }
        return 0.0f;
    }

    public float getTextStartPadding() {
        e eVar = this.d;
        if (eVar != null) {
            return eVar.b0;
        }
        return 0.0f;
    }

    public final void h() {
        TextPaint paint = getPaint();
        e eVar = this.d;
        if (eVar != null) {
            paint.drawableState = eVar.getState();
        }
        C0290e textAppearance = getTextAppearance();
        if (textAppearance != null) {
            textAppearance.d(getContext(), paint, this.v);
        }
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        k.P(this, this.d);
    }

    public final int[] onCreateDrawableState(int i2) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i2 + 2);
        if (isChecked()) {
            View.mergeDrawableStates(onCreateDrawableState, f1437x);
        }
        e eVar = this.d;
        if (eVar != null && eVar.S) {
            View.mergeDrawableStates(onCreateDrawableState, y);
        }
        return onCreateDrawableState;
    }

    public final void onFocusChanged(boolean z, int i2, Rect rect) {
        super.onFocusChanged(z, i2, rect);
        if (this.s) {
            this.r.onFocusChanged(z, i2, rect);
        }
    }

    public final boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 7) {
            setCloseIconHovered(getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY()));
        } else if (actionMasked == 10) {
            setCloseIconHovered(false);
        }
        return super.onHoverEvent(motionEvent);
    }

    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        boolean z;
        int i2;
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(getAccessibilityClassName());
        e eVar = this.d;
        int i7 = 0;
        if (eVar == null || !eVar.S) {
            z = false;
        } else {
            z = true;
        }
        accessibilityNodeInfo.setCheckable(z);
        accessibilityNodeInfo.setClickable(isClickable());
        if (getParent() instanceof j) {
            j jVar = (j) getParent();
            AccessibilityNodeInfoCompat wrap = AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo);
            int i8 = -1;
            if (jVar.f) {
                int i10 = 0;
                while (true) {
                    if (i7 >= jVar.getChildCount()) {
                        i10 = -1;
                        break;
                    }
                    View childAt = jVar.getChildAt(i7);
                    if ((childAt instanceof Chip) && jVar.getChildAt(i7).getVisibility() == 0) {
                        if (((Chip) childAt) == this) {
                            break;
                        }
                        i10++;
                    }
                    i7++;
                }
                i2 = i10;
            } else {
                i2 = -1;
            }
            Object tag = getTag(R.id.row_index_key);
            if (tag instanceof Integer) {
                i8 = ((Integer) tag).intValue();
            }
            wrap.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(i8, 1, i2, 1, false, isChecked()));
        }
    }

    public final PointerIcon onResolvePointerIcon(MotionEvent motionEvent, int i2) {
        if (!getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY()) || !isEnabled()) {
            return super.onResolvePointerIcon(motionEvent, i2);
        }
        return PointerIcon.getSystemIcon(getContext(), 1002);
    }

    public final void onRtlPropertiesChanged(int i2) {
        super.onRtlPropertiesChanged(i2);
        if (this.f1441o != i2) {
            this.f1441o = i2;
            g();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001e, code lost:
        if (r0 != 3) goto L_0x0050;
     */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x005a A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x005b A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            int r0 = r6.getActionMasked()
            android.graphics.RectF r1 = r5.getCloseIconTouchBounds()
            float r2 = r6.getX()
            float r3 = r6.getY()
            boolean r1 = r1.contains(r2, r3)
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L_0x004a
            if (r0 == r2) goto L_0x002c
            r4 = 2
            if (r0 == r4) goto L_0x0021
            r1 = 3
            if (r0 == r1) goto L_0x0045
            goto L_0x0050
        L_0x0021:
            boolean r0 = r5.k
            if (r0 == 0) goto L_0x0050
            if (r1 != 0) goto L_0x002a
            r5.setCloseIconPressed(r3)
        L_0x002a:
            r0 = r2
            goto L_0x0051
        L_0x002c:
            boolean r0 = r5.k
            if (r0 == 0) goto L_0x0045
            r5.playSoundEffect(r3)
            android.view.View$OnClickListener r0 = r5.g
            if (r0 == 0) goto L_0x003a
            r0.onClick(r5)
        L_0x003a:
            boolean r0 = r5.s
            if (r0 == 0) goto L_0x0043
            c2.c r0 = r5.r
            r0.sendEventForVirtualView(r2, r2)
        L_0x0043:
            r0 = r2
            goto L_0x0046
        L_0x0045:
            r0 = r3
        L_0x0046:
            r5.setCloseIconPressed(r3)
            goto L_0x0051
        L_0x004a:
            if (r1 == 0) goto L_0x0050
            r5.setCloseIconPressed(r2)
            goto L_0x002a
        L_0x0050:
            r0 = r3
        L_0x0051:
            if (r0 != 0) goto L_0x005b
            boolean r5 = super.onTouchEvent(r6)
            if (r5 == 0) goto L_0x005a
            goto L_0x005b
        L_0x005a:
            return r3
        L_0x005b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.Chip.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setAccessibilityClassName(CharSequence charSequence) {
        this.q = charSequence;
    }

    public void setBackground(Drawable drawable) {
        if (drawable == getBackgroundDrawable() || drawable == this.f) {
            super.setBackground(drawable);
        } else {
            Log.w("Chip", "Do not set the background; Chip manages its own background drawable.");
        }
    }

    public void setBackgroundColor(int i2) {
        Log.w("Chip", "Do not set the background color; Chip manages its own background drawable.");
    }

    public void setBackgroundDrawable(Drawable drawable) {
        if (drawable == getBackgroundDrawable() || drawable == this.f) {
            super.setBackgroundDrawable(drawable);
        } else {
            Log.w("Chip", "Do not set the background drawable; Chip manages its own background drawable.");
        }
    }

    public void setBackgroundResource(int i2) {
        Log.w("Chip", "Do not set the background resource; Chip manages its own background drawable.");
    }

    public void setBackgroundTintList(ColorStateList colorStateList) {
        Log.w("Chip", "Do not set the background tint list; Chip manages its own background drawable.");
    }

    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        Log.w("Chip", "Do not set the background tint mode; Chip manages its own background drawable.");
    }

    public void setCheckable(boolean z) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.y(z);
        }
    }

    public void setCheckableResource(int i2) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.y(eVar.f1081g0.getResources().getBoolean(i2));
        }
    }

    public void setChecked(boolean z) {
        e eVar = this.d;
        if (eVar == null) {
            this.f1440j = z;
        } else if (eVar.S) {
            super.setChecked(z);
        }
    }

    public void setCheckedIcon(Drawable drawable) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.z(drawable);
        }
    }

    @Deprecated
    public void setCheckedIconEnabled(boolean z) {
        setCheckedIconVisible(z);
    }

    @Deprecated
    public void setCheckedIconEnabledResource(int i2) {
        setCheckedIconVisible(i2);
    }

    public void setCheckedIconResource(int i2) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.z(AppCompatResources.getDrawable(eVar.f1081g0, i2));
        }
    }

    public void setCheckedIconTint(ColorStateList colorStateList) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.A(colorStateList);
        }
    }

    public void setCheckedIconTintResource(int i2) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.A(AppCompatResources.getColorStateList(eVar.f1081g0, i2));
        }
    }

    public void setCheckedIconVisible(int i2) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.B(eVar.f1081g0.getResources().getBoolean(i2));
        }
    }

    public void setChipBackgroundColor(ColorStateList colorStateList) {
        e eVar = this.d;
        if (eVar != null && eVar.f1057A != colorStateList) {
            eVar.f1057A = colorStateList;
            eVar.onStateChange(eVar.getState());
        }
    }

    public void setChipBackgroundColorResource(int i2) {
        ColorStateList colorStateList;
        e eVar = this.d;
        if (eVar != null && eVar.f1057A != (colorStateList = AppCompatResources.getColorStateList(eVar.f1081g0, i2))) {
            eVar.f1057A = colorStateList;
            eVar.onStateChange(eVar.getState());
        }
    }

    @Deprecated
    public void setChipCornerRadius(float f5) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.C(f5);
        }
    }

    @Deprecated
    public void setChipCornerRadiusResource(int i2) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.C(eVar.f1081g0.getResources().getDimension(i2));
        }
    }

    public void setChipDrawable(e eVar) {
        e eVar2 = this.d;
        if (eVar2 != eVar) {
            if (eVar2 != null) {
                eVar2.f1061C0 = new WeakReference((Object) null);
            }
            this.d = eVar;
            eVar.f1063E0 = false;
            eVar.f1061C0 = new WeakReference(this);
            c(this.f1442p);
        }
    }

    public void setChipEndPadding(float f5) {
        e eVar = this.d;
        if (eVar != null && eVar.f1080f0 != f5) {
            eVar.f1080f0 = f5;
            eVar.invalidateSelf();
            eVar.w();
        }
    }

    public void setChipEndPaddingResource(int i2) {
        e eVar = this.d;
        if (eVar != null) {
            float dimension = eVar.f1081g0.getResources().getDimension(i2);
            if (eVar.f1080f0 != dimension) {
                eVar.f1080f0 = dimension;
                eVar.invalidateSelf();
                eVar.w();
            }
        }
    }

    public void setChipIcon(Drawable drawable) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.D(drawable);
        }
    }

    @Deprecated
    public void setChipIconEnabled(boolean z) {
        setChipIconVisible(z);
    }

    @Deprecated
    public void setChipIconEnabledResource(int i2) {
        setChipIconVisible(i2);
    }

    public void setChipIconResource(int i2) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.D(AppCompatResources.getDrawable(eVar.f1081g0, i2));
        }
    }

    public void setChipIconSize(float f5) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.E(f5);
        }
    }

    public void setChipIconSizeResource(int i2) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.E(eVar.f1081g0.getResources().getDimension(i2));
        }
    }

    public void setChipIconTint(ColorStateList colorStateList) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.F(colorStateList);
        }
    }

    public void setChipIconTintResource(int i2) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.F(AppCompatResources.getColorStateList(eVar.f1081g0, i2));
        }
    }

    public void setChipIconVisible(int i2) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.G(eVar.f1081g0.getResources().getBoolean(i2));
        }
    }

    public void setChipMinHeight(float f5) {
        e eVar = this.d;
        if (eVar != null && eVar.B != f5) {
            eVar.B = f5;
            eVar.invalidateSelf();
            eVar.w();
        }
    }

    public void setChipMinHeightResource(int i2) {
        e eVar = this.d;
        if (eVar != null) {
            float dimension = eVar.f1081g0.getResources().getDimension(i2);
            if (eVar.B != dimension) {
                eVar.B = dimension;
                eVar.invalidateSelf();
                eVar.w();
            }
        }
    }

    public void setChipStartPadding(float f5) {
        e eVar = this.d;
        if (eVar != null && eVar.Y != f5) {
            eVar.Y = f5;
            eVar.invalidateSelf();
            eVar.w();
        }
    }

    public void setChipStartPaddingResource(int i2) {
        e eVar = this.d;
        if (eVar != null) {
            float dimension = eVar.f1081g0.getResources().getDimension(i2);
            if (eVar.Y != dimension) {
                eVar.Y = dimension;
                eVar.invalidateSelf();
                eVar.w();
            }
        }
    }

    public void setChipStrokeColor(ColorStateList colorStateList) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.H(colorStateList);
        }
    }

    public void setChipStrokeColorResource(int i2) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.H(AppCompatResources.getColorStateList(eVar.f1081g0, i2));
        }
    }

    public void setChipStrokeWidth(float f5) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.I(f5);
        }
    }

    public void setChipStrokeWidthResource(int i2) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.I(eVar.f1081g0.getResources().getDimension(i2));
        }
    }

    @Deprecated
    public void setChipText(CharSequence charSequence) {
        setText(charSequence);
    }

    @Deprecated
    public void setChipTextResource(int i2) {
        setText(getResources().getString(i2));
    }

    public void setCloseIcon(Drawable drawable) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.J(drawable);
        }
        e();
    }

    public void setCloseIconContentDescription(CharSequence charSequence) {
        e eVar = this.d;
        if (eVar != null && eVar.R != charSequence) {
            eVar.R = BidiFormatter.getInstance().unicodeWrap(charSequence);
            eVar.invalidateSelf();
        }
    }

    @Deprecated
    public void setCloseIconEnabled(boolean z) {
        setCloseIconVisible(z);
    }

    @Deprecated
    public void setCloseIconEnabledResource(int i2) {
        setCloseIconVisible(i2);
    }

    public void setCloseIconEndPadding(float f5) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.K(f5);
        }
    }

    public void setCloseIconEndPaddingResource(int i2) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.K(eVar.f1081g0.getResources().getDimension(i2));
        }
    }

    public void setCloseIconResource(int i2) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.J(AppCompatResources.getDrawable(eVar.f1081g0, i2));
        }
        e();
    }

    public void setCloseIconSize(float f5) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.L(f5);
        }
    }

    public void setCloseIconSizeResource(int i2) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.L(eVar.f1081g0.getResources().getDimension(i2));
        }
    }

    public void setCloseIconStartPadding(float f5) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.M(f5);
        }
    }

    public void setCloseIconStartPaddingResource(int i2) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.M(eVar.f1081g0.getResources().getDimension(i2));
        }
    }

    public void setCloseIconTint(ColorStateList colorStateList) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.N(colorStateList);
        }
    }

    public void setCloseIconTintResource(int i2) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.N(AppCompatResources.getColorStateList(eVar.f1081g0, i2));
        }
    }

    public void setCloseIconVisible(int i2) {
        setCloseIconVisible(getResources().getBoolean(i2));
    }

    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        } else if (drawable3 == null) {
            super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        } else {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
    }

    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        } else if (drawable3 == null) {
            super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        } else {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
    }

    public final void setCompoundDrawablesRelativeWithIntrinsicBounds(int i2, int i7, int i8, int i10) {
        if (i2 != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        } else if (i8 == 0) {
            super.setCompoundDrawablesRelativeWithIntrinsicBounds(i2, i7, i8, i10);
        } else {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
    }

    public final void setCompoundDrawablesWithIntrinsicBounds(int i2, int i7, int i8, int i10) {
        if (i2 != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        } else if (i8 == 0) {
            super.setCompoundDrawablesWithIntrinsicBounds(i2, i7, i8, i10);
        } else {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
    }

    public void setElevation(float f5) {
        super.setElevation(f5);
        e eVar = this.d;
        if (eVar != null) {
            eVar.j(f5);
        }
    }

    public void setEllipsize(TextUtils.TruncateAt truncateAt) {
        if (this.d != null) {
            if (truncateAt != TextUtils.TruncateAt.MARQUEE) {
                super.setEllipsize(truncateAt);
                e eVar = this.d;
                if (eVar != null) {
                    eVar.f1062D0 = truncateAt;
                    return;
                }
                return;
            }
            throw new UnsupportedOperationException("Text within a chip are not allowed to scroll.");
        }
    }

    public void setEnsureMinTouchTargetSize(boolean z) {
        this.n = z;
        c(this.f1442p);
    }

    public void setGravity(int i2) {
        if (i2 != 8388627) {
            Log.w("Chip", "Chip text must be vertically center and start aligned");
        } else {
            super.setGravity(i2);
        }
    }

    public void setHideMotionSpec(b bVar) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.f1077X = bVar;
        }
    }

    public void setHideMotionSpecResource(int i2) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.f1077X = b.a(eVar.f1081g0, i2);
        }
    }

    public void setIconEndPadding(float f5) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.P(f5);
        }
    }

    public void setIconEndPaddingResource(int i2) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.P(eVar.f1081g0.getResources().getDimension(i2));
        }
    }

    public void setIconStartPadding(float f5) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.Q(f5);
        }
    }

    public void setIconStartPaddingResource(int i2) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.Q(eVar.f1081g0.getResources().getDimension(i2));
        }
    }

    public void setInternalOnCheckedChangeListener(g gVar) {
        this.f1439i = gVar;
    }

    public void setLayoutDirection(int i2) {
        if (this.d != null) {
            super.setLayoutDirection(i2);
        }
    }

    public void setLines(int i2) {
        if (i2 <= 1) {
            super.setLines(i2);
            return;
        }
        throw new UnsupportedOperationException("Chip does not support multi-line text");
    }

    public void setMaxLines(int i2) {
        if (i2 <= 1) {
            super.setMaxLines(i2);
            return;
        }
        throw new UnsupportedOperationException("Chip does not support multi-line text");
    }

    public void setMaxWidth(int i2) {
        super.setMaxWidth(i2);
        e eVar = this.d;
        if (eVar != null) {
            eVar.f1065F0 = i2;
        }
    }

    public void setMinLines(int i2) {
        if (i2 <= 1) {
            super.setMinLines(i2);
            return;
        }
        throw new UnsupportedOperationException("Chip does not support multi-line text");
    }

    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.f1438h = onCheckedChangeListener;
    }

    public void setOnCloseIconClickListener(View.OnClickListener onClickListener) {
        this.g = onClickListener;
        e();
    }

    public void setRippleColor(ColorStateList colorStateList) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.R(colorStateList);
        }
        this.d.getClass();
        f();
    }

    public void setRippleColorResource(int i2) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.R(AppCompatResources.getColorStateList(eVar.f1081g0, i2));
            this.d.getClass();
            f();
        }
    }

    public void setShapeAppearanceModel(C0344k kVar) {
        this.d.setShapeAppearanceModel(kVar);
    }

    public void setShowMotionSpec(b bVar) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.f1076W = bVar;
        }
    }

    public void setShowMotionSpecResource(int i2) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.f1076W = b.a(eVar.f1081g0, i2);
        }
    }

    public void setSingleLine(boolean z) {
        if (z) {
            super.setSingleLine(z);
            return;
        }
        throw new UnsupportedOperationException("Chip does not support multi-line text");
    }

    public final void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        CharSequence charSequence2;
        e eVar = this.d;
        if (eVar != null) {
            if (charSequence == null) {
                charSequence = "";
            }
            if (eVar.f1063E0) {
                charSequence2 = null;
            } else {
                charSequence2 = charSequence;
            }
            super.setText(charSequence2, bufferType);
            e eVar2 = this.d;
            if (eVar2 != null && !TextUtils.equals(eVar2.f1066G, charSequence)) {
                eVar2.f1066G = charSequence;
                eVar2.f1084m0.e = true;
                eVar2.invalidateSelf();
                eVar2.w();
            }
        }
    }

    public void setTextAppearance(C0290e eVar) {
        e eVar2 = this.d;
        if (eVar2 != null) {
            eVar2.f1084m0.b(eVar, eVar2.f1081g0);
        }
        h();
    }

    public void setTextAppearanceResource(int i2) {
        setTextAppearance(getContext(), i2);
    }

    public void setTextEndPadding(float f5) {
        e eVar = this.d;
        if (eVar != null && eVar.f1078c0 != f5) {
            eVar.f1078c0 = f5;
            eVar.invalidateSelf();
            eVar.w();
        }
    }

    public void setTextEndPaddingResource(int i2) {
        e eVar = this.d;
        if (eVar != null) {
            float dimension = eVar.f1081g0.getResources().getDimension(i2);
            if (eVar.f1078c0 != dimension) {
                eVar.f1078c0 = dimension;
                eVar.invalidateSelf();
                eVar.w();
            }
        }
    }

    public final void setTextSize(int i2, float f5) {
        super.setTextSize(i2, f5);
        e eVar = this.d;
        if (eVar != null) {
            float applyDimension = TypedValue.applyDimension(i2, f5, getResources().getDisplayMetrics());
            m mVar = eVar.f1084m0;
            C0290e eVar2 = mVar.g;
            if (eVar2 != null) {
                eVar2.k = applyDimension;
                mVar.f1774a.setTextSize(applyDimension);
                eVar.a();
            }
        }
        h();
    }

    public void setTextStartPadding(float f5) {
        e eVar = this.d;
        if (eVar != null && eVar.b0 != f5) {
            eVar.b0 = f5;
            eVar.invalidateSelf();
            eVar.w();
        }
    }

    public void setTextStartPaddingResource(int i2) {
        e eVar = this.d;
        if (eVar != null) {
            float dimension = eVar.f1081g0.getResources().getDimension(i2);
            if (eVar.b0 != dimension) {
                eVar.b0 = dimension;
                eVar.invalidateSelf();
                eVar.w();
            }
        }
    }

    public void setCloseIconVisible(boolean z) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.O(z);
        }
        e();
    }

    public void setCheckedIconVisible(boolean z) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.B(z);
        }
    }

    public void setChipIconVisible(boolean z) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.G(z);
        }
    }

    public final void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        } else if (drawable3 == null) {
            super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        } else {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
    }

    public final void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
        } else if (drawable3 == null) {
            super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        } else {
            throw new UnsupportedOperationException("Please set right drawable using R.attr#closeIcon.");
        }
    }

    public final void setTextAppearance(Context context, int i2) {
        super.setTextAppearance(context, i2);
        e eVar = this.d;
        if (eVar != null) {
            Context context2 = eVar.f1081g0;
            eVar.f1084m0.b(new C0290e(context2, i2), context2);
        }
        h();
    }

    public void setTextAppearance(int i2) {
        super.setTextAppearance(i2);
        e eVar = this.d;
        if (eVar != null) {
            Context context = eVar.f1081g0;
            eVar.f1084m0.b(new C0290e(context, i2), context);
        }
        h();
    }
}
