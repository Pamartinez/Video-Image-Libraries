package androidx.dynamicanimation.animation;

import androidx.dynamicanimation.animation.DynamicAnimation;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SpringAnimation extends DynamicAnimation<SpringAnimation> {
    private boolean mEndRequested = false;
    private float mPendingPosition = Float.MAX_VALUE;
    private SpringForce mSpring = null;

    public SpringAnimation(FloatValueHolder floatValueHolder) {
        super(floatValueHolder);
    }

    private void sanityCheck() {
        SpringForce springForce = this.mSpring;
        if (springForce != null) {
            double finalPosition = (double) springForce.getFinalPosition();
            if (finalPosition > ((double) this.mMaxValue)) {
                throw new UnsupportedOperationException("Final position of the spring cannot be greater than the max value.");
            } else if (finalPosition < ((double) this.mMinValue)) {
                throw new UnsupportedOperationException("Final position of the spring cannot be less than the min value.");
            }
        } else {
            throw new UnsupportedOperationException("Incomplete SpringAnimation: Either final position or a spring force needs to be set.");
        }
    }

    public void animateToFinalPosition(float f) {
        if (isRunning()) {
            this.mPendingPosition = f;
            return;
        }
        if (this.mSpring == null) {
            this.mSpring = new SpringForce(f);
        }
        this.mSpring.setFinalPosition(f);
        start();
    }

    public void cancel() {
        super.cancel();
        float f = this.mPendingPosition;
        if (f != Float.MAX_VALUE) {
            SpringForce springForce = this.mSpring;
            if (springForce == null) {
                this.mSpring = new SpringForce(f);
            } else {
                springForce.setFinalPosition(f);
            }
            this.mPendingPosition = Float.MAX_VALUE;
        }
    }

    public SpringForce getSpring() {
        return this.mSpring;
    }

    public boolean isAtEquilibrium(float f, float f5) {
        return this.mSpring.isAtEquilibrium(f, f5);
    }

    public SpringAnimation setSpring(SpringForce springForce) {
        this.mSpring = springForce;
        return this;
    }

    public void start() {
        sanityCheck();
        this.mSpring.setValueThreshold((double) getValueThreshold());
        super.start();
    }

    public boolean updateValueAndVelocity(long j2) {
        if (this.mEndRequested) {
            float f = this.mPendingPosition;
            if (f != Float.MAX_VALUE) {
                this.mSpring.setFinalPosition(f);
                this.mPendingPosition = Float.MAX_VALUE;
            }
            this.mValue = this.mSpring.getFinalPosition();
            this.mVelocity = 0.0f;
            this.mEndRequested = false;
            return true;
        }
        if (this.mPendingPosition != Float.MAX_VALUE) {
            long j3 = j2 / 2;
            DynamicAnimation.MassState updateValues = this.mSpring.updateValues((double) this.mValue, (double) this.mVelocity, j3);
            this.mSpring.setFinalPosition(this.mPendingPosition);
            this.mPendingPosition = Float.MAX_VALUE;
            DynamicAnimation.MassState updateValues2 = this.mSpring.updateValues((double) updateValues.mValue, (double) updateValues.mVelocity, j3);
            this.mValue = updateValues2.mValue;
            this.mVelocity = updateValues2.mVelocity;
        } else {
            DynamicAnimation.MassState updateValues3 = this.mSpring.updateValues((double) this.mValue, (double) this.mVelocity, j2);
            this.mValue = updateValues3.mValue;
            this.mVelocity = updateValues3.mVelocity;
        }
        float max = Math.max(this.mValue, this.mMinValue);
        this.mValue = max;
        float min = Math.min(max, this.mMaxValue);
        this.mValue = min;
        if (!isAtEquilibrium(min, this.mVelocity)) {
            return false;
        }
        this.mValue = this.mSpring.getFinalPosition();
        this.mVelocity = 0.0f;
        return true;
    }

    public <K> SpringAnimation(K k, FloatPropertyCompat<K> floatPropertyCompat) {
        super(k, floatPropertyCompat);
    }

    public void setValueThreshold(float f) {
    }
}
