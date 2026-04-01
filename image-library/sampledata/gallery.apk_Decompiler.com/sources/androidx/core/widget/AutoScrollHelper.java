package androidx.core.widget;

import android.content.res.Resources;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import androidx.core.view.ViewCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AutoScrollHelper implements View.OnTouchListener {
    private static final int DEFAULT_ACTIVATION_DELAY = ViewConfiguration.getTapTimeout();
    private int mActivationDelay;
    private boolean mAlreadyDelayed;
    boolean mAnimating;
    private final Interpolator mEdgeInterpolator = new AccelerateInterpolator();
    private int mEdgeType;
    private boolean mEnabled;
    private boolean mExclusive;
    private float[] mMaximumEdges = {Float.MAX_VALUE, Float.MAX_VALUE};
    private float[] mMaximumVelocity = {Float.MAX_VALUE, Float.MAX_VALUE};
    private float[] mMinimumVelocity = {0.0f, 0.0f};
    boolean mNeedsCancel;
    boolean mNeedsReset;
    private float[] mRelativeEdges = {0.0f, 0.0f};
    private float[] mRelativeVelocity = {0.0f, 0.0f};
    private Runnable mRunnable;
    final ClampedScroller mScroller = new ClampedScroller();
    final View mTarget;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ClampedScroller {
        private long mDeltaTime = 0;
        private int mDeltaX = 0;
        private int mDeltaY = 0;
        private int mEffectiveRampDown;
        private int mRampDownDuration;
        private int mRampUpDuration;
        private long mStartTime = Long.MIN_VALUE;
        private long mStopTime = -1;
        private float mStopValue;
        private float mTargetVelocityX;
        private float mTargetVelocityY;

        private float getValueAt(long j2) {
            long j3 = this.mStartTime;
            if (j2 < j3) {
                return 0.0f;
            }
            long j8 = this.mStopTime;
            if (j8 < 0 || j2 < j8) {
                return AutoScrollHelper.constrain(((float) (j2 - j3)) / ((float) this.mRampUpDuration), 0.0f, 1.0f) * 0.5f;
            }
            float f = this.mStopValue;
            return (AutoScrollHelper.constrain(((float) (j2 - j8)) / ((float) this.mEffectiveRampDown), 0.0f, 1.0f) * f) + (1.0f - f);
        }

        private float interpolateValue(float f) {
            return (f * 4.0f) + (-4.0f * f * f);
        }

        public void computeScrollDelta() {
            if (this.mDeltaTime != 0) {
                long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
                float interpolateValue = interpolateValue(getValueAt(currentAnimationTimeMillis));
                this.mDeltaTime = currentAnimationTimeMillis;
                float f = ((float) (currentAnimationTimeMillis - this.mDeltaTime)) * interpolateValue;
                this.mDeltaX = (int) (this.mTargetVelocityX * f);
                this.mDeltaY = (int) (f * this.mTargetVelocityY);
                return;
            }
            throw new RuntimeException("Cannot compute scroll delta before calling start()");
        }

        public int getDeltaX() {
            return this.mDeltaX;
        }

        public int getDeltaY() {
            return this.mDeltaY;
        }

        public int getHorizontalDirection() {
            float f = this.mTargetVelocityX;
            return (int) (f / Math.abs(f));
        }

        public int getVerticalDirection() {
            float f = this.mTargetVelocityY;
            return (int) (f / Math.abs(f));
        }

        public boolean isFinished() {
            if (this.mStopTime <= 0 || AnimationUtils.currentAnimationTimeMillis() <= this.mStopTime + ((long) this.mEffectiveRampDown)) {
                return false;
            }
            return true;
        }

        public void requestStop() {
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.mEffectiveRampDown = AutoScrollHelper.constrain((int) (currentAnimationTimeMillis - this.mStartTime), 0, this.mRampDownDuration);
            this.mStopValue = getValueAt(currentAnimationTimeMillis);
            this.mStopTime = currentAnimationTimeMillis;
        }

        public void setRampDownDuration(int i2) {
            this.mRampDownDuration = i2;
        }

        public void setRampUpDuration(int i2) {
            this.mRampUpDuration = i2;
        }

        public void setTargetVelocity(float f, float f5) {
            this.mTargetVelocityX = f;
            this.mTargetVelocityY = f5;
        }

        public void start() {
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.mStartTime = currentAnimationTimeMillis;
            this.mStopTime = -1;
            this.mDeltaTime = currentAnimationTimeMillis;
            this.mStopValue = 0.5f;
            this.mDeltaX = 0;
            this.mDeltaY = 0;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ScrollAnimationRunnable implements Runnable {
        public ScrollAnimationRunnable() {
        }

        public void run() {
            AutoScrollHelper autoScrollHelper = AutoScrollHelper.this;
            if (autoScrollHelper.mAnimating) {
                if (autoScrollHelper.mNeedsReset) {
                    autoScrollHelper.mNeedsReset = false;
                    autoScrollHelper.mScroller.start();
                }
                ClampedScroller clampedScroller = AutoScrollHelper.this.mScroller;
                if (clampedScroller.isFinished() || !AutoScrollHelper.this.shouldAnimate()) {
                    AutoScrollHelper.this.mAnimating = false;
                    return;
                }
                AutoScrollHelper autoScrollHelper2 = AutoScrollHelper.this;
                if (autoScrollHelper2.mNeedsCancel) {
                    autoScrollHelper2.mNeedsCancel = false;
                    autoScrollHelper2.cancelTargetTouch();
                }
                clampedScroller.computeScrollDelta();
                AutoScrollHelper.this.scrollTargetBy(clampedScroller.getDeltaX(), clampedScroller.getDeltaY());
                ViewCompat.postOnAnimation(AutoScrollHelper.this.mTarget, this);
            }
        }
    }

    public AutoScrollHelper(View view) {
        this.mTarget = view;
        float f = Resources.getSystem().getDisplayMetrics().density;
        float f5 = (float) ((int) ((1575.0f * f) + 0.5f));
        setMaximumVelocity(f5, f5);
        float f8 = (float) ((int) ((f * 315.0f) + 0.5f));
        setMinimumVelocity(f8, f8);
        setEdgeType(1);
        setMaximumEdges(Float.MAX_VALUE, Float.MAX_VALUE);
        setRelativeEdges(0.2f, 0.2f);
        setRelativeVelocity(1.0f, 1.0f);
        setActivationDelay(DEFAULT_ACTIVATION_DELAY);
        setRampUpDuration(500);
        setRampDownDuration(500);
    }

    private float computeTargetVelocity(int i2, float f, float f5, float f8) {
        float edgeValue = getEdgeValue(this.mRelativeEdges[i2], f5, this.mMaximumEdges[i2], f);
        int i7 = (edgeValue > 0.0f ? 1 : (edgeValue == 0.0f ? 0 : -1));
        if (i7 == 0) {
            return 0.0f;
        }
        float f10 = this.mRelativeVelocity[i2];
        float f11 = this.mMinimumVelocity[i2];
        float f12 = this.mMaximumVelocity[i2];
        float f13 = f10 * f8;
        if (i7 > 0) {
            return constrain(edgeValue * f13, f11, f12);
        }
        return -constrain((-edgeValue) * f13, f11, f12);
    }

    public static float constrain(float f, float f5, float f8) {
        if (f > f8) {
            return f8;
        }
        return f < f5 ? f5 : f;
    }

    private float constrainEdgeValue(float f, float f5) {
        if (f5 == 0.0f) {
            return 0.0f;
        }
        int i2 = this.mEdgeType;
        if (i2 == 0 || i2 == 1) {
            if (f < f5) {
                if (f >= 0.0f) {
                    return 1.0f - (f / f5);
                }
                if (!this.mAnimating || i2 != 1) {
                    return 0.0f;
                }
                return 1.0f;
            }
        } else if (i2 == 2 && f < 0.0f) {
            return f / (-f5);
        }
        return 0.0f;
    }

    private float getEdgeValue(float f, float f5, float f8, float f10) {
        float f11;
        float constrain = constrain(f * f5, 0.0f, f8);
        float constrainEdgeValue = constrainEdgeValue(f5 - f10, constrain) - constrainEdgeValue(f10, constrain);
        if (constrainEdgeValue < 0.0f) {
            f11 = -this.mEdgeInterpolator.getInterpolation(-constrainEdgeValue);
        } else if (constrainEdgeValue <= 0.0f) {
            return 0.0f;
        } else {
            f11 = this.mEdgeInterpolator.getInterpolation(constrainEdgeValue);
        }
        return constrain(f11, -1.0f, 1.0f);
    }

    private void requestStop() {
        if (this.mNeedsReset) {
            this.mAnimating = false;
        } else {
            this.mScroller.requestStop();
        }
    }

    private void startAnimating() {
        int i2;
        if (this.mRunnable == null) {
            this.mRunnable = new ScrollAnimationRunnable();
        }
        this.mAnimating = true;
        this.mNeedsReset = true;
        if (this.mAlreadyDelayed || (i2 = this.mActivationDelay) <= 0) {
            this.mRunnable.run();
        } else {
            ViewCompat.postOnAnimationDelayed(this.mTarget, this.mRunnable, (long) i2);
        }
        this.mAlreadyDelayed = true;
    }

    public abstract boolean canTargetScrollHorizontally(int i2);

    public abstract boolean canTargetScrollVertically(int i2);

    public void cancelTargetTouch() {
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
        this.mTarget.onTouchEvent(obtain);
        obtain.recycle();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0013, code lost:
        if (r0 != 3) goto L_0x0058;
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0060 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0061 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouch(android.view.View r6, android.view.MotionEvent r7) {
        /*
            r5 = this;
            boolean r0 = r5.mEnabled
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            int r0 = r7.getActionMasked()
            r2 = 1
            if (r0 == 0) goto L_0x001a
            if (r0 == r2) goto L_0x0016
            r3 = 2
            if (r0 == r3) goto L_0x001e
            r6 = 3
            if (r0 == r6) goto L_0x0016
            goto L_0x0058
        L_0x0016:
            r5.requestStop()
            goto L_0x0058
        L_0x001a:
            r5.mNeedsCancel = r2
            r5.mAlreadyDelayed = r1
        L_0x001e:
            float r0 = r7.getX()
            int r3 = r6.getWidth()
            float r3 = (float) r3
            android.view.View r4 = r5.mTarget
            int r4 = r4.getWidth()
            float r4 = (float) r4
            float r0 = r5.computeTargetVelocity(r1, r0, r3, r4)
            float r7 = r7.getY()
            int r6 = r6.getHeight()
            float r6 = (float) r6
            android.view.View r3 = r5.mTarget
            int r3 = r3.getHeight()
            float r3 = (float) r3
            float r6 = r5.computeTargetVelocity(r2, r7, r6, r3)
            androidx.core.widget.AutoScrollHelper$ClampedScroller r7 = r5.mScroller
            r7.setTargetVelocity(r0, r6)
            boolean r6 = r5.mAnimating
            if (r6 != 0) goto L_0x0058
            boolean r6 = r5.shouldAnimate()
            if (r6 == 0) goto L_0x0058
            r5.startAnimating()
        L_0x0058:
            boolean r6 = r5.mExclusive
            if (r6 == 0) goto L_0x0061
            boolean r5 = r5.mAnimating
            if (r5 == 0) goto L_0x0061
            return r2
        L_0x0061:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.AutoScrollHelper.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public abstract void scrollTargetBy(int i2, int i7);

    public AutoScrollHelper setActivationDelay(int i2) {
        this.mActivationDelay = i2;
        return this;
    }

    public AutoScrollHelper setEdgeType(int i2) {
        this.mEdgeType = i2;
        return this;
    }

    public AutoScrollHelper setEnabled(boolean z) {
        if (this.mEnabled && !z) {
            requestStop();
        }
        this.mEnabled = z;
        return this;
    }

    public AutoScrollHelper setMaximumEdges(float f, float f5) {
        float[] fArr = this.mMaximumEdges;
        fArr[0] = f;
        fArr[1] = f5;
        return this;
    }

    public AutoScrollHelper setMaximumVelocity(float f, float f5) {
        float[] fArr = this.mMaximumVelocity;
        fArr[0] = f / 1000.0f;
        fArr[1] = f5 / 1000.0f;
        return this;
    }

    public AutoScrollHelper setMinimumVelocity(float f, float f5) {
        float[] fArr = this.mMinimumVelocity;
        fArr[0] = f / 1000.0f;
        fArr[1] = f5 / 1000.0f;
        return this;
    }

    public AutoScrollHelper setRampDownDuration(int i2) {
        this.mScroller.setRampDownDuration(i2);
        return this;
    }

    public AutoScrollHelper setRampUpDuration(int i2) {
        this.mScroller.setRampUpDuration(i2);
        return this;
    }

    public AutoScrollHelper setRelativeEdges(float f, float f5) {
        float[] fArr = this.mRelativeEdges;
        fArr[0] = f;
        fArr[1] = f5;
        return this;
    }

    public AutoScrollHelper setRelativeVelocity(float f, float f5) {
        float[] fArr = this.mRelativeVelocity;
        fArr[0] = f / 1000.0f;
        fArr[1] = f5 / 1000.0f;
        return this;
    }

    public boolean shouldAnimate() {
        ClampedScroller clampedScroller = this.mScroller;
        int verticalDirection = clampedScroller.getVerticalDirection();
        int horizontalDirection = clampedScroller.getHorizontalDirection();
        if (verticalDirection != 0 && canTargetScrollVertically(verticalDirection)) {
            return true;
        }
        if (horizontalDirection == 0 || !canTargetScrollHorizontally(horizontalDirection)) {
            return false;
        }
        return true;
    }

    public static int constrain(int i2, int i7, int i8) {
        if (i2 > i8) {
            return i8;
        }
        return i2 < i7 ? i7 : i2;
    }
}
