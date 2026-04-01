package com.google.android.material.bottomsheet;

import Y1.a;
import Y1.b;
import Y1.c;
import Y1.d;
import Y1.e;
import Y1.f;
import Y1.g;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.animation.PathInterpolatorCompat;
import androidx.customview.widget.ViewDragHelper;
import c0.C0086a;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.sdk.scs.base.StatusCodes;
import com.sec.android.gallery3d.R;
import h2.u;
import i.C0212a;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import og.k;
import x2.C0340g;
import x2.C0344k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BottomSheetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {

    /* renamed from: A  reason: collision with root package name */
    public final boolean f1405A;
    public final C0344k B;

    /* renamed from: C  reason: collision with root package name */
    public boolean f1406C;
    public final f D = new f(this);
    public final ValueAnimator E;

    /* renamed from: F  reason: collision with root package name */
    public final int f1407F;

    /* renamed from: G  reason: collision with root package name */
    public int f1408G;

    /* renamed from: H  reason: collision with root package name */
    public int f1409H;

    /* renamed from: I  reason: collision with root package name */
    public final float f1410I = 0.5f;

    /* renamed from: J  reason: collision with root package name */
    public int f1411J;

    /* renamed from: K  reason: collision with root package name */
    public final float f1412K = -1.0f;
    public boolean L;

    /* renamed from: M  reason: collision with root package name */
    public boolean f1413M;

    /* renamed from: N  reason: collision with root package name */
    public final boolean f1414N = true;

    /* renamed from: O  reason: collision with root package name */
    public int f1415O = 4;

    /* renamed from: P  reason: collision with root package name */
    public ViewDragHelper f1416P;
    public boolean Q;
    public int R;
    public boolean S;
    public final float T = 0.1f;
    public int U;
    public int V;

    /* renamed from: W  reason: collision with root package name */
    public int f1417W;

    /* renamed from: X  reason: collision with root package name */
    public WeakReference f1418X;
    public WeakReference Y;
    public final ArrayList Z = new ArrayList();
    public VelocityTracker a0;
    public int b0;

    /* renamed from: c0  reason: collision with root package name */
    public int f1419c0 = -1;
    public final int d = 0;
    public boolean d0;
    public boolean e = true;

    /* renamed from: e0  reason: collision with root package name */
    public HashMap f1420e0;
    public final float f;

    /* renamed from: f0  reason: collision with root package name */
    public final SparseIntArray f1421f0 = new SparseIntArray();
    public final int g;

    /* renamed from: g0  reason: collision with root package name */
    public final c f1422g0 = new c(this);

    /* renamed from: h  reason: collision with root package name */
    public int f1423h;

    /* renamed from: i  reason: collision with root package name */
    public boolean f1424i;

    /* renamed from: j  reason: collision with root package name */
    public int f1425j;
    public final int k;
    public final C0340g l;
    public final ColorStateList m;
    public final int n = -1;

    /* renamed from: o  reason: collision with root package name */
    public final int f1426o = -1;

    /* renamed from: p  reason: collision with root package name */
    public int f1427p;
    public final boolean q;
    public final boolean r;
    public final boolean s;
    public final boolean t;
    public final boolean u;
    public final boolean v;
    public final boolean w;

    /* renamed from: x  reason: collision with root package name */
    public final boolean f1428x;
    public int y;
    public int z;

    public BottomSheetBehavior() {
    }

    public static int c(int i2, int i7, int i8, int i10) {
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, i7, i10);
        if (i8 == -1) {
            return childMeasureSpec;
        }
        int mode = View.MeasureSpec.getMode(childMeasureSpec);
        int size = View.MeasureSpec.getSize(childMeasureSpec);
        if (mode == 1073741824) {
            return View.MeasureSpec.makeMeasureSpec(Math.min(size, i8), 1073741824);
        }
        if (size != 0) {
            i8 = Math.min(size, i8);
        }
        return View.MeasureSpec.makeMeasureSpec(i8, Integer.MIN_VALUE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x005b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final float a() {
        /*
            r5 = this;
            x2.g r0 = r5.l
            r1 = 0
            if (r0 == 0) goto L_0x006f
            java.lang.ref.WeakReference r0 = r5.f1418X
            if (r0 == 0) goto L_0x006f
            java.lang.Object r0 = r0.get()
            if (r0 == 0) goto L_0x006f
            int r0 = android.os.Build.VERSION.SDK_INT
            r2 = 31
            if (r0 < r2) goto L_0x006f
            java.lang.ref.WeakReference r0 = r5.f1418X
            java.lang.Object r0 = r0.get()
            android.view.View r0 = (android.view.View) r0
            boolean r2 = r5.d()
            if (r2 == 0) goto L_0x006f
            android.view.WindowInsets r0 = r0.getRootWindowInsets()
            if (r0 == 0) goto L_0x006f
            x2.g r2 = r5.l
            float r2 = r2.g()
            android.view.RoundedCorner r3 = r0.getRoundedCorner(0)
            if (r3 == 0) goto L_0x0044
            int r3 = r3.getRadius()
            float r3 = (float) r3
            int r4 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r4 <= 0) goto L_0x0044
            int r4 = (r2 > r1 ? 1 : (r2 == r1 ? 0 : -1))
            if (r4 <= 0) goto L_0x0044
            float r3 = r3 / r2
            goto L_0x0045
        L_0x0044:
            r3 = r1
        L_0x0045:
            x2.g r5 = r5.l
            x2.f r2 = r5.d
            x2.k r2 = r2.f2103a
            x2.c r2 = r2.f
            android.graphics.RectF r5 = r5.f()
            float r5 = r2.a(r5)
            android.view.RoundedCorner r0 = r0.getRoundedCorner(1)
            if (r0 == 0) goto L_0x006a
            int r0 = r0.getRadius()
            float r0 = (float) r0
            int r2 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r2 <= 0) goto L_0x006a
            int r2 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r2 <= 0) goto L_0x006a
            float r1 = r0 / r5
        L_0x006a:
            float r5 = java.lang.Math.max(r3, r1)
            return r5
        L_0x006f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.a():float");
    }

    public final int b() {
        int i2;
        int i7;
        int i8;
        if (this.f1424i) {
            i2 = Math.min(Math.max(this.f1425j, this.f1417W - ((this.V * 9) / 16)), this.U);
            i7 = this.y;
        } else if (!this.q && !this.r && (i8 = this.f1427p) > 0) {
            return Math.max(this.f1423h, i8 + this.k);
        } else {
            i2 = this.f1423h;
            i7 = this.y;
        }
        return i2 + i7;
    }

    public final void calculateCollapsedOffset() {
        int b = b();
        if (this.e) {
            this.f1411J = Math.max(this.f1417W - b, this.f1408G);
        } else {
            this.f1411J = this.f1417W - b;
        }
    }

    public final boolean d() {
        WeakReference weakReference = this.f1418X;
        if (!(weakReference == null || weakReference.get() == null)) {
            int[] iArr = new int[2];
            ((View) this.f1418X.get()).getLocationOnScreen(iArr);
            if (iArr[1] == 0) {
                return true;
            }
        }
        return false;
    }

    public final void dispatchOnSlide(int i2) {
        if (((View) this.f1418X.get()) != null) {
            ArrayList arrayList = this.Z;
            if (!arrayList.isEmpty()) {
                int i7 = this.f1411J;
                if (i2 <= i7 && i7 != getExpandedOffset()) {
                    getExpandedOffset();
                }
                if (arrayList.size() > 0) {
                    arrayList.get(0).getClass();
                    throw new ClassCastException();
                }
            }
        }
    }

    public final boolean e(float f5, View view) {
        if (this.f1413M) {
            return true;
        }
        if (view.getTop() < this.f1411J) {
            return false;
        }
        int b = b();
        if (Math.abs(((f5 * this.T) + ((float) view.getTop())) - ((float) this.f1411J)) / ((float) b) > 0.5f) {
            return true;
        }
        return false;
    }

    public final void f(int i2, boolean z3) {
        boolean z7;
        C0340g gVar;
        if (i2 != 2) {
            if (this.f1415O != 3 || (!this.f1405A && !d())) {
                z7 = false;
            } else {
                z7 = true;
            }
            if (this.f1406C != z7 && (gVar = this.l) != null) {
                this.f1406C = z7;
                ValueAnimator valueAnimator = this.E;
                float f5 = 1.0f;
                if (!z3 || valueAnimator == null) {
                    if (valueAnimator != null && valueAnimator.isRunning()) {
                        valueAnimator.cancel();
                    }
                    if (this.f1406C) {
                        f5 = a();
                    }
                    gVar.l(f5);
                } else if (valueAnimator.isRunning()) {
                    valueAnimator.reverse();
                } else {
                    float f8 = gVar.d.f2106i;
                    if (z7) {
                        f5 = a();
                    }
                    valueAnimator.setFloatValues(new float[]{f8, f5});
                    valueAnimator.start();
                }
            }
        }
    }

    public final View findScrollingChild(View view) {
        if (view.getVisibility() != 0) {
            return null;
        }
        if (ViewCompat.isNestedScrollingEnabled(view)) {
            return view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View findScrollingChild = findScrollingChild(viewGroup.getChildAt(i2));
                if (findScrollingChild != null) {
                    return findScrollingChild;
                }
            }
        }
        return null;
    }

    public final void g() {
        View view;
        if (this.f1418X != null) {
            calculateCollapsedOffset();
            if (this.f1415O == 4 && (view = (View) this.f1418X.get()) != null) {
                view.requestLayout();
            }
        }
    }

    public final int getExpandedOffset() {
        int i2;
        if (this.e) {
            return this.f1408G;
        }
        if (this.u) {
            i2 = 0;
        } else {
            i2 = this.z;
        }
        return Math.max(this.f1407F, i2);
    }

    public final int getTopOffsetForState(int i2) {
        if (i2 == 3) {
            return getExpandedOffset();
        }
        if (i2 == 4) {
            return this.f1411J;
        }
        if (i2 == 5) {
            return this.f1417W;
        }
        if (i2 == 6) {
            return this.f1409H;
        }
        throw new IllegalArgumentException(C0086a.i(i2, "Invalid state to get top offset: "));
    }

    public final void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams layoutParams) {
        super.onAttachedToLayoutParams(layoutParams);
        this.f1418X = null;
        this.f1416P = null;
    }

    public final void onDetachedFromLayoutParams() {
        super.onDetachedFromLayoutParams();
        this.f1418X = null;
        this.f1416P = null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: android.view.View} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onInterceptTouchEvent(androidx.coordinatorlayout.widget.CoordinatorLayout r10, android.view.View r11, android.view.MotionEvent r12) {
        /*
            r9 = this;
            boolean r0 = r11.isShown()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x00de
            boolean r0 = r9.f1414N
            if (r0 != 0) goto L_0x000e
            goto L_0x00de
        L_0x000e:
            int r0 = r12.getActionMasked()
            r3 = 0
            r4 = -1
            if (r0 != 0) goto L_0x0023
            r9.b0 = r4
            r9.f1419c0 = r4
            android.view.VelocityTracker r5 = r9.a0
            if (r5 == 0) goto L_0x0023
            r5.recycle()
            r9.a0 = r3
        L_0x0023:
            android.view.VelocityTracker r5 = r9.a0
            if (r5 != 0) goto L_0x002d
            android.view.VelocityTracker r5 = android.view.VelocityTracker.obtain()
            r9.a0 = r5
        L_0x002d:
            android.view.VelocityTracker r5 = r9.a0
            r5.addMovement(r12)
            r5 = 2
            if (r0 == 0) goto L_0x0046
            if (r0 == r2) goto L_0x003b
            r11 = 3
            if (r0 == r11) goto L_0x003b
            goto L_0x0089
        L_0x003b:
            r9.d0 = r1
            r9.b0 = r4
            boolean r11 = r9.Q
            if (r11 == 0) goto L_0x0089
            r9.Q = r1
            return r1
        L_0x0046:
            float r6 = r12.getX()
            int r6 = (int) r6
            float r7 = r12.getY()
            int r7 = (int) r7
            r9.f1419c0 = r7
            int r7 = r9.f1415O
            if (r7 == r5) goto L_0x0078
            java.lang.ref.WeakReference r7 = r9.Y
            if (r7 == 0) goto L_0x0061
            java.lang.Object r7 = r7.get()
            android.view.View r7 = (android.view.View) r7
            goto L_0x0062
        L_0x0061:
            r7 = r3
        L_0x0062:
            if (r7 == 0) goto L_0x0078
            int r8 = r9.f1419c0
            boolean r7 = r10.isPointInChildBounds(r7, r6, r8)
            if (r7 == 0) goto L_0x0078
            int r7 = r12.getActionIndex()
            int r7 = r12.getPointerId(r7)
            r9.b0 = r7
            r9.d0 = r2
        L_0x0078:
            int r7 = r9.b0
            if (r7 != r4) goto L_0x0086
            int r7 = r9.f1419c0
            boolean r11 = r10.isPointInChildBounds(r11, r6, r7)
            if (r11 != 0) goto L_0x0086
            r11 = r2
            goto L_0x0087
        L_0x0086:
            r11 = r1
        L_0x0087:
            r9.Q = r11
        L_0x0089:
            boolean r11 = r9.Q
            if (r11 != 0) goto L_0x0098
            androidx.customview.widget.ViewDragHelper r11 = r9.f1416P
            if (r11 == 0) goto L_0x0098
            boolean r11 = r11.shouldInterceptTouchEvent(r12)
            if (r11 == 0) goto L_0x0098
            goto L_0x00dc
        L_0x0098:
            java.lang.ref.WeakReference r11 = r9.Y
            if (r11 == 0) goto L_0x00a3
            java.lang.Object r11 = r11.get()
            r3 = r11
            android.view.View r3 = (android.view.View) r3
        L_0x00a3:
            if (r0 != r5) goto L_0x00dd
            if (r3 == 0) goto L_0x00dd
            boolean r11 = r9.Q
            if (r11 != 0) goto L_0x00dd
            int r11 = r9.f1415O
            if (r11 == r2) goto L_0x00dd
            float r11 = r12.getX()
            int r11 = (int) r11
            float r0 = r12.getY()
            int r0 = (int) r0
            boolean r10 = r10.isPointInChildBounds(r3, r11, r0)
            if (r10 != 0) goto L_0x00dd
            androidx.customview.widget.ViewDragHelper r10 = r9.f1416P
            if (r10 == 0) goto L_0x00dd
            int r10 = r9.f1419c0
            if (r10 == r4) goto L_0x00dd
            float r10 = (float) r10
            float r11 = r12.getY()
            float r10 = r10 - r11
            float r10 = java.lang.Math.abs(r10)
            androidx.customview.widget.ViewDragHelper r9 = r9.f1416P
            int r9 = r9.getTouchSlop()
            float r9 = (float) r9
            int r9 = (r10 > r9 ? 1 : (r10 == r9 ? 0 : -1))
            if (r9 <= 0) goto L_0x00dd
        L_0x00dc:
            return r2
        L_0x00dd:
            return r1
        L_0x00de:
            r9.Q = r2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.onInterceptTouchEvent(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.MotionEvent):boolean");
    }

    public final boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i2) {
        boolean z3;
        if (ViewCompat.getFitsSystemWindows(coordinatorLayout) && !ViewCompat.getFitsSystemWindows(view)) {
            view.setFitsSystemWindows(true);
        }
        if (this.f1418X == null) {
            this.f1425j = coordinatorLayout.getResources().getDimensionPixelSize(R.dimen.design_bottom_sheet_peek_height_min);
            if (this.q || this.f1424i) {
                z3 = false;
            } else {
                z3 = true;
            }
            if (this.r || this.s || this.t || this.v || this.w || this.f1428x || z3) {
                u.a(view, new b(this, z3));
            }
            ViewCompat.setWindowInsetsAnimationCallback(view, new g(view));
            this.f1418X = new WeakReference(view);
            Context context = view.getContext();
            k.M(context, R.attr.motionEasingStandardDecelerateInterpolator, PathInterpolatorCompat.create(0.0f, 0.0f, 0.0f, 1.0f));
            k.L(context, R.attr.motionDurationMedium2, StatusCodes.INPUT_MISSING);
            k.L(context, R.attr.motionDurationShort3, 150);
            k.L(context, R.attr.motionDurationShort2, 100);
            Resources resources = view.getResources();
            resources.getDimension(R.dimen.m3_back_progress_bottom_container_max_scale_x_distance);
            resources.getDimension(R.dimen.m3_back_progress_bottom_container_max_scale_y_distance);
            C0340g gVar = this.l;
            if (gVar != null) {
                ViewCompat.setBackground(view, gVar);
                float f5 = this.f1412K;
                if (f5 == -1.0f) {
                    f5 = ViewCompat.getElevation(view);
                }
                this.l.j(f5);
            } else {
                ColorStateList colorStateList = this.m;
                if (colorStateList != null) {
                    ViewCompat.setBackgroundTintList(view, colorStateList);
                }
            }
            updateAccessibilityActions();
            if (ViewCompat.getImportantForAccessibility(view) == 0) {
                ViewCompat.setImportantForAccessibility(view, 1);
            }
        }
        if (this.f1416P == null) {
            this.f1416P = ViewDragHelper.create(coordinatorLayout, this.f1422g0);
        }
        int top = view.getTop();
        coordinatorLayout.onLayoutChild(view, i2);
        this.V = coordinatorLayout.getWidth();
        this.f1417W = coordinatorLayout.getHeight();
        int height = view.getHeight();
        this.U = height;
        int i7 = this.f1417W;
        int i8 = i7 - height;
        int i10 = this.z;
        if (i8 < i10) {
            if (this.u) {
                int i11 = this.f1426o;
                if (i11 != -1) {
                    i7 = Math.min(i7, i11);
                }
                this.U = i7;
            } else {
                int i12 = i7 - i10;
                int i13 = this.f1426o;
                if (i13 != -1) {
                    i12 = Math.min(i12, i13);
                }
                this.U = i12;
            }
        }
        this.f1408G = Math.max(0, this.f1417W - this.U);
        this.f1409H = (int) ((1.0f - this.f1410I) * ((float) this.f1417W));
        calculateCollapsedOffset();
        int i14 = this.f1415O;
        if (i14 == 3) {
            ViewCompat.offsetTopAndBottom(view, getExpandedOffset());
        } else if (i14 == 6) {
            ViewCompat.offsetTopAndBottom(view, this.f1409H);
        } else if (this.L && i14 == 5) {
            ViewCompat.offsetTopAndBottom(view, this.f1417W);
        } else if (i14 == 4) {
            ViewCompat.offsetTopAndBottom(view, this.f1411J);
        } else if (i14 == 1 || i14 == 2) {
            ViewCompat.offsetTopAndBottom(view, top - view.getTop());
        }
        f(this.f1415O, false);
        this.Y = new WeakReference(findScrollingChild(view));
        ArrayList arrayList = this.Z;
        if (arrayList.size() <= 0) {
            return true;
        }
        arrayList.get(0).getClass();
        throw new ClassCastException();
    }

    public final boolean onMeasureChild(CoordinatorLayout coordinatorLayout, View view, int i2, int i7, int i8, int i10) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(c(i2, coordinatorLayout.getPaddingRight() + coordinatorLayout.getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i7, this.n, marginLayoutParams.width), c(i8, coordinatorLayout.getPaddingBottom() + coordinatorLayout.getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i10, this.f1426o, marginLayoutParams.height));
        return true;
    }

    public final boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View view, View view2, float f5, float f8) {
        WeakReference weakReference = this.Y;
        if (weakReference == null || view2 != weakReference.get() || (this.f1415O == 3 && !super.onNestedPreFling(coordinatorLayout, view, view2, f5, f8))) {
            return false;
        }
        return true;
    }

    public final void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i2, int i7, int[] iArr, int i8) {
        View view3;
        if (i8 != 1) {
            WeakReference weakReference = this.Y;
            if (weakReference != null) {
                view3 = (View) weakReference.get();
            } else {
                view3 = null;
            }
            if (view2 == view3) {
                int top = view.getTop();
                int i10 = top - i7;
                if (i7 > 0) {
                    if (i10 < getExpandedOffset()) {
                        int expandedOffset = top - getExpandedOffset();
                        iArr[1] = expandedOffset;
                        ViewCompat.offsetTopAndBottom(view, -expandedOffset);
                        setStateInternal(3);
                    } else if (this.f1414N) {
                        iArr[1] = i7;
                        ViewCompat.offsetTopAndBottom(view, -i7);
                        setStateInternal(1);
                    } else {
                        return;
                    }
                } else if (i7 < 0 && !view2.canScrollVertically(-1)) {
                    int i11 = this.f1411J;
                    if (i10 > i11 && !this.L) {
                        int i12 = top - i11;
                        iArr[1] = i12;
                        ViewCompat.offsetTopAndBottom(view, -i12);
                        setStateInternal(4);
                    } else if (this.f1414N) {
                        iArr[1] = i7;
                        ViewCompat.offsetTopAndBottom(view, -i7);
                        setStateInternal(1);
                    } else {
                        return;
                    }
                }
                dispatchOnSlide(view.getTop());
                this.R = i7;
                this.S = true;
            }
        }
    }

    public final void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, View view, Parcelable parcelable) {
        e eVar = (e) parcelable;
        super.onRestoreInstanceState(coordinatorLayout, view, eVar.getSuperState());
        int i2 = this.d;
        if (i2 != 0) {
            if (i2 == -1 || (i2 & 1) == 1) {
                this.f1423h = eVar.e;
            }
            if (i2 == -1 || (i2 & 2) == 2) {
                this.e = eVar.f;
            }
            if (i2 == -1 || (i2 & 4) == 4) {
                this.L = eVar.g;
            }
            if (i2 == -1 || (i2 & 8) == 8) {
                this.f1413M = eVar.f949h;
            }
        }
        int i7 = eVar.d;
        if (i7 == 1 || i7 == 2) {
            this.f1415O = 4;
        } else {
            this.f1415O = i7;
        }
    }

    public final Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, View view) {
        return new e(super.onSaveInstanceState(coordinatorLayout, view), this);
    }

    public final boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i2, int i7) {
        this.R = 0;
        this.S = false;
        if ((i2 & 2) != 0) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0030, code lost:
        if (r4.getTop() <= r2.f1409H) goto L_0x00af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0071, code lost:
        if (java.lang.Math.abs(r3 - r2.f1408G) < java.lang.Math.abs(r3 - r2.f1411J)) goto L_0x00af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0080, code lost:
        if (r3 < java.lang.Math.abs(r3 - r2.f1411J)) goto L_0x00af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0090, code lost:
        if (java.lang.Math.abs(r3 - r1) < java.lang.Math.abs(r3 - r2.f1411J)) goto L_0x00ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00ac, code lost:
        if (java.lang.Math.abs(r3 - r2.f1409H) < java.lang.Math.abs(r3 - r2.f1411J)) goto L_0x00ae;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onStopNestedScroll(androidx.coordinatorlayout.widget.CoordinatorLayout r3, android.view.View r4, android.view.View r5, int r6) {
        /*
            r2 = this;
            int r3 = r4.getTop()
            int r6 = r2.getExpandedOffset()
            r0 = 3
            if (r3 != r6) goto L_0x000f
            r2.setStateInternal(r0)
            return
        L_0x000f:
            java.lang.ref.WeakReference r3 = r2.Y
            if (r3 == 0) goto L_0x00b5
            java.lang.Object r3 = r3.get()
            if (r5 != r3) goto L_0x00b5
            boolean r3 = r2.S
            if (r3 != 0) goto L_0x001f
            goto L_0x00b5
        L_0x001f:
            int r3 = r2.R
            r5 = 6
            if (r3 <= 0) goto L_0x0034
            boolean r3 = r2.e
            if (r3 == 0) goto L_0x002a
            goto L_0x00af
        L_0x002a:
            int r3 = r4.getTop()
            int r6 = r2.f1409H
            if (r3 <= r6) goto L_0x00af
            goto L_0x00ae
        L_0x0034:
            boolean r3 = r2.L
            if (r3 == 0) goto L_0x0055
            android.view.VelocityTracker r3 = r2.a0
            if (r3 != 0) goto L_0x003e
            r3 = 0
            goto L_0x004d
        L_0x003e:
            r6 = 1000(0x3e8, float:1.401E-42)
            float r1 = r2.f
            r3.computeCurrentVelocity(r6, r1)
            android.view.VelocityTracker r3 = r2.a0
            int r6 = r2.b0
            float r3 = r3.getYVelocity(r6)
        L_0x004d:
            boolean r3 = r2.e(r3, r4)
            if (r3 == 0) goto L_0x0055
            r0 = 5
            goto L_0x00af
        L_0x0055:
            int r3 = r2.R
            r6 = 4
            if (r3 != 0) goto L_0x0093
            int r3 = r4.getTop()
            boolean r1 = r2.e
            if (r1 == 0) goto L_0x0074
            int r5 = r2.f1408G
            int r5 = r3 - r5
            int r5 = java.lang.Math.abs(r5)
            int r1 = r2.f1411J
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r5 >= r3) goto L_0x0097
            goto L_0x00af
        L_0x0074:
            int r1 = r2.f1409H
            if (r3 >= r1) goto L_0x0083
            int r6 = r2.f1411J
            int r6 = r3 - r6
            int r6 = java.lang.Math.abs(r6)
            if (r3 >= r6) goto L_0x00ae
            goto L_0x00af
        L_0x0083:
            int r0 = r3 - r1
            int r0 = java.lang.Math.abs(r0)
            int r1 = r2.f1411J
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r0 >= r3) goto L_0x0097
            goto L_0x00ae
        L_0x0093:
            boolean r3 = r2.e
            if (r3 == 0) goto L_0x0099
        L_0x0097:
            r0 = r6
            goto L_0x00af
        L_0x0099:
            int r3 = r4.getTop()
            int r0 = r2.f1409H
            int r0 = r3 - r0
            int r0 = java.lang.Math.abs(r0)
            int r1 = r2.f1411J
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r0 >= r3) goto L_0x0097
        L_0x00ae:
            r0 = r5
        L_0x00af:
            r3 = 0
            r2.startSettling(r4, r0, r3)
            r2.S = r3
        L_0x00b5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.onStopNestedScroll(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.View, int):void");
    }

    public final boolean onTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        if (!view.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        int i2 = this.f1415O;
        if (i2 == 1 && actionMasked == 0) {
            return true;
        }
        ViewDragHelper viewDragHelper = this.f1416P;
        if (viewDragHelper != null && (this.f1414N || i2 == 1)) {
            viewDragHelper.processTouchEvent(motionEvent);
        }
        if (actionMasked == 0) {
            this.b0 = -1;
            this.f1419c0 = -1;
            VelocityTracker velocityTracker = this.a0;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.a0 = null;
            }
        }
        if (this.a0 == null) {
            this.a0 = VelocityTracker.obtain();
        }
        this.a0.addMovement(motionEvent);
        if (this.f1416P != null && ((this.f1414N || this.f1415O == 1) && actionMasked == 2 && !this.Q && Math.abs(((float) this.f1419c0) - motionEvent.getY()) > ((float) this.f1416P.getTouchSlop()))) {
            this.f1416P.captureChildView(view, motionEvent.getPointerId(motionEvent.getActionIndex()));
        }
        return !this.Q;
    }

    public final void setPeekHeight(int i2) {
        if (i2 == -1) {
            if (!this.f1424i) {
                this.f1424i = true;
            } else {
                return;
            }
        } else if (this.f1424i || this.f1423h != i2) {
            this.f1424i = false;
            this.f1423h = Math.max(0, i2);
        } else {
            return;
        }
        g();
    }

    public final void setState(int i2) {
        String str;
        int i7;
        if (i2 == 1 || i2 == 2) {
            StringBuilder sb2 = new StringBuilder("STATE_");
            if (i2 == 1) {
                str = "DRAGGING";
            } else {
                str = "SETTLING";
            }
            throw new IllegalArgumentException(C0212a.p(sb2, str, " should not be set externally."));
        } else if (this.L || i2 != 5) {
            if (i2 != 6 || !this.e || getTopOffsetForState(i2) > this.f1408G) {
                i7 = i2;
            } else {
                i7 = 3;
            }
            WeakReference weakReference = this.f1418X;
            if (weakReference == null || weakReference.get() == null) {
                setStateInternal(i2);
                return;
            }
            View view = (View) this.f1418X.get();
            a aVar = new a(this, view, i7);
            ViewParent parent = view.getParent();
            if (parent == null || !parent.isLayoutRequested() || !ViewCompat.isAttachedToWindow(view)) {
                aVar.run();
            } else {
                view.post(aVar);
            }
        } else {
            Log.w("BottomSheetBehavior", "Cannot set state: " + i2);
        }
    }

    public final void setStateInternal(int i2) {
        if (this.f1415O != i2) {
            this.f1415O = i2;
            if (!(i2 == 4 || i2 == 3 || i2 == 6)) {
                boolean z3 = this.L;
            }
            WeakReference weakReference = this.f1418X;
            if (weakReference != null && ((View) weakReference.get()) != null) {
                if (i2 == 3) {
                    updateImportantForAccessibility(true);
                } else if (i2 == 6 || i2 == 5 || i2 == 4) {
                    updateImportantForAccessibility(false);
                }
                f(i2, true);
                ArrayList arrayList = this.Z;
                if (arrayList.size() <= 0) {
                    updateAccessibilityActions();
                } else {
                    arrayList.get(0).getClass();
                    throw new ClassCastException();
                }
            }
        }
    }

    public final void startSettling(View view, int i2, boolean z3) {
        int topOffsetForState = getTopOffsetForState(i2);
        ViewDragHelper viewDragHelper = this.f1416P;
        if (viewDragHelper == null || (!z3 ? !viewDragHelper.smoothSlideViewTo(view, view.getLeft(), topOffsetForState) : !viewDragHelper.settleCapturedViewAt(view.getLeft(), topOffsetForState))) {
            setStateInternal(i2);
            return;
        }
        setStateInternal(2);
        f(i2, true);
        this.D.b(i2);
    }

    public final void updateAccessibilityActions() {
        View view;
        WeakReference weakReference = this.f1418X;
        if (weakReference != null && (view = (View) weakReference.get()) != null) {
            ViewCompat.removeAccessibilityAction(view, 524288);
            ViewCompat.removeAccessibilityAction(view, 262144);
            ViewCompat.removeAccessibilityAction(view, MediaDefs.Meta.SEF.SEF_MIN_SIZE);
            SparseIntArray sparseIntArray = this.f1421f0;
            int i2 = sparseIntArray.get(0, -1);
            if (i2 != -1) {
                ViewCompat.removeAccessibilityAction(view, i2);
                sparseIntArray.delete(0);
            }
            int i7 = 6;
            if (!this.e && this.f1415O != 6) {
                sparseIntArray.put(0, ViewCompat.addAccessibilityAction(view, view.getResources().getString(R.string.bottomsheet_action_expand_halfway), new d(this, 6)));
            }
            if (this.L && this.f1415O != 5) {
                ViewCompat.replaceAccessibilityAction(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_DISMISS, (CharSequence) null, new d(this, 5));
            }
            int i8 = this.f1415O;
            if (i8 == 3) {
                if (this.e) {
                    i7 = 4;
                }
                ViewCompat.replaceAccessibilityAction(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE, (CharSequence) null, new d(this, i7));
            } else if (i8 == 4) {
                if (this.e) {
                    i7 = 3;
                }
                ViewCompat.replaceAccessibilityAction(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND, (CharSequence) null, new d(this, i7));
            } else if (i8 == 6) {
                ViewCompat.replaceAccessibilityAction(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE, (CharSequence) null, new d(this, 4));
                ViewCompat.replaceAccessibilityAction(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND, (CharSequence) null, new d(this, 3));
            }
        }
    }

    public final void updateImportantForAccessibility(boolean z3) {
        WeakReference weakReference = this.f1418X;
        if (weakReference != null) {
            ViewParent parent = ((View) weakReference.get()).getParent();
            if (parent instanceof CoordinatorLayout) {
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
                int childCount = coordinatorLayout.getChildCount();
                if (z3) {
                    if (this.f1420e0 == null) {
                        this.f1420e0 = new HashMap(childCount);
                    } else {
                        return;
                    }
                }
                for (int i2 = 0; i2 < childCount; i2++) {
                    View childAt = coordinatorLayout.getChildAt(i2);
                    if (childAt != this.f1418X.get() && z3) {
                        this.f1420e0.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                    }
                }
                if (!z3) {
                    this.f1420e0 = null;
                }
            }
        }
    }

    public BottomSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        int i2;
        this.k = context.getResources().getDimensionPixelSize(R.dimen.mtrl_min_touch_target_size);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, Q1.a.e);
        int i7 = 3;
        if (obtainStyledAttributes.hasValue(3)) {
            this.m = B1.a.u(context, obtainStyledAttributes, 3);
        }
        if (obtainStyledAttributes.hasValue(21)) {
            this.B = C0344k.b(context, attributeSet, R.attr.bottomSheetStyle, com.samsung.android.photoremaster.engine.R.style.Widget_Design_BottomSheet_Modal).a();
        }
        C0344k kVar = this.B;
        if (kVar != null) {
            C0340g gVar = new C0340g(kVar);
            this.l = gVar;
            gVar.i(context);
            ColorStateList colorStateList = this.m;
            if (colorStateList != null) {
                this.l.k(colorStateList);
            } else {
                TypedValue typedValue = new TypedValue();
                context.getTheme().resolveAttribute(16842801, typedValue, true);
                this.l.setTint(typedValue.data);
            }
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{a(), 1.0f});
        this.E = ofFloat;
        ofFloat.setDuration(500);
        this.E.addUpdateListener(new A2.e(3, this));
        this.f1412K = obtainStyledAttributes.getDimension(2, -1.0f);
        if (obtainStyledAttributes.hasValue(0)) {
            this.n = obtainStyledAttributes.getDimensionPixelSize(0, -1);
        }
        if (obtainStyledAttributes.hasValue(1)) {
            this.f1426o = obtainStyledAttributes.getDimensionPixelSize(1, -1);
        }
        TypedValue peekValue = obtainStyledAttributes.peekValue(9);
        if (peekValue == null || (i2 = peekValue.data) != -1) {
            setPeekHeight(obtainStyledAttributes.getDimensionPixelSize(9, -1));
        } else {
            setPeekHeight(i2);
        }
        boolean z3 = obtainStyledAttributes.getBoolean(8, false);
        if (this.L != z3) {
            this.L = z3;
            if (!z3 && this.f1415O == 5) {
                setState(4);
            }
            updateAccessibilityActions();
        }
        this.q = obtainStyledAttributes.getBoolean(13, false);
        boolean z7 = obtainStyledAttributes.getBoolean(6, true);
        if (this.e != z7) {
            this.e = z7;
            if (this.f1418X != null) {
                calculateCollapsedOffset();
            }
            setStateInternal((!this.e || this.f1415O != 6) ? this.f1415O : i7);
            f(this.f1415O, true);
            updateAccessibilityActions();
        }
        this.f1413M = obtainStyledAttributes.getBoolean(12, false);
        this.f1414N = obtainStyledAttributes.getBoolean(4, true);
        this.d = obtainStyledAttributes.getInt(10, 0);
        float f5 = obtainStyledAttributes.getFloat(7, 0.5f);
        if (f5 <= 0.0f || f5 >= 1.0f) {
            throw new IllegalArgumentException("ratio must be a float value between 0 and 1");
        }
        this.f1410I = f5;
        if (this.f1418X != null) {
            this.f1409H = (int) ((1.0f - f5) * ((float) this.f1417W));
        }
        TypedValue peekValue2 = obtainStyledAttributes.peekValue(5);
        if (peekValue2 == null || peekValue2.type != 16) {
            int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(5, 0);
            if (dimensionPixelOffset >= 0) {
                this.f1407F = dimensionPixelOffset;
                f(this.f1415O, true);
            } else {
                throw new IllegalArgumentException("offset must be greater than or equal to 0");
            }
        } else {
            int i8 = peekValue2.data;
            if (i8 >= 0) {
                this.f1407F = i8;
                f(this.f1415O, true);
            } else {
                throw new IllegalArgumentException("offset must be greater than or equal to 0");
            }
        }
        this.g = obtainStyledAttributes.getInt(11, 500);
        this.r = obtainStyledAttributes.getBoolean(17, false);
        this.s = obtainStyledAttributes.getBoolean(18, false);
        this.t = obtainStyledAttributes.getBoolean(19, false);
        this.u = obtainStyledAttributes.getBoolean(20, true);
        this.v = obtainStyledAttributes.getBoolean(14, false);
        this.w = obtainStyledAttributes.getBoolean(15, false);
        this.f1428x = obtainStyledAttributes.getBoolean(16, false);
        this.f1405A = obtainStyledAttributes.getBoolean(23, true);
        obtainStyledAttributes.recycle();
        this.f = (float) ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }

    public final void onNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i2, int i7, int i8, int i10, int i11, int[] iArr) {
    }
}
