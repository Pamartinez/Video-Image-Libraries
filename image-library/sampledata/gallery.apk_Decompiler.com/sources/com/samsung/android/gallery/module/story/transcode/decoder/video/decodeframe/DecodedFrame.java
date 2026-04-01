package com.samsung.android.gallery.module.story.transcode.decoder.video.decodeframe;

import android.media.MediaCodec;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DecodedFrame {
    public int bufferIndex;
    public MediaCodec codec;
    public long presentationTimeUS;
    public int size;
    public int videoID;

    public DecodedFrame(int i2, int i7, int i8, long j2) {
        this.videoID = i2;
        this.bufferIndex = i7;
        this.size = i8;
        this.presentationTimeUS = j2;
    }

    public String toString() {
        return "DecodedFrame@" + hashCode() + "Id=" + this.videoID + ",index=" + this.bufferIndex + ",size=" + this.size + ",timeUs=" + this.presentationTimeUS;
    }

    public DecodedFrame(MediaCodec mediaCodec, int i2, int i7, int i8, long j2) {
        this(i2, i7, i8, j2);
        this.codec = mediaCodec;
    }
}
