package com.samsung.android.gallery.module.story.transcode.transcoder;

import A.a;
import android.media.MediaMuxer;
import android.os.Build;
import com.samsung.android.gallery.module.story.transcode.config.EncodeInfo;
import com.samsung.android.gallery.module.story.transcode.config.FrameProperty;
import com.samsung.android.gallery.module.story.transcode.decoder.video.MediaDecoder;
import com.samsung.android.gallery.module.story.transcode.encoder.video.FrameProvider;
import com.samsung.android.gallery.module.story.transcode.encoder.video.VideoEncodeRender;
import com.samsung.android.gallery.module.story.transcode.encoder.video.VideoEncoder;
import com.samsung.android.gallery.module.story.transcode.util.OnErrorListener;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoTranscoder implements ITranscoder {
    private final EncodeInfo mEncodeInfo;
    private final OnErrorListener mErrorListener;
    private FrameProvider mFrameProvider;
    private final long mIntervalMs = 33;
    private final ArrayList<ThumbnailInterface> mItems;
    private final long mTotalDuration;
    private MediaDecoder mVideoDecoder;
    private VideoEncoder mVideoEncoder;
    private int mVideoFramesWritten;
    private VideoEncodeRender mVideoRender;
    private long mVideoWrittenTimeMs;

    public VideoTranscoder(ArrayList<ThumbnailInterface> arrayList, EncodeInfo encodeInfo, OnErrorListener onErrorListener) {
        this.mItems = arrayList;
        this.mEncodeInfo = encodeInfo;
        this.mTotalDuration = (33 * 2) + ((long) encodeInfo.duration);
        this.mErrorListener = onErrorListener;
    }

    private void applyFrame() {
        this.mVideoEncoder.applyFrame(this.mVideoFramesWritten);
    }

    private void decodeFrame() {
        ArrayList<FrameProperty> frameProperties = this.mFrameProvider.getFrameProperties(this.mVideoWrittenTimeMs);
        this.mVideoFramesWritten++;
        this.mVideoRender.prepareDraw();
        this.mVideoDecoder.draw(frameProperties);
        int i2 = this.mVideoFramesWritten;
        if (i2 < 66) {
            this.mVideoDecoder.drawTitle((((float) i2) / 2000.0f) * 30.0f);
        }
        this.mVideoRender.draw();
    }

    private void encodeFrame(MediaMuxer mediaMuxer) {
        this.mVideoEncoder.encodeFrame(mediaMuxer);
    }

    private void initDecoder() {
        this.mFrameProvider = new FrameProvider(this.mEncodeInfo);
        MediaDecoder mediaDecoder = new MediaDecoder(this.mItems, this.mEncodeInfo, this.mErrorListener);
        this.mVideoDecoder = mediaDecoder;
        mediaDecoder.initEffectFilter(this.mEncodeInfo.filterName);
        this.mVideoDecoder.prepareDecoders();
    }

    private void initEncoder() {
        VideoEncoder videoEncoder = new VideoEncoder();
        this.mVideoEncoder = videoEncoder;
        EncodeInfo encodeInfo = this.mEncodeInfo;
        videoEncoder.init(encodeInfo.outWidth, encodeInfo.outHeight);
        this.mVideoEncoder.start();
    }

    private void initRender() {
        EncodeInfo encodeInfo = this.mEncodeInfo;
        this.mVideoRender = new VideoEncodeRender(encodeInfo.outWidth, encodeInfo.outHeight);
    }

    private void releaseDecoder() {
        MediaDecoder mediaDecoder = this.mVideoDecoder;
        if (mediaDecoder != null) {
            mediaDecoder.release();
        }
    }

    private void releaseEncoder() {
        VideoEncoder videoEncoder = this.mVideoEncoder;
        if (videoEncoder != null) {
            videoEncoder.releaseVideoEncoder();
        }
    }

    private void releaseRender() {
        VideoEncodeRender videoEncodeRender = this.mVideoRender;
        if (videoEncodeRender != null) {
            videoEncodeRender.release();
        }
    }

    private void transcodeVideo(MediaMuxer mediaMuxer, boolean z) {
        if (!this.mVideoEncoder.hasOutputFormat() || z) {
            decodeFrame();
            applyFrame();
            encodeFrame(mediaMuxer);
        }
    }

    public void addTrack(MediaMuxer mediaMuxer) {
        this.mVideoEncoder.addTrack(mediaMuxer);
    }

    public float getProgress() {
        return ((float) this.mVideoWrittenTimeMs) / ((float) this.mTotalDuration);
    }

    public boolean hasOutputFormat() {
        return this.mVideoEncoder.hasOutputFormat();
    }

    public void interrupt() {
        MediaDecoder mediaDecoder = this.mVideoDecoder;
        if (mediaDecoder != null) {
            mediaDecoder.interrupt();
        }
    }

    public boolean isReady() {
        return hasOutputFormat();
    }

    public boolean isRunning() {
        if (this.mVideoWrittenTimeMs < this.mTotalDuration) {
            return true;
        }
        return false;
    }

    public void prepare() {
        initEncoder();
        initDecoder();
        initRender();
        this.mVideoFramesWritten = 0;
        this.mVideoWrittenTimeMs = 0;
        a.w(new StringBuilder("Build.VERSION.SEM_PLATFORM_INT: "), Build.VERSION.SEM_PLATFORM_INT, "VideoTranscoder");
    }

    public void release() {
        releaseEncoder();
        releaseDecoder();
        releaseRender();
    }

    public void transcode(MediaMuxer mediaMuxer, boolean z) {
        if (!isRunning()) {
            interrupt();
            return;
        }
        transcodeVideo(mediaMuxer, z);
        this.mVideoWrittenTimeMs += this.mIntervalMs;
    }
}
