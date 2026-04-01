package com.samsung.android.gallery.module.thumbnail.logic;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.thumbnail.type.ReqInfo;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingThumbnailLoaderImpl extends LocalThumbnailLoaderImpl {
    public Bitmap getThumbnail(ReqInfo reqInfo) {
        if (TextUtils.isEmpty(reqInfo.path)) {
            return null;
        }
        return getImageThumbnail(reqInfo);
    }

    public boolean isImageInfoRequired(ReqInfo reqInfo) {
        return true;
    }

    public boolean support(ThumbnailInterface thumbnailInterface) {
        if (thumbnailInterface.getStorageType() == StorageType.Sharing) {
            return true;
        }
        return false;
    }

    public String tag() {
        return "SharingThumbnailLoaderImpl";
    }
}
