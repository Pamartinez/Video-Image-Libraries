package com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.reorder;

import B2.h;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.widget.utils.WindowUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AutoScroller {
    private final int mAutoScrollDetectionBoundHeight = ((int) (Resources.getSystem().getDisplayMetrics().density * 56.0f));
    private final Rect mBottomBound = new Rect();
    private final ValueAnimator mDeAccelerator;
    private boolean mInBottomSpot;
    private boolean mInTopSpot;
    private boolean mIsActive;
    /* access modifiers changed from: private */
    public final int mMaxScrollDistance = ((int) (Resources.getSystem().getDisplayMetrics().density * 12.0f));
    /* access modifiers changed from: private */
    public RecyclerView mRecyclerView;
    /* access modifiers changed from: private */
    public int mScrollDistance;
    private boolean mScrollRangeDirty;
    /* access modifiers changed from: private */
    public final Runnable mScrollRunnable = new Runnable() {
        private void closeAppbarByFling() {
            AutoScroller.this.mRecyclerView.fling(0, AutoScroller.this.mRecyclerView.getMinFlingVelocity());
            AutoScroller.this.setScrollRangeDirty();
        }

        private void scrollBy(int i2) {
            int i7;
            if (i2 > 0) {
                i7 = Math.min(i2, AutoScroller.this.mMaxScrollDistance);
            } else {
                i7 = Math.max(i2, -AutoScroller.this.mMaxScrollDistance);
            }
            AutoScroller.this.mRecyclerView.scrollBy(0, i7);
        }

        private boolean scrollDownWhenAppbarIsOpened(int i2) {
            if (AutoScroller.this.mView.getAppBarVisibleHeight() <= 0 || i2 <= 0) {
                return false;
            }
            return true;
        }

        public void run() {
            if (scrollDownWhenAppbarIsOpened(AutoScroller.this.mScrollDistance)) {
                closeAppbarByFling();
            } else {
                scrollBy(AutoScroller.this.mScrollDistance);
            }
            AutoScroller.this.mRecyclerView.postOnAnimation(AutoScroller.this.mScrollRunnable);
        }
    };
    private final Rect mTopBound = new Rect();
    private int mTouchRegionBottomOffset = 0;
    private int mTouchRegionOffset = 0;
    /* access modifiers changed from: private */
    public final IBaseListView mView;

    public AutoScroller(IBaseListView iBaseListView) {
        ValueAnimator valueAnimator = new ValueAnimator();
        this.mDeAccelerator = valueAnimator;
        this.mView = iBaseListView;
        setTouchRegionOffset();
        reset();
        valueAnimator.setDuration(2500);
        valueAnimator.addUpdateListener(new h(17, this));
    }

    private void calculateTopBottomBoundary(RecyclerView recyclerView) {
        int i2;
        if (this.mRecyclerView == null) {
            this.mRecyclerView = recyclerView;
        }
        int height = recyclerView.getHeight();
        Rect rect = this.mTopBound;
        if (this.mView.getToolbar() != null) {
            i2 = this.mView.getToolbar().getHeight();
        } else {
            i2 = 0;
        }
        rect.top = this.mView.getAppBarVisibleHeight() + i2;
        Rect rect2 = this.mTopBound;
        int i7 = rect2.top;
        int i8 = this.mAutoScrollDetectionBoundHeight;
        rect2.bottom = i7 + i8;
        Rect rect3 = this.mBottomBound;
        int i10 = height + this.mTouchRegionBottomOffset;
        rect3.bottom = i10;
        rect3.top = i10 - i8;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(ValueAnimator valueAnimator) {
        this.mScrollDistance = ((Integer) valueAnimator.getAnimatedValue()).intValue();
    }

    private void reset() {
        setActive(false);
        this.mInTopSpot = false;
        this.mInBottomSpot = false;
        stopAutoScroll();
    }

    /* access modifiers changed from: private */
    public void setScrollRangeDirty() {
        this.mScrollRangeDirty = true;
    }

    private void setTouchRegionOffset() {
        IBaseListView iBaseListView = this.mView;
        if (iBaseListView != null) {
            this.mTouchRegionOffset = iBaseListView.getBottomTabHeight() + this.mTouchRegionOffset;
            int systemInsetsBottom = WindowUtils.getSystemInsetsBottom(this.mView.getListView().getRootWindowInsets());
            if (systemInsetsBottom > 0) {
                this.mTouchRegionOffset += systemInsetsBottom;
            }
            if (this.mView.getView() != null && this.mView.getListView() != null) {
                int[] iArr = new int[2];
                this.mView.getListView().getLocationInWindow(iArr);
                int[] iArr2 = new int[2];
                this.mView.getView().getLocationInWindow(iArr2);
                this.mTouchRegionBottomOffset = iArr[1] - iArr2[1];
            }
        }
    }

    private void startAutoScroll() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.removeCallbacks(this.mScrollRunnable);
            this.mRecyclerView.postOnAnimation(this.mScrollRunnable);
        }
    }

    private void stopAutoScroll() {
        this.mInBottomSpot = false;
        this.mInTopSpot = false;
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.removeCallbacks(this.mScrollRunnable);
        }
    }

    public void processAutoScroll(int i2, RecyclerView recyclerView) {
        if (!this.mIsActive) {
            this.mIsActive = true;
            calculateTopBottomBoundary(recyclerView);
        }
        if (this.mScrollRangeDirty) {
            calculateTopBottomBoundary(recyclerView);
        }
        Rect rect = this.mTopBound;
        int i7 = rect.top;
        if (i2 >= i7 && i2 <= rect.bottom) {
            int i8 = this.mAutoScrollDetectionBoundHeight;
            this.mDeAccelerator.cancel();
            this.mScrollDistance = (int) (((float) this.mMaxScrollDistance) * ((((float) i8) - (((float) i2) - ((float) i7))) / ((float) i8)) * -1.0f);
            if (!this.mInTopSpot) {
                this.mInTopSpot = true;
                startAutoScroll();
            }
            this.mDeAccelerator.setIntValues(new int[]{this.mScrollDistance, 0});
            this.mDeAccelerator.start();
        } else if (i2 < i7) {
            this.mDeAccelerator.cancel();
            this.mScrollDistance = this.mMaxScrollDistance * -1;
            if (!this.mInTopSpot) {
                this.mInTopSpot = true;
                startAutoScroll();
            }
            this.mDeAccelerator.setIntValues(new int[]{this.mScrollDistance, 0});
            this.mDeAccelerator.start();
        } else {
            Rect rect2 = this.mBottomBound;
            int i10 = rect2.top;
            if (i2 >= i10 && i2 <= rect2.bottom) {
                float f = (((float) i2) - ((float) i10)) / ((float) this.mAutoScrollDetectionBoundHeight);
                this.mDeAccelerator.cancel();
                this.mScrollDistance = (int) (((float) this.mMaxScrollDistance) * f);
                if (!this.mInBottomSpot) {
                    this.mInBottomSpot = true;
                    startAutoScroll();
                }
                this.mDeAccelerator.setIntValues(new int[]{this.mScrollDistance, 0});
                this.mDeAccelerator.start();
            } else if (i2 > rect2.bottom) {
                this.mDeAccelerator.cancel();
                this.mScrollDistance = this.mMaxScrollDistance;
                if (!this.mInTopSpot) {
                    this.mInTopSpot = true;
                    startAutoScroll();
                }
                this.mDeAccelerator.setIntValues(new int[]{this.mScrollDistance, 0});
                this.mDeAccelerator.start();
            } else {
                this.mInBottomSpot = false;
                this.mInTopSpot = false;
                stopAutoScroll();
            }
        }
    }

    public void setActive(boolean z) {
        this.mIsActive = z;
        if (!z) {
            stopAutoScroll();
        }
    }
}
