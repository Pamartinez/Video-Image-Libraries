package com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.behavior;

import B2.h;
import a6.C0418a;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.customview.widget.ViewDragHelper;
import c0.C0086a;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.R$styleable;
import com.samsung.android.gallery.widget.behavior.SheetBehaviorCompat;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import h.C0199b;
import h2.t;
import h2.u;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;
import l6.a;
import x2.C0340g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryHighlightBehavior<V extends View> extends CoordinatorLayout.Behavior<V> implements SheetBehaviorCompat {
    int activePointerId;
    private final ArrayList<BottomSheetCallback> callbacks = new ArrayList<>();
    private int childHeight;
    boolean draggable = true;
    float elevation = -1.0f;
    int expandedOffset;
    int expandedOffsetDelta;
    boolean fitToContents;
    int fitToContentsOffset;
    int halfExpandedOffset;
    float halfExpandedRatio = 0.8f;
    private boolean ignoreEvents;
    private int initialX;
    private int initialY;
    private ValueAnimator interpolatorAnimator;
    private boolean isShapeExpanded;
    private int lastNestedScrollDy;
    private StoryHighlightBehaviorAccessibility mAccessibilityDelegate = new StoryHighlightBehaviorAccessibility(this);
    private int mInsetTop;
    private BooleanSupplier mTouchInterceptSupplier;
    private C0340g materialShapeDrawable;
    private int maxWidth = -1;
    private boolean nestedScrolled;
    WeakReference<View> nestedScrollingChildRef;
    int parentHeight;
    int parentWidth;
    int peekHeight;
    private boolean peekHeightAuto;
    private int saveFlags = 0;
    private SettleRunnable settleRunnable = null;
    private final boolean shapeThemingEnabled;
    int state = 5;
    boolean touchingScrollingChild;
    private VelocityTracker velocityTracker;
    ViewDragHelper viewDragHelper;
    WeakReference<V> viewRef;

    public StoryHighlightBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        int i2;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.BottomSheetBehavior_Layout);
        this.shapeThemingEnabled = obtainStyledAttributes.hasValue(21);
        createShapeValueAnimator();
        TypedValue peekValue = obtainStyledAttributes.peekValue(9);
        if (peekValue == null || (i2 = peekValue.data) != -1) {
            setPeekHeight(obtainStyledAttributes.getDimensionPixelSize(9, -1));
        } else {
            setPeekHeight(i2);
        }
        setFitToContents(obtainStyledAttributes.getBoolean(6, true));
        setDraggable(obtainStyledAttributes.getBoolean(4, true));
        setSaveFlags(obtainStyledAttributes.getInt(10, 0));
        setHalfExpandedRatio(obtainStyledAttributes.getFloat(7, 0.8f));
        obtainStyledAttributes.recycle();
    }

    private boolean allowDegree(float f, float f5) {
        if ((((float) this.initialY) - f5) / Math.abs(((float) this.initialX) - f) > 2.0f) {
            return true;
        }
        return false;
    }

    private void calculateHalfExpandedOffset() {
        this.halfExpandedOffset = getExpandedOffset() + ((int) ((1.0f - this.halfExpandedRatio) * ((float) this.parentHeight)));
    }

    private boolean canTouchIntercept() {
        BooleanSupplier booleanSupplier = this.mTouchInterceptSupplier;
        if (booleanSupplier == null || booleanSupplier.getAsBoolean()) {
            return true;
        }
        return false;
    }

    private void cancelSettling() {
        SettleRunnable settleRunnable2 = this.settleRunnable;
        if (settleRunnable2 != null) {
            settleRunnable2.cancel();
            this.settleRunnable = null;
        }
    }

    private void createShapeValueAnimator() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.interpolatorAnimator = ofFloat;
        ofFloat.setDuration(500);
        this.interpolatorAnimator.addUpdateListener(new h(16, this));
    }

    public static <V extends View> StoryHighlightBehavior<V> from(V v) {
        ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
            CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior();
            if (behavior instanceof StoryHighlightBehavior) {
                return (StoryHighlightBehavior) behavior;
            }
            throw new IllegalArgumentException("The view is not associated with StoryHighlightBehavior");
        }
        throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
    }

    private boolean isDexMode(View view) {
        return DeviceInfo.isDexMode(view.getContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createShapeValueAnimator$2(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        C0340g gVar = this.materialShapeDrawable;
        if (gVar != null) {
            gVar.l(floatValue);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setWindowInsetsListener$3(View view) {
        if (SettleRunnable.isExpanding(this, this.settleRunnable)) {
            Log.d("StoryHighlightBehavior", "inset is changed, cancel settling", Integer.valueOf(this.state));
            cancelSettling();
            lambda$settleToStatePendingLayout$1(view, 3);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ WindowInsetsCompat lambda$setWindowInsetsListener$4(View view, View view2, WindowInsetsCompat windowInsetsCompat, t tVar) {
        int i2;
        if (isDexMode(view)) {
            i2 = 0;
        } else {
            i2 = WindowUtils.getSystemInsets(view2.getRootWindowInsets()).top;
        }
        this.mInsetTop = i2;
        view.post(new C0199b(10, this, view));
        return windowInsetsCompat;
    }

    private void reset() {
        this.activePointerId = -1;
        VelocityTracker velocityTracker2 = this.velocityTracker;
        if (velocityTracker2 != null) {
            velocityTracker2.recycle();
            this.velocityTracker = null;
        }
    }

    private void resetWindowInset() {
        View view;
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference != null) {
            view = (View) weakReference.get();
        } else {
            view = null;
        }
        if (view != null) {
            this.mInsetTop = 0;
        }
    }

    private void restoreOptionalState(SavedState savedState) {
        int i2 = this.saveFlags;
        if (i2 != 0) {
            if (i2 == -1 || (i2 & 1) == 1) {
                this.peekHeight = savedState.peekHeight;
            }
            if (i2 == -1 || (i2 & 2) == 2) {
                this.fitToContents = savedState.fitToContents;
            }
        }
    }

    private void setWindowInsetsListener(View view) {
        u.a(view, new a(0, this, view));
    }

    private void settleToStatePendingLayout(int i2) {
        View view = (View) this.viewRef.get();
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent == null || !parent.isLayoutRequested() || !ViewCompat.isAttachedToWindow(view)) {
                lambda$settleToStatePendingLayout$1(view, i2);
            } else {
                view.post(new C0418a((Object) this, (Object) view, i2, 11));
            }
        }
    }

    private void updateDrawableForTargetState(int i2) {
        boolean z;
        ValueAnimator valueAnimator;
        float f;
        if (i2 != 2) {
            if (i2 == 3) {
                z = true;
            } else {
                z = false;
            }
            if (this.isShapeExpanded != z) {
                this.isShapeExpanded = z;
                if (this.materialShapeDrawable != null && (valueAnimator = this.interpolatorAnimator) != null) {
                    if (valueAnimator.isRunning()) {
                        this.interpolatorAnimator.reverse();
                        return;
                    }
                    if (z) {
                        f = 0.0f;
                    } else {
                        f = 1.0f;
                    }
                    this.interpolatorAnimator.setFloatValues(new float[]{1.0f - f, f});
                    this.interpolatorAnimator.start();
                }
            }
        }
    }

    public void addBottomSheetCallback(BottomSheetCallback bottomSheetCallback) {
        if (!this.callbacks.contains(bottomSheetCallback)) {
            this.callbacks.add(bottomSheetCallback);
        }
    }

    public void dispatchOnSlide(int i2) {
        View view;
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference != null) {
            view = (View) weakReference.get();
        } else {
            view = null;
        }
        if (view != null && !this.callbacks.isEmpty()) {
            float expandedOffset2 = 1.0f - (((float) (i2 - getExpandedOffset())) / ((float) (this.parentHeight - getExpandedOffset())));
            for (int i7 = 0; i7 < this.callbacks.size(); i7++) {
                this.callbacks.get(i7).onSlide(view, expandedOffset2);
            }
        }
    }

    public View findScrollingChild(View view) {
        return this.mAccessibilityDelegate.findScrollingChild(view);
    }

    public int getExpandedOffset() {
        if (this.fitToContents) {
            return this.fitToContentsOffset;
        }
        return Math.max(this.expandedOffset, this.mInsetTop) + this.expandedOffsetDelta;
    }

    public int getState() {
        return this.state;
    }

    public boolean isFitToContents() {
        return this.fitToContents;
    }

    public void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams layoutParams) {
        super.onAttachedToLayoutParams(layoutParams);
        this.viewRef = null;
        this.viewDragHelper = null;
    }

    public void onDetachedFromLayoutParams() {
        super.onDetachedFromLayoutParams();
        this.viewRef = null;
        this.viewDragHelper = null;
    }

    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        ViewDragHelper viewDragHelper2;
        boolean z;
        View view;
        if (!v.isShown() || !this.draggable || !canTouchIntercept()) {
            this.ignoreEvents = true;
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            reset();
        }
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(motionEvent);
        View view2 = null;
        if (actionMasked == 0) {
            int x9 = (int) motionEvent.getX();
            this.initialY = (int) motionEvent.getY();
            this.initialX = x9;
            if (this.state != 2) {
                WeakReference<View> weakReference = this.nestedScrollingChildRef;
                if (weakReference != null) {
                    view = weakReference.get();
                } else {
                    view = null;
                }
                if (view != null && coordinatorLayout.isPointInChildBounds(view, x9, this.initialY)) {
                    this.activePointerId = motionEvent.getPointerId(motionEvent.getActionIndex());
                    this.touchingScrollingChild = true;
                }
            }
            if (this.initialY > DeviceInfo.getNavigationBarTopInPixel(coordinatorLayout.getContext())) {
                z = true;
            } else {
                z = false;
            }
            this.ignoreEvents = z;
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.touchingScrollingChild = false;
            this.activePointerId = -1;
            if (this.ignoreEvents) {
                this.ignoreEvents = false;
                return false;
            }
        }
        if (!this.ignoreEvents && (viewDragHelper2 = this.viewDragHelper) != null && viewDragHelper2.shouldInterceptTouchEvent(motionEvent)) {
            return true;
        }
        WeakReference<View> weakReference2 = this.nestedScrollingChildRef;
        if (weakReference2 != null) {
            view2 = weakReference2.get();
        }
        if (actionMasked != 2 || this.ignoreEvents || this.state == 1 || (((view2 == null || coordinatorLayout.isPointInChildBounds(view2, (int) motionEvent.getX(), (int) motionEvent.getY())) && ((ViewGroup) v).getChildCount() != 0) || this.viewDragHelper == null || Math.abs(((float) this.initialY) - motionEvent.getY()) <= ((float) this.viewDragHelper.getTouchSlop()) || !allowDegree(motionEvent.getX(), motionEvent.getY()))) {
            return false;
        }
        return true;
    }

    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, V v, int i2) {
        boolean z;
        float f;
        C0340g gVar;
        if (ViewCompat.getFitsSystemWindows(coordinatorLayout) && !ViewCompat.getFitsSystemWindows(v)) {
            v.setFitsSystemWindows(true);
        }
        if (this.viewRef == null) {
            setWindowInsetsListener(v);
            this.viewRef = new WeakReference<>(v);
            if (this.shapeThemingEnabled && (gVar = this.materialShapeDrawable) != null) {
                ViewCompat.setBackground(v, gVar);
            }
            C0340g gVar2 = this.materialShapeDrawable;
            if (gVar2 != null) {
                float f5 = this.elevation;
                if (f5 == -1.0f) {
                    f5 = ViewCompat.getElevation(v);
                }
                gVar2.j(f5);
                if (this.state == 3) {
                    z = true;
                } else {
                    z = false;
                }
                this.isShapeExpanded = z;
                C0340g gVar3 = this.materialShapeDrawable;
                if (z) {
                    f = 0.0f;
                } else {
                    f = 1.0f;
                }
                gVar3.l(f);
            }
            this.mAccessibilityDelegate.updateAccessibilityActions(v);
            if (ViewCompat.getImportantForAccessibility(v) == 0) {
                ViewCompat.setImportantForAccessibility(v, 1);
            }
            int measuredWidth = v.getMeasuredWidth();
            int i7 = this.maxWidth;
            if (measuredWidth > i7 && i7 != -1) {
                ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
                layoutParams.width = this.maxWidth;
                v.post(new C0199b(9, v, layoutParams));
            }
        }
        if (this.viewDragHelper == null) {
            this.viewDragHelper = ViewDragHelper.create(coordinatorLayout, new StoryHighlightBottomSheetDragCallback(this));
        }
        int top = v.getTop();
        coordinatorLayout.onLayoutChild(v, i2);
        this.parentWidth = coordinatorLayout.getWidth();
        this.parentHeight = coordinatorLayout.getHeight();
        int height = v.getHeight();
        this.childHeight = height;
        int i8 = this.parentHeight;
        int i10 = i8 - height;
        int i11 = this.mInsetTop;
        if (i10 < i11) {
            this.childHeight = i8 - i11;
        }
        this.fitToContentsOffset = Math.max(0, i8 - this.childHeight) + this.expandedOffsetDelta;
        calculateHalfExpandedOffset();
        int i12 = this.state;
        if (i12 == 3) {
            ViewCompat.offsetTopAndBottom(v, getExpandedOffset());
        } else if (i12 == 6) {
            ViewCompat.offsetTopAndBottom(v, this.halfExpandedOffset);
        } else if (i12 == 5) {
            ViewCompat.offsetTopAndBottom(v, this.parentHeight);
        } else if (i12 == 1 || i12 == 2) {
            ViewCompat.offsetTopAndBottom(v, top - v.getTop());
        }
        this.nestedScrollingChildRef = new WeakReference<>(findScrollingChild(v));
        return true;
    }

    public void onMultiWindowModeChanged(boolean z) {
        if (SdkConfig.atLeast(SdkConfig.SEM.U) && z) {
            resetWindowInset();
        }
    }

    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, V v, View view, float f, float f5) {
        WeakReference<View> weakReference = this.nestedScrollingChildRef;
        if (weakReference == null || view != weakReference.get() || (this.state == 3 && !super.onNestedPreFling(coordinatorLayout, v, view, f, f5))) {
            return false;
        }
        return true;
    }

    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i2, int i7, int[] iArr, int i8) {
        View view2;
        if (i8 != 1) {
            WeakReference<View> weakReference = this.nestedScrollingChildRef;
            if (weakReference != null) {
                view2 = weakReference.get();
            } else {
                view2 = null;
            }
            if (view == view2) {
                int top = v.getTop();
                int i10 = top - i7;
                if (i7 > 0) {
                    if (i10 < getExpandedOffset()) {
                        int expandedOffset2 = top - getExpandedOffset();
                        iArr[1] = expandedOffset2;
                        ViewCompat.offsetTopAndBottom(v, -expandedOffset2);
                        setStateInternal(3);
                    } else if (this.draggable) {
                        iArr[1] = i7;
                        ViewCompat.offsetTopAndBottom(v, -i7);
                        setStateInternal(1);
                    } else {
                        return;
                    }
                } else if (i7 < 0 && !view.canScrollVertically(-1)) {
                    if (this.draggable) {
                        iArr[1] = i7;
                        ViewCompat.offsetTopAndBottom(v, -i7);
                        setStateInternal(1);
                    } else {
                        return;
                    }
                }
                dispatchOnSlide(v.getTop());
                this.lastNestedScrollDy = i7;
                this.nestedScrolled = true;
            }
        }
    }

    public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, V v, Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        if (savedState.getSuperState() != null) {
            super.onRestoreInstanceState(coordinatorLayout, v, savedState.getSuperState());
        }
        restoreOptionalState(savedState);
        int i2 = savedState.state;
        if (i2 == 1 || i2 == 2) {
            this.state = 5;
        } else {
            this.state = i2;
        }
    }

    public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, V v) {
        return new SavedState(super.onSaveInstanceState(coordinatorLayout, v), (StoryHighlightBehavior<?>) this);
    }

    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i2, int i7) {
        this.lastNestedScrollDy = 0;
        this.nestedScrolled = false;
        if ((i2 & 2) != 0) {
            return true;
        }
        return false;
    }

    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i2) {
        int i7;
        int i8 = 3;
        if (v.getTop() == getExpandedOffset()) {
            setStateInternal(3);
            return;
        }
        WeakReference<View> weakReference = this.nestedScrollingChildRef;
        if (weakReference != null && view == weakReference.get() && this.nestedScrolled) {
            if (this.lastNestedScrollDy <= 0) {
                i7 = this.parentHeight;
                i8 = 5;
            } else if (this.fitToContents) {
                i7 = this.fitToContentsOffset;
            } else {
                int top = v.getTop();
                int i10 = this.halfExpandedOffset;
                if (top > i10) {
                    i8 = 6;
                    i7 = i10;
                } else {
                    i7 = getExpandedOffset();
                }
            }
            startSettlingAnimation(v, i8, i7, false);
            this.nestedScrolled = false;
        }
    }

    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (!v.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (this.state == 1 && actionMasked == 0) {
            return true;
        }
        ViewDragHelper viewDragHelper2 = this.viewDragHelper;
        if (viewDragHelper2 != null) {
            viewDragHelper2.processTouchEvent(motionEvent);
        }
        if (actionMasked == 0) {
            reset();
        }
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(motionEvent);
        if (this.viewDragHelper != null && actionMasked == 2 && !this.ignoreEvents && Math.abs(((float) this.initialY) - motionEvent.getY()) > ((float) this.viewDragHelper.getTouchSlop())) {
            this.viewDragHelper.captureChildView(v, motionEvent.getPointerId(motionEvent.getActionIndex()));
        }
        return !this.ignoreEvents;
    }

    public void removeBottomSheetCallback(BottomSheetCallback bottomSheetCallback) {
        this.callbacks.remove(bottomSheetCallback);
    }

    public void setDraggable(boolean z) {
        this.draggable = z;
    }

    public void setExpandedOffsetDelta(int i2) {
        this.expandedOffsetDelta = i2;
    }

    public void setFitToContents(boolean z) {
        int i2;
        View view;
        if (this.fitToContents != z) {
            this.fitToContents = z;
            if (!z || this.state != 6) {
                i2 = this.state;
            } else {
                i2 = 3;
            }
            setStateInternal(i2);
            StoryHighlightBehaviorAccessibility storyHighlightBehaviorAccessibility = this.mAccessibilityDelegate;
            WeakReference<V> weakReference = this.viewRef;
            if (weakReference == null) {
                view = null;
            } else {
                view = (View) weakReference.get();
            }
            storyHighlightBehaviorAccessibility.updateAccessibilityActions(view);
        }
    }

    public void setHalfExpandedRatio(float f) {
        if (f <= 0.0f || f >= 1.0f) {
            throw new IllegalArgumentException("ratio must be a float value between 0 and 1");
        }
        this.halfExpandedRatio = f;
        if (this.viewRef != null) {
            calculateHalfExpandedOffset();
        }
    }

    public void setPeekHeight(int i2) {
        setPeekHeight(i2, false);
    }

    public void setSaveFlags(int i2) {
        this.saveFlags = i2;
    }

    public void setState(int i2) {
        if (i2 != this.state) {
            if (this.viewRef != null) {
                settleToStatePendingLayout(i2);
            } else if (i2 == 3 || i2 == 6 || i2 == 5) {
                this.state = i2;
            }
        }
    }

    public void setStateInternal(int i2) {
        View view;
        if (this.state != i2) {
            this.state = i2;
            WeakReference<V> weakReference = this.viewRef;
            if (weakReference != null && (view = (View) weakReference.get()) != null) {
                if (i2 == 3) {
                    this.mAccessibilityDelegate.updateImportantForAccessibility(view, true);
                } else if (i2 == 6 || i2 == 5) {
                    this.mAccessibilityDelegate.updateImportantForAccessibility(view, false);
                }
                updateDrawableForTargetState(i2);
                for (int i7 = 0; i7 < this.callbacks.size(); i7++) {
                    this.callbacks.get(i7).onStateChanged(view, i2);
                }
                this.mAccessibilityDelegate.updateAccessibilityActions(view);
            }
        }
    }

    public void setTouchInterceptProvider(BooleanSupplier booleanSupplier) {
        this.mTouchInterceptSupplier = booleanSupplier;
    }

    /* renamed from: settleToState */
    public void lambda$settleToStatePendingLayout$1(View view, int i2) {
        int i7;
        int i8;
        if (i2 == 6) {
            i7 = this.halfExpandedOffset;
            if (this.fitToContents && i7 <= (i8 = this.fitToContentsOffset)) {
                i2 = 3;
                i7 = i8;
            }
        } else if (i2 == 3) {
            i7 = getExpandedOffset();
        } else if (i2 == 5) {
            i7 = this.parentHeight;
        } else {
            throw new IllegalArgumentException(C0086a.i(i2, "Illegal state argument: "));
        }
        startSettlingAnimation(view, i2, i7, false);
    }

    public void startSettlingAnimation(View view, int i2, int i7, boolean z) {
        ViewDragHelper viewDragHelper2 = this.viewDragHelper;
        if (viewDragHelper2 == null || (!z ? !viewDragHelper2.smoothSlideViewTo(view, view.getLeft(), i7) : !viewDragHelper2.settleCapturedViewAt(view.getLeft(), i7))) {
            setStateInternal(i2);
            return;
        }
        setStateInternal(2);
        updateDrawableForTargetState(i2);
        if (this.settleRunnable == null) {
            this.settleRunnable = new SettleRunnable(this, view, i2);
        }
        SettleRunnable settleRunnable2 = this.settleRunnable;
        if (!settleRunnable2.isPosted) {
            settleRunnable2.targetState = i2;
            ViewCompat.postOnAnimation(view, settleRunnable2);
            this.settleRunnable.isPosted = true;
            return;
        }
        settleRunnable2.targetState = i2;
    }

    public final void setPeekHeight(int i2, boolean z) {
        if (i2 == -1) {
            if (!this.peekHeightAuto) {
                this.peekHeightAuto = true;
            }
        } else if (this.peekHeightAuto || this.peekHeight != i2) {
            this.peekHeightAuto = false;
            this.peekHeight = Math.max(0, i2);
        }
    }

    public void onNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i2, int i7, int i8, int i10, int i11, int[] iArr) {
    }
}
