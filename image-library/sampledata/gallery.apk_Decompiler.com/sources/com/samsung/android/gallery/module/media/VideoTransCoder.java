package com.samsung.android.gallery.module.media;

import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.abstraction.TranscodingOptions;
import com.samsung.android.gallery.support.library.abstraction.VideoTranscoderCompat;
import com.samsung.android.gallery.support.utils.Log;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoTransCoder extends Thread {
    private FileItemInterface mOriginalItem;
    private final String mOutFilePath;
    private final VideoTranscoderCompat mTranscoderCompat;

    public VideoTransCoder(String str, Consumer<Integer> consumer, Consumer<Boolean> consumer2) {
        VideoTranscoderCompat createVideoTranscoder = SeApiCompat.createVideoTranscoder();
        this.mTranscoderCompat = createVideoTranscoder;
        createVideoTranscoder.setOnProgressListener(consumer);
        createVideoTranscoder.setOnCompleteListener(consumer2);
        this.mOutFilePath = str;
    }

    public void finish() {
        Log.d("VideoTransCoder", "stop transcoding");
        this.mTranscoderCompat.stop();
    }

    public FileItemInterface getOriginalItem() {
        return this.mOriginalItem;
    }

    public int prepare(FileItemInterface fileItemInterface) {
        boolean z;
        this.mOriginalItem = fileItemInterface;
        TranscodingOptions.Builder frameRate = new TranscodingOptions.Builder().setInput(fileItemInterface.getPath()).setOutput(this.mOutFilePath).setWidth(fileItemInterface.getWidth()).setHeight(fileItemInterface.getHeight()).setFrameRate(VideoPropData.of(fileItemInterface).videoFrameRate);
        if (fileItemInterface.isHdr10Video() || fileItemInterface.isHlgVideo()) {
            z = true;
        } else {
            z = false;
        }
        this.mTranscoderCompat.setOptions(frameRate.setHdr(z).setApv(MediaItemUtil.isApv((MediaItem) fileItemInterface)).build());
        return this.mTranscoderCompat.getEstimatedSize();
    }

    public void run() {
        this.mTranscoderCompat.encode();
    }
}
