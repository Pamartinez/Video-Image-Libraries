package com.samsung.android.gallery.module.thumbnail.logic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.graphics.FaceDecoder;
import com.samsung.android.gallery.module.thumbnail.type.ReqInfo;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ContactThumbnailLoaderImpl extends LocalThumbnailLoaderImpl {
    public Bitmap getImageThumbnail(ReqInfo reqInfo) {
        String str;
        long currentTimeMillis = System.currentTimeMillis();
        ThumbnailInterface thumbnailInterface = reqInfo.item;
        Bitmap bitmap = null;
        if (TextUtils.isEmpty(reqInfo.path) || "null".equals(reqInfo.path)) {
            str = null;
        } else {
            str = reqInfo.path;
        }
        Boolean bool = (Boolean) thumbnailInterface.getTag("contact-tagged");
        if (bool == null || !bool.booleanValue() || str == null) {
            byte[] bArr = (byte[]) thumbnailInterface.getTag("contact-photo-stream");
            if (bArr != null) {
                bitmap = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, new BitmapFactory.Options());
            } else if (str != null) {
                bitmap = FaceDecoder.getContactFaceBitmap(AppResources.getAppContext(), str);
            }
        } else {
            bitmap = FaceDecoder.getFaceBitmap(thumbnailInterface.getCropRect(), Uri.parse(str).getPath(), thumbnailInterface.getOrientation());
        }
        if (bitmap != null) {
            bitmap = BitmapUtils.resizeAndCropCenter(bitmap, 128);
        }
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (currentTimeMillis2 > 100) {
            Log.d(this.TAG, "getImageThumbnail {" + str + "} +" + currentTimeMillis2);
        }
        return bitmap;
    }

    public Bitmap getVideoThumbnail(ReqInfo reqInfo) {
        return null;
    }

    public boolean support(ThumbnailInterface thumbnailInterface) {
        if (thumbnailInterface.getStorageType() == StorageType.ContactItem) {
            return true;
        }
        return false;
    }

    public String tag() {
        return "ContactThumbnailLoaderImpl";
    }
}
