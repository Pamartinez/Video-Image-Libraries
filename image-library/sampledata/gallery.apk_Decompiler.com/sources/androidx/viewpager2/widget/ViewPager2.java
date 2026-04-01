package androidx.viewpager2.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.PathInterpolator;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.R$styleable;
import com.adobe.internal.xmp.options.SerializeOptions;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ViewPager2 extends ViewGroup {
    private static final PathInterpolator CONTAINER_SCALE_INTERPOLATOR = new PathInterpolator(0.22f, 0.25f, 0.0f, 1.0f);
    static boolean sFeatureEnhancedA11yEnabled = true;
    AccessibilityProvider mAccessibilityProvider;
    /* access modifiers changed from: private */
    public float mContainerScaleValue = 1.0f;
    int mCurrentItem;
    private RecyclerView.AdapterDataObserver mCurrentItemDataSetChangeObserver = new DataSetChangeObserver() {
        public void onChanged() {
            ViewPager2 viewPager2 = ViewPager2.this;
            viewPager2.mCurrentItemDirty = true;
            viewPager2.mScrollEventAdapter.notifyDataSetChangeHappened();
        }
    };
    boolean mCurrentItemDirty = false;
    private CompositeOnPageChangeCallback mExternalPageChangeCallbacks = new CompositeOnPageChangeCallback(3);
    private FakeDrag mFakeDragger;
    private OrientationHelper mHorizontalHelper;
    /* access modifiers changed from: private */
    public boolean mIsSuggestionPagingEnabled = false;
    LinearLayoutManager mLayoutManager;
    private int mOffscreenPageLimit = -1;
    private CompositeOnPageChangeCallback mPageChangeEventDispatcher;
    private PageTransformerAdapter mPageTransformerAdapter;
    private PagerSnapHelper mPagerSnapHelper;
    private Parcelable mPendingAdapterState;
    private int mPendingCurrentItem = -1;
    private boolean mPrevIsClipChildren = false;
    RecyclerView mRecyclerView;
    private RecyclerView.ItemAnimator mSavedItemAnimator = null;
    private boolean mSavedItemAnimatorPresent = false;
    ScrollEventAdapter mScrollEventAdapter;
    /* access modifiers changed from: private */
    public int mScrollState = 0;
    /* access modifiers changed from: private */
    public ValueAnimator mSuggestionReleaseAnimator;
    /* access modifiers changed from: private */
    public ValueAnimator mSuggestionStartDragAnimator;
    private final Rect mTmpChildRect = new Rect();
    private final Rect mTmpContainerRect = new Rect();
    private boolean mUserInputEnabled = true;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class BasicAccessibilityProvider extends AccessibilityProvider {
        public BasicAccessibilityProvider() {
            super();
        }

        public boolean handlesLmPerformAccessibilityAction(int i2) {
            if ((i2 == 8192 || i2 == 4096) && !ViewPager2.this.isUserInputEnabled()) {
                return true;
            }
            return false;
        }

        public boolean handlesRvGetAccessibilityClassName() {
            return true;
        }

        public void onLmInitializeAccessibilityNodeInfo(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            if (!ViewPager2.this.isUserInputEnabled()) {
                accessibilityNodeInfoCompat.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD);
                accessibilityNodeInfoCompat.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD);
                accessibilityNodeInfoCompat.setScrollable(false);
            }
        }

        public boolean onLmPerformAccessibilityAction(int i2) {
            if (handlesLmPerformAccessibilityAction(i2)) {
                return false;
            }
            throw new IllegalStateException();
        }

        public CharSequence onRvGetAccessibilityClassName() {
            if (handlesRvGetAccessibilityClassName()) {
                return "androidx.viewpager.widget.ViewPager";
            }
            throw new IllegalStateException();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class DataSetChangeObserver extends RecyclerView.AdapterDataObserver {
        private DataSetChangeObserver() {
        }

        public abstract void onChanged();

        public final void onItemRangeChanged(int i2, int i7) {
            onChanged();
        }

        public final void onItemRangeInserted(int i2, int i7) {
            onChanged();
        }

        public final void onItemRangeMoved(int i2, int i7, int i8) {
            onChanged();
        }

        public final void onItemRangeRemoved(int i2, int i7) {
            onChanged();
        }

        public final void onItemRangeChanged(int i2, int i7, Object obj) {
            onChanged();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class LinearLayoutManagerImpl extends LinearLayoutManager {
        public LinearLayoutManagerImpl(Context context) {
            super(context);
        }

        public void calculateExtraLayoutSpace(RecyclerView.State state, int[] iArr) {
            int offscreenPageLimit = ViewPager2.this.getOffscreenPageLimit();
            if (offscreenPageLimit == -1) {
                super.calculateExtraLayoutSpace(state, iArr);
                return;
            }
            int pageSize = ViewPager2.this.getPageSize() * offscreenPageLimit;
            iArr[0] = pageSize;
            iArr[1] = pageSize;
        }

        public void onInitializeAccessibilityNodeInfo(RecyclerView.Recycler recycler, RecyclerView.State state, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(recycler, state, accessibilityNodeInfoCompat);
            ViewPager2.this.mAccessibilityProvider.onLmInitializeAccessibilityNodeInfo(accessibilityNodeInfoCompat);
        }

        public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler recycler, RecyclerView.State state, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            ViewPager2.this.mAccessibilityProvider.onLmInitializeAccessibilityNodeInfoForItem(view, accessibilityNodeInfoCompat);
        }

        public boolean performAccessibilityAction(RecyclerView.Recycler recycler, RecyclerView.State state, int i2, Bundle bundle) {
            if (ViewPager2.this.mAccessibilityProvider.handlesLmPerformAccessibilityAction(i2)) {
                return ViewPager2.this.mAccessibilityProvider.onLmPerformAccessibilityAction(i2);
            }
            return super.performAccessibilityAction(recycler, state, i2, bundle);
        }

        public boolean requestChildRectangleOnScreen(RecyclerView recyclerView, View view, Rect rect, boolean z, boolean z3) {
            return false;
        }

        public int scrollHorizontallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
            if (ViewPager2.this.mIsSuggestionPagingEnabled) {
                ViewPager2.this.setSuggestionPagingVI();
            }
            return super.scrollHorizontallyBy(i2, recycler, state);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class PageAwareAccessibilityProvider extends AccessibilityProvider {
        private final AccessibilityViewCommand mActionPageBackward = new AccessibilityViewCommand() {
            public boolean perform(View view, AccessibilityViewCommand.CommandArguments commandArguments) {
                PageAwareAccessibilityProvider.this.setCurrentItemFromAccessibilityCommand(((ViewPager2) view).getCurrentItem() - 1);
                return true;
            }
        };
        private final AccessibilityViewCommand mActionPageForward = new AccessibilityViewCommand() {
            public boolean perform(View view, AccessibilityViewCommand.CommandArguments commandArguments) {
                PageAwareAccessibilityProvider.this.setCurrentItemFromAccessibilityCommand(((ViewPager2) view).getCurrentItem() + 1);
                return true;
            }
        };
        private RecyclerView.AdapterDataObserver mAdapterDataObserver;

        public PageAwareAccessibilityProvider() {
            super();
        }

        private void addCollectionInfo(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            int i2;
            int i7;
            if (ViewPager2.this.getAdapter() != null) {
                i7 = 1;
                if (ViewPager2.this.getOrientation() == 1) {
                    i7 = ViewPager2.this.getAdapter().getItemCount();
                    i2 = 1;
                } else {
                    i2 = ViewPager2.this.getAdapter().getItemCount();
                }
            } else {
                i2 = 0;
                i7 = 0;
            }
            accessibilityNodeInfoCompat.setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(i7, i2, false, 0));
        }

        private void addCollectionItemInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            int i2;
            int i7 = 0;
            if (ViewPager2.this.getOrientation() == 1) {
                i2 = ViewPager2.this.mLayoutManager.getPosition(view);
            } else {
                i2 = 0;
            }
            if (ViewPager2.this.getOrientation() == 0) {
                i7 = ViewPager2.this.mLayoutManager.getPosition(view);
            }
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(i2, 1, i7, 1, false, false));
        }

        private void addScrollActions(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            int itemCount;
            RecyclerView.Adapter adapter = ViewPager2.this.getAdapter();
            if (adapter != null && (itemCount = adapter.getItemCount()) != 0 && ViewPager2.this.isUserInputEnabled()) {
                if (ViewPager2.this.mCurrentItem > 0) {
                    accessibilityNodeInfoCompat.addAction((int) SerializeOptions.SORT);
                }
                if (ViewPager2.this.mCurrentItem < itemCount - 1) {
                    accessibilityNodeInfoCompat.addAction(4096);
                }
                accessibilityNodeInfoCompat.setScrollable(true);
            }
        }

        public boolean handlesGetAccessibilityClassName() {
            return true;
        }

        public boolean handlesPerformAccessibilityAction(int i2, Bundle bundle) {
            if (i2 == 8192 || i2 == 4096) {
                return true;
            }
            return false;
        }

        public void onAttachAdapter(RecyclerView.Adapter<?> adapter) {
            updatePageAccessibilityActions();
            if (adapter != null) {
                adapter.registerAdapterDataObserver(this.mAdapterDataObserver);
            }
        }

        public void onDetachAdapter(RecyclerView.Adapter<?> adapter) {
            if (adapter != null) {
                adapter.unregisterAdapterDataObserver(this.mAdapterDataObserver);
            }
        }

        public String onGetAccessibilityClassName() {
            if (handlesGetAccessibilityClassName()) {
                return "androidx.viewpager.widget.ViewPager";
            }
            throw new IllegalStateException();
        }

        public void onInitialize(CompositeOnPageChangeCallback compositeOnPageChangeCallback, RecyclerView recyclerView) {
            recyclerView.setImportantForAccessibility(2);
            this.mAdapterDataObserver = new DataSetChangeObserver() {
                public void onChanged() {
                    PageAwareAccessibilityProvider.this.updatePageAccessibilityActions();
                }
            };
            if (ViewPager2.this.getImportantForAccessibility() == 0) {
                ViewPager2.this.setImportantForAccessibility(1);
            }
        }

        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            AccessibilityNodeInfoCompat wrap = AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo);
            addCollectionInfo(wrap);
            addScrollActions(wrap);
        }

        public void onLmInitializeAccessibilityNodeInfoForItem(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            addCollectionItemInfo(view, accessibilityNodeInfoCompat);
        }

        public boolean onPerformAccessibilityAction(int i2, Bundle bundle) {
            int i7;
            if (handlesPerformAccessibilityAction(i2, bundle)) {
                if (i2 == 8192) {
                    i7 = ViewPager2.this.getCurrentItem() - 1;
                } else {
                    i7 = ViewPager2.this.getCurrentItem() + 1;
                }
                setCurrentItemFromAccessibilityCommand(i7);
                return true;
            }
            throw new IllegalStateException();
        }

        public void onRestorePendingState() {
            updatePageAccessibilityActions();
        }

        public void onRvInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            accessibilityEvent.setSource(ViewPager2.this);
            accessibilityEvent.setClassName(onGetAccessibilityClassName());
        }

        public void onSetLayoutDirection() {
            updatePageAccessibilityActions();
        }

        public void onSetNewCurrentItem() {
            updatePageAccessibilityActions();
        }

        public void onSetOrientation() {
            updatePageAccessibilityActions();
        }

        public void onSetUserInputEnabled() {
            updatePageAccessibilityActions();
        }

        public void setCurrentItemFromAccessibilityCommand(int i2) {
            if (ViewPager2.this.isUserInputEnabled()) {
                ViewPager2.this.setCurrentItemInternal(i2, true);
            }
        }

        public void updatePageAccessibilityActions() {
            int itemCount;
            int i2;
            ViewPager2 viewPager2 = ViewPager2.this;
            int i7 = 16908360;
            ViewCompat.removeAccessibilityAction(viewPager2, 16908360);
            ViewCompat.removeAccessibilityAction(viewPager2, 16908361);
            ViewCompat.removeAccessibilityAction(viewPager2, 16908358);
            ViewCompat.removeAccessibilityAction(viewPager2, 16908359);
            if (ViewPager2.this.getAdapter() != null && (itemCount = ViewPager2.this.getAdapter().getItemCount()) != 0 && ViewPager2.this.isUserInputEnabled()) {
                if (ViewPager2.this.getOrientation() == 0) {
                    boolean isRtl = ViewPager2.this.isRtl();
                    if (isRtl) {
                        i2 = 16908360;
                    } else {
                        i2 = 16908361;
                    }
                    if (isRtl) {
                        i7 = 16908361;
                    }
                    if (ViewPager2.this.mCurrentItem < itemCount - 1) {
                        ViewCompat.replaceAccessibilityAction(viewPager2, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(i2, (CharSequence) null), (CharSequence) null, this.mActionPageForward);
                    }
                    if (ViewPager2.this.mCurrentItem > 0) {
                        ViewCompat.replaceAccessibilityAction(viewPager2, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(i7, (CharSequence) null), (CharSequence) null, this.mActionPageBackward);
                        return;
                    }
                    return;
                }
                if (ViewPager2.this.mCurrentItem < itemCount - 1) {
                    ViewCompat.replaceAccessibilityAction(viewPager2, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(16908359, (CharSequence) null), (CharSequence) null, this.mActionPageForward);
                }
                if (ViewPager2.this.mCurrentItem > 0) {
                    ViewCompat.replaceAccessibilityAction(viewPager2, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(16908358, (CharSequence) null), (CharSequence) null, this.mActionPageBackward);
                }
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface PageTransformer {
        void transformPage(View view, float f);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class PagerSnapHelperImpl extends PagerSnapHelper {
        public PagerSnapHelperImpl() {
        }

        public View findSnapView(RecyclerView.LayoutManager layoutManager) {
            if (ViewPager2.this.isFakeDragging()) {
                return null;
            }
            return super.findSnapView(layoutManager);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class RecyclerViewImpl extends RecyclerView {
        public RecyclerViewImpl(Context context) {
            super(context);
        }

        public CharSequence getAccessibilityClassName() {
            if (ViewPager2.this.mAccessibilityProvider.handlesRvGetAccessibilityClassName()) {
                return ViewPager2.this.mAccessibilityProvider.onRvGetAccessibilityClassName();
            }
            return super.getAccessibilityClassName();
        }

        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setFromIndex(ViewPager2.this.mCurrentItem);
            accessibilityEvent.setToIndex(ViewPager2.this.mCurrentItem);
            ViewPager2.this.mAccessibilityProvider.onRvInitializeAccessibilityEvent(accessibilityEvent);
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (!ViewPager2.this.isUserInputEnabled() || !super.onInterceptTouchEvent(motionEvent)) {
                return false;
            }
            return true;
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            int actionMasked;
            if (ViewPager2.this.isSuggestionPagingEnabled() && (((actionMasked = motionEvent.getActionMasked()) == 1 || actionMasked == 3) && ViewPager2.this.mScrollState == 1)) {
                ViewPager2.this.mSuggestionReleaseAnimator.setFloatValues(new float[]{0.95f, 1.0f});
                if (ViewPager2.this.mSuggestionStartDragAnimator.isRunning()) {
                    ViewPager2.this.mSuggestionReleaseAnimator.setFloatValues(new float[]{ViewPager2.this.mContainerScaleValue, 1.0f});
                    ViewPager2.this.mSuggestionStartDragAnimator.cancel();
                }
                if (ViewPager2.this.mSuggestionReleaseAnimator.isRunning()) {
                    ViewPager2.this.mSuggestionReleaseAnimator.cancel();
                }
                ViewPager2.this.mSuggestionReleaseAnimator.start();
            }
            if (!ViewPager2.this.isUserInputEnabled() || !super.onTouchEvent(motionEvent)) {
                return false;
            }
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SmoothScrollToPosition implements Runnable {
        private final int mPosition;
        private final RecyclerView mRecyclerView;

        public SmoothScrollToPosition(int i2, RecyclerView recyclerView) {
            this.mPosition = i2;
            this.mRecyclerView = recyclerView;
        }

        public void run() {
            this.mRecyclerView.smoothScrollToPosition(this.mPosition);
        }
    }

    public ViewPager2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize(context, attributeSet);
    }

    private RecyclerView.OnChildAttachStateChangeListener enforceChildFillListener() {
        return new RecyclerView.OnChildAttachStateChangeListener() {
            public void onChildViewAttachedToWindow(View view) {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                if (layoutParams.width != -1 || layoutParams.height != -1) {
                    throw new IllegalStateException("Pages must fill the whole ViewPager2 (use match_parent)");
                }
            }

            public void onChildViewDetachedFromWindow(View view) {
            }
        };
    }

    private OrientationHelper getHorizontalHelper(RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelper = this.mHorizontalHelper;
        if (orientationHelper == null || orientationHelper.getLayoutManager() != layoutManager) {
            this.mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return this.mHorizontalHelper;
    }

    private void initialize(Context context, AttributeSet attributeSet) {
        AccessibilityProvider accessibilityProvider;
        if (sFeatureEnhancedA11yEnabled) {
            accessibilityProvider = new PageAwareAccessibilityProvider();
        } else {
            accessibilityProvider = new BasicAccessibilityProvider();
        }
        this.mAccessibilityProvider = accessibilityProvider;
        RecyclerViewImpl recyclerViewImpl = new RecyclerViewImpl(context);
        this.mRecyclerView = recyclerViewImpl;
        recyclerViewImpl.setId(View.generateViewId());
        this.mRecyclerView.setDescendantFocusability(131072);
        LinearLayoutManagerImpl linearLayoutManagerImpl = new LinearLayoutManagerImpl(context);
        this.mLayoutManager = linearLayoutManagerImpl;
        this.mRecyclerView.setLayoutManager(linearLayoutManagerImpl);
        this.mRecyclerView.setScrollingTouchSlop(1);
        setOrientation(context, attributeSet);
        this.mRecyclerView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.mRecyclerView.addOnChildAttachStateChangeListener(enforceChildFillListener());
        ScrollEventAdapter scrollEventAdapter = new ScrollEventAdapter(this);
        this.mScrollEventAdapter = scrollEventAdapter;
        this.mFakeDragger = new FakeDrag(this, scrollEventAdapter, this.mRecyclerView);
        PagerSnapHelperImpl pagerSnapHelperImpl = new PagerSnapHelperImpl();
        this.mPagerSnapHelper = pagerSnapHelperImpl;
        pagerSnapHelperImpl.attachToRecyclerView(this.mRecyclerView);
        this.mRecyclerView.addOnScrollListener(this.mScrollEventAdapter);
        this.mRecyclerView.setOverScrollMode(getOverScrollMode());
        CompositeOnPageChangeCallback compositeOnPageChangeCallback = new CompositeOnPageChangeCallback(3);
        this.mPageChangeEventDispatcher = compositeOnPageChangeCallback;
        this.mScrollEventAdapter.setOnPageChangeCallback(compositeOnPageChangeCallback);
        AnonymousClass2 r32 = new OnPageChangeCallback() {
            public void onPageScrollStateChanged(int i2) {
                if (i2 == 0) {
                    ViewPager2.this.updateCurrentItem();
                }
                if (ViewPager2.this.mScrollState != i2) {
                    int unused = ViewPager2.this.mScrollState = i2;
                }
                if (ViewPager2.this.isSuggestionPagingEnabled() && i2 == 1) {
                    ViewPager2.this.getParent().requestDisallowInterceptTouchEvent(true);
                    if (ViewPager2.this.mSuggestionStartDragAnimator.isRunning()) {
                        ViewPager2.this.mSuggestionStartDragAnimator.cancel();
                    }
                    ViewPager2.this.mSuggestionStartDragAnimator.setFloatValues(new float[]{1.0f, 0.95f});
                    if (ViewPager2.this.mSuggestionReleaseAnimator.isRunning()) {
                        ViewPager2.this.mSuggestionStartDragAnimator.setFloatValues(new float[]{ViewPager2.this.mContainerScaleValue, 0.95f});
                        ViewPager2.this.mSuggestionReleaseAnimator.cancel();
                    }
                    ViewPager2.this.mSuggestionStartDragAnimator.start();
                }
            }

            public void onPageSelected(int i2) {
                ViewPager2 viewPager2 = ViewPager2.this;
                if (viewPager2.mCurrentItem != i2) {
                    viewPager2.mCurrentItem = i2;
                    viewPager2.mAccessibilityProvider.onSetNewCurrentItem();
                }
            }
        };
        AnonymousClass3 r42 = new OnPageChangeCallback() {
            public void onPageSelected(int i2) {
                ViewPager2.this.clearFocus();
                if (ViewPager2.this.hasFocus()) {
                    ViewPager2.this.mRecyclerView.requestFocus(2);
                }
            }
        };
        this.mPageChangeEventDispatcher.addOnPageChangeCallback(r32);
        this.mPageChangeEventDispatcher.addOnPageChangeCallback(r42);
        this.mAccessibilityProvider.onInitialize(this.mPageChangeEventDispatcher, this.mRecyclerView);
        this.mPageChangeEventDispatcher.addOnPageChangeCallback(this.mExternalPageChangeCallbacks);
        PageTransformerAdapter pageTransformerAdapter = new PageTransformerAdapter(this.mLayoutManager);
        this.mPageTransformerAdapter = pageTransformerAdapter;
        this.mPageChangeEventDispatcher.addOnPageChangeCallback(pageTransformerAdapter);
        RecyclerView recyclerView = this.mRecyclerView;
        attachViewToParent(recyclerView, 0, recyclerView.getLayoutParams());
    }

    /* access modifiers changed from: private */
    public boolean isSuggestionPagingEnabled() {
        if (!this.mIsSuggestionPagingEnabled || !ValueAnimator.areAnimatorsEnabled()) {
            return false;
        }
        return true;
    }

    private void registerCurrentItemDataSetTracker(RecyclerView.Adapter<?> adapter) {
        if (adapter != null) {
            adapter.registerAdapterDataObserver(this.mCurrentItemDataSetChangeObserver);
        }
    }

    private void restorePendingState() {
        RecyclerView.Adapter adapter;
        if (this.mPendingCurrentItem != -1 && (adapter = getAdapter()) != null) {
            if (this.mPendingAdapterState != null) {
                this.mPendingAdapterState = null;
            }
            int max = Math.max(0, Math.min(this.mPendingCurrentItem, adapter.getItemCount() - 1));
            this.mCurrentItem = max;
            this.mPendingCurrentItem = -1;
            this.mRecyclerView.scrollToPosition(max);
            this.mAccessibilityProvider.onRestorePendingState();
        }
    }

    private void setOrientation(Context context, AttributeSet attributeSet) {
        int[] iArr = R$styleable.ViewPager2;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr);
        ViewCompat.saveAttributeDataForStyleable(this, context, iArr, attributeSet, obtainStyledAttributes, 0, 0);
        try {
            setOrientation(obtainStyledAttributes.getInt(R$styleable.ViewPager2_android_orientation, 0));
            obtainStyledAttributes.recycle();
        } catch (Throwable th) {
            Throwable th2 = th;
            obtainStyledAttributes.recycle();
            throw th2;
        }
    }

    /* access modifiers changed from: private */
    public void setSuggestionPagingVI() {
        View findSnapView;
        int i2;
        int i7;
        int i8;
        PagerSnapHelper pagerSnapHelper = this.mPagerSnapHelper;
        if (pagerSnapHelper != null && (findSnapView = pagerSnapHelper.findSnapView(this.mLayoutManager)) != null) {
            int indexOfChild = this.mRecyclerView.indexOfChild(findSnapView);
            OrientationHelper horizontalHelper = getHorizontalHelper(this.mLayoutManager);
            this.mHorizontalHelper = horizontalHelper;
            int decoratedStart = horizontalHelper.getDecoratedStart(findSnapView);
            if (decoratedStart < 0) {
                i2 = indexOfChild + 1;
            } else {
                i2 = indexOfChild - 1;
            }
            View childAt = this.mRecyclerView.getChildAt(i2);
            if (decoratedStart < 0) {
                i7 = decoratedStart * -1;
            } else {
                i7 = decoratedStart;
            }
            float width = (((((float) (findSnapView.getWidth() - i7)) / ((float) findSnapView.getWidth())) * 0.1f) + 0.9f) * this.mContainerScaleValue;
            float f = (float) i7;
            float width2 = (((f / ((float) findSnapView.getWidth())) * 0.1f) + 0.9f) * this.mContainerScaleValue;
            if (decoratedStart > 0) {
                i8 = -4;
            } else {
                i8 = 4;
            }
            float f5 = (float) i8;
            float width3 = (((float) (findSnapView.getWidth() - i7)) / ((float) findSnapView.getWidth())) * f5;
            findSnapView.setScaleX(width);
            findSnapView.setScaleY(width);
            findSnapView.setRotationY((f / ((float) findSnapView.getWidth())) * f5);
            if (childAt != null) {
                childAt.setScaleX(width2);
                childAt.setScaleY(width2);
                childAt.setRotationY(-width3);
            }
        }
    }

    private void unregisterCurrentItemDataSetTracker(RecyclerView.Adapter<?> adapter) {
        if (adapter != null) {
            adapter.unregisterAdapterDataObserver(this.mCurrentItemDataSetChangeObserver);
        }
    }

    public boolean beginFakeDrag() {
        return this.mFakeDragger.beginFakeDrag();
    }

    public boolean canScrollHorizontally(int i2) {
        return this.mRecyclerView.canScrollHorizontally(i2);
    }

    public boolean canScrollVertically(int i2) {
        return this.mRecyclerView.canScrollVertically(i2);
    }

    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        Parcelable parcelable = sparseArray.get(getId());
        if (parcelable instanceof SavedState) {
            int i2 = ((SavedState) parcelable).mRecyclerViewId;
            sparseArray.put(this.mRecyclerView.getId(), sparseArray.get(i2));
            sparseArray.remove(i2);
        }
        super.dispatchRestoreInstanceState(sparseArray);
        restorePendingState();
    }

    public boolean endFakeDrag() {
        return this.mFakeDragger.endFakeDrag();
    }

    public boolean fakeDragBy(float f) {
        return this.mFakeDragger.fakeDragBy(f);
    }

    public CharSequence getAccessibilityClassName() {
        if (this.mAccessibilityProvider.handlesGetAccessibilityClassName()) {
            return this.mAccessibilityProvider.onGetAccessibilityClassName();
        }
        return super.getAccessibilityClassName();
    }

    public RecyclerView.Adapter getAdapter() {
        return this.mRecyclerView.getAdapter();
    }

    public int getCurrentItem() {
        return this.mCurrentItem;
    }

    public int getItemDecorationCount() {
        return this.mRecyclerView.getItemDecorationCount();
    }

    public int getOffscreenPageLimit() {
        return this.mOffscreenPageLimit;
    }

    public int getOrientation() {
        if (this.mLayoutManager.getOrientation() == 1) {
            return 1;
        }
        return 0;
    }

    public int getPageSize() {
        int height;
        int paddingBottom;
        RecyclerView recyclerView = this.mRecyclerView;
        if (getOrientation() == 0) {
            height = recyclerView.getWidth() - recyclerView.getPaddingLeft();
            paddingBottom = recyclerView.getPaddingRight();
        } else {
            height = recyclerView.getHeight() - recyclerView.getPaddingTop();
            paddingBottom = recyclerView.getPaddingBottom();
        }
        return height - paddingBottom;
    }

    public int getScrollState() {
        return this.mScrollEventAdapter.getScrollState();
    }

    public boolean isFakeDragging() {
        return this.mFakeDragger.isFakeDragging();
    }

    public boolean isRtl() {
        if (this.mLayoutManager.getLayoutDirection() == 1) {
            return true;
        }
        return false;
    }

    public boolean isUserInputEnabled() {
        return this.mUserInputEnabled;
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        this.mAccessibilityProvider.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
    }

    public void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        int measuredWidth = this.mRecyclerView.getMeasuredWidth();
        int measuredHeight = this.mRecyclerView.getMeasuredHeight();
        this.mTmpContainerRect.left = getPaddingLeft();
        this.mTmpContainerRect.right = (i8 - i2) - getPaddingRight();
        this.mTmpContainerRect.top = getPaddingTop();
        this.mTmpContainerRect.bottom = (i10 - i7) - getPaddingBottom();
        Gravity.apply(8388659, measuredWidth, measuredHeight, this.mTmpContainerRect, this.mTmpChildRect);
        RecyclerView recyclerView = this.mRecyclerView;
        Rect rect = this.mTmpChildRect;
        recyclerView.layout(rect.left, rect.top, rect.right, rect.bottom);
        if (this.mCurrentItemDirty) {
            updateCurrentItem();
        }
    }

    public void onMeasure(int i2, int i7) {
        measureChild(this.mRecyclerView, i2, i7);
        int measuredWidth = this.mRecyclerView.getMeasuredWidth();
        int measuredHeight = this.mRecyclerView.getMeasuredHeight();
        int measuredState = this.mRecyclerView.getMeasuredState();
        int paddingRight = getPaddingRight() + getPaddingLeft() + measuredWidth;
        int paddingTop = getPaddingTop();
        setMeasuredDimension(View.resolveSizeAndState(Math.max(paddingRight, getSuggestedMinimumWidth()), i2, measuredState), View.resolveSizeAndState(Math.max(getPaddingBottom() + paddingTop + measuredHeight, getSuggestedMinimumHeight()), i7, measuredState << 16));
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mPendingCurrentItem = savedState.mCurrentItem;
        this.mPendingAdapterState = savedState.mAdapterState;
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.mRecyclerViewId = this.mRecyclerView.getId();
        int i2 = this.mPendingCurrentItem;
        if (i2 == -1) {
            i2 = this.mCurrentItem;
        }
        savedState.mCurrentItem = i2;
        Parcelable parcelable = this.mPendingAdapterState;
        if (parcelable != null) {
            savedState.mAdapterState = parcelable;
            return savedState;
        }
        this.mRecyclerView.getAdapter();
        return savedState;
    }

    public void onViewAdded(View view) {
        throw new IllegalStateException("ViewPager2 does not support direct child views");
    }

    public boolean performAccessibilityAction(int i2, Bundle bundle) {
        if (this.mAccessibilityProvider.handlesPerformAccessibilityAction(i2, bundle)) {
            return this.mAccessibilityProvider.onPerformAccessibilityAction(i2, bundle);
        }
        return super.performAccessibilityAction(i2, bundle);
    }

    public void registerOnPageChangeCallback(OnPageChangeCallback onPageChangeCallback) {
        this.mExternalPageChangeCallbacks.addOnPageChangeCallback(onPageChangeCallback);
    }

    public void requestTransform() {
        if (this.mPageTransformerAdapter.getPageTransformer() != null) {
            double relativeScrollPosition = this.mScrollEventAdapter.getRelativeScrollPosition();
            int i2 = (int) relativeScrollPosition;
            float f = (float) (relativeScrollPosition - ((double) i2));
            this.mPageTransformerAdapter.onPageScrolled(i2, f, Math.round(((float) getPageSize()) * f));
        }
    }

    public RecyclerView seslGetListView() {
        return this.mRecyclerView;
    }

    public void seslSetSuggestionPaging(boolean z) {
        this.mIsSuggestionPagingEnabled = z;
        if (z) {
            this.mRecyclerView.setEdgeEffectEnabled(false);
            ValueAnimator duration = ValueAnimator.ofFloat(new float[]{1.0f, 0.95f}).setDuration(400);
            this.mSuggestionStartDragAnimator = duration;
            PathInterpolator pathInterpolator = CONTAINER_SCALE_INTERPOLATOR;
            duration.setInterpolator(pathInterpolator);
            this.mSuggestionStartDragAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float unused = ViewPager2.this.mContainerScaleValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    ViewPager2.this.setSuggestionPagingVI();
                }
            });
            ValueAnimator duration2 = ValueAnimator.ofFloat(new float[]{0.95f, 1.0f}).setDuration(400);
            this.mSuggestionReleaseAnimator = duration2;
            duration2.setInterpolator(pathInterpolator);
            this.mSuggestionReleaseAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float unused = ViewPager2.this.mContainerScaleValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    ViewPager2.this.setSuggestionPagingVI();
                }
            });
            boolean clipChildren = this.mRecyclerView.getClipChildren();
            this.mPrevIsClipChildren = clipChildren;
            if (clipChildren) {
                this.mRecyclerView.setClipChildren(false);
                return;
            }
            return;
        }
        this.mSuggestionStartDragAnimator.removeAllUpdateListeners();
        this.mSuggestionStartDragAnimator.removeAllListeners();
        this.mSuggestionReleaseAnimator.removeAllUpdateListeners();
        this.mSuggestionReleaseAnimator.removeAllListeners();
        if (this.mPrevIsClipChildren) {
            this.mRecyclerView.setClipChildren(true);
        }
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        RecyclerView.Adapter adapter2 = this.mRecyclerView.getAdapter();
        this.mAccessibilityProvider.onDetachAdapter(adapter2);
        unregisterCurrentItemDataSetTracker(adapter2);
        this.mRecyclerView.setAdapter(adapter);
        this.mCurrentItem = 0;
        restorePendingState();
        this.mAccessibilityProvider.onAttachAdapter(adapter);
        registerCurrentItemDataSetTracker(adapter);
    }

    public void setCurrentItem(int i2) {
        setCurrentItem(i2, true);
    }

    public void setCurrentItemInternal(int i2, boolean z) {
        int i7;
        RecyclerView.Adapter adapter = getAdapter();
        if (adapter == null) {
            if (this.mPendingCurrentItem != -1) {
                this.mPendingCurrentItem = Math.max(i2, 0);
            }
        } else if (adapter.getItemCount() > 0) {
            int min = Math.min(Math.max(i2, 0), adapter.getItemCount() - 1);
            if (min != this.mCurrentItem || !this.mScrollEventAdapter.isIdle()) {
                int i8 = this.mCurrentItem;
                if (min != i8 || !z) {
                    double d = (double) i8;
                    this.mCurrentItem = min;
                    this.mAccessibilityProvider.onSetNewCurrentItem();
                    if (!this.mScrollEventAdapter.isIdle()) {
                        d = this.mScrollEventAdapter.getRelativeScrollPosition();
                    }
                    this.mScrollEventAdapter.notifyProgrammaticScroll(min, z);
                    if (!z) {
                        this.mRecyclerView.scrollToPosition(min);
                        return;
                    }
                    double d2 = (double) min;
                    if (Math.abs(d2 - d) > 3.0d) {
                        RecyclerView recyclerView = this.mRecyclerView;
                        if (d2 > d) {
                            i7 = min - 3;
                        } else {
                            i7 = min + 3;
                        }
                        recyclerView.scrollToPosition(i7);
                        RecyclerView recyclerView2 = this.mRecyclerView;
                        recyclerView2.post(new SmoothScrollToPosition(min, recyclerView2));
                        return;
                    }
                    this.mRecyclerView.smoothScrollToPosition(min);
                }
            }
        }
    }

    public void setLayoutDirection(int i2) {
        super.setLayoutDirection(i2);
        this.mAccessibilityProvider.onSetLayoutDirection();
    }

    public void setOffscreenPageLimit(int i2) {
        if (i2 >= 1 || i2 == -1) {
            this.mOffscreenPageLimit = i2;
            this.mRecyclerView.requestLayout();
            return;
        }
        throw new IllegalArgumentException("Offscreen page limit must be OFFSCREEN_PAGE_LIMIT_DEFAULT or a number > 0");
    }

    public void setOverScrollMode(int i2) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.setOverScrollMode(i2);
        }
        super.setOverScrollMode(i2);
    }

    public void setPageTransformer(PageTransformer pageTransformer) {
        if (pageTransformer != null) {
            if (!this.mSavedItemAnimatorPresent) {
                this.mSavedItemAnimator = this.mRecyclerView.getItemAnimator();
                this.mSavedItemAnimatorPresent = true;
            }
            this.mRecyclerView.setItemAnimator((RecyclerView.ItemAnimator) null);
        } else if (this.mSavedItemAnimatorPresent) {
            this.mRecyclerView.setItemAnimator(this.mSavedItemAnimator);
            this.mSavedItemAnimator = null;
            this.mSavedItemAnimatorPresent = false;
        }
        if (pageTransformer != this.mPageTransformerAdapter.getPageTransformer()) {
            this.mPageTransformerAdapter.setPageTransformer(pageTransformer);
            requestTransform();
        }
    }

    public void setUserInputEnabled(boolean z) {
        this.mUserInputEnabled = z;
        this.mAccessibilityProvider.onSetUserInputEnabled();
    }

    public void snapToPage() {
        View findSnapView = this.mPagerSnapHelper.findSnapView(this.mLayoutManager);
        if (findSnapView != null) {
            int[] calculateDistanceToFinalSnap = this.mPagerSnapHelper.calculateDistanceToFinalSnap(this.mLayoutManager, findSnapView);
            int i2 = calculateDistanceToFinalSnap[0];
            if (i2 != 0 || calculateDistanceToFinalSnap[1] != 0) {
                this.mRecyclerView.smoothScrollBy(i2, calculateDistanceToFinalSnap[1]);
            }
        }
    }

    public void unregisterOnPageChangeCallback(OnPageChangeCallback onPageChangeCallback) {
        this.mExternalPageChangeCallbacks.removeOnPageChangeCallback(onPageChangeCallback);
    }

    public void updateCurrentItem() {
        PagerSnapHelper pagerSnapHelper = this.mPagerSnapHelper;
        if (pagerSnapHelper != null) {
            View findSnapView = pagerSnapHelper.findSnapView(this.mLayoutManager);
            if (findSnapView != null) {
                int position = this.mLayoutManager.getPosition(findSnapView);
                if (position != this.mCurrentItem && getScrollState() == 0) {
                    this.mPageChangeEventDispatcher.onPageSelected(position);
                }
                this.mCurrentItemDirty = false;
                return;
            }
            return;
        }
        throw new IllegalStateException("Design assumption violated.");
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() {
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }

            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            public SavedState createFromParcel(Parcel parcel) {
                return createFromParcel(parcel, (ClassLoader) null);
            }
        };
        Parcelable mAdapterState;
        int mCurrentItem;
        int mRecyclerViewId;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            readValues(parcel, classLoader);
        }

        private void readValues(Parcel parcel, ClassLoader classLoader) {
            this.mRecyclerViewId = parcel.readInt();
            this.mCurrentItem = parcel.readInt();
            this.mAdapterState = parcel.readParcelable(classLoader);
        }

        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.mRecyclerViewId);
            parcel.writeInt(this.mCurrentItem);
            parcel.writeParcelable(this.mAdapterState, i2);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public void setCurrentItem(int i2, boolean z) {
        if (!isFakeDragging()) {
            setCurrentItemInternal(i2, z);
            return;
        }
        throw new IllegalStateException("Cannot change current item when ViewPager2 is fake dragging");
    }

    public void setOrientation(int i2) {
        this.mLayoutManager.setOrientation(i2);
        this.mAccessibilityProvider.onSetOrientation();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public abstract class AccessibilityProvider {
        private AccessibilityProvider() {
        }

        public boolean handlesGetAccessibilityClassName() {
            return false;
        }

        public boolean handlesLmPerformAccessibilityAction(int i2) {
            return false;
        }

        public boolean handlesPerformAccessibilityAction(int i2, Bundle bundle) {
            return false;
        }

        public boolean handlesRvGetAccessibilityClassName() {
            return false;
        }

        public String onGetAccessibilityClassName() {
            throw new IllegalStateException("Not implemented.");
        }

        public boolean onLmPerformAccessibilityAction(int i2) {
            throw new IllegalStateException("Not implemented.");
        }

        public boolean onPerformAccessibilityAction(int i2, Bundle bundle) {
            throw new IllegalStateException("Not implemented.");
        }

        public CharSequence onRvGetAccessibilityClassName() {
            throw new IllegalStateException("Not implemented.");
        }

        public void onRestorePendingState() {
        }

        public void onSetLayoutDirection() {
        }

        public void onSetNewCurrentItem() {
        }

        public void onSetOrientation() {
        }

        public void onSetUserInputEnabled() {
        }

        public void onAttachAdapter(RecyclerView.Adapter<?> adapter) {
        }

        public void onDetachAdapter(RecyclerView.Adapter<?> adapter) {
        }

        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        }

        public void onLmInitializeAccessibilityNodeInfo(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        }

        public void onRvInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        }

        public void onInitialize(CompositeOnPageChangeCallback compositeOnPageChangeCallback, RecyclerView recyclerView) {
        }

        public void onLmInitializeAccessibilityNodeInfoForItem(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class OnPageChangeCallback {
        public void onPageScrollStateChanged(int i2) {
        }

        public void onPageSelected(int i2) {
        }

        public void onPageScrolled(int i2, float f, int i7) {
        }
    }
}
