package com.samsung.android.app.sdk.deepsky.contract.suggestion.view.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0016\u0018\u0000 X2\u00020\u0001:\u0004YZ[XB1\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u0017\u0010\u0016J\u000f\u0010\u0018\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u0018\u0010\u0016J\u0017\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u0019H\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u0019H\u0002¢\u0006\u0004\b\u001d\u0010\u001cJ\u0017\u0010 \u001a\u00020\u00122\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e¢\u0006\u0004\b \u0010!J\u0017\u0010#\u001a\u00020\u00122\b\u0010\u001f\u001a\u0004\u0018\u00010\"¢\u0006\u0004\b#\u0010$J\u0017\u0010&\u001a\u00020\u00122\b\u0010\u001f\u001a\u0004\u0018\u00010%¢\u0006\u0004\b&\u0010'J\u0017\u0010)\u001a\u00020\u00122\u0006\u0010(\u001a\u00020\u000eH\u0016¢\u0006\u0004\b)\u0010*J\u0017\u0010+\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0004\b+\u0010,J\u0017\u0010.\u001a\u00020\u000e2\u0006\u0010-\u001a\u00020\u0006H\u0016¢\u0006\u0004\b.\u0010/J\u0017\u00100\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0004\b0\u0010,J7\u00106\u001a\u00020\u000e2\u0006\u00102\u001a\u0002012\u0006\u00103\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u00104\u001a\u00020\u000b2\u0006\u00105\u001a\u00020\u000bH\u0004¢\u0006\u0004\b6\u00107R\u0014\u00108\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b8\u00109R\u0014\u0010:\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b:\u00109R\u0014\u0010;\u001a\u00020\u000b8\u0002X\u0004¢\u0006\u0006\n\u0004\b;\u0010<R\u0016\u0010=\u001a\u00020\u00068\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b=\u00109R\u0016\u0010>\u001a\u00020\u000b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b>\u0010<R\u0016\u0010?\u001a\u00020\u000b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b?\u0010<R\"\u0010@\u001a\u00020\u000e8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b@\u0010A\u001a\u0004\b@\u0010B\"\u0004\bC\u0010*R\u0016\u0010D\u001a\u00020\u000e8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bD\u0010AR\u0016\u0010E\u001a\u00020\u000e8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bE\u0010AR\u0016\u0010F\u001a\u00020\u000e8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bF\u0010AR\u0016\u0010G\u001a\u00020\u000e8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bG\u0010AR\u0018\u0010I\u001a\u0004\u0018\u00010H8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bI\u0010JR\u0016\u0010K\u001a\u00020\u000b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bK\u0010<R\u0016\u0010L\u001a\u00020\u000e8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bL\u0010AR\u0018\u0010M\u001a\u0004\u0018\u00010\u001e8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bM\u0010NR\u0018\u0010O\u001a\u0004\u0018\u00010\"8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bO\u0010PR\u0018\u0010Q\u001a\u0004\u0018\u00010%8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bQ\u0010RR\u0016\u0010S\u001a\u00020\u000b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bS\u0010<R\"\u0010T\u001a\u00020\u000b8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\bT\u0010<\u001a\u0004\bU\u0010V\"\u0004\bW\u0010\u0014¨\u0006\\"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout;", "Landroid/widget/FrameLayout;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "", "defStyle", "defStyleRes", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "", "dx", "dy", "", "isPotentialSwipe", "(FF)Z", "deltaX", "Lme/x;", "setProgress", "(F)V", "dismiss", "()V", "cancel", "resetMembers", "Landroid/view/MotionEvent;", "ev", "updateSwiping", "(Landroid/view/MotionEvent;)V", "updateDismiss", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout$OnPreSwipeListener;", "listener", "setOnPreSwipeListener", "(Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout$OnPreSwipeListener;)V", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout$OnDismissedListener;", "setOnDismissedListener", "(Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout$OnDismissedListener;)V", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout$OnSwipeProgressChangedListener;", "setOnSwipeProgressChangedListener", "(Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout$OnSwipeProgressChangedListener;)V", "disallowIntercept", "requestDisallowInterceptTouchEvent", "(Z)V", "onInterceptTouchEvent", "(Landroid/view/MotionEvent;)Z", "direction", "canScrollHorizontally", "(I)Z", "onTouchEvent", "Landroid/view/View;", "v", "checkV", "x", "y", "canScroll", "(Landroid/view/View;ZFFF)Z", "mSlop", "I", "mMinFlingVelocity", "mGestureThresholdPx", "F", "mActiveTouchId", "mDownX", "mDownY", "isSwipeable", "Z", "()Z", "setSwipeable", "mSwiping", "mCanStartSwipe", "mDismissed", "mDiscardIntercept", "Landroid/view/VelocityTracker;", "mVelocityTracker", "Landroid/view/VelocityTracker;", "mTranslationX", "mDisallowIntercept", "mOnPreSwipeListener", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout$OnPreSwipeListener;", "mDismissedListener", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout$OnDismissedListener;", "mProgressListener", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout$OnSwipeProgressChangedListener;", "mLastX", "dismissMinDragWidthRatio", "getDismissMinDragWidthRatio", "()F", "setDismissMinDragWidthRatio", "Companion", "OnPreSwipeListener", "OnDismissedListener", "OnSwipeProgressChangedListener", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SwipeDismissLayout extends FrameLayout {
    public static final Companion Companion = new Companion((e) null);
    public static final float DEFAULT_DISMISS_DRAG_WIDTH_RATIO = 0.33f;
    private static final float EDGE_SWIPE_THRESHOLD = 0.1f;
    private static final String TAG = "SSS:SwipeDismissLayout";
    private float dismissMinDragWidthRatio;
    private boolean isSwipeable;
    private int mActiveTouchId;
    private boolean mCanStartSwipe;
    private boolean mDisallowIntercept;
    private boolean mDiscardIntercept;
    private boolean mDismissed;
    private OnDismissedListener mDismissedListener;
    private float mDownX;
    private float mDownY;
    private final float mGestureThresholdPx;
    private float mLastX;
    private final int mMinFlingVelocity;
    private OnPreSwipeListener mOnPreSwipeListener;
    private OnSwipeProgressChangedListener mProgressListener;
    private final int mSlop;
    private boolean mSwiping;
    private float mTranslationX;
    private VelocityTracker mVelocityTracker;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout$Companion;", "", "<init>", "()V", "TAG", "", "DEFAULT_DISMISS_DRAG_WIDTH_RATIO", "", "EDGE_SWIPE_THRESHOLD", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout$OnDismissedListener;", "", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout;", "layout", "Lme/x;", "onDismissed", "(Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout;)V", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnDismissedListener {
        void onDismissed(SwipeDismissLayout swipeDismissLayout);
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H&¨\u0006\t"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout$OnPreSwipeListener;", "", "onPreSwipe", "", "swipeDismissLayout", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout;", "xDown", "", "yDown", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnPreSwipeListener {
        boolean onPreSwipe(SwipeDismissLayout swipeDismissLayout, float f, float f5);
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J)\u0010\b\u001a\u00020\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H&¢\u0006\u0004\b\b\u0010\tJ\u0019\u0010\n\u001a\u00020\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H&¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout$OnSwipeProgressChangedListener;", "", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout;", "layout", "", "progress", "translate", "Lme/x;", "onSwipeProgressChanged", "(Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout;FF)V", "onSwipeCanceled", "(Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout;)V", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnSwipeProgressChangedListener {
        void onSwipeCanceled(SwipeDismissLayout swipeDismissLayout);

        void onSwipeProgressChanged(SwipeDismissLayout swipeDismissLayout, float f, float f5);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SwipeDismissLayout(Context context) {
        this(context, (AttributeSet) null, 0, 0, 14, (e) null);
        j.e(context, "context");
    }

    private final void cancel() {
        OnSwipeProgressChangedListener onSwipeProgressChangedListener = this.mProgressListener;
        if (onSwipeProgressChangedListener != null) {
            j.b(onSwipeProgressChangedListener);
            onSwipeProgressChangedListener.onSwipeCanceled(this);
        }
    }

    private final void dismiss() {
        OnDismissedListener onDismissedListener = this.mDismissedListener;
        if (onDismissedListener != null) {
            j.b(onDismissedListener);
            onDismissedListener.onDismissed(this);
        }
    }

    private final boolean isPotentialSwipe(float f, float f5) {
        float f8 = f5 * f5;
        int i2 = this.mSlop;
        if (f8 + (f * f) > ((float) (i2 * i2))) {
            return true;
        }
        return false;
    }

    private final void resetMembers() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            j.b(velocityTracker);
            velocityTracker.recycle();
        }
        this.mVelocityTracker = null;
        this.mTranslationX = 0.0f;
        this.mDownX = 0.0f;
        this.mDownY = 0.0f;
        this.mSwiping = false;
        this.mDismissed = false;
        this.mDiscardIntercept = false;
        this.mCanStartSwipe = true;
        this.mDisallowIntercept = false;
    }

    private final void setProgress(float f) {
        this.mTranslationX = f;
        OnSwipeProgressChangedListener onSwipeProgressChangedListener = this.mProgressListener;
        if (onSwipeProgressChangedListener != null && f >= 0.0f) {
            j.b(onSwipeProgressChangedListener);
            onSwipeProgressChangedListener.onSwipeProgressChanged(this, f / ((float) getWidth()), f);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0041, code lost:
        if (r4.getXVelocity() >= ((float) r3.mMinFlingVelocity)) goto L_0x0043;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void updateDismiss(android.view.MotionEvent r4) {
        /*
            r3 = this;
            float r0 = r4.getRawX()
            float r1 = r3.mDownX
            float r0 = r0 - r1
            android.view.VelocityTracker r1 = r3.mVelocityTracker
            kotlin.jvm.internal.j.b(r1)
            r1.addMovement(r4)
            android.view.VelocityTracker r1 = r3.mVelocityTracker
            kotlin.jvm.internal.j.b(r1)
            r2 = 1000(0x3e8, float:1.401E-42)
            r1.computeCurrentVelocity(r2)
            boolean r1 = r3.mDismissed
            if (r1 != 0) goto L_0x0046
            int r1 = r3.getWidth()
            float r1 = (float) r1
            float r2 = r3.dismissMinDragWidthRatio
            float r1 = r1 * r2
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 <= 0) goto L_0x0033
            float r4 = r4.getRawX()
            float r0 = r3.mLastX
            int r4 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r4 >= 0) goto L_0x0043
        L_0x0033:
            android.view.VelocityTracker r4 = r3.mVelocityTracker
            kotlin.jvm.internal.j.b(r4)
            float r4 = r4.getXVelocity()
            int r0 = r3.mMinFlingVelocity
            float r0 = (float) r0
            int r4 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r4 < 0) goto L_0x0046
        L_0x0043:
            r4 = 1
            r3.mDismissed = r4
        L_0x0046:
            boolean r4 = r3.mDismissed
            if (r4 == 0) goto L_0x0062
            boolean r4 = r3.mSwiping
            if (r4 == 0) goto L_0x0062
            android.view.VelocityTracker r4 = r3.mVelocityTracker
            kotlin.jvm.internal.j.b(r4)
            float r4 = r4.getXVelocity()
            int r0 = r3.mMinFlingVelocity
            int r0 = -r0
            float r0 = (float) r0
            int r4 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r4 >= 0) goto L_0x0062
            r4 = 0
            r3.mDismissed = r4
        L_0x0062:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.contract.suggestion.view.widget.SwipeDismissLayout.updateDismiss(android.view.MotionEvent):void");
    }

    private final void updateSwiping(MotionEvent motionEvent) {
        boolean z;
        if (!this.mSwiping) {
            float rawX = motionEvent.getRawX() - this.mDownX;
            float rawY = motionEvent.getRawY() - this.mDownY;
            if (isPotentialSwipe(rawX, rawY)) {
                if (!this.mCanStartSwipe || Math.abs(rawY) >= Math.abs(rawX) || rawX <= 0.0f) {
                    z = false;
                } else {
                    z = true;
                }
                this.mSwiping = z;
                this.mCanStartSwipe = z;
            }
        }
    }

    public final boolean canScroll(View view, boolean z, float f, float f5, float f8) {
        j.e(view, "v");
        if (view instanceof ViewGroup) {
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            ViewGroup viewGroup = (ViewGroup) view;
            for (int childCount = viewGroup.getChildCount() - 1; -1 < childCount; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                float f10 = f5 + ((float) scrollX);
                if (f10 >= ((float) childAt.getLeft()) && f10 < ((float) childAt.getRight())) {
                    float f11 = f8 + ((float) scrollY);
                    if (f11 >= ((float) childAt.getTop()) && f11 < ((float) childAt.getBottom())) {
                        if (canScroll(childAt, true, f, f10 - ((float) childAt.getLeft()), f11 - ((float) childAt.getTop()))) {
                            return true;
                        }
                    }
                }
            }
        }
        if (!z || !view.canScrollHorizontally((int) (-f))) {
            return false;
        }
        return true;
    }

    public boolean canScrollHorizontally(int i2) {
        if (i2 >= 0 || !this.isSwipeable || getVisibility() != 0) {
            return false;
        }
        return true;
    }

    public final float getDismissMinDragWidthRatio() {
        return this.dismissMinDragWidthRatio;
    }

    public final boolean isSwipeable() {
        return this.isSwipeable;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        SwipeDismissLayout swipeDismissLayout;
        int i2;
        j.e(motionEvent, "ev");
        if (!this.isSwipeable) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        motionEvent.offsetLocation(this.mTranslationX, 0.0f);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked != 2) {
                    if (actionMasked != 3) {
                        if (actionMasked == 5) {
                            this.mActiveTouchId = motionEvent.getPointerId(motionEvent.getActionIndex());
                        } else if (actionMasked == 6) {
                            int actionIndex = motionEvent.getActionIndex();
                            if (motionEvent.getPointerId(actionIndex) == this.mActiveTouchId) {
                                if (actionIndex == 0) {
                                    i2 = 1;
                                } else {
                                    i2 = 0;
                                }
                                this.mActiveTouchId = motionEvent.getPointerId(i2);
                            }
                        }
                    }
                } else if (this.mVelocityTracker != null && !this.mDiscardIntercept) {
                    int findPointerIndex = motionEvent.findPointerIndex(this.mActiveTouchId);
                    if (findPointerIndex == -1) {
                        Log.e(TAG, "Invalid pointer index: ignoring.");
                        this.mDiscardIntercept = true;
                    } else {
                        float rawX = motionEvent.getRawX() - this.mDownX;
                        float x9 = motionEvent.getX(findPointerIndex);
                        float y = motionEvent.getY(findPointerIndex);
                        if (rawX != 0.0f && this.mDownX >= this.mGestureThresholdPx) {
                            swipeDismissLayout = this;
                            if (swipeDismissLayout.canScroll(this, false, rawX, x9, y)) {
                                swipeDismissLayout.mDiscardIntercept = true;
                            }
                        } else {
                            swipeDismissLayout = this;
                        }
                        swipeDismissLayout.updateSwiping(motionEvent);
                    }
                }
                swipeDismissLayout = this;
            }
            swipeDismissLayout = this;
            swipeDismissLayout.resetMembers();
        } else {
            swipeDismissLayout = this;
            swipeDismissLayout.resetMembers();
            swipeDismissLayout.mDownX = motionEvent.getRawX();
            swipeDismissLayout.mDownY = motionEvent.getRawY();
            swipeDismissLayout.mActiveTouchId = motionEvent.getPointerId(0);
            VelocityTracker obtain = VelocityTracker.obtain();
            swipeDismissLayout.mVelocityTracker = obtain;
            if (obtain != null) {
                obtain.addMovement(motionEvent);
            }
        }
        OnPreSwipeListener onPreSwipeListener = swipeDismissLayout.mOnPreSwipeListener;
        if (((onPreSwipeListener != null || swipeDismissLayout.mDisallowIntercept) && (onPreSwipeListener == null || !onPreSwipeListener.onPreSwipe(swipeDismissLayout, swipeDismissLayout.mDownX, swipeDismissLayout.mDownY))) || swipeDismissLayout.mDiscardIntercept || !swipeDismissLayout.mSwiping) {
            return false;
        }
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        j.e(motionEvent, "ev");
        if (!this.isSwipeable) {
            return super.onTouchEvent(motionEvent);
        }
        if (this.mVelocityTracker == null) {
            return super.onTouchEvent(motionEvent);
        }
        OnPreSwipeListener onPreSwipeListener = this.mOnPreSwipeListener;
        if (onPreSwipeListener != null) {
            j.b(onPreSwipeListener);
            if (!onPreSwipeListener.onPreSwipe(this, this.mDownX, this.mDownY)) {
                return super.onTouchEvent(motionEvent);
            }
        }
        motionEvent.offsetLocation(this.mTranslationX, 0.0f);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1) {
            updateDismiss(motionEvent);
            if (this.mDismissed) {
                dismiss();
            } else if (this.mSwiping) {
                cancel();
            }
            resetMembers();
        } else if (actionMasked == 2) {
            VelocityTracker velocityTracker = this.mVelocityTracker;
            j.b(velocityTracker);
            velocityTracker.addMovement(motionEvent);
            this.mLastX = motionEvent.getRawX();
            updateSwiping(motionEvent);
            if (this.mSwiping) {
                setProgress(motionEvent.getRawX() - this.mDownX);
            }
        } else if (actionMasked == 3) {
            cancel();
            resetMembers();
        }
        return true;
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        this.mDisallowIntercept = z;
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(z);
        }
    }

    public final void setDismissMinDragWidthRatio(float f) {
        this.dismissMinDragWidthRatio = f;
    }

    public final void setOnDismissedListener(OnDismissedListener onDismissedListener) {
        this.mDismissedListener = onDismissedListener;
    }

    public final void setOnPreSwipeListener(OnPreSwipeListener onPreSwipeListener) {
        this.mOnPreSwipeListener = onPreSwipeListener;
    }

    public final void setOnSwipeProgressChangedListener(OnSwipeProgressChangedListener onSwipeProgressChangedListener) {
        this.mProgressListener = onSwipeProgressChangedListener;
    }

    public final void setSwipeable(boolean z) {
        this.isSwipeable = z;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SwipeDismissLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, (e) null);
        j.e(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SwipeDismissLayout(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0, 8, (e) null);
        j.e(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SwipeDismissLayout(Context context, AttributeSet attributeSet, int i2, int i7, int i8, e eVar) {
        this(context, (i8 & 2) != 0 ? null : attributeSet, (i8 & 4) != 0 ? 0 : i2, (i8 & 8) != 0 ? 0 : i7);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SwipeDismissLayout(Context context, AttributeSet attributeSet, int i2, int i7) {
        super(context, attributeSet, i2, i7);
        j.e(context, "context");
        this.mCanStartSwipe = true;
        this.dismissMinDragWidthRatio = 0.33f;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mSlop = viewConfiguration.getScaledTouchSlop();
        this.mMinFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mGestureThresholdPx = ((float) Resources.getSystem().getDisplayMetrics().widthPixels) * 0.1f;
        this.isSwipeable = true;
    }
}
