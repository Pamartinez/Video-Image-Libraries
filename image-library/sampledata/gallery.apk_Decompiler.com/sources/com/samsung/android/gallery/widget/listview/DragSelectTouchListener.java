package com.samsung.android.gallery.widget.listview;

import A.a;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.OverScroller;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.R$bool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DragSelectTouchListener implements RecyclerView.OnItemTouchListener {
    private int mBottomBoundFrom;
    private int mBottomBoundTo;
    private boolean mDebug;
    Runnable mDragUpdater;
    private int mEnd;
    private boolean mInBottomSpot;
    private boolean mInTopSpot;
    private int mInitialBottomBoundFrom;
    private boolean mIsActive;
    private boolean mIsLongPressedAfterSelectionMode;
    private int mLastEnd;
    private float mLastRawX;
    private float mLastRawY;
    private int mLastStart;
    private float mLastX;
    private float mLastY;
    private int mMaxScrollDistance = 20;
    private int mMaxScrollVelocityLevel = 0;
    private OnAppbarVisibleListener mOnAppbarVisibleListener;
    /* access modifiers changed from: private */
    public RecyclerView mRecyclerView;
    /* access modifiers changed from: private */
    public int mScrollDistance;
    /* access modifiers changed from: private */
    public final Runnable mScrollRunnable = new Runnable() {
        public void run() {
            try {
                if (DragSelectTouchListener.this.mScroller != null && DragSelectTouchListener.this.mScroller.computeScrollOffset()) {
                    DragSelectTouchListener dragSelectTouchListener = DragSelectTouchListener.this;
                    dragSelectTouchListener.scrollBy(dragSelectTouchListener.mScrollDistance);
                    DragSelectTouchListener.this.mRecyclerView.postOnAnimation(DragSelectTouchListener.this.mScrollRunnable);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("DragSelectTouchListener", "fail scroll. ignore exception");
            }
        }
    };
    /* access modifiers changed from: private */
    public OverScroller mScroller;
    private OnDragSelectListener mSelectListener;
    private int mStart;
    private int mTopBoundFrom;
    private int mTopBoundTo;
    private int mTouchRegionBottomOffset = 0;
    private int mTouchRegionTopOffset = 0;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnAdvancedDragSelectListener extends OnDragSelectListener {
        void onSelectionFinished(int i2);

        void onSelectionStarted(int i2);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnAppbarVisibleListener {
        boolean isAppbarVisible();

        void setExpand(boolean z);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnDragSelectListener {
        void onSelectChange(int i2, int i7, boolean z, boolean z3);
    }

    public DragSelectTouchListener() {
        reset();
    }

    private boolean equals(float f, float f5) {
        if (Math.abs(f - f5) < 1.0E-5f) {
            return true;
        }
        return false;
    }

    private int getInitialBottomBoundFrom(int i2, int i7) {
        return (i2 - i7) - this.mTouchRegionBottomOffset;
    }

    private int getMaxScrollDistance() {
        int i2;
        int i7 = this.mMaxScrollVelocityLevel;
        if (i7 >= 2) {
            i2 = 10;
        } else if (i7 >= 1) {
            i2 = 5;
        } else {
            i2 = 0;
        }
        return i2 + 20;
    }

    private boolean handleEmptySpace(RecyclerView recyclerView, float f, float f5) {
        boolean z;
        float f8;
        int childAdapterPosition;
        View view = null;
        if (recyclerView.getContext().getResources().getBoolean(R$bool.is_right_to_left)) {
            while (f5 >= 0.0f) {
                float f10 = f;
                while (f10 <= ((float) recyclerView.getWidth()) && (view = recyclerView.findChildViewUnder(f10, f5)) == null) {
                    f10 += 50.0f;
                }
                if (view != null) {
                    break;
                }
                f5 -= 50.0f;
            }
            if (!(view == null || (childAdapterPosition = recyclerView.getChildAdapterPosition(view)) == -1)) {
                int i2 = childAdapterPosition + 1;
                int itemCount = recyclerView.getAdapter().getItemCount();
                if (childAdapterPosition <= this.mStart && i2 < itemCount) {
                    childAdapterPosition = i2;
                }
                if (this.mEnd != childAdapterPosition) {
                    this.mEnd = childAdapterPosition;
                    notifySelectRangeChange();
                }
            }
        } else {
            if (f5 < 0.0f) {
                z = true;
            } else {
                z = false;
            }
            while (lambda$handleEmptySpace$0(z, Float.valueOf(f5)).booleanValue()) {
                float f11 = f;
                while (f11 >= 0.0f && (view = recyclerView.findChildViewUnder(f11, f5)) == null) {
                    f11 -= 50.0f;
                }
                if (view != null) {
                    break;
                }
                if (z) {
                    f8 = 50.0f;
                } else {
                    f8 = -50.0f;
                }
                f5 += f8;
            }
            if (view != null) {
                int childAdapterPosition2 = recyclerView.getChildAdapterPosition(view);
                RecyclerView.Adapter adapter = recyclerView.getAdapter();
                if (!(adapter instanceof GalleryListAdapter) || ((GalleryListAdapter) adapter).isDragSelectable(childAdapterPosition2)) {
                    if (childAdapterPosition2 != -1) {
                        int i7 = childAdapterPosition2 + 1;
                        int itemCount2 = recyclerView.getAdapter().getItemCount();
                        if (childAdapterPosition2 <= this.mStart && itemCount2 > i7) {
                            childAdapterPosition2 = i7;
                        }
                        if (this.mEnd != childAdapterPosition2) {
                            this.mEnd = childAdapterPosition2;
                            notifySelectRangeChange();
                        }
                    }
                }
                return false;
            }
        }
        if (view != null) {
            return true;
        }
        return false;
    }

    private void initScroller(Context context) {
        if (this.mScroller == null) {
            this.mScroller = new OverScroller(context, new LinearInterpolator());
        }
    }

    private static /* synthetic */ Boolean lambda$handleEmptySpace$0(boolean z, Float f) {
        boolean z3 = false;
        if (!z ? f.floatValue() >= 0.0f : f.floatValue() < 0.0f) {
            z3 = true;
        }
        return Boolean.valueOf(z3);
    }

    private void notifySelectRangeChange() {
        int i2;
        int i7;
        boolean z;
        if (this.mSelectListener != null && (i2 = this.mStart) != -1 && (i7 = this.mEnd) != -1) {
            if (i2 < i7) {
                z = true;
            } else {
                z = false;
            }
            int min = Math.min(i2, i7);
            int max = Math.max(this.mStart, this.mEnd);
            int i8 = this.mLastStart;
            if (i8 != -1 && this.mLastEnd != -1) {
                if (min > i8) {
                    this.mSelectListener.onSelectChange(i8, min - 1, false, z);
                } else if (min < i8) {
                    this.mSelectListener.onSelectChange(min, i8 - 1, true, z);
                }
                int i10 = this.mLastEnd;
                if (max > i10) {
                    this.mSelectListener.onSelectChange(i10 + 1, max, true, z);
                } else if (max < i10) {
                    this.mSelectListener.onSelectChange(max + 1, i10, false, z);
                }
            } else if (max - min == 1) {
                this.mSelectListener.onSelectChange(min, min, true, z);
            } else {
                this.mSelectListener.onSelectChange(min, max, true, z);
            }
            this.mLastStart = min;
            this.mLastEnd = max;
        }
    }

    private void onDeactivated() {
        Runnable runnable = this.mDragUpdater;
        if (runnable != null) {
            runnable.run();
        }
    }

    private void processAutoScroll(MotionEvent motionEvent) {
        int y = (int) motionEvent.getY();
        if (this.mDebug) {
            StringBuilder o2 = C0086a.o(y, "y = ", " | rv.height = ");
            o2.append(this.mRecyclerView.getHeight());
            o2.append(" | mTopBoundFrom ~ mTopBoundTo = ");
            o2.append(this.mTopBoundFrom);
            o2.append(" ~ ");
            o2.append(this.mTopBoundTo);
            o2.append(" | mBottomBoundFrom ~ mBottomBoundTo = ");
            o2.append(this.mBottomBoundFrom);
            o2.append(" ~ ");
            o2.append(this.mBottomBoundTo);
            o2.append(" | mTouchRegionTopOffset = ");
            o2.append(this.mTouchRegionTopOffset);
            o2.append(" | mTouchRegionBottomOffset = ");
            o2.append(this.mTouchRegionBottomOffset);
            o2.append(" | mMaxScrollDistance = ");
            a.w(o2, this.mMaxScrollDistance, "DragSelectTouchListener");
        }
        int i2 = this.mTopBoundFrom;
        if (y >= i2 && y <= this.mTopBoundTo) {
            this.mLastX = motionEvent.getX();
            this.mLastY = motionEvent.getY();
            int i7 = this.mTopBoundTo;
            int i8 = this.mTopBoundFrom;
            this.mScrollDistance = (int) (((float) this.mMaxScrollDistance) * (((((float) i7) - ((float) i8)) - (((float) y) - ((float) i8))) / (((float) i7) - ((float) i8))) * -1.0f);
            if (!this.mInTopSpot) {
                this.mInTopSpot = true;
                if (this.mDebug) {
                    Log.d("DragSelectTouchListener", "startAutoScroll between topBoundFrom and toBoundTo.");
                }
                startAutoScroll();
            }
        } else if (y < i2) {
            this.mLastX = motionEvent.getX();
            this.mLastY = motionEvent.getY();
            this.mScrollDistance = this.mMaxScrollDistance * -1;
            if (!this.mInTopSpot) {
                this.mInTopSpot = true;
                if (this.mDebug) {
                    Log.d("DragSelectTouchListener", "startAutoScroll under topBoundFrom.");
                }
                startAutoScroll();
            }
        } else if (y >= this.mBottomBoundFrom && y <= this.mBottomBoundTo) {
            this.mLastX = motionEvent.getX();
            this.mLastY = motionEvent.getY();
            int i10 = this.mBottomBoundFrom;
            this.mScrollDistance = (int) (((float) this.mMaxScrollDistance) * ((((float) y) - ((float) i10)) / (((float) this.mBottomBoundTo) - ((float) i10))));
            if (!this.mInBottomSpot) {
                this.mInBottomSpot = true;
                if (this.mDebug) {
                    Log.d("DragSelectTouchListener", "event.getHistorySize() = " + motionEvent.getHistorySize());
                }
                if (this.mDebug) {
                    Log.d("DragSelectTouchListener", "startAutoScroll between bottomBoundFrom and bottomBoundTo.");
                }
                startAutoScroll();
            }
        } else if (y > this.mBottomBoundTo) {
            this.mLastX = motionEvent.getX();
            this.mLastY = motionEvent.getY();
            this.mScrollDistance = this.mMaxScrollDistance;
            if (!this.mInBottomSpot) {
                this.mInBottomSpot = true;
                if (this.mDebug) {
                    Log.d("DragSelectTouchListener", "startAutoScroll over bottomBoundTo.");
                }
                startAutoScroll();
            }
        } else {
            this.mInBottomSpot = false;
            this.mInTopSpot = false;
            this.mLastX = Float.MIN_VALUE;
            this.mLastY = Float.MIN_VALUE;
            stopAutoScroll();
        }
    }

    /* access modifiers changed from: private */
    public void scrollBy(int i2) {
        int i7;
        OnAppbarVisibleListener onAppbarVisibleListener;
        if (i2 > 0) {
            i7 = Math.min(i2, this.mMaxScrollDistance);
        } else {
            i7 = Math.max(i2, -this.mMaxScrollDistance);
        }
        if (i7 <= 0 || (onAppbarVisibleListener = this.mOnAppbarVisibleListener) == null || !onAppbarVisibleListener.isAppbarVisible()) {
            this.mRecyclerView.scrollBy(0, i7);
        } else {
            this.mRecyclerView.startNestedScroll(2, 1);
            int i8 = i2;
            this.mRecyclerView.dispatchNestedPreScroll(0, i8, (int[]) null, (int[]) null, 1);
            this.mBottomBoundFrom += i8;
            this.mBottomBoundTo += i8;
        }
        if (!equals(this.mLastX, Float.MIN_VALUE) && !equals(this.mLastY, Float.MIN_VALUE)) {
            updateSelectedRange(this.mRecyclerView, this.mLastX, this.mLastY);
        }
    }

    private void updateAppbarExpansion() {
        OnAppbarVisibleListener onAppbarVisibleListener;
        if (this.mBottomBoundFrom != this.mInitialBottomBoundFrom && (onAppbarVisibleListener = this.mOnAppbarVisibleListener) != null) {
            onAppbarVisibleListener.setExpand(false);
        }
    }

    private void updateSelectedRange(RecyclerView recyclerView, MotionEvent motionEvent) {
        updateSelectedRange(recyclerView, motionEvent.getX(), motionEvent.getY());
    }

    public void exitDragSelect() {
        if (this.mIsActive) {
            setIsActive(false);
            reset();
        }
    }

    public boolean isActive() {
        return this.mIsActive;
    }

    public boolean isAutoScrolling() {
        OverScroller overScroller = this.mScroller;
        if (overScroller == null || overScroller.isFinished()) {
            return false;
        }
        return true;
    }

    public boolean isLongPressedAfterSelectionMode() {
        return this.mIsLongPressedAfterSelectionMode;
    }

    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        this.mLastRawX = motionEvent.getRawX();
        this.mLastRawY = motionEvent.getRawY();
        if (!this.mIsActive || recyclerView.getAdapter() == null || recyclerView.getAdapter().getItemCount() == 0) {
            return false;
        }
        if (motionEvent.getPointerCount() > 1) {
            reset();
            Log.e("DragSelectTouchListener", "detect multi touch. stop");
            return true;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0 || actionMasked == 1 || actionMasked == 5) {
            reset();
        }
        this.mRecyclerView = recyclerView;
        Rect rect = new Rect();
        this.mRecyclerView.getGlobalVisibleRect(rect);
        int height = rect.height();
        int i2 = (int) (Resources.getSystem().getDisplayMetrics().density * 28.0f);
        this.mMaxScrollDistance = getMaxScrollDistance();
        int i7 = this.mTouchRegionTopOffset;
        this.mTopBoundFrom = -i7;
        this.mTopBoundTo = i7 + i2;
        int initialBottomBoundFrom = getInitialBottomBoundFrom(height, i2);
        this.mInitialBottomBoundFrom = initialBottomBoundFrom;
        this.mBottomBoundFrom = initialBottomBoundFrom;
        this.mBottomBoundTo = height + this.mTouchRegionBottomOffset;
        return true;
    }

    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        this.mLastRawX = motionEvent.getRawX();
        this.mLastRawY = motionEvent.getRawY();
        if (this.mIsActive) {
            if (motionEvent.getPointerCount() > 1) {
                reset();
                Log.e("DragSelectTouchListener", "detect multi touch. stop");
                return;
            }
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    if (!this.mInTopSpot) {
                        updateSelectedRange(recyclerView, motionEvent);
                    }
                    processAutoScroll(motionEvent);
                    return;
                } else if (!(actionMasked == 3 || actionMasked == 6)) {
                    return;
                }
            }
            reset();
        }
    }

    public void release() {
        this.mSelectListener = null;
        this.mOnAppbarVisibleListener = null;
    }

    public void reset() {
        setIsActive(false);
        OnDragSelectListener onDragSelectListener = this.mSelectListener;
        if (onDragSelectListener instanceof OnAdvancedDragSelectListener) {
            ((OnAdvancedDragSelectListener) onDragSelectListener).onSelectionFinished(this.mEnd);
        }
        this.mStart = -1;
        this.mEnd = -1;
        this.mLastStart = -1;
        this.mLastEnd = -1;
        this.mInTopSpot = false;
        this.mInBottomSpot = false;
        this.mLastX = Float.MIN_VALUE;
        this.mLastY = Float.MIN_VALUE;
        updateAppbarExpansion();
        stopAutoScroll();
    }

    public void setDragUpdater(Runnable runnable) {
        this.mDragUpdater = runnable;
    }

    public void setIsActive(boolean z) {
        this.mIsActive = z;
        if (!z) {
            onDeactivated();
        }
    }

    public void setLongPressedAfterSelectionMode(boolean z) {
        this.mIsLongPressedAfterSelectionMode = z;
    }

    public void startAutoScroll() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            if (recyclerView.getScrollState() != 0) {
                Log.w("DragSelectTouchListener", "recyclerview scroll state : " + this.mRecyclerView.getScrollState());
                this.mRecyclerView.stopScroll();
            }
            initScroller(this.mRecyclerView.getContext());
            if (this.mScroller.isFinished()) {
                this.mRecyclerView.removeCallbacks(this.mScrollRunnable);
                OverScroller overScroller = this.mScroller;
                overScroller.startScroll(0, overScroller.getCurrY(), 0, 5000, 100000);
                this.mRecyclerView.postOnAnimation(this.mScrollRunnable);
            }
            Log.d("DragSelectTouchListener", "startAutoScroll");
        }
    }

    public void startDragSelection(int i2) {
        setIsActive(true);
        this.mStart = i2;
        this.mEnd = i2;
        this.mLastStart = i2;
        this.mLastEnd = i2;
        OnDragSelectListener onDragSelectListener = this.mSelectListener;
        if (onDragSelectListener instanceof OnAdvancedDragSelectListener) {
            ((OnAdvancedDragSelectListener) onDragSelectListener).onSelectionStarted(i2);
        }
    }

    public void stopAutoScroll() {
        if (isAutoScrolling()) {
            this.mRecyclerView.removeCallbacks(this.mScrollRunnable);
            this.mScroller.abortAnimation();
            Log.d("DragSelectTouchListener", "stopAutoScroll");
        }
    }

    public DragSelectTouchListener withBottomOffset(int i2) {
        this.mTouchRegionBottomOffset = i2;
        return this;
    }

    public DragSelectTouchListener withMaxScrollVelocityLevel(int i2) {
        this.mMaxScrollVelocityLevel = i2;
        return this;
    }

    public DragSelectTouchListener withOnAppbarVisibleListener(OnAppbarVisibleListener onAppbarVisibleListener) {
        this.mOnAppbarVisibleListener = onAppbarVisibleListener;
        return this;
    }

    public DragSelectTouchListener withSelectListener(OnDragSelectListener onDragSelectListener) {
        this.mSelectListener = onDragSelectListener;
        return this;
    }

    public DragSelectTouchListener withTopOffset(int i2) {
        this.mTouchRegionTopOffset = i2;
        return this;
    }

    private void updateSelectedRange(RecyclerView recyclerView, float f, float f5) {
        View findChildViewUnder = recyclerView.findChildViewUnder(f, f5);
        if (findChildViewUnder != null) {
            int childAdapterPosition = recyclerView.getChildAdapterPosition(findChildViewUnder);
            if (childAdapterPosition != -1 && this.mEnd != childAdapterPosition) {
                this.mEnd = childAdapterPosition;
                notifySelectRangeChange();
                return;
            }
            return;
        }
        handleEmptySpace(recyclerView, f, f5);
    }

    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }
}
