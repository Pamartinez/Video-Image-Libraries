package androidx.media3.extractor;

import androidx.media3.common.DataReader;
import androidx.media3.common.Format;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.TrackOutput;
import java.io.EOFException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DiscardingTrackOutput implements TrackOutput {
    private final byte[] readBuffer = new byte[4096];

    public int sampleData(DataReader dataReader, int i2, boolean z, int i7) {
        int read = dataReader.read(this.readBuffer, 0, Math.min(this.readBuffer.length, i2));
        if (read != -1) {
            return read;
        }
        if (z) {
            return -1;
        }
        throw new EOFException();
    }

    public void sampleData(ParsableByteArray parsableByteArray, int i2, int i7) {
        parsableByteArray.skipBytes(i2);
    }

    public void format(Format format) {
    }

    public void sampleMetadata(long j2, int i2, int i7, int i8, TrackOutput.CryptoData cryptoData) {
    }
}
