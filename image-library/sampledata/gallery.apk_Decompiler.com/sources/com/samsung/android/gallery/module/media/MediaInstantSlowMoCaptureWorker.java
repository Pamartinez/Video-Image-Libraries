package com.samsung.android.gallery.module.media;

import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.support.library.abstraction.MediaCaptureCompat;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayback;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaInstantSlowMoCaptureWorker extends MediaCaptureWorker {
    public MediaInstantSlowMoCaptureWorker(String str, Consumer<Integer> consumer, Consumer<Boolean> consumer2, MediaCaptureCompat.OnErrorListener onErrorListener) {
        super(str, consumer, consumer2, onErrorListener);
    }

    private boolean isMotionPhotoInstantSlowMo(FileItemInterface fileItemInterface) {
        if (!PreferenceFeatures.OneUi6x.SUPPORT_MOTION_PHOTO_INSTANT_SLOW_MO || !fileItemInterface.isMotionPhoto()) {
            return false;
        }
        return true;
    }

    public String getFileInputStreamName(FileItemInterface fileItemInterface) {
        if (isMotionPhotoInstantSlowMo(fileItemInterface)) {
            return exportTempMotionPhotoVideo(fileItemInterface.getPath());
        }
        return fileItemInterface.getPath();
    }

    public void prepareInternal(FileItemInterface fileItemInterface, List<MediaPlayback> list, Consumer<Boolean> consumer) {
        int i2;
        this.mMediaCaptureCompat.setParameter(ErrorCodeConvertor.TEMP_AGENT_INVALID_MSISDN, 2);
        this.mMediaCaptureCompat.setParameter(ErrorCodeConvertor.TEMP_AGENT_INVALID_PARAMETER, 1);
        this.mMediaCaptureCompat.setParameter(ErrorCodeConvertor.TEMP_AGENT_SERVICE_DISABLED, 1);
        MediaCaptureCompat mediaCaptureCompat = this.mMediaCaptureCompat;
        if (fileItemInterface.isMotionPhoto()) {
            i2 = 81;
        } else {
            i2 = 89;
        }
        mediaCaptureCompat.setParameter(ErrorCodeConvertor.TEMP_AGENT_NOT_ACTIVATED, i2);
        long[] jArr = VideoPropData.of(fileItemInterface).instantSlowMoLastExecutedSection;
        VideoPropData.of(fileItemInterface).instantSlowMoLastExecutedSection = null;
        int i7 = (int) jArr[0];
        int i8 = (int) jArr[1];
        this.mMediaCaptureCompat.setStartEndTime(i7, i8);
        this.mMediaCaptureCompat.setDynamicViewingConfigurations(i7, i8, 0.25f);
        this.mMediaCaptureCompat.setOnPreparedListener(consumer);
        this.mMediaCaptureCompat.prepare();
    }

    public boolean supportInternal(FileItemInterface fileItemInterface) {
        if (!isMotionPhotoInstantSlowMo(fileItemInterface) || exportTempMotionPhotoVideo(fileItemInterface.getPath()) != null) {
            return super.supportInternal(fileItemInterface);
        }
        Log.w(this.TAG, "prepareInstantSlowMoCapture : failed export motion photo video!");
        return false;
    }
}
