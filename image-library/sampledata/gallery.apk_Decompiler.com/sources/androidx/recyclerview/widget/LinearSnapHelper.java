package androidx.recyclerview.widget;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import androidx.recyclerview.widget.RecyclerView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class LinearSnapHelper extends SnapHelper {
    /* access modifiers changed from: private */
    public int mDeccelateTimeRatio = 1;
    private final DecelerateInterpolator mDecelerateInterpolator = new DecelerateInterpolator();
    private OrientationHelper mHorizontalHelper;
    /* access modifiers changed from: private */
    public float mMillisecondsPerInch = 100.0f;
    private float mVelocityRatio = 0.5f;
    private OrientationHelper mVerticalHelper;

    public LinearSnapHelper() {
        setSnapValue(0.5f, 100.0f, 1);
    }

    private float computeDistancePerChild(RecyclerView.LayoutManager layoutManager, OrientationHelper orientationHelper) {
        int max;
        int childCount = layoutManager.getChildCount();
        if (childCount == 0) {
            return 1.0f;
        }
        View view = null;
        int i2 = Integer.MIN_VALUE;
        int i7 = Integer.MAX_VALUE;
        View view2 = null;
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = layoutManager.getChildAt(i8);
            int position = layoutManager.getPosition(childAt);
            if (position != -1) {
                if (position < i7) {
                    view = childAt;
                    i7 = position;
                }
                if (position > i2) {
                    view2 = childAt;
                    i2 = position;
                }
            }
        }
        if (view == null || view2 == null || (max = Math.max(orientationHelper.getDecoratedEnd(view), orientationHelper.getDecoratedEnd(view2)) - Math.min(orientationHelper.getDecoratedStart(view), orientationHelper.getDecoratedStart(view2))) == 0) {
            return 1.0f;
        }
        return (((float) max) * 1.0f) / ((float) ((i2 - i7) + 1));
    }

    private int distanceToCenter(View view, OrientationHelper orientationHelper) {
        return ((orientationHelper.getDecoratedMeasurement(view) / 2) + orientationHelper.getDecoratedStart(view)) - ((orientationHelper.getTotalSpace() / 2) + orientationHelper.getStartAfterPadding());
    }

    private int estimateNextPositionDiffForFling(RecyclerView.LayoutManager layoutManager, OrientationHelper orientationHelper, int i2, int i7, int[] iArr) {
        int i8;
        int i10;
        int[] seslCalculateScrollDistanceForLinear = seslCalculateScrollDistanceForLinear(i2, i7);
        float computeDistancePerChild = computeDistancePerChild(layoutManager, orientationHelper);
        if (computeDistancePerChild <= 0.0f) {
            return 0;
        }
        if (Math.abs(seslCalculateScrollDistanceForLinear[0]) > Math.abs(seslCalculateScrollDistanceForLinear[1])) {
            i8 = seslCalculateScrollDistanceForLinear[0];
        } else {
            i8 = seslCalculateScrollDistanceForLinear[1];
        }
        int round = Math.round(((float) i8) / computeDistancePerChild);
        boolean canScrollHorizontally = layoutManager.canScrollHorizontally();
        if (!canScrollHorizontally) {
            i2 = i7;
        }
        if (canScrollHorizontally) {
            i10 = iArr[0];
        } else {
            i10 = iArr[1];
        }
        if (Math.signum((float) i2) == Math.signum((float) i10) || round != 0) {
            return round;
        }
        if (i8 < 0) {
            return -1;
        }
        return 1;
    }

    private View findCenterView(RecyclerView.LayoutManager layoutManager, OrientationHelper orientationHelper) {
        int childCount = layoutManager.getChildCount();
        View view = null;
        if (childCount == 0) {
            return null;
        }
        int totalSpace = (orientationHelper.getTotalSpace() / 2) + orientationHelper.getStartAfterPadding();
        int i2 = Integer.MAX_VALUE;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = layoutManager.getChildAt(i7);
            int abs = Math.abs(((orientationHelper.getDecoratedMeasurement(childAt) / 2) + orientationHelper.getDecoratedStart(childAt)) - totalSpace);
            if (abs < i2) {
                view = childAt;
                i2 = abs;
            }
        }
        return view;
    }

    private OrientationHelper getHorizontalHelper(RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelper = this.mHorizontalHelper;
        if (orientationHelper == null || orientationHelper.mLayoutManager != layoutManager) {
            this.mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return this.mHorizontalHelper;
    }

    private OrientationHelper getVerticalHelper(RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelper = this.mVerticalHelper;
        if (orientationHelper == null || orientationHelper.mLayoutManager != layoutManager) {
            this.mVerticalHelper = OrientationHelper.createVerticalHelper(layoutManager);
        }
        return this.mVerticalHelper;
    }

    private void setSnapValue(float f, float f5, int i2) {
        this.mMillisecondsPerInch = f5;
        this.mVelocityRatio = f;
        this.mDeccelateTimeRatio = i2;
    }

    public int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager layoutManager, View view) {
        int[] iArr = new int[2];
        if (layoutManager.canScrollHorizontally()) {
            iArr[0] = distanceToCenter(view, getHorizontalHelper(layoutManager));
        } else {
            iArr[0] = 0;
        }
        if (layoutManager.canScrollVertically()) {
            iArr[1] = distanceToCenter(view, getVerticalHelper(layoutManager));
            return iArr;
        }
        iArr[1] = 0;
        return iArr;
    }

    public RecyclerView.SmoothScroller createScroller(RecyclerView.LayoutManager layoutManager) {
        if (!(layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider)) {
            return null;
        }
        return new LinearSmoothScroller(this.mRecyclerView.getContext()) {
            public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return LinearSnapHelper.this.mMillisecondsPerInch / ((float) displayMetrics.densityDpi);
            }

            public void onTargetFound(View view, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
                LinearSnapHelper linearSnapHelper = LinearSnapHelper.this;
                RecyclerView recyclerView = linearSnapHelper.mRecyclerView;
                if (recyclerView != null) {
                    int[] calculateDistanceToFinalSnap = linearSnapHelper.calculateDistanceToFinalSnap(recyclerView.getLayoutManager(), view);
                    int i2 = calculateDistanceToFinalSnap[0];
                    int i7 = calculateDistanceToFinalSnap[1];
                    int calculateTimeForDeceleration = calculateTimeForDeceleration(Math.max(Math.abs(LinearSnapHelper.this.mDeccelateTimeRatio * i2), Math.abs(LinearSnapHelper.this.mDeccelateTimeRatio * i7)));
                    if (calculateTimeForDeceleration > 0) {
                        action.update(i2, i7, calculateTimeForDeceleration, this.mDecelerateInterpolator);
                    }
                }
            }
        };
    }

    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager.canScrollVertically()) {
            return findCenterView(layoutManager, getVerticalHelper(layoutManager));
        }
        if (layoutManager.canScrollHorizontally()) {
            return findCenterView(layoutManager, getHorizontalHelper(layoutManager));
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0027, code lost:
        r9 = r7 - 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int findTargetSnapPosition(androidx.recyclerview.widget.RecyclerView.LayoutManager r15, int r16, int r17) {
        /*
            r14 = this;
            r2 = r16
            float r2 = (float) r2
            float r3 = r14.mVelocityRatio
            float r2 = r2 * r3
            int r2 = (int) r2
            r4 = r17
            float r4 = (float) r4
            float r4 = r4 * r3
            int r6 = (int) r4
            boolean r3 = r15 instanceof androidx.recyclerview.widget.RecyclerView.SmoothScroller.ScrollVectorProvider
            r4 = -1
            if (r3 != 0) goto L_0x0012
            goto L_0x0032
        L_0x0012:
            int r7 = r15.getItemCount()
            if (r7 != 0) goto L_0x0019
            goto L_0x0032
        L_0x0019:
            android.view.View r3 = r14.findSnapView(r15)
            if (r3 != 0) goto L_0x0020
            goto L_0x0032
        L_0x0020:
            int r8 = r15.getPosition(r3)
            if (r8 != r4) goto L_0x0027
            goto L_0x0032
        L_0x0027:
            r5 = r15
            androidx.recyclerview.widget.RecyclerView$SmoothScroller$ScrollVectorProvider r5 = (androidx.recyclerview.widget.RecyclerView.SmoothScroller.ScrollVectorProvider) r5
            int r9 = r7 + -1
            android.graphics.PointF r10 = r5.computeScrollVectorForPosition(r9)
            if (r10 != 0) goto L_0x0033
        L_0x0032:
            return r4
        L_0x0033:
            int[] r5 = r14.calculateDistanceToFinalSnap(r15, r3)
            boolean r3 = r15.canScrollHorizontally()
            r11 = 0
            r12 = 0
            if (r3 == 0) goto L_0x0054
            r3 = r2
            androidx.recyclerview.widget.OrientationHelper r2 = r14.getHorizontalHelper(r15)
            r4 = 0
            r0 = r14
            r1 = r15
            int r2 = r0.estimateNextPositionDiffForFling(r1, r2, r3, r4, r5)
            float r0 = r10.x
            int r0 = (r0 > r11 ? 1 : (r0 == r11 ? 0 : -1))
            if (r0 >= 0) goto L_0x0052
            int r2 = -r2
        L_0x0052:
            r13 = r2
            goto L_0x0055
        L_0x0054:
            r13 = r12
        L_0x0055:
            boolean r0 = r15.canScrollVertically()
            if (r0 == 0) goto L_0x006f
            androidx.recyclerview.widget.OrientationHelper r2 = r14.getVerticalHelper(r15)
            r3 = 0
            r0 = r14
            r1 = r15
            r4 = r6
            int r0 = r0.estimateNextPositionDiffForFling(r1, r2, r3, r4, r5)
            float r1 = r10.y
            int r1 = (r1 > r11 ? 1 : (r1 == r11 ? 0 : -1))
            if (r1 >= 0) goto L_0x0070
            int r0 = -r0
            goto L_0x0070
        L_0x006f:
            r0 = r12
        L_0x0070:
            boolean r1 = r15.canScrollVertically()
            if (r1 == 0) goto L_0x0077
            r13 = r0
        L_0x0077:
            int r8 = r8 + r13
            if (r8 >= 0) goto L_0x007b
            goto L_0x007c
        L_0x007b:
            r12 = r8
        L_0x007c:
            if (r12 < r7) goto L_0x007f
            return r9
        L_0x007f:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.LinearSnapHelper.findTargetSnapPosition(androidx.recyclerview.widget.RecyclerView$LayoutManager, int, int):int");
    }

    public LinearSnapHelper(float f, float f5, int i2) {
        setSnapValue(f, f5, i2);
    }
}
