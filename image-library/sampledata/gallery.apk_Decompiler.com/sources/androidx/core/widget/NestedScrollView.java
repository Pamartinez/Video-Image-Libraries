package androidx.core.widget;

import B4.c;
import J6.a;
import V3.b;
import a6.g;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.EdgeEffect;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import android.widget.ScrollView;
import androidx.core.R$attr;
import androidx.core.R$dimen;
import androidx.core.os.BuildCompat;
import androidx.core.util.SeslFadingEdgeHelper;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.DifferentialMotionFlingController;
import androidx.core.view.DifferentialMotionFlingTarget;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.NestedScrollingChild2;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.NestedScrollingParent2;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.ScrollFeedbackProviderCompat;
import androidx.core.view.SeslPointerIconCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityRecordCompat;
import androidx.core.widget.SeslGoToTopConfig;
import androidx.core.widget.SeslGoToTopController;
import androidx.core.widget.SeslGoToTopControllerFactory;
import androidx.core.widget.SeslScrollable;
import androidx.reflect.provider.SeslSettingsReflector$SeslSystemReflector;
import androidx.reflect.view.SeslPointerIconReflector;
import androidx.reflect.view.SeslViewReflector;
import c0.C0086a;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.OCDHelperConstant;
import com.samsung.android.sdk.cover.ScoverState;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import s2.C0271a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NestedScrollView extends FrameLayout implements NestedScrollingParent3, NestedScrollingChild2, SeslScrollable {
    private static final AccessibilityDelegate ACCESSIBILITY_DELEGATE = new AccessibilityDelegate();
    static final int ANIMATED_SCROLL_GAP = 250;
    private static final float DECELERATION_RATE = ((float) (Math.log(0.78d) / Math.log(0.9d)));
    private static final int DEFAULT_SMOOTH_SCROLL_DURATION = 250;
    private static final float FLING_DESTRETCH_FACTOR = 4.0f;
    private static final int GoToTopScrollingDuration = 700;
    private static final int HOVERSCROLL_DELAY = 7;
    private static final int HOVERSCROLL_DOWN = 2;
    private static final int HOVERSCROLL_HEIGHT_BOTTOM_DP = 25;
    private static final int HOVERSCROLL_HEIGHT_TOP_DP = 25;
    private static final float HOVERSCROLL_SPEED = 10.0f;
    private static final int HOVERSCROLL_UP = 1;
    private static final float INFLEXION = 0.35f;
    private static final int INVALID_POINTER = -1;
    private static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    static final float MAX_SCROLL_FACTOR = 0.5f;
    private static final int MIN_PIXEL_PER_SECOND = 1;
    private static final int MOTION_EVENT_ACTION_PEN_DOWN = 211;
    private static final int MOTION_EVENT_ACTION_PEN_MOVE = 213;
    private static final int MOTION_EVENT_ACTION_PEN_UP = 212;
    private static final int MSG_HOVERSCROLL_MOVE = 1;
    private static final String NAVIGATION_MODE = "navigation_mode";
    private static final int NAV_BAR_MODE_3BUTTON = 0;
    private static final int NAV_BAR_MODE_GESTURAL = 2;
    private static final int ON_ABSORB_VELOCITY = 10000;
    private static final int[] SCROLLVIEW_STYLEABLE = {16843130};
    private static final float SCROLL_FRICTION = 0.015f;
    private static final String TAG = "NestedScrollView";
    public final Interpolator SINE_IN_OUT_70;
    private int mActivePointerId;
    /* access modifiers changed from: private */
    public Rect mAvailableBounds;
    /* access modifiers changed from: private */
    public final Runnable mCheckGoToTopAndAutoScrollCondition;
    private final NestedScrollingChildHelper mChildHelper;
    private View mChildToScrollTo;
    /* access modifiers changed from: private */
    public Context mContext;
    private boolean mDebugDrawAvailRect;
    DifferentialMotionFlingController mDifferentialMotionFlingController;
    final DifferentialMotionFlingTargetImpl mDifferentialMotionFlingTarget;
    private boolean mDrawHorizontalPadding;
    public EdgeEffect mEdgeGlowBottom;
    public EdgeEffect mEdgeGlowTop;
    private final SeslFadingEdgeHelper mFadingEdgeHelper;
    private boolean mFillViewport;
    private SeslGoToTopConfig mGoToTopConfig;
    /* access modifiers changed from: private */
    public SeslNestedGoToTopController mGoToTopController;
    private final SeslGoToTopController.Host mGoToTopHost;
    private boolean mHasNestedScrollRange;
    private boolean mHoverAreaEnter;
    private int mHoverBottomAreaHeight;
    private int mHoverDefaultBottomAreaHeight;
    private int mHoverDefaultTopAreaHeight;
    private HoverScrollHandler mHoverHandler;
    private long mHoverRecognitionCurrentTime;
    private long mHoverRecognitionDurationTime;
    private long mHoverRecognitionStartTime;
    private int mHoverScrollDirection;
    /* access modifiers changed from: private */
    public boolean mHoverScrollEnabled;
    private int mHoverScrollSpeed;
    private long mHoverScrollStartTime;
    private boolean mHoverScrollStateChanged;
    private long mHoverScrollTimeInterval;
    private int mHoverTopAreaHeight;
    private int mInitialTopOffsetOfScreen;
    private boolean mIsBeingDragged;
    private boolean mIsHoverOverscrolled;
    private boolean mIsLaidOut;
    private boolean mIsLayoutDirty;
    /* access modifiers changed from: private */
    public boolean mIsSupportHoverScroll;
    private int mLastMotionY;
    private long mLastScroll;
    private int mLastScrollerY;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private int mNaviBarTop;
    private boolean mNeedsHoverScroll;
    private int mNestedScrollRange;
    private int mNestedYOffset;
    private SeslOnGoToTopClickListener mOnGoToTopClickListener;
    private List<SeslScrollable.SeslOnGoToTopClickListener> mOnGoToTopClickListeners;
    View.OnLayoutChangeListener mOnLayoutChangeListener;
    private OnScrollChangeListener mOnScrollChangeListener;
    private List<OnScrollChangeListener> mOnScrollChangeListeners;
    private final NestedScrollingParentHelper mParentHelper;
    private final float mPhysicalCoeff;
    private final Paint mRectPaint;
    private int mRemainNestedScrollRange;
    private SavedState mSavedState;
    private int mScrollBarBottomOffset;
    private int mScrollBarTopOffset;
    private final int[] mScrollConsumed;
    ScrollFeedbackProviderCompat mScrollFeedbackProvider;
    private final SeslFadingEdgeHelper.ScrollInfoProvider mScrollInfoProvider;
    private final int[] mScrollOffset;
    private int mScrollbarBottomPadding;
    private int mScrollbarTopPadding;
    /* access modifiers changed from: private */
    public OverScroller mScroller;
    private int mSeslBottomBarHeight;
    private boolean mSmoothScrollingEnabled;
    private final Rect mTempRect;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    private float mVerticalScrollFactor;
    private final int[] mWindowOffsets;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class AccessibilityDelegate extends AccessibilityDelegateCompat {
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            boolean z;
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            accessibilityEvent.setClassName(ScrollView.class.getName());
            if (nestedScrollView.getScrollRange() > 0) {
                z = true;
            } else {
                z = false;
            }
            accessibilityEvent.setScrollable(z);
            accessibilityEvent.setScrollX(nestedScrollView.getScrollX());
            accessibilityEvent.setScrollY(nestedScrollView.getScrollY());
            AccessibilityRecordCompat.setMaxScrollX(accessibilityEvent, nestedScrollView.getScrollX());
            AccessibilityRecordCompat.setMaxScrollY(accessibilityEvent, nestedScrollView.getScrollRange());
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            int scrollRange;
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            accessibilityNodeInfoCompat.setClassName(ScrollView.class.getName());
            if (nestedScrollView.isEnabled() && (scrollRange = nestedScrollView.getScrollRange()) > 0) {
                accessibilityNodeInfoCompat.setScrollable(true);
                if (nestedScrollView.getScrollY() > 0) {
                    accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD);
                    accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_UP);
                }
                if (nestedScrollView.getScrollY() < scrollRange) {
                    accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD);
                    accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_DOWN);
                }
            }
        }

        public boolean performAccessibilityAction(View view, int i2, Bundle bundle) {
            if (super.performAccessibilityAction(view, i2, bundle)) {
                return true;
            }
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            if (!nestedScrollView.isEnabled()) {
                return false;
            }
            int height = nestedScrollView.getHeight();
            Rect rect = new Rect();
            if (nestedScrollView.getMatrix().isIdentity() && nestedScrollView.getGlobalVisibleRect(rect)) {
                height = rect.height();
            }
            if (i2 != 4096) {
                if (i2 == 8192 || i2 == 16908344) {
                    int max = Math.max(nestedScrollView.getScrollY() - ((height - nestedScrollView.getPaddingBottom()) - nestedScrollView.getPaddingTop()), 0);
                    if (max == nestedScrollView.getScrollY()) {
                        return false;
                    }
                    nestedScrollView.scrollTo(0, max);
                    return true;
                } else if (i2 != 16908346) {
                    return false;
                }
            }
            int min = Math.min(nestedScrollView.getScrollY() + ((height - nestedScrollView.getPaddingBottom()) - nestedScrollView.getPaddingTop()), nestedScrollView.getScrollRange());
            if (min == nestedScrollView.getScrollY()) {
                return false;
            }
            nestedScrollView.scrollTo(0, min);
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api21Impl {
        public static boolean getClipToPadding(ViewGroup viewGroup) {
            return viewGroup.getClipToPadding();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Api35Impl {
        public static void setFrameContentVelocity(View view, float f) {
            try {
                view.setFrameContentVelocity(f);
            } catch (LinkageError unused) {
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class DifferentialMotionFlingTargetImpl implements DifferentialMotionFlingTarget {
        public DifferentialMotionFlingTargetImpl() {
        }

        public float getScaledScrollFactor() {
            return -NestedScrollView.this.getVerticalScrollFactorCompat();
        }

        public boolean startDifferentialMotionFling(float f) {
            if (f == 0.0f) {
                return false;
            }
            stopDifferentialMotionFling();
            NestedScrollView.this.fling((int) f);
            return true;
        }

        public void stopDifferentialMotionFling() {
            NestedScrollView.this.mScroller.abortAnimation();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class HoverScrollHandler extends Handler {
        private final WeakReference<NestedScrollView> mScrollView;

        public HoverScrollHandler(NestedScrollView nestedScrollView) {
            this.mScrollView = new WeakReference<>(nestedScrollView);
        }

        public void handleMessage(Message message) {
            NestedScrollView nestedScrollView = this.mScrollView.get();
            if (nestedScrollView != null) {
                nestedScrollView.handleMessage(message);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnScrollChangeListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };
        public int scrollPosition;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("HorizontalScrollView.SavedState{");
            sb2.append(Integer.toHexString(System.identityHashCode(this)));
            sb2.append(" scrollPosition=");
            return C0086a.l(sb2, this.scrollPosition, "}");
        }

        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.scrollPosition);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.scrollPosition = parcel.readInt();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SeslOnGoToTopClickListener {
    }

    public NestedScrollView(Context context) {
        this(context, (AttributeSet) null);
    }

    private void abortAnimatedScroll() {
        this.mScroller.abortAnimation();
        stopNestedScroll(1);
    }

    private void adjustNestedScrollRange() {
        getLocationInWindow(this.mWindowOffsets);
        int i2 = this.mNestedScrollRange;
        int i7 = this.mInitialTopOffsetOfScreen;
        int i8 = this.mWindowOffsets[1];
        int i10 = i2 - (i7 - i8);
        this.mRemainNestedScrollRange = i10;
        if (i7 - i8 < 0) {
            this.mNestedScrollRange = i10;
            this.mInitialTopOffsetOfScreen = i8;
        }
    }

    private void adjustNestedScrollRangeBy(int i2) {
        if (!this.mHasNestedScrollRange) {
            return;
        }
        if (!canScrollUp() || this.mRemainNestedScrollRange != 0) {
            int i7 = this.mRemainNestedScrollRange - i2;
            this.mRemainNestedScrollRange = i7;
            if (i7 < 0) {
                this.mRemainNestedScrollRange = 0;
                return;
            }
            int i8 = this.mNestedScrollRange;
            if (i7 > i8) {
                this.mRemainNestedScrollRange = i8;
            }
        }
    }

    private void applyFadingEdge(boolean z, Runnable runnable) {
        if (z) {
            this.mFadingEdgeHelper.setTargetView(this);
        }
        if (runnable != null) {
            runnable.run();
        }
        invalidate();
    }

    private Rect calculateFadingEdgeBounds() {
        Rect rect = new Rect(getScrollX(), getScrollY(), (getRight() + getScrollX()) - getLeft(), (getBottom() + getScrollY()) - getTop());
        if (getClipToPadding()) {
            rect.left = getPaddingLeft() + rect.left;
            rect.right -= getPaddingRight();
            rect.top = getPaddingTop() + rect.top;
            rect.bottom -= getPaddingBottom();
        }
        if (isPaddingOffsetRequired()) {
            rect.top += getTopPaddingOffset();
            rect.bottom += getBottomPaddingOffset();
        }
        return rect;
    }

    private boolean canHoverScroll() {
        if (!this.mIsSupportHoverScroll || !this.mHoverScrollEnabled) {
            return false;
        }
        return true;
    }

    private boolean canOverScroll() {
        int overScrollMode = getOverScrollMode();
        if (overScrollMode == 0 || (overScrollMode == 1 && getScrollRange() > 0)) {
            return true;
        }
        return false;
    }

    private boolean canScroll() {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            if (childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin > (getHeight() - getPaddingTop()) - getPaddingBottom()) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public boolean canScrollDown() {
        return canScrollVertically(1);
    }

    /* access modifiers changed from: private */
    public boolean canScrollUp() {
        return canScrollVertically(-1);
    }

    /* access modifiers changed from: private */
    public boolean checkChildScrollableForGoToTopAndAutoScroll() {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 33) {
            Log.i(TAG, "GTT HSC not support : under Platform Version : " + i2);
            return false;
        }
        if (getChildCount() > 0 && (getChildAt(0) instanceof ViewGroup)) {
            ViewGroup viewGroup = (ViewGroup) getChildAt(0);
            if (viewGroup.getHeight() < getHeight()) {
                Log.i(TAG, "GTT HSC not support : Small Height child");
                return false;
            }
            int i7 = 0;
            while (i7 < viewGroup.getChildCount()) {
                View childAt = viewGroup.getChildAt(i7);
                if (childAt.getVisibility() == 8 || (!childAt.canScrollVertically(1) && !childAt.canScrollVertically(-1))) {
                    i7++;
                } else {
                    Log.i(TAG, "GTT HSC not support : Some child view can scroll index: " + i7 + " " + childAt);
                    return false;
                }
            }
        }
        return true;
    }

    private static int clamp(int i2, int i7, int i8) {
        if (i7 >= i8 || i2 < 0) {
            return 0;
        }
        if (i7 + i2 > i8) {
            return i8 - i7;
        }
        return i2;
    }

    private void doScrollY(int i2) {
        if (i2 == 0) {
            return;
        }
        if (this.mSmoothScrollingEnabled) {
            smoothScrollBy(0, i2);
        } else {
            scrollBy(0, i2);
        }
    }

    private boolean edgeEffectFling(int i2) {
        if (EdgeEffectCompat.getDistance(this.mEdgeGlowTop) != 0.0f) {
            if (shouldAbsorb(this.mEdgeGlowTop, i2)) {
                this.mEdgeGlowTop.onAbsorb(i2);
                return true;
            }
            fling(-i2);
            return true;
        } else if (EdgeEffectCompat.getDistance(this.mEdgeGlowBottom) == 0.0f) {
            return false;
        } else {
            int i7 = -i2;
            if (shouldAbsorb(this.mEdgeGlowBottom, i7)) {
                this.mEdgeGlowBottom.onAbsorb(i7);
                return true;
            }
            fling(i7);
            return true;
        }
    }

    private void endDrag() {
        this.mIsBeingDragged = false;
        recycleVelocityTracker();
        stopNestedScroll(0);
        this.mEdgeGlowTop.onRelease();
        this.mEdgeGlowBottom.onRelease();
    }

    private void ensureGoToTopController() {
        if (this.mGoToTopController == null) {
            this.mGoToTopController = (SeslNestedGoToTopController) SeslGoToTopControllerFactory.createController(SeslGoToTopControllerFactory.ControllerType.NESTEDSCROLLVIEW, updateGoToTopConfig(), this.mGoToTopHost, TAG);
        }
    }

    private int findAndGetColor(String str, int i2) {
        try {
            return this.mContext.getColor(this.mContext.getResources().getIdentifier(str, "color", this.mContext.getPackageName()));
        } catch (Resources.NotFoundException unused) {
            return i2;
        }
    }

    private int findAndGetDimension(String str, int i2) {
        try {
            return this.mContext.getResources().getDimensionPixelSize(this.mContext.getResources().getIdentifier(str, "dimen", this.mContext.getPackageName()));
        } catch (Resources.NotFoundException unused) {
            return i2;
        }
    }

    private Drawable findAndGetDrawable(String str) {
        try {
            return this.mContext.getResources().getDrawable(this.mContext.getResources().getIdentifier(str, "drawable", this.mContext.getPackageName()));
        } catch (Resources.NotFoundException unused) {
            return null;
        }
    }

    private View findFocusableViewInBounds(boolean z, int i2, int i7) {
        boolean z3;
        boolean z7;
        ArrayList<View> focusables = getFocusables(2);
        int size = focusables.size();
        View view = null;
        boolean z9 = false;
        for (int i8 = 0; i8 < size; i8++) {
            View view2 = focusables.get(i8);
            int top = view2.getTop();
            int bottom = view2.getBottom();
            if (i2 < bottom && top < i7) {
                if (i2 >= top || bottom >= i7) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                if (view == null) {
                    view = view2;
                    z9 = z3;
                } else {
                    if ((!z || top >= view.getTop()) && (z || bottom <= view.getBottom())) {
                        z7 = false;
                    } else {
                        z7 = true;
                    }
                    if (z9) {
                        if (z3) {
                            if (!z7) {
                            }
                        }
                    } else if (z3) {
                        view = view2;
                        z9 = true;
                    } else if (!z7) {
                    }
                    view = view2;
                }
            }
        }
        return view;
    }

    private boolean findSuperClass(ViewParent viewParent, String str) {
        for (Class cls = viewParent.getClass(); cls != null; cls = cls.getSuperclass()) {
            if (cls.getSimpleName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    private ScrollFeedbackProviderCompat getScrollFeedbackProvider() {
        if (this.mScrollFeedbackProvider == null) {
            this.mScrollFeedbackProvider = ScrollFeedbackProviderCompat.createProvider(this);
        }
        return this.mScrollFeedbackProvider;
    }

    private float getSplineFlingDistance(int i2) {
        double log = Math.log((double) ((((float) Math.abs(i2)) * 0.35f) / (this.mPhysicalCoeff * SCROLL_FRICTION)));
        float f = DECELERATION_RATE;
        return (float) (Math.exp((((double) f) / (((double) f) - 1.0d)) * log) * ((double) (this.mPhysicalCoeff * SCROLL_FRICTION)));
    }

    /* access modifiers changed from: private */
    public void handleMessage(Message message) {
        int i2;
        if (message.what == 1) {
            int scrollRange = getScrollRange();
            long currentTimeMillis = System.currentTimeMillis();
            this.mHoverRecognitionCurrentTime = currentTimeMillis;
            this.mHoverRecognitionDurationTime = (currentTimeMillis - this.mHoverRecognitionStartTime) / 1000;
            if (currentTimeMillis - this.mHoverScrollStartTime >= this.mHoverScrollTimeInterval) {
                int applyDimension = (int) (TypedValue.applyDimension(1, 10.0f, this.mContext.getResources().getDisplayMetrics()) + 0.5f);
                this.mHoverScrollSpeed = applyDimension;
                long j2 = this.mHoverRecognitionDurationTime;
                if (j2 > 2 && j2 < 4) {
                    this.mHoverScrollSpeed = applyDimension + ((int) (((double) applyDimension) * 0.1d));
                } else if (j2 >= 4 && j2 < 5) {
                    this.mHoverScrollSpeed = applyDimension + ((int) (((double) applyDimension) * 0.2d));
                } else if (j2 >= 5) {
                    this.mHoverScrollSpeed = applyDimension + ((int) (((double) applyDimension) * 0.3d));
                }
                if (this.mHoverScrollDirection == 2) {
                    i2 = this.mHoverScrollSpeed * -1;
                } else {
                    i2 = this.mHoverScrollSpeed;
                }
                int i7 = i2;
                ViewCompat.getLayoutDirection(this);
                boolean z = false;
                if ((i7 >= 0 || getScrollY() <= 0) && (i7 <= 0 || getScrollY() >= scrollRange)) {
                    int overScrollMode = getOverScrollMode();
                    if (overScrollMode == 0 || (overScrollMode == 1 && scrollRange > 0)) {
                        z = true;
                    }
                    if (z && !this.mIsHoverOverscrolled) {
                        int i8 = this.mHoverScrollDirection;
                        if (i8 == 2) {
                            this.mEdgeGlowTop.setSize((getWidth() - getPaddingLeft()) - getPaddingRight(), getHeight());
                            this.mEdgeGlowTop.onAbsorb(10000);
                            if (!this.mEdgeGlowBottom.isFinished()) {
                                this.mEdgeGlowBottom.onRelease();
                            }
                        } else if (i8 == 1) {
                            this.mEdgeGlowBottom.setSize((getWidth() - getPaddingLeft()) - getPaddingRight(), getHeight());
                            this.mEdgeGlowBottom.onAbsorb(10000);
                            SeslNestedGoToTopController seslNestedGoToTopController = this.mGoToTopController;
                            if (seslNestedGoToTopController != null) {
                                seslNestedGoToTopController.showIfNeeded();
                            }
                            if (!this.mEdgeGlowTop.isFinished()) {
                                this.mEdgeGlowTop.onRelease();
                            }
                        }
                        if (!this.mEdgeGlowTop.isFinished() || !this.mEdgeGlowBottom.isFinished()) {
                            invalidate();
                        }
                        this.mIsHoverOverscrolled = true;
                    }
                    if (!z && !this.mIsHoverOverscrolled) {
                        this.mIsHoverOverscrolled = true;
                        return;
                    }
                    return;
                }
                startNestedScroll(2, 1);
                if (!dispatchNestedPreScroll(0, i7, (int[]) null, (int[]) null, 1)) {
                    smoothScrollBy(0, i7);
                } else {
                    adjustNestedScrollRangeBy(i7);
                }
                this.mHoverHandler.sendEmptyMessageDelayed(1, 7);
            }
        }
    }

    private boolean inChild(int i2, int i7) {
        if (getChildCount() > 0) {
            int scrollY = getScrollY();
            View childAt = getChildAt(0);
            if (i7 < childAt.getTop() - scrollY || i7 >= childAt.getBottom() - scrollY || i2 < childAt.getLeft() || i2 >= childAt.getRight()) {
                return false;
            }
            return true;
        }
        return false;
    }

    private void initOrResetVelocityTracker() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        } else {
            velocityTracker.clear();
        }
    }

    private void initScrollView() {
        this.mScroller = new OverScroller(getContext());
        setFocusable(true);
        setDescendantFocusability(262144);
        setWillNotDraw(false);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mMinimumVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        post(this.mCheckGoToTopAndAutoScrollCondition);
        addOnLayoutChangeListener(this.mOnLayoutChangeListener);
    }

    private void initVelocityTrackerIfNotExists() {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
    }

    private boolean isFloatingGoToTopScrollRequest(int i2) {
        if (i2 != (-getScrollY()) || this.mAvailableBounds == null) {
            return false;
        }
        return true;
    }

    private boolean isLightTheme(Context context) {
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(16844176, typedValue, true) || typedValue.data != 0) {
            return true;
        }
        return false;
    }

    private boolean isLockScreenMode() {
        if (!((KeyguardManager) this.mContext.getSystemService("keyguard")).inKeyguardRestrictedInputMode()) {
            return false;
        }
        return true;
    }

    private boolean isOffScreen(View view) {
        return !isWithinDeltaOfScreen(view, 0, getHeight());
    }

    private static boolean isViewDescendantOf(View view, View view2) {
        if (view == view2) {
            return true;
        }
        ViewParent parent = view.getParent();
        if (!(parent instanceof ViewGroup) || !isViewDescendantOf((View) parent, view2)) {
            return false;
        }
        return true;
    }

    private boolean isWithinDeltaOfScreen(View view, int i2, int i7) {
        view.getDrawingRect(this.mTempRect);
        offsetDescendantRectToMyCoords(view, this.mTempRect);
        if (this.mTempRect.bottom + i2 < getScrollY() || this.mTempRect.top - i2 > getScrollY() + i7) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$seslSetFadingEdgeEnabled$0(boolean z, int i2, int i7) {
        this.mFadingEdgeHelper.setFadingEdgeEnabled(z, i2, i7, true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$seslSetFadingEdgeEnabled$1(boolean z) {
        this.mFadingEdgeHelper.setFadingEdgeEnabled(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$seslSetFadingEdgeEnabled$2(boolean z, boolean z3) {
        this.mFadingEdgeHelper.setFadingEdgeEnabled(z, false, z3);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$seslSetFadingEdgeEnabled$3(boolean z, boolean z3, boolean z7) {
        this.mFadingEdgeHelper.setFadingEdgeEnabled(z, z3, z7);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$seslSetGoToTopEnabled$4() {
        int size;
        List<SeslScrollable.SeslOnGoToTopClickListener> list = this.mOnGoToTopClickListeners;
        if (list == null || list.size() - 1 < 0) {
            return false;
        }
        this.mOnGoToTopClickListeners.get(size).getClass();
        throw new ClassCastException();
    }

    private void onNestedScrollInternal(int i2, int i7, int[] iArr) {
        SeslNestedGoToTopController seslNestedGoToTopController = this.mGoToTopController;
        if (seslNestedGoToTopController == null || !seslNestedGoToTopController.isScrollRunning() || this.mScroller.isFinished()) {
            int scrollY = getScrollY();
            scrollBy(0, i2);
            this.mLastScrollerY = getScrollY();
            if (this.mScroller.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                postInvalidateOnAnimation();
            }
            int scrollY2 = getScrollY() - scrollY;
            if (iArr != null) {
                iArr[1] = iArr[1] + scrollY2;
            }
            this.mChildHelper.dispatchNestedScroll(0, scrollY2, 0, i2 - scrollY2, (int[]) null, i7, iArr);
        }
    }

    private void onSecondaryPointerUp(MotionEvent motionEvent) {
        int i2;
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.mActivePointerId) {
            if (actionIndex == 0) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            this.mLastMotionY = (int) motionEvent.getY(i2);
            this.mActivePointerId = motionEvent.getPointerId(i2);
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    private void recycleVelocityTracker() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0060  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int releaseVerticalGlow(int r4, float r5) {
        /*
            r3 = this;
            int r0 = r3.getWidth()
            float r0 = (float) r0
            float r5 = r5 / r0
            float r4 = (float) r4
            int r0 = r3.getHeight()
            float r0 = (float) r0
            float r4 = r4 / r0
            android.widget.EdgeEffect r0 = r3.mEdgeGlowTop
            float r0 = androidx.core.widget.EdgeEffectCompat.getDistance(r0)
            r1 = 0
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 == 0) goto L_0x0031
            android.widget.EdgeEffect r0 = r3.mEdgeGlowTop
            float r4 = -r4
            float r4 = androidx.core.widget.EdgeEffectCompat.onPullDistance(r0, r4, r5)
            float r4 = -r4
            android.widget.EdgeEffect r5 = r3.mEdgeGlowTop
            float r5 = androidx.core.widget.EdgeEffectCompat.getDistance(r5)
            int r5 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r5 != 0) goto L_0x002f
            android.widget.EdgeEffect r5 = r3.mEdgeGlowTop
            r5.onRelease()
        L_0x002f:
            r1 = r4
            goto L_0x0054
        L_0x0031:
            android.widget.EdgeEffect r0 = r3.mEdgeGlowBottom
            float r0 = androidx.core.widget.EdgeEffectCompat.getDistance(r0)
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 == 0) goto L_0x0054
            android.widget.EdgeEffect r0 = r3.mEdgeGlowBottom
            r2 = 1065353216(0x3f800000, float:1.0)
            float r2 = r2 - r5
            float r4 = androidx.core.widget.EdgeEffectCompat.onPullDistance(r0, r4, r2)
            android.widget.EdgeEffect r5 = r3.mEdgeGlowBottom
            float r5 = androidx.core.widget.EdgeEffectCompat.getDistance(r5)
            int r5 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r5 != 0) goto L_0x002f
            android.widget.EdgeEffect r5 = r3.mEdgeGlowBottom
            r5.onRelease()
            goto L_0x002f
        L_0x0054:
            int r4 = r3.getHeight()
            float r4 = (float) r4
            float r1 = r1 * r4
            int r4 = java.lang.Math.round(r1)
            if (r4 == 0) goto L_0x0063
            r3.invalidate()
        L_0x0063:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.releaseVerticalGlow(int, float):int");
    }

    private void runAnimatedScroll(boolean z) {
        if (z) {
            startNestedScroll(2, 1);
        } else {
            stopNestedScroll(1);
        }
        this.mLastScrollerY = getScrollY();
        postInvalidateOnAnimation();
    }

    private boolean scrollAndFocus(int i2, int i7, int i8) {
        boolean z;
        int i10;
        int height = getHeight();
        int scrollY = getScrollY();
        int i11 = height + scrollY;
        boolean z3 = false;
        if (i2 == 33) {
            z = true;
        } else {
            z = false;
        }
        View findFocusableViewInBounds = findFocusableViewInBounds(z, i7, i8);
        if (findFocusableViewInBounds == null) {
            findFocusableViewInBounds = this;
        }
        if (i7 < scrollY || i8 > i11) {
            if (z) {
                i10 = i7 - scrollY;
            } else {
                i10 = i8 - i11;
            }
            doScrollY(i10);
            z3 = true;
        }
        if (findFocusableViewInBounds != findFocus()) {
            findFocusableViewInBounds.requestFocus(i2);
        }
        return z3;
    }

    private void scrollToChild(View view) {
        view.getDrawingRect(this.mTempRect);
        offsetDescendantRectToMyCoords(view, this.mTempRect);
        int computeScrollDeltaToGetChildRectOnScreen = computeScrollDeltaToGetChildRectOnScreen(this.mTempRect);
        if (computeScrollDeltaToGetChildRectOnScreen != 0) {
            scrollBy(0, computeScrollDeltaToGetChildRectOnScreen);
        }
    }

    private boolean scrollToChildRect(Rect rect, boolean z) {
        boolean z3;
        int computeScrollDeltaToGetChildRectOnScreen = computeScrollDeltaToGetChildRectOnScreen(rect);
        if (computeScrollDeltaToGetChildRectOnScreen != 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3) {
            if (z) {
                scrollBy(0, computeScrollDeltaToGetChildRectOnScreen);
                return z3;
            }
            smoothScrollBy(0, computeScrollDeltaToGetChildRectOnScreen);
        }
        return z3;
    }

    private boolean seslDispatchNestedScroll(int i2, int i7, int i8, int i10, int[] iArr, int i11, int[] iArr2) {
        return this.mChildHelper.seslDispatchNestedScroll(i2, i7, i8, i10, iArr, i11, iArr2);
    }

    private void seslRenderFadingEffect(Canvas canvas) {
        this.mFadingEdgeHelper.renderFadingEffect(canvas, this.mScrollInfoProvider);
    }

    private boolean shouldAbsorb(EdgeEffect edgeEffect, int i2) {
        if (i2 > 0) {
            return true;
        }
        if (getSplineFlingDistance(-i2) < EdgeEffectCompat.getDistance(edgeEffect) * ((float) getHeight())) {
            return true;
        }
        return false;
    }

    private void showPointerIcon(MotionEvent motionEvent, int i2) {
        PointerIcon pointerIcon;
        motionEvent.getDevice();
        if (SeslPointerIconCompat.isSemStylusDefault(i2)) {
            pointerIcon = null;
        } else {
            pointerIcon = PointerIcon.getSystemIcon(this.mContext, i2);
        }
        SeslViewReflector.semSetPointerIcon(this, motionEvent.getToolType(0), pointerIcon);
    }

    private boolean stopGlowAnimations(MotionEvent motionEvent) {
        boolean z;
        if (EdgeEffectCompat.getDistance(this.mEdgeGlowTop) != 0.0f) {
            EdgeEffectCompat.onPullDistance(this.mEdgeGlowTop, 0.0f, motionEvent.getX() / ((float) getWidth()));
            z = true;
        } else {
            z = false;
        }
        if (EdgeEffectCompat.getDistance(this.mEdgeGlowBottom) == 0.0f) {
            return z;
        }
        EdgeEffectCompat.onPullDistance(this.mEdgeGlowBottom, 0.0f, 1.0f - (motionEvent.getX() / ((float) getWidth())));
        return true;
    }

    private SeslGoToTopConfig updateGoToTopConfig() {
        int findAndGetDimension = findAndGetDimension("sesl_go_to_top_scrollable_view_gap", 0);
        int findAndGetDimension2 = findAndGetDimension("sesl_go_to_top_scrollable_view_size", -1);
        int findAndGetDimension3 = findAndGetDimension("sesl_go_to_top_elevation", -1);
        Drawable findAndGetDrawable = findAndGetDrawable("sesl_list_go_to_top_light");
        Drawable findAndGetDrawable2 = findAndGetDrawable("sesl_list_go_to_top_dark");
        Drawable findAndGetDrawable3 = findAndGetDrawable("sesl_go_to_top_background_light");
        Drawable findAndGetDrawable4 = findAndGetDrawable("sesl_go_to_top_background_dark");
        Drawable findAndGetDrawable5 = findAndGetDrawable("sesl_go_to_top_background_blur");
        return new SeslGoToTopConfig.Builder().setIconLight(findAndGetDrawable).setIconDark(findAndGetDrawable2).setBackgroundLight(findAndGetDrawable3).setBackgroundDark(findAndGetDrawable4).setBackgroundBlur(findAndGetDrawable5).setBackgroundColorBlur(findAndGetColor("sesl_figma_floating_component_blur_background_dark", -1)).setPaddingBottom(findAndGetDimension).setPaddingLeft(0).setPaddingRight(0).setSize(findAndGetDimension2).setElevation((float) findAndGetDimension3).setOverlayFeatureHiddenHeightPx(0).setScrollToTopDurationMs(700).setFadeInInterpolator(this.SINE_IN_OUT_70).setFadeOutInterpolator(LINEAR_INTERPOLATOR).build();
    }

    private void updateScrollbarVerticalPadding() {
        SeslViewReflector.semSetScrollBarTopPadding(this, this.mScrollbarTopPadding + this.mScrollBarTopOffset);
        SeslViewReflector.semSetScrollBarBottomPadding(this, this.mScrollbarBottomPadding + this.mScrollBarBottomOffset);
    }

    public void addOnScrollChangeListener(OnScrollChangeListener onScrollChangeListener) {
        if (this.mOnScrollChangeListeners == null) {
            this.mOnScrollChangeListeners = new ArrayList();
        }
        this.mOnScrollChangeListeners.add(onScrollChangeListener);
    }

    public void addView(View view) {
        if (getChildCount() <= 0) {
            super.addView(view);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    public boolean arrowScroll(int i2) {
        View findFocus = findFocus();
        if (findFocus == this) {
            findFocus = null;
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, findFocus, i2);
        int maxScrollAmount = getMaxScrollAmount();
        if (findNextFocus == null || !isWithinDeltaOfScreen(findNextFocus, maxScrollAmount, getHeight())) {
            if (i2 == 33 && getScrollY() < maxScrollAmount) {
                maxScrollAmount = getScrollY();
            } else if (i2 == 130 && getChildCount() > 0) {
                View childAt = getChildAt(0);
                maxScrollAmount = Math.min((childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin) - ((getHeight() + getScrollY()) - getPaddingBottom()), maxScrollAmount);
            }
            if (maxScrollAmount == 0) {
                return false;
            }
            if (i2 != 130) {
                maxScrollAmount = -maxScrollAmount;
            }
            doScrollY(maxScrollAmount);
        } else {
            findNextFocus.getDrawingRect(this.mTempRect);
            offsetDescendantRectToMyCoords(findNextFocus, this.mTempRect);
            int computeScrollDeltaToGetChildRectOnScreen = computeScrollDeltaToGetChildRectOnScreen(this.mTempRect);
            this.mLastScrollerY = getScrollY();
            doScrollY(computeScrollDeltaToGetChildRectOnScreen);
            findNextFocus.requestFocus(i2);
        }
        if (findFocus == null || !findFocus.isFocused() || !isOffScreen(findFocus)) {
            return true;
        }
        int descendantFocusability = getDescendantFocusability();
        setDescendantFocusability(131072);
        requestFocus();
        setDescendantFocusability(descendantFocusability);
        return true;
    }

    public int computeHorizontalScrollExtent() {
        return super.computeHorizontalScrollExtent();
    }

    public int computeHorizontalScrollOffset() {
        return super.computeHorizontalScrollOffset();
    }

    public int computeHorizontalScrollRange() {
        return super.computeHorizontalScrollRange();
    }

    public void computeScroll() {
        int i2;
        if (!this.mScroller.isFinished()) {
            this.mScroller.computeScrollOffset();
            int currY = this.mScroller.getCurrY();
            int consumeFlingInVerticalStretch = consumeFlingInVerticalStretch(currY - this.mLastScrollerY);
            this.mLastScrollerY = currY;
            int[] iArr = this.mScrollConsumed;
            iArr[1] = 0;
            dispatchNestedPreScroll(0, consumeFlingInVerticalStretch, iArr, (int[]) null, 1);
            int i7 = consumeFlingInVerticalStretch - this.mScrollConsumed[1];
            int scrollRange = getScrollRange();
            if (Build.VERSION.SDK_INT >= 35) {
                Api35Impl.setFrameContentVelocity(this, Math.abs(this.mScroller.getCurrVelocity()));
            }
            if (i7 != 0) {
                int scrollY = getScrollY();
                overScrollByCompat(0, i7, getScrollX(), scrollY, 0, scrollRange, 0, 0, false);
                i2 = scrollRange;
                int scrollY2 = getScrollY() - scrollY;
                int i8 = i7 - scrollY2;
                int[] iArr2 = this.mScrollConsumed;
                iArr2[1] = 0;
                if (seslDispatchNestedScroll(0, scrollY2, 0, i8, this.mScrollOffset, 1, iArr2)) {
                    int[] iArr3 = this.mScrollOffset;
                    iArr3[0] = 0;
                    iArr3[1] = 0;
                }
                int[] iArr4 = this.mScrollOffset;
                if (iArr4[0] < 0 || iArr4[1] < 0) {
                    iArr4[0] = 0;
                    iArr4[1] = 0;
                }
                i7 = i8 - this.mScrollConsumed[1];
            } else {
                i2 = scrollRange;
            }
            if (i7 != 0) {
                int overScrollMode = getOverScrollMode();
                if (overScrollMode == 0 || (overScrollMode == 1 && i2 > 0)) {
                    if (i7 < 0) {
                        if (this.mEdgeGlowTop.isFinished()) {
                            this.mEdgeGlowTop.onAbsorb((int) this.mScroller.getCurrVelocity());
                        }
                    } else if (this.mEdgeGlowBottom.isFinished()) {
                        this.mEdgeGlowBottom.onAbsorb((int) this.mScroller.getCurrVelocity());
                    }
                }
                abortAnimatedScroll();
            }
            if (!this.mScroller.isFinished()) {
                postInvalidateOnAnimation();
            } else {
                stopNestedScroll(1);
            }
            SeslViewReflector.setFrameContentVelocity(this, Math.abs(this.mScroller.getCurrVelocity()));
        }
    }

    public int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
        int i2;
        int i7;
        int i8;
        if (getChildCount() == 0) {
            return 0;
        }
        int height = getHeight();
        int scrollY = getScrollY();
        int i10 = (scrollY + height) - this.mHoverBottomAreaHeight;
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        if (rect.top > 0) {
            scrollY += verticalFadingEdgeLength;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        if (rect.bottom < childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin) {
            i2 = i10 - verticalFadingEdgeLength;
        } else {
            i2 = i10;
        }
        int i11 = rect.bottom;
        if (i11 > i2 && rect.top > scrollY) {
            if (rect.height() > height) {
                i8 = rect.top - scrollY;
            } else {
                i8 = rect.bottom - i2;
            }
            return Math.min(i8, (childAt.getBottom() + layoutParams.bottomMargin) - i10);
        } else if (rect.top >= scrollY || i11 >= i2) {
            return 0;
        } else {
            if (rect.height() > height) {
                i7 = 0 - (i2 - rect.bottom);
            } else {
                i7 = 0 - (scrollY - rect.top);
            }
            return Math.max(i7, -getScrollY());
        }
    }

    public int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }

    public int computeVerticalScrollOffset() {
        return Math.max(0, super.computeVerticalScrollOffset());
    }

    public int computeVerticalScrollRange() {
        int childCount = getChildCount();
        int height = (getHeight() - getPaddingBottom()) - getPaddingTop();
        if (childCount == 0) {
            return height;
        }
        View childAt = getChildAt(0);
        int bottom = childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin;
        int scrollY = getScrollY();
        int max = Math.max(0, bottom - height);
        if (scrollY < 0) {
            return bottom - scrollY;
        }
        if (scrollY > max) {
            return (scrollY - max) + bottom;
        }
        return bottom;
    }

    public int consumeFlingInVerticalStretch(int i2) {
        int height = getHeight();
        if (i2 > 0 && EdgeEffectCompat.getDistance(this.mEdgeGlowTop) != 0.0f) {
            int round = Math.round(EdgeEffectCompat.onPullDistance(this.mEdgeGlowTop, (((float) (-i2)) * FLING_DESTRETCH_FACTOR) / ((float) height), 0.5f) * (((float) (-height)) / FLING_DESTRETCH_FACTOR));
            if (round != i2) {
                this.mEdgeGlowTop.finish();
            }
            return i2 - round;
        } else if (i2 >= 0 || EdgeEffectCompat.getDistance(this.mEdgeGlowBottom) == 0.0f) {
            return i2;
        } else {
            float f = (float) height;
            float f5 = (((float) i2) * FLING_DESTRETCH_FACTOR) / f;
            int round2 = Math.round(EdgeEffectCompat.onPullDistance(this.mEdgeGlowBottom, f5, 0.5f) * (f / FLING_DESTRETCH_FACTOR));
            if (round2 != i2) {
                this.mEdgeGlowBottom.finish();
            }
            return i2 - round2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0037  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void dispatchDraw(android.graphics.Canvas r14) {
        /*
            r13 = this;
            super.dispatchDraw(r14)
            boolean r0 = r13.mDebugDrawAvailRect
            if (r0 == 0) goto L_0x0032
            android.graphics.Paint r6 = new android.graphics.Paint
            r6.<init>()
            r0 = -16711936(0xffffffffff00ff00, float:-1.7146522E38)
            r6.setColor(r0)
            r0 = 128(0x80, float:1.794E-43)
            r6.setAlpha(r0)
            android.graphics.Paint$Style r0 = android.graphics.Paint.Style.FILL
            r6.setStyle(r0)
            android.graphics.Rect r0 = r13.mAvailableBounds
            if (r0 == 0) goto L_0x0032
            int r1 = r0.left
            float r2 = (float) r1
            int r1 = r0.top
            float r3 = (float) r1
            int r1 = r0.right
            float r4 = (float) r1
            int r0 = r0.bottom
            float r5 = (float) r0
            r1 = r14
            r1.drawRect(r2, r3, r4, r5, r6)
            r7 = r1
            goto L_0x0033
        L_0x0032:
            r7 = r14
        L_0x0033:
            boolean r14 = r13.mDrawHorizontalPadding
            if (r14 == 0) goto L_0x0066
            int r14 = r13.getPaddingLeft()
            int r0 = r13.getPaddingRight()
            int r1 = r13.getHeight()
            int r2 = r13.getWidth()
            int r3 = r13.getScrollY()
            if (r14 <= 0) goto L_0x0058
            float r9 = (float) r3
            float r10 = (float) r14
            int r14 = r1 + r3
            float r11 = (float) r14
            android.graphics.Paint r12 = r13.mRectPaint
            r8 = 0
            r7.drawRect(r8, r9, r10, r11, r12)
        L_0x0058:
            if (r0 <= 0) goto L_0x0066
            int r14 = r2 - r0
            float r8 = (float) r14
            float r9 = (float) r3
            float r10 = (float) r2
            int r1 = r1 + r3
            float r11 = (float) r1
            android.graphics.Paint r12 = r13.mRectPaint
            r7.drawRect(r8, r9, r10, r11, r12)
        L_0x0066:
            androidx.core.util.SeslFadingEdgeHelper r14 = r13.mFadingEdgeHelper
            boolean r14 = r14.isFadingEdgeEnabled()
            if (r14 == 0) goto L_0x0071
            r13.seslRenderFadingEffect(r7)
        L_0x0071:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.dispatchDraw(android.graphics.Canvas):void");
    }

    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        int i2;
        boolean z;
        SeslNestedGoToTopController seslNestedGoToTopController;
        int action = motionEvent.getAction();
        if (action == 9 || this.mHoverScrollStateChanged) {
            if (this.mHasNestedScrollRange) {
                adjustNestedScrollRange();
            }
            int toolType = motionEvent.getToolType(0);
            this.mNeedsHoverScroll = true;
            this.mHoverScrollStateChanged = false;
            if (!canHoverScroll()) {
                this.mNeedsHoverScroll = false;
            }
            if (this.mNeedsHoverScroll && toolType == 2) {
                if (Settings.System.getInt(this.mContext.getContentResolver(), SeslSettingsReflector$SeslSystemReflector.getField_SEM_PEN_HOVERING(), 0) != 1) {
                    this.mNeedsHoverScroll = false;
                }
            }
            if (this.mNeedsHoverScroll && toolType == 3) {
                this.mNeedsHoverScroll = false;
            }
        }
        if (!this.mNeedsHoverScroll) {
            return super.dispatchHoverEvent(motionEvent);
        }
        int x9 = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int childCount = getChildCount();
        int scrollRange = getScrollRange();
        if (this.mHoverHandler == null) {
            this.mHoverHandler = new HoverScrollHandler(this);
        }
        if (this.mHoverDefaultTopAreaHeight <= 0 || this.mHoverDefaultBottomAreaHeight <= 0) {
            this.mHoverDefaultTopAreaHeight = (int) (TypedValue.applyDimension(1, 25.0f, this.mContext.getResources().getDisplayMetrics()) + 0.5f);
            this.mHoverDefaultBottomAreaHeight = (int) (TypedValue.applyDimension(1, 25.0f, this.mContext.getResources().getDisplayMetrics()) + 0.5f);
        }
        if (childCount != 0) {
            i2 = getHeight();
        } else {
            i2 = 0;
        }
        if (motionEvent.getToolType(0) == 2) {
            z = true;
        } else {
            z = false;
        }
        if ((y <= this.mHoverTopAreaHeight + this.mHoverDefaultTopAreaHeight || y >= ((i2 - this.mHoverBottomAreaHeight) - this.mHoverDefaultBottomAreaHeight) - this.mRemainNestedScrollRange) && x9 > 0 && x9 <= getRight() && scrollRange != 0 && ((y < 0 || y > this.mHoverTopAreaHeight + this.mHoverDefaultTopAreaHeight || getScrollY() > 0 || !this.mIsHoverOverscrolled) && ((y < i2 - this.mHoverBottomAreaHeight || y > i2 || getScrollY() < scrollRange || !this.mIsHoverOverscrolled) && ((!z || motionEvent.getButtonState() != 32) && z && !isLockScreenMode() && ((seslNestedGoToTopController = this.mGoToTopController) == null || !seslNestedGoToTopController.isAvailable() || this.mGoToTopController.getState() == 0 || !this.mGoToTopController.contains(x9, y)))))) {
            if (!this.mHoverAreaEnter) {
                this.mHoverScrollStartTime = System.currentTimeMillis();
            }
            if (action != 7) {
                if (action == 9) {
                    this.mHoverAreaEnter = true;
                    if (y < 0 || y > this.mHoverTopAreaHeight + this.mHoverDefaultTopAreaHeight) {
                        if (y >= ((i2 - this.mHoverBottomAreaHeight) - this.mHoverDefaultBottomAreaHeight) - this.mRemainNestedScrollRange && y <= i2 && !this.mHoverHandler.hasMessages(1)) {
                            this.mHoverRecognitionStartTime = System.currentTimeMillis();
                            showPointerIcon(motionEvent, SeslPointerIconReflector.getField_SEM_TYPE_STYLUS_SCROLL_DOWN());
                            this.mHoverScrollDirection = 1;
                            this.mHoverHandler.sendEmptyMessage(1);
                        }
                    } else if (!this.mHoverHandler.hasMessages(1)) {
                        this.mHoverRecognitionStartTime = System.currentTimeMillis();
                        showPointerIcon(motionEvent, SeslPointerIconReflector.getField_SEM_TYPE_STYLUS_SCROLL_UP());
                        this.mHoverScrollDirection = 2;
                        this.mHoverHandler.sendEmptyMessage(1);
                    }
                } else if (action == 10) {
                    if (this.mHoverHandler.hasMessages(1)) {
                        this.mHoverHandler.removeMessages(1);
                    }
                    showPointerIcon(motionEvent, SeslPointerIconReflector.getField_SEM_TYPE_STYLUS_DEFAULT());
                    this.mHoverRecognitionStartTime = 0;
                    this.mHoverScrollStartTime = 0;
                    this.mIsHoverOverscrolled = false;
                    this.mHoverAreaEnter = false;
                    this.mScroller.forceFinished(true);
                    return super.dispatchHoverEvent(motionEvent);
                }
            } else if (!this.mHoverAreaEnter) {
                this.mHoverAreaEnter = true;
                motionEvent.setAction(10);
                return super.dispatchHoverEvent(motionEvent);
            } else if (y < 0 || y > this.mHoverTopAreaHeight + this.mHoverDefaultTopAreaHeight) {
                if (y >= ((i2 - this.mHoverBottomAreaHeight) - this.mHoverDefaultBottomAreaHeight) - this.mRemainNestedScrollRange && y <= i2 && !this.mHoverHandler.hasMessages(1)) {
                    this.mHoverRecognitionStartTime = System.currentTimeMillis();
                    if (!this.mIsHoverOverscrolled || this.mHoverScrollDirection == 2) {
                        showPointerIcon(motionEvent, SeslPointerIconReflector.getField_SEM_TYPE_STYLUS_SCROLL_DOWN());
                    }
                    this.mHoverScrollDirection = 1;
                    this.mHoverHandler.sendEmptyMessage(1);
                }
            } else if (!this.mHoverHandler.hasMessages(1)) {
                this.mHoverRecognitionStartTime = System.currentTimeMillis();
                if (!this.mIsHoverOverscrolled || this.mHoverScrollDirection == 1) {
                    showPointerIcon(motionEvent, SeslPointerIconReflector.getField_SEM_TYPE_STYLUS_SCROLL_UP());
                }
                this.mHoverScrollDirection = 2;
                this.mHoverHandler.sendEmptyMessage(1);
            }
            return true;
        }
        if (this.mHoverHandler.hasMessages(1)) {
            this.mHoverHandler.removeMessages(1);
            showPointerIcon(motionEvent, SeslPointerIconReflector.getField_SEM_TYPE_STYLUS_DEFAULT());
        }
        if ((y > this.mHoverTopAreaHeight + this.mHoverDefaultTopAreaHeight && y < (i2 - this.mHoverBottomAreaHeight) - this.mHoverDefaultBottomAreaHeight) || x9 <= 0 || x9 > getRight()) {
            this.mIsHoverOverscrolled = false;
        }
        if (this.mHoverAreaEnter || this.mHoverScrollStartTime != 0) {
            showPointerIcon(motionEvent, SeslPointerIconReflector.getField_SEM_TYPE_STYLUS_DEFAULT());
        }
        this.mHoverRecognitionStartTime = 0;
        this.mHoverScrollStartTime = 0;
        this.mHoverAreaEnter = false;
        return super.dispatchHoverEvent(motionEvent);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (super.dispatchKeyEvent(keyEvent) || executeKeyEvent(keyEvent)) {
            return true;
        }
        return false;
    }

    public boolean dispatchNestedFling(float f, float f5, boolean z) {
        return this.mChildHelper.dispatchNestedFling(f, f5, z);
    }

    public boolean dispatchNestedPreFling(float f, float f5) {
        SeslViewReflector.setFrameContentVelocity(this, 1.0f);
        return this.mChildHelper.dispatchNestedPreFling(f, f5);
    }

    public boolean dispatchNestedPreScroll(int i2, int i7, int[] iArr, int[] iArr2, int i8) {
        SeslNestedGoToTopController seslNestedGoToTopController = this.mGoToTopController;
        if (seslNestedGoToTopController != null) {
            seslNestedGoToTopController.invalidate();
        }
        return this.mChildHelper.dispatchNestedPreScroll(i2, i7, iArr, iArr2, i8);
    }

    public void dispatchNestedScroll(int i2, int i7, int i8, int i10, int[] iArr, int i11, int[] iArr2) {
        this.mChildHelper.dispatchNestedScroll(i2, i7, i8, i10, iArr, i11, iArr2);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int i2;
        boolean z;
        motionEvent.getX();
        int y = (int) motionEvent.getY();
        int childCount = getChildCount();
        int scrollRange = getScrollRange();
        if (this.mHoverHandler == null) {
            this.mHoverHandler = new HoverScrollHandler(this);
        }
        if (this.mHoverDefaultTopAreaHeight <= 0 || this.mHoverDefaultBottomAreaHeight <= 0) {
            this.mHoverDefaultTopAreaHeight = (int) (TypedValue.applyDimension(1, 25.0f, this.mContext.getResources().getDisplayMetrics()) + 0.5f);
            this.mHoverDefaultBottomAreaHeight = (int) (TypedValue.applyDimension(1, 25.0f, this.mContext.getResources().getDisplayMetrics()) + 0.5f);
        }
        if (childCount != 0) {
            i2 = getHeight();
        } else {
            i2 = 0;
        }
        if (motionEvent.getToolType(0) == 2) {
            z = true;
        } else {
            z = false;
        }
        int action = motionEvent.getAction();
        SeslNestedGoToTopController seslNestedGoToTopController = this.mGoToTopController;
        if (seslNestedGoToTopController != null && seslNestedGoToTopController.onTouchEvent(motionEvent)) {
            return true;
        }
        if ((y <= this.mHoverTopAreaHeight + this.mHoverDefaultTopAreaHeight || y >= (i2 - this.mHoverBottomAreaHeight) - this.mHoverDefaultBottomAreaHeight) && scrollRange != 0 && z && motionEvent.getButtonState() == 32) {
            if (!this.mHoverAreaEnter) {
                this.mHoverScrollStartTime = System.currentTimeMillis();
            }
            SeslNestedGoToTopController seslNestedGoToTopController2 = this.mGoToTopController;
            if (seslNestedGoToTopController2 != null && seslNestedGoToTopController2.onTouchPenEvent(motionEvent)) {
                return true;
            }
            if (action != MOTION_EVENT_ACTION_PEN_UP) {
                return super.dispatchTouchEvent(motionEvent);
            }
            if (this.mHoverHandler.hasMessages(1)) {
                this.mHoverHandler.removeMessages(1);
            }
            this.mHoverRecognitionStartTime = 0;
            this.mHoverScrollStartTime = 0;
            this.mIsHoverOverscrolled = false;
            this.mHoverAreaEnter = false;
            return super.dispatchTouchEvent(motionEvent);
        }
        if (this.mHoverHandler.hasMessages(1)) {
            this.mHoverHandler.removeMessages(1);
        }
        this.mHoverRecognitionStartTime = 0;
        this.mHoverScrollStartTime = 0;
        this.mHoverAreaEnter = false;
        this.mIsHoverOverscrolled = false;
        return super.dispatchTouchEvent(motionEvent);
    }

    public void draw(Canvas canvas) {
        int i2;
        super.draw(canvas);
        int scrollY = getScrollY();
        int i7 = 0;
        if (!this.mEdgeGlowTop.isFinished()) {
            int save = canvas.save();
            int width = getWidth();
            int height = getHeight();
            int min = Math.min(0, scrollY);
            if (Api21Impl.getClipToPadding(this)) {
                width -= getPaddingRight() + getPaddingLeft();
                i2 = getPaddingLeft();
            } else {
                i2 = 0;
            }
            if (Api21Impl.getClipToPadding(this)) {
                height -= getPaddingBottom() + getPaddingTop();
                min += getPaddingTop();
            }
            canvas.translate((float) i2, (float) min);
            this.mEdgeGlowTop.setSize(width, height);
            if (this.mEdgeGlowTop.draw(canvas)) {
                postInvalidateOnAnimation();
            }
            canvas.restoreToCount(save);
        }
        if (!this.mEdgeGlowBottom.isFinished()) {
            int save2 = canvas.save();
            int width2 = getWidth();
            int height2 = getHeight();
            int max = Math.max(getScrollRange(), scrollY) + height2;
            if (Api21Impl.getClipToPadding(this)) {
                width2 -= getPaddingRight() + getPaddingLeft();
                i7 = getPaddingLeft();
            }
            if (Api21Impl.getClipToPadding(this)) {
                height2 -= getPaddingBottom() + getPaddingTop();
                max -= getPaddingBottom();
            }
            canvas.translate((float) (i7 - width2), (float) max);
            canvas.rotate(180.0f, (float) width2, 0.0f);
            this.mEdgeGlowBottom.setSize(width2, height2);
            if (this.mEdgeGlowBottom.draw(canvas)) {
                postInvalidateOnAnimation();
            }
            canvas.restoreToCount(save2);
        }
        SeslNestedGoToTopController seslNestedGoToTopController = this.mGoToTopController;
        if (seslNestedGoToTopController != null) {
            seslNestedGoToTopController.draw();
        }
    }

    public boolean executeKeyEvent(KeyEvent keyEvent) {
        this.mTempRect.setEmpty();
        int i2 = 130;
        if (!canScroll()) {
            if (isFocused() && keyEvent.getKeyCode() != 4) {
                View findFocus = findFocus();
                if (findFocus == this) {
                    findFocus = null;
                }
                View findNextFocus = FocusFinder.getInstance().findNextFocus(this, findFocus, 130);
                if (findNextFocus == null || findNextFocus == this || !findNextFocus.requestFocus(130)) {
                    return false;
                }
                return true;
            }
            return false;
        }
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode != 19) {
                if (keyCode != 20) {
                    if (keyCode == 62) {
                        if (keyEvent.isShiftPressed()) {
                            i2 = 33;
                        }
                        pageScroll(i2);
                        return false;
                    }
                } else if (!keyEvent.isAltPressed()) {
                    return arrowScroll(130);
                } else {
                    return fullScroll(130);
                }
            } else if (!keyEvent.isAltPressed()) {
                return arrowScroll(33);
            } else {
                return fullScroll(33);
            }
        }
        return false;
    }

    public void fling(int i2) {
        if (getChildCount() > 0) {
            this.mScroller.fling(getScrollX(), getScrollY(), 0, i2, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
            SeslViewReflector.setFrameContentVelocity(this, Math.abs(this.mScroller.getCurrVelocity()));
            runAnimatedScroll(true);
            if (BuildCompat.isAtLeastV()) {
                Api35Impl.setFrameContentVelocity(this, Math.abs(this.mScroller.getCurrVelocity()));
            }
        }
    }

    public boolean fullScroll(int i2) {
        boolean z;
        int childCount;
        if (i2 == 130) {
            z = true;
        } else {
            z = false;
        }
        int height = getHeight();
        Rect rect = this.mTempRect;
        rect.top = 0;
        rect.bottom = height;
        if (z && (childCount = getChildCount()) > 0) {
            View childAt = getChildAt(childCount - 1);
            this.mTempRect.bottom = getPaddingBottom() + childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin;
            Rect rect2 = this.mTempRect;
            rect2.top = rect2.bottom - height;
        }
        Rect rect3 = this.mTempRect;
        return scrollAndFocus(i2, rect3.top, rect3.bottom);
    }

    public float getBottomFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        View childAt = getChildAt(0);
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int bottom = ((childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin) - getScrollY()) - (getHeight() - getPaddingBottom());
        if (bottom < verticalFadingEdgeLength) {
            return ((float) bottom) / ((float) verticalFadingEdgeLength);
        }
        return 1.0f;
    }

    public int getMaxScrollAmount() {
        return (int) (((float) getHeight()) * 0.5f);
    }

    public int getNestedScrollAxes() {
        return this.mParentHelper.getNestedScrollAxes();
    }

    public int getScrollRange() {
        if (getChildCount() <= 0) {
            return 0;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        return Math.max(0, ((childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin) - ((getHeight() - getPaddingTop()) - getPaddingBottom()));
    }

    public float getTopFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int scrollY = getScrollY();
        if (scrollY < verticalFadingEdgeLength) {
            return ((float) scrollY) / ((float) verticalFadingEdgeLength);
        }
        return 1.0f;
    }

    public float getVerticalScrollFactorCompat() {
        if (this.mVerticalScrollFactor == 0.0f) {
            TypedValue typedValue = new TypedValue();
            Context context = getContext();
            if (context.getTheme().resolveAttribute(16842829, typedValue, true)) {
                this.mVerticalScrollFactor = typedValue.getDimension(context.getResources().getDisplayMetrics());
            } else {
                throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
            }
        }
        return this.mVerticalScrollFactor;
    }

    public boolean hasNestedScrollingParent(int i2) {
        return this.mChildHelper.hasNestedScrollingParent(i2);
    }

    public boolean isFillViewport() {
        return this.mFillViewport;
    }

    public boolean isNestedScrollingEnabled() {
        return this.mChildHelper.isNestedScrollingEnabled();
    }

    public boolean isSmoothScrollingEnabled() {
        return this.mSmoothScrollingEnabled;
    }

    public void measureChild(View view, int i2, int i7) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        view.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingRight() + getPaddingLeft(), layoutParams.width), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    public void measureChildWithMargins(View view, int i2, int i7, int i8, int i10) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingRight() + getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i7, marginLayoutParams.width), View.MeasureSpec.makeMeasureSpec(marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, 0));
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mIsLaidOut = false;
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        SeslNestedGoToTopController seslNestedGoToTopController = this.mGoToTopController;
        if (seslNestedGoToTopController != null) {
            seslNestedGoToTopController.release();
        }
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mFadingEdgeHelper.isFadingEdgeEnabled()) {
            Rect calculateFadingEdgeBounds = calculateFadingEdgeBounds();
            this.mFadingEdgeHelper.prepareFadingEffect(canvas, calculateFadingEdgeBounds.left, calculateFadingEdgeBounds.top, calculateFadingEdgeBounds.right, calculateFadingEdgeBounds.bottom);
        }
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        float f;
        int i2;
        int i7;
        boolean z;
        boolean z3 = false;
        if (motionEvent.getAction() == 8 && !this.mIsBeingDragged) {
            if (MotionEventCompat.isFromSource(motionEvent, 2)) {
                i2 = 9;
                f = motionEvent.getAxisValue(9);
            } else if (MotionEventCompat.isFromSource(motionEvent, OCDHelperConstant.TEMP_TO_CHECK_OBJECT_CAPTURE)) {
                i2 = 26;
                f = motionEvent.getAxisValue(26);
            } else {
                f = 0.0f;
                i2 = 0;
            }
            if (f != 0.0f) {
                int scrollRange = getScrollRange();
                int scrollY = getScrollY();
                int verticalScrollFactorCompat = scrollY - ((int) (getVerticalScrollFactorCompat() * f));
                if (verticalScrollFactorCompat < 0) {
                    if (!canOverScroll() || MotionEventCompat.isFromSource(motionEvent, 8194)) {
                        z = false;
                    } else {
                        EdgeEffectCompat.onPullDistance(this.mEdgeGlowTop, (-((float) verticalScrollFactorCompat)) / ((float) getHeight()), 0.5f);
                        this.mEdgeGlowTop.onRelease();
                        invalidate();
                        z = true;
                    }
                    i7 = 0;
                    z3 = z;
                } else if (verticalScrollFactorCompat > scrollRange) {
                    if (canOverScroll() && !MotionEventCompat.isFromSource(motionEvent, 8194)) {
                        EdgeEffectCompat.onPullDistance(this.mEdgeGlowBottom, ((float) (verticalScrollFactorCompat - scrollRange)) / ((float) getHeight()), 0.5f);
                        this.mEdgeGlowBottom.onRelease();
                        invalidate();
                        z3 = true;
                    }
                    i7 = scrollRange;
                } else {
                    i7 = verticalScrollFactorCompat;
                }
                if (i2 != 0) {
                    this.mDifferentialMotionFlingController.onMotionEvent(motionEvent, i2);
                }
                if (i7 != scrollY) {
                    super.scrollTo(getScrollX(), i7);
                    startNestedScroll(i7, 1);
                    SeslNestedGoToTopController seslNestedGoToTopController = this.mGoToTopController;
                    if (seslNestedGoToTopController != null) {
                        seslNestedGoToTopController.showIfNeeded();
                    }
                    if (!dispatchNestedPreScroll(0, i7, (int[]) null, (int[]) null, 1)) {
                        super.scrollTo(getScrollX(), i7);
                    }
                    return true;
                }
            }
        }
        return z3;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        boolean z = true;
        if (action == 2 && this.mIsBeingDragged) {
            return true;
        }
        int i2 = action & ScoverState.TYPE_NFC_SMART_COVER;
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 == 2) {
                    int i7 = this.mActivePointerId;
                    if (i7 != -1) {
                        int findPointerIndex = motionEvent.findPointerIndex(i7);
                        if (findPointerIndex == -1) {
                            Log.e(TAG, "Invalid pointerId=" + i7 + " in onInterceptTouchEvent");
                        } else {
                            int y = (int) motionEvent.getY(findPointerIndex);
                            if (Math.abs(y - this.mLastMotionY) > this.mTouchSlop && (2 & getNestedScrollAxes()) == 0) {
                                this.mIsBeingDragged = true;
                                this.mLastMotionY = y;
                                initVelocityTrackerIfNotExists();
                                this.mVelocityTracker.addMovement(motionEvent);
                                this.mNestedYOffset = 0;
                                ViewParent parent = getParent();
                                if (parent != null) {
                                    parent.requestDisallowInterceptTouchEvent(true);
                                }
                            }
                        }
                    }
                } else if (i2 != 3) {
                    if (i2 == 6) {
                        onSecondaryPointerUp(motionEvent);
                    }
                }
            }
            this.mIsBeingDragged = false;
            this.mActivePointerId = -1;
            recycleVelocityTracker();
            if (this.mScroller.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                postInvalidateOnAnimation();
            }
            stopNestedScroll(0);
        } else {
            int y8 = (int) motionEvent.getY();
            if (!inChild((int) motionEvent.getX(), y8)) {
                if (!stopGlowAnimations(motionEvent) && this.mScroller.isFinished()) {
                    z = false;
                }
                this.mIsBeingDragged = z;
                recycleVelocityTracker();
            } else {
                this.mLastMotionY = y8;
                this.mActivePointerId = motionEvent.getPointerId(0);
                initOrResetVelocityTracker();
                this.mVelocityTracker.addMovement(motionEvent);
                this.mScroller.computeScrollOffset();
                SeslViewReflector.setFrameContentVelocity(this, Math.abs(this.mScroller.getCurrVelocity()));
                if (!stopGlowAnimations(motionEvent) && this.mScroller.isFinished()) {
                    z = false;
                }
                this.mIsBeingDragged = z;
                startNestedScroll(2, 0);
            }
        }
        return this.mIsBeingDragged;
    }

    public void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        int i11;
        SeslNestedGoToTopController seslNestedGoToTopController;
        super.onLayout(z, i2, i7, i8, i10);
        this.mIsLayoutDirty = false;
        View view = this.mChildToScrollTo;
        if (view != null && isViewDescendantOf(view, this)) {
            scrollToChild(this.mChildToScrollTo);
        }
        this.mChildToScrollTo = null;
        if (z && (seslNestedGoToTopController = this.mGoToTopController) != null) {
            seslNestedGoToTopController.setSizeChanged(true);
            this.mGoToTopController.setOverlayFeatureHiddenHeightPx(getResources().getDimensionPixelOffset(R$dimen.sesl_nestedscrollview_overlay_feature_hidden_height));
            this.mGoToTopController.onSizeChanged();
        }
        if (!this.mIsLaidOut) {
            if (this.mSavedState != null) {
                scrollTo(getScrollX(), this.mSavedState.scrollPosition);
                this.mSavedState = null;
            }
            if (getChildCount() > 0) {
                View childAt = getChildAt(0);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                i11 = childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            } else {
                i11 = 0;
            }
            int paddingTop = ((i10 - i7) - getPaddingTop()) - getPaddingBottom();
            int scrollY = getScrollY();
            int clamp = clamp(scrollY, paddingTop, i11);
            if (clamp != scrollY) {
                scrollTo(getScrollX(), clamp);
            }
        }
        scrollTo(getScrollX(), getScrollY());
        this.mIsLaidOut = true;
        if (z && computeHorizontalScrollRange() <= computeHorizontalScrollExtent()) {
            this.mHasNestedScrollRange = false;
            ViewParent parent = getParent();
            while (true) {
                if (parent == null || !(parent instanceof ViewGroup)) {
                    break;
                } else if (!(parent instanceof NestedScrollingParent2) || !findSuperClass(parent, "CoordinatorLayout")) {
                    parent = parent.getParent();
                } else {
                    ViewGroup viewGroup = (ViewGroup) parent;
                    viewGroup.getLocationInWindow(this.mWindowOffsets);
                    int height = viewGroup.getHeight() + this.mWindowOffsets[1];
                    getLocationInWindow(this.mWindowOffsets);
                    this.mInitialTopOffsetOfScreen = this.mWindowOffsets[1];
                    int height2 = getHeight() - (height - this.mInitialTopOffsetOfScreen);
                    this.mRemainNestedScrollRange = height2;
                    if (height2 < 0) {
                        this.mRemainNestedScrollRange = 0;
                    }
                    this.mNestedScrollRange = this.mRemainNestedScrollRange;
                    this.mHasNestedScrollRange = true;
                }
            }
            if (!this.mHasNestedScrollRange) {
                this.mInitialTopOffsetOfScreen = 0;
                this.mRemainNestedScrollRange = 0;
                this.mNestedScrollRange = 0;
            }
        }
    }

    public void onMeasure(int i2, int i7) {
        super.onMeasure(i2, i7);
        if (this.mFillViewport && View.MeasureSpec.getMode(i7) != 0 && getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int measuredHeight2 = (((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) - layoutParams.topMargin) - layoutParams.bottomMargin;
            if (measuredHeight < measuredHeight2) {
                childAt.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingRight() + getPaddingLeft() + layoutParams.leftMargin + layoutParams.rightMargin, layoutParams.width), View.MeasureSpec.makeMeasureSpec(measuredHeight2, 1073741824));
            }
        }
    }

    public boolean onNestedFling(View view, float f, float f5, boolean z) {
        if (z) {
            return false;
        }
        dispatchNestedFling(0.0f, f5, true);
        fling((int) f5);
        return true;
    }

    public boolean onNestedPreFling(View view, float f, float f5) {
        return dispatchNestedPreFling(f, f5);
    }

    public void onNestedPreScroll(View view, int i2, int i7, int[] iArr, int i8) {
        dispatchNestedPreScroll(i2, i7, iArr, (int[]) null, i8);
    }

    public void onNestedScroll(View view, int i2, int i7, int i8, int i10, int i11, int[] iArr) {
        onNestedScrollInternal(i10, i11, iArr);
    }

    public void onNestedScrollAccepted(View view, View view2, int i2, int i7) {
        this.mParentHelper.onNestedScrollAccepted(view, view2, i2, i7);
        startNestedScroll(2, i7);
    }

    public void onOverScrolled(int i2, int i7, boolean z, boolean z3) {
        SeslNestedGoToTopController seslNestedGoToTopController = this.mGoToTopController;
        if (seslNestedGoToTopController != null) {
            seslNestedGoToTopController.showIfNeeded();
        }
        super.scrollTo(i2, i7);
    }

    public boolean onRequestFocusInDescendants(int i2, Rect rect) {
        View view;
        if (i2 == 2) {
            i2 = 130;
        } else if (i2 == 1) {
            i2 = 33;
        }
        if (rect == null) {
            view = FocusFinder.getInstance().findNextFocus(this, (View) null, i2);
        } else {
            view = FocusFinder.getInstance().findNextFocusFromRect(this, rect, i2);
        }
        if (view != null && !isOffScreen(view)) {
            return view.requestFocus(i2, rect);
        }
        return false;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mSavedState = savedState;
        requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.scrollPosition = getScrollY();
        return savedState;
    }

    public void onScrollChanged(int i2, int i7, int i8, int i10) {
        int i11;
        int i12;
        int i13;
        int i14;
        NestedScrollView nestedScrollView;
        SeslNestedGoToTopController seslNestedGoToTopController;
        super.onScrollChanged(i2, i7, i8, i10);
        if (!(!canOverScroll() || i7 == i10 || (seslNestedGoToTopController = this.mGoToTopController) == null)) {
            seslNestedGoToTopController.showIfNeeded();
        }
        OnScrollChangeListener onScrollChangeListener = this.mOnScrollChangeListener;
        if (onScrollChangeListener != null) {
            nestedScrollView = this;
            i14 = i2;
            i13 = i7;
            i12 = i8;
            i11 = i10;
            ((C0271a) onScrollChangeListener).a(nestedScrollView, i14, i13, i12, i11);
        } else {
            nestedScrollView = this;
            i14 = i2;
            i13 = i7;
            i12 = i8;
            i11 = i10;
        }
        List<OnScrollChangeListener> list = nestedScrollView.mOnScrollChangeListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                int i15 = i11;
                int i16 = i12;
                int i17 = i13;
                int i18 = i14;
                NestedScrollView nestedScrollView2 = nestedScrollView;
                ((C0271a) nestedScrollView.mOnScrollChangeListeners.get(size)).a(nestedScrollView2, i18, i17, i16, i15);
                nestedScrollView = nestedScrollView2;
                i14 = i18;
                i13 = i17;
                i12 = i16;
                i11 = i15;
            }
        }
    }

    public void onSizeChanged(int i2, int i7, int i8, int i10) {
        super.onSizeChanged(i2, i7, i8, i10);
        View findFocus = findFocus();
        if (findFocus != null && this != findFocus && isWithinDeltaOfScreen(findFocus, 0, i10)) {
            findFocus.getDrawingRect(this.mTempRect);
            offsetDescendantRectToMyCoords(findFocus, this.mTempRect);
            doScrollY(computeScrollDeltaToGetChildRectOnScreen(this.mTempRect));
        }
    }

    public boolean onStartNestedScroll(View view, View view2, int i2, int i7) {
        return (i2 & 2) != 0;
    }

    public void onStopNestedScroll(View view, int i2) {
        this.mParentHelper.onStopNestedScroll(view, i2);
        stopNestedScroll(i2);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        ViewParent parent;
        boolean z;
        boolean z3;
        VelocityTracker velocityTracker;
        MotionEvent motionEvent2 = motionEvent;
        initVelocityTrackerIfNotExists();
        int actionMasked = motionEvent2.getActionMasked();
        boolean z7 = false;
        if (actionMasked == 0) {
            this.mNestedYOffset = 0;
        }
        MotionEvent obtain = MotionEvent.obtain(motionEvent2);
        obtain.offsetLocation(0.0f, (float) this.mNestedYOffset);
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                VelocityTracker velocityTracker2 = this.mVelocityTracker;
                velocityTracker2.computeCurrentVelocity(1000, (float) this.mMaximumVelocity);
                int yVelocity = (int) velocityTracker2.getYVelocity(this.mActivePointerId);
                if (Math.abs(yVelocity) >= this.mMinimumVelocity) {
                    if (!edgeEffectFling(yVelocity)) {
                        int i2 = -yVelocity;
                        float f = (float) i2;
                        if (!dispatchNestedPreFling(0.0f, f)) {
                            dispatchNestedFling(0.0f, f, true);
                            fling(i2);
                        }
                    }
                } else if (this.mScroller.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                    postInvalidateOnAnimation();
                }
                this.mActivePointerId = -1;
                endDrag();
            } else if (actionMasked == 2) {
                int findPointerIndex = motionEvent2.findPointerIndex(this.mActivePointerId);
                if (findPointerIndex == -1) {
                    Log.e(TAG, "Invalid pointerId=" + this.mActivePointerId + " in onTouchEvent");
                } else {
                    int y = (int) motionEvent2.getY(findPointerIndex);
                    int i7 = this.mLastMotionY - y;
                    int releaseVerticalGlow = i7 - releaseVerticalGlow(i7, motionEvent2.getX(findPointerIndex));
                    if (!this.mIsBeingDragged && Math.abs(releaseVerticalGlow) > this.mTouchSlop) {
                        ViewParent parent2 = getParent();
                        if (parent2 != null) {
                            parent2.requestDisallowInterceptTouchEvent(true);
                        }
                        this.mIsBeingDragged = true;
                        releaseVerticalGlow = releaseVerticalGlow > 0 ? releaseVerticalGlow - this.mTouchSlop : releaseVerticalGlow + this.mTouchSlop;
                    }
                    int i8 = releaseVerticalGlow;
                    if (this.mIsBeingDragged) {
                        if (dispatchNestedPreScroll(0, i8, this.mScrollConsumed, this.mScrollOffset, 0)) {
                            i8 -= this.mScrollConsumed[1];
                            this.mNestedYOffset += this.mScrollOffset[1];
                        }
                        this.mLastMotionY = y - this.mScrollOffset[1];
                        int scrollY = getScrollY();
                        int scrollRange = getScrollRange();
                        int overScrollMode = getOverScrollMode();
                        if (overScrollMode == 0 || (overScrollMode == 1 && scrollRange > 0)) {
                            z = true;
                        } else {
                            z = false;
                        }
                        int i10 = i8;
                        int i11 = scrollRange;
                        if (!overScrollByCompat(0, i8, 0, getScrollY(), 0, scrollRange, 0, 0, true) || hasNestedScrollingParent(0)) {
                            z3 = false;
                        } else {
                            z3 = true;
                        }
                        int scrollY2 = getScrollY() - scrollY;
                        int[] iArr = this.mScrollConsumed;
                        iArr[1] = 0;
                        dispatchNestedScroll(0, scrollY2, 0, i10 - scrollY2, this.mScrollOffset, 0, iArr);
                        int i12 = this.mLastMotionY;
                        int i13 = this.mScrollOffset[1];
                        this.mLastMotionY = i12 - i13;
                        this.mNestedYOffset += i13;
                        if (z) {
                            int i14 = i10 - this.mScrollConsumed[1];
                            int i15 = scrollY + i14;
                            if (i15 < 0) {
                                EdgeEffectCompat.onPullDistance(this.mEdgeGlowTop, ((float) (-i14)) / ((float) getHeight()), motionEvent2.getX(findPointerIndex) / ((float) getWidth()));
                                if (!this.mEdgeGlowBottom.isFinished()) {
                                    this.mEdgeGlowBottom.onRelease();
                                }
                            } else if (i15 > i11) {
                                EdgeEffectCompat.onPullDistance(this.mEdgeGlowBottom, ((float) i14) / ((float) getHeight()), 1.0f - (motionEvent2.getX(findPointerIndex) / ((float) getWidth())));
                                SeslNestedGoToTopController seslNestedGoToTopController = this.mGoToTopController;
                                if (seslNestedGoToTopController != null) {
                                    seslNestedGoToTopController.showIfNeeded();
                                }
                                if (!this.mEdgeGlowTop.isFinished()) {
                                    this.mEdgeGlowTop.onRelease();
                                }
                            }
                            if (!this.mEdgeGlowTop.isFinished() || !this.mEdgeGlowBottom.isFinished()) {
                                postInvalidateOnAnimation();
                                if (z7 && (velocityTracker = this.mVelocityTracker) != null) {
                                    velocityTracker.clear();
                                }
                            }
                        }
                        z7 = z3;
                        velocityTracker.clear();
                    }
                }
            } else if (actionMasked == 3) {
                if (this.mIsBeingDragged && getChildCount() > 0 && this.mScroller.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                    postInvalidateOnAnimation();
                }
                this.mActivePointerId = -1;
                endDrag();
            } else if (actionMasked == 5) {
                int actionIndex = motionEvent2.getActionIndex();
                this.mLastMotionY = (int) motionEvent2.getY(actionIndex);
                this.mActivePointerId = motionEvent2.getPointerId(actionIndex);
            } else if (actionMasked == 6) {
                onSecondaryPointerUp(motionEvent);
                this.mLastMotionY = (int) motionEvent2.getY(motionEvent2.findPointerIndex(this.mActivePointerId));
            }
        } else if (getChildCount() == 0) {
            obtain.recycle();
            return false;
        } else {
            if (this.mIsBeingDragged && (parent = getParent()) != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
            if (!this.mScroller.isFinished()) {
                abortAnimatedScroll();
            }
            this.mLastMotionY = (int) motionEvent2.getY();
            this.mActivePointerId = motionEvent2.getPointerId(0);
            startNestedScroll(2, 0);
        }
        VelocityTracker velocityTracker3 = this.mVelocityTracker;
        if (velocityTracker3 != null) {
            velocityTracker3.addMovement(obtain);
        }
        obtain.recycle();
        return true;
    }

    public boolean overScrollByCompat(int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14, boolean z) {
        boolean z3;
        boolean z7;
        boolean z9;
        boolean z10;
        int i15;
        int i16;
        boolean z11;
        boolean z12;
        int i17;
        int overScrollMode = getOverScrollMode();
        if (computeHorizontalScrollRange() > computeHorizontalScrollExtent()) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (computeVerticalScrollRange() > computeVerticalScrollExtent()) {
            z7 = true;
        } else {
            z7 = false;
        }
        if (overScrollMode == 0 || (overScrollMode == 1 && z3)) {
            z9 = true;
        } else {
            z9 = false;
        }
        if (overScrollMode == 0 || (overScrollMode == 1 && z7)) {
            z10 = true;
        } else {
            z10 = false;
        }
        int i18 = i8 + i2;
        if (!z9) {
            i15 = 0;
        } else {
            i15 = i13;
        }
        int i19 = i10 + i7;
        if (!z10) {
            i16 = 0;
        } else {
            i16 = i14;
        }
        int i20 = -i15;
        int i21 = i15 + i11;
        int i22 = -i16;
        int i23 = i16 + i12;
        if (i18 > i21) {
            i18 = i21;
            z11 = true;
        } else if (i18 < i20) {
            z11 = true;
            i18 = i20;
        } else {
            z11 = false;
        }
        if (i19 > i23) {
            i19 = i23;
            z12 = true;
        } else if (i19 < i22) {
            z12 = true;
            i19 = i22;
        } else {
            z12 = false;
        }
        if (!z12 || hasNestedScrollingParent(1)) {
            i17 = i18;
        } else {
            int i24 = i18;
            this.mScroller.springBack(i24, i19, 0, 0, 0, getScrollRange());
            i17 = i24;
        }
        onOverScrolled(i17, i19, z11, z12);
        if (z11 || z12) {
            return true;
        }
        return false;
    }

    public boolean pageScroll(int i2) {
        boolean z;
        if (i2 == 130) {
            z = true;
        } else {
            z = false;
        }
        int height = getHeight();
        if (z) {
            this.mTempRect.top = getScrollY() + height;
            int childCount = getChildCount();
            if (childCount > 0) {
                View childAt = getChildAt(childCount - 1);
                int paddingBottom = getPaddingBottom() + childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin;
                Rect rect = this.mTempRect;
                if (rect.top + height > paddingBottom) {
                    rect.top = paddingBottom - height;
                }
            }
        } else {
            this.mTempRect.top = getScrollY() - height;
            Rect rect2 = this.mTempRect;
            if (rect2.top < 0) {
                rect2.top = 0;
            }
        }
        Rect rect3 = this.mTempRect;
        int i7 = rect3.top;
        int i8 = height + i7;
        rect3.bottom = i8;
        return scrollAndFocus(i2, i7, i8);
    }

    public void removeOnScrollChangeListener(OnScrollChangeListener onScrollChangeListener) {
        List<OnScrollChangeListener> list = this.mOnScrollChangeListeners;
        if (list != null) {
            list.remove(onScrollChangeListener);
        }
    }

    public void requestChildFocus(View view, View view2) {
        if (!this.mIsLayoutDirty) {
            scrollToChild(view2);
        } else {
            this.mChildToScrollTo = view2;
        }
        super.requestChildFocus(view, view2);
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        rect.offset(view.getLeft() - view.getScrollX(), view.getTop() - view.getScrollY());
        return scrollToChildRect(rect, z);
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (z) {
            recycleVelocityTracker();
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    public void requestLayout() {
        this.mIsLayoutDirty = true;
        super.requestLayout();
    }

    public void scrollTo(int i2, int i7) {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int clamp = clamp(i2, (getWidth() - getPaddingLeft()) - getPaddingRight(), childAt.getWidth() + layoutParams.leftMargin + layoutParams.rightMargin);
            int clamp2 = clamp(i7, (getHeight() - getPaddingTop()) - getPaddingBottom(), childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin);
            if (clamp != getScrollX() || clamp2 != getScrollY()) {
                super.scrollTo(clamp, clamp2);
            }
        }
    }

    public void seslAddOnGoToTopClickListener(SeslScrollable.SeslOnGoToTopClickListener seslOnGoToTopClickListener) {
        if (this.mOnGoToTopClickListeners == null) {
            this.mOnGoToTopClickListeners = new ArrayList();
        }
        this.mOnGoToTopClickListeners.add(seslOnGoToTopClickListener);
    }

    public void seslClearOnGoToTopClickListener() {
        List<SeslScrollable.SeslOnGoToTopClickListener> list = this.mOnGoToTopClickListeners;
        if (list != null) {
            list.clear();
        }
    }

    public void seslForceBottomFadingEdgeClamped(int i2) {
        this.mFadingEdgeHelper.forceBottomFadingEdgeClamped(i2);
    }

    public void seslForceTopFadingEdgeClamped(int i2) {
        this.mFadingEdgeHelper.forceTopFadingEdgeClamped(i2);
    }

    public Rect seslGetAvailableBounds() {
        return this.mAvailableBounds;
    }

    public int seslGetBottomScrollOffset() {
        return this.mFadingEdgeHelper.getFadingEdgeBottomOffset();
    }

    public int seslGetGoToTopBottomPadding() {
        SeslNestedGoToTopController seslNestedGoToTopController = this.mGoToTopController;
        if (seslNestedGoToTopController != null) {
            return seslNestedGoToTopController.getBottomPadding();
        }
        return 0;
    }

    public int seslGetGoToTopDefaultBottomPadding() {
        SeslNestedGoToTopController seslNestedGoToTopController = this.mGoToTopController;
        if (seslNestedGoToTopController != null) {
            return seslNestedGoToTopController.getDefaultBottomPadding();
        }
        return 0;
    }

    public int seslGetHoverBottomPadding() {
        return this.mHoverBottomAreaHeight;
    }

    public int seslGetHoverDefaultBottomPadding() {
        return this.mHoverDefaultBottomAreaHeight;
    }

    public int seslGetHoverDefaultTopPadding() {
        return this.mHoverDefaultTopAreaHeight;
    }

    public int seslGetHoverTopPadding() {
        return this.mHoverTopAreaHeight;
    }

    public int seslGetScrollBarBottomOffset() {
        return this.mScrollBarBottomOffset;
    }

    public int seslGetScrollBarTopOffset() {
        return this.mScrollBarTopOffset;
    }

    public void seslHideBottomFadingEdge(boolean z) {
        this.mFadingEdgeHelper.hideBottomFadingEdge(z);
    }

    public void seslHideTopFadingEdge(boolean z) {
        this.mFadingEdgeHelper.hideTopFadingEdge(z);
    }

    public void seslRemoveOnGoToTopClickListener(SeslScrollable.SeslOnGoToTopClickListener seslOnGoToTopClickListener) {
        List<SeslScrollable.SeslOnGoToTopClickListener> list = this.mOnGoToTopClickListeners;
        if (list != null) {
            list.remove(seslOnGoToTopClickListener);
        }
    }

    public void seslSetAllowTopFadingEdgeWithoutEdgeToEdge(boolean z) {
        this.mFadingEdgeHelper.setAllowTopFadingEdgeWithoutEdgeToEdge(z);
    }

    public void seslSetAvailableBounds(Rect rect) {
        this.mAvailableBounds = rect;
    }

    public void seslSetBottomScrollOffset(int i2) {
        if (this.mFadingEdgeHelper.getFadingEdgeBottomOffset() != i2) {
            this.mFadingEdgeHelper.setFadingEdgeBottomOffset(i2);
            invalidate();
        }
    }

    public void seslSetFadingEdgeColor(int i2) {
        this.mFadingEdgeHelper.setFadingEdgeColor(i2, new b(26, this));
        invalidate();
    }

    public void seslSetFadingEdgeEnabled(boolean z, int i2, int i7) {
        boolean z3 = z;
        applyFadingEdge(z3, new a(this, z3, i2, i7, 1));
    }

    public void seslSetFadingEdgeWindowBottomAlignment(boolean z) {
        this.mFadingEdgeHelper.setWindowBottomAlignment(z);
    }

    public void seslSetFillHorizontalPaddingEnabled(boolean z, int i2) {
        int i7;
        this.mDrawHorizontalPadding = z;
        if (z) {
            i7 = getResources().getDimensionPixelOffset(R$dimen.sesl_nestedscrollview_system_scroller_vertical_padding);
        } else {
            i7 = 0;
        }
        this.mScrollbarTopPadding = i7;
        this.mScrollbarBottomPadding = i7;
        updateScrollbarVerticalPadding();
        this.mRectPaint.setColor(i2);
    }

    public void seslSetFloatingBottomLayoutHeight(int i2) {
        this.mSeslBottomBarHeight = i2;
    }

    public void seslSetGoToTopBlurEnabled(boolean z) {
        SeslNestedGoToTopController seslNestedGoToTopController = this.mGoToTopController;
        if (seslNestedGoToTopController != null) {
            seslNestedGoToTopController.setBlurEnabled(z, isLightTheme(this.mContext));
        }
    }

    public void seslSetGoToTopBottomPadding(int i2) {
        SeslNestedGoToTopController seslNestedGoToTopController = this.mGoToTopController;
        if (seslNestedGoToTopController != null) {
            seslNestedGoToTopController.setBottomPadding(i2);
        }
    }

    public void seslSetGoToTopEnabled(boolean z) {
        seslSetGoToTopEnabled(z, isLightTheme(this.mContext));
    }

    public void seslSetHoverBottomPadding(int i2) {
        int max = Math.max(0, i2);
        if (this.mHoverBottomAreaHeight != max) {
            this.mHoverBottomAreaHeight = max;
        }
    }

    public void seslSetHoverScrollEnabled(boolean z) {
        this.mHoverScrollEnabled = z;
    }

    public void seslSetHoverTopPadding(int i2) {
        int max = Math.max(0, i2);
        if (this.mHoverTopAreaHeight != max) {
            this.mHoverTopAreaHeight = max;
        }
    }

    public void seslSetScrollBarBottomOffset(int i2) {
        int i7 = i2 - this.mScrollBarTopOffset;
        if (this.mScrollBarBottomOffset != i7) {
            this.mScrollBarBottomOffset = Math.max(i7, 0);
            updateScrollbarVerticalPadding();
        }
    }

    public /* bridge */ /* synthetic */ void seslSetScrollBarOffsetChangedListener(SeslScrollable.SeslScrollBarOffsetChangedListener seslScrollBarOffsetChangedListener) {
        super.seslSetScrollBarOffsetChangedListener(seslScrollBarOffsetChangedListener);
    }

    public void seslSetScrollBarTopOffset(int i2) {
        if (this.mScrollBarTopOffset != i2) {
            this.mScrollBarTopOffset = Math.max(i2, 0);
            updateScrollbarVerticalPadding();
        }
    }

    public void seslSetScrollbarVerticalPadding(int i2, int i7) {
        this.mScrollbarTopPadding = i2;
        this.mScrollbarBottomPadding = i7;
        updateScrollbarVerticalPadding();
    }

    public void seslSmoothScrollToWithNestedScrolling(int i2, int i7) {
        smoothScrollTo(i2, i7, true);
    }

    public void setFillViewport(boolean z) {
        if (z != this.mFillViewport) {
            this.mFillViewport = z;
            requestLayout();
        }
    }

    public void setNestedScrollingEnabled(boolean z) {
        this.mChildHelper.setNestedScrollingEnabled(z);
    }

    public void setOnScrollChangeListener(OnScrollChangeListener onScrollChangeListener) {
        this.mOnScrollChangeListener = onScrollChangeListener;
    }

    public void setSmoothScrollingEnabled(boolean z) {
        this.mSmoothScrollingEnabled = z;
    }

    public boolean shouldDelayChildPressedState() {
        return true;
    }

    public final void smoothScrollBy(int i2, int i7) {
        smoothScrollBy(i2, i7, 250, false);
    }

    public final void smoothScrollTo(int i2, int i7) {
        smoothScrollTo(i2, i7, 250, false);
    }

    public boolean startNestedScroll(int i2, int i7) {
        return this.mChildHelper.startNestedScroll(i2, i7);
    }

    public void stopNestedScroll(int i2) {
        this.mChildHelper.stopNestedScroll(i2);
    }

    public NestedScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.nestedScrollViewStyle);
    }

    public boolean dispatchNestedScroll(int i2, int i7, int i8, int i10, int[] iArr, int i11) {
        return this.mChildHelper.dispatchNestedScroll(i2, i7, i8, i10, iArr, i11);
    }

    public boolean hasNestedScrollingParent() {
        return hasNestedScrollingParent(0);
    }

    public void onNestedPreScroll(View view, int i2, int i7, int[] iArr) {
        onNestedPreScroll(view, i2, i7, iArr, 0);
    }

    public void onNestedScroll(View view, int i2, int i7, int i8, int i10, int i11) {
        onNestedScrollInternal(i10, i11, (int[]) null);
    }

    public boolean onStartNestedScroll(View view, View view2, int i2) {
        return onStartNestedScroll(view, view2, i2, 0);
    }

    public void seslSetFadingEdgeEnabled(boolean z) {
        applyFadingEdge(z, new c((Object) this, z, 17));
    }

    public void seslSetGoToTopEnabled(boolean z, boolean z3) {
        ensureGoToTopController();
        post(this.mCheckGoToTopAndAutoScrollCondition);
        SeslNestedGoToTopController seslNestedGoToTopController = this.mGoToTopController;
        if (seslNestedGoToTopController != null) {
            seslNestedGoToTopController.setEnabled(z, z3);
            if (z) {
                this.mGoToTopController.setOnGoToTopClickListener(new g(3, this));
            } else {
                this.mGoToTopController.setOnGoToTopClickListener((SeslGoToTopController.OnGoToTopClickListener) null);
            }
        }
    }

    public final void smoothScrollBy(int i2, int i7, int i8) {
        smoothScrollBy(i2, i7, i8, false);
    }

    public final void smoothScrollTo(int i2, int i7, int i8) {
        smoothScrollTo(i2, i7, i8, false);
    }

    public boolean startNestedScroll(int i2) {
        return startNestedScroll(i2, 0);
    }

    public void stopNestedScroll() {
        stopNestedScroll(0);
    }

    public NestedScrollView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mTempRect = new Rect();
        this.mIsLayoutDirty = true;
        this.mIsLaidOut = false;
        this.mChildToScrollTo = null;
        this.mIsBeingDragged = false;
        this.mSmoothScrollingEnabled = true;
        this.mActivePointerId = -1;
        this.mScrollOffset = new int[2];
        this.mScrollConsumed = new int[2];
        this.mHoverScrollSpeed = 0;
        this.mGoToTopHost = new SeslGoToTopController.Host() {
            public boolean canScrollDown() {
                return NestedScrollView.this.canScrollDown();
            }

            public boolean canScrollUp() {
                return NestedScrollView.this.canScrollUp();
            }

            public Context getContext() {
                return NestedScrollView.this.mContext;
            }

            public int getHeight() {
                return NestedScrollView.this.getHeight();
            }

            public void getLocationInWindow(int[] iArr) {
                NestedScrollView.this.getLocationInWindow(iArr);
            }

            public ViewGroupOverlay getOverlay() {
                return NestedScrollView.this.getOverlay();
            }

            public int getPaddingLeft() {
                return NestedScrollView.this.getPaddingLeft();
            }

            public int getPaddingRight() {
                return NestedScrollView.this.getPaddingRight();
            }

            public int getScrollY() {
                return NestedScrollView.this.getScrollY();
            }

            public int getWidth() {
                return NestedScrollView.this.getWidth();
            }

            public boolean isFastScrollerEnabled() {
                return false;
            }

            public void playSoundEffect(int i2) {
                NestedScrollView.this.playSoundEffect(i2);
            }

            public void post(Runnable runnable) {
                NestedScrollView.this.post(runnable);
            }

            public void postDelayed(Runnable runnable, long j2) {
                NestedScrollView.this.postDelayed(runnable, j2);
            }

            public void removeCallbacks(Runnable runnable) {
                NestedScrollView.this.removeCallbacks(runnable);
            }

            public void showTopEdgeEffect() {
                NestedScrollView.this.mEdgeGlowTop.onAbsorb(10000);
                NestedScrollView.this.invalidate();
            }

            public void smoothScrollToTop() {
                post(new Runnable() {
                    public void run() {
                        if (NestedScrollView.this.mGoToTopController != null) {
                            NestedScrollView.this.mGoToTopController.setScrollRunning(true);
                            if (Settings.System.getInt(AnonymousClass1.this.getContext().getContentResolver(), "remove_animations", 0) == 1) {
                                NestedScrollView.this.scrollTo(0, 0);
                                return;
                            }
                            int scrollToTopDurationMs = NestedScrollView.this.mGoToTopController.getScrollToTopDurationMs();
                            if (NestedScrollView.this.mAvailableBounds != null) {
                                NestedScrollView.this.smoothScrollTo(0, 0, scrollToTopDurationMs, true);
                            } else {
                                NestedScrollView.this.smoothScrollTo(0, 0, scrollToTopDurationMs);
                            }
                        }
                    }
                });
            }
        };
        this.SINE_IN_OUT_70 = new PathInterpolator(0.33f, 0.0f, 0.3f, 1.0f);
        this.mDrawHorizontalPadding = false;
        Paint paint = new Paint();
        this.mRectPaint = paint;
        this.mScrollbarTopPadding = 0;
        this.mScrollbarBottomPadding = 0;
        this.mIsSupportHoverScroll = false;
        this.mHoverScrollEnabled = true;
        this.mHoverScrollStateChanged = false;
        this.mHoverAreaEnter = false;
        this.mNeedsHoverScroll = false;
        this.mHoverTopAreaHeight = 0;
        this.mHoverBottomAreaHeight = 0;
        this.mHoverDefaultTopAreaHeight = 0;
        this.mHoverDefaultBottomAreaHeight = 0;
        this.mHoverScrollDirection = -1;
        this.mHoverRecognitionDurationTime = 0;
        this.mHoverRecognitionCurrentTime = 0;
        this.mHoverRecognitionStartTime = 0;
        this.mHoverScrollTimeInterval = 300;
        this.mHoverScrollStartTime = 0;
        this.mIsHoverOverscrolled = false;
        this.mInitialTopOffsetOfScreen = 0;
        this.mHasNestedScrollRange = false;
        this.mWindowOffsets = new int[2];
        this.mRemainNestedScrollRange = 0;
        this.mNestedScrollRange = 0;
        this.mScrollBarTopOffset = 0;
        this.mScrollBarBottomOffset = 0;
        this.mNaviBarTop = -1;
        this.mScrollInfoProvider = new SeslFadingEdgeHelper.ScrollInfoProvider() {
            public int computeVerticalScrollExtent() {
                return NestedScrollView.this.computeVerticalScrollExtent();
            }

            public int computeVerticalScrollOffset() {
                return NestedScrollView.this.computeVerticalScrollOffset();
            }

            public int computeVerticalScrollRange() {
                return NestedScrollView.this.computeVerticalScrollRange();
            }

            public int getLastItemHeightIfVisible() {
                return -1;
            }

            public boolean shouldNormalizeFadingEdge() {
                return false;
            }
        };
        DifferentialMotionFlingTargetImpl differentialMotionFlingTargetImpl = new DifferentialMotionFlingTargetImpl();
        this.mDifferentialMotionFlingTarget = differentialMotionFlingTargetImpl;
        this.mDifferentialMotionFlingController = new DifferentialMotionFlingController(getContext(), differentialMotionFlingTargetImpl);
        this.mAvailableBounds = null;
        this.mDebugDrawAvailRect = false;
        this.mSeslBottomBarHeight = 0;
        this.mOnLayoutChangeListener = new View.OnLayoutChangeListener() {
            public void onLayoutChange(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
                NestedScrollView nestedScrollView = NestedScrollView.this;
                nestedScrollView.post(nestedScrollView.mCheckGoToTopAndAutoScrollCondition);
            }
        };
        this.mCheckGoToTopAndAutoScrollCondition = new Runnable() {
            public void run() {
                if ((NestedScrollView.this.mGoToTopController != null && NestedScrollView.this.mGoToTopController.isEnabled()) || NestedScrollView.this.mHoverScrollEnabled) {
                    boolean access$900 = NestedScrollView.this.checkChildScrollableForGoToTopAndAutoScroll();
                    boolean unused = NestedScrollView.this.mIsSupportHoverScroll = access$900;
                    if (NestedScrollView.this.mGoToTopController != null) {
                        NestedScrollView.this.mGoToTopController.setSupportGoToTop(access$900);
                    }
                }
            }
        };
        this.mContext = context;
        this.mEdgeGlowTop = EdgeEffectCompat.create(context, attributeSet);
        this.mEdgeGlowBottom = EdgeEffectCompat.create(context, attributeSet);
        this.mPhysicalCoeff = context.getResources().getDisplayMetrics().density * 160.0f * 386.0878f * 0.84f;
        initScrollView();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, SCROLLVIEW_STYLEABLE, i2, 0);
        setFillViewport(obtainStyledAttributes.getBoolean(0, false));
        obtainStyledAttributes.recycle();
        this.mParentHelper = new NestedScrollingParentHelper(this);
        this.mChildHelper = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
        ViewCompat.setAccessibilityDelegate(this, ACCESSIBILITY_DELEGATE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.mFadingEdgeHelper = new SeslFadingEdgeHelper(this.mContext);
    }

    private void smoothScrollBy(int i2, int i7, int i8, boolean z) {
        if (getChildCount() != 0) {
            if (AnimationUtils.currentAnimationTimeMillis() - this.mLastScroll > 250) {
                boolean isFloatingGoToTopScrollRequest = isFloatingGoToTopScrollRequest(i7);
                View childAt = getChildAt(0);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                int scrollY = getScrollY();
                int max = Math.max(0, Math.min(i7 + scrollY, Math.max(0, ((childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin) - ((getHeight() - getPaddingTop()) - getPaddingBottom())))) - scrollY;
                if (isFloatingGoToTopScrollRequest) {
                    max -= this.mAvailableBounds.top;
                }
                this.mScroller.startScroll(getScrollX(), scrollY, 0, max, i8);
                runAnimatedScroll(z);
            } else {
                if (!this.mScroller.isFinished()) {
                    abortAnimatedScroll();
                }
                scrollBy(i2, i7);
            }
            this.mLastScroll = AnimationUtils.currentAnimationTimeMillis();
        }
    }

    public boolean dispatchNestedScroll(int i2, int i7, int i8, int i10, int[] iArr) {
        return this.mChildHelper.dispatchNestedScroll(i2, i7, i8, i10, iArr);
    }

    public void onNestedScroll(View view, int i2, int i7, int i8, int i10) {
        onNestedScrollInternal(i10, 0, (int[]) null);
    }

    public void onNestedScrollAccepted(View view, View view2, int i2) {
        onNestedScrollAccepted(view, view2, i2, 0);
    }

    public void onStopNestedScroll(View view) {
        onStopNestedScroll(view, 0);
    }

    public void seslSetFadingEdgeEnabled(boolean z, boolean z3) {
        applyFadingEdge(z, new a(this, z, z3, 0));
    }

    public void smoothScrollTo(int i2, int i7, boolean z) {
        smoothScrollTo(i2, i7, 250, z);
    }

    public void addView(View view, int i2) {
        if (getChildCount() <= 0) {
            super.addView(view, i2);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    public boolean dispatchNestedPreScroll(int i2, int i7, int[] iArr, int[] iArr2) {
        return dispatchNestedPreScroll(i2, i7, iArr, iArr2, 0);
    }

    public void seslSetFadingEdgeEnabled(boolean z, boolean z3, boolean z7) {
        applyFadingEdge(z, new b(this, z, z3, z7));
    }

    public void smoothScrollTo(int i2, int i7, int i8, boolean z) {
        smoothScrollBy(i2 - getScrollX(), i7 - getScrollY(), i8, z);
    }

    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, i2, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    public void seslSetOnGoToTopClickListener(SeslOnGoToTopClickListener seslOnGoToTopClickListener) {
    }
}
