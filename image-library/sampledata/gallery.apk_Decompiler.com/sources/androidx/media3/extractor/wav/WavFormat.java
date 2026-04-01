package androidx.media3.extractor.wav;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class WavFormat {
    public final int averageBytesPerSecond;
    public final int bitsPerSample;
    public final int blockSize;
    public final byte[] extraData;
    public final int formatType;
    public final int frameRateHz;
    public final int numChannels;

    public WavFormat(int i2, int i7, int i8, int i10, int i11, int i12, byte[] bArr) {
        this.formatType = i2;
        this.numChannels = i7;
        this.frameRateHz = i8;
        this.averageBytesPerSecond = i10;
        this.blockSize = i11;
        this.bitsPerSample = i12;
        this.extraData = bArr;
    }
}
