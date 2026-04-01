package com.samsung.android.gallery.module.story.transcode.transcoder.audio;

import A.a;
import android.media.MediaMuxer;
import android.os.Handler;
import com.samsung.android.gallery.module.story.transcode.config.EncodeInfo;
import com.samsung.android.gallery.module.story.transcode.decoder.audio.IAudioDecoder;
import com.samsung.android.gallery.module.story.transcode.encoder.audio.AudioEncoder;
import com.samsung.android.gallery.module.story.transcode.transcoder.ITranscoder;
import com.samsung.android.gallery.module.story.transcode.util.OnErrorListener;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.io.IOException;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbsAudioTranscoder implements ITranscoder {
    private final Object RELEASE_LOCK = new Object();
    protected AudioEncoder mAudioEncoder;
    protected final EncodeInfo mEncodeInfo;
    private final OnErrorListener mErrorListener;
    protected final ArrayList<ThumbnailInterface> mItems;
    private final Handler mMuxerWriteHandler = new Handler(ThreadUtil.createBackgroundThreadLooper("MuxerWriter"));
    private final AudioTranscodeHandler mTranscodeHandler;

    public AbsAudioTranscoder(ArrayList<ThumbnailInterface> arrayList, EncodeInfo encodeInfo, OnErrorListener onErrorListener) {
        this.mItems = arrayList;
        this.mEncodeInfo = encodeInfo;
        this.mErrorListener = onErrorListener;
        this.mTranscodeHandler = new AudioTranscodeHandler(ThreadUtil.createBackgroundThreadLooper("AudioTranscoder"));
    }

    private boolean isAudioEncoderDone() {
        AudioEncoder audioEncoder = this.mAudioEncoder;
        if (audioEncoder == null || !audioEncoder.isAudioEncoderDone()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$transcode$0(MediaMuxer mediaMuxer, boolean z) {
        synchronized (this.RELEASE_LOCK) {
            try {
                transcodeInternal(mediaMuxer, z);
            } catch (IOException e) {
                this.mErrorListener.onError(e);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$transcodeInternal$1(boolean z, MediaMuxer mediaMuxer) {
        try {
            AudioEncoder audioEncoder = this.mAudioEncoder;
            if (audioEncoder != null) {
                audioEncoder.sendAudioToMuxer(z, mediaMuxer, isDecoderDone());
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("fail to sendAudioToMuxer e="), "AudioTranscoder");
        }
    }

    private void releaseAudioMixer() {
        AudioEncoder audioEncoder = this.mAudioEncoder;
        if (audioEncoder != null) {
            audioEncoder.release();
            this.mAudioEncoder = null;
        }
    }

    private void releaseHandler() {
        this.mMuxerWriteHandler.getLooper().quitSafely();
        AudioTranscodeHandler audioTranscodeHandler = this.mTranscodeHandler;
        if (audioTranscodeHandler != null) {
            audioTranscodeHandler.stop();
        }
    }

    public void addTrack(MediaMuxer mediaMuxer) {
        if (copyAudio()) {
            AudioEncoder audioEncoder = this.mAudioEncoder;
            audioEncoder.setTrackIndex(mediaMuxer.addTrack(audioEncoder.getAudioEncoderOutputMediaFormat()));
        }
    }

    public boolean canExtract(boolean z) {
        if (this.mAudioEncoder.getAudioEncoderOutputMediaFormat() == null || z) {
            return true;
        }
        return false;
    }

    public boolean copyAudio() {
        return true;
    }

    public abstract boolean extractAudio(boolean z);

    public long getDurationUs(int i2) {
        int i7;
        if (i2 >= this.mItems.size()) {
            i7 = 2000;
        } else {
            i7 = this.mEncodeInfo.getItemDuration(this.mItems.get(i2));
        }
        return ((long) i7) * 1000;
    }

    public abstract IAudioDecoder getMainDecoder();

    public float getProgress() {
        AudioEncoder audioEncoder = this.mAudioEncoder;
        if (audioEncoder == null) {
            return 0.0f;
        }
        return audioEncoder.getAudioProgress();
    }

    public IAudioDecoder getSubAudioDecoder(boolean z) {
        return null;
    }

    public long getTotalDurationUs() {
        return ((long) this.mEncodeInfo.duration) * 1000;
    }

    public boolean hasOutputFormat() {
        AudioEncoder audioEncoder = this.mAudioEncoder;
        if (audioEncoder == null || audioEncoder.getAudioEncoderOutputMediaFormat() == null) {
            return false;
        }
        return true;
    }

    public boolean isAudioEncodingRunning() {
        return !isAudioEncoderDone();
    }

    public abstract boolean isDecoderDone();

    public boolean isReady() {
        if (!copyAudio() || hasOutputFormat()) {
            return true;
        }
        return false;
    }

    public boolean isRunning() {
        return isAudioEncodingRunning();
    }

    public void release() {
        releaseHandler();
        synchronized (this.RELEASE_LOCK) {
            releaseAudioMixer();
            releaseAudioDecoder();
        }
    }

    public abstract void releaseAudioDecoder();

    public void transcode(MediaMuxer mediaMuxer, boolean z) {
        this.mTranscodeHandler.addJob(new ma.a(this, mediaMuxer, z));
    }

    public void transcodeInternal(MediaMuxer mediaMuxer, boolean z) {
        if (this.mAudioEncoder != null && copyAudio()) {
            prepareTranscode();
            if (extractAudio(z)) {
                this.mAudioEncoder.sendAudioDecoderOutputToEncoder(getMainDecoder(), getSubAudioDecoder(z));
                this.mMuxerWriteHandler.post(new ma.a(this, z, mediaMuxer));
            }
        }
    }

    public void prepareTranscode() {
    }
}
