package com.samsung.android.gallery.module.story.transcode.decoder.audio;

import com.samsung.android.gallery.module.story.transcode.config.AudioInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IAudioDecoder {
    boolean extractAudio();

    AudioInfo getAudioInfo();

    int getBufferInfoFlags();

    short[] getContentShort();

    int getPendingAudioDecoderOutputBufferIndex();

    boolean isAudioDecoderDone();

    boolean isCopyAudio();

    void onEncodingFinished();

    void prepareAudioCodec();

    void prepareAudioEncoding(long j2);

    void release();

    void releaseOutputBuffer();

    void setFDInfo(long j2, long j3) {
    }
}
