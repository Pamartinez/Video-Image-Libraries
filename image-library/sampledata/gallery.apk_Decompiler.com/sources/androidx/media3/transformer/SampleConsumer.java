package androidx.media3.transformer;

import android.graphics.Bitmap;
import android.view.Surface;
import androidx.media3.common.util.TimestampIterator;
import androidx.media3.decoder.DecoderInputBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface SampleConsumer {
    DecoderInputBuffer getInputBuffer() {
        throw new UnsupportedOperationException();
    }

    Surface getInputSurface() {
        throw new UnsupportedOperationException();
    }

    int getPendingVideoFrameCount() {
        throw new UnsupportedOperationException();
    }

    int queueInputBitmap(Bitmap bitmap, TimestampIterator timestampIterator) {
        throw new UnsupportedOperationException();
    }

    boolean queueInputBuffer() {
        throw new UnsupportedOperationException();
    }

    boolean registerVideoFrame(long j2) {
        throw new UnsupportedOperationException();
    }

    void signalEndOfVideoInput() {
        throw new UnsupportedOperationException();
    }
}
