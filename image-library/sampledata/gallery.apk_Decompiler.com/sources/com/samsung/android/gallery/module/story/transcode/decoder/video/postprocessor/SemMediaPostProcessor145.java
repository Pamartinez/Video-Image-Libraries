package com.samsung.android.gallery.module.story.transcode.decoder.video.postprocessor;

import A.a;
import android.media.MediaFormat;
import android.view.Surface;
import com.samsung.android.gallery.module.story.transcode.decoder.video.postprocessor.PostProcessor;
import com.samsung.android.media.SemMediaPostProcessor;
import com.samsung.android.sum.core.message.Message;
import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SemMediaPostProcessor145 implements PostProcessor {
    private int mFilterIntensity;
    private String mFilterPath;
    private final SemMediaPostProcessor mSemMediaPostProcessor = SemMediaPostProcessor.createByType(6);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BufferInfoClient implements PostProcessor.BufferInfoWrapper {
        private final SemMediaPostProcessor.BufferInfo mBufferInfo = new SemMediaPostProcessor.BufferInfo();

        public Object getBufferInfo() {
            return this.mBufferInfo;
        }

        public int getFlags() {
            return this.mBufferInfo.flags;
        }

        public int getIndex() {
            return this.mBufferInfo.index;
        }

        public long getPresentationTimeUs() {
            return this.mBufferInfo.timeUs;
        }

        public boolean isEndOfStream() {
            if (getFlags() == 2) {
                return true;
            }
            return false;
        }
    }

    private SemMediaPostProcessor.ProcessingFormat getConfigs(MediaFormat mediaFormat) {
        int integer = mediaFormat.getInteger("width");
        int integer2 = mediaFormat.getInteger("height");
        SemMediaPostProcessor.ProcessingFormat processingFormat = new SemMediaPostProcessor.ProcessingFormat();
        processingFormat.setInteger("width", integer);
        processingFormat.setInteger("height", integer2);
        processingFormat.setInteger("stride", mediaFormat.getInteger("stride", integer));
        processingFormat.setInteger("elevation", mediaFormat.getInteger("slice-height", integer2));
        processingFormat.setInteger("rotation-degree", mediaFormat.getInteger(Message.KEY_ROTATION, 0));
        processingFormat.setInteger("input-color", 2);
        processingFormat.setInteger("output-color", 9);
        String str = this.mFilterPath;
        if (str == null) {
            str = "";
        }
        processingFormat.setString("filterName", str);
        processingFormat.setInteger("filterLevel", this.mFilterIntensity);
        setConfiguration(processingFormat, mediaFormat, "colorRange", "color-range");
        setConfiguration(processingFormat, mediaFormat, "colorStandard", "color-standard");
        setConfiguration(processingFormat, mediaFormat, "colorTransfer", "color-transfer");
        setConfiguration(processingFormat, mediaFormat, "fps", "frame-rate");
        return processingFormat;
    }

    private void setConfiguration(SemMediaPostProcessor.ProcessingFormat processingFormat, MediaFormat mediaFormat, String str, String str2) {
        if (mediaFormat.containsKey(str2)) {
            processingFormat.setInteger(str, mediaFormat.getInteger(str2));
        }
    }

    public void configure(MediaFormat mediaFormat, Surface surface) {
        try {
            this.mSemMediaPostProcessor.configure(getConfigs(mediaFormat), surface);
        } catch (Exception e) {
            a.s(e, new StringBuilder("configure failed="), "PostProcessor145");
        }
    }

    public PostProcessor.BufferInfoWrapper createBufferInfo() {
        return new BufferInfoClient();
    }

    public Surface createInputSurface() {
        try {
            return this.mSemMediaPostProcessor.createInputSurface();
        } catch (Exception e) {
            a.s(e, new StringBuilder("createInputSurface failed="), "PostProcessor145");
            return null;
        }
    }

    public ByteBuffer dequeueOutputBuffer(PostProcessor.BufferInfoWrapper bufferInfoWrapper) {
        try {
            return this.mSemMediaPostProcessor.dequeueOutputBuffer((SemMediaPostProcessor.BufferInfo) bufferInfoWrapper.getBufferInfo(), 1000000);
        } catch (Exception e) {
            a.s(e, new StringBuilder("dequeueOutputBuffer failed="), "PostProcessor145");
            return null;
        }
    }

    public void release() {
        try {
            this.mSemMediaPostProcessor.release();
        } catch (Exception e) {
            a.s(e, new StringBuilder("release failed="), "PostProcessor145");
        }
    }

    public void renderAndReleaseOutputBuffer(int i2, long j2, long j3) {
        try {
            this.mSemMediaPostProcessor.renderAndReleaseOutputBuffer(i2, j2, j3);
        } catch (Exception e) {
            a.s(e, new StringBuilder("renderAndReleaseOutputBuffer failed="), "PostProcessor145");
        }
    }

    public void setFilter(String str, int i2) {
        this.mFilterPath = str;
        this.mFilterIntensity = i2;
    }

    public void signalEndOfInputStream() {
        try {
            this.mSemMediaPostProcessor.signalEndOfInputStream();
        } catch (Exception e) {
            a.s(e, new StringBuilder("signalEndOfInputStream failed="), "PostProcessor145");
        }
    }

    public boolean support() {
        if (this.mSemMediaPostProcessor != null) {
            return true;
        }
        return false;
    }
}
