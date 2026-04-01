package androidx.recyclerview.widget;

import A.a;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GridLayoutManager extends LinearLayoutManager {
    int[] mCachedBorders;
    final Rect mDecorInsets = new Rect();
    boolean mPendingSpanCountChange = false;
    final SparseIntArray mPreLayoutSpanIndexCache = new SparseIntArray();
    final SparseIntArray mPreLayoutSpanSizeCache = new SparseIntArray();
    View[] mSet;
    int mSpanCount = -1;
    SpanSizeLookup mSpanSizeLookup = new DefaultSpanSizeLookup();
    private boolean mUsingSpansToEstimateScrollBarDimensions;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DefaultSpanSizeLookup extends SpanSizeLookup {
        public int getSpanIndex(int i2, int i7) {
            return i2 % i7;
        }

        public int getSpanSize(int i2) {
            return 1;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class SpanSizeLookup {
        private boolean mCacheSpanGroupIndices = false;
        private boolean mCacheSpanIndices = false;
        final SparseIntArray mSpanGroupIndexCache = new SparseIntArray();
        final SparseIntArray mSpanIndexCache = new SparseIntArray();

        public static int findFirstKeyLessThan(SparseIntArray sparseIntArray, int i2) {
            int size = sparseIntArray.size() - 1;
            int i7 = 0;
            while (i7 <= size) {
                int i8 = (i7 + size) >>> 1;
                if (sparseIntArray.keyAt(i8) < i2) {
                    i7 = i8 + 1;
                } else {
                    size = i8 - 1;
                }
            }
            int i10 = i7 - 1;
            if (i10 < 0 || i10 >= sparseIntArray.size()) {
                return -1;
            }
            return sparseIntArray.keyAt(i10);
        }

        public int getCachedSpanGroupIndex(int i2, int i7) {
            if (!this.mCacheSpanGroupIndices) {
                return getSpanGroupIndex(i2, i7);
            }
            int i8 = this.mSpanGroupIndexCache.get(i2, -1);
            if (i8 != -1) {
                return i8;
            }
            int spanGroupIndex = getSpanGroupIndex(i2, i7);
            this.mSpanGroupIndexCache.put(i2, spanGroupIndex);
            return spanGroupIndex;
        }

        public int getCachedSpanIndex(int i2, int i7) {
            if (!this.mCacheSpanIndices) {
                return getSpanIndex(i2, i7);
            }
            int i8 = this.mSpanIndexCache.get(i2, -1);
            if (i8 != -1) {
                return i8;
            }
            int spanIndex = getSpanIndex(i2, i7);
            this.mSpanIndexCache.put(i2, spanIndex);
            return spanIndex;
        }

        public int getSpanGroupIndex(int i2, int i7) {
            int i8;
            int i10;
            int i11;
            int findFirstKeyLessThan;
            if (!this.mCacheSpanGroupIndices || (findFirstKeyLessThan = findFirstKeyLessThan(this.mSpanGroupIndexCache, i2)) == -1) {
                i11 = 0;
                i10 = 0;
                i8 = 0;
            } else {
                i10 = this.mSpanGroupIndexCache.get(findFirstKeyLessThan);
                i8 = findFirstKeyLessThan + 1;
                i11 = getSpanSize(findFirstKeyLessThan) + getCachedSpanIndex(findFirstKeyLessThan, i7);
                if (i11 == i7) {
                    i10++;
                    i11 = 0;
                }
            }
            int spanSize = getSpanSize(i2);
            while (i8 < i2) {
                int spanSize2 = getSpanSize(i8);
                int i12 = i11 + spanSize2;
                if (i12 == i7) {
                    i10++;
                    i12 = 0;
                } else if (i12 > i7) {
                    i10++;
                    i12 = spanSize2;
                }
                i8++;
            }
            if (i11 + spanSize > i7) {
                return i10 + 1;
            }
            return i10;
        }

        /* JADX WARNING: Removed duplicated region for block: B:10:0x0024  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int getSpanIndex(int r6, int r7) {
            /*
                r5 = this;
                int r0 = r5.getSpanSize(r6)
                r1 = 0
                if (r0 != r7) goto L_0x0008
                return r1
            L_0x0008:
                boolean r2 = r5.mCacheSpanIndices
                if (r2 == 0) goto L_0x0020
                android.util.SparseIntArray r2 = r5.mSpanIndexCache
                int r2 = findFirstKeyLessThan(r2, r6)
                if (r2 < 0) goto L_0x0020
                android.util.SparseIntArray r3 = r5.mSpanIndexCache
                int r3 = r3.get(r2)
                int r4 = r5.getSpanSize(r2)
                int r4 = r4 + r3
                goto L_0x0030
            L_0x0020:
                r2 = r1
                r4 = r2
            L_0x0022:
                if (r2 >= r6) goto L_0x0033
                int r3 = r5.getSpanSize(r2)
                int r4 = r4 + r3
                if (r4 != r7) goto L_0x002d
                r4 = r1
                goto L_0x0030
            L_0x002d:
                if (r4 <= r7) goto L_0x0030
                r4 = r3
            L_0x0030:
                int r2 = r2 + 1
                goto L_0x0022
            L_0x0033:
                int r0 = r0 + r4
                if (r0 > r7) goto L_0x0037
                return r4
            L_0x0037:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup.getSpanIndex(int, int):int");
        }

        public abstract int getSpanSize(int i2);

        public void invalidateSpanGroupIndexCache() {
            this.mSpanGroupIndexCache.clear();
        }

        public void invalidateSpanIndexCache() {
            this.mSpanIndexCache.clear();
        }
    }

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i2, int i7) {
        super(context, attributeSet, i2, i7);
        setSpanCount(RecyclerView.LayoutManager.getProperties(context, attributeSet, i2, i7).spanCount);
    }

    private void assignSpans(RecyclerView.Recycler recycler, RecyclerView.State state, int i2, boolean z) {
        int i7;
        int i8;
        int i10;
        int i11 = 0;
        if (z) {
            i10 = 1;
            i7 = i2;
            i8 = 0;
        } else {
            i8 = i2 - 1;
            i7 = -1;
            i10 = -1;
        }
        while (i8 != i7) {
            View view = this.mSet[i8];
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            int spanSize = getSpanSize(recycler, state, getPosition(view));
            layoutParams.mSpanSize = spanSize;
            layoutParams.mSpanIndex = i11;
            i11 += spanSize;
            i8 += i10;
        }
    }

    private void cachePreLayoutSpanMapping() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            LayoutParams layoutParams = (LayoutParams) getChildAt(i2).getLayoutParams();
            int viewLayoutPosition = layoutParams.getViewLayoutPosition();
            this.mPreLayoutSpanSizeCache.put(viewLayoutPosition, layoutParams.getSpanSize());
            this.mPreLayoutSpanIndexCache.put(viewLayoutPosition, layoutParams.getSpanIndex());
        }
    }

    private void calculateItemBorders(int i2) {
        this.mCachedBorders = calculateItemBorders(this.mCachedBorders, this.mSpanCount, i2);
    }

    private void clearPreLayoutSpanMappingCache() {
        this.mPreLayoutSpanSizeCache.clear();
        this.mPreLayoutSpanIndexCache.clear();
    }

    private int computeScrollOffsetWithSpanInfo(RecyclerView.State state) {
        int i2;
        if (!(getChildCount() == 0 || state.getItemCount() == 0)) {
            ensureLayoutState();
            boolean isSmoothScrollbarEnabled = isSmoothScrollbarEnabled();
            View findFirstVisibleChildClosestToStart = findFirstVisibleChildClosestToStart(!isSmoothScrollbarEnabled, true);
            View findFirstVisibleChildClosestToEnd = findFirstVisibleChildClosestToEnd(!isSmoothScrollbarEnabled, true);
            if (!(findFirstVisibleChildClosestToStart == null || findFirstVisibleChildClosestToEnd == null)) {
                int cachedSpanGroupIndex = this.mSpanSizeLookup.getCachedSpanGroupIndex(getPosition(findFirstVisibleChildClosestToStart), this.mSpanCount);
                int cachedSpanGroupIndex2 = this.mSpanSizeLookup.getCachedSpanGroupIndex(getPosition(findFirstVisibleChildClosestToEnd), this.mSpanCount);
                int min = Math.min(cachedSpanGroupIndex, cachedSpanGroupIndex2);
                int max = Math.max(cachedSpanGroupIndex, cachedSpanGroupIndex2);
                int cachedSpanGroupIndex3 = this.mSpanSizeLookup.getCachedSpanGroupIndex(state.getItemCount() - 1, this.mSpanCount) + 1;
                if (this.mShouldReverseLayout) {
                    i2 = Math.max(0, (cachedSpanGroupIndex3 - max) - 1);
                } else {
                    i2 = Math.max(0, min);
                }
                if (!isSmoothScrollbarEnabled) {
                    return i2;
                }
                return Math.round((((float) i2) * (((float) Math.abs(this.mOrientationHelper.getDecoratedEnd(findFirstVisibleChildClosestToEnd) - this.mOrientationHelper.getDecoratedStart(findFirstVisibleChildClosestToStart))) / ((float) ((this.mSpanSizeLookup.getCachedSpanGroupIndex(getPosition(findFirstVisibleChildClosestToEnd), this.mSpanCount) - this.mSpanSizeLookup.getCachedSpanGroupIndex(getPosition(findFirstVisibleChildClosestToStart), this.mSpanCount)) + 1)))) + ((float) (this.mOrientationHelper.getStartAfterPadding() - this.mOrientationHelper.getDecoratedStart(findFirstVisibleChildClosestToStart))));
            }
        }
        return 0;
    }

    private int computeScrollRangeWithSpanInfo(RecyclerView.State state) {
        if (!(getChildCount() == 0 || state.getItemCount() == 0)) {
            ensureLayoutState();
            View findFirstVisibleChildClosestToStart = findFirstVisibleChildClosestToStart(!isSmoothScrollbarEnabled(), true);
            View findFirstVisibleChildClosestToEnd = findFirstVisibleChildClosestToEnd(!isSmoothScrollbarEnabled(), true);
            if (!(findFirstVisibleChildClosestToStart == null || findFirstVisibleChildClosestToEnd == null)) {
                if (!isSmoothScrollbarEnabled()) {
                    return this.mSpanSizeLookup.getCachedSpanGroupIndex(state.getItemCount() - 1, this.mSpanCount) + 1;
                }
                int decoratedEnd = this.mOrientationHelper.getDecoratedEnd(findFirstVisibleChildClosestToEnd) - this.mOrientationHelper.getDecoratedStart(findFirstVisibleChildClosestToStart);
                int cachedSpanGroupIndex = this.mSpanSizeLookup.getCachedSpanGroupIndex(getPosition(findFirstVisibleChildClosestToStart), this.mSpanCount);
                return (int) ((((float) decoratedEnd) / ((float) ((this.mSpanSizeLookup.getCachedSpanGroupIndex(getPosition(findFirstVisibleChildClosestToEnd), this.mSpanCount) - cachedSpanGroupIndex) + 1))) * ((float) (this.mSpanSizeLookup.getCachedSpanGroupIndex(state.getItemCount() - 1, this.mSpanCount) + 1)));
            }
        }
        return 0;
    }

    private void ensureAnchorIsInCorrectSpan(RecyclerView.Recycler recycler, RecyclerView.State state, LinearLayoutManager.AnchorInfo anchorInfo, int i2) {
        boolean z;
        if (i2 == 1) {
            z = true;
        } else {
            z = false;
        }
        int spanIndex = getSpanIndex(recycler, state, anchorInfo.mPosition);
        if (z) {
            while (spanIndex > 0) {
                int i7 = anchorInfo.mPosition;
                if (i7 > 0) {
                    int i8 = i7 - 1;
                    anchorInfo.mPosition = i8;
                    spanIndex = getSpanIndex(recycler, state, i8);
                } else {
                    return;
                }
            }
            return;
        }
        int itemCount = state.getItemCount() - 1;
        int i10 = anchorInfo.mPosition;
        while (i10 < itemCount) {
            int i11 = i10 + 1;
            int spanIndex2 = getSpanIndex(recycler, state, i11);
            if (spanIndex2 <= spanIndex) {
                break;
            }
            i10 = i11;
            spanIndex = spanIndex2;
        }
        anchorInfo.mPosition = i10;
    }

    private void ensureViewSet() {
        View[] viewArr = this.mSet;
        if (viewArr == null || viewArr.length != this.mSpanCount) {
            this.mSet = new View[this.mSpanCount];
        }
    }

    private int getSpanGroupIndex(RecyclerView.Recycler recycler, RecyclerView.State state, int i2) {
        if (!state.isPreLayout()) {
            return this.mSpanSizeLookup.getCachedSpanGroupIndex(i2, this.mSpanCount);
        }
        int convertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(i2);
        if (convertPreLayoutPositionToPostLayout != -1) {
            return this.mSpanSizeLookup.getCachedSpanGroupIndex(convertPreLayoutPositionToPostLayout, this.mSpanCount);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + i2);
        return 0;
    }

    private int getSpanIndex(RecyclerView.Recycler recycler, RecyclerView.State state, int i2) {
        if (!state.isPreLayout()) {
            return this.mSpanSizeLookup.getCachedSpanIndex(i2, this.mSpanCount);
        }
        int i7 = this.mPreLayoutSpanIndexCache.get(i2, -1);
        if (i7 != -1) {
            return i7;
        }
        int convertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(i2);
        if (convertPreLayoutPositionToPostLayout != -1) {
            return this.mSpanSizeLookup.getCachedSpanIndex(convertPreLayoutPositionToPostLayout, this.mSpanCount);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i2);
        return 0;
    }

    private int getSpanSize(RecyclerView.Recycler recycler, RecyclerView.State state, int i2) {
        if (!state.isPreLayout()) {
            return this.mSpanSizeLookup.getSpanSize(i2);
        }
        int i7 = this.mPreLayoutSpanSizeCache.get(i2, -1);
        if (i7 != -1) {
            return i7;
        }
        int convertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(i2);
        if (convertPreLayoutPositionToPostLayout != -1) {
            return this.mSpanSizeLookup.getSpanSize(convertPreLayoutPositionToPostLayout);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i2);
        return 1;
    }

    private void guessMeasurement(float f, int i2) {
        calculateItemBorders(Math.max(Math.round(f * ((float) this.mSpanCount)), i2));
    }

    private void measureChild(View view, int i2, boolean z) {
        int i7;
        int i8;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        Rect rect = layoutParams.mDecorInsets;
        int i10 = rect.top + rect.bottom + layoutParams.topMargin + layoutParams.bottomMargin;
        int i11 = rect.left + rect.right + layoutParams.leftMargin + layoutParams.rightMargin;
        int spaceForSpanRange = getSpaceForSpanRange(layoutParams.mSpanIndex, layoutParams.mSpanSize);
        if (this.mOrientation == 1) {
            i7 = RecyclerView.LayoutManager.getChildMeasureSpec(spaceForSpanRange, i2, i11, layoutParams.width, false);
            i8 = RecyclerView.LayoutManager.getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), getHeightMode(), i10, layoutParams.height, true);
        } else {
            int childMeasureSpec = RecyclerView.LayoutManager.getChildMeasureSpec(spaceForSpanRange, i2, i10, layoutParams.height, false);
            int childMeasureSpec2 = RecyclerView.LayoutManager.getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), getWidthMode(), i11, layoutParams.width, true);
            i8 = childMeasureSpec;
            i7 = childMeasureSpec2;
        }
        measureChildWithDecorationsAndMargin(view, i7, i8, z);
    }

    private void measureChildWithDecorationsAndMargin(View view, int i2, int i7, boolean z) {
        boolean z3;
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        if (z) {
            z3 = shouldReMeasureChild(view, i2, i7, layoutParams);
        } else {
            z3 = shouldMeasureChild(view, i2, i7, layoutParams);
        }
        if (z3) {
            view.measure(i2, i7);
        }
    }

    private void updateMeasurements() {
        int height;
        int paddingTop;
        if (getOrientation() == 1) {
            height = getWidth() - getPaddingRight();
            paddingTop = getPaddingLeft();
        } else {
            height = getHeight() - getPaddingBottom();
            paddingTop = getPaddingTop();
        }
        calculateItemBorders(height - paddingTop);
    }

    public boolean checkLayoutParams(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void collectPrefetchPositionsForLayoutState(RecyclerView.State state, LinearLayoutManager.LayoutState layoutState, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int i2 = this.mSpanCount;
        for (int i7 = 0; i7 < this.mSpanCount && layoutState.hasMore(state) && i2 > 0; i7++) {
            int i8 = layoutState.mCurrentPosition;
            layoutPrefetchRegistry.addPosition(i8, Math.max(0, layoutState.mScrollingOffset));
            i2 -= this.mSpanSizeLookup.getSpanSize(i8);
            layoutState.mCurrentPosition += layoutState.mItemDirection;
        }
    }

    public int computeHorizontalScrollOffset(RecyclerView.State state) {
        if (this.mUsingSpansToEstimateScrollBarDimensions) {
            return computeScrollOffsetWithSpanInfo(state);
        }
        return super.computeHorizontalScrollOffset(state);
    }

    public int computeHorizontalScrollRange(RecyclerView.State state) {
        if (this.mUsingSpansToEstimateScrollBarDimensions) {
            return computeScrollRangeWithSpanInfo(state);
        }
        return super.computeHorizontalScrollRange(state);
    }

    public int computeVerticalScrollOffset(RecyclerView.State state) {
        if (this.mUsingSpansToEstimateScrollBarDimensions) {
            return computeScrollOffsetWithSpanInfo(state);
        }
        return super.computeVerticalScrollOffset(state);
    }

    public int computeVerticalScrollRange(RecyclerView.State state) {
        if (this.mUsingSpansToEstimateScrollBarDimensions) {
            return computeScrollRangeWithSpanInfo(state);
        }
        return super.computeVerticalScrollRange(state);
    }

    public View findReferenceChild(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z, boolean z3) {
        int i2;
        int i7 = 0;
        int max = Math.max(getChildCount(), 0);
        if (z3) {
            i7 = max - 1;
            max = -1;
            i2 = -1;
        } else {
            i2 = 1;
        }
        int itemCount = state.getItemCount();
        ensureLayoutState();
        int startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
        int endAfterPadding = this.mOrientationHelper.getEndAfterPadding();
        View view = null;
        View view2 = null;
        while (i7 != max) {
            View childAt = getChildAt(i7);
            int position = getPosition(childAt);
            if (position >= 0 && position < itemCount && getSpanIndex(recycler, state, position) == 0) {
                if (((RecyclerView.LayoutParams) childAt.getLayoutParams()).isItemRemoved()) {
                    if (view2 == null) {
                        view2 = childAt;
                    }
                } else if (this.mOrientationHelper.getDecoratedStart(childAt) < endAfterPadding && this.mOrientationHelper.getDecoratedEnd(childAt) > startAfterPadding) {
                    return childAt;
                } else {
                    if (view == null) {
                        view = childAt;
                    }
                }
            }
            i7 += i2;
        }
        if (view != null) {
            return view;
        }
        return view2;
    }

    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        if (this.mOrientation == 0) {
            return new LayoutParams(-2, -1);
        }
        return new LayoutParams(-1, -2);
    }

    public RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    public int getColumnCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation == 1) {
            if (state.getItemCount() < 1) {
                return this.mSpanCount;
            }
            if (getSpanGroupIndex(recycler, state, state.getItemCount() - 1) + 1 != 1 || state.getItemCount() >= this.mSpanCount) {
                return this.mSpanCount;
            }
            return state.getItemCount();
        } else if (state.getItemCount() < 1) {
            return 0;
        } else {
            return getSpanGroupIndex(recycler, state, state.getItemCount() - 1) + 1;
        }
    }

    public int getRowCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation == 0) {
            return this.mSpanCount;
        }
        if (state.getItemCount() < 1) {
            return 0;
        }
        return getSpanGroupIndex(recycler, state, state.getItemCount() - 1) + 1;
    }

    public int getSpaceForSpanRange(int i2, int i7) {
        if (this.mOrientation != 1 || !isLayoutRTL()) {
            int[] iArr = this.mCachedBorders;
            return iArr[i7 + i2] - iArr[i2];
        }
        int[] iArr2 = this.mCachedBorders;
        int i8 = this.mSpanCount;
        return iArr2[i8 - i2] - iArr2[(i8 - i2) - i7];
    }

    public int getSpanCount() {
        return this.mSpanCount;
    }

    public SpanSizeLookup getSpanSizeLookup() {
        return this.mSpanSizeLookup;
    }

    public void layoutChunk(RecyclerView.Recycler recycler, RecyclerView.State state, LinearLayoutManager.LayoutState layoutState, LinearLayoutManager.LayoutChunkResult layoutChunkResult) {
        boolean z;
        int i2;
        boolean z3;
        int i7;
        int i8;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        View next;
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        LinearLayoutManager.LayoutState layoutState2 = layoutState;
        LinearLayoutManager.LayoutChunkResult layoutChunkResult2 = layoutChunkResult;
        int modeInOther = this.mOrientationHelper.getModeInOther();
        if (modeInOther != 1073741824) {
            z = true;
        } else {
            z = false;
        }
        if (getChildCount() > 0) {
            i2 = this.mCachedBorders[this.mSpanCount];
        } else {
            i2 = 0;
        }
        if (z) {
            updateMeasurements();
        }
        if (layoutState2.mItemDirection == 1) {
            z3 = true;
        } else {
            z3 = false;
        }
        int i17 = this.mSpanCount;
        if (!z3) {
            i17 = getSpanIndex(recycler2, state2, layoutState2.mCurrentPosition) + getSpanSize(recycler2, state2, layoutState2.mCurrentPosition);
        }
        int i18 = 0;
        while (i18 < this.mSpanCount && layoutState2.hasMore(state2) && i17 > 0) {
            int i19 = layoutState2.mCurrentPosition;
            int spanSize = getSpanSize(recycler2, state2, i19);
            if (spanSize <= this.mSpanCount) {
                i17 -= spanSize;
                if (i17 < 0 || (next = layoutState2.next(recycler2)) == null) {
                    break;
                }
                this.mSet[i18] = next;
                i18++;
            } else {
                throw new IllegalArgumentException(C0086a.l(a.h(i19, spanSize, "Item at position ", " requires ", " spans but GridLayoutManager has only "), this.mSpanCount, " spans."));
            }
        }
        if (i18 == 0) {
            layoutChunkResult2.mFinished = true;
            return;
        }
        assignSpans(recycler2, state2, i18, z3);
        float f = 0.0f;
        int i20 = 0;
        for (int i21 = 0; i21 < i18; i21++) {
            View view = this.mSet[i21];
            if (layoutState2.mScrapList == null) {
                if (z3) {
                    addView(view);
                } else {
                    addView(view, 0);
                }
            } else if (z3) {
                addDisappearingView(view);
            } else {
                addDisappearingView(view, 0);
            }
            calculateItemDecorationsForChild(view, this.mDecorInsets);
            measureChild(view, modeInOther, false);
            int decoratedMeasurement = this.mOrientationHelper.getDecoratedMeasurement(view);
            if (decoratedMeasurement > i20) {
                i20 = decoratedMeasurement;
            }
            float decoratedMeasurementInOther = (((float) this.mOrientationHelper.getDecoratedMeasurementInOther(view)) * 1.0f) / ((float) ((LayoutParams) view.getLayoutParams()).mSpanSize);
            if (decoratedMeasurementInOther > f) {
                f = decoratedMeasurementInOther;
            }
        }
        if (z) {
            guessMeasurement(f, i2);
            i20 = 0;
            for (int i22 = 0; i22 < i18; i22++) {
                View view2 = this.mSet[i22];
                measureChild(view2, 1073741824, true);
                int decoratedMeasurement2 = this.mOrientationHelper.getDecoratedMeasurement(view2);
                if (decoratedMeasurement2 > i20) {
                    i20 = decoratedMeasurement2;
                }
            }
        }
        for (int i23 = 0; i23 < i18; i23++) {
            View view3 = this.mSet[i23];
            if (this.mOrientationHelper.getDecoratedMeasurement(view3) != i20) {
                LayoutParams layoutParams = (LayoutParams) view3.getLayoutParams();
                Rect rect = layoutParams.mDecorInsets;
                int i24 = rect.top + rect.bottom + layoutParams.topMargin + layoutParams.bottomMargin;
                int i25 = rect.left + rect.right + layoutParams.leftMargin + layoutParams.rightMargin;
                int spaceForSpanRange = getSpaceForSpanRange(layoutParams.mSpanIndex, layoutParams.mSpanSize);
                if (this.mOrientation == 1) {
                    i16 = RecyclerView.LayoutManager.getChildMeasureSpec(spaceForSpanRange, 1073741824, i25, layoutParams.width, false);
                    i15 = View.MeasureSpec.makeMeasureSpec(i20 - i24, 1073741824);
                } else {
                    int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i20 - i25, 1073741824);
                    i15 = RecyclerView.LayoutManager.getChildMeasureSpec(spaceForSpanRange, 1073741824, i24, layoutParams.height, false);
                    i16 = makeMeasureSpec;
                }
                measureChildWithDecorationsAndMargin(view3, i16, i15, true);
            }
        }
        layoutChunkResult2.mConsumed = i20;
        if (this.mOrientation == 1) {
            if (layoutState2.mLayoutDirection == -1) {
                i11 = layoutState2.mOffset;
                i14 = i11 - i20;
            } else {
                i14 = layoutState2.mOffset;
                i11 = i14 + i20;
            }
            i7 = i14;
            i10 = 0;
            i8 = 0;
        } else {
            if (layoutState2.mLayoutDirection == -1) {
                i13 = layoutState2.mOffset;
                i12 = i13 - i20;
            } else {
                i12 = layoutState2.mOffset;
                i13 = i12 + i20;
            }
            i8 = i12;
            i7 = 0;
            i10 = i13;
            i11 = 0;
        }
        for (int i26 = 0; i26 < i18; i26++) {
            View view4 = this.mSet[i26];
            LayoutParams layoutParams2 = (LayoutParams) view4.getLayoutParams();
            if (this.mOrientation != 1) {
                i7 = this.mCachedBorders[layoutParams2.mSpanIndex] + getPaddingTop();
                i11 = this.mOrientationHelper.getDecoratedMeasurementInOther(view4) + i7;
            } else if (isLayoutRTL()) {
                i10 = getPaddingLeft() + this.mCachedBorders[this.mSpanCount - layoutParams2.mSpanIndex];
                i8 = i10 - this.mOrientationHelper.getDecoratedMeasurementInOther(view4);
            } else {
                i8 = this.mCachedBorders[layoutParams2.mSpanIndex] + getPaddingLeft();
                i10 = this.mOrientationHelper.getDecoratedMeasurementInOther(view4) + i8;
            }
            View view5 = view4;
            int i27 = i11;
            View view6 = view5;
            int i28 = i7;
            int i29 = i10;
            int i30 = i8;
            int i31 = i28;
            layoutDecoratedWithMargins(view6, i30, i31, i29, i27);
            int i32 = i27;
            View view7 = view6;
            i11 = i32;
            int i33 = i31;
            i8 = i30;
            i10 = i29;
            i7 = i33;
            if (layoutParams2.isItemRemoved() || layoutParams2.isItemChanged()) {
                layoutChunkResult2.mIgnoreConsumed = true;
            }
            layoutChunkResult2.mFocusable = view7.hasFocusable() | layoutChunkResult2.mFocusable;
        }
        Arrays.fill(this.mSet, (Object) null);
    }

    public void onAnchorReady(RecyclerView.Recycler recycler, RecyclerView.State state, LinearLayoutManager.AnchorInfo anchorInfo, int i2) {
        super.onAnchorReady(recycler, state, anchorInfo, i2);
        updateMeasurements();
        if (state.getItemCount() > 0 && !state.isPreLayout()) {
            ensureAnchorIsInCorrectSpan(recycler, state, anchorInfo, i2);
        }
        ensureViewSet();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00d3, code lost:
        if (r13 == r4) goto L_0x00ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00f3, code lost:
        if (r13 == r8) goto L_0x00f5;
     */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00fb  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0111  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View onFocusSearchFailed(android.view.View r24, int r25, androidx.recyclerview.widget.RecyclerView.Recycler r26, androidx.recyclerview.widget.RecyclerView.State r27) {
        /*
            r23 = this;
            r0 = r23
            r1 = r26
            r2 = r27
            android.view.View r3 = r23.findContainingItemView(r24)
            r4 = 0
            if (r3 != 0) goto L_0x000e
            return r4
        L_0x000e:
            android.view.ViewGroup$LayoutParams r5 = r3.getLayoutParams()
            androidx.recyclerview.widget.GridLayoutManager$LayoutParams r5 = (androidx.recyclerview.widget.GridLayoutManager.LayoutParams) r5
            int r6 = r5.mSpanIndex
            int r5 = r5.mSpanSize
            int r5 = r5 + r6
            android.view.View r7 = super.onFocusSearchFailed(r24, r25, r26, r27)
            if (r7 != 0) goto L_0x0020
            return r4
        L_0x0020:
            r7 = r25
            int r7 = r0.convertFocusDirectionToLayoutDirection(r7)
            r9 = 1
            if (r7 != r9) goto L_0x002b
            r7 = r9
            goto L_0x002c
        L_0x002b:
            r7 = 0
        L_0x002c:
            boolean r10 = r0.mShouldReverseLayout
            r11 = -1
            if (r7 == r10) goto L_0x0039
            int r7 = r0.getChildCount()
            int r7 = r7 - r9
            r10 = r11
            r12 = r10
            goto L_0x0040
        L_0x0039:
            int r7 = r0.getChildCount()
            r10 = r7
            r12 = r9
            r7 = 0
        L_0x0040:
            int r13 = r0.mOrientation
            if (r13 != r9) goto L_0x004c
            boolean r13 = r0.isLayoutRTL()
            if (r13 == 0) goto L_0x004c
            r13 = r9
            goto L_0x004d
        L_0x004c:
            r13 = 0
        L_0x004d:
            int r14 = r0.getSpanGroupIndex(r1, r2, r7)
            r15 = r11
            r16 = r15
            r8 = 0
            r17 = 0
            r11 = r7
            r7 = r4
        L_0x0059:
            if (r11 == r10) goto L_0x0065
            int r9 = r0.getSpanGroupIndex(r1, r2, r11)
            android.view.View r1 = r0.getChildAt(r11)
            if (r1 != r3) goto L_0x006b
        L_0x0065:
            r21 = r4
            r19 = r7
            goto L_0x0138
        L_0x006b:
            boolean r18 = r1.hasFocusable()
            if (r18 == 0) goto L_0x0084
            if (r9 == r14) goto L_0x0084
            if (r4 == 0) goto L_0x0076
            goto L_0x0065
        L_0x0076:
            r18 = r3
            r21 = r4
        L_0x007a:
            r19 = r7
            r20 = r8
        L_0x007e:
            r4 = r16
            r7 = r17
            goto L_0x0127
        L_0x0084:
            android.view.ViewGroup$LayoutParams r9 = r1.getLayoutParams()
            androidx.recyclerview.widget.GridLayoutManager$LayoutParams r9 = (androidx.recyclerview.widget.GridLayoutManager.LayoutParams) r9
            int r2 = r9.mSpanIndex
            r18 = r3
            int r3 = r9.mSpanSize
            int r3 = r3 + r2
            boolean r19 = r1.hasFocusable()
            if (r19 == 0) goto L_0x009c
            if (r2 != r6) goto L_0x009c
            if (r3 != r5) goto L_0x009c
            return r1
        L_0x009c:
            boolean r19 = r1.hasFocusable()
            if (r19 == 0) goto L_0x00a4
            if (r4 == 0) goto L_0x00ac
        L_0x00a4:
            boolean r19 = r1.hasFocusable()
            if (r19 != 0) goto L_0x00b7
            if (r7 != 0) goto L_0x00b7
        L_0x00ac:
            r21 = r4
        L_0x00ae:
            r19 = r7
            r20 = r8
            r4 = r16
            r7 = r17
            goto L_0x00f5
        L_0x00b7:
            int r19 = java.lang.Math.max(r2, r6)
            int r20 = java.lang.Math.min(r3, r5)
            r21 = r4
            int r4 = r20 - r19
            boolean r19 = r1.hasFocusable()
            if (r19 == 0) goto L_0x00d6
            if (r4 <= r8) goto L_0x00cc
        L_0x00cb:
            goto L_0x00ae
        L_0x00cc:
            if (r4 != r8) goto L_0x007a
            if (r2 <= r15) goto L_0x00d2
            r4 = 1
            goto L_0x00d3
        L_0x00d2:
            r4 = 0
        L_0x00d3:
            if (r13 != r4) goto L_0x007a
            goto L_0x00cb
        L_0x00d6:
            if (r21 != 0) goto L_0x007a
            r19 = r7
            r20 = r8
            r7 = 0
            r8 = 1
            boolean r22 = r0.isViewPartiallyVisible(r1, r7, r8)
            if (r22 == 0) goto L_0x007e
            r7 = r17
            if (r4 <= r7) goto L_0x00eb
            r4 = r16
            goto L_0x00f5
        L_0x00eb:
            if (r4 != r7) goto L_0x0125
            r4 = r16
            if (r2 <= r4) goto L_0x00f2
            goto L_0x00f3
        L_0x00f2:
            r8 = 0
        L_0x00f3:
            if (r13 != r8) goto L_0x0127
        L_0x00f5:
            boolean r8 = r1.hasFocusable()
            if (r8 == 0) goto L_0x0111
            int r8 = r9.mSpanIndex
            int r3 = java.lang.Math.min(r3, r5)
            int r2 = java.lang.Math.max(r2, r6)
            int r2 = r3 - r2
            r16 = r4
            r17 = r7
            r15 = r8
            r7 = r19
            r4 = r1
            r8 = r2
            goto L_0x012e
        L_0x0111:
            int r4 = r9.mSpanIndex
            int r3 = java.lang.Math.min(r3, r5)
            int r2 = java.lang.Math.max(r2, r6)
            int r17 = r3 - r2
            r7 = r1
            r16 = r4
        L_0x0120:
            r8 = r20
            r4 = r21
            goto L_0x012e
        L_0x0125:
            r4 = r16
        L_0x0127:
            r16 = r4
            r17 = r7
            r7 = r19
            goto L_0x0120
        L_0x012e:
            int r11 = r11 + r12
            r1 = r26
            r2 = r27
            r3 = r18
            r9 = 1
            goto L_0x0059
        L_0x0138:
            if (r21 == 0) goto L_0x013b
            return r21
        L_0x013b:
            return r19
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.onFocusSearchFailed(android.view.View, int, androidx.recyclerview.widget.RecyclerView$Recycler, androidx.recyclerview.widget.RecyclerView$State):android.view.View");
    }

    public void onInitializeAccessibilityNodeInfo(RecyclerView.Recycler recycler, RecyclerView.State state, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(recycler, state, accessibilityNodeInfoCompat);
        accessibilityNodeInfoCompat.setClassName(GridView.class.getName());
    }

    public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler recycler, RecyclerView.State state, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof LayoutParams)) {
            super.onInitializeAccessibilityNodeInfoForItem(view, accessibilityNodeInfoCompat);
            return;
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        int spanGroupIndex = getSpanGroupIndex(recycler, state, layoutParams2.getViewLayoutPosition());
        if (this.mOrientation == 0) {
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(layoutParams2.getSpanIndex(), layoutParams2.getSpanSize(), spanGroupIndex, 1, false, false));
            return;
        }
        int i2 = spanGroupIndex;
        accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(spanGroupIndex, 1, layoutParams2.getSpanIndex(), layoutParams2.getSpanSize(), false, false));
    }

    public void onItemsAdded(RecyclerView recyclerView, int i2, int i7) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
        this.mSpanSizeLookup.invalidateSpanGroupIndexCache();
    }

    public void onItemsChanged(RecyclerView recyclerView) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
        this.mSpanSizeLookup.invalidateSpanGroupIndexCache();
    }

    public void onItemsMoved(RecyclerView recyclerView, int i2, int i7, int i8) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
        this.mSpanSizeLookup.invalidateSpanGroupIndexCache();
    }

    public void onItemsRemoved(RecyclerView recyclerView, int i2, int i7) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
        this.mSpanSizeLookup.invalidateSpanGroupIndexCache();
    }

    public void onItemsUpdated(RecyclerView recyclerView, int i2, int i7, Object obj) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
        this.mSpanSizeLookup.invalidateSpanGroupIndexCache();
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (state.isPreLayout()) {
            cachePreLayoutSpanMapping();
        }
        super.onLayoutChildren(recycler, state);
        clearPreLayoutSpanMappingCache();
    }

    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        this.mPendingSpanCountChange = false;
    }

    public int scrollHorizontallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        updateMeasurements();
        ensureViewSet();
        return super.scrollHorizontallyBy(i2, recycler, state);
    }

    public int scrollVerticallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        updateMeasurements();
        ensureViewSet();
        return super.scrollVerticallyBy(i2, recycler, state);
    }

    public void setMeasuredDimension(Rect rect, int i2, int i7) {
        int i8;
        int i10;
        if (this.mCachedBorders == null) {
            super.setMeasuredDimension(rect, i2, i7);
        }
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        if (this.mOrientation == 1) {
            i10 = RecyclerView.LayoutManager.chooseSize(i7, rect.height() + paddingBottom, getMinimumHeight());
            int[] iArr = this.mCachedBorders;
            i8 = RecyclerView.LayoutManager.chooseSize(i2, iArr[iArr.length - 1] + paddingRight, getMinimumWidth());
        } else {
            i8 = RecyclerView.LayoutManager.chooseSize(i2, rect.width() + paddingRight, getMinimumWidth());
            int[] iArr2 = this.mCachedBorders;
            i10 = RecyclerView.LayoutManager.chooseSize(i7, iArr2[iArr2.length - 1] + paddingBottom, getMinimumHeight());
        }
        setMeasuredDimension(i8, i10);
    }

    public void setSpanCount(int i2) {
        if (i2 != this.mSpanCount) {
            this.mPendingSpanCountChange = true;
            if (i2 >= 1) {
                this.mSpanCount = i2;
                this.mSpanSizeLookup.invalidateSpanIndexCache();
                requestLayout();
                return;
            }
            throw new IllegalArgumentException(C0086a.i(i2, "Span count should be at least 1. Provided "));
        }
    }

    public void setSpanSizeLookup(SpanSizeLookup spanSizeLookup) {
        this.mSpanSizeLookup = spanSizeLookup;
    }

    public void setStackFromEnd(boolean z) {
        if (!z) {
            super.setStackFromEnd(false);
            return;
        }
        throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
    }

    public boolean supportsPredictiveItemAnimations() {
        if (this.mPendingSavedState != null || this.mPendingSpanCountChange) {
            return false;
        }
        return true;
    }

    public static int[] calculateItemBorders(int[] iArr, int i2, int i7) {
        int i8;
        if (!(iArr != null && iArr.length == i2 + 1 && iArr[iArr.length - 1] == i7)) {
            iArr = new int[(i2 + 1)];
        }
        int i10 = 0;
        iArr[0] = 0;
        int i11 = i7 / i2;
        int i12 = i7 % i2;
        int i13 = 0;
        for (int i14 = 1; i14 <= i2; i14++) {
            i10 += i12;
            if (i10 <= 0 || i2 - i10 >= i12) {
                i8 = i11;
            } else {
                i8 = i11 + 1;
                i10 -= i2;
            }
            i13 += i8;
            iArr[i14] = i13;
        }
        return iArr;
    }

    public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LayoutParams extends RecyclerView.LayoutParams {
        int mSpanIndex = -1;
        int mSpanSize = 0;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public int getSpanIndex() {
            return this.mSpanIndex;
        }

        public int getSpanSize() {
            return this.mSpanSize;
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
    }

    public GridLayoutManager(Context context, int i2) {
        super(context);
        setSpanCount(i2);
    }

    public GridLayoutManager(Context context, int i2, int i7, boolean z) {
        super(context, i7, z);
        setSpanCount(i2);
    }
}
