package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MetadataList extends Table {
    public static MetadataList getRootAsMetadataList(ByteBuffer byteBuffer) {
        return getRootAsMetadataList(byteBuffer, new MetadataList());
    }

    public MetadataList __assign(int i2, ByteBuffer byteBuffer) {
        __init(i2, byteBuffer);
        return this;
    }

    public void __init(int i2, ByteBuffer byteBuffer) {
        __reset(i2, byteBuffer);
    }

    public MetadataItem list(MetadataItem metadataItem, int i2) {
        int __offset = __offset(6);
        if (__offset == 0) {
            return null;
        }
        return metadataItem.__assign(__indirect((i2 * 4) + __vector(__offset)), this.bb);
    }

    public int listLength() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public int version() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public static MetadataList getRootAsMetadataList(ByteBuffer byteBuffer, MetadataList metadataList) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return metadataList.__assign(byteBuffer.position() + byteBuffer.getInt(byteBuffer.position()), byteBuffer);
    }
}
