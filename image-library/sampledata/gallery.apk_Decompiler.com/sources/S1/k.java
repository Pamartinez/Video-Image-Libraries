package S1;

import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.d;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class k extends w {
    public j f;
    public OverScroller g;

    /* renamed from: h  reason: collision with root package name */
    public boolean f782h;

    /* renamed from: i  reason: collision with root package name */
    public int f783i;

    /* renamed from: j  reason: collision with root package name */
    public int f784j;
    public int k;
    public VelocityTracker l;
    public int m;
    public int n;

    /* renamed from: o  reason: collision with root package name */
    public boolean f785o;

    public abstract int d();

    public final int e(CoordinatorLayout coordinatorLayout, View view, int i2, int i7, int i8) {
        return f(coordinatorLayout, view, d() - i2, i7, i8);
    }

    public abstract int f(CoordinatorLayout coordinatorLayout, View view, int i2, int i7, int i8);

    public final void g(CoordinatorLayout coordinatorLayout, View view, int i2) {
        f(coordinatorLayout, view, i2, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        boolean z;
        boolean z3;
        View view2;
        int findPointerIndex;
        if (this.k < 0) {
            this.k = ViewConfiguration.get(coordinatorLayout.getContext()).getScaledTouchSlop();
        }
        this.m = motionEvent.getAction();
        if (motionEvent.getActionMasked() == 2 && this.f782h) {
            int i2 = this.f783i;
            if (i2 == -1 || (findPointerIndex = motionEvent.findPointerIndex(i2)) == -1) {
                return false;
            }
            int y = (int) motionEvent.getY(findPointerIndex);
            if (Math.abs(y - this.f784j) > this.k) {
                this.f784j = y;
                return true;
            }
        }
        if (motionEvent.getActionMasked() == 0) {
            this.f783i = -1;
            int x9 = (int) motionEvent.getX();
            int y8 = (int) motionEvent.getY();
            AppBarLayout.BaseBehavior baseBehavior = (AppBarLayout.BaseBehavior) this;
            AppBarLayout appBarLayout = (AppBarLayout) view;
            d dVar = baseBehavior.u;
            if (dVar != null) {
                z = dVar.canDrag(appBarLayout);
            } else {
                WeakReference weakReference = baseBehavior.t;
                if (weakReference == null || ((view2 = (View) weakReference.get()) != null && view2.isShown() && !view2.canScrollVertically(-1))) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (!z || !coordinatorLayout.isPointInChildBounds(view, x9, y8)) {
                z3 = false;
            } else {
                z3 = true;
            }
            this.f782h = z3;
            if (z3) {
                this.f784j = y8;
                this.f783i = motionEvent.getPointerId(0);
                if (this.l == null) {
                    this.l = VelocityTracker.obtain();
                }
                OverScroller overScroller = this.g;
                if (overScroller != null && !overScroller.isFinished()) {
                    this.g.abortAnimation();
                    return true;
                }
            }
        }
        VelocityTracker velocityTracker = this.l;
        if (velocityTracker != null) {
            velocityTracker.addMovement(motionEvent);
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00ea A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00eb A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(androidx.coordinatorlayout.widget.CoordinatorLayout r19, android.view.View r20, android.view.MotionEvent r21) {
        /*
            r18 = this;
            r0 = r18
            r6 = r21
            int r1 = r6.getAction()
            r0.n = r1
            int r1 = r6.getActionMasked()
            r2 = 0
            r3 = -1
            r7 = 0
            r8 = 1
            if (r1 == r8) goto L_0x0061
            r4 = 2
            if (r1 == r4) goto L_0x003a
            r4 = 3
            if (r1 == r4) goto L_0x00d2
            r2 = 6
            if (r1 == r2) goto L_0x001f
            goto L_0x00df
        L_0x001f:
            int r1 = r6.getActionIndex()
            if (r1 != 0) goto L_0x0027
            r1 = r8
            goto L_0x0028
        L_0x0027:
            r1 = r7
        L_0x0028:
            int r2 = r6.getPointerId(r1)
            r0.f783i = r2
            float r1 = r6.getY(r1)
            r2 = 1056964608(0x3f000000, float:0.5)
            float r1 = r1 + r2
            int r1 = (int) r1
            r0.f784j = r1
            goto L_0x00df
        L_0x003a:
            int r1 = r0.f783i
            int r1 = r6.findPointerIndex(r1)
            if (r1 != r3) goto L_0x0044
            goto L_0x00ea
        L_0x0044:
            float r1 = r6.getY(r1)
            int r1 = (int) r1
            int r2 = r0.f784j
            int r3 = r2 - r1
            r0.f784j = r1
            r1 = r20
            com.google.android.material.appbar.AppBarLayout r1 = (com.google.android.material.appbar.AppBarLayout) r1
            int r4 = com.google.android.material.appbar.AppBarLayout.BaseBehavior.l(r1)
            r5 = 0
            r1 = r19
            r2 = r20
            r0.e(r1, r2, r3, r4, r5)
            goto L_0x00df
        L_0x0061:
            r1 = r20
            boolean r4 = r0.f785o
            if (r4 == 0) goto L_0x00d2
            android.view.VelocityTracker r4 = r0.l
            if (r4 == 0) goto L_0x00d2
            r4.addMovement(r6)
            android.view.VelocityTracker r4 = r0.l
            r5 = 1000(0x3e8, float:1.401E-42)
            r4.computeCurrentVelocity(r5)
            android.view.VelocityTracker r4 = r0.l
            int r5 = r0.f783i
            float r4 = r4.getYVelocity(r5)
            r5 = r1
            com.google.android.material.appbar.AppBarLayout r5 = (com.google.android.material.appbar.AppBarLayout) r5
            int r5 = r5.getTotalScrollRange()
            int r5 = -r5
            S1.j r9 = r0.f
            if (r9 == 0) goto L_0x008e
            r1.removeCallbacks(r9)
            r0.f = r2
        L_0x008e:
            android.widget.OverScroller r9 = r0.g
            if (r9 != 0) goto L_0x009d
            android.widget.OverScroller r9 = new android.widget.OverScroller
            android.content.Context r10 = r1.getContext()
            r9.<init>(r10)
            r0.g = r9
        L_0x009d:
            android.widget.OverScroller r9 = r0.g
            int r11 = r0.a()
            int r13 = java.lang.Math.round(r4)
            r14 = 0
            r15 = 0
            r10 = 0
            r12 = 0
            r17 = 0
            r16 = r5
            r9.fling(r10, r11, r12, r13, r14, r15, r16, r17)
            android.widget.OverScroller r4 = r0.g
            boolean r4 = r4.computeScrollOffset()
            if (r4 == 0) goto L_0x00c8
            S1.j r4 = new S1.j
            r5 = 0
            r9 = r19
            r4.<init>(r0, r9, r1, r5)
            r0.f = r4
            androidx.core.view.ViewCompat.postOnAnimation(r1, r4)
            goto L_0x00d2
        L_0x00c8:
            r1 = r0
            com.google.android.material.appbar.AppBarLayout$BaseBehavior r1 = (com.google.android.material.appbar.AppBarLayout.BaseBehavior) r1
            android.widget.OverScroller r1 = r1.g
            if (r1 == 0) goto L_0x00d2
            r1.forceFinished(r8)
        L_0x00d2:
            r0.f782h = r7
            r0.f783i = r3
            android.view.VelocityTracker r1 = r0.l
            if (r1 == 0) goto L_0x00df
            r1.recycle()
            r0.l = r2
        L_0x00df:
            android.view.VelocityTracker r1 = r0.l
            if (r1 == 0) goto L_0x00e6
            r1.addMovement(r6)
        L_0x00e6:
            boolean r0 = r0.f782h
            if (r0 != 0) goto L_0x00eb
        L_0x00ea:
            return r7
        L_0x00eb:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: S1.k.onTouchEvent(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.MotionEvent):boolean");
    }
}
