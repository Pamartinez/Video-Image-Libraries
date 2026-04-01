package com.samsung.android.gallery.module.longexposure;

import N2.j;
import android.content.Context;
import com.samsung.android.gallery.module.data.MediaCacheLoader;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IFormat;
import com.sec.longexposure.ILongExposureCallback;
import com.sec.longexposure.LongExposure;
import java.io.File;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LongExposureHelper {
    private final LongExposure mLongExposure = new LongExposure();
    private final ILongExposureCallback mLongExposureCallback;
    /* access modifiers changed from: private */
    public String mLongExposurePhotoTempPath;

    public LongExposureHelper(final Consumer<String> consumer) {
        this.mLongExposureCallback = new ILongExposureCallback() {
            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onFinish$0(Consumer consumer) {
                consumer.accept(LongExposureHelper.this.mLongExposurePhotoTempPath);
            }

            public void onError(int i2) {
                Log.w((CharSequence) "LongExposureHelper", "onError", Integer.valueOf(i2));
            }

            public void onFinish() {
                ThreadUtil.postOnUiThread(new a(this, consumer));
            }

            public void onProgress(int i2) {
            }
        };
    }

    private static boolean hasVideoInfo(MediaItem mediaItem) {
        if (!supportedVideo(mediaItem)) {
            return false;
        }
        boolean hasSefFileTypes = mediaItem.hasSefFileTypes(3441);
        if (!MediaCacheLoader.SUPPORT_VIDEO_LONG_EXPOSURE || hasSefFileTypes) {
            return hasSefFileTypes;
        }
        return MediaCacheLoader.getInstance().isLongExposureCandidate(mediaItem);
    }

    public static boolean supportLongExposure(MediaItem mediaItem) {
        if (mediaItem == null || Features.isEnabled(Features.IS_REPAIR_MODE) || mediaItem.hasSefFileTypes(MediaDefs.Meta.SEF.SEF_KEY_TYPE_LONG_EXPOSURE_EFFECT_INFO)) {
            return false;
        }
        if (mediaItem.isShotMode("motion_photo") || hasVideoInfo(mediaItem)) {
            return true;
        }
        return false;
    }

    private static boolean supportedVideo(MediaItem mediaItem) {
        String mimeType = mediaItem.getMimeType();
        if (mimeType == null || !mimeType.endsWith(IFormat.FORMAT_MP4)) {
            return false;
        }
        if (mediaItem.getHeight() * mediaItem.getWidth() > 2073600 || ((long) mediaItem.getFileDuration()) < 1000 || ((long) mediaItem.getFileDuration()) > 60000 || mediaItem.isCloudOnly()) {
            return false;
        }
        return true;
    }

    public void cancel() {
        this.mLongExposure.cancel();
    }

    public String getLongExposureTempPath(MediaItem mediaItem) {
        String str;
        if (mediaItem.isVideo()) {
            str = IFormat.FORMAT_MP4;
        } else if (mediaItem.isHeif()) {
            str = IFormat.FORMAT_HEIC;
        } else {
            str = "jpg";
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(FileUtils.getFilesDir("longexp"));
        return j.f(sb2, File.separator, "longexposure.", str);
    }

    public int startLongExposure(Context context, MediaItem mediaItem) {
        this.mLongExposurePhotoTempPath = getLongExposureTempPath(mediaItem);
        return this.mLongExposure.start(context, mediaItem.getPath(), this.mLongExposurePhotoTempPath, this.mLongExposureCallback);
    }
}
