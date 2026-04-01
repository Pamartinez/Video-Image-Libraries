package androidx.media3.extractor.avi;

import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class AviStreamHeaderChunk implements AviChunk {
    public final int initialFrames;
    public final int length;
    public final int rate;
    public final int sampleSize;
    public final int scale;
    public final int streamType;
    public final int suggestedBufferSize;

    private AviStreamHeaderChunk(int i2, int i7, int i8, int i10, int i11, int i12, int i13) {
        this.streamType = i2;
        this.initialFrames = i7;
        this.scale = i8;
        this.rate = i10;
        this.length = i11;
        this.suggestedBufferSize = i12;
        this.sampleSize = i13;
    }

    public static AviStreamHeaderChunk parseFrom(ParsableByteArray parsableByteArray) {
        int readLittleEndianInt = parsableByteArray.readLittleEndianInt();
        parsableByteArray.skipBytes(12);
        int readLittleEndianInt2 = parsableByteArray.readLittleEndianInt();
        int readLittleEndianInt3 = parsableByteArray.readLittleEndianInt();
        int readLittleEndianInt4 = parsableByteArray.readLittleEndianInt();
        parsableByteArray.skipBytes(4);
        int readLittleEndianInt5 = parsableByteArray.readLittleEndianInt();
        int readLittleEndianInt6 = parsableByteArray.readLittleEndianInt();
        parsableByteArray.skipBytes(4);
        return new AviStreamHeaderChunk(readLittleEndianInt, readLittleEndianInt2, readLittleEndianInt3, readLittleEndianInt4, readLittleEndianInt5, readLittleEndianInt6, parsableByteArray.readLittleEndianInt());
    }

    public long getDurationUs() {
        return Util.scaleLargeTimestamp((long) this.length, ((long) this.scale) * 1000000, (long) this.rate);
    }

    public int getTrackType() {
        int i2 = this.streamType;
        if (i2 == 1935960438) {
            return 2;
        }
        if (i2 == 1935963489) {
            return 1;
        }
        if (i2 == 1937012852) {
            return 3;
        }
        Log.w("AviStreamHeaderChunk", "Found unsupported streamType fourCC: " + Integer.toHexString(this.streamType));
        return -1;
    }

    public int getType() {
        return 1752331379;
    }
}
