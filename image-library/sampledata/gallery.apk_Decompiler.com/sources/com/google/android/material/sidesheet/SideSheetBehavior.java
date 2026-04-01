package com.google.android.material.sidesheet;

import A4.Y;
import Gd.a;
import Y1.f;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ViewDragHelper;
import c0.C0086a;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import i.C0212a;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedHashSet;
import x2.C0340g;
import x2.C0344k;
import y2.C0358b;
import y2.C0359c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SideSheetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    public a d;
    public final C0340g e;
    public final ColorStateList f;
    public final C0344k g;

    /* renamed from: h  reason: collision with root package name */
    public final f f1489h = new f(this);

    /* renamed from: i  reason: collision with root package name */
    public final float f1490i;

    /* renamed from: j  reason: collision with root package name */
    public final boolean f1491j = true;
    public int k = 5;
    public ViewDragHelper l;
    public boolean m;
    public final float n = 0.1f;

    /* renamed from: o  reason: collision with root package name */
    public int f1492o;

    /* renamed from: p  reason: collision with root package name */
    public int f1493p;
    public int q;
    public int r;
    public WeakReference s;
    public WeakReference t;
    public final int u = -1;
    public VelocityTracker v;
    public int w;

    /* renamed from: x  reason: collision with root package name */
    public final LinkedHashSet f1494x = new LinkedHashSet();
    public final C0358b y = new C0358b(this);

    public SideSheetBehavior() {
    }

    public final boolean a() {
        if (this.l == null) {
            return false;
        }
        if (this.f1491j || this.k == 1) {
            return true;
        }
        return false;
    }

    public final void b(View view, int i2, boolean z) {
        int i7;
        if (i2 == 3) {
            i7 = this.d.w();
        } else if (i2 == 5) {
            i7 = this.d.y();
        } else {
            throw new IllegalArgumentException(C0086a.i(i2, "Invalid state to get outer edge offset: "));
        }
        ViewDragHelper viewDragHelper = this.l;
        if (viewDragHelper == null || (!z ? !viewDragHelper.smoothSlideViewTo(view, i7, view.getTop()) : !viewDragHelper.settleCapturedViewAt(i7, view.getTop()))) {
            setStateInternal(i2);
            return;
        }
        setStateInternal(2);
        this.f1489h.b(i2);
    }

    public final void c() {
        View view;
        WeakReference weakReference = this.s;
        if (weakReference != null && (view = (View) weakReference.get()) != null) {
            ViewCompat.removeAccessibilityAction(view, 262144);
            ViewCompat.removeAccessibilityAction(view, MediaDefs.Meta.SEF.SEF_MIN_SIZE);
            if (this.k != 5) {
                ViewCompat.replaceAccessibilityAction(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_DISMISS, (CharSequence) null, new Y((Object) this, 5, 8));
            }
            if (this.k != 3) {
                ViewCompat.replaceAccessibilityAction(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND, (CharSequence) null, new Y((Object) this, 3, 8));
            }
        }
    }

    public final void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams layoutParams) {
        super.onAttachedToLayoutParams(layoutParams);
        this.s = null;
        this.l = null;
    }

    public final void onDetachedFromLayoutParams() {
        super.onDetachedFromLayoutParams();
        this.s = null;
        this.l = null;
    }

    public final boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        ViewDragHelper viewDragHelper;
        VelocityTracker velocityTracker;
        if ((view.isShown() || ViewCompat.getAccessibilityPaneTitle(view) != null) && this.f1491j) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0 && (velocityTracker = this.v) != null) {
                velocityTracker.recycle();
                this.v = null;
            }
            if (this.v == null) {
                this.v = VelocityTracker.obtain();
            }
            this.v.addMovement(motionEvent);
            if (actionMasked == 0) {
                this.w = (int) motionEvent.getX();
            } else if ((actionMasked == 1 || actionMasked == 3) && this.m) {
                this.m = false;
                return false;
            }
            if (this.m || (viewDragHelper = this.l) == null || !viewDragHelper.shouldInterceptTouchEvent(motionEvent)) {
                return false;
            }
            return true;
        }
        this.m = true;
        return false;
    }

    /* JADX WARNING: type inference failed for: r0v19, types: [android.view.ViewGroup$LayoutParams] */
    /* JADX WARNING: type inference failed for: r0v27, types: [android.view.ViewGroup$LayoutParams] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onLayoutChild(androidx.coordinatorlayout.widget.CoordinatorLayout r10, android.view.View r11, int r12) {
        /*
            r9 = this;
            boolean r0 = androidx.core.view.ViewCompat.getFitsSystemWindows(r10)
            r1 = 1
            if (r0 == 0) goto L_0x0010
            boolean r0 = androidx.core.view.ViewCompat.getFitsSystemWindows(r11)
            if (r0 != 0) goto L_0x0010
            r11.setFitsSystemWindows(r1)
        L_0x0010:
            java.lang.ref.WeakReference r0 = r9.s
            r2 = 5
            r3 = 0
            r4 = 0
            if (r0 != 0) goto L_0x00ac
            java.lang.ref.WeakReference r0 = new java.lang.ref.WeakReference
            r0.<init>(r11)
            r9.s = r0
            android.content.Context r0 = r11.getContext()
            r5 = 1065353216(0x3f800000, float:1.0)
            android.view.animation.Interpolator r5 = androidx.core.view.animation.PathInterpolatorCompat.create(r3, r3, r3, r5)
            r6 = 2130969560(0x7f0403d8, float:1.7547805E38)
            og.k.M(r0, r6, r5)
            r5 = 2130969543(0x7f0403c7, float:1.754777E38)
            r6 = 300(0x12c, float:4.2E-43)
            og.k.L(r0, r5, r6)
            r5 = 2130969548(0x7f0403cc, float:1.754778E38)
            r6 = 150(0x96, float:2.1E-43)
            og.k.L(r0, r5, r6)
            r5 = 2130969547(0x7f0403cb, float:1.7547779E38)
            r6 = 100
            og.k.L(r0, r5, r6)
            android.content.res.Resources r0 = r11.getResources()
            r5 = 2131166226(0x7f070412, float:1.7946691E38)
            r0.getDimension(r5)
            r5 = 2131166225(0x7f070411, float:1.794669E38)
            r0.getDimension(r5)
            r5 = 2131166227(0x7f070413, float:1.7946693E38)
            r0.getDimension(r5)
            x2.g r0 = r9.e
            if (r0 == 0) goto L_0x0075
            androidx.core.view.ViewCompat.setBackground(r11, r0)
            r0 = -1082130432(0xffffffffbf800000, float:-1.0)
            float r5 = r9.f1490i
            int r0 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r0 != 0) goto L_0x006f
            float r5 = androidx.core.view.ViewCompat.getElevation(r11)
        L_0x006f:
            x2.g r0 = r9.e
            r0.j(r5)
            goto L_0x007c
        L_0x0075:
            android.content.res.ColorStateList r0 = r9.f
            if (r0 == 0) goto L_0x007c
            androidx.core.view.ViewCompat.setBackgroundTintList(r11, r0)
        L_0x007c:
            int r0 = r9.k
            if (r0 != r2) goto L_0x0082
            r0 = 4
            goto L_0x0083
        L_0x0082:
            r0 = r4
        L_0x0083:
            int r5 = r11.getVisibility()
            if (r5 == r0) goto L_0x008c
            r11.setVisibility(r0)
        L_0x008c:
            r9.c()
            int r0 = androidx.core.view.ViewCompat.getImportantForAccessibility(r11)
            if (r0 != 0) goto L_0x0098
            androidx.core.view.ViewCompat.setImportantForAccessibility(r11, r1)
        L_0x0098:
            java.lang.CharSequence r0 = androidx.core.view.ViewCompat.getAccessibilityPaneTitle(r11)
            if (r0 != 0) goto L_0x00ac
            android.content.res.Resources r0 = r11.getResources()
            r5 = 2131888557(0x7f1209ad, float:1.9411753E38)
            java.lang.String r0 = r0.getString(r5)
            androidx.core.view.ViewCompat.setAccessibilityPaneTitle(r11, r0)
        L_0x00ac:
            android.view.ViewGroup$LayoutParams r0 = r11.getLayoutParams()
            androidx.coordinatorlayout.widget.CoordinatorLayout$LayoutParams r0 = (androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams) r0
            int r0 = r0.gravity
            int r0 = androidx.core.view.GravityCompat.getAbsoluteGravity(r0, r12)
            r5 = 3
            if (r0 != r5) goto L_0x00bd
            r0 = r1
            goto L_0x00be
        L_0x00bd:
            r0 = r4
        L_0x00be:
            Gd.a r6 = r9.d
            if (r6 == 0) goto L_0x00c8
            int r6 = r6.H()
            if (r6 == r0) goto L_0x0162
        L_0x00c8:
            r6 = 0
            x2.k r7 = r9.g
            if (r0 != 0) goto L_0x0117
            y2.a r0 = new y2.a
            r8 = 1
            r0.<init>(r9, r8)
            r9.d = r0
            if (r7 == 0) goto L_0x0162
            java.lang.ref.WeakReference r0 = r9.s
            if (r0 == 0) goto L_0x00f2
            java.lang.Object r0 = r0.get()
            android.view.View r0 = (android.view.View) r0
            if (r0 == 0) goto L_0x00f2
            android.view.ViewGroup$LayoutParams r8 = r0.getLayoutParams()
            boolean r8 = r8 instanceof androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams
            if (r8 == 0) goto L_0x00f2
            android.view.ViewGroup$LayoutParams r0 = r0.getLayoutParams()
            r6 = r0
            androidx.coordinatorlayout.widget.CoordinatorLayout$LayoutParams r6 = (androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams) r6
        L_0x00f2:
            if (r6 == 0) goto L_0x00f9
            int r0 = r6.rightMargin
            if (r0 <= 0) goto L_0x00f9
            goto L_0x0162
        L_0x00f9:
            x2.j r0 = r7.e()
            x2.a r6 = new x2.a
            r6.<init>(r3)
            r0.f = r6
            x2.a r6 = new x2.a
            r6.<init>(r3)
            r0.g = r6
            x2.k r0 = r0.a()
            x2.g r3 = r9.e
            if (r3 == 0) goto L_0x0162
            r3.setShapeAppearanceModel(r0)
            goto L_0x0162
        L_0x0117:
            if (r0 != r1) goto L_0x0200
            y2.a r0 = new y2.a
            r8 = 0
            r0.<init>(r9, r8)
            r9.d = r0
            if (r7 == 0) goto L_0x0162
            java.lang.ref.WeakReference r0 = r9.s
            if (r0 == 0) goto L_0x013e
            java.lang.Object r0 = r0.get()
            android.view.View r0 = (android.view.View) r0
            if (r0 == 0) goto L_0x013e
            android.view.ViewGroup$LayoutParams r8 = r0.getLayoutParams()
            boolean r8 = r8 instanceof androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams
            if (r8 == 0) goto L_0x013e
            android.view.ViewGroup$LayoutParams r0 = r0.getLayoutParams()
            r6 = r0
            androidx.coordinatorlayout.widget.CoordinatorLayout$LayoutParams r6 = (androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams) r6
        L_0x013e:
            if (r6 == 0) goto L_0x0145
            int r0 = r6.leftMargin
            if (r0 <= 0) goto L_0x0145
            goto L_0x0162
        L_0x0145:
            x2.j r0 = r7.e()
            x2.a r6 = new x2.a
            r6.<init>(r3)
            r0.e = r6
            x2.a r6 = new x2.a
            r6.<init>(r3)
            r0.f2119h = r6
            x2.k r0 = r0.a()
            x2.g r3 = r9.e
            if (r3 == 0) goto L_0x0162
            r3.setShapeAppearanceModel(r0)
        L_0x0162:
            androidx.customview.widget.ViewDragHelper r0 = r9.l
            if (r0 != 0) goto L_0x016e
            y2.b r0 = r9.y
            androidx.customview.widget.ViewDragHelper r0 = androidx.customview.widget.ViewDragHelper.create(r10, r0)
            r9.l = r0
        L_0x016e:
            Gd.a r0 = r9.d
            int r0 = r0.E(r11)
            r10.onLayoutChild(r11, r12)
            int r12 = r10.getWidth()
            r9.f1493p = r12
            Gd.a r12 = r9.d
            int r12 = r12.F(r10)
            r9.q = r12
            int r12 = r11.getWidth()
            r9.f1492o = r12
            android.view.ViewGroup$LayoutParams r12 = r11.getLayoutParams()
            android.view.ViewGroup$MarginLayoutParams r12 = (android.view.ViewGroup.MarginLayoutParams) r12
            if (r12 == 0) goto L_0x019a
            Gd.a r3 = r9.d
            int r12 = r3.c(r12)
            goto L_0x019b
        L_0x019a:
            r12 = r4
        L_0x019b:
            r9.r = r12
            int r12 = r9.k
            if (r12 == r1) goto L_0x01c5
            r3 = 2
            if (r12 == r3) goto L_0x01c5
            if (r12 == r5) goto L_0x01cd
            if (r12 != r2) goto L_0x01af
            Gd.a r12 = r9.d
            int r4 = r12.y()
            goto L_0x01cd
        L_0x01af:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r12 = "Unexpected value: "
            r11.<init>(r12)
            int r9 = r9.k
            r11.append(r9)
            java.lang.String r9 = r11.toString()
            r10.<init>(r9)
            throw r10
        L_0x01c5:
            Gd.a r12 = r9.d
            int r12 = r12.E(r11)
            int r4 = r0 - r12
        L_0x01cd:
            androidx.core.view.ViewCompat.offsetLeftAndRight(r11, r4)
            java.lang.ref.WeakReference r11 = r9.t
            if (r11 != 0) goto L_0x01e6
            r11 = -1
            int r12 = r9.u
            if (r12 == r11) goto L_0x01e6
            android.view.View r10 = r10.findViewById(r12)
            if (r10 == 0) goto L_0x01e6
            java.lang.ref.WeakReference r11 = new java.lang.ref.WeakReference
            r11.<init>(r10)
            r9.t = r11
        L_0x01e6:
            java.util.LinkedHashSet r9 = r9.f1494x
            java.util.Iterator r9 = r9.iterator()
        L_0x01ec:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L_0x01ff
            java.lang.Object r10 = r9.next()
            if (r10 != 0) goto L_0x01f9
            goto L_0x01ec
        L_0x01f9:
            java.lang.ClassCastException r9 = new java.lang.ClassCastException
            r9.<init>()
            throw r9
        L_0x01ff:
            return r1
        L_0x0200:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r10 = "Invalid sheet edge position value: "
            java.lang.String r11 = ". Must be 0 or 1."
            java.lang.String r10 = i.C0212a.j(r0, r10, r11)
            r9.<init>(r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.sidesheet.SideSheetBehavior.onLayoutChild(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, int):boolean");
    }

    public final boolean onMeasureChild(CoordinatorLayout coordinatorLayout, View view, int i2, int i7, int i8, int i10) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(ViewGroup.getChildMeasureSpec(i2, coordinatorLayout.getPaddingRight() + coordinatorLayout.getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i7, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i8, coordinatorLayout.getPaddingBottom() + coordinatorLayout.getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i10, marginLayoutParams.height));
        return true;
    }

    public final void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, View view, Parcelable parcelable) {
        C0359c cVar = (C0359c) parcelable;
        if (cVar.getSuperState() != null) {
            super.onRestoreInstanceState(coordinatorLayout, view, cVar.getSuperState());
        }
        int i2 = cVar.d;
        if (i2 == 1 || i2 == 2) {
            i2 = 5;
        }
        this.k = i2;
    }

    public final Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, View view) {
        return new C0359c(super.onSaveInstanceState(coordinatorLayout, view), this);
    }

    public final boolean onTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        VelocityTracker velocityTracker;
        if (!view.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (this.k == 1 && actionMasked == 0) {
            return true;
        }
        if (a()) {
            this.l.processTouchEvent(motionEvent);
        }
        if (actionMasked == 0 && (velocityTracker = this.v) != null) {
            velocityTracker.recycle();
            this.v = null;
        }
        if (this.v == null) {
            this.v = VelocityTracker.obtain();
        }
        this.v.addMovement(motionEvent);
        if (a() && actionMasked == 2 && !this.m && a() && Math.abs(((float) this.w) - motionEvent.getX()) > ((float) this.l.getTouchSlop())) {
            this.l.captureChildView(view, motionEvent.getPointerId(motionEvent.getActionIndex()));
        }
        return !this.m;
    }

    public final void setStateInternal(int i2) {
        View view;
        int i7;
        if (this.k != i2) {
            this.k = i2;
            WeakReference weakReference = this.s;
            if (weakReference != null && (view = (View) weakReference.get()) != null) {
                if (this.k == 5) {
                    i7 = 4;
                } else {
                    i7 = 0;
                }
                if (view.getVisibility() != i7) {
                    view.setVisibility(i7);
                }
                Iterator it = this.f1494x.iterator();
                if (!it.hasNext()) {
                    c();
                    return;
                }
                throw C0212a.h(it);
            }
        }
    }

    public SideSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, Q1.a.f621K);
        if (obtainStyledAttributes.hasValue(3)) {
            this.f = B1.a.u(context, obtainStyledAttributes, 3);
        }
        if (obtainStyledAttributes.hasValue(6)) {
            this.g = C0344k.b(context, attributeSet, 0, 2131953105).a();
        }
        if (obtainStyledAttributes.hasValue(5)) {
            int resourceId = obtainStyledAttributes.getResourceId(5, -1);
            this.u = resourceId;
            WeakReference weakReference = this.t;
            if (weakReference != null) {
                weakReference.clear();
            }
            this.t = null;
            WeakReference weakReference2 = this.s;
            if (weakReference2 != null) {
                View view = (View) weakReference2.get();
                if (resourceId != -1 && ViewCompat.isLaidOut(view)) {
                    view.requestLayout();
                }
            }
        }
        C0344k kVar = this.g;
        if (kVar != null) {
            C0340g gVar = new C0340g(kVar);
            this.e = gVar;
            gVar.i(context);
            ColorStateList colorStateList = this.f;
            if (colorStateList != null) {
                this.e.k(colorStateList);
            } else {
                TypedValue typedValue = new TypedValue();
                context.getTheme().resolveAttribute(16842801, typedValue, true);
                this.e.setTint(typedValue.data);
            }
        }
        this.f1490i = obtainStyledAttributes.getDimension(2, -1.0f);
        this.f1491j = obtainStyledAttributes.getBoolean(4, true);
        obtainStyledAttributes.recycle();
        ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }
}
