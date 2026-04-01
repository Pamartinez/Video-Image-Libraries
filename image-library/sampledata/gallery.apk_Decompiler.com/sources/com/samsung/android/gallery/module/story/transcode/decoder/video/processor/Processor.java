package com.samsung.android.gallery.module.story.transcode.decoder.video.processor;

import android.media.MediaFormat;
import android.view.Surface;
import com.samsung.android.gallery.module.story.transcode.decoder.video.decodeframe.DecodedFrame;
import com.samsung.android.gallery.module.story.transcode.decoder.video.decodeframe.DecoderFrameManager;
import com.samsung.android.gallery.module.story.transcode.util.OnErrorListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface Processor {
    void configure(int i2, MediaFormat mediaFormat, Surface surface);

    Surface createInputSurface() {
        return null;
    }

    BufferQueue getOutputBufferQueue() {
        return null;
    }

    long getPresentationTimeUs();

    boolean isProcessDone();

    boolean isRunning() {
        return false;
    }

    void release();

    void releaseAndRender(DecodedFrame decodedFrame);

    void start();

    void interrupt() {
    }

    void setDecodeFrameManager(DecoderFrameManager decoderFrameManager) {
    }

    void setErrorListener(OnErrorListener onErrorListener) {
    }

    void setInputBufferQueue(BufferQueue bufferQueue) {
    }
}
