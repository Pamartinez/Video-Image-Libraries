package com.samsung.android.gallery.module.media;

import A4.C0375j;
import E9.a;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.motionphoto.MotionPhotoViewMode;
import com.samsung.android.gallery.support.library.abstraction.MediaCaptureCompat;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayback;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaMotionPhotoCaptureWorker extends MediaCaptureWorker {
    public MediaMotionPhotoCaptureWorker(String str, Consumer<Integer> consumer, Consumer<Boolean> consumer2, MediaCaptureCompat.OnErrorListener onErrorListener) {
        super(str, consumer, consumer2, onErrorListener);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$prepareInternal$0(MediaPlayback mediaPlayback) {
        if (mediaPlayback.speed == 0.25f) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareInternal$1(MediaPlayback mediaPlayback) {
        this.mMediaCaptureCompat.setStartEndTime(mediaPlayback.startMs, mediaPlayback.endMs);
        this.mMediaCaptureCompat.setDynamicViewingConfigurations(mediaPlayback.startMs, mediaPlayback.endMs, mediaPlayback.speed);
    }

    public String getFileInputStreamName(FileItemInterface fileItemInterface) {
        return exportTempMotionPhotoVideo(fileItemInterface.getPath());
    }

    public void prepareInternal(FileItemInterface fileItemInterface, List<MediaPlayback> list, Consumer<Boolean> consumer) {
        this.mMediaCaptureCompat.setParameter(ErrorCodeConvertor.TEMP_AGENT_INVALID_PARAMETER, 1);
        if (((MotionPhotoViewMode) Optional.ofNullable(DetailsData.of(fileItemInterface).motionPhotoViewMode).orElse(MotionPhotoViewMode.ON)) == MotionPhotoViewMode.BOOMERANG) {
            MediaPlayback mediaPlayback = list.get(0);
            this.mMediaCaptureCompat.setParameter(ErrorCodeConvertor.TEMP_AGENT_INVALID_MSISDN, 3);
            this.mMediaCaptureCompat.setParameter(ErrorCodeConvertor.TEMP_AGENT_NOT_ACTIVATED, 80);
            this.mMediaCaptureCompat.setBoomerangConfiguration(mediaPlayback.startMs, mediaPlayback.endMs, mediaPlayback.speed, 3);
        } else {
            this.mMediaCaptureCompat.setParameter(ErrorCodeConvertor.TEMP_AGENT_INVALID_MSISDN, 2);
            this.mMediaCaptureCompat.setParameter(ErrorCodeConvertor.TEMP_AGENT_SERVICE_DISABLED, 1);
            this.mMediaCaptureCompat.setParameter(ErrorCodeConvertor.TEMP_AGENT_NOT_ACTIVATED, 81);
            list.stream().filter(new C0375j(26)).findFirst().ifPresent(new a(3, this));
            this.mMediaCaptureCompat.setMediaCapturePlaybacks(list);
        }
        this.mMediaCaptureCompat.setOnPreparedListener(consumer);
        this.mMediaCaptureCompat.prepare();
    }

    public boolean supportInternal(FileItemInterface fileItemInterface) {
        if (getFileInputStreamName(fileItemInterface) != null) {
            return super.supportInternal(fileItemInterface);
        }
        Log.w(this.TAG, "prepareMotionPhotoCapture : failed export motion photo video!");
        return false;
    }
}
