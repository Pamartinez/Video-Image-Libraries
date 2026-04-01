package androidx.viewpager2.widget;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import c0.C0086a;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class ScrollEventAdapter extends RecyclerView.OnScrollListener {
    private int mAdapterState;
    private ViewPager2.OnPageChangeCallback mCallback;
    private boolean mDataSetChangeHappened;
    private boolean mDispatchSelected;
    private int mDragStartPosition;
    private boolean mFakeDragging;
    private final LinearLayoutManager mLayoutManager;
    private final RecyclerView mRecyclerView;
    private boolean mScrollHappened;
    private int mScrollState;
    private ScrollEventValues mScrollValues = new ScrollEventValues();
    private int mTarget;
    private final ViewPager2 mViewPager;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ScrollEventValues {
        float mOffset;
        int mOffsetPx;
        int mPosition;

        public void reset() {
            this.mPosition = -1;
            this.mOffset = 0.0f;
            this.mOffsetPx = 0;
        }
    }

    public ScrollEventAdapter(ViewPager2 viewPager2) {
        this.mViewPager = viewPager2;
        RecyclerView recyclerView = viewPager2.mRecyclerView;
        this.mRecyclerView = recyclerView;
        this.mLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        resetState();
    }

    private void dispatchScrolled(int i2, float f, int i7) {
        ViewPager2.OnPageChangeCallback onPageChangeCallback = this.mCallback;
        if (onPageChangeCallback != null) {
            onPageChangeCallback.onPageScrolled(i2, f, i7);
        }
    }

    private void dispatchSelected(int i2) {
        ViewPager2.OnPageChangeCallback onPageChangeCallback = this.mCallback;
        if (onPageChangeCallback != null) {
            onPageChangeCallback.onPageSelected(i2);
        }
    }

    private void dispatchStateChanged(int i2) {
        if ((this.mAdapterState != 3 || this.mScrollState != 0) && this.mScrollState != i2) {
            this.mScrollState = i2;
            ViewPager2.OnPageChangeCallback onPageChangeCallback = this.mCallback;
            if (onPageChangeCallback != null) {
                onPageChangeCallback.onPageScrollStateChanged(i2);
            }
        }
    }

    private int getPosition() {
        return this.mLayoutManager.findFirstVisibleItemPosition();
    }

    private boolean isInAnyDraggingState() {
        int i2 = this.mAdapterState;
        if (i2 == 1 || i2 == 4) {
            return true;
        }
        return false;
    }

    private void resetState() {
        this.mAdapterState = 0;
        this.mScrollState = 0;
        this.mScrollValues.reset();
        this.mDragStartPosition = -1;
        this.mTarget = -1;
        this.mDispatchSelected = false;
        this.mScrollHappened = false;
        this.mFakeDragging = false;
        this.mDataSetChangeHappened = false;
    }

    private void startDrag(boolean z) {
        int i2;
        this.mFakeDragging = z;
        if (z) {
            i2 = 4;
        } else {
            i2 = 1;
        }
        this.mAdapterState = i2;
        int i7 = this.mTarget;
        if (i7 != -1) {
            this.mDragStartPosition = i7;
            this.mTarget = -1;
        } else if (this.mDragStartPosition == -1) {
            this.mDragStartPosition = getPosition();
        }
        dispatchStateChanged(1);
    }

    private void updateScrollEventValues() {
        int i2;
        float f;
        ScrollEventValues scrollEventValues = this.mScrollValues;
        int findFirstVisibleItemPosition = this.mLayoutManager.findFirstVisibleItemPosition();
        scrollEventValues.mPosition = findFirstVisibleItemPosition;
        if (findFirstVisibleItemPosition == -1) {
            scrollEventValues.reset();
            return;
        }
        View findViewByPosition = this.mLayoutManager.findViewByPosition(findFirstVisibleItemPosition);
        if (findViewByPosition == null) {
            scrollEventValues.reset();
            return;
        }
        int leftDecorationWidth = this.mLayoutManager.getLeftDecorationWidth(findViewByPosition);
        int rightDecorationWidth = this.mLayoutManager.getRightDecorationWidth(findViewByPosition);
        int topDecorationHeight = this.mLayoutManager.getTopDecorationHeight(findViewByPosition);
        int bottomDecorationHeight = this.mLayoutManager.getBottomDecorationHeight(findViewByPosition);
        ViewGroup.LayoutParams layoutParams = findViewByPosition.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            leftDecorationWidth += marginLayoutParams.leftMargin;
            rightDecorationWidth += marginLayoutParams.rightMargin;
            topDecorationHeight += marginLayoutParams.topMargin;
            bottomDecorationHeight += marginLayoutParams.bottomMargin;
        }
        int height = findViewByPosition.getHeight() + topDecorationHeight + bottomDecorationHeight;
        int width = findViewByPosition.getWidth() + leftDecorationWidth + rightDecorationWidth;
        if (this.mLayoutManager.getOrientation() == 0) {
            i2 = (findViewByPosition.getLeft() - leftDecorationWidth) - this.mRecyclerView.getPaddingLeft();
            if (this.mViewPager.isRtl()) {
                i2 = -i2;
            }
            height = width;
        } else {
            i2 = (findViewByPosition.getTop() - topDecorationHeight) - this.mRecyclerView.getPaddingTop();
        }
        int i7 = -i2;
        scrollEventValues.mOffsetPx = i7;
        if (i7 >= 0) {
            if (height == 0) {
                f = 0.0f;
            } else {
                f = ((float) i7) / ((float) height);
            }
            scrollEventValues.mOffset = f;
        } else if (new AnimateLayoutChangeDetector(this.mLayoutManager).mayHaveInterferingAnimations()) {
            throw new IllegalStateException("Page(s) contain a ViewGroup with a LayoutTransition (or animateLayoutChanges=\"true\"), which interferes with the scrolling animation. Make sure to call getLayoutTransition().setAnimateParentHierarchy(false) on all ViewGroups with a LayoutTransition before an animation is started.");
        } else {
            Locale locale = Locale.US;
            throw new IllegalStateException(C0086a.i(scrollEventValues.mOffsetPx, "Page can only be offset by a positive amount, not by "));
        }
    }

    public double getRelativeScrollPosition() {
        updateScrollEventValues();
        ScrollEventValues scrollEventValues = this.mScrollValues;
        return ((double) scrollEventValues.mPosition) + ((double) scrollEventValues.mOffset);
    }

    public int getScrollState() {
        return this.mScrollState;
    }

    public boolean isDragging() {
        if (this.mScrollState == 1) {
            return true;
        }
        return false;
    }

    public boolean isFakeDragging() {
        return this.mFakeDragging;
    }

    public boolean isIdle() {
        if (this.mScrollState == 0) {
            return true;
        }
        return false;
    }

    public void notifyBeginFakeDrag() {
        this.mAdapterState = 4;
        startDrag(true);
    }

    public void notifyDataSetChangeHappened() {
        this.mDataSetChangeHappened = true;
    }

    public void notifyEndFakeDrag() {
        if (!isDragging() || this.mFakeDragging) {
            this.mFakeDragging = false;
            updateScrollEventValues();
            ScrollEventValues scrollEventValues = this.mScrollValues;
            if (scrollEventValues.mOffsetPx == 0) {
                int i2 = scrollEventValues.mPosition;
                if (i2 != this.mDragStartPosition) {
                    dispatchSelected(i2);
                }
                dispatchStateChanged(0);
                resetState();
                return;
            }
            dispatchStateChanged(2);
        }
    }

    public void notifyProgrammaticScroll(int i2, boolean z) {
        int i7;
        if (z) {
            i7 = 2;
        } else {
            i7 = 3;
        }
        this.mAdapterState = i7;
        boolean z3 = false;
        this.mFakeDragging = false;
        if (this.mTarget != i2) {
            z3 = true;
        }
        this.mTarget = i2;
        dispatchStateChanged(2);
        if (z3) {
            dispatchSelected(i2);
        }
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
        if (!(this.mAdapterState == 1 && this.mScrollState == 1) && i2 == 1) {
            startDrag(false);
        } else if (!isInAnyDraggingState() || i2 != 2) {
            if (isInAnyDraggingState() && i2 == 0) {
                updateScrollEventValues();
                if (!this.mScrollHappened) {
                    int i7 = this.mScrollValues.mPosition;
                    if (i7 != -1) {
                        dispatchScrolled(i7, 0.0f, 0);
                    }
                } else {
                    ScrollEventValues scrollEventValues = this.mScrollValues;
                    if (scrollEventValues.mOffsetPx == 0) {
                        int i8 = this.mDragStartPosition;
                        int i10 = scrollEventValues.mPosition;
                        if (i8 != i10) {
                            dispatchSelected(i10);
                        }
                    }
                }
                dispatchStateChanged(0);
                resetState();
            }
            if (this.mAdapterState == 2 && i2 == 0 && this.mDataSetChangeHappened) {
                updateScrollEventValues();
                ScrollEventValues scrollEventValues2 = this.mScrollValues;
                if (scrollEventValues2.mOffsetPx == 0) {
                    int i11 = this.mTarget;
                    int i12 = scrollEventValues2.mPosition;
                    if (i11 != i12) {
                        if (i12 == -1) {
                            i12 = 0;
                        }
                        dispatchSelected(i12);
                    }
                    dispatchStateChanged(0);
                    resetState();
                }
            }
        } else if (this.mScrollHappened) {
            dispatchStateChanged(2);
            this.mDispatchSelected = true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001d, code lost:
        if (r5 == r3.mViewPager.isRtl()) goto L_0x001f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0033  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onScrolled(androidx.recyclerview.widget.RecyclerView r4, int r5, int r6) {
        /*
            r3 = this;
            r4 = 1
            r3.mScrollHappened = r4
            r3.updateScrollEventValues()
            boolean r0 = r3.mDispatchSelected
            r1 = -1
            r2 = 0
            if (r0 == 0) goto L_0x0037
            r3.mDispatchSelected = r2
            if (r6 > 0) goto L_0x001f
            if (r6 != 0) goto L_0x0029
            if (r5 >= 0) goto L_0x0016
            r5 = r4
            goto L_0x0017
        L_0x0016:
            r5 = r2
        L_0x0017:
            androidx.viewpager2.widget.ViewPager2 r6 = r3.mViewPager
            boolean r6 = r6.isRtl()
            if (r5 != r6) goto L_0x0029
        L_0x001f:
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r5 = r3.mScrollValues
            int r6 = r5.mOffsetPx
            if (r6 == 0) goto L_0x0029
            int r5 = r5.mPosition
            int r5 = r5 + r4
            goto L_0x002d
        L_0x0029:
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r5 = r3.mScrollValues
            int r5 = r5.mPosition
        L_0x002d:
            r3.mTarget = r5
            int r6 = r3.mDragStartPosition
            if (r6 == r5) goto L_0x0045
            r3.dispatchSelected(r5)
            goto L_0x0045
        L_0x0037:
            int r5 = r3.mAdapterState
            if (r5 != 0) goto L_0x0045
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r5 = r3.mScrollValues
            int r5 = r5.mPosition
            if (r5 != r1) goto L_0x0042
            r5 = r2
        L_0x0042:
            r3.dispatchSelected(r5)
        L_0x0045:
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r5 = r3.mScrollValues
            int r6 = r5.mPosition
            if (r6 != r1) goto L_0x004c
            r6 = r2
        L_0x004c:
            float r0 = r5.mOffset
            int r5 = r5.mOffsetPx
            r3.dispatchScrolled(r6, r0, r5)
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r5 = r3.mScrollValues
            int r6 = r5.mPosition
            int r0 = r3.mTarget
            if (r6 == r0) goto L_0x005d
            if (r0 != r1) goto L_0x006b
        L_0x005d:
            int r5 = r5.mOffsetPx
            if (r5 != 0) goto L_0x006b
            int r5 = r3.mScrollState
            if (r5 == r4) goto L_0x006b
            r3.dispatchStateChanged(r2)
            r3.resetState()
        L_0x006b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager2.widget.ScrollEventAdapter.onScrolled(androidx.recyclerview.widget.RecyclerView, int, int):void");
    }

    public void setOnPageChangeCallback(ViewPager2.OnPageChangeCallback onPageChangeCallback) {
        this.mCallback = onPageChangeCallback;
    }
}
