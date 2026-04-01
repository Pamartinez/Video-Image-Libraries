package com.samsung.android.gallery.support.library.v11.media;

import com.samsung.android.gallery.support.library.abstraction.TranscodingOptions;
import com.samsung.android.gallery.support.library.v9.media.SemVideoTranscoderCompat110;
import com.samsung.android.media.codec.SemVideoTranscoder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SemVideoTranscoderCompat120 extends SemVideoTranscoderCompat110 {
    public void setEncodingOptions(SemVideoTranscoder semVideoTranscoder, TranscodingOptions transcodingOptions) {
        semVideoTranscoder.setOutputConfig(SemVideoTranscoder.ConfigType.videoCodec, transcodingOptions.videoCodec);
        semVideoTranscoder.setOutputConfig(SemVideoTranscoder.ConfigType.audioCodec, transcodingOptions.audioCodec);
        int i2 = transcodingOptions.colorDepth;
        if (i2 > 0) {
            semVideoTranscoder.setOutputConfig(SemVideoTranscoder.ConfigType.bitDepth, i2);
        }
        int i7 = transcodingOptions.frameRate;
        if (i7 > 0) {
            semVideoTranscoder.setOutputConfig(SemVideoTranscoder.ConfigType.frameRate, i7);
        }
    }

    public String tag() {
        return "SemVideoTranscoderCompat120";
    }
}
