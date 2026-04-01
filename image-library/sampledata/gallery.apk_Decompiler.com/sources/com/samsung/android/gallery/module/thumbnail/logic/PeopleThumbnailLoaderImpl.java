package com.samsung.android.gallery.module.thumbnail.logic;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;
import com.samsung.android.gallery.module.graphics.BitmapOperator;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.thumbnail.type.ReqInfo;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.TimeTickLog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PeopleThumbnailLoaderImpl extends LocalThumbnailLoaderImpl {
    private Bitmap getCroppedBitmap(ReqInfo reqInfo, Bitmap bitmap) {
        RectF rectF;
        int i2;
        ThumbnailInterface item = reqInfo.getItem();
        if (item.isVideo()) {
            rectF = item.getCropRectRatio();
        } else {
            rectF = item.getRawCropRectRatio();
        }
        Rect smartCropRectWithLimit = RectUtils.getSmartCropRectWithLimit(rectF, bitmap.getWidth(), bitmap.getHeight());
        int min = Math.min(smartCropRectWithLimit.width(), smartCropRectWithLimit.height());
        if (min <= 0) {
            String str = this.TAG;
            Log.w(str, "check crop rect(" + smartCropRectWithLimit.width() + "x" + smartCropRectWithLimit.height() + ") : " + reqInfo);
            min = 256;
        }
        if (reqInfo.isDecodeExif() && min < 200) {
            reqInfo.addDecodeStatus(16);
        }
        BitmapOperator bitmapOperator = new BitmapOperator(bitmap);
        if (item.isVideo()) {
            i2 = 0;
        } else {
            i2 = item.getOrientation();
        }
        return bitmapOperator.rotate(i2).crop(rectF).resize(Math.min(min, 256)).faceCropFlag(getFaceCropFlag(item)).apply();
    }

    private int getFaceCropFlag(ThumbnailInterface thumbnailInterface) {
        Object tag = thumbnailInterface.getTag("is_rectangle_thumb");
        if (!(tag instanceof Boolean) || !((Boolean) tag).booleanValue()) {
            return 1;
        }
        return 2;
    }

    public int getCacheId(ThumbKind thumbKind) {
        return 4;
    }

    public long getFrameTime(ReqInfo reqInfo) {
        if (PreferenceFeatures.OneUi8x.SUPPORT_CREATURE_THUMB_FROM_VIDEO_FRAME && reqInfo.item.isVideo()) {
            Object tag = reqInfo.item.getTag("creature_frame_pos");
            if (tag instanceof Integer) {
                Integer num = (Integer) tag;
                if (num.intValue() >= 0 && num.intValue() <= reqInfo.item.getFileDuration()) {
                    return (long) num.intValue();
                }
            }
        }
        return super.getFrameTime(reqInfo);
    }

    public Bitmap getImageThumbnail(ReqInfo reqInfo) {
        TimeTickLog timeTickLog = new TimeTickLog("PeopleImageThumb");
        Bitmap imageThumbnail = super.getImageThumbnail(reqInfo);
        if (imageThumbnail == null) {
            String str = this.TAG;
            Log.e(str, "getImageThumbnail failed " + reqInfo);
            return null;
        }
        Bitmap croppedBitmap = getCroppedBitmap(reqInfo, imageThumbnail);
        if (Logger.THUMBNAIL) {
            timeTickLog.tock(0);
        }
        return croppedBitmap;
    }

    public int getInSampleSizeFromOriginal(ReqInfo reqInfo, BitmapOptions bitmapOptions) {
        Rect smartCropRectWithLimit = RectUtils.getSmartCropRectWithLimit(reqInfo.item.getCropRectRatio(), bitmapOptions.outWidth, bitmapOptions.outHeight);
        int i2 = 1;
        while ((Math.min(smartCropRectWithLimit.width(), smartCropRectWithLimit.height()) / 2) / i2 >= 256) {
            i2 *= 2;
        }
        return i2;
    }

    public Bitmap getVideoThumbnail(ReqInfo reqInfo) {
        if (FileUtils.isCloudVideoThumbCache(reqInfo.path)) {
            return getImageThumbnail(reqInfo);
        }
        TimeTickLog timeTickLog = new TimeTickLog("PeopleVideoThumb");
        Bitmap videoThumbnail = super.getVideoThumbnail(reqInfo);
        if (videoThumbnail == null) {
            String str = this.TAG;
            Log.e(str, "getVideoThumbnail failed " + reqInfo);
            return null;
        }
        Bitmap croppedBitmap = getCroppedBitmap(reqInfo, videoThumbnail);
        if (Logger.THUMBNAIL) {
            timeTickLog.tock(0);
        }
        return croppedBitmap;
    }

    public boolean support(ThumbnailInterface thumbnailInterface) {
        return thumbnailInterface.isCreature();
    }

    public String tag() {
        return "PeopleThumbnailLoaderImpl";
    }

    public void resizeBitmapFromOriginal(ReqInfo reqInfo, BitmapOptions bitmapOptions) {
    }
}
