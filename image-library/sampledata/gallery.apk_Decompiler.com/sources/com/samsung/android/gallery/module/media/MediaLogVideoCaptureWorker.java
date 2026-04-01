package com.samsung.android.gallery.module.media;

import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.support.library.abstraction.MediaCaptureCompat;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayback;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaLogVideoCaptureWorker extends MediaCaptureWorker {
    public MediaLogVideoCaptureWorker(String str, Consumer<Integer> consumer, Consumer<Boolean> consumer2, MediaCaptureCompat.OnErrorListener onErrorListener) {
        super(str, consumer, consumer2, onErrorListener);
    }

    public void prepareInternal(FileItemInterface fileItemInterface, List<MediaPlayback> list, Consumer<Boolean> consumer) {
        this.mMediaCaptureCompat.setParameter(ErrorCodeConvertor.TEMP_AGENT_INVALID_MSISDN, 4);
        this.mMediaCaptureCompat.setParameter(ErrorCodeConvertor.TEMP_AGENT_INVALID_PARAMETER, 1);
        this.mMediaCaptureCompat.setParameter(ErrorCodeConvertor.TEMP_AGENT_SERVICE_DISABLED, 1);
        this.mMediaCaptureCompat.setStartEndTime(0, fileItemInterface.getFileDuration());
        this.mMediaCaptureCompat.setOnPreparedListener(consumer);
        this.mMediaCaptureCompat.prepare();
    }
}
