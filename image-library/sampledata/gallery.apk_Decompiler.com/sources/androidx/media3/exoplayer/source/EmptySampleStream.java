package androidx.media3.exoplayer.source;

import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EmptySampleStream implements SampleStream {
    public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i2) {
        decoderInputBuffer.setFlags(4);
        return -4;
    }

    public void maybeThrowError() {
    }
}
