package androidx.media3.muxer;

import F2.U;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface Muxer extends AutoCloseable {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Factory {
        Muxer create(String str);

        U getSupportedSampleMimeTypes(int i2);

        boolean supportsWritingNegativeTimestampsInEditList() {
            return false;
        }
    }

    void addMetadataEntry(Metadata.Entry entry);

    int addTrack(Format format);

    void close();

    void writeSampleData(int i2, ByteBuffer byteBuffer, BufferInfo bufferInfo);
}
