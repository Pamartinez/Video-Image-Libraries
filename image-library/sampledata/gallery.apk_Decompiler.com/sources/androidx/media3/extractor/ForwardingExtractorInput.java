package androidx.media3.extractor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ForwardingExtractorInput implements ExtractorInput {
    private final ExtractorInput input;

    public ForwardingExtractorInput(ExtractorInput extractorInput) {
        this.input = extractorInput;
    }

    public boolean advancePeekPosition(int i2, boolean z) {
        return this.input.advancePeekPosition(i2, z);
    }

    public long getLength() {
        return this.input.getLength();
    }

    public long getPeekPosition() {
        return this.input.getPeekPosition();
    }

    public long getPosition() {
        return this.input.getPosition();
    }

    public int peek(byte[] bArr, int i2, int i7) {
        return this.input.peek(bArr, i2, i7);
    }

    public boolean peekFully(byte[] bArr, int i2, int i7, boolean z) {
        return this.input.peekFully(bArr, i2, i7, z);
    }

    public int read(byte[] bArr, int i2, int i7) {
        return this.input.read(bArr, i2, i7);
    }

    public boolean readFully(byte[] bArr, int i2, int i7, boolean z) {
        return this.input.readFully(bArr, i2, i7, z);
    }

    public void resetPeekPosition() {
        this.input.resetPeekPosition();
    }

    public int skip(int i2) {
        return this.input.skip(i2);
    }

    public boolean skipFully(int i2, boolean z) {
        return this.input.skipFully(i2, z);
    }

    public void advancePeekPosition(int i2) {
        this.input.advancePeekPosition(i2);
    }

    public void peekFully(byte[] bArr, int i2, int i7) {
        this.input.peekFully(bArr, i2, i7);
    }

    public void readFully(byte[] bArr, int i2, int i7) {
        this.input.readFully(bArr, i2, i7);
    }

    public void skipFully(int i2) {
        this.input.skipFully(i2);
    }
}
