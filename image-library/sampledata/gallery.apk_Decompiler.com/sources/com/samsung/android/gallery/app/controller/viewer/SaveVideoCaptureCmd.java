package com.samsung.android.gallery.app.controller.viewer;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.media.MetadataManager;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.support.utils.FileNameBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.MemoryUtils;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.TimeUtil;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SaveVideoCaptureCmd extends SaveCaptureCmd {
    private long mDateTaken;
    private double mLatitude = MapUtil.INVALID_LOCATION;
    private double mLongitude = MapUtil.INVALID_LOCATION;

    public SaveVideoCaptureCmd() {
        this.TAG = "VideoCaptureCmd/" + this.mSessionId;
    }

    private Bitmap getPreviewBitmap(Bitmap bitmap) {
        return BitmapUtils.downsizeVideoBitmap(bitmap, ThumbKind.MEDIUM_KIND.size());
    }

    public void executeCapture(MediaItem mediaItem, Object... objArr) {
        Bitmap bitmap = objArr[1];
        if (bitmap == null) {
            Log.e(this.TAG, "Unable to operate. captured bitmap is null");
            sendCompleteEventIfFailed();
            return;
        }
        this.mDateTaken = mediaItem.getDateTaken() + 1000;
        this.mLatitude = mediaItem.getLatitude();
        this.mLongitude = mediaItem.getLongitude();
        Bitmap previewBitmap = getPreviewBitmap(bitmap);
        publishPreviewBitmap(mediaItem, previewBitmap);
        sendProcessingEvent();
        try {
            saveCapturedFileWithBitmap(mediaItem, bitmap);
        } catch (IOException e) {
            String str = this.TAG;
            Log.e(str, "saveCapturedFileWithBuffer failed e=" + e);
            sendCompleteEventIfFailed();
        }
        if (!bitmap.equals(previewBitmap)) {
            MemoryUtils.forceFree(bitmap);
        }
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_CAPTURE_FRAME.toString();
    }

    public String makeCapturedPath(boolean z) {
        return new FileNameBuilder(this.mOriginalPath).setDirectoryPath(StorageInfo.getDefault().videoCaptures).setExtension("jpg").buildUnique();
    }

    public void saveExif(String str, int i2, int i7) {
        String str2 = str;
        ExifUtils.changeDateLocation(str2, new String[]{TimeUtil.getExifDateTime(this.mDateTaken), TimeUtil.getSystemTimeZoneOffsetTag()}, this.mLatitude, this.mLongitude);
        String modelName = MetadataManager.getInstance().lambda$preload$0(this.mMediaItem).getModelName();
        if (!TextUtils.isEmpty(modelName)) {
            ExifUtils.changeModel(str2, modelName);
        }
    }

    public void updateThumbnail(MediaItem mediaItem) {
        super.updateThumbnail(mediaItem);
    }
}
