package androidx.transition;

import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class VelocityTracker1D {
    private float[] mDataSamples = new float[20];
    private int mIndex = 0;
    private long[] mTimeSamples;

    public VelocityTracker1D() {
        long[] jArr = new long[20];
        this.mTimeSamples = jArr;
        Arrays.fill(jArr, Long.MIN_VALUE);
    }

    private float kineticEnergyToVelocity(float f) {
        return (float) (Math.sqrt((double) (Math.abs(f) * 2.0f)) * ((double) Math.signum(f)));
    }

    public void addDataPoint(long j2, float f) {
        int i2 = (this.mIndex + 1) % 20;
        this.mIndex = i2;
        this.mTimeSamples[i2] = j2;
        this.mDataSamples[i2] = f;
    }

    public float calculateVelocity() {
        int i2;
        int i7 = this.mIndex;
        if (i7 == 0 && this.mTimeSamples[i7] == Long.MIN_VALUE) {
            return 0.0f;
        }
        long j2 = this.mTimeSamples[i7];
        int i8 = 0;
        long j3 = j2;
        while (true) {
            long j8 = this.mTimeSamples[i7];
            if (j8 != Long.MIN_VALUE) {
                float abs = (float) Math.abs(j8 - j3);
                if (((float) (j2 - j8)) > 100.0f || abs > 40.0f) {
                    break;
                }
                if (i7 == 0) {
                    i7 = 20;
                }
                i7--;
                i8++;
                if (i8 >= 20) {
                    break;
                }
                j3 = j8;
            } else {
                break;
            }
        }
        if (i8 < 2) {
            return 0.0f;
        }
        if (i8 == 2) {
            int i10 = this.mIndex;
            if (i10 == 0) {
                i2 = 19;
            } else {
                i2 = i10 - 1;
            }
            long[] jArr = this.mTimeSamples;
            float f = (float) (jArr[i10] - jArr[i2]);
            if (f == 0.0f) {
                return 0.0f;
            }
            float[] fArr = this.mDataSamples;
            return ((fArr[i10] - fArr[i2]) / f) * 1000.0f;
        }
        int i11 = this.mIndex;
        int i12 = ((i11 - i8) + 21) % 20;
        int i13 = (i11 + 21) % 20;
        long j10 = this.mTimeSamples[i12];
        float f5 = this.mDataSamples[i12];
        int i14 = i12 + 1;
        float f8 = 0.0f;
        for (int i15 = i14 % 20; i15 != i13; i15 = (i15 + 1) % 20) {
            long j11 = this.mTimeSamples[i15];
            float f10 = (float) (j11 - j10);
            if (f10 != 0.0f) {
                float f11 = this.mDataSamples[i15];
                float f12 = (f11 - f5) / f10;
                float abs2 = (Math.abs(f12) * (f12 - kineticEnergyToVelocity(f8))) + f8;
                if (i15 == i14) {
                    abs2 *= 0.5f;
                }
                f8 = abs2;
                f5 = f11;
                j10 = j11;
            }
        }
        return kineticEnergyToVelocity(f8) * 1000.0f;
    }
}
