package com.samsung.android.gallery.app.ui.list.dragdrop;

import B2.h;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.widget.utils.WindowUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DragAndDropListScroller {
    private final Rect mBottomBound = new Rect();
    private final ValueAnimator mDeAccelerator;
    private final int mDetectionBoundDefaultHeight = ((int) (Resources.getSystem().getDisplayMetrics().density * 56.0f));
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
            DragAndDropListScroller.this.mRecyclerView.fling(0, DragAndDropListScroller.this.mRecyclerView.getMinFlingVelocity());
            DragAndDropListScroller.this.setScrollRangeDirty();
        }

        private void scrollBy(int i2) {
            int i7;
            if (i2 > 0) {
                i7 = Math.min(i2, DragAndDropListScroller.this.mMaxScrollDistance);
            } else {
                i7 = Math.max(i2, -DragAndDropListScroller.this.mMaxScrollDistance);
            }
            DragAndDropListScroller.this.mRecyclerView.scrollBy(0, i7);
        }

        private boolean scrollDownWhenAppbarIsOpened(int i2) {
            if (DragAndDropListScroller.this.mView.getAppBarVisibleHeight() <= 0 || i2 <= 0) {
                return false;
            }
            return true;
        }

        public void run() {
            if (scrollDownWhenAppbarIsOpened(DragAndDropListScroller.this.mScrollDistance)) {
                closeAppbarByFling();
            } else {
                scrollBy(DragAndDropListScroller.this.mScrollDistance);
            }
            DragAndDropListScroller.this.mRecyclerView.postOnAnimation(DragAndDropListScroller.this.mScrollRunnable);
        }
    };
    private final Rect mTopBound = new Rect();
    /* access modifiers changed from: private */
    public final IBaseListView mView;

    public DragAndDropListScroller(IBaseListView iBaseListView) {
        ValueAnimator valueAnimator = new ValueAnimator();
        this.mDeAccelerator = valueAnimator;
        this.mView = iBaseListView;
        reset();
        valueAnimator.setDuration(2500);
        valueAnimator.addUpdateListener(new h(4, this));
    }

    private int calculateBottomBoundFrom() {
        int bottomTabHeight = this.mView.getBottomTabHeight() + this.mDetectionBoundDefaultHeight;
        int systemInsetsBottom = WindowUtils.getSystemInsetsBottom(this.mView.getListView().getRootWindowInsets());
        if (systemInsetsBottom > 0) {
            bottomTabHeight += systemInsetsBottom;
        }
        return this.mBottomBound.bottom - bottomTabHeight;
    }

    private void calculateTopBottomBoundary(RecyclerView recyclerView) {
        if (this.mRecyclerView == null) {
            this.mRecyclerView = recyclerView;
        }
        int height = recyclerView.getHeight();
        this.mTopBound.top = calculateTopBoundFrom();
        this.mTopBound.bottom = calculateTopBoundTo();
        this.mBottomBound.bottom = calculateBottomBoundTo(height);
        this.mBottomBound.top = calculateBottomBoundFrom();
    }

    private int calculateTopBoundFrom() {
        if (this.mView.getToolbar() != null) {
            return this.mView.getToolbar().getHeight();
        }
        return 0;
    }

    private int calculateTopBoundTo() {
        return this.mTopBound.top + this.mDetectionBoundDefaultHeight;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(ValueAnimator valueAnimator) {
        this.mScrollDistance = ((Integer) valueAnimator.getAnimatedValue()).intValue();
    }

    private void reset() {
        setIsActive(false);
        this.mInTopSpot = false;
        this.mInBottomSpot = false;
        stopAutoScroll();
    }

    /* access modifiers changed from: private */
    public void setScrollRangeDirty() {
        this.mScrollRangeDirty = true;
    }

    public boolean processAutoScroll(int i2, RecyclerView recyclerView) {
        boolean z = true;
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
            int i8 = this.mDetectionBoundDefaultHeight;
            this.mDeAccelerator.cancel();
            this.mScrollDistance = (int) (((float) this.mMaxScrollDistance) * ((((float) i8) - (((float) i2) - ((float) i7))) / ((float) i8)) * -1.0f);
            if (!this.mInTopSpot) {
                this.mInTopSpot = true;
                startAutoScroll();
            } else {
                z = false;
            }
            this.mDeAccelerator.setIntValues(new int[]{this.mScrollDistance, 0});
            this.mDeAccelerator.start();
            return z;
        } else if (i2 < i7) {
            this.mDeAccelerator.cancel();
            this.mScrollDistance = this.mMaxScrollDistance * -1;
            if (!this.mInTopSpot) {
                this.mInTopSpot = true;
                startAutoScroll();
            } else {
                z = false;
            }
            this.mDeAccelerator.setIntValues(new int[]{this.mScrollDistance, 0});
            this.mDeAccelerator.start();
            return z;
        } else {
            Rect rect2 = this.mBottomBound;
            int i10 = rect2.top;
            if (i2 >= i10 && i2 <= rect2.bottom) {
                float f = (((float) i2) - ((float) i10)) / ((float) this.mDetectionBoundDefaultHeight);
                this.mDeAccelerator.cancel();
                this.mScrollDistance = (int) (((float) this.mMaxScrollDistance) * f);
                if (!this.mInBottomSpot) {
                    this.mInBottomSpot = true;
                    startAutoScroll();
                } else {
                    z = false;
                }
                this.mDeAccelerator.setIntValues(new int[]{this.mScrollDistance, 0});
                this.mDeAccelerator.start();
                return z;
            } else if (i2 > rect2.bottom) {
                this.mDeAccelerator.cancel();
                this.mScrollDistance = this.mMaxScrollDistance;
                if (!this.mInTopSpot) {
                    this.mInTopSpot = true;
                    startAutoScroll();
                } else {
                    z = false;
                }
                this.mDeAccelerator.setIntValues(new int[]{this.mScrollDistance, 0});
                this.mDeAccelerator.start();
                return z;
            } else {
                this.mInBottomSpot = false;
                this.mInTopSpot = false;
                stopAutoScroll();
                return false;
            }
        }
    }

    public void setIsActive(boolean z) {
        this.mIsActive = z;
        if (!z) {
            stopAutoScroll();
        }
    }

    public void startAutoScroll() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.removeCallbacks(this.mScrollRunnable);
            this.mRecyclerView.postOnAnimation(this.mScrollRunnable);
        }
    }

    public void stopAutoScroll() {
        this.mInBottomSpot = false;
        this.mInTopSpot = false;
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.removeCallbacks(this.mScrollRunnable);
        }
    }

    private int calculateBottomBoundTo(int i2) {
        return i2;
    }
}
