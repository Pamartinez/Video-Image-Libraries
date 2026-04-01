package androidx.core.view;

import android.view.MotionEvent;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class VelocityTrackerFallback {
    private int mDataPointsBufferLastUsedIndex = 0;
    private int mDataPointsBufferSize = 0;
    private final long[] mEventTimes = new long[20];
    private float mLastComputedVelocity = 0.0f;
    private final float[] mMovements = new float[20];

    private void clear() {
        this.mDataPointsBufferSize = 0;
        this.mLastComputedVelocity = 0.0f;
    }

    private float getCurrentVelocity() {
        long[] jArr;
        long j2;
        int i2 = this.mDataPointsBufferSize;
        if (i2 < 2) {
            return 0.0f;
        }
        int i7 = this.mDataPointsBufferLastUsedIndex;
        int i8 = ((i7 + 20) - (i2 - 1)) % 20;
        long j3 = this.mEventTimes[i7];
        while (true) {
            jArr = this.mEventTimes;
            j2 = jArr[i8];
            if (j3 - j2 <= 100) {
                break;
            }
            this.mDataPointsBufferSize--;
            i8 = (i8 + 1) % 20;
        }
        int i10 = this.mDataPointsBufferSize;
        if (i10 < 2) {
            return 0.0f;
        }
        if (i10 == 2) {
            int i11 = (i8 + 1) % 20;
            long j8 = jArr[i11];
            if (j2 == j8) {
                return 0.0f;
            }
            return this.mMovements[i11] / ((float) (j8 - j2));
        }
        float f = 0.0f;
        int i12 = 0;
        for (int i13 = 0; i13 < this.mDataPointsBufferSize - 1; i13++) {
            int i14 = i13 + i8;
            long[] jArr2 = this.mEventTimes;
            long j10 = jArr2[i14 % 20];
            int i15 = (i14 + 1) % 20;
            if (jArr2[i15] != j10) {
                i12++;
                float kineticEnergyToVelocity = kineticEnergyToVelocity(f);
                float f5 = this.mMovements[i15] / ((float) (this.mEventTimes[i15] - j10));
                float abs = (Math.abs(f5) * (f5 - kineticEnergyToVelocity)) + f;
                if (i12 == 1) {
                    abs *= 0.5f;
                }
                f = abs;
            }
        }
        return kineticEnergyToVelocity(f);
    }

    private static float kineticEnergyToVelocity(float f) {
        float f5;
        if (f < 0.0f) {
            f5 = -1.0f;
        } else {
            f5 = 1.0f;
        }
        return f5 * ((float) Math.sqrt((double) (Math.abs(f) * 2.0f)));
    }

    public void addMovement(MotionEvent motionEvent) {
        long eventTime = motionEvent.getEventTime();
        if (this.mDataPointsBufferSize != 0 && eventTime - this.mEventTimes[this.mDataPointsBufferLastUsedIndex] > 40) {
            clear();
        }
        int i2 = (this.mDataPointsBufferLastUsedIndex + 1) % 20;
        this.mDataPointsBufferLastUsedIndex = i2;
        int i7 = this.mDataPointsBufferSize;
        if (i7 != 20) {
            this.mDataPointsBufferSize = i7 + 1;
        }
        this.mMovements[i2] = motionEvent.getAxisValue(26);
        this.mEventTimes[this.mDataPointsBufferLastUsedIndex] = eventTime;
    }

    public void computeCurrentVelocity(int i2, float f) {
        float currentVelocity = getCurrentVelocity() * ((float) i2);
        this.mLastComputedVelocity = currentVelocity;
        if (currentVelocity < (-Math.abs(f))) {
            this.mLastComputedVelocity = -Math.abs(f);
        } else if (this.mLastComputedVelocity > Math.abs(f)) {
            this.mLastComputedVelocity = Math.abs(f);
        }
    }

    public float getAxisVelocity(int i2) {
        if (i2 != 26) {
            return 0.0f;
        }
        return this.mLastComputedVelocity;
    }
}
