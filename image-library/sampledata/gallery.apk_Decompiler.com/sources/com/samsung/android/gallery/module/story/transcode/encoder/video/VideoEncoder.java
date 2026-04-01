package com.samsung.android.gallery.module.story.transcode.encoder.video;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.view.Surface;
import com.samsung.android.gallery.module.story.transcode.renderer.surface.InputSurface;
import com.samsung.android.gallery.module.story.transcode.util.CodecsHelper;
import com.samsung.android.gallery.module.story.transcode.util.TranscodingHelper;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoEncoder {
    private MediaCodec.BufferInfo mBufferInfo;
    private MediaCodec mCodec;
    private InputSurface mInputSurface;
    private MediaFormat mMediaFormat;
    private int mVideoTrackIndex = -1;

    private MediaFormat createMediaFormat(int i2, int i7) {
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat(Encode.CodecsMime.VIDEO_CODEC_H264, i2, i7);
        createVideoFormat.setInteger("color-format", 2130708361);
        createVideoFormat.setInteger("bitrate", CodecsHelper.suggestBitRate(i2, i7) * 1000);
        createVideoFormat.setInteger("frame-rate", 30);
        createVideoFormat.setInteger("i-frame-interval", 1);
        createVideoFormat.setInteger("priority", 1);
        createVideoFormat.setInteger("color-standard", 1);
        return createVideoFormat;
    }

    public void addTrack(MediaMuxer mediaMuxer) {
        if (this.mVideoTrackIndex < 0) {
            this.mVideoTrackIndex = mediaMuxer.addTrack(this.mMediaFormat);
            return;
        }
        throw new RuntimeException("Video encoder output format changed after muxer has started");
    }

    public void applyFrame(int i2) {
        this.mInputSurface.setPresentationTime(TranscodingHelper.generateTimestampUS(30, i2) * 1000);
        this.mInputSurface.swapBuffers();
    }

    public void encodeFrame(MediaMuxer mediaMuxer) {
        int dequeueOutputBuffer = this.mCodec.dequeueOutputBuffer(this.mBufferInfo, 10000);
        if (dequeueOutputBuffer == -1) {
            Log.d("VideoEncoder", "no video encoder output buffer");
        } else if (dequeueOutputBuffer == -2) {
            this.mMediaFormat = this.mCodec.getOutputFormat();
            Log.d("VideoEncoder", "output format changed " + this.mMediaFormat);
        } else if (dequeueOutputBuffer < 0) {
            Log.e("VideoEncoder", "Unexpected result from video encoder dequeue output format.");
        } else {
            ByteBuffer outputBuffer = this.mCodec.getOutputBuffer(dequeueOutputBuffer);
            MediaCodec.BufferInfo bufferInfo = this.mBufferInfo;
            if ((bufferInfo.flags & 2) != 0) {
                Log.d("VideoEncoder", "codec config buffer");
                this.mCodec.releaseOutputBuffer(dequeueOutputBuffer, false);
                return;
            }
            if (bufferInfo.size != 0) {
                mediaMuxer.writeSampleData(this.mVideoTrackIndex, outputBuffer, bufferInfo);
            }
            this.mCodec.releaseOutputBuffer(dequeueOutputBuffer, false);
            if ((this.mBufferInfo.flags & 4) != 0) {
                Log.d("VideoEncoder", "video encoder: EOS");
            }
        }
    }

    public boolean hasOutputFormat() {
        if (this.mMediaFormat != null) {
            return true;
        }
        return false;
    }

    public void init(int i2, int i7) {
        MediaFormat createMediaFormat = createMediaFormat(i2, i7);
        this.mBufferInfo = new MediaCodec.BufferInfo();
        Log.d("VideoEncoder", "output video format " + createMediaFormat);
        MediaCodec createEncoderByType = MediaCodec.createEncoderByType(Encode.CodecsMime.VIDEO_CODEC_H264);
        this.mCodec = createEncoderByType;
        createEncoderByType.configure(createMediaFormat, (Surface) null, (MediaCrypto) null, 1);
        this.mInputSurface = new InputSurface(this.mCodec.createInputSurface());
    }

    public void releaseVideoEncoder() {
        InputSurface inputSurface = this.mInputSurface;
        if (inputSurface != null) {
            try {
                inputSurface.release();
                this.mInputSurface = null;
            } catch (Exception e) {
                Log.e("VideoEncoder", "Exception in releasing input surface.");
                e.printStackTrace();
            }
        }
        MediaCodec mediaCodec = this.mCodec;
        if (mediaCodec != null) {
            try {
                mediaCodec.stop();
            } catch (IllegalStateException unused) {
                Log.e("VideoEncoder", "Exception in stopping output video encoder.");
            }
            try {
                this.mCodec.release();
                Log.d("VideoEncoder", "Output video encoder has been released.");
            } catch (Exception e7) {
                Log.e("VideoEncoder", "Exception in releasing output video encoder.");
                e7.printStackTrace();
            }
            this.mCodec = null;
        }
    }

    public void start() {
        this.mCodec.start();
        this.mInputSurface.makeCurrent();
    }
}
