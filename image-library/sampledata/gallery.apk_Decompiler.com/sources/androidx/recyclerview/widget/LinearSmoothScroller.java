package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import androidx.recyclerview.widget.RecyclerView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LinearSmoothScroller extends RecyclerView.SmoothScroller {
    protected final DecelerateInterpolator mDecelerateInterpolator = new DecelerateInterpolator();
    private final DisplayMetrics mDisplayMetrics;
    private boolean mHasCalculatedMillisPerPixel = false;
    protected int mInterimTargetDx = 0;
    protected int mInterimTargetDy = 0;
    protected final LinearInterpolator mLinearInterpolator = new LinearInterpolator();
    private float mMillisPerPixel;
    protected PointF mTargetVector;

    public LinearSmoothScroller(Context context) {
        this.mDisplayMetrics = context.getResources().getDisplayMetrics();
    }

    private int clampApplyScroll(int i2, int i7) {
        int i8 = i2 - i7;
        if (i2 * i8 <= 0) {
            return 0;
        }
        return i8;
    }

    private float getSpeedPerPixel() {
        if (!this.mHasCalculatedMillisPerPixel) {
            this.mMillisPerPixel = calculateSpeedPerPixel(this.mDisplayMetrics);
            this.mHasCalculatedMillisPerPixel = true;
        }
        return this.mMillisPerPixel;
    }

    public int calculateDtToFit(int i2, int i7, int i8, int i10, int i11) {
        if (i11 == -1) {
            return i8 - i2;
        }
        if (i11 == 0) {
            int i12 = i8 - i2;
            if (i12 > 0) {
                return i12;
            }
            int i13 = i10 - i7;
            if (i13 < 0) {
                return i13;
            }
            return 0;
        } else if (i11 == 1) {
            return i10 - i7;
        } else {
            throw new IllegalArgumentException("snap preference should be one of the constants defined in SmoothScroller, starting with SNAP_");
        }
    }

    public int calculateDxToMakeVisible(View view, int i2) {
        int paddingLeft;
        int width;
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager == null || !layoutManager.canScrollHorizontally()) {
            return 0;
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        int decoratedLeft = layoutManager.getDecoratedLeft(view) - layoutParams.leftMargin;
        int decoratedRight = layoutManager.getDecoratedRight(view) + layoutParams.rightMargin;
        Rect rect = this.mAvailableBounds;
        if (rect != null) {
            int i7 = rect.left;
            width = rect.right;
            paddingLeft = i7;
        } else {
            paddingLeft = layoutManager.getPaddingLeft();
            width = layoutManager.getWidth() - layoutManager.getPaddingRight();
        }
        return calculateDtToFit(decoratedLeft, decoratedRight, paddingLeft, width, i2);
    }

    public int calculateDyToMakeVisible(View view, int i2) {
        int paddingTop;
        int height;
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager == null || !layoutManager.canScrollVertically()) {
            return 0;
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        int decoratedTop = layoutManager.getDecoratedTop(view) - layoutParams.topMargin;
        int decoratedBottom = layoutManager.getDecoratedBottom(view) + layoutParams.bottomMargin;
        Rect rect = this.mAvailableBounds;
        if (rect != null) {
            int i7 = rect.top;
            height = rect.bottom;
            paddingTop = i7;
        } else {
            paddingTop = layoutManager.getPaddingTop();
            height = layoutManager.getHeight() - layoutManager.getPaddingBottom();
        }
        return calculateDtToFit(decoratedTop, decoratedBottom, paddingTop, height, i2);
    }

    public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
        return 25.0f / ((float) displayMetrics.densityDpi);
    }

    public int calculateTimeForDeceleration(int i2) {
        return (int) Math.ceil(((double) calculateTimeForScrolling(i2)) / 0.3356d);
    }

    public int calculateTimeForScrolling(int i2) {
        return (int) Math.ceil((double) (((float) Math.abs(i2)) * getSpeedPerPixel()));
    }

    public int getHorizontalSnapPreference() {
        PointF pointF = this.mTargetVector;
        if (pointF == null) {
            return 0;
        }
        float f = pointF.x;
        if (f == 0.0f) {
            return 0;
        }
        if (f > 0.0f) {
            return 1;
        }
        return -1;
    }

    public int getVerticalSnapPreference() {
        PointF pointF = this.mTargetVector;
        if (pointF == null) {
            return 0;
        }
        float f = pointF.y;
        if (f == 0.0f) {
            return 0;
        }
        if (f > 0.0f) {
            return 1;
        }
        return -1;
    }

    public void onSeekTargetStep(int i2, int i7, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
        if (getChildCount() == 0) {
            stop();
            return;
        }
        this.mInterimTargetDx = clampApplyScroll(this.mInterimTargetDx, i2);
        int clampApplyScroll = clampApplyScroll(this.mInterimTargetDy, i7);
        this.mInterimTargetDy = clampApplyScroll;
        if (this.mInterimTargetDx == 0 && clampApplyScroll == 0) {
            updateActionForInterimTarget(action);
        }
    }

    public void onStop() {
        this.mInterimTargetDy = 0;
        this.mInterimTargetDx = 0;
        this.mTargetVector = null;
    }

    public void onTargetFound(View view, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
        int calculateDxToMakeVisible = calculateDxToMakeVisible(view, getHorizontalSnapPreference());
        int calculateDyToMakeVisible = calculateDyToMakeVisible(view, getVerticalSnapPreference());
        int calculateTimeForDeceleration = calculateTimeForDeceleration((int) Math.sqrt((double) ((calculateDyToMakeVisible * calculateDyToMakeVisible) + (calculateDxToMakeVisible * calculateDxToMakeVisible))));
        if (calculateTimeForDeceleration > 0) {
            action.update(-calculateDxToMakeVisible, -calculateDyToMakeVisible, calculateTimeForDeceleration, this.mDecelerateInterpolator);
        }
    }

    public void updateActionForInterimTarget(RecyclerView.SmoothScroller.Action action) {
        PointF computeScrollVectorForPosition = computeScrollVectorForPosition(getTargetPosition());
        if (computeScrollVectorForPosition == null || (computeScrollVectorForPosition.x == 0.0f && computeScrollVectorForPosition.y == 0.0f)) {
            action.jumpTo(getTargetPosition());
            stop();
            return;
        }
        normalize(computeScrollVectorForPosition);
        this.mTargetVector = computeScrollVectorForPosition;
        this.mInterimTargetDx = (int) (computeScrollVectorForPosition.x * 10000.0f);
        this.mInterimTargetDy = (int) (computeScrollVectorForPosition.y * 10000.0f);
        action.update((int) (((float) this.mInterimTargetDx) * 1.2f), (int) (((float) this.mInterimTargetDy) * 1.2f), (int) (((float) calculateTimeForScrolling(10000)) * 1.2f), this.mLinearInterpolator);
    }

    public void onStart() {
    }
}
