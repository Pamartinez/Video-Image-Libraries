package androidx.viewpager2.widget;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import androidx.recyclerview.widget.RecyclerView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class FakeDrag {
    private int mActualDraggedDistance;
    private long mFakeDragBeginTime;
    private int mMaximumVelocity;
    private final RecyclerView mRecyclerView;
    private float mRequestedDragDistance;
    private final ScrollEventAdapter mScrollEventAdapter;
    private VelocityTracker mVelocityTracker;
    private final ViewPager2 mViewPager;

    public FakeDrag(ViewPager2 viewPager2, ScrollEventAdapter scrollEventAdapter, RecyclerView recyclerView) {
        this.mViewPager = viewPager2;
        this.mScrollEventAdapter = scrollEventAdapter;
        this.mRecyclerView = recyclerView;
    }

    private void addFakeMotionEvent(long j2, int i2, float f, float f5) {
        MotionEvent obtain = MotionEvent.obtain(this.mFakeDragBeginTime, j2, i2, f, f5, 0);
        this.mVelocityTracker.addMovement(obtain);
        obtain.recycle();
    }

    private void beginFakeVelocityTracker() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
            this.mMaximumVelocity = ViewConfiguration.get(this.mViewPager.getContext()).getScaledMaximumFlingVelocity();
            return;
        }
        velocityTracker.clear();
    }

    public boolean beginFakeDrag() {
        if (this.mScrollEventAdapter.isDragging()) {
            return false;
        }
        this.mActualDraggedDistance = 0;
        this.mRequestedDragDistance = (float) 0;
        this.mFakeDragBeginTime = SystemClock.uptimeMillis();
        beginFakeVelocityTracker();
        this.mScrollEventAdapter.notifyBeginFakeDrag();
        if (!this.mScrollEventAdapter.isIdle()) {
            this.mRecyclerView.stopScroll();
        }
        addFakeMotionEvent(this.mFakeDragBeginTime, 0, 0.0f, 0.0f);
        return true;
    }

    public boolean endFakeDrag() {
        if (!this.mScrollEventAdapter.isFakeDragging()) {
            return false;
        }
        this.mScrollEventAdapter.notifyEndFakeDrag();
        VelocityTracker velocityTracker = this.mVelocityTracker;
        velocityTracker.computeCurrentVelocity(1000, (float) this.mMaximumVelocity);
        if (this.mRecyclerView.fling((int) velocityTracker.getXVelocity(), (int) velocityTracker.getYVelocity())) {
            return true;
        }
        this.mViewPager.snapToPage();
        return true;
    }

    public boolean fakeDragBy(float f) {
        boolean z;
        int i2;
        float f5;
        int i7 = 0;
        if (!this.mScrollEventAdapter.isFakeDragging()) {
            return false;
        }
        float f8 = this.mRequestedDragDistance - f;
        this.mRequestedDragDistance = f8;
        int round = Math.round(f8 - ((float) this.mActualDraggedDistance));
        this.mActualDraggedDistance += round;
        long uptimeMillis = SystemClock.uptimeMillis();
        if (this.mViewPager.getOrientation() == 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            i2 = round;
        } else {
            i2 = 0;
        }
        if (!z) {
            i7 = round;
        }
        float f10 = 0.0f;
        if (z) {
            f5 = this.mRequestedDragDistance;
        } else {
            f5 = 0.0f;
        }
        if (!z) {
            f10 = this.mRequestedDragDistance;
        }
        float f11 = f10;
        this.mRecyclerView.scrollBy(i2, i7);
        addFakeMotionEvent(uptimeMillis, 2, f5, f11);
        return true;
    }

    public boolean isFakeDragging() {
        return this.mScrollEventAdapter.isFakeDragging();
    }
}
