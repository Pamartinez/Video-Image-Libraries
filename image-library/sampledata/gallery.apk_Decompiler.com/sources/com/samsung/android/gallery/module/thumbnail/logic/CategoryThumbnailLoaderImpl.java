package com.samsung.android.gallery.module.thumbnail.logic;

import android.graphics.Bitmap;
import android.graphics.RectF;
import com.samsung.android.gallery.module.graphics.BitmapOperator;
import com.samsung.android.gallery.module.thumbnail.type.ReqInfo;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CategoryThumbnailLoaderImpl extends LocalThumbnailLoaderImpl {
    public int getCacheId(ThumbKind thumbKind) {
        return 4;
    }

    public Bitmap getImageThumbnail(ReqInfo reqInfo) {
        RectF rectF;
        Bitmap imageThumbnail = super.getImageThumbnail(reqInfo);
        if (imageThumbnail == null) {
            String str = this.TAG;
            Log.e(str, "fail get category thumb : " + reqInfo);
            return null;
        }
        ThumbnailInterface item = reqInfo.getItem();
        if (item.isScenes()) {
            rectF = item.getRawCropRectRatio();
        } else {
            rectF = item.getCropRectRatio();
        }
        BitmapOperator rotate = new BitmapOperator(imageThumbnail).rotate(item.getOrientation());
        if (reqInfo.thumbKind != ThumbKind.SMALL_CROP_KIND) {
            rotate.crop(rectF, item.isScenes());
            if (!item.isRectThumbCategory()) {
                rotate.resize(ThumbKind.MEDIUM_KIND.size());
            }
        }
        return rotate.apply();
    }

    public Bitmap getVideoThumbnail(ReqInfo reqInfo) {
        if (reqInfo.path.endsWith(".jpg")) {
            return getImageThumbnail(reqInfo);
        }
        return super.getVideoThumbnail(reqInfo);
    }

    public boolean isImageInfoRequired(ReqInfo reqInfo) {
        if (reqInfo.item.isCloudOnly()) {
            return true;
        }
        return super.isImageInfoRequired(reqInfo);
    }

    public boolean support(ThumbnailInterface thumbnailInterface) {
        return thumbnailInterface.isScenes();
    }

    public String tag() {
        return "CategoryThumbnailLoaderImpl";
    }
}
