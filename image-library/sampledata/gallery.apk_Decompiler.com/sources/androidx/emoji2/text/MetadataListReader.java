package androidx.emoji2.text;

import androidx.emoji2.text.flatbuffer.MetadataList;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class MetadataListReader {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ByteBufferReader implements OpenTypeReader {
        private final ByteBuffer mByteBuffer;

        public ByteBufferReader(ByteBuffer byteBuffer) {
            this.mByteBuffer = byteBuffer;
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
        }

        public long getPosition() {
            return (long) this.mByteBuffer.position();
        }

        public int readTag() {
            return this.mByteBuffer.getInt();
        }

        public long readUnsignedInt() {
            return MetadataListReader.toUnsignedInt(this.mByteBuffer.getInt());
        }

        public int readUnsignedShort() {
            return MetadataListReader.toUnsignedShort(this.mByteBuffer.getShort());
        }

        public void skip(int i2) {
            ByteBuffer byteBuffer = this.mByteBuffer;
            byteBuffer.position(byteBuffer.position() + i2);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class OffsetInfo {
        private final long mLength;
        private final long mStartOffset;

        public OffsetInfo(long j2, long j3) {
            this.mStartOffset = j2;
            this.mLength = j3;
        }

        public long getStartOffset() {
            return this.mStartOffset;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OpenTypeReader {
        long getPosition();

        int readTag();

        long readUnsignedInt();

        int readUnsignedShort();

        void skip(int i2);
    }

    private static OffsetInfo findOffsetInfo(OpenTypeReader openTypeReader) {
        long j2;
        openTypeReader.skip(4);
        int readUnsignedShort = openTypeReader.readUnsignedShort();
        if (readUnsignedShort <= 100) {
            openTypeReader.skip(6);
            int i2 = 0;
            while (true) {
                if (i2 >= readUnsignedShort) {
                    j2 = -1;
                    break;
                }
                int readTag = openTypeReader.readTag();
                openTypeReader.skip(4);
                j2 = openTypeReader.readUnsignedInt();
                openTypeReader.skip(4);
                if (1835365473 == readTag) {
                    break;
                }
                i2++;
            }
            if (j2 != -1) {
                openTypeReader.skip((int) (j2 - openTypeReader.getPosition()));
                openTypeReader.skip(12);
                long readUnsignedInt = openTypeReader.readUnsignedInt();
                for (int i7 = 0; ((long) i7) < readUnsignedInt; i7++) {
                    int readTag2 = openTypeReader.readTag();
                    long readUnsignedInt2 = openTypeReader.readUnsignedInt();
                    long readUnsignedInt3 = openTypeReader.readUnsignedInt();
                    if (1164798569 == readTag2 || 1701669481 == readTag2) {
                        return new OffsetInfo(readUnsignedInt2 + j2, readUnsignedInt3);
                    }
                }
            }
            throw new IOException("Cannot read metadata.");
        }
        throw new IOException("Cannot read metadata.");
    }

    public static MetadataList read(ByteBuffer byteBuffer) {
        ByteBuffer duplicate = byteBuffer.duplicate();
        duplicate.position((int) findOffsetInfo(new ByteBufferReader(duplicate)).getStartOffset());
        return MetadataList.getRootAsMetadataList(duplicate);
    }

    public static long toUnsignedInt(int i2) {
        return ((long) i2) & 4294967295L;
    }

    public static int toUnsignedShort(short s) {
        return s & 65535;
    }
}
