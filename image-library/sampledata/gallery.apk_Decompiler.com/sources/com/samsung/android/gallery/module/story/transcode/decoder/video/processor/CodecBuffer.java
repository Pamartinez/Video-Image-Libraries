package com.samsung.android.gallery.module.story.transcode.decoder.video.processor;

import android.media.MediaCodec;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CodecBuffer implements Buffer {
    int bufferIndex;
    MediaCodec.BufferInfo bufferInfo;
    MediaCodec codec;
    boolean eos;
    boolean render;

    public CodecBuffer(MediaCodec mediaCodec, MediaCodec.BufferInfo bufferInfo2, int i2, boolean z, boolean z3) {
        this.codec = mediaCodec;
        this.bufferInfo = bufferInfo2;
        this.bufferIndex = i2;
        this.render = z;
        this.eos = z3;
    }

    public MediaCodec getCodec() {
        return this.codec;
    }

    public int getIndex() {
        return this.bufferIndex;
    }

    public int getSize() {
        return this.bufferInfo.size;
    }

    public long getTimeUs() {
        return this.bufferInfo.presentationTimeUs;
    }

    public boolean isEos() {
        return this.eos;
    }

    public boolean releaseAndRender() {
        MediaCodec.BufferInfo bufferInfo2 = this.bufferInfo;
        if (bufferInfo2 != null && (bufferInfo2.presentationTimeUs == -4 || !this.render)) {
            this.codec.releaseOutputBuffer(this.bufferIndex, false);
            return false;
        } else if (bufferInfo2 == null || this.bufferIndex == -1) {
            return false;
        } else {
            long j2 = bufferInfo2.presentationTimeUs;
            if (SdkConfig.atLeast(SdkConfig.SEM.U_MR5)) {
                j2 *= 1000;
            }
            this.codec.releaseOutputBuffer(this.bufferIndex, j2);
            return true;
        }
    }

    public String toString() {
        return "CodecBuffer{" + getTimeUs() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + isEos() + "}";
    }
}
