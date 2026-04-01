package com.samsung.android.app.sdk.deepsky.contract.suggestion.view.widget;

import Ae.b;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import com.samsung.android.app.sdk.deepsky.contract.suggestion.view.widget.SwipeDismissLayout;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\b\u0007\u0018\u0000 52\u00020\u0001:\u000567895B1\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0010\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0012\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u0012\u0010\u0011J\u0019\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J#\u0010\u001a\u001a\u00020\u000b2\u0014\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0018¢\u0006\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001d\u001a\u00020\u001c8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0014\u0010 \u001a\u00020\u001f8\u0002X\u0004¢\u0006\u0006\n\u0004\b \u0010!R\u0014\u0010#\u001a\u00020\"8\u0002X\u0004¢\u0006\u0006\n\u0004\b#\u0010$R\u001a\u0010&\u001a\b\u0012\u0004\u0012\u00020\u000e0%8\u0002X\u0004¢\u0006\u0006\n\u0004\b&\u0010'R\u0014\u0010(\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b(\u0010)R\u0014\u0010+\u001a\u00020*8\u0002X\u0004¢\u0006\u0006\n\u0004\b+\u0010,R\u0014\u0010.\u001a\u00020-8\u0002X\u0004¢\u0006\u0006\n\u0004\b.\u0010/R\u0014\u00100\u001a\u00020*8\u0002X\u0004¢\u0006\u0006\n\u0004\b0\u0010,R$\u00101\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u00188\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b1\u00102R\u0016\u00103\u001a\u00020\u00158\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b3\u00104¨\u0006:"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissFrameLayout;", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "", "defStyle", "defStyleRes", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "Lme/x;", "resetTranslationAndAlpha", "()V", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissFrameLayout$Callback;", "callback", "addCallback", "(Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissFrameLayout$Callback;)V", "removeCallback", "Landroid/view/MotionEvent;", "ev", "", "dispatchTouchEvent", "(Landroid/view/MotionEvent;)Z", "Lkotlin/Function1;", "listener", "setDispatchTouchEventListener", "(LAe/b;)V", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout$OnPreSwipeListener;", "mOnPreSwipeListener", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout$OnPreSwipeListener;", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout$OnDismissedListener;", "mOnDismissedListener", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout$OnDismissedListener;", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout$OnSwipeProgressChangedListener;", "mOnSwipeProgressListener", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout$OnSwipeProgressChangedListener;", "Ljava/util/ArrayList;", "mCallbacks", "Ljava/util/ArrayList;", "mAnimationTime", "I", "Landroid/view/animation/DecelerateInterpolator;", "mCancelInterpolator", "Landroid/view/animation/DecelerateInterpolator;", "Landroid/view/animation/AccelerateInterpolator;", "mDismissInterpolator", "Landroid/view/animation/AccelerateInterpolator;", "mCompleteDismissGestureInterpolator", "mDispatchEventListener", "LAe/b;", "mStarted", "Z", "Companion", "Callback", "MyOnPreSwipeListener", "MyOnDismissedListener", "MyOnSwipeProgressChangedListener", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SwipeDismissFrameLayout extends SwipeDismissLayout {
    public static final Companion Companion = new Companion((e) null);
    private static final float DEFAULT_INTERPOLATION_FACTOR = 1.5f;
    private static final String TAG = "SSS:SwipeDismissFrameLayout";
    private static final float TRANSLATION_MIN_ALPHA = 1.0f;
    /* access modifiers changed from: private */
    public final int mAnimationTime;
    /* access modifiers changed from: private */
    public final ArrayList<Callback> mCallbacks;
    /* access modifiers changed from: private */
    public final DecelerateInterpolator mCancelInterpolator;
    /* access modifiers changed from: private */
    public final DecelerateInterpolator mCompleteDismissGestureInterpolator;
    /* access modifiers changed from: private */
    public final AccelerateInterpolator mDismissInterpolator;
    private b mDispatchEventListener;
    private final SwipeDismissLayout.OnDismissedListener mOnDismissedListener;
    private final SwipeDismissLayout.OnPreSwipeListener mOnPreSwipeListener;
    private final SwipeDismissLayout.OnSwipeProgressChangedListener mOnSwipeProgressListener;
    /* access modifiers changed from: private */
    public boolean mStarted;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissFrameLayout$Companion;", "", "<init>", "()V", "TAG", "", "TRANSLATION_MIN_ALPHA", "", "DEFAULT_INTERPOLATION_FACTOR", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissFrameLayout$MyOnDismissedListener;", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout$OnDismissedListener;", "<init>", "(Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissFrameLayout;)V", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout;", "layout", "Lme/x;", "onDismissed", "(Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout;)V", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class MyOnDismissedListener implements SwipeDismissLayout.OnDismissedListener {
        public MyOnDismissedListener() {
        }

        /* access modifiers changed from: private */
        public static final void onDismissed$lambda$0(SwipeDismissFrameLayout swipeDismissFrameLayout) {
            int size = swipeDismissFrameLayout.mCallbacks.size() - 1;
            if (size >= 0) {
                while (true) {
                    int i2 = size - 1;
                    Object obj = swipeDismissFrameLayout.mCallbacks.get(size);
                    j.d(obj, "get(...)");
                    ((Callback) obj).onDismissed(swipeDismissFrameLayout);
                    if (i2 < 0) {
                        break;
                    }
                    size = i2;
                }
            }
            swipeDismissFrameLayout.resetTranslationAndAlpha();
        }

        public void onDismissed(SwipeDismissLayout swipeDismissLayout) {
            TimeInterpolator timeInterpolator;
            if (Log.isLoggable(SwipeDismissFrameLayout.TAG, 3)) {
                Log.d(SwipeDismissFrameLayout.TAG, "onDismissed()");
            }
            ViewPropertyAnimator duration = SwipeDismissFrameLayout.this.animate().translationX((float) SwipeDismissFrameLayout.this.getWidth()).alpha(0.0f).setDuration((long) SwipeDismissFrameLayout.this.mAnimationTime);
            if (SwipeDismissFrameLayout.this.mStarted) {
                timeInterpolator = SwipeDismissFrameLayout.this.mCompleteDismissGestureInterpolator;
            } else {
                timeInterpolator = SwipeDismissFrameLayout.this.mDismissInterpolator;
            }
            duration.setInterpolator(timeInterpolator).withEndAction(new a(SwipeDismissFrameLayout.this, 0));
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissFrameLayout$MyOnPreSwipeListener;", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout$OnPreSwipeListener;", "<init>", "(Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissFrameLayout;)V", "onPreSwipe", "", "swipeDismissLayout", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout;", "xDown", "", "yDown", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class MyOnPreSwipeListener implements SwipeDismissLayout.OnPreSwipeListener {
        public MyOnPreSwipeListener() {
        }

        public boolean onPreSwipe(SwipeDismissLayout swipeDismissLayout, float f, float f5) {
            Iterator it = SwipeDismissFrameLayout.this.mCallbacks.iterator();
            j.d(it, "iterator(...)");
            while (it.hasNext()) {
                Object next = it.next();
                j.d(next, "next(...)");
                if (!((Callback) next).onPreSwipeStart(SwipeDismissFrameLayout.this, f, f5)) {
                    return false;
                }
            }
            return true;
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J)\u0010\n\u001a\u00020\t2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u0019\u0010\f\u001a\u00020\t2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissFrameLayout$MyOnSwipeProgressChangedListener;", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout$OnSwipeProgressChangedListener;", "<init>", "(Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissFrameLayout;)V", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout;", "layout", "", "progress", "translate", "Lme/x;", "onSwipeProgressChanged", "(Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout;FF)V", "onSwipeCanceled", "(Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissLayout;)V", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class MyOnSwipeProgressChangedListener implements SwipeDismissLayout.OnSwipeProgressChangedListener {
        public MyOnSwipeProgressChangedListener() {
        }

        /* access modifiers changed from: private */
        public static final void onSwipeCanceled$lambda$0(SwipeDismissFrameLayout swipeDismissFrameLayout) {
            int size = swipeDismissFrameLayout.mCallbacks.size() - 1;
            if (size >= 0) {
                while (true) {
                    int i2 = size - 1;
                    Object obj = swipeDismissFrameLayout.mCallbacks.get(size);
                    j.d(obj, "get(...)");
                    ((Callback) obj).onSwipeCanceled(swipeDismissFrameLayout);
                    if (i2 < 0) {
                        break;
                    }
                    size = i2;
                }
            }
            swipeDismissFrameLayout.resetTranslationAndAlpha();
        }

        public void onSwipeCanceled(SwipeDismissLayout swipeDismissLayout) {
            if (Log.isLoggable(SwipeDismissFrameLayout.TAG, 3)) {
                Log.d(SwipeDismissFrameLayout.TAG, "onSwipeCanceled() run swipe cancel animation");
            }
            SwipeDismissFrameLayout.this.mStarted = false;
            SwipeDismissFrameLayout.this.animate().translationX(0.0f).alpha(1.0f).setDuration((long) SwipeDismissFrameLayout.this.mAnimationTime).setInterpolator(SwipeDismissFrameLayout.this.mCancelInterpolator).withEndAction(new a(SwipeDismissFrameLayout.this, 1));
        }

        public void onSwipeProgressChanged(SwipeDismissLayout swipeDismissLayout, float f, float f5) {
            if (Log.isLoggable(SwipeDismissFrameLayout.TAG, 3)) {
                Log.d(SwipeDismissFrameLayout.TAG, "onSwipeProgressChanged() - " + f5);
            }
            SwipeDismissFrameLayout.this.setTranslationX(f5);
            SwipeDismissFrameLayout.this.setAlpha(((float) 1) - (f * 1.0f));
            if (!SwipeDismissFrameLayout.this.mStarted) {
                int size = SwipeDismissFrameLayout.this.mCallbacks.size() - 1;
                if (size >= 0) {
                    while (true) {
                        int i2 = size - 1;
                        Object obj = SwipeDismissFrameLayout.this.mCallbacks.get(size);
                        j.d(obj, "get(...)");
                        ((Callback) obj).onSwipeStarted(SwipeDismissFrameLayout.this);
                        if (i2 < 0) {
                            break;
                        }
                        size = i2;
                    }
                }
                SwipeDismissFrameLayout.this.mStarted = true;
            }
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SwipeDismissFrameLayout(Context context) {
        this(context, (AttributeSet) null, 0, 0, 14, (e) null);
        j.e(context, "context");
    }

    /* access modifiers changed from: private */
    public final void resetTranslationAndAlpha() {
        animate().cancel();
        setTranslationX(0.0f);
        setAlpha(1.0f);
        this.mStarted = false;
    }

    public final void addCallback(Callback callback) {
        if (callback != null) {
            this.mCallbacks.add(callback);
            return;
        }
        throw new NullPointerException("addCallback called with null callback");
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        b bVar;
        if (!(motionEvent == null || (bVar = this.mDispatchEventListener) == null)) {
            bVar.invoke(Integer.valueOf(motionEvent.getAction()));
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public final void removeCallback(Callback callback) {
        if (callback == null) {
            throw new NullPointerException("removeCallback called with null callback");
        } else if (!this.mCallbacks.remove(callback)) {
            throw new IllegalStateException("removeCallback called with nonexistent callback");
        }
    }

    public final void setDispatchTouchEventListener(b bVar) {
        this.mDispatchEventListener = bVar;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SwipeDismissFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, (e) null);
        j.e(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SwipeDismissFrameLayout(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0, 8, (e) null);
        j.e(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SwipeDismissFrameLayout(Context context, AttributeSet attributeSet, int i2, int i7, int i8, e eVar) {
        this(context, (i8 & 2) != 0 ? null : attributeSet, (i8 & 4) != 0 ? 0 : i2, (i8 & 8) != 0 ? 0 : i7);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SwipeDismissFrameLayout(Context context, AttributeSet attributeSet, int i2, int i7) {
        super(context, attributeSet, i2, i7);
        j.e(context, "context");
        MyOnPreSwipeListener myOnPreSwipeListener = new MyOnPreSwipeListener();
        this.mOnPreSwipeListener = myOnPreSwipeListener;
        MyOnDismissedListener myOnDismissedListener = new MyOnDismissedListener();
        this.mOnDismissedListener = myOnDismissedListener;
        MyOnSwipeProgressChangedListener myOnSwipeProgressChangedListener = new MyOnSwipeProgressChangedListener();
        this.mOnSwipeProgressListener = myOnSwipeProgressChangedListener;
        this.mCallbacks = new ArrayList<>();
        setOnPreSwipeListener(myOnPreSwipeListener);
        setOnDismissedListener(myOnDismissedListener);
        setOnSwipeProgressChangedListener(myOnSwipeProgressChangedListener);
        this.mAnimationTime = getContext().getResources().getInteger(17694720);
        this.mCancelInterpolator = new DecelerateInterpolator(1.5f);
        this.mDismissInterpolator = new AccelerateInterpolator(1.5f);
        this.mCompleteDismissGestureInterpolator = new DecelerateInterpolator(1.5f);
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0017\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\n\u001a\u00020\t2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\r\u001a\u00020\f2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u000f\u001a\u00020\f2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u000f\u0010\u000eJ\u0019\u0010\u0010\u001a\u00020\f2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0004\b\u0010\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissFrameLayout$Callback;", "", "<init>", "()V", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissFrameLayout;", "layout", "", "xDown", "yDown", "", "onPreSwipeStart", "(Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissFrameLayout;FF)Z", "Lme/x;", "onSwipeStarted", "(Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/widget/SwipeDismissFrameLayout;)V", "onSwipeCanceled", "onDismissed", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Callback {
        public final boolean onPreSwipeStart(SwipeDismissFrameLayout swipeDismissFrameLayout, float f, float f5) {
            return true;
        }

        public void onDismissed(SwipeDismissFrameLayout swipeDismissFrameLayout) {
        }

        public final void onSwipeCanceled(SwipeDismissFrameLayout swipeDismissFrameLayout) {
        }

        public final void onSwipeStarted(SwipeDismissFrameLayout swipeDismissFrameLayout) {
        }
    }
}
