package com.samsung.android.gallery.module.story.transcode.transcoder.audio;

import N2.j;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.media.MetadataManager;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.module.story.transcode.config.EncodeInfo;
import com.samsung.android.gallery.module.story.transcode.decoder.audio.EmptyAudioDecoder;
import com.samsung.android.gallery.module.story.transcode.decoder.audio.IAudioDecoder;
import com.samsung.android.gallery.module.story.transcode.decoder.audio.MediaAudioDecoder;
import com.samsung.android.gallery.module.story.transcode.encoder.audio.AudioEncoder;
import com.samsung.android.gallery.module.story.transcode.util.OnErrorListener;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.shotmode.RecordingMode;
import com.samsung.android.gallery.support.utils.MediaHelper;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AudioTranscoder extends AbsAudioTranscoder {
    private IAudioDecoder[] mDecoder = new IAudioDecoder[2];
    private int mIndex;

    public AudioTranscoder(ArrayList<ThumbnailInterface> arrayList, EncodeInfo encodeInfo, OnErrorListener onErrorListener) {
        super(arrayList, encodeInfo, onErrorListener);
    }

    private boolean checkEOS(boolean z) {
        if (isCurrentFlagEOS() || isDecodingLastItem()) {
            return true;
        }
        moveToNextItem();
        return extractAudio(z);
    }

    private IAudioDecoder createDecoder(int i2) {
        String str;
        MediaHelper.VideoInfo load;
        long durationUs = getDurationUs(i2);
        if (isSupportedVideo(i2)) {
            ThumbnailInterface thumbnailInterface = this.mItems.get(i2);
            if (thumbnailInterface.isUriItem()) {
                str = null;
            } else {
                str = thumbnailInterface.getPath();
            }
            MediaAudioDecoder mediaAudioDecoder = new MediaAudioDecoder(str, ContentUri.getUri(thumbnailInterface), durationUs);
            if (thumbnailInterface.isMotionPhoto() && (load = MetadataManager.getInstance().lambda$preload$0(thumbnailInterface)) != null) {
                mediaAudioDecoder.setFDInfo(load.offset, load.length);
            }
            try {
                mediaAudioDecoder.prepareAudioCodec();
                if (mediaAudioDecoder.isCopyAudio()) {
                    mediaAudioDecoder.prepareAudioEncoding(((long) this.mEncodeInfo.getItemStartTimeMs(thumbnailInterface)) * 1000);
                    return mediaAudioDecoder;
                }
            } catch (Exception e) {
                j.v("failed to prepare audio codex : ", e, "AudioTranscoder");
            }
        }
        return new EmptyAudioDecoder(durationUs);
    }

    private boolean extractAudioInternal(boolean z) {
        if (!canExtract(z) || !getCurrentDecoder().extractAudio()) {
            return false;
        }
        return true;
    }

    private IAudioDecoder getCurrentDecoder() {
        return this.mDecoder[this.mIndex % 2];
    }

    private void initDecoder() {
        this.mIndex = 0;
        for (int i2 = 0; i2 < Math.min(2, this.mItems.size()); i2++) {
            this.mDecoder[i2] = createDecoder(i2);
        }
    }

    private boolean isCurrentFlagEOS() {
        if (getCurrentDecoder().getBufferInfoFlags() != 4) {
            return true;
        }
        return false;
    }

    private boolean isDecodingLastItem() {
        if (this.mIndex == this.mItems.size() - 1) {
            return true;
        }
        return false;
    }

    private boolean isSupportedRecordingMode(int i2) {
        if (2 == i2 || 5 == i2 || RecordingMode.isSlowMo(i2) || RecordingMode.isSuperSlowMo(i2)) {
            return false;
        }
        return true;
    }

    private void releaseDecoder(IAudioDecoder iAudioDecoder) {
        if (iAudioDecoder != null) {
            iAudioDecoder.release();
        }
    }

    public boolean extractAudio(boolean z) {
        if (!extractAudioInternal(z) || !checkEOS(z)) {
            return false;
        }
        return true;
    }

    public IAudioDecoder getMainDecoder() {
        return getCurrentDecoder();
    }

    public boolean isDecoderDone() {
        if (this.mIndex >= this.mItems.size()) {
            return true;
        }
        return false;
    }

    public boolean isSupportedVideo(int i2) {
        if (i2 >= this.mItems.size()) {
            return false;
        }
        ThumbnailInterface thumbnailInterface = this.mItems.get(i2);
        if (thumbnailInterface.isMotionPhoto()) {
            return true;
        }
        if (!StoryHelper.isVideoItem(thumbnailInterface) || !isSupportedRecordingMode(thumbnailInterface.getRecordingMode())) {
            return false;
        }
        return true;
    }

    public void moveToNextItem() {
        if (this.mIndex < this.mItems.size() - 2) {
            releaseDecoder(getCurrentDecoder());
            IAudioDecoder[] iAudioDecoderArr = this.mDecoder;
            int i2 = this.mIndex;
            iAudioDecoderArr[i2 % 2] = createDecoder(i2 + 2);
        }
        this.mIndex++;
    }

    public void prepare() {
        initDecoder();
        AudioEncoder audioEncoder = new AudioEncoder();
        this.mAudioEncoder = audioEncoder;
        audioEncoder.init(getTotalDurationUs(), this.mDecoder[0].getAudioInfo());
    }

    public void releaseAudioDecoder() {
        for (IAudioDecoder releaseDecoder : this.mDecoder) {
            releaseDecoder(releaseDecoder);
        }
        this.mDecoder = null;
    }
}
