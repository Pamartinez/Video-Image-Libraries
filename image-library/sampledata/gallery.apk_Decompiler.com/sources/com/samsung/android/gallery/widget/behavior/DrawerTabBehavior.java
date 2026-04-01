package com.samsung.android.gallery.widget.behavior;

import a6.C0418a;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.ViewDragHelper;
import c0.C0086a;
import com.samsung.android.gallery.widget.R$dimen;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DrawerTabBehavior<V extends View> extends CoordinatorLayout.Behavior<V> implements SheetBehaviorCompat {
    private final ViewDragHelper.Callback dragCallback = new ViewDragHelper.Callback() {
        public int clampViewPositionHorizontal(View view, int i2, int i7) {
            int i8;
            int i10;
            if (DrawerTabBehavior.this.mIsRtl) {
                i8 = (DrawerTabBehavior.this.mCollapsedOffset + DrawerTabBehavior.this.mParentWidth) - DrawerTabBehavior.this.mCollapsedWidth;
            } else {
                i8 = DrawerTabBehavior.this.mCollapsedOffset;
            }
            if (DrawerTabBehavior.this.mIsRtl) {
                i10 = DrawerTabBehavior.this.mParentWidth - DrawerTabBehavior.this.mCollapsedWidth;
            } else {
                i10 = 0;
            }
            return MathUtils.clamp(i2, i8, i10);
        }

        public int clampViewPositionVertical(View view, int i2, int i7) {
            return view.getTop();
        }

        public int getViewHorizontalDragRange(View view) {
            return Math.abs(DrawerTabBehavior.this.mCollapsedOffset);
        }

        public void onViewDragStateChanged(int i2) {
            if (i2 == 1 && DrawerTabBehavior.this.isDraggable()) {
                DrawerTabBehavior.this.setStateInternal(1);
            }
        }

        public void onViewPositionChanged(View view, int i2, int i7, int i8, int i10) {
            DrawerTabBehavior.this.dispatchOnSlide(i2);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:15:0x004a, code lost:
            if (com.samsung.android.gallery.widget.behavior.DrawerTabBehavior.e(r4.this$0) != false) goto L_0x0061;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:6:0x001f, code lost:
            if (com.samsung.android.gallery.widget.behavior.DrawerTabBehavior.e(r4.this$0) != false) goto L_0x0021;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onViewReleased(android.view.View r5, float r6, float r7) {
            /*
                r4 = this;
                r0 = 0
                int r0 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
                r1 = 3
                r2 = 4
                r3 = 0
                if (r0 <= 0) goto L_0x0023
                r5.getLeft()
                com.samsung.android.gallery.widget.behavior.DrawerTabBehavior r6 = com.samsung.android.gallery.widget.behavior.DrawerTabBehavior.this
                boolean r6 = r6.mIsRtl
                if (r6 == 0) goto L_0x0019
                com.samsung.android.gallery.widget.behavior.DrawerTabBehavior r6 = com.samsung.android.gallery.widget.behavior.DrawerTabBehavior.this
                int r3 = r6.mCollapsedOffset
            L_0x0019:
                com.samsung.android.gallery.widget.behavior.DrawerTabBehavior r6 = com.samsung.android.gallery.widget.behavior.DrawerTabBehavior.this
                boolean r6 = r6.mIsRtl
                if (r6 == 0) goto L_0x0061
            L_0x0021:
                r1 = r2
                goto L_0x0061
            L_0x0023:
                if (r0 == 0) goto L_0x004d
                float r7 = java.lang.Math.abs(r7)
                float r6 = java.lang.Math.abs(r6)
                int r6 = (r7 > r6 ? 1 : (r7 == r6 ? 0 : -1))
                if (r6 <= 0) goto L_0x0032
                goto L_0x004d
            L_0x0032:
                r5.getLeft()
                com.samsung.android.gallery.widget.behavior.DrawerTabBehavior r6 = com.samsung.android.gallery.widget.behavior.DrawerTabBehavior.this
                boolean r6 = r6.mIsRtl
                if (r6 == 0) goto L_0x003e
                goto L_0x0044
            L_0x003e:
                com.samsung.android.gallery.widget.behavior.DrawerTabBehavior r6 = com.samsung.android.gallery.widget.behavior.DrawerTabBehavior.this
                int r3 = r6.mCollapsedOffset
            L_0x0044:
                com.samsung.android.gallery.widget.behavior.DrawerTabBehavior r6 = com.samsung.android.gallery.widget.behavior.DrawerTabBehavior.this
                boolean r6 = r6.mIsRtl
                if (r6 == 0) goto L_0x0021
                goto L_0x0061
            L_0x004d:
                int r6 = r5.getLeft()
                com.samsung.android.gallery.widget.behavior.DrawerTabBehavior r7 = com.samsung.android.gallery.widget.behavior.DrawerTabBehavior.this
                int r7 = r7.mHalfExpandedOffset
                if (r6 <= r7) goto L_0x005a
                goto L_0x0061
            L_0x005a:
                com.samsung.android.gallery.widget.behavior.DrawerTabBehavior r6 = com.samsung.android.gallery.widget.behavior.DrawerTabBehavior.this
                int r3 = r6.mCollapsedOffset
                goto L_0x0021
            L_0x0061:
                com.samsung.android.gallery.widget.behavior.DrawerTabBehavior r4 = com.samsung.android.gallery.widget.behavior.DrawerTabBehavior.this
                r6 = 1
                r4.startSettlingAnimation(r5, r1, r3, r6)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.behavior.DrawerTabBehavior.AnonymousClass1.onViewReleased(android.view.View, float, float):void");
        }

        public boolean tryCaptureView(View view, int i2) {
            View view2;
            DrawerTabBehavior drawerTabBehavior = DrawerTabBehavior.this;
            int i7 = drawerTabBehavior.mState;
            if (i7 == 1 || drawerTabBehavior.mTouchingScrollingChild) {
                return false;
            }
            if (i7 == 3 && drawerTabBehavior.mActivePointerId == i2) {
                WeakReference<View> weakReference = drawerTabBehavior.mNestedScrollingChildRef;
                if (weakReference != null) {
                    view2 = weakReference.get();
                } else {
                    view2 = null;
                }
                if (view2 != null && view2.canScrollHorizontally(-1)) {
                    return false;
                }
            }
            WeakReference<V> weakReference2 = DrawerTabBehavior.this.mViewRef;
            if (weakReference2 == null || weakReference2.get() != view) {
                return false;
            }
            return true;
        }
    };
    int mActivePointerId;
    private DrawerSlideCallback mCallback;
    private int mChildWidth;
    /* access modifiers changed from: private */
    public int mCollapsedOffset;
    /* access modifiers changed from: private */
    public int mCollapsedWidth;
    private boolean mDraggable = true;
    /* access modifiers changed from: private */
    public int mHalfExpandedOffset;
    private boolean mIgnoreEvents;
    private int mInitialX;
    /* access modifiers changed from: private */
    public boolean mIsRtl;
    private int mLastNestedScrollDx;
    private boolean mNestedScrolled;
    WeakReference<View> mNestedScrollingChildRef;
    /* access modifiers changed from: private */
    public int mParentWidth;
    private DrawerTabBehavior<V>.SettleRunnable mSettleRunnable = null;
    int mState = 4;
    boolean mTouchingScrollingChild;
    private VelocityTracker mVelocityTracker;
    ViewDragHelper mViewDragHelper;
    WeakReference<V> mViewRef;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface DrawerSlideCallback {
        boolean canDrag();

        void onSlide(View view, float f);

        void onStateChanged(int i2);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class SettleRunnable implements Runnable {
        /* access modifiers changed from: private */
        public boolean mIsPosted;
        private int mTargetState;
        private final View mView;

        public SettleRunnable(View view, int i2) {
            this.mView = view;
            this.mTargetState = i2;
        }

        public void run() {
            ViewDragHelper viewDragHelper = DrawerTabBehavior.this.mViewDragHelper;
            if (viewDragHelper == null || !viewDragHelper.continueSettling(true)) {
                DrawerTabBehavior.this.setStateInternal(this.mTargetState);
            } else {
                ViewCompat.postOnAnimation(this.mView, this);
            }
            this.mIsPosted = false;
        }

        public void setTargetState(int i2) {
            this.mTargetState = i2;
        }
    }

    public DrawerTabBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void calculateCollapsedOffset() {
        this.mCollapsedOffset = (-this.mChildWidth) + this.mCollapsedWidth;
    }

    private void calculateHalfExpandedOffset() {
        this.mHalfExpandedOffset = -((int) (((float) this.mChildWidth) * 0.7f));
    }

    private void calculateOffsets() {
        calculateHalfExpandedOffset();
        calculateCollapsedOffset();
    }

    public static <V extends View> DrawerTabBehavior<V> from(V v) {
        ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
            CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior();
            if (behavior instanceof DrawerTabBehavior) {
                return (DrawerTabBehavior) behavior;
            }
            throw new IllegalArgumentException("The view is not associated with HorizontalBehavior");
        }
        throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
    }

    /* access modifiers changed from: private */
    public boolean isDraggable() {
        DrawerSlideCallback drawerSlideCallback = this.mCallback;
        if ((drawerSlideCallback == null || drawerSlideCallback.canDrag()) && this.mDraggable) {
            return true;
        }
        return false;
    }

    private boolean isPointInChildBounds(View view, int i2) {
        if (this.mIsRtl) {
            if (view.getLeft() < i2) {
                return true;
            }
            return false;
        } else if (view.getRight() > i2) {
            return true;
        } else {
            return false;
        }
    }

    private void offsetLeftAndRight(V v, int i2) {
        if (this.mIsRtl) {
            i2 = -i2;
        }
        ViewCompat.offsetLeftAndRight(v, i2);
    }

    private void reset() {
        this.mActivePointerId = -1;
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    private void settleToStatePendingLayout(int i2) {
        View view = (View) this.mViewRef.get();
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent == null || !parent.isLayoutRequested() || !ViewCompat.isAttachedToWindow(view)) {
                lambda$settleToStatePendingLayout$0(view, i2);
            } else {
                view.post(new C0418a((Object) this, (Object) view, i2, 12));
            }
        }
    }

    public void dispatchOnSlide(int i2) {
        View view;
        DrawerSlideCallback drawerSlideCallback;
        float f;
        int i7;
        float f5;
        WeakReference<V> weakReference = this.mViewRef;
        if (weakReference != null && (view = (View) weakReference.get()) != null && (drawerSlideCallback = this.mCallback) != null) {
            if (this.mIsRtl) {
                i7 = this.mCollapsedOffset;
                if (i2 < i7 || i7 == 0) {
                    f = ((float) ((i2 - i7) - this.mCollapsedWidth)) / ((float) (i7 - this.mChildWidth));
                    drawerSlideCallback.onSlide(view, f);
                }
                f5 = (float) ((this.mParentWidth - i2) - this.mCollapsedWidth);
            } else {
                i7 = this.mCollapsedOffset;
                if (i2 < i7 || i7 == 0) {
                    f = ((float) (i2 - i7)) / ((float) (i7 - this.mChildWidth));
                    drawerSlideCallback.onSlide(view, f);
                }
                f5 = (float) (i2 - i7);
            }
            f = f5 / ((float) (0 - i7));
            drawerSlideCallback.onSlide(view, f);
        }
    }

    public View findScrollingChild(View view) {
        if (ViewCompat.isNestedScrollingEnabled(view)) {
            return view;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View findScrollingChild = findScrollingChild(viewGroup.getChildAt(i2));
            if (findScrollingChild != null) {
                return findScrollingChild;
            }
        }
        return null;
    }

    public void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams layoutParams) {
        super.onAttachedToLayoutParams(layoutParams);
        this.mViewRef = null;
        this.mViewDragHelper = null;
    }

    public void onDetachedFromLayoutParams() {
        super.onDetachedFromLayoutParams();
        this.mViewRef = null;
        this.mViewDragHelper = null;
    }

    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        ViewDragHelper viewDragHelper;
        boolean z;
        View view;
        if (!v.isShown() || !isDraggable()) {
            this.mIgnoreEvents = true;
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            reset();
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        View view2 = null;
        if (actionMasked == 0) {
            this.mInitialX = (int) motionEvent.getX();
            if (this.mState != 2) {
                WeakReference<View> weakReference = this.mNestedScrollingChildRef;
                if (weakReference != null) {
                    view = weakReference.get();
                } else {
                    view = null;
                }
                if (view != null && isPointInChildBounds(v, this.mInitialX)) {
                    this.mActivePointerId = motionEvent.getPointerId(motionEvent.getActionIndex());
                    this.mTouchingScrollingChild = true;
                }
            }
            if (this.mActivePointerId != -1 || isPointInChildBounds(v, this.mInitialX)) {
                z = false;
            } else {
                z = true;
            }
            this.mIgnoreEvents = z;
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.mTouchingScrollingChild = false;
            this.mActivePointerId = -1;
            if (this.mIgnoreEvents) {
                this.mIgnoreEvents = false;
                return false;
            }
        }
        if (!this.mIgnoreEvents && (viewDragHelper = this.mViewDragHelper) != null && viewDragHelper.shouldInterceptTouchEvent(motionEvent)) {
            return true;
        }
        WeakReference<View> weakReference2 = this.mNestedScrollingChildRef;
        if (weakReference2 != null) {
            view2 = weakReference2.get();
        }
        if (actionMasked != 2 || view2 == null || this.mIgnoreEvents || this.mState == 1 || !isPointInChildBounds(view2, (int) motionEvent.getX()) || this.mViewDragHelper == null || Math.abs(((float) this.mInitialX) - motionEvent.getX()) <= ((float) this.mViewDragHelper.getTouchSlop())) {
            return false;
        }
        return true;
    }

    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, V v, int i2) {
        boolean z;
        if (ViewCompat.getFitsSystemWindows(coordinatorLayout) && !ViewCompat.getFitsSystemWindows(v)) {
            v.setFitsSystemWindows(true);
        }
        if (this.mViewRef == null) {
            this.mViewRef = new WeakReference<>(v);
            if (ViewCompat.getImportantForAccessibility(v) == 0) {
                ViewCompat.setImportantForAccessibility(v, 1);
            }
        }
        if (this.mViewDragHelper == null) {
            this.mViewDragHelper = ViewDragHelper.create(coordinatorLayout, this.dragCallback);
        }
        if (i2 == 1) {
            z = true;
        } else {
            z = false;
        }
        this.mIsRtl = z;
        int left = v.getLeft();
        coordinatorLayout.onLayoutChild(v, i2);
        this.mChildWidth = v.getWidth();
        this.mParentWidth = coordinatorLayout.getWidth();
        this.mCollapsedWidth = v.getResources().getDimensionPixelOffset(R$dimen.drawer_collapsed_width);
        calculateOffsets();
        int i7 = this.mState;
        if (i7 == 3) {
            offsetLeftAndRight(v, 0);
        } else if (i7 == 6) {
            offsetLeftAndRight(v, this.mHalfExpandedOffset);
        } else if (i7 == 4) {
            offsetLeftAndRight(v, this.mCollapsedOffset);
        } else if (i7 == 1 || i7 == 2) {
            ViewCompat.offsetLeftAndRight(v, left - v.getLeft());
        }
        this.mNestedScrollingChildRef = new WeakReference<>(findScrollingChild(v));
        return true;
    }

    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, V v, View view, float f, float f5) {
        WeakReference<View> weakReference = this.mNestedScrollingChildRef;
        if (weakReference == null || view != weakReference.get() || (this.mState == 3 && !super.onNestedPreFling(coordinatorLayout, v, view, f, f5))) {
            return false;
        }
        return true;
    }

    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i2, int i7, int[] iArr, int i8) {
        View view2;
        if (i8 != 1) {
            WeakReference<View> weakReference = this.mNestedScrollingChildRef;
            if (weakReference != null) {
                view2 = weakReference.get();
            } else {
                view2 = null;
            }
            if (view == view2) {
                int left = v.getLeft();
                int i10 = left - i2;
                if (i2 > 0) {
                    if (i10 > 0) {
                        iArr[1] = left;
                        offsetLeftAndRight(v, -left);
                        setStateInternal(3);
                    } else if (isDraggable()) {
                        iArr[1] = i2;
                        offsetLeftAndRight(v, -i2);
                        setStateInternal(1);
                    } else {
                        return;
                    }
                } else if (i2 < 0 && !view.canScrollHorizontally(-1)) {
                    int i11 = this.mCollapsedOffset;
                    if (i10 < i11) {
                        int i12 = left - i11;
                        iArr[1] = i12;
                        offsetLeftAndRight(v, -i12);
                        setStateInternal(4);
                    } else if (isDraggable()) {
                        iArr[1] = i2;
                        offsetLeftAndRight(v, -i2);
                        setStateInternal(1);
                    } else {
                        return;
                    }
                }
                dispatchOnSlide(v.getLeft());
                this.mLastNestedScrollDx = i2;
                this.mNestedScrolled = true;
            }
        }
    }

    public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, V v, Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        if (savedState.getSuperState() != null) {
            super.onRestoreInstanceState(coordinatorLayout, v, savedState.getSuperState());
        }
        int i2 = savedState.state;
        if (i2 == 1 || i2 == 2) {
            this.mState = 4;
        } else {
            this.mState = i2;
        }
    }

    public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, V v) {
        return new SavedState(super.onSaveInstanceState(coordinatorLayout, v), (DrawerTabBehavior<?>) this);
    }

    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i2, int i7) {
        this.mLastNestedScrollDx = 0;
        this.mNestedScrolled = false;
        if ((i2 & 1) != 0) {
            return true;
        }
        return false;
    }

    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i2) {
        int i7;
        int i8 = 3;
        if (v.getLeft() == 0) {
            setStateInternal(3);
            return;
        }
        WeakReference<View> weakReference = this.mNestedScrollingChildRef;
        if (weakReference != null && view == weakReference.get() && this.mNestedScrolled) {
            int i10 = this.mLastNestedScrollDx;
            if (i10 > 0) {
                v.getLeft();
            } else {
                if (i10 != 0) {
                    v.getLeft();
                    i7 = this.mCollapsedOffset;
                } else if (v.getLeft() <= this.mHalfExpandedOffset) {
                    i7 = this.mCollapsedOffset;
                }
                i8 = 4;
                startSettlingAnimation(v, i8, i7, false);
                this.mNestedScrolled = false;
            }
            i7 = 0;
            startSettlingAnimation(v, i8, i7, false);
            this.mNestedScrolled = false;
        }
    }

    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (!v.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if ((this.mState == 1 && actionMasked == 0) || this.mActivePointerId != motionEvent.getPointerId(motionEvent.getActionIndex())) {
            return true;
        }
        ViewDragHelper viewDragHelper = this.mViewDragHelper;
        if (viewDragHelper != null) {
            viewDragHelper.processTouchEvent(motionEvent);
        }
        if (actionMasked == 0) {
            reset();
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        if (this.mViewDragHelper != null && actionMasked == 2 && !this.mIgnoreEvents) {
            float x9 = ((float) this.mInitialX) - motionEvent.getX();
            int i2 = this.mState;
            if ((i2 == 4 && (!this.mIsRtl ? x9 > 0.0f : x9 < 0.0f)) || (i2 == 3 && (!this.mIsRtl ? x9 < 0.0f : x9 > 0.0f))) {
                return true;
            }
            if (Math.abs(x9) > ((float) this.mViewDragHelper.getTouchSlop())) {
                this.mViewDragHelper.captureChildView(v, motionEvent.getPointerId(motionEvent.getActionIndex()));
            }
        }
        return !this.mIgnoreEvents;
    }

    public void removeDrawerSlideCallback() {
        this.mCallback = null;
    }

    public void resetViewInfo() {
        this.mViewRef = null;
        this.mSettleRunnable = null;
    }

    public void setDrawerSlideCallback(DrawerSlideCallback drawerSlideCallback) {
        this.mCallback = drawerSlideCallback;
    }

    public boolean setState(boolean z) {
        return setState(z ? 3 : 4);
    }

    public void setStateInternal(int i2) {
        boolean z;
        if (this.mState != i2) {
            this.mState = i2;
            WeakReference<V> weakReference = this.mViewRef;
            if (weakReference != null && ((View) weakReference.get()) != null) {
                DrawerSlideCallback drawerSlideCallback = this.mCallback;
                if (drawerSlideCallback != null) {
                    drawerSlideCallback.onStateChanged(i2);
                }
                if (i2 != 2) {
                    z = true;
                } else {
                    z = false;
                }
                this.mDraggable = z;
            }
        }
    }

    /* renamed from: settleToState */
    public void lambda$settleToStatePendingLayout$0(View view, int i2) {
        int i7;
        if (i2 == 4) {
            i7 = this.mCollapsedOffset;
        } else if (i2 == 6) {
            i7 = this.mHalfExpandedOffset;
        } else if (i2 == 3) {
            i7 = 0;
        } else {
            throw new IllegalArgumentException(C0086a.i(i2, "Illegal state argument: "));
        }
        startSettlingAnimation(view, i2, i7, false);
    }

    public void startSettlingAnimation(View view, int i2, int i7, boolean z) {
        if (this.mIsRtl) {
            i7 = (this.mParentWidth - this.mChildWidth) - i7;
        }
        ViewDragHelper viewDragHelper = this.mViewDragHelper;
        if (viewDragHelper == null || (!z ? !viewDragHelper.smoothSlideViewTo(view, i7, view.getTop()) : !viewDragHelper.settleCapturedViewAt(i7, view.getTop()))) {
            setStateInternal(i2);
            return;
        }
        setStateInternal(2);
        if (this.mSettleRunnable == null) {
            this.mSettleRunnable = new SettleRunnable(view, i2);
        }
        if (!this.mSettleRunnable.mIsPosted) {
            this.mSettleRunnable.setTargetState(i2);
            ViewCompat.postOnAnimation(view, this.mSettleRunnable);
            this.mSettleRunnable.mIsPosted = true;
            return;
        }
        this.mSettleRunnable.setTargetState(i2);
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
        final int state;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.state = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.state);
        }

        public SavedState(Parcelable parcelable, DrawerTabBehavior<?> drawerTabBehavior) {
            super(parcelable);
            this.state = drawerTabBehavior.mState;
        }
    }

    public boolean setState(int i2) {
        if (i2 == this.mState) {
            return false;
        }
        if (this.mViewRef == null) {
            if (i2 == 4 || i2 == 3 || i2 == 6) {
                this.mState = i2;
            }
            return false;
        }
        settleToStatePendingLayout(i2);
        return true;
    }

    public void onNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i2, int i7, int i8, int i10, int i11, int[] iArr) {
    }
}
