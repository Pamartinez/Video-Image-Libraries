package androidx.media3.transformer;

import F2.U;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.muxer.BufferInfo;
import androidx.media3.muxer.Muxer;
import androidx.media3.transformer.FrameworkMuxer;
import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DefaultMuxer implements Muxer {
    public static final String MUXER_NAME = FrameworkMuxer.MUXER_NAME;
    private final Muxer muxer;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Factory implements Muxer.Factory {
        private final FrameworkMuxer.Factory muxerFactory = new FrameworkMuxer.Factory();

        public Muxer create(String str) {
            return new DefaultMuxer(this.muxerFactory.create(str));
        }

        public U getSupportedSampleMimeTypes(int i2) {
            return this.muxerFactory.getSupportedSampleMimeTypes(i2);
        }
    }

    public void addMetadataEntry(Metadata.Entry entry) {
        this.muxer.addMetadataEntry(entry);
    }

    public int addTrack(Format format) {
        return this.muxer.addTrack(format);
    }

    public void close() {
        this.muxer.close();
    }

    public void writeSampleData(int i2, ByteBuffer byteBuffer, BufferInfo bufferInfo) {
        this.muxer.writeSampleData(i2, byteBuffer, bufferInfo);
    }

    private DefaultMuxer(Muxer muxer2) {
        this.muxer = muxer2;
    }
}
