package androidx.dynamicanimation.animation;

import androidx.dynamicanimation.animation.DynamicAnimation;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FlingAnimation extends DynamicAnimation<FlingAnimation> {
    private final DragForce mFlingForce;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DragForce {
        private float mFriction = -4.2f;
        private final DynamicAnimation.MassState mMassState = new DynamicAnimation.MassState();
        private float mVelocityThreshold;

        public boolean isAtEquilibrium(float f, float f5) {
            if (Math.abs(f5) < this.mVelocityThreshold) {
                return true;
            }
            return false;
        }

        public void setFrictionScalar(float f) {
            this.mFriction = f * -4.2f;
        }

        public void setValueThreshold(float f) {
            this.mVelocityThreshold = f * 62.5f;
        }

        public DynamicAnimation.MassState updateValueAndVelocity(float f, float f5, long j2) {
            float f8 = (float) j2;
            this.mMassState.mVelocity = (float) (Math.exp((double) ((f8 / 1000.0f) * this.mFriction)) * ((double) f5));
            DynamicAnimation.MassState massState = this.mMassState;
            float f10 = this.mFriction;
            massState.mValue = (float) ((Math.exp((double) ((f10 * f8) / 1000.0f)) * ((double) (f5 / f10))) + ((double) (f - (f5 / f10))));
            DynamicAnimation.MassState massState2 = this.mMassState;
            if (isAtEquilibrium(massState2.mValue, massState2.mVelocity)) {
                this.mMassState.mVelocity = 0.0f;
            }
            return this.mMassState;
        }
    }

    public FlingAnimation(FloatValueHolder floatValueHolder) {
        super(floatValueHolder);
        DragForce dragForce = new DragForce();
        this.mFlingForce = dragForce;
        dragForce.setValueThreshold(getValueThreshold());
    }

    public boolean isAtEquilibrium(float f, float f5) {
        if (f >= this.mMaxValue || f <= this.mMinValue || this.mFlingForce.isAtEquilibrium(f, f5)) {
            return true;
        }
        return false;
    }

    public FlingAnimation setFriction(float f) {
        if (f > 0.0f) {
            this.mFlingForce.setFrictionScalar(f);
            return this;
        }
        throw new IllegalArgumentException("Friction must be positive");
    }

    public FlingAnimation setMaxValue(float f) {
        super.setMaxValue(f);
        return this;
    }

    public FlingAnimation setMinValue(float f) {
        super.setMinValue(f);
        return this;
    }

    public FlingAnimation setStartVelocity(float f) {
        super.setStartVelocity(f);
        return this;
    }

    public void setValueThreshold(float f) {
        this.mFlingForce.setValueThreshold(f);
    }

    public boolean updateValueAndVelocity(long j2) {
        DynamicAnimation.MassState updateValueAndVelocity = this.mFlingForce.updateValueAndVelocity(this.mValue, this.mVelocity, j2);
        float f = updateValueAndVelocity.mValue;
        this.mValue = f;
        float f5 = updateValueAndVelocity.mVelocity;
        this.mVelocity = f5;
        float f8 = this.mMinValue;
        if (f < f8) {
            this.mValue = f8;
            return true;
        }
        float f10 = this.mMaxValue;
        if (f > f10) {
            this.mValue = f10;
            return true;
        } else if (isAtEquilibrium(f, f5)) {
            return true;
        } else {
            return false;
        }
    }

    public <K> FlingAnimation(K k, FloatPropertyCompat<K> floatPropertyCompat) {
        super(k, floatPropertyCompat);
        DragForce dragForce = new DragForce();
        this.mFlingForce = dragForce;
        dragForce.setValueThreshold(getValueThreshold());
    }
}
