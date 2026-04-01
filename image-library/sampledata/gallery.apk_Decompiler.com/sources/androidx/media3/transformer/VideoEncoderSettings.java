package androidx.media3.transformer;

import N2.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VideoEncoderSettings {
    public static final VideoEncoderSettings DEFAULT = new Builder().build();
    public final int bitrate;
    public final int bitrateMode;
    public final float iFrameIntervalSeconds;
    public final int level;
    public final int maxBFrames;
    public final int numBidirectionalTemporalLayers;
    public final int numNonBidirectionalTemporalLayers;
    public final int operatingRate;
    public final int priority;
    public final int profile;
    public final long repeatPreviousFrameIntervalUs;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private int bitrate;
        private int bitrateMode;
        private float iFrameIntervalSeconds;
        private int level;
        private int maxBFrames;
        private int numBidirectionalTemporalLayers;
        private int numNonBidirectionalTemporalLayers;
        private int operatingRate;
        private int priority;
        private int profile;
        private long repeatPreviousFrameIntervalUs;

        public VideoEncoderSettings build() {
            return new VideoEncoderSettings(this.bitrate, this.bitrateMode, this.profile, this.level, this.iFrameIntervalSeconds, this.operatingRate, this.priority, this.repeatPreviousFrameIntervalUs, this.maxBFrames, this.numNonBidirectionalTemporalLayers, this.numBidirectionalTemporalLayers);
        }

        public Builder setBitrate(int i2) {
            this.bitrate = i2;
            return this;
        }

        public Builder setEncodingProfileLevel(int i2, int i7) {
            this.profile = i2;
            this.level = i7;
            return this;
        }

        public Builder setMaxBFrames(int i2) {
            this.maxBFrames = i2;
            return this;
        }

        public Builder setTemporalLayers(int i2, int i7) {
            this.numNonBidirectionalTemporalLayers = i2;
            this.numBidirectionalTemporalLayers = i7;
            return this;
        }

        public Builder() {
            this.bitrate = -1;
            this.bitrateMode = 1;
            this.profile = -1;
            this.level = -1;
            this.iFrameIntervalSeconds = 1.0f;
            this.operatingRate = -1;
            this.priority = -1;
            this.repeatPreviousFrameIntervalUs = -1;
            this.maxBFrames = -1;
            this.numNonBidirectionalTemporalLayers = -1;
            this.numBidirectionalTemporalLayers = -1;
        }

        private Builder(VideoEncoderSettings videoEncoderSettings) {
            this.bitrate = videoEncoderSettings.bitrate;
            this.bitrateMode = videoEncoderSettings.bitrateMode;
            this.profile = videoEncoderSettings.profile;
            this.level = videoEncoderSettings.level;
            this.iFrameIntervalSeconds = videoEncoderSettings.iFrameIntervalSeconds;
            this.operatingRate = videoEncoderSettings.operatingRate;
            this.priority = videoEncoderSettings.priority;
            this.repeatPreviousFrameIntervalUs = videoEncoderSettings.repeatPreviousFrameIntervalUs;
            this.maxBFrames = videoEncoderSettings.maxBFrames;
            this.numNonBidirectionalTemporalLayers = videoEncoderSettings.numNonBidirectionalTemporalLayers;
            this.numBidirectionalTemporalLayers = videoEncoderSettings.numBidirectionalTemporalLayers;
        }
    }

    public Builder buildUpon() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VideoEncoderSettings)) {
            return false;
        }
        VideoEncoderSettings videoEncoderSettings = (VideoEncoderSettings) obj;
        if (this.bitrate == videoEncoderSettings.bitrate && this.bitrateMode == videoEncoderSettings.bitrateMode && this.profile == videoEncoderSettings.profile && this.level == videoEncoderSettings.level && this.iFrameIntervalSeconds == videoEncoderSettings.iFrameIntervalSeconds && this.operatingRate == videoEncoderSettings.operatingRate && this.priority == videoEncoderSettings.priority && this.repeatPreviousFrameIntervalUs == videoEncoderSettings.repeatPreviousFrameIntervalUs && this.maxBFrames == videoEncoderSettings.maxBFrames && this.numNonBidirectionalTemporalLayers == videoEncoderSettings.numNonBidirectionalTemporalLayers && this.numBidirectionalTemporalLayers == videoEncoderSettings.numBidirectionalTemporalLayers) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int floatToIntBits = Float.floatToIntBits(this.iFrameIntervalSeconds);
        long j2 = this.repeatPreviousFrameIntervalUs;
        return ((((((((((((floatToIntBits + ((((((((217 + this.bitrate) * 31) + this.bitrateMode) * 31) + this.profile) * 31) + this.level) * 31)) * 31) + this.operatingRate) * 31) + this.priority) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + this.maxBFrames) * 31) + this.numNonBidirectionalTemporalLayers) * 31) + this.numBidirectionalTemporalLayers;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("VideoEncoderSettings{bitrate=");
        sb2.append(this.bitrate);
        sb2.append(", bitrateMode=");
        sb2.append(this.bitrateMode);
        sb2.append(", profile=");
        sb2.append(this.profile);
        sb2.append(", level=");
        sb2.append(this.level);
        sb2.append(", iFrameIntervalSeconds=");
        sb2.append(this.iFrameIntervalSeconds);
        sb2.append(", operatingRate=");
        sb2.append(this.operatingRate);
        sb2.append(", priority=");
        sb2.append(this.priority);
        sb2.append(", repeatPreviousFrameIntervalUs=");
        sb2.append(this.repeatPreviousFrameIntervalUs);
        sb2.append(", maxBFrames=");
        sb2.append(this.maxBFrames);
        sb2.append(", numNonBidirectionalTemporalLayers=");
        sb2.append(this.numNonBidirectionalTemporalLayers);
        sb2.append(", numBidirectionalTemporalLayers=");
        return j.e(sb2, this.numBidirectionalTemporalLayers, '}');
    }

    private VideoEncoderSettings(int i2, int i7, int i8, int i10, float f, int i11, int i12, long j2, int i13, int i14, int i15) {
        this.bitrate = i2;
        this.bitrateMode = i7;
        this.profile = i8;
        this.level = i10;
        this.iFrameIntervalSeconds = f;
        this.operatingRate = i11;
        this.priority = i12;
        this.repeatPreviousFrameIntervalUs = j2;
        this.maxBFrames = i13;
        this.numNonBidirectionalTemporalLayers = i14;
        this.numBidirectionalTemporalLayers = i15;
    }
}
