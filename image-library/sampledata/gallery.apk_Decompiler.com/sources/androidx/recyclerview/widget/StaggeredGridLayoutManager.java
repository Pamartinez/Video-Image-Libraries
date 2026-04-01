package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.RecyclerView;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StaggeredGridLayoutManager extends RecyclerView.LayoutManager implements RecyclerView.SmoothScroller.ScrollVectorProvider {
    private final AnchorInfo mAnchorInfo = new AnchorInfo();
    private final Runnable mCheckForGapsRunnable = new Runnable() {
        public void run() {
            StaggeredGridLayoutManager.this.checkForGaps();
        }
    };
    private int mFullSizeSpec;
    private int mGapStrategy = 2;
    private boolean mLaidOutInvalidFullSpan = false;
    private boolean mLastLayoutFromEnd;
    private boolean mLastLayoutRTL;
    private final LayoutState mLayoutState;
    LazySpanLookup mLazySpanLookup = new LazySpanLookup();
    private int mOrientation;
    private SavedState mPendingSavedState;
    int mPendingScrollPosition = -1;
    int mPendingScrollPositionOffset = Integer.MIN_VALUE;
    private int[] mPrefetchDistances;
    OrientationHelper mPrimaryOrientation;
    private BitSet mRemainingSpans;
    boolean mReverseLayout = false;
    OrientationHelper mSecondaryOrientation;
    boolean mShouldReverseLayout = false;
    private int mSizePerSpan;
    private boolean mSmoothScrollbarEnabled = true;
    private int mSpanCount = -1;
    Span[] mSpans;
    private final Rect mTmpRect = new Rect();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LayoutParams extends RecyclerView.LayoutParams {
        boolean mFullSpan;
        Span mSpan;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public final int getSpanIndex() {
            Span span = this.mSpan;
            if (span == null) {
                return -1;
            }
            return span.mIndex;
        }

        public boolean isFullSpan() {
            return this.mFullSpan;
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

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };
        boolean mAnchorLayoutFromEnd;
        int mAnchorPosition;
        List<LazySpanLookup.FullSpanItem> mFullSpanItems;
        boolean mLastLayoutRTL;
        boolean mReverseLayout;
        int[] mSpanLookup;
        int mSpanLookupSize;
        int[] mSpanOffsets;
        int mSpanOffsetsSize;
        int mVisibleAnchorPosition;

        public SavedState() {
        }

        public int describeContents() {
            return 0;
        }

        public void invalidateAnchorPositionInfo() {
            this.mSpanOffsets = null;
            this.mSpanOffsetsSize = 0;
            this.mAnchorPosition = -1;
            this.mVisibleAnchorPosition = -1;
        }

        public void invalidateSpanInfo() {
            this.mSpanOffsets = null;
            this.mSpanOffsetsSize = 0;
            this.mSpanLookupSize = 0;
            this.mSpanLookup = null;
            this.mFullSpanItems = null;
        }

        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.mAnchorPosition);
            parcel.writeInt(this.mVisibleAnchorPosition);
            parcel.writeInt(this.mSpanOffsetsSize);
            if (this.mSpanOffsetsSize > 0) {
                parcel.writeIntArray(this.mSpanOffsets);
            }
            parcel.writeInt(this.mSpanLookupSize);
            if (this.mSpanLookupSize > 0) {
                parcel.writeIntArray(this.mSpanLookup);
            }
            parcel.writeInt(this.mReverseLayout ? 1 : 0);
            parcel.writeInt(this.mAnchorLayoutFromEnd ? 1 : 0);
            parcel.writeInt(this.mLastLayoutRTL ? 1 : 0);
            parcel.writeList(this.mFullSpanItems);
        }

        public SavedState(Parcel parcel) {
            this.mAnchorPosition = parcel.readInt();
            this.mVisibleAnchorPosition = parcel.readInt();
            int readInt = parcel.readInt();
            this.mSpanOffsetsSize = readInt;
            if (readInt > 0) {
                int[] iArr = new int[readInt];
                this.mSpanOffsets = iArr;
                parcel.readIntArray(iArr);
            }
            int readInt2 = parcel.readInt();
            this.mSpanLookupSize = readInt2;
            if (readInt2 > 0) {
                int[] iArr2 = new int[readInt2];
                this.mSpanLookup = iArr2;
                parcel.readIntArray(iArr2);
            }
            boolean z = false;
            this.mReverseLayout = parcel.readInt() == 1;
            this.mAnchorLayoutFromEnd = parcel.readInt() == 1;
            this.mLastLayoutRTL = parcel.readInt() == 1 ? true : z;
            this.mFullSpanItems = parcel.readArrayList(LazySpanLookup.FullSpanItem.class.getClassLoader());
        }

        public SavedState(SavedState savedState) {
            this.mSpanOffsetsSize = savedState.mSpanOffsetsSize;
            this.mAnchorPosition = savedState.mAnchorPosition;
            this.mVisibleAnchorPosition = savedState.mVisibleAnchorPosition;
            this.mSpanOffsets = savedState.mSpanOffsets;
            this.mSpanLookupSize = savedState.mSpanLookupSize;
            this.mSpanLookup = savedState.mSpanLookup;
            this.mReverseLayout = savedState.mReverseLayout;
            this.mAnchorLayoutFromEnd = savedState.mAnchorLayoutFromEnd;
            this.mLastLayoutRTL = savedState.mLastLayoutRTL;
            this.mFullSpanItems = savedState.mFullSpanItems;
        }
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i2, int i7) {
        RecyclerView.LayoutManager.Properties properties = RecyclerView.LayoutManager.getProperties(context, attributeSet, i2, i7);
        setOrientation(properties.orientation);
        setSpanCount(properties.spanCount);
        setReverseLayout(properties.reverseLayout);
        this.mLayoutState = new LayoutState();
        createOrientationHelpers();
    }

    private void appendViewToAllSpans(View view) {
        for (int i2 = this.mSpanCount - 1; i2 >= 0; i2--) {
            this.mSpans[i2].appendToSpan(view);
        }
    }

    private void applyPendingSavedState(AnchorInfo anchorInfo) {
        int startAfterPadding;
        SavedState savedState = this.mPendingSavedState;
        int i2 = savedState.mSpanOffsetsSize;
        if (i2 > 0) {
            if (i2 == this.mSpanCount) {
                for (int i7 = 0; i7 < this.mSpanCount; i7++) {
                    this.mSpans[i7].clear();
                    SavedState savedState2 = this.mPendingSavedState;
                    int i8 = savedState2.mSpanOffsets[i7];
                    if (i8 != Integer.MIN_VALUE) {
                        if (savedState2.mAnchorLayoutFromEnd) {
                            startAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
                        } else {
                            startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
                        }
                        i8 += startAfterPadding;
                    }
                    this.mSpans[i7].setLine(i8);
                }
            } else {
                savedState.invalidateSpanInfo();
                SavedState savedState3 = this.mPendingSavedState;
                savedState3.mAnchorPosition = savedState3.mVisibleAnchorPosition;
            }
        }
        SavedState savedState4 = this.mPendingSavedState;
        this.mLastLayoutRTL = savedState4.mLastLayoutRTL;
        setReverseLayout(savedState4.mReverseLayout);
        resolveShouldLayoutReverse();
        SavedState savedState5 = this.mPendingSavedState;
        int i10 = savedState5.mAnchorPosition;
        if (i10 != -1) {
            this.mPendingScrollPosition = i10;
            anchorInfo.mLayoutFromEnd = savedState5.mAnchorLayoutFromEnd;
        } else {
            anchorInfo.mLayoutFromEnd = this.mShouldReverseLayout;
        }
        if (savedState5.mSpanLookupSize > 1) {
            LazySpanLookup lazySpanLookup = this.mLazySpanLookup;
            lazySpanLookup.mData = savedState5.mSpanLookup;
            lazySpanLookup.mFullSpanItems = savedState5.mFullSpanItems;
        }
    }

    private void attachViewToSpans(View view, LayoutParams layoutParams, LayoutState layoutState) {
        if (layoutState.mLayoutDirection == 1) {
            if (layoutParams.mFullSpan) {
                appendViewToAllSpans(view);
            } else {
                layoutParams.mSpan.appendToSpan(view);
            }
        } else if (layoutParams.mFullSpan) {
            prependViewToAllSpans(view);
        } else {
            layoutParams.mSpan.prependToSpan(view);
        }
    }

    private int calculateScrollDirectionForPosition(int i2) {
        boolean z;
        if (getChildCount() != 0) {
            if (i2 < getFirstChildPosition()) {
                z = true;
            } else {
                z = false;
            }
            if (z != this.mShouldReverseLayout) {
                return -1;
            }
            return 1;
        } else if (this.mShouldReverseLayout) {
            return 1;
        } else {
            return -1;
        }
    }

    private boolean checkSpanForGap(Span span) {
        boolean z;
        if (this.mShouldReverseLayout) {
            if (span.getEndLine() < this.mPrimaryOrientation.getEndAfterPadding()) {
                z = span.getLayoutParams((View) C0212a.i(span.mViews, 1)).mFullSpan;
            }
            return false;
        }
        if (span.getStartLine() > this.mPrimaryOrientation.getStartAfterPadding()) {
            z = span.getLayoutParams(span.mViews.get(0)).mFullSpan;
        }
        return false;
        return !z;
    }

    private int computeScrollExtent(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        return ScrollbarHelper.computeScrollExtent(state, this.mPrimaryOrientation, findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled), findFirstVisibleItemClosestToEnd(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled);
    }

    private int computeScrollOffset(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        return ScrollbarHelper.computeScrollOffset(state, this.mPrimaryOrientation, findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled), findFirstVisibleItemClosestToEnd(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled, this.mShouldReverseLayout);
    }

    private int computeScrollRange(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        return ScrollbarHelper.computeScrollRange(state, this.mPrimaryOrientation, findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled), findFirstVisibleItemClosestToEnd(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled);
    }

    private int convertFocusDirectionToLayoutDirection(int i2) {
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 17) {
                    if (i2 != 33) {
                        if (i2 != 66) {
                            if (i2 == 130 && this.mOrientation == 1) {
                                return 1;
                            }
                            return Integer.MIN_VALUE;
                        } else if (this.mOrientation == 0) {
                            return 1;
                        } else {
                            return Integer.MIN_VALUE;
                        }
                    } else if (this.mOrientation == 1) {
                        return -1;
                    } else {
                        return Integer.MIN_VALUE;
                    }
                } else if (this.mOrientation == 0) {
                    return -1;
                } else {
                    return Integer.MIN_VALUE;
                }
            } else if (this.mOrientation != 1 && isLayoutRTL()) {
                return -1;
            } else {
                return 1;
            }
        } else if (this.mOrientation != 1 && isLayoutRTL()) {
            return 1;
        } else {
            return -1;
        }
    }

    private LazySpanLookup.FullSpanItem createFullSpanItemFromEnd(int i2) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.mGapPerSpan = new int[this.mSpanCount];
        for (int i7 = 0; i7 < this.mSpanCount; i7++) {
            fullSpanItem.mGapPerSpan[i7] = i2 - this.mSpans[i7].getEndLine(i2);
        }
        return fullSpanItem;
    }

    private LazySpanLookup.FullSpanItem createFullSpanItemFromStart(int i2) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.mGapPerSpan = new int[this.mSpanCount];
        for (int i7 = 0; i7 < this.mSpanCount; i7++) {
            fullSpanItem.mGapPerSpan[i7] = this.mSpans[i7].getStartLine(i2) - i2;
        }
        return fullSpanItem;
    }

    private void createOrientationHelpers() {
        this.mPrimaryOrientation = OrientationHelper.createOrientationHelper(this, this.mOrientation);
        this.mSecondaryOrientation = OrientationHelper.createOrientationHelper(this, 1 - this.mOrientation);
    }

    /* JADX WARNING: type inference failed for: r8v0 */
    /* JADX WARNING: type inference failed for: r8v1, types: [boolean, int] */
    /* JADX WARNING: type inference failed for: r8v3 */
    private int fill(RecyclerView.Recycler recycler, LayoutState layoutState, RecyclerView.State state) {
        int i2;
        int startAfterPadding;
        int i7;
        boolean z;
        Span span;
        int i8;
        int i10;
        int i11;
        int decoratedMeasurement;
        boolean areAllStartsEqual;
        int startLine;
        StaggeredGridLayoutManager staggeredGridLayoutManager = this;
        RecyclerView.Recycler recycler2 = recycler;
        LayoutState layoutState2 = layoutState;
        ? r82 = 0;
        staggeredGridLayoutManager.mRemainingSpans.set(0, staggeredGridLayoutManager.mSpanCount, true);
        if (staggeredGridLayoutManager.mLayoutState.mInfinite) {
            if (layoutState2.mLayoutDirection == 1) {
                i2 = Integer.MAX_VALUE;
            } else {
                i2 = Integer.MIN_VALUE;
            }
        } else if (layoutState2.mLayoutDirection == 1) {
            i2 = layoutState2.mEndLine + layoutState2.mAvailable;
        } else {
            i2 = layoutState2.mStartLine - layoutState2.mAvailable;
        }
        int i12 = i2;
        staggeredGridLayoutManager.updateAllRemainingSpans(layoutState2.mLayoutDirection, i12);
        if (staggeredGridLayoutManager.mShouldReverseLayout) {
            startAfterPadding = staggeredGridLayoutManager.mPrimaryOrientation.getEndAfterPadding();
        } else {
            startAfterPadding = staggeredGridLayoutManager.mPrimaryOrientation.getStartAfterPadding();
        }
        int i13 = startAfterPadding;
        boolean z3 = false;
        while (layoutState.hasMore(state) && (staggeredGridLayoutManager.mLayoutState.mInfinite || !staggeredGridLayoutManager.mRemainingSpans.isEmpty())) {
            View next = layoutState2.next(recycler2);
            LayoutParams layoutParams = (LayoutParams) next.getLayoutParams();
            int viewLayoutPosition = layoutParams.getViewLayoutPosition();
            int span2 = staggeredGridLayoutManager.mLazySpanLookup.getSpan(viewLayoutPosition);
            if (span2 == -1) {
                z = true;
            } else {
                z = r82;
            }
            if (z) {
                if (layoutParams.mFullSpan) {
                    span = staggeredGridLayoutManager.mSpans[r82];
                } else {
                    span = staggeredGridLayoutManager.getNextSpan(layoutState2);
                }
                staggeredGridLayoutManager.mLazySpanLookup.setSpan(viewLayoutPosition, span);
            } else {
                span = staggeredGridLayoutManager.mSpans[span2];
            }
            Span span3 = span;
            layoutParams.mSpan = span3;
            if (layoutState2.mLayoutDirection == 1) {
                staggeredGridLayoutManager.addView(next);
            } else {
                staggeredGridLayoutManager.addView(next, r82);
            }
            staggeredGridLayoutManager.measureChildWithDecorationsAndMargin(next, layoutParams, r82);
            if (layoutState2.mLayoutDirection == 1) {
                if (layoutParams.mFullSpan) {
                    i10 = staggeredGridLayoutManager.getMaxEnd(i13);
                } else {
                    i10 = span3.getEndLine(i13);
                }
                i8 = staggeredGridLayoutManager.mPrimaryOrientation.getDecoratedMeasurement(next) + i10;
                if (z && layoutParams.mFullSpan) {
                    LazySpanLookup.FullSpanItem createFullSpanItemFromEnd = staggeredGridLayoutManager.createFullSpanItemFromEnd(i10);
                    createFullSpanItemFromEnd.mGapDir = -1;
                    createFullSpanItemFromEnd.mPosition = viewLayoutPosition;
                    staggeredGridLayoutManager.mLazySpanLookup.addFullSpanItem(createFullSpanItemFromEnd);
                }
            } else {
                if (layoutParams.mFullSpan) {
                    startLine = staggeredGridLayoutManager.getMinStart(i13);
                } else {
                    startLine = span3.getStartLine(i13);
                }
                i8 = startLine;
                i10 = i8 - staggeredGridLayoutManager.mPrimaryOrientation.getDecoratedMeasurement(next);
                if (z && layoutParams.mFullSpan) {
                    LazySpanLookup.FullSpanItem createFullSpanItemFromStart = staggeredGridLayoutManager.createFullSpanItemFromStart(i8);
                    createFullSpanItemFromStart.mGapDir = 1;
                    createFullSpanItemFromStart.mPosition = viewLayoutPosition;
                    staggeredGridLayoutManager.mLazySpanLookup.addFullSpanItem(createFullSpanItemFromStart);
                }
            }
            if (layoutParams.mFullSpan && layoutState2.mItemDirection == -1) {
                if (z) {
                    staggeredGridLayoutManager.mLaidOutInvalidFullSpan = true;
                } else {
                    if (layoutState2.mLayoutDirection == 1) {
                        areAllStartsEqual = staggeredGridLayoutManager.areAllEndsEqual();
                    } else {
                        areAllStartsEqual = staggeredGridLayoutManager.areAllStartsEqual();
                    }
                    if (!areAllStartsEqual) {
                        LazySpanLookup.FullSpanItem fullSpanItem = staggeredGridLayoutManager.mLazySpanLookup.getFullSpanItem(viewLayoutPosition);
                        if (fullSpanItem != null) {
                            fullSpanItem.mHasUnwantedGapAfter = true;
                        }
                        staggeredGridLayoutManager.mLaidOutInvalidFullSpan = true;
                    }
                }
            }
            staggeredGridLayoutManager.attachViewToSpans(next, layoutParams, layoutState2);
            if (!staggeredGridLayoutManager.isLayoutRTL() || staggeredGridLayoutManager.mOrientation != 1) {
                if (layoutParams.mFullSpan) {
                    i11 = staggeredGridLayoutManager.mSecondaryOrientation.getStartAfterPadding();
                } else {
                    i11 = staggeredGridLayoutManager.mSecondaryOrientation.getStartAfterPadding() + (span3.mIndex * staggeredGridLayoutManager.mSizePerSpan);
                }
                decoratedMeasurement = staggeredGridLayoutManager.mSecondaryOrientation.getDecoratedMeasurement(next) + i11;
            } else {
                if (layoutParams.mFullSpan) {
                    decoratedMeasurement = staggeredGridLayoutManager.mSecondaryOrientation.getEndAfterPadding();
                } else {
                    decoratedMeasurement = staggeredGridLayoutManager.mSecondaryOrientation.getEndAfterPadding() - (((staggeredGridLayoutManager.mSpanCount - 1) - span3.mIndex) * staggeredGridLayoutManager.mSizePerSpan);
                }
                i11 = decoratedMeasurement - staggeredGridLayoutManager.mSecondaryOrientation.getDecoratedMeasurement(next);
            }
            int i14 = decoratedMeasurement;
            int i15 = i11;
            if (staggeredGridLayoutManager.mOrientation == 1) {
                staggeredGridLayoutManager.layoutDecoratedWithMargins(next, i15, i10, i14, i8);
                staggeredGridLayoutManager = this;
            } else {
                staggeredGridLayoutManager.layoutDecoratedWithMargins(next, i10, i15, i8, i14);
            }
            if (layoutParams.mFullSpan) {
                staggeredGridLayoutManager.updateAllRemainingSpans(staggeredGridLayoutManager.mLayoutState.mLayoutDirection, i12);
            } else {
                staggeredGridLayoutManager.updateRemainingSpans(span3, staggeredGridLayoutManager.mLayoutState.mLayoutDirection, i12);
            }
            staggeredGridLayoutManager.recycle(recycler2, staggeredGridLayoutManager.mLayoutState);
            if (staggeredGridLayoutManager.mLayoutState.mStopInFocusable && next.hasFocusable()) {
                if (layoutParams.mFullSpan) {
                    staggeredGridLayoutManager.mRemainingSpans.clear();
                } else {
                    staggeredGridLayoutManager.mRemainingSpans.set(span3.mIndex, false);
                }
            }
            z3 = true;
            r82 = 0;
        }
        if (!z3) {
            staggeredGridLayoutManager.recycle(recycler2, staggeredGridLayoutManager.mLayoutState);
        }
        if (staggeredGridLayoutManager.mLayoutState.mLayoutDirection == -1) {
            i7 = staggeredGridLayoutManager.mPrimaryOrientation.getStartAfterPadding() - staggeredGridLayoutManager.getMinStart(staggeredGridLayoutManager.mPrimaryOrientation.getStartAfterPadding());
        } else {
            i7 = staggeredGridLayoutManager.getMaxEnd(staggeredGridLayoutManager.mPrimaryOrientation.getEndAfterPadding()) - staggeredGridLayoutManager.mPrimaryOrientation.getEndAfterPadding();
        }
        if (i7 > 0) {
            return Math.min(layoutState2.mAvailable, i7);
        }
        return 0;
    }

    private int findFirstReferenceChildPosition(int i2) {
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            int position = getPosition(getChildAt(i7));
            if (position >= 0 && position < i2) {
                return position;
            }
        }
        return 0;
    }

    private int findLastReferenceChildPosition(int i2) {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            int position = getPosition(getChildAt(childCount));
            if (position >= 0 && position < i2) {
                return position;
            }
        }
        return 0;
    }

    private void fixEndGap(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int endAfterPadding;
        int maxEnd = getMaxEnd(Integer.MIN_VALUE);
        if (maxEnd != Integer.MIN_VALUE && (endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding() - maxEnd) > 0) {
            int i2 = endAfterPadding - (-scrollBy(-endAfterPadding, recycler, state));
            if (z && i2 > 0) {
                this.mPrimaryOrientation.offsetChildren(i2);
            }
        }
    }

    private void fixStartGap(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int startAfterPadding;
        int minStart = getMinStart(Integer.MAX_VALUE);
        if (minStart != Integer.MAX_VALUE && (startAfterPadding = minStart - this.mPrimaryOrientation.getStartAfterPadding()) > 0) {
            int scrollBy = startAfterPadding - scrollBy(startAfterPadding, recycler, state);
            if (z && scrollBy > 0) {
                this.mPrimaryOrientation.offsetChildren(-scrollBy);
            }
        }
    }

    private int getMaxEnd(int i2) {
        int endLine = this.mSpans[0].getEndLine(i2);
        for (int i7 = 1; i7 < this.mSpanCount; i7++) {
            int endLine2 = this.mSpans[i7].getEndLine(i2);
            if (endLine2 > endLine) {
                endLine = endLine2;
            }
        }
        return endLine;
    }

    private int getMaxStart(int i2) {
        int startLine = this.mSpans[0].getStartLine(i2);
        for (int i7 = 1; i7 < this.mSpanCount; i7++) {
            int startLine2 = this.mSpans[i7].getStartLine(i2);
            if (startLine2 > startLine) {
                startLine = startLine2;
            }
        }
        return startLine;
    }

    private int getMinEnd(int i2) {
        int endLine = this.mSpans[0].getEndLine(i2);
        for (int i7 = 1; i7 < this.mSpanCount; i7++) {
            int endLine2 = this.mSpans[i7].getEndLine(i2);
            if (endLine2 < endLine) {
                endLine = endLine2;
            }
        }
        return endLine;
    }

    private int getMinStart(int i2) {
        int startLine = this.mSpans[0].getStartLine(i2);
        for (int i7 = 1; i7 < this.mSpanCount; i7++) {
            int startLine2 = this.mSpans[i7].getStartLine(i2);
            if (startLine2 < startLine) {
                startLine = startLine2;
            }
        }
        return startLine;
    }

    private Span getNextSpan(LayoutState layoutState) {
        int i2;
        int i7;
        int i8;
        if (preferLastSpan(layoutState.mLayoutDirection)) {
            i8 = this.mSpanCount - 1;
            i7 = -1;
            i2 = -1;
        } else {
            i7 = this.mSpanCount;
            i8 = 0;
            i2 = 1;
        }
        Span span = null;
        if (layoutState.mLayoutDirection == 1) {
            int startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
            int i10 = Integer.MAX_VALUE;
            while (i8 != i7) {
                Span span2 = this.mSpans[i8];
                int endLine = span2.getEndLine(startAfterPadding);
                if (endLine < i10) {
                    span = span2;
                    i10 = endLine;
                }
                i8 += i2;
            }
            return span;
        }
        int endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
        int i11 = Integer.MIN_VALUE;
        while (i8 != i7) {
            Span span3 = this.mSpans[i8];
            int startLine = span3.getStartLine(endAfterPadding);
            if (startLine > i11) {
                span = span3;
                i11 = startLine;
            }
            i8 += i2;
        }
        return span;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0026  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handleUpdate(int r7, int r8, int r9) {
        /*
            r6 = this;
            boolean r0 = r6.mShouldReverseLayout
            if (r0 == 0) goto L_0x0009
            int r0 = r6.getLastChildPosition()
            goto L_0x000d
        L_0x0009:
            int r0 = r6.getFirstChildPosition()
        L_0x000d:
            r1 = 8
            if (r9 != r1) goto L_0x001b
            if (r7 >= r8) goto L_0x0017
            int r2 = r8 + 1
        L_0x0015:
            r3 = r7
            goto L_0x001e
        L_0x0017:
            int r2 = r7 + 1
            r3 = r8
            goto L_0x001e
        L_0x001b:
            int r2 = r7 + r8
            goto L_0x0015
        L_0x001e:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r4 = r6.mLazySpanLookup
            r4.invalidateAfter(r3)
            r4 = 1
            if (r9 == r4) goto L_0x003d
            r5 = 2
            if (r9 == r5) goto L_0x0037
            if (r9 == r1) goto L_0x002c
            goto L_0x0042
        L_0x002c:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r9 = r6.mLazySpanLookup
            r9.offsetForRemoval(r7, r4)
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r7 = r6.mLazySpanLookup
            r7.offsetForAddition(r8, r4)
            goto L_0x0042
        L_0x0037:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r9 = r6.mLazySpanLookup
            r9.offsetForRemoval(r7, r8)
            goto L_0x0042
        L_0x003d:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r9 = r6.mLazySpanLookup
            r9.offsetForAddition(r7, r8)
        L_0x0042:
            if (r2 > r0) goto L_0x0045
            goto L_0x0057
        L_0x0045:
            boolean r7 = r6.mShouldReverseLayout
            if (r7 == 0) goto L_0x004e
            int r7 = r6.getFirstChildPosition()
            goto L_0x0052
        L_0x004e:
            int r7 = r6.getLastChildPosition()
        L_0x0052:
            if (r3 > r7) goto L_0x0057
            r6.requestLayout()
        L_0x0057:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.handleUpdate(int, int, int):void");
    }

    private void measureChildWithDecorationsAndMargin(View view, LayoutParams layoutParams, boolean z) {
        if (layoutParams.mFullSpan) {
            if (this.mOrientation == 1) {
                measureChildWithDecorationsAndMargin(view, this.mFullSizeSpec, RecyclerView.LayoutManager.getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingBottom() + getPaddingTop(), layoutParams.height, true), z);
                return;
            }
            measureChildWithDecorationsAndMargin(view, RecyclerView.LayoutManager.getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingRight() + getPaddingLeft(), layoutParams.width, true), this.mFullSizeSpec, z);
        } else if (this.mOrientation == 1) {
            measureChildWithDecorationsAndMargin(view, RecyclerView.LayoutManager.getChildMeasureSpec(this.mSizePerSpan, getWidthMode(), 0, layoutParams.width, false), RecyclerView.LayoutManager.getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingBottom() + getPaddingTop(), layoutParams.height, true), z);
        } else {
            measureChildWithDecorationsAndMargin(view, RecyclerView.LayoutManager.getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingRight() + getPaddingLeft(), layoutParams.width, true), RecyclerView.LayoutManager.getChildMeasureSpec(this.mSizePerSpan, getHeightMode(), 0, layoutParams.height, false), z);
        }
    }

    private boolean preferLastSpan(int i2) {
        boolean z;
        boolean z3;
        boolean z7;
        if (this.mOrientation == 0) {
            if (i2 == -1) {
                z7 = true;
            } else {
                z7 = false;
            }
            if (z7 != this.mShouldReverseLayout) {
                return true;
            }
            return false;
        }
        if (i2 == -1) {
            z = true;
        } else {
            z = false;
        }
        if (z == this.mShouldReverseLayout) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 == isLayoutRTL()) {
            return true;
        }
        return false;
    }

    private void prependViewToAllSpans(View view) {
        for (int i2 = this.mSpanCount - 1; i2 >= 0; i2--) {
            this.mSpans[i2].prependToSpan(view);
        }
    }

    private void recycle(RecyclerView.Recycler recycler, LayoutState layoutState) {
        int i2;
        int i7;
        if (layoutState.mRecycle && !layoutState.mInfinite) {
            if (layoutState.mAvailable == 0) {
                if (layoutState.mLayoutDirection == -1) {
                    recycleFromEnd(recycler, layoutState.mEndLine);
                } else {
                    recycleFromStart(recycler, layoutState.mStartLine);
                }
            } else if (layoutState.mLayoutDirection == -1) {
                int i8 = layoutState.mStartLine;
                int maxStart = i8 - getMaxStart(i8);
                if (maxStart < 0) {
                    i7 = layoutState.mEndLine;
                } else {
                    i7 = layoutState.mEndLine - Math.min(maxStart, layoutState.mAvailable);
                }
                recycleFromEnd(recycler, i7);
            } else {
                int minEnd = getMinEnd(layoutState.mEndLine) - layoutState.mEndLine;
                if (minEnd < 0) {
                    i2 = layoutState.mStartLine;
                } else {
                    i2 = Math.min(minEnd, layoutState.mAvailable) + layoutState.mStartLine;
                }
                recycleFromStart(recycler, i2);
            }
        }
    }

    private void recycleFromEnd(RecyclerView.Recycler recycler, int i2) {
        int childCount = getChildCount() - 1;
        while (childCount >= 0) {
            View childAt = getChildAt(childCount);
            if (this.mPrimaryOrientation.getDecoratedStart(childAt) >= i2 && this.mPrimaryOrientation.getTransformedStartWithDecoration(childAt) >= i2) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.mFullSpan) {
                    int i7 = 0;
                    while (i7 < this.mSpanCount) {
                        if (this.mSpans[i7].mViews.size() != 1) {
                            i7++;
                        } else {
                            return;
                        }
                    }
                    for (int i8 = 0; i8 < this.mSpanCount; i8++) {
                        this.mSpans[i8].popEnd();
                    }
                } else if (layoutParams.mSpan.mViews.size() != 1) {
                    layoutParams.mSpan.popEnd();
                } else {
                    return;
                }
                removeAndRecycleView(childAt, recycler);
                childCount--;
            } else {
                return;
            }
        }
    }

    private void recycleFromStart(RecyclerView.Recycler recycler, int i2) {
        while (getChildCount() > 0) {
            View childAt = getChildAt(0);
            if (this.mPrimaryOrientation.getDecoratedEnd(childAt) <= i2 && this.mPrimaryOrientation.getTransformedEndWithDecoration(childAt) <= i2) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.mFullSpan) {
                    int i7 = 0;
                    while (i7 < this.mSpanCount) {
                        if (this.mSpans[i7].mViews.size() != 1) {
                            i7++;
                        } else {
                            return;
                        }
                    }
                    for (int i8 = 0; i8 < this.mSpanCount; i8++) {
                        this.mSpans[i8].popStart();
                    }
                } else if (layoutParams.mSpan.mViews.size() != 1) {
                    layoutParams.mSpan.popStart();
                } else {
                    return;
                }
                removeAndRecycleView(childAt, recycler);
            } else {
                return;
            }
        }
    }

    private void repositionToWrapContentIfNecessary() {
        if (this.mSecondaryOrientation.getMode() != 1073741824) {
            int childCount = getChildCount();
            float f = 0.0f;
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                float decoratedMeasurement = (float) this.mSecondaryOrientation.getDecoratedMeasurement(childAt);
                if (decoratedMeasurement >= f) {
                    if (((LayoutParams) childAt.getLayoutParams()).isFullSpan()) {
                        decoratedMeasurement = (decoratedMeasurement * 1.0f) / ((float) this.mSpanCount);
                    }
                    f = Math.max(f, decoratedMeasurement);
                }
            }
            int i7 = this.mSizePerSpan;
            int round = Math.round(f * ((float) this.mSpanCount));
            if (this.mSecondaryOrientation.getMode() == Integer.MIN_VALUE) {
                round = Math.min(round, this.mSecondaryOrientation.getTotalSpace());
            }
            updateMeasureSpecs(round);
            if (this.mSizePerSpan != i7) {
                for (int i8 = 0; i8 < childCount; i8++) {
                    View childAt2 = getChildAt(i8);
                    LayoutParams layoutParams = (LayoutParams) childAt2.getLayoutParams();
                    if (!layoutParams.mFullSpan) {
                        if (!isLayoutRTL() || this.mOrientation != 1) {
                            int i10 = layoutParams.mSpan.mIndex;
                            int i11 = this.mSizePerSpan * i10;
                            int i12 = i10 * i7;
                            if (this.mOrientation == 1) {
                                childAt2.offsetLeftAndRight(i11 - i12);
                            } else {
                                childAt2.offsetTopAndBottom(i11 - i12);
                            }
                        } else {
                            int i13 = this.mSpanCount;
                            int i14 = layoutParams.mSpan.mIndex;
                            childAt2.offsetLeftAndRight(((-((i13 - 1) - i14)) * this.mSizePerSpan) - ((-((i13 - 1) - i14)) * i7));
                        }
                    }
                }
            }
        }
    }

    private void resolveShouldLayoutReverse() {
        if (this.mOrientation == 1 || !isLayoutRTL()) {
            this.mShouldReverseLayout = this.mReverseLayout;
        } else {
            this.mShouldReverseLayout = !this.mReverseLayout;
        }
    }

    private void setLayoutStateDirection(int i2) {
        boolean z;
        LayoutState layoutState = this.mLayoutState;
        layoutState.mLayoutDirection = i2;
        boolean z3 = this.mShouldReverseLayout;
        int i7 = 1;
        if (i2 == -1) {
            z = true;
        } else {
            z = false;
        }
        if (z3 != z) {
            i7 = -1;
        }
        layoutState.mItemDirection = i7;
    }

    private void updateAllRemainingSpans(int i2, int i7) {
        for (int i8 = 0; i8 < this.mSpanCount; i8++) {
            if (!this.mSpans[i8].mViews.isEmpty()) {
                updateRemainingSpans(this.mSpans[i8], i2, i7);
            }
        }
    }

    private boolean updateAnchorFromChildren(RecyclerView.State state, AnchorInfo anchorInfo) {
        int i2;
        if (this.mLastLayoutFromEnd) {
            i2 = findLastReferenceChildPosition(state.getItemCount());
        } else {
            i2 = findFirstReferenceChildPosition(state.getItemCount());
        }
        anchorInfo.mPosition = i2;
        anchorInfo.mOffset = Integer.MIN_VALUE;
        return true;
    }

    private void updateLayoutState(int i2, RecyclerView.State state) {
        int i7;
        int i8;
        int targetScrollPosition;
        boolean z;
        LayoutState layoutState = this.mLayoutState;
        boolean z3 = false;
        layoutState.mAvailable = 0;
        layoutState.mCurrentPosition = i2;
        if (!isSmoothScrolling() || (targetScrollPosition = state.getTargetScrollPosition()) == -1) {
            i8 = 0;
            i7 = 0;
        } else {
            boolean z7 = this.mShouldReverseLayout;
            if (targetScrollPosition < i2) {
                z = true;
            } else {
                z = false;
            }
            if (z7 == z) {
                i8 = this.mPrimaryOrientation.getTotalSpace();
                i7 = 0;
            } else {
                i7 = this.mPrimaryOrientation.getTotalSpace();
                i8 = 0;
            }
        }
        if (getClipToPadding()) {
            this.mLayoutState.mStartLine = this.mPrimaryOrientation.getStartAfterPadding() - i7;
            this.mLayoutState.mEndLine = this.mPrimaryOrientation.getEndAfterPadding() + i8;
        } else {
            this.mLayoutState.mEndLine = this.mPrimaryOrientation.getEnd() + i8;
            this.mLayoutState.mStartLine = -i7;
        }
        LayoutState layoutState2 = this.mLayoutState;
        layoutState2.mStopInFocusable = false;
        layoutState2.mRecycle = true;
        if (this.mPrimaryOrientation.getMode() == 0 && this.mPrimaryOrientation.getEnd() == 0) {
            z3 = true;
        }
        layoutState2.mInfinite = z3;
    }

    private void updateRemainingSpans(Span span, int i2, int i7) {
        int deletedSize = span.getDeletedSize();
        if (i2 == -1) {
            if (span.getStartLine() + deletedSize <= i7) {
                this.mRemainingSpans.set(span.mIndex, false);
            }
        } else if (span.getEndLine() - deletedSize >= i7) {
            this.mRemainingSpans.set(span.mIndex, false);
        }
    }

    private int updateSpecWithExtra(int i2, int i7, int i8) {
        int mode;
        if ((i7 != 0 || i8 != 0) && ((mode = View.MeasureSpec.getMode(i2)) == Integer.MIN_VALUE || mode == 1073741824)) {
            return View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i2) - i7) - i8), mode);
        }
        return i2;
    }

    public boolean areAllEndsEqual() {
        int endLine = this.mSpans[0].getEndLine(Integer.MIN_VALUE);
        for (int i2 = 1; i2 < this.mSpanCount; i2++) {
            if (this.mSpans[i2].getEndLine(Integer.MIN_VALUE) != endLine) {
                return false;
            }
        }
        return true;
    }

    public boolean areAllStartsEqual() {
        int startLine = this.mSpans[0].getStartLine(Integer.MIN_VALUE);
        for (int i2 = 1; i2 < this.mSpanCount; i2++) {
            if (this.mSpans[i2].getStartLine(Integer.MIN_VALUE) != startLine) {
                return false;
            }
        }
        return true;
    }

    public void assertNotInLayoutOrScroll(String str) {
        if (this.mPendingSavedState == null) {
            super.assertNotInLayoutOrScroll(str);
        }
    }

    public boolean canScrollHorizontally() {
        if (this.mOrientation == 0) {
            return true;
        }
        return false;
    }

    public boolean canScrollVertically() {
        if (this.mOrientation == 1) {
            return true;
        }
        return false;
    }

    public boolean checkForGaps() {
        int i2;
        int i7;
        int i8;
        if (getChildCount() == 0 || this.mGapStrategy == 0 || !isAttachedToWindow()) {
            return false;
        }
        if (this.mShouldReverseLayout) {
            i7 = getLastChildPosition();
            i2 = getFirstChildPosition();
        } else {
            i7 = getFirstChildPosition();
            i2 = getLastChildPosition();
        }
        if (i7 == 0 && hasGapsToFix() != null) {
            this.mLazySpanLookup.clear();
            requestSimpleAnimationsInNextLayout();
            requestLayout();
            return true;
        } else if (!this.mLaidOutInvalidFullSpan) {
            return false;
        } else {
            if (this.mShouldReverseLayout) {
                i8 = -1;
            } else {
                i8 = 1;
            }
            int i10 = i2 + 1;
            LazySpanLookup.FullSpanItem firstFullSpanItemInRange = this.mLazySpanLookup.getFirstFullSpanItemInRange(i7 - 1, i10, i8, true);
            if (firstFullSpanItemInRange == null) {
                this.mLaidOutInvalidFullSpan = false;
                this.mLazySpanLookup.forceInvalidateAfter(i10);
                return false;
            }
            LazySpanLookup.FullSpanItem firstFullSpanItemInRange2 = this.mLazySpanLookup.getFirstFullSpanItemInRange(i7, firstFullSpanItemInRange.mPosition, i8 * -1, true);
            if (firstFullSpanItemInRange2 == null) {
                this.mLazySpanLookup.forceInvalidateAfter(firstFullSpanItemInRange.mPosition);
            } else {
                this.mLazySpanLookup.forceInvalidateAfter(firstFullSpanItemInRange2.mPosition + 1);
            }
            requestSimpleAnimationsInNextLayout();
            requestLayout();
            return true;
        }
    }

    public boolean checkLayoutParams(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void collectAdjacentPrefetchPositions(int i2, int i7, RecyclerView.State state, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int endLine;
        int i8;
        if (this.mOrientation != 0) {
            i2 = i7;
        }
        if (getChildCount() != 0 && i2 != 0) {
            prepareLayoutStateForDelta(i2, state);
            int[] iArr = this.mPrefetchDistances;
            if (iArr == null || iArr.length < this.mSpanCount) {
                this.mPrefetchDistances = new int[this.mSpanCount];
            }
            int i10 = 0;
            for (int i11 = 0; i11 < this.mSpanCount; i11++) {
                LayoutState layoutState = this.mLayoutState;
                if (layoutState.mItemDirection == -1) {
                    endLine = layoutState.mStartLine;
                    i8 = this.mSpans[i11].getStartLine(endLine);
                } else {
                    endLine = this.mSpans[i11].getEndLine(layoutState.mEndLine);
                    i8 = this.mLayoutState.mEndLine;
                }
                int i12 = endLine - i8;
                if (i12 >= 0) {
                    this.mPrefetchDistances[i10] = i12;
                    i10++;
                }
            }
            Arrays.sort(this.mPrefetchDistances, 0, i10);
            for (int i13 = 0; i13 < i10 && this.mLayoutState.hasMore(state); i13++) {
                layoutPrefetchRegistry.addPosition(this.mLayoutState.mCurrentPosition, this.mPrefetchDistances[i13]);
                LayoutState layoutState2 = this.mLayoutState;
                layoutState2.mCurrentPosition += layoutState2.mItemDirection;
            }
        }
    }

    public int computeHorizontalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    public int computeHorizontalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    public int computeHorizontalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    public PointF computeScrollVectorForPosition(int i2) {
        int calculateScrollDirectionForPosition = calculateScrollDirectionForPosition(i2);
        PointF pointF = new PointF();
        if (calculateScrollDirectionForPosition == 0) {
            return null;
        }
        if (this.mOrientation == 0) {
            pointF.x = (float) calculateScrollDirectionForPosition;
            pointF.y = 0.0f;
            return pointF;
        }
        pointF.x = 0.0f;
        pointF.y = (float) calculateScrollDirectionForPosition;
        return pointF;
    }

    public int computeVerticalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    public int computeVerticalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    public int computeVerticalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    public View findFirstVisibleItemClosestToEnd(boolean z) {
        int startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
        int endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
        View view = null;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            int decoratedStart = this.mPrimaryOrientation.getDecoratedStart(childAt);
            int decoratedEnd = this.mPrimaryOrientation.getDecoratedEnd(childAt);
            if (decoratedEnd > startAfterPadding && decoratedStart < endAfterPadding) {
                if (decoratedEnd <= endAfterPadding || !z) {
                    return childAt;
                }
                if (view == null) {
                    view = childAt;
                }
            }
        }
        return view;
    }

    public View findFirstVisibleItemClosestToStart(boolean z) {
        int startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
        int endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
        int childCount = getChildCount();
        View view = null;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            int decoratedStart = this.mPrimaryOrientation.getDecoratedStart(childAt);
            if (this.mPrimaryOrientation.getDecoratedEnd(childAt) > startAfterPadding && decoratedStart < endAfterPadding) {
                if (decoratedStart >= startAfterPadding || !z) {
                    return childAt;
                }
                if (view == null) {
                    view = childAt;
                }
            }
        }
        return view;
    }

    public int findFirstVisibleItemPositionInt() {
        View view;
        if (this.mShouldReverseLayout) {
            view = findFirstVisibleItemClosestToEnd(true);
        } else {
            view = findFirstVisibleItemClosestToStart(true);
        }
        if (view == null) {
            return -1;
        }
        return getPosition(view);
    }

    public int[] findFirstVisibleItemPositions(int[] iArr) {
        if (iArr == null) {
            iArr = new int[this.mSpanCount];
        } else if (iArr.length < this.mSpanCount) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + iArr.length);
        }
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            iArr[i2] = this.mSpans[i2].findFirstVisibleItemPosition();
        }
        return iArr;
    }

    public int[] findLastCompletelyVisibleItemPositions(int[] iArr) {
        if (iArr == null) {
            iArr = new int[this.mSpanCount];
        } else if (iArr.length < this.mSpanCount) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + iArr.length);
        }
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            iArr[i2] = this.mSpans[i2].findLastCompletelyVisibleItemPosition();
        }
        return iArr;
    }

    public int[] findLastVisibleItemPositions(int[] iArr) {
        if (iArr == null) {
            iArr = new int[this.mSpanCount];
        } else if (iArr.length < this.mSpanCount) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + iArr.length);
        }
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            iArr[i2] = this.mSpans[i2].findLastVisibleItemPosition();
        }
        return iArr;
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
            return this.mSpanCount;
        }
        return super.getColumnCountForAccessibility(recycler, state);
    }

    public int getFirstChildPosition() {
        if (getChildCount() == 0) {
            return 0;
        }
        return getPosition(getChildAt(0));
    }

    public int getLastChildPosition() {
        int childCount = getChildCount();
        if (childCount == 0) {
            return 0;
        }
        return getPosition(getChildAt(childCount - 1));
    }

    public int getRowCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation == 0) {
            return this.mSpanCount;
        }
        return super.getRowCountForAccessibility(recycler, state);
    }

    public int getSpanCount() {
        return this.mSpanCount;
    }

    public View hasGapsToFix() {
        char c5;
        int i2;
        boolean z;
        boolean z3;
        int childCount = getChildCount();
        int i7 = childCount - 1;
        BitSet bitSet = new BitSet(this.mSpanCount);
        bitSet.set(0, this.mSpanCount, true);
        int i8 = -1;
        if (this.mOrientation != 1 || !isLayoutRTL()) {
            c5 = 65535;
        } else {
            c5 = 1;
        }
        if (this.mShouldReverseLayout) {
            childCount = -1;
        } else {
            i7 = 0;
        }
        if (i7 < childCount) {
            i8 = 1;
        }
        while (i7 != childCount) {
            View childAt = getChildAt(i7);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (bitSet.get(layoutParams.mSpan.mIndex)) {
                if (checkSpanForGap(layoutParams.mSpan)) {
                    return childAt;
                }
                bitSet.clear(layoutParams.mSpan.mIndex);
            }
            if (!layoutParams.mFullSpan && (i2 = i7 + i8) != childCount) {
                View childAt2 = getChildAt(i2);
                if (this.mShouldReverseLayout) {
                    int decoratedEnd = this.mPrimaryOrientation.getDecoratedEnd(childAt);
                    int decoratedEnd2 = this.mPrimaryOrientation.getDecoratedEnd(childAt2);
                    if (decoratedEnd < decoratedEnd2) {
                        return childAt;
                    }
                    if (decoratedEnd != decoratedEnd2) {
                        continue;
                    }
                } else {
                    int decoratedStart = this.mPrimaryOrientation.getDecoratedStart(childAt);
                    int decoratedStart2 = this.mPrimaryOrientation.getDecoratedStart(childAt2);
                    if (decoratedStart > decoratedStart2) {
                        return childAt;
                    }
                    if (decoratedStart != decoratedStart2) {
                        continue;
                    }
                }
                if (layoutParams.mSpan.mIndex - ((LayoutParams) childAt2.getLayoutParams()).mSpan.mIndex < 0) {
                    z = true;
                } else {
                    z = false;
                }
                if (c5 < 0) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z != z3) {
                    return childAt;
                }
            }
            i7 += i8;
        }
        return null;
    }

    public void invalidateSpanAssignments() {
        this.mLazySpanLookup.clear();
        requestLayout();
    }

    public boolean isAutoMeasureEnabled() {
        if (this.mGapStrategy != 0) {
            return true;
        }
        return false;
    }

    public boolean isLayoutRTL() {
        if (getLayoutDirection() == 1) {
            return true;
        }
        return false;
    }

    public void offsetChildrenHorizontal(int i2) {
        super.offsetChildrenHorizontal(i2);
        for (int i7 = 0; i7 < this.mSpanCount; i7++) {
            this.mSpans[i7].onOffset(i2);
        }
    }

    public void offsetChildrenVertical(int i2) {
        super.offsetChildrenVertical(i2);
        for (int i7 = 0; i7 < this.mSpanCount; i7++) {
            this.mSpans[i7].onOffset(i2);
        }
    }

    public void onAdapterChanged(RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2) {
        this.mLazySpanLookup.clear();
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            this.mSpans[i2].clear();
        }
    }

    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        removeCallbacks(this.mCheckForGapsRunnable);
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            this.mSpans[i2].clear();
        }
        recyclerView.requestLayout();
    }

    public View onFocusSearchFailed(View view, int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        View findContainingItemView;
        int i7;
        boolean z;
        boolean z3;
        int i8;
        int i10;
        int i11;
        View focusableViewAfter;
        if (getChildCount() == 0 || (findContainingItemView = findContainingItemView(view)) == null) {
            return null;
        }
        resolveShouldLayoutReverse();
        int convertFocusDirectionToLayoutDirection = convertFocusDirectionToLayoutDirection(i2);
        if (convertFocusDirectionToLayoutDirection == Integer.MIN_VALUE) {
            return null;
        }
        LayoutParams layoutParams = (LayoutParams) findContainingItemView.getLayoutParams();
        boolean z7 = layoutParams.mFullSpan;
        Span span = layoutParams.mSpan;
        if (convertFocusDirectionToLayoutDirection == 1) {
            i7 = getLastChildPosition();
        } else {
            i7 = getFirstChildPosition();
        }
        updateLayoutState(i7, state);
        setLayoutStateDirection(convertFocusDirectionToLayoutDirection);
        LayoutState layoutState = this.mLayoutState;
        layoutState.mCurrentPosition = layoutState.mItemDirection + i7;
        layoutState.mAvailable = (int) (((float) this.mPrimaryOrientation.getTotalSpace()) * 0.33333334f);
        LayoutState layoutState2 = this.mLayoutState;
        layoutState2.mStopInFocusable = true;
        layoutState2.mRecycle = false;
        fill(recycler, layoutState2, state);
        this.mLastLayoutFromEnd = this.mShouldReverseLayout;
        if (!z7 && (focusableViewAfter = span.getFocusableViewAfter(i7, convertFocusDirectionToLayoutDirection)) != null && focusableViewAfter != findContainingItemView) {
            return focusableViewAfter;
        }
        if (preferLastSpan(convertFocusDirectionToLayoutDirection)) {
            for (int i12 = this.mSpanCount - 1; i12 >= 0; i12--) {
                View focusableViewAfter2 = this.mSpans[i12].getFocusableViewAfter(i7, convertFocusDirectionToLayoutDirection);
                if (focusableViewAfter2 != null && focusableViewAfter2 != findContainingItemView) {
                    return focusableViewAfter2;
                }
            }
        } else {
            for (int i13 = 0; i13 < this.mSpanCount; i13++) {
                View focusableViewAfter3 = this.mSpans[i13].getFocusableViewAfter(i7, convertFocusDirectionToLayoutDirection);
                if (focusableViewAfter3 != null && focusableViewAfter3 != findContainingItemView) {
                    return focusableViewAfter3;
                }
            }
        }
        boolean z9 = !this.mReverseLayout;
        if (convertFocusDirectionToLayoutDirection == -1) {
            z = true;
        } else {
            z = false;
        }
        if (z9 == z) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (!z7) {
            if (z3) {
                i11 = span.findFirstPartiallyVisibleItemPosition();
            } else {
                i11 = span.findLastPartiallyVisibleItemPosition();
            }
            View findViewByPosition = findViewByPosition(i11);
            if (!(findViewByPosition == null || findViewByPosition == findContainingItemView)) {
                return findViewByPosition;
            }
        }
        if (preferLastSpan(convertFocusDirectionToLayoutDirection)) {
            for (int i14 = this.mSpanCount - 1; i14 >= 0; i14--) {
                if (i14 != span.mIndex) {
                    if (z3) {
                        i10 = this.mSpans[i14].findFirstPartiallyVisibleItemPosition();
                    } else {
                        i10 = this.mSpans[i14].findLastPartiallyVisibleItemPosition();
                    }
                    View findViewByPosition2 = findViewByPosition(i10);
                    if (!(findViewByPosition2 == null || findViewByPosition2 == findContainingItemView)) {
                        return findViewByPosition2;
                    }
                }
            }
        } else {
            for (int i15 = 0; i15 < this.mSpanCount; i15++) {
                if (z3) {
                    i8 = this.mSpans[i15].findFirstPartiallyVisibleItemPosition();
                } else {
                    i8 = this.mSpans[i15].findLastPartiallyVisibleItemPosition();
                }
                View findViewByPosition3 = findViewByPosition(i8);
                if (findViewByPosition3 != null && findViewByPosition3 != findContainingItemView) {
                    return findViewByPosition3;
                }
            }
        }
        return null;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (getChildCount() > 0) {
            View findFirstVisibleItemClosestToStart = findFirstVisibleItemClosestToStart(false);
            View findFirstVisibleItemClosestToEnd = findFirstVisibleItemClosestToEnd(false);
            if (findFirstVisibleItemClosestToStart != null && findFirstVisibleItemClosestToEnd != null) {
                int position = getPosition(findFirstVisibleItemClosestToStart);
                int position2 = getPosition(findFirstVisibleItemClosestToEnd);
                if (position < position2) {
                    accessibilityEvent.setFromIndex(position);
                    accessibilityEvent.setToIndex(position2);
                    return;
                }
                accessibilityEvent.setFromIndex(position2);
                accessibilityEvent.setToIndex(position);
            }
        }
    }

    public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler recycler, RecyclerView.State state, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof LayoutParams)) {
            super.onInitializeAccessibilityNodeInfoForItem(view, accessibilityNodeInfoCompat);
            return;
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        int i2 = 1;
        if (this.mOrientation == 0) {
            int spanIndex = layoutParams2.getSpanIndex();
            if (layoutParams2.mFullSpan) {
                i2 = this.mSpanCount;
            }
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(spanIndex, i2, -1, -1, false, false));
            return;
        }
        int spanIndex2 = layoutParams2.getSpanIndex();
        if (layoutParams2.mFullSpan) {
            i2 = this.mSpanCount;
        }
        accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(-1, -1, spanIndex2, i2, false, false));
    }

    public void onItemsAdded(RecyclerView recyclerView, int i2, int i7) {
        handleUpdate(i2, i7, 1);
    }

    public void onItemsChanged(RecyclerView recyclerView) {
        this.mLazySpanLookup.clear();
        requestLayout();
    }

    public void onItemsMoved(RecyclerView recyclerView, int i2, int i7, int i8) {
        handleUpdate(i2, i7, 8);
    }

    public void onItemsRemoved(RecyclerView recyclerView, int i2, int i7) {
        handleUpdate(i2, i7, 2);
    }

    public void onItemsUpdated(RecyclerView recyclerView, int i2, int i7, Object obj) {
        handleUpdate(i2, i7, 4);
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        onLayoutChildren(recycler, state, true);
    }

    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPendingSavedState = null;
        this.mAnchorInfo.reset();
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.mPendingSavedState = savedState;
            if (this.mPendingScrollPosition != -1) {
                savedState.invalidateAnchorPositionInfo();
                this.mPendingSavedState.invalidateSpanInfo();
            }
            requestLayout();
        }
    }

    public Parcelable onSaveInstanceState() {
        int i2;
        int i7;
        int startAfterPadding;
        int[] iArr;
        if (this.mPendingSavedState != null) {
            return new SavedState(this.mPendingSavedState);
        }
        SavedState savedState = new SavedState();
        savedState.mReverseLayout = this.mReverseLayout;
        savedState.mAnchorLayoutFromEnd = this.mLastLayoutFromEnd;
        savedState.mLastLayoutRTL = this.mLastLayoutRTL;
        LazySpanLookup lazySpanLookup = this.mLazySpanLookup;
        if (lazySpanLookup == null || (iArr = lazySpanLookup.mData) == null) {
            savedState.mSpanLookupSize = 0;
        } else {
            savedState.mSpanLookup = iArr;
            savedState.mSpanLookupSize = iArr.length;
            savedState.mFullSpanItems = lazySpanLookup.mFullSpanItems;
        }
        if (getChildCount() > 0) {
            if (this.mLastLayoutFromEnd) {
                i2 = getLastChildPosition();
            } else {
                i2 = getFirstChildPosition();
            }
            savedState.mAnchorPosition = i2;
            savedState.mVisibleAnchorPosition = findFirstVisibleItemPositionInt();
            int i8 = this.mSpanCount;
            savedState.mSpanOffsetsSize = i8;
            savedState.mSpanOffsets = new int[i8];
            for (int i10 = 0; i10 < this.mSpanCount; i10++) {
                if (this.mLastLayoutFromEnd) {
                    i7 = this.mSpans[i10].getEndLine(Integer.MIN_VALUE);
                    if (i7 != Integer.MIN_VALUE) {
                        startAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
                    } else {
                        savedState.mSpanOffsets[i10] = i7;
                    }
                } else {
                    i7 = this.mSpans[i10].getStartLine(Integer.MIN_VALUE);
                    if (i7 != Integer.MIN_VALUE) {
                        startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
                    } else {
                        savedState.mSpanOffsets[i10] = i7;
                    }
                }
                i7 -= startAfterPadding;
                savedState.mSpanOffsets[i10] = i7;
            }
            return savedState;
        }
        savedState.mAnchorPosition = -1;
        savedState.mVisibleAnchorPosition = -1;
        savedState.mSpanOffsetsSize = 0;
        return savedState;
    }

    public void onScrollStateChanged(int i2) {
        if (i2 == 0) {
            checkForGaps();
        }
    }

    public void prepareLayoutStateForDelta(int i2, RecyclerView.State state) {
        int i7;
        int i8;
        if (i2 > 0) {
            i8 = getLastChildPosition();
            i7 = 1;
        } else {
            i8 = getFirstChildPosition();
            i7 = -1;
        }
        this.mLayoutState.mRecycle = true;
        updateLayoutState(i8, state);
        setLayoutStateDirection(i7);
        LayoutState layoutState = this.mLayoutState;
        layoutState.mCurrentPosition = i8 + layoutState.mItemDirection;
        layoutState.mAvailable = Math.abs(i2);
    }

    public int scrollBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0 || i2 == 0) {
            return 0;
        }
        prepareLayoutStateForDelta(i2, state);
        int fill = fill(recycler, this.mLayoutState, state);
        if (this.mLayoutState.mAvailable >= fill) {
            if (i2 < 0) {
                i2 = -fill;
            } else {
                i2 = fill;
            }
        }
        this.mPrimaryOrientation.offsetChildren(-i2);
        this.mLastLayoutFromEnd = this.mShouldReverseLayout;
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.showGoToTop();
        }
        LayoutState layoutState = this.mLayoutState;
        layoutState.mAvailable = 0;
        recycle(recycler, layoutState);
        return i2;
    }

    public int scrollHorizontallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return scrollBy(i2, recycler, state);
    }

    public void scrollToPosition(int i2) {
        SavedState savedState = this.mPendingSavedState;
        if (!(savedState == null || savedState.mAnchorPosition == i2)) {
            savedState.invalidateAnchorPositionInfo();
        }
        this.mPendingScrollPosition = i2;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.showGoToTop();
        }
        requestLayout();
    }

    public void scrollToPositionWithOffset(int i2, int i7) {
        scrollToPositionWithOffset(i2, i7, false);
    }

    public int scrollVerticallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return scrollBy(i2, recycler, state);
    }

    public void setMeasuredDimension(Rect rect, int i2, int i7) {
        int i8;
        int i10;
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        if (this.mOrientation == 1) {
            i10 = RecyclerView.LayoutManager.chooseSize(i7, rect.height() + paddingBottom, getMinimumHeight());
            i8 = RecyclerView.LayoutManager.chooseSize(i2, (this.mSizePerSpan * this.mSpanCount) + paddingRight, getMinimumWidth());
        } else {
            i8 = RecyclerView.LayoutManager.chooseSize(i2, rect.width() + paddingRight, getMinimumWidth());
            i10 = RecyclerView.LayoutManager.chooseSize(i7, (this.mSizePerSpan * this.mSpanCount) + paddingBottom, getMinimumHeight());
        }
        setMeasuredDimension(i8, i10);
    }

    public void setOrientation(int i2) {
        if (i2 == 0 || i2 == 1) {
            assertNotInLayoutOrScroll((String) null);
            if (i2 != this.mOrientation) {
                this.mOrientation = i2;
                OrientationHelper orientationHelper = this.mPrimaryOrientation;
                this.mPrimaryOrientation = this.mSecondaryOrientation;
                this.mSecondaryOrientation = orientationHelper;
                requestLayout();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("invalid orientation.");
    }

    public void setReverseLayout(boolean z) {
        assertNotInLayoutOrScroll((String) null);
        SavedState savedState = this.mPendingSavedState;
        if (!(savedState == null || savedState.mReverseLayout == z)) {
            savedState.mReverseLayout = z;
        }
        this.mReverseLayout = z;
        requestLayout();
    }

    public void setSpanCount(int i2) {
        assertNotInLayoutOrScroll((String) null);
        if (i2 != this.mSpanCount) {
            invalidateSpanAssignments();
            this.mSpanCount = i2;
            this.mRemainingSpans = new BitSet(this.mSpanCount);
            this.mSpans = new Span[this.mSpanCount];
            for (int i7 = 0; i7 < this.mSpanCount; i7++) {
                this.mSpans[i7] = new Span(i7);
            }
            requestLayout();
        }
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i2) {
        if (recyclerView != null) {
            LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
            recyclerView.showGoToTop();
            linearSmoothScroller.setTargetPosition(i2);
            startSmoothScroll(linearSmoothScroller);
        }
    }

    public boolean supportsPredictiveItemAnimations() {
        if (this.mPendingSavedState == null) {
            return true;
        }
        return false;
    }

    public boolean updateAnchorFromPendingData(RecyclerView.State state, AnchorInfo anchorInfo) {
        int i2;
        int i7;
        int i8;
        boolean z = false;
        if (!state.isPreLayout() && (i2 = this.mPendingScrollPosition) != -1) {
            if (i2 < 0 || i2 >= state.getItemCount()) {
                this.mPendingScrollPosition = -1;
                this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
            } else {
                SavedState savedState = this.mPendingSavedState;
                if (savedState == null || savedState.mAnchorPosition == -1 || savedState.mSpanOffsetsSize < 1) {
                    View findViewByPosition = findViewByPosition(this.mPendingScrollPosition);
                    if (findViewByPosition != null) {
                        if (this.mShouldReverseLayout) {
                            i7 = getLastChildPosition();
                        } else {
                            i7 = getFirstChildPosition();
                        }
                        anchorInfo.mPosition = i7;
                        if (this.mPendingScrollPositionOffset != Integer.MIN_VALUE) {
                            if (anchorInfo.mLayoutFromEnd) {
                                anchorInfo.mOffset = (this.mPrimaryOrientation.getEndAfterPadding() - this.mPendingScrollPositionOffset) - this.mPrimaryOrientation.getDecoratedEnd(findViewByPosition);
                            } else {
                                anchorInfo.mOffset = (this.mPrimaryOrientation.getStartAfterPadding() + this.mPendingScrollPositionOffset) - this.mPrimaryOrientation.getDecoratedStart(findViewByPosition);
                            }
                            return true;
                        } else if (this.mPrimaryOrientation.getDecoratedMeasurement(findViewByPosition) > this.mPrimaryOrientation.getTotalSpace()) {
                            if (anchorInfo.mLayoutFromEnd) {
                                i8 = this.mPrimaryOrientation.getEndAfterPadding();
                            } else {
                                i8 = this.mPrimaryOrientation.getStartAfterPadding();
                            }
                            anchorInfo.mOffset = i8;
                            return true;
                        } else {
                            int decoratedStart = this.mPrimaryOrientation.getDecoratedStart(findViewByPosition) - this.mPrimaryOrientation.getStartAfterPadding();
                            if (decoratedStart < 0) {
                                anchorInfo.mOffset = -decoratedStart;
                                return true;
                            }
                            int endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding() - this.mPrimaryOrientation.getDecoratedEnd(findViewByPosition);
                            if (endAfterPadding < 0) {
                                anchorInfo.mOffset = endAfterPadding;
                                return true;
                            }
                            anchorInfo.mOffset = Integer.MIN_VALUE;
                        }
                    } else {
                        int i10 = this.mPendingScrollPosition;
                        anchorInfo.mPosition = i10;
                        int i11 = this.mPendingScrollPositionOffset;
                        if (i11 == Integer.MIN_VALUE) {
                            if (calculateScrollDirectionForPosition(i10) == 1) {
                                z = true;
                            }
                            anchorInfo.mLayoutFromEnd = z;
                            anchorInfo.assignCoordinateFromPadding();
                        } else {
                            anchorInfo.assignCoordinateFromPadding(i11);
                        }
                        anchorInfo.mInvalidateOffsets = true;
                    }
                } else {
                    anchorInfo.mOffset = Integer.MIN_VALUE;
                    anchorInfo.mPosition = this.mPendingScrollPosition;
                }
                return true;
            }
        }
        return false;
    }

    public void updateAnchorInfoForLayout(RecyclerView.State state, AnchorInfo anchorInfo) {
        if (!updateAnchorFromPendingData(state, anchorInfo) && !updateAnchorFromChildren(state, anchorInfo)) {
            anchorInfo.assignCoordinateFromPadding();
            anchorInfo.mPosition = 0;
        }
    }

    public void updateMeasureSpecs(int i2) {
        this.mSizePerSpan = i2 / this.mSpanCount;
        this.mFullSizeSpec = View.MeasureSpec.makeMeasureSpec(i2, this.mSecondaryOrientation.getMode());
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class AnchorInfo {
        boolean mInvalidateOffsets;
        boolean mLayoutFromEnd;
        int mOffset;
        int mPosition;
        int[] mSpanReferenceLines;
        boolean mValid;

        public AnchorInfo() {
            reset();
        }

        public void assignCoordinateFromPadding() {
            int i2;
            if (this.mLayoutFromEnd) {
                i2 = StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding();
            } else {
                i2 = StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding();
            }
            this.mOffset = i2;
        }

        public void reset() {
            this.mPosition = -1;
            this.mOffset = Integer.MIN_VALUE;
            this.mLayoutFromEnd = false;
            this.mInvalidateOffsets = false;
            this.mValid = false;
            int[] iArr = this.mSpanReferenceLines;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
        }

        public void saveSpanReferenceLines(Span[] spanArr) {
            int length = spanArr.length;
            int[] iArr = this.mSpanReferenceLines;
            if (iArr == null || iArr.length < length) {
                this.mSpanReferenceLines = new int[StaggeredGridLayoutManager.this.mSpans.length];
            }
            for (int i2 = 0; i2 < length; i2++) {
                this.mSpanReferenceLines[i2] = spanArr[i2].getStartLine(Integer.MIN_VALUE);
            }
        }

        public void assignCoordinateFromPadding(int i2) {
            if (this.mLayoutFromEnd) {
                this.mOffset = StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding() - i2;
            } else {
                this.mOffset = StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding() + i2;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:88:0x018b, code lost:
        if (checkForGaps() != false) goto L_0x018f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void onLayoutChildren(androidx.recyclerview.widget.RecyclerView.Recycler r9, androidx.recyclerview.widget.RecyclerView.State r10, boolean r11) {
        /*
            r8 = this;
            androidx.recyclerview.widget.StaggeredGridLayoutManager$AnchorInfo r0 = r8.mAnchorInfo
            androidx.recyclerview.widget.StaggeredGridLayoutManager$SavedState r1 = r8.mPendingSavedState
            r2 = -1
            if (r1 != 0) goto L_0x000b
            int r1 = r8.mPendingScrollPosition
            if (r1 == r2) goto L_0x0018
        L_0x000b:
            int r1 = r10.getItemCount()
            if (r1 != 0) goto L_0x0018
            r8.removeAndRecycleAllViews(r9)
            r0.reset()
            return
        L_0x0018:
            boolean r1 = r0.mValid
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L_0x0029
            int r1 = r8.mPendingScrollPosition
            if (r1 != r2) goto L_0x0029
            androidx.recyclerview.widget.StaggeredGridLayoutManager$SavedState r1 = r8.mPendingSavedState
            if (r1 == 0) goto L_0x0027
            goto L_0x0029
        L_0x0027:
            r1 = r3
            goto L_0x002a
        L_0x0029:
            r1 = r4
        L_0x002a:
            if (r1 == 0) goto L_0x0043
            r0.reset()
            androidx.recyclerview.widget.StaggeredGridLayoutManager$SavedState r5 = r8.mPendingSavedState
            if (r5 == 0) goto L_0x0037
            r8.applyPendingSavedState(r0)
            goto L_0x003e
        L_0x0037:
            r8.resolveShouldLayoutReverse()
            boolean r5 = r8.mShouldReverseLayout
            r0.mLayoutFromEnd = r5
        L_0x003e:
            r8.updateAnchorInfoForLayout(r10, r0)
            r0.mValid = r4
        L_0x0043:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$SavedState r5 = r8.mPendingSavedState
            if (r5 != 0) goto L_0x0060
            int r5 = r8.mPendingScrollPosition
            if (r5 != r2) goto L_0x0060
            boolean r5 = r0.mLayoutFromEnd
            boolean r6 = r8.mLastLayoutFromEnd
            if (r5 != r6) goto L_0x0059
            boolean r5 = r8.isLayoutRTL()
            boolean r6 = r8.mLastLayoutRTL
            if (r5 == r6) goto L_0x0060
        L_0x0059:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r5 = r8.mLazySpanLookup
            r5.clear()
            r0.mInvalidateOffsets = r4
        L_0x0060:
            int r5 = r8.getChildCount()
            if (r5 <= 0) goto L_0x0102
            androidx.recyclerview.widget.StaggeredGridLayoutManager$SavedState r5 = r8.mPendingSavedState
            if (r5 == 0) goto L_0x006e
            int r5 = r5.mSpanOffsetsSize
            if (r5 >= r4) goto L_0x0102
        L_0x006e:
            boolean r5 = r0.mInvalidateOffsets
            if (r5 == 0) goto L_0x008e
            r1 = r3
        L_0x0073:
            int r5 = r8.mSpanCount
            if (r1 >= r5) goto L_0x0102
            androidx.recyclerview.widget.StaggeredGridLayoutManager$Span[] r5 = r8.mSpans
            r5 = r5[r1]
            r5.clear()
            int r5 = r0.mOffset
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r5 == r6) goto L_0x008b
            androidx.recyclerview.widget.StaggeredGridLayoutManager$Span[] r6 = r8.mSpans
            r6 = r6[r1]
            r6.setLine(r5)
        L_0x008b:
            int r1 = r1 + 1
            goto L_0x0073
        L_0x008e:
            if (r1 != 0) goto L_0x00b4
            androidx.recyclerview.widget.StaggeredGridLayoutManager$AnchorInfo r5 = r8.mAnchorInfo
            int[] r5 = r5.mSpanReferenceLines
            if (r5 == 0) goto L_0x00b4
            int r5 = r5.length
            int r6 = r8.mSpanCount
            if (r5 >= r6) goto L_0x009c
            goto L_0x00b4
        L_0x009c:
            r1 = r3
        L_0x009d:
            int r5 = r8.mSpanCount
            if (r1 >= r5) goto L_0x0102
            androidx.recyclerview.widget.StaggeredGridLayoutManager$Span[] r5 = r8.mSpans
            r5 = r5[r1]
            r5.clear()
            androidx.recyclerview.widget.StaggeredGridLayoutManager$AnchorInfo r6 = r8.mAnchorInfo
            int[] r6 = r6.mSpanReferenceLines
            r6 = r6[r1]
            r5.setLine(r6)
            int r1 = r1 + 1
            goto L_0x009d
        L_0x00b4:
            if (r1 != 0) goto L_0x00e8
            androidx.recyclerview.widget.StaggeredGridLayoutManager$AnchorInfo r1 = r8.mAnchorInfo
            int[] r1 = r1.mSpanReferenceLines
            if (r1 == 0) goto L_0x00e8
            int r1 = r1.length
            int r5 = r8.mSpanCount
            if (r1 >= r5) goto L_0x00e8
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r5 = "mSpanReferenceLines length("
            r1.<init>(r5)
            androidx.recyclerview.widget.StaggeredGridLayoutManager$AnchorInfo r5 = r8.mAnchorInfo
            int[] r5 = r5.mSpanReferenceLines
            int r5 = r5.length
            r1.append(r5)
            java.lang.String r5 = ") smaller than SpanCount("
            r1.append(r5)
            int r5 = r8.mSpanCount
            r1.append(r5)
            java.lang.String r5 = ")"
            r1.append(r5)
            java.lang.String r1 = r1.toString()
            java.lang.String r5 = "StaggeredGridLManager"
            android.util.Log.w(r5, r1)
        L_0x00e8:
            r1 = r3
        L_0x00e9:
            int r5 = r8.mSpanCount
            if (r1 >= r5) goto L_0x00fb
            androidx.recyclerview.widget.StaggeredGridLayoutManager$Span[] r5 = r8.mSpans
            r5 = r5[r1]
            boolean r6 = r8.mShouldReverseLayout
            int r7 = r0.mOffset
            r5.cacheReferenceLineAndClear(r6, r7)
            int r1 = r1 + 1
            goto L_0x00e9
        L_0x00fb:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$AnchorInfo r1 = r8.mAnchorInfo
            androidx.recyclerview.widget.StaggeredGridLayoutManager$Span[] r5 = r8.mSpans
            r1.saveSpanReferenceLines(r5)
        L_0x0102:
            r8.detachAndScrapAttachedViews(r9)
            androidx.recyclerview.widget.LayoutState r1 = r8.mLayoutState
            r1.mRecycle = r3
            r8.mLaidOutInvalidFullSpan = r3
            androidx.recyclerview.widget.OrientationHelper r1 = r8.mSecondaryOrientation
            int r1 = r1.getTotalSpace()
            r8.updateMeasureSpecs(r1)
            int r1 = r0.mPosition
            r8.updateLayoutState(r1, r10)
            boolean r1 = r0.mLayoutFromEnd
            if (r1 == 0) goto L_0x0135
            r8.setLayoutStateDirection(r2)
            androidx.recyclerview.widget.LayoutState r1 = r8.mLayoutState
            r8.fill(r9, r1, r10)
            r8.setLayoutStateDirection(r4)
            androidx.recyclerview.widget.LayoutState r1 = r8.mLayoutState
            int r2 = r0.mPosition
            int r5 = r1.mItemDirection
            int r2 = r2 + r5
            r1.mCurrentPosition = r2
            r8.fill(r9, r1, r10)
            goto L_0x014c
        L_0x0135:
            r8.setLayoutStateDirection(r4)
            androidx.recyclerview.widget.LayoutState r1 = r8.mLayoutState
            r8.fill(r9, r1, r10)
            r8.setLayoutStateDirection(r2)
            androidx.recyclerview.widget.LayoutState r1 = r8.mLayoutState
            int r2 = r0.mPosition
            int r5 = r1.mItemDirection
            int r2 = r2 + r5
            r1.mCurrentPosition = r2
            r8.fill(r9, r1, r10)
        L_0x014c:
            r8.repositionToWrapContentIfNecessary()
            int r1 = r8.getChildCount()
            if (r1 <= 0) goto L_0x0166
            boolean r1 = r8.mShouldReverseLayout
            if (r1 == 0) goto L_0x0160
            r8.fixEndGap(r9, r10, r4)
            r8.fixStartGap(r9, r10, r3)
            goto L_0x0166
        L_0x0160:
            r8.fixStartGap(r9, r10, r4)
            r8.fixEndGap(r9, r10, r3)
        L_0x0166:
            if (r11 == 0) goto L_0x018e
            boolean r11 = r10.isPreLayout()
            if (r11 != 0) goto L_0x018e
            int r11 = r8.mGapStrategy
            if (r11 == 0) goto L_0x018e
            int r11 = r8.getChildCount()
            if (r11 <= 0) goto L_0x018e
            boolean r11 = r8.mLaidOutInvalidFullSpan
            if (r11 != 0) goto L_0x0182
            android.view.View r11 = r8.hasGapsToFix()
            if (r11 == 0) goto L_0x018e
        L_0x0182:
            java.lang.Runnable r11 = r8.mCheckForGapsRunnable
            r8.removeCallbacks(r11)
            boolean r11 = r8.checkForGaps()
            if (r11 == 0) goto L_0x018e
            goto L_0x018f
        L_0x018e:
            r4 = r3
        L_0x018f:
            boolean r11 = r10.isPreLayout()
            if (r11 == 0) goto L_0x019a
            androidx.recyclerview.widget.StaggeredGridLayoutManager$AnchorInfo r11 = r8.mAnchorInfo
            r11.reset()
        L_0x019a:
            boolean r11 = r0.mLayoutFromEnd
            r8.mLastLayoutFromEnd = r11
            boolean r11 = r8.isLayoutRTL()
            r8.mLastLayoutRTL = r11
            if (r4 == 0) goto L_0x01ae
            androidx.recyclerview.widget.StaggeredGridLayoutManager$AnchorInfo r11 = r8.mAnchorInfo
            r11.reset()
            r8.onLayoutChildren(r9, r10, r3)
        L_0x01ae:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.onLayoutChildren(androidx.recyclerview.widget.RecyclerView$Recycler, androidx.recyclerview.widget.RecyclerView$State, boolean):void");
    }

    public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    public void scrollToPositionWithOffset(int i2, int i7, boolean z) {
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null) {
            savedState.invalidateAnchorPositionInfo();
        }
        this.mPendingScrollPosition = i2;
        this.mPendingScrollPositionOffset = i7;
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.showGoToTop();
        }
        if (z) {
            this.mLazySpanLookup.clear();
        }
        requestLayout();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class Span {
        int mCachedEnd = Integer.MIN_VALUE;
        int mCachedStart = Integer.MIN_VALUE;
        int mDeletedSize = 0;
        final int mIndex;
        ArrayList<View> mViews = new ArrayList<>();

        public Span(int i2) {
            this.mIndex = i2;
        }

        public void appendToSpan(View view) {
            LayoutParams layoutParams = getLayoutParams(view);
            layoutParams.mSpan = this;
            this.mViews.add(view);
            this.mCachedEnd = Integer.MIN_VALUE;
            if (this.mViews.size() == 1) {
                this.mCachedStart = Integer.MIN_VALUE;
            }
            if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
                this.mDeletedSize = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(view) + this.mDeletedSize;
            }
        }

        public void cacheReferenceLineAndClear(boolean z, int i2) {
            int i7;
            if (z) {
                i7 = getEndLine(Integer.MIN_VALUE);
            } else {
                i7 = getStartLine(Integer.MIN_VALUE);
            }
            clear();
            if (i7 != Integer.MIN_VALUE) {
                if (z && i7 < StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding()) {
                    return;
                }
                if (z || i7 <= StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding()) {
                    if (i2 != Integer.MIN_VALUE) {
                        i7 += i2;
                    }
                    this.mCachedEnd = i7;
                    this.mCachedStart = i7;
                }
            }
        }

        public void calculateCachedEnd() {
            LazySpanLookup.FullSpanItem fullSpanItem;
            View view = (View) C0212a.i(this.mViews, 1);
            LayoutParams layoutParams = getLayoutParams(view);
            this.mCachedEnd = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedEnd(view);
            if (layoutParams.mFullSpan && (fullSpanItem = StaggeredGridLayoutManager.this.mLazySpanLookup.getFullSpanItem(layoutParams.getViewLayoutPosition())) != null && fullSpanItem.mGapDir == 1) {
                this.mCachedEnd += fullSpanItem.getGapForSpan(this.mIndex);
            }
        }

        public void calculateCachedStart() {
            LazySpanLookup.FullSpanItem fullSpanItem;
            View view = this.mViews.get(0);
            LayoutParams layoutParams = getLayoutParams(view);
            this.mCachedStart = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedStart(view);
            if (layoutParams.mFullSpan && (fullSpanItem = StaggeredGridLayoutManager.this.mLazySpanLookup.getFullSpanItem(layoutParams.getViewLayoutPosition())) != null && fullSpanItem.mGapDir == -1) {
                this.mCachedStart -= fullSpanItem.getGapForSpan(this.mIndex);
            }
        }

        public void clear() {
            this.mViews.clear();
            invalidateCache();
            this.mDeletedSize = 0;
        }

        public int findFirstPartiallyVisibleItemPosition() {
            if (StaggeredGridLayoutManager.this.mReverseLayout) {
                return findOnePartiallyVisibleChild(this.mViews.size() - 1, -1, true);
            }
            return findOnePartiallyVisibleChild(0, this.mViews.size(), true);
        }

        public int findFirstVisibleItemPosition() {
            if (StaggeredGridLayoutManager.this.mReverseLayout) {
                return findOneVisibleChild(this.mViews.size() - 1, -1, false);
            }
            return findOneVisibleChild(0, this.mViews.size(), false);
        }

        public int findLastCompletelyVisibleItemPosition() {
            if (StaggeredGridLayoutManager.this.mReverseLayout) {
                return findOneVisibleChild(0, this.mViews.size(), true);
            }
            return findOneVisibleChild(this.mViews.size() - 1, -1, true);
        }

        public int findLastPartiallyVisibleItemPosition() {
            if (StaggeredGridLayoutManager.this.mReverseLayout) {
                return findOnePartiallyVisibleChild(0, this.mViews.size(), true);
            }
            return findOnePartiallyVisibleChild(this.mViews.size() - 1, -1, true);
        }

        public int findLastVisibleItemPosition() {
            if (StaggeredGridLayoutManager.this.mReverseLayout) {
                return findOneVisibleChild(0, this.mViews.size(), false);
            }
            return findOneVisibleChild(this.mViews.size() - 1, -1, false);
        }

        public int findOnePartiallyOrCompletelyVisibleChild(int i2, int i7, boolean z, boolean z3, boolean z7) {
            int i8;
            boolean z9;
            int startAfterPadding = StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding();
            int endAfterPadding = StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding();
            if (i7 > i2) {
                i8 = 1;
            } else {
                i8 = -1;
            }
            while (i2 != i7) {
                View view = this.mViews.get(i2);
                int decoratedStart = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedStart(view);
                int decoratedEnd = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedEnd(view);
                boolean z10 = false;
                if (!z7 ? decoratedStart >= endAfterPadding : decoratedStart > endAfterPadding) {
                    z9 = false;
                } else {
                    z9 = true;
                }
                if (!z7 ? decoratedEnd > startAfterPadding : decoratedEnd >= startAfterPadding) {
                    z10 = true;
                }
                if (z9 && z10) {
                    if (!z || !z3) {
                        if (z3) {
                            return StaggeredGridLayoutManager.this.getPosition(view);
                        }
                        if (decoratedStart < startAfterPadding || decoratedEnd > endAfterPadding) {
                            return StaggeredGridLayoutManager.this.getPosition(view);
                        }
                    } else if (decoratedStart >= startAfterPadding && decoratedEnd <= endAfterPadding) {
                        return StaggeredGridLayoutManager.this.getPosition(view);
                    }
                }
                i2 += i8;
            }
            return -1;
        }

        public int findOnePartiallyVisibleChild(int i2, int i7, boolean z) {
            return findOnePartiallyOrCompletelyVisibleChild(i2, i7, false, false, z);
        }

        public int findOneVisibleChild(int i2, int i7, boolean z) {
            return findOnePartiallyOrCompletelyVisibleChild(i2, i7, z, true, false);
        }

        public int getDeletedSize() {
            return this.mDeletedSize;
        }

        public int getEndLine(int i2) {
            int i7 = this.mCachedEnd;
            if (i7 != Integer.MIN_VALUE) {
                return i7;
            }
            if (this.mViews.size() == 0) {
                return i2;
            }
            calculateCachedEnd();
            return this.mCachedEnd;
        }

        public View getFocusableViewAfter(int i2, int i7) {
            View view = null;
            if (i7 == -1) {
                int size = this.mViews.size();
                int i8 = 0;
                while (i8 < size) {
                    View view2 = this.mViews.get(i8);
                    StaggeredGridLayoutManager staggeredGridLayoutManager = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager.mReverseLayout && staggeredGridLayoutManager.getPosition(view2) <= i2) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager2 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager2.mReverseLayout && staggeredGridLayoutManager2.getPosition(view2) >= i2) || !view2.hasFocusable()) {
                        break;
                    }
                    i8++;
                    view = view2;
                }
                return view;
            }
            int size2 = this.mViews.size() - 1;
            while (size2 >= 0) {
                View view3 = this.mViews.get(size2);
                StaggeredGridLayoutManager staggeredGridLayoutManager3 = StaggeredGridLayoutManager.this;
                if (staggeredGridLayoutManager3.mReverseLayout && staggeredGridLayoutManager3.getPosition(view3) >= i2) {
                    break;
                }
                StaggeredGridLayoutManager staggeredGridLayoutManager4 = StaggeredGridLayoutManager.this;
                if ((!staggeredGridLayoutManager4.mReverseLayout && staggeredGridLayoutManager4.getPosition(view3) <= i2) || !view3.hasFocusable()) {
                    break;
                }
                size2--;
                view = view3;
            }
            return view;
        }

        public LayoutParams getLayoutParams(View view) {
            return (LayoutParams) view.getLayoutParams();
        }

        public int getStartLine(int i2) {
            int i7 = this.mCachedStart;
            if (i7 != Integer.MIN_VALUE) {
                return i7;
            }
            if (this.mViews.size() == 0) {
                return i2;
            }
            calculateCachedStart();
            return this.mCachedStart;
        }

        public void invalidateCache() {
            this.mCachedStart = Integer.MIN_VALUE;
            this.mCachedEnd = Integer.MIN_VALUE;
        }

        public void onOffset(int i2) {
            int i7 = this.mCachedStart;
            if (i7 != Integer.MIN_VALUE) {
                this.mCachedStart = i7 + i2;
            }
            int i8 = this.mCachedEnd;
            if (i8 != Integer.MIN_VALUE) {
                this.mCachedEnd = i8 + i2;
            }
        }

        public void popEnd() {
            int size = this.mViews.size();
            View remove = this.mViews.remove(size - 1);
            LayoutParams layoutParams = getLayoutParams(remove);
            layoutParams.mSpan = null;
            if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
                this.mDeletedSize -= StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(remove);
            }
            if (size == 1) {
                this.mCachedStart = Integer.MIN_VALUE;
            }
            this.mCachedEnd = Integer.MIN_VALUE;
        }

        public void popStart() {
            View remove = this.mViews.remove(0);
            LayoutParams layoutParams = getLayoutParams(remove);
            layoutParams.mSpan = null;
            if (this.mViews.size() == 0) {
                this.mCachedEnd = Integer.MIN_VALUE;
            }
            if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
                this.mDeletedSize -= StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(remove);
            }
            this.mCachedStart = Integer.MIN_VALUE;
        }

        public void prependToSpan(View view) {
            LayoutParams layoutParams = getLayoutParams(view);
            layoutParams.mSpan = this;
            this.mViews.add(0, view);
            this.mCachedStart = Integer.MIN_VALUE;
            if (this.mViews.size() == 1) {
                this.mCachedEnd = Integer.MIN_VALUE;
            }
            if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
                this.mDeletedSize = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(view) + this.mDeletedSize;
            }
        }

        public void setLine(int i2) {
            this.mCachedStart = i2;
            this.mCachedEnd = i2;
        }

        public int getEndLine() {
            int i2 = this.mCachedEnd;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            calculateCachedEnd();
            return this.mCachedEnd;
        }

        public int getStartLine() {
            int i2 = this.mCachedStart;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            calculateCachedStart();
            return this.mCachedStart;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LazySpanLookup {
        int[] mData;
        List<FullSpanItem> mFullSpanItems;

        private int invalidateFullSpansAfter(int i2) {
            if (this.mFullSpanItems == null) {
                return -1;
            }
            FullSpanItem fullSpanItem = getFullSpanItem(i2);
            if (fullSpanItem != null) {
                this.mFullSpanItems.remove(fullSpanItem);
            }
            int size = this.mFullSpanItems.size();
            int i7 = 0;
            while (true) {
                if (i7 >= size) {
                    i7 = -1;
                    break;
                } else if (this.mFullSpanItems.get(i7).mPosition >= i2) {
                    break;
                } else {
                    i7++;
                }
            }
            if (i7 == -1) {
                return -1;
            }
            this.mFullSpanItems.remove(i7);
            return this.mFullSpanItems.get(i7).mPosition;
        }

        private void offsetFullSpansForAddition(int i2, int i7) {
            List<FullSpanItem> list = this.mFullSpanItems;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    FullSpanItem fullSpanItem = this.mFullSpanItems.get(size);
                    int i8 = fullSpanItem.mPosition;
                    if (i8 >= i2) {
                        fullSpanItem.mPosition = i8 + i7;
                    }
                }
            }
        }

        private void offsetFullSpansForRemoval(int i2, int i7) {
            List<FullSpanItem> list = this.mFullSpanItems;
            if (list != null) {
                int i8 = i2 + i7;
                for (int size = list.size() - 1; size >= 0; size--) {
                    FullSpanItem fullSpanItem = this.mFullSpanItems.get(size);
                    int i10 = fullSpanItem.mPosition;
                    if (i10 >= i2) {
                        if (i10 < i8) {
                            this.mFullSpanItems.remove(size);
                        } else {
                            fullSpanItem.mPosition = i10 - i7;
                        }
                    }
                }
            }
        }

        public void addFullSpanItem(FullSpanItem fullSpanItem) {
            if (this.mFullSpanItems == null) {
                this.mFullSpanItems = new ArrayList();
            }
            int size = this.mFullSpanItems.size();
            for (int i2 = 0; i2 < size; i2++) {
                FullSpanItem fullSpanItem2 = this.mFullSpanItems.get(i2);
                if (fullSpanItem2.mPosition == fullSpanItem.mPosition) {
                    this.mFullSpanItems.remove(i2);
                }
                if (fullSpanItem2.mPosition >= fullSpanItem.mPosition) {
                    this.mFullSpanItems.add(i2, fullSpanItem);
                    return;
                }
            }
            this.mFullSpanItems.add(fullSpanItem);
        }

        public void clear() {
            int[] iArr = this.mData;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.mFullSpanItems = null;
        }

        public void ensureSize(int i2) {
            int[] iArr = this.mData;
            if (iArr == null) {
                int[] iArr2 = new int[(Math.max(i2, 10) + 1)];
                this.mData = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i2 >= iArr.length) {
                int[] iArr3 = new int[sizeForPosition(i2)];
                this.mData = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
                int[] iArr4 = this.mData;
                Arrays.fill(iArr4, iArr.length, iArr4.length, -1);
            }
        }

        public int forceInvalidateAfter(int i2) {
            List<FullSpanItem> list = this.mFullSpanItems;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    if (this.mFullSpanItems.get(size).mPosition >= i2) {
                        this.mFullSpanItems.remove(size);
                    }
                }
            }
            return invalidateAfter(i2);
        }

        public FullSpanItem getFirstFullSpanItemInRange(int i2, int i7, int i8, boolean z) {
            List<FullSpanItem> list = this.mFullSpanItems;
            if (list == null) {
                return null;
            }
            if (i2 < 0) {
                i2 = 0;
            }
            int size = list.size();
            for (int i10 = 0; i10 < size; i10++) {
                FullSpanItem fullSpanItem = this.mFullSpanItems.get(i10);
                int i11 = fullSpanItem.mPosition;
                if (i11 >= i7) {
                    return null;
                }
                if (i11 >= i2 && (i8 == 0 || fullSpanItem.mGapDir == i8 || (z && fullSpanItem.mHasUnwantedGapAfter))) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        public FullSpanItem getFullSpanItem(int i2) {
            List<FullSpanItem> list = this.mFullSpanItems;
            if (list == null) {
                return null;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.mFullSpanItems.get(size);
                if (fullSpanItem.mPosition == i2) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        public int getSpan(int i2) {
            int[] iArr = this.mData;
            if (iArr == null || i2 >= iArr.length) {
                return -1;
            }
            return iArr[i2];
        }

        public int invalidateAfter(int i2) {
            int[] iArr = this.mData;
            if (iArr == null || i2 >= iArr.length) {
                return -1;
            }
            int invalidateFullSpansAfter = invalidateFullSpansAfter(i2);
            if (invalidateFullSpansAfter == -1) {
                int[] iArr2 = this.mData;
                Arrays.fill(iArr2, i2, iArr2.length, -1);
                return this.mData.length;
            }
            int min = Math.min(invalidateFullSpansAfter + 1, this.mData.length);
            Arrays.fill(this.mData, i2, min, -1);
            return min;
        }

        public void offsetForAddition(int i2, int i7) {
            int[] iArr = this.mData;
            if (iArr != null && i2 < iArr.length) {
                int i8 = i2 + i7;
                ensureSize(i8);
                int[] iArr2 = this.mData;
                System.arraycopy(iArr2, i2, iArr2, i8, (iArr2.length - i2) - i7);
                Arrays.fill(this.mData, i2, i8, -1);
                offsetFullSpansForAddition(i2, i7);
            }
        }

        public void offsetForRemoval(int i2, int i7) {
            int[] iArr = this.mData;
            if (iArr != null && i2 < iArr.length) {
                int i8 = i2 + i7;
                ensureSize(i8);
                int[] iArr2 = this.mData;
                System.arraycopy(iArr2, i8, iArr2, i2, (iArr2.length - i2) - i7);
                int[] iArr3 = this.mData;
                Arrays.fill(iArr3, iArr3.length - i7, iArr3.length, -1);
                offsetFullSpansForRemoval(i2, i7);
            }
        }

        public void setSpan(int i2, Span span) {
            ensureSize(i2);
            this.mData[i2] = span.mIndex;
        }

        public int sizeForPosition(int i2) {
            int length = this.mData.length;
            while (length <= i2) {
                length *= 2;
            }
            return length;
        }

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class FullSpanItem implements Parcelable {
            public static final Parcelable.Creator<FullSpanItem> CREATOR = new Parcelable.Creator<FullSpanItem>() {
                public FullSpanItem createFromParcel(Parcel parcel) {
                    return new FullSpanItem(parcel);
                }

                public FullSpanItem[] newArray(int i2) {
                    return new FullSpanItem[i2];
                }
            };
            int mGapDir;
            int[] mGapPerSpan;
            boolean mHasUnwantedGapAfter;
            int mPosition;

            public FullSpanItem(Parcel parcel) {
                this.mPosition = parcel.readInt();
                this.mGapDir = parcel.readInt();
                this.mHasUnwantedGapAfter = parcel.readInt() != 1 ? false : true;
                int readInt = parcel.readInt();
                if (readInt > 0) {
                    int[] iArr = new int[readInt];
                    this.mGapPerSpan = iArr;
                    parcel.readIntArray(iArr);
                }
            }

            public int describeContents() {
                return 0;
            }

            public int getGapForSpan(int i2) {
                int[] iArr = this.mGapPerSpan;
                if (iArr == null || iArr.length <= i2) {
                    return 0;
                }
                return iArr[i2];
            }

            public String toString() {
                return "FullSpanItem{mPosition=" + this.mPosition + ", mGapDir=" + this.mGapDir + ", mHasUnwantedGapAfter=" + this.mHasUnwantedGapAfter + ", mGapPerSpan=" + Arrays.toString(this.mGapPerSpan) + '}';
            }

            public void writeToParcel(Parcel parcel, int i2) {
                parcel.writeInt(this.mPosition);
                parcel.writeInt(this.mGapDir);
                parcel.writeInt(this.mHasUnwantedGapAfter ? 1 : 0);
                int[] iArr = this.mGapPerSpan;
                if (iArr == null || iArr.length <= 0) {
                    parcel.writeInt(0);
                    return;
                }
                parcel.writeInt(iArr.length);
                parcel.writeIntArray(this.mGapPerSpan);
            }

            public FullSpanItem() {
            }
        }
    }

    public StaggeredGridLayoutManager(int i2, int i7) {
        this.mOrientation = i7;
        setSpanCount(i2);
        this.mLayoutState = new LayoutState();
        createOrientationHelpers();
    }

    private void measureChildWithDecorationsAndMargin(View view, int i2, int i7, boolean z) {
        boolean z3;
        calculateItemDecorationsForChild(view, this.mTmpRect);
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i8 = layoutParams.leftMargin;
        Rect rect = this.mTmpRect;
        int updateSpecWithExtra = updateSpecWithExtra(i2, i8 + rect.left, layoutParams.rightMargin + rect.right);
        int i10 = layoutParams.topMargin;
        Rect rect2 = this.mTmpRect;
        int updateSpecWithExtra2 = updateSpecWithExtra(i7, i10 + rect2.top, layoutParams.bottomMargin + rect2.bottom);
        if (z) {
            z3 = shouldReMeasureChild(view, updateSpecWithExtra, updateSpecWithExtra2, layoutParams);
        } else {
            z3 = shouldMeasureChild(view, updateSpecWithExtra, updateSpecWithExtra2, layoutParams);
        }
        if (z3) {
            view.measure(updateSpecWithExtra, updateSpecWithExtra2);
        }
    }
}
