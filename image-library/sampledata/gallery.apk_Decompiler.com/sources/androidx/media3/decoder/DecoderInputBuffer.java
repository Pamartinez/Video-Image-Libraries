package androidx.media3.decoder;

import A.a;
import androidx.media3.common.MediaLibraryInfo;
import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DecoderInputBuffer extends Buffer {
    private final int bufferReplacementMode;
    public final CryptoInfo cryptoInfo;
    public ByteBuffer data;
    private final int paddingSize;
    public ByteBuffer supplementalData;
    public long timeUs;
    public boolean waitingForKeys;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class InsufficientCapacityException extends IllegalStateException {
        public final int currentCapacity;
        public final int requiredCapacity;

        public InsufficientCapacityException(int i2, int i7) {
            super(a.d(i2, i7, "Buffer too small (", " < ", ")"));
            this.currentCapacity = i2;
            this.requiredCapacity = i7;
        }
    }

    static {
        MediaLibraryInfo.registerModule("media3.decoder");
    }

    public DecoderInputBuffer(int i2) {
        this(i2, 0);
    }

    private ByteBuffer createReplacementByteBuffer(int i2) {
        int i7;
        int i8 = this.bufferReplacementMode;
        if (i8 == 1) {
            return ByteBuffer.allocate(i2);
        }
        if (i8 == 2) {
            return ByteBuffer.allocateDirect(i2);
        }
        ByteBuffer byteBuffer = this.data;
        if (byteBuffer == null) {
            i7 = 0;
        } else {
            i7 = byteBuffer.capacity();
        }
        throw new InsufficientCapacityException(i7, i2);
    }

    public void clear() {
        super.clear();
        ByteBuffer byteBuffer = this.data;
        if (byteBuffer != null) {
            byteBuffer.clear();
        }
        ByteBuffer byteBuffer2 = this.supplementalData;
        if (byteBuffer2 != null) {
            byteBuffer2.clear();
        }
        this.waitingForKeys = false;
    }

    public void ensureSpaceForWrite(int i2) {
        int i7 = i2 + this.paddingSize;
        ByteBuffer byteBuffer = this.data;
        if (byteBuffer == null) {
            this.data = createReplacementByteBuffer(i7);
            return;
        }
        int capacity = byteBuffer.capacity();
        int position = byteBuffer.position();
        int i8 = i7 + position;
        if (capacity >= i8) {
            this.data = byteBuffer;
            return;
        }
        ByteBuffer createReplacementByteBuffer = createReplacementByteBuffer(i8);
        createReplacementByteBuffer.order(byteBuffer.order());
        if (position > 0) {
            byteBuffer.flip();
            createReplacementByteBuffer.put(byteBuffer);
        }
        this.data = createReplacementByteBuffer;
    }

    public final void flip() {
        ByteBuffer byteBuffer = this.data;
        if (byteBuffer != null) {
            byteBuffer.flip();
        }
        ByteBuffer byteBuffer2 = this.supplementalData;
        if (byteBuffer2 != null) {
            byteBuffer2.flip();
        }
    }

    public final boolean isEncrypted() {
        return getFlag(1073741824);
    }

    public void resetSupplementalData(int i2) {
        ByteBuffer byteBuffer = this.supplementalData;
        if (byteBuffer == null || byteBuffer.capacity() < i2) {
            this.supplementalData = ByteBuffer.allocate(i2);
        } else {
            this.supplementalData.clear();
        }
    }

    public DecoderInputBuffer(int i2, int i7) {
        this.cryptoInfo = new CryptoInfo();
        this.bufferReplacementMode = i2;
        this.paddingSize = i7;
    }
}
