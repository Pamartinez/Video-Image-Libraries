package androidx.media3.exoplayer;

import F2.C0010c0;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ScrubbingModeParameters {
    public static final ScrubbingModeParameters DEFAULT = new Builder().build();
    public final boolean allowSkippingMediaCodecFlush;
    public final C0010c0 disabledTrackTypes;
    public final Double fractionalSeekToleranceAfter;
    public final Double fractionalSeekToleranceBefore;
    @Deprecated
    public final boolean isMediaCodecFlushEnabled;
    public final boolean shouldEnableDynamicScheduling;
    public final boolean shouldIncreaseCodecOperatingRate;
    public final boolean useDecodeOnlyFlag;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        /* access modifiers changed from: private */
        public boolean allowSkippingMediaCodecFlush = true;
        /* access modifiers changed from: private */
        public C0010c0 disabledTrackTypes = C0010c0.x(2, 1, 5);
        /* access modifiers changed from: private */
        public Double fractionalSeekToleranceAfter;
        /* access modifiers changed from: private */
        public Double fractionalSeekToleranceBefore;
        /* access modifiers changed from: private */
        public boolean shouldEnableDynamicScheduling = true;
        /* access modifiers changed from: private */
        public boolean shouldIncreaseCodecOperatingRate = true;
        /* access modifiers changed from: private */
        public boolean useDecodeOnlyFlag = true;

        public ScrubbingModeParameters build() {
            return new ScrubbingModeParameters(this);
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ScrubbingModeParameters)) {
            return false;
        }
        ScrubbingModeParameters scrubbingModeParameters = (ScrubbingModeParameters) obj;
        if (this.disabledTrackTypes.equals(scrubbingModeParameters.disabledTrackTypes) && this.allowSkippingMediaCodecFlush == scrubbingModeParameters.allowSkippingMediaCodecFlush && Objects.equals(this.fractionalSeekToleranceBefore, scrubbingModeParameters.fractionalSeekToleranceBefore) && Objects.equals(this.fractionalSeekToleranceAfter, scrubbingModeParameters.fractionalSeekToleranceAfter) && this.shouldIncreaseCodecOperatingRate == scrubbingModeParameters.shouldIncreaseCodecOperatingRate && this.shouldEnableDynamicScheduling == scrubbingModeParameters.shouldEnableDynamicScheduling && this.useDecodeOnlyFlag == scrubbingModeParameters.useDecodeOnlyFlag) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.disabledTrackTypes, this.fractionalSeekToleranceBefore, this.fractionalSeekToleranceAfter, Boolean.valueOf(this.shouldIncreaseCodecOperatingRate), Boolean.valueOf(this.allowSkippingMediaCodecFlush), Boolean.valueOf(this.shouldEnableDynamicScheduling), Boolean.valueOf(this.useDecodeOnlyFlag)});
    }

    private ScrubbingModeParameters(Builder builder) {
        this.disabledTrackTypes = builder.disabledTrackTypes;
        this.fractionalSeekToleranceBefore = builder.fractionalSeekToleranceBefore;
        this.fractionalSeekToleranceAfter = builder.fractionalSeekToleranceAfter;
        this.shouldIncreaseCodecOperatingRate = builder.shouldIncreaseCodecOperatingRate;
        this.isMediaCodecFlushEnabled = !builder.allowSkippingMediaCodecFlush;
        this.allowSkippingMediaCodecFlush = builder.allowSkippingMediaCodecFlush;
        this.shouldEnableDynamicScheduling = builder.shouldEnableDynamicScheduling;
        this.useDecodeOnlyFlag = builder.useDecodeOnlyFlag;
    }
}
