package androidx.media3.extractor;

import androidx.media3.common.DataReader;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.util.Util;
import java.io.EOFException;
import java.io.InterruptedIOException;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DefaultExtractorInput implements ExtractorInput {
    private final DataReader dataReader;
    private byte[] peekBuffer = new byte[65536];
    private int peekBufferLength;
    private int peekBufferPosition;
    private long position;
    private final byte[] scratchSpace = new byte[4096];
    private final long streamLength;

    static {
        MediaLibraryInfo.registerModule("media3.extractor");
    }

    public DefaultExtractorInput(DataReader dataReader2, long j2, long j3) {
        this.dataReader = dataReader2;
        this.position = j2;
        this.streamLength = j3;
    }

    private void commitBytesRead(int i2) {
        if (i2 != -1) {
            this.position += (long) i2;
        }
    }

    private void ensureSpaceForPeek(int i2) {
        int i7 = this.peekBufferPosition + i2;
        byte[] bArr = this.peekBuffer;
        if (i7 > bArr.length) {
            this.peekBuffer = Arrays.copyOf(this.peekBuffer, Util.constrainValue(bArr.length * 2, 65536 + i7, i7 + 524288));
        }
    }

    private int readFromPeekBuffer(byte[] bArr, int i2, int i7) {
        int i8 = this.peekBufferLength;
        if (i8 == 0) {
            return 0;
        }
        int min = Math.min(i8, i7);
        System.arraycopy(this.peekBuffer, 0, bArr, i2, min);
        updatePeekBuffer(min);
        return min;
    }

    private int readFromUpstream(byte[] bArr, int i2, int i7, int i8, boolean z) {
        if (!Thread.interrupted()) {
            int read = this.dataReader.read(bArr, i2 + i8, i7 - i8);
            if (read != -1) {
                return i8 + read;
            }
            if (i8 == 0 && z) {
                return -1;
            }
            throw new EOFException();
        }
        throw new InterruptedIOException();
    }

    private int skipFromPeekBuffer(int i2) {
        int min = Math.min(this.peekBufferLength, i2);
        updatePeekBuffer(min);
        return min;
    }

    private void updatePeekBuffer(int i2) {
        byte[] bArr;
        int i7 = this.peekBufferLength - i2;
        this.peekBufferLength = i7;
        this.peekBufferPosition = 0;
        byte[] bArr2 = this.peekBuffer;
        if (i7 < bArr2.length - 524288) {
            bArr = new byte[(65536 + i7)];
        } else {
            bArr = bArr2;
        }
        System.arraycopy(bArr2, i2, bArr, 0, i7);
        this.peekBuffer = bArr;
    }

    public boolean advancePeekPosition(int i2, boolean z) {
        ensureSpaceForPeek(i2);
        int i7 = this.peekBufferLength - this.peekBufferPosition;
        while (i7 < i2) {
            DefaultExtractorInput defaultExtractorInput = this;
            int i8 = i2;
            boolean z3 = z;
            i7 = defaultExtractorInput.readFromUpstream(this.peekBuffer, this.peekBufferPosition, i8, i7, z3);
            if (i7 == -1) {
                return false;
            }
            defaultExtractorInput.peekBufferLength = defaultExtractorInput.peekBufferPosition + i7;
            this = defaultExtractorInput;
            i2 = i8;
            z = z3;
        }
        this.peekBufferPosition += i2;
        return true;
    }

    public long getLength() {
        return this.streamLength;
    }

    public long getPeekPosition() {
        return this.position + ((long) this.peekBufferPosition);
    }

    public long getPosition() {
        return this.position;
    }

    public int peek(byte[] bArr, int i2, int i7) {
        int i8;
        DefaultExtractorInput defaultExtractorInput;
        ensureSpaceForPeek(i7);
        int i10 = this.peekBufferLength;
        int i11 = this.peekBufferPosition;
        int i12 = i10 - i11;
        if (i12 == 0) {
            defaultExtractorInput = this;
            i8 = defaultExtractorInput.readFromUpstream(this.peekBuffer, i11, i7, 0, true);
            if (i8 == -1) {
                return -1;
            }
            defaultExtractorInput.peekBufferLength += i8;
        } else {
            defaultExtractorInput = this;
            i8 = Math.min(i7, i12);
        }
        System.arraycopy(defaultExtractorInput.peekBuffer, defaultExtractorInput.peekBufferPosition, bArr, i2, i8);
        defaultExtractorInput.peekBufferPosition += i8;
        return i8;
    }

    public boolean peekFully(byte[] bArr, int i2, int i7, boolean z) {
        if (!advancePeekPosition(i7, z)) {
            return false;
        }
        System.arraycopy(this.peekBuffer, this.peekBufferPosition - i7, bArr, i2, i7);
        return true;
    }

    public int read(byte[] bArr, int i2, int i7) {
        DefaultExtractorInput defaultExtractorInput;
        int readFromPeekBuffer = readFromPeekBuffer(bArr, i2, i7);
        if (readFromPeekBuffer == 0) {
            defaultExtractorInput = this;
            readFromPeekBuffer = defaultExtractorInput.readFromUpstream(bArr, i2, i7, 0, true);
        } else {
            defaultExtractorInput = this;
        }
        defaultExtractorInput.commitBytesRead(readFromPeekBuffer);
        return readFromPeekBuffer;
    }

    public boolean readFully(byte[] bArr, int i2, int i7, boolean z) {
        int readFromPeekBuffer = readFromPeekBuffer(bArr, i2, i7);
        while (readFromPeekBuffer < i7 && readFromPeekBuffer != -1) {
            readFromPeekBuffer = readFromUpstream(bArr, i2, i7, readFromPeekBuffer, z);
        }
        commitBytesRead(readFromPeekBuffer);
        return readFromPeekBuffer != -1;
    }

    public void resetPeekPosition() {
        this.peekBufferPosition = 0;
    }

    public int skip(int i2) {
        DefaultExtractorInput defaultExtractorInput;
        int skipFromPeekBuffer = skipFromPeekBuffer(i2);
        if (skipFromPeekBuffer == 0) {
            byte[] bArr = this.scratchSpace;
            defaultExtractorInput = this;
            skipFromPeekBuffer = defaultExtractorInput.readFromUpstream(bArr, 0, Math.min(i2, bArr.length), 0, true);
        } else {
            defaultExtractorInput = this;
        }
        defaultExtractorInput.commitBytesRead(skipFromPeekBuffer);
        return skipFromPeekBuffer;
    }

    public boolean skipFully(int i2, boolean z) {
        int skipFromPeekBuffer = skipFromPeekBuffer(i2);
        while (skipFromPeekBuffer < i2 && skipFromPeekBuffer != -1) {
            skipFromPeekBuffer = readFromUpstream(this.scratchSpace, -skipFromPeekBuffer, Math.min(i2, this.scratchSpace.length + skipFromPeekBuffer), skipFromPeekBuffer, z);
        }
        commitBytesRead(skipFromPeekBuffer);
        return skipFromPeekBuffer != -1;
    }

    public void peekFully(byte[] bArr, int i2, int i7) {
        peekFully(bArr, i2, i7, false);
    }

    public void readFully(byte[] bArr, int i2, int i7) {
        readFully(bArr, i2, i7, false);
    }

    public void skipFully(int i2) {
        skipFully(i2, false);
    }

    public void advancePeekPosition(int i2) {
        advancePeekPosition(i2, false);
    }
}
