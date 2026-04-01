package com.samsung.android.gallery.module.media;

import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.library.abstraction.MediaCaptureCompat;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayback;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import com.samsung.android.sdk.scs.base.StatusCodes;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaSuperSlowCaptureWorker extends MediaCaptureWorker {
    public MediaSuperSlowCaptureWorker(String str, Consumer<Integer> consumer, Consumer<Boolean> consumer2, MediaCaptureCompat.OnErrorListener onErrorListener) {
        super(str, consumer, consumer2, onErrorListener);
    }

    private int getSuperSlowEffect(MediaItem mediaItem) {
        int superSlowClipEffect = MediaItemUtil.getSuperSlowClipEffect(mediaItem);
        if (superSlowClipEffect == 2) {
            return 91;
        }
        if (superSlowClipEffect == 3) {
            return 92;
        }
        return 90;
    }

    public void prepareInternal(FileItemInterface fileItemInterface, List<MediaPlayback> list, Consumer<Boolean> consumer) {
        this.mMediaCaptureCompat.setParameter(ErrorCodeConvertor.TEMP_AGENT_NOT_ACTIVATED, getSuperSlowEffect((MediaItem) fileItemInterface));
        this.mMediaCaptureCompat.setParameter(ErrorCodeConvertor.TEMP_AGENT_INVALID_PARAMETER, 1);
        int[] iArr = VideoPropData.of(fileItemInterface).superSlowTitleRegion;
        if (iArr == null) {
            iArr = new int[]{0, VideoPropData.getVideoDuration(fileItemInterface)};
        } else {
            iArr[0] = Math.max(0, iArr[0] + StatusCodes.UNDEFINED);
            iArr[1] = Math.min(VideoPropData.getVideoDuration(fileItemInterface), iArr[1] + 1000);
        }
        String str = this.TAG;
        Log.d(str, "prepareSuperSlowCapture titleRegion=[" + iArr[0] + GlobalPostProcInternalPPInterface.SPLIT_REGEX + iArr[1] + "]");
        this.mMediaCaptureCompat.setStartEndTime(iArr[0], iArr[1]);
        this.mMediaCaptureCompat.setOnPreparedListener(consumer);
        this.mMediaCaptureCompat.prepare();
    }
}
