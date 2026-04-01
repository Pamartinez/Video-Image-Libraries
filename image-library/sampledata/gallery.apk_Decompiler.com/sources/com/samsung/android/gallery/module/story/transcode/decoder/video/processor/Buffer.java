package com.samsung.android.gallery.module.story.transcode.decoder.video.processor;

import android.media.MediaCodec;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface Buffer {
    MediaCodec getCodec();

    int getIndex();

    int getSize();

    long getTimeUs();

    boolean isEos();

    boolean releaseAndRender();
}
