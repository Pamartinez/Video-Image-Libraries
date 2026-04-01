package com.samsung.android.gallery.widget.details;

import A.a;
import L7.p;
import a6.C0418a;
import android.content.Context;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.ViewDragHelper;
import c0.C0086a;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.behavior.SheetBehaviorCompat;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.function.IntSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DetailsBehavior extends CoordinatorLayout.Behavior<View> implements SheetBehaviorCompat {
    /* access modifiers changed from: private */
    public int mActivePointerId;
    private final Blackboard mBlackboard;
    /* access modifiers changed from: private */
    public final ArrayList<BottomSheetCallback> mCallbacks = new ArrayList<>();
    /* access modifiers changed from: private */
    public int mCollapsedOffset;
    private final ViewDragHelper.Callback mDragCallback = new ViewDragHelper.Callback() {
        public int clampViewPositionHorizontal(View view, int i2, int i7) {
            return view.getLeft();
        }

        public int clampViewPositionVertical(View view, int i2, int i7) {
            return MathUtils.clamp(i2, DetailsBehavior.this.getExpandedOffset(), DetailsBehavior.this.mParentHeight);
        }

        public int getViewVerticalDragRange(View view) {
            return DetailsBehavior.this.mParentHeight;
        }

        public void onViewDragStateChanged(int i2) {
            if (i2 == 1) {
                DetailsBehavior.this.setStateInternal(1);
            }
        }

        public void onViewPositionChanged(View view, int i2, int i7, int i8, int i10) {
            boolean z = false;
            for (int i11 = 0; i11 < DetailsBehavior.this.mCallbacks.size(); i11++) {
                ((BottomSheetCallback) DetailsBehavior.this.mCallbacks.get(i11)).onBottomSheetViewPositionChanged();
            }
            DetailsBehavior detailsBehavior = DetailsBehavior.this;
            if (i10 < 0) {
                z = true;
            }
            detailsBehavior.dispatchOnSlide(i7, z);
        }

        public void onViewReleased(View view, float f, float f5) {
            int i2;
            int i7 = 3;
            if (f5 < 0.0f) {
                i2 = DetailsBehavior.this.getExpandedOffset();
            } else if (!DetailsBehavior.this.shouldHide(view) || (view.getTop() <= DetailsBehavior.this.mCollapsedOffset && Math.abs(f) >= Math.abs(f5))) {
                if (f5 == 0.0f || Math.abs(f) > Math.abs(f5)) {
                    int top = view.getTop();
                    if (top < Math.abs(top - DetailsBehavior.this.mCollapsedOffset)) {
                        i2 = DetailsBehavior.this.getExpandedOffset();
                    } else {
                        i2 = DetailsBehavior.this.mCollapsedOffset;
                    }
                } else {
                    i2 = DetailsBehavior.this.mCollapsedOffset;
                }
                i7 = 4;
            } else {
                i2 = DetailsBehavior.this.mParentHeight;
                i7 = 5;
            }
            if (DetailsBehavior.this.mViewDragHelper == null || !DetailsBehavior.this.mViewDragHelper.settleCapturedViewAt(view.getLeft(), i2)) {
                DetailsBehavior.this.setStateInternal(i7);
                return;
            }
            DetailsBehavior.this.setStateInternal(2);
            for (int i8 = 0; i8 < DetailsBehavior.this.mCallbacks.size(); i8++) {
                ((BottomSheetCallback) DetailsBehavior.this.mCallbacks.get(i8)).onPostStateChanged(i7);
            }
            view.postOnAnimation(new SettleRunnable(view, i7));
        }

        public boolean tryCaptureView(View view, int i2) {
            View k;
            if (DetailsBehavior.this.mState == 1 || DetailsBehavior.this.mTouchingScrollingChild) {
                return false;
            }
            if ((DetailsBehavior.this.mActivePointerId != i2 || DetailsBehavior.this.mState != 3 || (k = DetailsBehavior.this.getScrollingView()) == null || (!k.canScrollVertically(1) && !k.canScrollVertically(-1))) && DetailsBehavior.this.getView() == view) {
                return true;
            }
            return false;
        }
    };
    private final float mDragTouchSlop;
    private final float mDragUpBoundSlop;
    private IntSupplier mExpandedOffsetSupplier;
    private boolean mIgnoreEvents = true;
    private int mInitialY;
    private int mInterceptedInitialX;
    private int mInterceptedInitialY;
    private int mLastNestedScrollDy;
    private boolean mNestedScrolled;
    private WeakReference<View> mNestedScrollingChildRef;
    /* access modifiers changed from: private */
    public int mParentHeight;
    /* access modifiers changed from: private */
    public int mState = 4;
    private PointF mTouchDownPoint;
    private boolean mTouchInNavigationArea;
    private IDetailsTouchListener mTouchListener;
    private final float mTouchSlop;
    /* access modifiers changed from: private */
    public boolean mTouchingScrollingChild;
    private boolean mTryUnlock = false;
    /* access modifiers changed from: private */
    public DetailsDragHelper mViewDragHelper;
    private WeakReference<View> mViewRef;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class SettleRunnable implements Runnable {
        private final int targetState;
        private final View view;

        public SettleRunnable(View view2, int i2) {
            this.view = view2;
            this.targetState = i2;
        }

        public void run() {
            if (DetailsBehavior.this.mViewDragHelper != null && DetailsBehavior.this.mViewDragHelper.continueSettling(true)) {
                this.view.postOnAnimation(this);
            } else if (DetailsBehavior.this.mState != 1) {
                DetailsBehavior.this.setStateInternal(this.targetState);
            }
        }
    }

    public DetailsBehavior(Context context) {
        this.mBlackboard = Blackboard.getInstance(context.toString());
        this.mTouchSlop = (float) ViewConfiguration.get(context).getScaledPagingTouchSlop();
        float scaledPagingTouchSlop = (float) ViewConfiguration.get(context).getScaledPagingTouchSlop();
        this.mDragTouchSlop = scaledPagingTouchSlop;
        this.mDragUpBoundSlop = scaledPagingTouchSlop / 3.0f;
    }

    private void calculateCollapsedOffset() {
        this.mCollapsedOffset = this.mParentHeight;
    }

    private void calculateOffsetAndTranslate(View view, int i2) {
        calculateCollapsedOffset();
        int i7 = this.mState;
        if (i7 == 1 || i7 == 2) {
            view.offsetTopAndBottom(Math.max(getExpandedOffset(), i2 - view.getTop()));
        } else if (i7 == 3) {
            view.offsetTopAndBottom(getExpandedOffset());
        } else if (i7 == 4) {
            view.offsetTopAndBottom(this.mCollapsedOffset);
        } else if (i7 == 5) {
            view.offsetTopAndBottom(this.mParentHeight);
        }
    }

    private void calculatePeekHeight(CoordinatorLayout coordinatorLayout) {
        this.mParentHeight = coordinatorLayout.getHeight();
    }

    /* access modifiers changed from: private */
    public void dispatchOnSlide(int i2, boolean z) {
        float f;
        View view = getView();
        if (view != null && !this.mCallbacks.isEmpty()) {
            int i7 = this.mCollapsedOffset;
            if (i2 > i7) {
                f = (float) (this.mParentHeight - i7);
            } else {
                f = (float) (i7 - getExpandedOffset());
            }
            float f5 = 0.0f;
            if (f != 0.0f) {
                f5 = ((float) (this.mCollapsedOffset - i2)) / f;
            }
            for (int i8 = 0; i8 < this.mCallbacks.size(); i8++) {
                this.mCallbacks.get(i8).onSlide(view, Math.min(1.0f, f5), z);
            }
        }
    }

    private View findScrollingChild(View view) {
        if (ViewCompat.isNestedScrollingEnabled(view)) {
            return view;
        }
        if (view instanceof ViewGroup) {
            return ((ViewGroup) view).findViewById(R$id.details_scrollview);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public View getScrollingView() {
        WeakReference<View> weakReference = this.mNestedScrollingChildRef;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    private int getStateOffset(int i2) {
        if (i2 == 4) {
            return this.mCollapsedOffset;
        }
        if (i2 == 3) {
            return getExpandedOffset();
        }
        return 0;
    }

    /* access modifiers changed from: private */
    public View getView() {
        WeakReference<View> weakReference = this.mViewRef;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    private void handleDragExit(MotionEvent motionEvent) {
        IDetailsTouchListener iDetailsTouchListener;
        if (this.mTouchDownPoint != null && motionEvent.getPointerCount() <= 1) {
            float abs = Math.abs(motionEvent.getX() - this.mTouchDownPoint.x);
            float abs2 = Math.abs(motionEvent.getY() - this.mTouchDownPoint.y);
            float y = motionEvent.getY();
            float f = this.mTouchDownPoint.y;
            if (y - f > 0.0f) {
                if (f > ((float) DeviceInfo.getStatusBarHeight()) && abs2 > this.mDragTouchSlop && abs2 / abs > 1.4f && (iDetailsTouchListener = this.mTouchListener) != null) {
                    iDetailsTouchListener.onDetectedDragExit();
                }
            } else if (f - motionEvent.getY() > this.mDragUpBoundSlop) {
                this.mTouchDownPoint = null;
            }
        }
    }

    private boolean isCollapsed() {
        int state = getState();
        if (state == 5 || state == 4) {
            return true;
        }
        return false;
    }

    private boolean isLocked(IDetailsTouchListener iDetailsTouchListener) {
        if (iDetailsTouchListener == null || !iDetailsTouchListener.isLocked()) {
            return false;
        }
        return true;
    }

    private boolean isMovable(IDetailsTouchListener iDetailsTouchListener) {
        if (iDetailsTouchListener == null || !iDetailsTouchListener.isMovable()) {
            return false;
        }
        return true;
    }

    private boolean isMovableOnDetails(IDetailsTouchListener iDetailsTouchListener) {
        if (iDetailsTouchListener == null || !iDetailsTouchListener.isMovableOnDetails()) {
            return false;
        }
        return true;
    }

    private boolean isPointInValidBounds(CoordinatorLayout coordinatorLayout, View view, int i2, int i7) {
        try {
            if (coordinatorLayout.isPointInChildBounds(view, i2, i7) || coordinatorLayout.isPointInChildBounds(coordinatorLayout, i2, i7)) {
                return true;
            }
            return false;
        } catch (NullPointerException e) {
            Log.e("DetailsBehavior", "Fail to check touch position: " + e.getMessage());
            return false;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onDismissedKeyguard$0() {
        show(true);
    }

    private boolean onDetailsPageTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        IDetailsTouchListener iDetailsTouchListener = this.mTouchListener;
        if (iDetailsTouchListener == null || iDetailsTouchListener.isMovableOnDetails()) {
            return onInterceptTouchEventInternal(coordinatorLayout, view, motionEvent);
        }
        return false;
    }

    private boolean onInterceptTouchEventInternal(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        DetailsDragHelper detailsDragHelper;
        boolean z = true;
        if (!view.isShown()) {
            this.mIgnoreEvents = true;
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        this.mActivePointerId = -1;
        if (actionMasked == 0) {
            this.mInterceptedInitialX = (int) motionEvent.getX();
            this.mInterceptedInitialY = (int) motionEvent.getY();
            int navigationBarTopInPixel = DeviceInfo.getNavigationBarTopInPixel(coordinatorLayout.getContext());
            if (navigationBarTopInPixel <= 0 || this.mInterceptedInitialY <= navigationBarTopInPixel) {
                int i2 = this.mState;
                if ((i2 == 1 || i2 == 2) && (coordinatorLayout.getParent() instanceof ViewGroup)) {
                    Log.w("DetailsBehavior", "set parent disallowInterceptTouchEvent true");
                    coordinatorLayout.getParent().requestDisallowInterceptTouchEvent(true);
                }
                View scrollingView = getScrollingView();
                if (!(scrollingView == null || !coordinatorLayout.isPointInChildBounds(scrollingView, this.mInterceptedInitialX, this.mInterceptedInitialY) || this.mState == 2)) {
                    this.mTouchingScrollingChild = true;
                }
                boolean isPointInValidBounds = isPointInValidBounds(coordinatorLayout, view, this.mInterceptedInitialX, this.mInterceptedInitialY);
                if (isPointInValidBounds) {
                    this.mActivePointerId = motionEvent.getPointerId(motionEvent.getActionIndex());
                }
                DetailsDragHelper detailsDragHelper2 = this.mViewDragHelper;
                if (detailsDragHelper2 != null) {
                    detailsDragHelper2.processTouchEvent(motionEvent);
                }
                if (this.mActivePointerId != -1 || isPointInValidBounds) {
                    z = false;
                }
                this.mIgnoreEvents = z;
            } else {
                this.mTouchInNavigationArea = true;
                return false;
            }
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.mTouchInNavigationArea = false;
            this.mTouchingScrollingChild = false;
            if (actionMasked == 3 && (detailsDragHelper = this.mViewDragHelper) != null) {
                detailsDragHelper.processTouchEvent(motionEvent);
            }
            if (this.mIgnoreEvents) {
                this.mIgnoreEvents = false;
                return false;
            }
        }
        if (!this.mTouchInNavigationArea) {
            return shouldInterceptDragTouchEvent(coordinatorLayout, motionEvent, actionMasked);
        }
        Log.d("DetailsBehavior", "Touch is not intercepted : Navigation bar area");
        return false;
    }

    private boolean onStartNestedScrollInternal(int i2) {
        this.mLastNestedScrollDy = 0;
        this.mNestedScrolled = false;
        if ((i2 & 2) != 0) {
            return true;
        }
        return false;
    }

    private boolean onTouchEventInternal(View view, MotionEvent motionEvent) {
        DetailsDragHelper detailsDragHelper;
        if (!view.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (this.mState == 1 && actionMasked == 0) {
            return true;
        }
        DetailsDragHelper detailsDragHelper2 = this.mViewDragHelper;
        if (!(detailsDragHelper2 == null || actionMasked == 3)) {
            try {
                detailsDragHelper2.processTouchEvent(motionEvent);
            } catch (Exception e) {
                a.s(e, new StringBuilder("mViewDragHelper.processTouchEvent fail : "), "DetailsBehavior");
                return false;
            }
        }
        this.mActivePointerId = -1;
        float abs = Math.abs(((float) this.mInterceptedInitialX) - motionEvent.getX());
        float abs2 = Math.abs(((float) this.mInterceptedInitialY) - motionEvent.getY());
        if (actionMasked == 2 && !this.mIgnoreEvents && (detailsDragHelper = this.mViewDragHelper) != null && abs2 > this.mTouchSlop && abs2 * 1.0f > abs) {
            detailsDragHelper.captureChildView(view, motionEvent.getPointerId(motionEvent.getActionIndex()));
        }
        if (actionMasked == 1 && (!ResourceCompat.isLandscape(getView()) ? !(abs2 <= this.mTouchSlop || abs2 * 1.0f <= abs) : abs2 * 1.0f > abs)) {
            this.mTouchListener.onTouchSlideUpToExpand();
        }
        return !this.mIgnoreEvents;
    }

    private boolean onViewerPageTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        boolean z;
        boolean z3;
        boolean isLocked = isLocked(this.mTouchListener);
        if (!onInterceptTouchEventInternal(coordinatorLayout, view, motionEvent) || !isMovable(this.mTouchListener)) {
            z = false;
        } else {
            z = true;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.mTryUnlock = true;
            this.mInitialY = (int) motionEvent.getY();
            if (this.mTouchListener.isSupportedDragExit()) {
                this.mTouchDownPoint = new PointF(motionEvent.getX(), motionEvent.getY());
            }
        } else if (actionMasked == 1) {
            this.mTryUnlock = false;
            this.mTouchDownPoint = null;
        } else if (actionMasked == 2) {
            if (!isLocked || !this.mTryUnlock || motionEvent.getY() - ((float) this.mInitialY) >= 0.0f) {
                z3 = false;
            } else {
                z3 = true;
            }
            if (z && z3) {
                showRequestDismissKeyGuard();
                this.mTryUnlock = false;
            }
            handleDragExit(motionEvent);
        } else if (actionMasked == 3) {
            this.mTouchDownPoint = null;
        }
        if (isLocked || !z) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void setStateInternal(int i2) {
        if (this.mState != i2) {
            this.mState = i2;
            View view = getView();
            if (view != null && !this.mCallbacks.isEmpty()) {
                for (int i7 = 0; i7 < this.mCallbacks.size(); i7++) {
                    this.mCallbacks.get(i7).onStateChanged(view, i2);
                }
            }
        }
    }

    private boolean shouldBlockBehaviorTouchIntercept() {
        IDetailsTouchListener iDetailsTouchListener = this.mTouchListener;
        if (iDetailsTouchListener == null || !iDetailsTouchListener.shouldBlockBehaviorTouchIntercept()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public boolean shouldHide(View view) {
        if (view.getTop() >= this.mCollapsedOffset) {
            return true;
        }
        return false;
    }

    private boolean shouldInterceptDragTouchEvent(CoordinatorLayout coordinatorLayout, MotionEvent motionEvent, int i2) {
        DetailsDragHelper detailsDragHelper;
        if (!this.mIgnoreEvents && (detailsDragHelper = this.mViewDragHelper) != null && detailsDragHelper.shouldInterceptTouchEvent(motionEvent)) {
            return true;
        }
        View scrollingView = getScrollingView();
        float abs = Math.abs(((float) this.mInterceptedInitialX) - motionEvent.getX());
        float abs2 = Math.abs(((float) this.mInterceptedInitialY) - motionEvent.getY());
        if (i2 != 2 || scrollingView == null || this.mIgnoreEvents || this.mState == 1 || coordinatorLayout.isPointInChildBounds(scrollingView, (int) motionEvent.getX(), (int) motionEvent.getY()) || this.mViewDragHelper == null || abs2 <= this.mTouchSlop || abs2 * 1.0f <= abs) {
            return false;
        }
        return true;
    }

    private void showRequestDismissKeyGuard() {
        IDetailsTouchListener iDetailsTouchListener = this.mTouchListener;
        if (iDetailsTouchListener != null) {
            iDetailsTouchListener.showRequestDismissKeyGuard();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: startSettlingAnimation */
    public void lambda$setState$1(View view, int i2) {
        int i7;
        if (i2 == 4) {
            i7 = this.mCollapsedOffset;
        } else if (i2 == 3) {
            i7 = getExpandedOffset();
        } else if (i2 == 5) {
            i7 = this.mParentHeight;
        } else {
            throw new IllegalArgumentException(C0086a.i(i2, "Illegal mState argument: "));
        }
        DetailsDragHelper detailsDragHelper = this.mViewDragHelper;
        if (detailsDragHelper == null || !detailsDragHelper.smoothSlideViewTo(view, view.getLeft(), i7)) {
            setStateInternal(i2);
            return;
        }
        setStateInternal(2);
        for (int i8 = 0; i8 < this.mCallbacks.size(); i8++) {
            this.mCallbacks.get(i8).onPostStateChanged(i2);
        }
        view.postOnAnimation(new SettleRunnable(view, i2));
    }

    private void stopScroll() {
        if (getScrollingView() != null) {
            NestedScrollView nestedScrollView = (NestedScrollView) getScrollingView();
            nestedScrollView.smoothScrollBy(0, 0);
            nestedScrollView.setScrollY(0);
        }
    }

    public void addBottomSheetCallback(BottomSheetCallback bottomSheetCallback) {
        if (!this.mCallbacks.contains(bottomSheetCallback)) {
            this.mCallbacks.add(bottomSheetCallback);
        }
    }

    public int getExpandedOffset() {
        IntSupplier intSupplier = this.mExpandedOffsetSupplier;
        if (intSupplier != null) {
            return intSupplier.getAsInt();
        }
        return 0;
    }

    public final int getState() {
        return this.mState;
    }

    public boolean hasViewRef() {
        if (this.mViewRef != null) {
            return true;
        }
        return false;
    }

    public void hide() {
        stopScroll();
        setState(4);
    }

    public boolean isExpanded() {
        if (getState() == 3) {
            return true;
        }
        return false;
    }

    public void onDismissedKeyguard() {
        ThreadUtil.postOnUiThreadDelayed(new p(this, 1), 100);
    }

    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        if (shouldBlockBehaviorTouchIntercept()) {
            return false;
        }
        if (isCollapsed()) {
            return onViewerPageTouchEvent(coordinatorLayout, view, motionEvent);
        }
        if (isExpanded()) {
            return onDetailsPageTouchEvent(coordinatorLayout, view, motionEvent);
        }
        return onInterceptTouchEventInternal(coordinatorLayout, view, motionEvent);
    }

    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i2) {
        if (ViewCompat.getFitsSystemWindows(coordinatorLayout) && !ViewCompat.getFitsSystemWindows(view)) {
            view.setFitsSystemWindows(true);
        }
        int top = view.getTop();
        coordinatorLayout.onLayoutChild(view, i2);
        calculatePeekHeight(coordinatorLayout);
        calculateOffsetAndTranslate(view, top);
        if (this.mViewDragHelper == null) {
            this.mViewDragHelper = DetailsDragHelper.create(coordinatorLayout, this.mDragCallback);
        }
        this.mViewRef = new WeakReference<>(view);
        this.mNestedScrollingChildRef = new WeakReference<>(findScrollingChild(view));
        for (int i7 = 0; i7 < this.mCallbacks.size(); i7++) {
            this.mCallbacks.get(i7).onChildLayoutChanged();
        }
        return true;
    }

    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View view, View view2, float f, float f5) {
        if (view2 != getScrollingView()) {
            return false;
        }
        if (this.mState != 3 || super.onNestedPreFling(coordinatorLayout, view, view2, f, f5)) {
            return true;
        }
        return false;
    }

    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i2, int i7, int[] iArr, int i8) {
        boolean z;
        if (i8 == 1) {
            ViewCompat.stopNestedScroll(view2, 1);
        } else if (view2 == getScrollingView()) {
            int top = view.getTop();
            int i10 = top - i7;
            if (i7 > 0) {
                if (i10 < getExpandedOffset()) {
                    int expandedOffset = top - getExpandedOffset();
                    iArr[1] = expandedOffset;
                    view.offsetTopAndBottom(-expandedOffset);
                    setStateInternal(3);
                } else {
                    iArr[1] = i7;
                    view.offsetTopAndBottom(-i7);
                    setStateInternal(1);
                }
            } else if (i7 < 0 && !view2.canScrollVertically(-1)) {
                iArr[1] = i7;
                view.offsetTopAndBottom(-i7);
                setStateInternal(1);
            }
            if (iArr[1] != 0) {
                if (i7 > 0) {
                    z = true;
                } else {
                    z = false;
                }
                dispatchOnSlide(top, z);
                this.mLastNestedScrollDy = i7;
            }
            this.mNestedScrolled = true;
        }
    }

    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i2, int i7, int i8, int i10, int i11, int[] iArr) {
        boolean z;
        super.onNestedScroll(coordinatorLayout, view, view2, i2, i7, i8, i10, i11, iArr);
        if (this.mState != 3 || i7 == 0) {
            if (i11 == 1) {
                ViewCompat.stopNestedScroll(view2, 1);
            }
        } else if (view2 == getScrollingView()) {
            int top = view.getTop();
            if (i7 > 0) {
                z = true;
            } else {
                z = false;
            }
            dispatchOnSlide(top, z);
            this.mLastNestedScrollDy = i7;
            this.mNestedScrolled = true;
        }
    }

    public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, View view, Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        if (savedState.getSuperState() != null) {
            super.onRestoreInstanceState(coordinatorLayout, view, savedState.getSuperState());
        }
        int i2 = savedState.state;
        if (i2 == 1 || i2 == 2) {
            this.mState = 4;
        } else {
            this.mState = i2;
        }
    }

    public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, View view) {
        return new SavedState(super.onSaveInstanceState(coordinatorLayout, view), this.mState);
    }

    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i2, int i7) {
        if ((!isExpanded() || isMovableOnDetails(this.mTouchListener)) && onStartNestedScrollInternal(i2)) {
            return true;
        }
        return false;
    }

    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i2) {
        int i7;
        if (view.getTop() != getExpandedOffset() && view2 == getScrollingView() && this.mNestedScrolled) {
            int i8 = 3;
            if (this.mLastNestedScrollDy > 0) {
                i7 = getExpandedOffset();
            } else if (shouldHide(view)) {
                i7 = this.mParentHeight;
                i8 = 5;
            } else {
                if (this.mLastNestedScrollDy == 0) {
                    int top = view.getTop();
                    if (top < Math.abs(top - this.mCollapsedOffset)) {
                        i7 = getExpandedOffset();
                    } else {
                        i7 = this.mCollapsedOffset;
                    }
                } else {
                    i7 = this.mCollapsedOffset;
                }
                i8 = 4;
            }
            DetailsDragHelper detailsDragHelper = this.mViewDragHelper;
            if (detailsDragHelper == null || !detailsDragHelper.smoothSlideViewTo(view, view.getLeft(), i7)) {
                setStateInternal(i8);
            } else {
                setStateInternal(2);
                for (int i10 = 0; i10 < this.mCallbacks.size(); i10++) {
                    this.mCallbacks.get(i10).onPostStateChanged(i8);
                }
                view.postOnAnimation(new SettleRunnable(view, i8));
            }
            this.mNestedScrolled = false;
        }
    }

    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        if (!isMovableOnDetails(this.mTouchListener) || !onTouchEventInternal(view, motionEvent)) {
            return false;
        }
        return true;
    }

    public void onViewRecycled() {
        this.mState = 4;
    }

    public void setExpandedOffsetSupplier(IntSupplier intSupplier) {
        this.mExpandedOffsetSupplier = intSupplier;
    }

    public final void setState(int i2) {
        ViewParent viewParent;
        if (i2 != this.mState) {
            if (this.mViewRef != null) {
                View view = getView();
                if (view != null) {
                    viewParent = view.getParent();
                } else {
                    viewParent = null;
                }
                if (viewParent != null && viewParent.isLayoutRequested() && view.isAttachedToWindow()) {
                    view.post(new C0418a((Object) this, (Object) view, i2, 14));
                } else if (view != null) {
                    lambda$setState$1(view, i2);
                }
            } else if (i2 == 4 || i2 == 3 || i2 == 5) {
                this.mState = i2;
            }
        }
    }

    public void setStateNoAnim(int i2) {
        if (getView() != null) {
            stopScroll();
            dispatchOnSlide(getStateOffset(i2), false);
            setStateInternal(i2);
        }
    }

    public void setTouchListener(IDetailsTouchListener iDetailsTouchListener) {
        this.mTouchListener = iDetailsTouchListener;
    }

    public void show(boolean z) {
        if (isLocked(this.mTouchListener)) {
            this.mTouchListener.showRequestDismissKeyGuard();
        } else if (!z) {
            setStateNoAnim(3);
        } else if (!isLocked(this.mTouchListener)) {
            setState(3);
        }
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

        public SavedState(Parcelable parcelable, int i2) {
            super(parcelable);
            this.state = i2;
        }
    }

    public void hide(boolean z) {
        if (z) {
            stopScroll();
            setState(4);
            return;
        }
        setStateNoAnim(4);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface BottomSheetCallback {
        void onBottomSheetViewPositionChanged() {
        }

        void onChildLayoutChanged() {
        }

        void onPostStateChanged(int i2) {
        }

        void onStateChanged(View view, int i2) {
        }

        void onSlide(View view, float f, boolean z) {
        }
    }
}
