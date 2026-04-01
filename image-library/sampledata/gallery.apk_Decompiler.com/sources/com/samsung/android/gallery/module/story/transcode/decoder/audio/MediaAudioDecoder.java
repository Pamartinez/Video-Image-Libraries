package com.samsung.android.gallery.module.story.transcode.decoder.audio;

import android.media.MediaExtractor;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.module.story.transcode.config.AudioInfo;
import com.samsung.android.gallery.module.story.transcode.util.CodecsHelper;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaAudioDecoder extends AbsAudioDecoder {
    private final long mDuration;
    private long mFDLength;
    private Long mFDOffset;
    private final String mInputFilePath;
    private final boolean mIsBGM;
    private boolean mIsErrorLogged;
    private long mStartTimeUs;
    private final Uri mUri;

    public MediaAudioDecoder(String str, Uri uri, boolean z) {
        this(str, uri, -1, z);
    }

    private boolean checkDuration(long j2) {
        long j3 = this.mDuration;
        if (j3 < 0 || j2 < j3 + this.mStartTimeUs) {
            return true;
        }
        return false;
    }

    public boolean canPassSampleDataToDecoder(MediaExtractor mediaExtractor) {
        try {
            if (mediaExtractor.getSampleTime() >= 0 || mediaExtractor.getSampleSize() >= 0 || mediaExtractor.getSampleFlags() >= 0 || mediaExtractor.getSampleTrackIndex() >= 0) {
                return true;
            }
            return false;
        } catch (IllegalArgumentException e) {
            if (!this.mIsErrorLogged) {
                String str = this.TAG;
                Log.e(str, "canPassSampleDataToDecoder failed. e= " + e.getMessage());
                this.mIsErrorLogged = true;
            }
            return false;
        }
    }

    public MediaExtractor createAudioExtractor() {
        if (TextUtils.isEmpty(this.mInputFilePath)) {
            return CodecsHelper.createExtractor(this.mUri);
        }
        Long l = this.mFDOffset;
        if (l != null) {
            return CodecsHelper.createExtractor(this.mInputFilePath, l.longValue(), this.mFDLength);
        }
        return CodecsHelper.createExtractor(this.mInputFilePath);
    }

    public /* bridge */ /* synthetic */ boolean extractAudio() {
        return super.extractAudio();
    }

    public /* bridge */ /* synthetic */ AudioInfo getAudioInfo() {
        return super.getAudioInfo();
    }

    public /* bridge */ /* synthetic */ int getBufferInfoFlags() {
        return super.getBufferInfoFlags();
    }

    public /* bridge */ /* synthetic */ short[] getContentShort() {
        return super.getContentShort();
    }

    public /* bridge */ /* synthetic */ int getPendingAudioDecoderOutputBufferIndex() {
        return super.getPendingAudioDecoderOutputBufferIndex();
    }

    public void handleFailedToPassSampleDataToDecoder() {
        if (this.mIsBGM) {
            this.mExtractor.seekTo(0, 0);
        } else {
            setAudioExtractDone(true);
        }
    }

    public boolean hasMoreData(long j2, int i2) {
        if (!checkDuration(j2) || !super.hasMoreData(j2, i2)) {
            return false;
        }
        return true;
    }

    public /* bridge */ /* synthetic */ boolean isAudioDecoderDone() {
        return super.isAudioDecoderDone();
    }

    public /* bridge */ /* synthetic */ boolean isCopyAudio() {
        return super.isCopyAudio();
    }

    public /* bridge */ /* synthetic */ void onEncodingFinished() {
        super.onEncodingFinished();
    }

    public /* bridge */ /* synthetic */ void prepareAudioCodec() {
        super.prepareAudioCodec();
    }

    public void prepareAudioEncoding(long j2) {
        super.prepareAudioEncoding(j2);
        this.mStartTimeUs = j2;
    }

    public /* bridge */ /* synthetic */ void release() {
        super.release();
    }

    public /* bridge */ /* synthetic */ void releaseOutputBuffer() {
        super.releaseOutputBuffer();
    }

    public void setFDInfo(long j2, long j3) {
        this.mFDOffset = Long.valueOf(j2);
        this.mFDLength = j3;
    }

    public MediaAudioDecoder(String str, Uri uri, long j2) {
        this(str, uri, j2, false);
    }

    public MediaAudioDecoder(String str, Uri uri, long j2, boolean z) {
        this.mInputFilePath = str;
        this.mUri = uri;
        this.mIsBGM = z;
        this.mDuration = j2;
    }
}
