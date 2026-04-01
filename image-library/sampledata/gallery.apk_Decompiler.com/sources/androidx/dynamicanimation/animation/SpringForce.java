package androidx.dynamicanimation.animation;

import androidx.dynamicanimation.animation.DynamicAnimation;
import com.samsung.android.gallery.support.utils.MapUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SpringForce {
    private double mDampedFreq;
    double mDampingRatio = 0.5d;
    private double mFinalPosition = Double.MAX_VALUE;
    private double mGammaMinus;
    private double mGammaPlus;
    private boolean mInitialized = false;
    private final DynamicAnimation.MassState mMassState = new DynamicAnimation.MassState();
    double mNaturalFreq = Math.sqrt(1500.0d);
    private double mValueThreshold;
    private double mVelocityThreshold;

    public SpringForce() {
    }

    private void init() {
        if (!this.mInitialized) {
            if (this.mFinalPosition != Double.MAX_VALUE) {
                double d = this.mDampingRatio;
                if (d > 1.0d) {
                    double d2 = this.mNaturalFreq;
                    this.mGammaPlus = (Math.sqrt((d * d) - 1.0d) * d2) + ((-d) * d2);
                    double d3 = this.mDampingRatio;
                    double d5 = this.mNaturalFreq;
                    this.mGammaMinus = ((-d3) * d5) - (Math.sqrt((d3 * d3) - 1.0d) * d5);
                } else if (d >= MapUtil.INVALID_LOCATION && d < 1.0d) {
                    this.mDampedFreq = Math.sqrt(1.0d - (d * d)) * this.mNaturalFreq;
                }
                this.mInitialized = true;
                return;
            }
            throw new IllegalStateException("Error: Final position of the spring must be set before the animation starts");
        }
    }

    public float getFinalPosition() {
        return (float) this.mFinalPosition;
    }

    public boolean isAtEquilibrium(float f, float f5) {
        if (((double) Math.abs(f5)) >= this.mVelocityThreshold || ((double) Math.abs(f - getFinalPosition())) >= this.mValueThreshold) {
            return false;
        }
        return true;
    }

    public SpringForce setDampingRatio(float f) {
        if (f >= 0.0f) {
            this.mDampingRatio = (double) f;
            this.mInitialized = false;
            return this;
        }
        throw new IllegalArgumentException("Damping ratio must be non-negative");
    }

    public SpringForce setFinalPosition(float f) {
        this.mFinalPosition = (double) f;
        return this;
    }

    public SpringForce setStiffness(float f) {
        if (f > 0.0f) {
            this.mNaturalFreq = Math.sqrt((double) f);
            this.mInitialized = false;
            return this;
        }
        throw new IllegalArgumentException("Spring stiffness constant must be positive.");
    }

    public void setValueThreshold(double d) {
        double abs = Math.abs(d);
        this.mValueThreshold = abs;
        this.mVelocityThreshold = abs * 62.5d;
    }

    public DynamicAnimation.MassState updateValues(double d, double d2, long j2) {
        double d3;
        double d5;
        init();
        double d6 = ((double) j2) / 1000.0d;
        double d7 = d - this.mFinalPosition;
        double d9 = this.mDampingRatio;
        if (d9 > 1.0d) {
            double d10 = this.mGammaMinus;
            double d11 = this.mGammaPlus;
            double d12 = d7 - (((d10 * d7) - d2) / (d10 - d11));
            double d13 = ((d7 * d10) - d2) / (d10 - d11);
            d3 = (Math.pow(2.718281828459045d, this.mGammaPlus * d6) * d13) + (Math.pow(2.718281828459045d, d10 * d6) * d12);
            double d14 = this.mGammaMinus;
            double pow = Math.pow(2.718281828459045d, d14 * d6) * d12 * d14;
            double d15 = this.mGammaPlus;
            d5 = (Math.pow(2.718281828459045d, d15 * d6) * d13 * d15) + pow;
        } else if (d9 == 1.0d) {
            double d16 = this.mNaturalFreq;
            double d17 = (d16 * d7) + d2;
            double d18 = (d17 * d6) + d7;
            double pow2 = Math.pow(2.718281828459045d, (-d16) * d6) * d18;
            double pow3 = Math.pow(2.718281828459045d, (-this.mNaturalFreq) * d6) * d18;
            double d19 = this.mNaturalFreq;
            d5 = (Math.pow(2.718281828459045d, (-d19) * d6) * d17) + (pow3 * (-d19));
            d3 = pow2;
        } else {
            double d20 = 1.0d / this.mDampedFreq;
            double d21 = this.mNaturalFreq;
            double d22 = ((d9 * d21 * d7) + d2) * d20;
            d3 = ((Math.sin(this.mDampedFreq * d6) * d22) + (Math.cos(this.mDampedFreq * d6) * d7)) * Math.pow(2.718281828459045d, (-d9) * d21 * d6);
            double d23 = this.mNaturalFreq;
            double d24 = this.mDampingRatio;
            double pow4 = Math.pow(2.718281828459045d, (-d24) * d23 * d6);
            double d25 = this.mDampedFreq;
            double d26 = d6;
            double sin = Math.sin(d25 * d26) * (-d25) * d7;
            double d27 = this.mDampedFreq;
            d5 = (((Math.cos(d27 * d26) * d22 * d27) + sin) * pow4) + ((-d23) * d3 * d24);
        }
        DynamicAnimation.MassState massState = this.mMassState;
        massState.mValue = (float) (d3 + this.mFinalPosition);
        massState.mVelocity = (float) d5;
        return massState;
    }

    public SpringForce(float f) {
        this.mFinalPosition = (double) f;
    }
}
