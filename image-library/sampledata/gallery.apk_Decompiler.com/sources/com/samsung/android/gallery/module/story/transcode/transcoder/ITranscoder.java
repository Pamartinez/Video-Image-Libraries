package com.samsung.android.gallery.module.story.transcode.transcoder;

import android.media.MediaMuxer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ITranscoder {
    void addTrack(MediaMuxer mediaMuxer);

    float getProgress();

    boolean hasOutputFormat();

    boolean isReady();

    boolean isRunning();

    void prepare();

    void release();

    void transcode(MediaMuxer mediaMuxer, boolean z);

    void interrupt() {
    }
}
