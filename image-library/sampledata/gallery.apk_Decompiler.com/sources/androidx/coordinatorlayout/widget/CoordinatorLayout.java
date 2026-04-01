package androidx.coordinatorlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import androidx.coordinatorlayout.R$attr;
import androidx.coordinatorlayout.R$style;
import androidx.coordinatorlayout.R$styleable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Pools$Pool;
import androidx.core.util.Pools$SynchronizedPool;
import androidx.core.view.GravityCompat;
import androidx.core.view.NestedScrollingParent2;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.customview.view.AbsSavedState;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.sdk.cover.ScoverState;
import i.C0212a;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CoordinatorLayout extends ViewGroup implements NestedScrollingParent2, NestedScrollingParent3 {
    static final Class<?>[] CONSTRUCTOR_PARAMS = {Context.class, AttributeSet.class};
    static final Comparator<View> TOP_SORTED_CHILDREN_COMPARATOR = new ViewElevationComparator();
    static final String WIDGET_PACKAGE_NAME;
    static final ThreadLocal<Map<String, Constructor<Behavior>>> sConstructors = new ThreadLocal<>();
    private static final Pools$Pool<Rect> sRectPool = new Pools$SynchronizedPool(12);
    private OnApplyWindowInsetsListener mApplyWindowInsetsListener;
    private final int[] mBehaviorConsumed;
    private View mBehaviorTouchView;
    private final DirectedAcyclicGraph<View> mChildDag;
    private final List<View> mDependencySortedChildren;
    private boolean mDisallowInterceptReset;
    private boolean mDrawStatusBarBackground;
    private boolean mEnableAutoCollapsingKeyEvent;
    private boolean mIsAttachedToWindow;
    private int[] mKeylines;
    private WindowInsetsCompat mLastInsets;
    private View mLastNestedScrollingChild;
    private boolean mNeedsPreDrawListener;
    private final NestedScrollingParentHelper mNestedScrollingParentHelper;
    private View mNestedScrollingTarget;
    private final int[] mNestedScrollingV2ConsumedCompat;
    ViewGroup.OnHierarchyChangeListener mOnHierarchyChangeListener;
    private OnPreDrawListener mOnPreDrawListener;
    private Paint mScrimPaint;
    private Drawable mStatusBarBackground;
    private final List<View> mTempList1;
    private boolean mToolIsMouse;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface AttachedBehavior {
        Behavior getBehavior();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Behavior<V extends View> {
        public Behavior() {
        }

        public boolean blocksInteractionBelow(CoordinatorLayout coordinatorLayout, V v) {
            if (getScrimOpacity(coordinatorLayout, v) > 0.0f) {
                return true;
            }
            return false;
        }

        public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
            return false;
        }

        public boolean getInsetDodgeRect(CoordinatorLayout coordinatorLayout, V v, Rect rect) {
            return false;
        }

        public int getScrimColor(CoordinatorLayout coordinatorLayout, V v) {
            return -16777216;
        }

        public float getScrimOpacity(CoordinatorLayout coordinatorLayout, V v) {
            return 0.0f;
        }

        public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, V v, View view) {
            return false;
        }

        public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, V v, View view) {
            return false;
        }

        public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
            return false;
        }

        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, V v, int i2) {
            return false;
        }

        public boolean onMeasureChild(CoordinatorLayout coordinatorLayout, V v, int i2, int i7, int i8, int i10) {
            return false;
        }

        public boolean onNestedFling(CoordinatorLayout coordinatorLayout, V v, View view, float f, float f5, boolean z) {
            return false;
        }

        public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, V v, View view, float f, float f5) {
            return false;
        }

        @Deprecated
        public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i2, int i7, int[] iArr) {
        }

        @Deprecated
        public void onNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i2, int i7, int i8, int i10) {
        }

        @Deprecated
        public void onNestedScrollAccepted(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i2) {
        }

        public boolean onRequestChildRectangleOnScreen(CoordinatorLayout coordinatorLayout, V v, Rect rect, boolean z) {
            return false;
        }

        public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, V v) {
            return View.BaseSavedState.EMPTY_STATE;
        }

        @Deprecated
        public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i2) {
            return false;
        }

        @Deprecated
        public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view) {
        }

        public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
            return false;
        }

        public Behavior(Context context, AttributeSet attributeSet) {
        }

        public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i2, int i7, int[] iArr, int i8) {
            if (i8 == 0) {
                onNestedPreScroll(coordinatorLayout, v, view, i2, i7, iArr);
            }
        }

        @Deprecated
        public void onNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i2, int i7, int i8, int i10, int i11) {
            if (i11 == 0) {
                onNestedScroll(coordinatorLayout, v, view, i2, i7, i8, i10);
            }
        }

        public void onNestedScrollAccepted(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i2, int i7) {
            if (i7 == 0) {
                onNestedScrollAccepted(coordinatorLayout, v, view, view2, i2);
            }
        }

        public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i2, int i7) {
            if (i7 == 0) {
                return onStartNestedScroll(coordinatorLayout, v, view, view2, i2);
            }
            return false;
        }

        public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i2) {
            if (i2 == 0) {
                onStopNestedScroll(coordinatorLayout, v, view);
            }
        }

        public void onNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i2, int i7, int i8, int i10, int i11, int[] iArr) {
            iArr[0] = iArr[0] + i8;
            iArr[1] = iArr[1] + i10;
            onNestedScroll(coordinatorLayout, v, view, i2, i7, i8, i10, i11);
        }

        public void onDetachedFromLayoutParams() {
        }

        public void onAttachedToLayoutParams(LayoutParams layoutParams) {
        }

        public WindowInsetsCompat onApplyWindowInsets(CoordinatorLayout coordinatorLayout, V v, WindowInsetsCompat windowInsetsCompat) {
            return windowInsetsCompat;
        }

        public void onDependentViewRemoved(CoordinatorLayout coordinatorLayout, V v, View view) {
        }

        public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, V v, Parcelable parcelable) {
        }
    }

    @Deprecated
    @Retention(RetentionPolicy.RUNTIME)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public @interface DefaultBehavior {
        Class<? extends Behavior> value();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class HierarchyChangeListener implements ViewGroup.OnHierarchyChangeListener {
        public HierarchyChangeListener() {
        }

        public void onChildViewAdded(View view, View view2) {
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = CoordinatorLayout.this.mOnHierarchyChangeListener;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewAdded(view, view2);
            }
        }

        public void onChildViewRemoved(View view, View view2) {
            CoordinatorLayout.this.onChildViewsChanged(2);
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = CoordinatorLayout.this.mOnHierarchyChangeListener;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewRemoved(view, view2);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class OnPreDrawListener implements ViewTreeObserver.OnPreDrawListener {
        public OnPreDrawListener() {
        }

        public boolean onPreDraw() {
            CoordinatorLayout.this.onChildViewsChanged(0);
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ViewElevationComparator implements Comparator<View> {
        public int compare(View view, View view2) {
            float z = ViewCompat.getZ(view);
            float z3 = ViewCompat.getZ(view2);
            if (z > z3) {
                return -1;
            }
            return z < z3 ? 1 : 0;
        }
    }

    static {
        String str;
        Package packageR = CoordinatorLayout.class.getPackage();
        if (packageR != null) {
            str = packageR.getName();
        } else {
            str = null;
        }
        WIDGET_PACKAGE_NAME = str;
    }

    public CoordinatorLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.coordinatorLayoutStyle);
    }

    private static Rect acquireTempRect() {
        Rect acquire = sRectPool.acquire();
        if (acquire == null) {
            return new Rect();
        }
        return acquire;
    }

    private void cancelInterceptBehaviors() {
        int childCount = getChildCount();
        MotionEvent motionEvent = null;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            Behavior behavior = ((LayoutParams) childAt.getLayoutParams()).getBehavior();
            if (behavior != null) {
                if (motionEvent == null) {
                    long uptimeMillis = SystemClock.uptimeMillis();
                    motionEvent = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                }
                behavior.onInterceptTouchEvent(this, childAt, motionEvent);
            }
        }
        if (motionEvent != null) {
            motionEvent.recycle();
        }
    }

    private static int clamp(int i2, int i7, int i8) {
        if (i2 < i7) {
            return i7;
        }
        if (i2 > i8) {
            return i8;
        }
        return i2;
    }

    private void constrainChildRect(LayoutParams layoutParams, Rect rect, int i2, int i7) {
        int width = getWidth();
        int height = getHeight();
        int max = Math.max(getPaddingLeft() + layoutParams.leftMargin, Math.min(rect.left, ((width - getPaddingRight()) - i2) - layoutParams.rightMargin));
        int max2 = Math.max(getPaddingTop() + layoutParams.topMargin, Math.min(rect.top, ((height - getPaddingBottom()) - i7) - layoutParams.bottomMargin));
        rect.set(max, max2, i2 + max, i7 + max2);
    }

    private WindowInsetsCompat dispatchApplyWindowInsetsToBehaviors(WindowInsetsCompat windowInsetsCompat) {
        Behavior behavior;
        if (windowInsetsCompat.isConsumed()) {
            return windowInsetsCompat;
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (ViewCompat.getFitsSystemWindows(childAt) && (behavior = ((LayoutParams) childAt.getLayoutParams()).getBehavior()) != null) {
                windowInsetsCompat = behavior.onApplyWindowInsets(this, childAt, windowInsetsCompat);
                if (windowInsetsCompat.isConsumed()) {
                    return windowInsetsCompat;
                }
            }
        }
        return windowInsetsCompat;
    }

    private void getDesiredAnchoredChildRectWithoutConstraints(int i2, Rect rect, Rect rect2, LayoutParams layoutParams, int i7, int i8) {
        int i10;
        int i11;
        int absoluteGravity = GravityCompat.getAbsoluteGravity(resolveAnchoredChildGravity(layoutParams.gravity), i2);
        int absoluteGravity2 = GravityCompat.getAbsoluteGravity(resolveGravity(layoutParams.anchorGravity), i2);
        int i12 = absoluteGravity & 7;
        int i13 = absoluteGravity & 112;
        int i14 = absoluteGravity2 & 7;
        int i15 = absoluteGravity2 & 112;
        if (i14 == 1) {
            i10 = rect.left + (rect.width() / 2);
        } else if (i14 != 5) {
            i10 = rect.left;
        } else {
            i10 = rect.right;
        }
        if (i15 == 16) {
            i11 = rect.top + (rect.height() / 2);
        } else if (i15 != 80) {
            i11 = rect.top;
        } else {
            i11 = rect.bottom;
        }
        if (i12 == 1) {
            i10 -= i7 / 2;
        } else if (i12 != 5) {
            i10 -= i7;
        }
        if (i13 == 16) {
            i11 -= i8 / 2;
        } else if (i13 != 80) {
            i11 -= i8;
        }
        rect2.set(i10, i11, i7 + i10, i8 + i11);
    }

    private int getKeyline(int i2) {
        int[] iArr = this.mKeylines;
        if (iArr == null) {
            Log.e("CoordinatorLayout", "No keylines defined for " + this + " - attempted index lookup " + i2);
            return 0;
        } else if (i2 >= 0 && i2 < iArr.length) {
            return iArr[i2];
        } else {
            Log.e("CoordinatorLayout", "Keyline index " + i2 + " out of range for " + this);
            return 0;
        }
    }

    private void getTopSortedChildren(List<View> list) {
        int i2;
        list.clear();
        boolean isChildrenDrawingOrderEnabled = isChildrenDrawingOrderEnabled();
        int childCount = getChildCount();
        for (int i7 = childCount - 1; i7 >= 0; i7--) {
            if (isChildrenDrawingOrderEnabled) {
                i2 = getChildDrawingOrder(childCount, i7);
            } else {
                i2 = i7;
            }
            list.add(getChildAt(i2));
        }
        Comparator<View> comparator = TOP_SORTED_CHILDREN_COMPARATOR;
        if (comparator != null) {
            Collections.sort(list, comparator);
        }
    }

    private boolean hasDependencies(View view) {
        return this.mChildDag.hasOutgoingEdges(view);
    }

    private boolean isMouseEvent(MotionEvent motionEvent) {
        if (motionEvent.getToolType(0) == 3) {
            return true;
        }
        return false;
    }

    private void layoutChild(View view, int i2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        Rect acquireTempRect = acquireTempRect();
        acquireTempRect.set(getPaddingLeft() + layoutParams.leftMargin, getPaddingTop() + layoutParams.topMargin, (getWidth() - getPaddingRight()) - layoutParams.rightMargin, (getHeight() - getPaddingBottom()) - layoutParams.bottomMargin);
        if (this.mLastInsets != null && ViewCompat.getFitsSystemWindows(this) && !ViewCompat.getFitsSystemWindows(view)) {
            acquireTempRect.left = this.mLastInsets.getSystemWindowInsetLeft() + acquireTempRect.left;
            acquireTempRect.top = this.mLastInsets.getSystemWindowInsetTop() + acquireTempRect.top;
            acquireTempRect.right -= this.mLastInsets.getSystemWindowInsetRight();
            acquireTempRect.bottom -= this.mLastInsets.getSystemWindowInsetBottom();
        }
        Rect acquireTempRect2 = acquireTempRect();
        GravityCompat.apply(resolveGravity(layoutParams.gravity), view.getMeasuredWidth(), view.getMeasuredHeight(), acquireTempRect, acquireTempRect2, i2);
        view.layout(acquireTempRect2.left, acquireTempRect2.top, acquireTempRect2.right, acquireTempRect2.bottom);
        releaseTempRect(acquireTempRect);
        releaseTempRect(acquireTempRect2);
    }

    private void layoutChildWithAnchor(View view, View view2, int i2) {
        Rect acquireTempRect = acquireTempRect();
        Rect acquireTempRect2 = acquireTempRect();
        try {
            getDescendantRect(view2, acquireTempRect);
            getDesiredAnchoredChildRect(view, i2, acquireTempRect, acquireTempRect2);
            view.layout(acquireTempRect2.left, acquireTempRect2.top, acquireTempRect2.right, acquireTempRect2.bottom);
        } finally {
            releaseTempRect(acquireTempRect);
            releaseTempRect(acquireTempRect2);
        }
    }

    private void layoutChildWithKeyline(View view, int i2, int i7) {
        int i8;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int absoluteGravity = GravityCompat.getAbsoluteGravity(resolveKeylineGravity(layoutParams.gravity), i7);
        int i10 = absoluteGravity & 7;
        int i11 = absoluteGravity & 112;
        int width = getWidth();
        int height = getHeight();
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        if (i7 == 1) {
            i2 = width - i2;
        }
        int keyline = getKeyline(i2) - measuredWidth;
        if (i10 == 1) {
            keyline += measuredWidth / 2;
        } else if (i10 == 5) {
            keyline += measuredWidth;
        }
        if (i11 == 16) {
            i8 = measuredHeight / 2;
        } else if (i11 != 80) {
            i8 = 0;
        } else {
            i8 = measuredHeight;
        }
        int max = Math.max(getPaddingLeft() + layoutParams.leftMargin, Math.min(keyline, ((width - getPaddingRight()) - measuredWidth) - layoutParams.rightMargin));
        int max2 = Math.max(getPaddingTop() + layoutParams.topMargin, Math.min(i8, ((height - getPaddingBottom()) - measuredHeight) - layoutParams.bottomMargin));
        view.layout(max, max2, measuredWidth + max, measuredHeight + max2);
    }

    private MotionEvent obtainCancelEvent(MotionEvent motionEvent) {
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.setAction(3);
        return obtain;
    }

    private void offsetChildByInset(View view, Rect rect, int i2) {
        boolean z;
        boolean z3;
        int width;
        int i7;
        int i8;
        int i10;
        int height;
        int i11;
        int i12;
        int i13;
        if (ViewCompat.isLaidOut(view) && view.getWidth() > 0 && view.getHeight() > 0) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Behavior behavior = layoutParams.getBehavior();
            Rect acquireTempRect = acquireTempRect();
            Rect acquireTempRect2 = acquireTempRect();
            acquireTempRect2.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            if (behavior == null || !behavior.getInsetDodgeRect(this, view, acquireTempRect)) {
                acquireTempRect.set(acquireTempRect2);
            } else if (!acquireTempRect2.contains(acquireTempRect)) {
                throw new IllegalArgumentException("Rect should be within the child's bounds. Rect:" + acquireTempRect.toShortString() + " | Bounds:" + acquireTempRect2.toShortString());
            }
            releaseTempRect(acquireTempRect2);
            if (acquireTempRect.isEmpty()) {
                releaseTempRect(acquireTempRect);
                return;
            }
            int absoluteGravity = GravityCompat.getAbsoluteGravity(layoutParams.dodgeInsetEdges, i2);
            boolean z7 = true;
            if ((absoluteGravity & 48) != 48 || (i12 = (acquireTempRect.top - layoutParams.topMargin) - layoutParams.mInsetOffsetY) >= (i13 = rect.top)) {
                z = false;
            } else {
                setInsetOffsetY(view, i13 - i12);
                z = true;
            }
            if ((absoluteGravity & 80) == 80 && (height = ((getHeight() - acquireTempRect.bottom) - layoutParams.bottomMargin) + layoutParams.mInsetOffsetY) < (i11 = rect.bottom)) {
                setInsetOffsetY(view, height - i11);
                z = true;
            }
            if (!z) {
                setInsetOffsetY(view, 0);
            }
            if ((absoluteGravity & 3) != 3 || (i8 = (acquireTempRect.left - layoutParams.leftMargin) - layoutParams.mInsetOffsetX) >= (i10 = rect.left)) {
                z3 = false;
            } else {
                setInsetOffsetX(view, i10 - i8);
                z3 = true;
            }
            if ((absoluteGravity & 5) != 5 || (width = ((getWidth() - acquireTempRect.right) - layoutParams.rightMargin) + layoutParams.mInsetOffsetX) >= (i7 = rect.right)) {
                z7 = z3;
            } else {
                setInsetOffsetX(view, width - i7);
            }
            if (!z7) {
                setInsetOffsetX(view, 0);
            }
            releaseTempRect(acquireTempRect);
        }
    }

    public static Behavior parseBehavior(Context context, AttributeSet attributeSet, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.startsWith(".")) {
            str = context.getPackageName() + str;
        } else if (str.indexOf(46) < 0) {
            String str2 = WIDGET_PACKAGE_NAME;
            if (!TextUtils.isEmpty(str2)) {
                str = str2 + '.' + str;
            }
        }
        try {
            ThreadLocal<Map<String, Constructor<Behavior>>> threadLocal = sConstructors;
            Map map = threadLocal.get();
            if (map == null) {
                map = new HashMap();
                threadLocal.set(map);
            }
            Constructor<?> constructor = (Constructor) map.get(str);
            if (constructor == null) {
                constructor = Class.forName(str, false, context.getClassLoader()).getConstructor(CONSTRUCTOR_PARAMS);
                constructor.setAccessible(true);
                map.put(str, constructor);
            }
            return (Behavior) constructor.newInstance(new Object[]{context, attributeSet});
        } catch (Exception e) {
            throw new RuntimeException(C0212a.l("Could not inflate Behavior subclass ", str), e);
        }
    }

    private boolean performEvent(Behavior behavior, View view, MotionEvent motionEvent, int i2) {
        if (i2 == 0) {
            return behavior.onInterceptTouchEvent(this, view, motionEvent);
        }
        if (i2 == 1) {
            return behavior.onTouchEvent(this, view, motionEvent);
        }
        throw new IllegalArgumentException();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003d, code lost:
        r6 = performEvent(r10, r8, r14, r15);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean performIntercept(android.view.MotionEvent r14, int r15) {
        /*
            r13 = this;
            int r0 = r14.getActionMasked()
            java.util.List<android.view.View> r1 = r13.mTempList1
            r13.getTopSortedChildren(r1)
            int r2 = r1.size()
            r3 = 0
            r4 = 0
            r5 = r3
            r6 = r5
            r7 = r6
        L_0x0012:
            if (r5 >= r2) goto L_0x0082
            java.lang.Object r8 = r1.get(r5)
            android.view.View r8 = (android.view.View) r8
            android.view.ViewGroup$LayoutParams r9 = r8.getLayoutParams()
            androidx.coordinatorlayout.widget.CoordinatorLayout$LayoutParams r9 = (androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams) r9
            androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior r10 = r9.getBehavior()
            if (r6 != 0) goto L_0x0028
            if (r7 == 0) goto L_0x0036
        L_0x0028:
            if (r0 == 0) goto L_0x0036
            if (r10 == 0) goto L_0x007f
            if (r4 != 0) goto L_0x0032
            android.view.MotionEvent r4 = r13.obtainCancelEvent(r14)
        L_0x0032:
            r13.performEvent(r10, r8, r4, r15)
            goto L_0x007f
        L_0x0036:
            r11 = 1
            if (r7 != 0) goto L_0x006b
            if (r6 != 0) goto L_0x006b
            if (r10 == 0) goto L_0x006b
            boolean r6 = r13.performEvent(r10, r8, r14, r15)
            if (r6 == 0) goto L_0x006b
            r13.mBehaviorTouchView = r8
            r7 = 3
            if (r0 == r7) goto L_0x006b
            if (r0 == r11) goto L_0x006b
            r7 = r3
        L_0x004b:
            if (r7 >= r5) goto L_0x006b
            java.lang.Object r10 = r1.get(r7)
            android.view.View r10 = (android.view.View) r10
            android.view.ViewGroup$LayoutParams r12 = r10.getLayoutParams()
            androidx.coordinatorlayout.widget.CoordinatorLayout$LayoutParams r12 = (androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams) r12
            androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior r12 = r12.getBehavior()
            if (r12 == 0) goto L_0x0068
            if (r4 != 0) goto L_0x0065
            android.view.MotionEvent r4 = r13.obtainCancelEvent(r14)
        L_0x0065:
            r13.performEvent(r12, r10, r4, r15)
        L_0x0068:
            int r7 = r7 + 1
            goto L_0x004b
        L_0x006b:
            boolean r7 = r9.didBlockInteraction()
            boolean r8 = r9.isBlockingInteractionBelow(r13, r8)
            if (r8 == 0) goto L_0x0079
            if (r7 != 0) goto L_0x0079
            r7 = r11
            goto L_0x007a
        L_0x0079:
            r7 = r3
        L_0x007a:
            if (r8 == 0) goto L_0x007f
            if (r7 != 0) goto L_0x007f
            goto L_0x0082
        L_0x007f:
            int r5 = r5 + 1
            goto L_0x0012
        L_0x0082:
            r1.clear()
            if (r4 == 0) goto L_0x008a
            r4.recycle()
        L_0x008a:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.performIntercept(android.view.MotionEvent, int):boolean");
    }

    private void prepareChildren() {
        this.mDependencySortedChildren.clear();
        this.mChildDag.clear();
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            LayoutParams resolvedLayoutParams = getResolvedLayoutParams(childAt);
            resolvedLayoutParams.findAnchorView(this, childAt);
            this.mChildDag.addNode(childAt);
            for (int i7 = 0; i7 < childCount; i7++) {
                if (i7 != i2) {
                    View childAt2 = getChildAt(i7);
                    if (resolvedLayoutParams.dependsOn(this, childAt, childAt2)) {
                        if (!this.mChildDag.contains(childAt2)) {
                            this.mChildDag.addNode(childAt2);
                        }
                        this.mChildDag.addEdge(childAt2, childAt);
                    }
                }
            }
        }
        this.mDependencySortedChildren.addAll(this.mChildDag.getSortedList());
        Collections.reverse(this.mDependencySortedChildren);
    }

    private static void releaseTempRect(Rect rect) {
        rect.setEmpty();
        sRectPool.release(rect);
    }

    private void resetTouchBehaviors() {
        View view = this.mBehaviorTouchView;
        if (view != null) {
            Behavior behavior = ((LayoutParams) view.getLayoutParams()).getBehavior();
            if (behavior != null) {
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                behavior.onTouchEvent(this, this.mBehaviorTouchView, obtain);
                obtain.recycle();
            }
            this.mBehaviorTouchView = null;
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            ((LayoutParams) getChildAt(i2).getLayoutParams()).resetTouchBehaviorTracking();
        }
        this.mDisallowInterceptReset = false;
    }

    private static int resolveAnchoredChildGravity(int i2) {
        if (i2 == 0) {
            return 17;
        }
        return i2;
    }

    private static int resolveGravity(int i2) {
        if ((i2 & 7) == 0) {
            i2 |= 8388611;
        }
        if ((i2 & 112) == 0) {
            return i2 | 48;
        }
        return i2;
    }

    private static int resolveKeylineGravity(int i2) {
        if (i2 == 0) {
            return 8388661;
        }
        return i2;
    }

    private void setInsetOffsetX(View view, int i2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i7 = layoutParams.mInsetOffsetX;
        if (i7 != i2) {
            ViewCompat.offsetLeftAndRight(view, i2 - i7);
            layoutParams.mInsetOffsetX = i2;
        }
    }

    private void setInsetOffsetY(View view, int i2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i7 = layoutParams.mInsetOffsetY;
        if (i7 != i2) {
            ViewCompat.offsetTopAndBottom(view, i2 - i7);
            layoutParams.mInsetOffsetY = i2;
        }
    }

    private void setupForInsets() {
        if (ViewCompat.getFitsSystemWindows(this)) {
            if (this.mApplyWindowInsetsListener == null) {
                this.mApplyWindowInsetsListener = new OnApplyWindowInsetsListener() {
                    public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                        return CoordinatorLayout.this.setWindowInsets(windowInsetsCompat);
                    }
                };
            }
            ViewCompat.setOnApplyWindowInsetsListener(this, this.mApplyWindowInsetsListener);
            setSystemUiVisibility(MediaDefs.Meta.XMP.XMP_MIX_RESERVED_SIZE);
            return;
        }
        ViewCompat.setOnApplyWindowInsetsListener(this, (OnApplyWindowInsetsListener) null);
    }

    public void addPreDrawListener() {
        if (this.mIsAttachedToWindow) {
            if (this.mOnPreDrawListener == null) {
                this.mOnPreDrawListener = new OnPreDrawListener();
            }
            getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
        }
        this.mNeedsPreDrawListener = true;
    }

    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (!(layoutParams instanceof LayoutParams) || !super.checkLayoutParams(layoutParams)) {
            return false;
        }
        return true;
    }

    public void dispatchDependentViewsChanged(View view) {
        ArrayList<View> incomingEdgesInternal = this.mChildDag.getIncomingEdgesInternal(view);
        if (incomingEdgesInternal != null && !incomingEdgesInternal.isEmpty()) {
            for (int i2 = 0; i2 < incomingEdgesInternal.size(); i2++) {
                View view2 = incomingEdgesInternal.get(i2);
                Behavior behavior = ((LayoutParams) view2.getLayoutParams()).getBehavior();
                if (behavior != null) {
                    behavior.onDependentViewChanged(this, view2, view);
                }
            }
        }
    }

    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        int childCount = getChildCount() - 1;
        while (true) {
            if (childCount < 0) {
                break;
            }
            View childAt = getChildAt(childCount);
            Behavior behavior = ((LayoutParams) childAt.getLayoutParams()).getBehavior();
            if (behavior != null) {
                behavior.dispatchGenericMotionEvent(motionEvent);
            }
            if (childAt instanceof AppBarLayoutBehavior) {
                AppBarLayoutBehavior appBarLayoutBehavior = (AppBarLayoutBehavior) childAt;
                boolean isMouseEvent = isMouseEvent(motionEvent);
                if (this.mToolIsMouse != isMouseEvent) {
                    this.mToolIsMouse = isMouseEvent;
                    appBarLayoutBehavior.seslSetIsMouse(isMouseEvent);
                }
                if (motionEvent.getAction() == 8) {
                    if (this.mLastNestedScrollingChild != null) {
                        if (motionEvent.getAxisValue(9) < 0.0f) {
                            if (appBarLayoutBehavior.useFloatingToolbar()) {
                                int seslGetCurrentAppBarState = appBarLayoutBehavior.seslGetCurrentAppBarState();
                                if ((seslGetCurrentAppBarState & 1) != 0) {
                                    appBarLayoutBehavior.seslSetExpanded(false);
                                    return true;
                                } else if ((seslGetCurrentAppBarState & 2) != 0 && appBarLayoutBehavior.seslCanChangeToHideState()) {
                                    appBarLayoutBehavior.seslSetHide();
                                    return true;
                                }
                            } else {
                                appBarLayoutBehavior.seslSetExpanded(false);
                            }
                        } else if (motionEvent.getAxisValue(9) > 0.0f && !this.mLastNestedScrollingChild.canScrollVertically(-1)) {
                            if (appBarLayoutBehavior.seslIsHided()) {
                                appBarLayoutBehavior.seslSetExpanded(false);
                            } else {
                                appBarLayoutBehavior.seslSetExpanded(true);
                            }
                            return true;
                        }
                    } else if (motionEvent.getAxisValue(9) < 0.0f) {
                        if (appBarLayoutBehavior.useFloatingToolbar()) {
                            appBarLayoutBehavior.seslSetHide();
                        } else {
                            appBarLayoutBehavior.seslSetExpanded(false);
                        }
                    } else if (motionEvent.getAxisValue(9) > 0.0f) {
                        appBarLayoutBehavior.seslSetExpanded(!appBarLayoutBehavior.seslIsHided());
                    }
                }
            } else {
                childCount--;
            }
        }
        return super.dispatchGenericMotionEvent(motionEvent);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (this.mEnableAutoCollapsingKeyEvent && (keyEvent.getKeyCode() == 61 || keyEvent.getKeyCode() == 19 || keyEvent.getKeyCode() == 20 || keyEvent.getKeyCode() == 21 || keyEvent.getKeyCode() == 22)) {
            int childCount = getChildCount();
            int i2 = 0;
            while (true) {
                if (i2 >= childCount) {
                    break;
                }
                View childAt = getChildAt(i2);
                if (childAt instanceof AppBarLayoutBehavior) {
                    AppBarLayoutBehavior appBarLayoutBehavior = (AppBarLayoutBehavior) childAt;
                    if (appBarLayoutBehavior.useFloatingToolbar()) {
                        if (keyEvent.getKeyCode() != 19) {
                            if (keyEvent.getKeyCode() == 20 && appBarLayoutBehavior.seslIsCollapsed()) {
                                appBarLayoutBehavior.seslSetHide();
                                break;
                            }
                        } else if (appBarLayoutBehavior.seslIsHided()) {
                            appBarLayoutBehavior.seslSetExpanded(false);
                            break;
                        }
                    }
                    if (!appBarLayoutBehavior.seslIsCollapsed()) {
                        appBarLayoutBehavior.seslSetExpanded(false);
                        break;
                    }
                }
                i2++;
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public boolean drawChild(Canvas canvas, View view, long j2) {
        View view2 = view;
        LayoutParams layoutParams = (LayoutParams) view2.getLayoutParams();
        Behavior behavior = layoutParams.mBehavior;
        if (behavior != null) {
            float scrimOpacity = behavior.getScrimOpacity(this, view2);
            if (scrimOpacity > 0.0f) {
                if (this.mScrimPaint == null) {
                    this.mScrimPaint = new Paint();
                }
                this.mScrimPaint.setColor(layoutParams.mBehavior.getScrimColor(this, view2));
                this.mScrimPaint.setAlpha(clamp(Math.round(scrimOpacity * 255.0f), 0, ScoverState.TYPE_NFC_SMART_COVER));
                int save = canvas.save();
                if (view2.isOpaque()) {
                    canvas.clipRect((float) view2.getLeft(), (float) view2.getTop(), (float) view2.getRight(), (float) view2.getBottom(), Region.Op.DIFFERENCE);
                }
                Canvas canvas2 = canvas;
                canvas2.drawRect((float) getPaddingLeft(), (float) getPaddingTop(), (float) (getWidth() - getPaddingRight()), (float) (getHeight() - getPaddingBottom()), this.mScrimPaint);
                canvas2.restoreToCount(save);
                return super.drawChild(canvas, view, j2);
            }
        }
        Canvas canvas3 = canvas;
        return super.drawChild(canvas, view, j2);
    }

    public void drawableStateChanged() {
        boolean z;
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.mStatusBarBackground;
        if (drawable == null || !drawable.isStateful()) {
            z = false;
        } else {
            z = drawable.setState(drawableState);
        }
        if (z) {
            invalidate();
        }
    }

    public void ensurePreDrawListener() {
        int childCount = getChildCount();
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 >= childCount) {
                break;
            } else if (hasDependencies(getChildAt(i2))) {
                z = true;
                break;
            } else {
                i2++;
            }
        }
        if (z == this.mNeedsPreDrawListener) {
            return;
        }
        if (z) {
            addPreDrawListener();
        } else {
            removePreDrawListener();
        }
    }

    public void getChildRect(View view, boolean z, Rect rect) {
        if (view.isLayoutRequested() || view.getVisibility() == 8) {
            rect.setEmpty();
        } else if (z) {
            getDescendantRect(view, rect);
        } else {
            rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
    }

    public List<View> getDependencies(View view) {
        List<View> outgoingEdges = this.mChildDag.getOutgoingEdges(view);
        if (outgoingEdges == null) {
            return Collections.EMPTY_LIST;
        }
        return outgoingEdges;
    }

    public final List<View> getDependencySortedChildren() {
        prepareChildren();
        return Collections.unmodifiableList(this.mDependencySortedChildren);
    }

    public List<View> getDependents(View view) {
        List<View> incomingEdges = this.mChildDag.getIncomingEdges(view);
        if (incomingEdges == null) {
            return Collections.EMPTY_LIST;
        }
        return incomingEdges;
    }

    public void getDescendantRect(View view, Rect rect) {
        ViewGroupUtils.getDescendantRect(this, view, rect);
    }

    public void getDesiredAnchoredChildRect(View view, int i2, Rect rect, Rect rect2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        Rect rect3 = rect2;
        getDesiredAnchoredChildRectWithoutConstraints(i2, rect, rect3, layoutParams, measuredWidth, measuredHeight);
        constrainChildRect(layoutParams, rect3, measuredWidth, measuredHeight);
    }

    public void getLastChildRect(View view, Rect rect) {
        rect.set(((LayoutParams) view.getLayoutParams()).getLastChildRect());
    }

    public final WindowInsetsCompat getLastWindowInsets() {
        return this.mLastInsets;
    }

    public int getNestedScrollAxes() {
        return this.mNestedScrollingParentHelper.getNestedScrollAxes();
    }

    public LayoutParams getResolvedLayoutParams(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (!layoutParams.mBehaviorResolved) {
            if (view instanceof AttachedBehavior) {
                Behavior behavior = ((AttachedBehavior) view).getBehavior();
                if (behavior == null) {
                    Log.e("CoordinatorLayout", "Attached behavior class is null");
                }
                layoutParams.setBehavior(behavior);
                layoutParams.mBehaviorResolved = true;
                return layoutParams;
            }
            DefaultBehavior defaultBehavior = null;
            for (Class cls = view.getClass(); cls != null; cls = cls.getSuperclass()) {
                defaultBehavior = (DefaultBehavior) cls.getAnnotation(DefaultBehavior.class);
                if (defaultBehavior != null) {
                    break;
                }
            }
            if (defaultBehavior != null) {
                try {
                    layoutParams.setBehavior((Behavior) defaultBehavior.value().getDeclaredConstructor((Class[]) null).newInstance((Object[]) null));
                } catch (Exception e) {
                    Log.e("CoordinatorLayout", "Default behavior class " + defaultBehavior.value().getName() + " could not be instantiated. Did you forget a default constructor?", e);
                }
            }
            layoutParams.mBehaviorResolved = true;
        }
        return layoutParams;
    }

    public Drawable getStatusBarBackground() {
        return this.mStatusBarBackground;
    }

    public int getSuggestedMinimumHeight() {
        return Math.max(super.getSuggestedMinimumHeight(), getPaddingBottom() + getPaddingTop());
    }

    public int getSuggestedMinimumWidth() {
        return Math.max(super.getSuggestedMinimumWidth(), getPaddingRight() + getPaddingLeft());
    }

    public boolean isPointInChildBounds(View view, int i2, int i7) {
        Rect acquireTempRect = acquireTempRect();
        getDescendantRect(view, acquireTempRect);
        try {
            return acquireTempRect.contains(i2, i7);
        } finally {
            releaseTempRect(acquireTempRect);
        }
    }

    public void offsetChildToAnchor(View view, int i2) {
        Behavior behavior;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (layoutParams.mAnchorView != null) {
            Rect acquireTempRect = acquireTempRect();
            Rect acquireTempRect2 = acquireTempRect();
            Rect acquireTempRect3 = acquireTempRect();
            getDescendantRect(layoutParams.mAnchorView, acquireTempRect);
            boolean z = false;
            getChildRect(view, false, acquireTempRect2);
            int measuredWidth = view.getMeasuredWidth();
            int measuredHeight = view.getMeasuredHeight();
            getDesiredAnchoredChildRectWithoutConstraints(i2, acquireTempRect, acquireTempRect3, layoutParams, measuredWidth, measuredHeight);
            if (!(acquireTempRect3.left == acquireTempRect2.left && acquireTempRect3.top == acquireTempRect2.top)) {
                z = true;
            }
            constrainChildRect(layoutParams, acquireTempRect3, measuredWidth, measuredHeight);
            int i7 = acquireTempRect3.left - acquireTempRect2.left;
            int i8 = acquireTempRect3.top - acquireTempRect2.top;
            if (i7 != 0) {
                ViewCompat.offsetLeftAndRight(view, i7);
            }
            if (i8 != 0) {
                ViewCompat.offsetTopAndBottom(view, i8);
            }
            if (z && (behavior = layoutParams.getBehavior()) != null) {
                behavior.onDependentViewChanged(this, view, layoutParams.mAnchorView);
            }
            releaseTempRect(acquireTempRect);
            releaseTempRect(acquireTempRect2);
            releaseTempRect(acquireTempRect3);
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        resetTouchBehaviors();
        if (this.mNeedsPreDrawListener) {
            if (this.mOnPreDrawListener == null) {
                this.mOnPreDrawListener = new OnPreDrawListener();
            }
            getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
        }
        if (this.mLastInsets == null && ViewCompat.getFitsSystemWindows(this)) {
            ViewCompat.requestApplyInsets(this);
        }
        this.mIsAttachedToWindow = true;
    }

    public final void onChildViewsChanged(int i2) {
        boolean z;
        int i7 = i2;
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        int size = this.mDependencySortedChildren.size();
        Rect acquireTempRect = acquireTempRect();
        Rect acquireTempRect2 = acquireTempRect();
        Rect acquireTempRect3 = acquireTempRect();
        for (int i8 = 0; i8 < size; i8++) {
            View view = this.mDependencySortedChildren.get(i8);
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (i7 != 0 || view.getVisibility() != 8) {
                for (int i10 = 0; i10 < i8; i10++) {
                    if (layoutParams.mAnchorDirectChild == this.mDependencySortedChildren.get(i10)) {
                        offsetChildToAnchor(view, layoutDirection);
                    }
                }
                getChildRect(view, true, acquireTempRect2);
                if (layoutParams.insetEdge != 0 && !acquireTempRect2.isEmpty()) {
                    int absoluteGravity = GravityCompat.getAbsoluteGravity(layoutParams.insetEdge, layoutDirection);
                    int i11 = absoluteGravity & 112;
                    if (i11 == 48) {
                        acquireTempRect.top = Math.max(acquireTempRect.top, acquireTempRect2.bottom);
                    } else if (i11 == 80) {
                        acquireTempRect.bottom = Math.max(acquireTempRect.bottom, getHeight() - acquireTempRect2.top);
                    }
                    int i12 = absoluteGravity & 7;
                    if (i12 == 3) {
                        acquireTempRect.left = Math.max(acquireTempRect.left, acquireTempRect2.right);
                    } else if (i12 == 5) {
                        acquireTempRect.right = Math.max(acquireTempRect.right, getWidth() - acquireTempRect2.left);
                    }
                }
                if (layoutParams.dodgeInsetEdges != 0 && view.getVisibility() == 0) {
                    offsetChildByInset(view, acquireTempRect, layoutDirection);
                }
                if (i7 != 2) {
                    getLastChildRect(view, acquireTempRect3);
                    if (!acquireTempRect3.equals(acquireTempRect2)) {
                        recordLastChildRect(view, acquireTempRect2);
                    }
                }
                for (int i13 = i8 + 1; i13 < size; i13++) {
                    View view2 = this.mDependencySortedChildren.get(i13);
                    LayoutParams layoutParams2 = (LayoutParams) view2.getLayoutParams();
                    Behavior behavior = layoutParams2.getBehavior();
                    if (behavior != null && behavior.layoutDependsOn(this, view2, view)) {
                        if (i7 != 0 || !layoutParams2.getChangedAfterNestedScroll()) {
                            if (i7 != 2) {
                                z = behavior.onDependentViewChanged(this, view2, view);
                            } else {
                                behavior.onDependentViewRemoved(this, view2, view);
                                z = true;
                            }
                            if (i7 == 1) {
                                layoutParams2.setChangedAfterNestedScroll(z);
                            }
                        } else {
                            layoutParams2.resetChangedAfterNestedScroll();
                        }
                    }
                }
            }
        }
        releaseTempRect(acquireTempRect);
        releaseTempRect(acquireTempRect2);
        releaseTempRect(acquireTempRect3);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        resetTouchBehaviors();
        if (this.mNeedsPreDrawListener && this.mOnPreDrawListener != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
        }
        View view = this.mNestedScrollingTarget;
        if (view != null) {
            this.mLastNestedScrollingChild = view;
            onStopNestedScroll(view);
        }
        this.mIsAttachedToWindow = false;
    }

    public void onDraw(Canvas canvas) {
        int i2;
        super.onDraw(canvas);
        if (this.mDrawStatusBarBackground && this.mStatusBarBackground != null) {
            WindowInsetsCompat windowInsetsCompat = this.mLastInsets;
            if (windowInsetsCompat != null) {
                i2 = windowInsetsCompat.getSystemWindowInsetTop();
            } else {
                i2 = 0;
            }
            if (i2 > 0) {
                this.mStatusBarBackground.setBounds(0, 0, getWidth(), i2);
                this.mStatusBarBackground.draw(canvas);
            }
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = getChildAt(childCount);
                if (childAt instanceof AppBarLayoutBehavior) {
                    AppBarLayoutBehavior appBarLayoutBehavior = (AppBarLayoutBehavior) childAt;
                    boolean isMouseEvent = isMouseEvent(motionEvent);
                    if (this.mToolIsMouse != isMouseEvent) {
                        this.mToolIsMouse = isMouseEvent;
                        appBarLayoutBehavior.seslSetIsMouse(isMouseEvent);
                    }
                }
            }
            resetTouchBehaviors();
        }
        boolean performIntercept = performIntercept(motionEvent, 0);
        if (actionMasked != 1 && actionMasked != 3) {
            return performIntercept;
        }
        this.mBehaviorTouchView = null;
        resetTouchBehaviors();
        return performIntercept;
    }

    public void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        Behavior behavior;
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        int size = this.mDependencySortedChildren.size();
        for (int i11 = 0; i11 < size; i11++) {
            View view = this.mDependencySortedChildren.get(i11);
            if (view.getVisibility() != 8 && ((behavior = ((LayoutParams) view.getLayoutParams()).getBehavior()) == null || !behavior.onLayoutChild(this, view, layoutDirection))) {
                onLayoutChild(view, layoutDirection);
            }
        }
    }

    public void onLayoutChild(View view, int i2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (!layoutParams.checkAnchorChanged()) {
            View view2 = layoutParams.mAnchorView;
            if (view2 != null) {
                layoutChildWithAnchor(view, view2, i2);
                return;
            }
            int i7 = layoutParams.keyline;
            if (i7 >= 0) {
                layoutChildWithKeyline(view, i7, i2);
            } else {
                layoutChild(view, i2);
            }
        } else {
            throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x00d3  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00fe  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x010a  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x012e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r27, int r28) {
        /*
            r26 = this;
            r0 = r26
            r0.prepareChildren()
            r0.ensurePreDrawListener()
            int r7 = r0.getPaddingLeft()
            int r1 = r0.getPaddingTop()
            int r8 = r0.getPaddingRight()
            int r2 = r0.getPaddingBottom()
            int r9 = androidx.core.view.ViewCompat.getLayoutDirection(r0)
            r3 = 1
            if (r9 != r3) goto L_0x0021
            r11 = r3
            goto L_0x0022
        L_0x0021:
            r11 = 0
        L_0x0022:
            int r12 = android.view.View.MeasureSpec.getMode(r27)
            int r13 = android.view.View.MeasureSpec.getSize(r27)
            int r14 = android.view.View.MeasureSpec.getMode(r28)
            int r15 = android.view.View.MeasureSpec.getSize(r28)
            int r16 = r7 + r8
            int r17 = r1 + r2
            int r1 = r0.getSuggestedMinimumWidth()
            int r2 = r0.getSuggestedMinimumHeight()
            androidx.core.view.WindowInsetsCompat r4 = r0.mLastInsets
            if (r4 == 0) goto L_0x004b
            boolean r4 = androidx.core.view.ViewCompat.getFitsSystemWindows(r0)
            if (r4 == 0) goto L_0x004b
            r18 = r3
            goto L_0x004d
        L_0x004b:
            r18 = 0
        L_0x004d:
            java.util.List<android.view.View> r3 = r0.mDependencySortedChildren
            int r3 = r3.size()
            r4 = 0
            r5 = 0
        L_0x0055:
            if (r4 >= r3) goto L_0x0182
            java.util.List<android.view.View> r6 = r0.mDependencySortedChildren
            java.lang.Object r6 = r6.get(r4)
            android.view.View r6 = (android.view.View) r6
            int r10 = r6.getVisibility()
            r20 = r1
            r1 = 8
            if (r10 != r1) goto L_0x0079
            r23 = r3
            r21 = r4
            r19 = r7
            r22 = r9
            r1 = r20
            r24 = 0
            r20 = r8
            goto L_0x0176
        L_0x0079:
            android.view.ViewGroup$LayoutParams r1 = r6.getLayoutParams()
            r10 = r1
            androidx.coordinatorlayout.widget.CoordinatorLayout$LayoutParams r10 = (androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams) r10
            int r1 = r10.keyline
            if (r1 < 0) goto L_0x00c6
            if (r12 == 0) goto L_0x00c6
            int r1 = r0.getKeyline(r1)
            r21 = r1
            int r1 = r10.gravity
            int r1 = resolveKeylineGravity(r1)
            int r1 = androidx.core.view.GravityCompat.getAbsoluteGravity(r1, r9)
            r1 = r1 & 7
            r22 = r2
            r2 = 3
            if (r1 != r2) goto L_0x009f
            if (r11 == 0) goto L_0x00a4
        L_0x009f:
            r2 = 5
            if (r1 != r2) goto L_0x00b3
            if (r11 == 0) goto L_0x00b3
        L_0x00a4:
            int r1 = r13 - r8
            int r1 = r1 - r21
            r2 = 0
            int r1 = java.lang.Math.max(r2, r1)
        L_0x00ad:
            r25 = r4
            r4 = r1
            r1 = r25
            goto L_0x00cb
        L_0x00b3:
            if (r1 != r2) goto L_0x00b7
            if (r11 == 0) goto L_0x00bc
        L_0x00b7:
            r2 = 3
            if (r1 != r2) goto L_0x00c4
            if (r11 == 0) goto L_0x00c4
        L_0x00bc:
            int r1 = r21 - r7
            r2 = 0
            int r1 = java.lang.Math.max(r2, r1)
            goto L_0x00ad
        L_0x00c4:
            r2 = 0
            goto L_0x00c9
        L_0x00c6:
            r22 = r2
            goto L_0x00c4
        L_0x00c9:
            r1 = r4
            r4 = r2
        L_0x00cb:
            if (r18 == 0) goto L_0x00fe
            boolean r19 = androidx.core.view.ViewCompat.getFitsSystemWindows(r6)
            if (r19 != 0) goto L_0x00fe
            androidx.core.view.WindowInsetsCompat r2 = r0.mLastInsets
            int r2 = r2.getSystemWindowInsetLeft()
            r21 = r1
            androidx.core.view.WindowInsetsCompat r1 = r0.mLastInsets
            int r1 = r1.getSystemWindowInsetRight()
            int r1 = r1 + r2
            androidx.core.view.WindowInsetsCompat r2 = r0.mLastInsets
            int r2 = r2.getSystemWindowInsetTop()
            r23 = r1
            androidx.core.view.WindowInsetsCompat r1 = r0.mLastInsets
            int r1 = r1.getSystemWindowInsetBottom()
            int r1 = r1 + r2
            int r2 = r13 - r23
            int r2 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r12)
            int r1 = r15 - r1
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r14)
            goto L_0x0104
        L_0x00fe:
            r21 = r1
            r2 = r27
            r1 = r28
        L_0x0104:
            androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior r0 = r10.getBehavior()
            if (r0 == 0) goto L_0x012e
            r23 = r3
            r3 = r2
            r2 = r6
            r6 = 0
            r19 = r22
            r22 = r9
            r9 = r19
            r24 = 0
            r19 = r7
            r7 = r20
            r20 = r8
            r8 = r5
            r5 = r1
            r1 = r26
            boolean r0 = r0.onMeasureChild(r1, r2, r3, r4, r5, r6)
            r1 = r3
            r3 = r4
            r4 = r5
            if (r0 != 0) goto L_0x012b
            goto L_0x0143
        L_0x012b:
            r0 = r26
            goto L_0x014d
        L_0x012e:
            r19 = r22
            r22 = r9
            r9 = r19
            r23 = r3
            r3 = r4
            r19 = r7
            r7 = r20
            r24 = 0
            r4 = r1
            r1 = r2
            r2 = r6
            r20 = r8
            r8 = r5
        L_0x0143:
            r5 = 0
            r0 = r2
            r2 = r1
            r1 = r0
            r0 = r26
            r0.onMeasureChild(r1, r2, r3, r4, r5)
            r2 = r1
        L_0x014d:
            int r1 = r2.getMeasuredWidth()
            int r1 = r1 + r16
            int r3 = r10.leftMargin
            int r1 = r1 + r3
            int r3 = r10.rightMargin
            int r1 = r1 + r3
            int r1 = java.lang.Math.max(r7, r1)
            int r3 = r2.getMeasuredHeight()
            int r3 = r3 + r17
            int r4 = r10.topMargin
            int r3 = r3 + r4
            int r4 = r10.bottomMargin
            int r3 = r3 + r4
            int r3 = java.lang.Math.max(r9, r3)
            int r2 = r2.getMeasuredState()
            int r5 = android.view.View.combineMeasuredStates(r8, r2)
            r2 = r3
        L_0x0176:
            int r4 = r21 + 1
            r7 = r19
            r8 = r20
            r9 = r22
            r3 = r23
            goto L_0x0055
        L_0x0182:
            r7 = r1
            r9 = r2
            r8 = r5
            r1 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r1 = r1 & r8
            r2 = r27
            int r1 = android.view.View.resolveSizeAndState(r7, r2, r1)
            int r2 = r8 << 16
            r3 = r28
            int r2 = android.view.View.resolveSizeAndState(r9, r3, r2)
            r0.setMeasuredDimension(r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.onMeasure(int, int):void");
    }

    public void onMeasureChild(View view, int i2, int i7, int i8, int i10) {
        measureChildWithMargins(view, i2, i7, i8, i10);
    }

    public boolean onNestedFling(View view, float f, float f5, boolean z) {
        boolean z3;
        float f8;
        float f10;
        View view2;
        CoordinatorLayout coordinatorLayout;
        Behavior behavior;
        int childCount = getChildCount();
        int i2 = 0;
        boolean z7 = false;
        while (i2 < childCount) {
            View childAt = this.getChildAt(i2);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.isNestedScrollAccepted(0) && (behavior = layoutParams.getBehavior()) != null) {
                    coordinatorLayout = this;
                    view2 = view;
                    f10 = f;
                    f8 = f5;
                    z3 = z;
                    z7 |= behavior.onNestedFling(coordinatorLayout, childAt, view2, f10, f8, z3);
                    i2++;
                    this = coordinatorLayout;
                    view = view2;
                    f = f10;
                    f5 = f8;
                    z = z3;
                }
            }
            coordinatorLayout = this;
            view2 = view;
            f10 = f;
            f8 = f5;
            z3 = z;
            i2++;
            this = coordinatorLayout;
            view = view2;
            f = f10;
            f5 = f8;
            z = z3;
        }
        CoordinatorLayout coordinatorLayout2 = this;
        if (z7) {
            coordinatorLayout2.onChildViewsChanged(1);
        }
        return z7;
    }

    public boolean onNestedPreFling(View view, float f, float f5) {
        float f8;
        float f10;
        View view2;
        CoordinatorLayout coordinatorLayout;
        Behavior behavior;
        int childCount = getChildCount();
        int i2 = 0;
        boolean z = false;
        while (i2 < childCount) {
            View childAt = this.getChildAt(i2);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.isNestedScrollAccepted(0) && (behavior = layoutParams.getBehavior()) != null) {
                    coordinatorLayout = this;
                    view2 = view;
                    f10 = f;
                    f8 = f5;
                    z |= behavior.onNestedPreFling(coordinatorLayout, childAt, view2, f10, f8);
                    i2++;
                    this = coordinatorLayout;
                    view = view2;
                    f = f10;
                    f5 = f8;
                }
            }
            coordinatorLayout = this;
            view2 = view;
            f10 = f;
            f8 = f5;
            i2++;
            this = coordinatorLayout;
            view = view2;
            f = f10;
            f5 = f8;
        }
        return z;
    }

    public void onNestedPreScroll(View view, int i2, int i7, int[] iArr) {
        onNestedPreScroll(view, i2, i7, iArr, 0);
    }

    public void onNestedScroll(View view, int i2, int i7, int i8, int i10) {
        onNestedScroll(view, i2, i7, i8, i10, 0);
    }

    public void onNestedScrollAccepted(View view, View view2, int i2) {
        onNestedScrollAccepted(view, view2, i2, 0);
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        SparseArray<Parcelable> sparseArray = savedState.behaviorStates;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            int id = childAt.getId();
            Behavior behavior = getResolvedLayoutParams(childAt).getBehavior();
            if (!(id == -1 || behavior == null || (parcelable2 = sparseArray.get(id)) == null)) {
                behavior.onRestoreInstanceState(this, childAt, parcelable2);
            }
        }
    }

    public Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            int id = childAt.getId();
            Behavior behavior = ((LayoutParams) childAt.getLayoutParams()).getBehavior();
            if (!(id == -1 || behavior == null || (onSaveInstanceState = behavior.onSaveInstanceState(this, childAt)) == null)) {
                sparseArray.append(id, onSaveInstanceState);
            }
        }
        savedState.behaviorStates = sparseArray;
        return savedState;
    }

    public boolean onStartNestedScroll(View view, View view2, int i2) {
        return onStartNestedScroll(view, view2, i2, 0);
    }

    public void onStopNestedScroll(View view) {
        onStopNestedScroll(view, 0);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        int actionMasked = motionEvent.getActionMasked();
        View view = this.mBehaviorTouchView;
        boolean z3 = false;
        if (view != null) {
            Behavior behavior = ((LayoutParams) view.getLayoutParams()).getBehavior();
            z = behavior != null ? behavior.onTouchEvent(this, this.mBehaviorTouchView, motionEvent) : false;
        } else {
            z = performIntercept(motionEvent, 1);
            if (actionMasked != 0 && z) {
                z3 = true;
            }
        }
        if (this.mBehaviorTouchView == null || actionMasked == 3) {
            z |= super.onTouchEvent(motionEvent);
        } else if (z3) {
            MotionEvent obtainCancelEvent = obtainCancelEvent(motionEvent);
            super.onTouchEvent(obtainCancelEvent);
            obtainCancelEvent.recycle();
        }
        if (actionMasked != 1 && actionMasked != 3) {
            return z;
        }
        this.mBehaviorTouchView = null;
        resetTouchBehaviors();
        return z;
    }

    public void recordLastChildRect(View view, Rect rect) {
        ((LayoutParams) view.getLayoutParams()).setLastChildRect(rect);
    }

    public void removePreDrawListener() {
        if (this.mIsAttachedToWindow && this.mOnPreDrawListener != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
        }
        this.mNeedsPreDrawListener = false;
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z, int i2) {
        if (i2 == 3) {
            return false;
        }
        return requestChildRectangleOnScreen(view, rect, z);
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        if (z && !this.mDisallowInterceptReset) {
            if (this.mBehaviorTouchView == null) {
                cancelInterceptBehaviors();
            }
            resetTouchBehaviors();
            this.mDisallowInterceptReset = true;
        }
    }

    public void setFitsSystemWindows(boolean z) {
        super.setFitsSystemWindows(z);
        setupForInsets();
    }

    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.mOnHierarchyChangeListener = onHierarchyChangeListener;
    }

    public void setStatusBarBackground(Drawable drawable) {
        boolean z;
        Drawable drawable2 = this.mStatusBarBackground;
        if (drawable2 != drawable) {
            Drawable drawable3 = null;
            if (drawable2 != null) {
                drawable2.setCallback((Drawable.Callback) null);
            }
            if (drawable != null) {
                drawable3 = drawable.mutate();
            }
            this.mStatusBarBackground = drawable3;
            if (drawable3 != null) {
                if (drawable3.isStateful()) {
                    this.mStatusBarBackground.setState(getDrawableState());
                }
                DrawableCompat.setLayoutDirection(this.mStatusBarBackground, ViewCompat.getLayoutDirection(this));
                Drawable drawable4 = this.mStatusBarBackground;
                if (getVisibility() == 0) {
                    z = true;
                } else {
                    z = false;
                }
                drawable4.setVisible(z, false);
                this.mStatusBarBackground.setCallback(this);
            }
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void setStatusBarBackgroundColor(int i2) {
        setStatusBarBackground(new ColorDrawable(i2));
    }

    public void setStatusBarBackgroundResource(int i2) {
        Drawable drawable;
        if (i2 != 0) {
            drawable = ContextCompat.getDrawable(getContext(), i2);
        } else {
            drawable = null;
        }
        setStatusBarBackground(drawable);
    }

    public void setVisibility(int i2) {
        boolean z;
        super.setVisibility(i2);
        if (i2 == 0) {
            z = true;
        } else {
            z = false;
        }
        Drawable drawable = this.mStatusBarBackground;
        if (drawable != null && drawable.isVisible() != z) {
            this.mStatusBarBackground.setVisible(z, false);
        }
    }

    public final WindowInsetsCompat setWindowInsets(WindowInsetsCompat windowInsetsCompat) {
        boolean z;
        if (ObjectsCompat.equals(this.mLastInsets, windowInsetsCompat)) {
            return windowInsetsCompat;
        }
        this.mLastInsets = windowInsetsCompat;
        boolean z3 = false;
        if (windowInsetsCompat == null || windowInsetsCompat.getSystemWindowInsetTop() <= 0) {
            z = false;
        } else {
            z = true;
        }
        this.mDrawStatusBarBackground = z;
        if (!z && getBackground() == null) {
            z3 = true;
        }
        setWillNotDraw(z3);
        WindowInsetsCompat dispatchApplyWindowInsetsToBehaviors = dispatchApplyWindowInsetsToBehaviors(windowInsetsCompat);
        requestLayout();
        return dispatchApplyWindowInsetsToBehaviors;
    }

    public boolean verifyDrawable(Drawable drawable) {
        if (super.verifyDrawable(drawable) || drawable == this.mStatusBarBackground) {
            return true;
        }
        return false;
    }

    public CoordinatorLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        TypedArray obtainStyledAttributes;
        Context context2;
        CoordinatorLayout coordinatorLayout;
        this.mDependencySortedChildren = new ArrayList();
        this.mChildDag = new DirectedAcyclicGraph<>();
        this.mTempList1 = new ArrayList();
        this.mBehaviorConsumed = new int[2];
        this.mNestedScrollingV2ConsumedCompat = new int[2];
        this.mEnableAutoCollapsingKeyEvent = true;
        this.mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        if (i2 == 0) {
            obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CoordinatorLayout, 0, R$style.Widget_Support_CoordinatorLayout);
        } else {
            obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CoordinatorLayout, i2, 0);
        }
        TypedArray typedArray = obtainStyledAttributes;
        if (i2 == 0) {
            coordinatorLayout = this;
            context2 = context;
            ViewCompat.saveAttributeDataForStyleable(coordinatorLayout, context2, R$styleable.CoordinatorLayout, attributeSet, typedArray, 0, R$style.Widget_Support_CoordinatorLayout);
        } else {
            coordinatorLayout = this;
            context2 = context;
            ViewCompat.saveAttributeDataForStyleable(coordinatorLayout, context2, R$styleable.CoordinatorLayout, attributeSet, typedArray, i2, 0);
        }
        int resourceId = typedArray.getResourceId(R$styleable.CoordinatorLayout_keylines, 0);
        if (resourceId != 0) {
            Resources resources = context2.getResources();
            coordinatorLayout.mKeylines = resources.getIntArray(resourceId);
            float f = resources.getDisplayMetrics().density;
            int length = coordinatorLayout.mKeylines.length;
            for (int i7 = 0; i7 < length; i7++) {
                int[] iArr = coordinatorLayout.mKeylines;
                iArr[i7] = (int) (((float) iArr[i7]) * f);
            }
        }
        coordinatorLayout.mStatusBarBackground = typedArray.getDrawable(R$styleable.CoordinatorLayout_statusBarBackground);
        typedArray.recycle();
        coordinatorLayout.setupForInsets();
        super.setOnHierarchyChangeListener(new HierarchyChangeListener());
        if (ViewCompat.getImportantForAccessibility(coordinatorLayout) == 0) {
            ViewCompat.setImportantForAccessibility(coordinatorLayout, 1);
        }
    }

    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    public void onNestedPreScroll(View view, int i2, int i7, int[] iArr, int i8) {
        Behavior behavior;
        int min;
        int min2;
        int childCount = getChildCount();
        boolean z = false;
        int i10 = 0;
        int i11 = 0;
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt = getChildAt(i12);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int i13 = i8;
                if (layoutParams.isNestedScrollAccepted(i13) && (behavior = layoutParams.getBehavior()) != null) {
                    int[] iArr2 = this.mBehaviorConsumed;
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                    behavior.onNestedPreScroll(this, childAt, view, i2, i7, iArr2, i13);
                    if (i2 > 0) {
                        min = Math.max(i10, this.mBehaviorConsumed[0]);
                    } else {
                        min = Math.min(i10, this.mBehaviorConsumed[0]);
                    }
                    i10 = min;
                    if (i7 > 0) {
                        min2 = Math.max(i11, this.mBehaviorConsumed[1]);
                    } else {
                        min2 = Math.min(i11, this.mBehaviorConsumed[1]);
                    }
                    i11 = min2;
                    z = true;
                }
            }
        }
        iArr[0] = i10;
        iArr[1] = i11;
        if (z) {
            onChildViewsChanged(1);
        }
    }

    public void onNestedScroll(View view, int i2, int i7, int i8, int i10, int i11) {
        onNestedScroll(view, i2, i7, i8, i10, 0, this.mNestedScrollingV2ConsumedCompat);
    }

    public void onNestedScrollAccepted(View view, View view2, int i2, int i7) {
        int i8;
        int i10;
        View view3;
        View view4;
        CoordinatorLayout coordinatorLayout;
        Behavior behavior;
        this.mNestedScrollingParentHelper.onNestedScrollAccepted(view, view2, i2, i7);
        this.mNestedScrollingTarget = view2;
        this.mLastNestedScrollingChild = view2;
        int childCount = getChildCount();
        int i11 = 0;
        while (i11 < childCount) {
            View childAt = this.getChildAt(i11);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (layoutParams.isNestedScrollAccepted(i7) && (behavior = layoutParams.getBehavior()) != null) {
                coordinatorLayout = this;
                view4 = view;
                view3 = view2;
                i10 = i2;
                i8 = i7;
                behavior.onNestedScrollAccepted(coordinatorLayout, childAt, view4, view3, i10, i8);
            } else {
                coordinatorLayout = this;
                view4 = view;
                view3 = view2;
                i10 = i2;
                i8 = i7;
            }
            i11++;
            this = coordinatorLayout;
            view = view4;
            view2 = view3;
            i2 = i10;
            i7 = i8;
        }
    }

    public boolean onStartNestedScroll(View view, View view2, int i2, int i7) {
        int childCount = getChildCount();
        boolean z = false;
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() == 8) {
                int i10 = i7;
            } else {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                Behavior behavior = layoutParams.getBehavior();
                if (behavior != null) {
                    int i11 = i7;
                    boolean onStartNestedScroll = behavior.onStartNestedScroll(this, childAt, view, view2, i2, i11);
                    z |= onStartNestedScroll;
                    layoutParams.setNestedScrollAccepted(i11, onStartNestedScroll);
                } else {
                    layoutParams.setNestedScrollAccepted(i7, false);
                }
            }
        }
        return z;
    }

    public void onStopNestedScroll(View view, int i2) {
        this.mNestedScrollingParentHelper.onStopNestedScroll(view, i2);
        this.mLastNestedScrollingChild = view;
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (layoutParams.isNestedScrollAccepted(i2)) {
                Behavior behavior = layoutParams.getBehavior();
                if (behavior != null) {
                    behavior.onStopNestedScroll(this, childAt, view, i2);
                }
                layoutParams.resetNestedScroll(i2);
                layoutParams.resetChangedAfterNestedScroll();
            }
        }
        this.mNestedScrollingTarget = null;
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        Behavior behavior = ((LayoutParams) view.getLayoutParams()).getBehavior();
        if (behavior == null || !behavior.onRequestChildRectangleOnScreen(this, view, rect, z)) {
            return super.requestChildRectangleOnScreen(view, rect, z);
        }
        return true;
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public void onNestedScroll(View view, int i2, int i7, int i8, int i10, int i11, int[] iArr) {
        Behavior behavior;
        int min;
        int min2;
        int childCount = getChildCount();
        boolean z = false;
        int i12 = 0;
        int i13 = 0;
        for (int i14 = 0; i14 < childCount; i14++) {
            View childAt = getChildAt(i14);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int i15 = i11;
                if (layoutParams.isNestedScrollAccepted(i15) && (behavior = layoutParams.getBehavior()) != null) {
                    int[] iArr2 = this.mBehaviorConsumed;
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                    behavior.onNestedScroll(this, childAt, view, i2, i7, i8, i10, i15, iArr2);
                    if (i8 > 0) {
                        min = Math.max(i12, this.mBehaviorConsumed[0]);
                    } else {
                        min = Math.min(i12, this.mBehaviorConsumed[0]);
                    }
                    i12 = min;
                    if (i10 > 0) {
                        min2 = Math.max(i13, this.mBehaviorConsumed[1]);
                    } else {
                        min2 = Math.min(i13, this.mBehaviorConsumed[1]);
                    }
                    i13 = min2;
                    z = true;
                }
            }
        }
        iArr[0] = iArr[0] + i12;
        iArr[1] = iArr[1] + i13;
        if (z) {
            onChildViewsChanged(1);
        }
    }

    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() {
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }

            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, (ClassLoader) null);
            }
        };
        SparseArray<Parcelable> behaviorStates;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            int readInt = parcel.readInt();
            int[] iArr = new int[readInt];
            parcel.readIntArray(iArr);
            Parcelable[] readParcelableArray = parcel.readParcelableArray(classLoader);
            this.behaviorStates = new SparseArray<>(readInt);
            for (int i2 = 0; i2 < readInt; i2++) {
                this.behaviorStates.append(iArr[i2], readParcelableArray[i2]);
            }
        }

        public void writeToParcel(Parcel parcel, int i2) {
            int i7;
            super.writeToParcel(parcel, i2);
            SparseArray<Parcelable> sparseArray = this.behaviorStates;
            if (sparseArray != null) {
                i7 = sparseArray.size();
            } else {
                i7 = 0;
            }
            parcel.writeInt(i7);
            int[] iArr = new int[i7];
            Parcelable[] parcelableArr = new Parcelable[i7];
            for (int i8 = 0; i8 < i7; i8++) {
                iArr[i8] = this.behaviorStates.keyAt(i8);
                parcelableArr[i8] = this.behaviorStates.valueAt(i8);
            }
            parcel.writeIntArray(iArr);
            parcel.writeParcelableArray(parcelableArr, i2);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int anchorGravity = 0;
        public int dodgeInsetEdges = 0;
        public int gravity = 0;
        public int insetEdge = 0;
        public int keyline = -1;
        View mAnchorDirectChild;
        int mAnchorId = -1;
        View mAnchorView;
        Behavior mBehavior;
        boolean mBehaviorResolved = false;
        Object mBehaviorTag;
        private boolean mDidAcceptNestedScrollNonTouch;
        private boolean mDidAcceptNestedScrollTouch;
        private boolean mDidBlockInteraction;
        private boolean mDidChangeAfterNestedScroll;
        int mInsetOffsetX;
        int mInsetOffsetY;
        final Rect mLastChildRect = new Rect();

        public LayoutParams(int i2, int i7) {
            super(i2, i7);
        }

        private void resolveAnchorView(View view, CoordinatorLayout coordinatorLayout) {
            View findViewById = coordinatorLayout.findViewById(this.mAnchorId);
            this.mAnchorView = findViewById;
            if (findViewById != null) {
                if (findViewById != coordinatorLayout) {
                    ViewParent parent = findViewById.getParent();
                    while (parent != coordinatorLayout && parent != null) {
                        if (parent != view) {
                            if (parent instanceof View) {
                                findViewById = (View) parent;
                            }
                            parent = parent.getParent();
                        } else if (coordinatorLayout.isInEditMode()) {
                            this.mAnchorDirectChild = null;
                            this.mAnchorView = null;
                            return;
                        } else {
                            throw new IllegalStateException("Anchor must not be a descendant of the anchored view");
                        }
                    }
                    this.mAnchorDirectChild = findViewById;
                } else if (coordinatorLayout.isInEditMode()) {
                    this.mAnchorDirectChild = null;
                    this.mAnchorView = null;
                } else {
                    throw new IllegalStateException("View can not be anchored to the the parent CoordinatorLayout");
                }
            } else if (coordinatorLayout.isInEditMode()) {
                this.mAnchorDirectChild = null;
                this.mAnchorView = null;
            } else {
                throw new IllegalStateException("Could not find CoordinatorLayout descendant view with id " + coordinatorLayout.getResources().getResourceName(this.mAnchorId) + " to anchor view " + view);
            }
        }

        private boolean shouldDodge(View view, int i2) {
            int absoluteGravity = GravityCompat.getAbsoluteGravity(((LayoutParams) view.getLayoutParams()).insetEdge, i2);
            if (absoluteGravity == 0 || (GravityCompat.getAbsoluteGravity(this.dodgeInsetEdges, i2) & absoluteGravity) != absoluteGravity) {
                return false;
            }
            return true;
        }

        private boolean verifyAnchorView(View view, CoordinatorLayout coordinatorLayout) {
            if (this.mAnchorView.getId() != this.mAnchorId) {
                return false;
            }
            View view2 = this.mAnchorView;
            for (ViewParent parent = view2.getParent(); parent != coordinatorLayout; parent = parent.getParent()) {
                if (parent == null || parent == view) {
                    this.mAnchorDirectChild = null;
                    this.mAnchorView = null;
                    return false;
                }
                if (parent instanceof View) {
                    view2 = (View) parent;
                }
            }
            this.mAnchorDirectChild = view2;
            return true;
        }

        public boolean checkAnchorChanged() {
            if (this.mAnchorView != null || this.mAnchorId == -1) {
                return false;
            }
            return true;
        }

        public boolean dependsOn(CoordinatorLayout coordinatorLayout, View view, View view2) {
            if (view2 == this.mAnchorDirectChild || shouldDodge(view2, ViewCompat.getLayoutDirection(coordinatorLayout))) {
                return true;
            }
            Behavior behavior = this.mBehavior;
            if (behavior == null || !behavior.layoutDependsOn(coordinatorLayout, view, view2)) {
                return false;
            }
            return true;
        }

        public boolean didBlockInteraction() {
            if (this.mBehavior == null) {
                this.mDidBlockInteraction = false;
            }
            return this.mDidBlockInteraction;
        }

        public View findAnchorView(CoordinatorLayout coordinatorLayout, View view) {
            if (this.mAnchorId == -1) {
                this.mAnchorDirectChild = null;
                this.mAnchorView = null;
                return null;
            }
            if (this.mAnchorView == null || !verifyAnchorView(view, coordinatorLayout)) {
                resolveAnchorView(view, coordinatorLayout);
            }
            return this.mAnchorView;
        }

        public int getAnchorId() {
            return this.mAnchorId;
        }

        public Behavior getBehavior() {
            return this.mBehavior;
        }

        public boolean getChangedAfterNestedScroll() {
            return this.mDidChangeAfterNestedScroll;
        }

        public Rect getLastChildRect() {
            return this.mLastChildRect;
        }

        public boolean isBlockingInteractionBelow(CoordinatorLayout coordinatorLayout, View view) {
            boolean z;
            boolean z3 = this.mDidBlockInteraction;
            if (z3) {
                return true;
            }
            Behavior behavior = this.mBehavior;
            if (behavior != null) {
                z = behavior.blocksInteractionBelow(coordinatorLayout, view);
            } else {
                z = false;
            }
            boolean z7 = z | z3;
            this.mDidBlockInteraction = z7;
            return z7;
        }

        public boolean isNestedScrollAccepted(int i2) {
            if (i2 == 0) {
                return this.mDidAcceptNestedScrollTouch;
            }
            if (i2 != 1) {
                return false;
            }
            return this.mDidAcceptNestedScrollNonTouch;
        }

        public void resetChangedAfterNestedScroll() {
            this.mDidChangeAfterNestedScroll = false;
        }

        public void resetNestedScroll(int i2) {
            setNestedScrollAccepted(i2, false);
        }

        public void resetTouchBehaviorTracking() {
            this.mDidBlockInteraction = false;
        }

        public void setBehavior(Behavior behavior) {
            Behavior behavior2 = this.mBehavior;
            if (behavior2 != behavior) {
                if (behavior2 != null) {
                    behavior2.onDetachedFromLayoutParams();
                }
                this.mBehavior = behavior;
                this.mBehaviorTag = null;
                this.mBehaviorResolved = true;
                if (behavior != null) {
                    behavior.onAttachedToLayoutParams(this);
                }
            }
        }

        public void setChangedAfterNestedScroll(boolean z) {
            this.mDidChangeAfterNestedScroll = z;
        }

        public void setLastChildRect(Rect rect) {
            this.mLastChildRect.set(rect);
        }

        public void setNestedScrollAccepted(int i2, boolean z) {
            if (i2 == 0) {
                this.mDidAcceptNestedScrollTouch = z;
            } else if (i2 == 1) {
                this.mDidAcceptNestedScrollNonTouch = z;
            }
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CoordinatorLayout_Layout);
            this.gravity = obtainStyledAttributes.getInteger(R$styleable.CoordinatorLayout_Layout_android_layout_gravity, 0);
            this.mAnchorId = obtainStyledAttributes.getResourceId(R$styleable.CoordinatorLayout_Layout_layout_anchor, -1);
            this.anchorGravity = obtainStyledAttributes.getInteger(R$styleable.CoordinatorLayout_Layout_layout_anchorGravity, 0);
            this.keyline = obtainStyledAttributes.getInteger(R$styleable.CoordinatorLayout_Layout_layout_keyline, -1);
            this.insetEdge = obtainStyledAttributes.getInt(R$styleable.CoordinatorLayout_Layout_layout_insetEdge, 0);
            this.dodgeInsetEdges = obtainStyledAttributes.getInt(R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges, 0);
            int i2 = R$styleable.CoordinatorLayout_Layout_layout_behavior;
            boolean hasValue = obtainStyledAttributes.hasValue(i2);
            this.mBehaviorResolved = hasValue;
            if (hasValue) {
                this.mBehavior = CoordinatorLayout.parseBehavior(context, attributeSet, obtainStyledAttributes.getString(i2));
            }
            obtainStyledAttributes.recycle();
            Behavior behavior = this.mBehavior;
            if (behavior != null) {
                behavior.onAttachedToLayoutParams(this);
            }
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }
}
