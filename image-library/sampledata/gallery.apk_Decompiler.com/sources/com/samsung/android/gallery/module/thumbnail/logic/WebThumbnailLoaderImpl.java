package com.samsung.android.gallery.module.thumbnail.logic;

import N2.j;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.graphics.BitmapSizeHolder;
import com.samsung.android.gallery.module.thumbnail.type.ReqInfo;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.utils.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class WebThumbnailLoaderImpl extends AbsThumbnailLoaderImpl {
    private Bitmap getWebItemBitmap(ThumbnailInterface thumbnailInterface) {
        InputStream inputStream;
        BitmapOptions adjustInSampleSize = new BitmapOptions(thumbnailInterface.getWidth(), thumbnailInterface.getHeight(), thumbnailInterface.getMimeType()).adjustInSampleSize(BitmapSizeHolder.size(), 0);
        try {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(thumbnailInterface.getPath()).openConnection();
            httpsURLConnection.setRequestMethod("GET");
            httpsURLConnection.connect();
            if (httpsURLConnection.getResponseCode() != 200) {
                String str = this.TAG;
                Log.e(str, "Server returned HTTP " + httpsURLConnection.getResponseCode() + " " + httpsURLConnection.getResponseMessage());
                return null;
            }
            inputStream = httpsURLConnection.getInputStream();
            Bitmap decodeStream = BitmapFactory.decodeStream(inputStream, (Rect) null, adjustInSampleSize);
            if (inputStream != null) {
                inputStream.close();
            }
            return decodeStream;
        } catch (IOException e) {
            j.r(e, new StringBuilder("getWebItemBitmap failed e="), this.TAG);
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public Bitmap getThumbnail(ReqInfo reqInfo) {
        if (TextUtils.isEmpty(reqInfo.path)) {
            return null;
        }
        Bitmap webItemBitmap = getWebItemBitmap(reqInfo.item);
        if (webItemBitmap != null && !webItemBitmap.isRecycled()) {
            return webItemBitmap;
        }
        String str = this.TAG;
        Log.e(str, "Fail to get bitmap = " + webItemBitmap);
        return null;
    }

    public boolean support(ThumbnailInterface thumbnailInterface) {
        if (thumbnailInterface.getStorageType() == StorageType.WebItem) {
            return true;
        }
        return false;
    }

    public String tag() {
        return "WebThumbnailLoaderImpl";
    }
}
