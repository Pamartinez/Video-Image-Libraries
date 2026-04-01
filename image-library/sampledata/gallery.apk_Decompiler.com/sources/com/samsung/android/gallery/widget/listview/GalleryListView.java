package com.samsung.android.gallery.widget.listview;

import A4.C0369d;
import A4.C0377l;
import A4.L;
import F6.d;
import F6.f;
import Fa.F;
import Fb.c;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.abstraction.GridSpans;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.tables.SpecProvider;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$integer;
import com.samsung.android.gallery.widget.abstraction.SelectableChecker;
import com.samsung.android.gallery.widget.listview.scroller.Scroller;
import com.samsung.android.gallery.widget.listview.selection.OnItemCheckChangeListener;
import com.samsung.android.gallery.widget.listview.selection.SelectionManager;
import com.samsung.android.gallery.widget.listview.selection.SelectionManagerInterface;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GalleryListView extends GalleryRecyclerView implements SelectionManagerInterface {
    protected final Blackboard mBlackboard;
    private boolean mDisableSelectMode = false;
    private RecyclerView.OnItemTouchListener mDragSelectTouchListener;
    protected final GridSpans mGridSpans = new GridSpans();
    private BooleanSupplier mIsAllowAdvancedMouseEvent = new f(1);
    private boolean mIsDrawerAnimating = false;
    private BooleanSupplier mIsFadingEdgeExtended = new f(3);
    private CopyOnWriteArrayList<onTouchCancelListener> mOnTouchCancelListeners;
    private CopyOnWriteArrayList<onTouchUpListener> mOnTouchUpListeners;
    private int mPendingUpdateScrollPosition;
    private SelectionManager mSelectionManager;
    final SpecProvider mSpecProvider = new SpecProvider() {
        public int getColumnCount() {
            return GalleryListView.this.mGridSpans.spanOf();
        }

        public int[] getHeightSpec() {
            Resources resources = GalleryListView.this.getResources();
            return new int[]{resources.getDimensionPixelOffset(R$dimen.real_ratio_default_height), resources.getDimensionPixelOffset(R$dimen.real_ratio_min_height), resources.getDimensionPixelOffset(R$dimen.real_ratio_max_height)};
        }

        public int getMonthXsColumnCount() {
            return GalleryListView.this.mGridSpans.spanMonthMax();
        }

        public int getRealRatioMaxColumnCount() {
            return GalleryListView.this.getResources().getInteger(R$integer.real_ratio_max_column_count);
        }

        public int[] getSpec(int i2, int i7) {
            return GalleryListView.this.getRealRatioSpec(i2, i7);
        }

        public int getWidthSpec(int i2) {
            RecyclerView.LayoutManager layoutManager = GalleryListView.this.getLayoutManager();
            if (!(layoutManager instanceof GalleryGridLayoutManager)) {
                return GalleryListView.this.getWidth();
            }
            return GalleryListView.this.getListWidth() - ((GalleryGridLayoutManager) layoutManager).getHintHorizontalPadding(i2);
        }

        public int getYearColumnCount() {
            return GalleryListView.this.mGridSpans.spanYear();
        }
    };
    private boolean mTouchOngoing = false;
    private BooleanSupplier mUseDelayedCancelEvent = new f(2);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface onTouchCancelListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface onTouchUpListener {
        void touchUpOnSelectionMode();
    }

    public GalleryListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mBlackboard = Blackboard.getInstance(context.toString());
    }

    private void LogViewInfo(ViewParent viewParent) {
        String str = this.TAG;
        Log.e(str, "view = " + viewParent);
        Log.e(this.TAG, "    getClass = ".concat(viewParent.getClass().getSimpleName()));
        if (viewParent instanceof View) {
            View view = (View) viewParent;
            String str2 = this.TAG;
            Log.e(str2, "    getId = " + view.getId());
            String str3 = this.TAG;
            Log.e(str3, "    layoutParams = " + view.getLayoutParams());
        }
    }

    private boolean checkConsumeTouchEventOnAdvancedMouseAction(MotionEvent motionEvent) {
        if (motionEvent.getToolType(0) == 3 && this.mIsAllowAdvancedMouseEvent.getAsBoolean() && DeviceInfo.isAdvancedMouseCompat(getContext()) && !isSelectionMode()) {
            Scroller scroller = getScroller();
            if (scroller == null || !scroller.onInterceptTouchEvent(this, motionEvent)) {
                GalleryListAdapter adapter = getAdapter();
                if (adapter != null && adapter.isOnKeyCombination()) {
                    return false;
                }
                if ((adapter == null || !ViewUtils.isTouchedOnLocalView(adapter.getHeaderView(), motionEvent, computeVerticalScrollOffset())) && adapter != null && adapter.checkConsumeTouchEventOnAdvancedMouseAction(motionEvent)) {
                    return true;
                }
            } else {
                scroller.onTouchEvent(this, motionEvent);
                return true;
            }
        }
        return false;
    }

    private void clearTouchCancelListeners() {
        CopyOnWriteArrayList<onTouchCancelListener> copyOnWriteArrayList = this.mOnTouchCancelListeners;
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.clear();
        }
    }

    private void clearTouchUpListeners() {
        CopyOnWriteArrayList<onTouchUpListener> copyOnWriteArrayList = this.mOnTouchUpListeners;
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.clear();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$new$0() {
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$new$1() {
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$new$2() {
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$smoothScrollToPositionJumpIfNeeded$7(int i2) {
        smoothScrollToPositionRecyclerView(i2);
        updateScrollOffset();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateTouch$3() {
        this.mTouchOngoing = false;
    }

    public void addOnTouchCancelListener(onTouchCancelListener ontouchcancellistener) {
        if (this.mOnTouchCancelListeners == null) {
            this.mOnTouchCancelListeners = new CopyOnWriteArrayList<>();
        }
        this.mOnTouchCancelListeners.add(ontouchcancellistener);
    }

    public void addOnTouchUpListener(onTouchUpListener ontouchuplistener) {
        if (this.mOnTouchUpListeners == null) {
            this.mOnTouchUpListeners = new CopyOnWriteArrayList<>();
        }
        this.mOnTouchUpListeners.add(ontouchuplistener);
    }

    public void changeDepth(GridLayoutManager gridLayoutManager, int i2) {
        int i7 = this.mGridSpans.set(i2);
        if (i7 > 0) {
            gridLayoutManager.setSpanCount(i7);
        }
    }

    public void clearChildViews() {
        removeAllCachedViews();
        resetRecyclerViewCache();
        ViewUtils.removeAllViews(this);
    }

    public void decreaseSpans(GridLayoutManager gridLayoutManager) {
        int decrease = this.mGridSpans.decrease();
        if (decrease > 0) {
            gridLayoutManager.setSpanCount(decrease);
        }
    }

    public void destroy() {
        RecyclerView.OnItemTouchListener onItemTouchListener = this.mDragSelectTouchListener;
        if (onItemTouchListener != null) {
            removeOnItemTouchListener(onItemTouchListener);
            this.mDragSelectTouchListener = null;
        }
        super.destroy();
    }

    public void disableSelectMode(boolean z) {
        this.mDisableSelectMode = z;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        updateTouch(motionEvent);
        if (this.mIsDrawerAnimating) {
            return true;
        }
        if (!checkConsumeTouchEventOnAdvancedMouseAction(motionEvent)) {
            return super.dispatchTouchEvent(motionEvent);
        }
        String str = this.TAG;
        Log.d(str, "consumed TouchEvent on Dex : " + motionEvent.getAction());
        return true;
    }

    public void enterSelectionMode(int i2) {
        Optional.ofNullable(getAdapter()).ifPresent(new C0369d(i2, 5));
    }

    public void exitSelectionMode(boolean z) {
        Optional.ofNullable(getAdapter()).ifPresent(new L(z, 11));
    }

    public int[] getActiveColumns() {
        return this.mGridSpans.getActiveSpans(isSelectionMode());
    }

    public Blackboard getBlackboard() {
        return this.mBlackboard;
    }

    public int getColumnCount() {
        return this.mGridSpans.spanOf();
    }

    public int getColumnCountByDepth(int i2) {
        return this.mGridSpans.spanOf(i2);
    }

    public int[] getColumns() {
        return this.mGridSpans.spans;
    }

    public int getDepth() {
        return this.mGridSpans.index;
    }

    public int getDepthFromColumnCount(int i2) {
        return this.mGridSpans.indexOf(i2);
    }

    public int getEndDepth() {
        boolean isSelectionMode = isSelectionMode();
        GridSpans gridSpans = this.mGridSpans;
        if (isSelectionMode) {
            return gridSpans.selectableRange()[1];
        }
        return gridSpans.indexMax();
    }

    public GridSpans getGridSpans() {
        return this.mGridSpans;
    }

    public int getHideDecoGrid() {
        int[] spanMonths = this.mGridSpans.spanMonths();
        if (spanMonths.length > 0) {
            return spanMonths[0];
        }
        return 20;
    }

    public int getMaxColumnCount() {
        return this.mGridSpans.spanMax();
    }

    public int getMaxDepth() {
        return this.mGridSpans.indexMax();
    }

    @Deprecated
    public int getMaxMonthColumnCount() {
        return this.mGridSpans.spanMonthMax();
    }

    public int getNextGrid(boolean z) {
        return this.mGridSpans.spanOf(getNextGridDepth(z));
    }

    public int getNextGridDepth(boolean z) {
        int i2;
        int i7;
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            i2 = ((GridLayoutManager) layoutManager).getSpanCount();
        } else {
            i2 = this.mGridSpans.spanOf();
        }
        int indexOf = this.mGridSpans.indexOf(i2);
        if (indexOf != this.mGridSpans.index) {
            Log.e((CharSequence) this.TAG, "getNextGridDepth wrong index", Integer.valueOf(indexOf), Integer.valueOf(this.mGridSpans.index), Integer.valueOf(i2));
            indexOf = this.mGridSpans.index;
        }
        GridSpans gridSpans = this.mGridSpans;
        if (!gridSpans.normalOrder ? !z : z) {
            i7 = indexOf + 1;
        } else {
            i7 = indexOf - 1;
        }
        if (i7 < 0) {
            return 0;
        }
        return Math.min(i7, gridSpans.indexMax());
    }

    public int[] getRealRatioSpec(int i2, int i7) {
        if (i2 == 3) {
            return new int[]{this.mGridSpans.spanMonthMax()};
        }
        if (i2 == 4) {
            return new int[]{this.mGridSpans.spanYear()};
        }
        return super.getRealRatioSpec(i2, i7);
    }

    public int getSelectedItemCount() {
        SelectionManager selectionManager = this.mSelectionManager;
        if (selectionManager == null) {
            return 0;
        }
        return selectionManager.getSelectedItemCount();
    }

    public ArrayList<Integer> getSelectedItemList() {
        SelectionManager selectionManager = this.mSelectionManager;
        if (selectionManager != null) {
            return selectionManager.getSelectedList();
        }
        return null;
    }

    public SpecProvider getSpecProvider() {
        return this.mSpecProvider;
    }

    public int getStartDepth() {
        if (isSelectionMode()) {
            return this.mGridSpans.selectableRange()[0];
        }
        return 0;
    }

    public void handleDensityChange() {
        int findFirstVisibleItemPositionCompat = findFirstVisibleItemPositionCompat();
        int i2 = 0;
        View childAt = getChildAt(0);
        if (childAt != null) {
            i2 = childAt.getTop() - getPaddingTop();
        }
        clearChildViews();
        scrollToPositionWithOffset(findFirstVisibleItemPositionCompat, i2);
    }

    public void increaseSpans(GridLayoutManager gridLayoutManager) {
        int increase = this.mGridSpans.increase();
        if (increase > 0) {
            gridLayoutManager.setSpanCount(increase);
        }
    }

    public boolean isDrawerAnimating() {
        return this.mIsDrawerAnimating;
    }

    public boolean isFadingEdgeExtended() {
        BooleanSupplier booleanSupplier = this.mIsFadingEdgeExtended;
        if (booleanSupplier == null || !booleanSupplier.getAsBoolean()) {
            return false;
        }
        return true;
    }

    public boolean isItemSelectable(int i2) {
        SelectableChecker<MediaItem> selectableChecker;
        GalleryListAdapter adapter = getAdapter();
        if (adapter != null) {
            selectableChecker = adapter.getSelectableChecker();
        } else {
            selectableChecker = null;
        }
        if (selectableChecker == null || selectableChecker.isSupported(adapter.getMediaItemSync(i2))) {
            return true;
        }
        return false;
    }

    public boolean isListSelectable() {
        return !this.mDisableSelectMode;
    }

    public boolean isMultiPick() {
        GalleryListAdapter adapter = getAdapter();
        if (adapter == null || !adapter.isMultiPick()) {
            return false;
        }
        return true;
    }

    public boolean isNormalColumnOrder() {
        return this.mGridSpans.normalOrder;
    }

    public boolean isSelected(int i2) {
        return this.mSelectionManager.isSelected(Integer.valueOf(i2));
    }

    public boolean isSelectionMode() {
        GalleryListAdapter adapter = getAdapter();
        if (adapter == null || !adapter.isSelectionMode()) {
            return false;
        }
        return true;
    }

    public boolean isSimilarModeAnimating() {
        return false;
    }

    public boolean isThumbLoaded() {
        int findLastCompletelyVisibleItemPositionCompat = findLastCompletelyVisibleItemPositionCompat();
        for (int findFirstVisibleItemPositionCompat = findFirstVisibleItemPositionCompat(); findFirstVisibleItemPositionCompat <= findLastCompletelyVisibleItemPositionCompat; findFirstVisibleItemPositionCompat++) {
            ListViewHolder listViewHolder = (ListViewHolder) findViewHolderForAdapterPosition(findFirstVisibleItemPositionCompat);
            if (listViewHolder != null && listViewHolder.hasImageView() && listViewHolder.getImage().getDrawable() == null) {
                return false;
            }
        }
        return true;
    }

    public boolean isTouchOngoing() {
        return this.mTouchOngoing;
    }

    public boolean isTouchedOnViewRectRangeOfScroller(MotionEvent motionEvent) {
        Scroller scroller = getScroller();
        if (scroller == null || !scroller.isTouchedOnViewRectRange(motionEvent, 0)) {
            return false;
        }
        return true;
    }

    public void notifySelectedItemChanged() {
        Optional.ofNullable(getAdapter()).ifPresent(new F(11));
    }

    public void onAdapterChanged(RecyclerView.Adapter adapter) {
        super.onAdapterChanged(adapter);
        GalleryListAdapter adapter2 = getAdapter();
        if (this.mSelectionManager == null) {
            this.mSelectionManager = new SelectionManager(this);
            if (adapter2 != null && adapter2.isDragSelectSupported()) {
                DragSelectTouchListener dragSelectTouchListener = adapter2.getDragSelectTouchListener();
                this.mDragSelectTouchListener = dragSelectTouchListener;
                addOnItemTouchListener(dragSelectTouchListener);
            }
        }
        GalleryListAdapter galleryListAdapter = (GalleryListAdapter) adapter;
        galleryListAdapter.setSelectionManager(this.mSelectionManager);
        galleryListAdapter.setAdvancedMouseFocusManager();
        if (adapter2 != null) {
            adapter2.setGridSize(getColumnCount(), getHideDecoGrid());
        }
    }

    public void onConstructor(Context context, AttributeSet attributeSet) {
        super.onConstructor(context, attributeSet);
    }

    public void onDump(PrintWriter printWriter, String str) {
        super.onDump(printWriter, str);
        SelectionManager selectionManager = this.mSelectionManager;
        if (selectionManager != null) {
            selectionManager.onDump(printWriter, str);
        }
    }

    public void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        super.onLayout(z, i2, i7, i8, i10);
        int i11 = this.mPendingUpdateScrollPosition;
        if (i11 > 0) {
            scrollToPositionWithOffset(i11, 0);
            this.mPendingUpdateScrollPosition = 0;
        }
    }

    public void onSizeChanged(int i2, int i7, int i8, int i10) {
        if (i7 > 99999) {
            Log.e(this.TAG, "=============");
            LogViewInfo(this);
            for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
                LogViewInfo(parent);
            }
            Log.e(this.TAG, "=============");
            new InternalException("wrong size", String.valueOf(i7)).post();
        }
        super.onSizeChanged(i2, i7, i8, i10);
    }

    public void onTouchCancel() {
        if (this.mOnTouchCancelListeners != null) {
            Iterator it = new ArrayList(this.mOnTouchCancelListeners).iterator();
            while (it.hasNext()) {
                ((C0377l) ((onTouchCancelListener) it.next())).f2253a.updateSelectionToolBar();
            }
        }
    }

    public void onTouchUp() {
        if (isSelectionMode() && this.mOnTouchUpListeners != null) {
            Iterator it = new ArrayList(this.mOnTouchUpListeners).iterator();
            while (it.hasNext()) {
                ((onTouchUpListener) it.next()).touchUpOnSelectionMode();
            }
        }
    }

    public void pendingUpdateScrollPosition(int i2) {
        this.mPendingUpdateScrollPosition = i2;
    }

    public void resetDefaultMaxScroll() {
        Optional.ofNullable(getScroller()).ifPresent(new F(9));
    }

    public void resetScrollbarHeight() {
        Optional.ofNullable(getScroller()).ifPresent(new F(12));
    }

    public void resetTouch() {
        this.mTouchOngoing = false;
    }

    public void select(int i2, boolean z) {
        select(i2, z, true);
    }

    public boolean selectAll() {
        GalleryListAdapter adapter = getAdapter();
        if (adapter == null || !adapter.selectAll()) {
            return false;
        }
        return true;
    }

    public void setColumnCount(int[] iArr) {
        this.mGridSpans.update(iArr);
    }

    public void setDrawerAnimating(boolean z) {
        this.mIsDrawerAnimating = z;
    }

    public void setFadingEdgeExtendSupplier(BooleanSupplier booleanSupplier) {
        this.mIsFadingEdgeExtended = booleanSupplier;
    }

    public void setIsAllowAdvancedMouseEvent(BooleanSupplier booleanSupplier) {
        this.mIsAllowAdvancedMouseEvent = booleanSupplier;
    }

    public void setOnCheckChangeRunnable(Runnable runnable) {
        this.mSelectionManager.setUpdateToolbarRunnable(runnable);
    }

    public void setOnItemCheckChangeListener(OnItemCheckChangeListener onItemCheckChangeListener) {
        this.mSelectionManager.setOnItemCheckChangedListener(onItemCheckChangeListener);
    }

    public void setStartDepth(int i2) {
        this.mGridSpans.set(i2);
    }

    public void smoothScrollToPositionJumpIfNeeded(int i2) {
        int childCount;
        if (i2 != 0 || getAdapter() == null || !getAdapter().isRealRatio() || (childCount = getChildCount() * 2) <= 0 || childCount >= findFirstVisibleItemPositionCompat()) {
            super.smoothScrollToPositionJumpIfNeeded(i2);
            updateScrollOffset();
            return;
        }
        scrollToPosition(childCount);
        post(new d(this, i2, 1));
    }

    public void unSelectAll() {
        Optional.ofNullable(getAdapter()).ifPresent(new F(10));
    }

    public void updateTouch(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action == 3) {
                    if (this.mUseDelayedCancelEvent.getAsBoolean()) {
                        ThreadUtil.postOnUiThreadDelayed(new Fb.d(0, this), 100);
                    } else {
                        this.mTouchOngoing = false;
                    }
                    onTouchCancel();
                    return;
                } else if (action != 5) {
                    if (action != 6) {
                        return;
                    }
                }
            }
            this.mTouchOngoing = false;
            onTouchUp();
            return;
        }
        this.mTouchOngoing = true;
        clearTouchUpListeners();
        clearTouchCancelListeners();
    }

    public void useDelayedCancelEvent(BooleanSupplier booleanSupplier) {
        this.mUseDelayedCancelEvent = booleanSupplier;
    }

    public void select(int i2, boolean z, boolean z3) {
        Optional.ofNullable(getAdapter()).ifPresent(new c(i2, z, z3));
    }

    public void onGridChanged() {
    }

    public void enableTabFocusBlock(boolean z) {
    }

    public void setSimilarModeAnimating(boolean z) {
    }

    public void startMonthForViewingClickedAnimation(int i2, RectF rectF) {
    }
}
