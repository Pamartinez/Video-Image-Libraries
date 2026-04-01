package com.samsung.android.gallery.module.story.transcode.decoder.video.postprocessor;

import android.media.MediaFormat;
import android.view.Surface;
import com.samsung.android.gallery.support.config.SdkConfig;
import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface PostProcessor {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface BufferInfoWrapper {
        Object getBufferInfo();

        int getIndex();

        long getPresentationTimeUs();

        boolean isEndOfStream();
    }

    static PostProcessor create() {
        try {
            if (SdkConfig.atLeast(SdkConfig.SEM.T_MR5)) {
                return new SemMediaPostProcessor145();
            }
            if (!SdkConfig.atLeast(SdkConfig.SEM.T_MR1)) {
                return null;
            }
            return new SemMediaPostProcessor140();
        } catch (Exception | NoSuchMethodError e) {
            e.printStackTrace();
            return null;
        }
    }

    void configure(MediaFormat mediaFormat, Surface surface);

    BufferInfoWrapper createBufferInfo();

    Surface createInputSurface();

    ByteBuffer dequeueOutputBuffer(BufferInfoWrapper bufferInfoWrapper);

    void release();

    void renderAndReleaseOutputBuffer(int i2, long j2, long j3);

    void setFilter(String str, int i2);

    void signalEndOfInputStream();

    boolean support();
}
