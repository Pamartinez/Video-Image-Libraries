package com.samsung.android.gallery.module.story.transcode.encoder.audio;

import A.a;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import com.samsung.android.gallery.module.story.transcode.config.AudioInfo;
import com.samsung.android.gallery.module.story.transcode.decoder.audio.IAudioDecoder;
import com.samsung.android.gallery.module.story.transcode.util.AudioTranscodingHelper;
import com.samsung.android.gallery.module.story.transcode.util.CodecsHelper;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AudioEncoder {
    private MediaCodec mCodec;
    private long mContentEndTime;
    private long mContentStartTime;
    private AudioInfo mDecoderAudioInfo;
    private MediaFormat mDecoderFormat;
    private long mDurationUs;
    private int mInputBufferCount;
    private boolean mIsEncoderDone;
    private long mLastWrittenTime;
    private final ArrayList<Short> mMainAudioData = new ArrayList<>();
    private final VolumeRatio mMainVolumeRatio = new VolumeRatio(1.0f, 0);
    private MediaCodec.BufferInfo mOutputBufferInfo;
    private MediaFormat mOutputFormat;
    private long mSeekAnchorTime = -1;
    private long mSeekTime = 0;
    private final ArrayList<Short> mSubAudioData = new ArrayList<>();
    private final VolumeRatio mSubVolumeRatio = new VolumeRatio(0.0f, 0);
    private int mTrackIndex = -1;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class VolumeRatio {
        /* access modifiers changed from: private */
        public float currentDelta;
        /* access modifiers changed from: private */
        public float currentTarget;
        /* access modifiers changed from: private */
        public float nextDelta;
        /* access modifiers changed from: private */
        public float nextTarget;
        /* access modifiers changed from: private */
        public float value;

        public /* synthetic */ VolumeRatio(float f, int i2) {
            this(f);
        }

        /* access modifiers changed from: private */
        public void updateTargetRatio(float f, float f5) {
            this.currentTarget = f;
            this.nextTarget = f5;
            this.currentDelta = Math.abs(this.value - f);
            this.nextDelta = Math.abs(f - f5);
        }

        private VolumeRatio(float f) {
            this.value = f;
        }
    }

    private void addAudioFadeEffect(long j2, float f, float f5) {
        float f8;
        long j3 = this.mSeekTime;
        this.mContentStartTime = j3;
        this.mContentEndTime = j3 + j2;
        VolumeRatio volumeRatio = this.mMainVolumeRatio;
        float f10 = 1.0f;
        if (f > 0.0f) {
            f8 = 0.5f;
        } else {
            f8 = 1.0f;
        }
        if (f5 > 0.0f) {
            f10 = 0.5f;
        }
        volumeRatio.updateTargetRatio(f8, f10);
        this.mSubVolumeRatio.updateTargetRatio(f, f5);
    }

    private void clearSubAudioData() {
        this.mSubAudioData.clear();
    }

    private void enqueueMixedAudio(int i2, byte[] bArr) {
        int dequeueInputBuffer = this.mCodec.dequeueInputBuffer(10000);
        if (dequeueInputBuffer == -1) {
            Log.d("AudioEncoder", "audio encoder input buffer try again later");
            return;
        }
        ByteBuffer inputBuffer = this.mCodec.getInputBuffer(dequeueInputBuffer);
        inputBuffer.position(0);
        inputBuffer.put(ByteBuffer.wrap(bArr));
        this.mCodec.queueInputBuffer(dequeueInputBuffer, 0, bArr.length, this.mSeekTime, i2);
        this.mInputBufferCount++;
    }

    private short[] extractBytes(ArrayList<Short> arrayList) {
        int min = Math.min(arrayList.size(), 2048);
        short[] sArr = new short[min];
        for (int i2 = 0; i2 < min; i2++) {
            sArr[i2] = arrayList.get(i2).shortValue();
        }
        arrayList.subList(0, min).clear();
        return sArr;
    }

    private boolean fillBytes(ArrayList<Short> arrayList, IAudioDecoder iAudioDecoder) {
        boolean isTranscodingRunningOn = isTranscodingRunningOn(iAudioDecoder);
        if (isTranscodingRunningOn) {
            short[] contentShort = iAudioDecoder.getContentShort();
            if (contentShort.length > 0) {
                for (short valueOf : resample(iAudioDecoder, AudioTranscodingHelper.asStereo(contentShort, iAudioDecoder.getAudioInfo().getChannelCount()))) {
                    arrayList.add(Short.valueOf(valueOf));
                }
            }
        }
        if (isTranscodingRunningOn) {
            if (arrayList.size() >= 2048) {
                return true;
            }
            return false;
        } else if (!arrayList.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    private void finishEncoding(IAudioDecoder... iAudioDecoderArr) {
        for (IAudioDecoder iAudioDecoder : iAudioDecoderArr) {
            if (iAudioDecoder != null) {
                iAudioDecoder.onEncodingFinished();
            }
        }
        this.mIsEncoderDone = true;
        Log.d("AudioEncoder", "Encode done");
    }

    private long getAudioSeekAnchorTime() {
        if (this.mSeekAnchorTime == -1) {
            this.mSeekAnchorTime = 1024000000 / ((long) this.mDecoderAudioInfo.getSampleRateHz());
            Log.d("AudioEncoder", "mAudioSeekAnchorTime=" + this.mSeekAnchorTime + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mDecoderAudioInfo.getSampleRateHz());
        }
        return this.mSeekAnchorTime;
    }

    private float getFadeTempo(long j2) {
        return (((float) j2) * 1000.0f) / ((((float) this.mDecoderAudioInfo.getSampleRateHz()) * 1000.0f) * ((float) this.mDecoderAudioInfo.getChannelCount()));
    }

    private float getNextVolumeRatio(float f, float f5, float f8) {
        if (f5 < f8) {
            return Math.min(f5 + f, f8);
        }
        return Math.max(f5 - f, f8);
    }

    private float getVolumeRatio(VolumeRatio volumeRatio, long j2) {
        if (isAudioEndFadeOut()) {
            volumeRatio.value = getNextVolumeRatio(getFadeTempo(j2), volumeRatio.value, 0.0f);
        } else if (isContentStartFadeInOutTime()) {
            volumeRatio.value = getNextVolumeRatio(getFadeTempo(j2) * volumeRatio.currentDelta, volumeRatio.value, volumeRatio.currentTarget);
        } else if (this.mMainVolumeRatio.currentTarget != 1.0f && isContentEndFadeInOutTime()) {
            volumeRatio.value = getNextVolumeRatio(getFadeTempo(j2) * volumeRatio.nextDelta, volumeRatio.value, volumeRatio.nextTarget);
        }
        return volumeRatio.value;
    }

    private void initOutputAudioEncoder() {
        Log.d("AudioEncoder", "Audio output format " + this.mDecoderFormat);
        this.mCodec = CodecsHelper.createAudioEncoder(CodecsHelper.getEncoderCodec(Encode.CodecsMime.AUDIO_CODEC_AAC), this.mDecoderFormat);
    }

    private void initOutputAudioFormat() {
        MediaFormat createAudioFormat = MediaFormat.createAudioFormat(Encode.CodecsMime.AUDIO_CODEC_AAC, this.mDecoderAudioInfo.getSampleRateHz(), this.mDecoderAudioInfo.getChannelCount());
        createAudioFormat.setInteger("bitrate", 128000);
        createAudioFormat.setInteger("aac-profile", 2);
        this.mDecoderFormat = createAudioFormat;
    }

    private void initOutputAudioInfo(AudioInfo audioInfo) {
        AudioInfo audioInfo2;
        if (audioInfo.hasStandardSampleRate()) {
            audioInfo2 = new AudioInfo(audioInfo.getSampleRateHz());
        } else {
            audioInfo2 = new AudioInfo();
        }
        this.mDecoderAudioInfo = audioInfo2;
    }

    private void initValues() {
        this.mOutputFormat = null;
        this.mOutputBufferInfo = new MediaCodec.BufferInfo();
        this.mLastWrittenTime = -1;
        this.mSeekTime = -1;
        this.mIsEncoderDone = false;
    }

    private boolean isAudioEndFadeOut() {
        long j2 = this.mDurationUs;
        return isSeekTimeBetween(j2 - 1000000, j2);
    }

    private boolean isContentEndFadeInOutTime() {
        long j2 = this.mContentEndTime;
        return isSeekTimeBetween(j2 - 1000000, j2);
    }

    private boolean isContentStartFadeInOutTime() {
        long j2 = this.mContentStartTime;
        return isSeekTimeBetween(j2, 1000000 + j2);
    }

    private boolean isSeekTimeBetween(long j2, long j3) {
        long j8 = this.mSeekTime;
        if (j8 < j2 || j8 > j3) {
            return false;
        }
        return true;
    }

    private boolean isTranscodingRunningOn(IAudioDecoder iAudioDecoder) {
        if (iAudioDecoder == null || iAudioDecoder.getPendingAudioDecoderOutputBufferIndex() == -1) {
            return false;
        }
        return true;
    }

    private void releaseOutputAudioEncoder() {
        MediaCodec mediaCodec = this.mCodec;
        if (mediaCodec != null) {
            try {
                mediaCodec.stop();
                this.mCodec.release();
                this.mCodec = null;
            } catch (Exception e) {
                Log.d("AudioEncoder", "Exception in releasing output audio encoder.");
                e.printStackTrace();
            }
        }
    }

    private void releaseOutputBuffer(IAudioDecoder... iAudioDecoderArr) {
        for (IAudioDecoder iAudioDecoder : iAudioDecoderArr) {
            if (isTranscodingRunningOn(iAudioDecoder)) {
                iAudioDecoder.releaseOutputBuffer();
            }
        }
    }

    private short[] resample(IAudioDecoder iAudioDecoder, short[] sArr) {
        double sampleRateHz = ((double) iAudioDecoder.getAudioInfo().getSampleRateHz()) / ((double) this.mDecoderAudioInfo.getSampleRateHz());
        if (sampleRateHz < 0.8999999761581421d || sampleRateHz > 1.100000023841858d) {
            return AudioTranscodingHelper.resample(sArr, sampleRateHz);
        }
        return sArr;
    }

    private void setDuration(long j2) {
        this.mDurationUs = j2;
    }

    public MediaFormat getAudioEncoderOutputMediaFormat() {
        return this.mOutputFormat;
    }

    public float getAudioProgress() {
        return ((float) this.mLastWrittenTime) / ((float) this.mDurationUs);
    }

    public long getLastAudioSampleWrittenTimeUs() {
        return this.mLastWrittenTime;
    }

    public void init(long j2, AudioInfo audioInfo) {
        setDuration(j2);
        initOutputAudioInfo(audioInfo);
        initOutputAudioFormat();
        initOutputAudioEncoder();
        initValues();
    }

    public boolean isAudioEncoderDone() {
        return this.mIsEncoderDone;
    }

    public void onSubAudioChanged(long j2, float f, float f5) {
        clearSubAudioData();
        addAudioFadeEffect(j2, f, f5);
    }

    public void release() {
        releaseOutputAudioEncoder();
        setDuration(0);
    }

    public void sendAudioDecoderOutputToEncoder(IAudioDecoder iAudioDecoder, IAudioDecoder iAudioDecoder2) {
        int i2;
        boolean z;
        if (this.mMainAudioData.isEmpty()) {
            i2 = iAudioDecoder.getBufferInfoFlags();
        } else {
            i2 = 0;
        }
        if (!this.mMainAudioData.isEmpty() || isTranscodingRunningOn(iAudioDecoder)) {
            boolean fillBytes = fillBytes(this.mMainAudioData, iAudioDecoder);
            if (!isTranscodingRunningOn(iAudioDecoder2) || fillBytes(this.mSubAudioData, iAudioDecoder2)) {
                z = true;
            } else {
                z = false;
            }
            if (fillBytes && z) {
                short[] extractBytes = extractBytes(this.mMainAudioData);
                short[] extractBytes2 = extractBytes(this.mSubAudioData);
                byte[] mix = AudioTranscodingHelper.mix(AudioTranscodingHelper.scale(extractBytes, getVolumeRatio(this.mMainVolumeRatio, (long) extractBytes.length)), AudioTranscodingHelper.scale(extractBytes2, getVolumeRatio(this.mSubVolumeRatio, (long) extractBytes2.length)));
                if (this.mSeekTime < this.mDurationUs) {
                    enqueueMixedAudio(i2, mix);
                } else {
                    finishEncoding(iAudioDecoder, iAudioDecoder2);
                }
                this.mSeekTime += getAudioSeekAnchorTime();
            }
            releaseOutputBuffer(iAudioDecoder, iAudioDecoder2);
        }
        if ((i2 & 4) != 0 && !iAudioDecoder.isAudioDecoderDone()) {
            finishEncoding(iAudioDecoder, iAudioDecoder2);
        }
    }

    public void sendAudioToMuxer(boolean z, MediaMuxer mediaMuxer, boolean z3) {
        try {
            if (this.mIsEncoderDone) {
                return;
            }
            if ((this.mOutputFormat == null || z) && this.mInputBufferCount >= 0) {
                int dequeueOutputBuffer = this.mCodec.dequeueOutputBuffer(this.mOutputBufferInfo, 10000);
                if (dequeueOutputBuffer == -1) {
                    if (z3) {
                        Log.d("AudioEncoder", "set encoder done without send eos");
                        this.mIsEncoderDone = true;
                    }
                } else if (dequeueOutputBuffer == -2) {
                    this.mOutputFormat = this.mCodec.getOutputFormat();
                    Log.d("AudioEncoder", "audio encoder: output format changed " + this.mOutputFormat);
                } else if (dequeueOutputBuffer < 0) {
                    Log.e("AudioEncoder", "Unexpected result from audio encoder dequeue output format.");
                } else {
                    ByteBuffer outputBuffer = this.mCodec.getOutputBuffer(dequeueOutputBuffer);
                    MediaCodec.BufferInfo bufferInfo = this.mOutputBufferInfo;
                    if ((bufferInfo.flags & 2) != 0) {
                        this.mCodec.releaseOutputBuffer(dequeueOutputBuffer, false);
                        Log.d("AudioEncoder", "audio encoder ignoring BUFFER_FLAG_CODEC_CONFIG  AudioEncoderInputBufferCount : " + this.mInputBufferCount);
                        return;
                    }
                    if (bufferInfo.size != 0) {
                        long j2 = bufferInfo.presentationTimeUs;
                        if (j2 <= this.mDurationUs) {
                            if (this.mLastWrittenTime <= j2) {
                                this.mLastWrittenTime = j2;
                                mediaMuxer.writeSampleData(this.mTrackIndex, outputBuffer, bufferInfo);
                            } else {
                                Log.d("AudioEncoder", "Audio time stamps are not in increasing order.");
                            }
                        }
                    }
                    long j3 = this.mOutputBufferInfo.presentationTimeUs;
                    long j8 = this.mDurationUs;
                    if (j3 >= j8) {
                        this.mSeekTime = j8;
                        Log.d("AudioEncoder", "muxer done  AudioSeekTime " + this.mSeekTime);
                    }
                    if ((this.mOutputBufferInfo.flags & 4) != 0) {
                        Log.d("AudioEncoder", "audio encoder: EOS");
                        this.mIsEncoderDone = true;
                    }
                    this.mCodec.releaseOutputBuffer(dequeueOutputBuffer, false);
                    this.mInputBufferCount--;
                }
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("skip sendAudioToMuxer by Exception "), "AudioEncoder");
        }
    }

    public void setTrackIndex(int i2) {
        a.k(i2, "set audio track index : ", "AudioEncoder");
        this.mTrackIndex = i2;
    }
}
