package com.samsung.android.gallery.module.media;

import c0.C0086a;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.DynamicViewData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dynamicview.DynamicViewPlayInfo;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.abstraction.MediaCaptureCompat;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayback;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDynamicCaptureWorker extends MediaCaptureWorker {
    public MediaDynamicCaptureWorker(String str, Consumer<Integer> consumer, Consumer<Boolean> consumer2, MediaCaptureCompat.OnErrorListener onErrorListener) {
        super(str, consumer, consumer2, onErrorListener);
    }

    private int getRecordingMode(MediaItem mediaItem) {
        DynamicViewPlayInfo dynamicViewPlayInfo = DynamicViewData.of(mediaItem).dynamicViewPlayInfo;
        if (dynamicViewPlayInfo == null || !SdkConfig.atLeast(SdkConfig.SEM.T_MR1)) {
            return -1;
        }
        return dynamicViewPlayInfo.getConvertedRecordingMode();
    }

    public void prepareInternal(FileItemInterface fileItemInterface, List<MediaPlayback> list, Consumer<Boolean> consumer) {
        this.mMediaCaptureCompat.setStartEndTime(list.get(0).startMs, ((MediaPlayback) C0086a.f(1, list)).endMs);
        this.mMediaCaptureCompat.setDynamicViewFormat(getRecordingMode((MediaItem) fileItemInterface));
        this.mMediaCaptureCompat.setMediaCapturePlaybacks(list);
        this.mMediaCaptureCompat.setOnPreparedListener(consumer);
        this.mMediaCaptureCompat.prepare();
    }
}
