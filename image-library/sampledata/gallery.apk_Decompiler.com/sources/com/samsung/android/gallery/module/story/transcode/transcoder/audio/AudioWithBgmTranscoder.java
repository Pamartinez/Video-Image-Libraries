package com.samsung.android.gallery.module.story.transcode.transcoder.audio;

import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.module.story.transcode.config.EncodeInfo;
import com.samsung.android.gallery.module.story.transcode.decoder.audio.BGMAudioDecoder;
import com.samsung.android.gallery.module.story.transcode.decoder.audio.IAudioDecoder;
import com.samsung.android.gallery.module.story.transcode.decoder.audio.MediaAudioDecoder;
import com.samsung.android.gallery.module.story.transcode.encoder.audio.AudioEncoder;
import com.samsung.android.gallery.module.story.transcode.util.OnErrorListener;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.shotmode.RecordingMode;
import com.samsung.android.gallery.support.utils.FileUtils;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AudioWithBgmTranscoder extends AbsAudioTranscoder {
    private int mIndex = 0;
    private IAudioDecoder mMainDecoder;
    private long mNextItemStartUs = -1;
    private IAudioDecoder mSubDecoder;

    public AudioWithBgmTranscoder(ArrayList<ThumbnailInterface> arrayList, EncodeInfo encodeInfo, OnErrorListener onErrorListener) {
        super(arrayList, encodeInfo, onErrorListener);
    }

    private IAudioDecoder createMainDecoder() {
        if (!TextUtils.isEmpty(FileUtils.getExtension(this.mEncodeInfo.bgmName))) {
            return new MediaAudioDecoder(this.mEncodeInfo.bgmName, (Uri) null, true);
        }
        EncodeInfo encodeInfo = this.mEncodeInfo;
        return new BGMAudioDecoder(encodeInfo.bgmName, encodeInfo.duration);
    }

    private void initSubAudioDecoder(String str, Uri uri) {
        MediaAudioDecoder mediaAudioDecoder = new MediaAudioDecoder(str, uri, true);
        this.mSubDecoder = mediaAudioDecoder;
        mediaAudioDecoder.prepareAudioCodec();
        this.mSubDecoder.prepareAudioEncoding(0);
    }

    private boolean isCopyAudio(IAudioDecoder iAudioDecoder) {
        if (iAudioDecoder == null || !iAudioDecoder.isCopyAudio()) {
            return false;
        }
        return true;
    }

    private boolean isSupportedRecordingMode(int i2) {
        if (2 == i2 || 5 == i2 || RecordingMode.isSlowMo(i2) || RecordingMode.isSuperSlowMo(i2)) {
            return false;
        }
        return true;
    }

    private boolean isSupportedVideo(int i2) {
        if (i2 >= this.mItems.size()) {
            return false;
        }
        ThumbnailInterface thumbnailInterface = this.mItems.get(i2);
        if (!StoryHelper.isVideoItem(thumbnailInterface) || !isSupportedRecordingMode(thumbnailInterface.getRecordingMode())) {
            return false;
        }
        return true;
    }

    private void releaseBgmDecoder() {
        IAudioDecoder iAudioDecoder = this.mMainDecoder;
        if (iAudioDecoder != null) {
            iAudioDecoder.release();
            this.mMainDecoder = null;
        }
    }

    private void releaseSubAudioDecoder() {
        IAudioDecoder iAudioDecoder = this.mSubDecoder;
        if (iAudioDecoder != null) {
            iAudioDecoder.release();
            this.mSubDecoder = null;
        }
    }

    public boolean canExtract(IAudioDecoder iAudioDecoder, boolean z) {
        if (!isCopyAudio(iAudioDecoder) || !canExtract(z)) {
            return false;
        }
        return true;
    }

    public void changeSubAudioDecoder(int i2) {
        boolean z;
        float f;
        String str;
        releaseSubAudioDecoder();
        if (isSupportedVideo(i2)) {
            ThumbnailInterface thumbnailInterface = this.mItems.get(i2);
            if (thumbnailInterface.isUriItem()) {
                str = null;
            } else {
                str = thumbnailInterface.getPath();
            }
            initSubAudioDecoder(str, ContentUri.getUri(thumbnailInterface));
            z = this.mSubDecoder.isCopyAudio();
        } else {
            z = false;
        }
        long durationUs = getDurationUs(i2);
        AudioEncoder audioEncoder = this.mAudioEncoder;
        if (z) {
            f = getVolumeRatio(i2);
        } else {
            f = 0.0f;
        }
        audioEncoder.onSubAudioChanged(durationUs, f, getVolumeRatio(i2 + 1));
        this.mNextItemStartUs += durationUs;
    }

    public boolean copyAudio() {
        return isCopyAudio(this.mMainDecoder);
    }

    public boolean extractAudio(boolean z) {
        return extractAudio(this.mMainDecoder, z);
    }

    public IAudioDecoder getMainDecoder() {
        return this.mMainDecoder;
    }

    public IAudioDecoder getSubAudioDecoder(boolean z) {
        if (extractAudio(this.mSubDecoder, z)) {
            return this.mSubDecoder;
        }
        return null;
    }

    public float getVolumeRatio(int i2) {
        if (isSupportedVideo(i2)) {
            return 1.0f;
        }
        return 0.0f;
    }

    public boolean isAudioEncodingRunning() {
        if (!isCopyAudio(this.mMainDecoder) || !super.isAudioEncodingRunning()) {
            return false;
        }
        return true;
    }

    public boolean isDecoderDone() {
        return this.mMainDecoder.isAudioDecoderDone();
    }

    public void prepare() {
        IAudioDecoder createMainDecoder = createMainDecoder();
        this.mMainDecoder = createMainDecoder;
        createMainDecoder.prepareAudioCodec();
        if (this.mMainDecoder.isCopyAudio()) {
            AudioEncoder audioEncoder = new AudioEncoder();
            this.mAudioEncoder = audioEncoder;
            audioEncoder.init(getTotalDurationUs(), this.mMainDecoder.getAudioInfo());
            this.mMainDecoder.prepareAudioEncoding(0);
        }
    }

    public void prepareTranscode() {
        if (this.mNextItemStartUs < this.mAudioEncoder.getLastAudioSampleWrittenTimeUs() && isAudioEncodingRunning()) {
            int i2 = this.mIndex;
            this.mIndex = i2 + 1;
            changeSubAudioDecoder(i2);
        }
    }

    public void releaseAudioDecoder() {
        releaseBgmDecoder();
        releaseSubAudioDecoder();
    }

    private boolean extractAudio(IAudioDecoder iAudioDecoder, boolean z) {
        return canExtract(iAudioDecoder, z) && iAudioDecoder.extractAudio();
    }
}
