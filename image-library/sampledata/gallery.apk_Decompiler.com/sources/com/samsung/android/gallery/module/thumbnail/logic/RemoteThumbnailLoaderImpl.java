package com.samsung.android.gallery.module.thumbnail.logic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.thumbnail.type.ReqInfo;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemoteThumbnailLoaderImpl extends AbsThumbnailLoaderImpl {
    RemoteThumbnailLoader mRemoteLoader;

    private synchronized Bitmap getRemoteBitmap(ThumbnailInterface thumbnailInterface) {
        byte[] load;
        try {
            String substring = thumbnailInterface.getPath().substring(9);
            load = this.mRemoteLoader.load(substring.substring(0, substring.indexOf("/")), thumbnailInterface.getFileId());
        } catch (Exception e) {
            String str = this.TAG;
            Log.e(str, "getRemoteBitmap failed e=" + e.getMessage());
            return null;
        }
        return BitmapFactory.decodeByteArray(load, 0, load.length);
    }

    public Bitmap getThumbnail(ReqInfo reqInfo) {
        if (TextUtils.isEmpty(reqInfo.path)) {
            return null;
        }
        Bitmap remoteBitmap = getRemoteBitmap(reqInfo.item);
        if (remoteBitmap != null && !remoteBitmap.isRecycled()) {
            return remoteBitmap;
        }
        String str = this.TAG;
        Log.e(str, "Fail to get bitmap = " + remoteBitmap);
        return null;
    }

    public void setRemoteThumbnailLoader(RemoteThumbnailLoader remoteThumbnailLoader) {
        this.mRemoteLoader = remoteThumbnailLoader;
    }

    public boolean support(ThumbnailInterface thumbnailInterface) {
        if (thumbnailInterface.getStorageType() == StorageType.RemoteItem) {
            return true;
        }
        return false;
    }

    public String tag() {
        return "WebThumbnailLoaderImpl";
    }
}
