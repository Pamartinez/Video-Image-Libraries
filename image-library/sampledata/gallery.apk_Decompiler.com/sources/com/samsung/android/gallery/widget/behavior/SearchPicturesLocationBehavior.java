package com.samsung.android.gallery.widget.behavior;

import A4.Y;
import D5.f;
import a6.C0418a;
import android.content.Context;
import android.os.Build;
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
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.ViewDragHelper;
import c0.C0086a;
import com.samsung.android.gallery.widget.R$color;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$string;
import com.samsung.android.gallery.widget.effects.blur.AGSLBlurEffect;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import i.C0212a;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import x2.C0334a;
import x2.C0340g;
import x2.C0344k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchPicturesLocationBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    int activePointerId;
    private final ArrayList<BottomSheetCallback> callbacks = new ArrayList<>();
    int collapsedOffset;
    private final ViewDragHelper.Callback dragCallback = new ViewDragHelper.Callback() {
        public int clampViewPositionHorizontal(View view, int i2, int i7) {
            return view.getLeft();
        }

        public int clampViewPositionVertical(View view, int i2, int i7) {
            return MathUtils.clamp(i2, SearchPicturesLocationBehavior.this.getExpandedOffset(), SearchPicturesLocationBehavior.this.collapsedOffset);
        }

        public int getViewVerticalDragRange(View view) {
            return SearchPicturesLocationBehavior.this.collapsedOffset;
        }

        public void onViewDragStateChanged(int i2) {
            if (i2 == 1) {
                SearchPicturesLocationBehavior.this.setStateInternal(1);
            }
        }

        public void onViewPositionChanged(View view, int i2, int i7, int i8, int i10) {
            SearchPicturesLocationBehavior.this.dispatchOnSlide(i7);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x003c, code lost:
            if (java.lang.Math.abs(r6 - r4.this$0.halfExpandedOffset) < java.lang.Math.abs(r6 - r4.this$0.collapsedOffset)) goto L_0x0011;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0053, code lost:
            if (r6 < java.lang.Math.abs(r6 - r7.collapsedOffset)) goto L_0x0068;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0065, code lost:
            if (java.lang.Math.abs(r6 - r0) < java.lang.Math.abs(r6 - r4.this$0.collapsedOffset)) goto L_0x0011;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:3:0x000f, code lost:
            if (r5.getTop() > r4.this$0.halfExpandedOffset) goto L_0x0011;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onViewReleased(android.view.View r5, float r6, float r7) {
            /*
                r4 = this;
                r0 = 0
                int r1 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
                r2 = 3
                r3 = 6
                if (r1 >= 0) goto L_0x0013
                int r6 = r5.getTop()
                com.samsung.android.gallery.widget.behavior.SearchPicturesLocationBehavior r7 = com.samsung.android.gallery.widget.behavior.SearchPicturesLocationBehavior.this
                int r7 = r7.halfExpandedOffset
                if (r6 <= r7) goto L_0x0068
            L_0x0011:
                r2 = r3
                goto L_0x0068
            L_0x0013:
                int r0 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
                r1 = 4
                if (r0 == 0) goto L_0x0041
                float r6 = java.lang.Math.abs(r6)
                float r7 = java.lang.Math.abs(r7)
                int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
                if (r6 <= 0) goto L_0x0025
                goto L_0x0041
            L_0x0025:
                int r6 = r5.getTop()
                com.samsung.android.gallery.widget.behavior.SearchPicturesLocationBehavior r7 = com.samsung.android.gallery.widget.behavior.SearchPicturesLocationBehavior.this
                int r7 = r7.halfExpandedOffset
                int r7 = r6 - r7
                int r7 = java.lang.Math.abs(r7)
                com.samsung.android.gallery.widget.behavior.SearchPicturesLocationBehavior r0 = com.samsung.android.gallery.widget.behavior.SearchPicturesLocationBehavior.this
                int r0 = r0.collapsedOffset
                int r6 = r6 - r0
                int r6 = java.lang.Math.abs(r6)
                if (r7 >= r6) goto L_0x003f
                goto L_0x0011
            L_0x003f:
                r2 = r1
                goto L_0x0068
            L_0x0041:
                int r6 = r5.getTop()
                com.samsung.android.gallery.widget.behavior.SearchPicturesLocationBehavior r7 = com.samsung.android.gallery.widget.behavior.SearchPicturesLocationBehavior.this
                int r0 = r7.halfExpandedOffset
                if (r6 >= r0) goto L_0x0056
                int r7 = r7.collapsedOffset
                int r7 = r6 - r7
                int r7 = java.lang.Math.abs(r7)
                if (r6 >= r7) goto L_0x0011
                goto L_0x0068
            L_0x0056:
                int r7 = r6 - r0
                int r7 = java.lang.Math.abs(r7)
                com.samsung.android.gallery.widget.behavior.SearchPicturesLocationBehavior r0 = com.samsung.android.gallery.widget.behavior.SearchPicturesLocationBehavior.this
                int r0 = r0.collapsedOffset
                int r6 = r6 - r0
                int r6 = java.lang.Math.abs(r6)
                if (r7 >= r6) goto L_0x003f
                goto L_0x0011
            L_0x0068:
                com.samsung.android.gallery.widget.behavior.SearchPicturesLocationBehavior r4 = com.samsung.android.gallery.widget.behavior.SearchPicturesLocationBehavior.this
                boolean r6 = r4.shouldSkipSmoothAnimation()
                r4.startSettling(r5, r2, r6)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.behavior.SearchPicturesLocationBehavior.AnonymousClass1.onViewReleased(android.view.View, float, float):void");
        }

        public boolean tryCaptureView(View view, int i2) {
            View view2;
            SearchPicturesLocationBehavior searchPicturesLocationBehavior = SearchPicturesLocationBehavior.this;
            int i7 = searchPicturesLocationBehavior.state;
            if (i7 == 1 || searchPicturesLocationBehavior.touchingScrollingChild) {
                return false;
            }
            if (i7 == 3 && searchPicturesLocationBehavior.activePointerId == i2) {
                WeakReference<View> weakReference = searchPicturesLocationBehavior.nestedScrollingChildRef;
                if (weakReference != null) {
                    view2 = weakReference.get();
                } else {
                    view2 = null;
                }
                if (view2 != null && view2.canScrollVertically(-1)) {
                    return false;
                }
            }
            WeakReference<V> weakReference2 = SearchPicturesLocationBehavior.this.viewRef;
            if (weakReference2 == null || weakReference2.get() != view) {
                return false;
            }
            return true;
        }
    };
    private int expandHalfwayActionId = -1;
    private int expandedOffset;
    int halfExpandedOffset;
    float halfExpandedRatio = 0.5f;
    private boolean ignoreEvents;
    private Map<View, Integer> importantForAccessibilityMap;
    private int initialY;
    private int lastNestedScrollDy;
    private AGSLBlurEffect mBlurEffect;
    private View mBlurTargetView;
    private C0340g materialShapeDrawable;
    private boolean needNotifyChildTop;
    private boolean nestedScrolled;
    WeakReference<View> nestedScrollingChildRef;
    int parentHeight;
    int state = 6;
    private final SearchPicturesLocationBehavior<V>.StateSettlingTracker stateSettlingTracker = new StateSettlingTracker(this, 0);
    boolean touchingScrollingChild;
    private VelocityTracker velocityTracker;
    ViewDragHelper viewDragHelper;
    WeakReference<V> viewRef;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface BottomSheetCallback {
        void onSlide(float f, int i2);

        void onStateChanged(int i2);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class StateSettlingTracker {
        private final Runnable continueSettlingRunnable;
        /* access modifiers changed from: private */
        public boolean isContinueSettlingRunnablePosted;
        /* access modifiers changed from: private */
        public int targetState;

        public /* synthetic */ StateSettlingTracker(SearchPicturesLocationBehavior searchPicturesLocationBehavior, int i2) {
            this();
        }

        public void continueSettlingToState(int i2) {
            WeakReference<V> weakReference = SearchPicturesLocationBehavior.this.viewRef;
            if (weakReference != null && weakReference.get() != null) {
                this.targetState = i2;
                if (!this.isContinueSettlingRunnablePosted) {
                    ViewCompat.postOnAnimation((View) SearchPicturesLocationBehavior.this.viewRef.get(), this.continueSettlingRunnable);
                    this.isContinueSettlingRunnablePosted = true;
                }
            }
        }

        private StateSettlingTracker() {
            this.continueSettlingRunnable = new Runnable() {
                public void run() {
                    StateSettlingTracker.this.isContinueSettlingRunnablePosted = false;
                    ViewDragHelper viewDragHelper = SearchPicturesLocationBehavior.this.viewDragHelper;
                    if (viewDragHelper == null || !viewDragHelper.continueSettling(true)) {
                        StateSettlingTracker stateSettlingTracker = StateSettlingTracker.this;
                        SearchPicturesLocationBehavior searchPicturesLocationBehavior = SearchPicturesLocationBehavior.this;
                        if (searchPicturesLocationBehavior.state == 2) {
                            searchPicturesLocationBehavior.setStateInternal(stateSettlingTracker.targetState);
                            return;
                        }
                        return;
                    }
                    StateSettlingTracker stateSettlingTracker2 = StateSettlingTracker.this;
                    stateSettlingTracker2.continueSettlingToState(stateSettlingTracker2.targetState);
                }
            };
        }
    }

    public SearchPicturesLocationBehavior() {
    }

    private void calculateCollapsedOffset(View view) {
        int i2;
        int i7 = this.parentHeight;
        if (view != null) {
            i2 = view.getBottom();
        } else {
            i2 = 0;
        }
        this.collapsedOffset = i7 - i2;
    }

    private void calculateHalfExpandedOffset() {
        this.halfExpandedOffset = (int) ((1.0f - this.halfExpandedRatio) * ((float) this.parentHeight));
    }

    private AccessibilityViewCommand createAccessibilityViewCommandForState(int i2) {
        return new Y((Object) this, i2, 7);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [o1.a, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r1v0, types: [o1.a, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r2v0, types: [o1.a, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r3v0, types: [o1.a, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r4v0, types: [x2.e, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r5v0, types: [x2.e, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r6v0, types: [x2.e, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r7v0, types: [x2.e, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r8v2, types: [x2.k, java.lang.Object] */
    private void createMaterialShapeDrawable(Context context) {
        ? obj = new Object();
        ? obj2 = new Object();
        ? obj3 = new Object();
        ? obj4 = new Object();
        ? obj5 = new Object();
        ? obj6 = new Object();
        ? obj7 = new Object();
        ? obj8 = new Object();
        float dimension = context.getResources().getDimension(R$dimen.rounded_corner_radius);
        C0334a aVar = new C0334a(dimension);
        C0334a aVar2 = new C0334a(dimension);
        C0334a aVar3 = new C0334a(dimension);
        C0334a aVar4 = new C0334a(dimension);
        ? obj9 = new Object();
        obj9.f2122a = obj;
        obj9.b = obj2;
        obj9.f2123c = obj3;
        obj9.d = obj4;
        obj9.e = aVar;
        obj9.f = aVar2;
        obj9.g = aVar3;
        obj9.f2124h = aVar4;
        obj9.f2125i = obj5;
        obj9.f2126j = obj6;
        obj9.k = obj7;
        obj9.l = obj8;
        C0340g gVar = new C0340g((C0344k) obj9);
        this.materialShapeDrawable = gVar;
        gVar.i(context);
        this.materialShapeDrawable.setTint(context.getColor(R$color.default_background));
    }

    public static <V extends View> SearchPicturesLocationBehavior<V> from(V v) {
        ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
            CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior();
            if (behavior instanceof SearchPicturesLocationBehavior) {
                return (SearchPicturesLocationBehavior) behavior;
            }
            throw new IllegalArgumentException("The view is not associated with SearchPicturesLocationBehavior");
        }
        throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
    }

    private int getTopOffsetForState(int i2) {
        if (i2 == 3) {
            return getExpandedOffset();
        }
        if (i2 == 4) {
            return this.collapsedOffset;
        }
        if (i2 == 5) {
            return this.parentHeight;
        }
        if (i2 == 6) {
            return this.halfExpandedOffset;
        }
        throw new IllegalArgumentException(C0086a.i(i2, "Invalid state to get top offset: "));
    }

    private boolean isLayouting(V v) {
        ViewParent parent = v.getParent();
        if (parent == null || !parent.isLayoutRequested() || !ViewCompat.isAttachedToWindow(v)) {
            return false;
        }
        return true;
    }

    private boolean isValidYForHiddenState() {
        if (this.initialY > this.parentHeight - 200) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$createAccessibilityViewCommandForState$2(int i2, View view, AccessibilityViewCommand.CommandArguments commandArguments) {
        setState(i2);
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$onLayoutChild$0(View view, MotionEvent motionEvent) {
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setState$1(View view, int i2) {
        startSettling(view, i2, false);
    }

    private void replaceAccessibilityActionForState(V v, AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat, int i2) {
        ViewCompat.replaceAccessibilityAction(v, accessibilityActionCompat, (CharSequence) null, createAccessibilityViewCommandForState(i2));
    }

    private void reset() {
        this.activePointerId = -1;
        VelocityTracker velocityTracker2 = this.velocityTracker;
        if (velocityTracker2 != null) {
            velocityTracker2.recycle();
            this.velocityTracker = null;
        }
    }

    private void runAfterLayout(V v, Runnable runnable) {
        if (isLayouting(v)) {
            v.post(runnable);
        } else {
            runnable.run();
        }
    }

    private boolean shouldHandleDraggingWithHelper() {
        if (this.viewDragHelper != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void startSettling(View view, int i2, boolean z) {
        int topOffsetForState = getTopOffsetForState(i2);
        ViewDragHelper viewDragHelper2 = this.viewDragHelper;
        if (viewDragHelper2 == null || (!z ? !viewDragHelper2.smoothSlideViewTo(view, view.getLeft(), topOffsetForState) : !viewDragHelper2.settleCapturedViewAt(view.getLeft(), topOffsetForState))) {
            setStateInternal(i2);
            return;
        }
        setStateInternal(2);
        this.stateSettlingTracker.continueSettlingToState(i2);
    }

    private void updateAccessibilityActions() {
        View view;
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference != null && (view = (View) weakReference.get()) != null) {
            ViewCompat.removeAccessibilityAction(view, 524288);
            ViewCompat.removeAccessibilityAction(view, 262144);
            ViewCompat.removeAccessibilityAction(view, MediaDefs.Meta.SEF.SEF_MIN_SIZE);
            int i2 = this.expandHalfwayActionId;
            if (i2 != -1) {
                ViewCompat.removeAccessibilityAction(view, i2);
            }
            if (this.state != 6) {
                this.expandHalfwayActionId = ViewCompat.addAccessibilityAction(view, view.getResources().getString(R$string.bottomsheet_action_expand_halfway), createAccessibilityViewCommandForState(6));
            }
            int i7 = this.state;
            if (i7 == 3) {
                replaceAccessibilityActionForState(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE, 6);
            } else if (i7 == 4) {
                replaceAccessibilityActionForState(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND, 6);
            } else if (i7 == 6) {
                replaceAccessibilityActionForState(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE, 4);
                replaceAccessibilityActionForState(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND, 3);
            }
        }
    }

    private void updateImportantForAccessibility(boolean z) {
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference != null) {
            ViewParent parent = ((View) weakReference.get()).getParent();
            if (parent instanceof CoordinatorLayout) {
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
                int childCount = coordinatorLayout.getChildCount();
                if (z) {
                    if (this.importantForAccessibilityMap == null) {
                        this.importantForAccessibilityMap = new HashMap(childCount);
                    } else {
                        return;
                    }
                }
                for (int i2 = 0; i2 < childCount; i2++) {
                    V childAt = coordinatorLayout.getChildAt(i2);
                    if (childAt != this.viewRef.get() && z) {
                        this.importantForAccessibilityMap.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                    }
                }
                if (!z) {
                    this.importantForAccessibilityMap = null;
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
        float f;
        float f5;
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference != null) {
            view = (View) weakReference.get();
        } else {
            view = null;
        }
        if (view != null && !this.callbacks.isEmpty()) {
            int i7 = this.collapsedOffset;
            if (i2 > i7 || i7 == getExpandedOffset()) {
                int i8 = this.collapsedOffset;
                f = (float) (i8 - i2);
                f5 = (float) (this.parentHeight - i8);
            } else {
                int i10 = this.collapsedOffset;
                f = (float) (i10 - i2);
                f5 = (float) (i10 - getExpandedOffset());
            }
            float f8 = f / f5;
            for (int i11 = 0; i11 < this.callbacks.size(); i11++) {
                this.callbacks.get(i11).onSlide(f8, i2);
            }
        }
    }

    public View findScrollingChild(View view) {
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

    public int getExpandedOffset() {
        return this.expandedOffset;
    }

    public int getState() {
        return this.state;
    }

    public boolean isNestedScrollingCheckEnabled() {
        return true;
    }

    public void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams layoutParams) {
        super.onAttachedToLayoutParams(layoutParams);
        this.viewRef = null;
        this.viewDragHelper = null;
    }

    public void onConfigurationChanged() {
        this.needNotifyChildTop = true;
    }

    public void onDetachedFromLayoutParams() {
        AGSLBlurEffect aGSLBlurEffect;
        super.onDetachedFromLayoutParams();
        this.viewRef = null;
        this.viewDragHelper = null;
        if (Build.VERSION.SDK_INT >= 33 && (aGSLBlurEffect = this.mBlurEffect) != null) {
            aGSLBlurEffect.clearEffect();
        }
    }

    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        ViewDragHelper viewDragHelper2;
        boolean z;
        View view;
        if (!v.isShown()) {
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
            if (this.state == 5 || (this.activePointerId == -1 && !isValidYForHiddenState() && !coordinatorLayout.isPointInChildBounds(v, x9, this.initialY))) {
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
        if (actionMasked != 2 || view2 == null || this.ignoreEvents || this.state == 1 || coordinatorLayout.isPointInChildBounds(view2, (int) motionEvent.getX(), (int) motionEvent.getY()) || this.viewDragHelper == null || Math.abs(((float) this.initialY) - motionEvent.getY()) <= ((float) this.viewDragHelper.getTouchSlop())) {
            return false;
        }
        return true;
    }

    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, V v, int i2) {
        boolean z;
        C0340g gVar;
        if (ViewCompat.getFitsSystemWindows(coordinatorLayout) && !ViewCompat.getFitsSystemWindows(v)) {
            v.setFitsSystemWindows(true);
        }
        if (this.viewRef == null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            WeakReference<V> weakReference = new WeakReference<>(v);
            this.viewRef = weakReference;
            View view = (View) weakReference.get();
            if (view != null) {
                view.setOnTouchListener(new f(1));
            }
            C0340g gVar2 = this.materialShapeDrawable;
            if (gVar2 != null) {
                ViewCompat.setBackground(v, gVar2);
                this.materialShapeDrawable.j(ViewCompat.getElevation(v));
            }
            if (Build.VERSION.SDK_INT >= 33 && this.mBlurTargetView != null && this.mBlurEffect == null && (gVar = this.materialShapeDrawable) != null) {
                gVar.setAlpha(230);
                this.mBlurEffect = new AGSLBlurEffect.Builder(this.mBlurTargetView, (View) this.viewRef.get()).useDualPath().setTopRounding((int) this.materialShapeDrawable.g()).usePreDrawListener().build();
            }
            updateAccessibilityActions();
            if (ViewCompat.getImportantForAccessibility(v) == 0) {
                ViewCompat.setImportantForAccessibility(v, 1);
            }
        }
        if (this.viewDragHelper == null) {
            this.viewDragHelper = ViewDragHelper.create(coordinatorLayout, this.dragCallback);
        }
        int top = v.getTop();
        coordinatorLayout.onLayoutChild(v, i2);
        this.parentHeight = coordinatorLayout.getHeight();
        calculateHalfExpandedOffset();
        calculateCollapsedOffset(v.findViewById(R$id.item_count));
        int i7 = this.state;
        if (i7 == 3) {
            ViewCompat.offsetTopAndBottom(v, getExpandedOffset());
        } else if (i7 == 6) {
            ViewCompat.offsetTopAndBottom(v, this.halfExpandedOffset);
        } else if (i7 == 4) {
            ViewCompat.offsetTopAndBottom(v, this.collapsedOffset);
        } else if (i7 == 1 || i7 == 2) {
            ViewCompat.offsetTopAndBottom(v, top - v.getTop());
        } else if (i7 == 5) {
            ViewCompat.offsetTopAndBottom(v, this.parentHeight);
        }
        this.nestedScrollingChildRef = new WeakReference<>(findScrollingChild(v));
        if (z || this.needNotifyChildTop) {
            dispatchOnSlide(v.getTop());
            this.needNotifyChildTop = false;
        }
        return true;
    }

    public boolean onMeasureChild(CoordinatorLayout coordinatorLayout, V v, int i2, int i7, int i8, int i10) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
        v.measure(ViewGroup.getChildMeasureSpec(i2, coordinatorLayout.getPaddingRight() + coordinatorLayout.getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i7, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i8, coordinatorLayout.getPaddingBottom() + coordinatorLayout.getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i10, marginLayoutParams.height));
        return true;
    }

    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, V v, View view, float f, float f5) {
        WeakReference<View> weakReference;
        int i2;
        if (!isNestedScrollingCheckEnabled() || (weakReference = this.nestedScrollingChildRef) == null || view != weakReference.get() || (i2 = this.state) == 3 || i2 == 6) {
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
            if ((!isNestedScrollingCheckEnabled() || view == view2) && !view.canScrollVertically(i7)) {
                int top = v.getTop();
                int i10 = top - i7;
                if (i7 > 0) {
                    if (i10 < getExpandedOffset()) {
                        int expandedOffset2 = top - getExpandedOffset();
                        iArr[1] = expandedOffset2;
                        ViewCompat.offsetTopAndBottom(v, -expandedOffset2);
                        setStateInternal(3);
                    } else {
                        iArr[1] = i7;
                        ViewCompat.offsetTopAndBottom(v, -i7);
                        setStateInternal(1);
                    }
                } else if (i7 < 0) {
                    int i11 = this.collapsedOffset;
                    if (i10 <= i11) {
                        iArr[1] = i7;
                        ViewCompat.offsetTopAndBottom(v, -i7);
                        setStateInternal(1);
                    } else {
                        int i12 = top - i11;
                        iArr[1] = i12;
                        ViewCompat.offsetTopAndBottom(v, -i12);
                        setStateInternal(4);
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
        int i2 = savedState.state;
        if (i2 == 1 || i2 == 2) {
            i2 = 4;
        }
        this.state = i2;
    }

    public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, V v) {
        return new SavedState(super.onSaveInstanceState(coordinatorLayout, v), (SearchPicturesLocationBehavior<?>) this);
    }

    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i2, int i7) {
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002f, code lost:
        if (r4.getTop() > r2.halfExpandedOffset) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0047, code lost:
        if (r3 < java.lang.Math.abs(r3 - r2.collapsedOffset)) goto L_0x0032;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onStopNestedScroll(androidx.coordinatorlayout.widget.CoordinatorLayout r3, V r4, android.view.View r5, int r6) {
        /*
            r2 = this;
            int r3 = r4.getTop()
            int r6 = r2.getExpandedOffset()
            r0 = 3
            if (r3 != r6) goto L_0x000f
            r2.setStateInternal(r0)
            return
        L_0x000f:
            boolean r3 = r2.isNestedScrollingCheckEnabled()
            if (r3 == 0) goto L_0x0024
            java.lang.ref.WeakReference<android.view.View> r3 = r2.nestedScrollingChildRef
            if (r3 == 0) goto L_0x0023
            java.lang.Object r3 = r3.get()
            if (r5 != r3) goto L_0x0023
            boolean r3 = r2.nestedScrolled
            if (r3 != 0) goto L_0x0024
        L_0x0023:
            return
        L_0x0024:
            int r3 = r2.lastNestedScrollDy
            r5 = 6
            if (r3 <= 0) goto L_0x0034
            int r3 = r4.getTop()
            int r6 = r2.halfExpandedOffset
            if (r3 <= r6) goto L_0x0032
        L_0x0031:
            r0 = r5
        L_0x0032:
            r5 = r0
            goto L_0x0073
        L_0x0034:
            r6 = 4
            if (r3 != 0) goto L_0x005c
            int r3 = r4.getTop()
            int r1 = r2.halfExpandedOffset
            if (r3 >= r1) goto L_0x004a
            int r6 = r2.collapsedOffset
            int r6 = r3 - r6
            int r6 = java.lang.Math.abs(r6)
            if (r3 >= r6) goto L_0x0031
            goto L_0x0032
        L_0x004a:
            int r0 = r3 - r1
            int r0 = java.lang.Math.abs(r0)
            int r1 = r2.collapsedOffset
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r0 >= r3) goto L_0x005a
            goto L_0x0031
        L_0x005a:
            r0 = r6
            goto L_0x0032
        L_0x005c:
            int r3 = r4.getTop()
            int r0 = r2.halfExpandedOffset
            int r0 = r3 - r0
            int r0 = java.lang.Math.abs(r0)
            int r1 = r2.collapsedOffset
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r0 >= r3) goto L_0x0072
            goto L_0x0073
        L_0x0072:
            r5 = r6
        L_0x0073:
            r3 = 0
            r2.startSettling(r4, r5, r3)
            r2.nestedScrolled = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.behavior.SearchPicturesLocationBehavior.onStopNestedScroll(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.View, int):void");
    }

    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (!v.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (this.state == 1 && actionMasked == 0) {
            return true;
        }
        if (shouldHandleDraggingWithHelper()) {
            this.viewDragHelper.processTouchEvent(motionEvent);
        }
        if (actionMasked == 0) {
            reset();
        }
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(motionEvent);
        if (shouldHandleDraggingWithHelper() && actionMasked == 2 && !this.ignoreEvents && Math.abs(((float) this.initialY) - motionEvent.getY()) > ((float) this.viewDragHelper.getTouchSlop())) {
            this.viewDragHelper.captureChildView(v, motionEvent.getPointerId(motionEvent.getActionIndex()));
        }
        return !this.ignoreEvents;
    }

    public void removeBottomSheetCallback(BottomSheetCallback bottomSheetCallback) {
        this.callbacks.remove(bottomSheetCallback);
    }

    public void setBlurTarget(View view) {
        this.mBlurTargetView = view;
    }

    public void setExpandedOffset(int i2) {
        WeakReference<V> weakReference;
        if (this.expandedOffset != i2) {
            this.expandedOffset = i2;
            if (SheetBehaviorCompat.isExpanded(this.state) && (weakReference = this.viewRef) != null) {
                ViewCompat.offsetTopAndBottom((View) weakReference.get(), i2);
                dispatchOnSlide(i2);
            }
        }
    }

    public void setState(int i2) {
        String str;
        if (i2 == 1 || i2 == 2) {
            StringBuilder sb2 = new StringBuilder("STATE_");
            if (i2 == 1) {
                str = "DRAGGING";
            } else {
                str = "SETTLING";
            }
            throw new IllegalArgumentException(C0212a.p(sb2, str, " should not be set externally."));
        }
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference == null || weakReference.get() == null) {
            setStateInternal(i2);
            return;
        }
        View view = (View) this.viewRef.get();
        runAfterLayout(view, new C0418a((Object) this, (Object) view, i2, 13));
    }

    public void setStateInternal(int i2) {
        if (this.state != i2) {
            this.state = i2;
            WeakReference<V> weakReference = this.viewRef;
            if (weakReference != null && ((View) weakReference.get()) != null) {
                if (i2 == 3) {
                    updateImportantForAccessibility(true);
                } else if (i2 == 6 || i2 == 5 || i2 == 4) {
                    updateImportantForAccessibility(false);
                }
                for (int i7 = 0; i7 < this.callbacks.size(); i7++) {
                    this.callbacks.get(i7).onStateChanged(i2);
                }
                updateAccessibilityActions();
            }
        }
    }

    public boolean shouldSkipSmoothAnimation() {
        return true;
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

        public SavedState(Parcelable parcelable, SearchPicturesLocationBehavior<?> searchPicturesLocationBehavior) {
            super(parcelable);
            this.state = searchPicturesLocationBehavior.state;
        }
    }

    public SearchPicturesLocationBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        createMaterialShapeDrawable(context);
    }

    public void onNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i2, int i7, int i8, int i10, int i11, int[] iArr) {
    }
}
