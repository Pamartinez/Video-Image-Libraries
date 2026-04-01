package com.samsung.android.gallery.module.story.transcode.decoder.audio;

import A.a;
import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import com.samsung.android.gallery.module.story.transcode.config.AudioInfo;
import com.samsung.android.gallery.module.story.transcode.util.CodecsHelper;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.ExecutionException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class AbsAudioDecoder implements IAudioDecoder {
    protected final String TAG = getClass().getSimpleName();
    protected AudioInfo mAudioInfo;
    protected MediaCodec mCodec;
    private boolean mCopyAudio;
    protected MediaExtractor mExtractor;
    private MediaFormat mFormat;
    private boolean mFormatUpdated = false;
    private boolean mIsDecoderDone;
    private boolean mIsExtractorDone;
    private String mMimeType;
    private MediaCodec.BufferInfo mOutputBufferInfo;
    private long mPendingEmptyAudio;
    protected int mPendingOutputBufferIndex;

    private boolean checkValidAudioFormat() {
        int integer;
        MediaFormat mediaFormat = this.mFormat;
        if (mediaFormat == null) {
            Log.e(this.TAG, "Audio format is null");
            return false;
        } else if ("audio/unknown".equals(mediaFormat.getString(MediaDefs.Image.HEIF.HEIF_MIME_BOX))) {
            Log.e(this.TAG, "Audio mime type is unknown. Ignore audio track.");
            return false;
        } else if (!this.mFormat.containsKey("error-type") || (integer = this.mFormat.getInteger("error-type")) == 0) {
            return true;
        } else {
            a.B(integer, "Audio codec error appear : ", this.TAG);
            return false;
        }
    }

    private void dequeueOutputBuffer() {
        if (!this.mIsDecoderDone && this.mPendingOutputBufferIndex == -1) {
            int dequeueOutputBuffer = this.mCodec.dequeueOutputBuffer(this.mOutputBufferInfo, 10000);
            if (dequeueOutputBuffer == -1) {
                Log.e(this.TAG, "Invalid output buffer Index.");
            } else if (dequeueOutputBuffer == -2) {
                String str = this.TAG;
                Log.e(str, "audio decoder: output format changed: " + this.mCodec.getOutputFormat());
            } else if (dequeueOutputBuffer < 0) {
                Log.e(this.TAG, "Unexpected result from audio decoder dequeue output format.");
            } else if ((this.mOutputBufferInfo.flags & 2) != 0) {
                Log.e(this.TAG, "audio decoder: codec config buffer");
                this.mCodec.releaseOutputBuffer(dequeueOutputBuffer, false);
            } else {
                setPendingOutputBufferIndex(dequeueOutputBuffer);
            }
        }
    }

    private long getAudioSeekAnchorTime() {
        return 1024000000 / ((long) this.mAudioInfo.getSampleRateHz());
    }

    private ByteBuffer getDecoderOutputBuffer() {
        return this.mCodec.getOutputBuffer(this.mPendingOutputBufferIndex);
    }

    private void initAudioExtractor() {
        this.mExtractor = createAudioExtractor();
    }

    private void initInputAudioDecoder() {
        if (Encode.CodecsMime.AUDIO_CODEC_AAC.equals(this.mMimeType)) {
            this.mCodec = CodecsHelper.createAudioDecoder(CodecsHelper.getDecoderCodec(this.mMimeType), this.mFormat);
        } else {
            this.mCodec = CodecsHelper.createAudioDecoder(this.mFormat);
        }
    }

    private void initInputAudioFormat() {
        int audioTrackIndex = getAudioTrackIndex();
        if (audioTrackIndex == -1) {
            setCopyAudio(false);
            Log.e(this.TAG, "invalid audio extractor for getting audio input track data");
            return;
        }
        this.mFormat = this.mExtractor.getTrackFormat(audioTrackIndex);
    }

    private boolean isDolbyAudioCodec(String str) {
        if ("audio/ac3".equals(str) || "audio/eac3".equals(str) || "audio/eac3-joc".equals(str) || "audio/ac4".equals(str)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$preprocessByAudioFormat$0() {
        this.mFormatUpdated = true;
    }

    private void preprocessByAudioFormat() {
        if (Encode.CodecsMime.AUDIO_CODEC_AAC.equals(this.mMimeType) || isDolbyAudioCodec(this.mMimeType)) {
            try {
                this.mCodec = CodecsHelper.createAudioDecoder(CodecsHelper.getDecoderCodec(this.mMimeType), this.mFormat);
                MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
                a aVar = new a(this);
                int i2 = -1;
                while (true) {
                    if (this.mFormatUpdated) {
                        break;
                    }
                    int dequeueInputBuffer = this.mCodec.dequeueInputBuffer(10000);
                    if (dequeueInputBuffer != -1) {
                        int readSampleData = this.mExtractor.readSampleData(this.mCodec.getInputBuffer(dequeueInputBuffer), 0);
                        long sampleTime = this.mExtractor.getSampleTime();
                        if (readSampleData <= 0) {
                            if (readSampleData == -1) {
                                setCopyAudio(false);
                                this.mFormatUpdated = true;
                                Log.d(this.TAG, "Audio buffer is empty, size " + readSampleData);
                                break;
                            }
                        } else {
                            this.mCodec.queueInputBuffer(dequeueInputBuffer, 0, readSampleData, sampleTime, this.mExtractor.getSampleFlags());
                        }
                    } else {
                        Log.d(this.TAG, "audio decoder input try again later while preparing audio codec");
                    }
                    CodecsHelper.scheduleAfter(3, aVar);
                    if (!this.mFormatUpdated && i2 == -1) {
                        i2 = this.mCodec.dequeueOutputBuffer(bufferInfo, 10000);
                        if (i2 == -1) {
                            Log.d(this.TAG, "invalid buffer index try again later");
                        } else if (i2 == -2) {
                            AudioInfo audioInfo = this.mAudioInfo;
                            if (audioInfo != null) {
                                audioInfo.updateValues(this.mCodec.getOutputFormat().getInteger("channel-count"), this.mCodec.getOutputFormat().getInteger("sample-rate"));
                            } else {
                                this.mAudioInfo = new AudioInfo(this.mCodec.getOutputFormat().getInteger("channel-count"), this.mCodec.getOutputFormat().getInteger("sample-rate"));
                            }
                            if (isDolbyAudioCodec(this.mMimeType)) {
                                preprocessDolbyMultiChannelAudioFormat();
                            }
                            Log.e(this.TAG, "audio decoder: output format changed: SampleRate" + this.mAudioInfo.getSampleRateHz() + ",ChannelCount" + this.mAudioInfo.getChannelCount());
                            this.mFormatUpdated = true;
                        } else if (i2 < 0) {
                            Log.e(this.TAG, "Unexpected result from audio decoder dequeue output format.");
                        } else if ((bufferInfo.flags & 2) != 0) {
                            Log.d(this.TAG, "audio decoder: codec config buffer");
                            this.mCodec.releaseOutputBuffer(i2, false);
                        }
                    }
                }
                releaseInputAudioDecoder();
                seekExtractor(0);
            } catch (IOException | InterruptedException e) {
                Throwable th = e;
                th.printStackTrace();
                throw new IOException(th.getMessage());
            } catch (ExecutionException e7) {
                ExecutionException executionException = e7;
                executionException.printStackTrace();
                throw new IOException(executionException.getMessage(), executionException.getCause());
            }
        }
    }

    private void preprocessDolbyMultiChannelAudioFormat() {
        if (this.mAudioInfo.getChannelCount() > 2) {
            this.mAudioInfo.setChannelCount(2);
            Log.d(this.TAG, "Audio ac3 type :  mOutputAudioChannelCount is changed.");
        }
    }

    private void releaseInputAudioDecoder() {
        MediaCodec mediaCodec = this.mCodec;
        if (mediaCodec != null) {
            try {
                mediaCodec.stop();
                this.mCodec.release();
                this.mCodec = null;
            } catch (Exception e) {
                Log.d(this.TAG, "Exception in releasing input audio decoder.");
                e.printStackTrace();
            }
        }
    }

    private void seekExtractor(long j2) {
        this.mExtractor.seekTo(j2, 0);
        this.mPendingEmptyAudio = Math.max(this.mExtractor.getSampleTime() - j2, 0);
    }

    private void sendAudioToDecoder() {
        if (!this.mIsExtractorDone) {
            if (canPassSampleDataToDecoder(this.mExtractor)) {
                int dequeueInputBuffer = this.mCodec.dequeueInputBuffer(10000);
                if (dequeueInputBuffer == -1) {
                    Log.e(this.TAG, "audio decoder input try again later");
                    return;
                } else {
                    passAudioSampleDataFromExtractorToDecoder(this.mExtractor.getSampleTime(), this.mExtractor.readSampleData(this.mCodec.getInputBuffer(dequeueInputBuffer), 0), dequeueInputBuffer);
                }
            } else {
                handleFailedToPassSampleDataToDecoder();
            }
            if (isExtractorDone()) {
                Log.d(this.TAG, "audio decoder sending EOS");
                int dequeueInputBuffer2 = this.mCodec.dequeueInputBuffer(10000);
                if (dequeueInputBuffer2 == -1) {
                    Log.e(this.TAG, "audio decoder input try again later");
                    setAudioExtractDone(false);
                    return;
                }
                this.mCodec.queueInputBuffer(dequeueInputBuffer2, 0, 0, 0, 4);
            }
        }
    }

    private void updateAudioConfigsFromInputAudioFormat() {
        String str = this.TAG;
        Log.d(str, "Audio input format " + this.mFormat);
        this.mMimeType = this.mFormat.getString(MediaDefs.Image.HEIF.HEIF_MIME_BOX);
        AudioInfo audioInfo = new AudioInfo(this.mFormat.getInteger("channel-count"), this.mFormat.getInteger("sample-rate"));
        this.mAudioInfo = audioInfo;
        try {
            audioInfo.setMaxInputSize(this.mFormat.getInteger("max-input-size"));
        } catch (NullPointerException unused) {
            Log.d(this.TAG, "Audio max input size not defined");
        }
    }

    public abstract boolean canPassSampleDataToDecoder(MediaExtractor mediaExtractor);

    public abstract MediaExtractor createAudioExtractor();

    public boolean extractAudio() {
        if (this.mPendingEmptyAudio > 0) {
            this.mPendingOutputBufferIndex = 0;
            return true;
        }
        sendAudioToDecoder();
        dequeueOutputBuffer();
        return true;
    }

    public AudioInfo getAudioInfo() {
        return this.mAudioInfo;
    }

    public int getAudioTrackIndex() {
        return CodecsHelper.getAndSelectAudioTrackIndex(this.mExtractor);
    }

    public int getBufferInfoFlags() {
        if (this.mPendingEmptyAudio > 0) {
            return 0;
        }
        return this.mOutputBufferInfo.flags;
    }

    public short[] getContentShort() {
        if (this.mPendingEmptyAudio > 0) {
            return new short[2048];
        }
        int i2 = this.mOutputBufferInfo.size;
        if (i2 < 0) {
            return new short[0];
        }
        short[] sArr = new short[(i2 / 2)];
        getDecoderOutputBuffer().duplicate().order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(sArr);
        return sArr;
    }

    public int getPendingAudioDecoderOutputBufferIndex() {
        return this.mPendingOutputBufferIndex;
    }

    public abstract void handleFailedToPassSampleDataToDecoder();

    public boolean hasMoreData(long j2, int i2) {
        if (i2 > 0) {
            return true;
        }
        return false;
    }

    public boolean isAudioDecoderDone() {
        return this.mIsDecoderDone;
    }

    public boolean isCopyAudio() {
        return this.mCopyAudio;
    }

    public boolean isExtractorDone() {
        return this.mIsExtractorDone;
    }

    public void onEncodingFinished() {
        this.mIsExtractorDone = true;
        this.mIsDecoderDone = true;
    }

    public void passAudioSampleDataFromExtractorToDecoder(long j2, int i2, int i7) {
        if (hasMoreData(j2, i2)) {
            int i8 = i2;
            int i10 = i7;
            this.mCodec.queueInputBuffer(i10, 0, i8, j2, this.mExtractor.getSampleFlags());
            this.mExtractor.advance();
            return;
        }
        this.mIsExtractorDone = true;
    }

    public void prepareAudioCodec() {
        initAudioExtractor();
        initInputAudioFormat();
        setCopyAudio(checkValidAudioFormat());
        if (isCopyAudio()) {
            updateAudioConfigsFromInputAudioFormat();
            preprocessByAudioFormat();
            initInputAudioDecoder();
        }
    }

    public void prepareAudioEncoding(long j2) {
        if (isCopyAudio()) {
            this.mIsExtractorDone = false;
            this.mIsDecoderDone = false;
            this.mOutputBufferInfo = new MediaCodec.BufferInfo();
            setPendingOutputBufferIndex(-1);
            if (j2 != 0 && this.mExtractor != null) {
                seekExtractor(j2);
            }
        }
    }

    public void release() {
        releaseInputAudioDecoder();
        releaseAudioExtractor();
    }

    public void releaseAudioExtractor() {
        MediaExtractor mediaExtractor = this.mExtractor;
        if (mediaExtractor != null) {
            try {
                mediaExtractor.release();
                this.mExtractor = null;
            } catch (Exception e) {
                Log.d(this.TAG, "Exception in releasing audio extractor.");
                e.printStackTrace();
            }
        }
    }

    public void releaseOutputBuffer() {
        long j2 = this.mPendingEmptyAudio;
        if (j2 > 0) {
            this.mPendingEmptyAudio = j2 - getAudioSeekAnchorTime();
        } else {
            MediaCodec mediaCodec = this.mCodec;
            if (mediaCodec != null) {
                mediaCodec.releaseOutputBuffer(this.mPendingOutputBufferIndex, false);
            }
        }
        setPendingOutputBufferIndex(-1);
    }

    public void setAudioExtractDone(boolean z) {
        this.mIsExtractorDone = z;
    }

    public void setCopyAudio(boolean z) {
        this.mCopyAudio = z;
    }

    public void setPendingOutputBufferIndex(int i2) {
        this.mPendingOutputBufferIndex = i2;
    }
}
