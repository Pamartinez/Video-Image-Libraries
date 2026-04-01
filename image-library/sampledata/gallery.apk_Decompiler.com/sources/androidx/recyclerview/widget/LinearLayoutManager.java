package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.PathInterpolator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LinearLayoutManager extends RecyclerView.LayoutManager implements ItemTouchHelper.ViewDropHandler, RecyclerView.SmoothScroller.ScrollVectorProvider {
    final AnchorInfo mAnchorInfo;
    private int mInitialPrefetchItemCount;
    private boolean mLastStackFromEnd;
    private final LayoutChunkResult mLayoutChunkResult;
    private LayoutState mLayoutState;
    int mOrientation;
    OrientationHelper mOrientationHelper;
    /* access modifiers changed from: private */
    public PathInterpolator mPathInterpolator;
    SavedState mPendingSavedState;
    int mPendingScrollPosition;
    int mPendingScrollPositionOffset;
    private boolean mRecycleChildrenOnDetach;
    private int[] mReusableIntPair;
    boolean mReverseLayout;
    boolean mShouldReverseLayout;
    private boolean mSmoothScrollbarEnabled;
    boolean mStackFromEnd;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class AnchorInfo {
        int mCoordinate;
        boolean mLayoutFromEnd;
        OrientationHelper mOrientationHelper;
        int mPosition;
        boolean mValid;

        public AnchorInfo() {
            reset();
        }

        public void assignCoordinateFromPadding() {
            int i2;
            if (this.mLayoutFromEnd) {
                i2 = this.mOrientationHelper.getEndAfterPadding();
            } else {
                i2 = this.mOrientationHelper.getStartAfterPadding();
            }
            this.mCoordinate = i2;
        }

        public void assignFromView(View view, int i2) {
            if (this.mLayoutFromEnd) {
                this.mCoordinate = this.mOrientationHelper.getTotalSpaceChange() + this.mOrientationHelper.getDecoratedEnd(view);
            } else {
                this.mCoordinate = this.mOrientationHelper.getDecoratedStart(view);
            }
            this.mPosition = i2;
        }

        public void assignFromViewAndKeepVisibleRect(View view, int i2) {
            int totalSpaceChange = this.mOrientationHelper.getTotalSpaceChange();
            if (totalSpaceChange >= 0) {
                assignFromView(view, i2);
                return;
            }
            this.mPosition = i2;
            if (this.mLayoutFromEnd) {
                int endAfterPadding = (this.mOrientationHelper.getEndAfterPadding() - totalSpaceChange) - this.mOrientationHelper.getDecoratedEnd(view);
                this.mCoordinate = this.mOrientationHelper.getEndAfterPadding() - endAfterPadding;
                if (endAfterPadding > 0) {
                    int decoratedMeasurement = this.mCoordinate - this.mOrientationHelper.getDecoratedMeasurement(view);
                    int startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
                    int min = decoratedMeasurement - (Math.min(this.mOrientationHelper.getDecoratedStart(view) - startAfterPadding, 0) + startAfterPadding);
                    if (min < 0) {
                        this.mCoordinate = Math.min(endAfterPadding, -min) + this.mCoordinate;
                        return;
                    }
                    return;
                }
                return;
            }
            int decoratedStart = this.mOrientationHelper.getDecoratedStart(view);
            int startAfterPadding2 = decoratedStart - this.mOrientationHelper.getStartAfterPadding();
            this.mCoordinate = decoratedStart;
            if (startAfterPadding2 > 0) {
                int endAfterPadding2 = (this.mOrientationHelper.getEndAfterPadding() - Math.min(0, (this.mOrientationHelper.getEndAfterPadding() - totalSpaceChange) - this.mOrientationHelper.getDecoratedEnd(view))) - (this.mOrientationHelper.getDecoratedMeasurement(view) + decoratedStart);
                if (endAfterPadding2 < 0) {
                    this.mCoordinate -= Math.min(startAfterPadding2, -endAfterPadding2);
                }
            }
        }

        public boolean isViewValidAsAnchor(View view, RecyclerView.State state) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            if (layoutParams.isItemRemoved() || layoutParams.getViewLayoutPosition() < 0 || layoutParams.getViewLayoutPosition() >= state.getItemCount()) {
                return false;
            }
            return true;
        }

        public void reset() {
            this.mPosition = -1;
            this.mCoordinate = Integer.MIN_VALUE;
            this.mLayoutFromEnd = false;
            this.mValid = false;
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("AnchorInfo{mPosition=");
            sb2.append(this.mPosition);
            sb2.append(", mCoordinate=");
            sb2.append(this.mCoordinate);
            sb2.append(", mLayoutFromEnd=");
            sb2.append(this.mLayoutFromEnd);
            sb2.append(", mValid=");
            return C0086a.n(sb2, this.mValid, '}');
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LayoutChunkResult {
        public int mConsumed;
        public boolean mFinished;
        public boolean mFocusable;
        public boolean mIgnoreConsumed;

        public void resetInternal() {
            this.mConsumed = 0;
            this.mFinished = false;
            this.mIgnoreConsumed = false;
            this.mFocusable = false;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LayoutState {
        int mAvailable;
        int mCurrentPosition;
        int mExtraFillSpace = 0;
        boolean mInfinite;
        boolean mIsPreLayout = false;
        int mItemDirection;
        int mLastScrollDelta;
        int mLayoutDirection;
        int mNoRecycleSpace = 0;
        int mOffset;
        boolean mRecycle = true;
        List<RecyclerView.ViewHolder> mScrapList = null;
        int mScrollingOffset;

        private View nextViewFromScrapList() {
            int size = this.mScrapList.size();
            for (int i2 = 0; i2 < size; i2++) {
                View view = this.mScrapList.get(i2).itemView;
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                if (!layoutParams.isItemRemoved() && this.mCurrentPosition == layoutParams.getViewLayoutPosition()) {
                    assignPositionFromScrapList(view);
                    return view;
                }
            }
            return null;
        }

        public void assignPositionFromScrapList() {
            assignPositionFromScrapList((View) null);
        }

        public boolean hasMore(RecyclerView.State state) {
            int i2 = this.mCurrentPosition;
            if (i2 < 0 || i2 >= state.getItemCount()) {
                return false;
            }
            return true;
        }

        public View next(RecyclerView.Recycler recycler) {
            if (this.mScrapList != null) {
                return nextViewFromScrapList();
            }
            View viewForPosition = recycler.getViewForPosition(this.mCurrentPosition);
            this.mCurrentPosition += this.mItemDirection;
            return viewForPosition;
        }

        public View nextViewInLimitedList(View view) {
            int viewLayoutPosition;
            int size = this.mScrapList.size();
            View view2 = null;
            int i2 = Integer.MAX_VALUE;
            for (int i7 = 0; i7 < size; i7++) {
                View view3 = this.mScrapList.get(i7).itemView;
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view3.getLayoutParams();
                if (view3 != view && !layoutParams.isItemRemoved() && (viewLayoutPosition = (layoutParams.getViewLayoutPosition() - this.mCurrentPosition) * this.mItemDirection) >= 0 && viewLayoutPosition < i2) {
                    if (viewLayoutPosition == 0) {
                        return view3;
                    }
                    view2 = view3;
                    i2 = viewLayoutPosition;
                }
            }
            return view2;
        }

        public void assignPositionFromScrapList(View view) {
            View nextViewInLimitedList = nextViewInLimitedList(view);
            if (nextViewInLimitedList == null) {
                this.mCurrentPosition = -1;
            } else {
                this.mCurrentPosition = ((RecyclerView.LayoutParams) nextViewInLimitedList.getLayoutParams()).getViewLayoutPosition();
            }
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
        int mAnchorOffset;
        int mAnchorPosition;

        public SavedState() {
        }

        public int describeContents() {
            return 0;
        }

        public boolean hasValidAnchor() {
            if (this.mAnchorPosition >= 0) {
                return true;
            }
            return false;
        }

        public void invalidateAnchor() {
            this.mAnchorPosition = -1;
        }

        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.mAnchorPosition);
            parcel.writeInt(this.mAnchorOffset);
            parcel.writeInt(this.mAnchorLayoutFromEnd ? 1 : 0);
        }

        public SavedState(Parcel parcel) {
            this.mAnchorPosition = parcel.readInt();
            this.mAnchorOffset = parcel.readInt();
            this.mAnchorLayoutFromEnd = parcel.readInt() != 1 ? false : true;
        }

        public SavedState(SavedState savedState) {
            this.mAnchorPosition = savedState.mAnchorPosition;
            this.mAnchorOffset = savedState.mAnchorOffset;
            this.mAnchorLayoutFromEnd = savedState.mAnchorLayoutFromEnd;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class SmoothScrollerJumpIfNeeded extends LinearSmoothScroller {
        public SmoothScrollerJumpIfNeeded(Context context) {
            super(context);
        }

        public void onTargetFound(View view, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
            int calculateDxToMakeVisible = calculateDxToMakeVisible(view, getHorizontalSnapPreference());
            int calculateDyToMakeVisible = calculateDyToMakeVisible(view, getVerticalSnapPreference());
            int sqrt = (int) Math.sqrt((double) ((calculateDyToMakeVisible * calculateDyToMakeVisible) + (calculateDxToMakeVisible * calculateDxToMakeVisible)));
            if (calculateTimeForDeceleration(sqrt) > 0) {
                int i2 = (int) (((((double) sqrt) * 2.0E-4d) + 0.44999998807907104d) * 1000.0d);
                if (i2 > 800) {
                    i2 = 800;
                }
                action.update(-calculateDxToMakeVisible, -calculateDyToMakeVisible, i2, LinearLayoutManager.this.mPathInterpolator);
            }
        }
    }

    public LinearLayoutManager(Context context) {
        this(context, 1, false);
    }

    private int computeScrollExtent(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        ensureLayoutState();
        return ScrollbarHelper.computeScrollExtent(state, this.mOrientationHelper, findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true), findFirstVisibleChildClosestToEnd(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled);
    }

    private int computeScrollOffset(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        ensureLayoutState();
        return ScrollbarHelper.computeScrollOffset(state, this.mOrientationHelper, findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true), findFirstVisibleChildClosestToEnd(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled, this.mShouldReverseLayout);
    }

    private int computeScrollRange(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        ensureLayoutState();
        return ScrollbarHelper.computeScrollRange(state, this.mOrientationHelper, findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true), findFirstVisibleChildClosestToEnd(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled);
    }

    private View findFirstPartiallyOrCompletelyInvisibleChild() {
        return findOnePartiallyOrCompletelyInvisibleChild(0, getChildCount());
    }

    private View findLastPartiallyOrCompletelyInvisibleChild() {
        return findOnePartiallyOrCompletelyInvisibleChild(getChildCount() - 1, -1);
    }

    private View findPartiallyOrCompletelyInvisibleChildClosestToEnd() {
        if (this.mShouldReverseLayout) {
            return findFirstPartiallyOrCompletelyInvisibleChild();
        }
        return findLastPartiallyOrCompletelyInvisibleChild();
    }

    private View findPartiallyOrCompletelyInvisibleChildClosestToStart() {
        if (this.mShouldReverseLayout) {
            return findLastPartiallyOrCompletelyInvisibleChild();
        }
        return findFirstPartiallyOrCompletelyInvisibleChild();
    }

    private int fixLayoutEndGap(int i2, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int endAfterPadding;
        int endAfterPadding2 = this.mOrientationHelper.getEndAfterPadding() - i2;
        if (endAfterPadding2 <= 0) {
            return 0;
        }
        int i7 = -scrollBy(-endAfterPadding2, recycler, state);
        int i8 = i2 + i7;
        if (!z || (endAfterPadding = this.mOrientationHelper.getEndAfterPadding() - i8) <= 0) {
            return i7;
        }
        this.mOrientationHelper.offsetChildren(endAfterPadding);
        return endAfterPadding + i7;
    }

    private int fixLayoutStartGap(int i2, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int startAfterPadding;
        int startAfterPadding2 = i2 - this.mOrientationHelper.getStartAfterPadding();
        if (startAfterPadding2 <= 0) {
            return 0;
        }
        int i7 = -scrollBy(startAfterPadding2, recycler, state);
        int i8 = i2 + i7;
        if (!z || (startAfterPadding = i8 - this.mOrientationHelper.getStartAfterPadding()) <= 0) {
            return i7;
        }
        this.mOrientationHelper.offsetChildren(-startAfterPadding);
        return i7 - startAfterPadding;
    }

    private View getChildClosestToEnd() {
        int i2;
        if (this.mShouldReverseLayout) {
            i2 = 0;
        } else {
            i2 = getChildCount() - 1;
        }
        return getChildAt(i2);
    }

    private View getChildClosestToStart() {
        int i2;
        if (this.mShouldReverseLayout) {
            i2 = getChildCount() - 1;
        } else {
            i2 = 0;
        }
        return getChildAt(i2);
    }

    private void layoutForPredictiveAnimations(RecyclerView.Recycler recycler, RecyclerView.State state, int i2, int i7) {
        boolean z;
        if (state.willRunPredictiveAnimations() && getChildCount() != 0 && !state.isPreLayout() && supportsPredictiveItemAnimations()) {
            List<RecyclerView.ViewHolder> scrapList = recycler.getScrapList();
            int size = scrapList.size();
            int position = getPosition(getChildAt(0));
            int i8 = 0;
            int i10 = 0;
            for (int i11 = 0; i11 < size; i11++) {
                RecyclerView.ViewHolder viewHolder = scrapList.get(i11);
                if (!viewHolder.isRemoved()) {
                    if (viewHolder.getLayoutPosition() < position) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z != this.mShouldReverseLayout) {
                        i8 += this.mOrientationHelper.getDecoratedMeasurement(viewHolder.itemView);
                    } else {
                        i10 += this.mOrientationHelper.getDecoratedMeasurement(viewHolder.itemView);
                    }
                }
            }
            this.mLayoutState.mScrapList = scrapList;
            if (i8 > 0) {
                updateLayoutStateToFillStart(getPosition(getChildClosestToStart()), i2);
                LayoutState layoutState = this.mLayoutState;
                layoutState.mExtraFillSpace = i8;
                layoutState.mAvailable = 0;
                layoutState.assignPositionFromScrapList();
                fill(recycler, this.mLayoutState, state, false);
            }
            if (i10 > 0) {
                updateLayoutStateToFillEnd(getPosition(getChildClosestToEnd()), i7);
                LayoutState layoutState2 = this.mLayoutState;
                layoutState2.mExtraFillSpace = i10;
                layoutState2.mAvailable = 0;
                layoutState2.assignPositionFromScrapList();
                fill(recycler, this.mLayoutState, state, false);
            }
            this.mLayoutState.mScrapList = null;
        }
    }

    private void recycleByLayoutState(RecyclerView.Recycler recycler, LayoutState layoutState) {
        if (layoutState.mRecycle && !layoutState.mInfinite) {
            int i2 = layoutState.mScrollingOffset;
            int i7 = layoutState.mNoRecycleSpace;
            if (layoutState.mLayoutDirection == -1) {
                recycleViewsFromEnd(recycler, i2, i7);
            } else {
                recycleViewsFromStart(recycler, i2, i7);
            }
        }
    }

    private void recycleChildren(RecyclerView.Recycler recycler, int i2, int i7) {
        if (i2 != i7) {
            if (i7 > i2) {
                for (int i8 = i7 - 1; i8 >= i2; i8--) {
                    removeAndRecycleViewAt(i8, recycler);
                }
                return;
            }
            while (i2 > i7) {
                removeAndRecycleViewAt(i2, recycler);
                i2--;
            }
        }
    }

    private void recycleViewsFromEnd(RecyclerView.Recycler recycler, int i2, int i7) {
        int childCount = getChildCount();
        if (i2 >= 0) {
            int end = (this.mOrientationHelper.getEnd() - i2) + i7;
            if (this.mShouldReverseLayout) {
                for (int i8 = 0; i8 < childCount; i8++) {
                    View childAt = getChildAt(i8);
                    if (this.mOrientationHelper.getDecoratedStart(childAt) < end || this.mOrientationHelper.getTransformedStartWithDecoration(childAt) < end) {
                        recycleChildren(recycler, 0, i8);
                        return;
                    }
                }
                return;
            }
            int i10 = childCount - 1;
            for (int i11 = i10; i11 >= 0; i11--) {
                View childAt2 = getChildAt(i11);
                if (this.mOrientationHelper.getDecoratedStart(childAt2) < end || this.mOrientationHelper.getTransformedStartWithDecoration(childAt2) < end) {
                    recycleChildren(recycler, i10, i11);
                    return;
                }
            }
        }
    }

    private void recycleViewsFromStart(RecyclerView.Recycler recycler, int i2, int i7) {
        if (i2 >= 0) {
            int i8 = i2 - i7;
            int childCount = getChildCount();
            if (this.mShouldReverseLayout) {
                int i10 = childCount - 1;
                for (int i11 = i10; i11 >= 0; i11--) {
                    View childAt = getChildAt(i11);
                    if (this.mOrientationHelper.getDecoratedEnd(childAt) > i8 || this.mOrientationHelper.getTransformedEndWithDecoration(childAt) > i8) {
                        recycleChildren(recycler, i10, i11);
                        return;
                    }
                }
                return;
            }
            for (int i12 = 0; i12 < childCount; i12++) {
                View childAt2 = getChildAt(i12);
                if (this.mOrientationHelper.getDecoratedEnd(childAt2) > i8 || this.mOrientationHelper.getTransformedEndWithDecoration(childAt2) > i8) {
                    recycleChildren(recycler, 0, i12);
                    return;
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

    private boolean updateAnchorFromChildren(RecyclerView.Recycler recycler, RecyclerView.State state, AnchorInfo anchorInfo) {
        View findReferenceChild;
        boolean z;
        boolean z3 = false;
        if (getChildCount() == 0) {
            return false;
        }
        View focusedChild = getFocusedChild();
        if (focusedChild == null || !anchorInfo.isViewValidAsAnchor(focusedChild, state)) {
            boolean z7 = this.mLastStackFromEnd;
            boolean z9 = this.mStackFromEnd;
            if (z7 != z9 || (findReferenceChild = findReferenceChild(recycler, state, anchorInfo.mLayoutFromEnd, z9)) == null) {
                return false;
            }
            anchorInfo.assignFromView(findReferenceChild, getPosition(findReferenceChild));
            if (!state.isPreLayout() && supportsPredictiveItemAnimations()) {
                int decoratedStart = this.mOrientationHelper.getDecoratedStart(findReferenceChild);
                int decoratedEnd = this.mOrientationHelper.getDecoratedEnd(findReferenceChild);
                int startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
                int endAfterPadding = this.mOrientationHelper.getEndAfterPadding();
                if (decoratedEnd > startAfterPadding || decoratedStart >= startAfterPadding) {
                    z = false;
                } else {
                    z = true;
                }
                if (decoratedStart >= endAfterPadding && decoratedEnd > endAfterPadding) {
                    z3 = true;
                }
                if (z || z3) {
                    if (anchorInfo.mLayoutFromEnd) {
                        startAfterPadding = endAfterPadding;
                    }
                    anchorInfo.mCoordinate = startAfterPadding;
                }
            }
            return true;
        }
        anchorInfo.assignFromViewAndKeepVisibleRect(focusedChild, getPosition(focusedChild));
        return true;
    }

    private boolean updateAnchorFromPendingData(RecyclerView.State state, AnchorInfo anchorInfo) {
        int i2;
        boolean z;
        int i7;
        boolean z3 = false;
        if (!state.isPreLayout() && (i2 = this.mPendingScrollPosition) != -1) {
            if (i2 < 0 || i2 >= state.getItemCount()) {
                this.mPendingScrollPosition = -1;
                this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
            } else {
                anchorInfo.mPosition = this.mPendingScrollPosition;
                SavedState savedState = this.mPendingSavedState;
                if (savedState != null && savedState.hasValidAnchor()) {
                    boolean z7 = this.mPendingSavedState.mAnchorLayoutFromEnd;
                    anchorInfo.mLayoutFromEnd = z7;
                    if (z7) {
                        anchorInfo.mCoordinate = this.mOrientationHelper.getEndAfterPadding() - this.mPendingSavedState.mAnchorOffset;
                    } else {
                        anchorInfo.mCoordinate = this.mOrientationHelper.getStartAfterPadding() + this.mPendingSavedState.mAnchorOffset;
                    }
                    return true;
                } else if (this.mPendingScrollPositionOffset == Integer.MIN_VALUE) {
                    View findViewByPosition = findViewByPosition(this.mPendingScrollPosition);
                    if (findViewByPosition == null) {
                        if (getChildCount() > 0) {
                            if (this.mPendingScrollPosition < getPosition(getChildAt(0))) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (z == this.mShouldReverseLayout) {
                                z3 = true;
                            }
                            anchorInfo.mLayoutFromEnd = z3;
                        }
                        anchorInfo.assignCoordinateFromPadding();
                    } else if (this.mOrientationHelper.getDecoratedMeasurement(findViewByPosition) > this.mOrientationHelper.getTotalSpace()) {
                        anchorInfo.assignCoordinateFromPadding();
                        return true;
                    } else if (this.mOrientationHelper.getDecoratedStart(findViewByPosition) - this.mOrientationHelper.getStartAfterPadding() < 0) {
                        anchorInfo.mCoordinate = this.mOrientationHelper.getStartAfterPadding();
                        anchorInfo.mLayoutFromEnd = false;
                        return true;
                    } else if (this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(findViewByPosition) < 0) {
                        anchorInfo.mCoordinate = this.mOrientationHelper.getEndAfterPadding();
                        anchorInfo.mLayoutFromEnd = true;
                        return true;
                    } else {
                        if (anchorInfo.mLayoutFromEnd) {
                            i7 = this.mOrientationHelper.getTotalSpaceChange() + this.mOrientationHelper.getDecoratedEnd(findViewByPosition);
                        } else {
                            i7 = this.mOrientationHelper.getDecoratedStart(findViewByPosition);
                        }
                        anchorInfo.mCoordinate = i7;
                    }
                    return true;
                } else {
                    boolean z9 = this.mShouldReverseLayout;
                    anchorInfo.mLayoutFromEnd = z9;
                    if (z9) {
                        anchorInfo.mCoordinate = this.mOrientationHelper.getEndAfterPadding() - this.mPendingScrollPositionOffset;
                    } else {
                        anchorInfo.mCoordinate = this.mOrientationHelper.getStartAfterPadding() + this.mPendingScrollPositionOffset;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private void updateAnchorInfoForLayout(RecyclerView.Recycler recycler, RecyclerView.State state, AnchorInfo anchorInfo) {
        int i2;
        if (!updateAnchorFromPendingData(state, anchorInfo) && !updateAnchorFromChildren(recycler, state, anchorInfo)) {
            anchorInfo.assignCoordinateFromPadding();
            if (this.mStackFromEnd) {
                i2 = state.getItemCount() - 1;
            } else {
                i2 = 0;
            }
            anchorInfo.mPosition = i2;
        }
    }

    private void updateLayoutState(int i2, int i7, boolean z, RecyclerView.State state) {
        int i8;
        int i10;
        this.mLayoutState.mInfinite = resolveIsInfinite();
        this.mLayoutState.mLayoutDirection = i2;
        int[] iArr = this.mReusableIntPair;
        boolean z3 = false;
        iArr[0] = 0;
        int i11 = 1;
        iArr[1] = 0;
        calculateExtraLayoutSpace(state, iArr);
        int max = Math.max(0, this.mReusableIntPair[0]);
        int max2 = Math.max(0, this.mReusableIntPair[1]);
        if (i2 == 1) {
            z3 = true;
        }
        LayoutState layoutState = this.mLayoutState;
        if (z3) {
            i8 = max2;
        } else {
            i8 = max;
        }
        layoutState.mExtraFillSpace = i8;
        if (!z3) {
            max = max2;
        }
        layoutState.mNoRecycleSpace = max;
        if (z3) {
            layoutState.mExtraFillSpace = this.mOrientationHelper.getEndPadding() + i8;
            View childClosestToEnd = getChildClosestToEnd();
            LayoutState layoutState2 = this.mLayoutState;
            if (this.mShouldReverseLayout) {
                i11 = -1;
            }
            layoutState2.mItemDirection = i11;
            int position = getPosition(childClosestToEnd);
            LayoutState layoutState3 = this.mLayoutState;
            layoutState2.mCurrentPosition = position + layoutState3.mItemDirection;
            layoutState3.mOffset = this.mOrientationHelper.getDecoratedEnd(childClosestToEnd);
            i10 = this.mOrientationHelper.getDecoratedEnd(childClosestToEnd) - this.mOrientationHelper.getEndAfterPadding();
        } else {
            View childClosestToStart = getChildClosestToStart();
            LayoutState layoutState4 = this.mLayoutState;
            layoutState4.mExtraFillSpace = this.mOrientationHelper.getStartAfterPadding() + layoutState4.mExtraFillSpace;
            LayoutState layoutState5 = this.mLayoutState;
            if (!this.mShouldReverseLayout) {
                i11 = -1;
            }
            layoutState5.mItemDirection = i11;
            int position2 = getPosition(childClosestToStart);
            LayoutState layoutState6 = this.mLayoutState;
            layoutState5.mCurrentPosition = position2 + layoutState6.mItemDirection;
            layoutState6.mOffset = this.mOrientationHelper.getDecoratedStart(childClosestToStart);
            i10 = (-this.mOrientationHelper.getDecoratedStart(childClosestToStart)) + this.mOrientationHelper.getStartAfterPadding();
        }
        LayoutState layoutState7 = this.mLayoutState;
        layoutState7.mAvailable = i7;
        if (z) {
            layoutState7.mAvailable = i7 - i10;
        }
        layoutState7.mScrollingOffset = i10;
    }

    private void updateLayoutStateToFillEnd(AnchorInfo anchorInfo) {
        updateLayoutStateToFillEnd(anchorInfo.mPosition, anchorInfo.mCoordinate);
    }

    private void updateLayoutStateToFillStart(AnchorInfo anchorInfo) {
        updateLayoutStateToFillStart(anchorInfo.mPosition, anchorInfo.mCoordinate);
    }

    public void assertNotInLayoutOrScroll(String str) {
        if (this.mPendingSavedState == null) {
            super.assertNotInLayoutOrScroll(str);
        }
    }

    public void calculateExtraLayoutSpace(RecyclerView.State state, int[] iArr) {
        int i2;
        int extraLayoutSpace = getExtraLayoutSpace(state);
        if (this.mLayoutState.mLayoutDirection == -1) {
            i2 = 0;
        } else {
            i2 = extraLayoutSpace;
            extraLayoutSpace = 0;
        }
        iArr[0] = extraLayoutSpace;
        iArr[1] = i2;
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

    public void collectAdjacentPrefetchPositions(int i2, int i7, RecyclerView.State state, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int i8;
        if (this.mOrientation != 0) {
            i2 = i7;
        }
        if (getChildCount() != 0 && i2 != 0) {
            ensureLayoutState();
            if (i2 > 0) {
                i8 = 1;
            } else {
                i8 = -1;
            }
            updateLayoutState(i8, Math.abs(i2), true, state);
            collectPrefetchPositionsForLayoutState(state, this.mLayoutState, layoutPrefetchRegistry);
        }
    }

    public void collectInitialPrefetchPositions(int i2, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        boolean z;
        int i7;
        SavedState savedState = this.mPendingSavedState;
        int i8 = -1;
        if (savedState == null || !savedState.hasValidAnchor()) {
            resolveShouldLayoutReverse();
            z = this.mShouldReverseLayout;
            i7 = this.mPendingScrollPosition;
            if (i7 == -1) {
                if (z) {
                    i7 = i2 - 1;
                } else {
                    i7 = 0;
                }
            }
        } else {
            SavedState savedState2 = this.mPendingSavedState;
            z = savedState2.mAnchorLayoutFromEnd;
            i7 = savedState2.mAnchorPosition;
        }
        if (!z) {
            i8 = 1;
        }
        for (int i10 = 0; i10 < this.mInitialPrefetchItemCount && i7 >= 0 && i7 < i2; i10++) {
            layoutPrefetchRegistry.addPosition(i7, 0);
            i7 += i8;
        }
    }

    public void collectPrefetchPositionsForLayoutState(RecyclerView.State state, LayoutState layoutState, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int i2 = layoutState.mCurrentPosition;
        if (i2 >= 0 && i2 < state.getItemCount()) {
            layoutPrefetchRegistry.addPosition(i2, Math.max(0, layoutState.mScrollingOffset));
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
        if (getChildCount() == 0) {
            return null;
        }
        boolean z = false;
        int i7 = 1;
        if (i2 < getPosition(getChildAt(0))) {
            z = true;
        }
        if (z != this.mShouldReverseLayout) {
            i7 = -1;
        }
        if (this.mOrientation == 0) {
            return new PointF((float) i7, 0.0f);
        }
        return new PointF(0.0f, (float) i7);
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

    public int convertFocusDirectionToLayoutDirection(int i2) {
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

    public LayoutState createLayoutState() {
        return new LayoutState();
    }

    public void ensureLayoutState() {
        if (this.mLayoutState == null) {
            this.mLayoutState = createLayoutState();
        }
    }

    public int fill(RecyclerView.Recycler recycler, LayoutState layoutState, RecyclerView.State state, boolean z) {
        int i2 = layoutState.mAvailable;
        int i7 = layoutState.mScrollingOffset;
        if (i7 != Integer.MIN_VALUE) {
            if (i2 < 0) {
                layoutState.mScrollingOffset = i7 + i2;
            }
            recycleByLayoutState(recycler, layoutState);
        }
        int i8 = layoutState.mAvailable + layoutState.mExtraFillSpace;
        LayoutChunkResult layoutChunkResult = this.mLayoutChunkResult;
        while (true) {
            if ((!layoutState.mInfinite && i8 <= 0) || !layoutState.hasMore(state)) {
                break;
            }
            layoutChunkResult.resetInternal();
            layoutChunk(recycler, state, layoutState, layoutChunkResult);
            if (!layoutChunkResult.mFinished) {
                layoutState.mOffset = (layoutChunkResult.mConsumed * layoutState.mLayoutDirection) + layoutState.mOffset;
                if (!layoutChunkResult.mIgnoreConsumed || layoutState.mScrapList != null || !state.isPreLayout()) {
                    int i10 = layoutState.mAvailable;
                    int i11 = layoutChunkResult.mConsumed;
                    layoutState.mAvailable = i10 - i11;
                    i8 -= i11;
                }
                int i12 = layoutState.mScrollingOffset;
                if (i12 != Integer.MIN_VALUE) {
                    int i13 = i12 + layoutChunkResult.mConsumed;
                    layoutState.mScrollingOffset = i13;
                    int i14 = layoutState.mAvailable;
                    if (i14 < 0) {
                        layoutState.mScrollingOffset = i13 + i14;
                    }
                    recycleByLayoutState(recycler, layoutState);
                }
                if (z && layoutChunkResult.mFocusable) {
                    break;
                }
            } else {
                break;
            }
        }
        return i2 - layoutState.mAvailable;
    }

    public int findFirstCompletelyVisibleItemPosition() {
        View findOneVisibleChild = findOneVisibleChild(0, getChildCount(), true, false);
        if (findOneVisibleChild == null) {
            return -1;
        }
        return getPosition(findOneVisibleChild);
    }

    public View findFirstVisibleChildClosestToEnd(boolean z, boolean z3) {
        if (this.mShouldReverseLayout) {
            return findOneVisibleChild(0, getChildCount(), z, z3);
        }
        return findOneVisibleChild(getChildCount() - 1, -1, z, z3);
    }

    public View findFirstVisibleChildClosestToStart(boolean z, boolean z3) {
        if (this.mShouldReverseLayout) {
            return findOneVisibleChild(getChildCount() - 1, -1, z, z3);
        }
        return findOneVisibleChild(0, getChildCount(), z, z3);
    }

    public int findFirstVisibleItemPosition() {
        View findOneVisibleChild = findOneVisibleChild(0, getChildCount(), false, true);
        if (findOneVisibleChild == null) {
            return -1;
        }
        return getPosition(findOneVisibleChild);
    }

    public int findLastCompletelyVisibleItemPosition() {
        View findOneVisibleChild = findOneVisibleChild(getChildCount() - 1, -1, true, false);
        if (findOneVisibleChild == null) {
            return -1;
        }
        return getPosition(findOneVisibleChild);
    }

    public int findLastVisibleItemPosition() {
        View findOneVisibleChild = findOneVisibleChild(getChildCount() - 1, -1, false, true);
        if (findOneVisibleChild == null) {
            return -1;
        }
        return getPosition(findOneVisibleChild);
    }

    public View findOnePartiallyOrCompletelyInvisibleChild(int i2, int i7) {
        int i8;
        int i10;
        ensureLayoutState();
        if (i7 <= i2 && i7 >= i2) {
            return getChildAt(i2);
        }
        if (this.mOrientationHelper.getDecoratedStart(getChildAt(i2)) < this.mOrientationHelper.getStartAfterPadding()) {
            i10 = 16644;
            i8 = 16388;
        } else {
            i10 = 4161;
            i8 = 4097;
        }
        if (this.mOrientation == 0) {
            return this.mHorizontalBoundCheck.findOneViewWithinBoundFlags(i2, i7, i10, i8);
        }
        return this.mVerticalBoundCheck.findOneViewWithinBoundFlags(i2, i7, i10, i8);
    }

    public View findOneVisibleChild(int i2, int i7, boolean z, boolean z3) {
        int i8;
        ensureLayoutState();
        int i10 = ThumbKind.MEDIUM_KIND_SIZE;
        if (z) {
            i8 = 24579;
        } else {
            i8 = 320;
        }
        if (!z3) {
            i10 = 0;
        }
        if (this.mOrientation == 0) {
            return this.mHorizontalBoundCheck.findOneViewWithinBoundFlags(i2, i7, i8, i10);
        }
        return this.mVerticalBoundCheck.findOneViewWithinBoundFlags(i2, i7, i8, i10);
    }

    public View findReferenceChild(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z, boolean z3) {
        int i2;
        int i7;
        int i8;
        boolean z7;
        boolean z9;
        ensureLayoutState();
        int max = Math.max(getChildCount(), 0);
        if (z3) {
            i8 = max - 1;
            i7 = -1;
            i2 = -1;
        } else {
            i7 = max;
            i8 = 0;
            i2 = 1;
        }
        int itemCount = state.getItemCount();
        int startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
        int endAfterPadding = this.mOrientationHelper.getEndAfterPadding();
        View view = null;
        View view2 = null;
        View view3 = null;
        while (i8 != i7) {
            View childAt = getChildAt(i8);
            int position = getPosition(childAt);
            if (position >= 0 && position < itemCount) {
                int decoratedStart = this.mOrientationHelper.getDecoratedStart(childAt);
                int decoratedEnd = this.mOrientationHelper.getDecoratedEnd(childAt);
                if (!((RecyclerView.LayoutParams) childAt.getLayoutParams()).isItemRemoved()) {
                    if (decoratedEnd > startAfterPadding || decoratedStart >= startAfterPadding) {
                        z7 = false;
                    } else {
                        z7 = true;
                    }
                    if (decoratedStart < endAfterPadding || decoratedEnd <= endAfterPadding) {
                        z9 = false;
                    } else {
                        z9 = true;
                    }
                    if (!z7 && !z9) {
                        return childAt;
                    }
                    if (z) {
                        if (!z9) {
                            if (view != null) {
                            }
                            view = childAt;
                        }
                    } else if (!z7) {
                        if (view != null) {
                        }
                        view = childAt;
                    }
                    view2 = childAt;
                } else if (view3 == null) {
                    view3 = childAt;
                }
            }
            i8 += i2;
        }
        if (view != null) {
            return view;
        }
        if (view2 != null) {
            return view2;
        }
        return view3;
    }

    public View findViewByPosition(int i2) {
        int childCount = getChildCount();
        if (childCount == 0) {
            return null;
        }
        int position = i2 - getPosition(getChildAt(0));
        if (position >= 0 && position < childCount) {
            View childAt = getChildAt(position);
            if (getPosition(childAt) == i2) {
                return childAt;
            }
        }
        return super.findViewByPosition(i2);
    }

    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    @Deprecated
    public int getExtraLayoutSpace(RecyclerView.State state) {
        if (state.hasTargetScrollPosition()) {
            return this.mOrientationHelper.getTotalSpace();
        }
        return 0;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public boolean getReverseLayout() {
        return this.mReverseLayout;
    }

    public boolean isAutoMeasureEnabled() {
        return true;
    }

    public boolean isLayoutRTL() {
        if (getLayoutDirection() == 1) {
            return true;
        }
        return false;
    }

    public boolean isSmoothScrollbarEnabled() {
        return this.mSmoothScrollbarEnabled;
    }

    public void layoutChunk(RecyclerView.Recycler recycler, RecyclerView.State state, LayoutState layoutState, LayoutChunkResult layoutChunkResult) {
        int i2;
        int i7;
        int i8;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        boolean z;
        boolean z3;
        View next = layoutState.next(recycler);
        if (next == null) {
            layoutChunkResult.mFinished = true;
            return;
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) next.getLayoutParams();
        if (layoutState.mScrapList == null) {
            boolean z7 = this.mShouldReverseLayout;
            if (layoutState.mLayoutDirection == -1) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (z7 == z3) {
                addView(next);
            } else {
                addView(next, 0);
            }
        } else {
            boolean z9 = this.mShouldReverseLayout;
            if (layoutState.mLayoutDirection == -1) {
                z = true;
            } else {
                z = false;
            }
            if (z9 == z) {
                addDisappearingView(next);
            } else {
                addDisappearingView(next, 0);
            }
        }
        measureChildWithMargins(next, 0, 0);
        layoutChunkResult.mConsumed = this.mOrientationHelper.getDecoratedMeasurement(next);
        if (this.mOrientation == 1) {
            if (isLayoutRTL()) {
                i12 = getWidth() - getPaddingRight();
                i11 = i12 - this.mOrientationHelper.getDecoratedMeasurementInOther(next);
            } else {
                i11 = getPaddingLeft();
                i12 = this.mOrientationHelper.getDecoratedMeasurementInOther(next) + i11;
            }
            if (layoutState.mLayoutDirection == -1) {
                i14 = layoutState.mOffset;
                i13 = i14 - layoutChunkResult.mConsumed;
            } else {
                i13 = layoutState.mOffset;
                i14 = layoutChunkResult.mConsumed + i13;
            }
            int i15 = i11;
            i10 = i13;
            i8 = i15;
            i7 = i14;
            i2 = i12;
        } else {
            int paddingTop = getPaddingTop();
            int decoratedMeasurementInOther = this.mOrientationHelper.getDecoratedMeasurementInOther(next) + paddingTop;
            if (layoutState.mLayoutDirection == -1) {
                int i16 = layoutState.mOffset;
                i8 = i16 - layoutChunkResult.mConsumed;
                i2 = i16;
                i7 = decoratedMeasurementInOther;
            } else {
                int i17 = layoutState.mOffset;
                i2 = layoutChunkResult.mConsumed + i17;
                i7 = decoratedMeasurementInOther;
                i8 = i17;
            }
            i10 = paddingTop;
        }
        layoutDecoratedWithMargins(next, i8, i10, i2, i7);
        if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
            layoutChunkResult.mIgnoreConsumed = true;
        }
        layoutChunkResult.mFocusable = next.hasFocusable();
    }

    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        if (this.mRecycleChildrenOnDetach) {
            removeAndRecycleAllViews(recycler);
            recycler.clear();
        }
    }

    public View onFocusSearchFailed(View view, int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int convertFocusDirectionToLayoutDirection;
        View view2;
        View view3;
        resolveShouldLayoutReverse();
        if (getChildCount() == 0 || (convertFocusDirectionToLayoutDirection = convertFocusDirectionToLayoutDirection(i2)) == Integer.MIN_VALUE) {
            return null;
        }
        ensureLayoutState();
        updateLayoutState(convertFocusDirectionToLayoutDirection, (int) (((float) this.mOrientationHelper.getTotalSpace()) * 0.33333334f), false, state);
        LayoutState layoutState = this.mLayoutState;
        layoutState.mScrollingOffset = Integer.MIN_VALUE;
        layoutState.mRecycle = false;
        fill(recycler, layoutState, state, true);
        if (convertFocusDirectionToLayoutDirection == -1) {
            view2 = findPartiallyOrCompletelyInvisibleChildClosestToStart();
        } else {
            view2 = findPartiallyOrCompletelyInvisibleChildClosestToEnd();
        }
        if (convertFocusDirectionToLayoutDirection == -1) {
            view3 = getChildClosestToStart();
        } else {
            view3 = getChildClosestToEnd();
        }
        if (!view3.hasFocusable()) {
            return view2;
        }
        if (view2 == null) {
            return null;
        }
        return view3;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (getChildCount() > 0) {
            accessibilityEvent.setFromIndex(findFirstVisibleItemPosition());
            accessibilityEvent.setToIndex(findLastVisibleItemPosition());
        }
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i2;
        int i7;
        int i8;
        int i10;
        int i11;
        int fixLayoutEndGap;
        int i12;
        View findViewByPosition;
        int decoratedStart;
        int i13;
        int i14 = -1;
        if (!(this.mPendingSavedState == null && this.mPendingScrollPosition == -1) && state.getItemCount() == 0) {
            removeAndRecycleAllViews(recycler);
            return;
        }
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null && savedState.hasValidAnchor()) {
            this.mPendingScrollPosition = this.mPendingSavedState.mAnchorPosition;
        }
        ensureLayoutState();
        this.mLayoutState.mRecycle = false;
        resolveShouldLayoutReverse();
        View focusedChild = getFocusedChild();
        AnchorInfo anchorInfo = this.mAnchorInfo;
        if (!anchorInfo.mValid || this.mPendingScrollPosition != -1 || this.mPendingSavedState != null) {
            anchorInfo.reset();
            AnchorInfo anchorInfo2 = this.mAnchorInfo;
            anchorInfo2.mLayoutFromEnd = this.mShouldReverseLayout ^ this.mStackFromEnd;
            updateAnchorInfoForLayout(recycler, state, anchorInfo2);
            this.mAnchorInfo.mValid = true;
        } else if (focusedChild != null && (this.mOrientationHelper.getDecoratedStart(focusedChild) >= this.mOrientationHelper.getEndAfterPadding() || this.mOrientationHelper.getDecoratedEnd(focusedChild) <= this.mOrientationHelper.getStartAfterPadding())) {
            this.mAnchorInfo.assignFromViewAndKeepVisibleRect(focusedChild, getPosition(focusedChild));
        }
        LayoutState layoutState = this.mLayoutState;
        if (layoutState.mLastScrollDelta >= 0) {
            i2 = 1;
        } else {
            i2 = -1;
        }
        layoutState.mLayoutDirection = i2;
        int[] iArr = this.mReusableIntPair;
        iArr[0] = 0;
        iArr[1] = 0;
        calculateExtraLayoutSpace(state, iArr);
        int startAfterPadding = this.mOrientationHelper.getStartAfterPadding() + Math.max(0, this.mReusableIntPair[0]);
        int endPadding = this.mOrientationHelper.getEndPadding() + Math.max(0, this.mReusableIntPair[1]);
        if (!(!state.isPreLayout() || (i12 = this.mPendingScrollPosition) == -1 || this.mPendingScrollPositionOffset == Integer.MIN_VALUE || (findViewByPosition = findViewByPosition(i12)) == null)) {
            if (this.mShouldReverseLayout) {
                i13 = this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(findViewByPosition);
                decoratedStart = this.mPendingScrollPositionOffset;
            } else {
                decoratedStart = this.mOrientationHelper.getDecoratedStart(findViewByPosition) - this.mOrientationHelper.getStartAfterPadding();
                i13 = this.mPendingScrollPositionOffset;
            }
            int i15 = i13 - decoratedStart;
            if (i15 > 0) {
                startAfterPadding += i15;
            } else {
                endPadding -= i15;
            }
        }
        AnchorInfo anchorInfo3 = this.mAnchorInfo;
        if (!anchorInfo3.mLayoutFromEnd ? !this.mShouldReverseLayout : this.mShouldReverseLayout) {
            i14 = 1;
        }
        onAnchorReady(recycler, state, anchorInfo3, i14);
        detachAndScrapAttachedViews(recycler);
        this.mLayoutState.mInfinite = resolveIsInfinite();
        this.mLayoutState.mIsPreLayout = state.isPreLayout();
        this.mLayoutState.mNoRecycleSpace = 0;
        AnchorInfo anchorInfo4 = this.mAnchorInfo;
        if (anchorInfo4.mLayoutFromEnd) {
            updateLayoutStateToFillStart(anchorInfo4);
            LayoutState layoutState2 = this.mLayoutState;
            layoutState2.mExtraFillSpace = startAfterPadding;
            fill(recycler, layoutState2, state, false);
            LayoutState layoutState3 = this.mLayoutState;
            i8 = layoutState3.mOffset;
            int i16 = layoutState3.mCurrentPosition;
            int i17 = layoutState3.mAvailable;
            if (i17 > 0) {
                endPadding += i17;
            }
            updateLayoutStateToFillEnd(this.mAnchorInfo);
            LayoutState layoutState4 = this.mLayoutState;
            layoutState4.mExtraFillSpace = endPadding;
            layoutState4.mCurrentPosition += layoutState4.mItemDirection;
            fill(recycler, layoutState4, state, false);
            LayoutState layoutState5 = this.mLayoutState;
            i7 = layoutState5.mOffset;
            int i18 = layoutState5.mAvailable;
            if (i18 > 0) {
                updateLayoutStateToFillStart(i16, i8);
                LayoutState layoutState6 = this.mLayoutState;
                layoutState6.mExtraFillSpace = i18;
                fill(recycler, layoutState6, state, false);
                i8 = this.mLayoutState.mOffset;
            }
        } else {
            updateLayoutStateToFillEnd(anchorInfo4);
            LayoutState layoutState7 = this.mLayoutState;
            layoutState7.mExtraFillSpace = endPadding;
            fill(recycler, layoutState7, state, false);
            LayoutState layoutState8 = this.mLayoutState;
            i7 = layoutState8.mOffset;
            int i19 = layoutState8.mCurrentPosition;
            int i20 = layoutState8.mAvailable;
            if (i20 > 0) {
                startAfterPadding += i20;
            }
            updateLayoutStateToFillStart(this.mAnchorInfo);
            LayoutState layoutState9 = this.mLayoutState;
            layoutState9.mExtraFillSpace = startAfterPadding;
            layoutState9.mCurrentPosition += layoutState9.mItemDirection;
            fill(recycler, layoutState9, state, false);
            LayoutState layoutState10 = this.mLayoutState;
            int i21 = layoutState10.mOffset;
            int i22 = layoutState10.mAvailable;
            if (i22 > 0) {
                updateLayoutStateToFillEnd(i19, i7);
                LayoutState layoutState11 = this.mLayoutState;
                layoutState11.mExtraFillSpace = i22;
                fill(recycler, layoutState11, state, false);
                i7 = this.mLayoutState.mOffset;
            }
            i8 = i21;
        }
        if (getChildCount() > 0) {
            if (this.mShouldReverseLayout ^ this.mStackFromEnd) {
                int fixLayoutEndGap2 = fixLayoutEndGap(i7, recycler, state, true);
                i10 = i8 + fixLayoutEndGap2;
                i11 = i7 + fixLayoutEndGap2;
                fixLayoutEndGap = fixLayoutStartGap(i10, recycler, state, false);
            } else {
                int fixLayoutStartGap = fixLayoutStartGap(i8, recycler, state, true);
                i10 = i8 + fixLayoutStartGap;
                i11 = i7 + fixLayoutStartGap;
                fixLayoutEndGap = fixLayoutEndGap(i11, recycler, state, false);
            }
            i8 = i10 + fixLayoutEndGap;
            i7 = i11 + fixLayoutEndGap;
        }
        layoutForPredictiveAnimations(recycler, state, i8, i7);
        if (!state.isPreLayout()) {
            this.mOrientationHelper.onLayoutComplete();
        } else {
            this.mAnchorInfo.reset();
        }
        this.mLastStackFromEnd = this.mStackFromEnd;
    }

    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        this.mPendingSavedState = null;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mAnchorInfo.reset();
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.mPendingSavedState = savedState;
            if (this.mPendingScrollPosition != -1) {
                savedState.invalidateAnchor();
            }
            requestLayout();
        }
    }

    public Parcelable onSaveInstanceState() {
        if (this.mPendingSavedState != null) {
            return new SavedState(this.mPendingSavedState);
        }
        SavedState savedState = new SavedState();
        if (getChildCount() > 0) {
            ensureLayoutState();
            boolean z = this.mLastStackFromEnd ^ this.mShouldReverseLayout;
            savedState.mAnchorLayoutFromEnd = z;
            if (z) {
                View childClosestToEnd = getChildClosestToEnd();
                savedState.mAnchorOffset = this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(childClosestToEnd);
                savedState.mAnchorPosition = getPosition(childClosestToEnd);
                return savedState;
            }
            View childClosestToStart = getChildClosestToStart();
            savedState.mAnchorPosition = getPosition(childClosestToStart);
            savedState.mAnchorOffset = this.mOrientationHelper.getDecoratedStart(childClosestToStart) - this.mOrientationHelper.getStartAfterPadding();
            return savedState;
        }
        savedState.invalidateAnchor();
        return savedState;
    }

    public void prepareForDrop(View view, View view2, int i2, int i7) {
        boolean z;
        assertNotInLayoutOrScroll("Cannot drop a view during a scroll or layout calculation");
        ensureLayoutState();
        resolveShouldLayoutReverse();
        int position = getPosition(view);
        int position2 = getPosition(view2);
        if (position < position2) {
            z = true;
        } else {
            z = true;
        }
        if (this.mShouldReverseLayout) {
            if (z) {
                scrollToPositionWithOffset(position2, this.mOrientationHelper.getEndAfterPadding() - (this.mOrientationHelper.getDecoratedMeasurement(view) + this.mOrientationHelper.getDecoratedStart(view2)));
                return;
            }
            scrollToPositionWithOffset(position2, this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(view2));
        } else if (z) {
            scrollToPositionWithOffset(position2, this.mOrientationHelper.getDecoratedStart(view2) - this.mOrientationHelper.getStartAfterPadding());
        } else {
            scrollToPositionWithOffset(position2, (this.mOrientationHelper.getDecoratedEnd(view2) - this.mOrientationHelper.getDecoratedMeasurement(view)) - this.mOrientationHelper.getStartAfterPadding());
        }
    }

    public boolean resolveIsInfinite() {
        if (this.mOrientationHelper.getMode() == 0 && this.mOrientationHelper.getEnd() == 0) {
            return true;
        }
        return false;
    }

    public int scrollBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i7;
        if (getChildCount() == 0 || i2 == 0) {
            return 0;
        }
        ensureLayoutState();
        this.mLayoutState.mRecycle = true;
        if (i2 > 0) {
            i7 = 1;
        } else {
            i7 = -1;
        }
        int abs = Math.abs(i2);
        updateLayoutState(i7, abs, true, state);
        LayoutState layoutState = this.mLayoutState;
        int fill = fill(recycler, layoutState, state, false) + layoutState.mScrollingOffset;
        if (fill < 0) {
            return 0;
        }
        if (abs > fill) {
            i2 = i7 * fill;
        }
        this.mOrientationHelper.offsetChildren(-i2);
        this.mLayoutState.mLastScrollDelta = i2;
        if (state.mLayoutStep != 2) {
            this.mRecyclerView.showGoToTop();
        }
        return i2;
    }

    public int scrollHorizontallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation == 1) {
            return 0;
        }
        return scrollBy(i2, recycler, state);
    }

    public void scrollToPosition(int i2) {
        this.mPendingScrollPosition = i2;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null) {
            savedState.invalidateAnchor();
        }
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.showGoToTop();
        }
        requestLayout();
    }

    public void scrollToPositionWithOffset(int i2, int i7) {
        this.mPendingScrollPosition = i2;
        this.mPendingScrollPositionOffset = i7;
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null) {
            savedState.invalidateAnchor();
        }
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.showGoToTop();
        }
        requestLayout();
    }

    public int scrollVerticallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation == 0) {
            return 0;
        }
        return scrollBy(i2, recycler, state);
    }

    public void setInitialPrefetchItemCount(int i2) {
        this.mInitialPrefetchItemCount = i2;
    }

    public void setOrientation(int i2) {
        if (i2 == 0 || i2 == 1) {
            assertNotInLayoutOrScroll((String) null);
            if (i2 != this.mOrientation || this.mOrientationHelper == null) {
                OrientationHelper createOrientationHelper = OrientationHelper.createOrientationHelper(this, i2);
                this.mOrientationHelper = createOrientationHelper;
                this.mAnchorInfo.mOrientationHelper = createOrientationHelper;
                this.mOrientation = i2;
                requestLayout();
                return;
            }
            return;
        }
        throw new IllegalArgumentException(C0086a.i(i2, "invalid orientation:"));
    }

    public void setReverseLayout(boolean z) {
        assertNotInLayoutOrScroll((String) null);
        if (z != this.mReverseLayout) {
            this.mReverseLayout = z;
            requestLayout();
        }
    }

    public void setSmoothScrollbarEnabled(boolean z) {
        this.mSmoothScrollbarEnabled = z;
    }

    public void setStackFromEnd(boolean z) {
        assertNotInLayoutOrScroll((String) null);
        if (this.mStackFromEnd != z) {
            this.mStackFromEnd = z;
            requestLayout();
        }
    }

    public boolean shouldMeasureTwice() {
        if (getHeightMode() == 1073741824 || getWidthMode() == 1073741824 || !hasFlexibleChildInBothOrientations()) {
            return false;
        }
        return true;
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i2) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        recyclerView.showGoToTop();
        linearSmoothScroller.seslSetAvailableBounds(recyclerView.seslGetAvailableBounds());
        linearSmoothScroller.setTargetPosition(i2);
        startSmoothScroll(linearSmoothScroller);
        C0086a.C(i2, "SS pos to : ", "SeslLinearLayoutManager");
    }

    public void smoothScrollToPositionJumpIfNeeded(RecyclerView recyclerView, RecyclerView.State state, int i2) {
        SmoothScrollerJumpIfNeeded smoothScrollerJumpIfNeeded = new SmoothScrollerJumpIfNeeded(recyclerView.getContext());
        recyclerView.showGoToTop();
        smoothScrollerJumpIfNeeded.seslSetAvailableBounds(recyclerView.seslGetAvailableBounds());
        smoothScrollerJumpIfNeeded.setTargetPosition(i2);
        startSmoothScroll(smoothScrollerJumpIfNeeded);
        Log.d("SeslLinearLayoutManager", "smoothScroller2");
    }

    public boolean supportsPredictiveItemAnimations() {
        if (this.mPendingSavedState == null && this.mLastStackFromEnd == this.mStackFromEnd) {
            return true;
        }
        return false;
    }

    public LinearLayoutManager(Context context, int i2, boolean z) {
        this.mOrientation = 1;
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.mStackFromEnd = false;
        this.mSmoothScrollbarEnabled = true;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPathInterpolator = new PathInterpolator(0.22f, 0.5f, 0.0f, 1.0f);
        this.mPendingSavedState = null;
        this.mAnchorInfo = new AnchorInfo();
        this.mLayoutChunkResult = new LayoutChunkResult();
        this.mInitialPrefetchItemCount = 2;
        this.mReusableIntPair = new int[2];
        setOrientation(i2);
        setReverseLayout(z);
    }

    private void updateLayoutStateToFillEnd(int i2, int i7) {
        this.mLayoutState.mAvailable = this.mOrientationHelper.getEndAfterPadding() - i7;
        LayoutState layoutState = this.mLayoutState;
        layoutState.mItemDirection = this.mShouldReverseLayout ? -1 : 1;
        layoutState.mCurrentPosition = i2;
        layoutState.mLayoutDirection = 1;
        layoutState.mOffset = i7;
        layoutState.mScrollingOffset = Integer.MIN_VALUE;
    }

    private void updateLayoutStateToFillStart(int i2, int i7) {
        this.mLayoutState.mAvailable = i7 - this.mOrientationHelper.getStartAfterPadding();
        LayoutState layoutState = this.mLayoutState;
        layoutState.mCurrentPosition = i2;
        layoutState.mItemDirection = this.mShouldReverseLayout ? 1 : -1;
        layoutState.mLayoutDirection = -1;
        layoutState.mOffset = i7;
        layoutState.mScrollingOffset = Integer.MIN_VALUE;
    }

    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i2, int i7) {
        this.mOrientation = 1;
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.mStackFromEnd = false;
        this.mSmoothScrollbarEnabled = true;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPathInterpolator = new PathInterpolator(0.22f, 0.5f, 0.0f, 1.0f);
        this.mPendingSavedState = null;
        this.mAnchorInfo = new AnchorInfo();
        this.mLayoutChunkResult = new LayoutChunkResult();
        this.mInitialPrefetchItemCount = 2;
        this.mReusableIntPair = new int[2];
        RecyclerView.LayoutManager.Properties properties = RecyclerView.LayoutManager.getProperties(context, attributeSet, i2, i7);
        setOrientation(properties.orientation);
        setReverseLayout(properties.reverseLayout);
        setStackFromEnd(properties.stackFromEnd);
    }

    public void onAnchorReady(RecyclerView.Recycler recycler, RecyclerView.State state, AnchorInfo anchorInfo, int i2) {
    }
}
