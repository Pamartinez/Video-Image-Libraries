package androidx.recyclerview.widget;

import B4.c;
import K4.a;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.Display;
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
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import android.widget.SectionIndexer;
import androidx.appcompat.R$color;
import androidx.appcompat.R$drawable;
import androidx.appcompat.R$string;
import androidx.appcompat.animation.SeslAnimationUtils;
import androidx.appcompat.animation.SeslRecoilAnimator;
import androidx.appcompat.graphics.drawable.SeslRecoilDrawable;
import androidx.appcompat.util.SeslMisc;
import androidx.appcompat.util.SeslSubheaderRoundedCorner;
import androidx.core.os.TraceCompat;
import androidx.core.util.Preconditions;
import androidx.core.util.SeslFadingEdgeHelper;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.NestedScrollingChild2;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.NestedScrollingParent2;
import androidx.core.view.SeslPointerIconCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.EdgeEffectCompat;
import androidx.core.widget.NestedScrollView;
import androidx.core.widget.SeslGoToTopConfig;
import androidx.core.widget.SeslGoToTopController;
import androidx.core.widget.SeslGoToTopControllerFactory;
import androidx.core.widget.SeslGoToTopImageView;
import androidx.core.widget.SeslScrollable;
import androidx.customview.poolingcontainer.PoolingContainer;
import androidx.customview.view.AbsSavedState;
import androidx.recyclerview.R$attr;
import androidx.recyclerview.R$dimen;
import androidx.recyclerview.R$styleable;
import androidx.recyclerview.widget.AdapterHelper;
import androidx.recyclerview.widget.ChildHelper;
import androidx.recyclerview.widget.GapWorker;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;
import androidx.recyclerview.widget.ViewBoundsCheck;
import androidx.recyclerview.widget.ViewInfoStore;
import androidx.reflect.os.SeslSystemPropertiesReflector;
import androidx.reflect.view.SeslPointerIconReflector;
import androidx.reflect.view.SeslViewReflector;
import androidx.reflect.widget.SeslOverScrollerReflector;
import androidx.reflect.widget.SeslTextViewReflector;
import c0.C0086a;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.OCDHelperConstant;
import com.samsung.android.gallery.widget.listview.scroller.GoToTopDelegate;
import com.samsung.android.sum.core.types.NumericEnum;
import f0.C0185a;
import i.C0212a;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecyclerView extends ViewGroup implements NestedScrollingChild2, SeslScrollable {
    static final boolean ALLOW_SIZE_IN_UNSPECIFIED_SPEC = true;
    static final boolean ALLOW_THREAD_GAP_WORK = true;
    private static final float DECELERATION_RATE = ((float) (Math.log(0.78d) / Math.log(0.9d)));
    private static final boolean FORCE_ABS_FOCUS_SEARCH_DIRECTION = false;
    static final boolean FORCE_INVALIDATE_DISPLAY_LIST = false;
    /* access modifiers changed from: private */
    public static float HOVERSCROLL_SPEED = 10.0f;
    private static final boolean IGNORE_DETACHED_FOCUSED_CHILD = false;
    private static final Class<?>[] LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE;
    private static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    private static final int[] NESTED_SCROLLING_ATTRS = {16843830};
    static final boolean POST_UPDATES_ON_ANIMATION = true;
    static boolean sDebugAssertionsEnabled = false;
    static final StretchEdgeEffectFactory sDefaultEdgeEffectFactory = new StretchEdgeEffectFactory();
    static final Interpolator sQuinticInterpolator = new Interpolator() {
        public float getInterpolation(float f) {
            float f5 = f - 1.0f;
            return (f5 * f5 * f5 * f5 * f5) + 1.0f;
        }
    };
    static boolean sVerboseLoggingEnabled = false;
    private final int ON_ABSORB_VELOCITY;
    RecyclerViewAccessibilityDelegate mAccessibilityDelegate;
    private final AccessibilityManager mAccessibilityManager;
    Adapter mAdapter;
    AdapterHelper mAdapterHelper;
    boolean mAdapterUpdateDuringMeasure;
    private Animator.AnimatorListener mAnimListener;
    /* access modifiers changed from: private */
    public int mAnimatedBlackTop;
    private Rect mAvailableBounds;
    int mBlackTop;
    /* access modifiers changed from: private */
    public EdgeEffect mBottomGlow;
    Rect mChildBound;
    private ChildDrawingOrderCallback mChildDrawingOrderCallback;
    ChildHelper mChildHelper;
    boolean mClipToPadding;
    /* access modifiers changed from: private */
    public View mCloseChildByBottom;
    /* access modifiers changed from: private */
    public View mCloseChildByTop;
    /* access modifiers changed from: private */
    public int mCloseChildPositionByBottom;
    /* access modifiers changed from: private */
    public int mCloseChildPositionByTop;
    /* access modifiers changed from: private */
    public Context mContext;
    boolean mDataSetHasChangedAfterLayout;
    private boolean mDebugDrawAvailRect;
    boolean mDispatchItemsChangedEvent;
    private int mDispatchScrollCounter;
    /* access modifiers changed from: private */
    public int mDistanceFromCloseChildBottom;
    /* access modifiers changed from: private */
    public int mDistanceFromCloseChildTop;
    private boolean mDrawHorizontalPadding;
    private boolean mDrawLastRoundedCorner;
    private boolean mDrawRect;
    private boolean mDrawReverse;
    private int mEatenAccessibilityChangeFlags;
    /* access modifiers changed from: private */
    public boolean mEdgeEffectByDragging;
    private EdgeEffectFactory mEdgeEffectFactory;
    boolean mEnableFastScroller;
    private int mExtraPaddingInBottomHoverArea;
    private int mExtraPaddingInTopHoverArea;
    private final SeslFadingEdgeHelper mFadingEdgeHelper;
    boolean mFirstLayoutComplete;
    /* access modifiers changed from: private */
    public float mFrameLatency;
    GapWorker mGapWorker;
    private SeslGoToTopController mGoToTopController;
    private final Runnable mGoToTopEdgeEffectRunnable;
    private final SeslGoToTopController.Host mGoToTopHost;
    boolean mHasFixedSize;
    private boolean mHasNestedScrollRange;
    private boolean mHoverAreaEnter;
    /* access modifiers changed from: private */
    public int mHoverBottomAreaHeight;
    private int mHoverDefaultBottomAreaHeight;
    private int mHoverDefaultTopAreaHeight;
    /* access modifiers changed from: private */
    public Handler mHoverHandler;
    /* access modifiers changed from: private */
    public long mHoverRecognitionCurrentTime;
    /* access modifiers changed from: private */
    public long mHoverRecognitionDurationTime;
    /* access modifiers changed from: private */
    public long mHoverRecognitionStartTime;
    int[] mHoverScrollArrows;
    /* access modifiers changed from: private */
    public int mHoverScrollDirection;
    private boolean mHoverScrollEnable;
    /* access modifiers changed from: private */
    public int mHoverScrollSpeed;
    /* access modifiers changed from: private */
    public long mHoverScrollStartTime;
    private boolean mHoverScrollStateChanged;
    /* access modifiers changed from: private */
    public int mHoverScrollStateForListener;
    /* access modifiers changed from: private */
    public long mHoverScrollTimeInterval;
    private int mHoverTopAreaHeight;
    private boolean mIgnoreMotionEventTillDown;
    /* access modifiers changed from: private */
    public IndexTip mIndexTip;
    private boolean mIndexTipEnabled;
    private int mInitialTopOffsetOfScreen;
    private int mInitialTouchX;
    private int mInitialTouchY;
    private int mInterceptRequestLayoutDepth;
    private OnItemTouchListener mInterceptingOnItemTouchListener;
    /* access modifiers changed from: private */
    public boolean mIsActionScrollFromMouse;
    private boolean mIsArrowKeyPressed;
    boolean mIsAttached;
    /* access modifiers changed from: private */
    public boolean mIsCloseChildSetted;
    private boolean mIsCtrlKeyPressed;
    private boolean mIsCtrlMultiSelection;
    private boolean mIsEdgeEffectEnabled;
    private boolean mIsEnabledPaddingInHoverScroll;
    private boolean mIsFirstMultiSelectionMove;
    private boolean mIsFirstPenMoveEvent;
    /* access modifiers changed from: private */
    public boolean mIsHoverOverscrolled;
    /* access modifiers changed from: private */
    public boolean mIsLongPressMultiSelection;
    private boolean mIsNeedCheckLatency;
    private boolean mIsNeedPenSelectIconSet;
    private boolean mIsNeedPenSelection;
    private boolean mIsPenButtonPressed;
    private boolean mIsPenDragBlockEnabled;
    /* access modifiers changed from: private */
    public boolean mIsPenHovered;
    /* access modifiers changed from: private */
    public boolean mIsPenPressed;
    private boolean mIsPenSelectPointerSetted;
    private boolean mIsPenSelectionEnabled;
    private boolean mIsRecoilEnabled;
    private final boolean mIsRecoilSupported;
    /* access modifiers changed from: private */
    public boolean mIsSendHoverScrollState;
    /* access modifiers changed from: private */
    public boolean mIsSetOnlyAddAnim;
    /* access modifiers changed from: private */
    public boolean mIsSetOnlyRemoveAnim;
    /* access modifiers changed from: private */
    public boolean mIsSkipMoveEvent;
    ItemAnimator mItemAnimator;
    private SeslRecoilAnimator.Holder mItemAnimatorHolder;
    private ItemAnimator.ItemAnimatorListener mItemAnimatorListener;
    private Runnable mItemAnimatorRunner;
    private ItemBackgroundHolder mItemBackgroundHolder;
    final ArrayList<ItemDecoration> mItemDecorations;
    boolean mItemsAddedOrRemoved;
    boolean mItemsChanged;
    private int mLastAutoMeasureNonExactMeasuredHeight;
    private int mLastAutoMeasureNonExactMeasuredWidth;
    private boolean mLastAutoMeasureSkippedDueToExact;
    private int mLastBlackTop;
    /* access modifiers changed from: private */
    public ValueAnimator mLastItemAddRemoveAnim;
    private int mLastItemAnimTop;
    private int mLastTouchX;
    private int mLastTouchY;
    LayoutManager mLayout;
    private int mLayoutOrScrollCounter;
    boolean mLayoutSuppressed;
    boolean mLayoutWasDefered;
    /* access modifiers changed from: private */
    public EdgeEffect mLeftGlow;
    Rect mListPadding;
    private SeslLongPressMultiSelectionListener mLongPressMultiSelectionListener;
    private int mMaxFlingVelocity;
    private int mMinFlingVelocity;
    private final int[] mMinMaxLayoutPositions;
    private final int mMotionEventUpPendingFlag;
    private int mNaviBarTop;
    private boolean mNeedsHoverScroll;
    private final int[] mNestedOffsets;
    private int mNestedScrollRange;
    private boolean mNewTextViewHoverState;
    private final RecyclerViewDataObserver mObserver;
    /* access modifiers changed from: private */
    public int mOldHoverScrollDirection;
    private boolean mOldTextViewHoverState;
    private List<OnChildAttachStateChangeListener> mOnChildAttachStateListeners;
    private OnFlingListener mOnFlingListener;
    private SeslOnGoToTopClickListener mOnGoToTopClickListener;
    private List<SeslScrollable.SeslOnGoToTopClickListener> mOnGoToTopClickListeners;
    private final ArrayList<OnItemTouchListener> mOnItemTouchListeners;
    private int mPagingTouchSlop;
    /* access modifiers changed from: private */
    public int mPenDistanceFromTrackedChildTop;
    private int mPenDragBlockBottom;
    private Drawable mPenDragBlockImage;
    private int mPenDragBlockLeft;
    private Rect mPenDragBlockRect;
    private int mPenDragBlockRight;
    private int mPenDragBlockTop;
    /* access modifiers changed from: private */
    public int mPenDragEndX;
    /* access modifiers changed from: private */
    public int mPenDragEndY;
    /* access modifiers changed from: private */
    public long mPenDragScrollTimeInterval;
    private ArrayList<Integer> mPenDragSelectedItemArray;
    private int mPenDragSelectedViewPosition;
    private int mPenDragStartX;
    private int mPenDragStartY;
    /* access modifiers changed from: private */
    public View mPenTrackedChild;
    /* access modifiers changed from: private */
    public int mPenTrackedChildPosition;
    final List<ViewHolder> mPendingAccessibilityImportanceChange;
    SavedState mPendingSavedState;
    private final float mPhysicalCoef;
    private float mPointerIconRotation;
    boolean mPostedAnimatorRunner;
    GapWorker.LayoutPrefetchRegistryImpl mPrefetchRegistry;
    private boolean mPreserveFocusAfterLayout;
    private boolean mPreventFirstGlow;
    private int mRectColor;
    private Paint mRectPaint;
    final Recycler mRecycler;
    final List<RecyclerListener> mRecyclerListeners;
    private final int[] mRecyclerViewOffsets;
    /* access modifiers changed from: private */
    public int mRemainNestedScrollRange;
    final int[] mReusableIntPair;
    /* access modifiers changed from: private */
    public EdgeEffect mRightGlow;
    private View mRootViewCheckForDialog;
    private SeslSubheaderRoundedCorner mRoundedCorner;
    private float mScaledHorizontalScrollFactor;
    private float mScaledVerticalScrollFactor;
    private int mScrollBarBottomOffset;
    private int mScrollBarTopOffset;
    private final SeslFadingEdgeHelper.ScrollInfoProvider mScrollInfoProvider;
    /* access modifiers changed from: private */
    public OnScrollListener mScrollListener;
    private List<OnScrollListener> mScrollListeners;
    /* access modifiers changed from: private */
    public final int[] mScrollOffset;
    private int mScrollPointerId;
    /* access modifiers changed from: private */
    public int mScrollState;
    private int mScrollbarBottomPadding;
    private int mScrollbarTopPadding;
    private NestedScrollingChildHelper mScrollingChildHelper;
    Rect mSelectorRect;
    int mSeslBottomBarHeight;
    private boolean mSeslIsNested;
    private int mSeslOverlayFeatureHeight;
    final State mState;
    final Rect mTempRect;
    private final Rect mTempRect2;
    final RectF mTempRectF;
    /* access modifiers changed from: private */
    public EdgeEffect mTopGlow;
    private int mTouchSlop;
    private int mTouchSlop2;
    final Runnable mUpdateChildViewsRunnable;
    private boolean mUsePagingTouchSlopForStylus;
    private VelocityTracker mVelocityTracker;
    final ViewFlinger mViewFlinger;
    private final ViewInfoStore.ProcessCallback mViewInfoProcessCallback;
    final ViewInfoStore mViewInfoStore;
    private final int[] mWindowOffsets;

    /* renamed from: androidx.recyclerview.widget.RecyclerView$16  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass16 {
        static final /* synthetic */ int[] $SwitchMap$androidx$recyclerview$widget$RecyclerView$Adapter$StateRestorationPolicy;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                androidx.recyclerview.widget.RecyclerView$Adapter$StateRestorationPolicy[] r0 = androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$androidx$recyclerview$widget$RecyclerView$Adapter$StateRestorationPolicy = r0
                androidx.recyclerview.widget.RecyclerView$Adapter$StateRestorationPolicy r1 = androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy.PREVENT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$androidx$recyclerview$widget$RecyclerView$Adapter$StateRestorationPolicy     // Catch:{ NoSuchFieldError -> 0x001d }
                androidx.recyclerview.widget.RecyclerView$Adapter$StateRestorationPolicy r1 = androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.AnonymousClass16.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Adapter<VH extends ViewHolder> {
        private boolean mHasStableIds = false;
        private final AdapterDataObservable mObservable = new AdapterDataObservable();
        private StateRestorationPolicy mStateRestorationPolicy = StateRestorationPolicy.ALLOW;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public enum StateRestorationPolicy {
            ALLOW,
            PREVENT_WHEN_EMPTY,
            PREVENT
        }

        public final void bindViewHolder(VH vh, int i2) {
            boolean z;
            if (vh.mBindingAdapter == null) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                vh.mPosition = i2;
                if (hasStableIds()) {
                    vh.mItemId = getItemId(i2);
                }
                vh.setFlags(1, 519);
                TraceCompat.beginSection("RV OnBindView");
            }
            vh.mBindingAdapter = this;
            if (RecyclerView.sDebugAssertionsEnabled) {
                if (vh.itemView.getParent() == null && ViewCompat.isAttachedToWindow(vh.itemView) != vh.isTmpDetached()) {
                    throw new IllegalStateException("Temp-detached state out of sync with reality. holder.isTmpDetached(): " + vh.isTmpDetached() + ", attached to window: " + ViewCompat.isAttachedToWindow(vh.itemView) + ", holder: " + vh);
                } else if (vh.itemView.getParent() == null && ViewCompat.isAttachedToWindow(vh.itemView)) {
                    throw new IllegalStateException("Attempting to bind attached holder with no parent (AKA temp detached): " + vh);
                }
            }
            onBindViewHolder(vh, i2, vh.getUnmodifiedPayloads());
            if (z) {
                vh.clearPayload();
                ViewGroup.LayoutParams layoutParams = vh.itemView.getLayoutParams();
                if (layoutParams instanceof LayoutParams) {
                    ((LayoutParams) layoutParams).mInsetsDirty = true;
                }
                TraceCompat.endSection();
            }
        }

        public boolean canRestoreState() {
            int i2 = AnonymousClass16.$SwitchMap$androidx$recyclerview$widget$RecyclerView$Adapter$StateRestorationPolicy[this.mStateRestorationPolicy.ordinal()];
            if (i2 == 1 || (i2 == 2 && getItemCount() <= 0)) {
                return false;
            }
            return true;
        }

        public final VH createViewHolder(ViewGroup viewGroup, int i2) {
            try {
                TraceCompat.beginSection("RV CreateView");
                VH onCreateViewHolder = onCreateViewHolder(viewGroup, i2);
                if (onCreateViewHolder.itemView.getParent() == null) {
                    onCreateViewHolder.mItemViewType = i2;
                    return onCreateViewHolder;
                }
                throw new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
            } finally {
                TraceCompat.endSection();
            }
        }

        public int findRelativeAdapterPositionIn(Adapter<? extends ViewHolder> adapter, ViewHolder viewHolder, int i2) {
            if (adapter == this) {
                return i2;
            }
            return -1;
        }

        public abstract int getItemCount();

        public long getItemId(int i2) {
            return -1;
        }

        public int getItemViewType(int i2) {
            return 0;
        }

        public final boolean hasObservers() {
            return this.mObservable.hasObservers();
        }

        public final boolean hasStableIds() {
            return this.mHasStableIds;
        }

        public final void notifyDataSetChanged() {
            this.mObservable.notifyChanged();
        }

        public final void notifyItemChanged(int i2) {
            this.mObservable.notifyItemRangeChanged(i2, 1);
        }

        public final void notifyItemInserted(int i2) {
            this.mObservable.notifyItemRangeInserted(i2, 1);
        }

        public final void notifyItemMoved(int i2, int i7) {
            this.mObservable.notifyItemMoved(i2, i7);
        }

        public final void notifyItemRangeChanged(int i2, int i7) {
            this.mObservable.notifyItemRangeChanged(i2, i7);
        }

        public final void notifyItemRangeInserted(int i2, int i7) {
            this.mObservable.notifyItemRangeInserted(i2, i7);
        }

        public final void notifyItemRangeRemoved(int i2, int i7) {
            this.mObservable.notifyItemRangeRemoved(i2, i7);
        }

        public final void notifyItemRemoved(int i2) {
            this.mObservable.notifyItemRangeRemoved(i2, 1);
        }

        public abstract void onBindViewHolder(VH vh, int i2);

        public void onBindViewHolder(VH vh, int i2, List<Object> list) {
            onBindViewHolder(vh, i2);
        }

        public abstract VH onCreateViewHolder(ViewGroup viewGroup, int i2);

        public boolean onFailedToRecycleView(VH vh) {
            return false;
        }

        public void registerAdapterDataObserver(AdapterDataObserver adapterDataObserver) {
            this.mObservable.registerObserver(adapterDataObserver);
        }

        public int seslGetAccessibilityItemCount() {
            return getItemCount();
        }

        public boolean seslUseCustomAccessibilityPosition() {
            return false;
        }

        public void setHasStableIds(boolean z) {
            if (!hasObservers()) {
                this.mHasStableIds = z;
                return;
            }
            throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
        }

        public void unregisterAdapterDataObserver(AdapterDataObserver adapterDataObserver) {
            this.mObservable.unregisterObserver(adapterDataObserver);
        }

        public final void notifyItemChanged(int i2, Object obj) {
            this.mObservable.notifyItemRangeChanged(i2, 1, obj);
        }

        public final void notifyItemRangeChanged(int i2, int i7, Object obj) {
            this.mObservable.notifyItemRangeChanged(i2, i7, obj);
        }

        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        }

        public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        }

        public void onViewAttachedToWindow(VH vh) {
        }

        public void onViewDetachedFromWindow(VH vh) {
        }

        public void onViewRecycled(VH vh) {
        }

        public int seslGetAccessibilityItemPosition(int i2) {
            return i2;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class AdapterDataObservable extends Observable<AdapterDataObserver> {
        public boolean hasObservers() {
            return !this.mObservers.isEmpty();
        }

        public void notifyChanged() {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) this.mObservers.get(size)).onChanged();
            }
        }

        public void notifyItemMoved(int i2, int i7) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) this.mObservers.get(size)).onItemRangeMoved(i2, i7, 1);
            }
        }

        public void notifyItemRangeChanged(int i2, int i7) {
            notifyItemRangeChanged(i2, i7, (Object) null);
        }

        public void notifyItemRangeInserted(int i2, int i7) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) this.mObservers.get(size)).onItemRangeInserted(i2, i7);
            }
        }

        public void notifyItemRangeRemoved(int i2, int i7) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) this.mObservers.get(size)).onItemRangeRemoved(i2, i7);
            }
        }

        public void notifyItemRangeChanged(int i2, int i7, Object obj) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) this.mObservers.get(size)).onItemRangeChanged(i2, i7, obj);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class AdapterDataObserver {
        public void onItemRangeChanged(int i2, int i7) {
        }

        public void onItemRangeChanged(int i2, int i7, Object obj) {
            onItemRangeChanged(i2, i7);
        }

        public void onChanged() {
        }

        public void onItemRangeInserted(int i2, int i7) {
        }

        public void onItemRangeRemoved(int i2, int i7) {
        }

        public void onItemRangeMoved(int i2, int i7, int i8) {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ChildDrawingOrderCallback {
        int onGetChildDrawingOrder(int i2, int i7);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class EdgeEffectFactory {
        public abstract EdgeEffect createEdgeEffect(RecyclerView recyclerView, int i2);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class IndexTip extends View {
        private final PathInterpolator ALPHA_INTERPOLATOR;
        private final PathInterpolator SCALE_INTERPOLATOR;
        /* access modifiers changed from: private */
        public float mAnimatingWidth;
        private int mCenterX;
        private int mCurrentOrientation;
        private boolean mForcedHide;
        private int mHeight;
        private int mHorizontalPadding;
        private final Animator.AnimatorListener mIndexTipTimerListener;
        private boolean mIsNeedUpdate;
        /* access modifiers changed from: private */
        public boolean mIsShowing;
        private int mMaxWidth;
        private int mMinWidth;
        private int mParentPosY;
        private String mPrevText;
        private float mPrevWidth;
        private float mRadius;
        private SectionIndexer mSectionIndexer;
        private Object[] mSections;
        private final Runnable mShapeDelayRunnable;
        private Paint mShapePaint;
        private int mStatusBarHeight;
        private String mTargetText;
        private String mText;
        private final Runnable mTextDelayRunnable;
        /* access modifiers changed from: private */
        public StaticLayout mTextLayout;
        private StaticLayout.Builder mTextLayoutBuilder;
        /* access modifiers changed from: private */
        public StaticLayout mTextLayoutDelay;
        private TextPaint mTextPaint;
        private ValueAnimator mTimer;
        private int mTopMargin;
        private int mVerticalPadding;
        private int mWidth;
        private ValueAnimator mWidthAnimator;

        private void calculateTextLines() {
            int i2 = this.mWidth;
            int i7 = ((i2 / 2) - this.mHorizontalPadding) * 2;
            if (i7 > 0) {
                i2 = i7;
            }
            String str = this.mText;
            float lineWidth = StaticLayout.Builder.obtain(str, 0, str.length(), this.mTextPaint, i2).build().getLineWidth(0);
            String str2 = this.mText;
            StaticLayout.Builder obtain = StaticLayout.Builder.obtain(str2, 0, str2.length(), this.mTextPaint, (int) lineWidth);
            this.mTextLayoutBuilder = obtain;
            obtain.setAlignment(Layout.Alignment.ALIGN_CENTER);
            this.mTextLayout = this.mTextLayoutBuilder.setMaxLines(2).setEllipsize(TextUtils.TruncateAt.END).build();
        }

        private void changeText(boolean z) {
            if (z) {
                this.mTargetText = this.mText;
                removeCallbacks(this.mTextDelayRunnable);
                postDelayed(this.mTextDelayRunnable, 90);
                return;
            }
            this.mTargetText = this.mText;
            this.mTextLayoutDelay = this.mTextLayout;
        }

        /* access modifiers changed from: private */
        public void forcedHide() {
            this.mIsShowing = false;
            removeCallbacks(this.mShapeDelayRunnable);
            setAlpha(0.0f);
            invalidate();
        }

        private int getColorWithAlpha(int i2, float f) {
            return Color.argb(Math.round(((float) Color.alpha(i2)) * f), Color.red(i2), Color.green(i2), Color.blue(i2));
        }

        /* access modifiers changed from: private */
        public void hide() {
            if (this.mIsShowing) {
                removeCallbacks(this.mShapeDelayRunnable);
                postDelayed(this.mShapeDelayRunnable, 300);
            } else {
                forcedHide();
            }
            boolean unused = RecyclerView.this.mIsActionScrollFromMouse = false;
        }

        private void init() {
            int i2;
            this.mSectionIndexer = (SectionIndexer) RecyclerView.this.mAdapter;
            updateSections();
            Resources resources = RecyclerView.this.mContext.getResources();
            if (SeslMisc.isLightTheme(RecyclerView.this.mContext)) {
                i2 = resources.getColor(R$color.sesl_scrollbar_index_tip_color);
            } else {
                i2 = resources.getColor(R$color.sesl_scrollbar_index_tip_color_dark);
            }
            Paint paint = new Paint();
            this.mShapePaint = paint;
            paint.setStyle(Paint.Style.FILL);
            this.mShapePaint.setAntiAlias(true);
            this.mShapePaint.setColor(getColorWithAlpha(i2, 0.9f));
            TextPaint textPaint = new TextPaint();
            this.mTextPaint = textPaint;
            textPaint.setAntiAlias(true);
            if (Build.VERSION.SDK_INT >= 34) {
                this.mTextPaint.setTypeface(Typeface.create(Typeface.create("sec", 0), 400, false));
            } else {
                this.mTextPaint.setTypeface(Typeface.create(RecyclerView.this.mContext.getString(R$string.sesl_font_family_regular), 0));
            }
            this.mTextPaint.setTextSize((float) resources.getDimensionPixelSize(R$dimen.sesl_index_tip_text_size));
            this.mTextPaint.setColor(resources.getColor(R$color.sesl_white));
            this.mText = "";
            int length = "".length();
            TextPaint textPaint2 = this.mTextPaint;
            StaticLayout build = StaticLayout.Builder.obtain("", 0, length, textPaint2, (int) textPaint2.measureText(this.mText)).build();
            this.mTextLayout = build;
            this.mTextLayoutDelay = build;
            this.mPrevText = "";
            this.mPrevWidth = 0.0f;
            this.mAnimatingWidth = 0.0f;
            this.mHorizontalPadding = resources.getDimensionPixelSize(R$dimen.sesl_index_tip_horizontal_padding);
            this.mVerticalPadding = resources.getDimensionPixelSize(R$dimen.sesl_index_tip_vertical_padding);
            this.mMinWidth = resources.getDimensionPixelSize(R$dimen.sesl_index_tip_min_width);
            this.mMaxWidth = resources.getDimensionPixelSize(R$dimen.sesl_index_tip_max_width);
            this.mTopMargin = resources.getDimensionPixelSize(R$dimen.sesl_index_tip_margin_top);
            this.mRadius = resources.getDimension(R$dimen.sesl_index_tip_radius);
            this.mParentPosY = 0;
            int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
            if (identifier > 0) {
                this.mStatusBarHeight = RecyclerView.this.mContext.getResources().getDimensionPixelSize(identifier);
            } else {
                this.mStatusBarHeight = 0;
            }
            setAlpha(0.0f);
        }

        private void resetTimer() {
            ValueAnimator valueAnimator = this.mTimer;
            if (valueAnimator != null) {
                valueAnimator.removeAllListeners();
                this.mTimer.cancel();
            }
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 0.0f});
            this.mTimer = ofFloat;
            ofFloat.setDuration(450);
            this.mTimer.addListener(this.mIndexTipTimerListener);
            this.mTimer.start();
        }

        /* access modifiers changed from: private */
        public void setLayout(int i2, int i7, int i8, int i10, int i11, int i12) {
            layout(i2, i7, i8, i10);
            int i13 = ((i8 - i2) - i11) - i12;
            int i14 = this.mHorizontalPadding;
            if (i13 > i14 * 2) {
                int i15 = this.mMaxWidth;
                if (i13 < i15) {
                    i15 = i13 - (i14 * 2);
                }
                this.mWidth = i15;
            }
            this.mCenterX = Math.round(((float) i13) / 2.0f) + i11;
            int i16 = RecyclerView.this.mContext.getResources().getConfiguration().orientation;
            this.mCurrentOrientation = i16;
            if (i16 == 2) {
                this.mIsNeedUpdate = false;
            }
            hide();
        }

        /* access modifiers changed from: private */
        public void show(int i2, int i7) {
            if (i2 == 1 && RecyclerView.this.mRemainNestedScrollRange != 0 && i7 >= 0) {
                RecyclerView.this.adjustNestedScrollRange();
            } else if (i7 != 0 && !this.mIsShowing && RecyclerView.this.canScrollUp() && !this.mForcedHide) {
                startAnimation();
                this.mIsShowing = true;
            }
        }

        /* access modifiers changed from: private */
        public void showWithTimer(int i2, int i7) {
            show(i2, i7);
            resetTimer();
        }

        /* access modifiers changed from: private */
        public void startAnimation() {
            ObjectAnimator objectAnimator;
            if (this.mIsShowing) {
                objectAnimator = ObjectAnimator.ofFloat(RecyclerView.this.mIndexTip, "alpha", new float[]{RecyclerView.this.mIndexTip.getAlpha(), 0.0f});
            } else {
                objectAnimator = ObjectAnimator.ofFloat(RecyclerView.this.mIndexTip, "alpha", new float[]{RecyclerView.this.mIndexTip.getAlpha(), 1.0f});
            }
            objectAnimator.setDuration(150);
            objectAnimator.setInterpolator(this.ALPHA_INTERPOLATOR);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(objectAnimator);
            animatorSet.start();
        }

        private void startWidthAnimation(float f) {
            ValueAnimator valueAnimator = this.mWidthAnimator;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{this.mAnimatingWidth, f});
            this.mWidthAnimator = ofFloat;
            ofFloat.setDuration(200);
            this.mWidthAnimator.setInterpolator(this.SCALE_INTERPOLATOR);
            this.mWidthAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float unused = IndexTip.this.mAnimatingWidth = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    IndexTip.this.invalidate();
                }
            });
            this.mWidthAnimator.start();
        }

        /* access modifiers changed from: private */
        public void updateSections() {
            SectionIndexer sectionIndexer = this.mSectionIndexer;
            if (sectionIndexer != null) {
                Object[] sections = sectionIndexer.getSections();
                this.mSections = sections;
                if (sections != null) {
                    hide();
                    return;
                }
                throw new IllegalStateException("Section is null. This array, or its contents should be non-null");
            }
        }

        private void updateText() {
            Object obj;
            this.mText = "";
            int findFirstVisibleItemPosition = RecyclerView.this.findFirstVisibleItemPosition();
            if (findFirstVisibleItemPosition == -1) {
                Log.e("SeslRecyclerView", "First visible item was null.");
                return;
            }
            int sectionForPosition = this.mSectionIndexer.getSectionForPosition(findFirstVisibleItemPosition);
            if (sectionForPosition >= 0) {
                Object[] objArr = this.mSections;
                if (sectionForPosition < objArr.length && (obj = objArr[sectionForPosition]) != null) {
                    String obj2 = obj.toString();
                    this.mText = obj2;
                    int length = obj2.length();
                    TextPaint textPaint = this.mTextPaint;
                    StaticLayout.Builder obtain = StaticLayout.Builder.obtain(obj2, 0, length, textPaint, (int) textPaint.measureText(this.mText));
                    this.mTextLayoutBuilder = obtain;
                    this.mTextLayout = obtain.build();
                }
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:41:0x0110  */
        /* JADX WARNING: Removed duplicated region for block: B:47:0x012f  */
        /* JADX WARNING: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onDraw(android.graphics.Canvas r13) {
            /*
                r12 = this;
                super.onDraw(r13)
                r12.updateText()
                java.lang.String r0 = r12.mText
                java.lang.String r1 = ""
                boolean r0 = r0.equals(r1)
                r8 = 1
                r9 = 0
                if (r0 == 0) goto L_0x0030
                java.lang.String r0 = r12.mPrevText
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x001c
                goto L_0x0135
            L_0x001c:
                boolean r0 = r12.mForcedHide
                if (r0 != 0) goto L_0x002b
                boolean r0 = r12.mIsShowing
                if (r0 == 0) goto L_0x002b
                r12.startAnimation()
                r12.mIsShowing = r9
                r12.mForcedHide = r8
            L_0x002b:
                java.lang.String r0 = r12.mPrevText
                r12.mText = r0
                goto L_0x0032
            L_0x0030:
                r12.mForcedHide = r9
            L_0x0032:
                android.text.TextPaint r0 = r12.mTextPaint
                java.lang.String r2 = r12.mText
                float r0 = r0.measureText(r2)
                r10 = 1073741824(0x40000000, float:2.0)
                float r0 = r0 / r10
                int r2 = r12.mHorizontalPadding
                float r2 = (float) r2
                float r0 = r0 + r2
                int r2 = r12.mMinWidth
                float r3 = (float) r2
                float r3 = r3 / r10
                int r3 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
                if (r3 >= 0) goto L_0x004c
                float r0 = (float) r2
                float r0 = r0 / r10
                goto L_0x0064
            L_0x004c:
                int r2 = r12.mWidth
                if (r2 <= 0) goto L_0x0064
                float r2 = (float) r2
                float r2 = r2 / r10
                int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r2 <= 0) goto L_0x0064
                r12.calculateTextLines()
                android.text.StaticLayout r0 = r12.mTextLayout
                float r0 = r0.getLineWidth(r9)
                float r0 = r0 / r10
                int r2 = r12.mHorizontalPadding
                float r2 = (float) r2
                float r0 = r0 + r2
            L_0x0064:
                android.text.StaticLayout r2 = r12.mTextLayoutDelay
                java.lang.CharSequence r2 = r2.getText()
                boolean r1 = r2.equals(r1)
                if (r1 == 0) goto L_0x0078
                java.lang.String r1 = r12.mText
                r12.mTargetText = r1
                android.text.StaticLayout r1 = r12.mTextLayout
                r12.mTextLayoutDelay = r1
            L_0x0078:
                int r1 = r12.mCenterX
                float r2 = (float) r1
                int r2 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
                if (r2 >= 0) goto L_0x0080
                float r0 = (float) r1
            L_0x0080:
                r11 = r0
                float r0 = r12.mPrevWidth
                r1 = 0
                int r2 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
                if (r2 <= 0) goto L_0x008f
                int r0 = (r0 > r11 ? 1 : (r0 == r11 ? 0 : -1))
                if (r0 == 0) goto L_0x008f
                r12.startWidthAnimation(r11)
            L_0x008f:
                float r0 = r12.mAnimatingWidth
                int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
                if (r0 != 0) goto L_0x0097
                r12.mAnimatingWidth = r11
            L_0x0097:
                android.text.StaticLayout r0 = r12.mTextLayout
                int r1 = r0.getLineCount()
                int r1 = r1 - r8
                int r0 = r0.getLineBottom(r1)
                android.text.StaticLayout r1 = r12.mTextLayout
                int r1 = r1.getLineTop(r9)
                int r0 = r0 - r1
                int r1 = r12.mVerticalPadding
                int r1 = r1 * 2
                int r1 = r1 + r0
                r12.mHeight = r1
                boolean r0 = r12.mIsNeedUpdate
                if (r0 == 0) goto L_0x00c2
                androidx.recyclerview.widget.RecyclerView r0 = androidx.recyclerview.widget.RecyclerView.this
                int r0 = r0.getRecyclerViewScreenLocationY()
                r12.mParentPosY = r0
                int r1 = r12.mStatusBarHeight
                if (r0 >= r1) goto L_0x00c2
                int r1 = r1 - r0
                goto L_0x00c3
            L_0x00c2:
                r1 = r9
            L_0x00c3:
                r13.save()
                int r0 = r12.mCenterX
                float r2 = (float) r0
                float r3 = r12.mAnimatingWidth
                float r2 = r2 - r3
                int r4 = r12.mTopMargin
                int r5 = r4 + r1
                float r5 = (float) r5
                float r0 = (float) r0
                float r3 = r3 + r0
                int r0 = r12.mHeight
                int r4 = r4 + r0
                int r4 = r4 + r1
                float r4 = (float) r4
                r1 = r2
                r2 = r5
                float r5 = r12.mRadius
                android.graphics.Paint r7 = r12.mShapePaint
                r6 = r5
                r0 = r13
                r0.drawRoundRect(r1, r2, r3, r4, r5, r6, r7)
                int r1 = r12.mCenterX
                float r1 = (float) r1
                android.text.StaticLayout r2 = r12.mTextLayoutDelay
                float r2 = r2.getLineWidth(r9)
                float r2 = r2 / r10
                float r1 = r1 - r2
                android.text.StaticLayout r2 = r12.mTextLayoutDelay
                int r2 = r2.getLineTop(r9)
                int r3 = r12.mTopMargin
                int r2 = r2 + r3
                int r3 = r12.mVerticalPadding
                int r2 = r2 + r3
                float r2 = (float) r2
                r13.translate(r1, r2)
                android.text.StaticLayout r1 = r12.mTextLayoutDelay
                r1.draw(r13)
                r13.restore()
                java.lang.String r0 = r12.mText
                java.lang.String r1 = r12.mTargetText
                boolean r0 = r0.equals(r1)
                if (r0 != 0) goto L_0x0125
                java.lang.String r0 = r12.mText
                int r0 = r0.length()
                java.lang.String r1 = r12.mTargetText
                int r1 = r1.length()
                if (r0 <= r1) goto L_0x0122
                r12.changeText(r8)
                goto L_0x0125
            L_0x0122:
                r12.changeText(r9)
            L_0x0125:
                java.lang.String r0 = r12.mText
                java.lang.String r1 = r12.mPrevText
                boolean r0 = r0.equals(r1)
                if (r0 != 0) goto L_0x0135
                java.lang.String r0 = r12.mText
                r12.mPrevText = r0
                r12.mPrevWidth = r11
            L_0x0135:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.IndexTip.onDraw(android.graphics.Canvas):void");
        }

        private IndexTip(Context context) {
            super(context);
            this.mIsNeedUpdate = false;
            this.mIsShowing = false;
            this.mForcedHide = false;
            this.mTimer = null;
            this.mIndexTipTimerListener = new Animator.AnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                    IndexTip.this.hide();
                }

                public void onAnimationEnd(Animator animator) {
                    IndexTip.this.hide();
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                }
            };
            this.ALPHA_INTERPOLATOR = new PathInterpolator(0.0f, 0.0f, 1.0f, 1.0f);
            this.SCALE_INTERPOLATOR = new PathInterpolator(0.22f, 0.25f, 0.0f, 1.0f);
            this.mTextDelayRunnable = new Runnable() {
                public void run() {
                    if (RecyclerView.this.mIndexTip != null) {
                        IndexTip indexTip = IndexTip.this;
                        StaticLayout unused = indexTip.mTextLayoutDelay = indexTip.mTextLayout;
                        IndexTip.this.invalidate();
                    }
                }
            };
            this.mShapeDelayRunnable = new Runnable() {
                public void run() {
                    if (RecyclerView.this.mIndexTip != null && IndexTip.this.mIsShowing) {
                        IndexTip.this.startAnimation();
                        boolean unused = IndexTip.this.mIsShowing = false;
                    }
                }
            };
            init();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ItemAnimatorRestoreListener implements ItemAnimator.ItemAnimatorListener {
        public ItemAnimatorRestoreListener() {
        }

        public void onAnimationFinished(ViewHolder viewHolder) {
            viewHolder.setIsRecyclable(true);
            if (viewHolder.mShadowedHolder != null && viewHolder.mShadowingHolder == null) {
                viewHolder.mShadowedHolder = null;
            }
            viewHolder.mShadowingHolder = null;
            Iterator<ItemDecoration> it = RecyclerView.this.mItemDecorations.iterator();
            while (it.hasNext()) {
                ItemDecoration next = it.next();
                if (next instanceof ItemTouchHelper) {
                    ((ItemTouchHelper) next).endRecoverAnimation(viewHolder, false);
                }
            }
            if (!viewHolder.shouldBeKeptAsChild() && !RecyclerView.this.removeAnimatingView(viewHolder.itemView) && viewHolder.isTmpDetached()) {
                RecyclerView.this.removeDetachedView(viewHolder.itemView, false);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ItemBackgroundHolder {
        /* access modifiers changed from: private */
        public SeslRecoilDrawable mActiveBg = null;

        public ItemBackgroundHolder() {
        }

        public void setCancel() {
            SeslRecoilDrawable seslRecoilDrawable = this.mActiveBg;
            if (seslRecoilDrawable != null) {
                seslRecoilDrawable.setCancel();
            }
        }

        public void setPress(View view) {
            setRelease();
            if (view.getBackground() instanceof SeslRecoilDrawable) {
                SeslRecoilDrawable seslRecoilDrawable = (SeslRecoilDrawable) view.getBackground();
                this.mActiveBg = seslRecoilDrawable;
                seslRecoilDrawable.setState(new int[]{16843623});
                this.mActiveBg.setListener(new SeslRecoilDrawable.SeslRecoilDrawableListener() {
                    public void onReleaseAnimationStart() {
                        ItemBackgroundHolder.this.mActiveBg.removeListener();
                        SeslRecoilDrawable unused = ItemBackgroundHolder.this.mActiveBg = null;
                    }
                });
            }
        }

        public void setRelease() {
            SeslRecoilDrawable seslRecoilDrawable = this.mActiveBg;
            if (seslRecoilDrawable != null) {
                seslRecoilDrawable.setState(new int[0]);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class ItemDecoration {
        @Deprecated
        public void getItemOffsets(Rect rect, int i2, RecyclerView recyclerView) {
            rect.set(0, 0, 0, 0);
        }

        @Deprecated
        public void onDraw(Canvas canvas, RecyclerView recyclerView) {
        }

        @Deprecated
        public void onDrawOver(Canvas canvas, RecyclerView recyclerView) {
        }

        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
            getItemOffsets(rect, ((LayoutParams) view.getLayoutParams()).getViewLayoutPosition(), recyclerView);
        }

        public void onDraw(Canvas canvas, RecyclerView recyclerView, State state) {
            onDraw(canvas, recyclerView);
        }

        public void onDrawOver(Canvas canvas, RecyclerView recyclerView, State state) {
            onDrawOver(canvas, recyclerView);
        }

        public void seslOnDispatchDraw(Canvas canvas, RecyclerView recyclerView, State state) {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class LayoutManager {
        boolean mAutoMeasure = false;
        ChildHelper mChildHelper;
        private int mHeight;
        private int mHeightMode;
        ViewBoundsCheck mHorizontalBoundCheck;
        private final ViewBoundsCheck.Callback mHorizontalBoundCheckCallback;
        boolean mIsAttachedToWindow = false;
        private boolean mItemPrefetchEnabled = true;
        private boolean mMeasurementCacheEnabled = true;
        int mPrefetchMaxCountObserved;
        boolean mPrefetchMaxObservedInInitialPrefetch;
        RecyclerView mRecyclerView;
        boolean mRequestedSimpleAnimations = false;
        SmoothScroller mSmoothScroller;
        ViewBoundsCheck mVerticalBoundCheck;
        private final ViewBoundsCheck.Callback mVerticalBoundCheckCallback;
        private int mWidth;
        private int mWidthMode;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public interface LayoutPrefetchRegistry {
            void addPosition(int i2, int i7);
        }

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Properties {
            public int orientation;
            public boolean reverseLayout;
            public int spanCount;
            public boolean stackFromEnd;
        }

        public LayoutManager() {
            AnonymousClass1 r0 = new ViewBoundsCheck.Callback() {
                public View getChildAt(int i2) {
                    return LayoutManager.this.getChildAt(i2);
                }

                public int getChildEnd(View view) {
                    return LayoutManager.this.getDecoratedRight(view) + ((LayoutParams) view.getLayoutParams()).rightMargin;
                }

                public int getChildStart(View view) {
                    return LayoutManager.this.getDecoratedLeft(view) - ((LayoutParams) view.getLayoutParams()).leftMargin;
                }

                public int getParentEnd() {
                    return LayoutManager.this.getWidth() - LayoutManager.this.getPaddingRight();
                }

                public int getParentStart() {
                    return LayoutManager.this.getPaddingLeft();
                }
            };
            this.mHorizontalBoundCheckCallback = r0;
            AnonymousClass2 r1 = new ViewBoundsCheck.Callback() {
                public View getChildAt(int i2) {
                    return LayoutManager.this.getChildAt(i2);
                }

                public int getChildEnd(View view) {
                    return LayoutManager.this.getDecoratedBottom(view) + ((LayoutParams) view.getLayoutParams()).bottomMargin;
                }

                public int getChildStart(View view) {
                    return LayoutManager.this.getDecoratedTop(view) - ((LayoutParams) view.getLayoutParams()).topMargin;
                }

                public int getParentEnd() {
                    return LayoutManager.this.getHeight() - LayoutManager.this.getPaddingBottom();
                }

                public int getParentStart() {
                    return LayoutManager.this.getPaddingTop();
                }
            };
            this.mVerticalBoundCheckCallback = r1;
            this.mHorizontalBoundCheck = new ViewBoundsCheck(r0);
            this.mVerticalBoundCheck = new ViewBoundsCheck(r1);
        }

        private void addViewInt(View view, int i2, boolean z) {
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (z || childViewHolderInt.isRemoved()) {
                this.mRecyclerView.mViewInfoStore.addToDisappearedInLayout(childViewHolderInt);
            } else {
                this.mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(childViewHolderInt);
            }
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (childViewHolderInt.wasReturnedFromScrap() || childViewHolderInt.isScrap()) {
                if (childViewHolderInt.isScrap()) {
                    childViewHolderInt.unScrap();
                } else {
                    childViewHolderInt.clearReturnedFromScrapFlag();
                }
                this.mChildHelper.attachViewToParent(view, i2, view.getLayoutParams(), false);
            } else if (view.getParent() == this.mRecyclerView) {
                int indexOfChild = this.mChildHelper.indexOfChild(view);
                if (i2 == -1) {
                    i2 = this.mChildHelper.getChildCount();
                }
                if (indexOfChild == -1) {
                    StringBuilder sb2 = new StringBuilder("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:");
                    sb2.append(this.mRecyclerView.indexOfChild(view));
                    throw new IllegalStateException(C0086a.k(this.mRecyclerView, sb2));
                } else if (indexOfChild != i2) {
                    this.mRecyclerView.mLayout.moveView(indexOfChild, i2);
                }
            } else {
                this.mChildHelper.addView(view, i2, false);
                layoutParams.mInsetsDirty = true;
                SmoothScroller smoothScroller = this.mSmoothScroller;
                if (smoothScroller != null && smoothScroller.isRunning()) {
                    this.mSmoothScroller.onChildAttachedToWindow(view);
                }
            }
            if (layoutParams.mPendingInvalidate) {
                if (RecyclerView.sVerboseLoggingEnabled) {
                    Log.d("SeslRecyclerView", "consuming pending invalidate on child " + layoutParams.mViewHolder);
                }
                childViewHolderInt.itemView.invalidate();
                layoutParams.mPendingInvalidate = false;
            }
        }

        public static int chooseSize(int i2, int i7, int i8) {
            int mode = View.MeasureSpec.getMode(i2);
            int size = View.MeasureSpec.getSize(i2);
            if (mode == Integer.MIN_VALUE) {
                return Math.min(size, Math.max(i7, i8));
            }
            if (mode != 1073741824) {
                return Math.max(i7, i8);
            }
            return size;
        }

        private void detachViewInternal(int i2, View view) {
            this.mChildHelper.detachViewFromParent(i2);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:7:0x0018, code lost:
            if (r5 == 1073741824) goto L_0x0022;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static int getChildMeasureSpec(int r4, int r5, int r6, int r7, boolean r8) {
            /*
                int r4 = r4 - r6
                r6 = 0
                int r4 = java.lang.Math.max(r6, r4)
                r0 = -2
                r1 = -1
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = 1073741824(0x40000000, float:2.0)
                if (r8 == 0) goto L_0x001d
                if (r7 < 0) goto L_0x0012
            L_0x0010:
                r5 = r3
                goto L_0x0030
            L_0x0012:
                if (r7 != r1) goto L_0x001a
                if (r5 == r2) goto L_0x0022
                if (r5 == 0) goto L_0x001a
                if (r5 == r3) goto L_0x0022
            L_0x001a:
                r5 = r6
                r7 = r5
                goto L_0x0030
            L_0x001d:
                if (r7 < 0) goto L_0x0020
                goto L_0x0010
            L_0x0020:
                if (r7 != r1) goto L_0x0024
            L_0x0022:
                r7 = r4
                goto L_0x0030
            L_0x0024:
                if (r7 != r0) goto L_0x001a
                if (r5 == r2) goto L_0x002e
                if (r5 != r3) goto L_0x002b
                goto L_0x002e
            L_0x002b:
                r7 = r4
                r5 = r6
                goto L_0x0030
            L_0x002e:
                r7 = r4
                r5 = r2
            L_0x0030:
                int r4 = android.view.View.MeasureSpec.makeMeasureSpec(r7, r5)
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.LayoutManager.getChildMeasureSpec(int, int, int, int, boolean):int");
        }

        private int[] getChildRectangleOnScreenScrollAmount(View view, Rect rect) {
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int width = getWidth() - getPaddingRight();
            int height = (getHeight() - getPaddingBottom()) - this.mRecyclerView.mHoverBottomAreaHeight;
            int left = (view.getLeft() + rect.left) - view.getScrollX();
            int top = (view.getTop() + rect.top) - view.getScrollY();
            int width2 = rect.width() + left;
            int height2 = rect.height() + top;
            int i2 = left - paddingLeft;
            int min = Math.min(0, i2);
            int i7 = top - paddingTop;
            int min2 = Math.min(0, i7);
            int i8 = width2 - width;
            int max = Math.max(0, i8);
            int max2 = Math.max(0, height2 - height);
            if (getLayoutDirection() != 1) {
                if (min == 0) {
                    min = Math.min(i2, max);
                }
                max = min;
            } else if (max == 0) {
                max = Math.max(min, i8);
            }
            if (min2 == 0) {
                min2 = Math.min(i7, max2);
            }
            return new int[]{max, min2};
        }

        public static Properties getProperties(Context context, AttributeSet attributeSet, int i2, int i7) {
            Properties properties = new Properties();
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RecyclerView, i2, i7);
            properties.orientation = obtainStyledAttributes.getInt(R$styleable.RecyclerView_android_orientation, 1);
            properties.spanCount = obtainStyledAttributes.getInt(R$styleable.RecyclerView_spanCount, 1);
            properties.reverseLayout = obtainStyledAttributes.getBoolean(R$styleable.RecyclerView_reverseLayout, false);
            properties.stackFromEnd = obtainStyledAttributes.getBoolean(R$styleable.RecyclerView_stackFromEnd, false);
            obtainStyledAttributes.recycle();
            return properties;
        }

        private boolean isFocusedChildVisibleAfterScrolling(RecyclerView recyclerView, int i2, int i7) {
            View focusedChild = recyclerView.getFocusedChild();
            if (focusedChild == null) {
                return false;
            }
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int width = getWidth() - getPaddingRight();
            int height = (getHeight() - getPaddingBottom()) - this.mRecyclerView.mHoverBottomAreaHeight;
            Rect rect = this.mRecyclerView.mTempRect;
            getDecoratedBoundsWithMargins(focusedChild, rect);
            if (rect.left - i2 >= width || rect.right - i2 <= paddingLeft || rect.top - i7 >= height || rect.bottom - i7 <= paddingTop) {
                return false;
            }
            return true;
        }

        private static boolean isMeasurementUpToDate(int i2, int i7, int i8) {
            int mode = View.MeasureSpec.getMode(i7);
            int size = View.MeasureSpec.getSize(i7);
            if (i8 > 0 && i2 != i8) {
                return false;
            }
            if (mode != Integer.MIN_VALUE) {
                if (mode == 0) {
                    return true;
                }
                if (mode == 1073741824 && size == i2) {
                    return true;
                }
                return false;
            } else if (size >= i2) {
                return true;
            } else {
                return false;
            }
        }

        private void scrapOrRecycleView(Recycler recycler, int i2, View view) {
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt.shouldIgnore()) {
                if (RecyclerView.sVerboseLoggingEnabled) {
                    Log.d("SeslRecyclerView", "ignoring view " + childViewHolderInt);
                }
            } else if (!childViewHolderInt.isInvalid() || childViewHolderInt.isRemoved() || this.mRecyclerView.mAdapter.hasStableIds()) {
                detachViewAt(i2);
                recycler.scrapView(view);
                this.mRecyclerView.mViewInfoStore.onViewDetached(childViewHolderInt);
            } else {
                removeViewAt(i2);
                recycler.recycleViewHolderInternal(childViewHolderInt);
            }
        }

        public void addDisappearingView(View view) {
            addDisappearingView(view, -1);
        }

        public void addView(View view) {
            addView(view, -1);
        }

        public void assertNotInLayoutOrScroll(String str) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                recyclerView.assertNotInLayoutOrScroll(str);
            }
        }

        public void attachView(View view, int i2, LayoutParams layoutParams) {
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt.isRemoved()) {
                this.mRecyclerView.mViewInfoStore.addToDisappearedInLayout(childViewHolderInt);
            } else {
                this.mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(childViewHolderInt);
            }
            this.mChildHelper.attachViewToParent(view, i2, layoutParams, childViewHolderInt.isRemoved());
        }

        public void calculateItemDecorationsForChild(View view, Rect rect) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView == null) {
                rect.set(0, 0, 0, 0);
            } else {
                rect.set(recyclerView.getItemDecorInsetsForChild(view));
            }
        }

        public boolean canScrollHorizontally() {
            return false;
        }

        public abstract boolean canScrollVertically();

        public boolean checkLayoutParams(LayoutParams layoutParams) {
            if (layoutParams != null) {
                return true;
            }
            return false;
        }

        public int computeHorizontalScrollExtent(State state) {
            return 0;
        }

        public int computeHorizontalScrollOffset(State state) {
            return 0;
        }

        public int computeHorizontalScrollRange(State state) {
            return 0;
        }

        public abstract int computeVerticalScrollExtent(State state);

        public abstract int computeVerticalScrollOffset(State state);

        public abstract int computeVerticalScrollRange(State state);

        public void detachAndScrapAttachedViews(Recycler recycler) {
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                scrapOrRecycleView(recycler, childCount, getChildAt(childCount));
            }
        }

        public void detachViewAt(int i2) {
            detachViewInternal(i2, getChildAt(i2));
        }

        public void dispatchAttachedToWindow(RecyclerView recyclerView) {
            this.mIsAttachedToWindow = true;
            onAttachedToWindow(recyclerView);
        }

        public void dispatchDetachedFromWindow(RecyclerView recyclerView, Recycler recycler) {
            this.mIsAttachedToWindow = false;
            onDetachedFromWindow(recyclerView, recycler);
        }

        public View findContainingItemView(View view) {
            View findContainingItemView;
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView == null || (findContainingItemView = recyclerView.findContainingItemView(view)) == null || this.mChildHelper.isHidden(findContainingItemView)) {
                return null;
            }
            return findContainingItemView;
        }

        public View findViewByPosition(int i2) {
            int childCount = getChildCount();
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = getChildAt(i7);
                ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(childAt);
                if (childViewHolderInt != null && childViewHolderInt.getLayoutPosition() == i2 && !childViewHolderInt.shouldIgnore() && (this.mRecyclerView.mState.isPreLayout() || !childViewHolderInt.isRemoved())) {
                    return childAt;
                }
            }
            return null;
        }

        public abstract LayoutParams generateDefaultLayoutParams();

        public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
            if (layoutParams instanceof LayoutParams) {
                return new LayoutParams((LayoutParams) layoutParams);
            }
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
            }
            return new LayoutParams(layoutParams);
        }

        public int getBaseline() {
            return -1;
        }

        public int getBottomDecorationHeight(View view) {
            return ((LayoutParams) view.getLayoutParams()).mDecorInsets.bottom;
        }

        public View getChildAt(int i2) {
            ChildHelper childHelper = this.mChildHelper;
            if (childHelper != null) {
                return childHelper.getChildAt(i2);
            }
            return null;
        }

        public int getChildCount() {
            ChildHelper childHelper = this.mChildHelper;
            if (childHelper != null) {
                return childHelper.getChildCount();
            }
            return 0;
        }

        public boolean getClipToPadding() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView == null || !recyclerView.mClipToPadding) {
                return false;
            }
            return true;
        }

        public int getColumnCountForAccessibility(Recycler recycler, State state) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView == null || recyclerView.mAdapter == null || !canScrollHorizontally()) {
                return 1;
            }
            return this.mRecyclerView.mAdapter.getItemCount();
        }

        public int getDecoratedBottom(View view) {
            return getBottomDecorationHeight(view) + view.getBottom();
        }

        public void getDecoratedBoundsWithMargins(View view, Rect rect) {
            RecyclerView.getDecoratedBoundsWithMarginsInt(view, rect);
        }

        public int getDecoratedLeft(View view) {
            return view.getLeft() - getLeftDecorationWidth(view);
        }

        public int getDecoratedMeasuredHeight(View view) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).mDecorInsets;
            return view.getMeasuredHeight() + rect.top + rect.bottom;
        }

        public int getDecoratedMeasuredWidth(View view) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).mDecorInsets;
            return view.getMeasuredWidth() + rect.left + rect.right;
        }

        public int getDecoratedRight(View view) {
            return getRightDecorationWidth(view) + view.getRight();
        }

        public int getDecoratedTop(View view) {
            return view.getTop() - getTopDecorationHeight(view);
        }

        public View getFocusedChild() {
            View focusedChild;
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView == null || (focusedChild = recyclerView.getFocusedChild()) == null || this.mChildHelper.isHidden(focusedChild)) {
                return null;
            }
            return focusedChild;
        }

        public int getHeight() {
            return this.mHeight;
        }

        public int getHeightMode() {
            return this.mHeightMode;
        }

        public int getItemCount() {
            Adapter adapter;
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                adapter = recyclerView.getAdapter();
            } else {
                adapter = null;
            }
            if (adapter != null) {
                return adapter.getItemCount();
            }
            return 0;
        }

        public int getItemViewType(View view) {
            return RecyclerView.getChildViewHolderInt(view).getItemViewType();
        }

        public int getLayoutDirection() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                return ViewCompat.getLayoutDirection(recyclerView);
            }
            Log.e("SeslRecyclerView", "RecyclerView is null.");
            return 0;
        }

        public int getLeftDecorationWidth(View view) {
            return ((LayoutParams) view.getLayoutParams()).mDecorInsets.left;
        }

        public int getMinimumHeight() {
            return ViewCompat.getMinimumHeight(this.mRecyclerView);
        }

        public int getMinimumWidth() {
            return ViewCompat.getMinimumWidth(this.mRecyclerView);
        }

        public int getPaddingBottom() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                return recyclerView.getPaddingBottom();
            }
            return 0;
        }

        public int getPaddingEnd() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                return ViewCompat.getPaddingEnd(recyclerView);
            }
            return 0;
        }

        public int getPaddingLeft() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                return recyclerView.getPaddingLeft();
            }
            return 0;
        }

        public int getPaddingRight() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                return recyclerView.getPaddingRight();
            }
            return 0;
        }

        public int getPaddingStart() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                return ViewCompat.getPaddingStart(recyclerView);
            }
            return 0;
        }

        public int getPaddingTop() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                return recyclerView.getPaddingTop();
            }
            return 0;
        }

        public int getPosition(View view) {
            if (view != null) {
                return ((LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
            }
            Log.e("SeslRecyclerView", "View is null.");
            return -1;
        }

        public int getRightDecorationWidth(View view) {
            return ((LayoutParams) view.getLayoutParams()).mDecorInsets.right;
        }

        public int getRowCountForAccessibility(Recycler recycler, State state) {
            Adapter adapter;
            RecyclerView recyclerView = this.mRecyclerView;
            if (!(recyclerView == null || (adapter = recyclerView.mAdapter) == null)) {
                if (adapter.seslUseCustomAccessibilityPosition()) {
                    if (canScrollVertically()) {
                        return this.mRecyclerView.mAdapter.seslGetAccessibilityItemCount();
                    }
                    return 1;
                } else if (canScrollVertically()) {
                    return this.mRecyclerView.mAdapter.getItemCount();
                }
            }
            return 1;
        }

        public int getSelectionModeForAccessibility(Recycler recycler, State state) {
            return 0;
        }

        public int getTopDecorationHeight(View view) {
            return ((LayoutParams) view.getLayoutParams()).mDecorInsets.top;
        }

        public void getTransformedBoundingBox(View view, boolean z, Rect rect) {
            Matrix matrix;
            if (z) {
                Rect rect2 = ((LayoutParams) view.getLayoutParams()).mDecorInsets;
                rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
            } else {
                rect.set(0, 0, view.getWidth(), view.getHeight());
            }
            if (!(this.mRecyclerView == null || (matrix = view.getMatrix()) == null || matrix.isIdentity())) {
                RectF rectF = this.mRecyclerView.mTempRectF;
                rectF.set(rect);
                matrix.mapRect(rectF);
                rect.set((int) Math.floor((double) rectF.left), (int) Math.floor((double) rectF.top), (int) Math.ceil((double) rectF.right), (int) Math.ceil((double) rectF.bottom));
            }
            rect.offset(view.getLeft(), view.getTop());
        }

        public int getWidth() {
            return this.mWidth;
        }

        public int getWidthMode() {
            return this.mWidthMode;
        }

        public boolean hasFlexibleChildInBothOrientations() {
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                ViewGroup.LayoutParams layoutParams = getChildAt(i2).getLayoutParams();
                if (layoutParams.width < 0 && layoutParams.height < 0) {
                    return true;
                }
            }
            return false;
        }

        public boolean isAttachedToWindow() {
            return this.mIsAttachedToWindow;
        }

        public abstract boolean isAutoMeasureEnabled();

        public final boolean isItemPrefetchEnabled() {
            return this.mItemPrefetchEnabled;
        }

        public boolean isLayoutHierarchical(Recycler recycler, State state) {
            return false;
        }

        public boolean isMeasurementCacheEnabled() {
            return this.mMeasurementCacheEnabled;
        }

        public boolean isSmoothScrolling() {
            SmoothScroller smoothScroller = this.mSmoothScroller;
            if (smoothScroller == null || !smoothScroller.isRunning()) {
                return false;
            }
            return true;
        }

        public boolean isViewPartiallyVisible(View view, boolean z, boolean z3) {
            boolean z7;
            if (!this.mHorizontalBoundCheck.isViewWithinBoundFlags(view, 24579) || !this.mVerticalBoundCheck.isViewWithinBoundFlags(view, 24579)) {
                z7 = false;
            } else {
                z7 = true;
            }
            if (z) {
                return z7;
            }
            return !z7;
        }

        public void layoutDecorated(View view, int i2, int i7, int i8, int i10) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).mDecorInsets;
            view.layout(i2 + rect.left, i7 + rect.top, i8 - rect.right, i10 - rect.bottom);
        }

        public void layoutDecoratedWithMargins(View view, int i2, int i7, int i8, int i10) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Rect rect = layoutParams.mDecorInsets;
            view.layout(i2 + rect.left + layoutParams.leftMargin, i7 + rect.top + layoutParams.topMargin, (i8 - rect.right) - layoutParams.rightMargin, (i10 - rect.bottom) - layoutParams.bottomMargin);
        }

        public void measureChildWithMargins(View view, int i2, int i7) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Rect itemDecorInsetsForChild = this.mRecyclerView.getItemDecorInsetsForChild(view);
            int i8 = itemDecorInsetsForChild.left + itemDecorInsetsForChild.right + i2;
            int i10 = itemDecorInsetsForChild.top + itemDecorInsetsForChild.bottom + i7;
            int childMeasureSpec = getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingRight() + getPaddingLeft() + layoutParams.leftMargin + layoutParams.rightMargin + i8, layoutParams.width, canScrollHorizontally());
            int childMeasureSpec2 = getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingBottom() + getPaddingTop() + layoutParams.topMargin + layoutParams.bottomMargin + i10, layoutParams.height, canScrollVertically());
            if (shouldMeasureChild(view, childMeasureSpec, childMeasureSpec2, layoutParams)) {
                view.measure(childMeasureSpec, childMeasureSpec2);
            }
        }

        public void moveView(int i2, int i7) {
            View childAt = getChildAt(i2);
            if (childAt != null) {
                detachViewAt(i2);
                attachView(childAt, i7);
                return;
            }
            throw new IllegalArgumentException("Cannot move a child from non-existing index:" + i2 + this.mRecyclerView.toString());
        }

        public void offsetChildrenHorizontal(int i2) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                recyclerView.offsetChildrenHorizontal(i2);
            }
        }

        public void offsetChildrenVertical(int i2) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                recyclerView.offsetChildrenVertical(i2);
            }
        }

        public boolean onAddFocusables(RecyclerView recyclerView, ArrayList<View> arrayList, int i2, int i7) {
            return false;
        }

        @Deprecated
        public void onDetachedFromWindow(RecyclerView recyclerView) {
        }

        public View onFocusSearchFailed(View view, int i2, Recycler recycler, State state) {
            return null;
        }

        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.mRecyclerView;
            onInitializeAccessibilityEvent(recyclerView.mRecycler, recyclerView.mState, accessibilityEvent);
        }

        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            RecyclerView recyclerView = this.mRecyclerView;
            onInitializeAccessibilityNodeInfo(recyclerView.mRecycler, recyclerView.mState, accessibilityNodeInfoCompat);
        }

        public void onInitializeAccessibilityNodeInfoForItem(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && !this.mChildHelper.isHidden(childViewHolderInt.itemView)) {
                RecyclerView recyclerView = this.mRecyclerView;
                onInitializeAccessibilityNodeInfoForItem(recyclerView.mRecycler, recyclerView.mState, view, accessibilityNodeInfoCompat);
            }
        }

        public View onInterceptFocusSearch(View view, int i2) {
            return null;
        }

        public void onItemsUpdated(RecyclerView recyclerView, int i2, int i7) {
        }

        public abstract void onLayoutChildren(Recycler recycler, State state);

        public void onMeasure(Recycler recycler, State state, int i2, int i7) {
            this.mRecyclerView.defaultOnMeasure(i2, i7);
        }

        @Deprecated
        public boolean onRequestChildFocus(RecyclerView recyclerView, View view, View view2) {
            return isSmoothScrolling() || recyclerView.isComputingLayout();
        }

        public Parcelable onSaveInstanceState() {
            return null;
        }

        public void onSmoothScrollerStopped(SmoothScroller smoothScroller) {
            if (this.mSmoothScroller == smoothScroller) {
                this.mSmoothScroller = null;
            }
        }

        public boolean performAccessibilityAction(int i2, Bundle bundle) {
            RecyclerView recyclerView = this.mRecyclerView;
            return performAccessibilityAction(recyclerView.mRecycler, recyclerView.mState, i2, bundle);
        }

        public boolean performAccessibilityActionForItem(Recycler recycler, State state, View view, int i2, Bundle bundle) {
            return false;
        }

        public void removeAllViews() {
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                this.mChildHelper.removeViewAt(childCount);
            }
        }

        public void removeAndRecycleAllViews(Recycler recycler) {
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                if (!RecyclerView.getChildViewHolderInt(getChildAt(childCount)).shouldIgnore()) {
                    removeAndRecycleViewAt(childCount, recycler);
                }
            }
        }

        public void removeAndRecycleScrapInt(Recycler recycler) {
            int scrapCount = recycler.getScrapCount();
            for (int i2 = scrapCount - 1; i2 >= 0; i2--) {
                View scrapViewAt = recycler.getScrapViewAt(i2);
                ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(scrapViewAt);
                if (!childViewHolderInt.shouldIgnore()) {
                    childViewHolderInt.setIsRecyclable(false);
                    if (childViewHolderInt.isTmpDetached()) {
                        this.mRecyclerView.removeDetachedView(scrapViewAt, false);
                    }
                    ItemAnimator itemAnimator = this.mRecyclerView.mItemAnimator;
                    if (itemAnimator != null) {
                        itemAnimator.endAnimation(childViewHolderInt);
                    }
                    childViewHolderInt.setIsRecyclable(true);
                    recycler.quickRecycleScrapView(scrapViewAt);
                }
            }
            recycler.clearScrap();
            if (scrapCount > 0) {
                this.mRecyclerView.invalidate();
            }
        }

        public void removeAndRecycleView(View view, Recycler recycler) {
            removeView(view);
            recycler.recycleView(view);
        }

        public void removeAndRecycleViewAt(int i2, Recycler recycler) {
            View childAt = getChildAt(i2);
            removeViewAt(i2);
            recycler.recycleView(childAt);
        }

        public boolean removeCallbacks(Runnable runnable) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                return recyclerView.removeCallbacks(runnable);
            }
            return false;
        }

        public void removeView(View view) {
            this.mChildHelper.removeView(view);
        }

        public void removeViewAt(int i2) {
            if (getChildAt(i2) != null) {
                this.mChildHelper.removeViewAt(i2);
            }
        }

        public boolean requestChildRectangleOnScreen(RecyclerView recyclerView, View view, Rect rect, boolean z) {
            return requestChildRectangleOnScreen(recyclerView, view, rect, z, false);
        }

        public void requestLayout() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                recyclerView.requestLayout();
            }
        }

        public void requestSimpleAnimationsInNextLayout() {
            this.mRequestedSimpleAnimations = true;
        }

        public int scrollHorizontallyBy(int i2, Recycler recycler, State state) {
            return 0;
        }

        public abstract void scrollToPosition(int i2);

        public abstract int scrollVerticallyBy(int i2, Recycler recycler, State state);

        public void setExactMeasureSpecsFrom(RecyclerView recyclerView) {
            setMeasureSpecs(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), 1073741824));
        }

        public final void setItemPrefetchEnabled(boolean z) {
            if (z != this.mItemPrefetchEnabled) {
                this.mItemPrefetchEnabled = z;
                this.mPrefetchMaxCountObserved = 0;
                RecyclerView recyclerView = this.mRecyclerView;
                if (recyclerView != null) {
                    recyclerView.mRecycler.updateViewCacheSize();
                }
            }
        }

        public void setMeasureSpecs(int i2, int i7) {
            this.mWidth = View.MeasureSpec.getSize(i2);
            int mode = View.MeasureSpec.getMode(i2);
            this.mWidthMode = mode;
            if (mode == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
                this.mWidth = 0;
            }
            this.mHeight = View.MeasureSpec.getSize(i7);
            int mode2 = View.MeasureSpec.getMode(i7);
            this.mHeightMode = mode2;
            if (mode2 == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
                this.mHeight = 0;
            }
        }

        public void setMeasuredDimension(Rect rect, int i2, int i7) {
            setMeasuredDimension(chooseSize(i2, getPaddingRight() + getPaddingLeft() + rect.width(), getMinimumWidth()), chooseSize(i7, getPaddingBottom() + getPaddingTop() + rect.height(), getMinimumHeight()));
        }

        public void setMeasuredDimensionFromChildren(int i2, int i7) {
            int childCount = getChildCount();
            if (childCount == 0) {
                this.mRecyclerView.defaultOnMeasure(i2, i7);
                return;
            }
            int i8 = Integer.MIN_VALUE;
            int i10 = Integer.MAX_VALUE;
            int i11 = Integer.MIN_VALUE;
            int i12 = Integer.MAX_VALUE;
            for (int i13 = 0; i13 < childCount; i13++) {
                View childAt = getChildAt(i13);
                Rect rect = this.mRecyclerView.mTempRect;
                getDecoratedBoundsWithMargins(childAt, rect);
                int i14 = rect.left;
                if (i14 < i12) {
                    i12 = i14;
                }
                int i15 = rect.right;
                if (i15 > i8) {
                    i8 = i15;
                }
                int i16 = rect.top;
                if (i16 < i10) {
                    i10 = i16;
                }
                int i17 = rect.bottom;
                if (i17 > i11) {
                    i11 = i17;
                }
            }
            this.mRecyclerView.mTempRect.set(i12, i10, i8, i11);
            setMeasuredDimension(this.mRecyclerView.mTempRect, i2, i7);
        }

        public void setRecyclerView(RecyclerView recyclerView) {
            if (recyclerView == null) {
                this.mRecyclerView = null;
                this.mChildHelper = null;
                this.mWidth = 0;
                this.mHeight = 0;
            } else {
                this.mRecyclerView = recyclerView;
                this.mChildHelper = recyclerView.mChildHelper;
                this.mWidth = recyclerView.getWidth();
                this.mHeight = recyclerView.getHeight();
            }
            this.mWidthMode = 1073741824;
            this.mHeightMode = 1073741824;
        }

        public boolean shouldMeasureChild(View view, int i2, int i7, LayoutParams layoutParams) {
            if (view.isLayoutRequested() || !this.mMeasurementCacheEnabled || !isMeasurementUpToDate(view.getWidth(), i2, layoutParams.width) || !isMeasurementUpToDate(view.getHeight(), i7, layoutParams.height)) {
                return true;
            }
            return false;
        }

        public boolean shouldMeasureTwice() {
            return false;
        }

        public boolean shouldReMeasureChild(View view, int i2, int i7, LayoutParams layoutParams) {
            if (!this.mMeasurementCacheEnabled || !isMeasurementUpToDate(view.getMeasuredWidth(), i2, layoutParams.width) || !isMeasurementUpToDate(view.getMeasuredHeight(), i7, layoutParams.height)) {
                return true;
            }
            return false;
        }

        public abstract void smoothScrollToPosition(RecyclerView recyclerView, State state, int i2);

        public void startSmoothScroll(SmoothScroller smoothScroller) {
            SmoothScroller smoothScroller2 = this.mSmoothScroller;
            if (!(smoothScroller2 == null || smoothScroller == smoothScroller2 || !smoothScroller2.isRunning())) {
                this.mSmoothScroller.stop();
            }
            this.mSmoothScroller = smoothScroller;
            smoothScroller.start(this.mRecyclerView, this);
        }

        public void stopSmoothScroller() {
            SmoothScroller smoothScroller = this.mSmoothScroller;
            if (smoothScroller != null) {
                smoothScroller.stop();
            }
        }

        public boolean supportsPredictiveItemAnimations() {
            return false;
        }

        public void addDisappearingView(View view, int i2) {
            addViewInt(view, i2, true);
        }

        public void addView(View view, int i2) {
            addViewInt(view, i2, false);
        }

        public void onDetachedFromWindow(RecyclerView recyclerView, Recycler recycler) {
            onDetachedFromWindow(recyclerView);
        }

        public void onInitializeAccessibilityEvent(Recycler recycler, State state, AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null && accessibilityEvent != null) {
                boolean z = true;
                if (!recyclerView.canScrollVertically(1) && !this.mRecyclerView.canScrollVertically(-1) && !this.mRecyclerView.canScrollHorizontally(-1) && !this.mRecyclerView.canScrollHorizontally(1)) {
                    z = false;
                }
                accessibilityEvent.setScrollable(z);
                Adapter adapter = this.mRecyclerView.mAdapter;
                if (adapter == null) {
                    return;
                }
                if (adapter.seslUseCustomAccessibilityPosition()) {
                    accessibilityEvent.setItemCount(this.mRecyclerView.mAdapter.seslGetAccessibilityItemCount());
                } else {
                    accessibilityEvent.setItemCount(this.mRecyclerView.mAdapter.getItemCount());
                }
            }
        }

        public void onInitializeAccessibilityNodeInfo(Recycler recycler, State state, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            if (this.mRecyclerView.canScrollVertically(-1) || this.mRecyclerView.canScrollHorizontally(-1)) {
                accessibilityNodeInfoCompat.addAction((int) SerializeOptions.SORT);
                accessibilityNodeInfoCompat.setScrollable(true);
            }
            if (this.mRecyclerView.canScrollVertically(1) || this.mRecyclerView.canScrollHorizontally(1)) {
                accessibilityNodeInfoCompat.addAction(4096);
                accessibilityNodeInfoCompat.setScrollable(true);
            }
            accessibilityNodeInfoCompat.setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(getRowCountForAccessibility(recycler, state), getColumnCountForAccessibility(recycler, state), isLayoutHierarchical(recycler, state), getSelectionModeForAccessibility(recycler, state)));
        }

        public void onItemsUpdated(RecyclerView recyclerView, int i2, int i7, Object obj) {
            onItemsUpdated(recyclerView, i2, i7);
        }

        public boolean onRequestChildFocus(RecyclerView recyclerView, State state, View view, View view2) {
            return onRequestChildFocus(recyclerView, view, view2);
        }

        /* JADX WARNING: Removed duplicated region for block: B:30:0x0094 A[ADDED_TO_REGION] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean performAccessibilityAction(androidx.recyclerview.widget.RecyclerView.Recycler r9, androidx.recyclerview.widget.RecyclerView.State r10, int r11, android.os.Bundle r12) {
            /*
                r8 = this;
                androidx.recyclerview.widget.RecyclerView r9 = r8.mRecyclerView
                r10 = 0
                if (r9 != 0) goto L_0x0006
                return r10
            L_0x0006:
                int r9 = r8.getHeight()
                int r12 = r8.getWidth()
                android.graphics.Rect r0 = new android.graphics.Rect
                r0.<init>()
                androidx.recyclerview.widget.RecyclerView r1 = r8.mRecyclerView
                android.graphics.Matrix r1 = r1.getMatrix()
                boolean r1 = r1.isIdentity()
                if (r1 == 0) goto L_0x002f
                androidx.recyclerview.widget.RecyclerView r1 = r8.mRecyclerView
                boolean r1 = r1.getGlobalVisibleRect(r0)
                if (r1 == 0) goto L_0x002f
                int r9 = r0.height()
                int r12 = r0.width()
            L_0x002f:
                r0 = 4096(0x1000, float:5.74E-42)
                r1 = 1
                if (r11 == r0) goto L_0x006a
                r0 = 8192(0x2000, float:1.14794E-41)
                if (r11 == r0) goto L_0x003b
                r3 = r10
                r4 = r3
                goto L_0x0092
            L_0x003b:
                androidx.recyclerview.widget.RecyclerView r11 = r8.mRecyclerView
                r0 = -1
                boolean r11 = r11.canScrollVertically(r0)
                if (r11 == 0) goto L_0x0050
                int r11 = r8.getPaddingTop()
                int r9 = r9 - r11
                int r11 = r8.getPaddingBottom()
                int r9 = r9 - r11
                int r9 = -r9
                goto L_0x0051
            L_0x0050:
                r9 = r10
            L_0x0051:
                androidx.recyclerview.widget.RecyclerView r11 = r8.mRecyclerView
                boolean r11 = r11.canScrollHorizontally(r0)
                if (r11 == 0) goto L_0x0067
                int r11 = r8.getPaddingLeft()
                int r12 = r12 - r11
                int r11 = r8.getPaddingRight()
                int r12 = r12 - r11
                int r11 = -r12
            L_0x0064:
                r4 = r9
                r3 = r11
                goto L_0x0092
            L_0x0067:
                r4 = r9
                r3 = r10
                goto L_0x0092
            L_0x006a:
                androidx.recyclerview.widget.RecyclerView r11 = r8.mRecyclerView
                boolean r11 = r11.canScrollVertically(r1)
                if (r11 == 0) goto L_0x007d
                int r11 = r8.getPaddingTop()
                int r9 = r9 - r11
                int r11 = r8.getPaddingBottom()
                int r9 = r9 - r11
                goto L_0x007e
            L_0x007d:
                r9 = r10
            L_0x007e:
                androidx.recyclerview.widget.RecyclerView r11 = r8.mRecyclerView
                boolean r11 = r11.canScrollHorizontally(r1)
                if (r11 == 0) goto L_0x0067
                int r11 = r8.getPaddingLeft()
                int r12 = r12 - r11
                int r11 = r8.getPaddingRight()
                int r11 = r12 - r11
                goto L_0x0064
            L_0x0092:
                if (r4 != 0) goto L_0x0097
                if (r3 != 0) goto L_0x0097
                return r10
            L_0x0097:
                androidx.recyclerview.widget.RecyclerView r2 = r8.mRecyclerView
                r6 = -2147483648(0xffffffff80000000, float:-0.0)
                r7 = 1
                r5 = 0
                r2.smoothScrollBy(r3, r4, r5, r6, r7)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.LayoutManager.performAccessibilityAction(androidx.recyclerview.widget.RecyclerView$Recycler, androidx.recyclerview.widget.RecyclerView$State, int, android.os.Bundle):boolean");
        }

        public boolean performAccessibilityActionForItem(View view, int i2, Bundle bundle) {
            RecyclerView recyclerView = this.mRecyclerView;
            return performAccessibilityActionForItem(recyclerView.mRecycler, recyclerView.mState, view, i2, bundle);
        }

        public boolean requestChildRectangleOnScreen(RecyclerView recyclerView, View view, Rect rect, boolean z, boolean z3) {
            int[] childRectangleOnScreenScrollAmount = getChildRectangleOnScreenScrollAmount(view, rect);
            int i2 = childRectangleOnScreenScrollAmount[0];
            int i7 = childRectangleOnScreenScrollAmount[1];
            if ((z3 && !isFocusedChildVisibleAfterScrolling(recyclerView, i2, i7)) || (i2 == 0 && i7 == 0)) {
                return false;
            }
            if (z) {
                recyclerView.scrollBy(i2, i7);
            } else {
                recyclerView.smoothScrollBy(i2, i7);
            }
            return true;
        }

        public void onInitializeAccessibilityNodeInfoForItem(Recycler recycler, State state, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            int i2 = 0;
            int position = canScrollVertically() ? getPosition(view) : 0;
            if (canScrollHorizontally()) {
                i2 = getPosition(view);
            }
            if (this.mRecyclerView.mAdapter.seslUseCustomAccessibilityPosition()) {
                position = this.mRecyclerView.mAdapter.seslGetAccessibilityItemPosition(position);
                i2 = this.mRecyclerView.mAdapter.seslGetAccessibilityItemPosition(i2);
            }
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(position, 1, i2, 1, false, false));
        }

        public void attachView(View view, int i2) {
            attachView(view, i2, (LayoutParams) view.getLayoutParams());
        }

        public LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
            return new LayoutParams(context, attributeSet);
        }

        public void setMeasuredDimension(int i2, int i7) {
            this.mRecyclerView.setMeasuredDimension(i2, i7);
        }

        public void onAttachedToWindow(RecyclerView recyclerView) {
        }

        public void onItemsChanged(RecyclerView recyclerView) {
        }

        public void onLayoutCompleted(State state) {
        }

        public void onRestoreInstanceState(Parcelable parcelable) {
        }

        public void onScrollStateChanged(int i2) {
        }

        public void collectInitialPrefetchPositions(int i2, LayoutPrefetchRegistry layoutPrefetchRegistry) {
        }

        public void onAdapterChanged(Adapter adapter, Adapter adapter2) {
        }

        public void onItemsAdded(RecyclerView recyclerView, int i2, int i7) {
        }

        public void onItemsRemoved(RecyclerView recyclerView, int i2, int i7) {
        }

        public void collectAdjacentPrefetchPositions(int i2, int i7, State state, LayoutPrefetchRegistry layoutPrefetchRegistry) {
        }

        public void onItemsMoved(RecyclerView recyclerView, int i2, int i7, int i8) {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnChildAttachStateChangeListener {
        void onChildViewAttachedToWindow(View view);

        void onChildViewDetachedFromWindow(View view);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class OnFlingListener {
        public abstract boolean onFling(int i2, int i7);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnItemTouchListener {
        boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent);

        void onRequestDisallowInterceptTouchEvent(boolean z);

        void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RecycledViewPool {
        int mAttachCountForClearing = 0;
        Set<Adapter<?>> mAttachedAdaptersForPoolingContainer = Collections.newSetFromMap(new IdentityHashMap());
        SparseArray<ScrapData> mScrap = new SparseArray<>();

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class ScrapData {
            long mBindRunningAverageNs = 0;
            long mCreateRunningAverageNs = 0;
            int mMaxScrap = 5;
            final ArrayList<ViewHolder> mScrapHeap = new ArrayList<>();
        }

        private ScrapData getScrapDataForType(int i2) {
            ScrapData scrapData = this.mScrap.get(i2);
            if (scrapData != null) {
                return scrapData;
            }
            ScrapData scrapData2 = new ScrapData();
            this.mScrap.put(i2, scrapData2);
            return scrapData2;
        }

        public void attach() {
            this.mAttachCountForClearing++;
        }

        public void attachForPoolingContainer(Adapter<?> adapter) {
            this.mAttachedAdaptersForPoolingContainer.add(adapter);
        }

        public void clear() {
            for (int i2 = 0; i2 < this.mScrap.size(); i2++) {
                ScrapData valueAt = this.mScrap.valueAt(i2);
                if (valueAt != null) {
                    Iterator<ViewHolder> it = valueAt.mScrapHeap.iterator();
                    while (it.hasNext()) {
                        PoolingContainer.callPoolingContainerOnRelease(it.next().itemView);
                    }
                    valueAt.mScrapHeap.clear();
                } else {
                    Log.e("SeslRecyclerView", "clear() wasn't executed because RecycledViewPool.mScrap was invalid");
                }
            }
        }

        public void detach() {
            this.mAttachCountForClearing--;
        }

        public void detachForPoolingContainer(Adapter<?> adapter, boolean z) {
            this.mAttachedAdaptersForPoolingContainer.remove(adapter);
            if (this.mAttachedAdaptersForPoolingContainer.size() == 0 && !z) {
                for (int i2 = 0; i2 < this.mScrap.size(); i2++) {
                    SparseArray<ScrapData> sparseArray = this.mScrap;
                    ScrapData scrapData = sparseArray.get(sparseArray.keyAt(i2));
                    if (scrapData != null) {
                        ArrayList<ViewHolder> arrayList = scrapData.mScrapHeap;
                        for (int i7 = 0; i7 < arrayList.size(); i7++) {
                            if (!(arrayList.get(i7) == null || arrayList.get(i7).itemView == null)) {
                                PoolingContainer.callPoolingContainerOnRelease(arrayList.get(i7).itemView);
                            }
                        }
                    }
                }
            }
        }

        public void factorInBindTime(int i2, long j2) {
            ScrapData scrapDataForType = getScrapDataForType(i2);
            scrapDataForType.mBindRunningAverageNs = runningAverage(scrapDataForType.mBindRunningAverageNs, j2);
        }

        public void factorInCreateTime(int i2, long j2) {
            ScrapData scrapDataForType = getScrapDataForType(i2);
            scrapDataForType.mCreateRunningAverageNs = runningAverage(scrapDataForType.mCreateRunningAverageNs, j2);
        }

        public ViewHolder getRecycledView(int i2) {
            ScrapData scrapData = this.mScrap.get(i2);
            if (scrapData == null || scrapData.mScrapHeap.isEmpty()) {
                return null;
            }
            ArrayList<ViewHolder> arrayList = scrapData.mScrapHeap;
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                if (arrayList.get(size) == null) {
                    StringBuilder o2 = C0086a.o(size, "ViewHolder object null when getRecycledView is in progress. pos= ", " size=");
                    C0086a.A(o2, arrayList, " max= ");
                    o2.append(scrapData.mMaxScrap);
                    o2.append(" holder= ");
                    o2.append(size());
                    o2.append(" scrapHeap= ");
                    o2.append(arrayList);
                    Log.e("SeslRecyclerView", o2.toString());
                } else if (!arrayList.get(size).isAttachedToTransitionOverlay()) {
                    return arrayList.remove(size);
                }
            }
            return null;
        }

        public int getRecycledViewCount(int i2) {
            return getScrapDataForType(i2).mScrapHeap.size();
        }

        public void onAdapterChanged(Adapter<?> adapter, Adapter<?> adapter2, boolean z) {
            if (adapter != null) {
                detach();
            }
            if (!z && this.mAttachCountForClearing == 0) {
                clear();
            }
            if (adapter2 != null) {
                attach();
            }
        }

        public void putRecycledView(ViewHolder viewHolder) {
            int itemViewType = viewHolder.getItemViewType();
            ArrayList<ViewHolder> arrayList = getScrapDataForType(itemViewType).mScrapHeap;
            if (this.mScrap.get(itemViewType).mMaxScrap <= arrayList.size()) {
                PoolingContainer.callPoolingContainerOnRelease(viewHolder.itemView);
            } else if (!RecyclerView.sDebugAssertionsEnabled || !arrayList.contains(viewHolder)) {
                viewHolder.resetInternal();
                arrayList.add(viewHolder);
            } else {
                throw new IllegalArgumentException("this scrap item already exists");
            }
        }

        public long runningAverage(long j2, long j3) {
            if (j2 == 0) {
                return j3;
            }
            return (j3 / 4) + ((j2 / 4) * 3);
        }

        public void setMaxRecycledViews(int i2, int i7) {
            ScrapData scrapDataForType = getScrapDataForType(i2);
            scrapDataForType.mMaxScrap = i7;
            ArrayList<ViewHolder> arrayList = scrapDataForType.mScrapHeap;
            while (arrayList.size() > i7) {
                arrayList.remove(arrayList.size() - 1);
            }
        }

        public int size() {
            int i2 = 0;
            for (int i7 = 0; i7 < this.mScrap.size(); i7++) {
                ArrayList<ViewHolder> arrayList = this.mScrap.valueAt(i7).mScrapHeap;
                if (arrayList != null) {
                    i2 = arrayList.size() + i2;
                }
            }
            return i2;
        }

        public boolean willBindInTime(int i2, long j2, long j3) {
            long j8 = getScrapDataForType(i2).mBindRunningAverageNs;
            if (j8 == 0 || j2 + j8 < j3) {
                return true;
            }
            return false;
        }

        public boolean willCreateInTime(int i2, long j2, long j3) {
            long j8 = getScrapDataForType(i2).mCreateRunningAverageNs;
            if (j8 == 0 || j2 + j8 < j3) {
                return true;
            }
            return false;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class Recycler {
        final ArrayList<ViewHolder> mAttachedScrap;
        final ArrayList<ViewHolder> mCachedViews = new ArrayList<>();
        ArrayList<ViewHolder> mChangedScrap = null;
        RecycledViewPool mRecyclerPool;
        private int mRequestedCacheMax;
        private final List<ViewHolder> mUnmodifiableAttachedScrap;
        int mViewCacheMax;

        public Recycler() {
            ArrayList<ViewHolder> arrayList = new ArrayList<>();
            this.mAttachedScrap = arrayList;
            this.mUnmodifiableAttachedScrap = Collections.unmodifiableList(arrayList);
            this.mRequestedCacheMax = 2;
            this.mViewCacheMax = 2;
        }

        private void attachAccessibilityDelegateOnBind(ViewHolder viewHolder) {
            if (RecyclerView.this.isAccessibilityEnabled()) {
                View view = viewHolder.itemView;
                if (ViewCompat.getImportantForAccessibility(view) == 0) {
                    ViewCompat.setImportantForAccessibility(view, 1);
                }
                RecyclerView recyclerView = RecyclerView.this;
                if (recyclerView.mAccessibilityDelegate == null) {
                    recyclerView.setAccessibilityDelegateCompat(new RecyclerViewAccessibilityDelegate(RecyclerView.this));
                    Log.d("SeslRecyclerView", "attachAccessibilityDelegate: mAccessibilityDelegate is null, so re create");
                }
                AccessibilityDelegateCompat itemDelegate = RecyclerView.this.mAccessibilityDelegate.getItemDelegate();
                if (itemDelegate instanceof RecyclerViewAccessibilityDelegate.ItemDelegate) {
                    ((RecyclerViewAccessibilityDelegate.ItemDelegate) itemDelegate).saveOriginalDelegate(view);
                }
                ViewCompat.setAccessibilityDelegate(view, itemDelegate);
            }
        }

        private void invalidateDisplayListInt(ViewHolder viewHolder) {
            View view = viewHolder.itemView;
            if (view instanceof ViewGroup) {
                invalidateDisplayListInt((ViewGroup) view, false);
            }
        }

        private void maybeSendPoolingContainerAttach() {
            if (this.mRecyclerPool != null) {
                RecyclerView recyclerView = RecyclerView.this;
                if (recyclerView.mAdapter != null && recyclerView.isAttachedToWindow()) {
                    this.mRecyclerPool.attachForPoolingContainer(RecyclerView.this.mAdapter);
                }
            }
        }

        private void poolingContainerDetach(Adapter<?> adapter) {
            poolingContainerDetach(adapter, false);
        }

        private boolean tryBindViewHolderByDeadline(ViewHolder viewHolder, int i2, int i7, long j2) {
            viewHolder.mBindingAdapter = null;
            viewHolder.mOwnerRecyclerView = RecyclerView.this;
            int itemViewType = viewHolder.getItemViewType();
            long nanoTime = RecyclerView.this.getNanoTime();
            boolean z = false;
            if (j2 != Long.MAX_VALUE && !this.mRecyclerPool.willBindInTime(itemViewType, nanoTime, j2)) {
                return false;
            }
            if (viewHolder.isTmpDetached() && RecyclerView.this.indexOfChild(viewHolder.itemView) > 0) {
                RecyclerView recyclerView = RecyclerView.this;
                recyclerView.attachViewToParent(viewHolder.itemView, recyclerView.getChildCount(), viewHolder.itemView.getLayoutParams());
                z = true;
            }
            RecyclerView.this.mAdapter.bindViewHolder(viewHolder, i2);
            if (z) {
                RecyclerView.this.detachViewFromParent(viewHolder.itemView);
            }
            this.mRecyclerPool.factorInBindTime(viewHolder.getItemViewType(), RecyclerView.this.getNanoTime() - nanoTime);
            attachAccessibilityDelegateOnBind(viewHolder);
            if (RecyclerView.this.mState.isPreLayout()) {
                viewHolder.mPreLayoutPosition = i7;
            }
            return true;
        }

        public void addViewHolderToRecycledViewPool(ViewHolder viewHolder, boolean z) {
            AccessibilityDelegateCompat accessibilityDelegateCompat;
            RecyclerView.clearNestedRecyclerViewIfNotNested(viewHolder);
            View view = viewHolder.itemView;
            RecyclerViewAccessibilityDelegate recyclerViewAccessibilityDelegate = RecyclerView.this.mAccessibilityDelegate;
            if (recyclerViewAccessibilityDelegate != null) {
                AccessibilityDelegateCompat itemDelegate = recyclerViewAccessibilityDelegate.getItemDelegate();
                if (itemDelegate instanceof RecyclerViewAccessibilityDelegate.ItemDelegate) {
                    accessibilityDelegateCompat = ((RecyclerViewAccessibilityDelegate.ItemDelegate) itemDelegate).getAndRemoveOriginalDelegateForItem(view);
                } else {
                    accessibilityDelegateCompat = null;
                }
                ViewCompat.setAccessibilityDelegate(view, accessibilityDelegateCompat);
            }
            if (z) {
                dispatchViewRecycled(viewHolder);
            }
            viewHolder.mBindingAdapter = null;
            viewHolder.mOwnerRecyclerView = null;
            getRecycledViewPool().putRecycledView(viewHolder);
        }

        public void clear() {
            this.mAttachedScrap.clear();
            recycleAndClearCachedViews();
        }

        public void clearOldPositions() {
            int size = this.mCachedViews.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.mCachedViews.get(i2).clearOldPosition();
            }
            int size2 = this.mAttachedScrap.size();
            for (int i7 = 0; i7 < size2; i7++) {
                this.mAttachedScrap.get(i7).clearOldPosition();
            }
            ArrayList<ViewHolder> arrayList = this.mChangedScrap;
            if (arrayList != null) {
                int size3 = arrayList.size();
                for (int i8 = 0; i8 < size3; i8++) {
                    this.mChangedScrap.get(i8).clearOldPosition();
                }
            }
        }

        public void clearScrap() {
            this.mAttachedScrap.clear();
            ArrayList<ViewHolder> arrayList = this.mChangedScrap;
            if (arrayList != null) {
                arrayList.clear();
            }
        }

        public int convertPreLayoutPositionToPostLayout(int i2) {
            if (i2 < 0 || i2 >= RecyclerView.this.mState.getItemCount()) {
                StringBuilder o2 = C0086a.o(i2, "invalid position ", ". State item count is ");
                o2.append(RecyclerView.this.mState.getItemCount());
                throw new IndexOutOfBoundsException(C0086a.k(RecyclerView.this, o2));
            } else if (!RecyclerView.this.mState.isPreLayout()) {
                return i2;
            } else {
                return RecyclerView.this.mAdapterHelper.findPositionOffset(i2);
            }
        }

        public void dispatchViewRecycled(ViewHolder viewHolder) {
            RecyclerView.this.getClass();
            if (RecyclerView.this.mRecyclerListeners.size() <= 0) {
                Adapter adapter = RecyclerView.this.mAdapter;
                if (adapter != null) {
                    adapter.onViewRecycled(viewHolder);
                }
                RecyclerView recyclerView = RecyclerView.this;
                if (recyclerView.mState != null) {
                    recyclerView.mViewInfoStore.removeViewHolder(viewHolder);
                }
                if (RecyclerView.sVerboseLoggingEnabled) {
                    Log.d("SeslRecyclerView", "dispatchViewRecycled: " + viewHolder);
                    return;
                }
                return;
            }
            RecyclerView.this.mRecyclerListeners.get(0).getClass();
            throw new ClassCastException();
        }

        public ViewHolder getChangedScrapViewForPosition(int i2) {
            int size;
            int findPositionOffset;
            ArrayList<ViewHolder> arrayList = this.mChangedScrap;
            if (!(arrayList == null || (size = arrayList.size()) == 0)) {
                int i7 = 0;
                int i8 = 0;
                while (i8 < size) {
                    ViewHolder viewHolder = this.mChangedScrap.get(i8);
                    if (viewHolder.wasReturnedFromScrap() || viewHolder.getLayoutPosition() != i2) {
                        i8++;
                    } else {
                        viewHolder.addFlags(32);
                        return viewHolder;
                    }
                }
                if (RecyclerView.this.mAdapter.hasStableIds() && (findPositionOffset = RecyclerView.this.mAdapterHelper.findPositionOffset(i2)) > 0 && findPositionOffset < RecyclerView.this.mAdapter.getItemCount()) {
                    long itemId = RecyclerView.this.mAdapter.getItemId(findPositionOffset);
                    while (i7 < size) {
                        ViewHolder viewHolder2 = this.mChangedScrap.get(i7);
                        if (viewHolder2.wasReturnedFromScrap() || viewHolder2.getItemId() != itemId) {
                            i7++;
                        } else {
                            viewHolder2.addFlags(32);
                            return viewHolder2;
                        }
                    }
                }
            }
            return null;
        }

        public RecycledViewPool getRecycledViewPool() {
            if (this.mRecyclerPool == null) {
                this.mRecyclerPool = new RecycledViewPool();
                maybeSendPoolingContainerAttach();
            }
            return this.mRecyclerPool;
        }

        public int getScrapCount() {
            return this.mAttachedScrap.size();
        }

        public List<ViewHolder> getScrapList() {
            return this.mUnmodifiableAttachedScrap;
        }

        public ViewHolder getScrapOrCachedViewForId(long j2, int i2, boolean z) {
            for (int size = this.mAttachedScrap.size() - 1; size >= 0; size--) {
                ViewHolder viewHolder = this.mAttachedScrap.get(size);
                if (viewHolder.getItemId() == j2 && !viewHolder.wasReturnedFromScrap()) {
                    if (i2 == viewHolder.getItemViewType()) {
                        viewHolder.addFlags(32);
                        if (viewHolder.isRemoved() && !RecyclerView.this.mState.isPreLayout()) {
                            viewHolder.setFlags(2, 14);
                        }
                        return viewHolder;
                    } else if (!z) {
                        this.mAttachedScrap.remove(size);
                        RecyclerView.this.removeDetachedView(viewHolder.itemView, false);
                        quickRecycleScrapView(viewHolder.itemView);
                    }
                }
            }
            int size2 = this.mCachedViews.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return null;
                }
                ViewHolder viewHolder2 = this.mCachedViews.get(size2);
                if (viewHolder2.getItemId() == j2 && !viewHolder2.isAttachedToTransitionOverlay()) {
                    if (i2 == viewHolder2.getItemViewType()) {
                        if (!z) {
                            this.mCachedViews.remove(size2);
                        }
                        return viewHolder2;
                    } else if (!z) {
                        recycleCachedViewAt(size2);
                        return null;
                    }
                }
            }
        }

        public ViewHolder getScrapOrHiddenOrCachedHolderForPosition(int i2, boolean z) {
            View findHiddenNonRemovedView;
            int size = this.mAttachedScrap.size();
            int i7 = 0;
            int i8 = 0;
            while (i8 < size) {
                ViewHolder viewHolder = this.mAttachedScrap.get(i8);
                if (viewHolder.wasReturnedFromScrap() || viewHolder.getLayoutPosition() != i2 || viewHolder.isInvalid() || (!RecyclerView.this.mState.mInPreLayout && viewHolder.isRemoved())) {
                    i8++;
                } else {
                    viewHolder.addFlags(32);
                    return viewHolder;
                }
            }
            if (z || (findHiddenNonRemovedView = RecyclerView.this.mChildHelper.findHiddenNonRemovedView(i2)) == null) {
                int size2 = this.mCachedViews.size();
                while (i7 < size2) {
                    ViewHolder viewHolder2 = this.mCachedViews.get(i7);
                    if (viewHolder2.isInvalid() || viewHolder2.getLayoutPosition() != i2 || viewHolder2.isAttachedToTransitionOverlay()) {
                        i7++;
                    } else {
                        if (!z) {
                            this.mCachedViews.remove(i7);
                        }
                        if (RecyclerView.sVerboseLoggingEnabled) {
                            Log.d("SeslRecyclerView", "getScrapOrHiddenOrCachedHolderForPosition(" + i2 + ") found match in cache: " + viewHolder2);
                        }
                        return viewHolder2;
                    }
                }
                return null;
            }
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(findHiddenNonRemovedView);
            RecyclerView.this.mChildHelper.unhide(findHiddenNonRemovedView);
            int indexOfChild = RecyclerView.this.mChildHelper.indexOfChild(findHiddenNonRemovedView);
            if (indexOfChild != -1) {
                RecyclerView.this.mChildHelper.detachViewFromParent(indexOfChild);
                scrapView(findHiddenNonRemovedView);
                childViewHolderInt.addFlags(8224);
                return childViewHolderInt;
            }
            StringBuilder sb2 = new StringBuilder("layout index should not be -1 after unhiding a view:");
            sb2.append(childViewHolderInt);
            throw new IllegalStateException(C0086a.k(RecyclerView.this, sb2));
        }

        public View getScrapViewAt(int i2) {
            return this.mAttachedScrap.get(i2).itemView;
        }

        public View getViewForPosition(int i2) {
            return getViewForPosition(i2, false);
        }

        public void markItemDecorInsetsDirty() {
            int size = this.mCachedViews.size();
            for (int i2 = 0; i2 < size; i2++) {
                LayoutParams layoutParams = (LayoutParams) this.mCachedViews.get(i2).itemView.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams.mInsetsDirty = true;
                }
            }
        }

        public void markKnownViewsInvalid() {
            int size = this.mCachedViews.size();
            for (int i2 = 0; i2 < size; i2++) {
                ViewHolder viewHolder = this.mCachedViews.get(i2);
                if (viewHolder != null) {
                    viewHolder.addFlags(6);
                    viewHolder.addChangePayload((Object) null);
                }
            }
            Adapter adapter = RecyclerView.this.mAdapter;
            if (adapter == null || !adapter.hasStableIds()) {
                recycleAndClearCachedViews();
            }
        }

        public void offsetPositionRecordsForInsert(int i2, int i7) {
            int size = this.mCachedViews.size();
            for (int i8 = 0; i8 < size; i8++) {
                ViewHolder viewHolder = this.mCachedViews.get(i8);
                if (viewHolder != null && viewHolder.mPosition >= i2) {
                    if (RecyclerView.sVerboseLoggingEnabled) {
                        Log.d("SeslRecyclerView", "offsetPositionRecordsForInsert cached " + i8 + " holder " + viewHolder + " now at position " + (viewHolder.mPosition + i7));
                    }
                    viewHolder.offsetPosition(i7, true);
                }
            }
        }

        public void offsetPositionRecordsForMove(int i2, int i7) {
            int i8;
            int i10;
            int i11;
            int i12;
            if (i2 < i7) {
                i11 = -1;
                i10 = i2;
                i8 = i7;
            } else {
                i11 = 1;
                i8 = i2;
                i10 = i7;
            }
            int size = this.mCachedViews.size();
            for (int i13 = 0; i13 < size; i13++) {
                ViewHolder viewHolder = this.mCachedViews.get(i13);
                if (viewHolder != null && (i12 = viewHolder.mPosition) >= i10 && i12 <= i8) {
                    if (i12 == i2) {
                        viewHolder.offsetPosition(i7 - i2, false);
                    } else {
                        viewHolder.offsetPosition(i11, false);
                    }
                    if (RecyclerView.sVerboseLoggingEnabled) {
                        Log.d("SeslRecyclerView", "offsetPositionRecordsForMove cached child " + i13 + " holder " + viewHolder);
                    }
                }
            }
        }

        public void offsetPositionRecordsForRemove(int i2, int i7, boolean z) {
            int i8 = i2 + i7;
            for (int size = this.mCachedViews.size() - 1; size >= 0; size--) {
                ViewHolder viewHolder = this.mCachedViews.get(size);
                if (viewHolder != null) {
                    int i10 = viewHolder.mPosition;
                    if (i10 >= i8) {
                        if (RecyclerView.sVerboseLoggingEnabled) {
                            Log.d("SeslRecyclerView", "offsetPositionRecordsForRemove cached " + size + " holder " + viewHolder + " now at position " + (viewHolder.mPosition - i7));
                        }
                        viewHolder.offsetPosition(-i7, z);
                    } else if (i10 >= i2) {
                        viewHolder.addFlags(8);
                        recycleCachedViewAt(size);
                    }
                }
            }
        }

        public void onAdapterChanged(Adapter<?> adapter, Adapter<?> adapter2, boolean z) {
            clear();
            poolingContainerDetach(adapter, true);
            getRecycledViewPool().onAdapterChanged(adapter, adapter2, z);
            maybeSendPoolingContainerAttach();
        }

        public void onAttachedToWindow() {
            maybeSendPoolingContainerAttach();
        }

        public void onDetachedFromWindow() {
            for (int i2 = 0; i2 < this.mCachedViews.size(); i2++) {
                PoolingContainer.callPoolingContainerOnRelease(this.mCachedViews.get(i2).itemView);
            }
            poolingContainerDetach(RecyclerView.this.mAdapter);
        }

        public void quickRecycleScrapView(View view) {
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            childViewHolderInt.mScrapContainer = null;
            childViewHolderInt.mInChangeScrap = false;
            childViewHolderInt.clearReturnedFromScrapFlag();
            recycleViewHolderInternal(childViewHolderInt);
        }

        public void recycleAndClearCachedViews() {
            for (int size = this.mCachedViews.size() - 1; size >= 0; size--) {
                recycleCachedViewAt(size);
            }
            this.mCachedViews.clear();
            if (RecyclerView.ALLOW_THREAD_GAP_WORK) {
                RecyclerView.this.mPrefetchRegistry.clearPrefetchPositions();
            }
        }

        public void recycleCachedViewAt(int i2) {
            if (RecyclerView.sVerboseLoggingEnabled) {
                C0086a.C(i2, "Recycling cached view at index ", "SeslRecyclerView");
            }
            ViewHolder viewHolder = this.mCachedViews.get(i2);
            if (RecyclerView.sVerboseLoggingEnabled) {
                Log.d("SeslRecyclerView", "CachedViewHolder to be recycled: " + viewHolder);
            }
            addViewHolderToRecycledViewPool(viewHolder, true);
            this.mCachedViews.remove(i2);
        }

        public void recycleView(View view) {
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt.isTmpDetached()) {
                RecyclerView.this.removeDetachedView(view, false);
            }
            if (childViewHolderInt.isScrap()) {
                childViewHolderInt.unScrap();
            } else if (childViewHolderInt.wasReturnedFromScrap()) {
                childViewHolderInt.clearReturnedFromScrapFlag();
            }
            recycleViewHolderInternal(childViewHolderInt);
            if (RecyclerView.this.mItemAnimator != null && !childViewHolderInt.isRecyclable()) {
                RecyclerView.this.mItemAnimator.endAnimation(childViewHolderInt);
            }
        }

        public void recycleViewHolderInternal(ViewHolder viewHolder) {
            boolean z;
            boolean z3;
            boolean z7 = false;
            boolean z9 = true;
            if (viewHolder.isScrap() || viewHolder.itemView.getParent() != null) {
                StringBuilder sb2 = new StringBuilder("Scrapped or attached views may not be recycled. isScrap:");
                sb2.append(viewHolder.isScrap());
                sb2.append(" isAttached:");
                if (viewHolder.itemView.getParent() != null) {
                    z7 = true;
                }
                sb2.append(z7);
                throw new IllegalArgumentException(C0086a.k(RecyclerView.this, sb2));
            } else if (viewHolder.isTmpDetached()) {
                StringBuilder sb3 = new StringBuilder("Tmp detached view should be removed from RecyclerView before it can be recycled: ");
                sb3.append(viewHolder);
                throw new IllegalArgumentException(C0086a.k(RecyclerView.this, sb3));
            } else if (!viewHolder.shouldIgnore()) {
                boolean doesTransientStatePreventRecycling = viewHolder.doesTransientStatePreventRecycling();
                Adapter adapter = RecyclerView.this.mAdapter;
                if (adapter == null || !doesTransientStatePreventRecycling || !adapter.onFailedToRecycleView(viewHolder)) {
                    z = false;
                } else {
                    z = true;
                }
                if (!RecyclerView.sDebugAssertionsEnabled || !this.mCachedViews.contains(viewHolder)) {
                    if (z || viewHolder.isRecyclable()) {
                        if (this.mViewCacheMax <= 0 || viewHolder.hasAnyOfTheFlags(526)) {
                            z3 = false;
                        } else {
                            int size = this.mCachedViews.size();
                            if (size >= this.mViewCacheMax && size > 0) {
                                recycleCachedViewAt(0);
                                size--;
                            }
                            if (RecyclerView.ALLOW_THREAD_GAP_WORK && size > 0 && !RecyclerView.this.mPrefetchRegistry.lastPrefetchIncludedPosition(viewHolder.mPosition)) {
                                int i2 = size - 1;
                                while (i2 >= 0) {
                                    if (!RecyclerView.this.mPrefetchRegistry.lastPrefetchIncludedPosition(this.mCachedViews.get(i2).mPosition)) {
                                        break;
                                    }
                                    i2--;
                                }
                                size = i2 + 1;
                            }
                            this.mCachedViews.add(size, viewHolder);
                            z3 = true;
                        }
                        if (!z3) {
                            addViewHolderToRecycledViewPool(viewHolder, true);
                        } else {
                            z9 = false;
                        }
                        z7 = z3;
                    } else {
                        if (RecyclerView.sVerboseLoggingEnabled) {
                            Log.d("SeslRecyclerView", "trying to recycle a non-recycleable holder. Hopefully, it will re-visit here. We are still removing it from animation lists" + RecyclerView.this.exceptionLabel());
                        }
                        z9 = false;
                    }
                    RecyclerView.this.mViewInfoStore.removeViewHolder(viewHolder);
                    if (!z7 && !z9 && doesTransientStatePreventRecycling) {
                        PoolingContainer.callPoolingContainerOnRelease(viewHolder.itemView);
                        viewHolder.mBindingAdapter = null;
                        viewHolder.mOwnerRecyclerView = null;
                        return;
                    }
                    return;
                }
                StringBuilder sb4 = new StringBuilder("cached view received recycle internal? ");
                sb4.append(viewHolder);
                throw new IllegalArgumentException(C0086a.k(RecyclerView.this, sb4));
            } else {
                throw new IllegalArgumentException(C0086a.k(RecyclerView.this, new StringBuilder("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle.")));
            }
        }

        public void scrapView(View view) {
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (!childViewHolderInt.hasAnyOfTheFlags(12) && childViewHolderInt.isUpdated() && !RecyclerView.this.canReuseUpdatedViewHolder(childViewHolderInt)) {
                if (this.mChangedScrap == null) {
                    this.mChangedScrap = new ArrayList<>();
                }
                childViewHolderInt.setScrapContainer(this, true);
                this.mChangedScrap.add(childViewHolderInt);
            } else if (!childViewHolderInt.isInvalid() || childViewHolderInt.isRemoved() || RecyclerView.this.mAdapter.hasStableIds()) {
                childViewHolderInt.setScrapContainer(this, false);
                this.mAttachedScrap.add(childViewHolderInt);
            } else {
                throw new IllegalArgumentException(C0086a.k(RecyclerView.this, new StringBuilder("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool.")));
            }
        }

        public void setRecycledViewPool(RecycledViewPool recycledViewPool) {
            poolingContainerDetach(RecyclerView.this.mAdapter);
            RecycledViewPool recycledViewPool2 = this.mRecyclerPool;
            if (recycledViewPool2 != null) {
                recycledViewPool2.detach();
            }
            this.mRecyclerPool = recycledViewPool;
            if (!(recycledViewPool == null || RecyclerView.this.getAdapter() == null)) {
                this.mRecyclerPool.attach();
            }
            maybeSendPoolingContainerAttach();
        }

        public void setViewCacheSize(int i2) {
            this.mRequestedCacheMax = i2;
            updateViewCacheSize();
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0037  */
        /* JADX WARNING: Removed duplicated region for block: B:25:0x005c  */
        /* JADX WARNING: Removed duplicated region for block: B:27:0x005f  */
        /* JADX WARNING: Removed duplicated region for block: B:70:0x0159  */
        /* JADX WARNING: Removed duplicated region for block: B:75:0x0182  */
        /* JADX WARNING: Removed duplicated region for block: B:76:0x0185  */
        /* JADX WARNING: Removed duplicated region for block: B:92:0x01d1  */
        /* JADX WARNING: Removed duplicated region for block: B:93:0x01df  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public androidx.recyclerview.widget.RecyclerView.ViewHolder tryGetViewHolderForPositionByDeadline(int r17, boolean r18, long r19) {
            /*
                r16 = this;
                r0 = r16
                r3 = r17
                r1 = r18
                if (r3 < 0) goto L_0x0203
                androidx.recyclerview.widget.RecyclerView r2 = androidx.recyclerview.widget.RecyclerView.this
                androidx.recyclerview.widget.RecyclerView$State r2 = r2.mState
                int r2 = r2.getItemCount()
                if (r3 >= r2) goto L_0x0203
                androidx.recyclerview.widget.RecyclerView r2 = androidx.recyclerview.widget.RecyclerView.this
                androidx.recyclerview.widget.RecyclerView$State r2 = r2.mState
                boolean r2 = r2.isPreLayout()
                r4 = 0
                r6 = 1
                r7 = 0
                if (r2 == 0) goto L_0x0027
                androidx.recyclerview.widget.RecyclerView$ViewHolder r2 = r16.getChangedScrapViewForPosition(r17)
                if (r2 == 0) goto L_0x0028
                r5 = r6
                goto L_0x0029
            L_0x0027:
                r2 = r4
            L_0x0028:
                r5 = r7
            L_0x0029:
                if (r2 != 0) goto L_0x005d
                androidx.recyclerview.widget.RecyclerView$ViewHolder r2 = r16.getScrapOrHiddenOrCachedHolderForPosition(r17, r18)
                if (r2 == 0) goto L_0x005d
                boolean r8 = r0.validateViewHolderForOffsetPosition(r2)
                if (r8 != 0) goto L_0x005c
                if (r1 != 0) goto L_0x005a
                r8 = 4
                r2.addFlags(r8)
                boolean r8 = r2.isScrap()
                if (r8 == 0) goto L_0x004e
                androidx.recyclerview.widget.RecyclerView r8 = androidx.recyclerview.widget.RecyclerView.this
                android.view.View r9 = r2.itemView
                r8.removeDetachedView(r9, r7)
                r2.unScrap()
                goto L_0x0057
            L_0x004e:
                boolean r8 = r2.wasReturnedFromScrap()
                if (r8 == 0) goto L_0x0057
                r2.clearReturnedFromScrapFlag()
            L_0x0057:
                r0.recycleViewHolderInternal(r2)
            L_0x005a:
                r2 = r4
                goto L_0x005d
            L_0x005c:
                r5 = r6
            L_0x005d:
                if (r2 != 0) goto L_0x0116
                androidx.recyclerview.widget.RecyclerView r8 = androidx.recyclerview.widget.RecyclerView.this
                androidx.recyclerview.widget.AdapterHelper r8 = r8.mAdapterHelper
                int r8 = r8.findPositionOffset(r3)
                if (r8 < 0) goto L_0x0119
                androidx.recyclerview.widget.RecyclerView r9 = androidx.recyclerview.widget.RecyclerView.this
                androidx.recyclerview.widget.RecyclerView$Adapter r9 = r9.mAdapter
                int r9 = r9.getItemCount()
                if (r8 >= r9) goto L_0x0119
                androidx.recyclerview.widget.RecyclerView r9 = androidx.recyclerview.widget.RecyclerView.this
                androidx.recyclerview.widget.RecyclerView$Adapter r9 = r9.mAdapter
                int r11 = r9.getItemViewType(r8)
                androidx.recyclerview.widget.RecyclerView r9 = androidx.recyclerview.widget.RecyclerView.this
                androidx.recyclerview.widget.RecyclerView$Adapter r9 = r9.mAdapter
                boolean r9 = r9.hasStableIds()
                if (r9 == 0) goto L_0x0096
                androidx.recyclerview.widget.RecyclerView r2 = androidx.recyclerview.widget.RecyclerView.this
                androidx.recyclerview.widget.RecyclerView$Adapter r2 = r2.mAdapter
                long r9 = r2.getItemId(r8)
                androidx.recyclerview.widget.RecyclerView$ViewHolder r2 = r0.getScrapOrCachedViewForId(r9, r11, r1)
                if (r2 == 0) goto L_0x0096
                r2.mPosition = r8
                r5 = r6
            L_0x0096:
                java.lang.String r1 = "SeslRecyclerView"
                if (r2 != 0) goto L_0x00c9
                boolean r2 = androidx.recyclerview.widget.RecyclerView.sVerboseLoggingEnabled
                if (r2 == 0) goto L_0x00b5
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                java.lang.String r8 = "tryGetViewHolderForPositionByDeadline("
                r2.<init>(r8)
                r2.append(r3)
                java.lang.String r8 = ") fetching from shared pool"
                r2.append(r8)
                java.lang.String r2 = r2.toString()
                android.util.Log.d(r1, r2)
            L_0x00b5:
                androidx.recyclerview.widget.RecyclerView$RecycledViewPool r2 = r0.getRecycledViewPool()
                androidx.recyclerview.widget.RecyclerView$ViewHolder r2 = r2.getRecycledView(r11)
                if (r2 == 0) goto L_0x00c9
                r2.resetInternal()
                boolean r8 = androidx.recyclerview.widget.RecyclerView.FORCE_INVALIDATE_DISPLAY_LIST
                if (r8 == 0) goto L_0x00c9
                r0.invalidateDisplayListInt(r2)
            L_0x00c9:
                if (r2 != 0) goto L_0x0116
                androidx.recyclerview.widget.RecyclerView r2 = androidx.recyclerview.widget.RecyclerView.this
                long r12 = r2.getNanoTime()
                r8 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r2 = (r19 > r8 ? 1 : (r19 == r8 ? 0 : -1))
                if (r2 == 0) goto L_0x00e5
                androidx.recyclerview.widget.RecyclerView$RecycledViewPool r10 = r0.mRecyclerPool
                r14 = r19
                boolean r2 = r10.willCreateInTime(r11, r12, r14)
                if (r2 != 0) goto L_0x00e5
                return r4
            L_0x00e5:
                androidx.recyclerview.widget.RecyclerView r2 = androidx.recyclerview.widget.RecyclerView.this
                androidx.recyclerview.widget.RecyclerView$Adapter r4 = r2.mAdapter
                androidx.recyclerview.widget.RecyclerView$ViewHolder r2 = r4.createViewHolder(r2, r11)
                boolean r4 = androidx.recyclerview.widget.RecyclerView.ALLOW_THREAD_GAP_WORK
                if (r4 == 0) goto L_0x0100
                android.view.View r4 = r2.itemView
                androidx.recyclerview.widget.RecyclerView r4 = androidx.recyclerview.widget.RecyclerView.findNestedRecyclerView(r4)
                if (r4 == 0) goto L_0x0100
                java.lang.ref.WeakReference r8 = new java.lang.ref.WeakReference
                r8.<init>(r4)
                r2.mNestedRecyclerView = r8
            L_0x0100:
                androidx.recyclerview.widget.RecyclerView r4 = androidx.recyclerview.widget.RecyclerView.this
                long r8 = r4.getNanoTime()
                androidx.recyclerview.widget.RecyclerView$RecycledViewPool r4 = r0.mRecyclerPool
                long r8 = r8 - r12
                r4.factorInCreateTime(r11, r8)
                boolean r4 = androidx.recyclerview.widget.RecyclerView.sVerboseLoggingEnabled
                if (r4 == 0) goto L_0x0116
                java.lang.String r4 = "tryGetViewHolderForPositionByDeadline created new ViewHolder"
                android.util.Log.d(r1, r4)
            L_0x0116:
                r1 = r2
                r8 = r5
                goto L_0x013a
            L_0x0119:
                java.lang.IndexOutOfBoundsException r1 = new java.lang.IndexOutOfBoundsException
                java.lang.String r2 = "(offset:"
                java.lang.String r4 = ").state:"
                java.lang.String r5 = "Inconsistency detected. Invalid item position "
                java.lang.StringBuilder r2 = A.a.h(r3, r8, r5, r2, r4)
                androidx.recyclerview.widget.RecyclerView r3 = androidx.recyclerview.widget.RecyclerView.this
                androidx.recyclerview.widget.RecyclerView$State r3 = r3.mState
                int r3 = r3.getItemCount()
                r2.append(r3)
                androidx.recyclerview.widget.RecyclerView r0 = androidx.recyclerview.widget.RecyclerView.this
                java.lang.String r0 = c0.C0086a.k(r0, r2)
                r1.<init>(r0)
                throw r1
            L_0x013a:
                if (r8 == 0) goto L_0x0172
                androidx.recyclerview.widget.RecyclerView r2 = androidx.recyclerview.widget.RecyclerView.this
                androidx.recyclerview.widget.RecyclerView$State r2 = r2.mState
                boolean r2 = r2.isPreLayout()
                if (r2 != 0) goto L_0x0172
                r2 = 8192(0x2000, float:1.14794E-41)
                boolean r4 = r1.hasAnyOfTheFlags(r2)
                if (r4 == 0) goto L_0x0172
                r1.setFlags(r7, r2)
                androidx.recyclerview.widget.RecyclerView r2 = androidx.recyclerview.widget.RecyclerView.this
                androidx.recyclerview.widget.RecyclerView$State r2 = r2.mState
                boolean r2 = r2.mRunSimpleAnimations
                if (r2 == 0) goto L_0x0172
                int r2 = androidx.recyclerview.widget.RecyclerView.ItemAnimator.buildAdapterChangeFlagsForAnimations(r1)
                r2 = r2 | 4096(0x1000, float:5.74E-42)
                androidx.recyclerview.widget.RecyclerView r4 = androidx.recyclerview.widget.RecyclerView.this
                androidx.recyclerview.widget.RecyclerView$ItemAnimator r5 = r4.mItemAnimator
                androidx.recyclerview.widget.RecyclerView$State r4 = r4.mState
                java.util.List r9 = r1.getUnmodifiedPayloads()
                androidx.recyclerview.widget.RecyclerView$ItemAnimator$ItemHolderInfo r2 = r5.recordPreLayoutInformation(r4, r1, r2, r9)
                androidx.recyclerview.widget.RecyclerView r4 = androidx.recyclerview.widget.RecyclerView.this
                r4.recordAnimationInfoIfBouncedHiddenView(r1, r2)
            L_0x0172:
                androidx.recyclerview.widget.RecyclerView r2 = androidx.recyclerview.widget.RecyclerView.this
                androidx.recyclerview.widget.RecyclerView$State r2 = r2.mState
                boolean r2 = r2.isPreLayout()
                if (r2 == 0) goto L_0x0185
                boolean r2 = r1.isBound()
                if (r2 == 0) goto L_0x0185
                r1.mPreLayoutPosition = r3
                goto L_0x0198
            L_0x0185:
                boolean r2 = r1.isBound()
                if (r2 == 0) goto L_0x019a
                boolean r2 = r1.needsUpdate()
                if (r2 != 0) goto L_0x019a
                boolean r2 = r1.isInvalid()
                if (r2 == 0) goto L_0x0198
                goto L_0x019a
            L_0x0198:
                r2 = r7
                goto L_0x01c9
            L_0x019a:
                boolean r2 = androidx.recyclerview.widget.RecyclerView.sDebugAssertionsEnabled
                if (r2 == 0) goto L_0x01bb
                boolean r2 = r1.isRemoved()
                if (r2 != 0) goto L_0x01a5
                goto L_0x01bb
            L_0x01a5:
                java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                java.lang.String r4 = "Removed holder should be bound and it should come here only in pre-layout. Holder: "
                r3.<init>(r4)
                r3.append(r1)
                androidx.recyclerview.widget.RecyclerView r0 = androidx.recyclerview.widget.RecyclerView.this
                java.lang.String r0 = c0.C0086a.k(r0, r3)
                r2.<init>(r0)
                throw r2
            L_0x01bb:
                androidx.recyclerview.widget.RecyclerView r2 = androidx.recyclerview.widget.RecyclerView.this
                androidx.recyclerview.widget.AdapterHelper r2 = r2.mAdapterHelper
                int r2 = r2.findPositionOffset(r3)
                r4 = r19
                boolean r2 = r0.tryBindViewHolderByDeadline(r1, r2, r3, r4)
            L_0x01c9:
                android.view.View r3 = r1.itemView
                android.view.ViewGroup$LayoutParams r3 = r3.getLayoutParams()
                if (r3 != 0) goto L_0x01df
                androidx.recyclerview.widget.RecyclerView r0 = androidx.recyclerview.widget.RecyclerView.this
                android.view.ViewGroup$LayoutParams r0 = r0.generateDefaultLayoutParams()
                androidx.recyclerview.widget.RecyclerView$LayoutParams r0 = (androidx.recyclerview.widget.RecyclerView.LayoutParams) r0
                android.view.View r3 = r1.itemView
                r3.setLayoutParams(r0)
                goto L_0x01f8
            L_0x01df:
                androidx.recyclerview.widget.RecyclerView r4 = androidx.recyclerview.widget.RecyclerView.this
                boolean r4 = r4.checkLayoutParams(r3)
                if (r4 != 0) goto L_0x01f5
                androidx.recyclerview.widget.RecyclerView r0 = androidx.recyclerview.widget.RecyclerView.this
                android.view.ViewGroup$LayoutParams r0 = r0.generateLayoutParams((android.view.ViewGroup.LayoutParams) r3)
                androidx.recyclerview.widget.RecyclerView$LayoutParams r0 = (androidx.recyclerview.widget.RecyclerView.LayoutParams) r0
                android.view.View r3 = r1.itemView
                r3.setLayoutParams(r0)
                goto L_0x01f8
            L_0x01f5:
                r0 = r3
                androidx.recyclerview.widget.RecyclerView$LayoutParams r0 = (androidx.recyclerview.widget.RecyclerView.LayoutParams) r0
            L_0x01f8:
                r0.mViewHolder = r1
                if (r8 == 0) goto L_0x01ff
                if (r2 == 0) goto L_0x01ff
                goto L_0x0200
            L_0x01ff:
                r6 = r7
            L_0x0200:
                r0.mPendingInvalidate = r6
                return r1
            L_0x0203:
                java.lang.IndexOutOfBoundsException r1 = new java.lang.IndexOutOfBoundsException
                java.lang.String r2 = "("
                java.lang.String r4 = "). Item count:"
                java.lang.String r5 = "Invalid item position "
                java.lang.StringBuilder r2 = A.a.h(r3, r3, r5, r2, r4)
                androidx.recyclerview.widget.RecyclerView r3 = androidx.recyclerview.widget.RecyclerView.this
                androidx.recyclerview.widget.RecyclerView$State r3 = r3.mState
                int r3 = r3.getItemCount()
                r2.append(r3)
                androidx.recyclerview.widget.RecyclerView r0 = androidx.recyclerview.widget.RecyclerView.this
                java.lang.String r0 = c0.C0086a.k(r0, r2)
                r1.<init>(r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.Recycler.tryGetViewHolderForPositionByDeadline(int, boolean, long):androidx.recyclerview.widget.RecyclerView$ViewHolder");
        }

        public void unscrapView(ViewHolder viewHolder) {
            if (viewHolder.mInChangeScrap) {
                this.mChangedScrap.remove(viewHolder);
            } else {
                this.mAttachedScrap.remove(viewHolder);
            }
            viewHolder.mScrapContainer = null;
            viewHolder.mInChangeScrap = false;
            viewHolder.clearReturnedFromScrapFlag();
        }

        public void updateViewCacheSize() {
            int i2;
            LayoutManager layoutManager = RecyclerView.this.mLayout;
            if (layoutManager != null) {
                i2 = layoutManager.mPrefetchMaxCountObserved;
            } else {
                i2 = 0;
            }
            this.mViewCacheMax = this.mRequestedCacheMax + i2;
            for (int size = this.mCachedViews.size() - 1; size >= 0 && this.mCachedViews.size() > this.mViewCacheMax; size--) {
                recycleCachedViewAt(size);
            }
        }

        public boolean validateViewHolderForOffsetPosition(ViewHolder viewHolder) {
            if (!viewHolder.isRemoved()) {
                int i2 = viewHolder.mPosition;
                if (i2 < 0 || i2 >= RecyclerView.this.mAdapter.getItemCount()) {
                    StringBuilder sb2 = new StringBuilder("Inconsistency detected. Invalid view holder adapter position");
                    sb2.append(viewHolder);
                    throw new IndexOutOfBoundsException(C0086a.k(RecyclerView.this, sb2));
                } else if (!RecyclerView.this.mState.isPreLayout() && RecyclerView.this.mAdapter.getItemViewType(viewHolder.mPosition) != viewHolder.getItemViewType()) {
                    return false;
                } else {
                    if (!RecyclerView.this.mAdapter.hasStableIds() || viewHolder.getItemId() == RecyclerView.this.mAdapter.getItemId(viewHolder.mPosition)) {
                        return true;
                    }
                    return false;
                }
            } else if (!RecyclerView.sDebugAssertionsEnabled || RecyclerView.this.mState.isPreLayout()) {
                return RecyclerView.this.mState.isPreLayout();
            } else {
                throw new IllegalStateException(C0086a.k(RecyclerView.this, new StringBuilder("should not receive a removed view unless it is pre layout")));
            }
        }

        public void viewRangeUpdate(int i2, int i7) {
            int i8;
            int i10 = i7 + i2;
            for (int size = this.mCachedViews.size() - 1; size >= 0; size--) {
                ViewHolder viewHolder = this.mCachedViews.get(size);
                if (viewHolder != null && (i8 = viewHolder.mPosition) >= i2 && i8 < i10) {
                    viewHolder.addFlags(2);
                    recycleCachedViewAt(size);
                }
            }
        }

        private void poolingContainerDetach(Adapter<?> adapter, boolean z) {
            RecycledViewPool recycledViewPool = this.mRecyclerPool;
            if (recycledViewPool != null) {
                recycledViewPool.detachForPoolingContainer(adapter, z);
            }
        }

        public View getViewForPosition(int i2, boolean z) {
            return tryGetViewHolderForPositionByDeadline(i2, z, Long.MAX_VALUE).itemView;
        }

        private void invalidateDisplayListInt(ViewGroup viewGroup, boolean z) {
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (childAt instanceof ViewGroup) {
                    invalidateDisplayListInt((ViewGroup) childAt, true);
                }
            }
            if (z) {
                if (viewGroup.getVisibility() == 4) {
                    viewGroup.setVisibility(0);
                    viewGroup.setVisibility(4);
                    return;
                }
                int visibility = viewGroup.getVisibility();
                viewGroup.setVisibility(4);
                viewGroup.setVisibility(visibility);
            }
        }

        public void setViewCacheExtension(ViewCacheExtension viewCacheExtension) {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface RecyclerListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class RecyclerViewDataObserver extends AdapterDataObserver {
        public RecyclerViewDataObserver() {
        }

        public void onChanged() {
            RecyclerView.this.assertNotInLayoutOrScroll((String) null);
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.mState.mStructureChanged = true;
            recyclerView.processDataSetCompletelyChanged(true);
            if (!RecyclerView.this.mAdapterHelper.hasPendingUpdates()) {
                RecyclerView.this.requestLayout();
            }
            SeslRecyclerViewFastScroller unused = RecyclerView.this.getClass();
            if (RecyclerView.this.mIndexTip != null) {
                RecyclerView.this.mIndexTip.updateSections();
            }
        }

        public void onItemRangeChanged(int i2, int i7, Object obj) {
            RecyclerView.this.assertNotInLayoutOrScroll((String) null);
            if (RecyclerView.this.mAdapterHelper.onItemRangeChanged(i2, i7, obj)) {
                triggerUpdateProcessor();
            }
        }

        public void onItemRangeInserted(int i2, int i7) {
            RecyclerView.this.assertNotInLayoutOrScroll((String) null);
            if (RecyclerView.this.mAdapterHelper.onItemRangeInserted(i2, i7)) {
                triggerUpdateProcessor();
            }
        }

        public void onItemRangeMoved(int i2, int i7, int i8) {
            RecyclerView.this.assertNotInLayoutOrScroll((String) null);
            if (RecyclerView.this.mAdapterHelper.onItemRangeMoved(i2, i7, i8)) {
                triggerUpdateProcessor();
            }
        }

        public void onItemRangeRemoved(int i2, int i7) {
            RecyclerView.this.assertNotInLayoutOrScroll((String) null);
            if (RecyclerView.this.mAdapterHelper.onItemRangeRemoved(i2, i7)) {
                triggerUpdateProcessor();
            }
        }

        public void triggerUpdateProcessor() {
            if (RecyclerView.POST_UPDATES_ON_ANIMATION) {
                RecyclerView recyclerView = RecyclerView.this;
                if (recyclerView.mHasFixedSize && recyclerView.mIsAttached) {
                    ViewCompat.postOnAnimation(recyclerView, recyclerView.mUpdateChildViewsRunnable);
                    return;
                }
            }
            RecyclerView recyclerView2 = RecyclerView.this;
            recyclerView2.mAdapterUpdateDuringMeasure = true;
            recyclerView2.requestLayout();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ScrollArrowDirection {
        UP,
        RIGHT,
        DOWN,
        LEFT
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SeslLongPressMultiSelectionListener {
        void onItemSelected(RecyclerView recyclerView, View view, int i2, long j2);

        void onLongPressMultiSelectionEnded(int i2, int i7);

        void onLongPressMultiSelectionStarted(int i2, int i7);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SeslOnGoToTopClickListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class SmoothScroller {
        protected Rect mAvailableBounds = null;
        private LayoutManager mLayoutManager;
        private boolean mPendingInitialRun;
        private RecyclerView mRecyclerView;
        private final Action mRecyclingAction = new Action(0, 0);
        private boolean mRunning;
        private boolean mStarted;
        private int mTargetPosition = -1;
        private View mTargetView;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Action {
            private boolean mChanged;
            private int mConsecutiveUpdates;
            private int mDuration;
            private int mDx;
            private int mDy;
            private Interpolator mInterpolator;
            private int mJumpToPosition;

            public Action(int i2, int i7) {
                this(i2, i7, Integer.MIN_VALUE, (Interpolator) null);
            }

            private void validate() {
                if (this.mInterpolator != null && this.mDuration < 1) {
                    throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
                } else if (this.mDuration < 1) {
                    throw new IllegalStateException("Scroll duration must be a positive number");
                }
            }

            public boolean hasJumpTarget() {
                if (this.mJumpToPosition >= 0) {
                    return true;
                }
                return false;
            }

            public void jumpTo(int i2) {
                this.mJumpToPosition = i2;
            }

            public void runIfNecessary(RecyclerView recyclerView) {
                int i2 = this.mJumpToPosition;
                if (i2 >= 0) {
                    this.mJumpToPosition = -1;
                    recyclerView.jumpToPositionForSmoothScroller(i2);
                    this.mChanged = false;
                } else if (this.mChanged) {
                    validate();
                    recyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy, this.mDuration, this.mInterpolator);
                    int i7 = this.mConsecutiveUpdates + 1;
                    this.mConsecutiveUpdates = i7;
                    if (i7 > 10) {
                        Log.e("SeslRecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                    }
                    this.mChanged = false;
                } else {
                    this.mConsecutiveUpdates = 0;
                }
            }

            public void update(int i2, int i7, int i8, Interpolator interpolator) {
                this.mDx = i2;
                this.mDy = i7;
                this.mDuration = i8;
                this.mInterpolator = interpolator;
                this.mChanged = true;
            }

            public Action(int i2, int i7, int i8, Interpolator interpolator) {
                this.mJumpToPosition = -1;
                this.mChanged = false;
                this.mConsecutiveUpdates = 0;
                this.mDx = i2;
                this.mDy = i7;
                this.mDuration = i8;
                this.mInterpolator = interpolator;
            }
        }

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public interface ScrollVectorProvider {
            PointF computeScrollVectorForPosition(int i2);
        }

        public PointF computeScrollVectorForPosition(int i2) {
            LayoutManager layoutManager = getLayoutManager();
            if (layoutManager instanceof ScrollVectorProvider) {
                return ((ScrollVectorProvider) layoutManager).computeScrollVectorForPosition(i2);
            }
            Log.w("SeslRecyclerView", "You should override computeScrollVectorForPosition when the LayoutManager does not implement " + ScrollVectorProvider.class.getCanonicalName());
            return null;
        }

        public View findViewByPosition(int i2) {
            return this.mRecyclerView.mLayout.findViewByPosition(i2);
        }

        public int getChildCount() {
            return this.mRecyclerView.mLayout.getChildCount();
        }

        public int getChildPosition(View view) {
            return this.mRecyclerView.getChildLayoutPosition(view);
        }

        public LayoutManager getLayoutManager() {
            return this.mLayoutManager;
        }

        public int getTargetPosition() {
            return this.mTargetPosition;
        }

        public boolean isPendingInitialRun() {
            return this.mPendingInitialRun;
        }

        public boolean isRunning() {
            return this.mRunning;
        }

        public void normalize(PointF pointF) {
            float f = pointF.x;
            float f5 = pointF.y;
            float sqrt = (float) Math.sqrt((double) ((f5 * f5) + (f * f)));
            pointF.x /= sqrt;
            pointF.y /= sqrt;
        }

        public void onAnimation(int i2, int i7) {
            PointF computeScrollVectorForPosition;
            RecyclerView recyclerView = this.mRecyclerView;
            if (this.mTargetPosition == -1 || recyclerView == null) {
                stop();
            }
            if (this.mPendingInitialRun && this.mTargetView == null && this.mLayoutManager != null && (computeScrollVectorForPosition = computeScrollVectorForPosition(this.mTargetPosition)) != null) {
                float f = computeScrollVectorForPosition.x;
                if (!(f == 0.0f && computeScrollVectorForPosition.y == 0.0f)) {
                    recyclerView.scrollStep((int) Math.signum(f), (int) Math.signum(computeScrollVectorForPosition.y), (int[]) null);
                }
            }
            this.mPendingInitialRun = false;
            View view = this.mTargetView;
            if (view != null) {
                if (getChildPosition(view) == this.mTargetPosition) {
                    onTargetFound(this.mTargetView, recyclerView.mState, this.mRecyclingAction);
                    this.mRecyclingAction.runIfNecessary(recyclerView);
                    stop();
                } else {
                    Log.e("SeslRecyclerView", "Passed over target position while smooth scrolling.");
                    this.mTargetView = null;
                }
            }
            if (this.mRunning) {
                onSeekTargetStep(i2, i7, recyclerView.mState, this.mRecyclingAction);
                boolean hasJumpTarget = this.mRecyclingAction.hasJumpTarget();
                this.mRecyclingAction.runIfNecessary(recyclerView);
                if (hasJumpTarget && this.mRunning) {
                    this.mPendingInitialRun = true;
                    recyclerView.mViewFlinger.postOnAnimation();
                }
            }
        }

        public void onChildAttachedToWindow(View view) {
            if (getChildPosition(view) == getTargetPosition()) {
                this.mTargetView = view;
                if (RecyclerView.sVerboseLoggingEnabled) {
                    Log.d("SeslRecyclerView", "smooth scroll target view has been attached");
                }
            }
        }

        public abstract void onSeekTargetStep(int i2, int i7, State state, Action action);

        public abstract void onStart();

        public abstract void onStop();

        public abstract void onTargetFound(View view, State state, Action action);

        public void seslSetAvailableBounds(Rect rect) {
            this.mAvailableBounds = rect;
        }

        public void setTargetPosition(int i2) {
            this.mTargetPosition = i2;
        }

        public void start(RecyclerView recyclerView, LayoutManager layoutManager) {
            recyclerView.mViewFlinger.stop();
            if (this.mStarted) {
                Log.w("SeslRecyclerView", "An instance of " + getClass().getSimpleName() + " was started more than once. Each instance of" + getClass().getSimpleName() + " is intended to only be used once. You should create a new instance for each use.");
            }
            this.mRecyclerView = recyclerView;
            this.mLayoutManager = layoutManager;
            int i2 = this.mTargetPosition;
            if (i2 != -1) {
                recyclerView.mState.mTargetPosition = i2;
                this.mRunning = true;
                this.mPendingInitialRun = true;
                this.mTargetView = findViewByPosition(getTargetPosition());
                onStart();
                this.mRecyclerView.mViewFlinger.postOnAnimation();
                this.mStarted = true;
                return;
            }
            throw new IllegalArgumentException("Invalid target position");
        }

        public final void stop() {
            if (this.mRunning) {
                this.mRunning = false;
                onStop();
                this.mRecyclerView.mState.mTargetPosition = -1;
                this.mTargetView = null;
                this.mTargetPosition = -1;
                this.mPendingInitialRun = false;
                this.mLayoutManager.onSmoothScrollerStopped(this);
                this.mLayoutManager = null;
                this.mRecyclerView = null;
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class State {
        private SparseArray<Object> mData;
        int mDeletedInvisibleItemCountSincePreviousLayout = 0;
        long mFocusedItemId;
        int mFocusedItemPosition;
        int mFocusedSubChildId;
        boolean mInPreLayout = false;
        boolean mIsMeasuring = false;
        int mItemCount = 0;
        int mLayoutStep = 1;
        int mPreviousLayoutItemCount = 0;
        int mRemainingScrollHorizontal;
        int mRemainingScrollVertical;
        boolean mRunPredictiveAnimations = false;
        boolean mRunSimpleAnimations = false;
        boolean mStructureChanged = false;
        int mTargetPosition = -1;
        boolean mTrackOldChangeHolders = false;

        public void assertLayoutStep(int i2) {
            if ((this.mLayoutStep & i2) == 0) {
                throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(i2) + " but it is " + Integer.toBinaryString(this.mLayoutStep));
            }
        }

        public boolean didStructureChange() {
            return this.mStructureChanged;
        }

        public int getItemCount() {
            if (this.mInPreLayout) {
                return this.mPreviousLayoutItemCount - this.mDeletedInvisibleItemCountSincePreviousLayout;
            }
            return this.mItemCount;
        }

        public int getTargetScrollPosition() {
            return this.mTargetPosition;
        }

        public boolean hasTargetScrollPosition() {
            if (this.mTargetPosition != -1) {
                return true;
            }
            return false;
        }

        public boolean isPreLayout() {
            return this.mInPreLayout;
        }

        public void prepareForNestedPrefetch(Adapter adapter) {
            this.mLayoutStep = 1;
            this.mItemCount = adapter.getItemCount();
            this.mInPreLayout = false;
            this.mTrackOldChangeHolders = false;
            this.mIsMeasuring = false;
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("State{mTargetPosition=");
            sb2.append(this.mTargetPosition);
            sb2.append(", mData=");
            sb2.append(this.mData);
            sb2.append(", mItemCount=");
            sb2.append(this.mItemCount);
            sb2.append(", mIsMeasuring=");
            sb2.append(this.mIsMeasuring);
            sb2.append(", mPreviousLayoutItemCount=");
            sb2.append(this.mPreviousLayoutItemCount);
            sb2.append(", mDeletedInvisibleItemCountSincePreviousLayout=");
            sb2.append(this.mDeletedInvisibleItemCountSincePreviousLayout);
            sb2.append(", mStructureChanged=");
            sb2.append(this.mStructureChanged);
            sb2.append(", mInPreLayout=");
            sb2.append(this.mInPreLayout);
            sb2.append(", mRunSimpleAnimations=");
            sb2.append(this.mRunSimpleAnimations);
            sb2.append(", mRunPredictiveAnimations=");
            return C0086a.n(sb2, this.mRunPredictiveAnimations, '}');
        }

        public boolean willRunPredictiveAnimations() {
            return this.mRunPredictiveAnimations;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class StretchEdgeEffectFactory extends EdgeEffectFactory {
        public EdgeEffect createEdgeEffect(RecyclerView recyclerView, int i2) {
            return new EdgeEffect(recyclerView.getContext());
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class ViewCacheExtension {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ViewFlinger implements Runnable {
        private boolean mEatRunOnAnimationRequest = false;
        Interpolator mInterpolator;
        private int mLastFlingX;
        private int mLastFlingY;
        OverScroller mOverScroller;
        private boolean mReSchedulePostAnimationCallback = false;

        public ViewFlinger() {
            Interpolator interpolator = RecyclerView.sQuinticInterpolator;
            this.mInterpolator = interpolator;
            this.mOverScroller = new OverScroller(RecyclerView.this.getContext(), interpolator);
        }

        private int computeScrollDuration(int i2, int i7, int i8, int i10) {
            boolean z;
            int i11;
            int i12;
            int abs = Math.abs(i2);
            int abs2 = Math.abs(i7);
            if (abs > abs2) {
                z = true;
            } else {
                z = false;
            }
            int sqrt = (int) Math.sqrt((double) ((i10 * i10) + (i8 * i8)));
            int sqrt2 = (int) Math.sqrt((double) ((i7 * i7) + (i2 * i2)));
            RecyclerView recyclerView = RecyclerView.this;
            if (z) {
                i11 = recyclerView.getWidth();
            } else {
                i11 = recyclerView.getHeight();
            }
            int i13 = i11 / 2;
            float f = (float) i11;
            float f5 = (float) i13;
            float distanceInfluenceForSnapDuration = (distanceInfluenceForSnapDuration(Math.min(1.0f, (((float) sqrt2) * 1.0f) / f)) * f5) + f5;
            if (sqrt > 0) {
                i12 = Math.round(Math.abs(distanceInfluenceForSnapDuration / ((float) sqrt)) * 1000.0f) * 4;
            } else {
                if (!z) {
                    abs = abs2;
                }
                i12 = (int) (((((float) abs) / f) + 1.0f) * 300.0f);
            }
            return Math.min(i12, 2000);
        }

        private float distanceInfluenceForSnapDuration(float f) {
            return (float) Math.sin((double) ((f - 0.5f) * 0.47123894f));
        }

        private void internalPostOnAnimation() {
            RecyclerView.this.removeCallbacks(this);
            ViewCompat.postOnAnimation(RecyclerView.this, this);
        }

        public void fling(int i2, int i7) {
            RecyclerView.this.setScrollState(2);
            this.mLastFlingY = 0;
            this.mLastFlingX = 0;
            Interpolator interpolator = this.mInterpolator;
            Interpolator interpolator2 = RecyclerView.sQuinticInterpolator;
            if (interpolator != interpolator2) {
                this.mInterpolator = interpolator2;
                this.mOverScroller = new OverScroller(RecyclerView.this.getContext(), interpolator2);
            }
            SeslOverScrollerReflector.fling(this.mOverScroller, 0, 0, i2, i7, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, RecyclerView.this.mIsSkipMoveEvent, RecyclerView.this.mFrameLatency);
            postOnAnimation();
        }

        public void postOnAnimation() {
            if (this.mEatRunOnAnimationRequest) {
                this.mReSchedulePostAnimationCallback = true;
            } else {
                internalPostOnAnimation();
            }
        }

        public void run() {
            int i2;
            int i7;
            boolean z;
            boolean z3;
            boolean z7;
            int i8;
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.mLayout == null) {
                stop();
                return;
            }
            this.mReSchedulePostAnimationCallback = false;
            this.mEatRunOnAnimationRequest = true;
            recyclerView.consumePendingUpdateOperations();
            OverScroller overScroller = this.mOverScroller;
            if (overScroller.computeScrollOffset()) {
                int currX = overScroller.getCurrX();
                int currY = overScroller.getCurrY();
                int i10 = currX - this.mLastFlingX;
                this.mLastFlingX = currX;
                this.mLastFlingY = currY;
                int consumeFlingInHorizontalStretch = RecyclerView.this.consumeFlingInHorizontalStretch(i10);
                int consumeFlingInVerticalStretch = RecyclerView.this.consumeFlingInVerticalStretch(currY - this.mLastFlingY);
                RecyclerView recyclerView2 = RecyclerView.this;
                int[] iArr = recyclerView2.mReusableIntPair;
                iArr[0] = 0;
                iArr[1] = 0;
                if (recyclerView2.dispatchNestedPreScroll(consumeFlingInHorizontalStretch, consumeFlingInVerticalStretch, iArr, (int[]) null, 1)) {
                    RecyclerView recyclerView3 = RecyclerView.this;
                    int[] iArr2 = recyclerView3.mReusableIntPair;
                    consumeFlingInHorizontalStretch -= iArr2[0];
                    int i11 = iArr2[1];
                    consumeFlingInVerticalStretch -= i11;
                    recyclerView3.adjustNestedScrollRangeBy(i11);
                } else {
                    RecyclerView.this.adjustNestedScrollRangeBy(consumeFlingInVerticalStretch);
                }
                if (RecyclerView.this.getOverScrollMode() != 2) {
                    RecyclerView.this.considerReleasingGlowsOnScroll(consumeFlingInHorizontalStretch, consumeFlingInVerticalStretch);
                }
                RecyclerView recyclerView4 = RecyclerView.this;
                if (recyclerView4.mAdapter != null) {
                    int[] iArr3 = recyclerView4.mReusableIntPair;
                    iArr3[0] = 0;
                    iArr3[1] = 0;
                    recyclerView4.scrollStep(consumeFlingInHorizontalStretch, consumeFlingInVerticalStretch, iArr3);
                    RecyclerView recyclerView5 = RecyclerView.this;
                    int[] iArr4 = recyclerView5.mReusableIntPair;
                    int i12 = iArr4[0];
                    int i13 = iArr4[1];
                    consumeFlingInHorizontalStretch -= i12;
                    consumeFlingInVerticalStretch -= i13;
                    SmoothScroller smoothScroller = recyclerView5.mLayout.mSmoothScroller;
                    if (smoothScroller != null && !smoothScroller.isPendingInitialRun() && smoothScroller.isRunning()) {
                        int itemCount = RecyclerView.this.mState.getItemCount();
                        if (itemCount == 0) {
                            smoothScroller.stop();
                        } else if (smoothScroller.getTargetPosition() >= itemCount) {
                            smoothScroller.setTargetPosition(itemCount - 1);
                            smoothScroller.onAnimation(i12, i13);
                        } else {
                            smoothScroller.onAnimation(i12, i13);
                        }
                    }
                    i7 = i13;
                    i2 = i12;
                } else {
                    i2 = 0;
                    i7 = 0;
                }
                int i14 = consumeFlingInHorizontalStretch;
                int i15 = consumeFlingInVerticalStretch;
                if (!RecyclerView.this.mItemDecorations.isEmpty()) {
                    RecyclerView.this.invalidate();
                }
                RecyclerView recyclerView6 = RecyclerView.this;
                int[] iArr5 = recyclerView6.mReusableIntPair;
                iArr5[0] = 0;
                iArr5[1] = 0;
                if (recyclerView6.seslDispatchNestedScroll(i2, i7, i14, i15, (int[]) null, 1, iArr5)) {
                    RecyclerView.this.mScrollOffset[0] = 0;
                    RecyclerView.this.mScrollOffset[1] = 0;
                }
                if (RecyclerView.this.mScrollOffset[0] < 0 || RecyclerView.this.mScrollOffset[1] < 0) {
                    RecyclerView.this.mScrollOffset[0] = 0;
                    RecyclerView.this.mScrollOffset[1] = 0;
                }
                RecyclerView recyclerView7 = RecyclerView.this;
                int[] iArr6 = recyclerView7.mReusableIntPair;
                int i16 = i14 - iArr6[0];
                int i17 = i15 - iArr6[1];
                if (!(i2 == 0 && i7 == 0)) {
                    recyclerView7.dispatchOnScrolled(i2, i7);
                }
                if (!RecyclerView.this.awakenScrollBars()) {
                    RecyclerView.this.invalidate();
                }
                if (overScroller.getCurrX() == overScroller.getFinalX()) {
                    z = true;
                } else {
                    z = false;
                }
                if (overScroller.getCurrY() == overScroller.getFinalY()) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (overScroller.isFinished() || ((z || i16 != 0) && (z3 || i17 != 0))) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                SmoothScroller smoothScroller2 = RecyclerView.this.mLayout.mSmoothScroller;
                if ((smoothScroller2 == null || !smoothScroller2.isPendingInitialRun()) && z7) {
                    if (RecyclerView.this.getOverScrollMode() != 2 && !RecyclerView.this.mEdgeEffectByDragging) {
                        int currVelocity = (int) overScroller.getCurrVelocity();
                        if (i16 < 0) {
                            i8 = -currVelocity;
                        } else if (i16 > 0) {
                            i8 = currVelocity;
                        } else {
                            i8 = 0;
                        }
                        if (i17 < 0) {
                            currVelocity = -currVelocity;
                        } else if (i17 <= 0) {
                            currVelocity = 0;
                        }
                        RecyclerView.this.absorbGlows(i8, currVelocity);
                    }
                    if (RecyclerView.ALLOW_THREAD_GAP_WORK) {
                        RecyclerView.this.mPrefetchRegistry.clearPrefetchPositions();
                    }
                } else {
                    postOnAnimation();
                    RecyclerView recyclerView8 = RecyclerView.this;
                    GapWorker gapWorker = recyclerView8.mGapWorker;
                    if (gapWorker != null) {
                        gapWorker.postFromTraversal(recyclerView8, i2, i7);
                    }
                }
                SeslViewReflector.setFrameContentVelocity(RecyclerView.this, Math.abs(overScroller.getCurrVelocity()));
            }
            SmoothScroller smoothScroller3 = RecyclerView.this.mLayout.mSmoothScroller;
            if (smoothScroller3 != null && smoothScroller3.isPendingInitialRun()) {
                smoothScroller3.onAnimation(0, 0);
            }
            this.mEatRunOnAnimationRequest = false;
            if (this.mReSchedulePostAnimationCallback) {
                internalPostOnAnimation();
                return;
            }
            RecyclerView.this.setScrollState(0);
            RecyclerView.this.stopNestedScroll(1);
        }

        public void smoothScrollBy(int i2, int i7, int i8, Interpolator interpolator) {
            int i10;
            if (i8 == Integer.MIN_VALUE) {
                i8 = computeScrollDuration(i2, i7, 0, 0);
            }
            int i11 = i8;
            if (interpolator == null) {
                interpolator = RecyclerView.sQuinticInterpolator;
            }
            if (i2 != 0) {
                i10 = 2;
            } else {
                i10 = 1;
            }
            RecyclerView.this.startNestedScroll(i10, 1);
            if (this.mInterpolator != interpolator) {
                this.mInterpolator = interpolator;
                this.mOverScroller = new OverScroller(RecyclerView.this.getContext(), interpolator);
            }
            this.mLastFlingY = 0;
            this.mLastFlingX = 0;
            RecyclerView.this.setScrollState(2);
            this.mOverScroller.startScroll(0, 0, i2, i7, i11);
            postOnAnimation();
        }

        public void stop() {
            RecyclerView.this.removeCallbacks(this);
            this.mOverScroller.abortAnimation();
            SeslViewReflector.setFrameContentVelocity(RecyclerView.this, 0.0f);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class ViewHolder {
        private static final List<Object> FULLUPDATE_PAYLOADS = Collections.EMPTY_LIST;
        public final View itemView;
        Adapter<? extends ViewHolder> mBindingAdapter;
        int mFlags;
        boolean mInChangeScrap = false;
        private int mIsRecyclableCount = 0;
        private boolean mIsViewHolderRecoilEffectEnabled = true;
        long mItemId = -1;
        int mItemViewType = -1;
        WeakReference<RecyclerView> mNestedRecyclerView;
        int mOldPosition = -1;
        RecyclerView mOwnerRecyclerView;
        List<Object> mPayloads = null;
        int mPendingAccessibilityState = -1;
        int mPosition = -1;
        int mPreLayoutPosition = -1;
        Recycler mScrapContainer = null;
        ViewHolder mShadowedHolder = null;
        ViewHolder mShadowingHolder = null;
        List<Object> mUnmodifiedPayloads = null;
        private int mWasImportantForAccessibilityBeforeHidden = 0;

        public ViewHolder(View view) {
            if (view != null) {
                this.itemView = view;
                return;
            }
            throw new IllegalArgumentException("itemView may not be null");
        }

        private void createPayloadsIfNeeded() {
            if (this.mPayloads == null) {
                ArrayList arrayList = new ArrayList();
                this.mPayloads = arrayList;
                this.mUnmodifiedPayloads = Collections.unmodifiableList(arrayList);
            }
        }

        public void addChangePayload(Object obj) {
            if (obj == null) {
                addFlags(1024);
            } else if ((1024 & this.mFlags) == 0) {
                createPayloadsIfNeeded();
                this.mPayloads.add(obj);
            }
        }

        public void addFlags(int i2) {
            this.mFlags = i2 | this.mFlags;
        }

        public void clearOldPosition() {
            this.mOldPosition = -1;
            this.mPreLayoutPosition = -1;
        }

        public void clearPayload() {
            List<Object> list = this.mPayloads;
            if (list != null) {
                list.clear();
            }
            this.mFlags &= -1025;
        }

        public void clearReturnedFromScrapFlag() {
            this.mFlags &= -33;
        }

        public void clearTmpDetachFlag() {
            this.mFlags &= -257;
        }

        public boolean doesTransientStatePreventRecycling() {
            if ((this.mFlags & 16) != 0 || !ViewCompat.hasTransientState(this.itemView)) {
                return false;
            }
            return true;
        }

        public void flagRemovedAndOffsetPosition(int i2, int i7, boolean z) {
            addFlags(8);
            offsetPosition(i7, z);
            this.mPosition = i2;
        }

        public final int getAbsoluteAdapterPosition() {
            RecyclerView recyclerView = this.mOwnerRecyclerView;
            if (recyclerView == null) {
                return -1;
            }
            return recyclerView.getAdapterPositionInRecyclerView(this);
        }

        @Deprecated
        public final int getAdapterPosition() {
            return getBindingAdapterPosition();
        }

        public final int getBindingAdapterPosition() {
            RecyclerView recyclerView;
            Adapter adapter;
            int adapterPositionInRecyclerView;
            if (this.mBindingAdapter == null || (recyclerView = this.mOwnerRecyclerView) == null || (adapter = recyclerView.getAdapter()) == null || (adapterPositionInRecyclerView = this.mOwnerRecyclerView.getAdapterPositionInRecyclerView(this)) == -1) {
                return -1;
            }
            return adapter.findRelativeAdapterPositionIn(this.mBindingAdapter, this, adapterPositionInRecyclerView);
        }

        public int getFlags() {
            return this.mFlags;
        }

        public final long getItemId() {
            return this.mItemId;
        }

        public final int getItemViewType() {
            return this.mItemViewType;
        }

        public final int getLayoutPosition() {
            int i2 = this.mPreLayoutPosition;
            if (i2 == -1) {
                return this.mPosition;
            }
            return i2;
        }

        public final int getOldPosition() {
            return this.mOldPosition;
        }

        public List<Object> getUnmodifiedPayloads() {
            if ((this.mFlags & 1024) != 0) {
                return FULLUPDATE_PAYLOADS;
            }
            List<Object> list = this.mPayloads;
            if (list == null || list.size() == 0) {
                return FULLUPDATE_PAYLOADS;
            }
            return this.mUnmodifiedPayloads;
        }

        public boolean hasAnyOfTheFlags(int i2) {
            if ((this.mFlags & i2) != 0) {
                return true;
            }
            return false;
        }

        public boolean isAdapterPositionUnknown() {
            if ((this.mFlags & 512) != 0 || isInvalid()) {
                return true;
            }
            return false;
        }

        public boolean isAttachedToTransitionOverlay() {
            if (this.itemView.getParent() == null || this.itemView.getParent() == this.mOwnerRecyclerView) {
                return false;
            }
            return true;
        }

        public boolean isBound() {
            if ((this.mFlags & 1) != 0) {
                return true;
            }
            return false;
        }

        public boolean isInvalid() {
            if ((this.mFlags & 4) != 0) {
                return true;
            }
            return false;
        }

        public final boolean isRecyclable() {
            if ((this.mFlags & 16) != 0 || ViewCompat.hasTransientState(this.itemView)) {
                return false;
            }
            return true;
        }

        public boolean isRemoved() {
            if ((this.mFlags & 8) != 0) {
                return true;
            }
            return false;
        }

        public boolean isScrap() {
            if (this.mScrapContainer != null) {
                return true;
            }
            return false;
        }

        public boolean isTmpDetached() {
            if ((this.mFlags & 256) != 0) {
                return true;
            }
            return false;
        }

        public boolean isUpdated() {
            if ((this.mFlags & 2) != 0) {
                return true;
            }
            return false;
        }

        public boolean needsUpdate() {
            if ((this.mFlags & 2) != 0) {
                return true;
            }
            return false;
        }

        public void offsetPosition(int i2, boolean z) {
            if (this.mOldPosition == -1) {
                this.mOldPosition = this.mPosition;
            }
            if (this.mPreLayoutPosition == -1) {
                this.mPreLayoutPosition = this.mPosition;
            }
            if (z) {
                this.mPreLayoutPosition += i2;
            }
            this.mPosition += i2;
            if (this.itemView.getLayoutParams() != null) {
                ((LayoutParams) this.itemView.getLayoutParams()).mInsetsDirty = true;
            }
        }

        public void onEnteredHiddenState(RecyclerView recyclerView) {
            int i2 = this.mPendingAccessibilityState;
            if (i2 != -1) {
                this.mWasImportantForAccessibilityBeforeHidden = i2;
            } else {
                this.mWasImportantForAccessibilityBeforeHidden = ViewCompat.getImportantForAccessibility(this.itemView);
            }
            recyclerView.setChildImportantForAccessibilityInternal(this, 4);
        }

        public void onLeftHiddenState(RecyclerView recyclerView) {
            recyclerView.setChildImportantForAccessibilityInternal(this, this.mWasImportantForAccessibilityBeforeHidden);
            this.mWasImportantForAccessibilityBeforeHidden = 0;
        }

        public void resetInternal() {
            if (!RecyclerView.sDebugAssertionsEnabled || !isTmpDetached()) {
                this.mFlags = 0;
                this.mPosition = -1;
                this.mOldPosition = -1;
                this.mItemId = -1;
                this.mPreLayoutPosition = -1;
                this.mIsRecyclableCount = 0;
                this.mShadowedHolder = null;
                this.mShadowingHolder = null;
                clearPayload();
                this.mWasImportantForAccessibilityBeforeHidden = 0;
                this.mPendingAccessibilityState = -1;
                RecyclerView.clearNestedRecyclerViewIfNotNested(this);
                return;
            }
            throw new IllegalStateException("Attempting to reset temp-detached ViewHolder: " + this + ". ViewHolders should be fully detached before resetting.");
        }

        public void saveOldPosition() {
            if (this.mOldPosition == -1) {
                this.mOldPosition = this.mPosition;
            }
        }

        public boolean seslIsViewHolderRecoilEffectEnabled() {
            return this.mIsViewHolderRecoilEffectEnabled;
        }

        public void seslSetViewHolderRecoilEffectEnabled(boolean z) {
            if (this.mIsViewHolderRecoilEffectEnabled != z) {
                this.mIsViewHolderRecoilEffectEnabled = z;
            }
        }

        public void setFlags(int i2, int i7) {
            this.mFlags = (i2 & i7) | (this.mFlags & (~i7));
        }

        public final void setIsRecyclable(boolean z) {
            int i2;
            int i7 = this.mIsRecyclableCount;
            if (z) {
                i2 = i7 - 1;
            } else {
                i2 = i7 + 1;
            }
            this.mIsRecyclableCount = i2;
            if (i2 < 0) {
                this.mIsRecyclableCount = 0;
                if (!RecyclerView.sDebugAssertionsEnabled) {
                    Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
                } else {
                    throw new RuntimeException("isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
                }
            } else if (!z && i2 == 1) {
                this.mFlags |= 16;
            } else if (z && i2 == 0) {
                this.mFlags &= -17;
            }
            if (RecyclerView.sVerboseLoggingEnabled) {
                Log.d("SeslRecyclerView", "setIsRecyclable val:" + z + NumericEnum.SEP + this);
            }
        }

        public void setScrapContainer(Recycler recycler, boolean z) {
            this.mScrapContainer = recycler;
            this.mInChangeScrap = z;
        }

        public boolean shouldBeKeptAsChild() {
            if ((this.mFlags & 16) != 0) {
                return true;
            }
            return false;
        }

        public boolean shouldIgnore() {
            if ((this.mFlags & 128) != 0) {
                return true;
            }
            return false;
        }

        public String toString() {
            String str;
            String str2;
            if (getClass().isAnonymousClass()) {
                str = "ViewHolder";
            } else {
                str = getClass().getSimpleName();
            }
            StringBuilder t = C0212a.t(str, "{");
            t.append(Integer.toHexString(hashCode()));
            t.append(" position=");
            t.append(this.mPosition);
            t.append(" id=");
            t.append(this.mItemId);
            t.append(", oldPos=");
            t.append(this.mOldPosition);
            t.append(", pLpos:");
            t.append(this.mPreLayoutPosition);
            StringBuilder sb2 = new StringBuilder(t.toString());
            if (isScrap()) {
                sb2.append(" scrap ");
                if (this.mInChangeScrap) {
                    str2 = "[changeScrap]";
                } else {
                    str2 = "[attachedScrap]";
                }
                sb2.append(str2);
            }
            if (isInvalid()) {
                sb2.append(" invalid");
            }
            if (!isBound()) {
                sb2.append(" unbound");
            }
            if (needsUpdate()) {
                sb2.append(" update");
            }
            if (isRemoved()) {
                sb2.append(" removed");
            }
            if (shouldIgnore()) {
                sb2.append(" ignored");
            }
            if (isTmpDetached()) {
                sb2.append(" tmpDetached");
            }
            if (!isRecyclable()) {
                sb2.append(" not recyclable(" + this.mIsRecyclableCount + ")");
            }
            if (isAdapterPositionUnknown()) {
                sb2.append(" undefined adapter position");
            }
            if (this.itemView.getParent() == null) {
                sb2.append(" no parent");
            }
            sb2.append("}");
            return sb2.toString();
        }

        public void unScrap() {
            this.mScrapContainer.unscrapView(this);
        }

        public boolean wasReturnedFromScrap() {
            if ((this.mFlags & 32) != 0) {
                return true;
            }
            return false;
        }
    }

    static {
        Class cls = Integer.TYPE;
        LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE = new Class[]{Context.class, AttributeSet.class, cls, cls};
    }

    public RecyclerView(Context context) {
        this(context, (AttributeSet) null);
    }

    private void addAnimatingView(ViewHolder viewHolder) {
        boolean z;
        View view = viewHolder.itemView;
        if (view.getParent() == this) {
            z = true;
        } else {
            z = false;
        }
        this.mRecycler.unscrapView(getChildViewHolder(view));
        if (viewHolder.isTmpDetached()) {
            this.mChildHelper.attachViewToParent(view, -1, view.getLayoutParams(), true);
        } else if (!z) {
            this.mChildHelper.addView(view, true);
        } else {
            this.mChildHelper.hide(view);
        }
    }

    /* access modifiers changed from: private */
    public void adjustNestedScrollRange() {
        int i2;
        getLocationInWindow(this.mWindowOffsets);
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null || !layoutManager.canScrollHorizontally()) {
            i2 = this.mWindowOffsets[1];
        } else {
            i2 = this.mWindowOffsets[0];
        }
        int i7 = this.mNestedScrollRange;
        int i8 = this.mInitialTopOffsetOfScreen;
        int i10 = i7 - (i8 - i2);
        this.mRemainNestedScrollRange = i10;
        if (i8 - i2 < 0) {
            this.mNestedScrollRange = i10;
            this.mInitialTopOffsetOfScreen = i2;
        }
    }

    /* access modifiers changed from: private */
    public void adjustNestedScrollRangeBy(int i2) {
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

    private void animateChange(ViewHolder viewHolder, ViewHolder viewHolder2, ItemAnimator.ItemHolderInfo itemHolderInfo, ItemAnimator.ItemHolderInfo itemHolderInfo2, boolean z, boolean z3) {
        viewHolder.setIsRecyclable(false);
        if (z) {
            addAnimatingView(viewHolder);
        }
        if (viewHolder != viewHolder2) {
            if (z3) {
                addAnimatingView(viewHolder2);
            }
            viewHolder.mShadowedHolder = viewHolder2;
            addAnimatingView(viewHolder);
            this.mRecycler.unscrapView(viewHolder);
            viewHolder2.setIsRecyclable(false);
            viewHolder2.mShadowingHolder = viewHolder;
        }
        if (this.mItemAnimator.animateChange(viewHolder, viewHolder2, itemHolderInfo, itemHolderInfo2)) {
            postAnimationRunner();
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

    /* access modifiers changed from: private */
    public boolean canScrollDown() {
        boolean z;
        boolean z3;
        boolean z7;
        boolean z9;
        int i2;
        int childCount = getChildCount();
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            z3 = layoutManager.canScrollHorizontally();
            if (this.mLayout.getLayoutDirection() == 1) {
                z = true;
            } else {
                z = false;
            }
        } else {
            z3 = false;
            z = false;
        }
        LayoutManager layoutManager2 = this.mLayout;
        if (layoutManager2 instanceof LinearLayoutManager) {
            z7 = ((LinearLayoutManager) layoutManager2).getReverseLayout();
        } else {
            z7 = false;
        }
        if (this.mAdapter == null) {
            Log.e("SeslRecyclerView", "No adapter attached; skipping canScrollDown");
            return false;
        }
        if (!z7 ? findFirstChildPosition() + childCount >= this.mAdapter.getItemCount() : findFirstChildPosition() <= 0) {
            z9 = false;
        } else {
            z9 = true;
        }
        if (z9 || childCount <= 0) {
            return z9;
        }
        if (z7) {
            i2 = 0;
        } else {
            i2 = childCount - 1;
        }
        getDecoratedBoundsWithMargins(getChildAt(i2), this.mChildBound);
        if (z3) {
            if (z) {
                if (this.mChildBound.left < this.mListPadding.left) {
                    return true;
                }
                return false;
            } else if (this.mChildBound.right > getRight() - this.mListPadding.right || this.mChildBound.right > getWidth() - this.mListPadding.right) {
                return true;
            } else {
                return false;
            }
        } else if (this.mChildBound.bottom > getBottom() - this.mListPadding.bottom || this.mChildBound.bottom > getHeight() - this.mListPadding.bottom) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public boolean canScrollUp() {
        boolean z;
        boolean z3;
        boolean z7;
        boolean z9;
        int i2;
        int childCount = getChildCount();
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            z3 = layoutManager.canScrollHorizontally();
            if (this.mLayout.getLayoutDirection() == 1) {
                z = true;
            } else {
                z = false;
            }
        } else {
            z3 = false;
            z = false;
        }
        LayoutManager layoutManager2 = this.mLayout;
        if (layoutManager2 instanceof LinearLayoutManager) {
            z7 = ((LinearLayoutManager) layoutManager2).getReverseLayout();
        } else {
            z7 = false;
        }
        if (!z7 ? findFirstChildPosition() <= 0 : findFirstChildPosition() + childCount >= this.mAdapter.getItemCount()) {
            z9 = false;
        } else {
            z9 = true;
        }
        if (z9 || childCount <= 0) {
            return z9;
        }
        if (z7) {
            i2 = childCount - 1;
        } else {
            i2 = 0;
        }
        getDecoratedBoundsWithMargins(getChildAt(i2), this.mChildBound);
        if (z3) {
            if (z) {
                if (this.mChildBound.right > getRight() - this.mListPadding.right || this.mChildBound.right > getWidth() - this.mListPadding.right) {
                    return true;
                }
                return false;
            } else if (this.mChildBound.left < this.mListPadding.left) {
                return true;
            } else {
                return false;
            }
        } else if (this.mChildBound.top < this.mListPadding.top) {
            return true;
        } else {
            return false;
        }
    }

    private void cancelScroll() {
        resetScroll();
        setScrollState(0);
    }

    public static void clearNestedRecyclerViewIfNotNested(ViewHolder viewHolder) {
        WeakReference<RecyclerView> weakReference = viewHolder.mNestedRecyclerView;
        if (weakReference != null) {
            View view = weakReference.get();
            while (view != null) {
                if (view != viewHolder.itemView) {
                    ViewParent parent = view.getParent();
                    if (parent instanceof View) {
                        view = (View) parent;
                    } else {
                        view = null;
                    }
                } else {
                    return;
                }
            }
            viewHolder.mNestedRecyclerView = null;
        }
    }

    private int consumeFlingInStretch(int i2, EdgeEffect edgeEffect, EdgeEffect edgeEffect2, int i7) {
        if (i2 > 0 && edgeEffect != null && EdgeEffectCompat.getDistance(edgeEffect) != 0.0f) {
            int round = Math.round(EdgeEffectCompat.onPullDistance(edgeEffect, (((float) (-i2)) * 4.0f) / ((float) i7), 0.5f) * (((float) (-i7)) / 4.0f));
            if (round != i2) {
                edgeEffect.finish();
            }
            return i2 - round;
        } else if (i2 >= 0 || edgeEffect2 == null || EdgeEffectCompat.getDistance(edgeEffect2) == 0.0f) {
            return i2;
        } else {
            float f = (float) i7;
            int round2 = Math.round(EdgeEffectCompat.onPullDistance(edgeEffect2, (((float) i2) * 4.0f) / f, 0.5f) * (f / 4.0f));
            if (round2 != i2) {
                edgeEffect2.finish();
            }
            return i2 - round2;
        }
    }

    /* access modifiers changed from: private */
    public boolean contentFits() {
        int childCount = getChildCount();
        if (childCount == 0) {
            return true;
        }
        if (childCount == this.mAdapter.getItemCount() && getChildAt(0).getTop() >= this.mListPadding.top && getChildAt(childCount - 1).getBottom() <= getHeight() - this.mListPadding.bottom) {
            return true;
        }
        return false;
    }

    private void createLayoutManager(Context context, String str, AttributeSet attributeSet, int i2, int i7) {
        ClassLoader classLoader;
        Object[] objArr;
        Constructor<? extends U> constructor;
        if (str != null) {
            String trim = str.trim();
            if (!trim.isEmpty()) {
                String fullClassName = getFullClassName(context, trim);
                try {
                    if (isInEditMode()) {
                        classLoader = getClass().getClassLoader();
                    } else {
                        classLoader = context.getClassLoader();
                    }
                    Class<? extends U> asSubclass = Class.forName(fullClassName, false, classLoader).asSubclass(LayoutManager.class);
                    try {
                        constructor = asSubclass.getConstructor(LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE);
                        objArr = new Object[]{context, attributeSet, Integer.valueOf(i2), Integer.valueOf(i7)};
                    } catch (NoSuchMethodException e) {
                        objArr = null;
                        constructor = asSubclass.getConstructor((Class[]) null);
                    }
                    constructor.setAccessible(true);
                    setLayoutManager((LayoutManager) constructor.newInstance(objArr));
                } catch (NoSuchMethodException e7) {
                    e7.initCause(e);
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + fullClassName, e7);
                } catch (ClassNotFoundException e8) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Unable to find LayoutManager " + fullClassName, e8);
                } catch (InvocationTargetException e9) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + fullClassName, e9);
                } catch (InstantiationException e10) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + fullClassName, e10);
                } catch (IllegalAccessException e11) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Cannot access non-public constructor " + fullClassName, e11);
                } catch (ClassCastException e12) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Class is not a LayoutManager " + fullClassName, e12);
                }
            }
        }
    }

    private boolean didChildRangeChange(int i2, int i7) {
        findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
        int[] iArr = this.mMinMaxLayoutPositions;
        if (iArr[0] == i2 && iArr[1] == i7) {
            return false;
        }
        return true;
    }

    private void dispatchContentChangedIfNecessary() {
        int i2 = this.mEatenAccessibilityChangeFlags;
        this.mEatenAccessibilityChangeFlags = 0;
        if (i2 != 0 && isAccessibilityEnabled()) {
            AccessibilityEvent obtain = AccessibilityEvent.obtain();
            obtain.setEventType(2048);
            AccessibilityEventCompat.setContentChangeTypes(obtain, i2);
            sendAccessibilityEventUnchecked(obtain);
        }
    }

    private void dispatchLayoutStep1() {
        boolean z = true;
        this.mState.assertLayoutStep(1);
        fillRemainingScrollValues(this.mState);
        this.mState.mIsMeasuring = false;
        startInterceptRequestLayout();
        this.mViewInfoStore.clear();
        onEnterLayoutOrScroll();
        processAdapterUpdatesAndSetAnimationFlags();
        saveFocusInfo();
        State state = this.mState;
        if (!state.mRunSimpleAnimations || !this.mItemsChanged) {
            z = false;
        }
        state.mTrackOldChangeHolders = z;
        this.mItemsChanged = false;
        this.mItemsAddedOrRemoved = false;
        state.mInPreLayout = state.mRunPredictiveAnimations;
        state.mItemCount = this.mAdapter.getItemCount();
        findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
        if (this.mState.mRunSimpleAnimations) {
            int childCount = this.mChildHelper.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i2));
                if (!childViewHolderInt.shouldIgnore() && (!childViewHolderInt.isInvalid() || this.mAdapter.hasStableIds())) {
                    this.mViewInfoStore.addToPreLayout(childViewHolderInt, this.mItemAnimator.recordPreLayoutInformation(this.mState, childViewHolderInt, ItemAnimator.buildAdapterChangeFlagsForAnimations(childViewHolderInt), childViewHolderInt.getUnmodifiedPayloads()));
                    if (this.mState.mTrackOldChangeHolders && childViewHolderInt.isUpdated() && !childViewHolderInt.isRemoved() && !childViewHolderInt.shouldIgnore() && !childViewHolderInt.isInvalid()) {
                        this.mViewInfoStore.addToOldChangeHolders(getChangedHolderKey(childViewHolderInt), childViewHolderInt);
                    }
                }
            }
        }
        if (this.mState.mRunPredictiveAnimations) {
            saveOldPositions();
            State state2 = this.mState;
            boolean z3 = state2.mStructureChanged;
            state2.mStructureChanged = false;
            this.mLayout.onLayoutChildren(this.mRecycler, state2);
            this.mState.mStructureChanged = z3;
            for (int i7 = 0; i7 < this.mChildHelper.getChildCount(); i7++) {
                ViewHolder childViewHolderInt2 = getChildViewHolderInt(this.mChildHelper.getChildAt(i7));
                if (!childViewHolderInt2.shouldIgnore() && !this.mViewInfoStore.isInPreLayout(childViewHolderInt2)) {
                    int buildAdapterChangeFlagsForAnimations = ItemAnimator.buildAdapterChangeFlagsForAnimations(childViewHolderInt2);
                    boolean hasAnyOfTheFlags = childViewHolderInt2.hasAnyOfTheFlags(SerializeOptions.SORT);
                    if (!hasAnyOfTheFlags) {
                        buildAdapterChangeFlagsForAnimations |= 4096;
                    }
                    ItemAnimator.ItemHolderInfo recordPreLayoutInformation = this.mItemAnimator.recordPreLayoutInformation(this.mState, childViewHolderInt2, buildAdapterChangeFlagsForAnimations, childViewHolderInt2.getUnmodifiedPayloads());
                    if (hasAnyOfTheFlags) {
                        recordAnimationInfoIfBouncedHiddenView(childViewHolderInt2, recordPreLayoutInformation);
                    } else {
                        this.mViewInfoStore.addToAppearedInPreLayoutHolders(childViewHolderInt2, recordPreLayoutInformation);
                    }
                }
            }
            clearOldPositions();
        } else {
            clearOldPositions();
        }
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
        this.mState.mLayoutStep = 2;
    }

    private void dispatchLayoutStep2() {
        boolean z;
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        this.mState.assertLayoutStep(6);
        this.mAdapterHelper.consumeUpdatesInOnePass();
        this.mState.mItemCount = this.mAdapter.getItemCount();
        this.mState.mDeletedInvisibleItemCountSincePreviousLayout = 0;
        if (this.mPendingSavedState != null && this.mAdapter.canRestoreState()) {
            Parcelable parcelable = this.mPendingSavedState.mLayoutState;
            if (parcelable != null) {
                this.mLayout.onRestoreInstanceState(parcelable);
            }
            this.mPendingSavedState = null;
        }
        State state = this.mState;
        state.mInPreLayout = false;
        this.mLayout.onLayoutChildren(this.mRecycler, state);
        State state2 = this.mState;
        state2.mStructureChanged = false;
        if (!state2.mRunSimpleAnimations || this.mItemAnimator == null) {
            z = false;
        } else {
            z = true;
        }
        state2.mRunSimpleAnimations = z;
        state2.mLayoutStep = 4;
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
    }

    private void dispatchLayoutStep3() {
        RecyclerView recyclerView;
        View childAt;
        RecyclerView recyclerView2;
        this.mState.assertLayoutStep(4);
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        State state = this.mState;
        state.mLayoutStep = 1;
        if (state.mRunSimpleAnimations) {
            int childCount = this.mChildHelper.getChildCount() - 1;
            while (childCount >= 0) {
                ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(childCount));
                if (!childViewHolderInt.shouldIgnore()) {
                    long changedHolderKey = this.getChangedHolderKey(childViewHolderInt);
                    ItemAnimator.ItemHolderInfo recordPostLayoutInformation = this.mItemAnimator.recordPostLayoutInformation(this.mState, childViewHolderInt);
                    ViewHolder fromOldChangeHolders = this.mViewInfoStore.getFromOldChangeHolders(changedHolderKey);
                    if (fromOldChangeHolders == null || fromOldChangeHolders.shouldIgnore()) {
                        recyclerView2 = this;
                        recyclerView2.mViewInfoStore.addToPostLayout(childViewHolderInt, recordPostLayoutInformation);
                        childCount--;
                        this = recyclerView2;
                    } else {
                        boolean isDisappearing = this.mViewInfoStore.isDisappearing(fromOldChangeHolders);
                        boolean isDisappearing2 = this.mViewInfoStore.isDisappearing(childViewHolderInt);
                        if (!isDisappearing || fromOldChangeHolders != childViewHolderInt) {
                            ItemAnimator.ItemHolderInfo popFromPreLayout = this.mViewInfoStore.popFromPreLayout(fromOldChangeHolders);
                            this.mViewInfoStore.addToPostLayout(childViewHolderInt, recordPostLayoutInformation);
                            ItemAnimator.ItemHolderInfo popFromPostLayout = this.mViewInfoStore.popFromPostLayout(childViewHolderInt);
                            if (popFromPreLayout == null) {
                                this.handleMissingPreInfoForChangeError(changedHolderKey, childViewHolderInt, fromOldChangeHolders);
                            } else {
                                ItemAnimator.ItemHolderInfo itemHolderInfo = popFromPreLayout;
                                ItemAnimator.ItemHolderInfo itemHolderInfo2 = popFromPostLayout;
                                ViewHolder viewHolder = fromOldChangeHolders;
                                ItemAnimator.ItemHolderInfo itemHolderInfo3 = itemHolderInfo;
                                recyclerView2 = this;
                                recyclerView2.animateChange(viewHolder, childViewHolderInt, itemHolderInfo3, itemHolderInfo2, isDisappearing, isDisappearing2);
                                childCount--;
                                this = recyclerView2;
                            }
                        } else {
                            this.mViewInfoStore.addToPostLayout(childViewHolderInt, recordPostLayoutInformation);
                        }
                    }
                }
                recyclerView2 = this;
                childCount--;
                this = recyclerView2;
            }
            recyclerView = this;
            recyclerView.mViewInfoStore.process(recyclerView.mViewInfoProcessCallback);
        } else {
            recyclerView = this;
        }
        recyclerView.mLastBlackTop = recyclerView.mBlackTop;
        int i2 = -1;
        recyclerView.mBlackTop = -1;
        if (recyclerView.mDrawRect && !recyclerView.canScrollVertically(-1) && !recyclerView.canScrollVertically(1)) {
            int itemCount = recyclerView.mAdapter.getItemCount() - 1;
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.mLayout;
            boolean z = linearLayoutManager.mReverseLayout;
            if (z && linearLayoutManager.mStackFromEnd) {
                recyclerView.mDrawReverse = true;
                i2 = 0;
            } else if (z || linearLayoutManager.mStackFromEnd) {
                recyclerView.mDrawRect = false;
            } else {
                i2 = itemCount;
            }
            if (i2 >= 0 && i2 <= recyclerView.findLastVisibleItemPosition() && (childAt = recyclerView.mChildHelper.getChildAt(i2)) != null) {
                recyclerView.mBlackTop = childAt.getBottom();
            }
        }
        recyclerView.mLayout.removeAndRecycleScrapInt(recyclerView.mRecycler);
        State state2 = recyclerView.mState;
        state2.mPreviousLayoutItemCount = state2.mItemCount;
        recyclerView.mDataSetHasChangedAfterLayout = false;
        recyclerView.mDispatchItemsChangedEvent = false;
        state2.mRunSimpleAnimations = false;
        state2.mRunPredictiveAnimations = false;
        recyclerView.mLayout.mRequestedSimpleAnimations = false;
        ArrayList<ViewHolder> arrayList = recyclerView.mRecycler.mChangedScrap;
        if (arrayList != null) {
            arrayList.clear();
        }
        LayoutManager layoutManager = recyclerView.mLayout;
        if (layoutManager.mPrefetchMaxObservedInInitialPrefetch) {
            layoutManager.mPrefetchMaxCountObserved = 0;
            layoutManager.mPrefetchMaxObservedInInitialPrefetch = false;
            recyclerView.mRecycler.updateViewCacheSize();
        }
        recyclerView.mLayout.onLayoutCompleted(recyclerView.mState);
        recyclerView.onExitLayoutOrScroll();
        recyclerView.stopInterceptRequestLayout(false);
        recyclerView.mViewInfoStore.clear();
        int[] iArr = recyclerView.mMinMaxLayoutPositions;
        if (recyclerView.didChildRangeChange(iArr[0], iArr[1])) {
            recyclerView.dispatchOnScrolled(0, 0);
        }
        recyclerView.recoverFocusFromState();
        recyclerView.resetFocusInfo();
    }

    private boolean dispatchToOnItemTouchListeners(MotionEvent motionEvent) {
        OnItemTouchListener onItemTouchListener = this.mInterceptingOnItemTouchListener;
        if (onItemTouchListener != null) {
            onItemTouchListener.onTouchEvent(this, motionEvent);
            int action = motionEvent.getAction();
            if (action == 3 || action == 1) {
                this.mInterceptingOnItemTouchListener = null;
            }
            return true;
        } else if (motionEvent.getAction() == 0) {
            return false;
        } else {
            return findInterceptingOnItemTouchListener(motionEvent);
        }
    }

    private void ensureGoToTopController(boolean z) {
        if (z) {
            SeslGoToTopController seslGoToTopController = this.mGoToTopController;
            if (seslGoToTopController == null) {
                this.mGoToTopController = SeslGoToTopControllerFactory.createController(SeslGoToTopControllerFactory.ControllerType.RECYCLERVIEW, updateGoToTopConfig(), this.mGoToTopHost, "SeslRecyclerView");
            } else {
                seslGoToTopController.updateConfig(updateGoToTopConfig());
            }
        } else {
            SeslGoToTopController seslGoToTopController2 = this.mGoToTopController;
            if (seslGoToTopController2 != null) {
                seslGoToTopController2.release();
                this.mGoToTopController = null;
            }
        }
    }

    private int findFirstChildPosition() {
        int i2;
        int i7;
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager instanceof LinearLayoutManager) {
            i2 = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            if (layoutManager.getLayoutDirection() == 1) {
                i7 = ((StaggeredGridLayoutManager) this.mLayout).getSpanCount() - 1;
            } else {
                i7 = 0;
            }
            i2 = ((StaggeredGridLayoutManager) this.mLayout).findFirstVisibleItemPositions((int[]) null)[i7];
        } else {
            i2 = 0;
        }
        if (i2 == -1) {
            return 0;
        }
        return i2;
    }

    private boolean findInterceptingOnItemTouchListener(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        int size = this.mOnItemTouchListeners.size();
        int i2 = 0;
        while (i2 < size) {
            OnItemTouchListener onItemTouchListener = this.mOnItemTouchListeners.get(i2);
            if (!onItemTouchListener.onInterceptTouchEvent(this, motionEvent) || action == 3) {
                i2++;
            } else {
                this.mInterceptingOnItemTouchListener = onItemTouchListener;
                return true;
            }
        }
        return false;
    }

    private void findMinMaxChildLayoutPositions(int[] iArr) {
        int childCount = this.mChildHelper.getChildCount();
        if (childCount == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i2 = Integer.MAX_VALUE;
        int i7 = Integer.MIN_VALUE;
        for (int i8 = 0; i8 < childCount; i8++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i8));
            if (!childViewHolderInt.shouldIgnore()) {
                int layoutPosition = childViewHolderInt.getLayoutPosition();
                if (layoutPosition < i2) {
                    i2 = layoutPosition;
                }
                if (layoutPosition > i7) {
                    i7 = layoutPosition;
                }
            }
        }
        iArr[0] = i2;
        iArr[1] = i7;
    }

    public static RecyclerView findNestedRecyclerView(View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        if (view instanceof RecyclerView) {
            return (RecyclerView) view;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            RecyclerView findNestedRecyclerView = findNestedRecyclerView(viewGroup.getChildAt(i2));
            if (findNestedRecyclerView != null) {
                return findNestedRecyclerView;
            }
        }
        return null;
    }

    private View findNextViewToFocus() {
        ViewHolder findViewHolderForAdapterPosition;
        State state = this.mState;
        int i2 = state.mFocusedItemPosition;
        if (i2 == -1) {
            i2 = 0;
        }
        int itemCount = state.getItemCount();
        int i7 = i2;
        while (i7 < itemCount) {
            ViewHolder findViewHolderForAdapterPosition2 = findViewHolderForAdapterPosition(i7);
            if (findViewHolderForAdapterPosition2 == null) {
                break;
            } else if (findViewHolderForAdapterPosition2.itemView.hasFocusable()) {
                return findViewHolderForAdapterPosition2.itemView;
            } else {
                i7++;
            }
        }
        int min = Math.min(itemCount, i2);
        while (true) {
            min--;
            if (min < 0 || (findViewHolderForAdapterPosition = findViewHolderForAdapterPosition(min)) == null) {
                return null;
            }
            if (findViewHolderForAdapterPosition.itemView.hasFocusable()) {
                return findViewHolderForAdapterPosition.itemView;
            }
        }
    }

    private boolean findSuperClass(ViewParent viewParent, String str) {
        for (Class cls = viewParent.getClass(); cls != null; cls = cls.getSuperclass()) {
            if (cls.getSimpleName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static ViewHolder getChildViewHolderInt(View view) {
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).mViewHolder;
    }

    public static void getDecoratedBoundsWithMarginsInt(View view, Rect rect) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        Rect rect2 = layoutParams.mDecorInsets;
        rect.set((view.getLeft() - rect2.left) - layoutParams.leftMargin, (view.getTop() - rect2.top) - layoutParams.topMargin, view.getRight() + rect2.right + layoutParams.rightMargin, view.getBottom() + rect2.bottom + layoutParams.bottomMargin);
    }

    private int getDeepestFocusedViewWithId(View view) {
        int id = view.getId();
        while (!view.isFocused() && (view instanceof ViewGroup) && view.hasFocus()) {
            view = ((ViewGroup) view).getFocusedChild();
            if (view.getId() != -1) {
                id = view.getId();
            }
        }
        return id;
    }

    private String getFullClassName(Context context, String str) {
        if (str.charAt(0) == '.') {
            return context.getPackageName() + str;
        } else if (str.contains(".")) {
            return str;
        } else {
            return RecyclerView.class.getPackage().getName() + '.' + str;
        }
    }

    private int getPendingAnimFlag() {
        ItemAnimator itemAnimator = getItemAnimator();
        if (itemAnimator instanceof DefaultItemAnimator) {
            return ((DefaultItemAnimator) itemAnimator).getPendingAnimFlag();
        }
        return 0;
    }

    /* access modifiers changed from: private */
    public int getRecyclerViewScreenLocationY() {
        getLocationOnScreen(this.mRecyclerViewOffsets);
        return this.mRecyclerViewOffsets[1];
    }

    private int getRotatedArrowPointerIcon(boolean z, boolean z3) {
        ScrollArrowDirection scrollArrowDirection;
        boolean z7;
        int i2;
        if (z) {
            if (z3) {
                scrollArrowDirection = ScrollArrowDirection.RIGHT;
            } else {
                scrollArrowDirection = ScrollArrowDirection.DOWN;
            }
        } else if (z3) {
            scrollArrowDirection = ScrollArrowDirection.LEFT;
        } else {
            scrollArrowDirection = ScrollArrowDirection.UP;
        }
        float f = this.mPointerIconRotation;
        if (f == 0.0f) {
            return this.mHoverScrollArrows[scrollArrowDirection.ordinal()];
        }
        int i7 = 1;
        if (f < 0.0f) {
            z7 = true;
        } else {
            z7 = false;
        }
        if (z7) {
            i2 = -45;
        } else {
            i2 = 45;
        }
        int i8 = (int) ((f + ((float) i2)) / 90.0f);
        if (z7) {
            i7 = -1;
        }
        int ordinal = ((scrollArrowDirection.ordinal() * i7) + i8) % 4;
        if (ordinal == 0) {
            return this.mHoverScrollArrows[ordinal];
        }
        int[] iArr = this.mHoverScrollArrows;
        if (z7) {
            ordinal += 4;
        }
        return iArr[ordinal];
    }

    private NestedScrollingChildHelper getScrollingChildHelper() {
        if (this.mScrollingChildHelper == null) {
            this.mScrollingChildHelper = new NestedScrollingChildHelper(this);
        }
        return this.mScrollingChildHelper;
    }

    private float getSplineFlingDistance(int i2) {
        double log = Math.log((double) ((((float) Math.abs(i2)) * 0.35f) / (this.mPhysicalCoef * 0.015f)));
        float f = DECELERATION_RATE;
        return (float) (Math.exp((((double) f) / (((double) f) - 1.0d)) * log) * ((double) (this.mPhysicalCoef * 0.015f)));
    }

    private int getTopUnderNestedScrollView(ViewGroup viewGroup) {
        int i2 = 0;
        while (!(viewGroup instanceof NestedScrollView)) {
            i2 += viewGroup.getTop();
            if (!(viewGroup.getParent() instanceof ViewGroup)) {
                break;
            }
            viewGroup = (ViewGroup) viewGroup.getParent();
        }
        return i2;
    }

    private void handleMissingPreInfoForChangeError(long j2, ViewHolder viewHolder, ViewHolder viewHolder2) {
        int childCount = this.mChildHelper.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i2));
            if (childViewHolderInt != viewHolder && getChangedHolderKey(childViewHolderInt) == j2) {
                Adapter adapter = this.mAdapter;
                if (adapter == null || !adapter.hasStableIds()) {
                    StringBuilder sb2 = new StringBuilder("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:");
                    sb2.append(childViewHolderInt);
                    sb2.append(" \n View Holder 2:");
                    sb2.append(viewHolder);
                    throw new IllegalStateException(C0086a.k(this, sb2));
                }
                StringBuilder sb3 = new StringBuilder("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:");
                sb3.append(childViewHolderInt);
                sb3.append(" \n View Holder 2:");
                sb3.append(viewHolder);
                throw new IllegalStateException(C0086a.k(this, sb3));
            }
        }
        Log.e("SeslRecyclerView", "Problem while matching changed view holders with the newones. The pre-layout information for the change holder " + viewHolder2 + " cannot be found but it is necessary for " + viewHolder + exceptionLabel());
    }

    private boolean hasUpdatedView() {
        int childCount = this.mChildHelper.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i2));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && childViewHolderInt.isUpdated()) {
                return true;
            }
        }
        return false;
    }

    private void initAutofill() {
        if (ViewCompat.getImportantForAutofill(this) == 0) {
            ViewCompat.setImportantForAutofill(this, 8);
        }
    }

    private void initChildrenHelper() {
        this.mChildHelper = new ChildHelper(new ChildHelper.Callback() {
            public void addView(View view, int i2) {
                RecyclerView.this.addView(view, i2);
                RecyclerView.this.dispatchChildAttached(view);
            }

            public void attachViewToParent(View view, int i2, ViewGroup.LayoutParams layoutParams) {
                ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
                if (childViewHolderInt != null) {
                    if (childViewHolderInt.isTmpDetached() || childViewHolderInt.shouldIgnore()) {
                        if (RecyclerView.sVerboseLoggingEnabled) {
                            Log.d("SeslRecyclerView", "reAttach " + childViewHolderInt);
                        }
                        childViewHolderInt.clearTmpDetachFlag();
                    } else {
                        StringBuilder sb2 = new StringBuilder("Called attach on a child which is not detached: ");
                        sb2.append(childViewHolderInt);
                        throw new IllegalArgumentException(C0086a.k(RecyclerView.this, sb2));
                    }
                } else if (RecyclerView.sDebugAssertionsEnabled) {
                    StringBuilder sb3 = new StringBuilder("No ViewHolder found for child: ");
                    sb3.append(view);
                    sb3.append(", index: ");
                    sb3.append(i2);
                    throw new IllegalArgumentException(C0086a.k(RecyclerView.this, sb3));
                }
                RecyclerView.this.attachViewToParent(view, i2, layoutParams);
            }

            public void detachViewFromParent(int i2) {
                View childAt = getChildAt(i2);
                if (childAt != null) {
                    ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(childAt);
                    if (childViewHolderInt != null) {
                        if (!childViewHolderInt.isTmpDetached() || childViewHolderInt.shouldIgnore()) {
                            if (RecyclerView.sVerboseLoggingEnabled) {
                                Log.d("SeslRecyclerView", "tmpDetach " + childViewHolderInt);
                            }
                            childViewHolderInt.addFlags(256);
                        } else {
                            StringBuilder sb2 = new StringBuilder("called detach on an already detached child ");
                            sb2.append(childViewHolderInt);
                            throw new IllegalArgumentException(C0086a.k(RecyclerView.this, sb2));
                        }
                    }
                } else if (RecyclerView.sDebugAssertionsEnabled) {
                    StringBuilder sb3 = new StringBuilder("No view at offset ");
                    sb3.append(i2);
                    throw new IllegalArgumentException(C0086a.k(RecyclerView.this, sb3));
                }
                RecyclerView.this.detachViewFromParent(i2);
            }

            public View getChildAt(int i2) {
                return RecyclerView.this.getChildAt(i2);
            }

            public int getChildCount() {
                return RecyclerView.this.getChildCount();
            }

            public ViewHolder getChildViewHolder(View view) {
                return RecyclerView.getChildViewHolderInt(view);
            }

            public int indexOfChild(View view) {
                return RecyclerView.this.indexOfChild(view);
            }

            public void onEnteredHiddenState(View view) {
                ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
                if (childViewHolderInt != null) {
                    childViewHolderInt.onEnteredHiddenState(RecyclerView.this);
                }
            }

            public void onLeftHiddenState(View view) {
                ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
                if (childViewHolderInt != null) {
                    childViewHolderInt.onLeftHiddenState(RecyclerView.this);
                }
            }

            public void removeAllViews() {
                int childCount = getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    View childAt = getChildAt(i2);
                    RecyclerView.this.dispatchChildDetached(childAt);
                    childAt.clearAnimation();
                }
                RecyclerView.this.removeAllViews();
            }

            public void removeViewAt(int i2) {
                View childAt = RecyclerView.this.getChildAt(i2);
                if (childAt != null) {
                    RecyclerView.this.dispatchChildDetached(childAt);
                    childAt.clearAnimation();
                }
                RecyclerView.this.removeViewAt(i2);
            }
        });
    }

    private boolean isLockScreenMode() {
        if (!((KeyguardManager) this.mContext.getSystemService("keyguard")).inKeyguardRestrictedInputMode()) {
            return false;
        }
        return true;
    }

    private boolean isPreferredNextFocus(View view, View view2, int i2) {
        int i7;
        int i8;
        if (view2 == null || view2 == this || view2 == view || findContainingItemView(view2) == null) {
            return false;
        }
        if (view == null || findContainingItemView(view) == null) {
            return true;
        }
        this.mTempRect.set(0, 0, view.getWidth(), view.getHeight());
        this.mTempRect2.set(0, 0, view2.getWidth(), view2.getHeight());
        offsetDescendantRectToMyCoords(view, this.mTempRect);
        offsetDescendantRectToMyCoords(view2, this.mTempRect2);
        char c5 = 65535;
        if (this.mLayout.getLayoutDirection() == 1) {
            i7 = -1;
        } else {
            i7 = 1;
        }
        Rect rect = this.mTempRect;
        int i10 = rect.left;
        Rect rect2 = this.mTempRect2;
        int i11 = rect2.left;
        if ((i10 < i11 || rect.right <= i11) && rect.right < rect2.right) {
            i8 = 1;
        } else {
            int i12 = rect.right;
            int i13 = rect2.right;
            if ((i12 > i13 || i10 >= i13) && i10 > i11) {
                i8 = -1;
            } else {
                i8 = 0;
            }
        }
        int i14 = rect.top;
        int i15 = rect2.top;
        if ((i14 < i15 || rect.bottom <= i15) && rect.bottom < rect2.bottom) {
            c5 = 1;
        } else {
            int i16 = rect.bottom;
            int i17 = rect2.bottom;
            if ((i16 <= i17 && i14 < i17) || i14 <= i15) {
                c5 = 0;
            }
        }
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 17) {
                    if (i2 != 33) {
                        if (i2 != 66) {
                            if (i2 != 130) {
                                StringBuilder sb2 = new StringBuilder("Invalid direction: ");
                                sb2.append(i2);
                                throw new IllegalArgumentException(C0086a.k(this, sb2));
                            } else if (c5 > 0) {
                                return true;
                            } else {
                                return false;
                            }
                        } else if (i8 > 0) {
                            return true;
                        } else {
                            return false;
                        }
                    } else if (c5 < 0) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (i8 < 0) {
                    return true;
                } else {
                    return false;
                }
            } else if (c5 > 0 || (c5 == 0 && i8 * i7 > 0)) {
                return true;
            } else {
                return false;
            }
        } else if (c5 < 0 || (c5 == 0 && i8 * i7 < 0)) {
            return true;
        } else {
            return false;
        }
    }

    private void jumpToPosition(int i2) {
        int spanCount;
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager instanceof LinearLayoutManager) {
            if ((layoutManager instanceof GridLayoutManager) && i2 < (spanCount = ((GridLayoutManager) layoutManager).getSpanCount())) {
                i2 = spanCount;
            }
            ((LinearLayoutManager) this.mLayout).scrollToPositionWithOffset(i2, 0);
            return;
        }
        scrollToPosition(i2);
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
    public boolean lambda$seslSetGoToTopEnabled$4() {
        int size;
        List<SeslScrollable.SeslOnGoToTopClickListener> list = this.mOnGoToTopClickListeners;
        if (list == null || list.size() - 1 < 0) {
            SeslOnGoToTopClickListener seslOnGoToTopClickListener = this.mOnGoToTopClickListener;
            if (seslOnGoToTopClickListener != null) {
                return ((GoToTopDelegate) ((a) seslOnGoToTopClickListener).e).lambda$setEnable$0(this);
            }
            return false;
        }
        this.mOnGoToTopClickListeners.get(size).getClass();
        throw new ClassCastException();
    }

    private void multiSelection(int i2, int i7, int i8, int i10, boolean z) {
        OnScrollListener onScrollListener;
        int i11;
        int i12;
        if (this.mIsNeedPenSelection) {
            if (this.mIsFirstPenMoveEvent) {
                this.mPenDragStartX = i2;
                this.mPenDragStartY = i7;
                this.mIsPenPressed = true;
                float f = (float) i2;
                float f5 = (float) i7;
                View findChildViewUnder = findChildViewUnder(f, f5);
                this.mPenTrackedChild = findChildViewUnder;
                if (findChildViewUnder == null) {
                    View seslFindNearChildViewUnder = seslFindNearChildViewUnder(f, f5);
                    this.mPenTrackedChild = seslFindNearChildViewUnder;
                    if (seslFindNearChildViewUnder == null) {
                        Log.e("SeslRecyclerView", "multiSelection, mPenTrackedChild is NULL");
                        this.mIsPenPressed = false;
                        this.mIsFirstPenMoveEvent = false;
                        return;
                    }
                }
                this.mPenTrackedChildPosition = getChildLayoutPosition(this.mPenTrackedChild);
                this.mPenDistanceFromTrackedChildTop = this.mPenDragStartY - this.mPenTrackedChild.getTop();
                this.mIsFirstPenMoveEvent = false;
            }
            if (this.mPenDragStartX == 0 && this.mPenDragStartY == 0) {
                this.mPenDragStartX = i2;
                this.mPenDragStartY = i7;
                this.mIsPenPressed = true;
            }
            this.mPenDragEndX = i2;
            this.mPenDragEndY = i7;
            if (i7 < 0) {
                this.mPenDragEndY = 0;
            } else if (i7 > i10) {
                this.mPenDragEndY = i10;
            }
            int i13 = this.mPenDragStartX;
            if (i13 < i2) {
                i11 = i13;
            } else {
                i11 = i2;
            }
            this.mPenDragBlockLeft = i11;
            int i14 = this.mPenDragStartY;
            int i15 = this.mPenDragEndY;
            if (i14 < i15) {
                i12 = i14;
            } else {
                i12 = i15;
            }
            this.mPenDragBlockTop = i12;
            if (i2 <= i13) {
                i2 = i13;
            }
            this.mPenDragBlockRight = i2;
            if (i15 > i14) {
                i14 = i15;
            }
            this.mPenDragBlockBottom = i14;
            z = true;
        }
        if (z) {
            if (i7 <= i8 + this.mHoverTopAreaHeight + this.mHoverDefaultTopAreaHeight) {
                if (!this.mHoverAreaEnter) {
                    this.mHoverAreaEnter = true;
                    this.mHoverScrollStartTime = System.currentTimeMillis();
                    OnScrollListener onScrollListener2 = this.mScrollListener;
                    if (onScrollListener2 != null) {
                        onScrollListener2.onScrollStateChanged(this, 1);
                    }
                }
                if (!this.mHoverHandler.hasMessages(0)) {
                    this.mHoverRecognitionStartTime = System.currentTimeMillis();
                    this.mHoverScrollDirection = 2;
                    this.mHoverHandler.sendEmptyMessage(0);
                }
            } else if (i7 >= ((i10 - this.mHoverBottomAreaHeight) - this.mHoverDefaultBottomAreaHeight) - this.mRemainNestedScrollRange) {
                if (!this.mHoverAreaEnter) {
                    this.mHoverAreaEnter = true;
                    this.mHoverScrollStartTime = System.currentTimeMillis();
                    OnScrollListener onScrollListener3 = this.mScrollListener;
                    if (onScrollListener3 != null) {
                        onScrollListener3.onScrollStateChanged(this, 1);
                    }
                }
                if (!this.mHoverHandler.hasMessages(0)) {
                    this.mHoverRecognitionStartTime = System.currentTimeMillis();
                    this.mHoverScrollDirection = 1;
                    this.mHoverHandler.sendEmptyMessage(0);
                }
            } else {
                if (this.mHoverAreaEnter && (onScrollListener = this.mScrollListener) != null) {
                    onScrollListener.onScrollStateChanged(this, 0);
                }
                this.mHoverScrollStartTime = 0;
                this.mHoverRecognitionStartTime = 0;
                this.mHoverAreaEnter = false;
                if (this.mHoverHandler.hasMessages(0)) {
                    this.mHoverHandler.removeMessages(0);
                    if (this.mScrollState == 1) {
                        setScrollState(0);
                    }
                }
                this.mIsHoverOverscrolled = false;
            }
            if (this.mIsPenDragBlockEnabled) {
                invalidate();
            }
        }
    }

    private void multiSelectionEnd(int i2, int i7) {
        this.mIsPenPressed = false;
        this.mIsFirstPenMoveEvent = true;
        this.mPenDragSelectedViewPosition = -1;
        this.mPenDragSelectedItemArray.clear();
        this.mPenDragStartX = 0;
        this.mPenDragStartY = 0;
        this.mPenDragEndX = 0;
        this.mPenDragEndY = 0;
        this.mPenDragBlockLeft = 0;
        this.mPenDragBlockTop = 0;
        this.mPenDragBlockRight = 0;
        this.mPenDragBlockBottom = 0;
        this.mPenTrackedChild = null;
        this.mPenDistanceFromTrackedChildTop = 0;
        if (this.mIsPenDragBlockEnabled) {
            invalidate();
        }
        if (this.mHoverHandler.hasMessages(0)) {
            this.mHoverHandler.removeMessages(0);
        }
    }

    private void nestedScrollByInternal(int i2, int i7, MotionEvent motionEvent, int i8) {
        boolean z;
        float f;
        float f5;
        int i10;
        int i11;
        int i12;
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null) {
            Log.e("SeslRecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (!this.mLayoutSuppressed) {
            int[] iArr = this.mReusableIntPair;
            int i13 = 0;
            iArr[0] = 0;
            iArr[1] = 0;
            boolean canScrollHorizontally = layoutManager.canScrollHorizontally();
            boolean canScrollVertically = this.mLayout.canScrollVertically();
            if (canScrollVertically) {
                z = canScrollHorizontally | true;
            } else {
                z = canScrollHorizontally;
            }
            if (motionEvent == null) {
                f = ((float) getHeight()) / 2.0f;
            } else {
                f = motionEvent.getY();
            }
            if (motionEvent == null) {
                f5 = ((float) getWidth()) / 2.0f;
            } else {
                f5 = motionEvent.getX();
            }
            int releaseHorizontalGlow = i2 - releaseHorizontalGlow(i2, f);
            int releaseVerticalGlow = i7 - releaseVerticalGlow(i7, f5);
            startNestedScroll(z ? 1 : 0, i8);
            if (canScrollHorizontally) {
                i10 = releaseHorizontalGlow;
            } else {
                i10 = 0;
            }
            if (canScrollVertically) {
                i11 = releaseVerticalGlow;
            } else {
                i11 = 0;
            }
            int i14 = i8;
            if (dispatchNestedPreScroll(i10, i11, this.mReusableIntPair, this.mScrollOffset, i14)) {
                int[] iArr2 = this.mReusableIntPair;
                releaseHorizontalGlow -= iArr2[0];
                releaseVerticalGlow -= iArr2[1];
            }
            if (canScrollHorizontally) {
                i12 = releaseHorizontalGlow;
            } else {
                i12 = 0;
            }
            if (canScrollVertically) {
                i13 = releaseVerticalGlow;
            }
            scrollByInternal(i12, i13, motionEvent, i14);
            GapWorker gapWorker = this.mGapWorker;
            if (!(gapWorker == null || (releaseHorizontalGlow == 0 && releaseVerticalGlow == 0))) {
                gapWorker.postFromTraversal(this, releaseHorizontalGlow, releaseVerticalGlow);
            }
            stopNestedScroll(i14);
        }
    }

    private void onPointerUp(MotionEvent motionEvent) {
        int i2;
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.mScrollPointerId) {
            if (actionIndex == 0) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            this.mScrollPointerId = motionEvent.getPointerId(i2);
            int x9 = (int) (motionEvent.getX(i2) + 0.5f);
            this.mLastTouchX = x9;
            this.mInitialTouchX = x9;
            int y = (int) (motionEvent.getY(i2) + 0.5f);
            this.mLastTouchY = y;
            this.mInitialTouchY = y;
        }
    }

    private boolean pageScroll(int i2) {
        int i7;
        Adapter adapter = this.mAdapter;
        int i8 = 0;
        if (adapter == null) {
            Log.e("SeslRecyclerView", "No adapter attached; skipping pageScroll");
            return false;
        }
        int itemCount = adapter.getItemCount();
        if (itemCount <= 0) {
            return false;
        }
        if (i2 == 0) {
            i7 = findFirstVisibleItemPosition() - getChildCount();
        } else if (i2 == 1) {
            i7 = findLastVisibleItemPosition() + getChildCount();
        } else if (i2 == 2) {
            i7 = 0;
        } else if (i2 != 3) {
            return false;
        } else {
            i7 = itemCount - 1;
        }
        int i10 = itemCount - 1;
        if (i7 > i10) {
            i8 = i10;
        } else if (i7 >= 0) {
            i8 = i7;
        }
        this.mLayout.mRecyclerView.scrollToPosition(i8);
        this.mLayout.mRecyclerView.post(new Runnable() {
            public void run() {
                View childAt = RecyclerView.this.getChildAt(0);
                if (childAt != null) {
                    childAt.requestFocus();
                }
            }
        });
        return true;
    }

    private boolean predictiveItemAnimationsEnabled() {
        if (this.mItemAnimator == null || !this.mLayout.supportsPredictiveItemAnimations()) {
            return false;
        }
        return true;
    }

    private void processAdapterUpdatesAndSetAnimationFlags() {
        boolean z;
        boolean z3;
        boolean z7;
        if (this.mDataSetHasChangedAfterLayout) {
            this.mAdapterHelper.reset();
            if (this.mDispatchItemsChangedEvent) {
                this.mLayout.onItemsChanged(this);
            }
        }
        if (predictiveItemAnimationsEnabled()) {
            this.mAdapterHelper.preProcess();
        } else {
            this.mAdapterHelper.consumeUpdatesInOnePass();
        }
        boolean z9 = true;
        if (this.mItemsAddedOrRemoved || this.mItemsChanged) {
            z = true;
        } else {
            z = false;
        }
        State state = this.mState;
        if (!this.mFirstLayoutComplete || this.mItemAnimator == null || ((!(z7 = this.mDataSetHasChangedAfterLayout) && !z && !this.mLayout.mRequestedSimpleAnimations) || (z7 && !this.mAdapter.hasStableIds()))) {
            z3 = false;
        } else {
            z3 = true;
        }
        state.mRunSimpleAnimations = z3;
        State state2 = this.mState;
        if (!state2.mRunSimpleAnimations || !z || this.mDataSetHasChangedAfterLayout || !predictiveItemAnimationsEnabled()) {
            z9 = false;
        }
        state2.mRunPredictiveAnimations = z9;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x007f A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0080  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void pullGlows(float r7, float r8, float r9, float r10) {
        /*
            r6 = this;
            r0 = 0
            int r1 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            r2 = 1065353216(0x3f800000, float:1.0)
            r3 = 1
            if (r1 >= 0) goto L_0x0021
            r6.ensureLeftGlow()
            android.widget.EdgeEffect r1 = r6.mLeftGlow
            float r4 = -r8
            int r5 = r6.getWidth()
            float r5 = (float) r5
            float r4 = r4 / r5
            int r5 = r6.getHeight()
            float r5 = (float) r5
            float r9 = r9 / r5
            float r9 = r2 - r9
            androidx.core.widget.EdgeEffectCompat.onPullDistance(r1, r4, r9)
        L_0x001f:
            r9 = r3
            goto L_0x003c
        L_0x0021:
            int r1 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r1 <= 0) goto L_0x003b
            r6.ensureRightGlow()
            android.widget.EdgeEffect r1 = r6.mRightGlow
            int r4 = r6.getWidth()
            float r4 = (float) r4
            float r4 = r8 / r4
            int r5 = r6.getHeight()
            float r5 = (float) r5
            float r9 = r9 / r5
            androidx.core.widget.EdgeEffectCompat.onPullDistance(r1, r4, r9)
            goto L_0x001f
        L_0x003b:
            r9 = 0
        L_0x003c:
            int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r1 >= 0) goto L_0x0056
            r6.ensureTopGlow()
            android.widget.EdgeEffect r9 = r6.mTopGlow
            float r1 = -r10
            int r2 = r6.getHeight()
            float r2 = (float) r2
            float r1 = r1 / r2
            int r2 = r6.getWidth()
            float r2 = (float) r2
            float r7 = r7 / r2
            androidx.core.widget.EdgeEffectCompat.onPullDistance(r9, r1, r7)
            goto L_0x0072
        L_0x0056:
            int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r1 <= 0) goto L_0x0071
            r6.ensureBottomGlow()
            android.widget.EdgeEffect r9 = r6.mBottomGlow
            int r1 = r6.getHeight()
            float r1 = (float) r1
            float r1 = r10 / r1
            int r4 = r6.getWidth()
            float r4 = (float) r4
            float r7 = r7 / r4
            float r2 = r2 - r7
            androidx.core.widget.EdgeEffectCompat.onPullDistance(r9, r1, r2)
            goto L_0x0072
        L_0x0071:
            r3 = r9
        L_0x0072:
            r6.mEdgeEffectByDragging = r3
            if (r3 != 0) goto L_0x0080
            int r7 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r7 != 0) goto L_0x0080
            int r7 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r7 == 0) goto L_0x007f
            goto L_0x0080
        L_0x007f:
            return
        L_0x0080:
            androidx.core.view.ViewCompat.postInvalidateOnAnimation(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.pullGlows(float, float, float, float):void");
    }

    private void recoverFocusFromState() {
        ViewHolder viewHolder;
        View findViewById;
        if (this.mPreserveFocusAfterLayout && this.mAdapter != null && hasFocus() && getDescendantFocusability() != 393216) {
            if (getDescendantFocusability() != 131072 || !isFocused()) {
                if (!isFocused()) {
                    View focusedChild = getFocusedChild();
                    if (!IGNORE_DETACHED_FOCUSED_CHILD || (focusedChild.getParent() != null && focusedChild.hasFocus())) {
                        if (!this.mChildHelper.isHidden(focusedChild)) {
                            return;
                        }
                    } else if (this.mChildHelper.getChildCount() == 0) {
                        requestFocus();
                        return;
                    }
                }
                View view = null;
                if (this.mState.mFocusedItemId == -1 || !this.mAdapter.hasStableIds()) {
                    viewHolder = null;
                } else {
                    viewHolder = findViewHolderForItemId(this.mState.mFocusedItemId);
                }
                if (viewHolder != null && !this.mChildHelper.isHidden(viewHolder.itemView) && viewHolder.itemView.hasFocusable()) {
                    view = viewHolder.itemView;
                } else if (this.mChildHelper.getChildCount() > 0) {
                    view = findNextViewToFocus();
                }
                if (view != null) {
                    int i2 = this.mState.mFocusedSubChildId;
                    if (!(((long) i2) == -1 || (findViewById = view.findViewById(i2)) == null || !findViewById.isFocusable())) {
                        view = findViewById;
                    }
                    view.requestFocus();
                }
            }
        }
    }

    private void releaseGlows() {
        boolean z;
        EdgeEffect edgeEffect = this.mLeftGlow;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            z = this.mLeftGlow.isFinished();
        } else {
            z = false;
        }
        EdgeEffect edgeEffect2 = this.mTopGlow;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            z |= this.mTopGlow.isFinished();
        }
        EdgeEffect edgeEffect3 = this.mRightGlow;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            z |= this.mRightGlow.isFinished();
        }
        EdgeEffect edgeEffect4 = this.mBottomGlow;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            z |= this.mBottomGlow.isFinished();
        }
        if (z) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private int releaseHorizontalGlow(int i2, float f) {
        float height = f / ((float) getHeight());
        float width = ((float) i2) / ((float) getWidth());
        EdgeEffect edgeEffect = this.mLeftGlow;
        float f5 = 0.0f;
        if (edgeEffect == null || EdgeEffectCompat.getDistance(edgeEffect) == 0.0f) {
            EdgeEffect edgeEffect2 = this.mRightGlow;
            if (!(edgeEffect2 == null || EdgeEffectCompat.getDistance(edgeEffect2) == 0.0f)) {
                if (canScrollHorizontally(1)) {
                    this.mRightGlow.onRelease();
                } else {
                    float onPullDistance = EdgeEffectCompat.onPullDistance(this.mRightGlow, width, height);
                    if (EdgeEffectCompat.getDistance(this.mRightGlow) == 0.0f) {
                        this.mRightGlow.onRelease();
                    }
                    f5 = onPullDistance;
                }
                invalidate();
            }
        } else {
            if (canScrollHorizontally(-1)) {
                this.mLeftGlow.onRelease();
            } else {
                float f8 = -EdgeEffectCompat.onPullDistance(this.mLeftGlow, -width, 1.0f - height);
                if (EdgeEffectCompat.getDistance(this.mLeftGlow) == 0.0f) {
                    this.mLeftGlow.onRelease();
                }
                f5 = f8;
            }
            invalidate();
        }
        return Math.round(f5 * ((float) getWidth()));
    }

    private int releaseVerticalGlow(int i2, float f) {
        float width = f / ((float) getWidth());
        float height = ((float) i2) / ((float) getHeight());
        EdgeEffect edgeEffect = this.mTopGlow;
        float f5 = 0.0f;
        if (edgeEffect == null || EdgeEffectCompat.getDistance(edgeEffect) == 0.0f) {
            EdgeEffect edgeEffect2 = this.mBottomGlow;
            if (!(edgeEffect2 == null || EdgeEffectCompat.getDistance(edgeEffect2) == 0.0f)) {
                if (canScrollVertically(1)) {
                    this.mBottomGlow.onRelease();
                } else {
                    float onPullDistance = EdgeEffectCompat.onPullDistance(this.mBottomGlow, height, 1.0f - width);
                    if (EdgeEffectCompat.getDistance(this.mBottomGlow) == 0.0f) {
                        this.mBottomGlow.onRelease();
                    }
                    f5 = onPullDistance;
                }
                invalidate();
            }
        } else {
            if (canScrollVertically(-1)) {
                this.mTopGlow.onRelease();
            } else {
                float f8 = -EdgeEffectCompat.onPullDistance(this.mTopGlow, -height, width);
                if (EdgeEffectCompat.getDistance(this.mTopGlow) == 0.0f) {
                    this.mTopGlow.onRelease();
                }
                f5 = f8;
            }
            invalidate();
        }
        return Math.round(f5 * ((float) getHeight()));
    }

    private void requestChildOnScreen(View view, View view2) {
        View view3;
        boolean z;
        if (view2 != null) {
            view3 = view2;
        } else {
            view3 = view;
        }
        this.mTempRect.set(0, 0, view3.getWidth(), view3.getHeight());
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof LayoutParams) {
            LayoutParams layoutParams2 = (LayoutParams) layoutParams;
            if (!layoutParams2.mInsetsDirty) {
                Rect rect = layoutParams2.mDecorInsets;
                Rect rect2 = this.mTempRect;
                rect2.left -= rect.left;
                rect2.right += rect.right;
                rect2.top -= rect.top;
                rect2.bottom += rect.bottom;
            }
        }
        if (view2 != null) {
            offsetDescendantRectToMyCoords(view2, this.mTempRect);
            offsetRectIntoDescendantCoords(view, this.mTempRect);
        }
        LayoutManager layoutManager = this.mLayout;
        Rect rect3 = this.mTempRect;
        boolean z3 = !this.mFirstLayoutComplete;
        if (view2 == null) {
            z = true;
        } else {
            z = false;
        }
        layoutManager.requestChildRectangleOnScreen(this, view, rect3, z3, z);
    }

    private void resetFocusInfo() {
        State state = this.mState;
        state.mFocusedItemId = -1;
        state.mFocusedItemPosition = -1;
        state.mFocusedSubChildId = -1;
    }

    private void resetScroll() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        stopNestedScroll(0);
        releaseGlows();
    }

    private void runLastItemAddDeleteAnim(View view) {
        if (this.mLastItemAddRemoveAnim == null) {
            ItemAnimator itemAnimator = getItemAnimator();
            if ((itemAnimator instanceof DefaultItemAnimator) && this.mLastItemAnimTop == -1) {
                this.mLastItemAnimTop = ((DefaultItemAnimator) itemAnimator).getLastItemBottom();
            }
            if (this.mIsSetOnlyAddAnim) {
                this.mLastItemAddRemoveAnim = ValueAnimator.ofInt(new int[]{this.mLastItemAnimTop, view.getHeight() + ((int) view.getY())});
            } else if (this.mIsSetOnlyRemoveAnim) {
                this.mLastItemAddRemoveAnim = ValueAnimator.ofInt(new int[]{this.mLastItemAnimTop, view.getBottom()});
            } else {
                Log.d("SeslRecyclerView", "Not set only add/remove anim");
                return;
            }
            this.mLastItemAddRemoveAnim.setDuration(330);
            this.mLastItemAddRemoveAnim.addListener(this.mAnimListener);
            this.mLastItemAddRemoveAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int unused = RecyclerView.this.mAnimatedBlackTop = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                    RecyclerView.this.invalidate();
                }
            });
            this.mLastItemAddRemoveAnim.start();
        }
    }

    private void saveFocusInfo() {
        View view;
        long j2;
        int i2;
        ViewHolder viewHolder = null;
        if (!this.mPreserveFocusAfterLayout || !hasFocus() || this.mAdapter == null) {
            view = null;
        } else {
            view = getFocusedChild();
        }
        if (view != null) {
            viewHolder = findContainingViewHolder(view);
        }
        if (viewHolder == null) {
            resetFocusInfo();
            return;
        }
        State state = this.mState;
        if (this.mAdapter.hasStableIds()) {
            j2 = viewHolder.getItemId();
        } else {
            j2 = -1;
        }
        state.mFocusedItemId = j2;
        State state2 = this.mState;
        if (this.mDataSetHasChangedAfterLayout) {
            i2 = -1;
        } else if (viewHolder.isRemoved()) {
            i2 = viewHolder.mOldPosition;
        } else {
            i2 = viewHolder.getAbsoluteAdapterPosition();
        }
        state2.mFocusedItemPosition = i2;
        this.mState.mFocusedSubChildId = getDeepestFocusedViewWithId(viewHolder.itemView);
    }

    /* access modifiers changed from: private */
    public boolean seslDispatchNestedScroll(int i2, int i7, int i8, int i10, int[] iArr, int i11, int[] iArr2) {
        return getScrollingChildHelper().seslDispatchNestedScroll(i2, i7, i8, i10, iArr, i11, iArr2);
    }

    private void seslRenderFadingEffect(Canvas canvas) {
        this.mFadingEdgeHelper.renderFadingEffect(canvas, this.mScrollInfoProvider);
    }

    private void setAdapterInternal(Adapter<?> adapter, boolean z, boolean z3) {
        Adapter adapter2 = this.mAdapter;
        if (adapter2 != null) {
            adapter2.unregisterAdapterDataObserver(this.mObserver);
            this.mAdapter.onDetachedFromRecyclerView(this);
        }
        if (!z || z3) {
            removeAndRecycleViews();
        }
        this.mAdapterHelper.reset();
        Adapter adapter3 = this.mAdapter;
        this.mAdapter = adapter;
        if (adapter != null) {
            adapter.registerAdapterDataObserver(this.mObserver);
            adapter.onAttachedToRecyclerView(this);
        }
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.onAdapterChanged(adapter3, this.mAdapter);
        }
        this.mRecycler.onAdapterChanged(adapter3, this.mAdapter, z);
        this.mState.mStructureChanged = true;
    }

    public static void setDebugAssertionsEnabled(boolean z) {
        sDebugAssertionsEnabled = z;
    }

    public static void setVerboseLoggingEnabled(boolean z) {
        sVerboseLoggingEnabled = z;
    }

    private boolean shouldAbsorb(EdgeEffect edgeEffect, int i2, int i7) {
        if (i2 > 0) {
            return true;
        }
        if (getSplineFlingDistance(-i2) < EdgeEffectCompat.getDistance(edgeEffect) * ((float) i7)) {
            return true;
        }
        return false;
    }

    private boolean stopGlowAnimations(MotionEvent motionEvent) {
        boolean z;
        EdgeEffect edgeEffect = this.mLeftGlow;
        if (edgeEffect == null || EdgeEffectCompat.getDistance(edgeEffect) == 0.0f || canScrollHorizontally(-1)) {
            z = false;
        } else {
            EdgeEffectCompat.onPullDistance(this.mLeftGlow, 0.0f, 1.0f - (motionEvent.getY() / ((float) getHeight())));
            z = true;
        }
        EdgeEffect edgeEffect2 = this.mRightGlow;
        if (!(edgeEffect2 == null || EdgeEffectCompat.getDistance(edgeEffect2) == 0.0f || canScrollHorizontally(1))) {
            EdgeEffectCompat.onPullDistance(this.mRightGlow, 0.0f, motionEvent.getY() / ((float) getHeight()));
            z = true;
        }
        EdgeEffect edgeEffect3 = this.mTopGlow;
        if (!(edgeEffect3 == null || EdgeEffectCompat.getDistance(edgeEffect3) == 0.0f || canScrollVertically(-1))) {
            EdgeEffectCompat.onPullDistance(this.mTopGlow, 0.0f, motionEvent.getX() / ((float) getWidth()));
            z = true;
        }
        EdgeEffect edgeEffect4 = this.mBottomGlow;
        if (edgeEffect4 == null || EdgeEffectCompat.getDistance(edgeEffect4) == 0.0f || canScrollVertically(1)) {
            return z;
        }
        EdgeEffectCompat.onPullDistance(this.mBottomGlow, 0.0f, 1.0f - (motionEvent.getX() / ((float) getWidth())));
        return true;
    }

    private void stopScrollersInternal() {
        this.mViewFlinger.stop();
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.stopSmoothScroller();
        }
    }

    private SeslGoToTopConfig updateGoToTopConfig() {
        return new SeslGoToTopConfig.Builder().setIconLight(this.mContext.getResources().getDrawable(R$drawable.sesl_list_go_to_top_light)).setIconDark(this.mContext.getResources().getDrawable(R$drawable.sesl_list_go_to_top_dark)).setBackgroundLight(this.mContext.getResources().getDrawable(androidx.recyclerview.R$drawable.sesl_go_to_top_background_light, (Resources.Theme) null)).setBackgroundDark(this.mContext.getResources().getDrawable(androidx.recyclerview.R$drawable.sesl_go_to_top_background_dark, (Resources.Theme) null)).setBackgroundBlur(this.mContext.getResources().getDrawable(androidx.recyclerview.R$drawable.sesl_go_to_top_background_blur, (Resources.Theme) null)).setBackgroundColorBlur(this.mContext.getResources().getColor(R$color.sesl_figma_floating_component_blur_background_dark)).setPaddingBottom(this.mContext.getResources().getDimensionPixelSize(androidx.appcompat.R$dimen.sesl_go_to_top_scrollable_view_gap)).setPaddingLeft(0).setPaddingRight(0).setSize(this.mContext.getResources().getDimensionPixelSize(androidx.appcompat.R$dimen.sesl_go_to_top_scrollable_view_size)).setElevation(this.mContext.getResources().getDimension(androidx.appcompat.R$dimen.sesl_go_to_top_elevation)).setOverlayFeatureHiddenHeightPx(this.mSeslOverlayFeatureHeight).setSizeChanged(false).setFadeInInterpolator(SeslAnimationUtils.SINE_IN_OUT_70).setFadeOutInterpolator(LINEAR_INTERPOLATOR).build();
    }

    /* access modifiers changed from: private */
    public void updateLongPressMultiSelection(int i2, int i7, boolean z) {
        int height;
        int i8;
        int i10;
        int i11;
        int i12;
        int i13;
        OnScrollListener onScrollListener;
        int i14 = i2;
        int i15 = i7;
        int childCount = this.mChildHelper.getChildCount();
        if (this.mIsFirstMultiSelectionMove) {
            this.mPenDragStartX = i14;
            this.mPenDragStartY = i15;
            float f = (float) i14;
            float f5 = (float) i15;
            View findChildViewUnder = findChildViewUnder(f, f5);
            this.mPenTrackedChild = findChildViewUnder;
            if (findChildViewUnder == null) {
                View seslFindNearChildViewUnder = seslFindNearChildViewUnder(f, f5);
                this.mPenTrackedChild = seslFindNearChildViewUnder;
                if (seslFindNearChildViewUnder == null) {
                    Log.e("SeslRecyclerView", "updateLongPressMultiSelection, mPenTrackedChild is NULL");
                    this.mIsFirstMultiSelectionMove = false;
                    return;
                }
            }
            SeslLongPressMultiSelectionListener seslLongPressMultiSelectionListener = this.mLongPressMultiSelectionListener;
            if (seslLongPressMultiSelectionListener != null) {
                seslLongPressMultiSelectionListener.onLongPressMultiSelectionStarted(i14, i15);
            }
            int childLayoutPosition = getChildLayoutPosition(this.mPenTrackedChild);
            this.mPenTrackedChildPosition = childLayoutPosition;
            this.mPenDragSelectedViewPosition = childLayoutPosition;
            this.mPenDistanceFromTrackedChildTop = this.mPenDragStartY - this.mPenTrackedChild.getTop();
            this.mIsFirstMultiSelectionMove = false;
        }
        if (this.mIsEnabledPaddingInHoverScroll) {
            int i16 = this.mListPadding.top;
            height = getHeight() - this.mListPadding.bottom;
            i8 = i16;
        } else {
            height = getHeight();
            i8 = 0;
        }
        int i17 = height;
        this.mPenDragEndX = i14;
        this.mPenDragEndY = i15;
        if (i15 < 0) {
            this.mPenDragEndY = 0;
        } else if (i15 > i17) {
            this.mPenDragEndY = i17;
        }
        View findChildViewUnder2 = findChildViewUnder((float) i14, (float) this.mPenDragEndY);
        if (findChildViewUnder2 == null && (findChildViewUnder2 = seslFindNearChildViewUnder((float) this.mPenDragEndX, (float) this.mPenDragEndY)) == null) {
            Log.e("SeslRecyclerView", "updateLongPressMultiSelection, touchedView is NULL");
            return;
        }
        int childLayoutPosition2 = getChildLayoutPosition(findChildViewUnder2);
        if (childLayoutPosition2 != -1) {
            this.mPenDragSelectedViewPosition = childLayoutPosition2;
            int i18 = this.mPenTrackedChildPosition;
            if (i18 < childLayoutPosition2) {
                i10 = childLayoutPosition2;
                i11 = i18;
            } else {
                i11 = childLayoutPosition2;
                i10 = i18;
            }
            int i19 = this.mPenDragStartX;
            int i20 = this.mPenDragEndX;
            if (i19 < i20) {
                i12 = i19;
            } else {
                i12 = i20;
            }
            this.mPenDragBlockLeft = i12;
            int i21 = this.mPenDragStartY;
            int i22 = this.mPenDragEndY;
            if (i21 < i22) {
                i13 = i21;
            } else {
                i13 = i22;
            }
            this.mPenDragBlockTop = i13;
            if (i20 > i19) {
                i19 = i20;
            }
            this.mPenDragBlockRight = i19;
            if (i22 > i21) {
                i21 = i22;
            }
            this.mPenDragBlockBottom = i21;
            for (int i23 = 0; i23 < childCount; i23++) {
                View childAt = getChildAt(i23);
                if (childAt != null) {
                    this.mPenDragSelectedViewPosition = getChildLayoutPosition(childAt);
                    if (childAt.getVisibility() == 0) {
                        int i24 = this.mPenDragSelectedViewPosition;
                        if (i11 > i24 || i24 > i10 || i24 == this.mPenTrackedChildPosition) {
                            if (i24 != -1 && this.mPenDragSelectedItemArray.contains(Integer.valueOf(i24))) {
                                this.mPenDragSelectedItemArray.remove(Integer.valueOf(this.mPenDragSelectedViewPosition));
                                SeslLongPressMultiSelectionListener seslLongPressMultiSelectionListener2 = this.mLongPressMultiSelectionListener;
                                if (seslLongPressMultiSelectionListener2 != null) {
                                    seslLongPressMultiSelectionListener2.onItemSelected(this, childAt, this.mPenDragSelectedViewPosition, getChildItemId(childAt));
                                }
                            }
                        } else if (i24 != -1 && !this.mPenDragSelectedItemArray.contains(Integer.valueOf(i24))) {
                            this.mPenDragSelectedItemArray.add(Integer.valueOf(this.mPenDragSelectedViewPosition));
                            SeslLongPressMultiSelectionListener seslLongPressMultiSelectionListener3 = this.mLongPressMultiSelectionListener;
                            if (seslLongPressMultiSelectionListener3 != null) {
                                seslLongPressMultiSelectionListener3.onItemSelected(this, childAt, this.mPenDragSelectedViewPosition, getChildItemId(childAt));
                            }
                        }
                    }
                }
            }
            int i25 = this.mLastTouchY - i15;
            if (z && Math.abs(i25) >= this.mTouchSlop) {
                if (i15 <= i8 + this.mHoverTopAreaHeight + this.mHoverDefaultTopAreaHeight && i25 > 0) {
                    if (!this.mHoverAreaEnter) {
                        this.mHoverAreaEnter = true;
                        this.mHoverScrollStartTime = System.currentTimeMillis();
                        OnScrollListener onScrollListener2 = this.mScrollListener;
                        if (onScrollListener2 != null) {
                            onScrollListener2.onScrollStateChanged(this, 1);
                        }
                    }
                    if (!this.mHoverHandler.hasMessages(0)) {
                        this.mHoverRecognitionStartTime = System.currentTimeMillis();
                        this.mHoverScrollDirection = 2;
                        this.mHoverHandler.sendEmptyMessage(0);
                    }
                } else if (i15 < ((i17 - this.mHoverBottomAreaHeight) - this.mHoverDefaultBottomAreaHeight) - this.mRemainNestedScrollRange || i25 >= 0) {
                    if (this.mHoverAreaEnter && (onScrollListener = this.mScrollListener) != null) {
                        onScrollListener.onScrollStateChanged(this, 0);
                    }
                    this.mHoverScrollStartTime = 0;
                    this.mHoverRecognitionStartTime = 0;
                    this.mHoverAreaEnter = false;
                    if (this.mHoverHandler.hasMessages(0)) {
                        this.mHoverHandler.removeMessages(0);
                        if (this.mScrollState == 1) {
                            setScrollState(0);
                        }
                    }
                    this.mIsHoverOverscrolled = false;
                } else {
                    if (!this.mHoverAreaEnter) {
                        this.mHoverAreaEnter = true;
                        this.mHoverScrollStartTime = System.currentTimeMillis();
                        OnScrollListener onScrollListener3 = this.mScrollListener;
                        if (onScrollListener3 != null) {
                            onScrollListener3.onScrollStateChanged(this, 1);
                        }
                    }
                    if (!this.mHoverHandler.hasMessages(0)) {
                        this.mHoverRecognitionStartTime = System.currentTimeMillis();
                        this.mHoverScrollDirection = 1;
                        this.mHoverHandler.sendEmptyMessage(0);
                    }
                }
            }
            invalidate();
            return;
        }
        Log.e("SeslRecyclerView", "touchedPosition is NO_POSITION");
    }

    private void updateScrollbarVerticalPadding() {
        SeslViewReflector.semSetScrollBarTopPadding(this, this.mScrollbarTopPadding + this.mScrollBarTopOffset);
        SeslViewReflector.semSetScrollBarBottomPadding(this, this.mScrollbarBottomPadding + this.mScrollBarBottomOffset);
    }

    public void absorbGlows(int i2, int i7) {
        if (i2 < 0) {
            ensureLeftGlow();
            if (this.mLeftGlow.isFinished()) {
                this.mLeftGlow.onAbsorb(-i2);
            }
        } else if (i2 > 0) {
            ensureRightGlow();
            if (this.mRightGlow.isFinished()) {
                this.mRightGlow.onAbsorb(i2);
            }
        }
        if (i7 < 0) {
            ensureTopGlow();
            if (this.mTopGlow.isFinished()) {
                this.mTopGlow.onAbsorb(-i7);
            }
        } else if (i7 > 0) {
            ensureBottomGlow();
            if (this.mBottomGlow.isFinished()) {
                this.mBottomGlow.onAbsorb(i7);
            }
        }
        if (i2 != 0 || i7 != 0) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void addFocusables(ArrayList<View> arrayList, int i2, int i7) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null || !layoutManager.onAddFocusables(this, arrayList, i2, i7)) {
            super.addFocusables(arrayList, i2, i7);
        }
    }

    public void addItemDecoration(ItemDecoration itemDecoration, int i2) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.assertNotInLayoutOrScroll("Cannot add item decoration during a scroll  or layout");
        }
        if (this.mItemDecorations.isEmpty()) {
            setWillNotDraw(false);
        }
        if (i2 < 0) {
            this.mItemDecorations.add(itemDecoration);
        } else {
            this.mItemDecorations.add(i2, itemDecoration);
        }
        markItemDecorInsetsDirty();
        requestLayout();
    }

    public void addOnChildAttachStateChangeListener(OnChildAttachStateChangeListener onChildAttachStateChangeListener) {
        if (this.mOnChildAttachStateListeners == null) {
            this.mOnChildAttachStateListeners = new ArrayList();
        }
        this.mOnChildAttachStateListeners.add(onChildAttachStateChangeListener);
    }

    public void addOnItemTouchListener(OnItemTouchListener onItemTouchListener) {
        this.mOnItemTouchListeners.add(onItemTouchListener);
    }

    public void addOnScrollListener(OnScrollListener onScrollListener) {
        if (this.mScrollListeners == null) {
            this.mScrollListeners = new ArrayList();
        }
        this.mScrollListeners.add(onScrollListener);
    }

    public void animateAppearance(ViewHolder viewHolder, ItemAnimator.ItemHolderInfo itemHolderInfo, ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        viewHolder.setIsRecyclable(false);
        if (this.mItemAnimator.animateAppearance(viewHolder, itemHolderInfo, itemHolderInfo2)) {
            postAnimationRunner();
        }
    }

    public void animateDisappearance(ViewHolder viewHolder, ItemAnimator.ItemHolderInfo itemHolderInfo, ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        addAnimatingView(viewHolder);
        viewHolder.setIsRecyclable(false);
        if (this.mItemAnimator.animateDisappearance(viewHolder, itemHolderInfo, itemHolderInfo2)) {
            postAnimationRunner();
        }
    }

    public void assertNotInLayoutOrScroll(String str) {
        if (isComputingLayout()) {
            if (str == null) {
                throw new IllegalStateException(C0086a.k(this, new StringBuilder("Cannot call this method while RecyclerView is computing a layout or scrolling")));
            }
            throw new IllegalStateException(str);
        } else if (this.mDispatchScrollCounter > 0) {
            Log.w("SeslRecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException(C0086a.k(this, new StringBuilder(""))));
        }
    }

    public boolean canReuseUpdatedViewHolder(ViewHolder viewHolder) {
        ItemAnimator itemAnimator = this.mItemAnimator;
        if (itemAnimator == null || itemAnimator.canReuseUpdatedViewHolder(viewHolder, viewHolder.getUnmodifiedPayloads())) {
            return true;
        }
        return false;
    }

    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (!(layoutParams instanceof LayoutParams) || !this.mLayout.checkLayoutParams((LayoutParams) layoutParams)) {
            return false;
        }
        return true;
    }

    public void clearOldPositions() {
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i2 = 0; i2 < unfilteredChildCount; i2++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i2));
            if (!childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.clearOldPosition();
            }
        }
        this.mRecycler.clearOldPositions();
    }

    public void clearOnScrollListeners() {
        List<OnScrollListener> list = this.mScrollListeners;
        if (list != null) {
            list.clear();
        }
    }

    public int computeHorizontalScrollExtent() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollHorizontally()) {
            return this.mLayout.computeHorizontalScrollExtent(this.mState);
        }
        return 0;
    }

    public int computeHorizontalScrollOffset() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollHorizontally()) {
            return this.mLayout.computeHorizontalScrollOffset(this.mState);
        }
        return 0;
    }

    public int computeHorizontalScrollRange() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollHorizontally()) {
            return this.mLayout.computeHorizontalScrollRange(this.mState);
        }
        return 0;
    }

    public int computeVerticalScrollExtent() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollVertically()) {
            return this.mLayout.computeVerticalScrollExtent(this.mState);
        }
        return 0;
    }

    public int computeVerticalScrollOffset() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollVertically()) {
            return this.mLayout.computeVerticalScrollOffset(this.mState);
        }
        return 0;
    }

    public int computeVerticalScrollRange() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollVertically()) {
            return this.mLayout.computeVerticalScrollRange(this.mState);
        }
        return 0;
    }

    public void considerReleasingGlowsOnScroll(int i2, int i7) {
        boolean z;
        EdgeEffect edgeEffect = this.mLeftGlow;
        if (edgeEffect == null || edgeEffect.isFinished() || i2 <= 0) {
            z = false;
        } else {
            this.mLeftGlow.onRelease();
            z = this.mLeftGlow.isFinished();
        }
        EdgeEffect edgeEffect2 = this.mRightGlow;
        if (edgeEffect2 != null && !edgeEffect2.isFinished() && i2 < 0) {
            this.mRightGlow.onRelease();
            z |= this.mRightGlow.isFinished();
        }
        EdgeEffect edgeEffect3 = this.mTopGlow;
        if (edgeEffect3 != null && !edgeEffect3.isFinished() && i7 > 0) {
            this.mTopGlow.onRelease();
            z |= this.mTopGlow.isFinished();
        }
        EdgeEffect edgeEffect4 = this.mBottomGlow;
        if (edgeEffect4 != null && !edgeEffect4.isFinished() && i7 < 0) {
            this.mBottomGlow.onRelease();
            z |= this.mBottomGlow.isFinished();
        }
        if (z) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public int consumeFlingInHorizontalStretch(int i2) {
        return consumeFlingInStretch(i2, this.mLeftGlow, this.mRightGlow, getWidth());
    }

    public int consumeFlingInVerticalStretch(int i2) {
        return consumeFlingInStretch(i2, this.mTopGlow, this.mBottomGlow, getHeight());
    }

    public void consumePendingUpdateOperations() {
        if (!this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout) {
            TraceCompat.beginSection("RV FullInvalidate");
            dispatchLayout();
            TraceCompat.endSection();
        } else if (this.mAdapterHelper.hasPendingUpdates()) {
            if (this.mAdapterHelper.hasAnyUpdateTypes(4) && !this.mAdapterHelper.hasAnyUpdateTypes(11)) {
                TraceCompat.beginSection("RV PartialInvalidate");
                startInterceptRequestLayout();
                onEnterLayoutOrScroll();
                this.mAdapterHelper.preProcess();
                if (!this.mLayoutWasDefered) {
                    if (hasUpdatedView()) {
                        dispatchLayout();
                    } else {
                        this.mAdapterHelper.consumePostponedUpdates();
                    }
                }
                stopInterceptRequestLayout(true);
                onExitLayoutOrScroll();
                TraceCompat.endSection();
            } else if (this.mAdapterHelper.hasPendingUpdates()) {
                TraceCompat.beginSection("RV FullInvalidate");
                dispatchLayout();
                TraceCompat.endSection();
            }
        }
    }

    public void defaultOnMeasure(int i2, int i7) {
        setMeasuredDimension(LayoutManager.chooseSize(i2, getPaddingRight() + getPaddingLeft(), ViewCompat.getMinimumWidth(this)), LayoutManager.chooseSize(i7, getPaddingBottom() + getPaddingTop(), ViewCompat.getMinimumHeight(this)));
    }

    public void dispatchChildAttached(View view) {
        ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        onChildAttachedToWindow(view);
        Adapter adapter = this.mAdapter;
        if (!(adapter == null || childViewHolderInt == null)) {
            adapter.onViewAttachedToWindow(childViewHolderInt);
        }
        List<OnChildAttachStateChangeListener> list = this.mOnChildAttachStateListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mOnChildAttachStateListeners.get(size).onChildViewAttachedToWindow(view);
            }
        }
    }

    public void dispatchChildDetached(View view) {
        ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        onChildDetachedFromWindow(view);
        Adapter adapter = this.mAdapter;
        if (!(adapter == null || childViewHolderInt == null)) {
            adapter.onViewDetachedFromWindow(childViewHolderInt);
        }
        List<OnChildAttachStateChangeListener> list = this.mOnChildAttachStateListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mOnChildAttachStateListeners.get(size).onChildViewDetachedFromWindow(view);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x010d  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0125  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0149  */
    /* JADX WARNING: Removed duplicated region for block: B:73:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x003d A[LOOP:0: B:7:0x003b->B:8:0x003d, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void dispatchDraw(android.graphics.Canvas r14) {
        /*
            r13 = this;
            super.dispatchDraw(r14)
            boolean r0 = r13.mDebugDrawAvailRect
            if (r0 == 0) goto L_0x0032
            android.graphics.Paint r6 = new android.graphics.Paint
            r6.<init>()
            r0 = -16776961(0xffffffffff0000ff, float:-1.7014636E38)
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
            java.util.ArrayList<androidx.recyclerview.widget.RecyclerView$ItemDecoration> r14 = r13.mItemDecorations
            int r14 = r14.size()
            r0 = 0
            r1 = r0
        L_0x003b:
            if (r1 >= r14) goto L_0x004d
            java.util.ArrayList<androidx.recyclerview.widget.RecyclerView$ItemDecoration> r2 = r13.mItemDecorations
            java.lang.Object r2 = r2.get(r1)
            androidx.recyclerview.widget.RecyclerView$ItemDecoration r2 = (androidx.recyclerview.widget.RecyclerView.ItemDecoration) r2
            androidx.recyclerview.widget.RecyclerView$State r3 = r13.mState
            r2.seslOnDispatchDraw(r7, r13, r3)
            int r1 = r1 + 1
            goto L_0x003b
        L_0x004d:
            int r14 = r13.getWidth()
            int r1 = r13.getPaddingLeft()
            int r2 = r13.getPaddingRight()
            boolean r3 = r13.mDrawRect
            if (r3 == 0) goto L_0x0067
            int r3 = r13.mBlackTop
            r4 = -1
            if (r3 != r4) goto L_0x006a
            int r3 = r13.mLastBlackTop
            if (r3 == r4) goto L_0x0067
            goto L_0x006a
        L_0x0067:
            r8 = r1
            goto L_0x011d
        L_0x006a:
            boolean r3 = r13.canScrollVertically(r4)
            if (r3 != 0) goto L_0x0067
            r3 = 1
            boolean r5 = r13.canScrollVertically(r3)
            if (r5 == 0) goto L_0x007d
            boolean r5 = r13.isAnimating()
            if (r5 == 0) goto L_0x0067
        L_0x007d:
            android.animation.ValueAnimator r5 = r13.mLastItemAddRemoveAnim
            if (r5 == 0) goto L_0x0087
            boolean r5 = r5.isRunning()
            if (r5 != 0) goto L_0x008b
        L_0x0087:
            int r5 = r13.mBlackTop
            r13.mAnimatedBlackTop = r5
        L_0x008b:
            boolean r5 = r13.isAnimating()
            if (r5 == 0) goto L_0x00ee
            int r5 = r13.getPendingAnimFlag()
            r6 = 8
            if (r5 != r6) goto L_0x009c
            r13.mIsSetOnlyAddAnim = r3
            goto L_0x00a0
        L_0x009c:
            if (r5 != r3) goto L_0x00a0
            r13.mIsSetOnlyRemoveAnim = r3
        L_0x00a0:
            boolean r5 = r13.mDrawReverse
            if (r5 == 0) goto L_0x00b4
            int r3 = r13.mBlackTop
            if (r3 == r4) goto L_0x00af
            androidx.recyclerview.widget.ChildHelper r3 = r13.mChildHelper
            android.view.View r0 = r3.getChildAt(r0)
            goto L_0x00cd
        L_0x00af:
            android.view.View r0 = r13.getChildAt(r0)
            goto L_0x00cd
        L_0x00b4:
            int r0 = r13.mBlackTop
            if (r0 == r4) goto L_0x00c4
            androidx.recyclerview.widget.ChildHelper r0 = r13.mChildHelper
            int r5 = r0.getChildCount()
            int r5 = r5 - r3
            android.view.View r0 = r0.getChildAt(r5)
            goto L_0x00cd
        L_0x00c4:
            int r0 = r13.getChildCount()
            int r0 = r0 - r3
            android.view.View r0 = r13.getChildAt(r0)
        L_0x00cd:
            if (r0 == 0) goto L_0x00eb
            boolean r3 = r13.mIsSetOnlyAddAnim
            if (r3 != 0) goto L_0x00e8
            boolean r3 = r13.mIsSetOnlyRemoveAnim
            if (r3 == 0) goto L_0x00d8
            goto L_0x00e8
        L_0x00d8:
            float r3 = r0.getY()
            int r3 = java.lang.Math.round(r3)
            int r0 = r0.getHeight()
            int r0 = r0 + r3
            r13.mAnimatedBlackTop = r0
            goto L_0x00eb
        L_0x00e8:
            r13.runLastItemAddDeleteAnim(r0)
        L_0x00eb:
            r13.invalidate()
        L_0x00ee:
            int r0 = r13.mBlackTop
            if (r0 != r4) goto L_0x00fa
            int r3 = r13.mAnimatedBlackTop
            if (r3 != r0) goto L_0x00fa
            boolean r0 = r13.mIsSetOnlyAddAnim
            if (r0 == 0) goto L_0x0067
        L_0x00fa:
            int r0 = r13.mAnimatedBlackTop
            float r9 = (float) r0
            float r10 = (float) r14
            int r0 = r13.getBottom()
            float r11 = (float) r0
            android.graphics.Paint r12 = r13.mRectPaint
            r8 = 0
            r7.drawRect(r8, r9, r10, r11, r12)
            boolean r0 = r13.mDrawLastRoundedCorner
            if (r0 == 0) goto L_0x0067
            r12 = r7
            androidx.appcompat.util.SeslSubheaderRoundedCorner r7 = r13.mRoundedCorner
            int r9 = r13.mAnimatedBlackTop
            int r10 = r14 - r2
            int r11 = r13.getBottom()
            r8 = r1
            r7.drawRoundedCorner(r8, r9, r10, r11, r12)
            r7 = r12
        L_0x011d:
            int r0 = r13.mBlackTop
            r13.mLastItemAnimTop = r0
            boolean r0 = r13.mDrawHorizontalPadding
            if (r0 == 0) goto L_0x0141
            int r0 = r13.getHeight()
            if (r8 <= 0) goto L_0x0134
            float r10 = (float) r8
            float r11 = (float) r0
            android.graphics.Paint r12 = r13.mRectPaint
            r8 = 0
            r9 = 0
            r7.drawRect(r8, r9, r10, r11, r12)
        L_0x0134:
            if (r2 <= 0) goto L_0x0141
            int r1 = r14 - r2
            float r8 = (float) r1
            float r10 = (float) r14
            float r11 = (float) r0
            android.graphics.Paint r12 = r13.mRectPaint
            r9 = 0
            r7.drawRect(r8, r9, r10, r11, r12)
        L_0x0141:
            androidx.core.util.SeslFadingEdgeHelper r14 = r13.mFadingEdgeHelper
            boolean r14 = r14.isFadingEdgeEnabled()
            if (r14 == 0) goto L_0x014c
            r13.seslRenderFadingEffect(r7)
        L_0x014c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.dispatchDraw(android.graphics.Canvas):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:269:0x03cf, code lost:
        if (r5 > r3) goto L_0x03c0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:174:0x0228  */
    /* JADX WARNING: Removed duplicated region for block: B:206:0x02c7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean dispatchHoverEvent(android.view.MotionEvent r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            androidx.recyclerview.widget.RecyclerView$Adapter r2 = r0.mAdapter
            java.lang.String r3 = "SeslRecyclerView"
            if (r2 != 0) goto L_0x0014
            java.lang.String r2 = "No adapter attached; skipping hover scroll"
            android.util.Log.d(r3, r2)
            boolean r0 = super.dispatchHoverEvent(r19)
            return r0
        L_0x0014:
            int r2 = r1.getAction()
            r4 = 0
            int r5 = r1.getToolType(r4)
            r6 = 9
            r7 = 7
            r8 = 10
            r9 = 2
            r10 = 1
            if (r2 == r7) goto L_0x0028
            if (r2 != r6) goto L_0x002d
        L_0x0028:
            if (r5 != r9) goto L_0x002d
            r0.mIsPenHovered = r10
            goto L_0x0031
        L_0x002d:
            if (r2 != r8) goto L_0x0031
            r0.mIsPenHovered = r4
        L_0x0031:
            boolean r11 = androidx.reflect.widget.SeslTextViewReflector.semIsTextViewHovered()
            r0.mNewTextViewHoverState = r11
            r12 = 32
            if (r11 != 0) goto L_0x0052
            boolean r11 = r0.mOldTextViewHoverState
            if (r11 == 0) goto L_0x0052
            boolean r11 = r0.mIsPenDragBlockEnabled
            if (r11 == 0) goto L_0x0052
            int r11 = r1.getButtonState()
            if (r11 == r12) goto L_0x004f
            int r11 = r1.getButtonState()
            if (r11 != r9) goto L_0x0052
        L_0x004f:
            r0.mIsNeedPenSelectIconSet = r10
            goto L_0x0054
        L_0x0052:
            r0.mIsNeedPenSelectIconSet = r4
        L_0x0054:
            boolean r11 = r0.mNewTextViewHoverState
            r0.mOldTextViewHoverState = r11
            if (r2 == r6) goto L_0x00be
            boolean r11 = r0.mHoverScrollStateChanged
            if (r11 == 0) goto L_0x005f
            goto L_0x00be
        L_0x005f:
            if (r2 != r7) goto L_0x00ad
            boolean r3 = r0.mIsPenDragBlockEnabled
            if (r3 == 0) goto L_0x007f
            boolean r3 = r0.mIsPenSelectPointerSetted
            if (r3 != 0) goto L_0x007f
            boolean r3 = r0.mIsPenSelectionEnabled
            if (r3 == 0) goto L_0x007f
            int r3 = r1.getToolType(r4)
            if (r3 != r9) goto L_0x007f
            int r3 = r1.getButtonState()
            if (r3 == r12) goto L_0x0083
            int r3 = r1.getButtonState()
            if (r3 == r9) goto L_0x0083
        L_0x007f:
            boolean r3 = r0.mIsNeedPenSelectIconSet
            if (r3 == 0) goto L_0x008e
        L_0x0083:
            int r3 = androidx.reflect.view.SeslPointerIconReflector.getField_SEM_TYPE_STYLUS_PEN_SELECT()
            r0.showPointerIcon(r1, r3)
            r0.mIsPenSelectPointerSetted = r10
            goto L_0x0130
        L_0x008e:
            boolean r3 = r0.mIsPenDragBlockEnabled
            if (r3 == 0) goto L_0x0130
            boolean r3 = r0.mIsPenSelectPointerSetted
            if (r3 == 0) goto L_0x0130
            int r3 = r1.getButtonState()
            if (r3 == r12) goto L_0x0130
            int r3 = r1.getButtonState()
            if (r3 == r9) goto L_0x0130
            int r3 = androidx.reflect.view.SeslPointerIconReflector.getField_SEM_TYPE_STYLUS_DEFAULT()
            r0.showPointerIcon(r1, r3)
            r0.mIsPenSelectPointerSetted = r4
            goto L_0x0130
        L_0x00ad:
            if (r2 != r8) goto L_0x0130
            boolean r3 = r0.mIsPenSelectPointerSetted
            if (r3 == 0) goto L_0x0130
            int r3 = androidx.reflect.view.SeslPointerIconReflector.getField_SEM_TYPE_STYLUS_DEFAULT()
            r0.showPointerIcon(r1, r3)
            r0.mIsPenSelectPointerSetted = r4
            goto L_0x0130
        L_0x00be:
            r0.mNeedsHoverScroll = r10
            r0.mHoverScrollStateChanged = r4
            boolean r11 = r0.mHasNestedScrollRange
            if (r11 == 0) goto L_0x00c9
            r0.adjustNestedScrollRange()
        L_0x00c9:
            boolean r11 = r0.mHoverScrollEnable
            if (r11 != 0) goto L_0x00cf
            r0.mNeedsHoverScroll = r4
        L_0x00cf:
            boolean r11 = r0.mNeedsHoverScroll
            if (r11 == 0) goto L_0x0127
            if (r5 != r9) goto L_0x0127
            java.lang.String r11 = androidx.reflect.provider.SeslSettingsReflector$SeslSystemReflector.getField_SEM_PEN_HOVERING()
            android.content.Context r13 = r0.mContext
            android.content.ContentResolver r13 = r13.getContentResolver()
            int r11 = android.provider.Settings.System.getInt(r13, r11, r4)
            if (r11 != r10) goto L_0x00e7
            r11 = r10
            goto L_0x00e8
        L_0x00e7:
            r11 = r4
        L_0x00e8:
            android.content.Context r13 = r0.mContext     // Catch:{ SettingNotFoundException -> 0x00f8 }
            android.content.ContentResolver r13 = r13.getContentResolver()     // Catch:{ SettingNotFoundException -> 0x00f8 }
            java.lang.String r14 = "car_mode_on"
            int r3 = android.provider.Settings.System.getInt(r13, r14)     // Catch:{ SettingNotFoundException -> 0x00f8 }
            if (r3 != r10) goto L_0x00fd
            r3 = r10
            goto L_0x00fe
        L_0x00f8:
            java.lang.String r13 = "dispatchHoverEvent car_mode_on SettingNotFoundException"
            android.util.Log.i(r3, r13)
        L_0x00fd:
            r3 = r4
        L_0x00fe:
            if (r11 == 0) goto L_0x0102
            if (r3 == 0) goto L_0x0104
        L_0x0102:
            r0.mNeedsHoverScroll = r4
        L_0x0104:
            if (r11 == 0) goto L_0x0127
            boolean r3 = r0.mIsPenDragBlockEnabled
            if (r3 == 0) goto L_0x0127
            boolean r3 = r0.mIsPenSelectPointerSetted
            if (r3 != 0) goto L_0x0127
            boolean r3 = r0.mIsPenSelectionEnabled
            if (r3 == 0) goto L_0x0127
            int r3 = r1.getButtonState()
            if (r3 == r12) goto L_0x011e
            int r3 = r1.getButtonState()
            if (r3 != r9) goto L_0x0127
        L_0x011e:
            int r3 = androidx.reflect.view.SeslPointerIconReflector.getField_SEM_TYPE_STYLUS_PEN_SELECT()
            r0.showPointerIcon(r1, r3)
            r0.mIsPenSelectPointerSetted = r10
        L_0x0127:
            boolean r3 = r0.mNeedsHoverScroll
            if (r3 == 0) goto L_0x0130
            r3 = 3
            if (r5 != r3) goto L_0x0130
            r0.mNeedsHoverScroll = r4
        L_0x0130:
            boolean r3 = r0.mNeedsHoverScroll
            if (r3 != 0) goto L_0x0139
            boolean r0 = super.dispatchHoverEvent(r19)
            return r0
        L_0x0139:
            androidx.recyclerview.widget.RecyclerView$LayoutManager r3 = r0.mLayout
            boolean r3 = r3.canScrollHorizontally()
            if (r3 == 0) goto L_0x0147
            float r5 = r1.getY()
        L_0x0145:
            int r5 = (int) r5
            goto L_0x014c
        L_0x0147:
            float r5 = r1.getX()
            goto L_0x0145
        L_0x014c:
            if (r3 == 0) goto L_0x0154
            float r11 = r1.getX()
        L_0x0152:
            int r11 = (int) r11
            goto L_0x0159
        L_0x0154:
            float r11 = r1.getY()
            goto L_0x0152
        L_0x0159:
            boolean r13 = r0.mIsEnabledPaddingInHoverScroll
            if (r13 == 0) goto L_0x016b
            android.graphics.Rect r13 = r0.mListPadding
            int r13 = r13.top
            int r14 = r0.getHeight()
            android.graphics.Rect r15 = r0.mListPadding
            int r15 = r15.bottom
            int r14 = r14 - r15
            goto L_0x0178
        L_0x016b:
            int r13 = r0.mExtraPaddingInTopHoverArea
            if (r3 == 0) goto L_0x0174
            int r14 = r0.getWidth()
            goto L_0x0178
        L_0x0174:
            int r14 = r0.getHeight()
        L_0x0178:
            int r15 = r0.mRemainNestedScrollRange
            if (r15 <= 0) goto L_0x017e
            r15 = r10
            goto L_0x0182
        L_0x017e:
            boolean r15 = r0.canScrollDown()
        L_0x0182:
            boolean r16 = r0.canScrollUp()
            int r10 = r1.getToolType(r4)
            if (r10 != r9) goto L_0x018e
            r10 = 1
            goto L_0x018f
        L_0x018e:
            r10 = r4
        L_0x018f:
            int r4 = r0.mHoverTopAreaHeight
            int r4 = r4 + r13
            int r8 = r0.mHoverDefaultTopAreaHeight
            int r4 = r4 + r8
            r6 = 0
            if (r11 <= r4) goto L_0x01a5
            int r4 = r0.mHoverBottomAreaHeight
            int r4 = r14 - r4
            int r8 = r0.mHoverDefaultBottomAreaHeight
            int r4 = r4 - r8
            int r8 = r0.mRemainNestedScrollRange
            int r4 = r4 - r8
            if (r11 < r4) goto L_0x0380
        L_0x01a5:
            if (r5 <= 0) goto L_0x0380
            if (r3 == 0) goto L_0x01ae
            int r4 = r0.getBottom()
            goto L_0x01b2
        L_0x01ae:
            int r4 = r0.getRight()
        L_0x01b2:
            if (r5 > r4) goto L_0x0380
            if (r16 != 0) goto L_0x01b8
            if (r15 == 0) goto L_0x0380
        L_0x01b8:
            if (r11 < r13) goto L_0x01c8
            int r4 = r0.mHoverTopAreaHeight
            int r4 = r4 + r13
            int r8 = r0.mHoverDefaultTopAreaHeight
            int r4 = r4 + r8
            if (r11 > r4) goto L_0x01c8
            if (r16 != 0) goto L_0x01c8
            boolean r4 = r0.mIsHoverOverscrolled
            if (r4 != 0) goto L_0x0380
        L_0x01c8:
            int r4 = r0.mHoverBottomAreaHeight
            int r4 = r14 - r4
            int r8 = r0.mHoverDefaultBottomAreaHeight
            int r4 = r4 - r8
            int r8 = r0.mRemainNestedScrollRange
            int r4 = r4 - r8
            if (r11 < r4) goto L_0x01de
            int r4 = r14 - r8
            if (r11 > r4) goto L_0x01de
            if (r15 != 0) goto L_0x01de
            boolean r4 = r0.mIsHoverOverscrolled
            if (r4 != 0) goto L_0x0380
        L_0x01de:
            if (r10 == 0) goto L_0x01ec
            int r4 = r1.getButtonState()
            if (r4 == r12) goto L_0x0380
            int r4 = r1.getButtonState()
            if (r4 == r9) goto L_0x0380
        L_0x01ec:
            if (r10 == 0) goto L_0x0380
            boolean r4 = r0.isLockScreenMode()
            if (r4 == 0) goto L_0x01f6
            goto L_0x0380
        L_0x01f6:
            boolean r4 = r0.mHasNestedScrollRange
            if (r4 == 0) goto L_0x0205
            int r4 = r0.mRemainNestedScrollRange
            if (r4 <= 0) goto L_0x0205
            int r5 = r0.mNestedScrollRange
            if (r4 == r5) goto L_0x0205
            r0.adjustNestedScrollRange()
        L_0x0205:
            boolean r4 = r0.mHoverAreaEnter
            if (r4 != 0) goto L_0x020f
            long r4 = java.lang.System.currentTimeMillis()
            r0.mHoverScrollStartTime = r4
        L_0x020f:
            int r4 = r0.mRemainNestedScrollRange
            if (r4 == 0) goto L_0x0224
            android.graphics.Rect r4 = new android.graphics.Rect
            r4.<init>()
            r0.getLocalVisibleRect(r4)
            int r4 = r4.bottom
            if (r14 > r4) goto L_0x0220
            goto L_0x0224
        L_0x0220:
            int r4 = r0.mRemainNestedScrollRange
        L_0x0222:
            r5 = 7
            goto L_0x0226
        L_0x0224:
            r4 = 0
            goto L_0x0222
        L_0x0226:
            if (r2 == r5) goto L_0x02c7
            r8 = 9
            if (r2 == r8) goto L_0x026d
            r5 = 10
            if (r2 == r5) goto L_0x0234
        L_0x0230:
            r17 = 1
            goto L_0x037f
        L_0x0234:
            android.os.Handler r2 = r0.mHoverHandler
            r3 = 0
            boolean r2 = r2.hasMessages(r3)
            if (r2 == 0) goto L_0x0242
            android.os.Handler r2 = r0.mHoverHandler
            r2.removeMessages(r3)
        L_0x0242:
            int r2 = r0.mScrollState
            r4 = 1
            if (r2 != r4) goto L_0x024a
            r0.setScrollState(r3)
        L_0x024a:
            int r2 = androidx.reflect.view.SeslPointerIconReflector.getField_SEM_TYPE_STYLUS_DEFAULT()
            r0.showPointerIcon(r1, r2)
            r0.mHoverRecognitionStartTime = r6
            r0.mHoverScrollStartTime = r6
            r0.mIsHoverOverscrolled = r3
            r0.mHoverAreaEnter = r3
            r0.mIsSendHoverScrollState = r3
            int r2 = r0.mHoverScrollStateForListener
            if (r2 == 0) goto L_0x0268
            r0.mHoverScrollStateForListener = r3
            androidx.recyclerview.widget.RecyclerView$OnScrollListener r2 = r0.mScrollListener
            if (r2 == 0) goto L_0x0268
            r2.onScrollStateChanged(r0, r3)
        L_0x0268:
            boolean r0 = super.dispatchHoverEvent(r19)
            return r0
        L_0x026d:
            r2 = 1
            r0.mHoverAreaEnter = r2
            if (r11 < r13) goto L_0x0298
            int r2 = r0.mHoverTopAreaHeight
            int r13 = r13 + r2
            int r2 = r0.mHoverDefaultTopAreaHeight
            int r13 = r13 + r2
            if (r11 > r13) goto L_0x0298
            android.os.Handler r2 = r0.mHoverHandler
            r4 = 0
            boolean r2 = r2.hasMessages(r4)
            if (r2 != 0) goto L_0x0230
            long r5 = java.lang.System.currentTimeMillis()
            r0.mHoverRecognitionStartTime = r5
            int r2 = r0.getRotatedArrowPointerIcon(r4, r3)
            r0.showPointerIcon(r1, r2)
            r0.mHoverScrollDirection = r9
            android.os.Handler r0 = r0.mHoverHandler
            r0.sendEmptyMessage(r4)
            goto L_0x0230
        L_0x0298:
            int r2 = r0.mHoverBottomAreaHeight
            int r2 = r14 - r2
            int r5 = r0.mHoverDefaultBottomAreaHeight
            int r2 = r2 - r5
            int r2 = r2 - r4
            if (r11 < r2) goto L_0x0230
            int r14 = r14 - r4
            if (r11 > r14) goto L_0x0230
            android.os.Handler r2 = r0.mHoverHandler
            r4 = 0
            boolean r2 = r2.hasMessages(r4)
            if (r2 != 0) goto L_0x0230
            long r5 = java.lang.System.currentTimeMillis()
            r0.mHoverRecognitionStartTime = r5
            r2 = 1
            int r3 = r0.getRotatedArrowPointerIcon(r2, r3)
            r0.showPointerIcon(r1, r3)
            r0.mHoverScrollDirection = r2
            android.os.Handler r0 = r0.mHoverHandler
            r0.sendEmptyMessage(r4)
            r17 = r2
            goto L_0x037f
        L_0x02c7:
            r2 = 1
            boolean r5 = r0.mHoverAreaEnter
            if (r5 != 0) goto L_0x02d8
            r0.mHoverAreaEnter = r2
            r5 = 10
            r1.setAction(r5)
            boolean r0 = super.dispatchHoverEvent(r19)
            return r0
        L_0x02d8:
            if (r11 < r13) goto L_0x030a
            int r2 = r0.mHoverTopAreaHeight
            int r13 = r13 + r2
            int r2 = r0.mHoverDefaultTopAreaHeight
            int r13 = r13 + r2
            if (r11 > r13) goto L_0x030a
            android.os.Handler r2 = r0.mHoverHandler
            r4 = 0
            boolean r2 = r2.hasMessages(r4)
            if (r2 != 0) goto L_0x0230
            long r5 = java.lang.System.currentTimeMillis()
            r0.mHoverRecognitionStartTime = r5
            boolean r2 = r0.mIsHoverOverscrolled
            if (r2 == 0) goto L_0x02fa
            int r2 = r0.mHoverScrollDirection
            r5 = 1
            if (r2 != r5) goto L_0x0301
        L_0x02fa:
            int r2 = r0.getRotatedArrowPointerIcon(r4, r3)
            r0.showPointerIcon(r1, r2)
        L_0x0301:
            r0.mHoverScrollDirection = r9
            android.os.Handler r0 = r0.mHoverHandler
            r0.sendEmptyMessage(r4)
            goto L_0x0230
        L_0x030a:
            int r2 = r0.mHoverBottomAreaHeight
            int r2 = r14 - r2
            int r5 = r0.mHoverDefaultBottomAreaHeight
            int r2 = r2 - r5
            int r2 = r2 - r4
            if (r11 < r2) goto L_0x0322
            int r14 = r14 - r4
            if (r11 > r14) goto L_0x0322
            androidx.core.widget.SeslGoToTopController r2 = r0.mGoToTopController
            if (r2 == 0) goto L_0x0324
            boolean r2 = r2.dispatchHoverEvent(r1)
            if (r2 != 0) goto L_0x0322
            goto L_0x0324
        L_0x0322:
            r4 = 0
            goto L_0x0357
        L_0x0324:
            android.os.Handler r2 = r0.mHoverHandler
            r4 = 0
            boolean r2 = r2.hasMessages(r4)
            if (r2 != 0) goto L_0x0230
            long r4 = java.lang.System.currentTimeMillis()
            r0.mHoverRecognitionStartTime = r4
            boolean r2 = r0.mIsHoverOverscrolled
            if (r2 == 0) goto L_0x033b
            int r2 = r0.mHoverScrollDirection
            if (r2 != r9) goto L_0x033d
        L_0x033b:
            r2 = 1
            goto L_0x033f
        L_0x033d:
            r2 = 1
            goto L_0x0346
        L_0x033f:
            int r3 = r0.getRotatedArrowPointerIcon(r2, r3)
            r0.showPointerIcon(r1, r3)
        L_0x0346:
            r0.mHoverScrollDirection = r2
            android.os.Handler r2 = r0.mHoverHandler
            r4 = 0
            r2.sendEmptyMessage(r4)
            androidx.core.widget.SeslGoToTopController r0 = r0.mGoToTopController
            if (r0 == 0) goto L_0x0230
            r0.dispatchHoverEvent(r1)
            goto L_0x0230
        L_0x0357:
            android.os.Handler r2 = r0.mHoverHandler
            boolean r2 = r2.hasMessages(r4)
            if (r2 == 0) goto L_0x036c
            android.os.Handler r2 = r0.mHoverHandler
            r2.removeMessages(r4)
            int r2 = r0.mScrollState
            r5 = 1
            if (r2 != r5) goto L_0x036c
            r0.setScrollState(r4)
        L_0x036c:
            int r2 = androidx.reflect.view.SeslPointerIconReflector.getField_SEM_TYPE_STYLUS_DEFAULT()
            r0.showPointerIcon(r1, r2)
            r0.mHoverRecognitionStartTime = r6
            r0.mHoverScrollStartTime = r6
            r0.mIsHoverOverscrolled = r4
            r0.mHoverAreaEnter = r4
            r0.mIsSendHoverScrollState = r4
            goto L_0x0230
        L_0x037f:
            return r17
        L_0x0380:
            boolean r4 = r0.mHasNestedScrollRange
            if (r4 == 0) goto L_0x038f
            int r4 = r0.mRemainNestedScrollRange
            if (r4 <= 0) goto L_0x038f
            int r8 = r0.mNestedScrollRange
            if (r4 == r8) goto L_0x038f
            r0.adjustNestedScrollRange()
        L_0x038f:
            android.os.Handler r4 = r0.mHoverHandler
            r8 = 0
            boolean r4 = r4.hasMessages(r8)
            if (r4 == 0) goto L_0x03ac
            android.os.Handler r4 = r0.mHoverHandler
            r4.removeMessages(r8)
            int r4 = androidx.reflect.view.SeslPointerIconReflector.getField_SEM_TYPE_STYLUS_DEFAULT()
            r0.showPointerIcon(r1, r4)
            int r4 = r0.mScrollState
            r9 = 1
            if (r4 != r9) goto L_0x03ac
            r0.setScrollState(r8)
        L_0x03ac:
            int r4 = r0.mHoverTopAreaHeight
            int r13 = r13 + r4
            int r4 = r0.mHoverDefaultTopAreaHeight
            int r13 = r13 + r4
            if (r11 <= r13) goto L_0x03c2
            int r4 = r0.mHoverBottomAreaHeight
            int r14 = r14 - r4
            int r4 = r0.mHoverDefaultBottomAreaHeight
            int r14 = r14 - r4
            int r4 = r0.mRemainNestedScrollRange
            int r14 = r14 - r4
            if (r11 < r14) goto L_0x03c0
            goto L_0x03c2
        L_0x03c0:
            r4 = 0
            goto L_0x03d2
        L_0x03c2:
            if (r5 <= 0) goto L_0x03c0
            if (r3 == 0) goto L_0x03cb
            int r3 = r0.getBottom()
            goto L_0x03cf
        L_0x03cb:
            int r3 = r0.getRight()
        L_0x03cf:
            if (r5 <= r3) goto L_0x03d4
            goto L_0x03c0
        L_0x03d2:
            r0.mIsHoverOverscrolled = r4
        L_0x03d4:
            boolean r3 = r0.mHoverAreaEnter
            if (r3 != 0) goto L_0x03de
            long r3 = r0.mHoverScrollStartTime
            int r3 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r3 == 0) goto L_0x03e5
        L_0x03de:
            int r3 = androidx.reflect.view.SeslPointerIconReflector.getField_SEM_TYPE_STYLUS_DEFAULT()
            r0.showPointerIcon(r1, r3)
        L_0x03e5:
            r0.mHoverRecognitionStartTime = r6
            r0.mHoverScrollStartTime = r6
            r4 = 0
            r0.mHoverAreaEnter = r4
            r0.mIsSendHoverScrollState = r4
            r5 = 10
            if (r2 != r5) goto L_0x0409
            int r2 = r0.mHoverScrollStateForListener
            if (r2 == 0) goto L_0x0400
            r0.mHoverScrollStateForListener = r4
            androidx.recyclerview.widget.RecyclerView$OnScrollListener r2 = r0.mScrollListener
            if (r2 == 0) goto L_0x0402
            r2.onScrollStateChanged(r0, r4)
            goto L_0x0402
        L_0x0400:
            r0.mIsHoverOverscrolled = r4
        L_0x0402:
            int r2 = androidx.reflect.view.SeslPointerIconReflector.getField_SEM_TYPE_STYLUS_DEFAULT()
            r0.showPointerIcon(r1, r2)
        L_0x0409:
            boolean r0 = super.dispatchHoverEvent(r19)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.dispatchHoverEvent(android.view.MotionEvent):boolean");
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 19 || keyCode == 20) {
            if (keyEvent.getAction() == 0) {
                this.mIsArrowKeyPressed = true;
            }
        } else if (keyCode == 66 && this.mIsRecoilSupported && this.mIsRecoilEnabled) {
            if (keyEvent.getAction() == 0) {
                View focusedChild = getFocusedChild();
                if (focusedChild != null) {
                    this.mItemAnimatorHolder.setPress(focusedChild);
                }
            } else {
                this.mItemAnimatorHolder.setRelease();
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public void dispatchLayout() {
        boolean z;
        if (this.mAdapter == null) {
            Log.w("SeslRecyclerView", "No adapter attached; skipping layout");
        } else if (this.mLayout == null) {
            Log.e("SeslRecyclerView", "No layout manager attached; skipping layout");
        } else {
            this.mState.mIsMeasuring = false;
            if (!this.mLastAutoMeasureSkippedDueToExact || (this.mLastAutoMeasureNonExactMeasuredWidth == getWidth() && this.mLastAutoMeasureNonExactMeasuredHeight == getHeight())) {
                z = false;
            } else {
                z = true;
            }
            this.mLastAutoMeasureNonExactMeasuredWidth = 0;
            this.mLastAutoMeasureNonExactMeasuredHeight = 0;
            this.mLastAutoMeasureSkippedDueToExact = false;
            if (this.mState.mLayoutStep == 1) {
                dispatchLayoutStep1();
                this.mLayout.setExactMeasureSpecsFrom(this);
                dispatchLayoutStep2();
            } else if (this.mAdapterHelper.hasUpdates() || z || this.mLayout.getWidth() != getWidth() || this.mLayout.getHeight() != getHeight()) {
                this.mLayout.setExactMeasureSpecsFrom(this);
                dispatchLayoutStep2();
            } else {
                this.mLayout.setExactMeasureSpecsFrom(this);
            }
            dispatchLayoutStep3();
        }
    }

    public boolean dispatchNestedFling(float f, float f5, boolean z) {
        return getScrollingChildHelper().dispatchNestedFling(f, f5, z);
    }

    public boolean dispatchNestedPreFling(float f, float f5) {
        return getScrollingChildHelper().dispatchNestedPreFling(f, f5);
    }

    public boolean dispatchNestedPreScroll(int i2, int i7, int[] iArr, int[] iArr2) {
        if (this.mGoToTopController != null && canScrollDown()) {
            this.mGoToTopController.invalidate();
        }
        return getScrollingChildHelper().dispatchNestedPreScroll(i2, i7, iArr, iArr2);
    }

    public boolean dispatchNestedScroll(int i2, int i7, int i8, int i10, int[] iArr) {
        return getScrollingChildHelper().dispatchNestedScroll(i2, i7, i8, i10, iArr);
    }

    public void dispatchOnScrollStateChanged(int i2) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.onScrollStateChanged(i2);
        }
        onScrollStateChanged(i2);
        OnScrollListener onScrollListener = this.mScrollListener;
        if (onScrollListener != null) {
            onScrollListener.onScrollStateChanged(this, i2);
        }
        List<OnScrollListener> list = this.mScrollListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mScrollListeners.get(size).onScrollStateChanged(this, i2);
            }
        }
    }

    public void dispatchOnScrolled(int i2, int i7) {
        this.mDispatchScrollCounter++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX - i2, scrollY - i7);
        onScrolled(i2, i7);
        if (this.mIndexTipEnabled && this.mIndexTip != null) {
            if (this.mScrollState != 0 && getHeight() > this.mSeslOverlayFeatureHeight) {
                this.mIndexTip.show(this.mScrollState, i7);
            } else if (this.mIsActionScrollFromMouse && i7 != 0) {
                this.mIndexTip.showWithTimer(this.mScrollState, i7);
            }
            this.mIndexTip.invalidate();
        }
        OnScrollListener onScrollListener = this.mScrollListener;
        if (onScrollListener != null) {
            onScrollListener.onScrolled(this, i2, i7);
        }
        List<OnScrollListener> list = this.mScrollListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mScrollListeners.get(size).onScrolled(this, i2, i7);
            }
        }
        this.mDispatchScrollCounter--;
    }

    public void dispatchPendingImportantForAccessibilityChanges() {
        int i2;
        for (int size = this.mPendingAccessibilityImportanceChange.size() - 1; size >= 0; size--) {
            ViewHolder viewHolder = this.mPendingAccessibilityImportanceChange.get(size);
            if (viewHolder.itemView.getParent() == this && !viewHolder.shouldIgnore() && (i2 = viewHolder.mPendingAccessibilityState) != -1) {
                ViewCompat.setImportantForAccessibility(viewHolder.itemView, i2);
                viewHolder.mPendingAccessibilityState = -1;
            }
        }
        this.mPendingAccessibilityImportanceChange.clear();
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    public void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean z;
        boolean z3;
        int i2;
        int i7;
        boolean z7;
        RecyclerView recyclerView;
        boolean z9;
        if (this.mLayout == null) {
            Log.d("SeslRecyclerView", "No layout manager attached; skipping gototop & multiselection");
            return super.dispatchTouchEvent(motionEvent);
        }
        int actionMasked = motionEvent.getActionMasked();
        int x9 = (int) (motionEvent.getX() + 0.5f);
        int y = (int) (motionEvent.getY() + 0.5f);
        if (motionEvent.getToolType(0) == 2) {
            z = true;
        } else {
            z = false;
        }
        int i8 = Build.VERSION.SDK_INT;
        if ((motionEvent.getButtonState() & 32) != 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (!this.mIsPenButtonPressed) {
            if (!z || !z3) {
                z9 = false;
            } else {
                z9 = true;
            }
            this.mIsPenButtonPressed = z9;
        }
        if (this.mPenDragSelectedItemArray == null) {
            this.mPenDragSelectedItemArray = new ArrayList<>();
        }
        if (this.mIsEnabledPaddingInHoverScroll) {
            i7 = this.mListPadding.top;
            i2 = getHeight() - this.mListPadding.bottom;
        } else {
            i2 = getHeight();
            i7 = 0;
        }
        if (!this.mIsPenSelectionEnabled || SeslTextViewReflector.semIsTextSelectionProgressing()) {
            z7 = false;
        } else {
            z7 = true;
        }
        this.mIsNeedPenSelection = z7;
        int i10 = i8;
        int i11 = i7;
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked != 2) {
                    if (actionMasked != 3) {
                        switch (actionMasked) {
                            case 211:
                                break;
                            case 212:
                                recyclerView = this;
                                break;
                            case 213:
                                break;
                            default:
                                recyclerView = this;
                                break;
                        }
                    } else {
                        SeslGoToTopController seslGoToTopController = this.mGoToTopController;
                        if (seslGoToTopController != null) {
                            seslGoToTopController.onTouchEvent(motionEvent);
                        }
                    }
                }
                if ((z && z3) || this.mIsPenButtonPressed || actionMasked == 213) {
                    recyclerView = this;
                    recyclerView.multiSelection(x9, y, i11, i2, false);
                    return super.dispatchTouchEvent(motionEvent);
                } else if (this.mIsCtrlMultiSelection) {
                    multiSelection(x9, y, i11, i2, false);
                    return true;
                } else {
                    recyclerView = this;
                    if (recyclerView.mIsLongPressMultiSelection) {
                        recyclerView.updateLongPressMultiSelection(x9, y, true);
                        return true;
                    }
                    SeslGoToTopController seslGoToTopController2 = recyclerView.mGoToTopController;
                    if (seslGoToTopController2 != null && seslGoToTopController2.onTouchEvent(motionEvent)) {
                        return true;
                    }
                    return super.dispatchTouchEvent(motionEvent);
                }
            }
            recyclerView = this;
            if (i10 < 34 || !z || !z3) {
                if (recyclerView.mIsCtrlMultiSelection) {
                    recyclerView.multiSelectionEnd(x9, y);
                    recyclerView.mIsCtrlMultiSelection = false;
                    return true;
                } else if (recyclerView.mIsLongPressMultiSelection) {
                    SeslLongPressMultiSelectionListener seslLongPressMultiSelectionListener = recyclerView.mLongPressMultiSelectionListener;
                    if (seslLongPressMultiSelectionListener != null) {
                        seslLongPressMultiSelectionListener.onLongPressMultiSelectionEnded(x9, y);
                    }
                    recyclerView.mIsFirstMultiSelectionMove = true;
                    recyclerView.mPenDragSelectedViewPosition = -1;
                    recyclerView.mPenDragStartX = 0;
                    recyclerView.mPenDragStartY = 0;
                    recyclerView.mPenDragEndX = 0;
                    recyclerView.mPenDragEndY = 0;
                    recyclerView.mPenDragBlockLeft = 0;
                    recyclerView.mPenDragBlockTop = 0;
                    recyclerView.mPenDragBlockRight = 0;
                    recyclerView.mPenDragBlockBottom = 0;
                    recyclerView.mPenDragSelectedItemArray.clear();
                    recyclerView.mPenTrackedChild = null;
                    recyclerView.mPenDistanceFromTrackedChildTop = 0;
                    if (recyclerView.mHoverHandler.hasMessages(0)) {
                        recyclerView.mHoverHandler.removeMessages(0);
                        if (recyclerView.mScrollState == 1) {
                            recyclerView.setScrollState(0);
                        }
                    }
                    recyclerView.mIsHoverOverscrolled = false;
                    recyclerView.invalidate();
                    recyclerView.mIsLongPressMultiSelection = false;
                }
            }
            recyclerView.mIsPenButtonPressed = false;
            SeslGoToTopController seslGoToTopController3 = recyclerView.mGoToTopController;
            if (seslGoToTopController3 != null && seslGoToTopController3.onTouchEvent(motionEvent)) {
                return true;
            }
            if (recyclerView.mIsPenPressed) {
                recyclerView.multiSelectionEnd(x9, y);
                return true;
            }
            recyclerView.multiSelectionEnd(x9, y);
            if (recyclerView.mIsRecoilSupported && recyclerView.mIsRecoilEnabled) {
                recyclerView.mItemBackgroundHolder.setRelease();
                recyclerView.mItemAnimatorHolder.setRelease();
            }
            return super.dispatchTouchEvent(motionEvent);
        }
        recyclerView = this;
        boolean z10 = z3;
        int i12 = i2;
        if ((!z || !z10) && actionMasked != 211) {
            SeslGoToTopController seslGoToTopController4 = recyclerView.mGoToTopController;
            if (seslGoToTopController4 != null && seslGoToTopController4.onTouchEvent(motionEvent)) {
                return true;
            }
            if (!recyclerView.mIsCtrlKeyPressed || motionEvent.getToolType(0) != 3) {
                if (recyclerView.mIsLongPressMultiSelection) {
                    recyclerView.mIsLongPressMultiSelection = false;
                }
                return super.dispatchTouchEvent(motionEvent);
            }
            recyclerView.mIsCtrlMultiSelection = true;
            recyclerView.mIsNeedPenSelection = true;
            recyclerView.multiSelection(x9, y, i11, i12, false);
            return true;
        }
        if (recyclerView.mPenDragSelectedItemArray == null) {
            recyclerView.mPenDragSelectedItemArray = new ArrayList<>();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void draw(Canvas canvas) {
        boolean z;
        int i2;
        boolean z3;
        int i7;
        boolean z7;
        boolean z9;
        int i8;
        super.draw(canvas);
        int size = this.mItemDecorations.size();
        int i10 = 0;
        for (int i11 = 0; i11 < size; i11++) {
            this.mItemDecorations.get(i11).onDrawOver(canvas, this, this.mState);
        }
        EdgeEffect edgeEffect = this.mLeftGlow;
        boolean z10 = true;
        if (edgeEffect == null || edgeEffect.isFinished()) {
            z = false;
        } else {
            int save = canvas.save();
            if (this.mClipToPadding) {
                i8 = getPaddingBottom();
            } else {
                i8 = 0;
            }
            canvas.rotate(270.0f);
            canvas.translate((float) ((-getHeight()) + i8), 0.0f);
            EdgeEffect edgeEffect2 = this.mLeftGlow;
            if (edgeEffect2 == null || !edgeEffect2.draw(canvas)) {
                z = false;
            } else {
                z = true;
            }
            canvas.restoreToCount(save);
        }
        EdgeEffect edgeEffect3 = this.mTopGlow;
        if (edgeEffect3 != null && !edgeEffect3.isFinished()) {
            int save2 = canvas.save();
            if (this.mClipToPadding) {
                canvas.translate((float) getPaddingLeft(), (float) getPaddingTop());
            }
            EdgeEffect edgeEffect4 = this.mTopGlow;
            if (edgeEffect4 == null || !edgeEffect4.draw(canvas)) {
                z9 = false;
            } else {
                z9 = true;
            }
            z |= z9;
            canvas.restoreToCount(save2);
        }
        EdgeEffect edgeEffect5 = this.mRightGlow;
        if (edgeEffect5 != null && !edgeEffect5.isFinished()) {
            int save3 = canvas.save();
            int width = getWidth();
            if (this.mClipToPadding) {
                i7 = getPaddingTop();
            } else {
                i7 = 0;
            }
            canvas.rotate(90.0f);
            canvas.translate((float) i7, (float) (-width));
            EdgeEffect edgeEffect6 = this.mRightGlow;
            if (edgeEffect6 == null || !edgeEffect6.draw(canvas)) {
                z7 = false;
            } else {
                z7 = true;
            }
            z |= z7;
            canvas.restoreToCount(save3);
        }
        EdgeEffect edgeEffect7 = this.mBottomGlow;
        if (edgeEffect7 != null && !edgeEffect7.isFinished()) {
            int save4 = canvas.save();
            canvas.rotate(180.0f);
            if (this.mClipToPadding) {
                canvas.translate((float) (getPaddingRight() + (-getWidth())), (float) (getPaddingBottom() + (-getHeight())));
            } else {
                canvas.translate((float) (-getWidth()), (float) (-getHeight()));
            }
            EdgeEffect edgeEffect8 = this.mBottomGlow;
            if (edgeEffect8 == null || !edgeEffect8.draw(canvas)) {
                z3 = false;
            } else {
                z3 = true;
            }
            z |= z3;
            canvas.restoreToCount(save4);
        }
        if (z || this.mItemAnimator == null || this.mItemDecorations.size() <= 0 || !this.mItemAnimator.isRunning()) {
            z10 = z;
        }
        if (z10) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        SeslGoToTopController seslGoToTopController = this.mGoToTopController;
        if (seslGoToTopController != null) {
            seslGoToTopController.draw();
        }
        if (this.mIsPenDragBlockEnabled && !this.mIsLongPressMultiSelection && this.mLayout != null) {
            if (this.mPenDragBlockLeft != 0 || this.mPenDragBlockTop != 0) {
                int findFirstVisibleItemPosition = findFirstVisibleItemPosition();
                int findLastVisibleItemPosition = findLastVisibleItemPosition();
                int i12 = this.mPenTrackedChildPosition;
                if (i12 >= findFirstVisibleItemPosition && i12 <= findLastVisibleItemPosition) {
                    View findViewByPosition = this.mLayout.findViewByPosition(i12);
                    this.mPenTrackedChild = findViewByPosition;
                    if (findViewByPosition != null) {
                        i10 = findViewByPosition.getTop();
                    }
                    this.mPenDragStartY = i10 + this.mPenDistanceFromTrackedChildTop;
                }
                int i13 = this.mPenDragStartY;
                int i14 = this.mPenDragEndY;
                if (i13 < i14) {
                    i2 = i13;
                } else {
                    i2 = i14;
                }
                this.mPenDragBlockTop = i2;
                if (i14 > i13) {
                    i13 = i14;
                }
                this.mPenDragBlockBottom = i13;
                this.mPenDragBlockRect.set(this.mPenDragBlockLeft, i2, this.mPenDragBlockRight, i13);
                this.mPenDragBlockImage.setBounds(this.mPenDragBlockRect);
                this.mPenDragBlockImage.draw(canvas);
            }
        }
    }

    public boolean drawChild(Canvas canvas, View view, long j2) {
        return super.drawChild(canvas, view, j2);
    }

    public void ensureBottomGlow() {
        if (this.mBottomGlow == null) {
            EdgeEffect createEdgeEffect = this.mEdgeEffectFactory.createEdgeEffect(this, 3);
            this.mBottomGlow = createEdgeEffect;
            if (this.mClipToPadding) {
                createEdgeEffect.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
            } else {
                createEdgeEffect.setSize(getMeasuredWidth(), getMeasuredHeight());
            }
        }
    }

    public void ensureLeftGlow() {
        if (this.mLeftGlow == null) {
            EdgeEffect createEdgeEffect = this.mEdgeEffectFactory.createEdgeEffect(this, 0);
            this.mLeftGlow = createEdgeEffect;
            if (this.mClipToPadding) {
                createEdgeEffect.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
            } else {
                createEdgeEffect.setSize(getMeasuredHeight(), getMeasuredWidth());
            }
        }
    }

    public void ensureRightGlow() {
        if (this.mRightGlow == null) {
            EdgeEffect createEdgeEffect = this.mEdgeEffectFactory.createEdgeEffect(this, 2);
            this.mRightGlow = createEdgeEffect;
            if (this.mClipToPadding) {
                createEdgeEffect.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
            } else {
                createEdgeEffect.setSize(getMeasuredHeight(), getMeasuredWidth());
            }
        }
    }

    public void ensureTopGlow() {
        if (this.mTopGlow == null) {
            EdgeEffect createEdgeEffect = this.mEdgeEffectFactory.createEdgeEffect(this, 1);
            this.mTopGlow = createEdgeEffect;
            if (this.mClipToPadding) {
                createEdgeEffect.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
            } else {
                createEdgeEffect.setSize(getMeasuredWidth(), getMeasuredHeight());
            }
        }
    }

    public String exceptionLabel() {
        return " " + super.toString() + ", adapter:" + this.mAdapter + ", layout:" + this.mLayout + ", context:" + getContext();
    }

    public final void fillRemainingScrollValues(State state) {
        if (getScrollState() == 2) {
            OverScroller overScroller = this.mViewFlinger.mOverScroller;
            state.mRemainingScrollHorizontal = overScroller.getFinalX() - overScroller.getCurrX();
            state.mRemainingScrollVertical = overScroller.getFinalY() - overScroller.getCurrY();
            return;
        }
        state.mRemainingScrollHorizontal = 0;
        state.mRemainingScrollVertical = 0;
    }

    public View findChildViewUnder(float f, float f5) {
        for (int childCount = this.mChildHelper.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.mChildHelper.getChildAt(childCount);
            float translationX = childAt.getTranslationX();
            float translationY = childAt.getTranslationY();
            if (f >= ((float) childAt.getLeft()) + translationX && f <= ((float) childAt.getRight()) + translationX && f5 >= ((float) childAt.getTop()) + translationY && f5 <= ((float) childAt.getBottom()) + translationY) {
                return childAt;
            }
        }
        return null;
    }

    public View findClickableChildUnder(MotionEvent motionEvent) {
        View findChildViewUnder = findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        if (findChildViewUnder == null || !findChildViewUnder.isEnabled()) {
            return null;
        }
        View findClickableChildUnder = findClickableChildUnder(findChildViewUnder, motionEvent.getX(), motionEvent.getY());
        if (!(findClickableChildUnder == null || findClickableChildUnder == findChildViewUnder)) {
            int height = findChildViewUnder.getHeight() * findChildViewUnder.getWidth();
            if (((double) (findClickableChildUnder.getHeight() * findClickableChildUnder.getWidth())) < ((double) height) * 0.5d) {
                return null;
            }
        }
        return findClickableChildUnder;
    }

    public View findContainingItemView(View view) {
        ViewParent parent = view.getParent();
        while (parent != null && parent != this && (parent instanceof View)) {
            view = (View) parent;
            parent = view.getParent();
        }
        if (parent == this) {
            return view;
        }
        return null;
    }

    public ViewHolder findContainingViewHolder(View view) {
        View findContainingItemView = findContainingItemView(view);
        if (findContainingItemView == null) {
            return null;
        }
        return getChildViewHolder(findContainingItemView);
    }

    public int findFirstVisibleItemPosition() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            return ((StaggeredGridLayoutManager) layoutManager).findFirstVisibleItemPositions((int[]) null)[0];
        }
        return -1;
    }

    public int findLastVisibleItemPosition() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            return ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions((int[]) null)[0];
        }
        return -1;
    }

    public ViewHolder findViewHolderForAdapterPosition(int i2) {
        ViewHolder viewHolder = null;
        if (this.mDataSetHasChangedAfterLayout) {
            return null;
        }
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i7 = 0; i7 < unfilteredChildCount; i7++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i7));
            if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && getAdapterPositionInRecyclerView(childViewHolderInt) == i2) {
                if (!this.mChildHelper.isHidden(childViewHolderInt.itemView)) {
                    return childViewHolderInt;
                }
                viewHolder = childViewHolderInt;
            }
        }
        return viewHolder;
    }

    public ViewHolder findViewHolderForItemId(long j2) {
        Adapter adapter = this.mAdapter;
        ViewHolder viewHolder = null;
        if (adapter != null && adapter.hasStableIds()) {
            int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
            for (int i2 = 0; i2 < unfilteredChildCount; i2++) {
                ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i2));
                if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && childViewHolderInt.getItemId() == j2) {
                    if (!this.mChildHelper.isHidden(childViewHolderInt.itemView)) {
                        return childViewHolderInt;
                    }
                    viewHolder = childViewHolderInt;
                }
            }
        }
        return viewHolder;
    }

    public ViewHolder findViewHolderForLayoutPosition(int i2) {
        return findViewHolderForPosition(i2, false);
    }

    public ViewHolder findViewHolderForPosition(int i2, boolean z) {
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        ViewHolder viewHolder = null;
        for (int i7 = 0; i7 < unfilteredChildCount; i7++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i7));
            if (childViewHolderInt != null && !childViewHolderInt.isRemoved()) {
                if (z) {
                    if (childViewHolderInt.mPosition != i2) {
                        continue;
                    }
                } else if (childViewHolderInt.getLayoutPosition() != i2) {
                    continue;
                }
                if (!this.mChildHelper.isHidden(childViewHolderInt.itemView)) {
                    return childViewHolderInt;
                }
                viewHolder = childViewHolderInt;
            }
        }
        return viewHolder;
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00e0 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00f1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean fling(int r8, int r9) {
        /*
            r7 = this;
            androidx.recyclerview.widget.RecyclerView$LayoutManager r0 = r7.mLayout
            r1 = 0
            if (r0 != 0) goto L_0x000d
            java.lang.String r7 = "SeslRecyclerView"
            java.lang.String r8 = "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument."
            android.util.Log.e(r7, r8)
            return r1
        L_0x000d:
            boolean r2 = r7.mLayoutSuppressed
            if (r2 == 0) goto L_0x0012
            return r1
        L_0x0012:
            boolean r0 = r0.canScrollHorizontally()
            androidx.recyclerview.widget.RecyclerView$LayoutManager r2 = r7.mLayout
            boolean r2 = r2.canScrollVertically()
            if (r0 == 0) goto L_0x0026
            int r3 = java.lang.Math.abs(r8)
            int r4 = r7.mMinFlingVelocity
            if (r3 >= r4) goto L_0x0027
        L_0x0026:
            r8 = r1
        L_0x0027:
            if (r2 == 0) goto L_0x0031
            int r3 = java.lang.Math.abs(r9)
            int r4 = r7.mMinFlingVelocity
            if (r3 >= r4) goto L_0x0032
        L_0x0031:
            r9 = r1
        L_0x0032:
            if (r8 != 0) goto L_0x0037
            if (r9 != 0) goto L_0x0037
            return r1
        L_0x0037:
            r3 = 0
            if (r8 == 0) goto L_0x007a
            android.widget.EdgeEffect r4 = r7.mLeftGlow
            if (r4 == 0) goto L_0x005c
            float r4 = androidx.core.widget.EdgeEffectCompat.getDistance(r4)
            int r4 = (r4 > r3 ? 1 : (r4 == r3 ? 0 : -1))
            if (r4 == 0) goto L_0x005c
            android.widget.EdgeEffect r4 = r7.mLeftGlow
            int r5 = -r8
            int r6 = r7.getWidth()
            boolean r4 = r7.shouldAbsorb(r4, r5, r6)
            if (r4 == 0) goto L_0x0059
            android.widget.EdgeEffect r8 = r7.mLeftGlow
            r8.onAbsorb(r5)
        L_0x0058:
            r8 = r1
        L_0x0059:
            r4 = r8
            r8 = r1
            goto L_0x007b
        L_0x005c:
            android.widget.EdgeEffect r4 = r7.mRightGlow
            if (r4 == 0) goto L_0x007a
            float r4 = androidx.core.widget.EdgeEffectCompat.getDistance(r4)
            int r4 = (r4 > r3 ? 1 : (r4 == r3 ? 0 : -1))
            if (r4 == 0) goto L_0x007a
            android.widget.EdgeEffect r4 = r7.mRightGlow
            int r5 = r7.getWidth()
            boolean r4 = r7.shouldAbsorb(r4, r8, r5)
            if (r4 == 0) goto L_0x0059
            android.widget.EdgeEffect r4 = r7.mRightGlow
            r4.onAbsorb(r8)
            goto L_0x0058
        L_0x007a:
            r4 = r1
        L_0x007b:
            if (r9 == 0) goto L_0x00bc
            android.widget.EdgeEffect r5 = r7.mTopGlow
            if (r5 == 0) goto L_0x009e
            float r5 = androidx.core.widget.EdgeEffectCompat.getDistance(r5)
            int r5 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x009e
            android.widget.EdgeEffect r3 = r7.mTopGlow
            int r5 = -r9
            int r6 = r7.getHeight()
            boolean r3 = r7.shouldAbsorb(r3, r5, r6)
            if (r3 == 0) goto L_0x009c
            android.widget.EdgeEffect r9 = r7.mTopGlow
            r9.onAbsorb(r5)
        L_0x009b:
            r9 = r1
        L_0x009c:
            r3 = r1
            goto L_0x00be
        L_0x009e:
            android.widget.EdgeEffect r5 = r7.mBottomGlow
            if (r5 == 0) goto L_0x00bc
            float r5 = androidx.core.widget.EdgeEffectCompat.getDistance(r5)
            int r3 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r3 == 0) goto L_0x00bc
            android.widget.EdgeEffect r3 = r7.mBottomGlow
            int r5 = r7.getHeight()
            boolean r3 = r7.shouldAbsorb(r3, r9, r5)
            if (r3 == 0) goto L_0x009c
            android.widget.EdgeEffect r3 = r7.mBottomGlow
            r3.onAbsorb(r9)
            goto L_0x009b
        L_0x00bc:
            r3 = r9
            r9 = r1
        L_0x00be:
            if (r4 != 0) goto L_0x00c2
            if (r9 == 0) goto L_0x00dd
        L_0x00c2:
            int r5 = r7.mMaxFlingVelocity
            int r6 = -r5
            int r4 = java.lang.Math.min(r4, r5)
            int r4 = java.lang.Math.max(r6, r4)
            int r5 = r7.mMaxFlingVelocity
            int r6 = -r5
            int r9 = java.lang.Math.min(r9, r5)
            int r9 = java.lang.Math.max(r6, r9)
            androidx.recyclerview.widget.RecyclerView$ViewFlinger r5 = r7.mViewFlinger
            r5.fling(r4, r9)
        L_0x00dd:
            r5 = 1
            if (r8 != 0) goto L_0x00e9
            if (r3 != 0) goto L_0x00e9
            if (r4 != 0) goto L_0x00e8
            if (r9 == 0) goto L_0x00e7
            goto L_0x00e8
        L_0x00e7:
            return r1
        L_0x00e8:
            return r5
        L_0x00e9:
            float r9 = (float) r8
            float r4 = (float) r3
            boolean r6 = r7.dispatchNestedPreFling(r9, r4)
            if (r6 != 0) goto L_0x012c
            if (r0 != 0) goto L_0x00f8
            if (r2 == 0) goto L_0x00f6
            goto L_0x00f8
        L_0x00f6:
            r6 = r1
            goto L_0x00f9
        L_0x00f8:
            r6 = r5
        L_0x00f9:
            r7.dispatchNestedFling(r9, r4, r6)
            androidx.recyclerview.widget.RecyclerView$OnFlingListener r9 = r7.mOnFlingListener
            if (r9 == 0) goto L_0x0107
            boolean r9 = r9.onFling(r8, r3)
            if (r9 == 0) goto L_0x0107
            return r5
        L_0x0107:
            if (r6 == 0) goto L_0x012c
            if (r2 == 0) goto L_0x010d
            r0 = r0 | 2
        L_0x010d:
            r7.startNestedScroll(r0, r5)
            int r9 = r7.mMaxFlingVelocity
            int r0 = -r9
            int r8 = java.lang.Math.min(r8, r9)
            int r8 = java.lang.Math.max(r0, r8)
            int r9 = r7.mMaxFlingVelocity
            int r0 = -r9
            int r9 = java.lang.Math.min(r3, r9)
            int r9 = java.lang.Math.max(r0, r9)
            androidx.recyclerview.widget.RecyclerView$ViewFlinger r7 = r7.mViewFlinger
            r7.fling(r8, r9)
            return r5
        L_0x012c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.fling(int, int):boolean");
    }

    public View focusSearch(View view, int i2) {
        boolean z;
        View view2;
        int i7;
        int top;
        int top2;
        boolean z3;
        boolean z7;
        boolean z9;
        int i8;
        int i10;
        View onInterceptFocusSearch = this.mLayout.onInterceptFocusSearch(view, i2);
        if (onInterceptFocusSearch != null) {
            return onInterceptFocusSearch;
        }
        boolean z10 = true;
        if (this.mAdapter == null || this.mLayout == null || isComputingLayout() || this.mLayoutSuppressed) {
            z = false;
        } else {
            z = true;
        }
        FocusFinder instance = FocusFinder.getInstance();
        if (!z || !(i2 == 2 || i2 == 1)) {
            View findNextFocus = instance.findNextFocus(this, view, i2);
            if (findNextFocus != null || !z) {
                view2 = findNextFocus;
            } else {
                consumePendingUpdateOperations();
                if (findContainingItemView(view) == null) {
                    return null;
                }
                startInterceptRequestLayout();
                view2 = this.mLayout.onFocusSearchFailed(view, i2, this.mRecycler, this.mState);
                stopInterceptRequestLayout(false);
            }
        } else {
            if (this.mLayout.canScrollVertically()) {
                if (i2 == 2) {
                    i10 = 130;
                } else {
                    i10 = 33;
                }
                if (instance.findNextFocus(this, view, i10) == null) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (FORCE_ABS_FOCUS_SEARCH_DIRECTION) {
                    i2 = i10;
                }
            } else {
                z3 = false;
            }
            if (!z3 && this.mLayout.canScrollHorizontally()) {
                if (this.mLayout.getLayoutDirection() == 1) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                if (i2 == 2) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                if (z7 ^ z9) {
                    i8 = 66;
                } else {
                    i8 = 17;
                }
                if (instance.findNextFocus(this, view, i8) != null) {
                    z10 = false;
                }
                if (FORCE_ABS_FOCUS_SEARCH_DIRECTION) {
                    i2 = i8;
                }
                z3 = z10;
            }
            if (z3) {
                consumePendingUpdateOperations();
                if (findContainingItemView(view) == null) {
                    return null;
                }
                startInterceptRequestLayout();
                this.mLayout.onFocusSearchFailed(view, i2, this.mRecycler, this.mState);
                stopInterceptRequestLayout(false);
            }
            view2 = instance.findNextFocus(this, view, i2);
        }
        if (view2 == null || view2.hasFocusable()) {
            if (!isPreferredNextFocus(view, view2, i2)) {
                view2 = super.focusSearch(view, i2);
            }
            View focusedChild = getFocusedChild();
            if (focusedChild != null && this.mIsArrowKeyPressed && view2 == null && (this.mLayout instanceof StaggeredGridLayoutManager)) {
                if (i2 == 130) {
                    top = focusedChild.getBottom();
                    top2 = getBottom();
                } else if (i2 == 33) {
                    top = focusedChild.getTop();
                    top2 = getTop();
                } else {
                    i7 = 0;
                    ((StaggeredGridLayoutManager) this.mLayout).scrollBy(i7, this.mRecycler, this.mState);
                    this.mIsArrowKeyPressed = false;
                }
                i7 = top - top2;
                ((StaggeredGridLayoutManager) this.mLayout).scrollBy(i7, this.mRecycler, this.mState);
                this.mIsArrowKeyPressed = false;
            }
            return view2;
        } else if (getFocusedChild() == null || (i2 == 33 && view != null && view.getBottom() < view2.getBottom() && !canScrollVertically(-1))) {
            return super.focusSearch(view, i2);
        } else {
            requestChildOnScreen(view2, (View) null);
            return view;
        }
    }

    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            return layoutManager.generateDefaultLayoutParams();
        }
        throw new IllegalStateException(C0086a.k(this, new StringBuilder("RecyclerView has no LayoutManager")));
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            return layoutManager.generateLayoutParams(getContext(), attributeSet);
        }
        throw new IllegalStateException(C0086a.k(this, new StringBuilder("RecyclerView has no LayoutManager")));
    }

    public CharSequence getAccessibilityClassName() {
        return "androidx.recyclerview.widget.RecyclerView";
    }

    public Adapter getAdapter() {
        return this.mAdapter;
    }

    public int getAdapterPositionInRecyclerView(ViewHolder viewHolder) {
        if (viewHolder.hasAnyOfTheFlags(524) || !viewHolder.isBound()) {
            return -1;
        }
        return this.mAdapterHelper.applyPendingUpdatesToPosition(viewHolder.mPosition);
    }

    public int getBaseline() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            return layoutManager.getBaseline();
        }
        return super.getBaseline();
    }

    public long getChangedHolderKey(ViewHolder viewHolder) {
        if (this.mAdapter.hasStableIds()) {
            return viewHolder.getItemId();
        }
        return (long) viewHolder.mPosition;
    }

    public int getChildAdapterPosition(View view) {
        ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            return childViewHolderInt.getAbsoluteAdapterPosition();
        }
        return -1;
    }

    public int getChildDrawingOrder(int i2, int i7) {
        ChildDrawingOrderCallback childDrawingOrderCallback = this.mChildDrawingOrderCallback;
        if (childDrawingOrderCallback == null) {
            return super.getChildDrawingOrder(i2, i7);
        }
        return childDrawingOrderCallback.onGetChildDrawingOrder(i2, i7);
    }

    public long getChildItemId(View view) {
        ViewHolder childViewHolderInt;
        Adapter adapter = this.mAdapter;
        if (adapter == null || !adapter.hasStableIds() || (childViewHolderInt = getChildViewHolderInt(view)) == null) {
            return -1;
        }
        return childViewHolderInt.getItemId();
    }

    public int getChildLayoutPosition(View view) {
        ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            return childViewHolderInt.getLayoutPosition();
        }
        return -1;
    }

    public ViewHolder getChildViewHolder(View view) {
        ViewParent parent = view.getParent();
        if (parent == null || parent == this) {
            return getChildViewHolderInt(view);
        }
        throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
    }

    public boolean getClipToPadding() {
        return this.mClipToPadding;
    }

    public RecyclerViewAccessibilityDelegate getCompatAccessibilityDelegate() {
        return this.mAccessibilityDelegate;
    }

    public void getDecoratedBoundsWithMargins(View view, Rect rect) {
        getDecoratedBoundsWithMarginsInt(view, rect);
    }

    public EdgeEffectFactory getEdgeEffectFactory() {
        return this.mEdgeEffectFactory;
    }

    public ItemAnimator getItemAnimator() {
        return this.mItemAnimator;
    }

    public Rect getItemDecorInsetsForChild(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (!layoutParams.mInsetsDirty) {
            return layoutParams.mDecorInsets;
        }
        if (this.mState.isPreLayout() && (layoutParams.isItemChanged() || layoutParams.isViewInvalid())) {
            return layoutParams.mDecorInsets;
        }
        Rect rect = layoutParams.mDecorInsets;
        rect.set(0, 0, 0, 0);
        int size = this.mItemDecorations.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mTempRect.set(0, 0, 0, 0);
            this.mItemDecorations.get(i2).getItemOffsets(this.mTempRect, view, this, this.mState);
            int i7 = rect.left;
            Rect rect2 = this.mTempRect;
            rect.left = i7 + rect2.left;
            rect.top += rect2.top;
            rect.right += rect2.right;
            rect.bottom += rect2.bottom;
        }
        layoutParams.mInsetsDirty = false;
        return rect;
    }

    public int getItemDecorationCount() {
        return this.mItemDecorations.size();
    }

    public LayoutManager getLayoutManager() {
        return this.mLayout;
    }

    public final SeslLongPressMultiSelectionListener getLongPressMultiSelectionListener() {
        return this.mLongPressMultiSelectionListener;
    }

    public int getMaxFlingVelocity() {
        return this.mMaxFlingVelocity;
    }

    public int getMinFlingVelocity() {
        return this.mMinFlingVelocity;
    }

    public long getNanoTime() {
        if (ALLOW_THREAD_GAP_WORK) {
            return System.nanoTime();
        }
        return 0;
    }

    public OnFlingListener getOnFlingListener() {
        return this.mOnFlingListener;
    }

    public boolean getPreserveFocusAfterLayout() {
        return this.mPreserveFocusAfterLayout;
    }

    public RecycledViewPool getRecycledViewPool() {
        return this.mRecycler.getRecycledViewPool();
    }

    public int getScrollState() {
        return this.mScrollState;
    }

    public boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().hasNestedScrollingParent();
    }

    public boolean hasPendingAdapterUpdates() {
        if (!this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout || this.mAdapterHelper.hasPendingUpdates()) {
            return true;
        }
        return false;
    }

    public void initAdapterManager() {
        this.mAdapterHelper = new AdapterHelper(new AdapterHelper.Callback() {
            public void dispatchUpdate(AdapterHelper.UpdateOp updateOp) {
                int i2 = updateOp.cmd;
                if (i2 == 1) {
                    RecyclerView recyclerView = RecyclerView.this;
                    recyclerView.mLayout.onItemsAdded(recyclerView, updateOp.positionStart, updateOp.itemCount);
                } else if (i2 == 2) {
                    RecyclerView recyclerView2 = RecyclerView.this;
                    recyclerView2.mLayout.onItemsRemoved(recyclerView2, updateOp.positionStart, updateOp.itemCount);
                } else if (i2 == 4) {
                    RecyclerView recyclerView3 = RecyclerView.this;
                    recyclerView3.mLayout.onItemsUpdated(recyclerView3, updateOp.positionStart, updateOp.itemCount, updateOp.payload);
                } else if (i2 == 8) {
                    RecyclerView recyclerView4 = RecyclerView.this;
                    recyclerView4.mLayout.onItemsMoved(recyclerView4, updateOp.positionStart, updateOp.itemCount, 1);
                }
            }

            public ViewHolder findViewHolder(int i2) {
                ViewHolder findViewHolderForPosition = RecyclerView.this.findViewHolderForPosition(i2, true);
                if (findViewHolderForPosition == null) {
                    return null;
                }
                if (!RecyclerView.this.mChildHelper.isHidden(findViewHolderForPosition.itemView)) {
                    return findViewHolderForPosition;
                }
                if (RecyclerView.sVerboseLoggingEnabled) {
                    Log.d("SeslRecyclerView", "assuming view holder cannot be find because it is hidden");
                }
                return null;
            }

            public void markViewHoldersUpdated(int i2, int i7, Object obj) {
                RecyclerView.this.viewRangeUpdate(i2, i7, obj);
                RecyclerView.this.mItemsChanged = true;
            }

            public void offsetPositionsForAdd(int i2, int i7) {
                RecyclerView.this.offsetPositionRecordsForInsert(i2, i7);
                RecyclerView.this.mItemsAddedOrRemoved = true;
            }

            public void offsetPositionsForMove(int i2, int i7) {
                RecyclerView.this.offsetPositionRecordsForMove(i2, i7);
                RecyclerView.this.mItemsAddedOrRemoved = true;
            }

            public void offsetPositionsForRemovingInvisible(int i2, int i7) {
                RecyclerView.this.offsetPositionRecordsForRemove(i2, i7, true);
                RecyclerView recyclerView = RecyclerView.this;
                recyclerView.mItemsAddedOrRemoved = true;
                recyclerView.mState.mDeletedInvisibleItemCountSincePreviousLayout += i7;
            }

            public void offsetPositionsForRemovingLaidOutOrNewView(int i2, int i7) {
                RecyclerView.this.offsetPositionRecordsForRemove(i2, i7, false);
                RecyclerView.this.mItemsAddedOrRemoved = true;
            }

            public void onDispatchFirstPass(AdapterHelper.UpdateOp updateOp) {
                dispatchUpdate(updateOp);
            }

            public void onDispatchSecondPass(AdapterHelper.UpdateOp updateOp) {
                dispatchUpdate(updateOp);
            }
        });
    }

    public void initFastScroller(StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2) {
        if (stateListDrawable == null || drawable == null || stateListDrawable2 == null || drawable2 == null) {
            throw new IllegalArgumentException(C0086a.k(this, new StringBuilder("Trying to set fast scroller without both required drawables.")));
        }
        Resources resources = getContext().getResources();
        new FastScroller(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(R$dimen.fastscroll_default_thickness), resources.getDimensionPixelSize(R$dimen.fastscroll_minimum_range), resources.getDimensionPixelOffset(R$dimen.fastscroll_margin));
    }

    public void invalidateGlows() {
        this.mBottomGlow = null;
        this.mTopGlow = null;
        this.mRightGlow = null;
        this.mLeftGlow = null;
    }

    public void invalidateItemDecorations() {
        if (this.mItemDecorations.size() != 0) {
            LayoutManager layoutManager = this.mLayout;
            if (layoutManager != null) {
                layoutManager.assertNotInLayoutOrScroll("Cannot invalidate item decorations during a scroll or layout");
            }
            markItemDecorInsetsDirty();
            requestLayout();
        }
    }

    public boolean isAccessibilityEnabled() {
        AccessibilityManager accessibilityManager = this.mAccessibilityManager;
        if (accessibilityManager == null || !accessibilityManager.isEnabled()) {
            return false;
        }
        return true;
    }

    public boolean isAnimating() {
        ItemAnimator itemAnimator = this.mItemAnimator;
        if (itemAnimator == null || !itemAnimator.isRunning()) {
            return false;
        }
        return true;
    }

    public boolean isAttachedToWindow() {
        return this.mIsAttached;
    }

    public boolean isComputingLayout() {
        if (this.mLayoutOrScrollCounter > 0) {
            return true;
        }
        return false;
    }

    public final boolean isLayoutSuppressed() {
        return this.mLayoutSuppressed;
    }

    public boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().isNestedScrollingEnabled();
    }

    public boolean isVerticalScrollBarEnabled() {
        return super.isVerticalScrollBarEnabled();
    }

    public void jumpToPositionForSmoothScroller(int i2) {
        if (this.mLayout != null) {
            setScrollState(2);
            this.mLayout.scrollToPosition(i2);
            awakenScrollBars();
        }
    }

    public void markItemDecorInsetsDirty() {
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i2 = 0; i2 < unfilteredChildCount; i2++) {
            ((LayoutParams) this.mChildHelper.getUnfilteredChildAt(i2).getLayoutParams()).mInsetsDirty = true;
        }
        this.mRecycler.markItemDecorInsetsDirty();
    }

    public void markKnownViewsInvalid() {
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i2 = 0; i2 < unfilteredChildCount; i2++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i2));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.addFlags(6);
            }
        }
        markItemDecorInsetsDirty();
        this.mRecycler.markKnownViewsInvalid();
    }

    public void offsetChildrenHorizontal(int i2) {
        int childCount = this.mChildHelper.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            this.mChildHelper.getChildAt(i7).offsetLeftAndRight(i2);
        }
    }

    public void offsetChildrenVertical(int i2) {
        int childCount = this.mChildHelper.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            this.mChildHelper.getChildAt(i7).offsetTopAndBottom(i2);
        }
    }

    public void offsetPositionRecordsForInsert(int i2, int i7) {
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i8 = 0; i8 < unfilteredChildCount; i8++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i8));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && childViewHolderInt.mPosition >= i2) {
                if (sVerboseLoggingEnabled) {
                    Log.d("SeslRecyclerView", "offsetPositionRecordsForInsert attached child " + i8 + " holder " + childViewHolderInt + " now at position " + (childViewHolderInt.mPosition + i7));
                }
                childViewHolderInt.offsetPosition(i7, false);
                this.mState.mStructureChanged = true;
            }
        }
        this.mRecycler.offsetPositionRecordsForInsert(i2, i7);
        requestLayout();
    }

    public void offsetPositionRecordsForMove(int i2, int i7) {
        int i8;
        int i10;
        int i11;
        int i12;
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        if (i2 < i7) {
            i11 = -1;
            i10 = i2;
            i8 = i7;
        } else {
            i8 = i2;
            i10 = i7;
            i11 = 1;
        }
        for (int i13 = 0; i13 < unfilteredChildCount; i13++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i13));
            if (childViewHolderInt != null && (i12 = childViewHolderInt.mPosition) >= i10 && i12 <= i8) {
                if (sVerboseLoggingEnabled) {
                    Log.d("SeslRecyclerView", "offsetPositionRecordsForMove attached child " + i13 + " holder " + childViewHolderInt);
                }
                if (childViewHolderInt.mPosition == i2) {
                    childViewHolderInt.offsetPosition(i7 - i2, false);
                } else {
                    childViewHolderInt.offsetPosition(i11, false);
                }
                this.mState.mStructureChanged = true;
            }
        }
        this.mRecycler.offsetPositionRecordsForMove(i2, i7);
        requestLayout();
    }

    public void offsetPositionRecordsForRemove(int i2, int i7, boolean z) {
        int i8 = i2 + i7;
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i10 = 0; i10 < unfilteredChildCount; i10++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i10));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore()) {
                int i11 = childViewHolderInt.mPosition;
                if (i11 >= i8) {
                    if (sVerboseLoggingEnabled) {
                        Log.d("SeslRecyclerView", "offsetPositionRecordsForRemove attached child " + i10 + " holder " + childViewHolderInt + " now at position " + (childViewHolderInt.mPosition - i7));
                    }
                    childViewHolderInt.offsetPosition(-i7, z);
                    this.mState.mStructureChanged = true;
                } else if (i11 >= i2) {
                    if (sVerboseLoggingEnabled) {
                        Log.d("SeslRecyclerView", "offsetPositionRecordsForRemove attached child " + i10 + " holder " + childViewHolderInt + " now REMOVED");
                    }
                    childViewHolderInt.flagRemovedAndOffsetPosition(i2 - 1, -i7, z);
                    this.mState.mStructureChanged = true;
                }
            }
        }
        this.mRecycler.offsetPositionRecordsForRemove(i2, i7, z);
        requestLayout();
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mLayoutOrScrollCounter = 0;
        boolean z = true;
        this.mIsAttached = true;
        if (!this.mFirstLayoutComplete || isLayoutRequested()) {
            z = false;
        }
        this.mFirstLayoutComplete = z;
        this.mRecycler.onAttachedToWindow();
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.dispatchAttachedToWindow(this);
        }
        this.mPostedAnimatorRunner = false;
        if (ALLOW_THREAD_GAP_WORK) {
            ThreadLocal<GapWorker> threadLocal = GapWorker.sGapWorker;
            GapWorker gapWorker = threadLocal.get();
            this.mGapWorker = gapWorker;
            if (gapWorker == null) {
                this.mGapWorker = new GapWorker();
                Display display = ViewCompat.getDisplay(this);
                float f = 60.0f;
                if (!isInEditMode() && display != null) {
                    float refreshRate = display.getRefreshRate();
                    if (refreshRate >= 30.0f) {
                        f = refreshRate;
                    }
                    if (this.mIsNeedCheckLatency) {
                        this.mFrameLatency = 1000.0f / f;
                        this.mIsNeedCheckLatency = false;
                    }
                }
                GapWorker gapWorker2 = this.mGapWorker;
                gapWorker2.mFrameIntervalNs = (long) (1.0E9f / f);
                threadLocal.set(gapWorker2);
            }
            this.mGapWorker.add(this);
            LayoutManager layoutManager2 = this.mLayout;
            if (layoutManager2 != null) {
                layoutManager2.getLayoutDirection();
            }
        }
    }

    public void onDetachedFromWindow() {
        IndexTip indexTip;
        GapWorker gapWorker;
        super.onDetachedFromWindow();
        ItemAnimator itemAnimator = this.mItemAnimator;
        if (itemAnimator != null) {
            itemAnimator.endAnimations();
        }
        stopScroll();
        this.mIsAttached = false;
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.dispatchDetachedFromWindow(this, this.mRecycler);
        }
        this.mPendingAccessibilityImportanceChange.clear();
        removeCallbacks(this.mItemAnimatorRunner);
        this.mViewInfoStore.onDetach();
        this.mRecycler.onDetachedFromWindow();
        PoolingContainer.callPoolingContainerOnReleaseForChildren(this);
        if (ALLOW_THREAD_GAP_WORK && (gapWorker = this.mGapWorker) != null) {
            gapWorker.remove(this);
            this.mGapWorker = null;
        }
        if (this.mIndexTipEnabled && (indexTip = this.mIndexTip) != null) {
            indexTip.forcedHide();
        }
        this.mIsNeedCheckLatency = true;
        if (this.mIsRecoilSupported) {
            this.mItemAnimatorHolder.removeAllUpdateListeners();
        }
        SeslGoToTopController seslGoToTopController = this.mGoToTopController;
        if (seslGoToTopController != null) {
            seslGoToTopController.release();
        }
    }

    public void onDraw(Canvas canvas) {
        Canvas canvas2;
        super.onDraw(canvas);
        if (this.mFadingEdgeHelper.isFadingEdgeEnabled()) {
            Rect calculateFadingEdgeBounds = calculateFadingEdgeBounds();
            canvas2 = canvas;
            this.mFadingEdgeHelper.prepareFadingEffect(canvas2, calculateFadingEdgeBounds.left, calculateFadingEdgeBounds.top, calculateFadingEdgeBounds.right, calculateFadingEdgeBounds.bottom);
        } else {
            canvas2 = canvas;
        }
        int size = this.mItemDecorations.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mItemDecorations.get(i2).onDraw(canvas2, this, this.mState);
        }
        if (this.mIsNeedCheckLatency) {
            Display display = ViewCompat.getDisplay(this);
            if (display != null) {
                this.mFrameLatency = 1000.0f / display.getRefreshRate();
            } else {
                this.mFrameLatency = 16.66f;
            }
            this.mIsNeedCheckLatency = false;
        }
    }

    public void onEnterLayoutOrScroll() {
        this.mLayoutOrScrollCounter++;
    }

    public void onExitLayoutOrScroll() {
        onExitLayoutOrScroll(true);
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        float f;
        float f5;
        if (this.mLayout != null && !this.mLayoutSuppressed && motionEvent.getAction() == 8) {
            int i2 = 2;
            if ((motionEvent.getSource() & 2) != 0) {
                if (this.mLayout.canScrollVertically()) {
                    f5 = -motionEvent.getAxisValue(9);
                } else {
                    f5 = 0.0f;
                }
                if (this.mLayout.canScrollHorizontally()) {
                    f = motionEvent.getAxisValue(10);
                } else {
                    f = 0.0f;
                }
                this.mIsActionScrollFromMouse = MotionEventCompat.isFromSource(motionEvent, 8194);
            } else {
                if ((motionEvent.getSource() & OCDHelperConstant.TEMP_TO_CHECK_OBJECT_CAPTURE) != 0) {
                    float axisValue = motionEvent.getAxisValue(26);
                    if (this.mLayout.canScrollVertically()) {
                        f5 = -axisValue;
                        f = 0.0f;
                    } else if (this.mLayout.canScrollHorizontally()) {
                        f = axisValue;
                        f5 = 0.0f;
                    }
                }
                f5 = 0.0f;
                f = 0.0f;
            }
            int i7 = (f5 > 0.0f ? 1 : (f5 == 0.0f ? 0 : -1));
            if (!(i7 == 0 && f == 0.0f)) {
                if (i7 == 0) {
                    i2 = 1;
                }
                startNestedScroll(i2, 1);
                if (!dispatchNestedPreScroll((int) (this.mScaledHorizontalScrollFactor * f), (int) (this.mScaledVerticalScrollFactor * f5), (int[]) null, (int[]) null, 1)) {
                    nestedScrollByInternal((int) (f * this.mScaledHorizontalScrollFactor), (int) (f5 * this.mScaledVerticalScrollFactor), motionEvent, 1);
                }
            }
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0077, code lost:
        if (r1 != 211) goto L_0x02e9;
     */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x02f0 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x02f1 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r18) {
        /*
            r17 = this;
            r0 = r17
            r6 = r18
            boolean r1 = r0.mLayoutSuppressed
            r7 = 0
            if (r1 == 0) goto L_0x000f
            r1 = -1
            r0.mLastTouchX = r1
            r0.mLastTouchY = r1
            return r7
        L_0x000f:
            r1 = 0
            r0.mInterceptingOnItemTouchListener = r1
            boolean r1 = r17.findInterceptingOnItemTouchListener(r18)
            r8 = 1
            if (r1 == 0) goto L_0x001d
            r0.cancelScroll()
            return r8
        L_0x001d:
            androidx.recyclerview.widget.RecyclerView$LayoutManager r1 = r0.mLayout
            if (r1 != 0) goto L_0x0022
            return r7
        L_0x0022:
            android.view.MotionEvent r9 = android.view.MotionEvent.obtain(r6)
            androidx.recyclerview.widget.RecyclerView$LayoutManager r1 = r0.mLayout
            boolean r10 = r1.canScrollHorizontally()
            androidx.recyclerview.widget.RecyclerView$LayoutManager r1 = r0.mLayout
            boolean r11 = r1.canScrollVertically()
            android.view.VelocityTracker r1 = r0.mVelocityTracker
            if (r1 != 0) goto L_0x003c
            android.view.VelocityTracker r1 = android.view.VelocityTracker.obtain()
            r0.mVelocityTracker = r1
        L_0x003c:
            android.view.VelocityTracker r1 = r0.mVelocityTracker
            r1.addMovement(r6)
            int r1 = r6.getActionMasked()
            int r2 = r6.getActionIndex()
            int r3 = r6.getToolType(r7)
            r4 = 2
            if (r3 != r4) goto L_0x0052
            r3 = r8
            goto L_0x0053
        L_0x0052:
            r3 = r7
        L_0x0053:
            int r5 = r6.getButtonState()
            r5 = r5 & 32
            if (r5 == 0) goto L_0x005d
            r5 = r8
            goto L_0x005e
        L_0x005d:
            r5 = r7
        L_0x005e:
            java.lang.String r12 = "] "
            java.lang.String r13 = "] mLastTouchY["
            r14 = 211(0xd3, float:2.96E-43)
            java.lang.String r15 = "SeslRecyclerView"
            r16 = 1056964608(0x3f000000, float:0.5)
            if (r1 == 0) goto L_0x020c
            if (r1 == r8) goto L_0x0202
            if (r1 == r4) goto L_0x00df
            r4 = 3
            if (r1 == r4) goto L_0x00c0
            r4 = 5
            if (r1 == r4) goto L_0x0080
            r2 = 6
            if (r1 == r2) goto L_0x007b
            if (r1 == r14) goto L_0x020c
            goto L_0x02e9
        L_0x007b:
            r17.onPointerUp(r18)
            goto L_0x02e9
        L_0x0080:
            int r1 = r6.getPointerId(r2)
            r0.mScrollPointerId = r1
            float r1 = r6.getX(r2)
            float r1 = r1 + r16
            int r1 = (int) r1
            r0.mLastTouchX = r1
            r0.mInitialTouchX = r1
            float r1 = r6.getY(r2)
            float r1 = r1 + r16
            int r1 = (int) r1
            r0.mLastTouchY = r1
            r0.mInitialTouchY = r1
            boolean r1 = sVerboseLoggingEnabled
            if (r1 == 0) goto L_0x02e9
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "onIntercept POINTER_DOWN mLastTouchX["
            r1.<init>(r2)
            int r2 = r0.mLastTouchX
            r1.append(r2)
            r1.append(r13)
            int r2 = r0.mLastTouchY
            r1.append(r2)
            r1.append(r12)
            java.lang.String r1 = r1.toString()
            android.util.Log.d(r15, r1)
            goto L_0x02e9
        L_0x00c0:
            boolean r1 = r0.mSeslIsNested
            if (r1 == 0) goto L_0x00c8
            int r1 = r0.mScrollState
            if (r1 != r8) goto L_0x00cb
        L_0x00c8:
            r0.cancelScroll()
        L_0x00cb:
            boolean r1 = r0.mIsRecoilSupported
            if (r1 == 0) goto L_0x02e9
            boolean r1 = r0.mIsRecoilEnabled
            if (r1 == 0) goto L_0x02e9
            androidx.recyclerview.widget.RecyclerView$ItemBackgroundHolder r1 = r0.mItemBackgroundHolder
            r1.setCancel()
            androidx.appcompat.animation.SeslRecoilAnimator$Holder r1 = r0.mItemAnimatorHolder
            r1.setRelease()
            goto L_0x02e9
        L_0x00df:
            int r1 = r0.mLastTouchX
            if (r1 < 0) goto L_0x02e9
            int r1 = r0.mLastTouchY
            if (r1 >= 0) goto L_0x00e9
            goto L_0x02e9
        L_0x00e9:
            if (r3 == 0) goto L_0x00ed
            if (r5 != 0) goto L_0x02e9
        L_0x00ed:
            boolean r1 = r0.mIsPenButtonPressed
            if (r1 == 0) goto L_0x00f3
            goto L_0x02e9
        L_0x00f3:
            int r1 = r0.mScrollPointerId
            int r1 = r6.findPointerIndex(r1)
            if (r1 >= 0) goto L_0x0117
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Error processing scroll; pointer index for id "
            r1.<init>(r2)
            int r0 = r0.mScrollPointerId
            r1.append(r0)
            java.lang.String r0 = " not found. Did any MotionEvents get skipped?"
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            android.util.Log.e(r15, r0)
            r9.recycle()
            return r7
        L_0x0117:
            float r2 = r6.getX(r1)
            float r2 = r2 + r16
            int r12 = (int) r2
            float r1 = r6.getY(r1)
            float r1 = r1 + r16
            int r13 = (int) r1
            int r1 = r0.mLastTouchX
            int r1 = r1 - r12
            int r2 = r0.mLastTouchY
            int r2 = r2 - r13
            boolean r3 = sVerboseLoggingEnabled
            if (r3 == 0) goto L_0x013c
            java.lang.String r3 = "] dy["
            java.lang.String r4 = "]"
            java.lang.String r5 = "onIntercept MOVE dx["
            java.lang.String r3 = A.a.d(r1, r2, r5, r3, r4)
            android.util.Log.d(r15, r3)
        L_0x013c:
            int r3 = r0.mScrollState
            if (r3 == r8) goto L_0x017f
            if (r10 == 0) goto L_0x0153
            int r3 = java.lang.Math.abs(r1)
            int r4 = r0.mTouchSlop
            if (r3 <= r4) goto L_0x0153
            if (r1 <= 0) goto L_0x014e
            int r1 = r1 - r4
            goto L_0x014f
        L_0x014e:
            int r1 = r1 + r4
        L_0x014f:
            r0.mLastTouchX = r12
            r3 = r8
            goto L_0x0154
        L_0x0153:
            r3 = r7
        L_0x0154:
            if (r11 == 0) goto L_0x0168
            int r4 = java.lang.Math.abs(r2)
            int r5 = r0.mTouchSlop
            if (r4 <= r5) goto L_0x0168
            if (r2 <= 0) goto L_0x0162
            int r2 = r2 - r5
            goto L_0x0163
        L_0x0162:
            int r2 = r2 + r5
        L_0x0163:
            r0.mPreventFirstGlow = r8
            r0.mLastTouchY = r13
            r3 = r8
        L_0x0168:
            if (r3 == 0) goto L_0x017f
            r0.setScrollState(r8)
            boolean r3 = r0.mIsRecoilSupported
            if (r3 == 0) goto L_0x017f
            boolean r3 = r0.mIsRecoilEnabled
            if (r3 == 0) goto L_0x017f
            androidx.recyclerview.widget.RecyclerView$ItemBackgroundHolder r3 = r0.mItemBackgroundHolder
            r3.setCancel()
            androidx.appcompat.animation.SeslRecoilAnimator$Holder r3 = r0.mItemAnimatorHolder
            r3.setRelease()
        L_0x017f:
            r14 = r1
            r15 = r2
            int r1 = r0.mScrollState
            if (r1 != r8) goto L_0x01fd
            if (r10 == 0) goto L_0x0189
            r1 = r14
            goto L_0x018a
        L_0x0189:
            r1 = r7
        L_0x018a:
            if (r11 == 0) goto L_0x018e
            r2 = r15
            goto L_0x018f
        L_0x018e:
            r2 = r7
        L_0x018f:
            int[] r3 = r0.mReusableIntPair
            int[] r4 = r0.mScrollOffset
            r5 = 0
            boolean r1 = r0.dispatchNestedPreScroll(r1, r2, r3, r4, r5)
            if (r1 == 0) goto L_0x01bb
            int[] r1 = r0.mReusableIntPair
            r2 = r1[r7]
            int r14 = r14 - r2
            r1 = r1[r8]
            int r15 = r15 - r1
            int[] r1 = r0.mNestedOffsets
            r2 = r1[r7]
            int[] r3 = r0.mScrollOffset
            r4 = r3[r7]
            int r2 = r2 + r4
            r1[r7] = r2
            r2 = r1[r8]
            r3 = r3[r8]
            int r2 = r2 + r3
            r1[r8] = r2
            android.view.ViewParent r1 = r0.getParent()
            r1.requestDisallowInterceptTouchEvent(r8)
        L_0x01bb:
            int[] r1 = r0.mScrollOffset
            r2 = r1[r7]
            int r12 = r12 - r2
            r0.mLastTouchX = r12
            r1 = r1[r8]
            int r13 = r13 - r1
            r0.mLastTouchY = r13
            int r1 = r6.getFlags()
            r2 = 33554432(0x2000000, float:9.403955E-38)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x01db
            android.view.VelocityTracker r1 = r0.mVelocityTracker
            r1.addMovement(r9)
            r0.mIsSkipMoveEvent = r8
            r9.recycle()
            return r7
        L_0x01db:
            if (r10 == 0) goto L_0x01df
            r1 = r14
            goto L_0x01e0
        L_0x01df:
            r1 = r7
        L_0x01e0:
            if (r11 == 0) goto L_0x01e4
            r2 = r15
            goto L_0x01e5
        L_0x01e4:
            r2 = r7
        L_0x01e5:
            boolean r1 = r0.scrollByInternal(r1, r2, r9, r7)
            if (r1 == 0) goto L_0x01f2
            android.view.ViewParent r1 = r0.getParent()
            r1.requestDisallowInterceptTouchEvent(r8)
        L_0x01f2:
            androidx.recyclerview.widget.GapWorker r1 = r0.mGapWorker
            if (r1 == 0) goto L_0x01fd
            if (r14 != 0) goto L_0x01fa
            if (r15 == 0) goto L_0x01fd
        L_0x01fa:
            r1.postFromTraversal(r0, r14, r15)
        L_0x01fd:
            r0.adjustNestedScrollRangeBy(r15)
            goto L_0x02e9
        L_0x0202:
            android.view.VelocityTracker r1 = r0.mVelocityTracker
            r1.clear()
            r0.stopNestedScroll(r7)
            goto L_0x02e9
        L_0x020c:
            if (r3 == 0) goto L_0x0210
            if (r5 != 0) goto L_0x0212
        L_0x0210:
            if (r1 != r14) goto L_0x021a
        L_0x0212:
            boolean r1 = r0.mIgnoreMotionEventTillDown
            if (r1 == 0) goto L_0x02c1
            r0.mIgnoreMotionEventTillDown = r7
            goto L_0x02c1
        L_0x021a:
            boolean r1 = r0.mIgnoreMotionEventTillDown
            if (r1 == 0) goto L_0x0220
            r0.mIgnoreMotionEventTillDown = r7
        L_0x0220:
            int r1 = r6.getPointerId(r7)
            r0.mScrollPointerId = r1
            float r1 = r6.getX()
            float r1 = r1 + r16
            int r1 = (int) r1
            r0.mLastTouchX = r1
            r0.mInitialTouchX = r1
            float r1 = r6.getY()
            float r1 = r1 + r16
            int r1 = (int) r1
            r0.mLastTouchY = r1
            r0.mInitialTouchY = r1
            boolean r1 = r0.mUsePagingTouchSlopForStylus
            if (r1 == 0) goto L_0x0251
            r1 = 16386(0x4002, float:2.2962E-41)
            boolean r1 = r6.isFromSource(r1)
            if (r1 == 0) goto L_0x024d
            int r1 = r0.mPagingTouchSlop
            r0.mTouchSlop = r1
            goto L_0x0251
        L_0x024d:
            int r1 = r0.mTouchSlop2
            r0.mTouchSlop = r1
        L_0x0251:
            boolean r1 = sVerboseLoggingEnabled
            if (r1 == 0) goto L_0x0291
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "onIntercept DOWN mTouchSlop["
            r1.<init>(r2)
            int r2 = r0.mTouchSlop
            r1.append(r2)
            java.lang.String r2 = "] mTouchSlop["
            r1.append(r2)
            int r2 = r0.mTouchSlop2
            r1.append(r2)
            java.lang.String r2 = "] mPagingTouchSlop["
            r1.append(r2)
            int r2 = r0.mPagingTouchSlop
            r1.append(r2)
            java.lang.String r2 = "] mLastTouchX["
            r1.append(r2)
            int r2 = r0.mLastTouchX
            r1.append(r2)
            r1.append(r13)
            int r2 = r0.mLastTouchY
            r1.append(r2)
            r1.append(r12)
            java.lang.String r1 = r1.toString()
            android.util.Log.d(r15, r1)
        L_0x0291:
            boolean r1 = r17.stopGlowAnimations(r18)
            if (r1 != 0) goto L_0x029c
            int r1 = r0.mScrollState
            r2 = 2
            if (r1 != r2) goto L_0x02a9
        L_0x029c:
            android.view.ViewParent r1 = r0.getParent()
            r1.requestDisallowInterceptTouchEvent(r8)
            r0.setScrollState(r8)
            r0.stopNestedScroll(r8)
        L_0x02a9:
            int[] r1 = r0.mNestedOffsets
            r1[r8] = r7
            r1[r7] = r7
            boolean r1 = r0.mHasNestedScrollRange
            if (r1 == 0) goto L_0x02b6
            r0.adjustNestedScrollRange()
        L_0x02b6:
            r0.mPreventFirstGlow = r7
            if (r11 == 0) goto L_0x02bc
            r10 = r10 | 2
        L_0x02bc:
            r0.startNestedScroll(r10, r7)
            r0.mIsSkipMoveEvent = r7
        L_0x02c1:
            boolean r1 = r0.mIsRecoilSupported
            if (r1 == 0) goto L_0x02e9
            boolean r1 = r0.mIsRecoilEnabled
            if (r1 == 0) goto L_0x02e9
            int r1 = r0.mScrollState
            if (r1 != 0) goto L_0x02e9
            android.view.View r1 = r17.findClickableChildUnder(r18)
            if (r1 == 0) goto L_0x02e9
            androidx.recyclerview.widget.RecyclerView$ViewHolder r2 = r0.findContainingViewHolder(r1)
            if (r2 == 0) goto L_0x02e9
            boolean r2 = r2.seslIsViewHolderRecoilEffectEnabled()
            if (r2 == 0) goto L_0x02e9
            androidx.recyclerview.widget.RecyclerView$ItemBackgroundHolder r2 = r0.mItemBackgroundHolder
            r2.setPress(r1)
            androidx.appcompat.animation.SeslRecoilAnimator$Holder r2 = r0.mItemAnimatorHolder
            r2.setPress(r1)
        L_0x02e9:
            r9.recycle()
            int r0 = r0.mScrollState
            if (r0 != r8) goto L_0x02f1
            return r8
        L_0x02f1:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 != 92) {
            if (i2 != 93) {
                if (i2 == 113 || i2 == 114) {
                    this.mIsCtrlKeyPressed = true;
                } else if (i2 != 122) {
                    if (i2 == 123 && keyEvent.hasNoModifiers()) {
                        pageScroll(3);
                    }
                } else if (keyEvent.hasNoModifiers()) {
                    pageScroll(2);
                }
            } else if (keyEvent.hasNoModifiers()) {
                pageScroll(1);
            }
        } else if (keyEvent.hasNoModifiers()) {
            pageScroll(0);
        }
        return super.onKeyDown(i2, keyEvent);
    }

    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (i2 == 113 || i2 == 114) {
            this.mIsCtrlKeyPressed = false;
        }
        return super.onKeyUp(i2, keyEvent);
    }

    public void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        IndexTip indexTip;
        TraceCompat.beginSection("RV OnLayout");
        dispatchLayout();
        TraceCompat.endSection();
        this.mFirstLayoutComplete = true;
        if (z) {
            this.mSeslOverlayFeatureHeight = getResources().getDimensionPixelSize(R$dimen.sesl_recyclerview_overlay_feature_hidden_height);
            SeslGoToTopController seslGoToTopController = this.mGoToTopController;
            if (seslGoToTopController != null) {
                seslGoToTopController.setSizeChanged(true);
                this.mGoToTopController.setOverlayFeatureHiddenHeightPx(this.mSeslOverlayFeatureHeight);
            }
            seslSetImmersiveScrollBottomPadding(0);
            SeslGoToTopController seslGoToTopController2 = this.mGoToTopController;
            if (seslGoToTopController2 != null) {
                seslGoToTopController2.onSizeChanged();
            }
            LayoutManager layoutManager = this.mLayout;
            if (layoutManager == null || layoutManager.canScrollHorizontally()) {
                LayoutManager layoutManager2 = this.mLayout;
                if (layoutManager2 != null && layoutManager2.canScrollHorizontally()) {
                    getLocationInWindow(this.mWindowOffsets);
                    this.mRemainNestedScrollRange = 0;
                    this.mNestedScrollRange = 0;
                    this.mInitialTopOffsetOfScreen = this.mWindowOffsets[0];
                }
            } else {
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
            if (this.mIndexTipEnabled && (indexTip = this.mIndexTip) != null) {
                indexTip.setLayout(0, this.mScrollBarTopOffset, i8 - i2, i10 - i7, getPaddingLeft() + indexTip.getPaddingLeft(), getPaddingRight() + this.mIndexTip.getPaddingRight());
            }
        }
    }

    public void onMeasure(int i2, int i7) {
        if (this.mLayout == null) {
            defaultOnMeasure(i2, i7);
            return;
        }
        this.mListPadding.set(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        boolean z = false;
        if (this.mLayout.isAutoMeasureEnabled()) {
            int mode = View.MeasureSpec.getMode(i2);
            int mode2 = View.MeasureSpec.getMode(i7);
            this.mLayout.onMeasure(this.mRecycler, this.mState, i2, i7);
            if (mode == 1073741824 && mode2 == 1073741824) {
                z = true;
            }
            this.mLastAutoMeasureSkippedDueToExact = z;
            if (!z && this.mAdapter != null) {
                if (this.mState.mLayoutStep == 1) {
                    dispatchLayoutStep1();
                }
                this.mLayout.setMeasureSpecs(i2, i7);
                this.mState.mIsMeasuring = true;
                dispatchLayoutStep2();
                this.mLayout.setMeasuredDimensionFromChildren(i2, i7);
                if (this.mLayout.shouldMeasureTwice()) {
                    this.mLayout.setMeasureSpecs(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
                    this.mState.mIsMeasuring = true;
                    dispatchLayoutStep2();
                    this.mLayout.setMeasuredDimensionFromChildren(i2, i7);
                }
                this.mLastAutoMeasureNonExactMeasuredWidth = getMeasuredWidth();
                this.mLastAutoMeasureNonExactMeasuredHeight = getMeasuredHeight();
            }
        } else if (this.mHasFixedSize) {
            this.mLayout.onMeasure(this.mRecycler, this.mState, i2, i7);
        } else {
            if (this.mAdapterUpdateDuringMeasure) {
                startInterceptRequestLayout();
                onEnterLayoutOrScroll();
                processAdapterUpdatesAndSetAnimationFlags();
                onExitLayoutOrScroll();
                State state = this.mState;
                if (state.mRunPredictiveAnimations) {
                    state.mInPreLayout = true;
                } else {
                    this.mAdapterHelper.consumeUpdatesInOnePass();
                    this.mState.mInPreLayout = false;
                }
                this.mAdapterUpdateDuringMeasure = false;
                stopInterceptRequestLayout(false);
            } else if (this.mState.mRunPredictiveAnimations) {
                setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
                return;
            }
            Adapter adapter = this.mAdapter;
            if (adapter != null) {
                this.mState.mItemCount = adapter.getItemCount();
            } else {
                this.mState.mItemCount = 0;
            }
            startInterceptRequestLayout();
            this.mLayout.onMeasure(this.mRecycler, this.mState, i2, i7);
            stopInterceptRequestLayout(false);
            this.mState.mInPreLayout = false;
        }
    }

    public boolean onRequestFocusInDescendants(int i2, Rect rect) {
        if (isComputingLayout()) {
            return false;
        }
        return super.onRequestFocusInDescendants(i2, rect);
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        this.mPendingSavedState = savedState;
        super.onRestoreInstanceState(savedState.getSuperState());
        requestLayout();
    }

    public void onRtlPropertiesChanged(int i2) {
        super.onRtlPropertiesChanged(i2);
    }

    public Parcelable onSaveInstanceState() {
        this.mIsNeedCheckLatency = true;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SavedState savedState2 = this.mPendingSavedState;
        if (savedState2 != null) {
            savedState.copyFrom(savedState2);
            return savedState;
        }
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            savedState.mLayoutState = layoutManager.onSaveInstanceState();
            return savedState;
        }
        savedState.mLayoutState = null;
        return savedState;
    }

    public void onSizeChanged(int i2, int i7, int i8, int i10) {
        super.onSizeChanged(i2, i7, i8, i10);
        if (i2 != i8 || i7 != i10) {
            invalidateGlows();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:60:0x0108  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x011e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r17) {
        /*
            r16 = this;
            r0 = r16
            r6 = r17
            boolean r1 = r0.mLayoutSuppressed
            r7 = 0
            if (r1 != 0) goto L_0x028a
            boolean r1 = r0.mIgnoreMotionEventTillDown
            if (r1 == 0) goto L_0x000f
            goto L_0x028a
        L_0x000f:
            boolean r1 = r16.dispatchToOnItemTouchListeners(r17)
            r8 = 1
            if (r1 == 0) goto L_0x001a
            r0.cancelScroll()
            return r8
        L_0x001a:
            androidx.recyclerview.widget.RecyclerView$LayoutManager r1 = r0.mLayout
            if (r1 != 0) goto L_0x001f
            return r7
        L_0x001f:
            boolean r9 = r1.canScrollHorizontally()
            androidx.recyclerview.widget.RecyclerView$LayoutManager r1 = r0.mLayout
            boolean r10 = r1.canScrollVertically()
            android.view.VelocityTracker r1 = r0.mVelocityTracker
            if (r1 != 0) goto L_0x0033
            android.view.VelocityTracker r1 = android.view.VelocityTracker.obtain()
            r0.mVelocityTracker = r1
        L_0x0033:
            int r1 = r6.getActionMasked()
            int r2 = r6.getActionIndex()
            if (r1 != 0) goto L_0x0043
            int[] r3 = r0.mNestedOffsets
            r3[r8] = r7
            r3[r7] = r7
        L_0x0043:
            android.view.MotionEvent r11 = android.view.MotionEvent.obtain(r6)
            int[] r3 = r0.mNestedOffsets
            r4 = r3[r7]
            float r4 = (float) r4
            r3 = r3[r8]
            float r3 = (float) r3
            r11.offsetLocation(r4, r3)
            int r3 = r6.getToolType(r7)
            r4 = 2
            if (r3 != r4) goto L_0x005b
            r3 = r8
            goto L_0x005c
        L_0x005b:
            r3 = r7
        L_0x005c:
            int r5 = r6.getButtonState()
            r5 = r5 & 32
            if (r5 == 0) goto L_0x0066
            r5 = r8
            goto L_0x0067
        L_0x0066:
            r5 = r7
        L_0x0067:
            java.lang.String r12 = "SeslRecyclerView"
            r13 = 1056964608(0x3f000000, float:0.5)
            if (r1 == 0) goto L_0x0233
            if (r1 == r8) goto L_0x01b9
            if (r1 == r4) goto L_0x00aa
            r3 = 3
            if (r1 == r3) goto L_0x009d
            r3 = 5
            if (r1 == r3) goto L_0x0081
            r2 = 6
            if (r1 == r2) goto L_0x007c
            goto L_0x0281
        L_0x007c:
            r16.onPointerUp(r17)
            goto L_0x0281
        L_0x0081:
            int r1 = r6.getPointerId(r2)
            r0.mScrollPointerId = r1
            float r1 = r6.getX(r2)
            float r1 = r1 + r13
            int r1 = (int) r1
            r0.mLastTouchX = r1
            r0.mInitialTouchX = r1
            float r1 = r6.getY(r2)
            float r1 = r1 + r13
            int r1 = (int) r1
            r0.mLastTouchY = r1
            r0.mInitialTouchY = r1
            goto L_0x0281
        L_0x009d:
            boolean r1 = r0.mSeslIsNested
            if (r1 == 0) goto L_0x00a5
            int r1 = r0.mScrollState
            if (r1 != r8) goto L_0x0281
        L_0x00a5:
            r0.cancelScroll()
            goto L_0x0281
        L_0x00aa:
            if (r3 == 0) goto L_0x00ae
            if (r5 != 0) goto L_0x0281
        L_0x00ae:
            boolean r1 = r0.mIsPenButtonPressed
            if (r1 == 0) goto L_0x00b4
            goto L_0x0281
        L_0x00b4:
            int r1 = r0.mScrollPointerId
            int r1 = r6.findPointerIndex(r1)
            if (r1 >= 0) goto L_0x00d8
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Error processing scroll; pointer index for id "
            r1.<init>(r2)
            int r0 = r0.mScrollPointerId
            r1.append(r0)
            java.lang.String r0 = " not found. Did any MotionEvents get skipped?"
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            android.util.Log.e(r12, r0)
            r11.recycle()
            return r7
        L_0x00d8:
            float r2 = r6.getX(r1)
            float r2 = r2 + r13
            int r12 = (int) r2
            float r1 = r6.getY(r1)
            float r1 = r1 + r13
            int r13 = (int) r1
            int r1 = r0.mLastTouchX
            int r1 = r1 - r12
            int r2 = r0.mLastTouchY
            int r2 = r2 - r13
            int r3 = r0.mScrollState
            if (r3 == r8) goto L_0x0121
            if (r9 == 0) goto L_0x0105
            if (r1 <= 0) goto L_0x00fa
            int r3 = r0.mTouchSlop
            int r1 = r1 - r3
            int r1 = java.lang.Math.max(r7, r1)
            goto L_0x0101
        L_0x00fa:
            int r3 = r0.mTouchSlop
            int r1 = r1 + r3
            int r1 = java.lang.Math.min(r7, r1)
        L_0x0101:
            if (r1 == 0) goto L_0x0105
            r3 = r8
            goto L_0x0106
        L_0x0105:
            r3 = r7
        L_0x0106:
            if (r10 == 0) goto L_0x011c
            if (r2 <= 0) goto L_0x0112
            int r4 = r0.mTouchSlop
            int r2 = r2 - r4
            int r2 = java.lang.Math.max(r7, r2)
            goto L_0x0119
        L_0x0112:
            int r4 = r0.mTouchSlop
            int r2 = r2 + r4
            int r2 = java.lang.Math.min(r7, r2)
        L_0x0119:
            if (r2 == 0) goto L_0x011c
            r3 = r8
        L_0x011c:
            if (r3 == 0) goto L_0x0121
            r0.setScrollState(r8)
        L_0x0121:
            int r3 = r0.mScrollState
            if (r3 != r8) goto L_0x0281
            int[] r3 = r0.mReusableIntPair
            r3[r7] = r7
            r3[r8] = r7
            float r3 = r6.getY()
            int r3 = r0.releaseHorizontalGlow(r1, r3)
            int r14 = r1 - r3
            float r1 = r6.getX()
            int r1 = r0.releaseVerticalGlow(r2, r1)
            int r15 = r2 - r1
            if (r9 == 0) goto L_0x0143
            r1 = r14
            goto L_0x0144
        L_0x0143:
            r1 = r7
        L_0x0144:
            if (r10 == 0) goto L_0x0148
            r2 = r15
            goto L_0x0149
        L_0x0148:
            r2 = r7
        L_0x0149:
            int[] r3 = r0.mReusableIntPair
            int[] r4 = r0.mScrollOffset
            r5 = 0
            boolean r1 = r0.dispatchNestedPreScroll(r1, r2, r3, r4, r5)
            if (r1 == 0) goto L_0x0175
            int[] r1 = r0.mReusableIntPair
            r2 = r1[r7]
            int r14 = r14 - r2
            r1 = r1[r8]
            int r15 = r15 - r1
            int[] r1 = r0.mNestedOffsets
            r2 = r1[r7]
            int[] r3 = r0.mScrollOffset
            r4 = r3[r7]
            int r2 = r2 + r4
            r1[r7] = r2
            r2 = r1[r8]
            r3 = r3[r8]
            int r2 = r2 + r3
            r1[r8] = r2
            android.view.ViewParent r1 = r0.getParent()
            r1.requestDisallowInterceptTouchEvent(r8)
        L_0x0175:
            int[] r1 = r0.mScrollOffset
            r2 = r1[r7]
            int r12 = r12 - r2
            r0.mLastTouchX = r12
            r1 = r1[r8]
            int r13 = r13 - r1
            r0.mLastTouchY = r13
            int r1 = r6.getFlags()
            r2 = 33554432(0x2000000, float:9.403955E-38)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0195
            android.view.VelocityTracker r1 = r0.mVelocityTracker
            r1.addMovement(r11)
            r0.mIsSkipMoveEvent = r8
            r11.recycle()
            return r7
        L_0x0195:
            if (r9 == 0) goto L_0x0199
            r1 = r14
            goto L_0x019a
        L_0x0199:
            r1 = r7
        L_0x019a:
            if (r10 == 0) goto L_0x019e
            r2 = r15
            goto L_0x019f
        L_0x019e:
            r2 = r7
        L_0x019f:
            boolean r1 = r0.scrollByInternal(r1, r2, r6, r7)
            if (r1 == 0) goto L_0x01ac
            android.view.ViewParent r1 = r0.getParent()
            r1.requestDisallowInterceptTouchEvent(r8)
        L_0x01ac:
            androidx.recyclerview.widget.GapWorker r1 = r0.mGapWorker
            if (r1 == 0) goto L_0x0281
            if (r14 != 0) goto L_0x01b4
            if (r15 == 0) goto L_0x0281
        L_0x01b4:
            r1.postFromTraversal(r0, r14, r15)
            goto L_0x0281
        L_0x01b9:
            android.view.VelocityTracker r1 = r0.mVelocityTracker
            r1.addMovement(r11)
            android.view.VelocityTracker r1 = r0.mVelocityTracker
            int r2 = r0.mMaxFlingVelocity
            float r2 = (float) r2
            r3 = 1000(0x3e8, float:1.401E-42)
            r1.computeCurrentVelocity(r3, r2)
            r1 = 0
            if (r9 == 0) goto L_0x01d5
            android.view.VelocityTracker r2 = r0.mVelocityTracker
            int r3 = r0.mScrollPointerId
            float r2 = r2.getXVelocity(r3)
            float r2 = -r2
            goto L_0x01d6
        L_0x01d5:
            r2 = r1
        L_0x01d6:
            if (r10 == 0) goto L_0x01e2
            android.view.VelocityTracker r3 = r0.mVelocityTracker
            int r4 = r0.mScrollPointerId
            float r3 = r3.getYVelocity(r4)
            float r3 = -r3
            goto L_0x01e3
        L_0x01e2:
            r3 = r1
        L_0x01e3:
            int r4 = (r2 > r1 ? 1 : (r2 == r1 ? 0 : -1))
            if (r4 != 0) goto L_0x01eb
            int r1 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r1 == 0) goto L_0x01f3
        L_0x01eb:
            int r1 = (int) r2
            int r2 = (int) r3
            boolean r1 = r0.fling(r1, r2)
            if (r1 != 0) goto L_0x01f6
        L_0x01f3:
            r0.setScrollState(r7)
        L_0x01f6:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "onTouchUp() velocity : "
            r1.<init>(r2)
            r1.append(r3)
            java.lang.String r2 = ", last move skip : "
            r1.append(r2)
            boolean r2 = r0.mIsSkipMoveEvent
            r1.append(r2)
            java.lang.String r2 = "("
            r1.append(r2)
            float r2 = r0.mFrameLatency
            r1.append(r2)
            java.lang.String r2 = "), use scroller : "
            r1.append(r2)
            androidx.recyclerview.widget.RecyclerView$ViewFlinger r2 = r0.mViewFlinger
            android.widget.OverScroller r2 = r2.mOverScroller
            java.lang.Class r2 = r2.getClass()
            java.lang.String r2 = r2.getName()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.util.Log.i(r12, r1)
            r0.resetScroll()
            goto L_0x0286
        L_0x0233:
            int r1 = r6.getPointerId(r7)
            r0.mScrollPointerId = r1
            float r1 = r6.getX()
            float r1 = r1 + r13
            int r1 = (int) r1
            r0.mLastTouchX = r1
            r0.mInitialTouchX = r1
            float r1 = r6.getY()
            float r1 = r1 + r13
            int r1 = (int) r1
            r0.mLastTouchY = r1
            r0.mInitialTouchY = r1
            boolean r1 = sVerboseLoggingEnabled
            if (r1 == 0) goto L_0x0273
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "onTouch DOWN mInitialTouchX["
            r1.<init>(r2)
            int r2 = r0.mInitialTouchX
            r1.append(r2)
            java.lang.String r2 = "] mInitialTouchY["
            r1.append(r2)
            int r2 = r0.mInitialTouchY
            r1.append(r2)
            java.lang.String r2 = "] "
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.util.Log.d(r12, r1)
        L_0x0273:
            boolean r1 = r0.mHasNestedScrollRange
            if (r1 == 0) goto L_0x027a
            r0.adjustNestedScrollRange()
        L_0x027a:
            if (r10 == 0) goto L_0x027e
            r9 = r9 | 2
        L_0x027e:
            r0.startNestedScroll(r9, r7)
        L_0x0281:
            android.view.VelocityTracker r0 = r0.mVelocityTracker
            r0.addMovement(r11)
        L_0x0286:
            r11.recycle()
            return r8
        L_0x028a:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void postAnimationRunner() {
        if (!this.mPostedAnimatorRunner && this.mIsAttached) {
            ViewCompat.postOnAnimation(this, this.mItemAnimatorRunner);
            this.mPostedAnimatorRunner = true;
        }
    }

    public void processDataSetCompletelyChanged(boolean z) {
        this.mDispatchItemsChangedEvent = z | this.mDispatchItemsChangedEvent;
        this.mDataSetHasChangedAfterLayout = true;
        markKnownViewsInvalid();
    }

    public void recordAnimationInfoIfBouncedHiddenView(ViewHolder viewHolder, ItemAnimator.ItemHolderInfo itemHolderInfo) {
        viewHolder.setFlags(0, SerializeOptions.SORT);
        if (this.mState.mTrackOldChangeHolders && viewHolder.isUpdated() && !viewHolder.isRemoved() && !viewHolder.shouldIgnore()) {
            this.mViewInfoStore.addToOldChangeHolders(getChangedHolderKey(viewHolder), viewHolder);
        }
        this.mViewInfoStore.addToPreLayout(viewHolder, itemHolderInfo);
    }

    public void removeAndRecycleViews() {
        ItemAnimator itemAnimator = this.mItemAnimator;
        if (itemAnimator != null) {
            itemAnimator.endAnimations();
        }
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.removeAndRecycleAllViews(this.mRecycler);
        }
        LayoutManager layoutManager2 = this.mLayout;
        if (layoutManager2 != null) {
            layoutManager2.removeAndRecycleScrapInt(this.mRecycler);
        }
        this.mRecycler.clear();
    }

    public boolean removeAnimatingView(View view) {
        startInterceptRequestLayout();
        boolean removeViewIfHidden = this.mChildHelper.removeViewIfHidden(view);
        if (removeViewIfHidden) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(view);
            this.mRecycler.unscrapView(childViewHolderInt);
            this.mRecycler.recycleViewHolderInternal(childViewHolderInt);
            if (sVerboseLoggingEnabled) {
                Log.d("SeslRecyclerView", "after removing animated view: " + view + ArcCommonLog.TAG_COMMA + this);
            }
        }
        stopInterceptRequestLayout(!removeViewIfHidden);
        return removeViewIfHidden;
    }

    public void removeDetachedView(View view, boolean z) {
        ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            if (childViewHolderInt.isTmpDetached()) {
                childViewHolderInt.clearTmpDetachFlag();
            } else if (!childViewHolderInt.shouldIgnore()) {
                StringBuilder sb2 = new StringBuilder("Called removeDetachedView with a view which is not flagged as tmp detached.");
                sb2.append(childViewHolderInt);
                throw new IllegalArgumentException(C0086a.k(this, sb2));
            }
        } else if (sDebugAssertionsEnabled) {
            StringBuilder sb3 = new StringBuilder("No ViewHolder found for child: ");
            sb3.append(view);
            throw new IllegalArgumentException(C0086a.k(this, sb3));
        }
        view.clearAnimation();
        dispatchChildDetached(view);
        super.removeDetachedView(view, z);
    }

    public void removeItemDecoration(ItemDecoration itemDecoration) {
        boolean z;
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.assertNotInLayoutOrScroll("Cannot remove item decoration during a scroll  or layout");
        }
        this.mItemDecorations.remove(itemDecoration);
        if (this.mItemDecorations.isEmpty()) {
            if (getOverScrollMode() == 2) {
                z = true;
            } else {
                z = false;
            }
            setWillNotDraw(z);
        }
        markItemDecorInsetsDirty();
        requestLayout();
    }

    public void removeOnChildAttachStateChangeListener(OnChildAttachStateChangeListener onChildAttachStateChangeListener) {
        List<OnChildAttachStateChangeListener> list = this.mOnChildAttachStateListeners;
        if (list != null) {
            list.remove(onChildAttachStateChangeListener);
        }
    }

    public void removeOnItemTouchListener(OnItemTouchListener onItemTouchListener) {
        this.mOnItemTouchListeners.remove(onItemTouchListener);
        if (this.mInterceptingOnItemTouchListener == onItemTouchListener) {
            this.mInterceptingOnItemTouchListener = null;
        }
    }

    public void removeOnScrollListener(OnScrollListener onScrollListener) {
        List<OnScrollListener> list = this.mScrollListeners;
        if (list != null) {
            list.remove(onScrollListener);
        }
    }

    public void repositionShadowingViews() {
        ViewHolder viewHolder;
        int childCount = this.mChildHelper.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = this.mChildHelper.getChildAt(i2);
            ViewHolder childViewHolder = getChildViewHolder(childAt);
            if (!(childViewHolder == null || (viewHolder = childViewHolder.mShadowingHolder) == null)) {
                View view = viewHolder.itemView;
                int left = childAt.getLeft();
                int top = childAt.getTop();
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
    }

    public void requestChildFocus(View view, View view2) {
        if (!this.mLayout.onRequestChildFocus(this, this.mState, view, view2) && view2 != null) {
            requestChildOnScreen(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z, int i2) {
        if (i2 != 3) {
            return this.mLayout.requestChildRectangleOnScreen(this, view, rect, z);
        }
        return false;
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        int size = this.mOnItemTouchListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mOnItemTouchListeners.get(i2).onRequestDisallowInterceptTouchEvent(z);
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    public void requestLayout() {
        if (this.mInterceptRequestLayoutDepth != 0 || this.mLayoutSuppressed) {
            this.mLayoutWasDefered = true;
        } else {
            super.requestLayout();
        }
    }

    public void saveOldPositions() {
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        int i2 = 0;
        while (i2 < unfilteredChildCount) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i2));
            if (!sDebugAssertionsEnabled || childViewHolderInt.mPosition != -1 || childViewHolderInt.isRemoved()) {
                if (!childViewHolderInt.shouldIgnore()) {
                    childViewHolderInt.saveOldPosition();
                }
                i2++;
            } else {
                throw new IllegalStateException(C0086a.k(this, new StringBuilder("view holder cannot have position -1 unless it is removed")));
            }
        }
    }

    public void scrollBy(int i2, int i7) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null) {
            Log.e("SeslRecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (!this.mLayoutSuppressed) {
            boolean canScrollHorizontally = layoutManager.canScrollHorizontally();
            boolean canScrollVertically = this.mLayout.canScrollVertically();
            if (canScrollHorizontally || canScrollVertically) {
                if (!canScrollHorizontally) {
                    i2 = 0;
                }
                if (!canScrollVertically) {
                    i7 = 0;
                }
                scrollByInternal(i2, i7, (MotionEvent) null, 0);
            }
        }
    }

    public boolean scrollByInternal(int i2, int i7, MotionEvent motionEvent, int i8) {
        int i10;
        int i11;
        int i12;
        int i13;
        boolean z;
        int i14 = i2;
        int i15 = i7;
        MotionEvent motionEvent2 = motionEvent;
        consumePendingUpdateOperations();
        if (this.mAdapter != null) {
            int[] iArr = this.mReusableIntPair;
            iArr[0] = 0;
            iArr[1] = 0;
            scrollStep(i14, i15, iArr);
            int[] iArr2 = this.mReusableIntPair;
            int i16 = iArr2[0];
            int i17 = iArr2[1];
            i11 = i14 - i16;
            i10 = i15 - i17;
            int i18 = i16;
            i12 = i17;
            i13 = i18;
        } else {
            i13 = 0;
            i12 = 0;
            i11 = 0;
            i10 = 0;
        }
        if (!this.mItemDecorations.isEmpty()) {
            invalidate();
        }
        int[] iArr3 = this.mReusableIntPair;
        iArr3[0] = 0;
        iArr3[1] = 0;
        if (!seslDispatchNestedScroll(i13, i12, i11, i10, this.mScrollOffset, i8, iArr3)) {
            int[] iArr4 = this.mScrollOffset;
            iArr4[0] = 0;
            iArr4[1] = 0;
        }
        int[] iArr5 = this.mReusableIntPair;
        int i19 = iArr5[0];
        int i20 = i11 - i19;
        int i21 = iArr5[1];
        int i22 = i10 - i21;
        if (i19 == 0 && i21 == 0) {
            z = false;
        } else {
            z = true;
        }
        int i23 = this.mLastTouchX;
        int[] iArr6 = this.mScrollOffset;
        int i24 = iArr6[0];
        this.mLastTouchX = i23 - i24;
        int i25 = this.mLastTouchY;
        int i26 = iArr6[1];
        this.mLastTouchY = i25 - i26;
        int[] iArr7 = this.mNestedOffsets;
        iArr7[0] = iArr7[0] + i24;
        iArr7[1] = iArr7[1] + i26;
        if (this.mIsEdgeEffectEnabled && !this.mPreventFirstGlow && getOverScrollMode() != 2) {
            if (motionEvent2 != null && !MotionEventCompat.isFromSource(motionEvent2, 8194)) {
                pullGlows(motionEvent2.getX(), (float) i20, motionEvent2.getY(), (float) i22);
            }
            considerReleasingGlowsOnScroll(i2, i7);
        }
        if (!(i13 == 0 && i12 == 0)) {
            dispatchOnScrolled(i13, i12);
        }
        if (!awakenScrollBars()) {
            invalidate();
        }
        if ((this.mLayout instanceof StaggeredGridLayoutManager) && (!canScrollVertically(-1) || !canScrollVertically(1))) {
            this.mLayout.onScrollStateChanged(0);
        }
        this.mPreventFirstGlow = false;
        if (!z && i13 == 0 && i12 == 0) {
            return false;
        }
        return true;
    }

    public void scrollStep(int i2, int i7, int[] iArr) {
        int i8;
        int i10;
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        TraceCompat.beginSection("RV Scroll");
        fillRemainingScrollValues(this.mState);
        if (i2 != 0) {
            i8 = this.mLayout.scrollHorizontallyBy(i2, this.mRecycler, this.mState);
        } else {
            i8 = 0;
        }
        if (i7 != 0) {
            i10 = this.mLayout.scrollVerticallyBy(i7, this.mRecycler, this.mState);
            SeslGoToTopController seslGoToTopController = this.mGoToTopController;
            if (seslGoToTopController != null && seslGoToTopController.getState() == 0) {
                showGoToTop();
            }
        } else {
            i10 = 0;
        }
        TraceCompat.endSection();
        repositionShadowingViews();
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
        if (iArr != null) {
            iArr[0] = i8;
            iArr[1] = i10;
        }
    }

    public void scrollTo(int i2, int i7) {
        Log.w("SeslRecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    public void scrollToPosition(int i2) {
        if (!this.mLayoutSuppressed) {
            stopScroll();
            LayoutManager layoutManager = this.mLayout;
            if (layoutManager == null) {
                Log.e("SeslRecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
                return;
            }
            layoutManager.scrollToPosition(i2);
            awakenScrollBars();
        }
    }

    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (!shouldDeferAccessibilityEvent(accessibilityEvent)) {
            super.sendAccessibilityEventUnchecked(accessibilityEvent);
        }
    }

    public View seslFindNearChildViewUnder(float f, float f5) {
        int i2 = (int) (f + 0.5f);
        int i7 = (int) (0.5f + f5);
        int childCount = this.mChildHelper.getChildCount() - 1;
        int i8 = 0;
        int i10 = i7;
        int i11 = Integer.MAX_VALUE;
        for (int i12 = childCount; i12 >= 0; i12--) {
            View childAt = getChildAt(i12);
            if (childAt != null) {
                int bottom = (childAt.getBottom() + childAt.getTop()) / 2;
                if (i8 != bottom) {
                    int abs = Math.abs(i7 - bottom);
                    if (abs < i11) {
                        i11 = abs;
                        i8 = bottom;
                        i10 = i8;
                    } else if (!(this.mLayout instanceof StaggeredGridLayoutManager)) {
                        break;
                    } else {
                        i8 = bottom;
                    }
                } else {
                    continue;
                }
            }
        }
        int i13 = -1;
        int i14 = Integer.MAX_VALUE;
        int i15 = Integer.MAX_VALUE;
        int i16 = -1;
        while (childCount >= 0) {
            View childAt2 = getChildAt(childCount);
            if (childAt2 != null) {
                int top = childAt2.getTop();
                int bottom2 = childAt2.getBottom();
                int left = childAt2.getLeft();
                int right = childAt2.getRight();
                if (i10 >= top && i10 <= bottom2) {
                    int abs2 = Math.abs(i2 - left);
                    int abs3 = Math.abs(i2 - right);
                    if (abs2 <= i14) {
                        i13 = childCount;
                        i14 = abs2;
                    }
                    if (abs3 <= i15) {
                        i16 = childCount;
                        i15 = abs3;
                    }
                }
                if (i10 > bottom2 || childCount == 0) {
                    if (i14 < i15) {
                        return this.mChildHelper.getChildAt(i13);
                    }
                    return this.mChildHelper.getChildAt(i16);
                }
            }
            childCount--;
        }
        Log.e("SeslRecyclerView", "findNearChildViewUnder didn't find valid child view! " + f + ArcCommonLog.TAG_COMMA + f5);
        return null;
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

    public int seslGetGoToTopDefaultBottomPadding() {
        SeslGoToTopController seslGoToTopController = this.mGoToTopController;
        if (seslGoToTopController != null) {
            return seslGoToTopController.getDefaultBottomPadding();
        }
        return 0;
    }

    public SeslGoToTopImageView seslGetGoToTopView() {
        SeslGoToTopController seslGoToTopController = this.mGoToTopController;
        if (seslGoToTopController != null) {
            return seslGoToTopController.getView();
        }
        return null;
    }

    public void seslHideBottomFadingEdge(boolean z) {
        this.mFadingEdgeHelper.hideBottomFadingEdge(z);
    }

    public void seslHideTopFadingEdge(boolean z) {
        this.mFadingEdgeHelper.hideTopFadingEdge(z);
    }

    public void seslInitConfigurations(Context context) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        Resources resources = context.getResources();
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mTouchSlop2 = viewConfiguration.getScaledTouchSlop();
        this.mPagingTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
        this.mScaledHorizontalScrollFactor = ViewConfigurationCompat.getScaledHorizontalScrollFactor(viewConfiguration, context);
        this.mScaledVerticalScrollFactor = ViewConfigurationCompat.getScaledVerticalScrollFactor(viewConfiguration, context);
        this.mMinFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaxFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mHoverDefaultTopAreaHeight = (int) (TypedValue.applyDimension(1, 25.0f, resources.getDisplayMetrics()) + 0.5f);
        this.mHoverDefaultBottomAreaHeight = (int) (TypedValue.applyDimension(1, 25.0f, resources.getDisplayMetrics()) + 0.5f);
    }

    public boolean seslIsFastScrollerEnabled() {
        return false;
    }

    public boolean seslIsIndexTipEnabled() {
        return this.mIndexTipEnabled;
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

    public void seslSetCtrlkeyPressed(boolean z) {
        this.mIsCtrlKeyPressed = z;
    }

    public void seslSetFadingEdgeColor(int i2) {
        this.mFadingEdgeHelper.setFadingEdgeColor(i2, new C0185a(this, 0));
        invalidate();
    }

    public void seslSetFadingEdgeEnabled(boolean z, int i2, int i7) {
        boolean z3 = z;
        applyFadingEdge(z3, new J6.a(this, z3, i2, i7, 2));
    }

    public void seslSetFillBottomColor(int i2) {
        this.mRectPaint.setColor(i2);
        this.mRoundedCorner.setRoundedCornerColor(12, i2);
    }

    public void seslSetFillBottomEnabled(boolean z) {
        if (this.mLayout instanceof LinearLayoutManager) {
            this.mDrawRect = z;
            requestLayout();
        }
    }

    public void seslSetFillHorizontalPaddingEnabled(boolean z) {
        int i2;
        this.mDrawHorizontalPadding = z;
        if (z) {
            i2 = getResources().getDimensionPixelOffset(R$dimen.sesl_system_scroller_vertical_padding);
        } else {
            i2 = 0;
        }
        this.mScrollbarTopPadding = i2;
        this.mScrollbarBottomPadding = i2;
        updateScrollbarVerticalPadding();
        requestLayout();
    }

    public void seslSetFloatingBottomLayoutHeight(int i2) {
        this.mSeslBottomBarHeight = i2;
    }

    public void seslSetGoToTopBottomPadding(int i2) {
        SeslGoToTopController seslGoToTopController = this.mGoToTopController;
        if (seslGoToTopController != null) {
            seslGoToTopController.setBottomPadding(i2);
        }
    }

    public void seslSetGoToTopEnabled(boolean z) {
        seslSetGoToTopEnabled(z, SeslMisc.isLightTheme(this.mContext));
    }

    public void seslSetGoToTopPaddingHorizontal(int i2, int i7) {
        SeslGoToTopController seslGoToTopController = this.mGoToTopController;
        if (seslGoToTopController != null) {
            seslGoToTopController.setPaddingHorizontal(i2, i7);
        }
    }

    public void seslSetHoverBottomPadding(int i2) {
        int max = Math.max(0, i2);
        if (this.mHoverBottomAreaHeight != max) {
            this.mHoverBottomAreaHeight = max;
        }
    }

    public void seslSetHoverScrollEnabled(boolean z) {
        this.mHoverScrollEnable = z;
        this.mHoverScrollStateChanged = true;
    }

    public void seslSetHoverTopPadding(int i2) {
        int max = Math.max(0, i2);
        if (this.mHoverTopAreaHeight != max) {
            this.mHoverTopAreaHeight = max;
        }
    }

    public void seslSetImmersiveScrollBottomPadding(int i2) {
        SeslGoToTopController seslGoToTopController;
        if (i2 >= 0 && (seslGoToTopController = this.mGoToTopController) != null) {
            seslGoToTopController.setImmersiveBottomPadding(i2);
        }
    }

    public void seslSetIndexTipEnabled(boolean z) {
        if (this.mAdapter instanceof SectionIndexer) {
            if (z) {
                IndexTip indexTip = this.mIndexTip;
                if (indexTip == null) {
                    this.mIndexTip = new IndexTip(this.mContext);
                } else {
                    indexTip.hide();
                }
                if (!this.mIndexTipEnabled) {
                    getOverlay().add(this.mIndexTip);
                }
                this.mIndexTip.setLayout(0, this.mScrollBarTopOffset, getRight(), getBottom(), getPaddingLeft() + this.mIndexTip.getPaddingLeft(), getPaddingRight() + this.mIndexTip.getPaddingRight());
            } else if (this.mIndexTipEnabled) {
                getOverlay().remove(this.mIndexTip);
            }
            this.mIndexTipEnabled = z;
            return;
        }
        throw new IllegalStateException("In order to use Index Tip, your Adapter has to implements SectionIndexer. or check if setAdapter is preceded.");
    }

    public void seslSetIndexTipPaddingHorizontal(int i2, int i7) {
        IndexTip indexTip = this.mIndexTip;
        if (indexTip != null) {
            indexTip.setPadding(i2, indexTip.getPaddingTop(), i7, this.mIndexTip.getPaddingBottom());
            this.mIndexTip.setLayout(0, this.mScrollBarTopOffset, getRight(), getBottom(), getPaddingLeft() + this.mIndexTip.getPaddingLeft(), getPaddingRight() + this.mIndexTip.getPaddingRight());
        }
    }

    public void seslSetLastRoundedCorner(boolean z) {
        this.mDrawLastRoundedCorner = z;
    }

    public void seslSetLongPressMultiSelectionListener(SeslLongPressMultiSelectionListener seslLongPressMultiSelectionListener) {
        this.mLongPressMultiSelectionListener = seslLongPressMultiSelectionListener;
    }

    public void seslSetOnGoToTopClickListener(SeslOnGoToTopClickListener seslOnGoToTopClickListener) {
        this.mOnGoToTopClickListener = seslOnGoToTopClickListener;
    }

    public void seslSetPagingTouchSlopForStylus(boolean z) {
        this.mUsePagingTouchSlopForStylus = z;
    }

    public void seslSetPenSelectionEnabled(boolean z) {
        this.mIsPenSelectionEnabled = z;
    }

    public void seslSetRecoilEnabled(boolean z) {
        if (this.mIsRecoilEnabled != z) {
            this.mIsRecoilEnabled = z;
        }
    }

    public void seslSetScrollBarBottomOffset(int i2) {
        int i7 = i2 - this.mScrollBarTopOffset;
        if (this.mScrollBarBottomOffset != i7) {
            this.mScrollBarBottomOffset = Math.max(i7, 0);
            updateScrollbarVerticalPadding();
        }
    }

    public void seslSetScrollBarTopOffset(int i2) {
        IndexTip indexTip;
        if (this.mScrollBarTopOffset != i2) {
            this.mScrollBarTopOffset = Math.max(i2, 0);
            updateScrollbarVerticalPadding();
            if (this.mIndexTipEnabled && (indexTip = this.mIndexTip) != null) {
                indexTip.setLayout(0, this.mScrollBarTopOffset, getRight(), getBottom(), getPaddingLeft() + this.mIndexTip.getPaddingLeft(), getPaddingRight() + this.mIndexTip.getPaddingRight());
            }
        }
    }

    public void seslUpdateIndexTipSections() {
        IndexTip indexTip = this.mIndexTip;
        if (indexTip != null) {
            indexTip.updateSections();
        }
    }

    public void setAccessibilityDelegateCompat(RecyclerViewAccessibilityDelegate recyclerViewAccessibilityDelegate) {
        this.mAccessibilityDelegate = recyclerViewAccessibilityDelegate;
        ViewCompat.setAccessibilityDelegate(this, recyclerViewAccessibilityDelegate);
    }

    public void setAdapter(Adapter adapter) {
        setLayoutFrozen(false);
        setAdapterInternal(adapter, false, true);
        processDataSetCompletelyChanged(false);
        requestLayout();
    }

    public void setChildDrawingOrderCallback(ChildDrawingOrderCallback childDrawingOrderCallback) {
        boolean z;
        if (childDrawingOrderCallback != this.mChildDrawingOrderCallback) {
            this.mChildDrawingOrderCallback = childDrawingOrderCallback;
            if (childDrawingOrderCallback != null) {
                z = true;
            } else {
                z = false;
            }
            setChildrenDrawingOrderEnabled(z);
        }
    }

    public boolean setChildImportantForAccessibilityInternal(ViewHolder viewHolder, int i2) {
        if (isComputingLayout()) {
            viewHolder.mPendingAccessibilityState = i2;
            this.mPendingAccessibilityImportanceChange.add(viewHolder);
            return false;
        }
        ViewCompat.setImportantForAccessibility(viewHolder.itemView, i2);
        return true;
    }

    public void setClipToPadding(boolean z) {
        if (z != this.mClipToPadding) {
            invalidateGlows();
        }
        this.mClipToPadding = z;
        super.setClipToPadding(z);
        if (this.mFirstLayoutComplete) {
            requestLayout();
        }
    }

    public void setEdgeEffectEnabled(boolean z) {
        if (this.mIsEdgeEffectEnabled != z) {
            this.mIsEdgeEffectEnabled = z;
        }
    }

    public void setEdgeEffectFactory(EdgeEffectFactory edgeEffectFactory) {
        Preconditions.checkNotNull(edgeEffectFactory);
        this.mEdgeEffectFactory = edgeEffectFactory;
        invalidateGlows();
    }

    public void setHasFixedSize(boolean z) {
        this.mHasFixedSize = z;
    }

    public void setItemAnimator(ItemAnimator itemAnimator) {
        ItemAnimator itemAnimator2 = this.mItemAnimator;
        if (itemAnimator2 != null) {
            itemAnimator2.endAnimations();
            this.mItemAnimator.setListener((ItemAnimator.ItemAnimatorListener) null);
        }
        this.mItemAnimator = itemAnimator;
        if (itemAnimator != null) {
            itemAnimator.setListener(this.mItemAnimatorListener);
            this.mItemAnimator.setHostView(this);
        }
    }

    public void setItemViewCacheSize(int i2) {
        this.mRecycler.setViewCacheSize(i2);
    }

    @Deprecated
    public void setLayoutFrozen(boolean z) {
        suppressLayout(z);
    }

    public void setLayoutManager(LayoutManager layoutManager) {
        boolean z;
        if (layoutManager != this.mLayout) {
            boolean z3 = layoutManager instanceof LinearLayoutManager;
            boolean z7 = false;
            if (!this.mDrawRect || !z3) {
                z = false;
            } else {
                z = true;
            }
            this.mDrawRect = z;
            if (this.mDrawLastRoundedCorner && z3) {
                z7 = true;
            }
            this.mDrawLastRoundedCorner = z7;
            stopScroll();
            if (this.mLayout != null) {
                ItemAnimator itemAnimator = this.mItemAnimator;
                if (itemAnimator != null) {
                    itemAnimator.endAnimations();
                }
                this.mLayout.removeAndRecycleAllViews(this.mRecycler);
                this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
                this.mRecycler.clear();
                if (this.mIsAttached) {
                    this.mLayout.dispatchDetachedFromWindow(this, this.mRecycler);
                }
                this.mLayout.setRecyclerView((RecyclerView) null);
                this.mLayout = null;
            } else {
                this.mRecycler.clear();
            }
            this.mChildHelper.removeAllViewsUnfiltered();
            this.mLayout = layoutManager;
            if (layoutManager != null) {
                if (layoutManager.mRecyclerView == null) {
                    layoutManager.setRecyclerView(this);
                    if (this.mIsAttached) {
                        this.mLayout.dispatchAttachedToWindow(this);
                    }
                } else {
                    StringBuilder sb2 = new StringBuilder("LayoutManager ");
                    sb2.append(layoutManager);
                    sb2.append(" is already attached to a RecyclerView:");
                    throw new IllegalArgumentException(C0086a.k(layoutManager.mRecyclerView, sb2));
                }
            }
            this.mRecycler.updateViewCacheSize();
            requestLayout();
        }
    }

    @Deprecated
    public void setLayoutTransition(LayoutTransition layoutTransition) {
        if (layoutTransition == null) {
            super.setLayoutTransition((LayoutTransition) null);
            return;
        }
        throw new IllegalArgumentException("Providing a LayoutTransition into RecyclerView is not supported. Please use setItemAnimator() instead for animating changes to the items in this RecyclerView");
    }

    public void setNestedScrollingEnabled(boolean z) {
        getScrollingChildHelper().setNestedScrollingEnabled(z);
    }

    public void setOnFlingListener(OnFlingListener onFlingListener) {
        this.mOnFlingListener = onFlingListener;
    }

    @Deprecated
    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.mScrollListener = onScrollListener;
    }

    public void setPreserveFocusAfterLayout(boolean z) {
        this.mPreserveFocusAfterLayout = z;
    }

    public void setRecycledViewPool(RecycledViewPool recycledViewPool) {
        this.mRecycler.setRecycledViewPool(recycledViewPool);
    }

    public void setScrollBarStyle(int i2) {
        super.setScrollBarStyle(i2);
    }

    public void setScrollState(int i2) {
        IndexTip indexTip;
        if (i2 != this.mScrollState) {
            StringBuilder o2 = C0086a.o(i2, "setting scroll state to ", " from ");
            o2.append(this.mScrollState);
            Log.d("SeslRecyclerView", o2.toString());
            if (sVerboseLoggingEnabled) {
                StringBuilder o3 = C0086a.o(i2, "setting scroll state to ", " from ");
                o3.append(this.mScrollState);
                Log.d("SeslRecyclerView", o3.toString(), new Exception());
            }
            this.mScrollState = i2;
            if (i2 != 2) {
                stopScrollersInternal();
            }
            dispatchOnScrollStateChanged(i2);
            if (i2 == 1) {
                this.mEdgeEffectByDragging = false;
            }
            if (i2 == 0 && this.mIndexTipEnabled && (indexTip = this.mIndexTip) != null) {
                indexTip.hide();
            }
        }
    }

    public void setScrollingTouchSlop(int i2) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        Log.d("SeslRecyclerView", "setScrollingTouchSlop(): slopConstant[" + i2 + "]");
        seslSetPagingTouchSlopForStylus(false);
        if (i2 != 0) {
            if (i2 != 1) {
                Log.w("SeslRecyclerView", "setScrollingTouchSlop(): bad argument constant " + i2 + "; using default value");
            } else {
                this.mTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
                return;
            }
        }
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
    }

    public void setViewCacheExtension(ViewCacheExtension viewCacheExtension) {
        this.mRecycler.setViewCacheExtension(viewCacheExtension);
    }

    public boolean shouldDeferAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        int i2;
        int i7 = 0;
        if (!isComputingLayout()) {
            return false;
        }
        if (accessibilityEvent != null) {
            i2 = AccessibilityEventCompat.getContentChangeTypes(accessibilityEvent);
        } else {
            i2 = 0;
        }
        if (i2 != 0) {
            i7 = i2;
        }
        this.mEatenAccessibilityChangeFlags |= i7;
        return true;
    }

    public void showGoToTop() {
        SeslGoToTopController seslGoToTopController = this.mGoToTopController;
        if (seslGoToTopController != null) {
            seslGoToTopController.showIfNeeded();
        }
    }

    public boolean showPointerIcon(MotionEvent motionEvent, int i2) {
        PointerIcon pointerIcon;
        if (SeslPointerIconCompat.isSemStylusDefault(i2)) {
            pointerIcon = null;
        } else {
            pointerIcon = PointerIcon.getSystemIcon(this.mContext, i2);
        }
        SeslViewReflector.semSetPointerIcon(this, motionEvent.getToolType(0), pointerIcon);
        return true;
    }

    public void smoothScrollBy(int i2, int i7) {
        smoothScrollBy(i2, i7, (Interpolator) null);
    }

    public void smoothScrollToPosition(int i2) {
        if (!this.mLayoutSuppressed) {
            LayoutManager layoutManager = this.mLayout;
            if (layoutManager == null) {
                Log.e("SeslRecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            } else {
                layoutManager.smoothScrollToPosition(this, this.mState, i2);
            }
        }
    }

    public void smoothScrollToPositionJumpIfNeeded(final int i2) {
        boolean z;
        int i7;
        int i8;
        int findFirstVisibleItemPosition = findFirstVisibleItemPosition();
        if (findFirstVisibleItemPosition > i2) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            i7 = findFirstVisibleItemPosition;
        } else {
            i7 = findLastVisibleItemPosition();
        }
        int childCount = getChildCount() * 2;
        if (z) {
            i8 = 1;
        } else {
            i8 = -1;
        }
        int abs = Math.abs((i8 * i2) + childCount);
        if (computeVerticalScrollOffset() != 0) {
            stopScroll();
        }
        if (Settings.System.getInt(getContext().getContentResolver(), "remove_animations", 0) == 1) {
            scrollToPosition(0);
            return;
        }
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            ((StaggeredGridLayoutManager) layoutManager).scrollToPositionWithOffset(i2, 0);
            return;
        }
        if (findFirstVisibleItemPosition > 0 && ((z && abs > 0 && abs < i7) || (!z && abs > 0 && abs > i7))) {
            jumpToPosition(abs);
        }
        post(new Runnable() {
            public void run() {
                RecyclerView recyclerView = RecyclerView.this;
                if (!recyclerView.mLayoutSuppressed) {
                    LayoutManager layoutManager = recyclerView.mLayout;
                    if (layoutManager == null) {
                        Log.e("SeslRecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
                    } else if (layoutManager instanceof LinearLayoutManager) {
                        ((LinearLayoutManager) layoutManager).smoothScrollToPositionJumpIfNeeded(recyclerView, recyclerView.mState, i2);
                    } else {
                        layoutManager.smoothScrollToPosition(recyclerView, recyclerView.mState, i2);
                    }
                }
            }
        });
    }

    public void startInterceptRequestLayout() {
        int i2 = this.mInterceptRequestLayoutDepth + 1;
        this.mInterceptRequestLayoutDepth = i2;
        if (i2 == 1 && !this.mLayoutSuppressed) {
            this.mLayoutWasDefered = false;
        }
    }

    public boolean startNestedScroll(int i2) {
        return getScrollingChildHelper().startNestedScroll(i2);
    }

    public void stopInterceptRequestLayout(boolean z) {
        if (this.mInterceptRequestLayoutDepth < 1) {
            if (!sDebugAssertionsEnabled) {
                this.mInterceptRequestLayoutDepth = 1;
            } else {
                throw new IllegalStateException(C0086a.k(this, new StringBuilder("stopInterceptRequestLayout was called more times than startInterceptRequestLayout.")));
            }
        }
        if (!z && !this.mLayoutSuppressed) {
            this.mLayoutWasDefered = false;
        }
        if (this.mInterceptRequestLayoutDepth == 1) {
            if (z && this.mLayoutWasDefered && !this.mLayoutSuppressed && this.mLayout != null && this.mAdapter != null) {
                dispatchLayout();
            }
            if (!this.mLayoutSuppressed) {
                this.mLayoutWasDefered = false;
            }
        }
        this.mInterceptRequestLayoutDepth--;
    }

    public void stopNestedScroll() {
        getScrollingChildHelper().stopNestedScroll();
    }

    public void stopScroll() {
        setScrollState(0);
        stopScrollersInternal();
    }

    public final void suppressLayout(boolean z) {
        if (z != this.mLayoutSuppressed) {
            assertNotInLayoutOrScroll("Do not suppressLayout in layout or scroll");
            if (!z) {
                this.mLayoutSuppressed = false;
                if (!(!this.mLayoutWasDefered || this.mLayout == null || this.mAdapter == null)) {
                    requestLayout();
                }
                this.mLayoutWasDefered = false;
                return;
            }
            long uptimeMillis = SystemClock.uptimeMillis();
            onTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0));
            this.mLayoutSuppressed = true;
            this.mIgnoreMotionEventTillDown = true;
            stopScroll();
        }
    }

    public boolean verifyDrawable(Drawable drawable) {
        boolean z;
        SeslGoToTopController seslGoToTopController = this.mGoToTopController;
        if (seslGoToTopController != null) {
            z = seslGoToTopController.verifyDrawable(drawable);
        } else {
            z = false;
        }
        if (z || super.verifyDrawable(drawable)) {
            return true;
        }
        return false;
    }

    public void viewRangeUpdate(int i2, int i7, Object obj) {
        int i8;
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        int i10 = i2 + i7;
        for (int i11 = 0; i11 < unfilteredChildCount; i11++) {
            View unfilteredChildAt = this.mChildHelper.getUnfilteredChildAt(i11);
            ViewHolder childViewHolderInt = getChildViewHolderInt(unfilteredChildAt);
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && (i8 = childViewHolderInt.mPosition) >= i2 && i8 < i10) {
                childViewHolderInt.addFlags(2);
                childViewHolderInt.addChangePayload(obj);
                ((LayoutParams) unfilteredChildAt.getLayoutParams()).mInsetsDirty = true;
            }
        }
        this.mRecycler.viewRangeUpdate(i2, i7);
    }

    public RecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.recyclerViewStyle);
    }

    public void onExitLayoutOrScroll(boolean z) {
        int i2 = this.mLayoutOrScrollCounter - 1;
        this.mLayoutOrScrollCounter = i2;
        if (i2 >= 1) {
            return;
        }
        if (!sDebugAssertionsEnabled || i2 >= 0) {
            this.mLayoutOrScrollCounter = 0;
            if (z) {
                dispatchContentChangedIfNecessary();
                dispatchPendingImportantForAccessibilityChanges();
                return;
            }
            return;
        }
        throw new IllegalStateException(C0086a.k(this, new StringBuilder("layout or scroll counter cannot go below zero.Some calls are not matching")));
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        return this.mLayout.requestChildRectangleOnScreen(this, view, rect, z);
    }

    public void seslSetFadingEdgeEnabled(boolean z) {
        applyFadingEdge(z, new c((Object) this, z, 20));
    }

    public void seslSetGoToTopEnabled(boolean z, boolean z3) {
        ensureGoToTopController(z);
        SeslGoToTopController seslGoToTopController = this.mGoToTopController;
        if (seslGoToTopController != null) {
            seslGoToTopController.setEnabled(z, z3);
            if (z) {
                this.mGoToTopController.setOnGoToTopClickListener(new com.samsung.android.sdk.scs.ai.language.a(16, this));
            } else {
                this.mGoToTopController.setOnGoToTopClickListener((SeslGoToTopController.OnGoToTopClickListener) null);
            }
        }
    }

    public void smoothScrollBy(int i2, int i7, Interpolator interpolator) {
        smoothScrollBy(i2, i7, interpolator, Integer.MIN_VALUE);
    }

    public boolean startNestedScroll(int i2, int i7) {
        return getScrollingChildHelper().startNestedScroll(i2, i7);
    }

    public void stopNestedScroll(int i2) {
        getScrollingChildHelper().stopNestedScroll(i2);
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
        Parcelable mLayoutState;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.mLayoutState = parcel.readParcelable(classLoader == null ? LayoutManager.class.getClassLoader() : classLoader);
        }

        public void copyFrom(SavedState savedState) {
            this.mLayoutState = savedState.mLayoutState;
        }

        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeParcelable(this.mLayoutState, 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RecyclerView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        Context context2 = context;
        AttributeSet attributeSet2 = attributeSet;
        int i7 = i2;
        this.mObserver = new RecyclerViewDataObserver();
        this.mRecycler = new Recycler();
        this.mViewInfoStore = new ViewInfoStore();
        this.mUpdateChildViewsRunnable = new Runnable() {
            public void run() {
                RecyclerView recyclerView = RecyclerView.this;
                if (recyclerView.mFirstLayoutComplete && !recyclerView.isLayoutRequested()) {
                    RecyclerView recyclerView2 = RecyclerView.this;
                    if (!recyclerView2.mIsAttached) {
                        recyclerView2.requestLayout();
                    } else if (recyclerView2.mLayoutSuppressed) {
                        recyclerView2.mLayoutWasDefered = true;
                    } else {
                        recyclerView2.consumePendingUpdateOperations();
                    }
                }
            }
        };
        this.mTempRect = new Rect();
        this.mTempRect2 = new Rect();
        this.mTempRectF = new RectF();
        this.mRecyclerListeners = new ArrayList();
        this.mItemDecorations = new ArrayList<>();
        this.mOnItemTouchListeners = new ArrayList<>();
        this.mInterceptRequestLayoutDepth = 0;
        this.mDataSetHasChangedAfterLayout = false;
        this.mDispatchItemsChangedEvent = false;
        this.mLayoutOrScrollCounter = 0;
        this.mDispatchScrollCounter = 0;
        this.mEdgeEffectFactory = sDefaultEdgeEffectFactory;
        this.mItemAnimator = new DefaultItemAnimator();
        this.mScrollState = 0;
        this.mScrollPointerId = -1;
        this.mScaledHorizontalScrollFactor = Float.MIN_VALUE;
        this.mScaledVerticalScrollFactor = Float.MIN_VALUE;
        this.mPreserveFocusAfterLayout = true;
        this.mViewFlinger = new ViewFlinger();
        this.mPrefetchRegistry = ALLOW_THREAD_GAP_WORK ? new GapWorker.LayoutPrefetchRegistryImpl() : null;
        this.mState = new State();
        this.mItemsAddedOrRemoved = false;
        this.mItemsChanged = false;
        this.mItemAnimatorListener = new ItemAnimatorRestoreListener();
        this.mPostedAnimatorRunner = false;
        this.mMinMaxLayoutPositions = new int[2];
        this.mScrollOffset = new int[2];
        this.mNestedOffsets = new int[2];
        this.mWindowOffsets = new int[2];
        this.mEdgeEffectByDragging = false;
        this.ON_ABSORB_VELOCITY = 10000;
        this.mIndexTipEnabled = false;
        this.mIsActionScrollFromMouse = false;
        this.mRecyclerViewOffsets = new int[2];
        this.mMotionEventUpPendingFlag = 33554432;
        this.mIsSkipMoveEvent = false;
        this.mFrameLatency = 16.66f;
        this.mIsNeedCheckLatency = true;
        this.mLastItemAddRemoveAnim = null;
        this.mIsSetOnlyAddAnim = false;
        this.mIsSetOnlyRemoveAnim = false;
        this.mLastItemAnimTop = -1;
        this.mPreventFirstGlow = false;
        this.mIsEdgeEffectEnabled = true;
        this.mSeslIsNested = false;
        this.mAnimListener = new Animator.AnimatorListener() {
            public void onAnimationEnd(Animator animator) {
                ValueAnimator unused = RecyclerView.this.mLastItemAddRemoveAnim = null;
                boolean unused2 = RecyclerView.this.mIsSetOnlyAddAnim = false;
                boolean unused3 = RecyclerView.this.mIsSetOnlyRemoveAnim = false;
                ItemAnimator itemAnimator = RecyclerView.this.getItemAnimator();
                if (itemAnimator instanceof DefaultItemAnimator) {
                    ((DefaultItemAnimator) itemAnimator).clearPendingAnimFlag();
                }
                RecyclerView.this.invalidate();
            }

            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }
        };
        this.mReusableIntPair = new int[2];
        this.mTouchSlop2 = 0;
        this.mPagingTouchSlop = 0;
        this.mUsePagingTouchSlopForStylus = false;
        this.mOnGoToTopClickListener = null;
        this.mSeslOverlayFeatureHeight = 0;
        this.mGoToTopController = null;
        this.mGoToTopHost = new SeslGoToTopController.Host() {
            public boolean canScrollDown() {
                return RecyclerView.this.canScrollDown();
            }

            public boolean canScrollUp() {
                return RecyclerView.this.canScrollUp();
            }

            public Context getContext() {
                return RecyclerView.this.mContext;
            }

            public int getHeight() {
                return RecyclerView.this.getHeight();
            }

            public void getLocationInWindow(int[] iArr) {
                RecyclerView.this.getLocationInWindow(iArr);
            }

            public ViewGroupOverlay getOverlay() {
                return RecyclerView.this.getOverlay();
            }

            public int getPaddingLeft() {
                return RecyclerView.this.getPaddingLeft();
            }

            public int getPaddingRight() {
                return RecyclerView.this.getPaddingRight();
            }

            public int getScrollY() {
                return RecyclerView.this.getScrollY();
            }

            public int getWidth() {
                return RecyclerView.this.getWidth();
            }

            public boolean isFastScrollerEnabled() {
                return RecyclerView.this.seslIsFastScrollerEnabled();
            }

            public void playSoundEffect(int i2) {
                RecyclerView.this.playSoundEffect(i2);
            }

            public void post(Runnable runnable) {
                RecyclerView.this.post(runnable);
            }

            public void postDelayed(Runnable runnable, long j2) {
                RecyclerView.this.postDelayed(runnable, j2);
            }

            public void removeCallbacks(Runnable runnable) {
                RecyclerView.this.removeCallbacks(runnable);
            }

            public void showTopEdgeEffect() {
                RecyclerView.this.ensureTopGlow();
                RecyclerView.this.mTopGlow.onAbsorb(10000);
                RecyclerView.this.invalidate();
            }

            public void smoothScrollToTop() {
                RecyclerView.this.smoothScrollToPositionJumpIfNeeded(0);
            }
        };
        this.mIsPenSelectionEnabled = true;
        this.mIsPenPressed = false;
        this.mIsFirstPenMoveEvent = true;
        this.mIsNeedPenSelection = false;
        this.mPenDragSelectedViewPosition = -1;
        this.mIsPenDragBlockEnabled = true;
        this.mPenDragStartX = 0;
        this.mPenDragStartY = 0;
        this.mPenDragEndX = 0;
        this.mPenDragEndY = 0;
        this.mPenDragBlockLeft = 0;
        this.mPenDragBlockTop = 0;
        this.mPenDragBlockRight = 0;
        this.mPenDragBlockBottom = 0;
        this.mPenTrackedChild = null;
        this.mPenTrackedChildPosition = -1;
        this.mPenDistanceFromTrackedChildTop = 0;
        this.mPenDragBlockRect = new Rect();
        this.mInitialTopOffsetOfScreen = 0;
        this.mRemainNestedScrollRange = 0;
        this.mNestedScrollRange = 0;
        this.mHasNestedScrollRange = false;
        this.mIsCtrlKeyPressed = false;
        this.mIsLongPressMultiSelection = false;
        this.mIsFirstMultiSelectionMove = true;
        this.mIsCtrlMultiSelection = false;
        this.mIsPenButtonPressed = false;
        this.mDrawHorizontalPadding = false;
        this.mDrawRect = false;
        this.mDrawLastRoundedCorner = true;
        this.mDrawReverse = false;
        this.mBlackTop = -1;
        this.mLastBlackTop = -1;
        this.mAnimatedBlackTop = -1;
        this.mRectPaint = new Paint();
        this.mScrollbarTopPadding = 0;
        this.mScrollbarBottomPadding = 0;
        this.mNaviBarTop = -1;
        this.mScrollInfoProvider = new SeslFadingEdgeHelper.ScrollInfoProvider() {
            public int computeVerticalScrollExtent() {
                return RecyclerView.this.computeVerticalScrollExtent();
            }

            public int computeVerticalScrollOffset() {
                return RecyclerView.this.computeVerticalScrollOffset();
            }

            public int computeVerticalScrollRange() {
                return RecyclerView.this.computeVerticalScrollRange();
            }

            public int getLastItemHeightIfVisible() {
                int findLastVisibleItemPosition;
                LayoutManager layoutManager;
                View findViewByPosition;
                Adapter adapter = RecyclerView.this.getAdapter();
                if (adapter == null || (findLastVisibleItemPosition = RecyclerView.this.findLastVisibleItemPosition()) != adapter.getItemCount() - 1 || (layoutManager = RecyclerView.this.getLayoutManager()) == null || (findViewByPosition = layoutManager.findViewByPosition(findLastVisibleItemPosition)) == null) {
                    return -1;
                }
                return findViewByPosition.getHeight();
            }

            public boolean shouldNormalizeFadingEdge() {
                LayoutManager layoutManager = RecyclerView.this.mLayout;
                if (layoutManager == null) {
                    return false;
                }
                if (!(layoutManager instanceof LinearLayoutManager) && !(layoutManager instanceof StaggeredGridLayoutManager)) {
                    return false;
                }
                return true;
            }
        };
        this.mScrollBarTopOffset = 0;
        this.mScrollBarBottomOffset = 0;
        this.mIsPenHovered = false;
        this.mRootViewCheckForDialog = null;
        this.mIsPenSelectPointerSetted = false;
        this.mIsNeedPenSelectIconSet = false;
        this.mOldTextViewHoverState = false;
        this.mNewTextViewHoverState = false;
        this.mHoverScrollSpeed = 0;
        this.mPointerIconRotation = 0.0f;
        this.mHoverScrollArrows = new int[]{SeslPointerIconReflector.getField_SEM_TYPE_STYLUS_SCROLL_UP(), SeslPointerIconReflector.getField_SEM_TYPE_STYLUS_SCROLL_RIGHT(), SeslPointerIconReflector.getField_SEM_TYPE_STYLUS_SCROLL_DOWN(), SeslPointerIconReflector.getField_SEM_TYPE_STYLUS_SCROLL_LEFT()};
        this.mHoverRecognitionDurationTime = 0;
        this.mHoverRecognitionCurrentTime = 0;
        this.mHoverRecognitionStartTime = 0;
        this.mHoverScrollTimeInterval = 300;
        this.mPenDragScrollTimeInterval = 500;
        this.mHoverScrollStartTime = 0;
        this.mHoverScrollDirection = -1;
        this.mIsHoverOverscrolled = false;
        this.mIsSendHoverScrollState = false;
        this.mHoverScrollStateForListener = 0;
        this.mIsEnabledPaddingInHoverScroll = false;
        this.mHoverAreaEnter = false;
        this.mSelectorRect = new Rect();
        this.mHoverScrollEnable = true;
        this.mHoverScrollStateChanged = false;
        this.mNeedsHoverScroll = false;
        this.mHoverTopAreaHeight = 0;
        this.mHoverBottomAreaHeight = 0;
        this.mHoverDefaultTopAreaHeight = 0;
        this.mHoverDefaultBottomAreaHeight = 0;
        this.mListPadding = new Rect();
        this.mChildBound = new Rect();
        this.mExtraPaddingInTopHoverArea = 0;
        this.mExtraPaddingInBottomHoverArea = 0;
        this.mIsCloseChildSetted = false;
        this.mOldHoverScrollDirection = -1;
        this.mCloseChildByTop = null;
        this.mCloseChildPositionByTop = -1;
        this.mDistanceFromCloseChildTop = 0;
        this.mCloseChildByBottom = null;
        this.mCloseChildPositionByBottom = -1;
        this.mDistanceFromCloseChildBottom = 0;
        this.mItemBackgroundHolder = new ItemBackgroundHolder();
        this.mHoverHandler = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message message) {
                boolean z;
                boolean z3;
                int i2;
                int i7;
                int i8;
                int i10;
                boolean z7;
                int access$1900;
                if (message.what == 0) {
                    RecyclerView recyclerView = RecyclerView.this;
                    if (recyclerView.mAdapter == null) {
                        Log.e("SeslRecyclerView", "No adapter attached; skipping MSG_HOVERSCROLL_MOVE");
                        return;
                    }
                    long unused = recyclerView.mHoverRecognitionCurrentTime = System.currentTimeMillis();
                    RecyclerView recyclerView2 = RecyclerView.this;
                    long unused2 = recyclerView2.mHoverRecognitionDurationTime = (recyclerView2.mHoverRecognitionCurrentTime - RecyclerView.this.mHoverRecognitionStartTime) / 1000;
                    if (RecyclerView.this.mIsPenHovered && RecyclerView.this.mHoverRecognitionCurrentTime - RecyclerView.this.mHoverScrollStartTime < RecyclerView.this.mHoverScrollTimeInterval) {
                        return;
                    }
                    if (!RecyclerView.this.mIsPenPressed || RecyclerView.this.mHoverRecognitionCurrentTime - RecyclerView.this.mHoverScrollStartTime >= RecyclerView.this.mPenDragScrollTimeInterval) {
                        if (RecyclerView.this.mIsPenHovered && !RecyclerView.this.mIsSendHoverScrollState) {
                            if (RecyclerView.this.mScrollListener != null) {
                                int unused3 = RecyclerView.this.mHoverScrollStateForListener = 1;
                                RecyclerView.this.mScrollListener.onScrollStateChanged(RecyclerView.this, 1);
                            }
                            boolean unused4 = RecyclerView.this.mIsSendHoverScrollState = true;
                        }
                        boolean canScrollVertically = RecyclerView.this.mLayout.canScrollVertically();
                        boolean canScrollHorizontally = RecyclerView.this.mLayout.canScrollHorizontally();
                        if (RecyclerView.this.mLayout.getLayoutDirection() == 1) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (RecyclerView.this.mRemainNestedScrollRange > 0) {
                            z3 = true;
                        } else {
                            z3 = RecyclerView.this.canScrollDown();
                        }
                        boolean access$400 = RecyclerView.this.canScrollUp();
                        int unused5 = RecyclerView.this.mHoverScrollSpeed = (int) (TypedValue.applyDimension(1, RecyclerView.HOVERSCROLL_SPEED, RecyclerView.this.mContext.getResources().getDisplayMetrics()) + 0.5f);
                        if (RecyclerView.this.mHoverRecognitionDurationTime > 2 && RecyclerView.this.mHoverRecognitionDurationTime < 4) {
                            RecyclerView recyclerView3 = RecyclerView.this;
                            int unused6 = recyclerView3.mHoverScrollSpeed = recyclerView3.mHoverScrollSpeed + ((int) (((double) RecyclerView.this.mHoverScrollSpeed) * 0.1d));
                        } else if (RecyclerView.this.mHoverRecognitionDurationTime >= 4 && RecyclerView.this.mHoverRecognitionDurationTime < 5) {
                            RecyclerView recyclerView4 = RecyclerView.this;
                            int unused7 = recyclerView4.mHoverScrollSpeed = recyclerView4.mHoverScrollSpeed + ((int) (((double) RecyclerView.this.mHoverScrollSpeed) * 0.2d));
                        } else if (RecyclerView.this.mHoverRecognitionDurationTime >= 5) {
                            RecyclerView recyclerView5 = RecyclerView.this;
                            int unused8 = recyclerView5.mHoverScrollSpeed = recyclerView5.mHoverScrollSpeed + ((int) (((double) RecyclerView.this.mHoverScrollSpeed) * 0.3d));
                        }
                        int i11 = 2;
                        if (RecyclerView.this.mHoverScrollDirection == 2) {
                            if (!canScrollHorizontally || !z) {
                                i2 = RecyclerView.this.mHoverScrollSpeed * -1;
                            } else {
                                i2 = RecyclerView.this.mHoverScrollSpeed;
                            }
                            if ((RecyclerView.this.mPenTrackedChild == null && RecyclerView.this.mCloseChildByBottom != null) || (RecyclerView.this.mOldHoverScrollDirection != RecyclerView.this.mHoverScrollDirection && RecyclerView.this.mIsCloseChildSetted)) {
                                RecyclerView recyclerView6 = RecyclerView.this;
                                View unused9 = recyclerView6.mPenTrackedChild = recyclerView6.mCloseChildByBottom;
                                RecyclerView recyclerView7 = RecyclerView.this;
                                int unused10 = recyclerView7.mPenDistanceFromTrackedChildTop = recyclerView7.mDistanceFromCloseChildBottom;
                                RecyclerView recyclerView8 = RecyclerView.this;
                                int unused11 = recyclerView8.mPenTrackedChildPosition = recyclerView8.mCloseChildPositionByBottom;
                                RecyclerView recyclerView9 = RecyclerView.this;
                                int unused12 = recyclerView9.mOldHoverScrollDirection = recyclerView9.mHoverScrollDirection;
                                boolean unused13 = RecyclerView.this.mIsCloseChildSetted = true;
                            }
                        } else {
                            if (!canScrollHorizontally || !z) {
                                access$1900 = RecyclerView.this.mHoverScrollSpeed;
                            } else {
                                access$1900 = RecyclerView.this.mHoverScrollSpeed * -1;
                            }
                            if ((RecyclerView.this.mPenTrackedChild == null && RecyclerView.this.mCloseChildByTop != null) || (RecyclerView.this.mOldHoverScrollDirection != RecyclerView.this.mHoverScrollDirection && RecyclerView.this.mIsCloseChildSetted)) {
                                RecyclerView recyclerView10 = RecyclerView.this;
                                View unused14 = recyclerView10.mPenTrackedChild = recyclerView10.mCloseChildByTop;
                                RecyclerView recyclerView11 = RecyclerView.this;
                                int unused15 = recyclerView11.mPenDistanceFromTrackedChildTop = recyclerView11.mDistanceFromCloseChildTop;
                                RecyclerView recyclerView12 = RecyclerView.this;
                                int unused16 = recyclerView12.mPenTrackedChildPosition = recyclerView12.mCloseChildPositionByTop;
                                RecyclerView recyclerView13 = RecyclerView.this;
                                int unused17 = recyclerView13.mOldHoverScrollDirection = recyclerView13.mHoverScrollDirection;
                                boolean unused18 = RecyclerView.this.mIsCloseChildSetted = true;
                            }
                        }
                        RecyclerView recyclerView14 = RecyclerView.this;
                        if (recyclerView14.getChildAt(recyclerView14.getChildCount() - 1) != null) {
                            if ((i2 >= 0 || !access$400) && (i2 <= 0 || !z3)) {
                                int overScrollMode = RecyclerView.this.getOverScrollMode();
                                if (overScrollMode == 0 || (overScrollMode == 1 && !RecyclerView.this.contentFits())) {
                                    z7 = true;
                                } else {
                                    z7 = false;
                                }
                                if (z7 && !RecyclerView.this.mIsHoverOverscrolled) {
                                    if (canScrollHorizontally) {
                                        RecyclerView.this.ensureLeftGlow();
                                        RecyclerView.this.ensureRightGlow();
                                    } else {
                                        RecyclerView.this.ensureTopGlow();
                                        RecyclerView.this.ensureBottomGlow();
                                    }
                                    if (RecyclerView.this.mHoverScrollDirection == 2) {
                                        if (canScrollHorizontally) {
                                            RecyclerView.this.mLeftGlow.onAbsorb(10000);
                                            if (!RecyclerView.this.mRightGlow.isFinished()) {
                                                RecyclerView.this.mRightGlow.onRelease();
                                            }
                                        } else {
                                            RecyclerView.this.mTopGlow.onAbsorb(10000);
                                            if (!RecyclerView.this.mBottomGlow.isFinished()) {
                                                RecyclerView.this.mBottomGlow.onRelease();
                                            }
                                        }
                                    } else if (RecyclerView.this.mHoverScrollDirection == 1) {
                                        if (canScrollHorizontally) {
                                            RecyclerView.this.mRightGlow.onAbsorb(10000);
                                            if (!RecyclerView.this.mLeftGlow.isFinished()) {
                                                RecyclerView.this.mLeftGlow.onRelease();
                                            }
                                        } else {
                                            RecyclerView.this.mBottomGlow.onAbsorb(10000);
                                            RecyclerView.this.showGoToTop();
                                            if (!RecyclerView.this.mTopGlow.isFinished()) {
                                                RecyclerView.this.mTopGlow.onRelease();
                                            }
                                        }
                                    }
                                    RecyclerView.this.invalidate();
                                    boolean unused19 = RecyclerView.this.mIsHoverOverscrolled = true;
                                }
                                if (RecyclerView.this.mScrollState == 1) {
                                    RecyclerView.this.setScrollState(0);
                                }
                                if (!z7 && !RecyclerView.this.mIsHoverOverscrolled) {
                                    boolean unused20 = RecyclerView.this.mIsHoverOverscrolled = true;
                                    return;
                                }
                                return;
                            }
                            RecyclerView recyclerView15 = RecyclerView.this;
                            if (canScrollHorizontally) {
                                i11 = 1;
                            }
                            recyclerView15.startNestedScroll(i11, 1);
                            RecyclerView recyclerView16 = RecyclerView.this;
                            if (!canScrollHorizontally) {
                                i7 = 0;
                            } else if (z) {
                                i7 = -i2;
                            } else {
                                i7 = i2;
                            }
                            if (canScrollVertically) {
                                i8 = i2;
                            } else {
                                i8 = 0;
                            }
                            if (!recyclerView16.dispatchNestedPreScroll(i7, i8, (int[]) null, (int[]) null, 1)) {
                                RecyclerView recyclerView17 = RecyclerView.this;
                                if (!canScrollHorizontally) {
                                    i10 = 0;
                                } else if (z) {
                                    i10 = -i2;
                                } else {
                                    i10 = i2;
                                }
                                if (!canScrollVertically) {
                                    i2 = 0;
                                }
                                recyclerView17.scrollByInternal(i10, i2, (MotionEvent) null, 0);
                                RecyclerView.this.setScrollState(1);
                                if (RecyclerView.this.mIsLongPressMultiSelection) {
                                    RecyclerView recyclerView18 = RecyclerView.this;
                                    recyclerView18.updateLongPressMultiSelection(recyclerView18.mPenDragEndX, RecyclerView.this.mPenDragEndY, false);
                                }
                            } else {
                                RecyclerView.this.adjustNestedScrollRangeBy(i2);
                            }
                            RecyclerView.this.mHoverHandler.sendEmptyMessageDelayed(0, 0);
                        }
                    }
                }
            }
        };
        this.mPendingAccessibilityImportanceChange = new ArrayList();
        this.mItemAnimatorRunner = new Runnable() {
            public void run() {
                ItemAnimator itemAnimator = RecyclerView.this.mItemAnimator;
                if (itemAnimator != null) {
                    itemAnimator.runPendingAnimations();
                }
                RecyclerView.this.mPostedAnimatorRunner = false;
            }
        };
        this.mLastAutoMeasureNonExactMeasuredWidth = 0;
        this.mLastAutoMeasureNonExactMeasuredHeight = 0;
        this.mViewInfoProcessCallback = new ViewInfoStore.ProcessCallback() {
            public void processAppeared(ViewHolder viewHolder, ItemAnimator.ItemHolderInfo itemHolderInfo, ItemAnimator.ItemHolderInfo itemHolderInfo2) {
                RecyclerView.this.animateAppearance(viewHolder, itemHolderInfo, itemHolderInfo2);
            }

            public void processDisappeared(ViewHolder viewHolder, ItemAnimator.ItemHolderInfo itemHolderInfo, ItemAnimator.ItemHolderInfo itemHolderInfo2) {
                RecyclerView.this.mRecycler.unscrapView(viewHolder);
                RecyclerView.this.animateDisappearance(viewHolder, itemHolderInfo, itemHolderInfo2);
            }

            public void processPersistent(ViewHolder viewHolder, ItemAnimator.ItemHolderInfo itemHolderInfo, ItemAnimator.ItemHolderInfo itemHolderInfo2) {
                viewHolder.setIsRecyclable(false);
                RecyclerView recyclerView = RecyclerView.this;
                if (recyclerView.mDataSetHasChangedAfterLayout) {
                    ItemAnimator itemAnimator = recyclerView.mItemAnimator;
                    if (itemAnimator != null && itemAnimator.animateChange(viewHolder, viewHolder, itemHolderInfo, itemHolderInfo2)) {
                        RecyclerView.this.postAnimationRunner();
                        return;
                    }
                    return;
                }
                ItemAnimator itemAnimator2 = recyclerView.mItemAnimator;
                if (itemAnimator2 != null && itemAnimator2.animatePersistence(viewHolder, itemHolderInfo, itemHolderInfo2)) {
                    RecyclerView.this.postAnimationRunner();
                }
            }

            public void unused(ViewHolder viewHolder) {
                RecyclerView recyclerView = RecyclerView.this;
                recyclerView.mLayout.removeAndRecycleView(viewHolder.itemView, recyclerView.mRecycler);
            }
        };
        this.mAvailableBounds = null;
        this.mSeslBottomBarHeight = 0;
        this.mDebugDrawAvailRect = false;
        this.mGoToTopEdgeEffectRunnable = new Runnable() {
            public void run() {
                RecyclerView.this.ensureTopGlow();
                RecyclerView.this.mTopGlow.onAbsorb(10000);
                RecyclerView.this.invalidate();
            }
        };
        this.mIsRecoilEnabled = true;
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        this.mContext = context2;
        seslInitConfigurations(context);
        try {
            boolean equals = Build.TYPE.toString().toLowerCase().equals("eng");
            String stringProperties = SeslSystemPropertiesReflector.getStringProperties("sesl.debug.recyclerview.avail_rect");
            if (equals && stringProperties != null && Integer.parseInt(stringProperties) == 1) {
                this.mDebugDrawAvailRect = true;
            }
        } catch (Exception e) {
            Log.w("SeslRecyclerView", "Can't check debug condition " + e);
        }
        this.mIsRecoilSupported = true;
        this.mItemAnimatorHolder = new SeslRecoilAnimator.Holder(this.mContext);
        this.mPhysicalCoef = context2.getResources().getDisplayMetrics().density * 160.0f * 386.0878f * 0.84f;
        setWillNotDraw(getOverScrollMode() == 2);
        this.mItemAnimator.setListener(this.mItemAnimatorListener);
        initAdapterManager();
        initChildrenHelper();
        initAutofill();
        if (ViewCompat.getImportantForAccessibility(this) == 0) {
            ViewCompat.setImportantForAccessibility(this, 1);
        }
        this.mAccessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new RecyclerViewAccessibilityDelegate(this));
        int[] iArr = R$styleable.RecyclerView;
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet2, iArr, i7, 0);
        ViewCompat.saveAttributeDataForStyleable(this, context2, iArr, attributeSet2, obtainStyledAttributes, i7, 0);
        String string = obtainStyledAttributes.getString(R$styleable.RecyclerView_layoutManager);
        if (obtainStyledAttributes.getInt(R$styleable.RecyclerView_android_descendantFocusability, -1) == -1) {
            setDescendantFocusability(262144);
        }
        this.mClipToPadding = obtainStyledAttributes.getBoolean(R$styleable.RecyclerView_android_clipToPadding, true);
        boolean z = obtainStyledAttributes.getBoolean(R$styleable.RecyclerView_fastScrollEnabled, false);
        this.mEnableFastScroller = z;
        if (z) {
            initFastScroller((StateListDrawable) obtainStyledAttributes.getDrawable(R$styleable.RecyclerView_fastScrollVerticalThumbDrawable), obtainStyledAttributes.getDrawable(R$styleable.RecyclerView_fastScrollVerticalTrackDrawable), (StateListDrawable) obtainStyledAttributes.getDrawable(R$styleable.RecyclerView_fastScrollHorizontalThumbDrawable), obtainStyledAttributes.getDrawable(R$styleable.RecyclerView_fastScrollHorizontalTrackDrawable));
        }
        obtainStyledAttributes.recycle();
        Context context3 = context;
        AttributeSet attributeSet3 = attributeSet;
        int i8 = i2;
        createLayoutManager(context3, string, attributeSet3, i8, 0);
        int i10 = i8;
        int[] iArr2 = NESTED_SCROLLING_ATTRS;
        TypedArray obtainStyledAttributes2 = context3.obtainStyledAttributes(attributeSet3, iArr2, i10, 0);
        ViewCompat.saveAttributeDataForStyleable(this, context3, iArr2, attributeSet3, obtainStyledAttributes2, i10, 0);
        boolean z3 = obtainStyledAttributes2.getBoolean(0, true);
        obtainStyledAttributes2.recycle();
        Resources resources = context.getResources();
        TypedValue typedValue = new TypedValue();
        this.mPenDragBlockImage = resources.getDrawable(R$drawable.sesl_pen_block_selection);
        context.getTheme().resolveAttribute(androidx.appcompat.R$attr.roundedCornerColor, typedValue, true);
        int i11 = typedValue.resourceId;
        if (i11 > 0) {
            this.mRectColor = resources.getColor(i11);
        }
        this.mRectPaint.setColor(this.mRectColor);
        this.mRectPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.mItemAnimator.setHostView(this);
        SeslSubheaderRoundedCorner seslSubheaderRoundedCorner = new SeslSubheaderRoundedCorner(getContext());
        this.mRoundedCorner = seslSubheaderRoundedCorner;
        seslSubheaderRoundedCorner.setRoundedCorners(12);
        setNestedScrollingEnabled(z3);
        PoolingContainer.setPoolingContainer(this, true);
        this.mFadingEdgeHelper = new SeslFadingEdgeHelper(this.mContext);
    }

    public void seslSetFadingEdgeEnabled(boolean z, boolean z3) {
        applyFadingEdge(z, new androidx.core.widget.a(this, z, z3, 1));
    }

    public void smoothScrollBy(int i2, int i7, Interpolator interpolator, int i8) {
        smoothScrollBy(i2, i7, interpolator, i8, false);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        final Rect mDecorInsets = new Rect();
        boolean mInsetsDirty = true;
        boolean mPendingInvalidate = false;
        ViewHolder mViewHolder;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public int getAbsoluteAdapterPosition() {
            return this.mViewHolder.getAbsoluteAdapterPosition();
        }

        @Deprecated
        public int getViewAdapterPosition() {
            return this.mViewHolder.getBindingAdapterPosition();
        }

        public int getViewLayoutPosition() {
            return this.mViewHolder.getLayoutPosition();
        }

        public boolean isItemChanged() {
            return this.mViewHolder.isUpdated();
        }

        public boolean isItemRemoved() {
            return this.mViewHolder.isRemoved();
        }

        public boolean isViewInvalid() {
            return this.mViewHolder.isInvalid();
        }

        public LayoutParams(int i2, int i7) {
            super(i2, i7);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public boolean dispatchNestedPreScroll(int i2, int i7, int[] iArr, int[] iArr2, int i8) {
        if (this.mGoToTopController != null && canScrollDown()) {
            this.mGoToTopController.invalidate();
        }
        return getScrollingChildHelper().dispatchNestedPreScroll(i2, i7, iArr, iArr2, i8);
    }

    public void smoothScrollBy(int i2, int i7, Interpolator interpolator, int i8, boolean z) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null) {
            Log.e("SeslRecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (!this.mLayoutSuppressed) {
            int i10 = 0;
            if (!layoutManager.canScrollHorizontally()) {
                i2 = 0;
            }
            if (!this.mLayout.canScrollVertically()) {
                i7 = 0;
            }
            if (i2 != 0 || i7 != 0) {
                if (i8 == Integer.MIN_VALUE || i8 > 0) {
                    if (z) {
                        if (i2 != 0) {
                            i10 = 1;
                        }
                        if (i7 != 0) {
                            i10 |= 2;
                        }
                        startNestedScroll(i10, 1);
                    }
                    this.mViewFlinger.smoothScrollBy(i2, i7, i8, interpolator);
                    showGoToTop();
                    return;
                }
                scrollBy(i2, i7);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0056  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View findClickableChildUnder(android.view.View r7, float r8, float r9) {
        /*
            r6 = this;
            boolean r0 = r7.isClickable()
            r1 = 0
            if (r0 == 0) goto L_0x0051
            android.graphics.Rect r0 = new android.graphics.Rect
            r0.<init>()
            android.graphics.Rect r2 = new android.graphics.Rect
            r2.<init>()
            r7.getGlobalVisibleRect(r0)
            r6.getGlobalVisibleRect(r2)
            androidx.core.view.NestedScrollingChildHelper r3 = r6.mScrollingChildHelper
            if (r3 == 0) goto L_0x003f
            android.view.ViewParent r3 = r3.getNestedScrollingParent(r1)
            boolean r3 = r3 instanceof androidx.core.widget.NestedScrollView
            if (r3 == 0) goto L_0x003f
            androidx.core.view.NestedScrollingChildHelper r3 = r6.mScrollingChildHelper
            android.view.ViewParent r3 = r3.getNestedScrollingParent(r1)
            androidx.core.widget.NestedScrollView r3 = (androidx.core.widget.NestedScrollView) r3
            int r4 = r3.getScrollY()
            int r5 = r6.getTopUnderNestedScrollView(r6)
            if (r4 <= r5) goto L_0x003f
            int r3 = r3.getScrollY()
            int r4 = r6.getTopUnderNestedScrollView(r6)
            int r3 = r3 - r4
            goto L_0x0040
        L_0x003f:
            r3 = r1
        L_0x0040:
            int r4 = (int) r8
            int r5 = r2.left
            int r4 = r4 + r5
            int r5 = (int) r9
            int r2 = r2.top
            int r5 = r5 + r2
            int r5 = r5 - r3
            boolean r0 = r0.contains(r4, r5)
            if (r0 == 0) goto L_0x0051
            r0 = r7
            goto L_0x0052
        L_0x0051:
            r0 = 0
        L_0x0052:
            boolean r2 = r7 instanceof android.view.ViewGroup
            if (r2 == 0) goto L_0x006c
            android.view.ViewGroup r7 = (android.view.ViewGroup) r7
        L_0x0058:
            int r2 = r7.getChildCount()
            if (r1 >= r2) goto L_0x006c
            android.view.View r2 = r7.getChildAt(r1)
            android.view.View r2 = r6.findClickableChildUnder(r2, r8, r9)
            if (r2 == 0) goto L_0x0069
            return r2
        L_0x0069:
            int r1 = r1 + 1
            goto L_0x0058
        L_0x006c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.findClickableChildUnder(android.view.View, float, float):android.view.View");
    }

    public void addItemDecoration(ItemDecoration itemDecoration) {
        addItemDecoration(itemDecoration, -1);
    }

    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            return layoutManager.generateLayoutParams(layoutParams);
        }
        throw new IllegalStateException(C0086a.k(this, new StringBuilder("RecyclerView has no LayoutManager")));
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class ItemAnimator {
        private long mAddDuration = 120;
        private long mChangeDuration = 250;
        private ArrayList<ItemAnimatorFinishedListener> mFinishedListeners = new ArrayList<>();
        private View mHostView = null;
        private ItemAnimatorListener mListener = null;
        private long mMoveDuration = 250;
        private long mRemoveDuration = 120;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public interface ItemAnimatorFinishedListener {
            void onAnimationsFinished();
        }

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public interface ItemAnimatorListener {
            void onAnimationFinished(ViewHolder viewHolder);
        }

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class ItemHolderInfo {
            public int bottom;
            public int left;
            public int right;
            public int top;

            public ItemHolderInfo setFrom(ViewHolder viewHolder) {
                return setFrom(viewHolder, 0);
            }

            public ItemHolderInfo setFrom(ViewHolder viewHolder, int i2) {
                View view = viewHolder.itemView;
                this.left = view.getLeft();
                this.top = view.getTop();
                this.right = view.getRight();
                this.bottom = view.getBottom();
                return this;
            }
        }

        public static int buildAdapterChangeFlagsForAnimations(ViewHolder viewHolder) {
            int i2 = viewHolder.mFlags;
            int i7 = i2 & 14;
            if (viewHolder.isInvalid()) {
                return 4;
            }
            if ((i2 & 4) == 0) {
                int oldPosition = viewHolder.getOldPosition();
                int absoluteAdapterPosition = viewHolder.getAbsoluteAdapterPosition();
                if (!(oldPosition == -1 || absoluteAdapterPosition == -1 || oldPosition == absoluteAdapterPosition)) {
                    return i7 | 2048;
                }
            }
            return i7;
        }

        public abstract boolean animateAppearance(ViewHolder viewHolder, ItemHolderInfo itemHolderInfo, ItemHolderInfo itemHolderInfo2);

        public abstract boolean animateChange(ViewHolder viewHolder, ViewHolder viewHolder2, ItemHolderInfo itemHolderInfo, ItemHolderInfo itemHolderInfo2);

        public abstract boolean animateDisappearance(ViewHolder viewHolder, ItemHolderInfo itemHolderInfo, ItemHolderInfo itemHolderInfo2);

        public abstract boolean animatePersistence(ViewHolder viewHolder, ItemHolderInfo itemHolderInfo, ItemHolderInfo itemHolderInfo2);

        public abstract boolean canReuseUpdatedViewHolder(ViewHolder viewHolder);

        public boolean canReuseUpdatedViewHolder(ViewHolder viewHolder, List<Object> list) {
            return canReuseUpdatedViewHolder(viewHolder);
        }

        public final void dispatchAnimationFinished(ViewHolder viewHolder) {
            onAnimationFinished(viewHolder);
            ItemAnimatorListener itemAnimatorListener = this.mListener;
            if (itemAnimatorListener != null) {
                itemAnimatorListener.onAnimationFinished(viewHolder);
            }
        }

        public final void dispatchAnimationsFinished() {
            if (this.mFinishedListeners.size() <= 0) {
                this.mFinishedListeners.clear();
            } else {
                this.mFinishedListeners.get(0).getClass();
                throw new ClassCastException();
            }
        }

        public abstract void endAnimation(ViewHolder viewHolder);

        public abstract void endAnimations();

        public abstract long getChangeDuration();

        public View getHostView() {
            return this.mHostView;
        }

        public abstract long getMoveDuration();

        public abstract long getRemoveDuration();

        public abstract boolean isRunning();

        public final boolean isRunning(ItemAnimatorFinishedListener itemAnimatorFinishedListener) {
            boolean isRunning = isRunning();
            if (itemAnimatorFinishedListener != null) {
                if (!isRunning) {
                    itemAnimatorFinishedListener.onAnimationsFinished();
                    return isRunning;
                }
                this.mFinishedListeners.add(itemAnimatorFinishedListener);
            }
            return isRunning;
        }

        public ItemHolderInfo obtainHolderInfo() {
            return new ItemHolderInfo();
        }

        public ItemHolderInfo recordPostLayoutInformation(State state, ViewHolder viewHolder) {
            return obtainHolderInfo().setFrom(viewHolder);
        }

        public ItemHolderInfo recordPreLayoutInformation(State state, ViewHolder viewHolder, int i2, List<Object> list) {
            return obtainHolderInfo().setFrom(viewHolder);
        }

        public abstract void runPendingAnimations();

        public void setChangeDuration(long j2) {
            this.mChangeDuration = j2;
        }

        public void setHostView(View view) {
            this.mHostView = view;
        }

        public void setListener(ItemAnimatorListener itemAnimatorListener) {
            this.mListener = itemAnimatorListener;
        }

        public void onAnimationFinished(ViewHolder viewHolder) {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SimpleOnItemTouchListener implements OnItemTouchListener {
        public void onRequestDisallowInterceptTouchEvent(boolean z) {
        }

        public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        }
    }

    public void onChildAttachedToWindow(View view) {
    }

    public void onChildDetachedFromWindow(View view) {
    }

    public void onScrollStateChanged(int i2) {
    }

    @Deprecated
    public void setRecyclerListener(RecyclerListener recyclerListener) {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class OnScrollListener {
        public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
        }

        public void onScrolled(RecyclerView recyclerView, int i2, int i7) {
        }
    }

    public void onScrolled(int i2, int i7) {
    }
}
