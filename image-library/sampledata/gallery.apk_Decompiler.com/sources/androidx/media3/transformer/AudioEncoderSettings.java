package androidx.media3.transformer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AudioEncoderSettings {
    public static final AudioEncoderSettings DEFAULT = new Builder().build();
    public final int bitrate;
    public final int profile;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private int bitrate = -1;
        private int profile = -1;

        public AudioEncoderSettings build() {
            return new AudioEncoderSettings(this.profile, this.bitrate);
        }

        public Builder setBitrate(int i2) {
            this.bitrate = i2;
            return this;
        }
    }

    private AudioEncoderSettings(int i2, int i7) {
        this.profile = i2;
        this.bitrate = i7;
    }
}
