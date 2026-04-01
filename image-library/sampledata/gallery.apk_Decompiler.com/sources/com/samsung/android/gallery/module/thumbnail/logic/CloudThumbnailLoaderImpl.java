package com.samsung.android.gallery.module.thumbnail.logic;

import android.graphics.Bitmap;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.thumbnail.type.ReqInfo;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IFormat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CloudThumbnailLoaderImpl extends LocalThumbnailLoaderImpl {
    private boolean supportCloudPreviewPlay(String str) {
        if (str == null || !Features.isEnabled(Features.SUPPORT_NEW_SAMSUNG_CLOUD)) {
            return false;
        }
        return IFormat.FORMAT_MP4.equalsIgnoreCase(FileUtils.getExtension(str));
    }

    public Bitmap getVideoThumbnail(ReqInfo reqInfo) {
        if (!FileUtils.isCloudVideoThumbCache(reqInfo.path) || supportCloudPreviewPlay(reqInfo.path)) {
            return super.getVideoThumbnail(reqInfo);
        }
        return getImageThumbnail(reqInfo);
    }

    public boolean isImageInfoRequired(ReqInfo reqInfo) {
        return true;
    }

    public boolean support(ThumbnailInterface thumbnailInterface) {
        if (thumbnailInterface.getStorageType() == StorageType.Cloud) {
            return true;
        }
        return false;
    }

    public String tag() {
        return "CloudThumbnailLoaderImpl";
    }
}
