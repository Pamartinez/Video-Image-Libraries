package androidx.media3.extractor;

import androidx.media3.common.DataReader;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ExtractorInput extends DataReader {
    void advancePeekPosition(int i2);

    boolean advancePeekPosition(int i2, boolean z);

    long getLength();

    long getPeekPosition();

    long getPosition();

    int peek(byte[] bArr, int i2, int i7);

    void peekFully(byte[] bArr, int i2, int i7);

    boolean peekFully(byte[] bArr, int i2, int i7, boolean z);

    int read(byte[] bArr, int i2, int i7);

    void readFully(byte[] bArr, int i2, int i7);

    boolean readFully(byte[] bArr, int i2, int i7, boolean z);

    void resetPeekPosition();

    int skip(int i2);

    void skipFully(int i2);

    boolean skipFully(int i2, boolean z);
}
