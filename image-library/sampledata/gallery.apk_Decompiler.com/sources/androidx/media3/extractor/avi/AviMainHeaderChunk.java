package androidx.media3.extractor.avi;

import androidx.media3.common.util.ParsableByteArray;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class AviMainHeaderChunk implements AviChunk {
    public final int flags;
    public final int frameDurationUs;
    public final int streams;
    public final int totalFrames;

    private AviMainHeaderChunk(int i2, int i7, int i8, int i10) {
        this.frameDurationUs = i2;
        this.flags = i7;
        this.totalFrames = i8;
        this.streams = i10;
    }

    public static AviMainHeaderChunk parseFrom(ParsableByteArray parsableByteArray) {
        int readLittleEndianInt = parsableByteArray.readLittleEndianInt();
        parsableByteArray.skipBytes(8);
        int readLittleEndianInt2 = parsableByteArray.readLittleEndianInt();
        int readLittleEndianInt3 = parsableByteArray.readLittleEndianInt();
        parsableByteArray.skipBytes(4);
        int readLittleEndianInt4 = parsableByteArray.readLittleEndianInt();
        parsableByteArray.skipBytes(12);
        return new AviMainHeaderChunk(readLittleEndianInt, readLittleEndianInt2, readLittleEndianInt3, readLittleEndianInt4);
    }

    public int getType() {
        return 1751742049;
    }

    public boolean hasIndex() {
        if ((this.flags & 16) == 16) {
            return true;
        }
        return false;
    }
}
