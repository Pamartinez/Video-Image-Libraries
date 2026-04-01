package com.samsung.android.gallery.support.library.v9.media;

import com.samsung.android.gallery.support.library.v0.media.SemVideoTranscoderCompat;
import com.samsung.android.media.codec.SemVideoTranscoder;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SemVideoTranscoderCompat110 extends SemVideoTranscoderCompat implements SemVideoTranscoder.ProgressListener {
    public void onProgressChanged(int i2) {
        int i7;
        Consumer<Integer> consumer = this.mOnProgressListener;
        if (consumer != null && (i7 = (i2 / 10) * 10) > this.mProgress) {
            this.mProgress = i7;
            consumer.accept(Integer.valueOf(i7));
        }
    }

    public void setProgressListener(SemVideoTranscoder semVideoTranscoder) {
        semVideoTranscoder.setProgressListener(this);
    }

    public String tag() {
        return "SemVideoTranscoderCompat110";
    }

    public void startProgressUpdate() {
    }
}
