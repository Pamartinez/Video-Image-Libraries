package com.samsung.android.gallery.module.story.transcode.decoder.video.processor;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.view.Surface;
import com.samsung.android.gallery.module.story.transcode.decoder.video.decodeframe.DecodedFrame;
import com.samsung.android.gallery.module.story.transcode.decoder.video.metadata.VideoMetaData;
import com.samsung.android.gallery.module.story.transcode.util.OnErrorListener;
import com.samsung.android.gallery.module.story.transcode.util.UnsupportedCodecException;
import com.samsung.android.gallery.module.story.transcode.util.VideoHandler;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import ic.l;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DecodeProcessor implements Processor {
    /* access modifiers changed from: private */
    public String TAG = "DecodeProcessor";
    protected final MediaCodec.Callback mCallback = new MediaCodec.Callback() {
        public void onError(MediaCodec mediaCodec, MediaCodec.CodecException codecException) {
            if (!DecodeProcessor.this.mIsReleased) {
                DecodeProcessor.this.handleError(codecException, "onError");
            } else {
                Log.e((CharSequence) DecodeProcessor.this.TAG, "ignore onError after release, cause", codecException.getMessage());
            }
        }

        public void onInputBufferAvailable(MediaCodec mediaCodec, int i2) {
            DecodeProcessor.this.inputProcess(i2);
        }

        public void onOutputBufferAvailable(MediaCodec mediaCodec, int i2, MediaCodec.BufferInfo bufferInfo) {
            DecodeProcessor.this.outputProcess(i2, bufferInfo);
        }

        public void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) {
            MediaFormat mediaFormat2;
            try {
                if (!DecodeProcessor.this.mIsReleased) {
                    String a7 = DecodeProcessor.this.TAG;
                    MediaCodec mediaCodec2 = DecodeProcessor.this.mCodec;
                    if (mediaCodec2 != null) {
                        mediaFormat2 = mediaCodec2.getOutputFormat();
                    } else {
                        mediaFormat2 = null;
                    }
                    Log.e((CharSequence) a7, "codec format changed", mediaFormat2);
                }
            } catch (IllegalStateException unused) {
            }
        }
    };
    protected MediaCodec mCodec;
    private boolean mDecodedEnoughFrame;
    protected OnErrorListener mErrorListener;
    private final MediaExtractor mExtractor;
    /* access modifiers changed from: private */
    public boolean mIsReleased;
    private long mLastPresentationTimeUs;
    protected int mLoopingCount;
    private final VideoMetaData mMetaData;
    private final BufferQueue mOutputBufferQueue = new BufferQueue();
    private boolean mRunning;
    private boolean mVideoDecodeDone;

    public DecodeProcessor(MediaExtractor mediaExtractor, VideoMetaData videoMetaData) {
        this.mExtractor = mediaExtractor;
        this.mMetaData = videoMetaData;
    }

    private long adjustPresentationTimeUs(long j2) {
        long j3 = this.mLastPresentationTimeUs;
        if (j2 <= j3) {
            this.mLastPresentationTimeUs = this.mMetaData.getSourceIntervalUs() + j3;
        } else {
            this.mLastPresentationTimeUs = j2;
        }
        return this.mLastPresentationTimeUs;
    }

    private long decodePresentationTimeUs(long j2, long j3) {
        return (j2 / 100) - (this.mMetaData.getStartTimeUs() * j3);
    }

    private long encodePresentationTimeUs(long j2, long j3) {
        return (((this.mMetaData.getStartTimeUs() * j3) + j2) * 100) + j3;
    }

    private boolean farFromSeek(long j2) {
        if (this.mMetaData.getStartTimeUs() <= 0 || this.mLoopingCount != 0 || this.mMetaData.getStartTimeUs() - j2 <= this.mMetaData.getEncodeIntervalUs() * 2) {
            return false;
        }
        return true;
    }

    private long getPlayTimeUs(long j2) {
        return (this.mMetaData.getDuration() * ((long) this.mLoopingCount)) + j2;
    }

    /* access modifiers changed from: private */
    public void handleError(Exception exc, String str) {
        Log.e(this.TAG, str);
        this.mErrorListener.onError(exc);
    }

    /* access modifiers changed from: private */
    public void inputProcess(int i2) {
        boolean z = this.mVideoDecodeDone;
        if (z || this.mIsReleased) {
            Log.w((CharSequence) this.TAG, "ignore inputProcess", Boolean.valueOf(z), Boolean.valueOf(this.mIsReleased));
            return;
        }
        try {
            inputProcessInternal(i2, this.mExtractor.readSampleData(this.mCodec.getInputBuffer(i2), 0), this.mExtractor.getSampleTime());
        } catch (IllegalStateException e) {
            String str = this.TAG;
            Log.e(str, "onInputBufferAvailable failed : " + e.getMessage());
        } catch (Exception e7) {
            e7.printStackTrace();
            handleError(e7, "onInputBufferAvailable fail");
        }
    }

    private void inputProcessInternal(int i2, int i7, long j2) {
        if (this.mDecodedEnoughFrame) {
            setDecodeDone(i2, j2);
            return;
        }
        long playTimeUs = getPlayTimeUs(j2);
        if (j2 > this.mMetaData.getEndTimeUs() || i7 < 0) {
            int i8 = i2;
            int i10 = i7;
            if (!this.mMetaData.isLooping() || j2 >= this.mMetaData.getEndTimeUs() || i10 > 0) {
                setDecodeDone(i8, j2);
                return;
            }
            Log.v(this.TAG, "seekToStartTime", Long.valueOf(j2));
            seekToStartTime(i8, 0);
            return;
        }
        int i11 = i2;
        int i12 = i7;
        this.mCodec.queueInputBuffer(i11, 0, i12, encodePresentationTimeUs(playTimeUs, (long) this.mLoopingCount), this.mExtractor.getSampleFlags());
        this.mExtractor.advance();
    }

    private boolean isRealSeekRange(long j2, long j3) {
        if (j2 < (this.mMetaData.getDuration() * j3) + this.mMetaData.getStartTimeUs()) {
            return true;
        }
        return false;
    }

    private void seekToStartTime(int i2, int i7) {
        this.mExtractor.seekTo(this.mMetaData.getStartTimeUs(), 0);
        this.mCodec.queueInputBuffer(i2, 0, 0, 0, i7);
        this.mLoopingCount++;
    }

    private void setDecodeDone(int i2, long j2) {
        this.mVideoDecodeDone = true;
        Log.d(this.TAG, "video decode done", Long.valueOf(j2), Boolean.valueOf(this.mDecodedEnoughFrame));
        this.mCodec.queueInputBuffer(i2, 0, 0, 0, 4);
    }

    public void configure(int i2, MediaFormat mediaFormat, Surface surface) {
        this.TAG += com.samsung.android.sdk.scs.base.utils.Log.TAG_SEPARATOR + i2;
        try {
            MediaCodec createDecoderByType = MediaCodec.createDecoderByType(mediaFormat.getString(MediaDefs.Image.HEIF.HEIF_MIME_BOX));
            this.mCodec = createDecoderByType;
            createDecoderByType.configure(mediaFormat, surface, (MediaCrypto) null, 0);
            Log.d(this.TAG, "configure");
        } catch (MediaCodec.CodecException | IllegalArgumentException e) {
            throw new UnsupportedCodecException(e);
        }
    }

    public BufferQueue getOutputBufferQueue() {
        return this.mOutputBufferQueue;
    }

    public long getPresentationTimeUs() {
        return this.mLastPresentationTimeUs;
    }

    public boolean isProcessDone() {
        return this.mVideoDecodeDone;
    }

    public boolean isRunning() {
        return this.mRunning;
    }

    public void outputProcess(int i2, MediaCodec.BufferInfo bufferInfo) {
        try {
            outputProcessInternal(i2, bufferInfo);
        } catch (IllegalArgumentException e) {
            handleError(e, "onOutputBufferAvailable fail");
        }
    }

    public void outputProcessInternal(int i2, MediaCodec.BufferInfo bufferInfo) {
        if (this.mIsReleased) {
            Log.w(this.TAG, "ignore outputProcessInternal : already released");
        } else if ((bufferInfo.flags & 2) != 0) {
            Log.d(this.TAG, "codec config buffer");
            this.mCodec.releaseOutputBuffer(i2, false);
        } else {
            long j2 = bufferInfo.presentationTimeUs;
            long j3 = j2 % 100;
            long adjustPresentationTimeUs = adjustPresentationTimeUs(decodePresentationTimeUs(j2, j3));
            if ((bufferInfo.flags & 4) != 0) {
                Log.d(this.TAG, "end of stream buffer", Integer.valueOf(i2), Long.valueOf(j2));
                bufferInfo.presentationTimeUs = adjustPresentationTimeUs;
                this.mOutputBufferQueue.push(new CodecBuffer(this.mCodec, bufferInfo, i2, true, true));
            } else if (isRealSeekRange(adjustPresentationTimeUs, j3)) {
                Log.d(this.TAG, "throw away a buffer for real seek", Integer.valueOf(i2), Long.valueOf(adjustPresentationTimeUs), Long.valueOf(j3));
                Optional.ofNullable(this.mCodec.getOutputBuffer(i2)).ifPresent(new l(27));
                this.mCodec.releaseOutputBuffer(i2, false);
            } else if (i2 == -1) {
            } else {
                if (farFromSeek(adjustPresentationTimeUs)) {
                    this.mCodec.releaseOutputBuffer(i2, false);
                    return;
                }
                bufferInfo.presentationTimeUs = adjustPresentationTimeUs;
                this.mOutputBufferQueue.push(new CodecBuffer(this.mCodec, bufferInfo, i2, true, false));
                if (adjustPresentationTimeUs > this.mMetaData.getPlayEndTimeUs()) {
                    Log.d(this.TAG, "decoded enough frame", Long.valueOf(adjustPresentationTimeUs), Long.valueOf(j3));
                    this.mDecodedEnoughFrame = true;
                }
            }
        }
    }

    public void release() {
        Log.d(this.TAG, "release");
        MediaCodec mediaCodec = this.mCodec;
        if (mediaCodec != null) {
            mediaCodec.release();
            this.mCodec = null;
        }
        this.mIsReleased = true;
    }

    public void setAsync() {
        if (this.mMetaData.getStartTimeUs() != 0) {
            this.mExtractor.seekTo(this.mMetaData.getStartTimeUs(), 0);
            Log.d(this.TAG, "seek", "req=" + this.mMetaData.getStartTimeUs(), "real=" + this.mExtractor.getSampleTime());
        }
        this.mCodec.setCallback(this.mCallback, VideoHandler.getHandler());
    }

    public void setErrorListener(OnErrorListener onErrorListener) {
        this.mErrorListener = onErrorListener;
    }

    public void start() {
        this.mRunning = true;
        setAsync();
        this.mCodec.start();
        Log.d(this.TAG, "start");
    }

    public void releaseAndRender(DecodedFrame decodedFrame) {
    }
}
