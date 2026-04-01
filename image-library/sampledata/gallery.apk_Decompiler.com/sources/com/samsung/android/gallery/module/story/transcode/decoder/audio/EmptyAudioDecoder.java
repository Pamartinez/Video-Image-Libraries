package com.samsung.android.gallery.module.story.transcode.decoder.audio;

import android.media.MediaExtractor;
import com.samsung.android.gallery.module.story.transcode.config.AudioInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EmptyAudioDecoder extends AbsAudioDecoder {
    private long mCurrentDuration;
    private final long mDuration;
    private boolean mIsEOSSent;
    private final long mSeekAnchorTime;

    public EmptyAudioDecoder(long j2) {
        this.mDuration = j2;
        AudioInfo audioInfo = new AudioInfo();
        this.mAudioInfo = audioInfo;
        this.mSeekAnchorTime = 1024000000 / ((long) audioInfo.getSampleRateHz());
    }

    public boolean canPassSampleDataToDecoder(MediaExtractor mediaExtractor) {
        return false;
    }

    public MediaExtractor createAudioExtractor() {
        return null;
    }

    public boolean extractAudio() {
        long j2 = this.mCurrentDuration;
        if (j2 < this.mDuration) {
            this.mCurrentDuration = j2 + this.mSeekAnchorTime;
            setPendingOutputBufferIndex(0);
        } else {
            setAudioExtractDone(true);
        }
        return true;
    }

    public /* bridge */ /* synthetic */ AudioInfo getAudioInfo() {
        return super.getAudioInfo();
    }

    public int getBufferInfoFlags() {
        if (!isExtractorDone()) {
            return 0;
        }
        if (this.mIsEOSSent) {
            return -1;
        }
        this.mIsEOSSent = true;
        return 4;
    }

    public short[] getContentShort() {
        int i2;
        if (isExtractorDone()) {
            i2 = 0;
        } else {
            i2 = 2048;
        }
        return new short[i2];
    }

    public /* bridge */ /* synthetic */ int getPendingAudioDecoderOutputBufferIndex() {
        return super.getPendingAudioDecoderOutputBufferIndex();
    }

    public /* bridge */ /* synthetic */ boolean isAudioDecoderDone() {
        return super.isAudioDecoderDone();
    }

    public boolean isCopyAudio() {
        return true;
    }

    public /* bridge */ /* synthetic */ void onEncodingFinished() {
        super.onEncodingFinished();
    }

    public /* bridge */ /* synthetic */ void prepareAudioEncoding(long j2) {
        super.prepareAudioEncoding(j2);
    }

    public /* bridge */ /* synthetic */ void release() {
        super.release();
    }

    public /* bridge */ /* synthetic */ void releaseOutputBuffer() {
        super.releaseOutputBuffer();
    }

    public void handleFailedToPassSampleDataToDecoder() {
    }

    public void prepareAudioCodec() {
    }
}
