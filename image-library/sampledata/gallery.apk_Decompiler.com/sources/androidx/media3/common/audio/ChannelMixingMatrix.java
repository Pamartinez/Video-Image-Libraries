package androidx.media3.common.audio;

import A.a;
import androidx.media3.common.util.Assertions;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ChannelMixingMatrix {
    private final float[] coefficients;
    private final int inputChannelCount;
    private final boolean isDiagonal;
    private final boolean isIdentity;
    private final boolean isZero;
    private final int outputChannelCount;

    public ChannelMixingMatrix(int i2, int i7, float[] fArr) {
        boolean z;
        boolean z3;
        boolean z7;
        boolean z9;
        boolean z10;
        boolean z11 = false;
        if (i2 > 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z, "Input channel count must be positive.");
        if (i7 > 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        Assertions.checkArgument(z3, "Output channel count must be positive.");
        if (fArr.length == i2 * i7) {
            z7 = true;
        } else {
            z7 = false;
        }
        Assertions.checkArgument(z7, "Coefficient array length is invalid.");
        this.inputChannelCount = i2;
        this.outputChannelCount = i7;
        this.coefficients = checkCoefficientsValid(fArr);
        boolean z12 = true;
        boolean z13 = true;
        boolean z14 = true;
        for (int i8 = 0; i8 < i2; i8++) {
            for (int i10 = 0; i10 < i7; i10++) {
                float mixingCoefficient = getMixingCoefficient(i8, i10);
                if (i8 == i10) {
                    z10 = true;
                } else {
                    z10 = false;
                }
                if (mixingCoefficient != 1.0f && z10) {
                    z14 = false;
                }
                if (mixingCoefficient != 0.0f) {
                    z12 = false;
                    if (!z10) {
                        z13 = false;
                    }
                }
            }
        }
        this.isZero = z12;
        if (!isSquare() || !z13) {
            z9 = false;
        } else {
            z9 = true;
        }
        this.isDiagonal = z9;
        if (z9 && z14) {
            z11 = true;
        }
        this.isIdentity = z11;
    }

    private static float[] checkCoefficientsValid(float[] fArr) {
        int i2 = 0;
        while (i2 < fArr.length) {
            if (fArr[i2] >= 0.0f) {
                i2++;
            } else {
                throw new IllegalArgumentException(C0212a.j(i2, "Coefficient at index ", " is negative."));
            }
        }
        return fArr;
    }

    private static float[] createConstantGainMixingCoefficients(int i2, int i7) {
        if (i2 == i7) {
            return initializeIdentityMatrix(i7);
        }
        if (i2 == 1 && i7 == 2) {
            return new float[]{1.0f, 1.0f};
        }
        if (i2 == 2 && i7 == 1) {
            return new float[]{0.5f, 0.5f};
        }
        throw new UnsupportedOperationException(a.d(i2, i7, "Default channel mixing coefficients for ", "->", " are not yet implemented."));
    }

    private static float[] createConstantPowerMixingCoefficients(int i2, int i7) {
        if (i7 == 1) {
            return getConstantPowerCoefficientsToMono(i2);
        }
        if (i7 == 2) {
            return getConstantPowerCoefficientsToStereo(i2);
        }
        if (i2 == i7) {
            return initializeIdentityMatrix(i7);
        }
        throw new UnsupportedOperationException(a.d(i2, i7, "Default constant power channel mixing coefficients for ", "->", " are not implemented."));
    }

    public static ChannelMixingMatrix createForConstantGain(int i2, int i7) {
        return new ChannelMixingMatrix(i2, i7, createConstantGainMixingCoefficients(i2, i7));
    }

    public static ChannelMixingMatrix createForConstantPower(int i2, int i7) {
        return new ChannelMixingMatrix(i2, i7, createConstantPowerMixingCoefficients(i2, i7));
    }

    private static float[] getConstantPowerCoefficientsToMono(int i2) {
        switch (i2) {
            case 1:
                return new float[]{1.0f};
            case 2:
                return new float[]{0.7071f, 0.7071f};
            case 3:
                return new float[]{0.7071f, 0.7071f, 1.0f};
            case 4:
                return new float[]{0.7071f, 0.7071f, 0.5f, 0.5f};
            case 5:
                return new float[]{0.7071f, 0.7071f, 1.0f, 0.5f, 0.5f};
            case 6:
                return new float[]{0.7071f, 0.7071f, 1.0f, 0.7071f, 0.5f, 0.5f};
            default:
                throw new UnsupportedOperationException(C0212a.j(i2, "Default constant power channel mixing coefficients for ", "->1 are not implemented."));
        }
    }

    private static float[] getConstantPowerCoefficientsToStereo(int i2) {
        switch (i2) {
            case 1:
                return new float[]{0.7071f, 0.7071f};
            case 2:
                return new float[]{1.0f, 0.0f, 0.0f, 1.0f};
            case 3:
                return new float[]{1.0f, 0.0f, 0.7071f, 0.0f, 1.0f, 0.7071f};
            case 4:
                return new float[]{1.0f, 0.0f, 0.7071f, 0.0f, 0.0f, 1.0f, 0.0f, 0.7071f};
            case 5:
                return new float[]{1.0f, 0.0f, 0.7071f, 0.7071f, 0.0f, 0.0f, 1.0f, 0.7071f, 0.0f, 0.7071f};
            case 6:
                return new float[]{1.0f, 0.0f, 0.7071f, 0.5f, 0.7071f, 0.0f, 0.0f, 1.0f, 0.7071f, 0.5f, 0.0f, 0.7071f};
            default:
                throw new UnsupportedOperationException(C0212a.j(i2, "Default constant power channel mixing coefficients for ", "->2 are not implemented."));
        }
    }

    private static float[] initializeIdentityMatrix(int i2) {
        float[] fArr = new float[(i2 * i2)];
        for (int i7 = 0; i7 < i2; i7++) {
            fArr[(i2 * i7) + i7] = 1.0f;
        }
        return fArr;
    }

    public int getInputChannelCount() {
        return this.inputChannelCount;
    }

    public float getMixingCoefficient(int i2, int i7) {
        return this.coefficients[(i2 * this.outputChannelCount) + i7];
    }

    public int getOutputChannelCount() {
        return this.outputChannelCount;
    }

    public boolean isIdentity() {
        return this.isIdentity;
    }

    public boolean isSquare() {
        if (this.inputChannelCount == this.outputChannelCount) {
            return true;
        }
        return false;
    }

    public boolean isZero() {
        return this.isZero;
    }
}
