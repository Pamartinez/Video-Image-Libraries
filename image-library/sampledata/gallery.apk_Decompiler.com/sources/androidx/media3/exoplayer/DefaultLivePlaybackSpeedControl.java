package androidx.media3.exoplayer;

import android.os.SystemClock;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Util;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DefaultLivePlaybackSpeedControl implements LivePlaybackSpeedControl {
    private float adjustedPlaybackSpeed;
    private long currentTargetLiveOffsetUs;
    private final float fallbackMaxPlaybackSpeed;
    private final float fallbackMinPlaybackSpeed;
    private long idealTargetLiveOffsetUs;
    private long lastPlaybackSpeedUpdateMs;
    private final long maxLiveOffsetErrorUsForUnitSpeed;
    private float maxPlaybackSpeed;
    private long maxTargetLiveOffsetUs;
    private long mediaConfigurationTargetLiveOffsetUs;
    private float minPlaybackSpeed;
    private final float minPossibleLiveOffsetSmoothingFactor;
    private long minTargetLiveOffsetUs;
    private final long minUpdateIntervalMs;
    private final float proportionalControlFactor;
    private long smoothedMinPossibleLiveOffsetDeviationUs;
    private long smoothedMinPossibleLiveOffsetUs;
    private long targetLiveOffsetOverrideUs;
    private final long targetLiveOffsetRebufferDeltaUs;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private float fallbackMaxPlaybackSpeed = 1.03f;
        private float fallbackMinPlaybackSpeed = 0.97f;
        private long maxLiveOffsetErrorUsForUnitSpeed = Util.msToUs(20);
        private float minPossibleLiveOffsetSmoothingFactor = 0.999f;
        private long minUpdateIntervalMs = 1000;
        private float proportionalControlFactorUs = 1.0E-7f;
        private long targetLiveOffsetIncrementOnRebufferUs = Util.msToUs(500);

        public DefaultLivePlaybackSpeedControl build() {
            return new DefaultLivePlaybackSpeedControl(this.fallbackMinPlaybackSpeed, this.fallbackMaxPlaybackSpeed, this.minUpdateIntervalMs, this.proportionalControlFactorUs, this.maxLiveOffsetErrorUsForUnitSpeed, this.targetLiveOffsetIncrementOnRebufferUs, this.minPossibleLiveOffsetSmoothingFactor);
        }
    }

    private void adjustTargetLiveOffsetUs(long j2) {
        long j3 = (this.smoothedMinPossibleLiveOffsetDeviationUs * 3) + this.smoothedMinPossibleLiveOffsetUs;
        if (this.currentTargetLiveOffsetUs > j3) {
            float msToUs = (float) Util.msToUs(this.minUpdateIntervalMs);
            long j8 = ((long) ((this.adjustedPlaybackSpeed - 1.0f) * msToUs)) + ((long) ((this.maxPlaybackSpeed - 1.0f) * msToUs));
            long[] jArr = {j3, this.idealTargetLiveOffsetUs, this.currentTargetLiveOffsetUs - j8};
            long j10 = jArr[0];
            for (int i2 = 1; i2 < 3; i2++) {
                long j11 = jArr[i2];
                if (j11 > j10) {
                    j10 = j11;
                }
            }
            this.currentTargetLiveOffsetUs = j10;
            return;
        }
        long constrainValue = Util.constrainValue(j2 - ((long) (Math.max(0.0f, this.adjustedPlaybackSpeed - 1.0f) / this.proportionalControlFactor)), this.currentTargetLiveOffsetUs, j3);
        this.currentTargetLiveOffsetUs = constrainValue;
        long j12 = this.maxTargetLiveOffsetUs;
        if (j12 != -9223372036854775807L && constrainValue > j12) {
            this.currentTargetLiveOffsetUs = j12;
        }
    }

    private void maybeResetTargetLiveOffsetUs() {
        long j2;
        long j3 = this.mediaConfigurationTargetLiveOffsetUs;
        if (j3 != -9223372036854775807L) {
            j2 = this.targetLiveOffsetOverrideUs;
            if (j2 == -9223372036854775807L) {
                long j8 = this.minTargetLiveOffsetUs;
                if (j8 != -9223372036854775807L && j3 < j8) {
                    j3 = j8;
                }
                j2 = this.maxTargetLiveOffsetUs;
                if (j2 == -9223372036854775807L || j3 <= j2) {
                    j2 = j3;
                }
            }
        } else {
            j2 = -9223372036854775807L;
        }
        if (this.idealTargetLiveOffsetUs != j2) {
            this.idealTargetLiveOffsetUs = j2;
            this.currentTargetLiveOffsetUs = j2;
            this.smoothedMinPossibleLiveOffsetUs = -9223372036854775807L;
            this.smoothedMinPossibleLiveOffsetDeviationUs = -9223372036854775807L;
            this.lastPlaybackSpeedUpdateMs = -9223372036854775807L;
        }
    }

    private static long smooth(long j2, long j3, float f) {
        return (long) (((1.0f - f) * ((float) j3)) + (((float) j2) * f));
    }

    private void updateSmoothedMinPossibleLiveOffsetUs(long j2, long j3) {
        long j8 = j2 - j3;
        long j10 = this.smoothedMinPossibleLiveOffsetUs;
        if (j10 == -9223372036854775807L) {
            this.smoothedMinPossibleLiveOffsetUs = j8;
            this.smoothedMinPossibleLiveOffsetDeviationUs = 0;
            return;
        }
        long max = Math.max(j8, smooth(j10, j8, this.minPossibleLiveOffsetSmoothingFactor));
        this.smoothedMinPossibleLiveOffsetUs = max;
        this.smoothedMinPossibleLiveOffsetDeviationUs = smooth(this.smoothedMinPossibleLiveOffsetDeviationUs, Math.abs(j8 - max), this.minPossibleLiveOffsetSmoothingFactor);
    }

    public float getAdjustedPlaybackSpeed(long j2, long j3) {
        if (this.mediaConfigurationTargetLiveOffsetUs == -9223372036854775807L) {
            return 1.0f;
        }
        updateSmoothedMinPossibleLiveOffsetUs(j2, j3);
        if (this.lastPlaybackSpeedUpdateMs != -9223372036854775807L && SystemClock.elapsedRealtime() - this.lastPlaybackSpeedUpdateMs < this.minUpdateIntervalMs) {
            return this.adjustedPlaybackSpeed;
        }
        this.lastPlaybackSpeedUpdateMs = SystemClock.elapsedRealtime();
        adjustTargetLiveOffsetUs(j2);
        long j8 = j2 - this.currentTargetLiveOffsetUs;
        if (Math.abs(j8) < this.maxLiveOffsetErrorUsForUnitSpeed) {
            this.adjustedPlaybackSpeed = 1.0f;
        } else {
            this.adjustedPlaybackSpeed = Util.constrainValue((this.proportionalControlFactor * ((float) j8)) + 1.0f, this.minPlaybackSpeed, this.maxPlaybackSpeed);
        }
        return this.adjustedPlaybackSpeed;
    }

    public long getTargetLiveOffsetUs() {
        return this.currentTargetLiveOffsetUs;
    }

    public void notifyRebuffer() {
        long j2 = this.currentTargetLiveOffsetUs;
        if (j2 != -9223372036854775807L) {
            long j3 = j2 + this.targetLiveOffsetRebufferDeltaUs;
            this.currentTargetLiveOffsetUs = j3;
            long j8 = this.maxTargetLiveOffsetUs;
            if (j8 != -9223372036854775807L && j3 > j8) {
                this.currentTargetLiveOffsetUs = j8;
            }
            this.lastPlaybackSpeedUpdateMs = -9223372036854775807L;
        }
    }

    public void setLiveConfiguration(MediaItem.LiveConfiguration liveConfiguration) {
        this.mediaConfigurationTargetLiveOffsetUs = Util.msToUs(liveConfiguration.targetOffsetMs);
        this.minTargetLiveOffsetUs = Util.msToUs(liveConfiguration.minOffsetMs);
        this.maxTargetLiveOffsetUs = Util.msToUs(liveConfiguration.maxOffsetMs);
        float f = liveConfiguration.minPlaybackSpeed;
        if (f == -3.4028235E38f) {
            f = this.fallbackMinPlaybackSpeed;
        }
        this.minPlaybackSpeed = f;
        float f5 = liveConfiguration.maxPlaybackSpeed;
        if (f5 == -3.4028235E38f) {
            f5 = this.fallbackMaxPlaybackSpeed;
        }
        this.maxPlaybackSpeed = f5;
        if (f == 1.0f && f5 == 1.0f) {
            this.mediaConfigurationTargetLiveOffsetUs = -9223372036854775807L;
        }
        maybeResetTargetLiveOffsetUs();
    }

    public void setTargetLiveOffsetOverrideUs(long j2) {
        this.targetLiveOffsetOverrideUs = j2;
        maybeResetTargetLiveOffsetUs();
    }

    private DefaultLivePlaybackSpeedControl(float f, float f5, long j2, float f8, long j3, long j8, float f10) {
        this.fallbackMinPlaybackSpeed = f;
        this.fallbackMaxPlaybackSpeed = f5;
        this.minUpdateIntervalMs = j2;
        this.proportionalControlFactor = f8;
        this.maxLiveOffsetErrorUsForUnitSpeed = j3;
        this.targetLiveOffsetRebufferDeltaUs = j8;
        this.minPossibleLiveOffsetSmoothingFactor = f10;
        this.mediaConfigurationTargetLiveOffsetUs = -9223372036854775807L;
        this.targetLiveOffsetOverrideUs = -9223372036854775807L;
        this.minTargetLiveOffsetUs = -9223372036854775807L;
        this.maxTargetLiveOffsetUs = -9223372036854775807L;
        this.minPlaybackSpeed = f;
        this.maxPlaybackSpeed = f5;
        this.adjustedPlaybackSpeed = 1.0f;
        this.lastPlaybackSpeedUpdateMs = -9223372036854775807L;
        this.idealTargetLiveOffsetUs = -9223372036854775807L;
        this.currentTargetLiveOffsetUs = -9223372036854775807L;
        this.smoothedMinPossibleLiveOffsetUs = -9223372036854775807L;
        this.smoothedMinPossibleLiveOffsetDeviationUs = -9223372036854775807L;
    }
}
